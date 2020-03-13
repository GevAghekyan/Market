package com.example.demo6.rest.model.category;

import java.io.Serializable;
import java.util.Objects;

public class CategoryRequestModel implements Serializable {

    private static final long serialVersionUID = -6201494674964215881L;

    private String name;

    public CategoryRequestModel() {
    }

    public CategoryRequestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryRequestModel that = (CategoryRequestModel) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CategoryResponseModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
