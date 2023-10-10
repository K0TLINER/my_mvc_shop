package kr.co.devcs.shop.dto;

import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.entity.Order;
import kr.co.devcs.shop.entity.OrderStatus;
import kr.co.devcs.shop.entity.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class OrderForm {
    private UUID orderId = null;
    private UUID buyerId = null;
    private Long productId = null;
    @NotNull(message = "제품 수량은 필수입니다.")
    private int quantity;
    @NotBlank(message = "배송지 입력은 필수입니다.")
    private String deliveryAddress;
    @NotBlank(message = "배송지 상세주소는 필수입니다.")
    private String deliveryAddressDetail;
    @NotBlank(message = "배송자 이름은 필수입니다.")
    private String recipientName;
    @NotBlank(message = "베송자 전화번호는 필수입니다.")
    private String recipientPhone;
    public Order toEntity(Member buyer, Product product) {
        return Order.builder()
                .orderId(UUID.randomUUID())
                .buyer(buyer)
                .product(product)
                .deliveryAddress(deliveryAddress)
                .deliveryAddressDetail(deliveryAddressDetail)
                .quantity(quantity)
                .recipientName(recipientName)
                .recipientPhone(recipientPhone)
                .build();
    }
    public Order toEntity(Order oldOrder) {
        oldOrder.setRecipientPhone(recipientPhone);
        oldOrder.setRecipientName(recipientName);
        oldOrder.setDeliveryAddress(deliveryAddress);
        oldOrder.setDeliveryAddressDetail(deliveryAddressDetail);
        oldOrder.setQuantity(quantity);
        return oldOrder;
    }
}
