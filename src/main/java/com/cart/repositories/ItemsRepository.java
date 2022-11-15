package com.cart.repositories;

import com.cart.models.Item;

// import org.springframework.data.jpa.repository.JpaRepository; <-- for real DB
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Item, Long> {
}
