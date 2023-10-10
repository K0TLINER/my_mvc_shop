package kr.co.devcs.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    BEFORE_SHIPMENT("배송출발전"),
    IN_TRANSIT("배송중"),
    DELIVERED("배송완료");
    private final String description;
}
