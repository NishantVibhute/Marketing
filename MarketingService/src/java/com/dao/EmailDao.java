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
 * @author nishant.vibhute
 */
public class EmailDao {

    Logger logger = Logger.getLogger(MessageDao.class);
    static final Logger errorLog = Logger.getLogger("errorLogger");
    static final Logger infoLog = Logger.getLogger("infoLogger");
    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;

    public List<MessageBean> getEmailTemplates() {
        List<MessageBean> msgList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getEmailTemplates()");
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

    public MessageBean getEmailContentFromSubject(String subject) {

        MessageBean messageContent = new MessageBean();

        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getEmailContentFromSubject(?)");
            ps.setString(1, subject);
            rs = ps.executeQuery();

            while (rs.next()) {
                messageContent.setId(rs.getInt(1));
                messageContent.setSubject(rs.getString(2));
                messageContent.setBody(rs.getString(3));
                messageContent.setEmailSubject(rs.getString(4));

            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return messageContent;

    }

    public MessageBean editEmailContentFromSubject(MessageBean messageContent) {
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call editEmailContentFromSubject(?,?,?)");
            ps.setString(1, messageContent.getSubject());
            ps.setString(2, messageContent.getBody());
            ps.setString(3, messageContent.getEmailSubject());
            rs = ps.executeQuery();

            while (rs.next()) {
                messageContent.setId(rs.getInt(1));
                messageContent.setSubject(rs.getString(2));
                messageContent.setBody(rs.getString(3));
                messageContent.setEmailSubject(rs.getString(4));
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return messageContent;
    }

    public SentMessageBean SendEmail(SentMessageBean sentMessageBean) {
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call saveEmailSent(?,?,?,?,?)");
            ps.setString(1, sentMessageBean.getTo());
            ps.setString(2, sentMessageBean.getMessage());
            ps.setLong(3, sentMessageBean.getFrom());
            ps.setInt(4, sentMessageBean.getTempId());
            ps.setString(5, sentMessageBean.getStatus());
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
            PreparedStatement ps = this.con.prepareStatement("call getSentEmailList()");

            rs = ps.executeQuery();

            while (rs.next()) {
                SentMessageBean sentMessageBean = new SentMessageBean();
                sentMessageBean.setId(rs.getInt(1));
                sentMessageBean.setFrom(rs.getInt(2));
                sentMessageBean.setTo(rs.getString(3));
                sentMessageBean.setMessage(rs.getString(4));
                sentMessageBean.setTempId(rs.getInt(5));
                sentMessageBean.setSendDate(rs.getString(6));

                sentMessageBean.setStatus(rs.getString(7));
                sentMessageList.add(sentMessageBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            errorLog.error("MessageDao", ex);
        }
        return sentMessageList;
    }

}
