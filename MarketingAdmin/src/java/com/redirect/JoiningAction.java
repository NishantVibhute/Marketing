/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.PaymentBean;
import com.beans.PaymentResponse;
import com.beans.PendingJoinRequest;
import com.beans.SchemeRows;
import com.beans.UserDatails;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
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
public class JoiningAction extends ActionSupport implements ModelDriven {

    List<PendingJoinRequest> pendingJoinRequestList = new ArrayList<>();
    List<UserDatails> pendingUserDetails = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    List<SchemeRows> schemeRowsList = new ArrayList<>();
    private InputStream inputStream;
    public String val;
    public String valScheme;
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    PaymentBean paymentBean = new PaymentBean();

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

    public String getSchemePool() {

        try {
            String resp = ServiceUtil.getResponse(this.getValScheme(), "/scheme/getSchemePool");

            schemeRowsList = objectMapper.readValue(resp, new TypeReference<List<SchemeRows>>() {
            });

            String res = objectMapper.writeValueAsString(schemeRowsList);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ActionSupport.SUCCESS;

    }

    public String savePayment() {
        try {
            String input = objectMapper.writeValueAsString(this.paymentBean);
            String resp = ServiceUtil.getResponse(input, "/scheme/savePaymentDetails");

            PaymentResponse ps = objectMapper.readValue(resp, PaymentResponse.class);

            if (ps.getIsSuccess() == 1) {
                successMsg = "Payment Added Successfully";
            } else {
                errorMsg = "Failed to Add Payment";
            }

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

    public String getValScheme() {
        return valScheme;
    }

    public void setValScheme(String valScheme) {
        this.valScheme = valScheme;
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
        return paymentBean;
    }

}
