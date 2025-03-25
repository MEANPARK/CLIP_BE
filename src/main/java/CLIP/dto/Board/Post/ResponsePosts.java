package CLIP.dto.Board.Post;

import CLIP.Entity.Board.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponsePosts {
    List<ResponsePost> postList;

    @Builder
    public ResponsePosts(List<Post> posts) {
        this.postList = posts.stream()
                .map(post -> ResponsePost.builder()
                        .post(post)
                        .build())
                .collect(Collectors.toList());
    }
}
