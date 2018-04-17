/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.EmailBean;
import com.beans.MessageBean;
import com.beans.SentMessageBean;
import com.dao.EmailDao;
import com.dao.SettingsDao;
import com.util.EmailUtil;
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
 * @author nishant.vibhute
 */
@Path("email")
public class Email {

    ObjectMapper objectMapper = new ObjectMapper();
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger infoLog = Logger.getLogger("infoLogger");
    SettingsDao settingsDao = new SettingsDao();

    @POST
    @Path("/sendEmail")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String sendEmail(String data) {
        String jsonInString = "";
        try {
            EmailUtil eu = new EmailUtil();
            EmailDao emailDao = new EmailDao();
            EmailBean emailBean = objectMapper.readValue(data, EmailBean.class);
            SentMessageBean sentMessageBean1 = new SentMessageBean();
            int isSendEMail = settingsDao.getSettingsValue(2);
            String resp = "";
            if (isSendEMail == 1) {
                resp = eu.send(emailBean.getTo(), emailBean.getSubject(), emailBean.getBody());
                sentMessageBean1.setStatus("Sent");
            } else {
                resp = "success";
                sentMessageBean1.setStatus("Not Activated");
            }
            if (resp.equalsIgnoreCase("success")) {
                sentMessageBean1.setTempId(0);
                sentMessageBean1.setFrom(1);
                sentMessageBean1.setTo(emailBean.getTo());
                sentMessageBean1.setSubject(emailBean.getSubject());
                sentMessageBean1.setMessage(emailBean.getBody());
                sentMessageBean1 = emailDao.SendEmail(sentMessageBean1);
                jsonInString = "Email sent successfully";
            } else {
                jsonInString = "Email sending failed";
            }

        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @GET
    @Path("/getEmailTemplates")
    @Produces("text/plain")

    public String getEmailTemplates() {
        String jsonInString = "";
        try {
            EmailDao emailDao = new EmailDao();
            List<MessageBean> messageContent = emailDao.getEmailTemplates();
            jsonInString = objectMapper.writeValueAsString(messageContent);

        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/getEmailTemplateContent")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getEmailContentFromSubject(String data) {
        String jsonInString = "";
        try {
            EmailDao emailDao = new EmailDao();
            MessageBean messageContent = emailDao.getEmailContentFromSubject(data);
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
            EmailDao emailDao = new EmailDao();
            MessageBean messageContent = objectMapper.readValue(data, MessageBean.class);
            messageContent = emailDao.editEmailContentFromSubject(messageContent);
            jsonInString = objectMapper.writeValueAsString(messageContent);

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
            EmailDao emailDao = new EmailDao();
            List<SentMessageBean> sentMessageList = emailDao.getSentList();
            jsonInString = objectMapper.writeValueAsString(sentMessageList);
        } catch (Exception ex) {
            errorLog.error("Message Class" + ex);
        }
        return jsonInString;
    }

}
