/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdu.coopbank.kb.account.statement.engine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fkabiru
 */
public class AccountStatementModule {
    private int Id;
    private String ACCOUNT_NAME;
    private String ORGKEY;
    private String CUST_SHORT_NAME;
    private String FORACID;
    private String REF_NUM;
    private String TRAN_RMKS;
    private String SYS_PART_TRAN_CODE;
    private String TRAN_CRNCY_CODE;
    private String SOL_ID;
    private Double AMOUNTDEBIT;
    private Double AMOUNTCREDIT;
    private String TRAN_AMT;
    private String VALUE_DATE;
    private String TRAN_DATE;
    private String PSTD_DATE;
    private String AVAILABLE_AMT;
    private String ACCT_BALANCE;
    private String SVS_TRAN_ID;
    private String REF_AMT;
    private String PSTD_USER_ID;
    private String TRAN_TYPE;
    private String PART_TRAN_SRL_NUM;
    private String REVERSAL_STATUS;
    private String VOUCHER_PRINT_FLG;
    private String SERIAL_NUM;
    private String TRAN_ID;
    private String ENTRY_USER_ID;
    private String PART_TRAN_TYPE;
    private String VFD_USER_ID;
    private String RATE;
    private String ORIGINALAMOUNT;
    private String CHANNEL;
    private String DS_PHYSICAL_ADDRESS;
    private String STATE;
    private String POSTAL_CODE;
    private String POSTAL_ADDRESS;
    private String ACCOUNT_DESCRIPTION;
    private Double UNCLEARED_BAL;

    public AccountStatementModule() {
    }

    public AccountStatementModule(int id, String ACCOUNT_NAME, String ORGKEY, String CUST_SHORT_NAME, String FORACID, String REF_NUM, String TRAN_RMKS, String SYS_PART_TRAN_CODE, String TRAN_CRNCY_CODE, String SOL_ID, Double AMOUNTDEBIT, Double AMOUNTCREDIT, String TRAN_AMT, String VALUE_DATE, String TRAN_DATE, String PSTD_DATE, String AVAILABLE_AMT, String ACCT_BALANCE, String SVS_TRAN_ID, String REF_AMT, String PSTD_USER_ID, String TRAN_TYPE, String PART_TRAN_SRL_NUM, String REVERSAL_STATUS, String VOUCHER_PRINT_FLG, String SERIAL_NUM, String TRAN_ID, String ENTRY_USER_ID, String PART_TRAN_TYPE, String VFD_USER_ID, String RATE, String ORIGINALAMOUNT, String CHANNEL, String DS_PHYSICAL_ADDRESS, String STATE, String POSTAL_CODE, String POSTAL_ADDRESS, String ACCOUNT_DESCRIPTION, Double UNCLEARED_BAL) {
        Id = id;
        this.ACCOUNT_NAME = ACCOUNT_NAME;
        this.ORGKEY = ORGKEY;
        this.CUST_SHORT_NAME = CUST_SHORT_NAME;
        this.FORACID = FORACID;
        this.REF_NUM = REF_NUM;
        this.TRAN_RMKS = TRAN_RMKS;
        this.SYS_PART_TRAN_CODE = SYS_PART_TRAN_CODE;
        this.TRAN_CRNCY_CODE = TRAN_CRNCY_CODE;
        this.SOL_ID = SOL_ID;
        this.AMOUNTDEBIT = AMOUNTDEBIT;
        this.AMOUNTCREDIT = AMOUNTCREDIT;
        this.TRAN_AMT = TRAN_AMT;
        this.VALUE_DATE = VALUE_DATE;
        this.TRAN_DATE = TRAN_DATE;
        this.PSTD_DATE = PSTD_DATE;
        this.AVAILABLE_AMT = AVAILABLE_AMT;
        this.ACCT_BALANCE = ACCT_BALANCE;
        this.SVS_TRAN_ID = SVS_TRAN_ID;
        this.REF_AMT = REF_AMT;
        this.PSTD_USER_ID = PSTD_USER_ID;
        this.TRAN_TYPE = TRAN_TYPE;
        this.PART_TRAN_SRL_NUM = PART_TRAN_SRL_NUM;
        this.REVERSAL_STATUS = REVERSAL_STATUS;
        this.VOUCHER_PRINT_FLG = VOUCHER_PRINT_FLG;
        this.SERIAL_NUM = SERIAL_NUM;
        this.TRAN_ID = TRAN_ID;
        this.ENTRY_USER_ID = ENTRY_USER_ID;
        this.PART_TRAN_TYPE = PART_TRAN_TYPE;
        this.VFD_USER_ID = VFD_USER_ID;
        this.RATE = RATE;
        this.ORIGINALAMOUNT = ORIGINALAMOUNT;
        this.CHANNEL = CHANNEL;
        this.DS_PHYSICAL_ADDRESS = DS_PHYSICAL_ADDRESS;
        this.STATE = STATE;
        this.POSTAL_CODE = POSTAL_CODE;
        this.POSTAL_ADDRESS = POSTAL_ADDRESS;
        this.ACCOUNT_DESCRIPTION = ACCOUNT_DESCRIPTION;
        this.UNCLEARED_BAL = UNCLEARED_BAL;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public String getORGKEY() {
        return ORGKEY;
    }

    public void setORGKEY(String ORGKEY) {
        this.ORGKEY = ORGKEY;
    }

    public String getCUST_SHORT_NAME() {
        return CUST_SHORT_NAME;
    }

    public void setCUST_SHORT_NAME(String CUST_SHORT_NAME) {
        this.CUST_SHORT_NAME = CUST_SHORT_NAME;
    }

    public String getFORACID() {
        return FORACID;
    }

    public void setFORACID(String FORACID) {
        this.FORACID = FORACID;
    }

    public String getREF_NUM() {
        return REF_NUM;
    }

    public void setREF_NUM(String REF_NUM) {
        this.REF_NUM = REF_NUM;
    }

    public String getTRAN_RMKS() {
        return TRAN_RMKS;
    }

    public void setTRAN_RMKS(String TRAN_RMKS) {
        this.TRAN_RMKS = TRAN_RMKS;
    }

    public String getSYS_PART_TRAN_CODE() {
        return SYS_PART_TRAN_CODE;
    }

    public void setSYS_PART_TRAN_CODE(String SYS_PART_TRAN_CODE) {
        this.SYS_PART_TRAN_CODE = SYS_PART_TRAN_CODE;
    }

    public String getTRAN_CRNCY_CODE() {
        return TRAN_CRNCY_CODE;
    }

    public void setTRAN_CRNCY_CODE(String TRAN_CRNCY_CODE) {
        this.TRAN_CRNCY_CODE = TRAN_CRNCY_CODE;
    }

    public String getSOL_ID() {
        return SOL_ID;
    }

    public void setSOL_ID(String SOL_ID) {
        this.SOL_ID = SOL_ID;
    }

    public Double getAMOUNTDEBIT() {
        return AMOUNTDEBIT;
    }

    public void setAMOUNTDEBIT(Double AMOUNTDEBIT) {
        this.AMOUNTDEBIT = AMOUNTDEBIT;
    }

    public Double getAMOUNTCREDIT() {
        return AMOUNTCREDIT;
    }

    public void setAMOUNTCREDIT(Double AMOUNTCREDIT) {
        this.AMOUNTCREDIT = AMOUNTCREDIT;
    }

    public String getTRAN_AMT() {
        return TRAN_AMT;
    }

    public void setTRAN_AMT(String TRAN_AMT) {
        this.TRAN_AMT = TRAN_AMT;
    }

    public String getVALUE_DATE() {
        return VALUE_DATE;
    }

    public void setVALUE_DATE(String VALUE_DATE) {
        this.VALUE_DATE = VALUE_DATE;
    }

    public String getTRAN_DATE() {
        return TRAN_DATE;
    }

    public void setTRAN_DATE(String TRAN_DATE) {
        this.TRAN_DATE = TRAN_DATE;
    }

    public String getPSTD_DATE() {
        return PSTD_DATE;
    }

    public void setPSTD_DATE(String PSTD_DATE) {
        this.PSTD_DATE = PSTD_DATE;
    }

    public String getAVAILABLE_AMT() {
        return AVAILABLE_AMT;
    }

    public void setAVAILABLE_AMT(String AVAILABLE_AMT) {
        this.AVAILABLE_AMT = AVAILABLE_AMT;
    }

    public String getACCT_BALANCE() {
        return ACCT_BALANCE;
    }

    public void setACCT_BALANCE(String ACCT_BALANCE) {
        this.ACCT_BALANCE = ACCT_BALANCE;
    }

    public String getSVS_TRAN_ID() {
        return SVS_TRAN_ID;
    }

    public void setSVS_TRAN_ID(String SVS_TRAN_ID) {
        this.SVS_TRAN_ID = SVS_TRAN_ID;
    }

    public String getREF_AMT() {
        return REF_AMT;
    }

    public void setREF_AMT(String REF_AMT) {
        this.REF_AMT = REF_AMT;
    }

    public String getPSTD_USER_ID() {
        return PSTD_USER_ID;
    }

    public void setPSTD_USER_ID(String PSTD_USER_ID) {
        this.PSTD_USER_ID = PSTD_USER_ID;
    }

    public String getTRAN_TYPE() {
        return TRAN_TYPE;
    }

    public void setTRAN_TYPE(String TRAN_TYPE) {
        this.TRAN_TYPE = TRAN_TYPE;
    }

    public String getPART_TRAN_SRL_NUM() {
        return PART_TRAN_SRL_NUM;
    }

    public void setPART_TRAN_SRL_NUM(String PART_TRAN_SRL_NUM) {
        this.PART_TRAN_SRL_NUM = PART_TRAN_SRL_NUM;
    }

    public String getREVERSAL_STATUS() {
        return REVERSAL_STATUS;
    }

    public void setREVERSAL_STATUS(String REVERSAL_STATUS) {
        this.REVERSAL_STATUS = REVERSAL_STATUS;
    }

    public String getVOUCHER_PRINT_FLG() {
        return VOUCHER_PRINT_FLG;
    }

    public void setVOUCHER_PRINT_FLG(String VOUCHER_PRINT_FLG) {
        this.VOUCHER_PRINT_FLG = VOUCHER_PRINT_FLG;
    }

    public String getSERIAL_NUM() {
        return SERIAL_NUM;
    }

    public void setSERIAL_NUM(String SERIAL_NUM) {
        this.SERIAL_NUM = SERIAL_NUM;
    }

    public String getTRAN_ID() {
        return TRAN_ID;
    }

    public void setTRAN_ID(String TRAN_ID) {
        this.TRAN_ID = TRAN_ID;
    }

    public String getENTRY_USER_ID() {
        return ENTRY_USER_ID;
    }

    public void setENTRY_USER_ID(String ENTRY_USER_ID) {
        this.ENTRY_USER_ID = ENTRY_USER_ID;
    }

    public String getPART_TRAN_TYPE() {
        return PART_TRAN_TYPE;
    }

    public void setPART_TRAN_TYPE(String PART_TRAN_TYPE) {
        this.PART_TRAN_TYPE = PART_TRAN_TYPE;
    }

    public String getVFD_USER_ID() {
        return VFD_USER_ID;
    }

    public void setVFD_USER_ID(String VFD_USER_ID) {
        this.VFD_USER_ID = VFD_USER_ID;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getORIGINALAMOUNT() {
        return ORIGINALAMOUNT;
    }

    public void setORIGINALAMOUNT(String ORIGINALAMOUNT) {
        this.ORIGINALAMOUNT = ORIGINALAMOUNT;
    }

    public String getCHANNEL() {
        return CHANNEL;
    }

    public void setCHANNEL(String CHANNEL) {
        this.CHANNEL = CHANNEL;
    }

    public String getDS_PHYSICAL_ADDRESS() {
        return DS_PHYSICAL_ADDRESS;
    }

    public void setDS_PHYSICAL_ADDRESS(String DS_PHYSICAL_ADDRESS) {
        this.DS_PHYSICAL_ADDRESS = DS_PHYSICAL_ADDRESS;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getPOSTAL_CODE() {
        return POSTAL_CODE;
    }

    public void setPOSTAL_CODE(String POSTAL_CODE) {
        this.POSTAL_CODE = POSTAL_CODE;
    }

    public String getPOSTAL_ADDRESS() {
        return POSTAL_ADDRESS;
    }

    public void setPOSTAL_ADDRESS(String POSTAL_ADDRESS) {
        this.POSTAL_ADDRESS = POSTAL_ADDRESS;
    }

    public String getACCOUNT_DESCRIPTION() {
        return ACCOUNT_DESCRIPTION;
    }

    public void setACCOUNT_DESCRIPTION(String ACCOUNT_DESCRIPTION) {
        this.ACCOUNT_DESCRIPTION = ACCOUNT_DESCRIPTION;
    }

    public Double getUNCLEARED_BAL() {
        return UNCLEARED_BAL;
    }

    public void setUNCLEARED_BAL(Double UNCLEARED_BAL) {
        this.UNCLEARED_BAL = UNCLEARED_BAL;
    }
}
