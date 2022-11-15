package com.cart.rest.user;

import com.cart.models.Cart;
import com.cart.models.User;
import com.cart.services.CartsService;
import com.cart.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CartsService cartsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty())
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(String.format("User %s not found", id));
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> create() throws JsonProcessingException {
        return new ResponseEntity<>(userService.create(new User()), HttpStatus.OK);
    }

    @PostMapping("/{id}/cart")
    public ResponseEntity<?> createCart(@PathVariable Long id) throws JsonProcessingException {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(String.format("User %s not found", id));
        user.get().addCart(cartsService.create(new Cart()));
        return new ResponseEntity<>(userService.update(user.get().getId(), user.get()), HttpStatus.OK);
    }

    @GetMapping("/{uid}/cart/{cid}")
    public ResponseEntity<?> getCart(@PathVariable Long uid, @PathVariable Long cid) {
        {
            Optional<User> opt = userService.findById(uid);
            if (opt.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(String.format("User %s not found", uid));
            User user = opt.get();
            if (!user.getCarts().contains(cid))
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(String.format("Cart %s not found", cid));
        }
        Optional<Cart> opt = cartsService.findById(cid);
        if (opt.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(String.format("Cart %s not found", cid));
        Cart cart = opt.get();
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @PutMapping("/{uid}/cart/{cid}")
    public ResponseEntity<?> addItem(@PathVariable Long uid, @PathVariable Long cid, @RequestParam Long item) {
        {
            Optional<User> opt = userService.findById(uid);
            if (opt.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(String.format("User %s not found", uid));
            User user = opt.get();
            if (!user.getCarts().contains(cid))
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(String.format("Cart %s not found", cid));
        }
        Optional<Cart> opt = cartsService.findById(cid);
        if (opt.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(String.format("Cart %s not found", cid));
        Cart cart = opt.get();
        cart.addItem(item);
        return new ResponseEntity<>(cartsService.update(cid, cart), HttpStatus.OK);
    }
}
