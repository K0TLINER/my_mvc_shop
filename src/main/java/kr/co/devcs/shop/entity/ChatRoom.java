package kr.co.devcs.shop.entity;

import kr.co.devcs.shop.service.ChatService;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<WebSocketSession>();

    @Builder
    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        System.out.println(chatMessage.getType());
        if(chatMessage.getType().equals(MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장");
        }
        sendMessage(chatMessage, chatService);
    }

    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().filter(session -> session.isOpen()).forEach(session -> chatService.sendMessage(session, message));
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
