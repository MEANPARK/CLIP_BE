package CLIP.dto.Items;

import CLIP.Entity.Items.LikeAction;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseLikeAction {
    private String likeSenderUUID;
    private String likeReceiverUUID;
    private int likeSenderItemSeq;
    private int likeReceiverItemSeq;

    public LikeAction toEntity(){
        return LikeAction.builder()
                .likeSenderUUID(likeSenderUUID)
                .likeReceiverUUID(likeReceiverUUID)
                .likeSenderItemSeq(likeSenderItemSeq)
                .likeReceiverItemSeq(likeReceiverItemSeq)
                .build();
    }
}
