package CLIP.dto.Items;

import CLIP.Entity.Items.Items;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestItem {
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
    public RequestItem(int itemsCategorySeq, String itemUpLoaderUuid, String itemName, int itemPrice, int itemCondition, String itemDescription, String itemPictures, String itemStatus, double latitude, double longitude){
        this.itemsCategorySeq = itemsCategorySeq;
        this.itemUpLoaderUuid = itemUpLoaderUuid;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCondition = itemCondition;
        this.itemDescription = itemDescription;
        this.itemPictures = itemPictures;
        this.itemStatus = itemStatus;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Items toEntity(String username){
        return Items.builder()
                .itemsCategorySeq(itemsCategorySeq)
                .itemUpLoaderUuid(username)
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemCondition(itemCondition)
                .itemDescription(itemDescription)
                .itemPictures(itemPictures)
                .itemStatus(itemStatus)
                .build();
    }
}
