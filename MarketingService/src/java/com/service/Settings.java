/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.beans.SettingsBean;
import com.dao.SettingsDao;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * REST Web Service
 *
 * @author nishant.vibhute
 */
@Path("settings")
public class Settings {

    ObjectMapper objectMapper = new ObjectMapper();
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Payment.class);
    SettingsDao settingsDao = new SettingsDao();

    @POST
    @Path("/getSettingsValue")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String getSettingsValue(String data) {

        String json = "";
        try {
            int prrbs = settingsDao.getSettingsValue(Integer.parseInt(data));
            json = "" + prrbs;
        } catch (Exception ex) {
            logger.error("Payment Class" + ex);
        }
        return json;
    }

    @GET
    @Path("/getSettingsList")

    @Produces("text/plain")
    public String getSettingsList() {

        String json = "";
        try {
            List<SettingsBean> prrbs = settingsDao.getSettingsList();

            json = objectMapper.writeValueAsString(prrbs);
        } catch (Exception ex) {
            logger.error("Payment Class" + ex);
        }
        return json;
    }

    @POST
    @Path("/updateSettings")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String updateSettings(String data) {

        String json = "";
        try {
            List<SettingsBean> sc = objectMapper.readValue(data, new TypeReference<List<SettingsBean>>() {
            });
            int prrbs = settingsDao.updateSettings(sc);
            json = "" + prrbs;
        } catch (Exception ex) {
            logger.error("Payment Class" + ex);
        }
        return json;
    }
}
