package CLIP.Entity.Items;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(LikeActionID.class) //복합키 설정
@Table(name = "LikeAction_TB")
public class LikeAction {
    @Id
    private String likeSenderUUID;
    @Id
    private String likeReceiverUUID;
    @Id
    private int likeSenderItemSeq;
    @Id
    private int likeReceiverItemSeq;

    @Builder
    public LikeAction(String likeSenderUUID, String likeReceiverUUID, int likeSenderItemSeq, int likeReceiverItemSeq){
        this.likeSenderUUID = likeSenderUUID;
        this.likeReceiverUUID = likeReceiverUUID;
        this.likeSenderItemSeq = likeSenderItemSeq;
        this.likeReceiverItemSeq = likeReceiverItemSeq;
    }
}
