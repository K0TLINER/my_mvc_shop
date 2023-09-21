package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.CategoryForm;
import kr.co.devcs.shop.entity.Category;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface CategoryService {
    void addCategory(CategoryForm categoryForm);
    Optional<Category> getCategory(long categoryId);
}
