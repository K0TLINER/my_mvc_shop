package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.CategoryForm;
import kr.co.devcs.shop.entity.Category;
import kr.co.devcs.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired private CategoryRepository categoryRepository;
    @Override
    public void addCategory(CategoryForm categoryForm) {
        Category category = Category.builder()
                .categoryName(categoryForm.getCategoryName())
                .parentCategory(categoryForm.getParentCategoryId() != null ? this.getCategory(categoryForm.getParentCategoryId()).orElseThrow() : null)
                .build();
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategory(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public List<Category> getCategoryListByParentCategoryId() {
        return categoryRepository.findAllByParentCategoryCategoryId(null);
    }

    @Override
    public List<Category> getCategoryListByParentCategoryId(Long parentCategoryId) {
        if(parentCategoryId != null)
            return categoryRepository.findAllByParentCategoryCategoryId(parentCategoryId);
        return this.getCategoryListByParentCategoryId();
    }
}
