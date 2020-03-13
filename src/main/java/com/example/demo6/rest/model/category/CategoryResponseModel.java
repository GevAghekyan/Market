package com.example.demo6.rest.model.category;

import java.io.Serializable;
import java.util.Objects;

public class CategoryResponseModel implements Serializable {

    private static final long serialVersionUID = -6473864344437117360L;

    private String name;
    private Long id;
    private int quantity;

    public CategoryResponseModel() {
    }

    public CategoryResponseModel(String name, Long id, int quantity) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryResponseModel that = (CategoryResponseModel) o;
        return quantity == that.quantity &&
                Objects.equals(name, that.name) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, quantity);
    }

    @Override
    public String toString() {
        return "CategoryResponseModel{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
