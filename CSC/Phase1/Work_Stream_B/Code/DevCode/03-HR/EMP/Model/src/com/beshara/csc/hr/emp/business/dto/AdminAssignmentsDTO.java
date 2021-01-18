package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.entity.AdminAssignmentsEntity;
import com.beshara.csc.hr.emp.business.entity.AdminAssignmentsEntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IDecisionMakerTypesDTO;
import com.beshara.csc.inf.business.entity.IDecisionMakerTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
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
public class AdminAssignmentsDTO extends EmpDTO implements IAdminAssignmentsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Date fromDate;
    private Date untilDate;
    private Long auditStatus;
    private Long tabrecSerial;
    private IAssignReasonsDTO assignReasonsDTO;
    private IAssignStatusDTO assignStatusDTO;
    private IAssignTypesDTO assignTypesDTO;
    private IEmployeesDTO employeesDTO;
    private ISalElementGuidesDTO salElementGuidesDTO;
    private IDecisionMakerTypesDTO decisionMakerTypesDTO;
    private IJobsDTO jobsDTO;
    private IWorkCentersDTO workCentersDTO;
    /** 
     * AdminAssignmentsDTO Default Constructor */
    public AdminAssignmentsDTO() {        super();
    }    /** 
     * @param adminAssignmentsEntity 
     */
    public AdminAssignmentsDTO(AdminAssignmentsEntity adminAssignmentsEntity) {        setCode(new AdminAssignmentsEntityKey(adminAssignmentsEntity.getAdmassignmentSerial()));
        this.fromDate = adminAssignmentsEntity.getFromDate();
        this.untilDate = adminAssignmentsEntity.getUntilDate();
        this.setCreatedBy(adminAssignmentsEntity.getCreatedBy());
        this.setCreatedDate(adminAssignmentsEntity.getCreatedDate());
        this.setLastUpdatedBy(adminAssignmentsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(adminAssignmentsEntity.getLastUpdatedDate());
        this.auditStatus = adminAssignmentsEntity.getAuditStatus();
        this.tabrecSerial = adminAssignmentsEntity.getTabrecSerial();
        this.assignReasonsDTO =                 new AssignReasonsDTO(adminAssignmentsEntity.getAssignReasonsEntity());
        this.assignStatusDTO =                 new AssignStatusDTO(adminAssignmentsEntity.getAssignStatusEntity());
        this.assignTypesDTO =                 new AssignTypesDTO(adminAssignmentsEntity.getAssignTypesEntity());
        this.employeesDTO =                 new EmployeesDTO(adminAssignmentsEntity.getEmployeesEntity());
    //    this.salElementGuidesDTO =                 new SalElementGuidesDTO(adminAssignmentsEntity.getSalElementGuidesEntity());
        // get SalElementGuideEntity
        try {
            // set salElementGuidesDTO
            ISalElementGuidesEntityKey salek =
                SalEntityKeyFactory.createSalElementGuidesEntityKey(adminAssignmentsEntity.getElmguideCode());
            this.salElementGuidesDTO = (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(salek);
            // set jobDto
            //this.jobsDTO = new JobsDTO(adminAssignmentsEntity.getJobsEntity());
            IJobsEntityKey jobEk =
                JobEntityKeyFactory.createJobsEntityKey(adminAssignmentsEntity.getJobCode());
            this.jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(jobEk);
            // set workCentersDTO
            // this.workCentersDTO = new WorkCentersDTO(adminAssignmentsEntity.getWorkCentersEntity());
            IWorkCentersEntityKey wrkEk =
            OrgEntityKeyFactory.createWorkCentersEntityKey(adminAssignmentsEntity.getMinCode(),adminAssignmentsEntity.getWrkCode());
            this.workCentersDTO = (IWorkCentersDTO)OrgClientFactory.getWorkCentersClient().getById(wrkEk);
            // set decisionMakerTypesDTO
            if(adminAssignmentsEntity.getDecmkrtypeCode() != null){
                IDecisionMakerTypesEntityKey dmtEk =
                InfEntityKeyFactory.createDecisionMakerTypesEntityKey(adminAssignmentsEntity.getDecmkrtypeCode());
                this.decisionMakerTypesDTO = (IDecisionMakerTypesDTO)InfClientFactory.getDecisionMakerTypesClient().getById(dmtEk);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
       
       
       /* if (adminAssignmentsEntity.getDecisionMakerTypesEntity() != null) {  
        this.decisionMakerTypesDTO =  new DecisionMakerTypesDTO(adminAssignmentsEntity.getDecisionMakerTypesEntity()); }    }   
       */
    }
     /** @return Timestamp 
     */
    public Date getFromDate() {        return fromDate;
    }    /** 
     * @return Timestamp 
     */
    public Date getUntilDate() {        return untilDate;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param fromDate 
     */
    public void setFromDate(Date fromDate) {        this.fromDate = fromDate;
    }    /** 
     * @param untilDate 
     */
    public void setUntilDate(Date untilDate) {        this.untilDate = untilDate;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public void setAssignReasonsDTO(IAssignReasonsDTO assignReasonsDTO) {        this.assignReasonsDTO = assignReasonsDTO;
    }    public IAssignReasonsDTO getAssignReasonsDTO() {        return assignReasonsDTO;
    }    public void setAssignStatusDTO(IAssignStatusDTO assignStatusDTO) {        this.assignStatusDTO = assignStatusDTO;
    }    public IAssignStatusDTO getAssignStatusDTO() {        return assignStatusDTO;
    }    public void setAssignTypesDTO(IAssignTypesDTO assignTypesDTO) {        this.assignTypesDTO = assignTypesDTO;
    }    public IAssignTypesDTO getAssignTypesDTO() {        return assignTypesDTO;
    }    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {        this.employeesDTO = employeesDTO;
    }    public IEmployeesDTO getEmployeesDTO() {        return employeesDTO;
    }    public void setSalElementGuidesDTO(ISalElementGuidesDTO salElementGuidesDTO) {        this.salElementGuidesDTO = salElementGuidesDTO;
    }    public ISalElementGuidesDTO getSalElementGuidesDTO() {        return salElementGuidesDTO;
    }    public void setDecisionMakerTypesDTO(IDecisionMakerTypesDTO decisionMakerTypesDTO) {        this.decisionMakerTypesDTO = decisionMakerTypesDTO;
    }    public IDecisionMakerTypesDTO getDecisionMakerTypesDTO() {        return decisionMakerTypesDTO;
    }    public void setJobsDTO(IJobsDTO jobsDTO) {        this.jobsDTO = jobsDTO;
    }    public IJobsDTO getJobsDTO() {        return jobsDTO;
    }    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO) {        this.workCentersDTO = workCentersDTO;
    }    public IWorkCentersDTO getWorkCentersDTO() {        return workCentersDTO;
    }}
