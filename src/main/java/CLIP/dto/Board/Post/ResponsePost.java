package CLIP.dto.Board.Post;

import CLIP.Entity.Board.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponsePost {
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
    public ResponsePost(Post post){
        this.postSeq = post.getPostSeq();
        this.boardSeq = post.getBoardSeq();
        this.postUploader = post.getPostUploader();
        this.postName = post.getPostName();
        this.postItemPrice = post.getPostItemPrice();
        this.postItemCondition = post.getPostItemCondition();
        this.postItemDescription = post.getPostItemDescription();
        this.postItemPictures = post.getPostItemPictures();
        this.postTimestamp = post.getPostTimestamp();
        this.postLikeNumbers = post.getPostLikeNumbers();
        this.latitude = post.getLatitude();
        this.longitude =post.getLongitude();
    }
}
