package kr.co.devcs.shop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {
    @NotBlank(message = "카테고리 이름은 필수입니다.")
    @NotNull(message = "카테고리 이름은 필수입니다.")
    private String categoryName;
    private Long parentCategoryId;
}
