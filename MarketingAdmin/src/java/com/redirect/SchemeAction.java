/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redirect;

import com.beans.SchemeBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.util.ServiceUtil;

/**
 *
 * @author Nishant
 */
public class SchemeAction extends ActionSupport implements ModelDriven {

    SchemeBean schemeBean = new SchemeBean();

    public String redirectNew() {

        return ActionSupport.SUCCESS;
    }

    public String redirectList() {

        return ActionSupport.SUCCESS;
    }

    public String createScheme() {
        String resp = ServiceUtil.getResponse(schemeBean);
        return ActionSupport.SUCCESS;
    }

    @Override
    public Object getModel() {
        return schemeBean;
    }

}
