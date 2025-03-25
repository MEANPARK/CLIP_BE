package CLIP.service.Chat;

import CLIP.Entity.Chat.ChatRooms;
import CLIP.dto.Chat.RequestChatRoom;
import CLIP.dto.Chat.ResponseChatRoom;
import CLIP.repository.Chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ResponseChatRoom getChatRooms(String chatRoomUUID){
        ResponseChatRoom responseChatRoom = new ResponseChatRoom(chatRoomRepository.findByChatUserUUID1OrChatUserUUID2(chatRoomUUID, chatRoomUUID));
        return responseChatRoom;
    }
    @Transactional
    public RequestChatRoom createChatRoom(RequestChatRoom requestChatRoom) {
        requestChatRoom.setChatRoomUUID(UUID.randomUUID().toString());
        ChatRooms result = chatRoomRepository.save(requestChatRoom.toEntity());
        return requestChatRoom;
    }

    @Transactional
    public void deleteChatRoom(String chatRoomUUID) {
        chatRoomRepository.deleteByChatRoomUUID(chatRoomUUID);
    }
}
