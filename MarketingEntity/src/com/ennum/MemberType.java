/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ennum;

/**
 *
 * @author Nishant
 */
public enum MemberType {

    PHYSICAL(1, "Physical"),
    VIRTUAL(2, "Virtual");

    int id;
    String value;

    private MemberType(int id, String value) {
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

    public static MemberType getById(int id) {
        for (MemberType e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

}
