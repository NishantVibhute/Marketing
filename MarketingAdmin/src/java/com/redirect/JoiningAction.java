/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.PendingJoinRequest;
import com.beans.UserDatails;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author nishant.vibhute
 */
public class JoiningAction extends ActionSupport {

    List<PendingJoinRequest> pendingJoinRequestList = new ArrayList<>();
    List<UserDatails> pendingUserDetails = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    private InputStream inputStream;
    public String val;

    public String redirect() {

        try {
            String resp = ServiceUtil.getResponseGet("/scheme/getpendingrequest");

            pendingJoinRequestList = objectMapper.readValue(resp, new TypeReference<List<PendingJoinRequest>>() {
            });

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;

    }

    public String getPendingUserListByScheme() {

        try {
            String resp = ServiceUtil.getResponse(this.getVal(), "/scheme/getpendingrequestbyscheme");

            pendingUserDetails = objectMapper.readValue(resp, new TypeReference<List<UserDatails>>() {
            });

            String res = objectMapper.writeValueAsString(pendingUserDetails);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ActionSupport.SUCCESS;

    }

    public List<PendingJoinRequest> getPendingJoinRequestList() {
        return pendingJoinRequestList;
    }

    public void setPendingJoinRequestList(List<PendingJoinRequest> pendingJoinRequestList) {
        this.pendingJoinRequestList = pendingJoinRequestList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public List<UserDatails> getPendingUserDetails() {
        return pendingUserDetails;
    }

    public void setPendingUserDetails(List<UserDatails> pendingUserDetails) {
        this.pendingUserDetails = pendingUserDetails;
    }

}
