/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author nishant.vibhute
 */
public class UserSchemeBalance {

    int userSchemeBalId;
    int userId;
    int schemeId;
    String schemeName;
    double balance;

    public int getUserSchemeBalId() {
        return userSchemeBalId;
    }

    public void setUserSchemeBalId(int userSchemeBalId) {
        this.userSchemeBalId = userSchemeBalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
