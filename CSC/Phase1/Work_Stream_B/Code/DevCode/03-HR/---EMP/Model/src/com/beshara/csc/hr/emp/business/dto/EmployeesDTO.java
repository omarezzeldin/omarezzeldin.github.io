package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.locking.dto.ILockableItem;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.bnk.business.dto.BnkDTOFactory;
import com.beshara.csc.nl.bnk.business.entity.BnkEntityKeyFactory;
import com.beshara.csc.nl.bnk.business.entity.IBankBranchesEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.sql.Date;

import java.util.List;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class EmployeesDTO extends EmpDTO implements IEmployeesDTO, ILockableItem {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    //private Long civilId ;
    private Long minCode;
    //private String wrkCode ;
    private Long realCivilId;
    private String ministryFileNo;
    private String cscFileNo;
    private Date hireDate;
    private Date startWorkingDate;
    private Date endJobDate;
    private Boolean documentsStatus;
    private Boolean proceduresStatus;


    //private Long hirstatusCode ;
    //private Long hirtypeCode ;
    //private Long hirstageCode ;
    private Date dateOfNextRaise;
    //private String techJobCode ;
    // private String jobCode ;
    //private Long bankCode ;
    //private Long bnkbranchCode ;
    private String accountNo;
    private Long auditStatus;
    private Long activeFlag;
    private Long recordDescCode;
    private Long tabrecSerial;
    private String socialInsurNo;
    //Relations Added By Taha Abdul Mejid
    private IBasicDTO citizensResidentsDTO;
    private IBasicDTO hireStatusDTO;
    private IBasicDTO hireStageDTO;
    private IBasicDTO jobDTO;
    private IBasicDTO workCenterDTO;
    private IBasicDTO bankBrancheDTO;
    private IBasicDTO hireTypeDTO;
    private IBasicDTO techJobsDTO;
    private List<IBasicDTO> employeeDocumentsDTOList;
    private List<IBasicDTO> employeeProceduresDTOList;
    private boolean hasExperience;
    //////ADDED BY TAHA ABDUL MEJID
    private IBgtTypesDTO bgtTypesDTO;
    private IBgtProgramsDTO bgtProgramsDTO;
    private List<ISalEmpSalaryElementsDTO> salEmpSalaryElementsDTOList;
    private IEmpExtraDataValueDTO empExtraDataValueDTO;

    private List<IBasicDTO> employeeExtraDataDTOList;
    private Boolean disableIfFound;
    private IPersonQualificationsDTO personQualificationsDTO;
    private IPersonsInformationDTO personsInformationDTO;
    private ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO;
    private ISalEmpMonSalariesDTO salEmpMonSalariesDTO;

    private IEmpCandidatesDTO empCandidatesDTO;
   private Long empSignId;
    private Long candidateCode;
    private Long candidateCodeSeq;


    private String employeeName;
    private Long nationality;
    // this property used in group of Moving
    private String errorMSG;
    private String civilIds;
    private boolean UnValidStatus;
    private IKwtWorkDataDTO lastKwtWorkDataDTO;
    private IMinistriesDTO ministriesDTO;


    // this property used in ESRV  By A.kamal 06/12/2016
    private Long userCode;
    private String UserName;
    private String nationalityValue;
    
    private Date firstHireDate;

    /**
     * EmployeesDTO Default Constructor */
    public EmployeesDTO() {
        super();

    }

    public EmployeesDTO(Long civilId, Long hireStatus, Long minCode, String wrkCode, String jobCode, Long rCivilId,
                        String fullName, Long nationality) {
        IEntityKey ek = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        setCode(ek);
        this.setHireStatusKey(hireStatus.toString());
        this.minCode = minCode;
        this.setWorkCenterKey(wrkCode.toString());
        this.setJobKey(jobCode);
        this.realCivilId = rCivilId;
        IKwtCitizensResidentsEntityKey kEk = InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(rCivilId);
        citizensResidentsDTO = new KwtCitizensResidentsDTO();
        ((IKwtCitizensResidentsDTO)citizensResidentsDTO).setCode(kEk);
        ((IKwtCitizensResidentsDTO)citizensResidentsDTO).setFullNameColumn(fullName);
        ((IKwtCitizensResidentsDTO)citizensResidentsDTO).setNationality(nationality);

    }


    /**
     * @param employeesEntity
     */
    @Deprecated
    public EmployeesDTO(EmployeesEntity employeesEntity) {

        //this.civilId = employeesEntity.getCivilId ( ) ;
        //KwtCitizensResidentsEntity citizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();
        Long civilId = employeesEntity.getCivilId();
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        this.setCode(employeesEnitityKey);
        setRealCivilId(employeesEntity.getRealCivilId());

        // if (employeesEntity.getCitizensResidentsEntity() != null) {
        if (employeesEntity.getCivilId() != null) {
            // citizensResidentsDTO =  InfDTOFactory.createKwtCitizensResidentsDTO(employeesEntity.getCitizensResidentsEntity());
            try {
                // IKwtCitizensResidentsEntityKey kEk=InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(employeesEntity.getCivilId()) ;
                // you shoud send RealCivilId to KWTCitizen not civilID
                IKwtCitizensResidentsEntityKey kEk =
                    InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(employeesEntity.getRealCivilId());
                citizensResidentsDTO = InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidents(kEk);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        this.minCode = employeesEntity.getMinCode();


        // if (employeesEntity.getWorkCentersEntity() != null) {
        if (employeesEntity.getWrkCode() != null && employeesEntity.getMinCode() != null) {
            //workCenterDTO = OrgDTOFactory.createWorkCentersDTO(employeesEntity.getWorkCentersEntity());
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(employeesEntity.getMinCode(), employeesEntity.getWrkCode());
            try {
                workCenterDTO = OrgClientFactory.getWorkCentersClient().getById(wEk);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        this.ministryFileNo = employeesEntity.getMinistryFileNo();
        this.cscFileNo = employeesEntity.getCscFileNo();
        this.hireDate = employeesEntity.getHireDate();
        this.startWorkingDate = employeesEntity.getStartWorkingDate();
        this.endJobDate = employeesEntity.getEndJobDate();

        //this.hirstatusCode = employeesEntity.getHirstatusCode ( ) ;
        if (employeesEntity.getHireStatusEntity() != null) {
            hireStatusDTO = EmpDTOFactory.createHireStatusDTO(employeesEntity.getHireStatusEntity());
        }

        // this.hirtypeCode = employeesEntity.getHirtypeCode ( ) ;
        if (employeesEntity.getHireTypesEntity() != null) {
            hireTypeDTO = EmpDTOFactory.createHireTypesDTO(employeesEntity.getHireTypesEntity());
        }

        // this.hirstageCode = employeesEntity.getHirstageCode ( ) ;

        if (employeesEntity.getHireStagesEntity() != null) {
            hireStageDTO = EmpDTOFactory.createHireStagesDTO(employeesEntity.getHireStagesEntity());
            //The following code to evaluate document status and procedure status is no longer valid
            //B.Zidan 18 FEB 2014.
            //            if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_EMPLOYMENT) ||
            //                employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT) ||
            //                employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION)) {
            //                this.documentsStatus = Boolean.TRUE;
            //                this.proceduresStatus = Boolean.TRUE;
            //            } else if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_COMPLETE_DOC)) {
            //                this.documentsStatus = Boolean.TRUE;
            //                this.proceduresStatus = Boolean.FALSE;
            //            } else if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_COMPLETE_PROC)) {
            //                this.proceduresStatus = Boolean.TRUE;
            //                this.documentsStatus = Boolean.FALSE;
            //            } else if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_DEFAULT)) {
            //                this.proceduresStatus = Boolean.FALSE;
            //                this.documentsStatus = Boolean.FALSE;
            //            }
        }

        this.dateOfNextRaise = employeesEntity.getDateOfNextRaise();

        //this.techJobCode = employeesEntity.getTechJobCode ( ) ;
        //        if (employeesEntity.getJobsEntity() != null) {
        //            jobDTO = JobDTOFactory.createJobsDTO(employeesEntity.getJobsEntity());
        //        }
        if (employeesEntity.getJobCode() != null) {
            jobDTO = getJob(employeesEntity.getJobCode());
        }

        // this.jobCode = employeesEntity.getJobCode ( ) ;
        if (employeesEntity.getTechJobCode() != null) {
            techJobsDTO = getJob(employeesEntity.getTechJobCode());
            ;
        }

        //this.bankCode = employeesEntity.getBankCode ( ) ;
        //this.bnkbranchCode = employeesEntity.getBnkbranchCode ( ) ;
        //        if (employeesEntity.getBankBranchesEntity() != null) {
        //            bankBrancheDTO =
        //                    InfDTOFactory.createBankBranchesDTO(employeesEntity.getBankBranchesEntity());
        //        }
        //this.accountNo = employeesEntity.getAccountNo();
        this.setCreatedBy(employeesEntity.getCreatedBy());
        this.setCreatedDate(employeesEntity.getCreatedDate());
        this.setLastUpdatedBy(employeesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(employeesEntity.getLastUpdatedDate());
        this.auditStatus = employeesEntity.getAuditStatus();
        this.activeFlag = employeesEntity.getActiveFlag();
        this.recordDescCode = employeesEntity.getRecordDescCode();
        this.tabrecSerial = employeesEntity.getTabrecSerial();
        this.socialInsurNo = employeesEntity.getSocialInsurNo();
        ///////////////////////////////////////////////////////////////////////////////
        //try{
        if (employeesEntity.getBgtprgCode() != null) {
            //  IBgtProgramsEntityKey bgtEk=BgtEntityKeyFactory.createBgtProgramsEntityKey(employeesEntity.getBgtprgCode());
            //   this.bgtProgramsDTO = (IBgtProgramsDTO)BgtClientFactory.getBgtProgramsClient().getById(bgtEk);
            this.bgtProgramsDTO = getBGTProgramDTO(employeesEntity.getBgtprgCode());
            //this.bgtProgramsDTO = BgtDTOFactory.createBgtProgramsDTO(employeesEntity.getBgtProgramsEntity());
        }
        if (employeesEntity.getBgttypeCode() != null) {
            //IBgtTypesEntityKey bgtTEk=BgtEntityKeyFactory.createBgtTypesEntityKey(employeesEntity.getBgttypeCode());
            this.bgtTypesDTO = getBGTTypDTO(employeesEntity.getBgttypeCode());
            // this.bgtTypesDTO =  BgtDTOFactory.createBgtTypesDTO(employeesEntity.getBgtTypesEntity());
        }


        //        }catch(Exception e){
        //            throw new RuntimeException(e);
        //        }
        //   }

        String nationalityNumberVal = null;
        try {


            nationalityNumberVal = EmpClientFactory.getEmployeeExtraDataClient().getNationalityNumberByCivilId(civilId);
        } catch (Exception e) {
            nationalityNumberVal = null;
        }

        if (nationalityNumberVal != null && !nationalityNumberVal.isEmpty()) {

            this.setNationalityValue(nationalityNumberVal);

        }


     
    }

    //Added by mohamed sayed & Hany Omar at 12-5-2015 at 12:30 to load simple DTO From Employee

    public EmployeesDTO(EmployeesEntity employeesEntity, boolean isSimple) {
        Long civilId = employeesEntity.getCivilId();
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        this.setCode(employeesEnitityKey);
        setRealCivilId(employeesEntity.getRealCivilId());
        this.ministryFileNo = employeesEntity.getMinistryFileNo();
        this.cscFileNo = employeesEntity.getCscFileNo();
        this.hireDate = employeesEntity.getHireDate();
        this.startWorkingDate = employeesEntity.getStartWorkingDate();
        this.endJobDate = employeesEntity.getEndJobDate();
        
        
        
        if (employeesEntity.getCivilId() != null) {
            try {
                System.out.println("====  CHECk KWT CITIZEN  ========");
                IKwtCitizensResidentsEntityKey kEk =
                    InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(employeesEntity.getRealCivilId());
                citizensResidentsDTO = InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidents(kEk);
            } catch (Exception e) {
                System.out.println("BEGIN TEST============");
                System.out.println("CIVIL============" + civilId);
                e.printStackTrace();
                System.out.println("END OF TEST============");
                throw new RuntimeException(e);
            }
        }

        if (employeesEntity.getHireStatusEntity() != null) {
            hireStatusDTO = EmpDTOFactory.createHireStatusDTO(employeesEntity.getHireStatusEntity());
        }
    }

    public EmployeesDTO(boolean withMinCode,EmployeesEntity employeesEntity) {
        Long civilId = employeesEntity.getCivilId();
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        this.setCode(employeesEnitityKey);
        setRealCivilId(employeesEntity.getRealCivilId());
        this.ministryFileNo = employeesEntity.getMinistryFileNo();
        this.cscFileNo = employeesEntity.getCscFileNo();
        this.hireDate = employeesEntity.getHireDate();
        this.startWorkingDate = employeesEntity.getStartWorkingDate();
        this.endJobDate = employeesEntity.getEndJobDate();
        //Added 21/21/2017
        this.minCode = employeesEntity.getMinCode();
        if (employeesEntity.getCivilId() != null) {
            try {
                System.out.println("====  CHECk KWT CITIZEN  ========");
                IKwtCitizensResidentsEntityKey kEk =
                    InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(employeesEntity.getRealCivilId());
                citizensResidentsDTO = InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidents(kEk);
            } catch (Exception e) {
                System.out.println("BEGIN TEST============");
                System.out.println("CIVIL============" + civilId);
                e.printStackTrace();
                System.out.println("END OF TEST============");
                throw new RuntimeException(e);
            }
        }
    }
    
    
    
    
    
    
    

    /**
     * this constructor sets JUST the civil id and the real civil id
     * @param civilId
     * @param realCivilId
     * @author o.ezzeldin
     */
    EmployeesDTO(Long civilId, Long realCivilId) {
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        this.setCode(employeesEnitityKey);
        setRealCivilId(realCivilId);
    }

    /**
     * @return IBgtProgramsDTO
     */
    private IBgtProgramsDTO getBGTProgramDTO(String bgtCode) {

        IBgtProgramsEntityKey bgtEk = BgtEntityKeyFactory.createBgtProgramsEntityKey(bgtCode);
        try {
            return (IBgtProgramsDTO)BgtClientFactory.getBgtProgramsClient().getById(bgtEk);
        } catch (Exception e) {
            return null;
        }

    }

    private IBgtTypesDTO getBGTTypDTO(Long bgtCode) {

        IBgtTypesEntityKey bgtTEk = BgtEntityKeyFactory.createBgtTypesEntityKey(bgtCode);
        try {
            return (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(bgtTEk);
        } catch (Exception e) {
            return null;
        }

    }
    //        EmployeesDTO(Object object) {
    //        System.out.println("object"+object.getClass().getName());
    //        Vector v=(Vector)object;
    //        //this.civilId = employeesEntity.getCivilId ( ) ;
    //        //KwtCitizensResidentsEntity citizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();
    //        Long civilId = v.get();
    //        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
    //        this.setCode(employeesEnitityKey);
    //        setRealCivilId(employeesEntity.getRealCivilId());
    //        if (employeesEntity.getCitizensResidentsEntity() != null) {
    //            citizensResidentsDTO =
    //                    InfDTOFactory.createKwtCitizensResidentsDTO(employeesEntity.getCitizensResidentsEntity());
    //        }
    //
    //        //this.minCode = employeesEntity.getMinCode ( ) ;
    //        //this.wrkCode = employeesEntity.getWrkCode ( ) ;
    //
    //        if (employeesEntity.getWorkCentersEntity() != null) {
    //            workCenterDTO =
    //                    OrgDTOFactory.createWorkCentersDTO(employeesEntity.getWorkCentersEntity());
    //        }
    //        this.ministryFileNo = employeesEntity.getMinistryFileNo();
    //        this.cscFileNo = employeesEntity.getCscFileNo();
    //        this.hireDate = employeesEntity.getHireDate();
    //        this.startWorkingDate = employeesEntity.getStartWorkingDate();
    //        this.endJobDate = employeesEntity.getEndJobDate();
    //
    //        //this.hirstatusCode = employeesEntity.getHirstatusCode ( ) ;
    //        if (employeesEntity.getHireStatusEntity() != null) {
    //            hireStatusDTO =
    //                    EmpDTOFactory.createHireStatusDTO(employeesEntity.getHireStatusEntity());
    //        }
    //
    //        // this.hirtypeCode = employeesEntity.getHirtypeCode ( ) ;
    //        if (employeesEntity.getHireTypesEntity() != null) {
    //            hireTypeDTO =
    //                    EmpDTOFactory.createHireTypesDTO(employeesEntity.getHireTypesEntity());
    //        }
    //
    //        // this.hirstageCode = employeesEntity.getHirstageCode ( ) ;
    //
    //        if (employeesEntity.getHireStagesEntity() != null) {
    //            hireStageDTO =
    //                    EmpDTOFactory.createHireStagesDTO(employeesEntity.getHireStagesEntity());
    //            //The following code to evaluate document status and procedure status is no longer valid
    //            //B.Zidan 18 FEB 2014.
    //        //            if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_EMPLOYMENT) ||
    //        //                employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT) ||
    //        //                employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION)) {
    //        //                this.documentsStatus = Boolean.TRUE;
    //        //                this.proceduresStatus = Boolean.TRUE;
    //        //            } else if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_COMPLETE_DOC)) {
    //        //                this.documentsStatus = Boolean.TRUE;
    //        //                this.proceduresStatus = Boolean.FALSE;
    //        //            } else if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_COMPLETE_PROC)) {
    //        //                this.proceduresStatus = Boolean.TRUE;
    //        //                this.documentsStatus = Boolean.FALSE;
    //        //            } else if (employeesEntity.getHireStagesEntity().getHirstageCode().equals(IEMPConstant.EMP_STAGE_DEFAULT)) {
    //        //                this.proceduresStatus = Boolean.FALSE;
    //        //                this.documentsStatus = Boolean.FALSE;
    //        //            }
    //        }
    //
    //        this.dateOfNextRaise = employeesEntity.getDateOfNextRaise();
    //
    //        //this.techJobCode = employeesEntity.getTechJobCode ( ) ;
    //        if (employeesEntity.getJobsEntity() != null) {
    //            jobDTO =
    //                    JobDTOFactory.createJobsDTO(employeesEntity.getJobsEntity());
    //        }
    //
    //        // this.jobCode = employeesEntity.getJobCode ( ) ;
    //        if (employeesEntity.getTechJobsEntity() != null) {
    //            techJobsDTO =
    //                    JobDTOFactory.createJobsDTO(employeesEntity.getTechJobsEntity());
    //        }
    //
    //        //this.bankCode = employeesEntity.getBankCode ( ) ;
    //        //this.bnkbranchCode = employeesEntity.getBnkbranchCode ( ) ;
    //        //        if (employeesEntity.getBankBranchesEntity() != null) {
    //        //            bankBrancheDTO =
    //        //                    InfDTOFactory.createBankBranchesDTO(employeesEntity.getBankBranchesEntity());
    //        //        }
    //        //this.accountNo = employeesEntity.getAccountNo();
    //        this.setCreatedBy(employeesEntity.getCreatedBy());
    //        this.setCreatedDate(employeesEntity.getCreatedDate());
    //        this.setLastUpdatedBy(employeesEntity.getLastUpdatedBy());
    //        this.setLastUpdatedDate(employeesEntity.getLastUpdatedDate());
    //        this.auditStatus = employeesEntity.getAuditStatus();
    //        this.activeFlag = employeesEntity.getActiveFlag();
    //        this.recordDescCode = employeesEntity.getRecordDescCode();
    //        this.tabrecSerial = employeesEntity.getTabrecSerial();
    //        this.socialInsurNo = employeesEntity.getSocialInsurNo();
    //        ///////////////////////////////////////////////////////////////////////////////
    //        if (employeesEntity.getBgtProgramsEntity() != null)
    //            this.bgtProgramsDTO =
    //                    BgtDTOFactory.createBgtProgramsDTO(employeesEntity.getBgtProgramsEntity());
    //        if (employeesEntity.getBgtTypesEntity() != null)
    //            this.bgtTypesDTO =
    //                    BgtDTOFactory.createBgtTypesDTO(employeesEntity.getBgtTypesEntity());
    //
    //
    //    }

    //
    ///**
    //* @return Long
    //*/
    //public Long getCivilId ( ) {
    // return civilId ;
    // }

    public Long getMinCode() {
        return minCode;
    }
    //
    // /**
    // * @return String
    // */
    // public String getWrkCode ( ) {
    // return wrkCode ;
    // }

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

    ///**
    //* @return Long
    //*/
    //public Long getHirstatusCode ( ) {
    // return hirstatusCode ;
    // }
    // /**
    // * @return Long
    // */
    // public Long getHirtypeCode ( ) {
    // return hirtypeCode ;
    // }
    //
    // /**
    // * @return Long
    // */
    // public Long getHirstageCode ( ) {
    // return hirstageCode ;
    // }
    // /**
    // * @return String
    // */
    // public String getTechJobCode ( ) {
    // return techJobCode ;
    // }
    // /**
    // * @return String
    // */
    // public String getJobCode ( ) {
    // return jobCode ;
    // }
    // /**
    // * @return Long
    // */
    // public Long getBankCode ( ) {
    // return bankCode ;
    // }
    //
    // /**
    // * @return Long
    // */
    // public Long getBnkbranchCode ( ) {
    // return bnkbranchCode ;
    // }

    /**
     * @return String
     */
    public String getAccountNo() {
        return accountNo;
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
    } ///**
    //* @param civilId
    //*/
    //public void setCivilId ( Long civilId ) {
    // this.civilId=civilId ;
    // }

    /**
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }
    //
    // /**
    // * @param wrkCode
    // */
    // public void setWrkCode ( String wrkCode ) {
    // this.wrkCode = wrkCode ;
    // }

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
    } ///**
    //* @param hirstatusCode
    //*/
    //public void setHirstatusCode ( Long hirstatusCode ) {
    // this.hirstatusCode=hirstatusCode ;
    // }
    // /**
    // * @param hirtypeCode
    // */
    // public void setHirtypeCode ( Long hirtypeCode ) {
    // this.hirtypeCode = hirtypeCode ;
    // }
    //
    // /**
    // * @param hirstageCode
    // */
    // public void setHirstageCode ( Long hirstageCode ) {
    // this.hirstageCode = hirstageCode ;
    // }
    // /**
    // * @param techJobCode
    // */
    // public void setTechJobCode ( String techJobCode ) {
    // this.techJobCode = techJobCode ;
    // }
    // /**
    // * @param jobCode
    // */
    // public void setJobCode ( String jobCode ) {
    // this.jobCode = jobCode ;
    // }
    // /**
    // * @param bankCode
    // */
    // public void setBankCode ( Long bankCode ) {
    // this.bankCode = bankCode ;
    // }
    //
    // /**
    // * @param bnkbranchCode
    // */
    // public void setBnkbranchCode ( Long bnkbranchCode ) {
    // this.bnkbranchCode = bnkbranchCode ;
    // }

    /**
     * @param accountNo
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public void setStartWorkingDate(Date startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public Date getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setEndJobDate(Date endJobDate) {
        this.endJobDate = endJobDate;
    }

    public Date getEndJobDate() {
        return endJobDate;
    }

    public void setDateOfNextRaise(Date dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    public Date getDateOfNextRaise() {
        return dateOfNextRaise;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireStatusDTO(IBasicDTO hireStatusDTO) {
        this.hireStatusDTO = hireStatusDTO;
    }

    public IBasicDTO getHireStatusDTO() {
        return hireStatusDTO;
    }

    public void setHireStageDTO(IBasicDTO hireStageDTO) {
        this.hireStageDTO = hireStageDTO;
    }

    public IBasicDTO getHireStageDTO() {
        return hireStageDTO;
    }

    public void setJobDTO(IBasicDTO jobDTO) {
        this.jobDTO = jobDTO;
    }

    public IBasicDTO getJobDTO() {
        return jobDTO;
    }

    public void setWorkCenterDTO(IBasicDTO workCenterDTO) {
        this.workCenterDTO = workCenterDTO;
    }

    public IBasicDTO getWorkCenterDTO() {
        return workCenterDTO;
    }

    public void setBankBrancheDTO(IBasicDTO bankBrancheDTO) {
        this.bankBrancheDTO = bankBrancheDTO;
    }

    public IBasicDTO getBankBrancheDTO() {
        return bankBrancheDTO;
    }

    public void setHireTypeDTO(IBasicDTO hireTypeDTO) {
        this.hireTypeDTO = hireTypeDTO;
    }

    public IBasicDTO getHireTypeDTO() {
        return hireTypeDTO;
    }

    public void setTechJobsDTO(IBasicDTO techJobsDTO) {
        this.techJobsDTO = techJobsDTO;
    }

    public IBasicDTO getTechJobsDTO() {
        return techJobsDTO;
    }

    public void setEmployeeDocumentsDTOList(List<IBasicDTO> employeeDocumentsDTOList) {
        this.employeeDocumentsDTOList = employeeDocumentsDTOList;
    }

    public List<IBasicDTO> getEmployeeDocumentsDTOList() {
        return employeeDocumentsDTOList;
    }

    public void setEmployeeProceduresDTOList(List<IBasicDTO> employeeProceduresDTOList) {
        this.employeeProceduresDTOList = employeeProceduresDTOList;
    }

    public List<IBasicDTO> getEmployeeProceduresDTOList() {
        return employeeProceduresDTOList;
    }

    public void setCitizensResidentsDTO(IBasicDTO citizensResidentsDTO) {
        this.citizensResidentsDTO = citizensResidentsDTO;
    }

    public IBasicDTO getCitizensResidentsDTO() {
        return citizensResidentsDTO;
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

    public String getJobKey() {
        if (this.jobDTO != null) {
            return (String)jobDTO.getCode().getKey();
        }
        return null;
    }

    public void setJobKey(String key) {
        if (key != null && !key.equals("")) {
            IJobsEntityKey entityKey = JobEntityKeyFactory.createJobsEntityKey(key);
            this.jobDTO = JobDTOFactory.createJobsDTO();
            jobDTO.setCode(entityKey);
        } else {
            this.jobDTO = null;
        }
    }

    public String getTechJobKey() {
        if (this.techJobsDTO != null) {
            return (String)techJobsDTO.getCode().getKey();
        }
        return null;
    }

    public void setTechJobKey(String key) {
        if (key != null && !key.equals("")) {
            IJobsEntityKey entityKey = JobEntityKeyFactory.createJobsEntityKey(key);
            this.techJobsDTO = JobDTOFactory.createJobsDTO();
            techJobsDTO.setCode(entityKey);
        }
    }

    public String getWorkCenterKey() {
        if (this.workCenterDTO != null) {
            return (String)workCenterDTO.getCode().getKey();
        }
        return null;
    }

    public void setWorkCenterKey(String key) {
        if (key != null && !key.equals("") && !key.equals(ISystemConstant.VIRTUAL_VALUE.toString())) {
            String[] keys = key.split("\\*");
            IWorkCentersEntityKey entityKey =
                OrgEntityKeyFactory.createWorkCentersEntityKey(Long.parseLong(keys[0]), keys[1]);
            this.workCenterDTO = OrgDTOFactory.createWorkCentersDTO();
            workCenterDTO.setCode(entityKey);
        }
    }

    public String getHireStatusKey() {
        if (this.hireStatusDTO != null) {
            return (String)hireStatusDTO.getCode().getKey();
        }
        return null;
    }

    public void setHireStatusKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
            IHireStatusEntityKey entityKey = EmpEntityKeyFactory.createHireStatusEntityKey(Long.parseLong(key));
            this.hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
            hireStatusDTO.setCode(entityKey);
        }
    }

    public String getHireStageKey() {
        if (this.hireStageDTO != null) {
            return (String)hireStageDTO.getCode().getKey();
        }
        return null;
    }

    public void setHireStageKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
            IHireStagesEntityKey entityKey = EmpEntityKeyFactory.createHireStagesEntityKey(Long.parseLong(key));
            this.hireStageDTO = EmpDTOFactory.createHireStagesDTO();
            hireStageDTO.setCode(entityKey);
        }
    }

    public String getHireTypeKey() {
        if (this.hireTypeDTO != null) {
            return (String)hireTypeDTO.getCode().getKey();
        }
        return null;
    }

    public void setHireTypeKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
            IHireTypesEntityKey entityKey = EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(key));
            this.hireTypeDTO = EmpDTOFactory.createHireTypesDTO();
            hireTypeDTO.setCode(entityKey);
        }
    }

    public String getBankBranchKey() {
        if (this.bankBrancheDTO != null) { //TODO set try and catch because error in jsf
            try {
                return (String)bankBrancheDTO.getCode().getKey();
            } catch (Exception e) {
                ;
            }
        }
        return null;
    }

    public void setBankBranchKey(String key) {
        if (key != null && !key.equals("")) {
            String[] keys = key.split("\\*");
            IBankBranchesEntityKey entityKey =
                BnkEntityKeyFactory.createBankBranchesEntityKey(Long.parseLong(keys[0]), Long.parseLong(keys[1]));
            this.bankBrancheDTO = BnkDTOFactory.createBankBranchesDTO();
            bankBrancheDTO.setCode(entityKey);
        } else {
            this.bankBrancheDTO = null;
        }
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

    public String getJobsKey() {
        if (this.jobDTO != null) {
            return (String)jobDTO.getCode().getKey();
        }
        return null;
    }

    public void setJobsKey(String key) {
        if (key != null && key.length() > 0) {
            IJobsEntityKey entityKey = JobEntityKeyFactory.createJobsEntityKey(key);
            this.jobDTO = JobDTOFactory.createJobsDTO();
            this.jobDTO.setCode(entityKey);
        }
    }

    public String getBgtPrgmKey() {
        if (this.bgtProgramsDTO != null) {
            return (String)bgtProgramsDTO.getCode().getKey();
        }
        return null;
    }

    public void setBgtPrgmKey(String key) {
        if (key != null && !key.equals("")) {
            IBgtProgramsEntityKey entityKey = BgtEntityKeyFactory.createBgtProgramsEntityKey(key);
            this.bgtProgramsDTO = BgtDTOFactory.createBgtProgramsDTO();
            this.bgtProgramsDTO.setCode(entityKey);
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
            IBgtTypesEntityKey entityKey = BgtEntityKeyFactory.createBgtTypesEntityKey(Long.parseLong(key));
            this.bgtTypesDTO = BgtDTOFactory.createBgtTypesDTO();
            this.bgtTypesDTO.setCode(entityKey);
        }
    } /////////////////////////////////////////////
    // public String getWorkCentersKey ( ) {
    // if ( this.workCenterDTO != null ) {
    // return ( String ) workCenterDTO.getCode ( ) .getKey ( ) ;
    // }
    // return null ;
    // }
    //
    // public void setWorkCentersKey ( String key ) {
    // if ( key != null && key.length ( ) > 0 ) {
    // String[] str = key.split ( "\\*" ) ;
    // IWorkCentersEntityKey entityKey =
    // OrgEntityKeyFactory.createWorkCentersEntityKey ( Long.parseLong ( str[0] ) , str[1] ) ;
    // this.workCenterDTO = OrgDTOFactory.createWorkCentersDTO ( ) ;
    // this.workCenterDTO.setCode ( entityKey ) ;
    // }
    // }

    public void setSalEmpSalaryElementsDTOList(List<ISalEmpSalaryElementsDTO> salEmpSalaryElementsDTOList) {
        this.salEmpSalaryElementsDTOList = salEmpSalaryElementsDTOList;
    }

    public List<ISalEmpSalaryElementsDTO> getSalEmpSalaryElementsDTOList() {
        return salEmpSalaryElementsDTOList;
    }

    public void setSocialInsurNo(String socialInsurNo) {
        this.socialInsurNo = socialInsurNo;
    }

    public String getSocialInsurNo() {
        return socialInsurNo;
    }

    public String getLockEntity() {
        return "HR_EMP_EMPLOYEES";
    }

    public String getLockId() {
        return tabrecSerial.toString();
    }


    public boolean isHasExperience() {
        if (getCode() != null) {
            try {
                //                System.out.println(getRealCivilId()); A.Kamal change civil-ID to getRealCivilId
                boolean ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(getRealCivilId());
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

    public void setEmpExtraDataValueDTO(IEmpExtraDataValueDTO empExtraDataValueDTO) {
        this.empExtraDataValueDTO = empExtraDataValueDTO;
    }

    public IEmpExtraDataValueDTO getEmpExtraDataValueDTO() {
        return empExtraDataValueDTO;
    }

    public void setEmployeeExtraDataDTOList(List<IBasicDTO> employeeExtraDataDTOList) {
        this.employeeExtraDataDTOList = employeeExtraDataDTOList;
    }

    public List<IBasicDTO> getEmployeeExtraDataDTOList() {
        return employeeExtraDataDTOList;
    }

    public void setDisableIfFound(Boolean disableIfFound) {
        this.disableIfFound = disableIfFound;
    }

    public Boolean getDisableIfFound() {
        return disableIfFound;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
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

    public void setHasExperience(boolean hasExperience) {
        this.hasExperience = hasExperience;
    }


    public void setSalEmpSalaryElementsDTO(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO) {
        this.salEmpSalaryElementsDTO = salEmpSalaryElementsDTO;
    }

    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO() {
        return salEmpSalaryElementsDTO;
    }

    public void setSalEmpMonSalariesDTO(ISalEmpMonSalariesDTO salEmpMonSalariesDTO) {
        this.salEmpMonSalariesDTO = salEmpMonSalariesDTO;
    }

    public ISalEmpMonSalariesDTO getSalEmpMonSalariesDTO() {
        return salEmpMonSalariesDTO;
    }

    @Override
    public void setRecordDescCode(Long recordDescCode) {
        this.recordDescCode = recordDescCode;
    }

    @Override
    public Long getRecordDescCode() {
        return recordDescCode;
    }

    private IBasicDTO getJob(String jobCode) {
        try {
            return JobClientFactory.getJobsClient().getById(JobEntityKeyFactory.createJobsEntityKey(jobCode));
        } catch (Exception e) {
            return null;
        }
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCodeSeq(Long candidateCodeSeq) {
        this.candidateCodeSeq = candidateCodeSeq;
    }

    public Long getCandidateCodeSeq() {
        return candidateCodeSeq;
    }

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO) {
        this.empCandidatesDTO = empCandidatesDTO;
    }

    public IEmpCandidatesDTO getEmpCandidatesDTO() {
        return empCandidatesDTO;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    public Long getNationality() {
        return nationality;
    }

    public void setErrorMSG(String errorMSG) {
        this.errorMSG = errorMSG;
    }

    public String getErrorMSG() {
        return errorMSG;
    }

    public void setCivilIds(String civilIds) {
        this.civilIds = civilIds;
    }

    public String getCivilIds() {
        return civilIds;
    }

    public void setUnValidStatus(boolean UnValidStatus) {
        this.UnValidStatus = UnValidStatus;
    }

    public boolean isUnValidStatus() {
        return UnValidStatus;
    }

    public void setLastKwtWorkDataDTO(IKwtWorkDataDTO lastKwtWorkDataDTO) {
        this.lastKwtWorkDataDTO = lastKwtWorkDataDTO;
    }

    public IKwtWorkDataDTO getLastKwtWorkDataDTO() {
        return lastKwtWorkDataDTO;
    }

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO) {
        this.ministriesDTO = ministriesDTO;
    }

    public IMinistriesDTO getMinistriesDTO() {
        return ministriesDTO;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserName() {
        return UserName;
    }


    public void setNationalityValue(String nationalityValue) {
        this.nationalityValue = nationalityValue;
    }

    public String getNationalityValue() {
        return nationalityValue;
    }

    public void setFirstHireDate(Date firstHireDate) {
        this.firstHireDate = firstHireDate;
    }

    public Date getFirstHireDate() {
        return firstHireDate;
    }

    public void setEmpSignId(Long empSignId) {
        this.empSignId = empSignId;
    }

    public Long getEmpSignId() {
        return empSignId;
    }
}
