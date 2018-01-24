/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.CreateVirtualUser;
import com.beans.UserBean;
import com.beans.UserPassword;
import com.dao.UserDao;
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
 * @author Nishant
 */
@Path("user")
public class User {

    Logger logger = Logger.getLogger(User.class);
    UserDao userDao = new UserDao();
    ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Path("/create")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String createUser(String data) {
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            UserBean userBean = new UserBean();

            userBean = objectMapper.readValue(data, UserBean.class);

            int updatedRows = userDao.createUser(userBean);

            if (updatedRows != 0) {
                msg = "User Added Successfuly";
            } else {
                msg = "Failed to Add user";
            }
            jsonInString = objectMapper.writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/createvirtualuser")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String createVirtualUser(String data) {
        String jsonInString = "";
        try {

            CreateVirtualUser userBean = new CreateVirtualUser();

            userBean = objectMapper.readValue(data, CreateVirtualUser.class);
            jsonInString = "" + userDao.createVirtualUser(userBean);

        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/signup")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String signup(String data) {
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            UserPassword up = objectMapper.readValue(data, UserPassword.class);

            int updatedRows = userDao.signUp(up);

            if (updatedRows != 0) {
                msg = "User Added Successfuly";
            } else {
                msg = "Failed to Add user";
            }
            jsonInString = objectMapper.writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;

    }

    @POST
    @Path("/validate")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String validate(String data) {
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            UserPassword up = objectMapper.readValue(data, UserPassword.class);

            UserBean userBean = userDao.validate(up);

            jsonInString = objectMapper.writeValueAsString(userBean);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;

    }

    @GET
    @Path("/getuserdetailslist")
    @Produces("text/plain")
    public String getUserListWithDetails() {
        String jsonInString = "";
        try {

            List<UserBean> userBean = userDao.getUserDetilsList();

            jsonInString = objectMapper.writeValueAsString(userBean);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @GET
    @Path("/getvisitorlist")
    @Produces("text/plain")
    public String getVisiorList() {
        String jsonInString = "";
        try {
            List<UserPassword> userPass = userDao.getVisitorList();
            jsonInString = objectMapper.writeValueAsString(userPass);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/updatevisitorstatus")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String updatevisitorstatus(String data) {
        String jsonInString = "";
        try {
            UserPassword up = objectMapper.readValue(data, UserPassword.class);

            int updatedRows = userDao.updatevisitorstatus(up);

            if (updatedRows != 0) {
                jsonInString = "success";
            } else {
                jsonInString = "failed";
            }
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

}
