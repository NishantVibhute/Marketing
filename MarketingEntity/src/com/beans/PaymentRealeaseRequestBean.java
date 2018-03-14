/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.List;

/**
 *
 * @author Nishant
 */
public class PaymentRealeaseRequestBean {

    int schemeId;
    int userId;
    String userName;
    double amount;
    List<String> joinDates;

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<String> getJoinDates() {
        return joinDates;
    }

    public void setJoinDates(List<String> joinDates) {
        this.joinDates = joinDates;
    }

}
