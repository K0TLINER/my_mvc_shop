package kr.co.devcs.shop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.devcs.shop.entity.Alert;
import kr.co.devcs.shop.entity.Member;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface AlertService {
    void addAlert(Member from, Member to, String message, UUID roomId) throws JsonProcessingException;
    void addAlertsByRoom(UUID roomId, String message, Member from);
}
