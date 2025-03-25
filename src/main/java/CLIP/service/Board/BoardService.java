package CLIP.service.Board;

import CLIP.dto.Board.BoardDTO;
import CLIP.dto.Board.RequestBoard;
import CLIP.dto.Board.RequestUpdateBoard;
import CLIP.dto.Board.ResponseBoards;
import CLIP.repository.Board.BoardRepository;
import CLIP.service.AwsS3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final AwsS3Service awsS3Service;
    @Transactional
    public ResponseBoards getBoards() {
        return new ResponseBoards(boardRepository.findAllBy());
    }

    @Transactional
    public BoardDTO addBoard(RequestBoard requestBoard, String username, List<MultipartFile> boardPicture) {
        if (boardRepository.existsByBoardName(requestBoard.getBoardName())) {
            throw new IllegalArgumentException("이미 사용 중인 이름입니다.");
        }
        //이미지 하나만 사용
        requestBoard.setBoardPicture(awsS3Service.uploadFile(boardPicture).getFirst());

        return new BoardDTO(boardRepository.save(requestBoard.toEntity(username)));
    }

    @Transactional
    public BoardDTO getBoard(Long boardSeq) {
        return new BoardDTO(boardRepository.getReferenceById(boardSeq));
    }

    @Transactional
    public void updateBoard(String username, Long boardSeq, RequestUpdateBoard requestUpdateBoard) {
        String boardUploader = boardRepository.findBoardUploaderbyBoardSeq(boardSeq);
        if(boardUploader.equals(username)){
            return;
        }
        String boardName = requestUpdateBoard.getBoardName();
        String boardDescription = requestUpdateBoard.getBoardDescription();
        String boardCategory = requestUpdateBoard.getBoardCategory();

        if(requestUpdateBoard.getBoardName() == null || requestUpdateBoard.getBoardName().isEmpty()) {
            boardName = boardRepository.findBoardNamebyBoardSeq(boardSeq);
        }
        if(requestUpdateBoard.getBoardDescription() == null || requestUpdateBoard.getBoardDescription().isEmpty()) {
            boardDescription = boardRepository.findBoardDescriptionbyBoardSeq(boardSeq);
        }
        if(requestUpdateBoard.getBoardCategory() == null || requestUpdateBoard.getBoardCategory().isEmpty()) {
            boardCategory = boardRepository.findBoardCategorybyBoardSeq(boardSeq);
        }

        boardRepository.updateBoard(boardSeq, boardName, boardDescription, boardCategory);
    }

    @Transactional
    public void updateBoardPicture(String username, Long boardSeq, List<MultipartFile> boardPicture) {
        String boardUploader = boardRepository.findBoardUploaderbyBoardSeq(boardSeq);
        if(boardUploader.equals(username)){
            return;
        }
        String boardPictureUrl = boardRepository.findBoardPicturebyBoardSeq(boardSeq);
        String boardImg = boardPictureUrl.substring(boardPictureUrl.lastIndexOf("/") + 1);
        awsS3Service.deleteFile(boardImg);

        boardRepository.updateBoardPicture(username, awsS3Service.uploadFile(boardPicture).getFirst());
    }
}
