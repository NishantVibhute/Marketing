/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.BalanceBean;
import com.beans.PassRowBean;
import com.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class AccountDao {

    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;
    Logger logger = Logger.getLogger(AccountDao.class);

    public BalanceBean getCompanyBalance() {

        BalanceBean balanceBean = new BalanceBean();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getCompanyBalance()");

            rs = ps.executeQuery();

            while (rs.next()) {
                balanceBean.setTotalBalance(rs.getDouble(1));
                balanceBean.setCompanyBalance(rs.getDouble(2));
                balanceBean.setMemberBalance(rs.getDouble(3));
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return balanceBean;

    }

    public List<PassRowBean> getSchemePassbook(int schemeId) {
        List<PassRowBean> passList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemePassbook(?)");
            ps.setInt(1, schemeId);

            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                PassRowBean passRowBean = new PassRowBean();
                passRowBean.setSrNo(++i);
                passRowBean.setDate(rs.getString(1));
                passRowBean.setParticulars(rs.getString(2));
                passRowBean.setWithdrawl(rs.getDouble(3));
                passRowBean.setDeposit(rs.getDouble(4));
                passRowBean.setSchemeid(rs.getInt(5));
                passRowBean.setBalance(rs.getDouble(6));
                passList.add(passRowBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return passList;
    }

    public BalanceBean getSchemeBalance(int schemeId) {

        BalanceBean balanceBean = new BalanceBean();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSchemeBalance(?)");
            ps.setInt(1, schemeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                balanceBean.setTotalBalance(rs.getDouble(1));
                balanceBean.setCompanyBalance(rs.getDouble(2));
                balanceBean.setMemberBalance(rs.getDouble(3));
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return balanceBean;

    }

}
