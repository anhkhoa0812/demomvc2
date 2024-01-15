/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import java.sql.Date;
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
public class OrderDTONGTest {
    
    public OrderDTONGTest() {
    }
    
        @Test
    public void testGetterAndSetter() {
        OrderDTO orderDTO = new OrderDTO();
        
        orderDTO.setId("OD123");
        orderDTO.setoDate(new java.sql.Date(System.currentTimeMillis()));
        orderDTO.setTotal(50.0F);
        
        assertEquals(orderDTO.getId(), "OD123");
        assertEquals(orderDTO.getTotal(), 50.0F);
        assertNotNull(orderDTO.getoDate());
    }
    @Test
    public void testContructor() {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        OrderDTO orderDTO = new OrderDTO("OD123", currentDate, 50.0F);
        
        assertEquals(orderDTO.getId(), "OD123");
        assertEquals(orderDTO.getoDate(), currentDate);
        assertEquals(orderDTO.getTotal(), 50.0F);
    }
//
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
//    public void testGetId() {
//        System.out.println("getId");
//        OrderDTO instance = new OrderDTO();
//        String expResult = "";
//        String result = instance.getId();
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testSetId() {
//        System.out.println("setId");
//        String id = "";
//        OrderDTO instance = new OrderDTO();
//        instance.setId(id);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetoDate() {
//        System.out.println("getoDate");
//        OrderDTO instance = new OrderDTO();
//        Date expResult = null;
//        Date result = instance.getoDate();
//        assertEquals(result, expResult);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testSetoDate() {
//        System.out.println("setoDate");
//        Date oDate = null;
//        OrderDTO instance = new OrderDTO();
//        instance.setoDate(oDate);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testGetTotal() {
//        System.out.println("getTotal");
//        OrderDTO instance = new OrderDTO();
//        float expResult = 0.0F;
//        float result = instance.getTotal();
//        assertEquals(result, expResult, 0.0F);
//        
//    }
//    
//    @Test(expectedExceptions = Exception.class)
//    public void testSetTotalWithNegative() {
//        System.out.println("setTotal");
//        float total = -10.0F;
//        OrderDTO instance = new OrderDTO();
//        instance.setTotal(total);
//
//    }
//    
//    @Test
//    public void testSetTotalWithPositive() {
//        System.out.println("setTotal");
//        float total = 10.0F;
//        OrderDTO instance = new OrderDTO();
//        instance.setTotal(total);
//        float actual = instance.getTotal();
//        assertEquals(actual, total);
////        fail("The test case is a prototype.");
//    }
    
}
