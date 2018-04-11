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

    public static String toTitleCase(String givenString) {
        StringBuffer sb = new StringBuffer();
        if (givenString != null && !givenString.isEmpty()) {
            String[] arr = givenString.split(" ");
            for (int i = 0; i < arr.length; i++) {
                sb.append(Character.toUpperCase(arr[i].charAt(0)))
                        .append(arr[i].substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
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

    public static String convertDateToShow(String date) {
        String newDate = "";
        try {
            String sDate1 = date;
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
            java.text.SimpleDateFormat sdf
                    = new java.text.SimpleDateFormat("dd/MM/yyyy");

            newDate = sdf.format(date1);

        } catch (ParseException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newDate;
    }

}
