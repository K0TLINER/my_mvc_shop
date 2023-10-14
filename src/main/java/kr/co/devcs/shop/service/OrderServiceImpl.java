package kr.co.devcs.shop.service;

import kr.co.devcs.shop.common.annotation.CurrentMember;
import kr.co.devcs.shop.dto.OrderForm;
import kr.co.devcs.shop.dto.OrderSearchForm;
import kr.co.devcs.shop.entity.*;
import kr.co.devcs.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final EmailService emailService;
    @Value("${product.page-size}") private int pageSize;
    @Override
    public Optional<Order> addOrder(Order order) throws IOException {
        Order newOrder = orderRepository.save(order);
        Product product = newOrder.getProduct();
        product.setStock(product.getStock() - newOrder.getQuantity());
        productService.updateProduct(product);
//        emailService.sendOrderEmail(newOrder);
        return Optional.of(newOrder);
    }

    @Override
    public Optional<Order> updateOrder(Order order) {
        Order updateOrder = orderRepository.save(order);
        return Optional.of(updateOrder);
    }


    @Override
    public void deleteOrder(Order order) {
        Product product = order.getProduct();
        product.setStock(product.getStock() + order.getQuantity());
        productService.updateProduct(product);
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
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "registrationDate"));
        Pageable pageable = PageRequest.of(orderSearchForm.getCurrentPage(), pageSize, sort);
        Page<Order> result = orderRepository.findAll(pageable);
        return result.getContent();
    }

    @Override
    public void updateOrderByOrderStatus(UUID orderId, char orderStatus, Member currentMember) {
        Order order = this.getOrder(orderId).orElseThrow();
        if(currentMember.getRole().equals(Role.ROLE_ADMIN) && !order.getBuyer().getMemberId().equals(currentMember.getMemberId()))
            throw new AccessDeniedException("수정 권한이 없습니다.");
        if(orderStatus == '1' && currentMember.getRole().equals(Role.ROLE_ADMIN.getRole()))
            order.setOrderStatus(OrderStatus.IN_TRANSIT);
        else if(orderStatus == '2' && currentMember.getMemberId().equals(order.getBuyer().getMemberId()))
            order.setOrderStatus(OrderStatus.DELIVERED);
        else
            throw new AccessDeniedException("허용되지 않는 접근입니다.");
        this.updateOrder(order);
    }
}
