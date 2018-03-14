/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.PaymentRealeaseRequestBean;
import com.beans.PendingJoinRequest;
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
public class PaymentDao {

    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;

    Logger logger = Logger.getLogger(PaymentDao.class);

    public List<PaymentRealeaseRequestBean> getPaymentRealeaseRequest(int schemeId) {
        List<PaymentRealeaseRequestBean> prrbs = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getPaymentJoinId(?)");
            ps.setInt(1, schemeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                PaymentRealeaseRequestBean pd = new PaymentRealeaseRequestBean();
                pd.setSchemeId(rs.getInt(2));
                pd.setUserId(rs.getInt(3));
                pd.setUserName(rs.getString(4));
                pd.setAmount(rs.getDouble(5));
                PreparedStatement ps1 = this.con.prepareStatement("call getPaymentReleaseRequest(?,?)");
                ps1.setInt(1, rs.getInt(2));
                ps1.setInt(2, rs.getInt(3));
                List<String> joinDates = new ArrayList<>();
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    joinDates.add(rs.getString(1));
                }
                pd.setJoinDates(joinDates);

                prrbs.add(pd);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return prrbs;
    }

    public List<PendingJoinRequest> getPaymentRealeaseSchemewiseCount() {
        List<PendingJoinRequest> pendingJoinRequestList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getPaymentRealeaseSchemewiseCount()");

            rs = ps.executeQuery();

            while (rs.next()) {
                PendingJoinRequest pd = new PendingJoinRequest();
                pd.setId(rs.getInt(1));
                pd.setName(rs.getString(2));
                pd.setCount(rs.getInt(3));

                pendingJoinRequestList.add(pd);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }

        return pendingJoinRequestList;

    }

}
