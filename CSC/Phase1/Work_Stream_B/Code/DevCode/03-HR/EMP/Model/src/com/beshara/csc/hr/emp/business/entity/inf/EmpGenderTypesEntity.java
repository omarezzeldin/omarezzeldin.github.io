package com.beshara.csc.hr.emp.business.entity.inf;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "INF_GENDER_TYPES")
public class EmpGenderTypesEntity implements Serializable {
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    public EmpGenderTypesEntity() {
    }

    @Id
    @Column(name = "GENTYPE_CODE", nullable = false)
    private Long gentypeCode;
    @Column(name = "GENTYPE_NAME")
    private String gentypeName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }

    public Long getGentypeCode() {
        return gentypeCode;
    }

    public void setGentypeName(String gentypeName) {
        this.gentypeName = gentypeName;
    }

    public String getGentypeName() {
        return gentypeName;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
