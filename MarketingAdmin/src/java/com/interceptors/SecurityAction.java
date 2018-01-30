/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.Map;

/**
 *
 * @author sagar.gurav
 */
public class SecurityAction extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map<String, Object> session = ai.getInvocationContext().getSession();

        String loginId = (String) session.get("loginId");

        if (loginId == null) {
            return Action.LOGIN;
        } else {
            return ai.invoke();
        }

    }

}
