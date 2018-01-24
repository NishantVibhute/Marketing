/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.MessageBean;
import com.beans.SentMessageBean;
import com.dao.MessageDao;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

    Logger logger = Logger.getLogger(Message.class);
    ObjectMapper objectMapper = new ObjectMapper();

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
            logger.error("Message Class" + ex);
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
            logger.error("Message Class" + ex);
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
            sentMessageBean = messageDao.saveSMSSentStatus(sentMessageBean);
            jsonInString = objectMapper.writeValueAsString(sentMessageBean);

        } catch (Exception ex) {
            logger.error("Message Class" + ex);
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
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }
}
