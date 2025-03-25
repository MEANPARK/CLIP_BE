package CLIP.controller.Board;

import CLIP.dto.Board.Post.RequestPost;
import CLIP.dto.Board.RequestBoard;
import CLIP.dto.Board.RequestUpdateBoard;
import CLIP.response.Response;
import CLIP.service.Board.BoardService;
import CLIP.service.Board.CommentService;
import CLIP.service.Board.PostService;
import CLIP.service.Member.SecurityUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;

    //모든 게시판 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public Response getBoards() {
        return Response.success(boardService.getBoards());
    }

    //새로운 게시판 등록(form-data형식)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    public Response addBoards(@RequestPart("boardPicture") List<MultipartFile> boardPicture, @RequestParam("board") String jsonItem) throws JsonProcessingException {
        String username = SecurityUtil.getCurrentUsername();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule());
        //json을 객체로 변환
        RequestBoard requestBoard = objectMapper.readValue(jsonItem, new TypeReference<>() {
        });

        return Response.success(boardService.addBoard(requestBoard, username, boardPicture));
    }

    //특정 게시판 정보 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{board-id}")
    public Response getBoard(@PathVariable("board-id") Long boardSeq) {
        return Response.success(boardService.getBoard(boardSeq));
    }

    //특정 게시판 수정
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{board-id}")
    public Response patchBoard(@PathVariable("board-id") Long boardSeq, @RequestBody RequestUpdateBoard requestUpdateBoard){
        String username = SecurityUtil.getCurrentUsername();
        boardService.updateBoard(username, boardSeq, requestUpdateBoard);
        return Response.success();
    }
    //특정 게시판 수정(이미지)
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{board-id}/boardPicture")
    public Response patchBoardPicture(@PathVariable("board-id") Long boardSeq, @RequestPart("boardPicture") List<MultipartFile> boardPicture){
        String username = SecurityUtil.getCurrentUsername();
        boardService.updateBoardPicture(username, boardSeq, boardPicture);
        return Response.success();
    }


    //특정 게시판 삭제 (게시물, 댓글 모두 삭제하기!!)
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{board-id}")
    public Response deleteBoard(@PathVariable("board-id") Long boardSeq) {
        return Response.success();
    }


    //게시물 관련 api
    //특정 게시판의 게시물 모두 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{board-id}/posts")
    public Response getPosts(@PathVariable("board-id") Long boardSeq) {
        return Response.success(postService.getPosts(boardSeq));
    }

    //특정 게시판에 새로운 게시물 등록
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{board-id}/posts")
    public Response addPost(@PathVariable("board-id") int boardSeq, @RequestPart("postItemPictures") List<MultipartFile> postItemPictures, @RequestParam("post") String jsonItem) throws JsonProcessingException {
        String username = SecurityUtil.getCurrentUsername();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule());
        //json을 객체로 변환
        RequestPost requestPost = objectMapper.readValue(jsonItem, new TypeReference<>() {
        });
        requestPost.setBoardSeq(boardSeq);

        return Response.success(postService.addPost(requestPost, username, postItemPictures));
    }

    //특정 게시판의 특정 게시물 반환
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{board-id}/posts/{post-id}")
    public Response getPost(@PathVariable("board-id") Long boardSeq, @PathVariable("post-id") Long postSeq) {
        return Response.success(postService.getPost(postSeq));
    }

    //특정 게시판의 특정 게시물 수정
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{board-id}/posts/{post-id}")
    public Response patchPost(@PathVariable("board-id") Long boardSeq, @PathVariable("post-id") Long postSeq) {
        return Response.success();
    }

    //특정 게시판의 특정 게시물 삭제 (댓글도 같이 삭제)
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{board-id}/posts/{post-id}")
    public Response deletePost(@PathVariable("board-id") Long boardSeq, @PathVariable("post-id") Long postSeq) {
        return Response.success();
    }
}
