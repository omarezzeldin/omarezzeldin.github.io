package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.entity.HireProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.HireProceduresEntityKey;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;

import java.sql.Date;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class HireProceduresDTO extends EmpDTO implements IHireProceduresDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    // private Long hirprocedureCode ;
    //private String hirprocedureName ;
    //private Long minCode ;
    private Long genderType;
    private IMinistriesDTO ministriesDTO;
    private IGenderTypesDTO genderTypesDTO;
    //    private Long nationalityType;
    private Long auditStatus;
    private Long tabrecSerial;

    private Date fromDate;
    private Date untilDate;
    private Long status;

    /**
     * HireProceduresDTO Default Constructor */
    public HireProceduresDTO() {
        super();
    }

    public HireProceduresDTO(Long code, String name) {
        this.setCode(new HireProceduresEntityKey(code));
        this.setName(name);
    }

    /**
     * @param hireProceduresEntity
     */
    public HireProceduresDTO(HireProceduresEntity hireProceduresEntity) { // this.hirprocedureCode = hireProceduresEntity.getHirprocedureCode ( ) ;
        //this.hirprocedureName = hireProceduresEntity.getHirprocedureName ( ) ;
        this.setCode(new HireProceduresEntityKey(hireProceduresEntity.getHirprocedureCode()));
        this.setName(hireProceduresEntity.getHirprocedureName());
        //if (hireProceduresEntity.getMinistriesEntity() != null) {
        if (hireProceduresEntity.getMinCode() != null) {
            try {
                // this.setMinistriesDTO(OrgDTOFactory.createMinistriesDTO(hireProceduresEntity.getMinistriesEntity()));
                IMinistriesEntityKey mEk =
                    OrgEntityKeyFactory.createMinistriesEntityKey(hireProceduresEntity.getMinCode());
                this.setMinistriesDTO((IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(mEk));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        //        if (hireProceduresEntity.getGenderTypesEntity() != null) {
        //            this.setGenderTypesDTO(InfDTOFactory.createGenderTypesDTO(hireProceduresEntity.getGenderTypesEntity()));
        //        }
        //        this.genderType = hireProceduresEntity.getGenderType();
        //        this.nationalityType = hireProceduresEntity.getNationalityType();
        this.setCreatedBy(hireProceduresEntity.getCreatedBy());
        this.setCreatedDate(hireProceduresEntity.getCreatedDate());
        this.setLastUpdatedBy(hireProceduresEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(hireProceduresEntity.getLastUpdatedDate());
        this.auditStatus = hireProceduresEntity.getAuditStatus();
        this.tabrecSerial = hireProceduresEntity.getTabrecSerial();
        this.setFromDate(hireProceduresEntity.getFromDate());
        this.setUntilDate(hireProceduresEntity.getUntilDate());
        this.setStatus(hireProceduresEntity.getStatus());
    } ///**
    //* @return Long
    //*/
    //public Long getHirprocedureCode ( ) {
    // return hirprocedureCode ;
    // }
    ///**
    //* @return String
    //*/
    //public String getHirprocedureName ( ) {
    // return hirprocedureName ;
    // }
    /**
 * @return Long
 */
    //public Long getMinCode ( ) {
    // return minCode ;
    // }

    /**
     * @return Long
     */
    public Long getGenderType() {
        return genderType;
    }

    //    /**
    //     * @return Long
    //     */
    //    public Long getNationalityType() {
    //        return nationalityType;
    //    }

    /**
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    } ///**
    //* @param hirprocedureCode
    //*/
    //public void setHirprocedureCode ( Long hirprocedureCode ) {
    // this.hirprocedureCode=hirprocedureCode ;
    // }
    ///**
    //* @param hirprocedureName
    //*/
    //public void setHirprocedureName ( String hirprocedureName ) {
    // this.hirprocedureName=hirprocedureName ;
    // }
    ///**
    //* @param minCode
    //*/
    //public void setMinCode ( Long minCode ) {
    // this.minCode=minCode ;
    // }

    /**
     * @param genderType
     */
    public void setGenderType(Long genderType) {
        this.genderType = genderType;
    }

    //    /**
    //     * @param nationalityType
    //     */
    //    public void setNationalityType(Long nationalityType) {
    //        this.nationalityType = nationalityType;
    //    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO) {
        this.ministriesDTO = ministriesDTO;
    }

    public IMinistriesDTO getMinistriesDTO() {
        return ministriesDTO;
    }

    //    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
    //        this.genderTypesDTO = genderTypesDTO;
    //    }
    //
    //    public IGenderTypesDTO getGenderTypesDTO() {
    //        return genderTypesDTO;
    //    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }
}
