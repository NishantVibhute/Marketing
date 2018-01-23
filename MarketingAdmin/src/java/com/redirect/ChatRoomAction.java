/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.ChatRoomBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author nishant.vibhute
 */
public class ChatRoomAction extends ActionSupport implements ModelDriven {

    List<ChatRoomBean> chatList = new ArrayList();
    ChatRoomBean chatRoomBean = new ChatRoomBean();
    ObjectMapper objectMapper = new ObjectMapper();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;

    public String redirect() {

        try {
            String resp = ServiceUtil.getResponseGet("/Chat/getlist");

            chatList = objectMapper.readValue(resp, new TypeReference<List<ChatRoomBean>>() {
            });

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getChats() {
        try {
            String resp = ServiceUtil.getResponseGet("/Chat/getlist");

            chatList = objectMapper.readValue(resp, new TypeReference<List<ChatRoomBean>>() {
            });

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String sendMessage() {
        try {
            chatRoomBean.setUserId(1);
            String input = objectMapper.writeValueAsString(chatRoomBean);
            String resp = ServiceUtil.getResponse(input, "/Chat/sendreply");

            if (!resp.equalsIgnoreCase("0")) {
                successMsg = "Message Sent";
            } else {
                errorMsg = "Failed to Send Message";
            }

        } catch (IOException ex) {
            Logger.getLogger(ChatRoomAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    @Override
    public Object getModel() {
        return chatRoomBean;
    }

    public List<ChatRoomBean> getChatList() {
        return chatList;
    }

    public void setChatList(List<ChatRoomBean> chatList) {
        this.chatList = chatList;
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

    public ChatRoomBean getChatRoomBean() {
        return chatRoomBean;
    }

    public void setChatRoomBean(ChatRoomBean chatRoomBean) {
        this.chatRoomBean = chatRoomBean;
    }

}
