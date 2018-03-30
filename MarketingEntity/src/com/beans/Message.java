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
public class Message {

    int num_parts;
    String sender;
    String content;

    public int getNum_parts() {
        return num_parts;
    }

    public void setNum_parts(int num_parts) {
        this.num_parts = num_parts;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
