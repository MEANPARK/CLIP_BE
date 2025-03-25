package CLIP.dto.Board.Comment;

import CLIP.Entity.Board.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentDTO {
    private int commentSeq;
    private int postSeq;
    private String commentUploader;
    private String commentText;
    private LocalDateTime commentTimestamp;

    @Builder
    public CommentDTO(Comment comment) {
        this.commentSeq = comment.getCommentSeq();
        this.postSeq = comment.getPostSeq();
        this.commentUploader = comment.getCommentUploader();
        this.commentText = comment.getCommentText();
        this.commentTimestamp = comment.getCommentTimestamp();
    }
}
