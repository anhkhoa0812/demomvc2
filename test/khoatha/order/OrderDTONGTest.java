/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import java.sql.Date;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
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
//    @Test
//    public void testContructor() {
//        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
//        OrderDTO orderDTO = new OrderDTO("OD123", currentDate, 50.0F);
//        
//        assertEquals(orderDTO.getId(), "OD123");
//        assertEquals(orderDTO.getoDate(), currentDate);
//        assertEquals(orderDTO.getTotal(), 50.0F);
//    }
 
    
    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        return new Object[][]{
            {"OD001", Date.valueOf("2022-01-01"), 100.0F},
            {"OD002", Date.valueOf("2022-02-15"), 150.0F},
            {"OD003", Date.valueOf("2022-03-20"), 200.0F},
            
        };
    }

    @Test(dataProvider = "orderData")
    public void testOrderDTOContructor(String id, Date oDate, float total) {
        OrderDTO orderDTO = new OrderDTO(id, oDate, total);

        assertEquals(orderDTO.getId(), id);
        assertEquals(orderDTO.getoDate(), oDate);
        assertEquals(orderDTO.getTotal(), total);
        
    }

}
