package com.cart.rest.items;

import com.cart.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/items")
public class ItemsController {
    @Autowired
    ItemsService service;
    // impl next
}
