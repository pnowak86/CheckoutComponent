package model;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private BigInteger price;
    private Integer discountQuantity;
    private BigInteger specialPrice;
    private int quantityInBasket;


    public Item() {
    }

    public Item(String name, BigInteger price, Integer discountQuantity, BigInteger specialPrice) {
        this.name = name;
        this.price = price;
        this.discountQuantity = discountQuantity;
        this.specialPrice = specialPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }


    public Integer getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(Integer discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public BigInteger getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigInteger specialPrice, Item item) {
        this.specialPrice = specialPrice;
    }

    public int getQuantityInBasket() {
        return quantityInBasket;
    }

    public void setQuantityInBasket(int quantityInBasket) {
        this.quantityInBasket = quantityInBasket;
    }
}
