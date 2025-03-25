package CLIP.dto.Chat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomDTO {
    private String chatRoomUUID;

    private String chatUserUUID1;

    private String chatUserUUID2;

    private int chatUserItem1;

    private int chatUserItem2;

    @Builder
    public ChatRoomDTO(String chatRoomUUID, String chatUserUUID1, String chatUserUUID2, int chatUserItem1, int chatUserItem2) {
        this.chatRoomUUID = chatRoomUUID;
        this.chatUserUUID1 = chatUserUUID1;
        this.chatUserUUID2 = chatUserUUID2;
        this.chatUserItem1 = chatUserItem1;
        this.chatUserItem2 = chatUserItem2;
    }
}
