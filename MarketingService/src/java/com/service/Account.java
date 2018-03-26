/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.BalanceBean;
import com.beans.PassRowBean;
import com.beans.SchemeJoinBean;
import com.dao.AccountDao;
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
@Path("account")
public class Account {

    Logger logger = Logger.getLogger(Account.class);
    ObjectMapper objectMapper = new ObjectMapper();
    AccountDao accountDao = new AccountDao();

    @GET
    @Path("/getCompanyBalance")
    @Produces("text/plain")
    public String getCompanyBalance() {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            BalanceBean balanceBean = accountDao.getCompanyBalance();
            jsonInString = objectMapper.writeValueAsString(balanceBean);
        } catch (Exception ex) {
            logger.error("Chat Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getSchemePassbook")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getSchemePassbook(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            List<PassRowBean> balanceBean = accountDao.getSchemePassbook(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(balanceBean);
        } catch (Exception ex) {
            logger.error("Chat Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getSchemeBalance")
    @Produces("text/plain")
    public String getSchemeBalance(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {

            //TODO return proper representation object
            BalanceBean balanceBean = accountDao.getSchemeBalance(Integer.parseInt(data));
            jsonInString = objectMapper.writeValueAsString(balanceBean);
        } catch (Exception ex) {
            logger.error("Chat Class" + ex);
        }
        return jsonInString;
    }

    @POST
    @Path("/getUserSchemePassbook")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getUserSchemePassbook(String data) {
        //TODO return proper representation object
        String jsonInString = "";
        try {
            SchemeJoinBean schemeJoinBean = objectMapper.readValue(data, SchemeJoinBean.class);
            //TODO return proper representation object
            List<PassRowBean> balanceBean = accountDao.getUserSchemePassbook(schemeJoinBean);
            jsonInString = objectMapper.writeValueAsString(balanceBean);
        } catch (Exception ex) {
            logger.error("Chat Class" + ex);
        }
        return jsonInString;
    }
}
