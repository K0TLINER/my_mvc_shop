package kr.co.devcs.shop.controller.admin;

import kr.co.devcs.shop.dto.ProductForm;
import kr.co.devcs.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.InvalidParameterException;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {
    private final ProductService productService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(
            @Valid @RequestBody ProductForm productForm,
            BindingResult bindingResult
            ) {
        if(bindingResult.hasErrors()) throw new InvalidParameterException();
        productService.addProduct(productForm);
        return ResponseEntity.ok().build();
    }
}
