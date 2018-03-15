/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.JoiningDetailsBean;
import com.beans.UserBean;
import com.beans.UserJoinPaymentBean;
import com.beans.UserSchemeBalance;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
    List<UserSchemeBalance> userSchemeBalances = new ArrayList<>();
    List<UserJoinPaymentBean> jdbs = new ArrayList<>();

    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    UserBean userBean = new UserBean();
    public String val;
    public String userId, schemeId;
    private InputStream inputStream;

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

    public String redirectDetails() {

        try {

            String resp = ServiceUtil.getResponseGet("/user/getuserdetailslist");

            userList = objectMapper.readValue(resp, new TypeReference<List<UserBean>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String getUserDetails() {

        try {

            String resp = ServiceUtil.getResponse(this.val, "/user/getUserDetailsByUserId");

//            userBean = objectMapper.readValue(resp, UserBean.class);
            String res = objectMapper.writeValueAsString(resp);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String getSchemeJoiningDetails() {

        try {

            JoiningDetailsBean jdb = new JoiningDetailsBean();
            jdb.setUserId(Integer.parseInt(userId));
            jdb.setSchemeId(Integer.parseInt(schemeId));
            String req = objectMapper.writeValueAsString(jdb);

            String resp = ServiceUtil.getResponse(req, "/user/getuserjoinpayment");

            jdbs = objectMapper.readValue(resp, new TypeReference<List<UserJoinPaymentBean>>() {
            });
            String res = objectMapper.writeValueAsString(jdbs);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String getSchemeUserBalance() {

        try {

            String resp = ServiceUtil.getResponse(this.val, "/user/getschemeuserbalance");

            userSchemeBalances = objectMapper.readValue(resp, new TypeReference<List<UserSchemeBalance>>() {
            });
            String res = objectMapper.writeValueAsString(userSchemeBalances);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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
