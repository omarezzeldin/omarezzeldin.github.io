package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;

import java.sql.Date;

import java.util.List;


public interface IEmployeesDTO extends IEmpDTO {

    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();

    public Long getMinCode();

    public void setMinCode(Long minCode);

    public String getMinistryFileNo();

    public String getCscFileNo();

    public String getAccountNo();

    public Long getAuditStatus();

    public Long getActiveFlag();

    public Long getTabrecSerial();

    public void setMinistryFileNo(String ministryFileNo);

    public void setCscFileNo(String cscFileNo);

    public void setAccountNo(String accountNo);

    public void setAuditStatus(Long auditStatus);

    public void setActiveFlag(Long activeFlag);

    public void setTabrecSerial(Long tabrecSerial);

    public void setStartWorkingDate(Date startWorkingDate);

    public Date getStartWorkingDate();

    public void setEndJobDate(Date endJobDate);

    public Date getEndJobDate();

    public void setDateOfNextRaise(Date dateOfNextRaise);

    public Date getDateOfNextRaise();

    public void setHireDate(Date hireDate);

    public Date getHireDate();

    public void setHireStatusDTO(IBasicDTO hireStatusDTO);

    public IBasicDTO getHireStatusDTO();

    public void setHireStageDTO(IBasicDTO hireStageDTO);

    public IBasicDTO getHireStageDTO();

    public void setJobDTO(IBasicDTO jobDTO);

    public IBasicDTO getJobDTO();

    public void setWorkCenterDTO(IBasicDTO workCenterDTO);

    public IBasicDTO getWorkCenterDTO();

    public void setBankBrancheDTO(IBasicDTO bankBrancheDTO);

    public IBasicDTO getBankBrancheDTO();

    public void setHireTypeDTO(IBasicDTO hireTypeDTO);

    public IBasicDTO getHireTypeDTO();

    public void setTechJobsDTO(IBasicDTO techJobsDTO);

    public IBasicDTO getTechJobsDTO();

    public void setEmployeeDocumentsDTOList(List<IBasicDTO> employeeDocumentsDTOList);

    public List<IBasicDTO> getEmployeeDocumentsDTOList();

    public void setEmployeeProceduresDTOList(List<IBasicDTO> employeeProceduresDTOList);

    public List<IBasicDTO> getEmployeeProceduresDTOList();

    public void setCitizensResidentsDTO(IBasicDTO citizensResidentsDTO);

    public IBasicDTO getCitizensResidentsDTO();

    public void setDocumentsStatus(Boolean documentsStatus);

    public Boolean getDocumentsStatus();

    public void setProceduresStatus(Boolean proceduresStatus);

    public Boolean getProceduresStatus();

    public String getJobKey();

    public void setJobKey(String key);

    public String getTechJobKey();

    public void setTechJobKey(String key);

    public String getWorkCenterKey();

    public void setWorkCenterKey(String key);

    public String getHireStatusKey();

    public void setHireStatusKey(String key);

    public String getHireStageKey();

    public void setHireStageKey(String key);

    public String getHireTypeKey();

    public void setHireTypeKey(String key);

    public String getBankBranchKey();

    public void setBankBranchKey(String key);

    public void setBgtTypesDTO(IBgtTypesDTO bgtTypesDTO);

    public IBgtTypesDTO getBgtTypesDTO();

    public void setBgtProgramsDTO(IBgtProgramsDTO bgtProgramsDTO);

    public IBgtProgramsDTO getBgtProgramsDTO();

    public String getJobsKey();

    public void setJobsKey(String key);

    public String getBgtPrgmKey();

    public void setBgtPrgmKey(String key);

    public String getBgtTypeKey();

    public void setBgtTypeKey(String key);

    public void setSalEmpSalaryElementsDTOList(List<ISalEmpSalaryElementsDTO> salEmpSalaryElementsDTOList);

    public List<ISalEmpSalaryElementsDTO> getSalEmpSalaryElementsDTOList();

    public void setSocialInsurNo(String socialInsurNo);

    public String getSocialInsurNo();

    boolean isHasExperience();

    void setEmpExtraDataValueDTO(IEmpExtraDataValueDTO empExtraDataValueDTO);

    public IEmpExtraDataValueDTO getEmpExtraDataValueDTO();

    void setEmployeeExtraDataDTOList(List<IBasicDTO> employeeExtraDataDTOList);

    List<IBasicDTO> getEmployeeExtraDataDTOList();


    void setDisableIfFound(Boolean disableIfFound);

    Boolean getDisableIfFound();

    void setRecordDescCode(Long recordDescCode);

    Long getRecordDescCode();

    void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO);

    IPersonQualificationsDTO getPersonQualificationsDTO();

    void setPersonsInformationDTO(IPersonsInformationDTO personsInformationDTO);

    IPersonsInformationDTO getPersonsInformationDTO();

    void setSalEmpSalaryElementsDTO(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO);

    ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO();

    public void setSalEmpMonSalariesDTO(ISalEmpMonSalariesDTO salEmpMonSalariesDTO);

    public ISalEmpMonSalariesDTO getSalEmpMonSalariesDTO();

    public void setCandidateCode(Long candidateCode);

    public Long getCandidateCode();

    public void setCandidateCodeSeq(Long candidateCodeSeq);

    public Long getCandidateCodeSeq();

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO);

    public IEmpCandidatesDTO getEmpCandidatesDTO();

    public void setEmployeeName(String employeeName);

    public String getEmployeeName();

    void setNationality(Long nationality);

    public Long getNationality();

    public void setErrorMSG(String errorMSG);

    public String getErrorMSG();

    public void setCivilIds(String civilIds);

    public String getCivilIds();

    public void setUnValidStatus(boolean UnValidStatus);

    public boolean isUnValidStatus();

    public void setLastKwtWorkDataDTO(IKwtWorkDataDTO lastKwtWorkDataDTO);

    public IKwtWorkDataDTO getLastKwtWorkDataDTO();

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO);

    public IMinistriesDTO getMinistriesDTO();

    public void setNationalityValue(String nationalityValue);

    public String getNationalityValue();
    
    public void setFirstHireDate(Date firstHireDate);

    public Date getFirstHireDate();
    
    public void setEmpSignId(Long empSignId);

    public Long getEmpSignId();
    
}
