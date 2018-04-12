/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.JoiningDetailsBean;
import com.beans.MessageBean;
import com.beans.PaymentBean;
import com.beans.PaymentRealeaseRequestBean;
import com.beans.PendingJoinRequest;
import com.beans.SentMessageBean;
import com.beans.TemplateBean;
import com.beans.UserBean;
import com.beans.UserSchemeBalance;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String payDate = dateFormat.format(date);

        try {
            String input = objectMapper.writeValueAsString(this.paymentBean);
            String resp = ServiceUtil.getResponse(input, "/payment/saveCustomerPaymentDetails");

            if (Integer.parseInt(resp) != 0) {

                String respJoin = ServiceUtil.getResponse("" + this.paymentBean.getJoiningId(), "/scheme/getUserIdSchemeIdByJoinId");
                JoiningDetailsBean uJoin = objectMapper.readValue(respJoin, JoiningDetailsBean.class);

                UserSchemeBalance u = new UserSchemeBalance();
                u.setUserId((int) uJoin.getUserId());
                u.setSchemeId(uJoin.getSchemeId());
                String inputBal = objectMapper.writeValueAsString(u);
                String respBal = ServiceUtil.getResponse(inputBal, "/user/getschemeusertotalbalance");
                UserSchemeBalance uBal = objectMapper.readValue(respBal, UserSchemeBalance.class);

                TemplateBean sc = new TemplateBean();
                sc.setSchemId(uJoin.getSchemeId());
                sc.setTemplate("Payment Release Msg");
                String inputTemp = objectMapper.writeValueAsString(sc);
                String respTemp = ServiceUtil.getResponse(inputTemp, "/message/getSMSTemplateContent");
                MessageBean messageContent = objectMapper.readValue(respTemp, MessageBean.class);

                String respUser = ServiceUtil.getResponse("" + uJoin.getUserId(), "/user/getUserDetailsByUserId");
                UserBean userDetails = objectMapper.readValue(respUser, UserBean.class);
                SentMessageBean sentMessageBean = new SentMessageBean();
                sentMessageBean.setTempId(messageContent.getId());
                sentMessageBean.setFrom(1);
                sentMessageBean.setTo(userDetails.getMobileNo());
                sentMessageBean.setToName(userDetails.getFirstName() + " " + userDetails.getLastName());
                String msg1 = messageContent.getBody().replace("<userId>", "P" + userDetails.getId());
                String msg2 = msg1.replace("<productName>", uBal.getSchemeName());
                String msg3 = msg2.replace("<balance>", "" + uBal.getBalance());
                String msg4 = msg3.replace("<paymentAmount>", "" + paymentBean.getAmount());
                String msg5 = msg4.replace("<paymentDate>", "" + payDate);
                sentMessageBean.setMessage(msg5);
                sentMessageBean.setSchemeId(uJoin.getSchemeId());
                String inputSMS = objectMapper.writeValueAsString(sentMessageBean);
                String respSMS = ServiceUtil.getResponse(inputSMS, "/message/sendSMS");
                SentMessageBean uSMS = objectMapper.readValue(respSMS, SentMessageBean.class);

                successMsg = " Payment Save Successfully";
            } else {
                errorMsg = " Failed to Save Payment";
            }

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ActionSupport.SUCCESS;

    }

    public String saveCustomerBonusPenalty() {

        try {
            String input = objectMapper.writeValueAsString(this.paymentBean);
            String resp = ServiceUtil.getResponse(input, "/payment/saveCustomerPaymentBonusPenalty");

            if (Integer.parseInt(resp) != 0) {
                successMsg = " Saved Successfully";
            } else {
                errorMsg = " Failed to Save";
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
