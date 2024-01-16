/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import java.sql.SQLException;
import javax.naming.NamingException;
import org.testng.Assert;
import static org.testng.Assert.*;
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
        System.out.println("Running testCountOrder");

        OrderDAO orderDAO = new OrderDAO();
        try {
            int count = orderDAO.countOrder();

            Assert.assertEquals(count, 19);
            System.out.println("testCountOrder passed! Count: " + count);
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
        System.out.println("Running testInsertOrder");
        try {
            OrderDAO orderDAO = new OrderDAO();
            String orderId = "OD126";
            float total = 100.0F;

            boolean result = orderDAO.insertOrder(orderId, total);
            assertTrue(result, "Insert Order must be true");
            System.out.println("testInsertOrder passed!");
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
        System.out.println("Running testShowOrderByIDWithValidID");
        try {
            OrderDAO orderDAO = new OrderDAO();
            String orderId = "HD001";

            OrderDTO orderDTO = orderDAO.showOrderByID(orderId);

            assertNotNull(orderDTO, "Order must not be null");
            assertEquals(orderDTO.getId(), orderId);
            System.out.println("testShowOrderByIDWithValidID passed!");
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
        System.out.println("Running testShowOrderByIDWithInvalidID");
        try {
            OrderDAO orderDAO = new OrderDAO();
            String orderId = "aaaaa";

            OrderDTO orderDTO = orderDAO.showOrderByID(orderId);

            assertNull(orderDTO, "Order must be null with invalid ID");
            System.out.println("testShowOrderByIDWithInvalidID passed!");
        } catch (NamingException e) {
            Assert.fail("Naming Exception: " + e.getMessage());
        } catch (SQLException e) {
            Assert.fail("SQL Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            Assert.fail("Class Not Found Exception: " + e.getMessage());
        }
    }

}
