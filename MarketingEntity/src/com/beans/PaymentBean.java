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
public class PaymentBean {

    int id;
    int joiningId;
    int paymentModeId;
    double amount;
    String chequeNo;
    String chequeDate;
    String bankName;
    String UTRNo;
    int vitualIdToBecreated;
    int schemeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(int paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(String chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUTRNo() {
        return UTRNo;
    }

    public void setUTRNo(String UTRNo) {
        this.UTRNo = UTRNo;
    }

    public int getJoiningId() {
        return joiningId;
    }

    public void setJoiningId(int joiningId) {
        this.joiningId = joiningId;
    }

    public int getVitualIdToBecreated() {
        return vitualIdToBecreated;
    }

    public void setVitualIdToBecreated(int vitualIdToBecreated) {
        this.vitualIdToBecreated = vitualIdToBecreated;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }

}
