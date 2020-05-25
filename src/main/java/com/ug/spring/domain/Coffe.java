package com.ug.spring.domain;

import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.*;

public class Coffe {
    private long coffeId;

    @NotBlank
    private String name;

    @Digits(integer = 20, fraction = 2)
    @Range
    private double price;

    @Digits(integer = 20, fraction = 0, message = "Wrong number")
    @Range
    private int quantity;

    @NotBlank
    private String category;

    @Digits(integer = 20, fraction = 0)
    @Range
    private int shelfNumber;



    public Coffe(){
        this.coffeId=-1;
    }

    public Coffe(long coffeId, String name, double price, int quantity, String category, int shelfNumber) {
        this.coffeId = coffeId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.shelfNumber = shelfNumber;
    }

    public long getCoffeId() {
        return coffeId;
    }

    public void setCoffeId(long coffeId) {
        this.coffeId = coffeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    @Override
    public String toString(){
        return name + " " + quantity;
    }

    public boolean equals(Object obj) {
        Coffe coffe =  (Coffe)obj;
        if (name.equals(coffe.name) &&
                price == coffe.price &&
                category.equals(coffe.category) &&
                quantity == coffe.quantity)
            return  true;
        else return false;
    }
}
