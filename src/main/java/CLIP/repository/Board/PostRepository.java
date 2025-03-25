package CLIP.repository.Board;

import CLIP.Entity.Board.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT m FROM Post m WHERE m.boardSeq = :boardSeq")
    List<Post> findAllByBoardSeq(Long boardSeq);

    boolean existsByBoardSeqAndPostName(int boardSeq, String postName);
}
