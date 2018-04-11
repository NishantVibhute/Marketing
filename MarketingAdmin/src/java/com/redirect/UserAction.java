/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.BankDetailsBean;
import com.beans.CreateUserExcelRow;
import com.beans.JoiningDetailsBean;
import com.beans.PassRowBean;
import com.beans.SchemeBean;
import com.beans.SchemeJoinBean;
import com.beans.UserBean;
import com.beans.UserJoinPaymentBean;
import com.beans.UserPassword;
import com.beans.UserSchemeBalance;
import com.ennum.PaymentMode;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 *
 * @author nishant.vibhute
 */
public class UserAction extends ActionSupport implements ModelDriven, ServletRequestAware {

    ObjectMapper objectMapper = new ObjectMapper();
    List<UserBean> userList = new ArrayList<>();
    List<UserSchemeBalance> userSchemeBalances = new ArrayList<>();
    List<UserJoinPaymentBean> jdbs = new ArrayList<>();
    List<String> userEmailList = new ArrayList<>();

    String successMsg = StringUtils.EMPTY, errorMsg = StringUtils.EMPTY;
    UserBean userBean = new UserBean();
    public String val;
    public String userId, schemeId;
    private InputStream inputStream;
    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private HttpServletRequest servletRequest;

    List<CreateUserExcelRow> userStatus = new ArrayList<>();

    public String redirect() {

        try {

            String resp = ServiceUtil.getResponseGet("/user/getuserdetailslist");

            userList = objectMapper.readValue(resp, new TypeReference<List<UserBean>>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String redirectDetails() {

        try {

            String resp = ServiceUtil.getResponseGet("/user/getuserdetailslist");

            userList = objectMapper.readValue(resp, new TypeReference<List<UserBean>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String getUserDetails() {

        try {

            String resp = ServiceUtil.getResponse(this.val, "/user/getUserDetailsByUserId");

//            userBean = objectMapper.readValue(resp, UserBean.class);
            String res = objectMapper.writeValueAsString(resp);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String getSchemeJoiningDetails() {

        try {

            JoiningDetailsBean jdb = new JoiningDetailsBean();
            jdb.setUserId(Integer.parseInt(userId));
            jdb.setSchemeId(Integer.parseInt(schemeId));
            String req = objectMapper.writeValueAsString(jdb);

            String resp = ServiceUtil.getResponse(req, "/user/getuserjoinpayment");

            jdbs = objectMapper.readValue(resp, new TypeReference<List<UserJoinPaymentBean>>() {
            });
            String res = objectMapper.writeValueAsString(jdbs);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String getUserSchemePassbook() {
        try {
            SchemeJoinBean sjb = new SchemeJoinBean();
            sjb.setSchemeId(Integer.parseInt(schemeId));
            sjb.setUserId(Integer.parseInt(userId));
            String req = objectMapper.writeValueAsString(sjb);
            String resp = ServiceUtil.getResponse(req, "/account/getUserSchemePassbook");

            List<PassRowBean> passRowBean = objectMapper.readValue(resp, new TypeReference<List<PassRowBean>>() {
            });

            String res = objectMapper.writeValueAsString(passRowBean);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (IOException ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getSchemePoolByNameByUserId() {
        try {

            JoiningDetailsBean jdb = new JoiningDetailsBean();
            jdb.setUserId(Integer.parseInt(userId));
            jdb.setSchemeId(Integer.parseInt(schemeId));
            String req = objectMapper.writeValueAsString(jdb);
            String resp = ServiceUtil.getResponse(req, "/user/getSchemePoolByNameForUser");

            inputStream = new ByteArrayInputStream(resp.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getUserPaymentDetails() {
        try {

            JoiningDetailsBean jdb = new JoiningDetailsBean();
            jdb.setUserId(Integer.parseInt(userId));
            jdb.setSchemeId(Integer.parseInt(schemeId));
            String req = objectMapper.writeValueAsString(jdb);
            String resp = ServiceUtil.getResponse(req, "/user/getPaymentDetails");

            inputStream = new ByteArrayInputStream(resp.getBytes(StandardCharsets.UTF_8));

        } catch (Exception ex) {
            Logger.getLogger(SchemeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getSchemeUserBalance() {

        try {

            String resp = ServiceUtil.getResponse(this.val, "/user/getschemeuserbalance");

            userSchemeBalances = objectMapper.readValue(resp, new TypeReference<List<UserSchemeBalance>>() {
            });
            String res = objectMapper.writeValueAsString(userSchemeBalances);
            inputStream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionSupport.SUCCESS;

    }

    public String redirectCreateUser() {
        return ActionSupport.SUCCESS;
    }

    public String createUser() {
        try {
            List<CreateUserExcelRow> excelRows = new ArrayList<>();
            LinkedHashMap<String, Integer> schemes = new LinkedHashMap<>();
            FileInputStream excelFile = new FileInputStream(fileUpload);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row header = iterator.next();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                int i = 0;
                CreateUserExcelRow createUserExcelRow = new CreateUserExcelRow();
                if (!currentRow.getCell(i).getStringCellValue().equals("")) {
                    createUserExcelRow.setEmailId(currentRow.getCell(i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setPassword(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setFirstName(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setMiddleName(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setLastName(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    currentRow.getCell(++i).setCellType(Cell.CELL_TYPE_STRING);
                    createUserExcelRow.setMobileNo(currentRow.getCell(i) == null ? "" : "" + currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setAddress(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setPanCardNo(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setAadharCardNo(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    BankDetailsBean bankDetailsBean = new BankDetailsBean();
                    bankDetailsBean.setBankName(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    bankDetailsBean.setIfscCode(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    bankDetailsBean.setBranchName(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    currentRow.getCell(++i).setCellType(Cell.CELL_TYPE_STRING);
                    bankDetailsBean.setBankAccNo(currentRow.getCell(i) == null ? "" : "" + currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setBankDetails(bankDetailsBean);
                    createUserExcelRow.setScheme(currentRow.getCell(++i) == null ? "" : currentRow.getCell(i).getStringCellValue());
                    createUserExcelRow.setPaymentModeId(PaymentMode.getByName(currentRow.getCell(++i).getStringCellValue()).getId());
                    excelRows.add(createUserExcelRow);
                }
            }

            String respUser = ServiceUtil.getResponseGet("/user/getuserdetailslist");

            userList = objectMapper.readValue(respUser, new TypeReference<List<UserBean>>() {
            });

            for (UserBean ub : userList) {
                userEmailList.add(ub.getEmailId());
            }

            String resp = ServiceUtil.getResponseGet("/scheme/getlist");

            List<SchemeBean> schemeList = objectMapper.readValue(resp, new TypeReference<List<SchemeBean>>() {
            });

            for (SchemeBean sb : schemeList) {
                schemes.put(sb.getSchemeName(), sb.getId());
            }

            for (CreateUserExcelRow createUserExcelRow : excelRows) {
                CreateUserExcelRow createUserExcelRowNew = new CreateUserExcelRow();
                createUserExcelRowNew = createUserExcelRow;
                if (!userEmailList.contains(createUserExcelRow.getEmailId())) {
                    UserPassword userBean = new UserPassword();
                    userBean.setEmailId(createUserExcelRow.getEmailId());
                    userBean.setPassword(createUserExcelRow.getPassword());
                    String req = objectMapper.writeValueAsString(userBean);
                    String resp2 = ServiceUtil.getResponse(req, "/user/signup");

                    String ret = objectMapper.readValue(resp2, String.class);

                    if (ret.equalsIgnoreCase("User Added Successfuly")) {
                        UserBean userBean10 = new UserBean();
                        userBean10.setFirstName(createUserExcelRow.getFirstName());
                        userBean10.setMiddleName(createUserExcelRow.getMiddleName());
                        userBean10.setLastName(createUserExcelRow.getLastName());
                        userBean10.setEmailId(createUserExcelRow.getEmailId());
                        userBean10.setMobileNo(createUserExcelRow.getMobileNo());
                        userBean10.setAddress(createUserExcelRow.getAddress());
                        userBean10.setPanCardNo(createUserExcelRow.getPanCardNo());
                        userBean10.setAadharCardNo(createUserExcelRow.getAadharCardNo());
                        userBean10.setBankDetails(createUserExcelRow.getBankDetails());
                        String req1 = objectMapper.writeValueAsString(userBean10);
                        String resp1 = ServiceUtil.getResponse(req1, "/user/create");
                        String ret1 = objectMapper.readValue(resp1, String.class);

                        int userId = Integer.parseInt(ret1);

                        if (userId != 0) {
                            createUserExcelRowNew.setStatus("Success");
                            createUserExcelRowNew.setReason("User created Successfully ");
                            SchemeJoinBean up = new SchemeJoinBean();
                            up.setSchemeId(schemes.get(createUserExcelRow.getScheme()));
                            up.setUserId(userId);
                            up.setMemberType(1);
                            up.setPaymentModeId(createUserExcelRow.getPaymentModeId());
                            up.setUserStatus(1);
                            String req3 = objectMapper.writeValueAsString(up);
                            String resp3 = ServiceUtil.getResponse(req3, "/scheme/join");
                            String ret3 = objectMapper.readValue(resp3, String.class);

                            int joinId = Integer.parseInt(ret3);
                            if (joinId != 0) {
                                createUserExcelRowNew.setStatus("Success");
                                createUserExcelRowNew.setReason(createUserExcelRowNew.getReason() + "| Joined Product");
                            } else {
                                createUserExcelRowNew.setStatus("Error");
                                createUserExcelRowNew.setReason(createUserExcelRowNew.getReason() + "| Failed To join");
                            }
                        } else {
                            createUserExcelRowNew.setStatus("Error");
                            createUserExcelRowNew.setReason("Failed To Create User");
                        }
                    } else {
                        createUserExcelRowNew.setStatus("Error");
                        createUserExcelRowNew.setReason("Failed To Add User");
                    }
                } else {
                    createUserExcelRowNew.setStatus("Error");
                    createUserExcelRowNew.setReason("User Already Exists");
                    UserPassword userBean = new UserPassword();
                    userBean.setEmailId(createUserExcelRow.getEmailId());
                    userBean.setPassword(createUserExcelRow.getPassword());
                    String req = objectMapper.writeValueAsString(userBean);
                    String respUserValid = ServiceUtil.getResponse(req, "/user/validate");
                    UserBean ur = objectMapper.readValue(respUserValid, UserBean.class);

                    if (ur.getId() != 0) {
                        SchemeJoinBean up = new SchemeJoinBean();
                        up.setSchemeId(schemes.get(createUserExcelRow.getScheme()));
                        up.setUserId((int) ur.getId());
                        up.setMemberType(1);
                        up.setPaymentModeId(createUserExcelRow.getPaymentModeId());
                        up.setUserStatus(1);
                        String req3 = objectMapper.writeValueAsString(up);
                        String resp3 = ServiceUtil.getResponse(req3, "/scheme/join");
                        String ret3 = objectMapper.readValue(resp3, String.class);

                        int joinId = Integer.parseInt(ret3);
                        if (joinId != 0) {
                            createUserExcelRowNew.setStatus("Success");
                            createUserExcelRowNew.setReason(createUserExcelRowNew.getReason() + "| Joined Product");
                        } else {
                            createUserExcelRowNew.setStatus("Error");
                            createUserExcelRowNew.setReason(createUserExcelRowNew.getReason() + "| Failed To join");
                        }
                    } else {
                        createUserExcelRowNew.setStatus("Error");
                        createUserExcelRowNew.setReason(createUserExcelRowNew.getReason() + "| Invalid credential");
                    }

                }
                userStatus.add(createUserExcelRowNew);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ActionSupport.SUCCESS;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Object getModel() {
        return userBean;
    }

    public List<UserBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserBean> userList) {
        this.userList = userList;
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;

    }

    public List<CreateUserExcelRow> getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(List<CreateUserExcelRow> userStatus) {
        this.userStatus = userStatus;
    }

}
