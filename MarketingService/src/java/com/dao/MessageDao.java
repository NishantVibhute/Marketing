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
    
    public MessageBean getSMSContentFromSubject(String subject) {
        
        MessageBean messageContent = new MessageBean();
        
        try {
            int i = 10 / 0;
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSMSContentFromSubject(?)");
            ps.setString(1, subject);
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

    public MessageBean editSMSContentFromSubject(MessageBean messageContent) {
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call editSMSContentFromSubject(?,?)");
            ps.setString(1, messageContent.getSubject());
            ps.setString(2, messageContent.getBody());
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

    public SentMessageBean saveSMSSentStatus(SentMessageBean sentMessageBean) {
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call saveSMSSentStatus(?,?)");
            ps.setString(1, sentMessageBean.getTo());
            ps.setString(2, sentMessageBean.getMessage());
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
                sentMessageBean.setFrom(rs.getString(2));
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
}
