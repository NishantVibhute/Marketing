/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ennum;

/**
 *
 * @author nishant.vibhute
 */
public enum PaymentMode {

    CASH(1, "Cash"),
    CHEQUE(2, "Cheque"),
    NETBANKING(3, "Netbanking"),
    COMPANY(4, "Company"),
    REJOINING(5, "Rejoining"),
    DENIED(5, "denied");

    int id;
    String value;

    private PaymentMode(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static PaymentMode getById(int id) {
        for (PaymentMode e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public static PaymentMode getByName(String name) {
        for (PaymentMode e : values()) {
            if (e.value.equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

}
