package CLIP.dto.Board.Comment;

import CLIP.Entity.Board.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RequestComment {
    private int postSeq;
    private String commentUploader;
    private String commentText;
    private LocalDateTime commentTimestamp;

    public Comment toEntity(int postSeq, String username) {
        return Comment.builder()
                .postSeq(postSeq)
                .commentUploader(username)
                .commentText(commentText)
                .commentTimestamp(LocalDateTime.now())
                .build();
    }
}
