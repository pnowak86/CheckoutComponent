package controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Basket;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class HttpMethodController extends HttpServlet {

    private ObjectMapper objectMapper;
    private StorageController storageController = new StorageController();
    private List<Item> stockList;
    private Basket clientBasket;
    private ClassLoader classLoader = getClass().getClassLoader();
    private File basketFile = new File(classLoader.getResource("basket.txt").getFile());
    private File stockFile = new File(classLoader.getResource("storagestate.txt").getFile());


    public HttpMethodController() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        stockList = storageController.loadStockFromFile(stockFile);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String showParameter = req.getParameter("show");
        if (showParameter.equals("stock")) {
            if (stockList.size() != 0) {
                for (int i = 0; i < stockList.size(); i++) {
                    String itemsAsString = objectMapper.writeValueAsString(stockList.get(i));
                    out.println(itemsAsString);
                    resp.setStatus(200);
                }
            } else {
                resp.setStatus(400);
                out.println("There is nothing in the store!");
            }
        } else if (showParameter.equals("basket")) {
            clientBasket = storageController.loadBasketFromFile(basketFile);
            int numberOfItems = clientBasket.getItemMap().size();
            if (numberOfItems > 0) {
                for (int i = 1; i <= numberOfItems; i++) {
                    String itemAsString = objectMapper.writeValueAsString(clientBasket.getItemMap().get(i));
                    out.println(itemAsString);
                }
                out.println(clientBasket.getPositionsInBasket() +
                        " positions in basket, for total price without discount " + clientBasket.getTotalPrice());

                out.println("Final price with discount is: " + clientBasket.calculateTotalDiscountedPrice());
                storageController.saveBasketToFile(clientBasket, basketFile);
                resp.setStatus(200);
            } else {
                resp.setStatus(400);
                out.println("There is nothing in the basket!");

            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String nameParameter = req.getParameter("name");
        Integer quantityParameter = Integer.valueOf(req.getParameter("quantity"));
        clientBasket = storageController.loadBasketFromFile(basketFile);
        Map<Integer, Item> tempMap = clientBasket.getItemMap();
        for (int i = 0; i < stockList.size(); i++) {
            Item tempItem = stockList.get(i);

            if (tempItem.getName().equals(nameParameter)) {
                boolean added = false;
                for (int j = 1; j <= tempMap.size(); j++) {
                    if (tempMap.get(j).getName().equals(tempItem.getName())) {
                        int sum = tempMap.get(j).getQuantityInBasket() + quantityParameter;
                        tempMap.get(j).setQuantityInBasket(sum);
                        clientBasket.calculateTotalPrice(tempItem, quantityParameter);
                        added = true;
                        resp.setStatus(201);
                        out.print("successfully added, you have " + clientBasket.getPositionsInBasket() + " position(s) in the basket!");
                        storageController.saveBasketToFile(clientBasket,basketFile);
                    }
                }
                if (added == false) {
                    clientBasket.addItem(tempItem, quantityParameter);
                    storageController.saveBasketToFile(clientBasket,basketFile);
                    resp.setStatus(201);
                    out.print("successfully added, you have " + clientBasket.getPositionsInBasket() + " position(s) in the basket!");
                }
            }
        }
    }
}

