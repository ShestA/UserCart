package com.cart.models;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Long id;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Carts implements Serializable {
        private final Set<Long> data;

        @JsonCreator
        public Carts(@JsonProperty("ids") Set<Long> ids) throws JsonProcessingException {
            data = ids;
        }
    }

    @Autowired
    @Column(name="carts")
    private final Carts carts;

    public User() throws JsonProcessingException {
        carts = new Carts(new HashSet<Long>());
    }

    public Long getId() {
        return id;
    }

    public Set<Long> getCarts() {
        return carts.data;
    }

    public void addCart(Cart cart) {
        carts.data.add(cart.getId());
    }
    public void update(User updated) {
        // dummy
    }
}
