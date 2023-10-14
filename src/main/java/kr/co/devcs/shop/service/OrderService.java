package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.OrderForm;
import kr.co.devcs.shop.dto.OrderSearchForm;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.entity.Order;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Optional<Order> addOrder(Order order) throws IOException;
    Optional<Order> updateOrder(Order order);
    void deleteOrder(Order order);
    Optional<Order> getOrder(UUID orderId);
    List<Order> getOrderList(OrderSearchForm orderSearchForm, Member currentmember);
    void updateOrderByOrderStatus(UUID orderId, char orderStatus, Member currentMember) ;
}
