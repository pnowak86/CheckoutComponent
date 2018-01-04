package controller;

import model.Item;
import org.junit.Before;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class StorageControllerTest {

    StorageController storageController;
    Item item;
    File tempFile;

    @Before
    public void setup() throws IOException {
        this.storageController = new StorageController();
        item = new Item("LEDTv", new BigInteger("21"), new Integer("2"), new BigInteger("70"));
        tempFile = File.createTempFile("tempFile","txt");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void loadStockFromFile_shouldReturnList() throws Exception {
        assertNotNull(storageController.loadStockFromFile(tempFile));
    }

    @Test
    public void loadStockFromFile_shouldReturnNotEmptyList() throws Exception {
        try( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))){
            bufferedWriter.write("LEDTv;40;3;for 70");
        }catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(storageController.loadStockFromFile(tempFile).get(0));
    }

    @Test
    public void loadStockFromFile_shouldHaveItemsTypeInArray() throws Exception {
        try( BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))){
            bufferedWriter.write("LEDTv;40;3;for 70");
        }catch (IOException e) {
            e.printStackTrace();
        }
        Object tempItem = storageController.loadStockFromFile(tempFile).get(0);
        assertEquals(item.getClass(), tempItem.getClass());

    }

}