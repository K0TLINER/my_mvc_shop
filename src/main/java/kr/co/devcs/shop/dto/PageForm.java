package kr.co.devcs.shop.dto;

import org.springframework.beans.factory.annotation.Value;

public class PageForm {
    @Value("${product.page-size}") private int pageSize;
    private int currentPage = 0;
}
