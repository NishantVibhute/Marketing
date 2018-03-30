/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.SchemeBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.CommonUtil;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author Nishant
 */
public class SchemeAction extends ActionSupport implements ModelDriven {

    SchemeBean schemeBean = new SchemeBean();
    ObjectMapper objectMapper = new ObjectMapper();
    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    List<SchemeBean> schemeList = new ArrayList<>();
    private InputStream inputStream;
    public String val, val2;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;

    public String redirectNew() {

        return ActionSupport.SUCCESS;
    }

    public String redirectList() {
        try {
            String resp = ServiceUtil.getResponseGet("/scheme/getlist");

            List<SchemeBean> schemeList1 = objectMapper.readValue(resp, new TypeReference<List<SchemeBean>>() {
            });

            for (SchemeBean schemeBean : schemeList1) {
                schemeBean.setFilePath(CommonUtil.url + "/scheme/getLogo/" + schemeBean.getSchemeName());
                schemeList.add(schemeBean);
            }

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getSchemeDetails() {
        try {
            String resp = ServiceUtil.getResponse(this.getVal(), "/scheme/getschemedetail");

            SchemeBean schemeBean = objectMapper.readValue(resp, SchemeBean.class);

            String res = objectMapper.writeValueAsString(schemeBean);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String createScheme() {
        try {
//            FileInputStream logoFile = new FileInputStream(fileUpload);

            String myFileFileName = schemeBean.getSchemeName() + ".jpg";

            File destFile = new File(CommonUtil.filePath, myFileFileName);
            FileUtils.copyFile(fileUpload, destFile);
            schemeBean.setFilePath(destFile.getPath());

            String input = objectMapper.writeValueAsString(schemeBean);
            String resp = ServiceUtil.getResponse(input, "/scheme/create");

            if (resp.equalsIgnoreCase("success")) {
                successMsg = "Scheme Added Successfully";
            } else {
                errorMsg = "Failed to Add Scheme";
            }

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String poolScheme() {
        try {

            String resp = ServiceUtil.getResponseGet("/scheme/getlist");

            schemeList = objectMapper.readValue(resp, new TypeReference<List<SchemeBean>>() {
            });

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getSchemeStats() {
        try {

            String resp = ServiceUtil.getResponse(val, "/scheme/getschemestats");

            inputStream = new ByteArrayInputStream(resp.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getSchemePoolByName() {
        try {

            String resp = ServiceUtil.getResponse(val2, "/scheme/getSchemePoolByName");

            inputStream = new ByteArrayInputStream(resp.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String editScheme() {
        try {
            String input = objectMapper.writeValueAsString(schemeBean);
            String resp = ServiceUtil.getResponse(input, "/scheme/edit");

            if (resp.equalsIgnoreCase("success")) {
                successMsg = "Scheme Edited Successfully";
            } else {
                errorMsg = "Failed to Edit Scheme";
            }

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    @Override
    public Object getModel() {
        return schemeBean;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

}
