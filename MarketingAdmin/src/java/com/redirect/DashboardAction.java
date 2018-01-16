/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Nishant
 */
public class DashboardAction extends ActionSupport {

    public String redirect() {
        return ActionSupport.SUCCESS;
    }

}
