package com.itstyle.database.domain.dto;

/**
 * @Author 10097454
 * @Date 2020/05/20
 * @Description TODO
 */
public class HomeOrderDTO {
    private String date;
    private int ordercnt;
    private double amount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrdercnt() {
        return ordercnt;
    }

    public void setOrdercnt(int ordercnt) {
        this.ordercnt = ordercnt;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
