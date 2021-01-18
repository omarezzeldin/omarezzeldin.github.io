package com.beshara.csc.hr.emp.business.dto;

import java.math.BigDecimal;

public interface IEmpFinicialData {
    
    public void setAccountCheckNo(String accountCheckNo);

    public String getAccountCheckNo();

    public void setRealCivilID(Long realCivilID) ;
    public Long getRealCivilID();
    public void setActualSalary( BigDecimal actualSalary) ;
    public BigDecimal getActualSalary() ;
    public void setBankCode(Long bankCode);
    public Long getBankCode() ;
    public void setBankName(String bankName);
    public String getBankName() ;
    public void setBankBranchCode(Long bankBranchCode);
    public Long getBankBranchCode();

    public void setBankBranchName(String bankBranchName) ;

    public String getBankBranchName() ;

    public void setTotalMertis(BigDecimal totalMertis) ;

    public BigDecimal getTotalMertis();

    public void setTotalDeducts(BigDecimal totalDeducts) ;
    public BigDecimal getTotalDeducts();

    public void setSalYear(Long salYear);

    public Long getSalYear() ;

    public void setSalMonth(Long salMonth) ;

    public Long getSalMonth();

    public void setMinCode(Long minCode);

    public Long getMinCode();
}
