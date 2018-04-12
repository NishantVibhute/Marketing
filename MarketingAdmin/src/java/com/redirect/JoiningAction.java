/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.CreateVirtualUser;
import com.beans.JoiningDetailsBean;
import com.beans.MessageBean;
import com.beans.PaymentBean;
import com.beans.PaymentResponse;
import com.beans.PendingJoinRequest;
import com.beans.SchemeJoinBean;
import com.beans.SchemeRows;
import com.beans.SentMessageBean;
import com.beans.TemplateBean;
import com.beans.UserBean;
import com.beans.UserDatails;
import com.beans.UserSchemeBalance;
import com.ennum.MemberType;
import com.ennum.PaymentMode;
import com.ennum.StatusEnum;
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

    public String denyUser() {

        try {
            String input = objectMapper.writeValueAsString(this.paymentBean);
            String resp = ServiceUtil.getResponse(input, "/scheme/denyUser");

            int a = Integer.parseInt(resp);

            if (a != 0) {
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
                sc.setTemplate("Business Exit Msg");
                String inputTemp = objectMapper.writeValueAsString(sc);
                String respTemp = ServiceUtil.getResponse(inputTemp, "/message/getSMSTemplateContent");
                MessageBean messageContent = objectMapper.readValue(respTemp, MessageBean.class);

                String respUser = ServiceUtil.getResponse("" + uJoin.getUserId(), "/user/getUserDetailsByUserId");
                UserBean userDetails = objectMapper.readValue(respUser, UserBean.class);
                SentMessageBean sentMessageBean = new SentMessageBean();
                sentMessageBean.setTempId(messageContent.getId());
                sentMessageBean.setFrom(1);
                sentMessageBean.setTo(userDetails.getMobileNo());
                String msg1 = messageContent.getBody().replace("<userId>", "P" + userDetails.getId());
                String msg2 = msg1.replace("<productName>", uBal.getSchemeName());
//                String msg3 = msg2.replace("<balance>", "" + uBal.getBalance());
//                String msg4 = msg2.replace("<paymentAmount>", "" + paymentBean.getAmount());
//                String msg5 = msg2.replace("<paymentDate>", "" + payDate);
                sentMessageBean.setMessage(msg2);
                sentMessageBean.setToName(userDetails.getFirstName() + " " + userDetails.getLastName());
                sentMessageBean.setSchemeId(uJoin.getSchemeId());
                String inputSMS = objectMapper.writeValueAsString(sentMessageBean);
                String respSMS = ServiceUtil.getResponse(inputSMS, "/message/sendSMS");
                SentMessageBean uSMS = objectMapper.readValue(respSMS, SentMessageBean.class);
                successMsg = successMsg + " Updated Successfully";
            } else {
                errorMsg = successMsg + " Failed To Update";
            }

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
            if (paymentBean.getPaymentModeId() == PaymentMode.REJOINING.getId()) {
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
                sc.setTemplate("Rejoining");

                String inputTemp = objectMapper.writeValueAsString(sc);
                String respTemp = ServiceUtil.getResponse(inputTemp, "/message/getSMSTemplateContent");
                MessageBean messageContent = objectMapper.readValue(respTemp, MessageBean.class);
                String respUser = ServiceUtil.getResponse("" + uJoin.getUserId(), "/user/getUserDetailsByUserId");
                UserBean userDetails = objectMapper.readValue(respUser, UserBean.class);
                SentMessageBean sentMessageBean = new SentMessageBean();
                sentMessageBean.setTempId(messageContent.getId());
                sentMessageBean.setFrom(1);
                sentMessageBean.setTo(userDetails.getMobileNo());
                String msg1 = messageContent.getBody().replace("<userId>", "P" + userDetails.getId());
                String msg2 = msg1.replace("<productName>", uBal.getSchemeName());
//                String msg3 = msg2.replace("<balance>", "" + uBal.getBalance());
//                String msg4 = msg2.replace("<paymentAmount>", "" + paymentBean.getAmount());
//                String msg5 = msg2.replace("<paymentDate>", "" + payDate);
                sentMessageBean.setMessage(msg2);
                sentMessageBean.setToName(userDetails.getFirstName() + " " + userDetails.getLastName());
                sentMessageBean.setSchemeId(uJoin.getSchemeId());
                String inputSMS = objectMapper.writeValueAsString(sentMessageBean);
                String respSMS = ServiceUtil.getResponse(inputSMS, "/message/sendSMS");
                SentMessageBean uSMS = objectMapper.readValue(respSMS, SentMessageBean.class);
            }

            if (paymentBean.getPaymentModeId() != PaymentMode.REJOINING.getId()) {
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
                sc.setTemplate("Joining");
                String inputTemp = objectMapper.writeValueAsString(sc);
                String respTemp = ServiceUtil.getResponse(inputTemp, "/message/getSMSTemplateContent");
                MessageBean messageContent = objectMapper.readValue(respTemp, MessageBean.class);

                String respUser = ServiceUtil.getResponse("" + uJoin.getUserId(), "/user/getUserDetailsByUserId");
                UserBean userDetails = objectMapper.readValue(respUser, UserBean.class);
                SentMessageBean sentMessageBean = new SentMessageBean();
                sentMessageBean.setTempId(messageContent.getId());
                sentMessageBean.setFrom(1);
                sentMessageBean.setTo(userDetails.getMobileNo());
                String msg1 = messageContent.getBody().replace("<userId>", "P" + userDetails.getId());
                String msg2 = msg1.replace("<productName>", uBal.getSchemeName());
//                String msg3 = msg2.replace("<balance>", "" + uBal.getBalance());
//                String msg4 = msg2.replace("<paymentAmount>", "" + paymentBean.getAmount());
//                String msg5 = msg2.replace("<paymentDate>", "" + payDate);
                sentMessageBean.setMessage(msg2);
                sentMessageBean.setToName(userDetails.getFirstName() + " " + userDetails.getLastName());
                sentMessageBean.setSchemeId(uJoin.getSchemeId());
                String inputSMS = objectMapper.writeValueAsString(sentMessageBean);
                String respSMS = ServiceUtil.getResponse(inputSMS, "/message/sendSMS");
                SentMessageBean uSMS = objectMapper.readValue(respSMS, SentMessageBean.class);
            }

            SchemeJoinBean sjb = new SchemeJoinBean();

            if (ps.getIsSuccess() == 1) {

                successMsg = "Payment Added Successfully";
                if (ps.getIsExit() == 1) {
                    if (ps.getMemberType() == MemberType.PHYSICAL.getId()) {

                        sjb.setMemberType(MemberType.PHYSICAL.getId());
                        sjb.setPaymentModeId(PaymentMode.REJOINING.getId());
                        sjb.setSchemeId(ps.getSchemeId());
                        sjb.setUserId(ps.getUserId());
                        sjb.setUserStatus(StatusEnum.PENDING.getId());
                        String input1 = objectMapper.writeValueAsString(sjb);
                        String resp1 = ServiceUtil.getResponse(input1, "/scheme/join");

                        if (Integer.parseInt(resp1) != 0) {
                            successMsg = successMsg + " And Join Request Created";
                        } else {
                            errorMsg = successMsg + " Failed Create Join Request";
                        }

                        UserSchemeBalance u = new UserSchemeBalance();
                        u.setUserId(ps.getUserId());
                        u.setSchemeId(ps.getSchemeId());
                        String inputBal = objectMapper.writeValueAsString(u);
                        String respBal = ServiceUtil.getResponse(inputBal, "/user/getschemeusertotalbalance");
                        UserSchemeBalance uBal = objectMapper.readValue(respBal, UserSchemeBalance.class);

                        TemplateBean sc = new TemplateBean();
                        sc.setSchemId(ps.getSchemeId());
                        sc.setTemplate("Business Pool Completed Msg");
                        String inputTemp = objectMapper.writeValueAsString(sc);
                        String respTemp = ServiceUtil.getResponse(inputTemp, "/message/getSMSTemplateContent");
                        MessageBean messageContent = objectMapper.readValue(respTemp, MessageBean.class);

                        String respUser = ServiceUtil.getResponse("" + ps.getUserId(), "/user/getUserDetailsByUserId");
                        UserBean userDetails = objectMapper.readValue(respUser, UserBean.class);
                        SentMessageBean sentMessageBean = new SentMessageBean();
                        sentMessageBean.setTempId(messageContent.getId());
                        sentMessageBean.setFrom(1);
                        sentMessageBean.setTo(userDetails.getMobileNo());
                        String msg1 = messageContent.getBody().replace("<userId>", "P" + userDetails.getId());
                        String msg2 = msg1.replace("<productName>", uBal.getSchemeName());
                        String msg3 = msg2.replace("<balance>", "" + uBal.getBalance());
                        sentMessageBean.setMessage(msg3);
                        sentMessageBean.setSchemeId(ps.getSchemeId());
                        sentMessageBean.setToName(userDetails.getFirstName() + " " + userDetails.getLastName());
                        String inputSMS = objectMapper.writeValueAsString(sentMessageBean);
                        String respSMS = ServiceUtil.getResponse(inputSMS, "/message/sendSMS");
                        SentMessageBean uSMS = objectMapper.readValue(respSMS, SentMessageBean.class);

                    } else {
                        sjb.setMemberType(MemberType.VIRTUAL.getId());
                        sjb.setPaymentModeId(PaymentMode.REJOINING.getId());
                        sjb.setSchemeId(ps.getSchemeId());
                        sjb.setUserId(ps.getUserId());
                        sjb.setUserStatus(StatusEnum.PENDING.getId());
                        String input1 = objectMapper.writeValueAsString(sjb);
                        String resp1 = ServiceUtil.getResponse(input1, "/scheme/join");

                        if (Integer.parseInt(resp1) != 0) {
                            successMsg = successMsg + " And Join Request Created";
                        } else {
                            errorMsg = successMsg + " Failed Create Join Request";
                        }
                    }
                }

            } else {
                errorMsg = "Failed to Add Payment";
            }

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ActionSupport.SUCCESS;
    }

    public String updateVirtualUserPayment() {
        try {

            String input = objectMapper.writeValueAsString(this.paymentBean);
            String resp = ServiceUtil.getResponse(input, "/scheme/savePaymentDetails");

            PaymentResponse ps = objectMapper.readValue(resp, PaymentResponse.class);

            if (ps.getIsSuccess() == 1) {
                successMsg = "Payment Added Successfully";
                if (ps.getIsExit() == 1) {
                    if (ps.getMemberType() == MemberType.PHYSICAL.getId()) {
                        SchemeJoinBean sjb = new SchemeJoinBean();
                        sjb.setMemberType(MemberType.PHYSICAL.getId());
                        sjb.setPaymentModeId(PaymentMode.REJOINING.getId());
                        sjb.setSchemeId(ps.getSchemeId());
                        sjb.setUserId(ps.getUserId());
                        sjb.setUserStatus(StatusEnum.PENDING.getId());
                        String input1 = objectMapper.writeValueAsString(sjb);
                        String resp1 = ServiceUtil.getResponse(input1, "/scheme/join");

                        if (Integer.parseInt(resp1) != 0) {
                            successMsg = successMsg + " And Join Request Created";
                        } else {
                            errorMsg = successMsg + " Failed Create Join Request";
                        }

                    } else {
                        SchemeJoinBean sjb = new SchemeJoinBean();
                        sjb.setMemberType(MemberType.VIRTUAL.getId());
                        sjb.setPaymentModeId(PaymentMode.COMPANY.getId());
                        sjb.setSchemeId(ps.getSchemeId());
                        sjb.setUserId(ps.getUserId());
                        sjb.setUserStatus(StatusEnum.PENDING.getId());
                        String input1 = objectMapper.writeValueAsString(sjb);
                        String resp1 = ServiceUtil.getResponse(input1, "/scheme/join");

                        if (Integer.parseInt(resp1) != 0) {
                            successMsg = successMsg + " And Join Request Created";
                        } else {
                            errorMsg = successMsg + " Failed Create Join Request";
                        }
                    }
                }

                for (int i = 0; i < paymentBean.getVitualIdToBecreated(); i++) {

                    CreateVirtualUser createVirtualUser = new CreateVirtualUser();
                    createVirtualUser.setUserId(paymentBean.getJoiningId());
                    createVirtualUser.setSchemeId(paymentBean.getSchemeId());
                    String input5 = objectMapper.writeValueAsString(createVirtualUser);

                    String resp1 = ServiceUtil.getResponse(input5, "/user/createvirtualuser");

                    if (!resp1.equalsIgnoreCase("0")) {
                        SchemeJoinBean sjb = new SchemeJoinBean();
                        sjb.setMemberType(MemberType.VIRTUAL.getId());
                        sjb.setPaymentModeId(PaymentMode.COMPANY.getId());
                        sjb.setSchemeId(paymentBean.getSchemeId());
                        sjb.setUserId(Integer.parseInt(resp1));
                        sjb.setUserStatus(StatusEnum.CONFIRMED.getId());
                        String input1 = objectMapper.writeValueAsString(sjb);
                        String resp2 = ServiceUtil.getResponse(input1, "/scheme/join");

                        if (Integer.parseInt(resp2) != 0) {

                            PaymentBean pb = new PaymentBean();
                            pb.setJoiningId(Integer.parseInt(resp2));
                            pb.setPaymentModeId(PaymentMode.COMPANY.getId());
                            pb.setAmount(this.paymentBean.getAmount());
                            String input3 = objectMapper.writeValueAsString(pb);
                            String resp3 = ServiceUtil.getResponse(input3, "/scheme/savePaymentDetails");

                            PaymentResponse ps3 = objectMapper.readValue(resp3, PaymentResponse.class);

                            if (ps3.getIsSuccess() == 1) {
                                successMsg = "Payment Added Successfully";
                                if (ps3.getIsExit() == 1) {
                                    if (ps3.getMemberType() == MemberType.PHYSICAL.getId()) {
                                        SchemeJoinBean sjb3 = new SchemeJoinBean();
                                        sjb3.setMemberType(MemberType.PHYSICAL.getId());
                                        sjb3.setPaymentModeId(PaymentMode.REJOINING.getId());
                                        sjb3.setSchemeId(ps3.getSchemeId());
                                        sjb3.setUserId(ps3.getUserId());
                                        sjb3.setUserStatus(StatusEnum.PENDING.getId());
                                        String input4 = objectMapper.writeValueAsString(sjb3);
                                        String resp4 = ServiceUtil.getResponse(input4, "/scheme/join");

                                        if (Integer.parseInt(resp4) != 0) {
                                            successMsg = successMsg + " And Join Request Created";
                                        } else {
                                            errorMsg = successMsg + " Failed Create Join Request";
                                        }

                                    } else {
                                        SchemeJoinBean sjb4 = new SchemeJoinBean();
                                        sjb4.setMemberType(MemberType.VIRTUAL.getId());
                                        sjb4.setPaymentModeId(PaymentMode.COMPANY.getId());
                                        sjb4.setSchemeId(ps3.getSchemeId());
                                        sjb4.setUserId(ps3.getUserId());
                                        sjb4.setUserStatus(StatusEnum.PENDING.getId());
                                        String input4 = objectMapper.writeValueAsString(sjb4);
                                        String resp4 = ServiceUtil.getResponse(input4, "/scheme/join");

                                        if (Integer.parseInt(resp4) != 0) {
                                            successMsg = successMsg + " And Join Request Created";
                                        } else {
                                            errorMsg = successMsg + " Failed Create Join Request";
                                        }
                                    }

                                }

                            }

                        }

                    } else {
                        errorMsg = "Failed to Add Payment";
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, e);
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
