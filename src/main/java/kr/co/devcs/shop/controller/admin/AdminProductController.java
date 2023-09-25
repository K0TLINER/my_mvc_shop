package kr.co.devcs.shop.controller.admin;

import kr.co.devcs.shop.dto.ProductForm;
import kr.co.devcs.shop.dto.SearchForm;
import kr.co.devcs.shop.entity.Product;
import kr.co.devcs.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminProductController {
    private final ProductService productService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(
            @Valid @RequestBody ProductForm productForm
    ) {
        productService.addProduct(productForm);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(
            @PathVariable("productId") long productId
    ) {
        Product product = productService.getProduct(productId).orElseThrow();
        return ResponseEntity.ok().body(product);
    }
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseEntity<?> getProductList(

    ) {
        List<Product> products = productService.getProductList(new SearchForm(10));
        return ResponseEntity.ok().body(products);
    }
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateProduct(
            @Validated @RequestBody ProductForm productForm
    ) {
        productService.updateProduct(productForm);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(
            @PathVariable("productId") long productId
    ) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}
