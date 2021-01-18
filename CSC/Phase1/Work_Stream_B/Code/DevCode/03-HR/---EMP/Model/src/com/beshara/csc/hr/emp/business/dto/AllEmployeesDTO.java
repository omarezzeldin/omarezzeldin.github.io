package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.AllEmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.AllEmployeesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.dto.BankBranchesDTO;
import com.beshara.csc.nl.bnk.business.entity.BnkEntityKeyFactory;
import com.beshara.csc.nl.bnk.business.entity.IBankBranchesEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;

import java.sql.Timestamp;

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
public class AllEmployeesDTO extends EmpDTO implements IAllEmployeesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Timestamp trxDatetime;
    private Long minCode;
    private String wrkCode;
    private String ministryFileNo;
    private String cscFileNo;
    private Timestamp hireDate;
    private Timestamp startWorkingDate;
    private Timestamp endJobDate;
    private Long hirstatusCode;
    private Long hirtypeCode;
    private Timestamp dateOfNextRaise;
    private String techJobCode;
    private String jobCode;
    private Long bankCode;
    private Long bnkbranchCode;
    private String accountNo;
    private Long activeFlag;
    private Long auditStatus;
    private Long tabrecSerial;
    private IHireStatusDTO hireStatusDTO;
    private IHireTypesDTO hireTypesDTO;
    private IKwtCitizensResidentsDTO citizensResidentsDTO;
    private IJobsDTO jobsDTO;
    private BankBranchesDTO bankBranchesDTO;
    private IWorkCentersDTO workCentersDTO;
    private IJobsDTO techJobsDTO;
    private IBgtTypesDTO bgtTypesDTO;
    private IBgtProgramsDTO bgtProgramsDTO;
    private String prgCode;
    private Long typeCode;

    /**
     * AllEmployeesDTO Default Constructor */
    public AllEmployeesDTO() {
        super();
    }

    /**
     * @param allEmployeesEntity
     */
    public AllEmployeesDTO(AllEmployeesEntity allEmployeesEntity) {
        setCode(new AllEmployeesEntityKey(allEmployeesEntity.getCivilId(), allEmployeesEntity.getTrxDatetime()));
        this.civilId = allEmployeesEntity.getCivilId();
        this.trxDatetime = allEmployeesEntity.getTrxDatetime();
        this.minCode = allEmployeesEntity.getMinCode();
        this.wrkCode = allEmployeesEntity.getWrkCode();
        this.ministryFileNo = allEmployeesEntity.getMinistryFileNo();
        this.cscFileNo = allEmployeesEntity.getCscFileNo();
        this.hireDate = allEmployeesEntity.getHireDate();
        this.startWorkingDate = allEmployeesEntity.getStartWorkingDate();
        this.endJobDate = allEmployeesEntity.getEndJobDate();
        this.hirstatusCode = allEmployeesEntity.getHirstatusCode();
        this.hirtypeCode = allEmployeesEntity.getHirtypeCode();
        this.dateOfNextRaise = allEmployeesEntity.getDateOfNextRaise();
        this.techJobCode = allEmployeesEntity.getTechJobCode();
        this.jobCode = allEmployeesEntity.getJobCode();
        this.bankCode = allEmployeesEntity.getBankCode();
        this.bnkbranchCode = allEmployeesEntity.getBnkbranchCode();
        this.accountNo = allEmployeesEntity.getAccountNo();
        this.activeFlag = allEmployeesEntity.getActiveFlag();
        this.setCreatedBy(allEmployeesEntity.getCreatedBy());
        this.setCreatedDate(allEmployeesEntity.getCreatedDate());
        this.setLastUpdatedBy(allEmployeesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(allEmployeesEntity.getLastUpdatedDate());
        this.auditStatus = allEmployeesEntity.getAuditStatus();
        this.tabrecSerial = allEmployeesEntity.getTabrecSerial();
        //        this.prgCode=allEmployeesEntity.getPrgCode();
        //        this.typeCode=allEmployeesEntity.getTypeCode();
        /////////////////////////////////////////////////////////////////
        /*
         * entity seperation 26-10-2014
         */
        try {
            if (allEmployeesEntity.getCivilId() != null) {
                IKwtCitizensResidentsEntityKey infEk =
                    InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(allEmployeesEntity.getCivilId());
                citizensResidentsDTO =
                        (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getById(infEk);
            }

            if (allEmployeesEntity.getWrkCode() != null && allEmployeesEntity.getMinCode() != null) {
                IWorkCentersEntityKey wrkEk =
                    OrgEntityKeyFactory.createWorkCentersEntityKey(allEmployeesEntity.getMinCode(),
                                                                   allEmployeesEntity.getWrkCode());
                workCentersDTO = (IWorkCentersDTO)OrgClientFactory.getWorkCentersClient().getById(wrkEk);
            }

            if (allEmployeesEntity.getHireStatusEntity() != null) {
                hireStatusDTO = EmpDTOFactory.createHireStatusDTO(allEmployeesEntity.getHireStatusEntity());
            }

            if (allEmployeesEntity.getHireTypesEntity() != null) {
                hireTypesDTO = EmpDTOFactory.createHireTypesDTO(allEmployeesEntity.getHireTypesEntity());
            }

            if (allEmployeesEntity.getJobCode() != null) {
                IJobsEntityKey jobEk = JobEntityKeyFactory.createJobsEntityKey(allEmployeesEntity.getJobCode());
                jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(jobEk);
            }
            if (allEmployeesEntity.getTechJobCode() != null) {
                IJobsEntityKey techJobEk =
                    JobEntityKeyFactory.createJobsEntityKey(allEmployeesEntity.getTechJobCode());
                techJobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(techJobEk);
            }
            if (allEmployeesEntity.getBankCode() != null && allEmployeesEntity.getBnkbranchCode() != null) {
                IBankBranchesEntityKey bEk =
                    BnkEntityKeyFactory.createBankBranchesEntityKey(allEmployeesEntity.getBankCode(),
                                                                    allEmployeesEntity.getBnkbranchCode());
                bankBranchesDTO = (BankBranchesDTO)BnkClientFactory.getBankBranchesClient().getById(bEk);
            }
            if (allEmployeesEntity.getPrgCode() != null) {
                IBgtProgramsEntityKey bgEk =
                    BgtEntityKeyFactory.createBgtProgramsEntityKey(allEmployeesEntity.getPrgCode());
                this.bgtProgramsDTO = (IBgtProgramsDTO)BgtClientFactory.getBgtProgramsClient().getById(bgEk);
            }

            if (allEmployeesEntity.getTypeCode() != null) {
                IBgtTypesEntityKey bgtTypeEk =
                    BgtEntityKeyFactory.createBgtTypesEntityKey(allEmployeesEntity.getTypeCode());
                this.bgtTypesDTO = (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(bgtTypeEk);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //        if (allEmployeesEntity.getCivilId() != null) {
        //            citizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO(allEmployeesEntity.getCitizensResidentsEntity());
        //        }
        //        if (allEmployeesEntity.getWrkCode() != null && allEmployeesEntity.getMinCode() != null) {            workCentersDTO =                     OrgDTOFactory.createWorkCentersDTO(allEmployeesEntity.getWorkCentersEntity());
        //        }
        //        if (allEmployeesEntity.getHireStatusEntity() != null) {            hireStatusDTO =                     EmpDTOFactory.createHireStatusDTO(allEmployeesEntity.getHireStatusEntity()); }
        //      if (allEmployeesEntity.getHireTypesEntity() != null) {            hireTypesDTO =                     EmpDTOFactory.createHireTypesDTO(allEmployeesEntity.getHireTypesEntity()); }
        //        if (allEmployeesEntity.getJobCode() != null) {            jobsDTO =                     JobDTOFactory.createJobsDTO(allEmployeesEntity.getJobsEntity());}        if (allEmployeesEntity.getJobCode() != null) {            techJobsDTO =                     JobDTOFactory.createJobsDTO(allEmployeesEntity.getTechJobsEntity());}        if (allEmployeesEntity.getBankCode() != null && allEmployeesEntity.getBnkbranchCode() !=null) {            bankBranchesDTO =                     InfDTOFactory.createBankBranchesDTO(allEmployeesEntity.getBankBranchesEntity());}        if (allEmployeesEntity.getPrgCode() != null)            this.bgtProgramsDTO =                     BgtDTOFactory.createBgtProgramsDTO(allEmployeesEntity.getBgtProgramsEntity());
        //        if (allEmployeesEntity.getTypeCode() != null)            this.bgtTypesDTO =                     BgtDTOFactory.createBgtTypesDTO(allEmployeesEntity.getBgtTypesEntity());
    }

    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getTrxDatetime() {
        return trxDatetime;
    }

    /**
     * @return Long
     */
    public Long getMinCode() {
        return minCode;
    }

    /**
     * @return String
     */
    public String getWrkCode() {
        return wrkCode;
    }

    /**
     * @return String
     */
    public String getMinistryFileNo() {
        return ministryFileNo;
    }

    /**
     * @return String
     */
    public String getCscFileNo() {
        return cscFileNo;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getHireDate() {
        return hireDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getStartWorkingDate() {
        return startWorkingDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getEndJobDate() {
        return endJobDate;
    }

    /**
     * @return Long
     */
    public Long getHirstatusCode() {
        return hirstatusCode;
    }

    /**
     * @return Long
     */
    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getDateOfNextRaise() {
        return dateOfNextRaise;
    }

    /**
     * @return String
     */
    public String getTechJobCode() {
        return techJobCode;
    }

    /**
     * @return String
     */
    public String getJobCode() {
        return jobCode;
    }

    /**
     * @return Long
     */
    public Long getBankCode() {
        return bankCode;
    }

    /**
     * @return Long
     */
    public Long getBnkbranchCode() {
        return bnkbranchCode;
    }

    /**
     * @return String
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @return Long
     */
    public Long getActiveFlag() {
        return activeFlag;
    }

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
    }

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param trxDatetime
     */
    public void setTrxDatetime(Timestamp trxDatetime) {
        this.trxDatetime = trxDatetime;
    }

    /**
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    /**
     * @param wrkCode
     */
    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    /**
     * @param ministryFileNo
     */
    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    /**
     * @param cscFileNo
     */
    public void setCscFileNo(String cscFileNo) {
        this.cscFileNo = cscFileNo;
    }

    /**
     * @param hireDate
     */
    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @param startWorkingDate
     */
    public void setStartWorkingDate(Timestamp startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    /**
     * @param endJobDate
     */
    public void setEndJobDate(Timestamp endJobDate) {
        this.endJobDate = endJobDate;
    }

    /**
     * @param hirstatusCode
     */
    public void setHirstatusCode(Long hirstatusCode) {
        this.hirstatusCode = hirstatusCode;
    }

    /**
     * @param hirtypeCode
     */
    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    /**
     * @param dateOfNextRaise
     */
    public void setDateOfNextRaise(Timestamp dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    /**
     * @param techJobCode
     */
    public void setTechJobCode(String techJobCode) {
        this.techJobCode = techJobCode;
    }

    /**
     * @param jobCode
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * @param bankCode
     */
    public void setBankCode(Long bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @param bnkbranchCode
     */
    public void setBnkbranchCode(Long bnkbranchCode) {
        this.bnkbranchCode = bnkbranchCode;
    }

    /**
     * @param accountNo
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @param activeFlag
     */
    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

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

    public void setHireStatusDTO(IHireStatusDTO hireStatusDTO) {
        this.hireStatusDTO = hireStatusDTO;
    }

    public IHireStatusDTO getHireStatusDTO() {
        return hireStatusDTO;
    }

    public void setHireTypesDTO(IHireTypesDTO hireTypesDTO) {
        this.hireTypesDTO = hireTypesDTO;
    }

    public IHireTypesDTO getHireTypesDTO() {
        return hireTypesDTO;
    }

    public void setCitizensResidentsDTO(IKwtCitizensResidentsDTO citizensResidentsDTO) {
        this.citizensResidentsDTO = citizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getCitizensResidentsDTO() {
        return citizensResidentsDTO;
    }

    public void setJobsDTO(IJobsDTO jobsDTO) {
        this.jobsDTO = jobsDTO;
    }

    public IJobsDTO getJobsDTO() {
        return jobsDTO;
    }

    public void setBankBranchesDTO(BankBranchesDTO bankBranchesDTO) {
        this.bankBranchesDTO = bankBranchesDTO;
    }

    public BankBranchesDTO getBankBranchesDTO() {
        return bankBranchesDTO;
    }

    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO) {
        this.workCentersDTO = workCentersDTO;
    }

    public IWorkCentersDTO getWorkCentersDTO() {
        return workCentersDTO;
    }

    public void setTechJobsDTO(IJobsDTO techJobsDTO) {
        this.techJobsDTO = techJobsDTO;
    }

    public IJobsDTO getTechJobsDTO() {
        return techJobsDTO;
    }

    public void setBgtTypesDTO(IBgtTypesDTO bgtTypesDTO) {
        this.bgtTypesDTO = bgtTypesDTO;
    }

    public IBgtTypesDTO getBgtTypesDTO() {
        return bgtTypesDTO;
    }

    public void setBgtProgramsDTO(IBgtProgramsDTO bgtProgramsDTO) {
        this.bgtProgramsDTO = bgtProgramsDTO;
    }

    public IBgtProgramsDTO getBgtProgramsDTO() {
        return bgtProgramsDTO;
    }

    public void setPrgCode(String prgCode) {
        this.prgCode = prgCode;
    }

    public String getPrgCode() {
        return prgCode;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    public Long getTypeCode() {
        return typeCode;
    }
}
