package CLIP.dto.Chat;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseMessage {
    private Long messageID;
    private String chatRoomUUID;
    //private Long chatRoomID; // 방 번호
    private String sender;
    private String messageText;
    private LocalDateTime sendTime;

    @Builder
    public ResponseMessage(Long messageID, String chatRoomUUID, String sender, String messageText, LocalDateTime sendTime) {
        this.messageID = messageID;
        this.chatRoomUUID = chatRoomUUID;
        this.sender = sender;
        this.messageText = messageText;
        this.sendTime = sendTime;
    }
}
