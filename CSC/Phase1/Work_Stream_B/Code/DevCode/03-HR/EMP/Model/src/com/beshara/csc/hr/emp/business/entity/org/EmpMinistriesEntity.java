package com.beshara.csc.hr.emp.business.entity.org;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table(name = "NL_ORG_MINISTRIES")
public class EmpMinistriesEntity implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MIN_CODE", nullable = false)
    private Long minCode;
    @Column(name = "MIN_NAME", nullable = false)
    private String minName;

    public EmpMinistriesEntity() {
    }

    @ManyToOne
    @JoinColumn(name = "CAT_CODE", referencedColumnName = "CAT_CODE", insertable = false, updatable = false)
    private EmpCatsEntity catsEntity;


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
     * @return minName
     */
    public String getMinName() {
        return minName;
    }

    /**
     *
     * @param minName
     */
    public void setMinName(String minName) {
        this.minName = minName;
    }


    public void setCatsEntity(EmpCatsEntity catsEntity) {
        this.catsEntity = catsEntity;
    }

    public EmpCatsEntity getCatsEntity() {
        return catsEntity;
    }
}
