/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.MessageBean;
import com.dao.EmailDao;
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
}
