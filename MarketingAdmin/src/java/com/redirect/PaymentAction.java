/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.PaymentBean;
import com.beans.PaymentRealeaseRequestBean;
import com.beans.PendingJoinRequest;
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
 * @author Nishant
 */
public class PaymentAction extends ActionSupport implements ModelDriven {

    List<PendingJoinRequest> pendingJoinRequestList = new ArrayList<>();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    ObjectMapper objectMapper = new ObjectMapper();
    String val;
    private InputStream inputStream;

    PaymentBean paymentBean = new PaymentBean();

    public String redirect() {

        try {
            String resp = ServiceUtil.getResponseGet("/payment/getPaymentRealeaseSchemewiseCount");

            pendingJoinRequestList = objectMapper.readValue(resp, new TypeReference<List<PendingJoinRequest>>() {
            });

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;

    }

    public String getPaymentRealeaseRequest() {

        try {
            String resp = ServiceUtil.getResponse(this.getVal(), "/payment/getPaymentRealeaseRequest");

            List<PaymentRealeaseRequestBean> paymentRealeaseRequestBean = objectMapper.readValue(resp, new TypeReference<List<PaymentRealeaseRequestBean>>() {
            });

            String res = objectMapper.writeValueAsString(paymentRealeaseRequestBean);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ActionSupport.SUCCESS;

    }

    public String saveCustomerPayment() {

        try {
            String input = objectMapper.writeValueAsString(this.paymentBean);
            String resp = ServiceUtil.getResponse(input, "/payment/saveCustomerPaymentDetails");

            if (Integer.parseInt(resp) != 0) {
                successMsg = " Payment Save Successfully";
            } else {
                errorMsg = " Failed to Save Payment";
            }

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ActionSupport.SUCCESS;

    }

    public PaymentBean getPaymentBean() {
        return paymentBean;
    }

    public void setPaymentBean(PaymentBean paymentBean) {
        this.paymentBean = paymentBean;
    }

    public List<PendingJoinRequest> getPendingJoinRequestList() {
        return pendingJoinRequestList;
    }

    public void setPendingJoinRequestList(List<PendingJoinRequest> pendingJoinRequestList) {
        this.pendingJoinRequestList = pendingJoinRequestList;
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

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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
        return paymentBean;
    }

}
