package CLIP.dto.Items;

import CLIP.Entity.Items.UserTradeHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserTradeHistoryDTO {
    private String userID1;
    private int itemSeq1;
    private String userID2;
    private int itemSeq2;
    private LocalDateTime tradeTimestamp;

    @Builder
    public UserTradeHistoryDTO(UserTradeHistory userTradeHistory) {
        this.userID1 = userTradeHistory.getUserID1();
        this.itemSeq1 = userTradeHistory.getItemSeq1();
        this.userID2 = userTradeHistory.getUserID2();
        this.itemSeq2 = userTradeHistory.getItemSeq2();
        this.tradeTimestamp = userTradeHistory.getTradeTimestamp();
    }
}
