package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.DeliveryInfoForm;
import kr.co.devcs.shop.entity.DeliveryInfo;
import kr.co.devcs.shop.entity.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryInfoService {
    Optional<DeliveryInfo> addDeliveryInfo(DeliveryInfo deliveryInfo);
    Optional<DeliveryInfo> updateDeliveryInfo(DeliveryInfo deliveryInfo);
    void deleteDeliveryInfo(UUID infoId);
    Optional<DeliveryInfo> getDeliveryInfo(UUID infoId, Member currentMember);
    List<DeliveryInfo> getDeliveryInfoList(Member currentMember);

}
