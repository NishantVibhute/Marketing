/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.BankDetailsBean;
import com.beans.CreateVirtualUser;
import com.beans.UserBean;
import com.beans.UserPassword;
import com.service.User;
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
            ps.setString(1, userBean.getFirstName());
            ps.setString(2, userBean.getMiddleName());
            ps.setString(3, userBean.getLastName());
            ps.setString(4, userBean.getEmailId());
            ps.setString(5, userBean.getMobileNo());
            ps.setString(6, userBean.getAddress());
            ps.setString(7, userBean.getPanCardNo());
            ps.setString(8, userBean.getAadharCardNo());
            ps.setString(9, userBean.getBankDetails().getBankName());
            ps.setString(10, userBean.getBankDetails().getIfscCode());
            ps.setString(11, userBean.getBankDetails().getBranchName());
            ps.setString(12, userBean.getBankDetails().getBankAccNo());
            count = ps.executeUpdate();
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

}
