package CLIP.repository.Board;

import CLIP.Entity.Board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllBy();

    boolean existsByBoardName(String boardName);

    Optional<Board> findByBoardSeq(Long boardSeq);

    @Modifying(clearAutomatically = true)
    @Query("update Board m set m.boardPicture = :boardPicture where m.boardUploader = :username")
    void updateBoardPicture(String username, String boardPicture);

    @Modifying(clearAutomatically = true)
    @Query("update Board m set m.boardRecentPost = :boardRecentPost where m.boardUploader = :username")
    void updateBoardRecentPost(String username, LocalDateTime boardRecentPost);

    @Modifying(clearAutomatically = true)
    @Query("update Board m set m.boardName = :boardName, m.boardDescription = :boardDescription, m.boardCategory = :boardCategory where m.boardSeq = :boardSeq")
    void updateBoard(Long boardSeq, String boardName, String boardDescription, String boardCategory);

    @Query("SELECT m.boardName FROM Board m WHERE m.boardSeq = :boardSeq")
    String findBoardUploaderbyBoardSeq(Long boardSeq);

    @Query("SELECT m.boardName FROM Board m WHERE m.boardSeq = :boardSeq")
    String findBoardNamebyBoardSeq(Long boardSeq);

    @Query("SELECT m.boardDescription FROM Board m WHERE m.boardSeq = :boardSeq")
    String findBoardDescriptionbyBoardSeq(Long boardSeq);

    @Query("SELECT m.boardCategory FROM Board m WHERE m.boardSeq = :boardSeq")
    String findBoardCategorybyBoardSeq(Long boardSeq);

    @Query("SELECT m.boardPicture FROM Board m WHERE m.boardSeq = :boardSeq")
    String findBoardPicturebyBoardSeq(Long boardSeq);

}
