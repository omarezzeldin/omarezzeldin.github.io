package com.beshara.csc.hr.emp.business.entity.inf;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table(name = "INF_KWT_CITIZENS_RESIDENTS")

public class EmpKwtCitizensResidentsEntity implements Serializable {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Column(name = "BIRTH_DATE")
    private Timestamp birthDate;


    @Column(name = "CAPSTATUS_CODE", nullable = false, insertable = false, updatable = false)
    private Long capstatusCode;


    @Id
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;

    @Column(name = "NATIONALITY", nullable = false)
    private Long nationality;

    @Column(name = "END_RESIDENT_DATE")
    private Timestamp endResidentDate;
    @Column(name = "ENGLISH_NAME")
    private String englishName;

    @Column(name = "FAMILY_NAME")
    private String familyName;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "GENTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long gentypeCode;

    @ManyToOne
    @JoinColumn(name = "GENTYPE_CODE", referencedColumnName = "GENTYPE_CODE")
    private EmpGenderTypesEntity genderTypesEntity;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "MAP_CODE", insertable = false, updatable = false)
    private String mapCode;


    @Column(name = "MLTSTATUS_CODE")
    private Long mltstatusCode;

    @Column(name = "MARSTATUS_CODE", nullable = false, insertable = false, updatable = false)
    private Long marstatusCode;
    @ManyToOne
    @JoinColumn(name = "MARSTATUS_CODE", referencedColumnName = "MARSTATUS_CODE")
    private EmpMaritalStatusEntity maritalStatusEntity;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "PASSPORT_NO")
    private String passportNo;
    @Column(name = "PHONES_NO")
    private String phonesNo;

    @Column(name = "RELIGION_CODE", nullable = false, insertable = false, updatable = false)
    private Long religionCode;

    @Column(name = "RESTYPE_CODE", nullable = true, insertable = false, updatable = false)
    private Long restypeCode;

    @Column(name = "SECOND_NAME", nullable = false)
    private String secondName;
    @Column(name = "SPCCSETYPE_CODE", insertable = false, updatable = false)
    private Long spccsetypeCode;

    @Column(name = "STREET_CODE")
    private Long streetCode;

    @Column(name = "THIRD_NAME", nullable = false)
    private String thirdName;

    //    @OneToMany(mappedBy = "empKwtCitizensResidentsEntity")
    //    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID", insertable = false, updatable = false)
    //    private List<EmpAoeAwardRequestsEntity> empAoeAwardRequestsEntityList;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "GENTYPE_CODE", 
                                referencedColumnName = "GENTYPE_CODE", 
                                insertable = false, updatable = false)
            , 
            @JoinColumn(name = "NATIONALITY", referencedColumnName = "CNTRY_CODE", 
                        insertable = false, updatable = false)
            } )
    private EmpGenderCountryEntity countriesEntity;

    public EmpKwtCitizensResidentsEntity() {
    }


    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }


    public Long getCapstatusCode() {
        return capstatusCode;
    }

    public void setCapstatusCode(Long capstatusCode) {
        this.capstatusCode = capstatusCode;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }


    public Timestamp getEndResidentDate() {
        return endResidentDate;
    }

    public void setEndResidentDate(Timestamp endResidentDate) {
        this.endResidentDate = endResidentDate;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }


    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public Long getGentypeCode() {
        return gentypeCode;
    }

    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }


    public Long getMltstatusCode() {
        return mltstatusCode;
    }

    public void setMltstatusCode(Long mltstatusCode) {
        this.mltstatusCode = mltstatusCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPhonesNo() {
        return phonesNo;
    }

    public void setPhonesNo(String phonesNo) {
        this.phonesNo = phonesNo;
    }

    public Long getReligionCode() {
        return religionCode;
    }

    public void setReligionCode(Long religionCode) {
        this.religionCode = religionCode;
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Long getSpccsetypeCode() {
        return spccsetypeCode;
    }

    public void setSpccsetypeCode(Long spccsetypeCode) {
        this.spccsetypeCode = spccsetypeCode;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }


    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    public Long getNationality() {
        return nationality;
    }

    //    public void setEmpAoeAwardRequestsEntityList(List<EmpAoeAwardRequestsEntity> empAoeAwardRequestsEntityList) {
    //        this.empAoeAwardRequestsEntityList = empAoeAwardRequestsEntityList;
    //    }
    //
    //    public List<EmpAoeAwardRequestsEntity> getEmpAoeAwardRequestsEntityList() {
    //        return empAoeAwardRequestsEntityList;
    //    }

    public void setMarstatusCode(Long marstatusCode) {
        this.marstatusCode = marstatusCode;
    }

    public Long getMarstatusCode() {
        return marstatusCode;
    }

    public void setMaritalStatusEntity(EmpMaritalStatusEntity maritalStatusEntity) {
        this.maritalStatusEntity = maritalStatusEntity;
    }

    public EmpMaritalStatusEntity getMaritalStatusEntity() {
        return maritalStatusEntity;
    }

    public void setGenderTypesEntity(EmpGenderTypesEntity genderTypesEntity) {
        this.genderTypesEntity = genderTypesEntity;
    }

    public EmpGenderTypesEntity getGenderTypesEntity() {
        return genderTypesEntity;
    }

    public void setCountriesEntity(EmpGenderCountryEntity countriesEntity) {
        this.countriesEntity = countriesEntity;
    }

    public EmpGenderCountryEntity getCountriesEntity() {
        return countriesEntity;
    }
}
