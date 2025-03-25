package CLIP.controller.Board;

import CLIP.dto.Board.Comment.RequestComment;
import CLIP.response.Response;
import CLIP.service.AwsS3.AwsS3Service;
import CLIP.service.Board.CommentService;
import CLIP.service.Board.PostService;
import CLIP.service.Member.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    private final AwsS3Service awsS3Service;

    //댓글 관련 api
    //특정 게시물에 달린 댓글 목록 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{post-id}/comments")
    public Response getComments(@PathVariable("post-id") Long postSeq) {
        return Response.success(commentService.getComments(postSeq));
    }

    //특정 게시물에 댓글 등록
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{post-id}/comments")
    public Response addComments(@PathVariable("post-id") int postSeq, @RequestBody RequestComment requestComment) {
        String username = SecurityUtil.getCurrentUsername();
        return Response.success(commentService.addComment(postSeq, username, requestComment));
    }

    //특정 게시물 특정 댓글 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{post-id}/comments/{comment-id}")
    public Response getComment(@PathVariable("post-id") Long postSeq, @PathVariable("comment-id") Long commentSeq) {
        return Response.success();
    }

    //특정 게시물 특정 댓글 수정
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{post-id}/comments/{comment-id}")
    public Response patchComment(@PathVariable("post-id") Long postSeq, @PathVariable("comment-id") Long commentSeq) {
        return Response.success();
    }

    //특정 게시물 특정 댓글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public Response deleteComment(@PathVariable("post-id") Long postSeq, @PathVariable("comment-id") Long commentSeq) {
        return Response.success();
    }
}
