/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.UserBean;
import com.beans.UserPassword;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ServiceUtil;
import java.io.IOException;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sagar.gurav
 */
public class LoginAction extends ActionSupport implements SessionAware {

    private String userName;
    private String password;
    private Map<String, Object> session;
    ObjectMapper objectMapper = new ObjectMapper();

    public String home() {
        return SUCCESS;
    }

    // Log Out user
    public String logOut() {
        session.remove("loginId");
        session.remove("user");
        addActionMessage("You have been Successfully Logged Out");
        return SUCCESS;
    }

    // Login user
    public String login() {
        if (userName.isEmpty() && password.isEmpty()) {
            addActionError("Username & password can't be blanked");
            return LOGIN;
        } else if (userName.isEmpty()) {
            addActionError("Username can't be blanked");
            return LOGIN;
        } else if (password.isEmpty()) {
            addActionError("Password can't be blanked");
            return LOGIN;
        } else {
            try {
                UserPassword up = new UserPassword();
                up.setEmailId(userName);
                up.setPassword(password);
                String input = objectMapper.writeValueAsString(up);
                String resp = ServiceUtil.getResponse(input, "/user/validate");
                UserBean userBean = objectMapper.readValue(resp, UserBean.class);
                if (userBean.getEmailId() != null) {
                    session.put("loginId", userName);
                    session.put("user", userBean);
                } else {
                    return LOGIN;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return SUCCESS;
        }
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
