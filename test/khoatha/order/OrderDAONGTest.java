/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author anhkhoa
 */
public class OrderDAONGTest {

    public OrderDAONGTest() {
    }

    @Test
    public void testCountOrder() {
        OrderDAO orderDAO = new OrderDAO();
        try {
            int count = orderDAO.countOrder();

            Assert.assertEquals(count, 16);
        } catch (NamingException e) {
            Assert.fail("Naming Exception: " + e.getMessage());
        } catch (SQLException e) {
            Assert.fail("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found Exception: " + e.getMessage());
        }

    }

    @Test
    public void testInsertOrder() {
        try {
            OrderDAO orderDAO = new OrderDAO();
            String orderId = "OD123";
            float total = 100.0F;
            
            boolean result = orderDAO.insertOrder(orderId, total);
            
            assertTrue(result, "Insert Order must be true");
        } catch (NamingException e) {
            Assert.fail("Naming Exception: " + e.getMessage());
        } catch (SQLException e) {
            Assert.fail("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found Exception: " + e.getMessage());
        }

    }
    
    @Test 
    public void testShowOrderByIDWithValidID() {
        try {
            OrderDAO orderDAO = new OrderDAO();
            String orderId = "HD001";
            
            OrderDTO orderDTO = orderDAO.showOrderByID(orderId);
            
            assertNotNull(orderDTO, "Order must not be null");
            assertEquals(orderDTO.getId(), orderId);
        } catch (NamingException e) {
            Assert.fail("Naming Exception: " + e.getMessage());
        } catch (SQLException e) {
            Assert.fail("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found Exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testShowOrderByIDWithInvalidID() {
        try {
            OrderDAO orderDAO = new OrderDAO();
            String orderId = "aaaaa";
            
            OrderDTO orderDTO = orderDAO.showOrderByID(orderId);
            
            assertNull(orderDTO, "Order must be null with invalid ID");
        } catch (NamingException e) {
            Assert.fail("Naming Exception: " + e.getMessage());
        } catch (SQLException e) {
            Assert.fail("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found Exception: " + e.getMessage());
        }
    }

//    @BeforeClass
//    public static void setUpClass() throws Exception {
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//
//    @BeforeMethod
//    public void setUpMethod() throws Exception {
//    }
//
//    @AfterMethod
//    public void tearDownMethod() throws Exception {
//    }
//
//    @Test
//    public void testCountOrder() throws Exception {
//        System.out.println("countOrder");
//        OrderDAO instance = new OrderDAO();
//        int expResult = 0;
//        int result = instance.countOrder();
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testInsertOrder() throws Exception {
//        System.out.println("insertOrder");
//        String orderID = "";
//        float total = 0.0F;
//        OrderDAO instance = new OrderDAO();
//        boolean expResult = false;
//        boolean result = instance.insertOrder(orderID, total);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testShowOrderByID() throws Exception {
//        System.out.println("showOrderByID");
//        String id = "";
//        OrderDAO instance = new OrderDAO();
//        OrderDTO expResult = null;
//        OrderDTO result = instance.showOrderByID(id);
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
}
