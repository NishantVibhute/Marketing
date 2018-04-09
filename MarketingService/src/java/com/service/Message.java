/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.MessageBean;
import com.beans.Messages;
import com.beans.SMSResponse;
import com.beans.SentMessageBean;
import com.beans.Warnings;
import com.dao.MessageDao;
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
            MessageDao messageDao = new MessageDao();
            MessageBean messageContent = messageDao.getSMSContentFromSubject(data);
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
            String resp = smsUtil.sendSms(sentMessageBean.getMessage(), sentMessageBean.getTo());
            SMSResponse sMSResponse = objectMapper.readValue(resp, SMSResponse.class);
            System.out.println("here");

            for (Warnings warnings : sMSResponse.getWarnings()) {
                if (warnings.getNumbers().contains(sentMessageBean.getTo())) {

                    sentMessageBean.setStatus("DND");
                }
            }

            for (Messages messages : sMSResponse.getMessages()) {
                if (messages.getRecipient().contains(sentMessageBean.getTo())) {
                    sentMessageBean.setTxtId(messages.getId());
                    sentMessageBean.setStatus("SUCCESS");
                }
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
            MessageDao messageDao = new MessageDao();
            SentMessageBean sentMessageBean = objectMapper.readValue(data, SentMessageBean.class);

            for (String to : sentMessageBean.getBulkTo()) {
                sentMessageBean.setTo(to);
                sentMessageBean = messageDao.SendSms(sentMessageBean);
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
            errorLog.error("User Class" + ex);
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
            errorLog.error("User Class" + ex);
        }
        return jsonInString;
    }
}
