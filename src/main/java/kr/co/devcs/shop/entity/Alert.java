package kr.co.devcs.shop.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Alert {
//    private String receiver;
    private UUID roomId;
    private String sender;
    private String message;
    private String regDate;
}
