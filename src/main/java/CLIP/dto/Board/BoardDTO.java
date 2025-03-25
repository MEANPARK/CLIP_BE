package CLIP.dto.Board;

import CLIP.Entity.Board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardDTO {
    private Long boardSeq;
    private String boardUploader;
    private String boardName;
    private String boardDescription;
    private String boardCategory;
    private LocalDateTime boardRecentPost;
    private int boardPostsNumbers;
    private String boardPicture;

    @Builder
    public BoardDTO(Board board) {
        this.boardSeq = board.getBoardSeq();
        this.boardUploader = board.getBoardUploader();
        this.boardName = board.getBoardName();
        this.boardDescription = board.getBoardDescription();
        this.boardCategory = board.getBoardCategory();
        this.boardRecentPost = board.getBoardRecentPost();
        this.boardPostsNumbers = board.getBoardPostsNumbers();
        this.boardPicture = board.getBoardPicture();
    }
}
