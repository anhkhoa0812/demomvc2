/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.order;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author tahoa
 */
public class OrderDTO implements Serializable {
    private String id;
    private java.sql.Date oDate;
    private float total;

    public OrderDTO() {
    }

    public OrderDTO(String id, java.sql.Date oDate, float total) {
        this.id = id;
        this.oDate = oDate;
        this.total = total;
    }

    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the oDate
     */
    public Date getoDate() {
        return oDate;
    }

    /**
     * @param oDate the oDate to set
     */
    public void setoDate(java.sql.Date oDate) {
        this.oDate = oDate;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
