/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Nishant
 */
public class SchemeBean {

    public int id;
    String schemeName;
    String schemeDescription;
    String startDate;
    int isSchemeActive;
    double memberPerc;
    double companyPerc;
    double amount;
    String videoId;
    int isClosed;
    int isStarted;
    String filePath;
    int joinCount;
    String chequeName;
    String bankName;
    String accountNo;
    String bankIfsc;
    String bankBranch;

    public int getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(int joinCount) {
        this.joinCount = joinCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeDescription() {
        return schemeDescription;
    }

    public void setSchemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getIsSchemeActive() {
        return isSchemeActive;
    }

    public void setIsSchemeActive(int isSchemeActive) {
        this.isSchemeActive = isSchemeActive;
    }

    public double getMemberPerc() {
        return memberPerc;
    }

    public void setMemberPerc(double memberPerc) {
        this.memberPerc = memberPerc;
    }

    public double getCompanyPerc() {
        return companyPerc;
    }

    public void setCompanyPerc(double companyPerc) {
        this.companyPerc = companyPerc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(int isClosed) {
        this.isClosed = isClosed;
    }

    public int getIsStarted() {
        return isStarted;
    }

    public void setIsStarted(int isStarted) {
        this.isStarted = isStarted;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getChequeName() {
        return chequeName;
    }

    public void setChequeName(String chequeName) {
        this.chequeName = chequeName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankIfsc() {
        return bankIfsc;
    }

    public void setBankIfsc(String bankIfsc) {
        this.bankIfsc = bankIfsc;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

}
