package com.beshara.csc.hr.emp.business.entity.inf;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INF_GENDER_COUNTRY")

public class EmpGenderCountryEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "GENTYPE_CODE", nullable = false, insertable = true, 
            updatable = true)
    private Long gentypeCode;
    @Id
    @Column(name = "CNTRY_CODE", nullable = false, insertable = true, 
            updatable = true)
    private Long cntryCode;
    @Column(name = "GENCNTRY_NAME", nullable = false)
    private String gencntryName;

//    @ManyToOne
//    @JoinColumn(name = "CNTRY_CODE", referencedColumnName = "CNTRY_CODE")
//    private CountriesEntity countriesEntity;
//    @ManyToOne
//    @JoinColumn(name = "GENTYPE_CODE", referencedColumnName = "GENTYPE_CODE")
//    private GenderTypesEntity genderTypesEntity;


    public EmpGenderCountryEntity() {
    }

    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }

    public Long getGentypeCode() {
        return gentypeCode;
    }

    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
    }

    public Long getCntryCode() {
        return cntryCode;
    }

    public void setGencntryName(String gencntryName) {
        this.gencntryName = gencntryName;
    }

    public String getGencntryName() {
        return gencntryName;
    }
}
