package CLIP.Entity.Items;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(UserTradeHistoryID.class) //복합키 설정
@Table(name = "UserTradeHistory_TB")
public class UserTradeHistory {
    @Id
    private String userID1;
    @Id
    private int itemSeq1;
    @Id
    private String userID2;
    @Id
    private int itemSeq2;

    private LocalDateTime tradeTimestamp;

    @Builder
    public UserTradeHistory(String userID1, int itemSeq1, String userID2, int itemSeq2, LocalDateTime tradeTimestamp){
        this.userID1 = userID1;
        this.itemSeq1 = itemSeq1;
        this.userID2 = userID2;
        this.itemSeq2 = itemSeq2;
        this.tradeTimestamp = tradeTimestamp;
    }
}
