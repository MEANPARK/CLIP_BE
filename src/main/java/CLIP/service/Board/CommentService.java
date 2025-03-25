package CLIP.service.Board;

import CLIP.dto.Board.Comment.CommentDTO;
import CLIP.dto.Board.Comment.RequestComment;
import CLIP.dto.Board.Comment.ResponseComments;
import CLIP.repository.Board.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public ResponseComments getComments(Long postSeq) {
        return new ResponseComments(commentRepository.findAllByPostSeq(postSeq));
    }

    @Transactional
    public CommentDTO addComment(int postSeq, String username, RequestComment requestComment) {
        return new CommentDTO(commentRepository.save(requestComment.toEntity(postSeq, username)));
    }
}
