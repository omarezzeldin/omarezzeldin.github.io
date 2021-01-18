package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.entity.EmpEmployeeHistoriesEntity;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.JobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.WorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;

import java.sql.Timestamp;

import java.util.List;


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
public class EmpEmployeeHistoriesDTO extends EmpDTO implements IEmpEmployeeHistoriesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long serial;
    private Long civilId;
    private String wrkCode;
    private Long minCode;
    private String jobCode;
    private String techJobCode;
    private Timestamp fromDate;
    private Timestamp untilDate;
    private Long auditBy;
    private Timestamp auditDate;
    private Long pisFlag;
    private Long perFlag;
    private Long trxtypeCode;
    private Long auditStatus;
    private Long tabrecSerial;
    // private WorkCentersDTO workCentersDTO ; 
    private WorkCentersDTO workCentersDTO;
    private JobsDTO jobsDTO;
    private JobsDTO techJobsDTO;
    private List<WorkCentersDTO> workCentersDTOList;
    private List<JobsDTO> jobsDTOList;
    private List<JobsDTO> techJobsDTOList;
    /** 
     * EmpEmployeeHistoriesDTO Default Constructor */
    public EmpEmployeeHistoriesDTO() {        super();
    }    /** 
     * @param empEmployeeHistoriesEntity 
     */
    public EmpEmployeeHistoriesDTO(EmpEmployeeHistoriesEntity empEmployeeHistoriesEntity) {        this.serial = empEmployeeHistoriesEntity.getSerial();
        this.civilId = empEmployeeHistoriesEntity.getCivilId();
        this.wrkCode = empEmployeeHistoriesEntity.getWrkCode();
        this.minCode = empEmployeeHistoriesEntity.getMinCode();
        this.jobCode = empEmployeeHistoriesEntity.getJobCode();
        this.techJobCode = empEmployeeHistoriesEntity.getTechJobCode();
        this.fromDate = empEmployeeHistoriesEntity.getFromDate();
        this.untilDate = empEmployeeHistoriesEntity.getUntilDate();
        this.auditBy = empEmployeeHistoriesEntity.getAuditBy();
        this.auditDate = empEmployeeHistoriesEntity.getAuditDate();
        this.pisFlag = empEmployeeHistoriesEntity.getPisFlag();
        this.perFlag = empEmployeeHistoriesEntity.getPerFlag();
        this.trxtypeCode = empEmployeeHistoriesEntity.getTrxtypeCode();
        this.auditStatus = empEmployeeHistoriesEntity.getAuditStatus();
        this.tabrecSerial = empEmployeeHistoriesEntity.getTabrecSerial();
        this.setCreatedBy(empEmployeeHistoriesEntity.getCreatedBy());
        this.setCreatedDate(empEmployeeHistoriesEntity.getCreatedDate());
        this.setLastUpdatedBy(empEmployeeHistoriesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(empEmployeeHistoriesEntity.getLastUpdatedDate());
        if (empEmployeeHistoriesEntity.getMinCode() != null && empEmployeeHistoriesEntity.getWrkCode() !=null) {
            IWorkCentersEntityKey wEk=OrgEntityKeyFactory.createWorkCentersEntityKey
                (empEmployeeHistoriesEntity.getMinCode(), empEmployeeHistoriesEntity.getWrkCode());
            try{
                this.setWorkCentersDTO((WorkCentersDTO)OrgClientFactory.getWorkCentersClient().getById(wEk));
            }catch(Exception e){
                throw new RuntimeException(e);
            }
            //this.setWorkCentersDTO(new WorkCentersDTO(empEmployeeHistoriesEntity.getWorkCentersEntity()));
        }
        try{
            
        
        if (empEmployeeHistoriesEntity.getJobCode() != null)  {
            IJobsEntityKey jobsEk=JobEntityKeyFactory.createJobsEntityKey(empEmployeeHistoriesEntity.getJobCode());
            this.setJobsDTO((JobsDTO)JobClientFactory.getJobsClient().getById(jobsEk));
           // this.setJobsDTO(new JobsDTO(empEmployeeHistoriesEntity.getJobsEntity()));
        }
        if (empEmployeeHistoriesEntity.getTechJobCode() != null) {
            IJobsEntityKey jobsEk=JobEntityKeyFactory.createJobsEntityKey(empEmployeeHistoriesEntity.getTechJobCode());
            this.setTechJobsDTO((JobsDTO)JobEntityKeyFactory.createJobsEntityKey(empEmployeeHistoriesEntity.getTechJobCode()));
            // this.setTechJobsDTO(new JobsDTO(empEmployeeHistoriesEntity.getTechJobsEntity()));
        }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }    /** 
     * @return Long 
     */
    public Long getSerial() {        return serial;
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return String 
     */
    public String getWrkCode() {        return wrkCode;
    }    /** 
     * @return Long 
     */
    public Long getMinCode() {        return minCode;
    }    /** 
     * @return String 
     */
    public String getJobCode() {        return jobCode;
    }    /** 
     * @return String 
     */
    public String getTechJobCode() {        return techJobCode;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getFromDate() {        return fromDate;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getUntilDate() {        return untilDate;
    }    /** 
     * @return Long 
     */
    public Long getAuditBy() {        return auditBy;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getAuditDate() {        return auditDate;
    }    /** 
     * @return Long 
     */
    public Long getPisFlag() {        return pisFlag;
    }    /** 
     * @return Long 
     */
    public Long getPerFlag() {        return perFlag;
    }    /** 
     * @return Long 
     */
    public Long getTrxtypeCode() {        return trxtypeCode;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param serial 
     */
    public void setSerial(Long serial) {        this.serial = serial;
    }    /** 
     * @param civilId 
     */
    public void setCivilId(Long civilId) {        this.civilId = civilId;
    }    /** 
     * @param wrkCode 
     */
    public void setWrkCode(String wrkCode) {        this.wrkCode = wrkCode;
    }    /** 
     * @param minCode 
     */
    public void setMinCode(Long minCode) {        this.minCode = minCode;
    }    /** 
     * @param jobCode 
     */
    public void setJobCode(String jobCode) {        this.jobCode = jobCode;
    }    /** 
     * @param techJobCode 
     */
    public void setTechJobCode(String techJobCode) {        this.techJobCode = techJobCode;
    }    /** 
     * @param fromDate 
     */
    public void setFromDate(Timestamp fromDate) {        this.fromDate = fromDate;
    }    /** 
     * @param untilDate 
     */
    public void setUntilDate(Timestamp untilDate) {        this.untilDate = untilDate;
    }    /** 
     * @param auditBy 
     */
    public void setAuditBy(Long auditBy) {        this.auditBy = auditBy;
    }    /** 
     * @param auditDate 
     */
    public void setAuditDate(Timestamp auditDate) {        this.auditDate = auditDate;
    }    /** 
     * @param pisFlag 
     */
    public void setPisFlag(Long pisFlag) {        this.pisFlag = pisFlag;
    }    /** 
     * @param perFlag 
     */
    public void setPerFlag(Long perFlag) {        this.perFlag = perFlag;
    }    /** 
     * @param trxtypeCode 
     */
    public void setTrxtypeCode(Long trxtypeCode) {        this.trxtypeCode = trxtypeCode;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public void setWorkCentersDTO(WorkCentersDTO workCentersDTO) {        this.workCentersDTO = workCentersDTO;
    }    public WorkCentersDTO getWorkCentersDTO() {        return workCentersDTO;
    }    public void setJobsDTO(JobsDTO jobsDTO) {        this.jobsDTO = jobsDTO;
    }    public JobsDTO getJobsDTO() {        return jobsDTO;
    }    public void setTechJobsDTO(JobsDTO techJobsDTO) {        this.techJobsDTO = techJobsDTO;
    }    public JobsDTO getTechJobsDTO() {        return techJobsDTO;
    }    public void setWorkCentersDTOList(List<WorkCentersDTO> workCentersDTOList) {        this.workCentersDTOList = workCentersDTOList;
    }    public List<WorkCentersDTO> getWorkCentersDTOList() {        return workCentersDTOList;
    }    public void setJobsDTOList(List<JobsDTO> jobsDTOList) {        this.jobsDTOList = jobsDTOList;
    }    public List<JobsDTO> getJobsDTOList() {        return jobsDTOList;
    }    public void setTechJobsDTOList(List<JobsDTO> techJobsDTOList) {        this.techJobsDTOList = techJobsDTOList;
    }    public List<JobsDTO> getTechJobsDTOList() {        return techJobsDTOList;
    }}
