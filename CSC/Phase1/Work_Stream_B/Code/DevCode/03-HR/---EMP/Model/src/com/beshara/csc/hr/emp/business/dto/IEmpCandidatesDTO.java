package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.reg.integration.business.joindec.IJoinDecTargetDTO;

import java.sql.Date;

import java.util.List;


public interface IEmpCandidatesDTO extends IEmpDTO, IJoinDecTargetDTO {


    public Long getCivilId();

    public void setCivilId(Long civilId);

    //        public Long getCandidateCodeSeq();
    //
    //        public void setCandidateCodeSeq(Long candidateCodeSeq);

    public Long getCndStatusCode();

    public void setCndStatusCode(Long cndStatusCode);

    public Long getHirtypeCode();

    public void setHirtypeCode(Long hirtypeCode);

    public Long getHirstageCode();

    public void setHirstageCode(Long hirstageCode);

    public void setWrkCode(String wrkCode);

    public Long getMinCode();

    public void setMinCode(Long minCode);

    public String getMinistryFileNo();

    public void setMinistryFileNo(String ministryFileNo);

    public String getCscFileNo();

    public void setCscFileNo(String cscFileNo);

    public void setHireDate(Date hireDate);

    public Date getHireDate();

    public void setStartWorkingDate(Date startWorkingDate);

    public Date getStartWorkingDate();

    public void setDateOfNextRaise(Date dateOfNextRaise);

    public Date getDateOfNextRaise();

    public void setTestPeriodDate(Date testPeriodDate);

    public Date getTestPeriodDate();

    public Long getAuditStatus();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);


    public Long getTabrecSerial();

    public void setSocialInsurNo(String socialInsurNo);

    public String getSocialInsurNo();

    public Long getActiveFlag();

    public void setActiveFlag(Long activeFlag);

    void setRecordDescCode(Long recordDescCode);

    public Long getRecordDescCode();


    public String getBgtprgCode();

    public void setBgtprgCode(String bgtprgCode);

    void setBgttypeCode(Long bgttypeCode);

    public Long getBgttypeCode();


    public String getJobCode();

    public void setJobCode(String jobCode);

    void setEmployeesDTOList(List<IBasicDTO> employeesDTOList);

    List<IBasicDTO> getEmployeesDTOList();

    void setEmpCndSalaryElementsList(List<IBasicDTO> empCndSalaryElementsList);

    List<IBasicDTO> getEmpCndSalaryElementsList();

    void setEmpCandidateExtraDataList(List<IBasicDTO> empCandidateExtraDataList);

    List<IBasicDTO> getEmpCandidateExtraDataList();

    void setEmpCandidateDocumentsList(List<IBasicDTO> empCandidateDocumentsList);

    List<IBasicDTO> getEmpCandidateDocumentsList();

    void setEmpCandidateProceduresList(List<IBasicDTO> empCandidateProceduresListv);

    List<IBasicDTO> getEmpCandidateProceduresList();

    public void setEmpCandidateStatusDTO(IBasicDTO empCandidateStatusDTO);

    public IBasicDTO getEmpCandidateStatusDTO();

    public void setBgtProgramsDTO(IBasicDTO bgtProgramsDTO);

    public IBasicDTO getBgtProgramsDTO();

    public void setBgtTypesDTO(IBasicDTO bgtTypesDTO);

    public IBasicDTO getBgtTypesDTO();

    public void setCitizensResidentsDTO(IBasicDTO citizensResidentsDTO);

    public IBasicDTO getCitizensResidentsDTO();

    public void setCandidatePersonsDTO(IBasicDTO candidatePersonsDTO);

    public IBasicDTO getCandidatePersonsDTO();

    public void setUsersDTOApproved(IBasicDTO usersDTOApprovedBy);

    public IBasicDTO getUsersDTOApprovedDTO();

    public void setUsersDTOAuditBy(IBasicDTO usersDTOAuditBy);

    public IBasicDTO getUsersDTOAuditBy();

    public void setHireStagesDTO(IBasicDTO hireStagesDTO);

    public IBasicDTO getHireStagesDTO();

    public void setHireTypesDTO(IBasicDTO hireStagesDTO);

    public IBasicDTO getHireTypesDTO();

    public void setJobsDTOOtherJob(IBasicDTO jobsDTOOtherJob);

    public IBasicDTO getJobsDTOOtherJob();

    public void setJobsDTO(IBasicDTO jobsDTO);

    public IBasicDTO getJobsDTO();

    public void setWorkCentersDTO(IBasicDTO workCentersDTO);

    public IBasicDTO getWorkCentersDTO();

    public void setApprovedByUser(Long approvedByUser);

    public Long getApprovedByUser();

    public void setApprovedDate(Date approvedDate);

    public Date getApprovedDate();

    public void setAuditByUser(Long auditByUser);

    public Long getAuditByUser();

    public void setTransferToEmpFlag(Long transferToEmpFlag);

    public Long getTransferToEmpFlag();

    public String getLockEntity();

    public String getLockId();

    public void setHasExperience(boolean hasExperience);

    public boolean isHasExperience();

    public String getHireStageKey();

    public void setHireStageKey(String key);

    public String getHireTypeKey();

    public void setHireTypeKey(String key);

    public String getHireStatusKey();

    public void setHireStatusKey(String key);

    public void setBgtTypeKey(String key);

    public String getBgtTypeKey();

    public void setDocumentsStatus(Boolean documentsStatus);

    public Boolean getDocumentsStatus();

    public void setProceduresStatus(Boolean proceduresStatus);

    public Boolean getProceduresStatus();

    public void setEmpExtraDataValueDTO(IEmpExtraDataValueDTO empExtraDataValueDTO);

    public IEmpExtraDataValueDTO getEmpExtraDataValueDTO();

    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO);

    public IPersonQualificationsDTO getPersonQualificationsDTO();

    public void setPersonsInformationDTO(IPersonsInformationDTO personsInformationDTO);

    public IPersonsInformationDTO getPersonsInformationDTO();

    public String getBgtPrgmKey();

    public void setBgtPrgmKey(String key);

    public String getWorkCenterKey();

    public void setWorkCenterKey(String key);

    public void setAcceptedRaiseCode(Long acceptedRaiseCode);

    public Long getAcceptedRaiseCode();

    public void setPersonsInformationDTOList(List<IPersonsInformationDTO> personsInformationDTOList);

    public List<IPersonsInformationDTO> getPersonsInformationDTOList();

    public void setKwtWorkDataTreeDTOList(List<IKwtWorkDataTreeDTO> kwtWorkDataTreeDTOList);

    public List<IKwtWorkDataTreeDTO> getKwtWorkDataTreeDTOList();

    public void setCitizensResidentsList(List<IKwtCitizensResidentsDTO> citizensResidentsList);

    public List<IKwtCitizensResidentsDTO> getCitizensResidentsList();
    
    public void setHasRevision(boolean hasRevision);

    public boolean isHasRevision() ;
    
    public void setMinExcepted(boolean minExcepted);

    public boolean isMinExcepted();
    
    public void setExtraDataValue(String extraDataValue);

    public String getExtraDataValue() ;
    
    public void setHasRevisionFatwa(boolean hasRevisionFatwa);

    public boolean isHasRevisionFatwa();
    
    public boolean getHasRevision();
    
    public boolean getHasRevisionFatwa() ;
    
    public void setUserNameValue(String userNameValue);

    public String getUserNameValue() ;
    
    public void setUserNameValueFatwa(String userNameValueFatwa);

    public String getUserNameValueFatwa();
    
    public void setTransReqDate(Date transReqDate);

    public Date getTransReqDate();
    
    public void setDeletedPersonsInformationDTOList(List<IPersonsInformationDTO> deletedPersonsInformationDTOList);

    public List<IPersonsInformationDTO> getDeletedPersonsInformationDTOList() ;
    
    public void setWitoutQualFlag(boolean witoutQualFlag);

    public boolean isWitoutQualFlag();
    
    public void setPreviousEmpExtraDataValueDTO(IEmpExtraDataValueDTO previousEmpExtraDataValueDTO);

    public IEmpExtraDataValueDTO getPreviousEmpExtraDataValueDTO();
    
    public void setPreviousEmpCandidateExtraDataList(List<IBasicDTO> previousEmpCandidateExtraDataList);

    public List<IBasicDTO> getPreviousEmpCandidateExtraDataList();
    
    public void setApprovedJobsDTO(IBasicDTO approvedJobsDTO);

    public IBasicDTO getApprovedJobsDTO();
    
    public void setShowerrorMsg(boolean showerrorMsg);

    public boolean isShowerrorMsg();
}

