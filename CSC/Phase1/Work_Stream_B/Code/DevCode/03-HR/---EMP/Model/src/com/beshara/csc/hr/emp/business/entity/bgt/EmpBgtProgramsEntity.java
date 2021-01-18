package com.beshara.csc.hr.emp.business.entity.bgt;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "HR_BGT_PROGRAMS")

public class EmpBgtProgramsEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "PRG_CODE", nullable = false)
    private String prgCode;
    @Column(name = "PRG_NAME", nullable = false)
    private String prgName;

    public EmpBgtProgramsEntity() {
    }

    public String getPrgCode() {
        return prgCode;
    }

    public void setPrgCode(String prgCode) {
        this.prgCode = prgCode;
    }

    public String getPrgName() {
        return prgName;
    }

    public void setPrgName(String prgName) {
        this.prgName = prgName;
    }
}
