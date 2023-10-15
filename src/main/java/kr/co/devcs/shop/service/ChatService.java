package kr.co.devcs.shop.service;

import kr.co.devcs.shop.entity.ChatRoom;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public interface ChatService {
    List<ChatRoom> findAllRoom();
    ChatRoom findRoomById(String roomId);
    ChatRoom createRoom(String name);
    <T> void sendMessage(WebSocketSession session, T message);
}
