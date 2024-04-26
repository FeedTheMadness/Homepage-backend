package com.ftmnet.homepage.controller;

import com.ftmnet.homepage.dto.item.ItemDTO;
import com.ftmnet.homepage.dto.item.ItemEditDTO;
import com.ftmnet.homepage.dto.item.ItemMapper;
import com.ftmnet.homepage.entity.Item;
import com.ftmnet.homepage.entity.User;
import com.ftmnet.homepage.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/items")
@CrossOrigin
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ItemDTO> getItems(@AuthenticationPrincipal User user) {
        Stream<Item> itemStream = itemService.getUserItemStream(user);
        return itemMapper.toDTOStream(itemStream).toList();
    }

    @GetMapping("/{itemId}")
    @PreAuthorize("isAuthenticated()")
    public ItemDTO getItem(@AuthenticationPrincipal User user, @PathVariable long itemId) {
        Item item = itemService.getItem(user, itemId);
        return itemMapper.toDTO(item);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ItemDTO addItem(@AuthenticationPrincipal User user, @RequestBody ItemEditDTO itemDTO) {
        //TODO add user checks
        Item item = itemService.addItem(user, itemMapper.fromDTO(itemDTO));
        return itemMapper.toDTO(item);
    }
}
