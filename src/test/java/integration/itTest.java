package integration;

import model.Basket;
import model.Item;
import org.junit.Assert;

import org.junit.Test;

import java.math.BigInteger;

public class itTest {



    @Test
    public void IntegrationTest1(){
        Basket basket = new Basket();
        Item item = new Item("LEDTv", new BigInteger("20"), new Integer("4"), new BigInteger("35"));
        Item item2 = new Item("DvdPlayer", new BigInteger("50"), new Integer("2"), new BigInteger("60"));
        Item item3 = new Item("MobilePhone", new BigInteger("100"), new Integer("3"), new BigInteger("120"));
        basket.addItem(item, 45);

        Assert.assertEquals(new BigInteger("405"),basket.calculateTotalDiscountedPrice());
        Assert.assertEquals(1, basket.getItemMap().size());

        basket.addItem(item2, 1);
        basket.addItem(item3, 1);

        Assert.assertEquals(3, basket.getItemMap().size());

        Assert.assertEquals(new BigInteger("555"),basket.calculateTotalDiscountedPrice());

        Assert.assertEquals(1, item3.getQuantityInBasket());


    }




}
