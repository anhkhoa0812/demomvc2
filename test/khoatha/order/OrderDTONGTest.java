/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import static org.testng.Assert.*;
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
 
    
}
