package com.ftmnet.homepage.controller;

import com.ftmnet.homepage.dto.CategoryDTO;
import com.ftmnet.homepage.dto.ItemDTO;
import com.ftmnet.homepage.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoryController {

    @GetMapping()
    public CategoryDTO[] categories(@AuthenticationPrincipal User user) {
        return new CategoryDTO[] {
                new CategoryDTO("Main", new ItemDTO[] {
                        new ItemDTO(1, user.getName(), "https://media.ftmnet.com/", "https://ftmnet.com/img/jellyfin_logo.png")
                })
        };
    }
}
