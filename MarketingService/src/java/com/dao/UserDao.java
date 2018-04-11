/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.BankDetailsBean;
import com.beans.CreateVirtualUser;
import com.beans.JoiningDetailsBean;
import com.beans.SchemeRowsByName;
import com.beans.UserBean;
import com.beans.UserJoinPaymentBean;
import com.beans.UserPassword;
import com.beans.UserSchemeBalance;
import com.service.User;
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
public class UserDao {

    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;

    Logger logger = Logger.getLogger(User.class);

    public int createUser(UserBean userBean) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call createUser(?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, CommonUtil.toTitleCase(userBean.getFirstName()));
            ps.setString(2, CommonUtil.toTitleCase(userBean.getMiddleName()));
            ps.setString(3, CommonUtil.toTitleCase(userBean.getLastName()));
            ps.setString(4, userBean.getEmailId());
            ps.setString(5, userBean.getMobileNo());
            ps.setString(6, userBean.getAddress());
            ps.setString(7, userBean.getPanCardNo());
            ps.setString(8, userBean.getAadharCardNo());
            ps.setString(9, userBean.getBankDetails().getBankName());
            ps.setString(10, userBean.getBankDetails().getIfscCode());
            ps.setString(11, userBean.getBankDetails().getBranchName());
            ps.setString(12, userBean.getBankDetails().getBankAccNo());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;

    }

    public int createVirtualUser(CreateVirtualUser data) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call createVirtualUser(?,?)");
            ps.setInt(1, data.getUserId());
            ps.setInt(2, data.getSchemeId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;

    }

    public int signUp(UserPassword userBean) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call signUp(?,?)");
            ps.setString(1, userBean.getEmailId());
            ps.setString(2, userBean.getPassword());

            count = ps.executeUpdate();
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;

    }

    public UserBean validate(UserPassword up) {

        UserBean userBean = new UserBean();
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call validate_user(?,?)");
            ps.setString(1, up.getEmailId());
            ps.setString(2, up.getPassword());

            rs = ps.executeQuery();

            while (rs.next()) {
                userBean.setEmailId(rs.getString(1));
                userBean.setId(rs.getInt(2));
                userBean.setFirstName(rs.getString(3));
                userBean.setMiddleName(rs.getString(4));
                userBean.setLastName(rs.getString(5));
                userBean.setMobileNo(rs.getString(6));
                userBean.setAddress(rs.getString(7));
                userBean.setPanCardNo(rs.getString(8));
                userBean.setAadharCardNo(rs.getString(9));
                BankDetailsBean bp = new BankDetailsBean();
                bp.setBankName(rs.getString(10));
                bp.setIfscCode(rs.getString(11));
                bp.setBranchName(rs.getString(12));
                bp.setBankAccNo(rs.getString(13));
                userBean.setBankDetails(bp);
                userBean.setIsValid(1);
                count = 1;
            }

            if (count == 0) {
                userBean.setIsValid(0);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return userBean;

    }

    public List<UserBean> getUserDetilsList() {
        List<UserBean> userList = new ArrayList<>();
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserDetails()");

            rs = ps.executeQuery();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setId(rs.getInt(1));
                ub.setFirstName(rs.getString(2));
                ub.setMiddleName(rs.getString(3));
                ub.setLastName(rs.getString(4));
                ub.setEmailId(rs.getString(5));
                ub.setMobileNo(rs.getString(6));
                ub.setAddress(rs.getString(7));
                ub.setPanCardNo(rs.getString(8));
                ub.setAadharCardNo(rs.getString(9));
                BankDetailsBean b = new BankDetailsBean();
                b.setBankName(rs.getString(10));
                b.setIfscCode(rs.getString(11));
                b.setBranchName(rs.getString(12));
                b.setBankAccNo(rs.getString(13));
                ub.setBankDetails(b);
                ub.setBalance(rs.getDouble(14));
                ub.setPassword(rs.getString(15));
                userList.add(ub);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return userList;

    }

    public UserBean getUserDetailsByUserId(int userId) {
        UserBean ub = new UserBean();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserDetailsByUserId(?)");
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {

                ub.setId(rs.getInt(1));
                ub.setFirstName(rs.getString(2));
                ub.setMiddleName(rs.getString(3));
                ub.setLastName(rs.getString(4));
                ub.setEmailId(rs.getString(5));
                ub.setMobileNo(rs.getString(6));
                ub.setAddress(rs.getString(7));
                ub.setPanCardNo(rs.getString(8));
                ub.setAadharCardNo(rs.getString(9));
                BankDetailsBean b = new BankDetailsBean();
                b.setBankName(rs.getString(10));
                b.setIfscCode(rs.getString(11));
                b.setBranchName(rs.getString(12));
                b.setBankAccNo(rs.getString(13));
                ub.setBankDetails(b);
                ub.setBalance(rs.getDouble(14));
                ub.setPassword(rs.getString(15));
                ub.setJoinDate(rs.getString(16));

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return ub;

    }

    public List<UserPassword> getVisitorList() {
        List<UserPassword> userList = new ArrayList<>();
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getVisitors()");

            rs = ps.executeQuery();

            while (rs.next()) {
                UserPassword ub = new UserPassword();
                ub.setId(rs.getInt(1));
                ub.setEmailId(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setDate(rs.getString(4));
                ub.setIsBlocked(rs.getInt(5));

                userList.add(ub);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return userList;

    }

    public int updatevisitorstatus(UserPassword data) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call updatevisitorstatus(?,?)");
            ps.setInt(1, data.getId());
            ps.setInt(2, data.getIsBlocked());

            count = ps.executeUpdate();

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;

    }

    public List<JoiningDetailsBean> userschemejoininglist(JoiningDetailsBean data) {
        List<JoiningDetailsBean> jdbs = new ArrayList<>();
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserSchemeDetail(?,?)");
            ps.setLong(1, data.getUserId());
            ps.setLong(2, data.getSchemeId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JoiningDetailsBean jdb = new JoiningDetailsBean();
                jdb.setId(rs.getLong(1));
                jdb.setUserId(rs.getLong(2));
                jdb.setPaymodeId(rs.getInt(3));
                jdb.setUser_status(rs.getInt(4));
                jdb.setSchemeId(rs.getInt(5));
                jdb.setMemberType(rs.getInt(6));
                jdb.setJoinDate(rs.getString(7));
                jdb.setRequestDate(rs.getString(8));
                jdb.setBalance(rs.getDouble(9));
                jdbs.add(jdb);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return jdbs;

    }

    public List<UserSchemeBalance> getSchemeUserBalance(int userId) {
        List<UserSchemeBalance> chartData = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserSchemeBalance(?)");
            ps.setInt(1, userId);

            rs = ps.executeQuery();

            while (rs.next()) {
                UserSchemeBalance ud = new UserSchemeBalance();
                ud.setUserSchemeBalId(rs.getInt(1));
                ud.setUserId(rs.getInt(2));
                ud.setSchemeId(rs.getInt(3));
                ud.setSchemeName(rs.getString(4));
                ud.setBalance(rs.getDouble(5));
                chartData.add(ud);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return chartData;

    }

    public UserSchemeBalance getSchemeUserTotalBalance(int userId, int schemeId) {
        UserSchemeBalance ud = new UserSchemeBalance();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserTotalSchemeBalanceById(?,?)");
            ps.setInt(1, userId);
            ps.setInt(1, schemeId);

            rs = ps.executeQuery();

            while (rs.next()) {

                ud.setSchemeName(rs.getString(1));
                ud.setBalance(rs.getDouble(2));

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return ud;

    }

    public List<UserJoinPaymentBean> getUserJoinPayment(JoiningDetailsBean data) {
        List<UserJoinPaymentBean> chartData = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserJoinPaymentDetails(?,?)");
            ps.setLong(1, data.getUserId());
            ps.setLong(2, data.getSchemeId());

            rs = ps.executeQuery();

            while (rs.next()) {
                UserJoinPaymentBean ud = new UserJoinPaymentBean();
                ud.setPaymenttype(rs.getInt(1));
                ud.setChequeno(rs.getString(2));
                ud.setAmount(rs.getDouble(3));
                ud.setCheque_date(rs.getString(4));
                ud.setBank_name(rs.getString(5));
                ud.setUtrno(rs.getString(6));
                ud.setPaymentstatus(rs.getInt(7));
                ud.setPaymentdate(rs.getString(8));
                ud.setPayment_modeid(rs.getInt(9));
                ud.setUserstatus(rs.getInt(10));
                ud.setRequestdate(rs.getString(11));
                ud.setJoindate(rs.getString(12));
                ud.setIsExit(rs.getInt(13));
                ud.setIsPaymentRealease(rs.getInt(14));
                ud.setPaymentid(rs.getInt(15));

                chartData.add(ud);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return chartData;

    }

    public List<SchemeRowsByName> getSchemePoolByNameForUser(JoiningDetailsBean schemeJoinBean) {
        List<SchemeRowsByName> schemeRowsList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemePoolDetailsByNameForUser(?,?)");
            ps.setString(1, "Scheme_" + schemeJoinBean.getSchemeId());
            ps.setLong(2, schemeJoinBean.getUserId());
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

    public List<UserJoinPaymentBean> getPaymentDetails(JoiningDetailsBean schemeJoinBean) {
        List<UserJoinPaymentBean> schemeRowsList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getCustomerPaymentDetails(?,?)");
            ps.setLong(1, schemeJoinBean.getUserId());
            ps.setInt(2, schemeJoinBean.getSchemeId());

            rs = ps.executeQuery();

            while (rs.next()) {
                UserJoinPaymentBean ud = new UserJoinPaymentBean();
                ud.setPaymentid(rs.getInt(1));
                ud.setUserId(rs.getInt(2));
                ud.setPaymenttype(rs.getInt(3));

                ud.setChequeno(rs.getString(4));
                ud.setAmount(rs.getDouble(5));
                ud.setCheque_date(rs.getString(6));

                ud.setBank_name(rs.getString(7));
                ud.setUtrno(rs.getString(8));
                ud.setPaymentstatus(rs.getInt(9));

                ud.setPaymentdate(rs.getString(10));

                schemeRowsList.add(ud);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return schemeRowsList;

    }

    public int changePassword(UserPassword userBean) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call changePassword(?,?)");
            ps.setString(1, userBean.getEmailId());
            ps.setString(2, userBean.getPassword());

            count = ps.executeUpdate();
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;
    }

}
