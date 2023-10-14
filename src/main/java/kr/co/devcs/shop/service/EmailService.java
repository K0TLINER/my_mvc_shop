package kr.co.devcs.shop.service;

import kr.co.devcs.shop.entity.Order;

import java.io.IOException;

public interface EmailService {
    void sendOrderEmail(Order order) throws IOException;
}
