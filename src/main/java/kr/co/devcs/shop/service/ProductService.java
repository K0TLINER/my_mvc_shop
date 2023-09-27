package kr.co.devcs.shop.service;

import kr.co.devcs.shop.dto.ProductForm;
import kr.co.devcs.shop.dto.SearchForm;
import kr.co.devcs.shop.entity.Product;
import org.springframework.beans.factory.annotation.Value;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface ProductService {
    void addProduct(ProductForm productForm);
    Optional<Product> getProduct(long productId);
    List<Product> getProductList(SearchForm searchForm);
    Product updateProduct(ProductForm productForm);
    void deleteProduct(long productId);
}
