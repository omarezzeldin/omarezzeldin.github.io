
package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.locking.dto.ILockableItem;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.sql.Date;

import java.util.List;


public class EmpCandidatesDTO extends EmpDTO implements IEmpCandidatesDTO, ILockableItem {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long recordDescCode;

    private Long cndStatusCode;

    private Long hirtypeCode;

    private Long hirstageCode;

    private Long minCode;

    private String wrkCode;

    private Long civilId;

    private Date hireDate;

    private Date startWorkingDate;

    private Date dateOfNextRaise;

    private Date testPeriodDate;

    private String bgtprgCode;

    private Long bgttypeCode;

    private String jobCode;

    private String jobCodeOtherJob;

    private String cscFileNo;

    private String ministryFileNo;

    private String socialInsurNo;

    private Long crsTrxCode;

    private Long crsRegperiodCode;

    private Long activeFlag;

    private Long approvedByUser;

    private Date approvedDate;

    private Long auditByUser;

    private Long transferToEmpFlag;

    private Long tabrecSerial;

    private Long auditStatus;

    private List<IBasicDTO> employeesDTOList;

    private List<IBasicDTO> empCndSalaryElementsList;

    private List<IBasicDTO> empCandidateExtraDataList;

    private List<IBasicDTO> empCandidateDocumentsList;

    private List<IBasicDTO> empCandidateProceduresList;

    private IBasicDTO empCandidateStatusDTO;

    private IBasicDTO bgtProgramsDTO;

    private IBasicDTO bgtTypesDTO;

    private IBasicDTO citizensResidentsDTO;

    private IBasicDTO candidatePersonsDTO;

    private IBasicDTO usersDTOApprovedBy;

    private IBasicDTO usersDTOAuditBy;

    private IBasicDTO hireStagesDTO;

    private IBasicDTO hireTypesDTO;

    private IBasicDTO jobsDTOOtherJob;

    private IBasicDTO jobsDTO;

    private IBasicDTO workCentersDTO;
    
    private Long acceptedRaiseCode;
    
    private IDecisionsDTO decisionsDTO; 
    
    private IEmployeesDTO employeesDTO;
    private boolean hasRevision;
    private boolean hasRevisionFatwa;
    private boolean minExcepted;
    private boolean hasExperience;
    private Boolean documentsStatus;
    private Boolean proceduresStatus;
    private IEmpExtraDataValueDTO empExtraDataValueDTO;
    private IPersonQualificationsDTO personQualificationsDTO ;
    private IPersonsInformationDTO personsInformationDTO;
    private List<IPersonsInformationDTO> personsInformationDTOList;
    private List<IKwtWorkDataTreeDTO> kwtWorkDataTreeDTOList;
    private List<IKwtCitizensResidentsDTO> citizensResidentsList;
    private List<Long> civilIdList;
    
    private Long realCivilId;
    private String  hireTypeKey;
    private String extraDataValue;
    private String userNameValue;
    private String userNameValueFatwa;
    private List<IPersonsInformationDTO> deletedPersonsInformationDTOList;
    private Date transReqDate;
    private boolean witoutQualFlag;
    private IEmpExtraDataValueDTO previousEmpExtraDataValueDTO;
    private List<IBasicDTO> previousEmpCandidateExtraDataList;
    private IBasicDTO approvedJobsDTO;
    private boolean showerrorMsg;
    public EmpCandidatesDTO() {
        super();
    }

    public EmpCandidatesDTO(EmpCandidatesEntity empCandidatesEntity) {
        EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity);

    }

    public void setCndStatusCode(Long cndStatusCode) {
        this.cndStatusCode = cndStatusCode;
    }

    public Long getCndStatusCode() {
        return cndStatusCode;
    }

    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    public void setHirstageCode(Long hirstageCode) {
        this.hirstageCode = hirstageCode;
    }

    public Long getHirstageCode() {
        return hirstageCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    public String getWrkCode() {
        return wrkCode;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setStartWorkingDate(Date startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public Date getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setDateOfNextRaise(Date dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    public Date getDateOfNextRaise() {
        return dateOfNextRaise;
    }

    public void setTestPeriodDate(Date testPeriodDate) {
        this.testPeriodDate = testPeriodDate;
    }

    public Date getTestPeriodDate() {
        return testPeriodDate;
    }

    public void setBgtprgCode(String bgtprgCode) {
        this.bgtprgCode = bgtprgCode;
    }

    public String getBgtprgCode() {
        return bgtprgCode;
    }

    public void setBgttypeCode(Long bgttypeCode) {
        this.bgttypeCode = bgttypeCode;
    }

    public Long getBgttypeCode() {
        return bgttypeCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCodeOtherJob(String jobCodeOtherJob) {
        this.jobCodeOtherJob = jobCodeOtherJob;
    }

    public String getJobCodeOtherJob() {
        return jobCodeOtherJob;
    }

    public void setCscFileNo(String cscFileNo) {
        this.cscFileNo = cscFileNo;
    }

    public String getCscFileNo() {
        return cscFileNo;
    }

    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    public String getMinistryFileNo() {
        return ministryFileNo;
    }

    public void setSocialInsurNo(String socialInsurNo) {
        this.socialInsurNo = socialInsurNo;
    }

    public String getSocialInsurNo() {
        return socialInsurNo;
    }

    public void setCrsTrxCode(Long crsTrxCode) {
        this.crsTrxCode = crsTrxCode;
    }

    public Long getCrsTrxCode() {
        return crsTrxCode;
    }

    public void setCrsRegperiodCode(Long crsRegperiodCode) {
        this.crsRegperiodCode = crsRegperiodCode;
    }

    public Long getCrsRegperiodCode() {
        return crsRegperiodCode;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setApprovedByUser(Long approvedByUser) {
        this.approvedByUser = approvedByUser;
    }

    public Long getApprovedByUser() {
        return approvedByUser;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setAuditByUser(Long auditByUser) {
        this.auditByUser = auditByUser;
    }

    public Long getAuditByUser() {
        return auditByUser;
    }

    public void setTransferToEmpFlag(Long transferToEmpFlag) {
        this.transferToEmpFlag = transferToEmpFlag;
    }

    public Long getTransferToEmpFlag() {
        return transferToEmpFlag;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setEmployeesDTOList(List<IBasicDTO> employeesDTOList) {
        this.employeesDTOList = employeesDTOList;
    }

    public List<IBasicDTO> getEmployeesDTOList() {
        return employeesDTOList;
    }

    public void setEmpCndSalaryElementsList(List<IBasicDTO> empCndSalaryElementsList) {
        this.empCndSalaryElementsList = empCndSalaryElementsList;
    }
    

    public List<IBasicDTO> getEmpCndSalaryElementsList() {
        return empCndSalaryElementsList;
    }

    public void setEmpCandidateExtraDataList(List<IBasicDTO> empCandidateExtraDataList) {
        this.empCandidateExtraDataList = empCandidateExtraDataList;
    }

    public List<IBasicDTO> getEmpCandidateExtraDataList() {
        return empCandidateExtraDataList;
    }

    public void setEmpCandidateDocumentsList(List<IBasicDTO> empCandidateDocumentsList) {
        this.empCandidateDocumentsList = empCandidateDocumentsList;
    }

    public List<IBasicDTO> getEmpCandidateDocumentsList() {
        return empCandidateDocumentsList;
    }

    public void setEmpCandidateProceduresList(List<IBasicDTO> empCandidateProceduresList) {
        this.empCandidateProceduresList = empCandidateProceduresList;
    }

    public List<IBasicDTO> getEmpCandidateProceduresList() {
        return empCandidateProceduresList;
    }

    public void setEmpCandidateStatusDTO(IBasicDTO empCandidateStatusDTO) {
        this.empCandidateStatusDTO = empCandidateStatusDTO;
    }

    public IBasicDTO getEmpCandidateStatusDTO() {
        return empCandidateStatusDTO;
    }

    public void setBgtProgramsDTO(IBasicDTO bgtProgramsDTO) {
        this.bgtProgramsDTO = bgtProgramsDTO;
    }

    public IBasicDTO getBgtProgramsDTO() {
        return bgtProgramsDTO;
    }

    public void setBgtTypesDTO(IBasicDTO bgtTypesDTO) {
        this.bgtTypesDTO = bgtTypesDTO;
    }

    public IBasicDTO getBgtTypesDTO() {
        return bgtTypesDTO;
    }

    public void setCitizensResidentsDTO(IBasicDTO citizensResidentsDTO) {
        this.citizensResidentsDTO = citizensResidentsDTO;
    }

    public IBasicDTO getCitizensResidentsDTO() {
        return citizensResidentsDTO;
    }

    public void setUsersDTOApprovedBy(IBasicDTO usersDTOApprovedBy) {
        this.usersDTOApprovedBy = usersDTOApprovedBy;
    }

    public IBasicDTO getUsersDTOApprovedBy() {
        return usersDTOApprovedBy;
    }

    public void setUsersDTOAuditBy(IBasicDTO usersDTOAuditBy) {
        this.usersDTOAuditBy = usersDTOAuditBy;
    }

    public IBasicDTO getUsersDTOAuditBy() {
        return usersDTOAuditBy;
    }

    public void setHireStagesDTO(IBasicDTO hireStagesDTO) {
        this.hireStagesDTO = hireStagesDTO;
    }

    public IBasicDTO getHireStagesDTO() {
        return hireStagesDTO;
    }

    public void setHireTypesDTO(IBasicDTO hireTypesDTO) {
        this.hireTypesDTO = hireTypesDTO;
    }

    public IBasicDTO getHireTypesDTO() {
        return hireTypesDTO;
    }

    public void setJobsDTOOtherJob(IBasicDTO jobsDTOOtherJob) {
        this.jobsDTOOtherJob = jobsDTOOtherJob;
    }

    public IBasicDTO getJobsDTOOtherJob() {
        return jobsDTOOtherJob;
    }

    public void setJobsDTO(IBasicDTO jobsDTO) {
        this.jobsDTO = jobsDTO;
    }

    public IBasicDTO getJobsDTO() {
        return jobsDTO;
    }

    public void setWorkCentersDTO(IBasicDTO workCentersDTO) {
        this.workCentersDTO = workCentersDTO;
    }

    public IBasicDTO getWorkCentersDTO() {
        return workCentersDTO;
    }


    public void setRecordDescCode(Long recordDescCode) {
        this.recordDescCode = recordDescCode;
    }

    public Long getRecordDescCode() {
        return recordDescCode;
    }

    public void setCandidatePersonsDTO(IBasicDTO candidatePersonsDTO) {
        this.candidatePersonsDTO = candidatePersonsDTO;
    }


    public IBasicDTO getCandidatePersonsDTO() {
        return candidatePersonsDTO;
    }

    public void setUsersDTOApproved(IBasicDTO usersDTOApprovedBy) {
        this.usersDTOApprovedBy = usersDTOApprovedBy;

    }

    public IBasicDTO getUsersDTOApprovedDTO() {
        return usersDTOApprovedBy;
    }

    public String getLockEntity() {
        return "HR_EMP_CANDIDATES";
    }

    public String getLockId() {
        return tabrecSerial.toString();
    }

    public void setHasExperience(boolean hasExperience) {
        this.hasExperience = hasExperience;
    }

    public boolean isHasExperience() {
        if (getCode() != null) {
            try {
        //                System.out.println(getRealCivilId()); A.Kamal change civil-ID to getRealCivilId
                boolean ret=InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(Long.valueOf(citizensResidentsDTO.getCode().getKey()));
        //                System.out.println(ret);
                return ret;
            } catch (DataBaseException e) {
                return false;
            } catch (SharedApplicationException e) {
                return false;
            }
        }

        return false;
    }
    
    public String getHireStageKey() {
        if (this.hireStagesDTO != null) {
            return hireStagesDTO.getCode().getKey();
        }
        return null;
    }

    public void setHireStageKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
            IHireStagesEntityKey entityKey = EmpEntityKeyFactory.createHireStagesEntityKey(Long.parseLong(key));
            this.hireStagesDTO = EmpDTOFactory.createHireStagesDTO();
            hireStagesDTO.setCode(entityKey);
        }
    }

    public String getHireTypeKey() {
//        if (this.hireTypesDTO != null) {
//            return (String)hireTypesDTO.getCode().getKey();
//        }
        return hireTypeKey;
    }

    public void setHireTypeKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE) && !key.equals("0")) {
            IHireTypesEntityKey entityKey = EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(key));
            this.hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
            hireTypesDTO.setCode(entityKey);
        }
        hireTypeKey=key;
        
    }
    
    public String getHireStatusKey() {
        if (this.empCandidateStatusDTO != null) {
            return (String)empCandidateStatusDTO.getCode().getKey();
        }
        return null;
    }

    public void setHireStatusKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
            IHireStatusEntityKey entityKey = EmpEntityKeyFactory.createHireStatusEntityKey(Long.parseLong(key));
            this.empCandidateStatusDTO = EmpDTOFactory.createHireStatusDTO();
            empCandidateStatusDTO.setCode(entityKey);
        }
    }
    
    public String getBgtTypeKey() {
        if (this.bgtTypesDTO != null) {
            return (String)bgtTypesDTO.getCode().getKey();
        }
        return null;
    }

    public void setBgtTypeKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
            IBgtTypesEntityKey entityKey = 
                BgtEntityKeyFactory.createBgtTypesEntityKey(Long.parseLong(key));
            this.bgtTypesDTO = BgtDTOFactory.createBgtTypesDTO();
            this.bgtTypesDTO.setCode(entityKey);
        }
    }
    
    public String getWorkCenterKey() {
        if (this.workCentersDTO != null) {
            return (String)workCentersDTO.getCode().getKey();
        }
        return null;
    }

    public void setWorkCenterKey(String key) {
        if (key != null && !key.equals("") && 
            !key.equals(ISystemConstant.VIRTUAL_VALUE.toString())) {
            String[] keys = key.split("\\*");
            IWorkCentersEntityKey entityKey = 
                OrgEntityKeyFactory.createWorkCentersEntityKey(Long.parseLong(keys[0]), 
                                                               keys[1]);
            this.workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
            workCentersDTO.setCode(entityKey);
        }
    }
    
    public void setDocumentsStatus(Boolean documentsStatus) {
        this.documentsStatus = documentsStatus;
    }

    public Boolean getDocumentsStatus() {
        return documentsStatus;
    }

    public void setProceduresStatus(Boolean proceduresStatus) {
        this.proceduresStatus = proceduresStatus;
    }

    public Boolean getProceduresStatus() {
        return proceduresStatus;
    }

    public void setEmpExtraDataValueDTO(IEmpExtraDataValueDTO empExtraDataValueDTO) {
        this.empExtraDataValueDTO = empExtraDataValueDTO;
    }

    public IEmpExtraDataValueDTO getEmpExtraDataValueDTO() {
        return empExtraDataValueDTO;
    }
    
    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO) {
        this.personQualificationsDTO = personQualificationsDTO;
    }

    public IPersonQualificationsDTO getPersonQualificationsDTO() {
        return personQualificationsDTO;
    }

    public void setPersonsInformationDTO(IPersonsInformationDTO personsInformationDTO) {
        this.personsInformationDTO = personsInformationDTO;
    }

    public IPersonsInformationDTO getPersonsInformationDTO() {
        return personsInformationDTO;
    }
    
    public String getBgtPrgmKey() {
        if (this.bgtProgramsDTO != null) {
            return (String)bgtProgramsDTO.getCode().getKey();
        }
        return null;
    }

    public void setBgtPrgmKey(String key) {
        if (key != null && !key.equals("")) {
            IBgtProgramsEntityKey entityKey = 
                BgtEntityKeyFactory.createBgtProgramsEntityKey(key);
            this.bgtProgramsDTO = BgtDTOFactory.createBgtProgramsDTO();
            this.bgtProgramsDTO.setCode(entityKey);
        }
    }

    public void setAcceptedRaiseCode(Long acceptedRaiseCode) {
        this.acceptedRaiseCode = acceptedRaiseCode;
    }

    public Long getAcceptedRaiseCode() {
        return acceptedRaiseCode;
    }

    @Override
    public String getJDTableName() {
        return "HR_EMP_CANDIDATES";
    }
    
    @Override
    public void setDecisionsDTO(IDecisionsDTO decisionsDTO) {
        this.decisionsDTO = decisionsDTO;
    }

    @Override
    public IDecisionsDTO getDecisionsDTO() {
        return decisionsDTO;
    }

    @Override
    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    @Override
    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    @Override
    public void setRealCivilId(Long realCivilId) {
        if(employeesDTO!=null)
        this.employeesDTO.setRealCivilId(realCivilId);
        else 
            this.realCivilId=realCivilId;
            
    }

    @Override
    public Long getRealCivilId() {
        if(employeesDTO!=null)
        return this.employeesDTO.getRealCivilId();
        else
          return this.realCivilId;  
    }

    public void setPersonsInformationDTOList(List<IPersonsInformationDTO> personsInformationDTOList) {
        this.personsInformationDTOList = personsInformationDTOList;
    }

    public List<IPersonsInformationDTO> getPersonsInformationDTOList() {
        return personsInformationDTOList;
    }
    
    ///
    public List<Long> getCivilIdList(){
        return civilIdList;
    }
    
    public void setCivilIdList(List<Long> civilList){
        this.civilIdList=civilList;
    }

    public void setKwtWorkDataTreeDTOList(List<IKwtWorkDataTreeDTO> kwtWorkDataTreeDTOList) {
        this.kwtWorkDataTreeDTOList = kwtWorkDataTreeDTOList;
    }

    public List<IKwtWorkDataTreeDTO> getKwtWorkDataTreeDTOList() {
        return kwtWorkDataTreeDTOList;
    }

    public void setCitizensResidentsList(List<IKwtCitizensResidentsDTO> citizensResidentsList) {
        this.citizensResidentsList = citizensResidentsList;
    }

    public List<IKwtCitizensResidentsDTO> getCitizensResidentsList() {
        return citizensResidentsList;
    }

    public void setHasRevision(boolean hasRevision) {
        this.hasRevision = hasRevision;
    }

    public boolean isHasRevision() {
        return hasRevision;
    }

    public void setMinExcepted(boolean minExcepted) {
        this.minExcepted = minExcepted;
    }

    public boolean isMinExcepted() {
        return minExcepted;
    }

    public void setExtraDataValue(String extraDataValue) {
        this.extraDataValue = extraDataValue;
    }

    public String getExtraDataValue() {
        return extraDataValue;
    }

    public void setHasRevisionFatwa(boolean hasRevisionFatwa) {
        this.hasRevisionFatwa = hasRevisionFatwa;
    }

    public boolean isHasRevisionFatwa() {
        return hasRevisionFatwa;
    }
    public boolean getHasRevision(){
            return hasRevision;
        }
    public boolean getHasRevisionFatwa() {
        return hasRevisionFatwa;
    }

    public void setUserNameValue(String userNameValue) {
        this.userNameValue = userNameValue;
    }

    public String getUserNameValue() {
        return userNameValue;
    }

    public void setUserNameValueFatwa(String userNameValueFatwa) {
        this.userNameValueFatwa = userNameValueFatwa;
    }

    public String getUserNameValueFatwa() {
        return userNameValueFatwa;
    }

    public void setTransReqDate(Date transReqDate) {
        this.transReqDate = transReqDate;
    }

    public Date getTransReqDate() {
        return transReqDate;
    }

    public void setDeletedPersonsInformationDTOList(List<IPersonsInformationDTO> deletedPersonsInformationDTOList) {
        this.deletedPersonsInformationDTOList = deletedPersonsInformationDTOList;
    }

    public List<IPersonsInformationDTO> getDeletedPersonsInformationDTOList() {
        return deletedPersonsInformationDTOList;
    }

    public void setWitoutQualFlag(boolean witoutQualFlag) {
        this.witoutQualFlag = witoutQualFlag;
    }

    public boolean isWitoutQualFlag() {
        return witoutQualFlag;
    }

    public void setPreviousEmpExtraDataValueDTO(IEmpExtraDataValueDTO previousEmpExtraDataValueDTO) {
        this.previousEmpExtraDataValueDTO = previousEmpExtraDataValueDTO;
    }

    public IEmpExtraDataValueDTO getPreviousEmpExtraDataValueDTO() {
        return previousEmpExtraDataValueDTO;
    }

    public void setPreviousEmpCandidateExtraDataList(List<IBasicDTO> previousEmpCandidateExtraDataList) {
        this.previousEmpCandidateExtraDataList = previousEmpCandidateExtraDataList;
    }

    public List<IBasicDTO> getPreviousEmpCandidateExtraDataList() {
        return previousEmpCandidateExtraDataList;
    }

    public void setApprovedJobsDTO(IBasicDTO approvedJobsDTO) {
        this.approvedJobsDTO = approvedJobsDTO;
    }

    public IBasicDTO getApprovedJobsDTO() {
        return approvedJobsDTO;
    }

    public void setShowerrorMsg(boolean showerrorMsg) {
        this.showerrorMsg = showerrorMsg;
    }

    public boolean isShowerrorMsg() {
        return showerrorMsg;
    }
}
