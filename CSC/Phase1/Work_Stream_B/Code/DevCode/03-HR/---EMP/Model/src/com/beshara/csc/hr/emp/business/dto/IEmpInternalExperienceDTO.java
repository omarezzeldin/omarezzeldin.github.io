package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Fitiany    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
public interface IEmpInternalExperienceDTO extends IEmpDTO {

    public void setActionDate(Timestamp actionDate);

    public Timestamp getActionDate();

    public void setPisJobCode(String pisJobCode);

    public String getPisJobCode();

    public void setPisWrkCode(String pisWrkCode);

    public String getPisWrkCode();

    public void setJobDetail(String jobDetail);

    public String getJobDetail();

    public void setRevFlag(Long revFlag);

    public Long getRevFlag();

    public void setRecordSourceFlag(Long recordSourceFlag);

    public Long getRecordSourceFlag();

    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setJobsDTO(IBasicDTO jobsDTO);

    public IBasicDTO getJobsDTO();

    public void setWorkCenterDTO(IBasicDTO workCenterDTO);

    public IBasicDTO getWorkCenterDTO();

    public void setBgtTypesDTO(IBasicDTO bgtTypesDTO);

    public IBasicDTO getBgtTypesDTO();

    public void setBgtProgramsDTO(IBasicDTO bgtProgramsDTO);

    public IBasicDTO getBgtProgramsDTO();

    public void setTrxTypesDTO(IBasicDTO trxTypesDTO);

    public IBasicDTO getTrxTypesDTO();

    public void setSalElementGuidesDTO(IBasicDTO salElementGuidesDTO);

    public IBasicDTO getSalElementGuidesDTO();

    public void setRevFlagBoolean(boolean revFlagBoolean);

    public boolean isRevFlagBoolean();

    public void setRecordSourcePis(boolean recordSourcePis);

    public boolean isRecordSourcePis();

    public void setRecordSourceLegacy(boolean recordSourceLegacy);

    public boolean isRecordSourceLegacy();

    public void setRecordSourceCsc(boolean recordSourceCsc);

    public boolean isRecordSourceCsc();
    
    public void setMinDTO(IBasicDTO minDTO);

    public IBasicDTO getMinDTO();
    
    //=================Start CSC-18251========================//
    public void setAoeAuditBy(String aoeAuditBy);
    public String getAoeAuditBy();
    public void setAoeAuditDate(Date aoeAuditDate);
    public Date getAoeAuditDate();
    public void setAoeAuditFlag(Long aoeAuditFlag);
    public Long getAoeAuditFlag();
  
    
    public void setAoeApprovedBy(String aoeApprovedBy);
    public String getAoeApprovedBy();
    public void setAoeApprovedDate(Date aoeApprovedDate);
    public Date getAoeApprovedDate();
    public void setAoeApprovedFlag(Long aoeApprovedFlag);
    public Long getAoeApprovedFlag();
    //=================End CSC-18251========================//
    public void setAoeAuditBy_name(String aoeAuditBy_name) ;
    public String getAoeAuditBy_name() ;
    public void setAoeApprovedBy_name(String aoeApprovedBy_name);
    public String getAoeApprovedBy_name() ;
    
}

