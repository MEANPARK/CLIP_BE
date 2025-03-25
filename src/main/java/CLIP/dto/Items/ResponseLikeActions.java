package CLIP.dto.Items;


import CLIP.Entity.Items.LikeAction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseLikeActions {
    List<LikeActionDTO> likeActionDTOList;

    @Builder
    public ResponseLikeActions(List<LikeAction> likeActionList) {
        this.likeActionDTOList = likeActionList.stream()
                .map(likeAction -> LikeActionDTO.builder()
                        .likeAction(likeAction)
                        .build())
                .collect(Collectors.toList());
    }
}
