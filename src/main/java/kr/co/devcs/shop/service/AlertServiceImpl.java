package kr.co.devcs.shop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.devcs.shop.entity.Alert;
import kr.co.devcs.shop.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService{
    private final RedisTemplate redisTemplate;
    private final ChatService chatService;
    @Override
    public void addAlert(Member from, Member to, String message, UUID roomId) throws JsonProcessingException {
        Alert alert = Alert.builder()
                .roomId(roomId)
                .sender(from.getNickname())
                .message(message)
                .regDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        ObjectMapper objectMapper = new ObjectMapper();

        redisTemplate.opsForList().rightPush(to.getNickname(), objectMapper.writeValueAsString(alert));
//        System.out.println((String) redisTemplate.opsForHash().get(to.getNickname(), roomId.toString()));
//        redisTemplate.opsForHash().put(to.getNickname(), roomId.toString(), List.of(alert));
    }

    @Override
    public void addAlertsByRoom(UUID roomId, String message, Member from) {
        chatService.getMemberListByRoom(roomId)
                .stream().filter(member -> !member.getNickname().equals(from.getNickname()))
                .forEach(member -> {
                    try {
                        this.addAlert(from, member, message, roomId);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
