package CLIP.repository.Chat;

import CLIP.Entity.Chat.Message_TB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface MessageRepository extends JpaRepository<Message_TB, Long> {
    List<Message_TB> findALLByChatRoomUUIDOrderBySendTime(String chatRoomUUID, Pageable pageable);

    void deleteByChatRoomUUID(String chatRoomUUID);
}
