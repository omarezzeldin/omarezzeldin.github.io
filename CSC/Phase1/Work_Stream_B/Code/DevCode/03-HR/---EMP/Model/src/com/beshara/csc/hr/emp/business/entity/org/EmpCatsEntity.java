package com.beshara.csc.hr.emp.business.entity.org;

import com.beshara.base.entity.BasicEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity

@Table(name = "NL_ORG_CATS")
public class EmpCatsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CAT_CODE", nullable = false)
    private Long catCode;
    @Column(name = "CAT_NAME", nullable = false)
    private String catName;
    
    @Column(name = "ENGLISH_NAME", nullable = false)
    private String catEnName;

    @Column(name = "GOV_FLAG", nullable = false)
    private Long govFlag;
    @Column(nullable = false)
    private Long status;
   
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
//    @OneToMany(mappedBy = "catsEntity")
//    private List<MinistriesEntity> ministriesEntityList;

    public EmpCatsEntity() {
    }

    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public Long getCatCode() {
        return catCode;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatEnName(String catEnName) {
        this.catEnName = catEnName;
    }

    public String getCatEnName() {
        return catEnName;
    }

    public void setGovFlag(Long govFlag) {
        this.govFlag = govFlag;
    }

    public Long getGovFlag() {
        return govFlag;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
