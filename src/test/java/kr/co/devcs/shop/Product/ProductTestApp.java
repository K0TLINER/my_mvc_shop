package kr.co.devcs.shop.Product;

import kr.co.devcs.shop.dto.ProductForm;
import kr.co.devcs.shop.entity.Product;
import kr.co.devcs.shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ProductTestApp {

    @Autowired private ProductRepository productRepository;
    @Test
    public void addProduct() {
        productRepository.save(Product.builder()
                .productId(1L)
                .productName("Test")
                .productDescription("0")
                .manufactureDate(LocalDate.now().minusDays(5))
                .build()
        );
    }
}
