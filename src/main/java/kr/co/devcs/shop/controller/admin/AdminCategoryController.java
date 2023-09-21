package kr.co.devcs.shop.controller.admin;

import kr.co.devcs.shop.dto.CategoryForm;
import kr.co.devcs.shop.entity.Category;
import kr.co.devcs.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.InvalidParameterException;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(
            @RequestBody @Validated CategoryForm categoryForm,
            BindingResult bindingResult
            ) {
        System.out.println(bindingResult.hasErrors());
        if(bindingResult.hasErrors()) throw new InvalidParameterException(bindingResult.getFieldError().getField());
        categoryService.addCategory(categoryForm);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/get/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(
            @PathVariable("categoryId") long categoryId
    ) {
        Category category = categoryService.getCategory(categoryId).orElseThrow();
        return ResponseEntity.ok().body(category);
    }
}
