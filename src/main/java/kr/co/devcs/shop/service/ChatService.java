package kr.co.devcs.shop.service;

import kr.co.devcs.shop.entity.ChatMessage;
import kr.co.devcs.shop.entity.ChatRoom;
import kr.co.devcs.shop.entity.Member;
import org.springframework.web.socket.WebSocketSession;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatService {
    Optional<ChatRoom> createRoom(Member currentMember);
    Optional<ChatRoom> getRoom(UUID roomId);
    Optional<ChatMessage> createMessage(Member currentMember, UUID roomId, String message);
    List<ChatRoom> getRoomList(Member currentMember);
    List<ChatMessage> getMessageList(Member currentMember, UUID roomId);
    List<Member> getMemberListByRoom(UUID roomId);
}
