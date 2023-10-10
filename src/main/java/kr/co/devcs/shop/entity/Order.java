package kr.co.devcs.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseTime {
    @Id
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID orderId;
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Member buyer;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;
    @Column(name = "delivery_address_detail", nullable = false)
    private String deliveryAddressDetail;
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;
    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
    @PrePersist
    public void prePersist() {
        isDeleted = false;
        orderStatus = OrderStatus.BEFORE_SHIPMENT;
        deliveryDate = null;
    }
}
