package kr.co.devcs.shop.controller.admin;

import kr.co.devcs.shop.dto.CategoryForm;
import kr.co.devcs.shop.entity.Category;
import kr.co.devcs.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryService categoryService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(
            @Validated @RequestBody CategoryForm categoryForm
            ) {
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

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseEntity<?> getCategoryList(
            @RequestParam(value = "parentCategoryId", required = false) Long parentCategoryId
    ) {
        List<Category> categories = categoryService.getCategoryListByParentCategoryId(parentCategoryId);
        return ResponseEntity.ok().body(categories);
    }
    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateCategory(
            @Validated @RequestBody CategoryForm categoryForm
    ) {
        categoryService.updateCategory(categoryForm);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(
            @PathVariable("categoryId") long categoryId
    ) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok().build();
    }
}
