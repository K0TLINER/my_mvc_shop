package kr.co.devcs.shop.dto;

import kr.co.devcs.shop.entity.DeliveryInfo;
import kr.co.devcs.shop.entity.Member;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class DeliveryInfoForm {
    private UUID infoId = null;
    private UUID memberId = null;
    @NotBlank(message = "배송지 입력은 필수입니다.")
    private String deliveryAddress;
    @NotBlank(message = "배송지 상세주소는 필수입니다.")
    private String deliveryAddressDetail;
    @NotBlank(message = "배송자 이름은 필수입니다.")
    private String recipientName;
    @NotBlank(message = "베송자 전화번호는 필수입니다.")
    private String recipientPhone;
    public DeliveryInfo toEntity(Member member) {
        return DeliveryInfo.builder()
                .infoId(UUID.randomUUID())
                .member(member)
                .deliveryAddress(deliveryAddress)
                .deliveryAddressDetail(deliveryAddressDetail)
                .recipientName(recipientName)
                .recipientPhone(recipientPhone)
                .build();
    }
    public DeliveryInfo toEntity(DeliveryInfo oldDeliveryInfo) {
        oldDeliveryInfo.setRecipientPhone(recipientPhone);
        oldDeliveryInfo.setRecipientName(recipientName);
        oldDeliveryInfo.setDeliveryAddress(deliveryAddress);
        oldDeliveryInfo.setDeliveryAddressDetail(deliveryAddressDetail);
        return oldDeliveryInfo;
    }
}
