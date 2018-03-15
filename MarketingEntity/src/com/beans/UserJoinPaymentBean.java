/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author nishant.vibhute
 */
public class UserJoinPaymentBean {

    int paymenttype;
    String chequeno;
    double amount;
    String cheque_date;
    String bank_name;
    String utrno;
    int paymentstatus;
    String paymentdate;
    int payment_modeid;
    int userstatus;
    String requestdate;
    String joindate;
    int isExit;
    int isPaymentRealease;
    int paymentid;

    public int getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(int paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getChequeno() {
        return chequeno;
    }

    public void setChequeno(String chequeno) {
        this.chequeno = chequeno;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCheque_date() {
        return cheque_date;
    }

    public void setCheque_date(String cheque_date) {
        this.cheque_date = cheque_date;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getUtrno() {
        return utrno;
    }

    public void setUtrno(String utrno) {
        this.utrno = utrno;
    }

    public int getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(int paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public int getPayment_modeid() {
        return payment_modeid;
    }

    public void setPayment_modeid(int payment_modeid) {
        this.payment_modeid = payment_modeid;
    }

    public int getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(int userstatus) {
        this.userstatus = userstatus;
    }

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public int getIsExit() {
        return isExit;
    }

    public void setIsExit(int isExit) {
        this.isExit = isExit;
    }

    public int getIsPaymentRealease() {
        return isPaymentRealease;
    }

    public void setIsPaymentRealease(int isPaymentRealease) {
        this.isPaymentRealease = isPaymentRealease;
    }

    public int getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(int paymentid) {
        this.paymentid = paymentid;
    }

}
