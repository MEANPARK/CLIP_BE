package CLIP.service.Items;

import CLIP.Entity.Items.Items;
import CLIP.dto.Items.ItemDTO;
import CLIP.dto.Items.RequestItem;
import CLIP.dto.Items.ResponseItems;
import CLIP.repository.Items.ItemsRepository;
import CLIP.service.PythonExecutor.PythonExecutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final PythonExecutorService pythonExecutorService;

    @Transactional
    public ResponseItems getUserItems(String itemUpLoaderUuid){
        return new ResponseItems(itemsRepository.findByItemUpLoaderUuid(itemUpLoaderUuid));
    }

    @Transactional
    public ResponseItems getItems(String itemUpLoaderUuid) {
        return new ResponseItems(itemsRepository.findAllByItemUpLoaderUuid(itemUpLoaderUuid, "before"));
    }

    @Transactional
    public ResponseItems getCustomizedItems(String itemUpLoaderUuid, Long itemSeq) {
        List<String> stringCustomizedItems = pythonExecutorService.executePythonScript(itemSeq.toString());
        // String 리스트를 Long 리스트로 변환
        if (stringCustomizedItems == null) {
            // 적절한 예외를 던지거나 빈 리스트를 반환
            throw new RuntimeException("Python 스크립트 실행 중 오류가 발생했습니다.");
            // 또는 빈 리스트를 반환
            // stringCustomizedItems = Collections.emptyList();
        }

        List<Long> customizedItems = stringCustomizedItems.stream()
                .map(Long::valueOf)
                .toList();

        ResponseItems responseItems = new ResponseItems(itemsRepository.findByItemSeqIn(itemUpLoaderUuid, customizedItems, "before"));

        responseItems.setItemDTOList(responseItems.getItemDTOList().stream()
                .sorted(Comparator.comparingInt(i -> customizedItems.indexOf(i.getItemSeq())))
                .collect(Collectors.toList()));

        return responseItems;
    }

    @Transactional
    public ItemDTO getItem(Long itemSeq){
        Items items = itemsRepository.findById(itemSeq).orElseThrow(() -> new IllegalArgumentException("no such data"));
        ItemDTO itemDTO = new ItemDTO(items);
        return itemDTO;
    }
    @Transactional
    public Long addUserItem(RequestItem requestItem, String username){
        return itemsRepository.save(requestItem.toEntity(username)).getItemSeq();
    }

    @Transactional
    public void updateUserItemStatus(String username, int itemSeq, String itemStatus) {
        itemsRepository.updateStatusBy(username, itemSeq, itemStatus);
    }

//    @Transactional
//    public String addUserItem(RequestItem requestItem, String username){
//        itemsRepository.save(requestItem.toEntity(username));
//        return "success";
//    }
 }
