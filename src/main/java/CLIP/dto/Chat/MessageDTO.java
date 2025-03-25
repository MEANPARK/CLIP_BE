package CLIP.dto.Chat;

import CLIP.Entity.Chat.Message_TB;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageDTO {
    private Long messageID;
    private String chatRoomUUID;
    //private Long chatRoomId; // 방 번호
    private String sender;
    private String messageText;
    private LocalDateTime sendTime;

    public Message_TB toEntity(String chatRoomUUID) {
        return Message_TB.builder()
                .chatRoomUUID(chatRoomUUID)
                .sender(sender)
                .messageText(messageText)
                .sendTime(LocalDateTime.now())
                .build();
    }
}
