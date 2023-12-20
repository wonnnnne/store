package com.devtw.store.domain.item.controller;

import com.devtw.store.common.ApiResponse;
import com.devtw.store.domain.item.model.Item;
import com.devtw.store.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping("/item/list")
    public ApiResponse<List<Item>> getAllItems() {
        List<Item> items = itemService.getAll();
        return ApiResponse.success(items);
    }
}
