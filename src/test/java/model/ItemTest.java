package model;

import controller.StorageController;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ItemTest {


    Item item;

    @Before
    public void setup() {
        this.item = new Item("LEDTv", new BigInteger("21"), new Integer("2"), new BigInteger("70"));
    }


    @Test
    public void shouldGetName() throws Exception {
        assertEquals("LEDTv", item.getName());

    }

    @Test
    public void shouldSetName() throws Exception {
        item.setName("Mp3Player");
        assertEquals("Mp3Player", item.getName());
    }

    @Test
    public void shouldGetPrice() throws Exception {
        assertEquals(new BigInteger("21"), item.getPrice());
    }

    @Test
    public void shouldSetPrice() throws Exception {
        item.setPrice(new BigInteger("12"));
        assertEquals(new BigInteger("12"), item.getPrice());
    }

    @Test
    public void shouldGetDiscountQuantity() throws Exception {
        assertEquals(new Integer("2"), item.getDiscountQuantity());
    }

    @Test
    public void shouldSetDiscountQuantity() throws Exception {
        item.setDiscountQuantity(new Integer("11"));
        assertEquals(new Integer("11"), item.getDiscountQuantity());
    }

    @Test
    public void shouldGetSpecialPrice() throws Exception {
        assertEquals(new BigInteger("70"), item.getSpecialPrice());
    }

    @Test
    public void shouldSetSpecialPrice() throws Exception {
        item.setSpecialPrice(new BigInteger("190"), item);
        assertEquals(new BigInteger("190"), item.getSpecialPrice());
    }

    @Test
    public void shouldGetQuantityInBasket() throws Exception {
        assertEquals(0, item.getQuantityInBasket());
    }

    @Test
    public void shouldSetQuantityInBasket() throws Exception {
        item.setQuantityInBasket(2);
        assertEquals(2, item.getQuantityInBasket());
    }

}