package controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Basket;
import model.Item;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class StorageController {

    public List loadStockFromFile(File filepath) {
        List<Item> shopStock = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split(";");
                int lastStringLenght = lineSplit[3].length();
                BigInteger specialPrice = new BigInteger(lineSplit[3].substring(lastStringLenght - 2, lastStringLenght));
                Item item = new Item(lineSplit[0], new BigInteger(lineSplit[1]), Integer.valueOf(lineSplit[2]),
                        specialPrice);
                shopStock.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shopStock;
    }

    public void saveBasketToFile(Basket basket, File filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            String basketAsString = objectMapper.writeValueAsString(basket);
            bufferedWriter.write(basketAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Basket loadBasketFromFile(File filepath) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)))) {

            String line = "";
            Basket tempBasket = null;
            if((line = bufferedReader.readLine()) != null) {
                   tempBasket = objectMapper.readValue(line, Basket.class);
                   return tempBasket;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Basket();

    }



}


