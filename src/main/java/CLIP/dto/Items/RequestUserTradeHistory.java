package CLIP.dto.Items;

import CLIP.Entity.Items.UserTradeHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestUserTradeHistory {
    private String userID1;
    private int itemSeq1;
    private String userID2;
    private int itemSeq2;
    private LocalDateTime tradeTimestamp;

    @Builder
    public RequestUserTradeHistory(String userID1, int itemSeq1, String userID2, int itemSeq2, LocalDateTime tradeTimestamp){
        this.userID1 = userID1;
        this.itemSeq1 = itemSeq1;
        this.userID2 = userID2;
        this.itemSeq2 = itemSeq2;
        this.tradeTimestamp = tradeTimestamp;
    }

    public UserTradeHistory toEntity(){
        return UserTradeHistory.builder()
                .userID1(userID1)
                .itemSeq1(itemSeq1)
                .userID2(userID2)
                .itemSeq2(itemSeq2)
                .tradeTimestamp(tradeTimestamp)
                .build();
    }
}
