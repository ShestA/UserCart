package com.cart.repositories;

import com.cart.models.User;

// import org.springframework.data.jpa.repository.JpaRepository; <-- for real DB
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
