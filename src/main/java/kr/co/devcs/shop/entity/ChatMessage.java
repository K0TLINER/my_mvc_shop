package kr.co.devcs.shop.entity;

import lombok.Data;


@Data
public class ChatMessage {
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
