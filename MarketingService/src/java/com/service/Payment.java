/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.PaymentRealeaseRequestBean;
import com.beans.PendingJoinRequest;
import com.dao.PaymentDao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @GET
    @Path("/getPaymentRealeaseSchemewiseCount")

    @Produces("text/plain")
    public String getPaymentRealeaseSchemewiseCount() {

        String json = "";
        try {
            List<PendingJoinRequest> prrbs = paymentDao.getPaymentRealeaseSchemewiseCount();
            json = objectMapper.writeValueAsString(prrbs);
        } catch (IOException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
