package CLIP.dto.Items;

import CLIP.Entity.Items.Items;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ResponseItems {
    List<ItemDTO> itemDTOList;

    @Builder
    public ResponseItems(List<Items> itemsList) {
        this.itemDTOList = itemsList.stream()
                .map(items -> ItemDTO.builder()
                        .items(items)
                        .build())
                .collect(Collectors.toList());
    }
//    @Builder
//    public ResponseItems(List<Items> itemsList) {
//        this.itemDTOList = itemsList.stream()
//                .map(items -> ItemDTO.builder()
//                        .itemSeq(items.getItemSeq())
//                        .itemsCategorySeq(items.getItemsCategorySeq())
//                        .itemUpLoaderUuid(items.getItemUpLoaderUuid())
//                        .itemName(items.getItemName())
//                        .itemPrice(items.getItemPrice())
//                        .itemCondition(items.getItemCondition())
//                        .itemDescription(items.getItemDescription())
//                        .itemPictures(items.getItemPictures())
//                        .itemStatus(items.getItemStatus())
//                        .build())
//                .collect(Collectors.toList());
//    }
}
