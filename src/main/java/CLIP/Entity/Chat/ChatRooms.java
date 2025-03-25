package CLIP.Entity.Chat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ChatRooms_TB")
public class ChatRooms {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String chatRoomUUID;

    private String chatUserUUID1;

    private String chatUserUUID2;

    private int chatUserItem1;

    private int chatUserItem2;

    @Builder
    public ChatRooms(String chatRoomUUID, String chatUserUUID1, String chatUserUUID2, int chatUserItem1, int chatUserItem2) {
        this.chatRoomUUID = chatRoomUUID;
        this.chatUserUUID1 = chatUserUUID1;
        this.chatUserUUID2 = chatUserUUID2;
        this.chatUserItem1 = chatUserItem1;
        this.chatUserItem2 = chatUserItem2;
    }
}
