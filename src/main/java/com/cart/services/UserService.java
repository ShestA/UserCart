package com.cart.services;

import com.cart.models.User;
import com.cart.repositories.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public List<User> findAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    public Optional<User> update(Long id, User updated) {
        Optional<User> optional = this.repository.findById(id);
        if (optional.isEmpty())
            return Optional.empty();
        User user = optional.get();
        user.update(updated);
        return Optional.of(this.repository.save(updated));
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return this.repository.findById(id);
    }
}
