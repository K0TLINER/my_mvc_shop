package kr.co.devcs.shop.dto;

import kr.co.devcs.shop.entity.Member;
import lombok.Data;

@Data
public class OrderSearchForm extends PageForm{
    private Member buyer;
}
