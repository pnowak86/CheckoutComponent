package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class BasketTest {

    Basket basket;
    Item item;

    @Before
    public void setup() {
        this.basket = new Basket();
        this.item = new Item("LEDTv", new BigInteger("21"), new Integer("2"), new BigInteger("70"));
    }
    @After
    public void teardow() {
        basket.setTotalPrice(new BigInteger("0"));
        basket.getItemMap().clear();
        basket.setPositionsInBasket(new Integer("0"));
        basket.setTotalDiscountetPrice(new BigInteger("0"));
    }


    @Test
    public void shouldAddItem() throws Exception {
        basket.addItem(item,3);
        assertNotNull(basket.getItemMap().get(1));

    }

    @Test
    public void shouldCalculateTotalPrice() throws Exception {
        assertEquals(new BigInteger("126"),basket.calculateTotalPrice(item,6));
    }


    @Test
    public void shouldCalculateTotalDiscountedPrice() throws Exception {
        basket.addItem(item,2);
        assertEquals(new BigInteger("70"),basket.calculateTotalDiscountedPrice());
    }

    @Test
    public void shouldGetTotalPrice() throws Exception {
        basket.addItem(item,2);
        assertEquals(new BigInteger("42"),basket.getTotalPrice());
    }

    @Test
    public void shouldSetTotalPrice() throws Exception {
        basket.setTotalPrice(new BigInteger("111"));
        assertEquals(new BigInteger("111"),basket.getTotalPrice());
    }

    @Test
    public void shouldGetItemMap() throws Exception {
        assertNotNull(basket.getItemMap());
    }


    @Test
    public void shouldGetPositionsInBasket() throws Exception {
      basket.addItem(item,new Integer(3));
      assertEquals(1,basket.getPositionsInBasket());
    }

    @Test
    public void shouldSetPositionsInBasket() throws Exception {
        basket.setPositionsInBasket(new Integer(6));
        assertEquals(6,basket.getPositionsInBasket());

    }

    @Test
    public void shouldGetTotalDiscountetPrice() throws Exception {
        basket.addItem(item,2);
        basket.calculateTotalDiscountedPrice();
        assertEquals(new BigInteger("70"), basket.getTotalDiscountetPrice());
    }

    @Test
    public void shouldSetTotalDiscountetPrice() throws Exception {
        basket.setTotalDiscountetPrice(new BigInteger("99"));
        assertEquals(new BigInteger("99"), basket.getTotalDiscountetPrice());
    }

}