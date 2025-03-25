package CLIP.repository.Items;

import CLIP.Entity.Items.UserTradeHistory;
import CLIP.Entity.Items.UserTradeHistoryID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTradeHistoryRepository extends JpaRepository<UserTradeHistory, UserTradeHistoryID> {
    List<UserTradeHistory> findByUserID1OrUserID2(String userID1, String userID2);
}
