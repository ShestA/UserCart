package com.cart.repositories;

import com.cart.models.Cart;

// import org.springframework.data.jpa.repository.JpaRepository; <-- for real DB
import org.springframework.data.repository.CrudRepository;

public interface CartsRepository extends CrudRepository<Cart, Long> {
}
