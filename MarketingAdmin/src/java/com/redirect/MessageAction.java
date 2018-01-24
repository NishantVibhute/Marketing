/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.MessageBean;
import com.beans.SentMessageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ServiceUtil;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author sagar.gurav
 */
public class MessageAction {

    ObjectMapper objectMapper = new ObjectMapper();
    private String valueToSubmit = "Welcome MSG";
    MessageBean messageContent;
    SentMessageBean sentMessageBean;
    List<SentMessageBean> sentList;
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;

    public String redirect() {
        try {
            System.out.println("start");
            System.out.println(valueToSubmit);
            String resp = ServiceUtil.getResponse(valueToSubmit, "/message/getSMSTemplateContent");

            messageContent = objectMapper.readValue(resp, MessageBean.class);
            System.out.println("end");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String editMessage() {
        try {
            String input = objectMapper.writeValueAsString(messageContent);
            String resp = ServiceUtil.getResponse(input, "/message/editTemplateContent");

            messageContent = objectMapper.readValue(resp, MessageBean.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String showSentSMS() {
        try {
            String resp = ServiceUtil.getResponseGet("/message/getSentList");

            sentList = objectMapper.readValue(resp, new TypeReference<List<SentMessageBean>>() {
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String newMessage() {

        return ActionSupport.SUCCESS;
    }

    public String sendMessage() {
        try {
            String input = objectMapper.writeValueAsString(sentMessageBean);
            String resp = ServiceUtil.getResponse(input, "/message/saveSMSSentStatus");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String getValueToSubmit() {
        return valueToSubmit;
    }

    public void setValueToSubmit(String valueToSubmit) {
        this.valueToSubmit = valueToSubmit;
    }

    public MessageBean getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(MessageBean messageContent) {
        this.messageContent = messageContent;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public SentMessageBean getSentMessageBean() {
        return sentMessageBean;
    }

    public void setSentMessageBean(SentMessageBean sentMessageBean) {
        this.sentMessageBean = sentMessageBean;
    }

    public List<SentMessageBean> getSentList() {
        return sentList;
    }

    public void setSentList(List<SentMessageBean> sentList) {
        this.sentList = sentList;
    }

}
