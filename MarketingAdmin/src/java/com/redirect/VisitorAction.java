/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.UserPassword;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;
import java.io.IOException;
import java.io.InputStream;
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
public class VisitorAction extends ActionSupport implements ModelDriven {

    UserPassword userPassword = new UserPassword();
    List<UserPassword> visitorList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    private InputStream inputStream;
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;

    public String redirect() {
        try {
            String resp = ServiceUtil.getResponseGet("/user/getvisitorlist");
            visitorList = objectMapper.readValue(resp, new TypeReference<List<UserPassword>>() {
            });

        } catch (IOException ex) {
            Logger.getLogger(VisitorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;

    }

    public String updatevisitorstatus() {
        try {
            String input = objectMapper.writeValueAsString(this.getUserPassword());
            String resp = ServiceUtil.getResponse(input, "/user/updatevisitorstatus");

            if (resp.equalsIgnoreCase("success")) {
                successMsg = "User Updated Successfully";
            } else {
                errorMsg = "Failed to Update User";
            }

        } catch (IOException ex) {
            Logger.getLogger(VisitorAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;

    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

    public List<UserPassword> getVisitorList() {
        return visitorList;
    }

    public void setVisitorList(List<UserPassword> visitorList) {
        this.visitorList = visitorList;
    }

    public UserPassword getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(UserPassword userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public Object getModel() {
        return userPassword;
    }

}
