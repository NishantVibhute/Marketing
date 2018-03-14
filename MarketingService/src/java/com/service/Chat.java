/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.ChatRoomBean;
import com.dao.ChatDao;
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
@Path("Chat")
public class Chat {

    Logger logger = Logger.getLogger(User.class);
    ObjectMapper objectMapper = new ObjectMapper();
    ChatDao chatDao = new ChatDao();

    @POST
    @Path("/getlist")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getChatList(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            List<ChatRoomBean> chatList = chatDao.getChatList(data);
            jsonInString = objectMapper.writeValueAsString(chatList);
        } catch (Exception ex) {
            logger.error("Chat Class" + ex);
        }
        return jsonInString;
    }

    @GET
    @Path("/getunreadmessage")
    @Produces("text/plain")

    public String getUnreadMessageList() {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            List<ChatRoomBean> chatList = chatDao.getUnreadMessageList();
            jsonInString = objectMapper.writeValueAsString(chatList);
        } catch (Exception ex) {
            logger.error("Chat Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/sendreply")
    @Produces("text/plain")
    @Consumes("text/plain")

    public String sendMessageOrReply(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            ChatRoomBean up = objectMapper.readValue(data, ChatRoomBean.class);

            int count = chatDao.sendMessageOrReply(up);
            jsonInString = "" + count;

//            jsonInString = objectMapper.writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error("Chat Class" + ex);
        }
        return jsonInString;
    }
}
