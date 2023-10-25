package kr.co.devcs.shop.repository;

import kr.co.devcs.shop.entity.ChatRoom;
import kr.co.devcs.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
    List<ChatRoom> findAllByMembers(Member member);
}
