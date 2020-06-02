package com.tre.bill.domain.dto;

/**
 * @Author 10097454
 * @Date 2020/05/22
 * @Description TODO
 */
public class BillInfo {
    private String zgznDate;
    private int zgznOrdercnt;
    private double zgznAmount;
    private int payOrdercnt;
    private double payAmount;
    private int suixingpayOrdercnt;
    private double suixingpayAmount;

    public String getZgznDate() {
        return zgznDate;
    }

    public void setZgznDate(String zgznDate) {
        this.zgznDate = zgznDate;
    }

    public int getZgznOrdercnt() {
        return zgznOrdercnt;
    }

    public void setZgznOrdercnt(int zgznOrdercnt) {
        this.zgznOrdercnt = zgznOrdercnt;
    }

    public double getZgznAmount() {
        return zgznAmount;
    }

    public void setZgznAmount(double zgznAmount) {
        this.zgznAmount = zgznAmount;
    }

    public int getPayOrdercnt() {
        return payOrdercnt;
    }

    public void setPayOrdercnt(int payOrdercnt) {
        this.payOrdercnt = payOrdercnt;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public int getSuixingpayOrdercnt() {
        return suixingpayOrdercnt;
    }

    public void setSuixingpayOrdercnt(int suixingpayOrdercnt) {
        this.suixingpayOrdercnt = suixingpayOrdercnt;
    }

    public double getSuixingpayAmount() {
        return suixingpayAmount;
    }

    public void setSuixingpayAmount(double suixingpayAmount) {
        this.suixingpayAmount = suixingpayAmount;
    }
}
