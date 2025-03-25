package CLIP.Entity.Chat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Message_TB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageID;
    private String sender;
    private String messageText;
    private String chatRoomUUID;
    private LocalDateTime sendTime;
    // private Long chatRoomID;


    @Builder
    public Message_TB(String chatRoomUUID, String sender, String messageText, LocalDateTime sendTime) {
        this.chatRoomUUID = chatRoomUUID;
        this.sender = sender;
        this.messageText = messageText;
        this.sendTime = sendTime;
    }

}
