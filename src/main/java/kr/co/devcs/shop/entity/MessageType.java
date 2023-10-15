package kr.co.devcs.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {
    ENTER("입장"),
    TALK("메세지");
    private final String type;
}
