package kr.co.devcs.shop.controller;

import kr.co.devcs.shop.common.annotation.CurrentMember;
import kr.co.devcs.shop.dto.DeliveryInfoForm;
import kr.co.devcs.shop.entity.DeliveryInfo;
import kr.co.devcs.shop.entity.Member;
import kr.co.devcs.shop.service.DeliveryInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryInfo")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DeliveryInfoController {
    private final DeliveryInfoService deliveryInfoService;
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> getList(
            @CurrentMember Member currentMember
            ) {
        List<DeliveryInfo> list = deliveryInfoService.getDeliveryInfoList(currentMember);
        return ResponseEntity.ok().body(list);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> add(
            @CurrentMember Member currentMember,
            @RequestBody DeliveryInfoForm deliveryInfoForm
    ) {
        DeliveryInfo deliveryInfo = deliveryInfoForm.toEntity(currentMember);
        deliveryInfoService.addDeliveryInfo(deliveryInfo).orElseThrow();
        return ResponseEntity.ok().build();
    }
}
