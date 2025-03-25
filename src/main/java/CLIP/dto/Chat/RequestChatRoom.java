package CLIP.dto.Chat;

import CLIP.Entity.Chat.ChatRooms;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

// Stomp 를 통해 pub/sub 를 사용하면 구독자 관리가 알아서 된다!!
// 따라서 따로 세션 관리를 하는 코드를 작성할 필도 없고,
// 메시지를 다른 세션의 클라이언트에게 발송하는 것도 구현 필요가 없다!
@Getter
@Setter
@NoArgsConstructor
public class RequestChatRoom {
    private String chatRoomUUID; // 채팅방 이름
    private String chatUserUUID1;
    private String chatUserUUID2;
    private int chatUserItem1;
    private int chatUserItem2;

    @Builder
    public RequestChatRoom(String chatUserUUID1, String chatUserUUID2, int chatUserItem1, int chatUserItem2){
        this.chatRoomUUID = UUID.randomUUID().toString();
        this.chatUserUUID1 = chatUserUUID1;
        this.chatUserUUID2 = chatUserUUID2;
        this.chatUserItem1 = chatUserItem1;
        this.chatUserItem2 = chatUserItem2;
    }

    public ChatRooms toEntity() {
        return ChatRooms.builder()
                .chatRoomUUID(chatRoomUUID)
                .chatUserUUID1(chatUserUUID1)
                .chatUserUUID2(chatUserUUID2)
                .chatUserItem1(chatUserItem1)
                .chatUserItem2(chatUserItem2)
                .build();
    }
}
