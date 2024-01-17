/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import java.sql.Date;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 *
 * @author anhkhoa
 */
public class OrderDTONGTest {

    public OrderDTONGTest() {
    }
    
    
    @Test
    @Parameters({"id","total"})
    public void testGetterAndSetter(@Optional("OD123") String id, @Optional("50.0F")float total) {
        System.out.println("Running testGetterAndSetter");
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(id);
        orderDTO.setoDate(new java.sql.Date(System.currentTimeMillis()));
        orderDTO.setTotal(total);

        assertEquals(orderDTO.getId(), id);
        assertEquals(orderDTO.getTotal(), total);
        assertNotNull(orderDTO.getoDate());
        System.out.println("testGetterAndSetter passed!");

    }

    @DataProvider(name = "orderData")
    public Object[][] orderData() {
        return new Object[][]{
            {"OD001", Date.valueOf("2022-01-01"), 100.0F},
            {"OD002", Date.valueOf("2022-02-15"), 150.0F},
            {"OD003", Date.valueOf("2022-03-20"), 200.0F},};
    }

    @Test(dataProvider = "orderData")
    public void testOrderDTOContructor(String id, Date oDate, float total) {

        System.out.println("Running testOrderDTOContructor with data: " + id + ", " + oDate + ", " + total);

        OrderDTO orderDTO = new OrderDTO(id, oDate, total);

        assertEquals(orderDTO.getId(), id);
        assertEquals(orderDTO.getoDate(), oDate);
        assertEquals(orderDTO.getTotal(), total);

        System.out.println("testOrderDTOContructor passed!");

    }

}
