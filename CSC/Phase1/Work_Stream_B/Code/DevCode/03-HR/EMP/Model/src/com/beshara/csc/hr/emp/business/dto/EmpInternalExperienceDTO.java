package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpInternalExperienceEntity;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;

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
public class EmpInternalExperienceDTO extends EmpDTO implements IEmpInternalExperienceDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Timestamp actionDate;
    private String pisJobCode;
    private String pisWrkCode;
    private String jobDetail;
    private Long revFlag;
    private Long recordSourceFlag;
    private Long auditStatus;
    private Long tabrecSerial;
    
    private IBasicDTO minDTO;
    private IBasicDTO jobsDTO;
    private IBasicDTO workCenterDTO;
    private IBasicDTO bgtTypesDTO;
    private IBasicDTO bgtProgramsDTO;
    private IBasicDTO trxTypesDTO;
    private IBasicDTO salElementGuidesDTO;
    
    //=================Start CSC-18251========================//
    private String aoeAuditBy;
    private Date aoeAuditDate;
    private Long aoeAuditFlag;
    private String aoeApprovedBy;
    private Date aoeApprovedDate;
    private Long aoeApprovedFlag;

    private String aoeAuditBy_name;
    private String aoeApprovedBy_name;
    
    //=================End CSC-18251========================//
    /**
     * EmpInternalExperienceDTO Default Constructor
     */
    public EmpInternalExperienceDTO() {
        super();
    }
    
    public EmpInternalExperienceDTO(EmpInternalExperienceEntity empInternalExperienceEntity) {
        
        this.setCode(EmpEntityKeyFactory.createEmpInternalExperienceEntityKey(empInternalExperienceEntity.getSerial()));
        this.civilId = empInternalExperienceEntity.getCivilId();
        this.actionDate = empInternalExperienceEntity.getActionDate();
        this.pisJobCode = empInternalExperienceEntity.getPisJobCode();
        this.pisWrkCode = empInternalExperienceEntity.getPisWrkCode();
        this.jobDetail = empInternalExperienceEntity.getJobDetail();
        this.revFlag = empInternalExperienceEntity.getRevFlag();
        this.recordSourceFlag = empInternalExperienceEntity.getRecordSourceFlag();
        this.auditStatus = empInternalExperienceEntity.getAuditStatus();
        this.tabrecSerial = empInternalExperienceEntity.getTabrecSerial();
        this.setCreatedBy( empInternalExperienceEntity.getCreatedBy() );
        this.setCreatedDate( empInternalExperienceEntity.getCreatedDate() );
        this.setLastUpdatedBy( empInternalExperienceEntity.getLastUpdatedBy() );
        this.setLastUpdatedDate( empInternalExperienceEntity.getLastUpdatedDate() );

        try{
        if (empInternalExperienceEntity.getJobCode() != null) {
            IJobsEntityKey jobEk=JobEntityKeyFactory.createJobsEntityKey(empInternalExperienceEntity.getJobCode());
            jobsDTO=JobClientFactory.getJobsClient().getById(jobEk);
            //jobsDTO = JobDTOFactory.createJobsDTO(empInternalExperienceEntity.getJobsEntity());
        }
        if (empInternalExperienceEntity.getMinCode() != null && empInternalExperienceEntity.getWrkCode() !=null) {
            IWorkCentersEntityKey wrkEk=OrgEntityKeyFactory.
                createWorkCentersEntityKey(empInternalExperienceEntity.getMinCode(), empInternalExperienceEntity.getWrkCode());
            workCenterDTO=OrgClientFactory.getWorkCentersClient().getById(wrkEk);
            //workCenterDTO = OrgDTOFactory.createWorkCentersDTO(empInternalExperienceEntity.getWorkCentersEntity());
        }
        if (empInternalExperienceEntity.getBgttypeCode() != null) {
            IBgtTypesEntityKey bgtTypeEk=BgtEntityKeyFactory.createBgtTypesEntityKey(empInternalExperienceEntity.getBgttypeCode());
            bgtTypesDTO=BgtClientFactory.getBgtTypesClient().getById(bgtTypeEk);
           // bgtTypesDTO = BgtDTOFactory.createBgtTypesDTO(empInternalExperienceEntity.getBgtTypesEntity());
        }
        if (empInternalExperienceEntity.getBgtprgCode() != null) {
            IBgtProgramsEntityKey pEk= BgtEntityKeyFactory.createBgtProgramsEntityKey(empInternalExperienceEntity.getBgtprgCode());
            bgtProgramsDTO=BgtClientFactory.getBgtProgramsClient().getById(pEk);
            //bgtProgramsDTO = BgtDTOFactory.createBgtProgramsDTO(empInternalExperienceEntity.getBgtProgramsEntity());
        }
        if (empInternalExperienceEntity.getTrxTypesEntity() != null) {
            trxTypesDTO = EmpDTOFactory.createTrxTypesDTO(empInternalExperienceEntity.getTrxTypesEntity());
        }
        if (empInternalExperienceEntity.getElmguideCode() != null) {
            ISalElementGuidesEntityKey salEk=SalEntityKeyFactory.createSalElementGuidesEntityKey(empInternalExperienceEntity.getElmguideCode());
            salElementGuidesDTO=SalClientFactory.getSalElementGuidesClient().getById(salEk);
            //salElementGuidesDTO = SalDTOFactory.createSalElementGuidesDTO(empInternalExperienceEntity.getSalElementGuidesEntity());
        }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    public Timestamp getActionDate() {
        return actionDate;
    }

    public void setPisJobCode(String pisJobCode) {
        this.pisJobCode = pisJobCode;
    }

    public String getPisJobCode() {
        return pisJobCode;
    }

    public void setPisWrkCode(String pisWrkCode) {
        this.pisWrkCode = pisWrkCode;
    }

    public String getPisWrkCode() {
        return pisWrkCode;
    }

    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    public String getJobDetail() {
        return jobDetail;
    }

    public void setRevFlag(Long revFlag) {
        this.revFlag = revFlag;
    }

    public Long getRevFlag() {
        return revFlag;
    }

    public void setRecordSourceFlag(Long recordSourceFlag) {
        this.recordSourceFlag = recordSourceFlag;
    }

    public Long getRecordSourceFlag() {
        return recordSourceFlag;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setJobsDTO(IBasicDTO jobsDTO) {
        this.jobsDTO = jobsDTO;
    }

    public IBasicDTO getJobsDTO() {
        return jobsDTO;
    }

    public void setWorkCenterDTO(IBasicDTO workCenterDTO) {
        this.workCenterDTO = workCenterDTO;
    }

    public IBasicDTO getWorkCenterDTO() {
        return workCenterDTO;
    }

    public void setBgtTypesDTO(IBasicDTO bgtTypesDTO) {
        this.bgtTypesDTO = bgtTypesDTO;
    }

    public IBasicDTO getBgtTypesDTO() {
        return bgtTypesDTO;
    }

    public void setBgtProgramsDTO(IBasicDTO bgtProgramsDTO) {
        this.bgtProgramsDTO = bgtProgramsDTO;
    }

    public IBasicDTO getBgtProgramsDTO() {
        return bgtProgramsDTO;
    }

    public void setTrxTypesDTO(IBasicDTO trxTypesDTO) {
        this.trxTypesDTO = trxTypesDTO;
    }

    public IBasicDTO getTrxTypesDTO() {
        return trxTypesDTO;
    }

    public void setSalElementGuidesDTO(IBasicDTO salElementGuidesDTO) {
        this.salElementGuidesDTO = salElementGuidesDTO;
    }

    public IBasicDTO getSalElementGuidesDTO() {
        return salElementGuidesDTO;
    }

    public void setRevFlagBoolean(boolean revFlagBoolean) {
        if(revFlagBoolean){
            setRevFlag(new Long(IEMPConstant.EMP_INTERNAL_EXP_REV_DONE));
        } else {
            setRevFlag(new Long(IEMPConstant.EMP_INTERNAL_EXP_REV_NOT_DONE));
        }
    }

    public boolean isRevFlagBoolean() {
        return (getRevFlag() != null && getRevFlag().equals(IEMPConstant.EMP_INTERNAL_EXP_REV_DONE));
    }

    public void setRecordSourcePis(boolean recordSourcePis) {
        if(recordSourcePis){
            setRecordSourceFlag(new Long(IEMPConstant.EMP_INTERNAL_EXP_SRC_PIS));
        }
    }

    public boolean isRecordSourcePis() {
        return (getRecordSourceFlag() != null && getRecordSourceFlag().equals(IEMPConstant.EMP_INTERNAL_EXP_SRC_PIS));
    }

    public void setRecordSourceLegacy(boolean recordSourceLegacy) {
        if(recordSourceLegacy){
            setRecordSourceFlag(new Long(IEMPConstant.EMP_INTERNAL_EXP_SRC_LEGACY));
        }
    }

    public boolean isRecordSourceLegacy() {
        return (getRecordSourceFlag() != null && getRecordSourceFlag().equals(IEMPConstant.EMP_INTERNAL_EXP_SRC_LEGACY));
    }

    public void setRecordSourceCsc(boolean recordSourceCsc) {
        if(recordSourceCsc){
            setRecordSourceFlag(new Long(IEMPConstant.EMP_INTERNAL_EXP_SRC_CSC));
        }
    }

    public boolean isRecordSourceCsc() {
        return (getRecordSourceFlag() != null && getRecordSourceFlag().equals(IEMPConstant.EMP_INTERNAL_EXP_SRC_CSC));
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setMinDTO(IBasicDTO minDTO) {
        this.minDTO = minDTO;
    }

    public IBasicDTO getMinDTO() {
        return minDTO;
    }
    
    //=================Start CSC-18251========================//
    public void setAoeAuditBy(String aoeAuditBy) {
        this.aoeAuditBy = aoeAuditBy;
    }

    public String getAoeAuditBy() {
        return aoeAuditBy;
    }

    public void setAoeAuditDate(Date aoeAuditDate) {
        this.aoeAuditDate = aoeAuditDate;
    }

    public Date getAoeAuditDate() {
        return aoeAuditDate;
    }

    public void setAoeAuditFlag(Long aoeAuditFlag) {
        this.aoeAuditFlag = aoeAuditFlag;
    }

    public Long getAoeAuditFlag() {
        return aoeAuditFlag;
    }

    public void setAoeApprovedBy(String aoeApprovedBy) {
        this.aoeApprovedBy = aoeApprovedBy;
    }

    public String getAoeApprovedBy() {
        return aoeApprovedBy;
    }

    public void setAoeApprovedDate(Date aoeApprovedDate) {
        this.aoeApprovedDate = aoeApprovedDate;
    }

    public Date getAoeApprovedDate() {
        return aoeApprovedDate;
    }

    public void setAoeApprovedFlag(Long aoeApprovedFlag) {
        this.aoeApprovedFlag = aoeApprovedFlag;
    }

    public Long getAoeApprovedFlag() {
        return aoeApprovedFlag;
    }
    //=================End CSC-18251========================//

    public void setAoeAuditBy_name(String aoeAuditBy_name) {
        this.aoeAuditBy_name = aoeAuditBy_name;
    }

    public String getAoeAuditBy_name() {
        return aoeAuditBy_name;
    }

    public void setAoeApprovedBy_name(String aoeApprovedBy_name) {
        this.aoeApprovedBy_name = aoeApprovedBy_name;
    }

    public String getAoeApprovedBy_name() {
        return aoeApprovedBy_name;
    }
}

