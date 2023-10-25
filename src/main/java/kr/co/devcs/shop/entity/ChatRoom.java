package kr.co.devcs.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.devcs.shop.service.ChatService;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "rooms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatRoom extends BaseTime {
    @Id
    @Column(name = "room_id", columnDefinition = "BINARY(16)")
    private UUID roomId;
    @ManyToMany
    @JoinTable(name = "room_member")
    @JsonIgnore
    private Set<Member> members = new LinkedHashSet<>();
//    private Set<WebSocketSession> sessions; = new HashSet<WebSocketSession>();

    @Builder
    public ChatRoom(UUID roomId, Member member) {
        this.roomId = roomId;
        members.add(member);
    }

    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
//        if(chatMessage.getType().equals(MessageType.ENTER)) {
//            sessions.add(session);
//            chatMessage.setMessage(chatMessage.getSender() + "님이 입장");
//        }
        sendMessage(chatMessage, chatService);
    }

    private <T> void sendMessage(T message, ChatService chatService) {
//        sessions.parallelStream().filter(session -> session.isOpen()).forEach(session -> chatService.sendMessage(session, message));
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
