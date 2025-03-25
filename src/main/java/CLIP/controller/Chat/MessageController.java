package CLIP.controller.Chat;

import CLIP.dto.Chat.MessageDTO;
import CLIP.dto.Chat.ResponseMessage;
import CLIP.response.Response;
import CLIP.service.Chat.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MessageController {
    // 메시지 브로커와 상호작용하여 WebSocket 메시지를 전송하는 데 사용
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    // /app/chat/send 엔드포인트로 들어오는 WebSocket 메시지를 처리한다.
    @ResponseStatus(HttpStatus.OK)
    @MessageMapping("/chat/send")
    public Response sendMessage(@Payload MessageDTO messageDTO) {
        // 채팅 저장
        messageService.saveMessage(messageDTO);
        // 해당 채팅 메시지를 WebSocket 토픽(/topic/채팅방ID)에 전송하여 클라이언트에게 브로드캐스팅한다.
        messagingTemplate.convertAndSend("/topic/chat/room/" + messageDTO.getChatRoomUUID(), messageDTO);//.getMessageText());
        return Response.success();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/room/chat/{roomName}")
    public Response getMessages(@PathVariable("roomName") String roomName, Pageable pageable) {
        List<ResponseMessage> messages = messageService.getMessages(roomName, pageable);
        return Response.success(messages);
    }
}
