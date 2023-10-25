package kr.co.devcs.shop.repository;

import kr.co.devcs.shop.entity.ChatMessage;
import kr.co.devcs.shop.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChatRoom(ChatRoom chatRoom);
}
