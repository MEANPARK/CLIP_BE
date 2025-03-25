package CLIP.dto.Board.Post;

import CLIP.Entity.Board.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RequestPost {
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

    public Post toEntity(int boardSeq, String username) {
        return Post.builder()
                .boardSeq(boardSeq)
                .postUploader(username)
                .postName(postName)
                .postItemPrice(postItemPrice)
                .postItemCondition(postItemCondition)
                .postItemDescription(postItemDescription)
                .postItemPictures(postItemPictures)
                .postTimestamp(LocalDateTime.now())
                .postLikeNumbers(postLikeNumbers)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
