package kr.co.devcs.shop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@Data
public class Order extends BaseTime {
    @Id
    @Column(name = "order_id")
    private UUID orderId;
    @ManyToOne
    @Column(name = "buyer_id", nullable = false)
    private Member buyerId;
    @ManyToOne
    @Column(name = "product_id", nullable = false)
    private Product productId;
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
}
