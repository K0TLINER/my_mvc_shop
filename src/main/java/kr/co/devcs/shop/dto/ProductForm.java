package kr.co.devcs.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ProductForm {
    @NotBlank(message = "제품 이름은 필수입니다.")
    private String productName;
    @NotBlank(message = "제품 설명은 필수입니다.")
    private String productDescription;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate manufactureDate;
    @NotBlank(message = "제품 가격은 필수입니다.")
    private Long price;
    @NotBlank(message = "제품 재고는 필수입니다.")
    private Long stock;
    @NotBlank(message = "제품 카테고리는 필수입니다.")
    private CategoryForm categoryForm;
}
