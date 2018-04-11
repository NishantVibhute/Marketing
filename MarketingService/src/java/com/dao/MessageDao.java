/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.MessageBean;
import com.beans.SentMessageBean;
import com.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author sagar.gurav
 */
public class MessageDao {

    Logger logger = Logger.getLogger(MessageDao.class);
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger infoLog = Logger.getLogger("infoLogger");
    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;

    public MessageBean getSMSContentFromSubject(String template, int schemeId) {

        MessageBean messageContent = new MessageBean();

        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSMSContentFromSubject(?,?)");
            ps.setString(1, template);
            ps.setInt(2, schemeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                messageContent.setId(rs.getInt(1));
                messageContent.setSubject(rs.getString(2));
                messageContent.setBody(rs.getString(3));
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return messageContent;

    }

    public List<MessageBean> getSMSTemplates() {
        List<MessageBean> msgList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSMSTemplates()");
            rs = ps.executeQuery();
            while (rs.next()) {
                MessageBean messageContent = new MessageBean();
                messageContent.setId(rs.getInt(1));
                messageContent.setSubject(rs.getString(2));
                messageContent.setBody(rs.getString(3));
                msgList.add(messageContent);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return msgList;

    }

    public MessageBean editSMSContentFromSubject(MessageBean messageContent) {
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call editSMSContentFromSubject(?,?,?)");
            ps.setString(1, messageContent.getSubject());
            ps.setString(2, messageContent.getBody());
            ps.setInt(3, messageContent.getSchemeId());
            rs = ps.executeQuery();

            while (rs.next()) {
                messageContent.setId(rs.getInt(1));
                messageContent.setSubject(rs.getString(2));
                messageContent.setBody(rs.getString(3));
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return messageContent;
    }

    public SentMessageBean SendSms(SentMessageBean sentMessageBean) {
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call saveSMSSentStatus(?,?,?,?,?,?,?)");
            ps.setString(1, sentMessageBean.getTo());
            ps.setString(2, sentMessageBean.getMessage());
            ps.setLong(3, sentMessageBean.getFrom());
            ps.setInt(4, sentMessageBean.getTempId());
            ps.setInt(5, sentMessageBean.getSchemeId());
            ps.setString(6, sentMessageBean.getStatus());
            ps.setString(7, sentMessageBean.getTxtId());
            rs = ps.executeQuery();

            while (rs.next()) {
                sentMessageBean.setId(rs.getInt(1));
            }
            db.closeConnection(con);
        } catch (Exception ex) {

            errorLog.error("MessageDao", ex);
        }
        return sentMessageBean;

    }

    public List<SentMessageBean> getSentList() {
        List<SentMessageBean> sentMessageList = new ArrayList();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSentMessageList()");

            rs = ps.executeQuery();

            while (rs.next()) {
                SentMessageBean sentMessageBean = new SentMessageBean();
                sentMessageBean.setId(rs.getInt(1));
                sentMessageBean.setFromName(rs.getString(2));
                sentMessageBean.setTo(rs.getString(3));
                sentMessageBean.setTempId(rs.getInt(4));
                sentMessageBean.setMessage(rs.getString(5));
                sentMessageBean.setSentDate(rs.getTimestamp(6));
                sentMessageBean.setDeliveredDate(rs.getTimestamp(7));
                sentMessageBean.setStatus(rs.getString(8));
                sentMessageList.add(sentMessageBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return sentMessageList;
    }

    public List<SentMessageBean> getSentListWithCount(SentMessageBean sb) {
        List<SentMessageBean> sentMessageList = new ArrayList();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserSentSmsListWithCount(?,?,?,?)");
            ps.setInt(1, (int) sb.getFrom());
            ps.setString(2, sb.getTo());
            ps.setInt(3, sb.getSchemeId());
            ps.setInt(4, sb.getTempId());
            rs = ps.executeQuery();

            while (rs.next()) {
                SentMessageBean sentMessageBean = new SentMessageBean();

                sentMessageBean.setTo(rs.getString(1));
                sentMessageBean.setCount(rs.getInt(2));
                sentMessageList.add(sentMessageBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return sentMessageList;
    }

    public List<SentMessageBean> getSentDetails(SentMessageBean sb) {
        List<SentMessageBean> sentMessageList = new ArrayList();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUserSentSmsDetails(?,?,?,?)");
            ps.setInt(1, (int) sb.getFrom());
            ps.setString(2, sb.getTo());
            ps.setInt(3, sb.getSchemeId());
            ps.setInt(4, sb.getTempId());
            rs = ps.executeQuery();

            while (rs.next()) {
                SentMessageBean sentMessageBean = new SentMessageBean();
                sentMessageBean.setMessage(rs.getString(1));
                sentMessageList.add(sentMessageBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return sentMessageList;
    }
}
