package CLIP.controller.Items;

import CLIP.dto.Items.RequestItem;
import CLIP.dto.Items.RequestItemStatus;
import CLIP.dto.Items.RequestLikeAction;
import com.example.demo_rest_api_test.dto.Items.*;
import CLIP.response.Response;
import CLIP.service.AwsS3.AwsS3Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import CLIP.service.Items.ItemsService;
import CLIP.service.Items.LikeActionService;
import CLIP.service.Items.UserTradeHistoryService;
import CLIP.service.Member.SecurityUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemsController {
    private final ItemsService itemsService;
    private final LikeActionService likeActionService;
    private final UserTradeHistoryService userTradeHistoryService;
    private final AwsS3Service awsS3Service;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public Response getItems() {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(itemsService.getItems(username));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customized-items/{itemSeq}")
    public Response getCustomizedItems(@PathVariable("itemSeq") Long itemSeq) {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(itemsService.getCustomizedItems(username, itemSeq));
    }

    // /items/ : 새로운 item 등록 (form data 형식)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/form-data")
    public Response addUserItem(@RequestPart("profileImg") List<MultipartFile> profileImg, @RequestParam("json") String jsonItem) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule());
        //객체
        RequestItem requestItem = objectMapper.readValue(jsonItem, new TypeReference<>() {
        });

        String username = SecurityUtil.getCurrentUsername();

        requestItem.setItemPictures(awsS3Service.uploadFile(profileImg).getFirst());

        return Response.success(itemsService.addUserItem(requestItem, username));
    }

    // (json 형식)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    public Response addUserItem(@RequestBody RequestItem requestItem){
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(itemsService.addUserItem(requestItem, username));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/status")
    public Response updateUserItemStatus(@RequestBody RequestItemStatus requestItemStatus) {
        String username = SecurityUtil.getCurrentUsername();
        itemsService.updateUserItemStatus(username, requestItemStatus.getItemSeq(), requestItemStatus.getItemStatus());
        return Response.success();
    }
    
    // 리스트를 직렬화하여 한 string으로 만드는 법
//    ObjectMapper mapper = new ObjectMapper();
//    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//    requestItem.setItemPictures(mapper.writeValueAsString(awsS3Service.uploadFile(이미지리스트)));

    // 복호화
//    String json = "[\"사과\",\"바나나\",\"체리\"]";
//    List<String> myList = objectMapper.readValue(json, new TypeReference<List<String>>() {});



    // /items/{itemSeq} : 해당 아이템의 정보를 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{itemSeq}")
    public Response getItem(@PathVariable("itemSeq") Long itemSeq) {
        return Response.success(itemsService.getItem(itemSeq));
    }

    // /items/member : 해당 UID를 가진 유저가 올린 아이템 정보 모두 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/member")
    public Response getUserItems(){
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(itemsService.getUserItems(username));
    }

    // /items/likeAction : 스와이프 좋아요 액션 저장 및 매칭 여부 확인하고 매칭이면 좋아요 테이블에서 해당되는 아이템 고유번호로 모두 삭제
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/likeAction")
    public Response addActionLog(@RequestBody RequestLikeAction requestLikeAction){
        //action 저장
        String result = likeActionService.addActionLog(requestLikeAction);

        //matching 확인
        String match = likeActionService.matchingOrNot(requestLikeAction);

        //저장 완료 여부
        if("success".equalsIgnoreCase(result)) {
            // matching 되면 match 리턴 아니면 success
            if("success".equalsIgnoreCase(match)){
                // 매칭 히스토리 테이블에 저장하기
                userTradeHistoryService.addTradeHistory(requestLikeAction, LocalDateTime.now());
                // 좋아요 테이블에서 매칭된 아이템들의 좋아요 정보 모두 삭제
                likeActionService.deleteLikeActions(requestLikeAction.getLikeSenderItemSeq(), requestLikeAction.getLikeReceiverItemSeq());
                // 반대도 삭제
                likeActionService.deleteLikeActions(requestLikeAction.getLikeReceiverItemSeq(), requestLikeAction.getLikeSenderItemSeq());
                return Response.success("match");
            }
            return Response.success("success");
        } else {
            return Response.success("fail");
        }
    }

//    @PostMapping("/likeAction/matching")
//    public String matchingOrNot(@RequestBody RequestLikeAction requestLikeAction){
//        String result = likeActionService.matchingOrNot(requestLikeAction);
//        if("success".equalsIgnoreCase(result)) {
//            return "success";
//        } else {
//            return "fail";
//        }
//    }
}
