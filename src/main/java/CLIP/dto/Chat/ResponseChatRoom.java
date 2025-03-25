package CLIP.dto.Chat;

import CLIP.Entity.Chat.ChatRooms;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseChatRoom {
    List<ChatRoomDTO> chatRoomDTOList;

    //객체화
    @Builder
    public ResponseChatRoom(List<ChatRooms> chatRoomsTbList) {
        this.chatRoomDTOList = chatRoomsTbList.stream()
                .map(chatRooms_tb -> ChatRoomDTO.builder()
                        .chatRoomUUID(chatRooms_tb.getChatRoomUUID())
                        .chatUserUUID1(chatRooms_tb.getChatUserUUID1())
                        .chatUserUUID2(chatRooms_tb.getChatUserUUID2())
                        .chatUserItem1(chatRooms_tb.getChatUserItem1())
                        .chatUserItem2(chatRooms_tb.getChatUserItem2())
                        .build())
                .collect(Collectors.toList());
    }
}
