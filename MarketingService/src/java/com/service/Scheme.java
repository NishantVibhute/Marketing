/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.ChartData;
import com.beans.JoiningDetailsBean;
import com.beans.PaymentBean;
import com.beans.PaymentResponse;
import com.beans.PendingJoinRequest;
import com.beans.SchemeBean;
import com.beans.SchemeJoinBean;
import com.beans.SchemeRows;
import com.beans.SchemeRowsByName;
import com.beans.UserDatails;
import com.dao.SchemeDao;
import java.io.File;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
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
            jsonInString = "" + count;

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

    @POST
    @Path("/getschemestats")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getSchemeStats(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            List<ChartData> chartDatas = schemeDao.getSchemeStats(Integer.parseInt(data));

            jsonInString = objectMapper.writeValueAsString(chartDatas);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getSchemePoolByName")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getSchemePoolByName(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            List<SchemeRowsByName> schemeRowList = schemeDao.getSchemePoolByName(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getUserIdSchemeIdByJoinId")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String getUserIdSchemeIdByJoinId(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            JoiningDetailsBean schemeRowList = schemeDao.getUserIdSchemeIdByJoinId(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(schemeRowList);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

    @GET
    @Path("/getLogo/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("id") String data) {
        String destPath = "/home/ec2-user/Files/";
        File file = new File(destPath + data + ".jpg");
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=" + data + ".jpg");
        return response.build();

    }

}
