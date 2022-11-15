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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Items implements Serializable {
        private final Set<Long> data;

        @JsonCreator
        public Items(@JsonProperty("items") Set<Long> ids) throws JsonProcessingException {
            data = ids;
        }
    }

    @Autowired
    @Column(name="items")
    private final Items items;

    public Cart() throws JsonProcessingException {
        name = "cart";
        // carts = new User.Carts(new HashSet<Long>());
        items = new Items(new HashSet<Long>());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItem(Long item) {
        items.data.add(item);
    }

    public List<Long> getItems() {
        return new ArrayList<>(items.data);
    }

    public void removeItem(Long id) {
        items.data.remove(id);
    }

    public void update(Cart updated) {
        // dummy
    }
}
