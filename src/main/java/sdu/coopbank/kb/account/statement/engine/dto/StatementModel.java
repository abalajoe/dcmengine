/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdu.coopbank.kb.account.statement.engine.dto;

import java.util.Date;

/**
 *
 * @author fkabiru
 */
public class StatementModel {
    
    private String AccountNo;
    private Date startDt;
    private Date endDt;
    private String statementType;
    private String statementPDF;
    private String curUser;
    private String POSTAL_ADDRESS;

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

   
    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String AccountNo) {
        this.AccountNo = AccountNo;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public String getStatementPDF() {
        return statementPDF;
    }

    public void setStatementPDF(String statementPDF) {
        this.statementPDF = statementPDF;
    }

    public String getCurUser() {
        return curUser;
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }

    public String getPOSTAL_ADDRESS() {
        return POSTAL_ADDRESS;
    }

    public void setPOSTAL_ADDRESS(String POSTAL_ADDRESS) {
        this.POSTAL_ADDRESS = POSTAL_ADDRESS;
    }

}
