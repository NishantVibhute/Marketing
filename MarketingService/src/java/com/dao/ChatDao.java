/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.ChatRoomBean;
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
public class ChatDao {

    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;
    Logger logger = Logger.getLogger(ChatDao.class);

    public List<ChatRoomBean> getChatList(String type) {

        List<ChatRoomBean> chatList = new ArrayList();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getChats(?)");
            ps.setString(1, type);

            rs = ps.executeQuery();

            while (rs.next()) {
                ChatRoomBean chatBean = new ChatRoomBean();
                chatBean.setId(rs.getInt(1));
                chatBean.setUserId(rs.getInt(2));
                chatBean.setShortForm(rs.getString(3));
                chatBean.setHashValue(("" + rs.getString(3).hashCode()).substring(1, 4));
                chatBean.setName(rs.getString(4));
                chatBean.setMessage(rs.getString(5));
                chatBean.setStatus(rs.getString(6));
                chatBean.setReplyTo(rs.getInt(7));
                chatBean.setTime(rs.getString(8));
                chatBean.setReplyToName(rs.getString(9));
                chatList.add(chatBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return chatList;

    }

    public List<ChatRoomBean> getUnreadMessageList() {

        List<ChatRoomBean> chatList = new ArrayList();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getUnreadMessage()");

            rs = ps.executeQuery();

            while (rs.next()) {
                ChatRoomBean chatBean = new ChatRoomBean();
                chatBean.setId(rs.getInt(1));
                chatBean.setUserId(rs.getInt(2));
                chatBean.setShortForm(rs.getString(3));
                chatBean.setHashValue(("" + rs.getString(3).hashCode()).substring(1, 4));
                chatBean.setName(rs.getString(4));
                chatBean.setMessage(rs.getString(5));
                chatBean.setStatus(rs.getString(6));
                chatBean.setReplyTo(rs.getInt(7));
                chatBean.setTime(rs.getString(8));
                chatBean.setReplyToName(rs.getString(9));
                chatList.add(chatBean);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return chatList;

    }

    public int sendMessageOrReply(ChatRoomBean ch) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call sendMessageOrReply(?,?,?)");
            ps.setInt(1, ch.getUserId());
            ps.setString(2, ch.getMessage());
            ps.setInt(3, ch.getReplyTo());

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
