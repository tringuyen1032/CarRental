/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trinm.tblcart;

import java.io.Serializable;

/**
 *
 * @author Tri Nguyen
 */
public class TblCartDTO implements Serializable{
    private int orderID;
    private String carID;
    private int amount;
    private float price;
    private String datePick;
    private String dateBack;
    private String dateRent;
    private String status;

    public TblCartDTO(int orderID, String carID, int amount, float price, String datePick, String dateBack, String dateRent, String status) {
        this.orderID = orderID;
        this.carID = carID;
        this.amount = amount;
        this.price = price;
        this.datePick = datePick;
        this.dateBack = dateBack;
        this.dateRent = dateRent;
        this.status = status;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the carID
     */
    public String getCarID() {
        return carID;
    }

    /**
     * @param carID the carID to set
     */
    public void setCarID(String carID) {
        this.carID = carID;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the datePick
     */
    public String getDatePick() {
        return datePick;
    }

    /**
     * @param datePick the datePick to set
     */
    public void setDatePick(String datePick) {
        this.datePick = datePick;
    }

    /**
     * @return the dateBack
     */
    public String getDateBack() {
        return dateBack;
    }

    /**
     * @param dateBack the dateBack to set
     */
    public void setDateBack(String dateBack) {
        this.dateBack = dateBack;
    }

    /**
     * @return the dateRent
     */
    public String getDateRent() {
        return dateRent;
    }

    /**
     * @param dateRent the dateRent to set
     */
    public void setDateRent(String dateRent) {
        this.dateRent = dateRent;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
