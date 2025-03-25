package CLIP.controller.Chat;

import CLIP.dto.Chat.RequestChatRoom;
import CLIP.response.Response;
import CLIP.service.Chat.ChatRoomService;
import CLIP.service.Chat.MessageService;
import CLIP.service.Member.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final Environment env;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public Response rooms(Model mode){
        return Response.success("/chat/room");
    }

    //Header에 토큰에서 username을 가져와서 해당 User가 참여하고 있는 채팅방 모두 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/List")
    public Response getChatRooms() {
        return Response.success(chatRoomService.getChatRooms(SecurityUtil.getCurrentUsername()));
    }

    //두 유저가 참여하고 있는 채팅방 생성
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    public Response creatChatRoom(@RequestBody RequestChatRoom requestChatRoom) {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(chatRoomService.createChatRoom(requestChatRoom));
    }

    //채팅방 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{chat_room_UUID}")
    public Response deleteChatRoom(@PathVariable("chat_room_UUID") String chatRoomUUID) {
        //roomName의 모든 message 내역 테이블에서도 지우기
        messageService.deleteMessages(chatRoomUUID);
        //
        chatRoomService.deleteChatRoom(chatRoomUUID);

        return Response.success();
    }
}
