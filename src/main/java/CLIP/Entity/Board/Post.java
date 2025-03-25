package CLIP.Entity.Board;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Posts_TB")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postSeq;

    private int boardSeq;

    private String postUploader;

    private String postName;

    private int postItemPrice;

    private int postItemCondition;

    private String postItemDescription;

    private String postItemPictures;

    private LocalDateTime postTimestamp;

    private int postLikeNumbers;

    private double latitude;

    private double longitude;

    @Builder
    public Post(int boardSeq, String postUploader, String postName, int postItemPrice, int postItemCondition, String postItemDescription, String postItemPictures, LocalDateTime postTimestamp, int postLikeNumbers, double latitude, double longitude) {
        this.boardSeq = boardSeq;
        this.postUploader = postUploader;
        this.postName = postName;
        this.postItemPrice = postItemPrice;
        this.postItemCondition = postItemCondition;
        this.postItemDescription = postItemDescription;
        this.postItemPictures = postItemPictures;
        this.postTimestamp = postTimestamp;
        this.postLikeNumbers = postLikeNumbers;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
