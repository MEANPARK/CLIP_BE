package CLIP.service.Items;

import CLIP.Entity.Items.LikeAction;
import CLIP.dto.Items.RequestLikeAction;
import CLIP.repository.Items.LikeActionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LikeActionService {
    private final LikeActionRepository likeActionRepository;


    @Transactional
    public String addActionLog(RequestLikeAction requestLikeAction){
        likeActionRepository.save(requestLikeAction.toEntity());
        return "success";
    }

    @Transactional
    public String matchingOrNot(RequestLikeAction responseLikeAction){
        LikeAction likeAction = likeActionRepository.findByLikeSenderUUIDAndLikeReceiverUUIDAndLikeSenderItemSeqAndLikeReceiverItemSeq(responseLikeAction.getLikeReceiverUUID(),
                responseLikeAction.getLikeSenderUUID(),
                responseLikeAction.getLikeReceiverItemSeq(),
                responseLikeAction.getLikeSenderItemSeq()).orElse(new LikeAction("null", "null", 0, 0));

        //찾은 것이 없다면 0 -> fail 리턴
        if(likeAction.getLikeSenderItemSeq() == 0){
            return "fail";
        } else {
            return "success";
        }

    }

    @Transactional
    public String deleteLikeActions(int likeSenderItemSeq, int likeReceiverItemSeq){
        likeActionRepository.deleteByLikeSenderItemSeq(likeSenderItemSeq);
        likeActionRepository.deleteByLikeSenderItemSeq(likeReceiverItemSeq);
        return "success";
    }
}
