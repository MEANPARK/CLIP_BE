package CLIP.dto.Items;

import CLIP.Entity.Items.LikeAction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeActionDTO {
    private String likeSenderUUID;
    private String likeReceiverUUID;
    private int likeSenderItemSeq;
    private int likeReceiverItemSeq;

    @Builder
    public LikeActionDTO(LikeAction likeAction) {
        this.likeSenderUUID = likeAction.getLikeSenderUUID();
        this.likeReceiverUUID = likeAction.getLikeReceiverUUID();
        this.likeSenderItemSeq = likeAction.getLikeSenderItemSeq();
        this.likeReceiverItemSeq = likeAction.getLikeReceiverItemSeq();
    }
}
