package CLIP.repository.Items;

import CLIP.Entity.Items.LikeAction;
import CLIP.Entity.Items.LikeActionID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeActionRepository extends JpaRepository<LikeAction, LikeActionID> {
    Optional<LikeAction> findByLikeSenderUUIDAndLikeReceiverUUIDAndLikeSenderItemSeqAndLikeReceiverItemSeq(String likeSenderUUID, String likeReceiverUUID, int likeSenderItemSeq, int likeReceiverItemSeq);
    void deleteByLikeSenderItemSeq(int likeSenderItemSeq);
    void deleteByLikeReceiverItemSeq(int likeReceiverItemSeq);

    List<LikeAction> findByLikeSenderUUID(String likeSenderUUID);
    List<LikeAction> findByLikeReceiverUUID(String likeReceiverUUID);
}
