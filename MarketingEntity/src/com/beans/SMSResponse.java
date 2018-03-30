/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class SMSResponse {

    double balance;
    double batch_id;
    int cost;
    int num_messages;
    String custom;
    String receipt_url;
    Message message;
    String status;
    List<String> inDND = new ArrayList<>();
    List<Messages> messages = new ArrayList<>();
    List<Warnings> warnings = new ArrayList<>();
    List<Errors> errors = new ArrayList<>();

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    public List<Warnings> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Warnings> warnings) {
        this.warnings = warnings;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(double batch_id) {
        this.batch_id = batch_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNum_messages() {
        return num_messages;
    }

    public void setNum_messages(int num_messages) {
        this.num_messages = num_messages;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getReceipt_url() {
        return receipt_url;
    }

    public void setReceipt_url(String receipt_url) {
        this.receipt_url = receipt_url;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getInDND() {
        return inDND;
    }

    public void setInDND(List<String> inDND) {
        this.inDND = inDND;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

}
