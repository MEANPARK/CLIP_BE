package CLIP.dto.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestUpdateBoard {
    private String boardName;
    private String boardDescription;
    private String boardCategory;
}
