package kr.co.devcs.shop.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SearchForm {
    private int page = 0;
    @Value("${product.page-size}")
    private final int size;
    private String keyword;
}
