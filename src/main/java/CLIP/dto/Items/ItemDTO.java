package CLIP.dto.Items;

import CLIP.Entity.Items.Items;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemDTO {
    private Long itemSeq;
    private int itemsCategorySeq;
    private String itemUpLoaderUuid;
    private String itemName;
    private int itemPrice;
    private int itemCondition;
    private String itemDescription;
    private String itemPictures;
    private String itemStatus;
    private double latitude;
    private double longitude;

    @Builder
    public ItemDTO(Items items){
        this.itemSeq = items.getItemSeq();
        this.itemsCategorySeq = items.getItemsCategorySeq();
        this.itemUpLoaderUuid = items.getItemUpLoaderUuid();
        this.itemName = items.getItemName();
        this.itemPrice = items.getItemPrice();
        this.itemCondition = items.getItemCondition();
        this.itemDescription = items.getItemDescription();
        this.itemPictures = items.getItemPictures();
        this.itemStatus = items.getItemStatus();
        this.latitude = items.getLatitude();
        this.longitude = items.getLongitude();
    }
}
