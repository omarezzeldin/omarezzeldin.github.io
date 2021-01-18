package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.ClientDTO;

import java.sql.Date;

public class EmpStatusForSalDTO  extends ClientDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

        private String empStatus;
        private Date empqurDate;

    public EmpStatusForSalDTO() {
    }


    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpqurDate(Date empqurDate) {
        this.empqurDate = empqurDate;
    }

    public Date getEmpqurDate() {
        return empqurDate;
    }
}
