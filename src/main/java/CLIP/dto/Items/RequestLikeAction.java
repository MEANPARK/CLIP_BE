package CLIP.dto.Items;

import CLIP.Entity.Items.LikeAction;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLikeAction {
    private String likeSenderUUID;
    private String likeReceiverUUID;
    private int likeSenderItemSeq;
    private int likeReceiverItemSeq;

    @Builder
    public RequestLikeAction(String likeSenderUUID, String likeReceiverUUID, int likeSenderItemSeq, int likeReceiverItemSeq){
        this.likeSenderUUID = likeSenderUUID;
        this.likeReceiverUUID = likeReceiverUUID;
        this.likeSenderItemSeq = likeSenderItemSeq;
        this.likeReceiverItemSeq = likeReceiverItemSeq;
    }

    public LikeAction toEntity(){
        return LikeAction.builder()
                .likeSenderUUID(likeSenderUUID)
                .likeReceiverUUID(likeReceiverUUID)
                .likeSenderItemSeq(likeSenderItemSeq)
                .likeReceiverItemSeq(likeReceiverItemSeq)
                .build();
    }

}
