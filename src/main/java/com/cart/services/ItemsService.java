package com.cart.services;

import com.cart.models.Item;
import com.cart.repositories.ItemsRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemsService {
    @Autowired
    ItemsRepository repository;

    public Item create(Item item) {
        return repository.save(item);
    }

    public List<Item> findAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    public Optional<Item> update(Long id, Item updated) {
        Optional<Item> optional = this.repository.findById(id);
        if (optional.isEmpty())
            return Optional.empty();
        Item item = optional.get();
        item.update(updated);
        return Optional.of(this.repository.save(item));
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public Optional<Item> findById(Long id) {
        return this.repository.findById(id);
    }
}
