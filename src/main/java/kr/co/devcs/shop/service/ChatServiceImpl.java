package kr.co.devcs.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.devcs.shop.entity.ChatMessage;
import kr.co.devcs.shop.entity.ChatRoom;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.repository.ChatMessageRepository;
import kr.co.devcs.shop.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatServiceImpl implements ChatService{
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberService memberService;
    private final RedisTemplate redisTemplate;

    @Override
    public Optional<ChatRoom> createRoom(Member currentMember) {
        Member admin = memberService.getMemberByEmail("admin@naver.com");
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(UUID.randomUUID())
                .member(currentMember)
                .build();
        chatRoom.getMembers().add(admin);
        ChatRoom newChatRoom = chatRoomRepository.save(chatRoom);

        return newChatRoom != null ? Optional.of(newChatRoom) : Optional.empty();
    }

    @Override
    public Optional<ChatRoom> getRoom(UUID roomId) {
        return chatRoomRepository.findById(roomId);
    }

    @Override
    public Optional<ChatMessage> createMessage(Member currentMember, UUID roomId, String message) {
        ChatRoom chatRoom = this.getRoom(roomId).orElseThrow();
        ChatMessage chatMessage = ChatMessage.builder()
                .member(currentMember)
                .chatRoom(chatRoom)
                .message(message)
                .build();
        ChatMessage newChatMessage = chatMessageRepository.save(chatMessage);
        return newChatMessage != null ? Optional.of(newChatMessage) : Optional.empty();
    }

    @Override
    public List<ChatRoom> getRoomList(Member currentMember) {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllByMembers(currentMember);
        return chatRooms;
    }

    @Override
    public List<ChatMessage> getMessageList(Member currentMember, UUID roomId) {
        ChatRoom chatRoom = this.getRoom(roomId).orElseThrow();
        System.out.println("chatRoom : " + chatRoom);
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByChatRoom(chatRoom);
        return chatMessages;
    }

    @Override
    public List<Member> getMemberListByRoom(UUID roomId) {
        ChatRoom chatRoom = this.getRoom(roomId).orElseThrow();
        List<Member> members = chatRoom.getMembers().stream().collect(Collectors.toList());
        return members;
    }
//    @PostConstruct
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }
//    @Override
//    public List<ChatRoom> findAllRoom() {
//        return chatRoomRepository.findAll();
//    }
//
//    @Override
//    public List<ChatRoom> findAllRoom(Member currentMember) {
//        return chatRoomRepository.findAllByMembers(currentMember);
//    }
//
//    @Override
//    public List<ChatMessage> findAllMessageByRoom(ChatRoom chatRoom) {
//        return chatMessageRepository.findAllByChatRoom(chatRoom);
//    }
//
//    @Override
//    public ChatRoom findRoomById(UUID roomId) {
//        return chatRoomRepository.findById(roomId).orElseThrow();
//    }
//
//    @Override
//    public ChatRoom createRoom(Member currentMember) {
//        UUID randomUUID = UUID.randomUUID();
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomId(randomUUID)
//                .member(currentMember)
//                .build();
//        chatRoomRepository.save(chatRoom);
//        chatRooms.put(randomUUID, new LinkedHashSet<>());
////        chatRooms.put(randomUUID, chatRoom);
//        return chatRoom;
//    }
//    @Override
//    public ChatMessage createMessage(ChatMessage chatMessage, Member currentMember, UUID roomId) {
//
//    }
//    @Override
//    public void sendMessage(UUID roomId, WebSocketSession session, ChatMessage chatMessage) {
//        chatRooms.get(roomId).add(session);
//        ChatMessage newChatMessage = chatMessageRepository.save(chatMessage);
//        chatRooms.get(roomId).parallelStream()
//                .filter(connectSession -> connectSession.isOpen())
//                .forEach(connectSession -> {
//                    try {
////                        connectSession.sendMessage(new TextMessage());
//                        connectSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(newChatMessage.getMessage() + "/" + newChatMessage.getRegistrationDate() + "/" + newChatMessage.getMember().getNickname())));
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//
////            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//    }
}
