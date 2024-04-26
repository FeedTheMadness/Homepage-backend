package com.ftmnet.homepage.controller;

import com.ftmnet.homepage.dto.category.CategoryDTO;
import com.ftmnet.homepage.dto.category.CategoryMapper;
import com.ftmnet.homepage.entity.Category;
import com.ftmnet.homepage.entity.User;
import com.ftmnet.homepage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{categoryId}")
    @PreAuthorize("isAuthenticated()")
    public CategoryDTO getCategory(@AuthenticationPrincipal User user, @PathVariable long categoryId) {
        Category category = categoryService.getCategory(user, categoryId);
        return categoryMapper.toDTO(category);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public CategoryDTO addCategory(@AuthenticationPrincipal User user, @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.addCategory(user, categoryMapper.fromDTO(categoryDTO));
        return categoryMapper.toDTO(category);
    }

    @GetMapping
    public List<CategoryDTO> categories(@AuthenticationPrincipal User user) {
        Stream<Category> categoryStream = categoryService.getUserCategoryStream(user);
        return categoryMapper.toDTOStream(categoryStream).toList();
    }
}
