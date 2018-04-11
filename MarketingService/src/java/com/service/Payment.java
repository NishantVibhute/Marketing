/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.PaymentBean;
import com.beans.PaymentRealeaseRequestBean;
import com.beans.PendingJoinRequest;
import com.dao.PaymentDao;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author Nishant
 */
@Path("payment")
public class Payment {

    PaymentDao paymentDao = new PaymentDao();
    ObjectMapper objectMapper = new ObjectMapper();
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Payment.class);

    @GET
    @Path("/getPaymentRealeaseSchemewiseCount")

    @Produces("text/plain")
    public String getPaymentRealeaseSchemewiseCount() {

        String json = "";
        try {
            List<PendingJoinRequest> prrbs = paymentDao.getPaymentRealeaseSchemewiseCount();
            json = objectMapper.writeValueAsString(prrbs);
        } catch (IOException ex) {
            logger.error("Payment Class" + ex);
        }
        return json;
    }

    @POST
    @Path("/getPaymentRealeaseRequest")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String getPaymentRealeaseRequest(String data) {

        String json = "";
        try {
            List<PaymentRealeaseRequestBean> prrbs = paymentDao.getPaymentRealeaseRequest(Integer.parseInt(data));
            json = objectMapper.writeValueAsString(prrbs);
        } catch (IOException ex) {
            logger.error("Payment Class" + ex);
        }
        return json;
    }

    @POST
    @Path("/saveCustomerPaymentDetails")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String saveCustomerPaymentDetails(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            PaymentBean up = objectMapper.readValue(data, PaymentBean.class);

            int count = paymentDao.updatePayment(up);

            jsonInString = "" + count;
        } catch (Exception ex) {
            logger.error("Payment Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/saveCustomerPaymentBonusPenalty")
    @Consumes("text/plain")
    @Produces("text/plain")

    public String saveCustomerPaymentBonusPenalty(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            PaymentBean up = objectMapper.readValue(data, PaymentBean.class);

            int count = paymentDao.updatePaymentBonusPenalty(up);

            jsonInString = "" + count;
        } catch (Exception ex) {
            logger.error("Payment Class" + ex);
        }
        return jsonInString;
    }
}
