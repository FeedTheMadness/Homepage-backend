package com.ftmnet.homepage.service;

import com.ftmnet.homepage.entity.Category;
import com.ftmnet.homepage.entity.User;
import com.ftmnet.homepage.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategory(User user, long categoryId) {
        //TODO cleanup checks
        return categoryRepository.findById(categoryId)
                .filter(category -> category.getUser().getId() == user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id %d not found".formatted(categoryId)));
    }

    @Transactional
    public Stream<Category> getUserCategoryStream(User user) {
        if (Objects.isNull(user)) {
            return Stream.empty();
        }
        return categoryRepository.streamAllByUserOrUserNull(user);
    }

    @Transactional
    public List<Category> getUserCategories(User user) {
        return getUserCategoryStream(user).toList();
    }

    public Category addCategory(User user, Category category) {
        //TODO add checks
        category.setUser(user);
        return categoryRepository.save(category);
    }
}
