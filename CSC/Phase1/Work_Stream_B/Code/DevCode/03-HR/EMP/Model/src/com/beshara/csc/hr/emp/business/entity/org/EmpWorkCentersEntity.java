package com.beshara.csc.hr.emp.business.entity.org;


import com.beshara.csc.hr.emp.business.entity.job.EmpJobsEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "NL_ORG_WORK_CENTERS")

public class EmpWorkCentersEntity implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MIN_CODE", nullable = false, updatable = false)
    private Long minCode;

    @ManyToOne
    @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE", insertable = false, updatable = false)
    private EmpMinistriesEntity ministriesEntity;
    

    @Id
    @Column(name = "WRK_CODE", nullable = false)
    private String wrkCode;
    @Column(name = "WRK_NAME", nullable = false)
    private String wrkName;

    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    @Column(name = "PARENT_WRK", nullable = false, insertable = false, updatable = false)
    private String parentWrkCode;
    @Column(name = "PARENT_MIN", nullable = false, insertable = false, updatable = false)
    private Long parentMinCode;
    ////////////////////////////////////////////new column
    @Column(name = "CENTER_STATUS", nullable = true)
    private Long centerStatus;
    ////////////////////////////////////////////new column
    @Column(name = "STATUS")
    private Long status;


    @Column(name = "WRKLEVEL_SERIAL")
    private String wrkLevelSerial;


    public EmpWorkCentersEntity() {
    }


    /**
     *
     * @return minCode
     */

    public Long getMinCode() {
        return minCode;
    }

    /**
     *
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }


    /**
     *
     * @return wrkCode
     */

    public String getWrkCode() {
        return wrkCode;
    }

    /**
     *
     * @param wrkCode
     */
    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    /**
     *
     * @return wrkName
     */
    public String getWrkName() {
        return wrkName;
    }

    /**
     *
     * @param wrkName
     */
    public void setWrkName(String wrkName) {
        this.wrkName = wrkName;
    }


    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setParentWrkCode(String parentWrkCode) {
        this.parentWrkCode = parentWrkCode;
    }

    public String getParentWrkCode() {
        return parentWrkCode;
    }

    public void setParentMinCode(Long parentMinCode) {
        this.parentMinCode = parentMinCode;
    }

    public Long getParentMinCode() {
        return parentMinCode;
    }

    public void setCenterStatus(Long centerStatus) {
        this.centerStatus = centerStatus;
    }

    public Long getCenterStatus() {
        return centerStatus;
    }


    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }


    public void setWrkLevelSerial(String wrkLevelSerial) {
        this.wrkLevelSerial = wrkLevelSerial;
    }

    public String getWrkLevelSerial() {
        return wrkLevelSerial;
    }

    public void setMinistriesEntity(EmpMinistriesEntity ministriesEntity) {
        this.ministriesEntity = ministriesEntity;
    }

    public EmpMinistriesEntity getMinistriesEntity() {
        return ministriesEntity;
    }
}
