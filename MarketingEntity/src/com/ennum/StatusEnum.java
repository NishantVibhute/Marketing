package com.ennum;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public enum StatusEnum {

    PENDING(1, "Pending"),
    CONFIRMED(2, "Confirmed"),
    REJECTED(3, "Rejected");

    int id;
    String value;

    private StatusEnum(int id, String value) {
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

    public static StatusEnum getById(int id) {
        for (StatusEnum e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }
}
