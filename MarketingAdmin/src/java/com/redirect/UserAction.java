/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
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
public class UserAction extends ActionSupport implements ModelDriven {

    ObjectMapper objectMapper = new ObjectMapper();
    List<UserBean> userList = new ArrayList<>();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    UserBean userBean = new UserBean();

    public String redirect() {

        try {

            String resp = ServiceUtil.getResponseGet("/user/getuserdetailslist");

            userList = objectMapper.readValue(resp, new TypeReference<List<UserBean>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    @Override
    public Object getModel() {
        return userBean;
    }

    public List<UserBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserBean> userList) {
        this.userList = userList;
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

}
