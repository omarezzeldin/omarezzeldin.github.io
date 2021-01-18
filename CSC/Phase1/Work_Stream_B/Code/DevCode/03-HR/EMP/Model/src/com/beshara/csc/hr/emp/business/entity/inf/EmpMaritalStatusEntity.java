package com.beshara.csc.hr.emp.business.entity.inf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "INF_MARITAL_STATUS")

public class EmpMaritalStatusEntity implements Serializable {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MARSTATUS_CODE", nullable = false)
    private Long marstatusCode;
    @Column(name = "MARSTATUS_NAME")
    private String marStatusName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    public EmpMaritalStatusEntity() {
    }


    public void setMarstatusCode(Long marstatusCode) {
        this.marstatusCode = marstatusCode;
    }

    public Long getMarstatusCode() {
        return marstatusCode;
    }

    public void setMarStatusName(String marStatusName) {
        this.marStatusName = marStatusName;
    }

    public String getMarStatusName() {
        return marStatusName;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
