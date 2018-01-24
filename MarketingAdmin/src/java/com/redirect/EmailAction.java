/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.EmailBean;
import com.beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.EmailUtil;
import com.util.ServiceUtil;
import java.util.ArrayList;
import java.util.List;
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
    EmailUtil emailUtil = new EmailUtil();
    EmailBean emailBean = new EmailBean();

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
        String resp = emailUtil.send(emailBean.getTo(), emailBean.getSubject(), emailBean.getBody());

        if (resp.equalsIgnoreCase("success")) {
            successMsg = "Email sent successfully";
        } else {
            errorMsg = "Email sending failed";
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

}
