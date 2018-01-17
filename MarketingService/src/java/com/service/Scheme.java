/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.PaymentBean;
import com.beans.PaymentResponse;
import com.beans.PendingJoinRequest;
import com.beans.SchemeBean;
import com.beans.SchemeJoinBean;
import com.beans.SchemeRows;
import com.beans.UserDatails;
import com.dao.SchemeDao;
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
@Path("scheme")
public class Scheme {

    Logger logger = Logger.getLogger(User.class);
    SchemeDao schemeDao = new SchemeDao();
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Retrieves representation of an instance of com.service.Scheme
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/join")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String joinScheme(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            SchemeJoinBean up = objectMapper.readValue(data, SchemeJoinBean.class);

            int count = schemeDao.joinScheme(up);

            if (count != 0) {
                jsonInString = "success";
            } else {
                jsonInString = "failed";
            }
//            jsonInString = objectMapper.writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/create")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String createScheme(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            SchemeBean up = objectMapper.readValue(data, SchemeBean.class);

            int count = schemeDao.createScheme(up);

            if (count != 0) {
                jsonInString = "success";
            } else {
                jsonInString = "failed";
            }
//            jsonInString = objectMapper.writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @GET
    @Path("/getlist")
    @Produces("text/plain")

    public String getSchemeList() {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            List<SchemeBean> schemeList = schemeDao.getSchemeList();
            jsonInString = objectMapper.writeValueAsString(schemeList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getschemedetail")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getSchemeDetail(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            SchemeBean scheme = schemeDao.getSchemeDetail(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(scheme);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/edit")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String edit(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            String msg;
            //TODO return proper representation object
            SchemeBean up = objectMapper.readValue(data, SchemeBean.class);

            int count = schemeDao.editScheme(up);

            if (count != 0) {
                jsonInString = "success";
            } else {
                jsonInString = "failed";
            }
//            jsonInString = objectMapper.writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @GET
    @Path("/getpendingrequest")
    @Produces("text/plain")

    public String getPendingRequest() {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            List<PendingJoinRequest> pendingReqestList = schemeDao.getPendingJoinRequest();
            jsonInString = objectMapper.writeValueAsString(pendingReqestList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getpendingrequestbyscheme")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getPendingRequestDetailByScheme(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            List<UserDatails> pendingUserDetails = schemeDao.getPendingRequestByScheme(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(pendingUserDetails);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getSchemePool")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getSchemePool(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            List<SchemeRows> schemeRowList = schemeDao.getSchemePool(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/savePaymentDetails")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String savePaymentDetails(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            PaymentBean up = objectMapper.readValue(data, PaymentBean.class);

            PaymentResponse psResponse = schemeDao.updatePayment(up);

            jsonInString = objectMapper.writeValueAsString(psResponse);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }
}
