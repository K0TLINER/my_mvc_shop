//package kr.co.devcs.shop.common.websocket;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import kr.co.devcs.shop.common.JwtUtils;
//import kr.co.devcs.shop.common.MemberDetails;
//import kr.co.devcs.shop.common.annotation.CurrentMember;
//import kr.co.devcs.shop.dto.ChatMessageForm;
//import kr.co.devcs.shop.entity.ChatMessage;
//import kr.co.devcs.shop.entity.ChatRoom;
//import kr.co.devcs.shop.entity.Member;
//import kr.co.devcs.shop.entity.Role;
//import kr.co.devcs.shop.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Component
//@RequiredArgsConstructor
//public class WebSocketHandler extends TextWebSocketHandler {
//    private final ObjectMapper objectMapper;
//    private final ChatService chatService;
//    private final JwtUtils jwtUtils;
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        super.afterConnectionEstablished(session);
//        System.out.println("연결됨");
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        String payload = message.getPayload().toString();
//        System.out.println(payload);
//        ChatMessageForm chatMessageForm = objectMapper.readValue(payload, ChatMessageForm.class);
//        Member member;
//        if(jwtUtils.validateToken(chatMessageForm.getToken())){
//            String email = (String) jwtUtils.getClaims(chatMessageForm.getToken()).get("email");
//            Authentication authentication = jwtUtils.getAuthentication(email);
//            member = ((MemberDetails)(authentication.getPrincipal())).getMember();
//        } else {
//            throw new AccessDeniedException("허용되지 않습니다.");
//        }
//        System.out.println("memberId : " + member.getMemberId());
//        ChatRoom chatRoom = chatService.findRoomById(chatMessageForm.getRoomId());
//        if (!member.getRole().equals(Role.ROLE_ADMIN) && !chatRoom.getMembers().contains(member))
//            throw new AccessDeniedException("허용되지 않는 접근입니다.");
//        ChatMessage chatMessage = ChatMessage.builder()
//                        .chatRoom(chatRoom)
//                        .member(member)
//                        .message(chatMessageForm.getMessage())
//                        .build();
//        chatService.sendMessage(chatMessageForm.getRoomId(), session, chatMessage);
////        chatRoom.handlerActions(session, chatMessage, chatService);
//    }
//
//}
