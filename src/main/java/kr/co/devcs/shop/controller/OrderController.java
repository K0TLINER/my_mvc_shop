package kr.co.devcs.shop.controller;

import kr.co.devcs.shop.common.annotation.CurrentMember;
import kr.co.devcs.shop.dto.OrderForm;
import kr.co.devcs.shop.dto.OrderSearchForm;
import kr.co.devcs.shop.entity.*;
import kr.co.devcs.shop.service.MemberService;
import kr.co.devcs.shop.service.OrderService;
import kr.co.devcs.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final MemberService memberService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> add(
            @CurrentMember Member currentMember,
            @RequestBody OrderForm orderForm
    ) throws IOException {
        Product product = productService.getProduct(orderForm.getProductId()).orElseThrow();
        Order order = orderForm.toEntity(currentMember, product);
        Order newOrder = orderService.addOrder(order).orElseThrow();
        return ResponseEntity.ok().body(Map.of("orderId", newOrder.getOrderId()));
    }
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> update(
            @CurrentMember Member currentMember,
            @RequestBody OrderForm orderForm
    ) {
        Order oldOrder = orderService.getOrder(orderForm.getOrderId()).orElseThrow();
        if(currentMember.getRole().equals(Role.ROLE_ADMIN) && !oldOrder.getBuyer().getMemberId().equals(currentMember.getMemberId()))
            throw new AccessDeniedException("수정 권한이 없습니다.");
        if(oldOrder.isDeleted())
            throw new AccessDeniedException("취소 항목은 수정할 수 없습니다.");
        Order newOlder = orderForm.toEntity(oldOrder);
        orderService.updateOrder(newOlder).orElseThrow();
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/delete/{orderId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> delete(
            @CurrentMember Member currentMember,
            @PathVariable("orderId") UUID orderId
    ) {
        Order order = orderService.getOrder(orderId).orElseThrow();
        if(currentMember.getRole().equals(Role.ROLE_ADMIN) && !order.getBuyer().getMemberId().equals(currentMember.getMemberId()))
            throw new AccessDeniedException("삭제 권한이 없습니다.");
        orderService.deleteOrder(order);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/get/{orderId}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> get(
            @CurrentMember Member currentMember,
            @PathVariable("orderId") UUID orderId
    ) {
        Order order = orderService.getOrder(orderId).orElseThrow();
        if(currentMember.getRole().equals(Role.ROLE_ADMIN) && !order.getBuyer().getMemberId().equals(currentMember.getMemberId()))
            throw new AccessDeniedException("보기 권한이 없습니다.");
        return ResponseEntity.ok().body(order);
    }
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getList(
            @CurrentMember Member currentMember,
            @ModelAttribute OrderSearchForm orderSearchForm
    ) {
        orderSearchForm.setBuyer(currentMember);
        if(currentMember.getRole().equals(Role.ROLE_ADMIN))
            orderSearchForm.setBuyer(null);

        List<Order> orders = orderService.getOrderList(orderSearchForm, currentMember);

        return ResponseEntity.ok().body(orders);
    }
    @RequestMapping(value = "/update/orderStatus/{orderStatus}", method = RequestMethod.PATCH)
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> updateOrderStatus(
            @CurrentMember Member currentMember,
            @RequestBody Map map,
            @PathVariable("orderStatus") char orderStatus
    ) {
        UUID orderId = UUID.fromString((String) map.get("orderId"));
        orderService.updateOrderByOrderStatus(orderId, orderStatus, currentMember);
        return ResponseEntity.ok().build();
    }
}
