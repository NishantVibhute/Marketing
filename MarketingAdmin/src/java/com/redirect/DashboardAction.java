/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.ChatRoomBean;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Nishant
 */
public class DashboardAction extends ActionSupport {

    List<ChatRoomBean> chatList = new ArrayList();
    ChatRoomBean chatRoomBean = new ChatRoomBean();
    ObjectMapper objectMapper = new ObjectMapper();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    int countMessage = 0;
    private InputStream inputStream;

    public String redirect() {

        return ActionSupport.SUCCESS;
    }

    public String getUnreadMessage() {

        try {
            String resp = ServiceUtil.getResponseGet("/Chat/getunreadmessage");

            chatList = objectMapper.readValue(resp, new TypeReference<List<ChatRoomBean>>() {
            });

            inputStream = new ByteArrayInputStream(resp.getBytes(StandardCharsets.UTF_8));
            countMessage = chatList.size();
        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public List<ChatRoomBean> getChatList() {
        return chatList;
    }

    public void setChatList(List<ChatRoomBean> chatList) {
        this.chatList = chatList;
    }

    public ChatRoomBean getChatRoomBean() {
        return chatRoomBean;
    }

    public void setChatRoomBean(ChatRoomBean chatRoomBean) {
        this.chatRoomBean = chatRoomBean;
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

    public int getCountMessage() {
        return countMessage;
    }

    public void setCountMessage(int countMessage) {
        this.countMessage = countMessage;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
