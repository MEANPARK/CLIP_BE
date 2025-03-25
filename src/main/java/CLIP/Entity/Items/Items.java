package CLIP.Entity.Items;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Items_TB")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public Items(int itemsCategorySeq, String itemUpLoaderUuid, String itemName, int itemPrice, int itemCondition, String itemDescription, String itemPictures, String itemStatus, double latitude, double longitude){
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
}
