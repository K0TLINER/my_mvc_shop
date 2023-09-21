package kr.co.devcs.shop.repository;

import kr.co.devcs.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
