package CLIP.dto.Board;

import CLIP.Entity.Board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@NoArgsConstructor
public class ResponseBoards {
    List<ResponseBoard> boardList;

    @Builder
    public ResponseBoards(List<Board> boards) {
        this.boardList = boards.stream()
                .map(board -> ResponseBoard.builder()
                        .board(board)
                        .build())
                .collect(Collectors.toList());
    }
}
