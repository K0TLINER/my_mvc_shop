package kr.co.devcs.shop.specifications;

import kr.co.devcs.shop.dto.SearchForm;
import kr.co.devcs.shop.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecifications {
    public static Specification<Product> filterBySearchForm(SearchForm searchForm) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchForm.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchForm.getMinPrice()));
            }
            if (searchForm.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchForm.getMaxPrice()));
            }
            if (searchForm.getCategoryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("categoryId"), searchForm.getCategoryId()));
            }
            if (searchForm.getKeyword() != null && !searchForm.getKeyword().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + searchForm.getKeyword() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
