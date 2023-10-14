package kr.co.devcs.shop.repository;

import kr.co.devcs.shop.entity.DeliveryInfo;
import kr.co.devcs.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, UUID> {
    List<DeliveryInfo> findAllByMember(Member member);
}
