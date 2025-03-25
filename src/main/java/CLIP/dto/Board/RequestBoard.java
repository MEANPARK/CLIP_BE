package CLIP.dto.Board;

import CLIP.Entity.Board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RequestBoard {
    private String boardUploader;
    private String boardName;
    private String boardDescription;
    private String boardCategory;
    private LocalDateTime boardRecentPost;
    private int boardPostsNumbers;
    private String boardPicture;

    public Board toEntity(String username) {
        return Board.builder()
                .boardUploader(username)
                .boardName(boardName)
                .boardDescription(boardDescription)
                .boardCategory(boardCategory)
                .boardRecentPost(boardRecentPost)
                .boardPostsNumbers(boardPostsNumbers)
                .boardPicture(boardPicture)
                .build();
    }
}
