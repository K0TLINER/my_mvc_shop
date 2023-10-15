package kr.co.devcs.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.devcs.shop.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;
    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }
    @Override
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    @Override
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    @Override
    public ChatRoom createRoom(String name) {
        String randomUUID = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomUUID).build();
        chatRooms.put(randomUUID, chatRoom);
        return chatRoom;
    }
    @Override
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
