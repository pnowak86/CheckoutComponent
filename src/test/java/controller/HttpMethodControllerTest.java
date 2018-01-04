package controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


import static org.junit.Assert.*;

public class HttpMethodControllerTest {
    private HttpMethodController servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    ObjectMapper objectMapper;
    StorageController storageController;

    @Before
    public void setUp() {
        servlet = new HttpMethodController();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testStatus_doGet() throws Exception {
        request.addParameter("show", "stock");
        assertEquals(200, response.getStatus());
    }


    @Test
    public void test_doPost() throws Exception {
        request.addParameter("name", "MobilePhone");
        request.addParameter("quantity", String.valueOf(3));
        assertEquals(200, response.getStatus());
    }

}