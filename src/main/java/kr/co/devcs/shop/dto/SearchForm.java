package kr.co.devcs.shop.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class SearchForm {
    private int currentPage = 0;
    private String keyword;
    private Long categoryId;
    private Long minPrice;
    private Long maxPrice;
    private String sortData = "registrationDate";
    private String sortType = "desc";
}
