package kr.co.devcs.shop.controller;

import kr.co.devcs.shop.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createRoom(
            @RequestBody String name
    ) {
        return ResponseEntity.ok().body(chatService.createRoom(name));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAllRoom(
    ) {
        return ResponseEntity.ok().body(chatService.findAllRoom());
    }
}
