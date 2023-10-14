package kr.co.devcs.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "delivery_info")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeliveryInfo extends BaseTime{
    @Id
    @Column(name = "info_id", columnDefinition = "BINARY(16)")
    private UUID infoId;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnore
    private Member member;
    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;
    @Column(name = "delivery_address_detail", nullable = false)
    private String deliveryAddressDetail;
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;
    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;
}
