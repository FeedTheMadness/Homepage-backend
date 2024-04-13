package com.ftmnet.homepage.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin
public class ItemController {

    @PostMapping("/add")
    public void addItem(@RequestHeader("Remote-User") String remoteUser) {

    }
}
