package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.ProductForm;
import kr.co.devcs.shop.dto.SearchForm;
import kr.co.devcs.shop.entity.Category;
import kr.co.devcs.shop.entity.Product;
import kr.co.devcs.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Override
    public void addProduct(ProductForm productForm) {
        Product product = Product.builder()
                .productName(productForm.getProductName())
                .productDescription(productForm.getProductDescription())
                .price(productForm.getPrice())
                .stock(productForm.getStock())
                .manufactureDate(productForm.getManufactureDate())
                .category(categoryService.getCategory(productForm.getCategoryForm().getParentCategoryId()).orElseThrow())
                .build();
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getProduct(long productId) {
        return Optional.empty();
    }

    @Override
    public List<Product> getProductList(SearchForm searchForm) {
        return null;
    }

    @Override
    public void updateProduct(long productId, ProductForm productForm) {

    }

    @Override
    public void deleteProduct(long productId) {

    }
}
