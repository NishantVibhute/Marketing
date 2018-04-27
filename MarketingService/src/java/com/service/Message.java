/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.JoiningDetailsBean;
import com.beans.MessageBean;
import com.beans.Messages;
import com.beans.SMSResponse;
import com.beans.SentMessageBean;
import com.beans.TemplateBean;
import com.beans.UserBean;
import com.beans.Warnings;
import com.dao.MessageDao;
import com.dao.SettingsDao;
import com.dao.UserDao;
import com.util.SMSUtil;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author
 */
@Path("message")
public class Message {

    ObjectMapper objectMapper = new ObjectMapper();
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger infoLog = Logger.getLogger("infoLogger");
    SMSUtil smsUtil = new SMSUtil();
    SettingsDao settingsDao = new SettingsDao();

    @GET
    @Path("/getSMSTemplates")
    @Produces("text/plain")

    public String getSMSTemplates() {
        String jsonInString = "";
        try {

            MessageDao messageDao = new MessageDao();
            List<MessageBean> messageContent = messageDao.getSMSTemplates();
            jsonInString = objectMapper.writeValueAsString(messageContent);

        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/getSMSTemplateContent")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getSMSContentFromSubject(String data) {
        String jsonInString = "";
        try {
            TemplateBean tc = objectMapper.readValue(data, TemplateBean.class);
            MessageDao messageDao = new MessageDao();
            MessageBean messageContent = messageDao.getSMSContentFromSubject(tc.getTemplate(), tc.getSchemId());
            jsonInString = objectMapper.writeValueAsString(messageContent);

        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/editTemplateContent")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String editTemplateContent(String data) {
        String jsonInString = "";
        try {
            MessageDao messageDao = new MessageDao();
            MessageBean messageContent = objectMapper.readValue(data, MessageBean.class);
            messageContent = messageDao.editSMSContentFromSubject(messageContent);
            jsonInString = objectMapper.writeValueAsString(messageContent);

        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/saveSMSSentStatus")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String saveSMSSentStatus(String data) {
        String jsonInString = "";
        try {
            MessageDao messageDao = new MessageDao();
            SentMessageBean sentMessageBean = objectMapper.readValue(data, SentMessageBean.class);
            sentMessageBean = messageDao.SendSms(sentMessageBean);
            jsonInString = objectMapper.writeValueAsString(sentMessageBean);

        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/sendSMS")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String sendSMS(String data) {
        String jsonInString = "";
        try {
            MessageDao messageDao = new MessageDao();

            SentMessageBean sentMessageBean = objectMapper.readValue(data, SentMessageBean.class);

            int isSend = settingsDao.getSettingsValue(1);
            if (isSend == 1) {
                String resp = smsUtil.sendSms(sentMessageBean.getMessage(), sentMessageBean.getTo());
                SMSResponse sMSResponse = objectMapper.readValue(resp, SMSResponse.class);
                if (sMSResponse.getStatus().equalsIgnoreCase("failure")) {
                    sentMessageBean.setStatus("FAILURE");
                    for (Warnings warnings : sMSResponse.getWarnings()) {
                        if (warnings.getNumbers().contains(sentMessageBean.getTo())) {

                            sentMessageBean.setStatus("DND");
                        }
                    }
                } else {
                    for (Messages messages : sMSResponse.getMessages()) {
//                        if (messages.getRecipient().contains(sentMessageBean.getTo())) {
                        sentMessageBean.setTxtId(messages.getId());
                        sentMessageBean.setStatus("SUCCESS");
//                        }
                    }
                }
            } else {
                sentMessageBean.setStatus("Not Activated");
            }
            sentMessageBean = messageDao.SendSms(sentMessageBean);
            jsonInString = objectMapper.writeValueAsString(sentMessageBean);
        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/sendBulkSMS")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String sendBulkSMS(String data) {
        String jsonInString = "";
        try {
            UserDao ud = new UserDao();
            MessageDao messageDao = new MessageDao();
            SentMessageBean sentMessageBean = objectMapper.readValue(data, SentMessageBean.class);
            int i = 0;
            for (String to : sentMessageBean.getBulkTo()) {
                SentMessageBean s1 = new SentMessageBean();
                s1.setTo(to);
                s1.setToName(sentMessageBean.getBulkNames().get(i));

                String msg = sentMessageBean.getMessage();
                UserBean ub = ud.getUserDetailsByUserId((int) sentMessageBean.getFrom());
                String msgN = msg.replace("<userName>", ub.getFirstName() + " " + ub.getLastName());

                s1.setMessage(msgN);
                s1.setFrom(sentMessageBean.getFrom());
                s1.setTempId(sentMessageBean.getTempId());
                s1.setSchemeId(sentMessageBean.getSchemeId());
//                s1.setToName(sentMessageBean.getToName());

                int isSend = settingsDao.getSettingsValue(1);
                if (isSend == 1) {
                    String resp = smsUtil.sendSms(s1.getMessage(), s1.getTo());
                    SMSResponse sMSResponse = objectMapper.readValue(resp, SMSResponse.class);

                    for (Warnings warnings : sMSResponse.getWarnings()) {
                        if (warnings.getNumbers().contains(s1.getTo())) {
                            s1.setStatus("DND");
                        }
                    }

                    for (Messages messages : sMSResponse.getMessages()) {
//                        if (messages.getRecipient().contains(s1.getTo())) {
                        s1.setTxtId(messages.getId());
                        s1.setStatus("SUCCESS");
//                        }
                    }
                } else {
                    s1.setStatus("Not Activated");
                }

                sentMessageBean = messageDao.SendSms(s1);
            }

            jsonInString = objectMapper.writeValueAsString(sentMessageBean);

        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @GET
    @Path("/getSentList")
    @Produces("text/plain")

    public String getSentList() {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            MessageDao messageDao = new MessageDao();
            List<SentMessageBean> sentMessageList = messageDao.getSentList();
            jsonInString = objectMapper.writeValueAsString(sentMessageList);
        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;
    }

    @GET
    @Path("/getDeliveryStatus")
    @Produces("text/plain")
    public String receiveResponse(@HeaderParam("number") String number,
            @HeaderParam("status") String status,
            @HeaderParam("customID") String customID,
            @HeaderParam("datetime") String datetime) {
        String jsonInString = "";
        try {

            MessageDao messageDao = new MessageDao();
            List<SentMessageBean> sentMessageList = messageDao.getSentList();
            jsonInString = objectMapper.writeValueAsString(sentMessageList);
        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getUserSentListWithCount")
    @Produces("text/plain")
    public String getUserSentListWithCount(String data) {
        //TODO return proper representation object
        String jsonInString = "";

        try {
            JoiningDetailsBean smb = objectMapper.readValue(data, JoiningDetailsBean.class
            );

            MessageDao messageDao = new MessageDao();
            List<SentMessageBean> sentMessageList = messageDao.getSentListWithCount(smb);
            jsonInString = objectMapper.writeValueAsString(sentMessageList);
        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getUserSentSmsDetails")
    @Produces("text/plain")
    public String getUserSentSmsDetails(String data) {
        //TODO return proper representation object
        String jsonInString = "";

        try {
            SentMessageBean smb = objectMapper.readValue(data, SentMessageBean.class
            );

            MessageDao messageDao = new MessageDao();
            List<SentMessageBean> sentMessageList = messageDao.getSentDetails(smb);
            jsonInString = objectMapper.writeValueAsString(sentMessageList);
        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;
    }
}
