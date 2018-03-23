/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.BalanceBean;
import com.beans.PassRowBean;
import com.beans.SchemeBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
public class AccountAction extends ActionSupport implements ModelDriven {

    ObjectMapper objectMapper = new ObjectMapper();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    BalanceBean balanceBean = new BalanceBean();
    List<SchemeBean> schemeList = new ArrayList<>();
    private InputStream inputStream;
    String val, valScheme;

    public String redirect() {
        try {
            String resp = ServiceUtil.getResponseGet("/account/getCompanyBalance");

            balanceBean = objectMapper.readValue(resp, BalanceBean.class);

            resp = ServiceUtil.getResponseGet("/scheme/getlist");

            schemeList = objectMapper.readValue(resp, new TypeReference<List<SchemeBean>>() {
            });

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getSchemeBalance() {
        try {
            String resp = ServiceUtil.getResponse(this.valScheme, "/account/getSchemeBalance");

            BalanceBean balanceBean = objectMapper.readValue(resp, BalanceBean.class);
            String res = objectMapper.writeValueAsString(balanceBean);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getSchemePassbook() {
        try {
            String resp = ServiceUtil.getResponse(this.getVal(), "/account/getSchemePassbook");

            List<PassRowBean> passRowBean = objectMapper.readValue(resp, new TypeReference<List<PassRowBean>>() {
            });

            String res = objectMapper.writeValueAsString(passRowBean);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
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

    public BalanceBean getBalanceBean() {
        return balanceBean;
    }

    public void setBalanceBean(BalanceBean balanceBean) {
        this.balanceBean = balanceBean;
    }

    @Override
    public Object getModel() {
        return balanceBean;
    }

    public List<SchemeBean> getSchemeList() {
        return schemeList;
    }

    public void setSchemeList(List<SchemeBean> schemeList) {
        this.schemeList = schemeList;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getValScheme() {
        return valScheme;
    }

    public void setValScheme(String valScheme) {
        this.valScheme = valScheme;
    }

}
