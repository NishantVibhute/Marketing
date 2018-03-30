/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.ChartData;
import com.beans.PaymentBean;
import com.beans.PaymentResponse;
import com.beans.PendingJoinRequest;
import com.beans.SchemeBean;
import com.beans.SchemeJoinBean;
import com.beans.SchemeRows;
import com.beans.SchemeRowsByName;
import com.beans.UserDatails;
import com.ennum.MemberType;
import com.ennum.StatusEnum;
import com.util.CommonUtil;
import com.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Nishant
 */
public class SchemeDao {

    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;

    Logger logger = Logger.getLogger(SchemeDao.class);

    public int createScheme(SchemeBean schemeBean) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call createScheme(?,?,?,?,?,?,?,?,?)");
            ps.setString(1, schemeBean.getSchemeName());
            ps.setString(2, schemeBean.getSchemeDescription());
            ps.setString(3, CommonUtil.convertDate(schemeBean.getStartDate()));
            ps.setInt(4, 1);
            ps.setDouble(5, schemeBean.getMemberPerc());
            ps.setDouble(6, (100 - schemeBean.getMemberPerc()));
            ps.setDouble(7, schemeBean.getAmount());
            ps.setString(8, schemeBean.getVideoId());
            ps.setString(9, schemeBean.getFilePath());
            count = ps.executeUpdate();
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;

    }

    public List<SchemeBean> getSchemeList() {

        List<SchemeBean> schemeList = new ArrayList();

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemeList()");

            rs = ps.executeQuery();

            while (rs.next()) {
                SchemeBean schemeBean = new SchemeBean();
                schemeBean.setId(rs.getInt(1));
                schemeBean.setSchemeName(rs.getString(2));
                schemeBean.setSchemeDescription(rs.getString(3));
                schemeBean.setStartDate(CommonUtil.convertDateToShow(rs.getString(4)));
                schemeBean.setIsSchemeActive(rs.getInt(5));
                schemeBean.setMemberPerc(rs.getDouble(6));
                schemeBean.setCompanyPerc(rs.getDouble(7));
                schemeBean.setAmount(rs.getDouble(8));
                schemeBean.setIsClosed(rs.getInt(9));
                schemeBean.setVideoId(rs.getString(10));
                schemeList.add(schemeBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return schemeList;

    }

    public SchemeBean getSchemeDetail(int id) {

        SchemeBean schemeBean = new SchemeBean();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemeDetail(?)");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                schemeBean.setId(rs.getInt(1));
                schemeBean.setSchemeName(rs.getString(2));
                schemeBean.setSchemeDescription(rs.getString(3));
                schemeBean.setStartDate(CommonUtil.convertDateToShow(rs.getString(4)));
                schemeBean.setIsSchemeActive(rs.getInt(5));
                schemeBean.setMemberPerc(rs.getDouble(6));
                schemeBean.setCompanyPerc(rs.getDouble(7));
                schemeBean.setAmount(rs.getDouble(8));
                schemeBean.setIsClosed(rs.getInt(9));
                schemeBean.setIsStarted(rs.getInt(10));
                schemeBean.setVideoId(rs.getString(11));

            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return schemeBean;

    }

    public int editScheme(SchemeBean schemeBean) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call editScheme(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, schemeBean.getSchemeName());
            ps.setString(2, schemeBean.getSchemeDescription());
            ps.setString(3, CommonUtil.convertDate(schemeBean.getStartDate()));
            ps.setInt(4, schemeBean.getIsSchemeActive());
            ps.setDouble(5, schemeBean.getMemberPerc());
            ps.setDouble(6, (100 - schemeBean.getMemberPerc()));
            ps.setDouble(7, schemeBean.getAmount());
            ps.setInt(8, schemeBean.getIsClosed());
            ps.setInt(9, schemeBean.getId());
            ps.setString(10, schemeBean.getVideoId());
            count = ps.executeUpdate();
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;
    }

    public int joinScheme(SchemeJoinBean schemeJoinBean) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call joinScheme(?,?,?,?,?)");
            ps.setInt(1, schemeJoinBean.getUserId());
            ps.setInt(2, schemeJoinBean.getPaymentModeId());
            ps.setInt(3, schemeJoinBean.getUserStatus());
            ps.setInt(4, schemeJoinBean.getSchemeId());
            ps.setInt(5, schemeJoinBean.getMemberType());
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;
    }

    public List<PendingJoinRequest> getPendingJoinRequest() {
        List<PendingJoinRequest> pendingJoinRequestList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getInprogressScheme()");

            rs = ps.executeQuery();

            while (rs.next()) {
                PendingJoinRequest pd = new PendingJoinRequest();
                pd.setId(rs.getInt(1));
                pd.setName(rs.getString(2));
                pd.setAmount(rs.getDouble(3));
                PreparedStatement ps1 = this.con.prepareStatement("call getCountUserOfScheme(?,?)");
                ps1.setInt(1, rs.getInt(1));
                ps1.setInt(2, 1);

                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    pd.setCount(rs1.getInt(1));
                }
                pendingJoinRequestList.add(pd);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return pendingJoinRequestList;

    }

    public List<UserDatails> getPendingRequestByScheme(int schemeId) {
        List<UserDatails> pendingUserDatails = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getListOFCustomerOnJoinStatus(?,?)");
            ps.setInt(1, schemeId);
            ps.setInt(2, 1);

            rs = ps.executeQuery();

            while (rs.next()) {
                UserDatails ud = new UserDatails();
                ud.setId(rs.getInt(1));
                ud.setName(rs.getString(2));
                ud.setType(MemberType.getById(rs.getInt(3)).name());
                ud.setPaymentModeId(rs.getInt(4));
                pendingUserDatails.add(ud);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return pendingUserDatails;

    }

    public List<SchemeRows> getSchemePool(int schemeId) {
        List<SchemeRows> schemeRowsList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemePoolDetails(?,?)");
            ps.setString(1, "Scheme_" + schemeId);
            ps.setInt(2, 10);

            rs = ps.executeQuery();

            while (rs.next()) {
                SchemeRows ud = new SchemeRows();
                ud.setParent(rs.getString(1));
                ud.setChild1(rs.getString(2) == null ? "" : rs.getString(2));
                ud.setChild2(rs.getString(3) == null ? "" : rs.getString(3));
                ud.setChild3(rs.getString(4) == null ? "" : rs.getString(4));
                schemeRowsList.add(ud);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return schemeRowsList;

    }

    public List<SchemeRowsByName> getSchemePoolByName(int schemeId) {
        List<SchemeRowsByName> schemeRowsList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemePoolDetailsByName(?)");
            ps.setString(1, "Scheme_" + schemeId);

            rs = ps.executeQuery();

            while (rs.next()) {
                SchemeRowsByName ud = new SchemeRowsByName();
                ud.setPmemberType(rs.getInt(1));
                ud.setPname(rs.getString(2));
                ud.setPjoinDate(rs.getString(3));

                ud.setCh1memberType(rs.getInt(4));
                ud.setCh1name(rs.getString(5));
                ud.setCh1joinDate(rs.getString(6));

                ud.setCh2memberType(rs.getInt(7));
                ud.setCh2name(rs.getString(8));
                ud.setCh2joinDate(rs.getString(9));

                ud.setCh3memberType(rs.getInt(10));
                ud.setCh3name(rs.getString(11));
                ud.setCh3joinDate(rs.getString(12));
                schemeRowsList.add(ud);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return schemeRowsList;

    }

    public PaymentResponse updatePayment(PaymentBean payBean) {
        int count = 0;
        PaymentResponse psResponse = new PaymentResponse();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call updatePaymentDetails(?,?,?,?,?,?,?,?)");
            ps.setInt(1, payBean.getJoiningId());
            ps.setInt(2, payBean.getPaymentModeId());
            ps.setString(3, payBean.getChequeNo());
            ps.setString(4, payBean.getChequeDate());
            ps.setString(5, payBean.getBankName());
            ps.setString(6, payBean.getUTRNo());
            ps.setInt(7, StatusEnum.CONFIRMED.getId());
            ps.setDouble(8, payBean.getAmount());
            rs = ps.executeQuery();

            while (rs.next()) {

                psResponse.setIsExit(rs.getInt(1));
                psResponse.setUserId(rs.getInt(2));
                psResponse.setMemberType(rs.getInt(3));
                psResponse.setIsSuccess(rs.getInt(4));
                psResponse.setSchemeId(rs.getInt(5));

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return psResponse;
    }

    public List<ChartData> getSchemeStats(int schemeId) {
        List<ChartData> chartData = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemePoolStats(?)");
            ps.setInt(1, schemeId);

            rs = ps.executeQuery();

            while (rs.next()) {
                ChartData ud = new ChartData();
                ud.setLabel(rs.getString(1));
                ud.setValue(rs.getInt(2));

                chartData.add(ud);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return chartData;

    }

}
