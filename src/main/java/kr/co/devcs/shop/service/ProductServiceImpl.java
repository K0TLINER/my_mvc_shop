package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.ProductForm;
import kr.co.devcs.shop.dto.SearchForm;
import kr.co.devcs.shop.entity.Category;
import kr.co.devcs.shop.entity.Product;
import kr.co.devcs.shop.repository.ProductRepository;
import kr.co.devcs.shop.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    @Value("${product.page-size}") private int pageSize;
    @Override
    public void addProduct(ProductForm productForm) {
        Category category = categoryService.getCategory(productForm.getCategoryId()).orElseThrow();
        Product product = Product.builder()
                .productName(productForm.getProductName())
                .productDescription(productForm.getProductDescription())
                .price(productForm.getPrice())
                .stock(productForm.getStock())
                .manufactureDate(productForm.getManufactureDate())
                .category(category)
                .build();
        productRepository.save(product);
    }
    @Override
    public Optional<Product> getProduct(long productId) {
        return productRepository.findById(productId);
    }
    @Override
    public List<Product> getProductList(SearchForm searchForm) {
        Sort.Order firstSort = new Sort.Order(
                searchForm.getSortType().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                searchForm.getSortData()
        );
        Sort.Order secondSort = new Sort.Order(
                Sort.Direction.DESC,
                "registrationDate"
        );
        Sort sort = Sort.by(firstSort, secondSort);

        Pageable pageable = PageRequest.of(searchForm.getCurrentPage(), pageSize, sort);
        Page<Product> result = productRepository.findAll(ProductSpecifications.filterBySearchForm(searchForm), pageable);

        return result.getContent();
    }
    @Override
    public Product updateProduct(ProductForm productForm) {
        Product product = this.getProduct(productForm.getProductId()).orElseThrow();
        product.setProductName(productForm.getProductName());
        product.setProductDescription(productForm.getProductDescription());
        product.setStock(productForm.getStock());
        product.setPrice(productForm.getPrice());
        product.setManufactureDate(productForm.getManufactureDate());
        product.setCategory(categoryService.getCategory(productForm.getCategoryId()).orElseThrow());

        return this.updateProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long productId) {
        Product product = this.getProduct(productId).orElseThrow();
        productRepository.delete(product);
    }
}
