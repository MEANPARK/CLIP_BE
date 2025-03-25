package CLIP.dto.Items;

import CLIP.Entity.Items.UserTradeHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseUserTradeHistorys {
    List<UserTradeHistoryDTO> userTradeHistoryDTOList;

    @Builder
    public ResponseUserTradeHistorys(List<UserTradeHistory> userTradeHistoryList) {
        this.userTradeHistoryDTOList = userTradeHistoryList.stream()
                .map(userTradeHistory -> UserTradeHistoryDTO.builder()
                        .userTradeHistory(userTradeHistory)
                        .build())
                .collect(Collectors.toList());

    }
}
