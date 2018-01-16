/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.SchemeBean;
import com.dao.SchemeDao;
import javax.ws.rs.Consumes;
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
@Path("Scheme")
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
    public String joinScheme() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
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
                msg = "Scheme Added Successfuly";
            } else {
                msg = "Failed to Add Scheme";
            }
            jsonInString = objectMapper.writeValueAsString(msg);
        } catch (Exception ex) {
            logger.error("User Class" + ex);
        }
        return jsonInString;
    }

}
