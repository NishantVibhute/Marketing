/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.CreateVirtualUser;
import com.beans.JoiningDetailsBean;
import com.beans.MessageBean;
import com.beans.Messages;
import com.beans.SMSResponse;
import com.beans.SchemeRowsByName;
import com.beans.SentMessageBean;
import com.beans.UserBean;
import com.beans.UserJoinPaymentBean;
import com.beans.UserPassword;
import com.beans.UserSchemeBalance;
import com.beans.Warnings;
import com.dao.EmailDao;
import com.dao.MessageDao;
import com.dao.SettingsDao;
import com.dao.UserDao;
import com.util.EmailUtil;
import com.util.SMSUtil;
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
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger infoLog = Logger.getLogger("infoLogger");
    SettingsDao settingsDao = new SettingsDao();

    @POST
    @Path("/create")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String createUser(String data) {
        String jsonInString = "";
        EmailUtil em = new EmailUtil();
        try {

            String msg;
            //TODO return proper representation object
            UserBean userBean = new UserBean();

            userBean = objectMapper.readValue(data, UserBean.class);

            int updatedRows = userDao.createUser(userBean);

            if (updatedRows != 0) {
                UserBean userBeanNew = userDao.getUserDetailsByUserId(updatedRows);
                MessageDao messageDao = new MessageDao();
                SentMessageBean sentMessageBean = new SentMessageBean();
                MessageBean messageContent = messageDao.getSMSContentFromSubject("Welcome MSG", 0);
                sentMessageBean.setTempId(messageContent.getId());
                sentMessageBean.setFrom(1);
                sentMessageBean.setTo(userBean.getMobileNo());
                sentMessageBean.setToName(userBean.getFirstName() + " " + userBean.getLastName());
                String msg1 = messageContent.getBody().replace("<userId>", "P" + updatedRows);
                String msg2 = msg1.replace("<loginId>", userBeanNew.getEmailId());
                String msg3 = msg2.replace("<password>", userBeanNew.getPassword());

                sentMessageBean.setMessage(msg3);
                int isSend = settingsDao.getSettingsValue(1);
                if (isSend == 1) {
                    String resp = SMSUtil.sendSms(sentMessageBean.getMessage(), sentMessageBean.getTo());
                    SMSResponse sMSResponse = objectMapper.readValue(resp, SMSResponse.class);
                    if (sMSResponse.getStatus().equals("failure")) {
                        for (Warnings warnings : sMSResponse.getWarnings()) {
                            if (warnings.getNumbers().contains(sentMessageBean.getTo())) {
                                sentMessageBean.setStatus("DND");
                            }
                        }
                    } else {
                        for (Messages messages : sMSResponse.getMessages()) {
                            if (messages.getRecipient().contains(sentMessageBean.getTo())) {
                                sentMessageBean.setTxtId(messages.getId());
                                sentMessageBean.setStatus("SUCCESS");
                            }
                        }
                    }
                } else {
                    sentMessageBean.setStatus("Not Activated");
                }
                EmailDao emailDao = new EmailDao();
                SentMessageBean sentMessageBean1 = new SentMessageBean();
                MessageBean messageContent1 = emailDao.getEmailContentFromSubject("Welcome Email");
                sentMessageBean1.setTempId(messageContent1.getId());
                sentMessageBean1.setFrom(1);
                sentMessageBean1.setTo(userBean.getEmailId());
                sentMessageBean1.setSubject(messageContent1.getEmailSubject());
//                String msg11 = messageContent1.getBody().replace("<username>", userBean.getFirstName());
                sentMessageBean1.setMessage(messageContent1.getBody());
                int isSendEMail = settingsDao.getSettingsValue(2);
                if (isSendEMail == 1) {
                    String resp1 = em.send(sentMessageBean1.getTo(), sentMessageBean1.getSubject(), sentMessageBean1.getMessage());
                    sentMessageBean1.setStatus("Sent");
                } else {
                    sentMessageBean1.setStatus("Not Activated");
                }
                sentMessageBean1 = emailDao.SendEmail(sentMessageBean1);
                sentMessageBean = messageDao.SendSms(sentMessageBean);
                msg = "" + updatedRows;
            } else {
                msg = "" + updatedRows;
            }
            jsonInString = msg;
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
            EmailUtil em = new EmailUtil();
            int updatedRows = userDao.signUp(up);

            if (updatedRows != 0) {

                EmailDao emailDao = new EmailDao();
                SentMessageBean sentMessageBean = new SentMessageBean();
                MessageBean messageContent = emailDao.getEmailContentFromSubject("SignUp");
                sentMessageBean.setTempId(messageContent.getId());
                sentMessageBean.setFrom(1);
                sentMessageBean.setTo(up.getEmailId());
                sentMessageBean.setSubject(messageContent.getEmailSubject());
                String msg1 = messageContent.getBody().replace("<userid>", up.getEmailId());
                String msg2 = msg1.replace("<password>", up.getPassword());
                sentMessageBean.setMessage(msg2);
                int isSendEMail = settingsDao.getSettingsValue(2);
                String resp = "";
                if (isSendEMail == 1) {
                    resp = em.send(sentMessageBean.getTo(), sentMessageBean.getSubject(), sentMessageBean.getMessage());
                    sentMessageBean.setStatus("Sent");
                } else {
                    resp = "success";
                    sentMessageBean.setStatus("Not Activated");
                }
                if (resp.equalsIgnoreCase("success")) {
                    sentMessageBean = emailDao.SendEmail(sentMessageBean);
                }
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
            errorLog.error("User Class : " + ex);
            infoLog.info("User Class info hey");
        }
        return jsonInString;
    }

    @POST
    @Path("/getUserDetailsByUserId")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getUserDetailsByUserId(String data) {
        String jsonInString = "";
        try {

            UserBean userBean = userDao.getUserDetailsByUserId(Integer.parseInt(data));

            jsonInString = objectMapper.writeValueAsString(userBean);
        } catch (Exception ex) {
            errorLog.error("User Class : " + ex);
            infoLog.info("User Class info hey");
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

    @POST
    @Path("/userschemejoininglist")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String userschemejoininglist(String data) {
        String jsonInString = "";
        try {
            JoiningDetailsBean up = objectMapper.readValue(data, JoiningDetailsBean.class);

            List<JoiningDetailsBean> updatedRows = userDao.userschemejoininglist(up);
            jsonInString = objectMapper.writeValueAsString(updatedRows);

        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getschemeuserbalance")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getSchemeUserBalance(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            List<UserSchemeBalance> schemeRowList = userDao.getSchemeUserBalance(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getschemeusertotalbalance")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getSchemeUserTotalBalance(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            UserSchemeBalance u = objectMapper.readValue(data, UserSchemeBalance.class);
            UserSchemeBalance schemeRowList = userDao.getSchemeUserTotalBalance(u.getUserId(), u.getSchemeId());
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getuserjoinpayment")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getUserJoinPayment(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            JoiningDetailsBean up = objectMapper.readValue(data, JoiningDetailsBean.class);
            List<UserJoinPaymentBean> schemeRowList = userDao.getUserJoinPayment(up);
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getSchemePoolByNameForUser")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getSchemePoolByUserId(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            JoiningDetailsBean joiningDetailsBean = objectMapper.readValue(data, JoiningDetailsBean.class);
            List<SchemeRowsByName> schemeRowList = userDao.getSchemePoolByNameForUser(joiningDetailsBean);
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getPaymentDetails")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getPaymentDetails(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            JoiningDetailsBean joiningDetailsBean = objectMapper.readValue(data, JoiningDetailsBean.class);
            List<UserJoinPaymentBean> schemeRowList = userDao.getPaymentDetails(joiningDetailsBean);
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/changePassword")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String changePassword(String data) {
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            UserPassword up = objectMapper.readValue(data, UserPassword.class);
            int updatedRows = userDao.changePassword(up);
            jsonInString = objectMapper.writeValueAsString(updatedRows);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }
}
