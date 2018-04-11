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
public enum Operation {

    ADD(1, "Add"),
    DEDUCT(2, "Deduct");

    int id;
    String value;

    private Operation(int id, String value) {
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

    public static Operation getById(int id) {
        for (Operation e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }
}
