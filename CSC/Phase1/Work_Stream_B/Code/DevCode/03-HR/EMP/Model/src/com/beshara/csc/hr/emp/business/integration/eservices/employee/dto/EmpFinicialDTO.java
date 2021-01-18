package com.beshara.csc.hr.emp.business.integration.eservices.employee.dto;

import java.io.Serializable;

import java.math.BigDecimal;

public class EmpFinicialDTO implements Serializable {
    
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    private Long realCivilID;
    private String accountCheckNo;
    private BigDecimal actualSalary;
    private Long bankCode;
    private String bankName;
    private Long bankBranchCode;
    private String bankBranchName;
    private BigDecimal totalMertis;
    private BigDecimal totalDeducts;
    private Long salYear;
    private Long salMonth;
    private Long minCode;
    public EmpFinicialDTO() {
        super();
    }

    public void setRealCivilID(Long realCivilID) {
        this.realCivilID = realCivilID;
    }

    public Long getRealCivilID() {
        return realCivilID;
    }

    public void setAccountCheckNo(String accountCheckNo) {
        this.accountCheckNo = accountCheckNo;
    }

    public String getAccountCheckNo() {
        return accountCheckNo;
    }

    public void setActualSalary(BigDecimal actualSalary) {
        this.actualSalary = actualSalary;
    }

    public BigDecimal getActualSalary() {
        return actualSalary;
    }

    public void setBankCode(Long bankCode) {
        this.bankCode = bankCode;
    }

    public Long getBankCode() {
        return bankCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankBranchCode(Long bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
    }

    public Long getBankBranchCode() {
        return bankBranchCode;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setTotalMertis(BigDecimal totalMertis) {
        this.totalMertis = totalMertis;
    }

    public BigDecimal getTotalMertis() {
        return totalMertis;
    }

    public void setTotalDeducts(BigDecimal totalDeducts) {
        this.totalDeducts = totalDeducts;
    }

    public BigDecimal getTotalDeducts() {
        return totalDeducts;
    }

    public void setSalYear(Long salYear) {
        this.salYear = salYear;
    }

    public Long getSalYear() {
        return salYear;
    }

    public void setSalMonth(Long salMonth) {
        this.salMonth = salMonth;
    }

    public Long getSalMonth() {
        return salMonth;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }
}
