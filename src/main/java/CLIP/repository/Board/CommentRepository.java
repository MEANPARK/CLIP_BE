package CLIP.repository.Board;

import CLIP.Entity.Board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT m FROM Comment m WHERE m.postSeq = :postSeq")
    List<Comment> findAllByPostSeq(Long postSeq);
}
