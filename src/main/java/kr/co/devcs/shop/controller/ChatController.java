package kr.co.devcs.shop.controller;

import kr.co.devcs.shop.common.annotation.CurrentMember;
import kr.co.devcs.shop.dto.ChatMessageForm;
import kr.co.devcs.shop.entity.ChatMessage;
import kr.co.devcs.shop.entity.ChatRoom;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.entity.Role;
import kr.co.devcs.shop.service.AlertService;
import kr.co.devcs.shop.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChatController {
    private final ChatService chatService;
    private final AlertService alertService;
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> createRoom(
        @CurrentMember Member currentMember
        ) {
        ChatRoom chatRoom = chatService.createRoom(currentMember).orElseThrow();
        return ResponseEntity.ok().body(chatRoom);
    }

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> createMessage(
        @CurrentMember Member currentMember,
        @RequestBody ChatMessageForm chatMessageForm
            ) {
        ChatMessage chatMessage = chatService.createMessage(currentMember, chatMessageForm.getRoomId(), chatMessageForm.getMessage()).orElseThrow();
        alertService.addAlertsByRoom(chatMessageForm.getRoomId(), chatMessageForm.getMessage(), currentMember);
        return ResponseEntity.ok().body(chatMessage);
    }

    @RequestMapping(value = "/getRoomList", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getRoomList(
        @CurrentMember Member currentMember
    ) {
        List<ChatRoom> chatRooms = chatService.getRoomList(currentMember);
        return ResponseEntity.ok().body(chatRooms);
    }

    @RequestMapping(value = "/getMessageList/{roomId}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getMessageList(
        @CurrentMember Member currentMember,
        @PathVariable UUID roomId
    ) {
        List<ChatMessage> chatMessageList = chatService.getMessageList(currentMember, roomId);
        return ResponseEntity.ok().body(chatMessageList);
    }

//    @RequestMapping(value = "/room/listAll", method = RequestMethod.GET)
//    public ResponseEntity<?> findAllRoom(
//    ) {
//        return ResponseEntity.ok().body(chatService.findAllRoom());
//    }
//
//    @RequestMapping(value = "/room/list", method = RequestMethod.GET)
//    public ResponseEntity<?> findAllRoomByMember(
//            @CurrentMember Member currentMember
//    ) {
//        return ResponseEntity.ok().body(chatService.findAllRoom(currentMember));
//    }
//
//    @RequestMapping(value = "/message/list/{roomId}", method = RequestMethod.GET)
//    public ResponseEntity<?> findAllMessageByMember(
//            @CurrentMember Member currentMember,
//            @PathVariable UUID roomId
//        ) {
//        ChatRoom chatRoom = chatService.findRoomById(roomId);
//        if(currentMember.getRole().equals(Role.ROLE_USER) && !chatRoom.getMembers().contains(currentMember))
//            throw new AccessDeniedException("접근 권한이 없습니다.");
//        List<ChatMessage> chatMessages = chatService.findAllMessageByRoom(chatRoom);
//        return ResponseEntity.ok().body(chatMessages);
//    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<?> findByMember(
//        @CurrentMember Member currentMember
//    ) {
//        chatService
//    }
}
