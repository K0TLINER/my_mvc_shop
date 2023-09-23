package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.ProductForm;
import kr.co.devcs.shop.dto.SearchForm;
import kr.co.devcs.shop.entity.Category;
import kr.co.devcs.shop.entity.Product;
import kr.co.devcs.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
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
        return productRepository.findAll();
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

        return productRepository.save(product);
    }
    @Override
    public void deleteProduct(long productId) {
        Product product = this.getProduct(productId).orElseThrow();
        productRepository.delete(product);
    }
}
