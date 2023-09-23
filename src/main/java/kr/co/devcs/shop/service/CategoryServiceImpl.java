package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.CategoryForm;
import kr.co.devcs.shop.entity.Category;
import kr.co.devcs.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public Category addCategory(CategoryForm categoryForm) {
        Category category = Category.builder()
                .categoryName(categoryForm.getCategoryName())
                .parentCategory(categoryForm.getParentCategoryId() != null ? this.getCategory(categoryForm.getParentCategoryId()).orElseThrow() : null)
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategory(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public List<Category> getCategoryListByParentCategoryId() {
        return categoryRepository.findAllByParentCategoryCategoryId(null, Sort.by("categoryName").ascending());
    }

    @Override
    public List<Category> getCategoryListByParentCategoryId(Long parentCategoryId) {
        if(parentCategoryId != null)
            return categoryRepository.findAllByParentCategoryCategoryId(parentCategoryId, Sort.by("categoryName").ascending());
        return this.getCategoryListByParentCategoryId();
    }

    @Override
    public Category updateCategory(CategoryForm categoryForm) {
        Category category = this.getCategory(categoryForm.getCategoryId()).orElseThrow();
        category.setCategoryName(categoryForm.getCategoryName());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        Category category = this.getCategory(categoryId).orElseThrow();
        categoryRepository.delete(category);
    }

    @Override
    public boolean existsByCategoryId(long categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
