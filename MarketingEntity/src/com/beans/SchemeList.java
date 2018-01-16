/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class SchemeList {

    public List<SchemeBean> schemeList = new ArrayList();

    public List<SchemeBean> getSchemeList() {
        return schemeList;
    }

    public void setSchemeList(List<SchemeBean> schemeList) {
        this.schemeList = schemeList;
    }

}
