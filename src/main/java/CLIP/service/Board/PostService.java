package CLIP.service.Board;

import CLIP.dto.Board.Post.PostDTO;
import CLIP.dto.Board.Post.RequestPost;
import CLIP.dto.Board.Post.ResponsePosts;
import CLIP.repository.Board.BoardRepository;
import CLIP.repository.Board.PostRepository;
import CLIP.service.AwsS3.AwsS3Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final AwsS3Service awsS3Service;

    @Transactional
    public ResponsePosts getPosts(Long boardSeq) { return new ResponsePosts(postRepository.findAllByBoardSeq(boardSeq)); }

    @Transactional
    public PostDTO addPost(RequestPost requestPost, String username, List<MultipartFile> postItemPictures) throws JsonProcessingException {
        if(postRepository.existsByBoardSeqAndPostName(requestPost.getBoardSeq(), requestPost.getPostUploader())) {
            throw new IllegalArgumentException("이미 사용 중인 이름입니다.");
        }
        //form-data 형식에서 받아온 영상리스트를 직렬화하여 한 String으로 만드는 법(게시물 등록에 사용하자!!)
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        requestPost.setPostItemPictures(mapper.writeValueAsString(awsS3Service.uploadFile(postItemPictures)));

        boardRepository.updateBoardRecentPost(username, LocalDateTime.now());

        return new PostDTO(postRepository.save(requestPost.toEntity(requestPost.getBoardSeq() ,username)));
    }

    @Transactional
    public PostDTO getPost(Long postSeq) {
        return new PostDTO(postRepository.getReferenceById(postSeq));
    }

}
