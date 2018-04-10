/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class CommonUtil {

    public static String filePath = "";
    public static String url = "";

    static {
//        filePath = "/home/ec2-user/Files/";
//        url = "http://13.127.62.153:8080/MarketingService/webresources";
        filePath = "/home/ec2-user/Files/";
        url = "http://localhost:8084/MarketingService/webresources";
    }

    public static String convertDate(String date) {
        String newDate = "";
        try {
            String sDate1 = date;
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("yyyy-MM-dd");

            newDate = sdf.format(date1);

        } catch (ParseException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newDate;
    }

}
