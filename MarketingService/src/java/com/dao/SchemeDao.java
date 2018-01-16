/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.beans.SchemeBean;
import com.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author Nishant
 */
public class SchemeDao {

    DbUtil db = new DbUtil();
    Connection con;
    ResultSet rs;

    Logger logger = Logger.getLogger(SchemeDao.class);

    public int createScheme(SchemeBean schemeBean) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement("call createScheme(?,?,?,?,?,?,?)");
            ps.setString(1, schemeBean.getSchemeName());
            ps.setString(2, schemeBean.getSchemeDescription());
            ps.setString(3, schemeBean.getStartDate());
            ps.setInt(4, 1);
            ps.setDouble(5, schemeBean.getMemberPerc());
            ps.setDouble(6, schemeBean.getCompanyPerc());
            ps.setDouble(7, schemeBean.getAmount());

            count = ps.executeUpdate();
            db.closeConnection(con);
        } catch (Exception ex) {
            logger.error("CreateUser", ex);
        }
        return count;

    }

}
