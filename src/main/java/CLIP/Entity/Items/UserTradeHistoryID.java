package CLIP.Entity.Items;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserTradeHistoryID implements Serializable {
    private String userID1;
    private int itemSeq1;
    private String userID2;
    private int itemSeq2;
}
