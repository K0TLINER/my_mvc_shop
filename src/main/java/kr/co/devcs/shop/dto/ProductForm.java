package kr.co.devcs.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ProductForm {
    private Long productId = null;
    @NotBlank(message = "제품 이름은 필수입니다.")
    private String productName;
    @NotBlank(message = "제품 설명은 필수입니다.")
    private String productDescription;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate manufactureDate;
    @NotNull(message = "제품 가격은 필수입니다.")
    private Long price;
    @NotNull(message = "제품 재고는 필수입니다.")
    private Long stock;
    @NotNull(message = "제품 카테고리는 필수입니다.")
//    private CategoryForm categoryForm;
    private long categoryId;
}
