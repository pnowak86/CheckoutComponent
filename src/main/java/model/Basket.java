package model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public class Basket {
    private static BigInteger totalPrice = new BigInteger("0");
    private Map<Integer, Item> itemMap = new HashMap<>();
    private static int positionsInBasket = 0;
    private static BigInteger totalDiscountetPrice = new BigInteger("0");

    public Basket() {

    }

    public void addItem(Item item, Integer quantity) {

        if (itemMap != null) {
            positionsInBasket++;
            item.setQuantityInBasket(quantity);
            itemMap.put(positionsInBasket, item);
            calculateTotalPrice(item, quantity);

        }
    }

    public BigInteger calculateTotalPrice(Item item, Integer quantity) {
        return totalPrice = totalPrice.add(item.getPrice().multiply(BigInteger.valueOf(quantity)));
    }

    public BigInteger calculateTotalDiscountedPrice() {
        BigInteger priceWithDiscount = new BigInteger("0");

        if (itemMap.size() > 0) {

            for (int i = 1; i <= itemMap.size(); i++) {
                Item item = itemMap.get(i);
                int discountedGroups = item.getQuantityInBasket() / item.getDiscountQuantity();
                int restWithoutDiscount = item.getQuantityInBasket() % item.getDiscountQuantity();

                BigInteger discountedGroupsPrice =
                        new BigInteger(String.valueOf(item.getSpecialPrice().multiply(BigInteger.valueOf(discountedGroups))));

                BigInteger priceOfRestItems =
                        new BigInteger(String.valueOf(item.getPrice().multiply(BigInteger.valueOf(restWithoutDiscount))));

                priceWithDiscount = priceWithDiscount.add(discountedGroupsPrice.add(priceOfRestItems));
            }
        }
        return totalDiscountetPrice = priceWithDiscount;
    }


    public BigInteger getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigInteger totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, Item> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<Integer, Item> itemMap) {
        this.itemMap = itemMap;
    }

    public int getPositionsInBasket() {
        return positionsInBasket;
    }

    public void setPositionsInBasket(int positionsInBasket) {
        this.positionsInBasket = positionsInBasket;
    }

    public BigInteger getTotalDiscountetPrice() {
        return totalDiscountetPrice;
    }

    public void setTotalDiscountetPrice(BigInteger totalDiscountetPrice) {
        Basket.totalDiscountetPrice = totalDiscountetPrice;
    }
}
