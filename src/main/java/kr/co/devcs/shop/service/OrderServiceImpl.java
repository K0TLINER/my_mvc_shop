package kr.co.devcs.shop.service;

import kr.co.devcs.shop.common.annotation.CurrentMember;
import kr.co.devcs.shop.dto.OrderForm;
import kr.co.devcs.shop.dto.OrderSearchForm;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.entity.Order;
import kr.co.devcs.shop.entity.OrderStatus;
import kr.co.devcs.shop.entity.Role;
import kr.co.devcs.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    @Override
    public Optional<Order> addOrder(Order order) {
        Order newOrder = orderRepository.save(order);
        return Optional.of(newOrder);
    }

    @Override
    public Optional<Order> updateOrder(Order order) {
        Order updateOrder = orderRepository.save(order);
        return Optional.of(updateOrder);
    }

    @Override
    public void deleteOrder(Order order) {
        order.setDeleted(true);
        this.updateOrder(order);
    }

    @Override
    public Optional<Order> getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        return Optional.of(order);
    }
    @Override
    public List<Order> getOrderList(OrderSearchForm orderSearchForm, Member currentmember) {
        return null;
    }

    @Override
    public void updateOrderByOrderStatus(UUID orderId, char orderStatus, Member currentMember) {
        Order order = this.getOrder(orderId).orElseThrow();
        if(currentMember.getRole().equals(Role.ROLE_ADMIN) && !order.getBuyer().getMemberId().equals(currentMember.getMemberId()))
            throw new AccessDeniedException("수정 권한이 없습니다.");
        if(orderStatus == '1' && currentMember.getRole().equals("ROLE_ADMIN"))
            order.setOrderStatus(OrderStatus.IN_TRANSIT);
        else if(orderStatus == '2' && currentMember.getMemberId().equals(order.getBuyer().getMemberId()))
            order.setOrderStatus(OrderStatus.DELIVERED);
        else
            throw new AccessDeniedException("허용되지 않는 접근입니다.");
        this.updateOrder(order);
    }
}
