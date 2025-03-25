package CLIP.service.Items;

import CLIP.dto.Items.RequestLikeAction;
import CLIP.dto.Items.RequestUserTradeHistory;
import CLIP.repository.Items.UserTradeHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserTradeHistoryService {
    private final UserTradeHistoryRepository userTradeHistoryRepository;

    @Transactional
    public String addTradeHistory(RequestLikeAction requestLikeAction, LocalDateTime localDateTime) {
        RequestUserTradeHistory requestUserTradeHistory = new RequestUserTradeHistory(requestLikeAction.getLikeSenderUUID(),
                requestLikeAction.getLikeSenderItemSeq(), requestLikeAction.getLikeReceiverUUID(), requestLikeAction.getLikeReceiverItemSeq(), localDateTime);

        userTradeHistoryRepository.save(requestUserTradeHistory.toEntity());
        return "success";
    }
}
