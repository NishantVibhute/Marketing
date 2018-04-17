/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.SettingsBean;
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
public class SettingsDao {

    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;

    Logger logger = Logger.getLogger(SettingsDao.class);

    public int getSettingsValue(int id) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSettingValue(?)");
            ps.setInt(1, id);
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

    public List<SettingsBean> getSettingsList() {

        List<SettingsBean> settingsList = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call getSettingList()");

            rs = ps.executeQuery();

            while (rs.next()) {
                SettingsBean sb = new SettingsBean();
                sb.setId(rs.getInt(1));
                sb.setType(rs.getString(2));
                sb.setValue(rs.getInt(3));
                sb.setValType(rs.getInt(3) == 0 ? "" : "Checked");
                settingsList.add(sb);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return settingsList;
    }

    public int updateSettings(List<SettingsBean> sl) {

        int count = 0;
        try {
            for (SettingsBean s : sl) {
                this.con = db.getConnection();
                PreparedStatement ps = this.con.prepareStatement("call updateSettingValue(?,?)");
                ps.setInt(1, s.getId());
                ps.setInt(2, s.getValue());
                count = ps.executeUpdate();
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;
    }

}
