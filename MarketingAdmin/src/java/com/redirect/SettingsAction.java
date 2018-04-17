/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.SettingsBean;
import com.opensymphony.xwork2.ActionSupport;
import com.util.ServiceUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author nishant.vibhute
 */
public class SettingsAction {

    ObjectMapper objectMapper = new ObjectMapper();
    private InputStream inputStream;
    public String id;
    public String valScheme;
    List<SettingsBean> settingsList = new ArrayList<>();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;

    public String redirect() {

        try {
            String resp = ServiceUtil.getResponseGet("/settings/getSettingsList");

            settingsList = objectMapper.readValue(resp, new TypeReference<List<SettingsBean>>() {
            });

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;

    }

    public String saveSettings() {

        try {
            String req = objectMapper.writeValueAsString(settingsList);

            String resp = ServiceUtil.getResponse(req, "/settings/updateSettings");

            if (Integer.parseInt(resp) != 0) {
                successMsg = "Updated Successfully";
            } else {
                errorMsg = "Failed to update";
            }
        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;

    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValScheme() {
        return valScheme;
    }

    public void setValScheme(String valScheme) {
        this.valScheme = valScheme;
    }

    public List<SettingsBean> getSettingsList() {
        return settingsList;
    }

    public void setSettingsList(List<SettingsBean> settingsList) {
        this.settingsList = settingsList;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
