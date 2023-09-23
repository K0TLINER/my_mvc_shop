package kr.co.devcs.shop.repository;

import kr.co.devcs.shop.entity.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentCategoryCategoryId(Long parentCategoryId, Sort sort);
    @Override
    boolean existsById(Long categoryId);
}
