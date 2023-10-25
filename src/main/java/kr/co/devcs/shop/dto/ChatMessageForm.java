package kr.co.devcs.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessageForm {
    private UUID roomId;
    private String message;
}
