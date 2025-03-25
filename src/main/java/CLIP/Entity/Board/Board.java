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
@Table(name = "Board_TB")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSeq;

    private String boardUploader;

    private String boardName;

    private String boardDescription;

    private String boardCategory;

    private LocalDateTime boardRecentPost;

    private int boardPostsNumbers;

    private String boardPicture;

    @Builder
    public Board(String boardUploader, String boardName, String boardDescription, String boardCategory, LocalDateTime boardRecentPost, int boardPostsNumbers, String boardPicture) {
        this.boardUploader = boardUploader;
        this.boardName = boardName;
        this.boardDescription = boardDescription;
        this.boardCategory = boardCategory;
        this.boardRecentPost = boardRecentPost;
        this.boardPostsNumbers = boardPostsNumbers;
        this.boardPicture = boardPicture;
    }

}
