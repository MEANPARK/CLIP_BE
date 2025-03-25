package CLIP.Entity.Items;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//복합키 설정

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class LikeActionID implements Serializable {
    private String likeSenderUUID;
    private String likeReceiverUUID;
    private int likeSenderItemSeq;
    private int likeReceiverItemSeq;
}
