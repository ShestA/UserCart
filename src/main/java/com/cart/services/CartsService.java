package com.cart.services;

import com.cart.models.Cart;
import com.cart.repositories.CartsRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartsService {
    @Autowired
    CartsRepository repository;

    public Cart create(Cart cart) {
        return repository.save(cart);
    }

    public List<Cart> findAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    public Optional<Cart> update(Long id, Cart updated) {
        Optional<Cart> optional = this.repository.findById(id);
        if (optional.isEmpty())
            return Optional.empty();
        Cart cart = optional.get();
        cart.update(updated);
        return Optional.of(this.repository.save(cart));
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public Optional<Cart> findById(Long id) {
        return this.repository.findById(id);
    }
}
