package kr.co.devcs.shop.service;

import kr.co.devcs.shop.entity.DeliveryInfo;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.repository.DeliveryInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryInfoServiceImpl implements DeliveryInfoService{
    private final DeliveryInfoRepository deliveryInfoRepository;
    @Override
    public Optional<DeliveryInfo> addDeliveryInfo(DeliveryInfo deliveryInfo) {
        DeliveryInfo newDeliveryInfo = deliveryInfoRepository.save(deliveryInfo);
        return Optional.of(newDeliveryInfo);
    }

    @Override
    public Optional<DeliveryInfo> updateDeliveryInfo(DeliveryInfo deliveryInfo) {
        return Optional.empty();
    }

    @Override
    public void deleteDeliveryInfo(UUID infoId) {

    }

    @Override
    public Optional<DeliveryInfo> getDeliveryInfo(UUID infoId, Member currentMember) {
        return Optional.empty();
    }

    @Override
    public List<DeliveryInfo> getDeliveryInfoList(Member currentMember) {
        return deliveryInfoRepository.findAllByMember(currentMember);
    }
}
