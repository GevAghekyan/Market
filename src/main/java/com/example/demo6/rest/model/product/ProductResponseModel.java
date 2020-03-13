package com.example.demo6.rest.model.product;

import java.io.Serializable;
import java.util.Objects;

public class ProductResponseModel implements Serializable {

    private static final long serialVersionUID = 1377600073114610892L;

    private Long id;
    private String name;
    private int price;
    private String categoryName;

    public ProductResponseModel() {
    }

    public ProductResponseModel(Long id, String name, int price, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponseModel that = (ProductResponseModel) o;
        return price == that.price &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, categoryName);
    }

    @Override
    public String toString() {
        return "ProductResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
