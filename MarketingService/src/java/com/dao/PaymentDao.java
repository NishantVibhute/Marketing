/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.PaymentBean;
import com.beans.PaymentRealeaseRequestBean;
import com.beans.PendingJoinRequest;
import com.ennum.StatusEnum;
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
//                pd.setAmount(rs.getDouble(5));

                PreparedStatement ps1 = this.con.prepareStatement("call getUserSchemeBalanceById(?,?)");
                ps1.setInt(1, rs.getInt(3));
                ps1.setInt(2, rs.getInt(2));
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    pd.setAmount(rs1.getDouble(1));
                }

                PreparedStatement ps2 = this.con.prepareStatement("call getPaymentReleaseRequest(?,?)");
                ps2.setInt(1, rs.getInt(2));
                ps2.setInt(2, rs.getInt(3));
                List<String> joinDates = new ArrayList<>();
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    joinDates.add(rs2.getString(1));
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

    public int updatePayment(PaymentBean payBean) {
        int count = 0;

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call updateCustomerPaymentDetails(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, payBean.getJoiningId());
            ps.setInt(2, payBean.getPaymentModeId());
            ps.setString(3, payBean.getChequeNo());
            ps.setString(4, payBean.getChequeDate());
            ps.setString(5, payBean.getBankName());
            ps.setString(6, payBean.getUTRNo());
            ps.setInt(7, StatusEnum.CONFIRMED.getId());
            ps.setDouble(8, payBean.getAmount());
            ps.setInt(9, payBean.getSchemeId());
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

    public int updatePaymentBonusPenalty(PaymentBean payBean) {
        int count = 0;

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call updateCustomerPaymentBonusPenalty(?,?,?,?,?)");
            ps.setInt(1, payBean.getJoiningId());
            ps.setDouble(2, payBean.getOperationalAmount());
            ps.setInt(3, payBean.getSchemeId());
            ps.setString(4, payBean.getReason());
            ps.setInt(5, payBean.getOperationId());
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

}
