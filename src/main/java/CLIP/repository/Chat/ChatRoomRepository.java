package CLIP.repository.Chat;

import CLIP.Entity.Chat.ChatRooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRooms, String> {
    Optional<ChatRooms> findByChatRoomUUID(String chatRoomUUID);
    void deleteByChatRoomUUID(String chatRoomUUID);

    List<ChatRooms> findByChatUserUUID1OrChatUserUUID2(String chatUserUUID1, String chatUserUUID2);
}
