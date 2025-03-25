package CLIP.dto.Board.Comment;

import CLIP.Entity.Board.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseComments {
    List<ResponseComment> commentList;

    @Builder
    public ResponseComments(List<Comment> comments) {
        this.commentList = comments.stream()
                .map(comment -> ResponseComment.builder()
                        .comment(comment)
                        .build())
                .collect(Collectors.toList());
    }
}
