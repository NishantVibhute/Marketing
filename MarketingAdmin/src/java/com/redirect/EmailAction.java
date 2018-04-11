/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.EmailBean;
import com.beans.MessageBean;
import com.beans.SentMessageBean;
import com.beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
 * @author nishant.vibhute
 */
public class EmailAction extends ActionSupport implements ModelDriven {

    ObjectMapper objectMapper = new ObjectMapper();
    List<String> emailIdList = new ArrayList<>();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    EmailBean emailBean = new EmailBean();
    List<MessageBean> messageContentList;
    private String valueToSubmit = "";
    MessageBean messageContent;
    private InputStream inputStream;
    List<SentMessageBean> sentList;

    public String redirectTemplates() {
        try {
            String resp = ServiceUtil.getResponseGet("/email/getEmailTemplates");
            messageContentList = objectMapper.readValue(resp, new TypeReference<List<MessageBean>>() {
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String getTemplateContent() {
        try {
            String resp = ServiceUtil.getResponse(valueToSubmit, "/email/getEmailTemplateContent");
            messageContent = objectMapper.readValue(resp, MessageBean.class);
            String res = objectMapper.writeValueAsString(messageContent);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        successMsg = StringUtils.EMPTY;
        errorMsg = StringUtils.EMPTY;
        return ActionSupport.SUCCESS;
    }

    public String editMessage() {
        try {
            String input = objectMapper.writeValueAsString(messageContent);
            String resp = ServiceUtil.getResponse(input, "/email/editTemplateContent");
            messageContent = objectMapper.readValue(resp, MessageBean.class);
            if (resp != null) {
                successMsg = "Message modified successfully";
            } else {
                errorMsg = "Message not modified";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public String redirect() {

        try {

            String resp = ServiceUtil.getResponseGet("/user/getuserdetailslist");

            List<UserBean> userList = objectMapper.readValue(resp, new TypeReference<List<UserBean>>() {
            });

            for (UserBean u : userList) {
                emailIdList.add(u.getEmailId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String send() {
        try {
            String input = objectMapper.writeValueAsString(emailBean);
            String resp = ServiceUtil.getResponse(input, "/email/sendEmail");

            if (resp.equalsIgnoreCase("Email sent successfully")) {

                successMsg = "Email sent successfully";
            } else {
                errorMsg = "Email sending failed";
            }

        } catch (IOException ex) {
            Logger.getLogger(EmailAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String showSentEmail() {
        try {
            String resp = ServiceUtil.getResponseGet("/email/getSentList");

            sentList = objectMapper.readValue(resp, new TypeReference<List<SentMessageBean>>() {
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ActionSupport.SUCCESS;
    }

    public List<String> getEmailIdList() {
        return emailIdList;
    }

    public void setEmailIdList(List<String> emailIdList) {
        this.emailIdList = emailIdList;
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

    @Override
    public Object getModel() {
        return emailBean;
    }

    public List<MessageBean> getMessageContentList() {
        return messageContentList;
    }

    public void setMessageContentList(List<MessageBean> messageContentList) {
        this.messageContentList = messageContentList;
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List<SentMessageBean> getSentList() {
        return sentList;
    }

    public void setSentList(List<SentMessageBean> sentList) {
        this.sentList = sentList;
    }

}
