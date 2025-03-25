package CLIP.service.Chat;

import CLIP.Entity.Chat.ChatRooms;
import CLIP.Entity.Chat.Message_TB;
import CLIP.dto.Chat.MessageDTO;
import CLIP.dto.Chat.ResponseMessage;
import CLIP.repository.Chat.ChatRoomRepository;
import CLIP.repository.Chat.MessageRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public void saveMessage(MessageDTO messageDTO) {
        //ChatRooms_TB에 존재하지 않으면 실행x
        ChatRooms chatRooms = chatRoomRepository.findByChatRoomUUID(messageDTO.getChatRoomUUID())
                .orElseThrow(() -> new NoSuchElementException("ChatRoom not found with roomName: " + messageDTO.getChatRoomUUID()));

        messageRepository.save(messageDTO.toEntity(messageDTO.getChatRoomUUID()));
    }

    //채팅방의 채팅 내역 모두 불러오기
    //시간 순서대로 불러오기
    //DB에서 받아온 데이터를 stream을 통해 모든 메시지를 객체화하고 리스트로 만들기
    @Transactional
    public List<ResponseMessage> getMessages(String roomName, Pageable pageable) {
        List<Message_TB> messages = messageRepository.findALLByChatRoomUUIDOrderBySendTime(roomName, pageable);
        return messages.stream()
                .map(messageTb -> ResponseMessage.builder()
                        .messageID(messageTb.getMessageID())
                        .chatRoomUUID(messageTb.getChatRoomUUID())
                        .sender(messageTb.getSender())
                        .messageText(messageTb.getMessageText())
                        .sendTime(messageTb.getSendTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMessages(String chatRoomUUID) { messageRepository.deleteByChatRoomUUID(chatRoomUUID);}
}
