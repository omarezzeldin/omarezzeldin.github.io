package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;


import com.beshara.base.dao.DAOFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.paging.impl.PagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.base.security.IMinistryInfo;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ICheckCivilIdOnConditionDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionRelationsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.emp.business.dao.EmpCandidatesDAO;
import com.beshara.csc.hr.emp.business.dao.EmployeeExtraDataDAO;
import com.beshara.csc.hr.emp.business.dao.EmployeesDAO;
import com.beshara.csc.hr.emp.business.dao.HireTypesDAO;
import com.beshara.csc.hr.emp.business.dao.RequiredDocumentsDAO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.EmpSearchRecodDesc;
import com.beshara.csc.hr.emp.business.dto.EmpStatusForSalDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IChangeBudgetTypeDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpBonusData;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpFinicialData;
import com.beshara.csc.hr.emp.business.dto.IEmployeeDTOService;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IVacEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.shared.ISimpleEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.sal.business.client.ISalEmpSalaryElementsCMTClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.IEmpSalaryElementsSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.ISALConstants;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.IPersonQualificationsCMTClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IPersonQualificationsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.qul.business.entity.IQualificationsEntityKey;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;
import com.beshara.csc.sharedutils.business.exception.emp.EmployeeAlreadyEmploymentException;
import com.beshara.csc.sharedutils.business.exception.emp.EquivalentDataDoesNotExist;
import com.beshara.csc.sharedutils.business.exception.emp.InvalidHireStatusException;
import com.beshara.csc.sharedutils.business.exception.emp.NotMatchedOnJobGRSConditionException;
import com.beshara.csc.sharedutils.business.exception.fil.MaxNoOfRecordsRequiredException;
import com.beshara.csc.sharedutils.business.exception.fil.PageNumRequiredException;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;

import java.rmi.RemoteException;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents the Business Object Wrapper ( as Session Bean ) for Business Component IpIuIbIlIiIsIhIiInIgI.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "EmployeesSession", mappedName = "Emp-Model-EmployeesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote( { EmployeesSession.class, EmployeesCMTSession.class })
public class EmployeesSessionBean extends EmpBaseSessionBean implements EmployeesSession,
                                                                        EmployeesCMTSession { //@PersistenceContext ( unitName = "Emp" )

    /**
     * JobsSession Default Constructor */
    public EmployeesSessionBean() {

    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmployeesEntity.class;
    }

    @Override
    protected EmployeesDAO DAO() {
        return (EmployeesDAO)super.DAO();
    }
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Add new employee * @param employeesDTO1
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
     //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                             DataBaseException {
        try {
            return DAO().add(employeesDTO1);
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            throw wrapIntoDataBaseException(e);
        }
    }


    /**
     * To add new Employee * @param employeesDTO1
     * @param usingTransAction
     * @return IEmployeesDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO employeesDTO1,
                         boolean usingTransAction) throws SharedApplicationException, DataBaseException {
        initRiForBsnLog("em/HRM", ri);
        IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
        //        EmployeeProceduresDAO empprocDAO =
        //            ((EmployeeProceduresDAO)super.newDAOInstance(EmployeeProceduresEntity.class));
        //        EmployeeDocumentsDAO empDocDAO = (EmployeeDocumentsDAO)super.newDAOInstance(EmployeeDocumentsEntity.class);
        Long civilId = ((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId();
        //-------------start of added part to make sure that IKwtCitizensResidentsDTO exist at local , if not exist it will select from center and insert local ------------------------
        IKwtCitizensResidentsClient kwtCitizensResidentsClient = InfClientFactory.getKwtCitizensResidentsClient();
        IKwtCitizensResidentsEntityKey kwtCitizensResidentsEntityKey =
            InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId);
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = null;
        boolean citizenInLocal = false;
        try {
            kwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)kwtCitizensResidentsClient.getCitizenInformation(civilId);
            citizenInLocal = true;
        } catch (Exception e) {
            kwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)kwtCitizensResidentsClient.getByIdInCenter(kwtCitizensResidentsEntityKey);
        }
        if (!citizenInLocal) {
            kwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)kwtCitizensResidentsClient.addInLocal(kwtCitizensResidentsDTO);
        } //-------------End of added part to make sure that IKwtCitizensResidentsDTO exist at local , if not exist it will select from center and insert local ------------------------
        /*Check if Start work date greater than hire date*/
        this.checkHireAndStartWorkDate(employeesDTO);
        //set date of next raise
        employeesDTO = this.addDateOfNextRaise(employeesDTO);
        IEmployeesDTO addedDTO = null;
        try { //begin the transaction
            // beginTransaction();
            //add main data of employee
            employeesDTO.setHireStageDTO(EmpDTOFactory.createHireStagesDTO());
            employeesDTO.getHireStageDTO().setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_DEFAULT));
            employeesDTO.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO());
            employeesDTO.getHireStatusDTO().setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.EMP_STATUS_CANDIDATE));
            addedDTO = (IEmployeesDTO)DAO().add(employeesDTO);
            employeesDTO.setCode(addedDTO.getCode());
            //SET EMPLOYEE SALARY
            this.addSalEmployeeSalary(employeesDTO);
            //get list of document valid for employee depend on gender type , nationality type and hire type
            //            try {
            //                List<IBasicDTO> documentList =
            //                    (empDocDAO).listAvailableEntities(addedDTO.getCode(), addedDTO.getHireTypeDTO().getCode());
            //                for (IBasicDTO reqdocument : documentList) {
            //                    IEmployeeDocumentsDTO employeeDocumentsDTO = EmpDTOFactory.createEmployeeDocumentsDTO();
            //                    employeeDocumentsDTO.setDocumentTypeDTO(((IRequiredDocumentsDTO)reqdocument).getDocumentTypeDTO());
            //                    employeeDocumentsDTO.setEmployeesDTO(addedDTO);
            //                    //set status equal default value
            //                    employeeDocumentsDTO.setStatus(0L);
            //                    //add decoument
            //                    empDocDAO.add(employeeDocumentsDTO);
            //                }
            //            } catch (ItemNotFoundException e) {
            //                ;
            //            } //get list of procedure valid for employee depend on gender type and nationality type
            //  try {
            //                List<IBasicDTO> proceduresList = empprocDAO.listAvailableEntities(addedDTO.getCode());
            //                for (IBasicDTO procedure : proceduresList) {
            //                    IEmployeeProceduresDTO employeeProceduresDTO = EmpDTOFactory.createEmployeeProceduresDTO();
            //                    employeeProceduresDTO.setHireProcedureDTO(procedure);
            //                    employeeProceduresDTO.setEmployeesDTO(addedDTO);
            //                    IProcedureResultsDTO procedureResultsDTO = EmpDTOFactory.createProcedureResultsDTO();
            //                    procedureResultsDTO.setCode(EmpEntityKeyFactory.createProcedureResultsEntityKey(IEMPConstant.EMP_RESULT_NONE));
            //                    //set result equal defalt result
            //                    employeeProceduresDTO.setProcedureResultsDTO(procedureResultsDTO);
            //                    //set send date equal current date
            //                    employeeProceduresDTO.setSendDate(SharedUtils.getCurrentDate());
            //                    //add procedure
            //
            //                    empprocDAO.add(employeeProceduresDTO);
            //
            //
            //                }
            //            } catch (ItemNotFoundException e) {
            //                ;
            //            } /*--Add qualification Info for specific person--*/
            List list =
                ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList();
            if (list != null && list.size() > 0) {
                IPersonQualificationsDTO personQualificationsDTO =
                    ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList().get(0);
                personQualificationsDTO.setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO());
                personQualificationsDTO.setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId(),
                                                                                                        ((IQualificationsEntityKey)personQualificationsDTO.getQualificationsDTO().getCode()).getQualificationKey().toString()));
                this.addPersonQualification(personQualificationsDTO);
            } //get hire type code
            Long hireTypeCode = ((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode();
            this.checkLegelJobDTO(ri, civilId, (IJobsEntityKey)employeesDTO.getJobDTO().getCode());
            //check if employee hire type is legal
            this.checkLegelHireType(ri, civilId, hireTypeCode);


            System.out.println("------------------------BEFORE--------COMMIT---------------------------------");
            // commitTransaction();
            System.out.println("------------------------AFTER--------COMMIT---------------------------------");
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            System.out.println("------------------------BEFORE--------FROM CATCH ItemNotFoundException---------------------------------");
            rollbackTransaction();
            System.out.println("------------------------BEFORE--------FROM CATCH ItemNotFoundException---------------------------------");
            throw new InvalidDataEntryException();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            System.out.println("------------------BEFORE--------------FROM CATCH SHARED---------------------------------");
            rollbackTransaction();
            System.out.println("-------------------AFTER-------------FROM CATCH SHARED---------------------------------");
            throw e;
        } catch (com.beshara.base.transaction.TransactionException e) {
            e.printStackTrace();
            System.out.println("----------------BEFORE----------------FROM CATCH TRANSACTION---------------------------------");
            rollbackTransaction();
            System.out.println("------------------AFTER--------------FROM CATCH TRANSACTION---------------------------------");
            throw e;
        }
        return addedDTO;
    }
    //@Auditable
    private IBasicDTO addSalEmployeeSalary(IEmployeesDTO employeesDTO) throws InvalidDataEntryException,
                                                                              ItemNotFoundException, DataBaseException,
                                                                              SharedApplicationException {

        if (employeesDTO.getSalEmpSalaryElementsDTOList() == null ||
            employeesDTO.getSalEmpSalaryElementsDTOList().size() == 0)
            throw new InvalidDataEntryException();
        ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = employeesDTO.getSalEmpSalaryElementsDTOList().get(0);
        salEmpSalaryElementsDTO.setElementRatio(ISalConstants.EMP_SALARY_ELEMENT_DEFAULT_RATIO);
        salEmpSalaryElementsDTO.setEmployeesDTO(employeesDTO);
        salEmpSalaryElementsDTO.setCode(SalEntityKeyFactory.createSalEmpSalaryElementsEntityKey(null, null,
                                                                                                employeesDTO.getStartWorkingDate()));
        ISalElementGuidesDTO salEmpSalaryElementsDTOOld = null;
        ISalElementGuidesDTO salEmpSalaryElementsDTONew = null;
        salEmpSalaryElementsDTOOld =
                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode());
        if (salEmpSalaryElementsDTOOld != null &&
            ((ISalElementGuidesDTO)salEmpSalaryElementsDTOOld.getParentObject()).getEqType().equals(ISalConstants.EQ_TYPE_TEMP)) {
            if (salEmpSalaryElementsDTO.getSalEqElementGuidesDTO() == null)
                throw new EquivalentDataDoesNotExist();
            salEmpSalaryElementsDTONew =
                    (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(salEmpSalaryElementsDTO.getSalEqElementGuidesDTO().getCode());
            salEmpSalaryElementsDTO.setSalElementGuidesDTO(salEmpSalaryElementsDTONew);
            salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(salEmpSalaryElementsDTOOld);
        }

        // added by nora
        salEmpSalaryElementsDTO.setFromDate(employeesDTO.getHireDate());
        salEmpSalaryElementsDTO.setEmpCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
        return SalClientFactory.getSalEmpSalaryElementsCMTClient().add(employeesDTO.getSalEmpSalaryElementsDTOList().get(0));
        //  return SalDAOFactory.getSalEmpSalaryElementsDAO().add(employeesDTO.getSalEmpSalaryElementsDTOList().get(0));
    }


    public Boolean checkLegelJobDTO(IRequestInfoDTO ri, Long civilID,
                                    IJobsEntityKey jobCode) throws SharedApplicationException, DataBaseException {
        initRiForBsnLog("em/HRM", ri);
        IJobsDTO jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(jobCode);
        System.out.println("job Code>>>>>>>>>>>>>>>>" + jobsDTO.getCode().getKey());
        if (jobsDTO.getConditionsDTO() != null && civilID != null) {
            if (DAO().checkGRSCondition(jobsDTO.getConditionsDTO().getCode(),
                                        InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilID)).equals(Boolean.FALSE)) {

                NotMatchedOnJobGRSConditionException notMatchedOnJobGRSConditionException =
                    new NotMatchedOnJobGRSConditionException();
                List<IResultDTO> linesResultList =
                    GrsClientFactory.getConditionsClient().executeGRSCondition(Long.valueOf(jobsDTO.getConditionsDTO().getCode().getKey().toString()),
                                                                               civilID);
                notMatchedOnJobGRSConditionException.setExtraInformation(linesResultList);
                throw notMatchedOnJobGRSConditionException;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Check if citizen is legel to employment with specific hire type * @param civilID
     * @param hireType
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean checkLegelHireType(IRequestInfoDTO ri, Long civilID,
                                      Long hireType) throws SharedApplicationException,
                                                            DataBaseException { //check if employee hire type is legal
        //        IHireTypesDTO hireTypeDTO =
        //            (IHireTypesDTO)EmpDAOFactory.getHireTypesDAO().getById(EmpEntityKeyFactory.createHireTypesEntityKey(hireType));
        HireTypesDAO hireTypeDao = (HireTypesDAO)super.newDAOInstance(HireTypesEntity.class);
        IHireTypesDTO hireTypeDTO = hireTypeDao.getById(EmpEntityKeyFactory.createHireTypesEntityKey(hireType));

        System.out.println("hireTypeDTO Code>>>>>>>>>>>>>>>>" + hireTypeDTO.getCode().getKey());
        //        if (hireTypeDTO.getConditionsDTO() != null && civilID != null) {
        //            if (getDAO().checkGRSCondition(hireTypeDTO.getConditionsDTO().getCode(),
        //                                           InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilID)).equals(Boolean.FALSE)) {
        //                NotMatchedOnHireTypeException notMatchedOnHireTypeException = new NotMatchedOnHireTypeException();
        //                List<IResultDTO> linesResultList =
        //                    GrsClientFactory.getConditionsClient().executeGRSCondition(Long.valueOf(hireTypeDTO.getConditionsDTO().getCode().getKey().toString()),
        //                                                                               civilID);
        //                notMatchedOnHireTypeException.setExtraInformation(linesResultList);
        //                throw notMatchedOnHireTypeException;
        //            }
        //
        //        }
        return Boolean.TRUE;
    }

    /**
     * Add SalEmployee Salary * @param employeesDTO
     * @return IBasicDTO
     * @throws InvalidDataEntryException
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
     //@Auditable
    private IEmployeesDTO addDateOfNextRaise(IEmployeesDTO employeesDTO) throws InvalidDataEntryException {
        //        if (employeesDTO.getDateOfNextRaise() !=
        //            null) { //check if next raise less than hire date
        //            if (employeesDTO.getHireDate() != null &&
        //                employeesDTO.getDateOfNextRaise() != null &&
        //                employeesDTO.getHireDate().compareTo(employeesDTO.getDateOfNextRaise()) >
        //                0) {
        //                throw new InvalidDataEntryException();
        //            }
        //        }
        if (employeesDTO.getHireDate() != null) {
            employeesDTO.setDateOfNextRaise(calculateNextRaiseDate(employeesDTO.getHireDate()));
        }
        return employeesDTO;
    }

    private Date calculateNextRaiseDate(Date hireDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(hireDate);
        String sqlMonth = "";

        if (cal.get(Calendar.MONTH) == 0 && cal.get(Calendar.DAY_OF_MONTH) == 1) {
            sqlMonth = "1";
            cal.add(Calendar.YEAR, 1);
        } else if (cal.get(Calendar.MONTH) < 6 ||
                   (cal.get(Calendar.MONTH) == 6 && cal.get(Calendar.DAY_OF_MONTH) == 1)) {
            sqlMonth = "7";
            cal.add(Calendar.YEAR, 1);
        } else {
            sqlMonth = "1";
            cal.add(Calendar.YEAR, 2);
        }
        return Date.valueOf("" + cal.get(Calendar.YEAR) + "-0" + sqlMonth + "-01");
    }

    public Date calculateNextRaiseDate(IRequestInfoDTO ri, Date hireDate) throws DataBaseException,
                                                                                 SharedApplicationException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(hireDate);
        String sqlMonth = "";

        if (cal.get(Calendar.MONTH) == 0 && cal.get(Calendar.DAY_OF_MONTH) == 1) {
            sqlMonth = "1";
            cal.add(Calendar.YEAR, 1);
        } else if (cal.get(Calendar.MONTH) < 6 ||
                   (cal.get(Calendar.MONTH) == 6 && cal.get(Calendar.DAY_OF_MONTH) == 1)) {
            sqlMonth = "7";
            cal.add(Calendar.YEAR, 1);
        } else {
            sqlMonth = "1";
            cal.add(Calendar.YEAR, 2);
        }
        return Date.valueOf("" + cal.get(Calendar.YEAR) + "-0" + sqlMonth + "-01");
    }
    //@Auditable
    private void addPersonQualification(IPersonQualificationsDTO personQualificationsDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            InfClientFactory.getPersonQualificationsClient().getByIdInCenter(personQualificationsDTO.getCode());
        } catch (ItemNotFoundException e) {
            personQualificationsDTO.setGradeTypeCode(ICRSConstant.GRADE_TYPE_PERCENTAGE);
            InfClientFactory.getPersonQualificationsClient().add(personQualificationsDTO);
        }
    }

    /**
     * check start work date and hire date * @param employeesDTO
     * @return Boolean
     * @throws InvalidDataEntryException
     */
    private Boolean checkHireAndStartWorkDate(IEmployeesDTO employeesDTO) throws InvalidDataEntryException {
        if (employeesDTO.getHireDate() != null && employeesDTO.getStartWorkingDate() != null &&
            SharedUtils.compareDatesOnly(employeesDTO.getHireDate(), employeesDTO.getStartWorkingDate()) > 0) {
            throw new InvalidDataEntryException();
        }
        return Boolean.TRUE;
    }

    //@Auditable
    private void addPersonQualificationCMT(IEmployeesDTO employeesDTO) throws DataBaseException,
                                                                              SharedApplicationException {


        System.out.println("EmployeeBO.addPersonQualificationCMT :: Start");
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO();
        List<IPersonQualificationsDTO> personQualifications = kwtCitizensResidentsDTO.getPersonQualificationsDTOList();
        if (personQualifications == null || personQualifications.size() == 0) {
            throw new InvalidDataEntryException();
        }

        IPersonQualificationsDTO personQualificationsDTO = personQualifications.get(0);
        System.out.println("kwtCitizensResidentsDTO.getCode()).getCivilId()>>>" +
                           ((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId());
        System.out.println("((IQualificationsEntityKey)personQualificationsDTO.getQualificationsDTO().getCode()).getQualificationKey()>>>" +
                           (((IQualificationsEntityKey)personQualificationsDTO.getQualificationsDTO().getCode()).getQualificationKey()));

        IPersonQualificationsEntityKey key =
            InfEntityKeyFactory.createPersonQualificationsEntityKey(((IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode()).getCivilId(),
                                                                    ((IQualificationsEntityKey)personQualificationsDTO.getQualificationsDTO().getCode()).getQualificationKey().toString());

        boolean qulFound = false;
        boolean transactionBegun = isTransactionBegun();
        IPersonQualificationsCMTClient personQualificationsCMTClient = null;
        ;
        try {
            if (transactionBegun) {
                suspendTransaction();
            }
            personQualificationsCMTClient = InfClientFactory.getPersonQualificationsCMTClient();
            personQualificationsDTO =
                    (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getByIdInCenter(key);
            System.out.println("Person Qulification exist @ Center");
            qulFound = true;
        } catch (ItemNotFoundException e) {
            //            personQualificationsCMTClient =
            //                    InfClientFactory.getPersonQualificationsCMTClient();
            System.out.println("Person Qulification Not exist @ Center && Not exist @ Local");
        } finally {
            if (transactionBegun) {
                resumeTransaction();
            }
        }

        if (!qulFound) {
            personQualificationsDTO.setCode(key);
            personQualificationsDTO.setKwtCitizensResidentsDTO(kwtCitizensResidentsDTO);
            personQualificationsDTO.setGradeTypeCode(ICRSConstant.GRADE_TYPE_PERCENTAGE);
            System.out.println("personQualificationsCMTClient.add::Start");
            personQualificationsDTO.setCrsRegistrationOrder(1L);
            personQualificationsDTO =
                    (IPersonQualificationsDTO)personQualificationsCMTClient.add(personQualificationsDTO);
            System.out.println("personQualificationsCMTClient.add::End && personQualificationsDTO.code>>" +
                               personQualificationsDTO.getCode().getKey().toString());

        } else {
            System.out.println("personQualificationsCMTClient.updateRegisterationOrderCMT::Start");
            personQualificationsCMTClient.updateRegisterationOrderCMT(personQualificationsDTO);
            System.out.println("personQualificationsCMTClient.updateRegisterationOrderCMT::End && registerion order >>>" +
                               personQualificationsDTO.getCrsRegistrationOrder());
        }
    }
    /////////////////////////////////////////////////////////////////////////////

    public List<IBasicDTO> search(IRequestInfoDTO ri, Long hirtypeCode) throws DataBaseException,
                                                                               SharedApplicationException {

        return DAO().search(hirtypeCode);

    }

    /**
     * TODO remove commented Code from Dao and remove method from session
     * and use getByRealCivil method
     * @param ri
     * @param id1
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IEmployeesDTO getById(IRequestInfoDTO ri, IEntityKey id1) throws DataBaseException,
                                                                            SharedApplicationException {
        EmployeeExtraDataDAO employeeExtraDataDAO =
            (EmployeeExtraDataDAO)DAOFactoryUtil.getInstance(EmployeeExtraDataEntity.class);
        IEmployeesDTO employeesDTO = DAO().getById(id1);
        Long civilId = null;
        if (employeesDTO != null) {
            civilId = Long.valueOf(employeesDTO.getCode().getKey());
        }
        // get Employee Salary
        try {
            List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList =
                (List)SalClientFactory.getSalEmpSalaryElementsClient().getEmpSalaryElementByCivilId(civilId);
            employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
        } catch (Exception e) {
            employeesDTO.setSalEmpSalaryElementsDTOList(new ArrayList());
        }
        // get EmployeeExtraData
        try {
            List<IEmployeeExtraDataDTO> employeeExtraDataDTOList =
                employeeExtraDataDAO.getAllEmployeeExtraDataByCivilId(civilId);
            if (employeeExtraDataDTOList != null && employeeExtraDataDTOList.size() > 0) {
                employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
                for (IEmployeeExtraDataDTO dto : employeeExtraDataDTOList) {
                    Long extdattypeCode = Long.valueOf(dto.getEmpExtraDataTypesDTO().getCode().getKey());
                    if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(dto.getValue());

                    }
                }
            }
            employeesDTO.setEmployeeExtraDataDTOList((List)employeeExtraDataDTOList);
        } catch (Exception e) {
            employeesDTO.setEmployeeExtraDataDTOList(new ArrayList());
        }

        return employeesDTO;

    }

    public List<IBasicDTO> searchByMainHireType(IRequestInfoDTO ri, Long hirtypeCode) throws DataBaseException,
                                                                                             SharedApplicationException {

        return DAO().searchByMainHireType(hirtypeCode);


    }

    public List<IBasicDTO> getAllEmployeesWithActiveHireTypes(IRequestInfoDTO ri) throws DataBaseException,
                                                                                         SharedApplicationException {

        return DAO().getAllEmployeesWithActiveHireTypes();


    }

    /**
     * Hany Omar 16/2/2014
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     */

    public List<IBasicDTO> filterByHireType(IRequestInfoDTO ri, Long hirtypeCode) throws DataBaseException,
                                                                                         SharedApplicationException {

        return DAO().filterByHireType(hirtypeCode);


    }


    /**
     * Hany Omar 18/2/2014
     * @param hireTypeList
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> filterByAllHireTypes(IRequestInfoDTO ri) throws DataBaseException,
                                                                           SharedApplicationException {

        return DAO().filterByAllHireTypes();


    }

    /**
     * filter employees by hire types for employee hire execution
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     * @author Dina
     */
    public List<IBasicDTO> filterByHireTypeForHireExecute(IRequestInfoDTO ri,
                                                          Long hirtypeCode) throws DataBaseException,
                                                                                   SharedApplicationException {

        return DAO().filterByHireTypeForHireExecute(hirtypeCode);

    }

    public List<IBasicDTO> filterByAllHireTypesForHireExecute(IRequestInfoDTO ri) throws DataBaseException,
                                                                                         SharedApplicationException {

        return DAO().filterByAllHireTypesForHireExecute();

    }

    /**
     * Get all employee copmleted their documents , procedures and waiting for hire decision * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllEmployeeWaitingForHireDecision(IRequestInfoDTO ri) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().getAllEmployeeWaitingForHireDecision();

    }

    /**
     * This method to send procedure for list of employee and check if the procedure valid to send or not * @param employeeDTOList
     * @return list of resultDTO
     */
    public List<IResultDTO> sendProcedure(IRequestInfoDTO ri,
                                          List<IBasicDTO> employeeDTOList) throws DataBaseException,
                                                                                  SharedApplicationException {
        return null;

    }


    /**
     * Filter employee waiting for hire decision depend on hire type 22/2/2009 * @param hireTypesEntityKey
     * @return list of employee
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO ri,
                                                                   IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                         SharedApplicationException {
        return DAO().getFilterEmployeeWaitingForHireDecision(hireTypesEntityKey);

    }

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> simpleSearch(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().simpleSearch(basicDTO);

    }

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> simpleSearchForAOE(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().simpleSearchForAOE(basicDTO);

    }


    /**
     * search hired employees and return list of employees whose civilIDs are not in the * @param basicDTO the IEmployeesDTO search criteria DTO
     * @param salEmpSalaryElementsDTOList the list of employees to be not included in the search result set
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchFilterEmployee(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                List<IBasicDTO> salEmpSalaryElementsDTOList) throws SharedApplicationException,
                                                                                                    DataBaseException {
        return DAO().searchFilterEmployee(basicDTO, salEmpSalaryElementsDTOList);

    }

    /**
//     * To add new Employee * @param employeesDTO1
//     * @param usingTransAction
//     * @return IEmployeesDTO
//     * @throws RemoteException
//     * @throws SharedApplicationException
//     */
    //    public IBasicDTO add(IBasicDTO employeesDTO1, boolean usingTransAction) throws SharedApplicationException,
    //                                                                                   RemoteException, DataBaseException {
    //        return DAO().add(employeesDTO1, usingTransAction);
    //
    //    }

    /**
     * Join Employee To an exist Decision * @param employeesList
     * @param decisionDTO
     * @return ResultDTO
     * @throws DataBaseException
     */
     //@Auditable
    public List<ResultDTO> addJoinToDecision(IRequestInfoDTO ri, List<IBasicDTO> employeesList,
                                             IBasicDTO decisionDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        List<ResultDTO> resultList = new ArrayList<ResultDTO>();
        for (IBasicDTO dto : employeesList) {
            List<IBasicDTO> empDecisionList = new ArrayList<IBasicDTO>();
            IEmpDecisionsDTO empDecision = RegDTOFactory.createEmpDecisionsDTO();
            empDecision.setDecisionsDTO(decisionDTO);
            IEmployeesDTO emp = (IEmployeesDTO)dto;
            emp.getActiveFlag();
            Long civilId = Long.valueOf(emp.getCode().getKey().toString());
            empDecision.setEmployeesDTO(emp);
            empDecision.setTabrecSerialRef(emp.getTabrecSerial());

            empDecision.setTableName(ISystemConstant.TABLE_HR_EMP_EMPLOYEES);
            empDecisionList.add(empDecision);
            ResultDTO resultDTO = RegClientFactory.getEmpDecisionsClient().joinToDecision(empDecisionList).get(0);

            if (resultDTO.getStatus().equals(ISystemConstant.ITEM_ADDED)) {

                if (emp.getHireStageDTO() == null)
                    emp.setHireStageDTO(EmpDTOFactory.createHireStagesDTO());
                emp.getHireStageDTO().setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT));
                DAO().update(emp);

                // added by nora to handle HR-1048 >>>>>> 14
                //                if (!executeRequiredProceduresForHireEmployee(civilId).equals(1L)) {
                //                    throw new InvalidDataEntryException();
                //                }
            }
            resultList.add(resultDTO);
        }
        // TODO
        // Commented By Nora to suspend File Creation(7-4-2010) as ahmed Abd EL fata7 and Hatem Reference
        //        for (ResultDTO resultDTO : resultList) {
        //            String fileNo = null;
        //            try {
        //                IBasicDTO fileDTO =
        //                    FilClientFactory.getPersonFilesClient().createEmploymentFileForEmp(((IEmployeesEntityKey)((IEmpDecisionsDTO)resultDTO.getCurrentObject()).getEmployeesDTO().getCode()).getCivilId());
        //                if (fileDTO != null)
        //                    fileNo = ((IPersonFilesEntityKey)fileDTO.getCode()).getFileNum();
        //            } catch (Exception e) { // resultDTO.setBusinessException ( new EmpOperationDoneFileOperationFailException ( ) ) ;
        //                throw new EmpOperationDoneFileOperationFailException();
        //            }
        //            if (fileNo != null) { // ( ( IEmployeesDTO ) resultDTO.getCurrentObject ( ) ) .setCscFileNo ( fileNo ) ;
        //                //getDAO().update ( resultDTO.getCurrentObject ( ) ) ;
        //                IEmpDecisionsDTO empDecision = (IEmpDecisionsDTO)resultDTO.getCurrentObject();
        //                ((IEmployeesDTO)empDecision.getEmployeesDTO()).setCscFileNo(fileNo);
        //                getDAO().update(empDecision.getEmployeesDTO());
        //            } else { // resultDTO.setBusinessException ( new EmpOperationDoneFileOperationFailException ( ) ) ;
        //                throw new EmpOperationDoneFileOperationFailException();
        //            }
        //        }


        return resultList;
    } //////////////////////////////////////////////

    /**
     * Change employee budget type * @param changeBudgetTypeDTO1
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public List<ResultDTO> changeBudgetType(IRequestInfoDTO ri,
                                            IBasicDTO changeBudgetTypeDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IChangeBudgetTypeDTO changeBudgetTypeDTO = (IChangeBudgetTypeDTO)changeBudgetTypeDTO1;
        List<ResultDTO> list = new ArrayList<ResultDTO>();
        boolean transactionBegun = isTransactionBegun();
        if (changeBudgetTypeDTO.getEmployeesDTOList() != null) { //I setTrxLogVar set here because hit on database once
            DAO().setTrxLogVar();
            if (transactionBegun) {
                suspendTransaction();
            }
            for (IBasicDTO basicDTO : changeBudgetTypeDTO.getEmployeesDTOList()) {
                try { /*BEGIN TRANSACTION*/
                    beginTransaction();
                    IEmployeesDTO empDTO = (IEmployeesDTO)basicDTO;
                    /*UPDATE EMPLOYEE WITH NEW BUDGET TYPE*/
                    empDTO.setBgtTypesDTO(changeBudgetTypeDTO.getBgtTypesDTO());
                    DAO().update(basicDTO);
                    DAO().keepLog(((IEmployeesEntityKey)empDTO.getCode()).getCivilId(),
                                  IEMPConstant.EMPLOYEE_KEEP_LOG_TRX_TYPE, changeBudgetTypeDTO.getApplyDate(), null);
                    /*COMMIT ALL */
                    commitTransaction();
                } catch (ItemNotFoundException e) {
                    SystemException se = new SystemException(e);
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO.setCurrentObject(basicDTO);
                    resultDTO.setStatus(IEMPConstant.EMP_NOT_SEND_PROC);
                    resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                    list.add(resultDTO);
                    rollbackTransaction();
                    //                } catch (RemoteException e) {
                    //                    SystemException se = new SystemException(e);
                    //                    ResultDTO resultDTO = new ResultDTO();
                    //                    resultDTO.setCurrentObject(basicDTO);
                    //                    resultDTO.setStatus(IEMPConstant.EMP_NOT_SEND_PROC);
                    //                    resultDTO.setDatabaseException(new DataBaseException(se.getSQLExceptionMessage()));
                    //                    list.add(resultDTO);
                    //                    rollbackTransaction();
                } catch (TransactionException e) {
                    ;
                } finally {
                    if (transactionBegun) {
                        resumeTransaction();
                    }
                }
            } //I setTrxLogVar set here because hit on database once
            DAO().releaseTrxLogVar();
        } else {
            return null;
        }
        return list;

    }

    /**
     * get employee info with his salary element info ( valid ) , for specific type * @param civilId
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author Amir Nasr
     */
    public IBasicDTO getEmployeeAndPayrollByElmType(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException {

        IEmployeesDTO empDTO =
            (IEmployeesDTO)DAO().getEmployeeInfoByElmType(civilId, ISalConstants.ELEMENT_GUIDE_TYPE_RAISE, ISALConstants.ELEMENT_GUIDE_TYPE_RAISE_2);
        //    empDTO.setSalEmpSalaryElementsDTOList(new ArrayList());

        //        empDTO.getSalEmpSalaryElementsDTOList().add((ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(((IEmployeesEntityKey)empDTO.getCode()).getCivilId(),
        //                                                                                                                                                         ISalConstants.ELEMENT_GUIDE_TYPE_RAISE));
        //        ISalElementGuidesDTO salElmGuideDTO;
        //
        //
        //        try{
        //        salElmGuideDTO =
        //                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(((ISalElementGuidesEntityKey)empDTO.getSalEmpSalaryElementsDTOList().get(0).getSalElementGuidesDTO().getCode()).getElmguideCode()));
        //        }
        //        catch(Exception e) {
        //        e.printStackTrace();
        //
        //        }
        //        if (salElmGuideDTO != null) {
        //            ISalElementGuidesDTO salElmGuideDegreeDTO =
        //                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(empDTO.getSalEmpSalaryElementsDTOList().get(0).getSalElementGuidesDTO().getElmguideCodeDegree()));
        //            ISalElementGuidesDTO salElmGuideGroupDTO =
        //                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(salElmGuideDegreeDTO.getElmguideCodeDegree()));
        //            ISalElementGuidesDTO salElmGuideCaderDTO =
        //                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getByCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(salElmGuideGroupDTO.getElmguideCodeDegree()));
        //            empDTO.getSalEmpSalaryElementsDTOList().get(0).getSalElementGuidesDTO().setCaderName(salElmGuideCaderDTO.getName());
        //            empDTO.getSalEmpSalaryElementsDTOList().get(0).getSalElementGuidesDTO().setGroupName(salElmGuideGroupDTO.getName());
        //            empDTO.getSalEmpSalaryElementsDTOList().get(0).getSalElementGuidesDTO().setDegreeName(salElmGuideDegreeDTO.getName());
        //        }

        return empDTO;

    }

    /**
     * used in to filter employees by WorkCenter * @param workCenterCode
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllEmployeesByWorkCenter(IRequestInfoDTO ri,
                                                       String workCenterCode) throws DataBaseException,
                                                                                     SharedApplicationException {

        IEmpEmployeeSearchDTO employeeSearchDTO = EmpDTOFactory.createEmpEmployeeSearchDTO();
        employeeSearchDTO.setWorkCenterCode(workCenterCode);
        return DAO().simpleSearch(employeeSearchDTO);


    }

    /**
     * find if an employee exists in a certain ministry * @param civilId
     * @param minCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isEmployeeInMinistry(IRequestInfoDTO ri, Long civilId, Long minCode) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().isEmployeeInMinistry(civilId, minCode);

    }

    /**
     * Add Decision to employee to hire * @param employeesList
     * @param basicDecisionDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @throws RemoteException
     */
     //@Auditable
    public Boolean addDecision(IRequestInfoDTO ri, List<IBasicDTO> employeesList,
                               IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {
        initRiForBsnLog("em/HRM", ri);
        List<IBasicDTO> empDecisionDTOList = new ArrayList<IBasicDTO>();
        List<IBasicDTO> empDTOList = employeesList;
        //GET lIST OF EMPLOYEE CANDIDATE AND FILL EMPLOYEE DECISION DTO
        boolean transactionBegun = isTransactionBegun();
        try {
            if (transactionBegun) {
                suspendTransaction();
            }
            beginTransaction();
            Long civilId = ((IEmployeesEntityKey)((IEmployeesDTO)empDTOList.get(0)).getCode()).getCivilId();
            for (IBasicDTO empCandDTO : empDTOList) {
                IEmpDecisionsDTO empDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
                empDecisionsDTO.setEmployeesDTO(empCandDTO);
                empDecisionsDTO.setTabrecSerialRef(((IEmployeesDTO)empCandDTO).getTabrecSerial());
                empDecisionsDTO.setTableName(ISystemConstant.TABLE_HR_EMP_EMPLOYEES);
                empDecisionDTOList.add(empDecisionsDTO);
                ((IEmployeesDTO)empCandDTO).getHireStageDTO().setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT));
                DAO().update(empCandDTO);
            }
            IDecisionsDTO decisionDTO = (IDecisionsDTO)basicDecisionDTO;
            decisionDTO.setEmpDecisionsDTOList(empDecisionDTOList);
            //ADD DECISION TO EMPLOYEE
            decisionDTO = (IDecisionsDTO)RegClientFactory.getDecisionsClient().add(decisionDTO, false);
            // added by nora to handle HR-1048 >>>>>> 14
            if (!DAO().executeRequiredProceduresForHireEmployee(civilId).equals(1L))
                throw new InvalidDataEntryException();
            commitTransaction();
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (DataBaseException e) {
            rollbackTransaction();
            throw e;
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            rollbackTransaction();
        } finally {
            if (transactionBegun) {
                resumeTransaction();
            }
        }

        return Boolean.TRUE;

    }


    /**
     * Get all employee after hire decision to start work * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToStartWork(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getAllEmployee(IEMPConstant.EMP_STATUS_CANDIDATE, IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT);

    }

    /**
     * Search employee after hire decision to start work * @param civilId
     * @param name
     * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployeeStartWork(IRequestInfoDTO ri, Long civilId,
                                                   String name) throws DataBaseException, SharedApplicationException {
        IEmpEmployeeSearchDTO dto = EmpDTOFactory.createEmpEmployeeSearchDTO();
        dto.setCivilId(civilId);
        dto.setEmpName(name);
        dto.setEmpHireStage(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT);
        dto.setEmpHireStatus(IEMPConstant.EMP_STATUS_CANDIDATE);
        return DAO().simpleSearch(dto);

    }

    /**
     * execute start work * @param employeesDTO1
     * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public Boolean executeStartWork(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws DataBaseException,
                                                                                        SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmployeesDTO employeesDTO = (IEmployeesDTO)DAO().getById(employeesDTO1.getCode());
        ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
        try {
            salEmpSalaryElementsDTO.setEmpCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
            salEmpSalaryElementsDTO.setFromDate(((IEmployeesDTO)employeesDTO1).getStartWorkingDate());
            System.out.println("Date>>>>" + salEmpSalaryElementsDTO.getFromDate());
            SalClientFactory.getSalEmpSalaryElementsClient().updateSalEmpSalaryElements(salEmpSalaryElementsDTO);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        employeesDTO.setStartWorkingDate(((IEmployeesDTO)employeesDTO1).getStartWorkingDate());
        this.checkHireAndStartWorkDate(employeesDTO);
        employeesDTO.getHireStageDTO().setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_STARTING_WORK));
        employeesDTO.getHireStatusDTO().setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.EMP_STATUS_EMPLOYMENT));
        return DAO().update(employeesDTO);
    }

    /**
     * Get all employee to complete employee's date * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToCompleteData(IRequestInfoDTO ri) throws DataBaseException,
                                                                           SharedApplicationException {
        IEmpEmployeeSearchDTO dto = EmpDTOFactory.createEmpEmployeeSearchDTO();
        dto.setEmploymentStage(IEMPConstant.EMP_STAGE_DEFAULT + " , " + IEMPConstant.EMP_STAGE_COMPLETE_DOC + " , " +
                               IEMPConstant.EMP_STAGE_COMPLETE_PROC);
        dto.setEmpHireStatus(IEMPConstant.EMP_STATUS_CANDIDATE);
        return DAO().simpleSearch(dto);

    }

    public boolean checkEmployeeRequiredProceduresStatus(IRequestInfoDTO ri, Long serial) throws DataBaseException,
                                                                                                 SharedApplicationException {
        boolean status = false;
        Long result = ISystemConstant.VIRTUAL_VALUE;
        result = DAO().checkEmployeeRequiredProceduresStatus(serial);
        return (result == IEMPConstant.EMP_RESULT_GOOD);
    }

    public boolean checkEmployeeRequiredDocumentsStatus(IRequestInfoDTO ri, Long serial) throws DataBaseException,
                                                                                                SharedApplicationException {

        boolean status = false;
        Long result = ISystemConstant.VIRTUAL_VALUE;

        result = DAO().checkEmployeeRequiredDocumentsStatus(serial);
        status = result == IEMPConstant.EMP_DOCUMENT_COMPLETED;
        return status;

    }

    /**
     * Search employee to complete employee's date * @param employeeSearchDTO
     * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployeeToCompleteData(IRequestInfoDTO ri,
                                                        IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        List<IBasicDTO> employeesList;
        boolean tempDocStatus = false;
        boolean tempProcStatus = false;


        IEmpEmployeeSearchDTO _dto = (IEmpEmployeeSearchDTO)employeeSearchDTO;
        _dto.setEmploymentStage(IEMPConstant.EMP_STAGE_DEFAULT + " , " +
                                IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION);
        _dto.setEmpHireStatus(IEMPConstant.EMP_STATUS_CANDIDATE);
        employeesList = DAO().completeDataSimpleSearch(_dto);

        for (IBasicDTO dto : employeesList) {
            Long serial = ((IEmployeesEntityKey)((IEmployeesDTO)dto).getCode()).getCivilId();

            tempDocStatus = checkEmployeeRequiredDocumentsStatus(ri, serial);
            tempProcStatus = checkEmployeeRequiredProceduresStatus(ri, serial);

            ((IEmployeesDTO)dto).setDocumentsStatus(tempDocStatus);
            ((IEmployeesDTO)dto).setProceduresStatus(tempProcStatus);

        }
        return employeesList;


    }

    /**
     * Get all employees in a given ministry * @param minCode
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getEmployeesByMinistry(IRequestInfoDTO ri, Long minCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().getEmployeesByMinistry(minCode);

    }

    /**
     * check if employee is hired * @param employeesEntityKey
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean isEmployeeHired(IRequestInfoDTO ri, IEntityKey employeesEntityKey) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().isEmployeeHired(employeesEntityKey);

    }

    public Boolean isEmployeeHiredForVac(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().isEmployeeHiredForVac(realCivilId, minCode);
    }

    public Boolean isEmployeeHired(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        return DAO().isEmployeeHired(realCivilId, minCode);

    }


    /**
     * Get all employee match search criteria and sorted by full name * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployee(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                         SharedApplicationException {
        return DAO().searchEmployee(basicDTO);

    }

    /**
     * Validate employee civil id if it exists or nt then validate his hire type * @param civilID
     * @param hireType
     * @return TRUE : Employee exist and his hire type is legal
     * FALSE : Employee doesnt exist * throws NotMatchedOnHireTypeException : not a legal hire type * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean validateEmployeeHireType(IRequestInfoDTO ri, Long civilID, Long hireType) throws DataBaseException,
                                                                                                    SharedApplicationException {

        Boolean empExist = DAO().isEmployeeExist(civilID);
        if (empExist) { //check if employee hire type is legal
            //    IHireTypesDTO hireTypeDTO =(IHireTypesDTO)EmpDAOFactory.HireTypesDAO().getById(EmpEntityKeyFactory.createHireTypesEntityKey(hireType));

            //            if (hireTypeDTO.getConditionsDTO() != null && civilID != null) {
            //                try {
            //                    if (GrsClientFactory.getConditionsClient().checkCivilIdOnCondition(((IConditionsEntityKey)hireTypeDTO.getConditionsDTO().getCode()).getConditionCode(),
            //                                                                                       civilID).equals(Boolean.FALSE))
            //                        throw new NotMatchedOnHireTypeException();
            //                } catch (DataBaseException e) {
            //                    e.printStackTrace();
            //                }
            //                return Boolean.TRUE;
            //            }
        } else //means Employee doesnt exist
            return Boolean.FALSE;

        return null;

    }

    /**
     * Get all employees match search criteria and sorted by full name for sal cal * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployeeForSalCalc(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {
        return DAO().searchEmployeeForSalCalc(basicDTO);

    }

    public List<IBasicDTO> searchMerVariableEmployee(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().searchMerVariableEmployee(basicDTO);
    }

    public List<IBasicDTO> searchEmployeeForSalPayment(IRequestInfoDTO ri,
                                                       IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().searchEmployeeForSalPayment(basicDTO);

    }

    /**
     * get employee info for specific type * @param civilId
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author MLotfy
     */
    public IBasicDTO getEmployeeInfoByElmType(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                       SharedApplicationException {
        IEmployeesDTO empDTO =
            (IEmployeesDTO)DAO().getEmployeeInfoByElmType(civilId, ISalConstants.ELEMENT_GUIDE_TYPE_RAISE, ISALConstants.ELEMENT_GUIDE_TYPE_RAISE_2);
        return empDTO;
    }

    public IBasicDTO getEmployeeInfoHasSocialRaise(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                            SharedApplicationException {
        return DAO().getEmployeeDTOCodeName(civilId);

    }

    public IEmployeesDTO getCurrentEmpFinancialData(IRequestInfoDTO ri, IEntityKey id1) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().getCurrentEmpFinancialData(id1);

    }

    /**
     * check if employee valid to add request to end his/her service * @param civilId
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isEmployeeValidToEndService(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                        SharedApplicationException {
        return DAO().isEmployeeValidToEndService(civilId);

    }
    //@Auditable
    public Boolean updateEmployeeDataForMov(IRequestInfoDTO ri, Long civilId, Long employeeHireStatus,
                                            Date endJobDate) throws DataBaseException, SharedApplicationException {
        return DAO().updateEmployeeDataForMov(civilId, employeeHireStatus, endJobDate);

    }

    //    public IBasicDTO addEmployeeForMovOnly(IBasicDTO employeesDTO1) throws RemoteException, SharedApplicationException,
    //                                                                           DataBaseException {
    //        return DAO().addEmployeeForMovOnly(employeesDTO1);
    //
    //    }

    public List<IBasicDTO> getEmployeByCivilId(IRequestInfoDTO ri, Long civilID) throws DataBaseException,
                                                                                        SharedApplicationException {
        return DAO().getEmployeByCivilId(civilID);
    }

    public List<IBasicDTO> searchEmployeesForEvaluator(IRequestInfoDTO ri, Long evaluatorCivilId,
                                                       IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().searchEmployeesForEvaluator(evaluatorCivilId, employeeSearchDTO);
    }

    public List<IBasicDTO> searchEmployeesForEvaluatorByCivilId(IRequestInfoDTO ri, Long evaluatorCivilId,
                                                                IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().searchEmployeesForEvaluatorByCivilId(evaluatorCivilId, employeeSearchDTO);
    }

    public List<IBasicDTO> searchEmployeesForEvaluatorByName(IRequestInfoDTO ri, Long evaluatorCivilId,
                                                             IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {
        return DAO().searchEmployeesForEvaluatorByName(evaluatorCivilId, employeeSearchDTO);
    }
    //
    //    public Date calculateNextRaiseDate(Date hireDate) throws RemoteException {
    //        return DAO().calculateNextRaiseDate(hireDate);
    //    }

    //**************************************************


    /**
     * Filter employee waiting for hire decision depend on hire type * @param empEmployeeSearchDTO
     * @return list of employee
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO ri,
                                                                   IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                                          SharedApplicationException {

        IEmpEmployeeSearchDTO dto = (IEmpEmployeeSearchDTO)empEmployeeSearchDTO;
        dto.setEmpHireStage(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION);
        dto.setEmpHireStatus(IEMPConstant.EMP_STATUS_CANDIDATE); //this line was changed by moatazz mansour
        return DAO().simpleSearch(dto);


    }


    public IPagingResponseDTO simpleSearchWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                     IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                          SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountWithPaging(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().simpleSearchWithPaging(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;
    }

    /**
     * @author [m.Sayed]
     * @param ri
     * @param basicDTO
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IPagingResponseDTO simpleSearchEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                             SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchEmpCountWithPaging(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().simpleSearchEmpWithPaging(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;
    }

    public List<IBasicDTO> simpleSearchBasic(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        return DAO().simpleSearchBasic(basicDTO);

    }

    public IPagingResponseDTO simpleSearchBasicWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                               SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountBasicWithPaging(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().simpleSearchBasicWithPaging(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }


    public IPagingResponseDTO simpleSearchBasicWithPagingAtt(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                               SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountBasicWithPagingAtt(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().simpleSearchBasicWithPagingAtt(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }


    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountVacWithPaging(basicDTO));
            }

            List<IBasicDTO> result = null;
            IVacEmployeeSearchDTO vacEmployeeSearchDTO = (IVacEmployeeSearchDTO)basicDTO;
            if (vacEmployeeSearchDTO.isGroupVac())
                result = DAO().simpleSearchGroupVacEmpWithPaging(basicDTO, requestDTO);
            else
                result = DAO().simpleSearchVacEmpWithPaging(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;

    }

    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,IBgtProgramsDTO level,
                                                           IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountVacWithPaging(basicDTO,level));
            }

            List<IBasicDTO> result = null;
                result = DAO().simpleSearchGroupVacEmpWithPaging(basicDTO,level, requestDTO);



            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;

    }

    public List<IBasicDTO> simpleSearchVacEmp(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().searchVacEmp(basicDTO);
    }

    public List<IBasicDTO> simpleSearchVacEmpAllData(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().searchVacEmpGetAll(basicDTO);
    }


    public List simpleSearchVacEmpEnhanced(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().searchVacEmpEnhanced(basicDTO);
    }


    public List<IBasicDTO> searchVacEmp(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().searchVacEmp(basicDTO);

    }


    public List<IBasicDTO> getAllEmployeeByStageAndMinistry(IRequestInfoDTO ri, Long minCode,
                                                            String selectedStage) throws DataBaseException,
                                                                                         SharedApplicationException {
        return DAO().getAllEmployeeByStageAndMinistry(minCode, selectedStage);

    }


    private Map<String, IBasicDTO> initDocMap(IEmployeesDTO oldEmp) {
        Map<String, IBasicDTO> docMap = new HashMap<String, IBasicDTO>();
        if (oldEmp.getEmployeeDocumentsDTOList() != null)
            for (IBasicDTO empDocDTO : oldEmp.getEmployeeDocumentsDTOList()) {
                docMap.put(((String)empDocDTO.getCode().getKey()), empDocDTO);
            }
        return docMap;
    } //____________________________________CR HR-669________________________________________//

    /**
     * Update Employee Data * @param employeesDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
     //@Auditable
    public IBasicDTO updateEmp(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                                   DataBaseException {
        initRiForBsnLog("em/HRM", ri);
        IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
        /*Check if Start work date greater than hire date*/
        this.checkHireAndStartWorkDate(employeesDTO);

        //set date of next raise
        employeesDTO = this.addDateOfNextRaise(employeesDTO);

        //used to update stage to complete document
        boolean documentStatus = true;
        //used to update stage to complete procedure
        boolean procedureStatus = true;


        //////////////
        RequiredDocumentsDAO reqDAO = ((RequiredDocumentsDAO)super.newDAOInstance(RequiredDocumentsEntity.class));
        //        EmployeeProceduresDAO empprocDAO =
        //            ((EmployeeProceduresDAO)super.newDAOInstance(EmployeeProceduresEntity.class));
        //        EmployeeDocumentsDAO empDocDAO = (EmployeeDocumentsDAO)super.newDAOInstance(EmployeeDocumentsEntity.class);
        //////////////////////
        //get old hire type code
        IEmployeesDTO oldEmp = (IEmployeesDTO)DAO().getById(employeesDTO.getCode());
        if (((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode().equals(IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN)) {

            if (!(((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_END_SERVICE) ||
                  ((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_CANDIDATE) ||
                  ((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_MOVING)))
                throw new EmployeeAlreadyEmploymentException();

        }
        Long oldHireTypeCode = ((IHireTypesEntityKey)oldEmp.getHireTypeDTO().getCode()).getHirtypeCode();
        Long hireTypeCode = ((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode();

        Map<String, IBasicDTO> empDocMap = null;
        if (!(oldHireTypeCode.equals(hireTypeCode)))
            empDocMap = this.initDocMap(oldEmp);
        //update list of employee documents
        //        if (employeesDTO.getEmployeeDocumentsDTOList() != null) {
        //            for (IBasicDTO employeeDocumentsDTO : employeesDTO.getEmployeeDocumentsDTOList()) {
        //                if (employeeDocumentsDTO.getStatusFlag() ==
        //                    null) { //Check if that document is required for that hire type
        //                    //and set document status
        //                    if (!((((IEmployeeDocumentsDTO)employeeDocumentsDTO).getStatus()).equals(IEMPConstant.EMP_DOCUMENT_COMPLETED)) &&
        //                        reqDAO.checkRequired(hireTypeCode,
        //                                             (((IDocumentTypesEntityKey)(((IEmployeeDocumentsDTO)employeeDocumentsDTO).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE))
        //                        documentStatus = false;
        //                    empDocDAO.update(employeeDocumentsDTO);
        //                } else if (employeeDocumentsDTO.getStatusFlag() != null &&
        //                           employeeDocumentsDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
        //                    ((IEmployeeDocumentsDTO)employeeDocumentsDTO).setEmployeesDTO(employeesDTO);
        //                    //Check if that document is required for that hire type
        //                    //and set document status
        //                    if (!((((IEmployeeDocumentsDTO)employeeDocumentsDTO).getStatus()).equals(IEMPConstant.EMP_DOCUMENT_COMPLETED)) &&
        //                        reqDAO.checkRequired(hireTypeCode,
        //                                             (((IDocumentTypesEntityKey)(((IEmployeeDocumentsDTO)employeeDocumentsDTO).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE))
        //                        documentStatus = false;
        //                    employeeDocumentsDTO.setCode(EmpEntityKeyFactory.createEmployeeDocumentsEntityKey(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId(),
        //                                                                                                      ((IDocumentTypesEntityKey)((IEmployeeDocumentsDTO)employeeDocumentsDTO).getDocumentTypeDTO().getCode()).getDoctypeCode()));
        //                    if (empDocMap == null) {
        //                        empDocDAO.add(employeeDocumentsDTO);
        //                    } else if (empDocMap.get(employeeDocumentsDTO.getCode().getKey()) == null) {
        //                        empDocDAO.add(employeeDocumentsDTO);
        //                    } else {
        //                        empDocMap.remove(employeeDocumentsDTO.getCode().getKey());
        //                    }
        //                }
        //            }
        //        } //remove old doc list
        //        if (!(oldHireTypeCode.equals(hireTypeCode)))
        //            this.removeOldDecouments((HashMap)empDocMap);
        //update list of employee procedure
        //        if (employeesDTO.getEmployeeProceduresDTOList() != null) {
        //            for (IBasicDTO employeeProceduresDTO : employeesDTO.getEmployeeProceduresDTOList()) {
        //                if (employeeProceduresDTO.getStatusFlag() ==
        //                    null) { //check if any procedure result not done then set procedure= false
        //                    if (!validateEmpProcedureStatus((IEmployeeProceduresDTO)employeeProceduresDTO))
        //                        procedureStatus = false;
        //                    empprocDAO.update(employeeProceduresDTO);
        //                } else if (employeeProceduresDTO.getStatusFlag() != null &&
        //                           employeeProceduresDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
        //                    ((IEmployeeProceduresDTO)employeeProceduresDTO).setEmployeesDTO(employeesDTO);
        //                    //check if any procedure result not done then set procedure= false
        //                    if (!validateEmpProcedureStatus((IEmployeeProceduresDTO)employeeProceduresDTO))
        //                        procedureStatus = false;
        //                    empprocDAO.add(employeeProceduresDTO);
        //                }
        //            }
        //        } //check for added procedure or add required document
        List<IBasicDTO> availableDocumentList = null;
        List<IBasicDTO> availableProcedureList = null;

        //using try and catch here to void item not found from listAvailableEntities method
        //        availableDocumentList =
        //                empDocDAO.listAvailableEntities(employeesDTO.getCode(), employeesDTO.getHireTypeDTO().getCode());


        //        availableProcedureList = empprocDAO.listAvailableEntities(employeesDTO.getCode());

        //        if (availableDocumentList != null && availableDocumentList.size() > 0) {
        //            for (IBasicDTO reqDocument : availableDocumentList) {
        //                if (reqDAO.checkRequired(hireTypeCode,
        //                                         (((IDocumentTypesEntityKey)(((IRequiredDocumentsDTO)reqDocument).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE)) {
        //                    documentStatus = false;
        //                    break;
        //                }
        //            }
        //        }
        if (availableProcedureList != null && availableProcedureList.size() > 0) {
            procedureStatus = false;
        } //update employee stage
        Long hireStageCode = ((IHireStagesEntityKey)employeesDTO.getHireStageDTO().getCode()).getHirstageCode();
        System.out.println("Stageeeeeeeeeeeee>>>>>>>>>>" + hireStageCode);
        if (hireStageCode.equals(IEMPConstant.EMP_STAGE_DEFAULT) ||
            hireStageCode.equals(IEMPConstant.EMP_STAGE_COMPLETE_DOC) ||
            hireStageCode.equals(IEMPConstant.EMP_STAGE_COMPLETE_PROC) ||
            hireStageCode.equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION)) {
            IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
            Long code =
                (documentStatus == true && procedureStatus == true) ? IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION :
                (documentStatus == true) ? IEMPConstant.EMP_STAGE_COMPLETE_DOC :
                (procedureStatus == true) ? IEMPConstant.EMP_STAGE_COMPLETE_PROC : IEMPConstant.EMP_STAGE_DEFAULT;
            stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(code));
            employeesDTO.setHireStageDTO(stage);
        } else if (hireStageCode.equals(IEMPConstant.EMP_STAGE_STARTING_WORK) &&
                   hireTypeCode.equals(IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN)) {
            IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
            stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_DEFAULT));
            employeesDTO.setHireStageDTO(stage);
            employeesDTO.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO());
            employeesDTO.getHireStatusDTO().setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.EMP_STATUS_CANDIDATE));
            employeesDTO.setEndJobDate(null);
        }

        if (hireTypeCode.equals(IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN) &&
            ((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_END_SERVICE))
            this.addSalEmployeeSalary(employeesDTO);
        else
            // modify to remove old row and add new only , so kuwit citizen has only one salEmpSalary Element ( one row ) before he become employee
            this.updateSalEmpSalaryDTOnly(employeesDTO);
        DAO().setTrxLogVar();
        DAO().update(employeesDTO);
        DAO().keepLog(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId(),
                      IEMPConstant.EMPLOYEE_KEEP_LOG_TRX_TYPE, SharedUtils.getCurrentDate(), null);
        DAO().releaseTrxLogVar();
        /*--Add qualification Info for specific person--*/
        List list =
            ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList();
        if (list != null && list.size() > 0) {
            IPersonQualificationsDTO personQualificationsDTO =
                ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getPersonQualificationsDTOList().get(0);
            personQualificationsDTO.setKwtCitizensResidentsDTO((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO());
            personQualificationsDTO.setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId(),
                                                                                                    ((IQualificationsEntityKey)personQualificationsDTO.getQualificationsDTO().getCode()).getQualificationKey().toString()));
            this.addPersonQualification(personQualificationsDTO);
        }
        this.checkLegelJobDTO(ri,
                              ((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId(),
                              (IJobsEntityKey)employeesDTO.getJobDTO().getCode());
        //check if employee hire type is legal
        this.checkLegelHireType(ri,
                                ((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId(),
                                ((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode());


        return employeesDTO;

    }

    /**
     * validate employee procedure status code if good OR suspended return true else false
     * suspended and good will be the same handling REC057
     * by Ashraf Gaber
     * @param employeeProceduresDTO
     * @return boolean
     */
    //    private boolean validateEmpProcedureStatus(IEmployeeProceduresDTO employeeProceduresDTO) {
    //        Long procedureCode =
    //            ((IProcedureResultsEntityKey)(employeeProceduresDTO.getProcedureResultsDTO().getCode())).getProresultCode();
    //        if (procedureCode.equals(IEMPConstant.EMP_RESULT_GOOD) ||
    //            procedureCode.equals(IEMPConstant.EMP_RESULT_SUSPENDED)) {
    //            return true;
    //        }
    //        return false;
    //    }

    /**
     * remove old decouments if user change hire type * @param map
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    //    private void removeOldDecouments(HashMap map) throws DataBaseException, SharedApplicationException {
    //        EmployeeDocumentsDAO empDocDAO = ((EmployeeDocumentsDAO)super.newDAOInstance(EmployeeDocumentsEntity.class));
    //        if (!map.isEmpty()) {
    //            Iterator iterate = map.values().iterator();
    //            while (iterate.hasNext()) {
    //                empDocDAO.remove((IBasicDTO)iterate.next());
    //            }
    //        }
    //    }


    /**
     * update SalEmployee Salary * @param employeesDTO
     * @return IBasicDTO
     * @throws InvalidDataEntryException
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
     //@Auditable
    private IBasicDTO updateSalEmpSalaryDTOnly(IEmployeesDTO employeesDTO) throws InvalidDataEntryException,
                                                                                  ItemNotFoundException,
                                                                                  DataBaseException,
                                                                                  SharedApplicationException {

        if (employeesDTO.getSalEmpSalaryElementsDTOList() != null) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO =
                (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId(),
                                                                                                                     ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
            Long eqElmguideCodePK = null;
            if (salEmpSalaryElementsDTO.getSalEqElementGuidesDTO() != null)
                eqElmguideCodePK =
                        ((ISalElementGuidesEntityKey)salEmpSalaryElementsDTO.getSalEqElementGuidesDTO().getCode()).getElmguideCode();
            Long eqElmguideCode = null;
            if (employeesDTO.getSalEmpSalaryElementsDTOList().get(0).getSalEqElementGuidesDTO() != null)
                eqElmguideCode =
                        ((ISalElementGuidesEntityKey)employeesDTO.getSalEmpSalaryElementsDTOList().get(0).getSalEqElementGuidesDTO().getCode()).getElmguideCode();
            Long elmguideCodePK =
                ((ISalElementGuidesEntityKey)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode()).getElmguideCode();
            Long elmguideCode =
                ((ISalElementGuidesEntityKey)employeesDTO.getSalEmpSalaryElementsDTOList().get(0).getSalElementGuidesDTO().getCode()).getElmguideCode();
            Date oldFromDate = salEmpSalaryElementsDTO.getFromDate();
            Date newFromDate = employeesDTO.getHireDate();
            //ADD NEW RAISE
            if (this.equalsObject(eqElmguideCodePK, eqElmguideCode) && elmguideCodePK.equals(elmguideCode) &&
                (SharedUtils.compareDatesOnly(oldFromDate, newFromDate) == 0)) {
                return salEmpSalaryElementsDTO;
            } else { //set element guide type equal degree because change in sal element guide
                Calendar cal = Calendar.getInstance();
                cal.setTime(SharedUtils.getCurrentDate());
                cal.add(Calendar.DATE, -1);
                salEmpSalaryElementsDTO.setUntilDate(new Date(cal.getTime().getTime()));
                salEmpSalaryElementsDTO.setSalStopReasonsDTO(SalDTOFactory.createSalStopReasonsDTO());
                salEmpSalaryElementsDTO.getSalStopReasonsDTO().setCode(SalEntityKeyFactory.createSalStopReasonsEntityKey(ISalConstants.STOP_REASON_INVALID_ENTRY));
                employeesDTO.getSalEmpSalaryElementsDTOList().get(0).setElementRatio(ISalConstants.EMP_SALARY_ELEMENT_DEFAULT_RATIO);
                employeesDTO.getSalEmpSalaryElementsDTOList().get(0).setCode(SalEntityKeyFactory.createSalEmpSalaryElementsEntityKey(null,
                                                                                                                                     null,
                                                                                                                                     SharedUtils.getCurrentDate()));
                employeesDTO.getSalEmpSalaryElementsDTOList().get(0).setEmployeesDTO(employeesDTO);
                // //UPDATE OLD RAISE commented by nora
                // SalDAOFactory.getSalEmpSalaryElementsDAO ( ) .update ( salEmpSalaryElementsDTO ) ;
                salEmpSalaryElementsDTO = employeesDTO.getSalEmpSalaryElementsDTOList().get(0);
                ISalElementGuidesDTO salEmpSalaryElementsDTOOld = null;
                ISalElementGuidesDTO salEmpSalaryElementsDTONew = null;
                salEmpSalaryElementsDTOOld =
                        (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode());
                if (salEmpSalaryElementsDTOOld != null &&
                    ((ISalElementGuidesDTO)salEmpSalaryElementsDTOOld.getParentObject()).getEqType().equals(ISalConstants.EQ_TYPE_TEMP)) {
                    if (salEmpSalaryElementsDTO.getSalEqElementGuidesDTO() == null)
                        throw new EquivalentDataDoesNotExist();
                    salEmpSalaryElementsDTONew =
                            (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(salEmpSalaryElementsDTO.getSalEqElementGuidesDTO().getCode());
                    salEmpSalaryElementsDTO.setSalElementGuidesDTO(salEmpSalaryElementsDTONew);
                    salEmpSalaryElementsDTO.setSalEqElementGuidesDTO(salEmpSalaryElementsDTOOld);
                }
                salEmpSalaryElementsDTO.setFromDate(employeesDTO.getHireDate());
                System.out.println("employeesDTO.getHireDate()>>>>>>>" + employeesDTO.getHireDate());
                salEmpSalaryElementsDTO.setEmpCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
                //validate if new sal emp salary will overlap with closed old sal emp salary element
                Long hireTypeCode = ((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode();
                if (hireTypeCode.equals(IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN)) {
                    IEmpSalaryElementsSearchCriteriaDTO searchDTO =
                        SalDTOFactory.createEmpSalaryElementsSearchCriteriaDTO();
                    searchDTO.setFromDate(employeesDTO.getHireDate());
                    searchDTO.setElmGuideType(ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
                    searchDTO.setCivilId(Long.valueOf(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId()));
                    try {
                        suspendTransaction();
                        SalClientFactory.getSalEmpSalaryElementsClient().validateSalEmpRaiseWithOldRaise(searchDTO);
                    } finally {
                        resumeTransaction();
                    }
                }
                return SalClientFactory.getSalEmpSalaryElementsCMTClient().updateSalEmpSalaryElements(employeesDTO.getSalEmpSalaryElementsDTOList().get(0));
            }
        }
        return null;
    }


    private boolean equalsObject(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        return o1.equals(o2);
    }
    //@Auditable
    public IBasicDTO updateEmpCancelCandidate(IRequestInfoDTO ri,
                                              IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                              DataBaseException {
        IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
        // update DAO
        DAO().update(employeesDTO1);
        return employeesDTO;


    }

    /**
     * return EmployeeDTO civilId and KwtCitizensResidentsDTO fullName with auditing attributes
     * @param civilID
     * @return
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author Ashraf Gaber 19/01/2011
     */
    public IBasicDTO getEmployeeDTOCodeName(IRequestInfoDTO ri, Long civilID) throws DataBaseException,
                                                                                     SharedApplicationException {
        return DAO().getEmployeeDTOCodeName(civilID);
    }

    public Long[] getEmpServicePeriod(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                               SharedApplicationException {
        return DAO().getEmpServicePeriod(civilId);
    }


    public IPagingResponseDTO getByHireStageWithPaging(IRequestInfoDTO ri,
                                                       IBasicDTO _searchDTO) throws DataBaseException,
                                                                                    SharedApplicationException {

        IPagingResponseDTO responseDTO = null;

        if (_searchDTO != null) {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;
            IPagingRequestDTO requestDTO = (IPagingRequestDTO)searchDTO.getPagingRequestDTO();
            if (requestDTO != null) {
                Long pageNum = requestDTO.getPageNum();
                Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
                if (pageNum != null) {
                    if (maxNoOfRecords != null) {
                        requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                    } else {
                        throw new MaxNoOfRecordsRequiredException();
                    }
                } else {
                    throw new PageNumRequiredException();
                }

                responseDTO = new PagingResponseDTO();

                if (requestDTO.isCountRequired()) {
                    responseDTO.setCount(DAO().getCountByHireStageAndMinistryWithPaging(searchDTO));
                }

                List<IBasicDTO> result = null;
                try {
                    result = DAO().getByHireStageWithPaging(searchDTO);
                } catch (ItemNotFoundException e) {
                    throw new NoResultException();
                }

                responseDTO.setBasicDTOList(result);
                responseDTO.setRequestDTO(requestDTO);
            }
        }

        return responseDTO;

    }


    public IPagingResponseDTO searchWithPaging(IRequestInfoDTO ri, IBasicDTO _searchDTO) throws DataBaseException,
                                                                                                SharedApplicationException {

        IPagingResponseDTO responseDTO = null;

        if (_searchDTO != null) {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;
            IPagingRequestDTO requestDTO = (IPagingRequestDTO)searchDTO.getPagingRequestDTO();
            if (requestDTO != null) {
                Long pageNum = requestDTO.getPageNum();
                Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
                if (pageNum != null) {
                    if (maxNoOfRecords != null) {
                        requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                    } else {
                        throw new MaxNoOfRecordsRequiredException();
                    }
                } else {
                    throw new PageNumRequiredException();
                }

                responseDTO = new PagingResponseDTO();

                if (requestDTO.isCountRequired()) {
                    responseDTO.setCount(DAO().getSearchCount(searchDTO));
                }

                List<IBasicDTO> result = null;
                try {
                    result = DAO().searchWithPaging(searchDTO);
                } catch (ItemNotFoundException e) {
                    throw new NoResultException();
                }

                responseDTO.setBasicDTOList(result);
                responseDTO.setRequestDTO(requestDTO);
            }
        }

        return responseDTO;

    }


    public void refresh(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        DAO().refresh();
    }

    public List<IBasicDTO> searchByCivilAndName(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().searchByCivilAndName(basicDTO);
    }

    public List<IBasicDTO> simpleSearchFullData(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().simpleSearchFullData(basicDTO);
    }

    public List<IBasicDTO> simpleSearchAllMinistriesFullData(IRequestInfoDTO ri,
                                                             IBasicDTO basicDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        return DAO().simpleSearchAllMinistriesFullData(basicDTO);
    }

    public IEmployeesDTO getByRealCivilId(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws DataBaseException,
                                                                                                     SharedApplicationException {
        EmployeeExtraDataDAO employeeExtraDataDAO =
            (EmployeeExtraDataDAO)DAOFactoryUtil.getInstance(EmployeeExtraDataEntity.class);
        IEmployeesDTO employeesDTO = DAO().getByRealCivilId(realCivilId, minCode);
        Long civilId = null;
        if (employeesDTO != null) {
            civilId = Long.valueOf(employeesDTO.getCode().getKey());
        }
        // get Employee Salary
        try {
            List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList =
                (List)SalClientFactory.getSalEmpSalaryElementsClient().getEmpSalaryElementByCivilId(civilId);
            employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
        } catch (Exception e) {
            employeesDTO.setSalEmpSalaryElementsDTOList(new ArrayList());
        }
        // get EmployeeExtraData
        try {
            List<IEmployeeExtraDataDTO> employeeExtraDataDTOList =
                employeeExtraDataDAO.getAllEmployeeExtraDataByCivilId(civilId);
            if (employeeExtraDataDTOList != null && employeeExtraDataDTOList.size() > 0) {
                employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
                for (IEmployeeExtraDataDTO dto : employeeExtraDataDTOList) {
                    Long extdattypeCode = Long.valueOf(dto.getEmpExtraDataTypesDTO().getCode().getKey());
                    if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(dto.getValue());

                    }
                }
            }
            employeesDTO.setEmployeeExtraDataDTOList((List)employeeExtraDataDTOList);
        } catch (Exception e) {
            employeesDTO.setEmployeeExtraDataDTOList(new ArrayList());
        }

        return employeesDTO;
    }

    public IEmployeesDTO getCivilByRealCivil(IRequestInfoDTO ri, Long realCivilId,
                                             Long minCode) throws DataBaseException, SharedApplicationException {
        IEmployeesDTO employeesDTO = DAO().getCivilByRealCivil(realCivilId, minCode);
        return employeesDTO;
    }

    @Override
    public IEmployeesDTO getByRealCivilIdForEsrv(IRequestInfoDTO iRequestInfoDTO, Long realCivilId,
                                                 Long minCode) throws RemoteException, SharedApplicationException,
                                                                      DataBaseException {
        EmployeeExtraDataDAO employeeExtraDataDAO =
            (EmployeeExtraDataDAO)DAOFactoryUtil.getInstance(EmployeeExtraDataEntity.class);
        IEmployeesDTO employeesDTO = DAO().getByRealCivilIdForEsrv(realCivilId, minCode);
        Long civilId = null;
        if (employeesDTO != null) {
            civilId = Long.valueOf(employeesDTO.getCode().getKey());
        }
        // get Employee Salary
        try {
            List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList =
                (List)SalClientFactory.getSalEmpSalaryElementsClient().getEmpSalaryElementByCivilId(civilId);
            employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
        } catch (Exception e) {
            employeesDTO.setSalEmpSalaryElementsDTOList(new ArrayList());
        }
        // get EmployeeExtraData
        try {
            List<IEmployeeExtraDataDTO> employeeExtraDataDTOList =
                employeeExtraDataDAO.getAllEmployeeExtraDataByCivilId(civilId);
            if (employeeExtraDataDTOList != null && employeeExtraDataDTOList.size() > 0) {
                employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
                for (IEmployeeExtraDataDTO dto : employeeExtraDataDTOList) {
                    Long extdattypeCode = Long.valueOf(dto.getEmpExtraDataTypesDTO().getCode().getKey());
                    if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(dto.getValue());
                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(dto.getValue());

                    }
                }
            }
            employeesDTO.setEmployeeExtraDataDTOList((List)employeeExtraDataDTOList);
        } catch (Exception e) {
            employeesDTO.setEmployeeExtraDataDTOList(new ArrayList<IBasicDTO>());
        }

        return employeesDTO;
    }


    public IEmployeesDTO getSimpleEmployeeByRealCivilId(IRequestInfoDTO ri, Long realCivilId,
                                                        Long minCode) throws DataBaseException,
                                                                             SharedApplicationException {
        IEmployeesDTO employeesDTO = DAO().getSimpleEmployeeByRealCivilId(realCivilId, minCode);
        return employeesDTO;
    }

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMov(IRequestInfoDTO ri, Long realCivilId,
                                                             Long minCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().getByRealCivilIdAndHireStatusForMov(realCivilId, minCode);
    }

    public IEmployeesDTO getByRealCivilIdForMovWithoutFilteration(IRequestInfoDTO ri, Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getByRealCivilIdForMovWithoutFilteration(realCivilId, minCode);

    }

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMovSimple(IRequestInfoDTO ri, Long realCivilId,
                                                                   Long minCode) throws DataBaseException,
                                                                                        SharedApplicationException {
        return DAO().getByRealCivilIdAndHireStatusForMovSimple(realCivilId, minCode);

    }

    public IEmployeesDTO getEmpForMov(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws DataBaseException,
                                                                                                 SharedApplicationException {
        return DAO().getEmpForMov(realCivilId, minCode);

    }

    public IEmployeesDTO getByRealCivilIdInallMin(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().getByRealCivilIdAndHireStatus(realCivilId);

    }


    public IEmployeesDTO getByRealCivilIdInallMinForReg(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                                     SharedApplicationException {
        return DAO().getByRealCivilIdAndHireStatusForReg(realCivilId);

    }

    public IEmployeesDTO getByRealCivilIdForReg(IRequestInfoDTO ri,
                                                Long realCivilId) throws SharedApplicationException,
                                                                         DataBaseException {
        return DAO().getByRealCivilIdForReg(realCivilId);
    }

    public IEmployeesDTO getByRealCivilIdAndHireStatus(IRequestInfoDTO ri, Long realCivilId,
                                                       Long minCode) throws DataBaseException,
                                                                            SharedApplicationException {
        return DAO().getByRealCivilIdAndHireStatus(realCivilId, minCode);

    }


    public IEmployeesDTO getByRealCivilIdAllMinistries(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                                    SharedApplicationException,
                                                                                                    DataBaseException {
        return DAO().getByRealCivilIdAllMinistries(realCivilId);
    }

    /**
     * return true if employee is hired
     * @param civilID
     * @return
     * @throws RemoteException
     * @author Dina Abd El-Rahim 06/03/2014
     **/
    public Boolean isEmployeeHiredForVac(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        Long hireStatus = DAO().executeCheckEmpHireStatus(civilId);
        if (!hireStatus.equals(IEMPConstants.HIRE_STATUS_HIRED_FROM_DB_FUNCTION)) {
            throw new InvalidHireStatusException();
        }
        return Boolean.TRUE;
    }

    public Long isJobHasEmployeees(IRequestInfoDTO ri, Long jobCode) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException {
        return DAO().isJobHasEmployees(jobCode);
    }

    public List<IBasicDTO> getAllEmployeesByPremittedMinistries(IRequestInfoDTO ri,
                                                                IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().getAllEmployeesByPremittedMinistries(basicDTO);
    }

    public List<IBasicDTO> filterAvailableEntitiesUsingPaging(IRequestInfoDTO ri, IBasicDTO employeeSearchDTO1,
                                                              IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {
        return DAO().filterAvailableEntitiesUsingPaging(employeeSearchDTO1, requestDTO);
    }

    public Long filterAvailableEntitiesUsingPagingCount(IRequestInfoDTO ri,
                                                        IBasicDTO employeeSearchDTO1) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().filterAvailableEntitiesUsingPagingCount(employeeSearchDTO1);
    }

    public Long CheckIfEmployeeOrCandidate(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                    SharedApplicationException {
        long status = 0L;
        List<IEmployeesDTO> empList = new ArrayList<IEmployeesDTO>();
        List<IEmpCandidatesDTO> candidateList = new ArrayList<IEmpCandidatesDTO>();

        EmpCandidatesDAO empcanDAO = ((EmpCandidatesDAO)super.newDAOInstance(EmpCandidatesEntity.class));
        empList = DAO().CheckIfEmployeeIsExist(civilId);
        candidateList = empcanDAO.checkIfCitizenIsCandidate(civilId);
        if (empList != null && empList.size() != 0) {
            status = 1L;
        } else if (candidateList != null && candidateList.size() != 0) {
            status = 2L;
        }
        return status;
    }
    ///////////////////////////////// for EmployeesCMTSessionBean////////////////////////////////////////////
    //@Auditable
    public IBasicDTO addEmployeeForMovOnly(IRequestInfoDTO ri,
                                           IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                           DataBaseException {
        try {
            IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
            IEmployeesEntityKey employeesEntityKey = (IEmployeesEntityKey)employeesDTO.getCode();
            Long civilId = employeesEntityKey.getCivilId();
            if (civilId == null)
                throw new InvalidDataEntryException();
            IEmployeesDTO addedDTO = null;
            employeesDTO.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
            //            employeesDTO.setRecordDescCode(null);
            addedDTO = DAO().addEmployeeForMovOnly(employeesDTO);
            System.out.println("@@@ EmployeesBO : addEmployeeForMovOnly : emp Added Successfully ");
            return addedDTO;
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            throw wrapIntoDataBaseException(e);
        }
    }

    /**
     * check if employee exists * @param civilId
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isEmployeeExist(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                            SharedApplicationException {
        return DAO().isEmployeeExist(civilId);

    }

    //    public Boolean updateEmployeeDataForMov(Long civilId, Long employeeHireStatus,
    //                                            Date endJobDate) throws DataBaseException, SharedApplicationException {
    //        try {
    //            return DAO().updateEmployeeDataForMov(civilId, employeeHireStatus, endJobDate);
    //        } catch (TransactionException e) {
    //            throw wrapIntoDataBaseException(e);
    //        } catch (SharedApplicationException e) {
    //            rollbackTransaction();
    //            throw e;
    //        } catch (Exception e) {
    //            rollbackTransaction();
    //            throw wrapIntoDataBaseException(e);
    //        }
    //    }

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isMinistryFileNoExist(IRequestInfoDTO ri, String ministryFileNo) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().isMinistryFileNoExist(ministryFileNo);

    }
    //@Auditable
    public IBasicDTO updateEmpCMT(IRequestInfoDTO requestInfo,
                                  IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException {
        System.out.println("Employee.updateEmpCMT::Start");
        initRiForBsnLog("em/HRM", requestInfo);
        IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
        EmployeeExtraDataDAO employeeExtraDataDAO =
            (EmployeeExtraDataDAO)newDAOInstance(EmployeeExtraDataEntity.class);
        //        EmployeeDocumentsDAO employeeDocumentsDAO =
        //            (EmployeeDocumentsDAO)newDAOInstance(EmployeeDocumentsEntity.class);
        //        EmployeeProceduresDAO employeeProceduresDAO =
        //            (EmployeeProceduresDAO)newDAOInstance(EmployeeProceduresEntity.class);
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)newDAOInstance(RequiredDocumentsEntity.class);
        /*Check if Start work date greater than hire date*/
        this.checkHireAndStartWorkDate(employeesDTO);

        //set date of next raise
        employeesDTO = this.addDateOfNextRaise(employeesDTO);

        //used to update stage to complete document
        boolean documentStatus = true;

        //used to update stage to complete procedure
        boolean procedureStatus = true;

        try {
            //get old hire type code
            System.out.println("Employee.getById::Start");
            IEmployeesDTO oldEmp = (IEmployeesDTO)this.getById(employeesDTO.getCode());
            System.out.println("Employee.getById::End");

            if (((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode().equals(IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN)) {
                if (!(((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_END_SERVICE) ||
                      ((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_CANDIDATE) ||
                      ((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_MOVING)))
                    throw new EmployeeAlreadyEmploymentException();

            }

            Long hireTypeCode = ((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode();

            System.out.println("Employee.updateEmployeeDocumentsCMT::Start");
            //TODO by i.omar-review
            documentStatus = updateEmployeeDocumentsCMT(oldEmp, employeesDTO);
            System.out.println("Employee.updateEmployeeDocumentsCMT::End");

            System.out.println("Employee.updateEmployeeProcedureCMT::Start");
            //TODO by i.omar-review
            procedureStatus = updateEmployeeProcedureCMT(employeesDTO);
            System.out.println("Employee.updateEmployeeProcedureCMT::End");

            //check for added procedure or add required document
            List<IBasicDTO> availableDocumentList = null;
            List<IBasicDTO> availableProcedureList = null;

            //            try {
            //                //get list of document and procedure
            //                //using try and catch here to void item not found from listAvailableEntities method
            //                availableDocumentList =
            //                        employeeDocumentsDAO.listAvailableEntities(employeesDTO.getCode(), employeesDTO.getHireTypeDTO().getCode());
            //            } catch (ItemNotFoundException e) {
            //                e.printStackTrace();
            //            }

            //            try {
            //                availableProcedureList =
            //                        employeeProceduresDAO.filterAvailableEntities(employeesDTO.getCode(), employeesDTO.getHireTypeDTO().getCode(),
            //                                                                      null);
            //            } catch (ItemNotFoundException e) {
            //                e.printStackTrace();
            //            }

            //TODO by i.omar-review

            //            if (availableDocumentList != null && availableDocumentList.size() > 0) {
            //                for (IBasicDTO reqDocument : availableDocumentList) {
            //                    if (requiredDocumentsDAO.checkRequired(hireTypeCode,
            //                                                           (((IDocumentTypesEntityKey)(((IRequiredDocumentsDTO)reqDocument).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE)) {
            //                        documentStatus = false;
            //                        break;
            //                    }
            //                }
            //            }

            if (availableProcedureList != null && availableProcedureList.size() > 0) {
                procedureStatus = false;
            }

            //update employee stage
            Long hireStageCode = ((IHireStagesEntityKey)employeesDTO.getHireStageDTO().getCode()).getHirstageCode();
            System.out.println("Stageeeeeeeeeeeee>>>>>>>>>>" + hireStageCode);
            if (hireStageCode.equals(IEMPConstant.EMP_STAGE_COMPLETE_DOC) ||
                hireStageCode.equals(IEMPConstant.EMP_STAGE_COMPLETE_PROC) ||
                hireStageCode.equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION)) {

                IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
                Long code =
                    (documentStatus == true && procedureStatus == true) ? IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION :
                    (documentStatus == true) ? IEMPConstant.EMP_STAGE_COMPLETE_DOC :
                    (procedureStatus == true) ? IEMPConstant.EMP_STAGE_COMPLETE_PROC : IEMPConstant.EMP_STAGE_DEFAULT;
                stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(code));
                employeesDTO.setHireStageDTO(stage);
            } else if (hireStageCode.equals(IEMPConstant.EMP_STAGE_STARTING_WORK) &&
                       hireTypeCode.equals(IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN)) {

                IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
                stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_DEFAULT));
                employeesDTO.setHireStageDTO(stage);
                employeesDTO.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO());
                employeesDTO.getHireStatusDTO().setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.EMP_STATUS_CANDIDATE));
                employeesDTO.setEndJobDate(null);
            } else {
                System.out.println("HireStage will be updated with value setted from Presentation.");
            }

            if (hireTypeCode.equals(IEMPConstant.EMP_HIRE_TYPE_NOMINATION_AGAIN) &&
                ((IHireStatusEntityKey)oldEmp.getHireStatusDTO().getCode()).getHirstatusCode().equals(IEMPConstant.EMP_STATUS_END_SERVICE)) {

                System.out.println("Employee.addSalEmployeeSalaryCMT::Start");

                updateSalEmployeeSalaryElement(employeesDTO);

                System.out.println("From Date after Adding Sal El>>>>>>>>" +
                                   employeesDTO.getSalEmpSalaryElementsDTOList().get(0).getFromDate());
                System.out.println("Employee.addSalEmployeeSalaryCMT::End");
            } else {
                /**
                 *                 ISalEmpSalaryElementsDTO _salEmpSalaryElementsDTO =
                    (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId(),
                                                                                                                         ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);

                if (((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode().toString().equals(IEMPConstant.EMP_HIRE_TYPE_FROM_CENTER_EMPLOYMENT.toString())) {
                    if ((_salEmpSalaryElementsDTO != null &&
                         (employeesDTO.getSalEmpSalaryElementsDTOList() != null && employeesDTO.getSalEmpSalaryElementsDTOList().size() >
                          0))) {
                        this.updateSalEmpSalaryDTOnlyCMT(employeesDTO);
                    } else if (employeesDTO.getSalEmpSalaryElementsDTOList() != null &&
                               employeesDTO.getSalEmpSalaryElementsDTOList().size() > 0) {
                        //this.addSalEmployeeSalaryCMT(employeesDTO);
                    }
                } else {
                    System.out.println("Employee.updateSalEmpSalaryDTOnlyCMT::Start");
                    // modify to remove old row and add new only , so kuwit citizen has only one salEmpSalary Element ( one row ) before he become employee
                    this.updateSalEmpSalaryDTOnlyCMT(employeesDTO);
                    System.out.println("Employee.updateSalEmpSalaryDTOnlyCMT::End");
                }
                */
            }
            employeesDTO.setJobDTO(oldEmp.getJobDTO());
            System.out.println("Employee.setTrxLogVar::Start");
            System.out.println("Employee.setTrxLogVar::End");

            /**
             * EmployeeExtraData
             * if notes ExtraData Not found in database add(new ExtraData)
             * @Updated By Kareem Sayed
             */
            if (employeesDTO.getEmployeeExtraDataDTOList() != null &&
                employeesDTO.getEmployeeExtraDataDTOList().size() > 0) {

                for (IBasicDTO employeeExtraData : employeesDTO.getEmployeeExtraDataDTOList()) {
                    IEmployeeExtraDataDTO employeeExtraDataDTO = (IEmployeeExtraDataDTO)employeeExtraData;
                    if ((employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT.toString())))
                        employeeExtraDataDTO.setValue(employeesDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes());

                    if ((employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT.toString())))
                        employeeExtraDataDTO.setValue(employeesDTO.getEmpExtraDataValueDTO().getFatwaDeptNotes());

                    if ((employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN.toString())))
                        employeeExtraDataDTO.setValue(employeesDTO.getEmpExtraDataValueDTO().getSuggestedJobCode());

                    if ((employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT.toString())))
                        employeeExtraDataDTO.setValue(employeesDTO.getEmpExtraDataValueDTO().getSelectionDeptNotes());

                    if ((employeeExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN.toString())))
                        employeeExtraDataDTO.setValue(employeesDTO.getEmpExtraDataValueDTO().getMinistryNotes());
                    if (employeeExtraDataDTO.getStatusFlag() != null &&
                        employeeExtraDataDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        employeeExtraDataDAO.add(employeeExtraData);
                    } else {
                        employeeExtraDataDAO.update(employeeExtraData);
                    }

                }
            }


            if (procedureStatus && documentStatus) {
                employeesDTO.setHireStageDTO(EmpDTOFactory.createHireStagesDTO(Long.parseLong(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS),
                                                                               ""));
                employeesDTO.setRecordDescCode(IEMPConstants.EMPLOYEE_RECORD_DESC);
            }
            System.out.println("Employee.update::Start");
            super.update(employeesDTO);
            System.out.println("Employee.update::End");

            System.out.println("Employee.keepLog::Start");
            System.out.println("Employee.keepLog::End");

            System.out.println("Employee.releaseTrxLogVar::Start");
            System.out.println("Employee.releaseTrxLogVar::Start");

            System.out.println("Employee.addPersonQualificationCMT::Start");
            System.out.println("Employee.addPersonQualificationCMT::Start");

            System.out.println("Employee.checkLegelJobDTOCMTForUpdate::Start");
            //TODO by i.omar-review

            this.checkLegelJobDTOCMTForUpdate(((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId(),
                                              (IJobsEntityKey)employeesDTO.getJobDTO().getCode());

            System.out.println("Employee.checkLegelJobDTOCMTForUpdate::End");

            System.out.println("Employee.checkLegelHireTypeCMTForUpdate::Start");
            //check if employee hire type is legal
            //TODO by i.omar-review

            this.checkLegelHireTypeCMTForUpdate(((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId(),
                                                ((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode());

            System.out.println("Employee.checkLegelHireTypeCMTForUpdate::End");

            System.out.println("Employee.updateEmpCMT::End");

            return employeesDTO;

        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    private void checkLegelJobDTOCMTForUpdate(Long civilID, IJobsEntityKey jobCode) throws SharedApplicationException,
                                                                                           DataBaseException {
        IJobsDTO jobsDTO = null;
        try {
            suspendTransaction();
            jobsDTO = (IJobsDTO)JobClientFactory.getJobsClient().getById(jobCode);
        } finally {
            resumeTransaction();
        }

        System.out.println("job Code>>>>>>>>>>>>>>>>" + jobsDTO.getCode().getKey());

        if (jobsDTO.getConditionsDTO() != null && civilID != null) {
            if (DAO().checkGRSCondition(jobsDTO.getConditionsDTO().getCode(),
                                        InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilID)).equals(Boolean.FALSE)) {

                NotMatchedOnJobGRSConditionException notMatchedOnJobGRSConditionException =
                    new NotMatchedOnJobGRSConditionException();
                //                List<IResultDTO> linesResultList =
                //                    GrsClientFactory.getConditionsCMTClient().executeGRSCondition(Long.valueOf(jobsDTO.getConditionsDTO().getCode().getKey().toString()),
                //                                                                                  civilID);
                //                notMatchedOnJobGRSConditionException.setExtraInformation(linesResultList);
                throw notMatchedOnJobGRSConditionException;
            }
        }
    }

    private void checkLegelHireTypeCMTForUpdate(Long civilID, Long hireType) throws SharedApplicationException,
                                                                                    DataBaseException {
        HireTypesDAO hireTypesDAO = (HireTypesDAO)newDAOInstance(HireTypesEntity.class);
        IHireTypesDTO hireTypeDTO = hireTypesDAO.getById(EmpEntityKeyFactory.createHireTypesEntityKey(hireType));

        System.out.println("hireTypeDTO Code>>>>>>>>>>>>>>>>" + hireTypeDTO.getCode().getKey());
    }
    //@Auditable
    private void updateSalEmployeeSalaryElement(IEmployeesDTO employeesDTO) throws DataBaseException,
                                                                                   SharedApplicationException {

        ISalEmpSalaryElementsCMTClient salEmpSalElClient = SalClientFactory.getSalEmpSalaryElementsCMTClient();

        ISalEmpSalaryElementsDTO salEmpSalElDTO =
            (ISalEmpSalaryElementsDTO)salEmpSalElClient.getById(employeesDTO.getSalEmpSalaryElementsDTOList().get(0).getCode());

        salEmpSalElClient.update(salEmpSalElDTO);


    }

    //@Auditable
    private boolean updateEmployeeDocumentsCMT(IEmployeesDTO oldEmp,
                                               IEmployeesDTO employeesDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)newDAOInstance(RequiredDocumentsEntity.class);
        //        EmployeeDocumentsDAO employeeDocumentsDAO =
        //            (EmployeeDocumentsDAO)newDAOInstance(EmployeeDocumentsEntity.class);
        boolean documentStatus = true;
        Map<String, IBasicDTO> empDocMap = null;
        try {
            Long oldHireTypeCode = ((IHireTypesEntityKey)oldEmp.getHireTypeDTO().getCode()).getHirtypeCode();
            Long hireTypeCode = ((IHireTypesEntityKey)employeesDTO.getHireTypeDTO().getCode()).getHirtypeCode();

            if (!(oldHireTypeCode.equals(hireTypeCode))) {
                empDocMap = this.initDocMap(oldEmp);
            }
            //update list of employee documents
            //            if (employeesDTO.getEmployeeDocumentsDTOList() != null) {
            //                for (IBasicDTO employeeDocumentsDTO : employeesDTO.getEmployeeDocumentsDTOList()) {
            //                    if (employeeDocumentsDTO.getStatusFlag() ==
            //                        null) { //Check if that document is required for that hire type
            //                        //and set document status
            //                        if (!((((IEmployeeDocumentsDTO)employeeDocumentsDTO).getStatus()).equals(IEMPConstant.EMP_DOCUMENT_COMPLETED)) &&
            //                            requiredDocumentsDAO.checkRequired(hireTypeCode,
            //                                                               (((IDocumentTypesEntityKey)(((IEmployeeDocumentsDTO)employeeDocumentsDTO).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE))
            //                            documentStatus = false;
            //                        employeeDocumentsDAO.update(employeeDocumentsDTO);
            //                    } else if (employeeDocumentsDTO.getStatusFlag() != null &&
            //                               employeeDocumentsDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
            //                        ((IEmployeeDocumentsDTO)employeeDocumentsDTO).setEmployeesDTO(employeesDTO);
            //                        //Check if that document is required for that hire type
            //                        //and set document status
            //                        if (!IEMPConstant.EMP_DOCUMENT_COMPLETED.equals(((((IEmployeeDocumentsDTO)employeeDocumentsDTO).getStatus()))) &&
            //                            requiredDocumentsDAO.checkRequired(hireTypeCode,
            //                                                               (((IDocumentTypesEntityKey)(((IEmployeeDocumentsDTO)employeeDocumentsDTO).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE))
            //                            documentStatus = false;
            //                        employeeDocumentsDTO.setCode(EmpEntityKeyFactory.createEmployeeDocumentsEntityKey(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId(),
            //                                                                                                          ((IDocumentTypesEntityKey)((IEmployeeDocumentsDTO)employeeDocumentsDTO).getDocumentTypeDTO().getCode()).getDoctypeCode()));
            //                        if (empDocMap == null) {
            //                            employeeDocumentsDAO.add(employeeDocumentsDTO);
            //                        } else if (empDocMap.get(employeeDocumentsDTO.getCode().getKey()) == null) {
            //                            employeeDocumentsDAO.add(employeeDocumentsDTO);
            //                        } else {
            //                            empDocMap.remove(employeeDocumentsDTO.getCode().getKey());
            //                        }
            //                    }
            //                }
            //            }
            //            //remove old doc list
            //            if (!(oldHireTypeCode.equals(hireTypeCode)))
            //                this.removeOldDecouments((HashMap)empDocMap);

            return documentStatus;
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    //@Auditable
    private boolean updateEmployeeProcedureCMT(IEmployeesDTO employeesDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        boolean procedureStatus = true;
        //        EmployeeProceduresDAO employeeProceduresDAO =
        //            (EmployeeProceduresDAO)newDAOInstance(EmployeeProceduresEntity.class);
        //update list of employee procedure
        try {
            if (employeesDTO.getEmployeeProceduresDTOList() != null) {
                for (IBasicDTO employeeProceduresDTO : employeesDTO.getEmployeeProceduresDTOList()) {

                    if (employeeProceduresDTO.getStatusFlag() ==
                        null) { //check if any procedure result not done then set procedure= false
                        //                        if (!validateEmpProcedureStatus((IEmployeeProceduresDTO)employeeProceduresDTO))
                        //                            procedureStatus = false;
                        //                        employeeProceduresDAO.update(employeeProceduresDTO);
                    } else if (employeeProceduresDTO.getStatusFlag() != null &&
                               employeeProceduresDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        //                        ((IEmployeeProceduresDTO)employeeProceduresDTO).setEmployeesDTO(employeesDTO);
                        //check if any procedure result not done then set procedure= false
                        //                        if (!validateEmpProcedureStatus((IEmployeeProceduresDTO)employeeProceduresDTO))
                        //                            procedureStatus = false;
                        //                        employeeProceduresDAO.add(employeeProceduresDTO);
                    }
                }
            }
            return procedureStatus;
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    //@Auditable
    public Boolean executeStartWorkCMT(IRequestInfoDTO requestInfo, IBasicDTO employeesDTO1) throws DataBaseException,
                                                                                                    SharedApplicationException {

        try {
            initRiForBsnLog("em/HRM", requestInfo);
            IEmployeesDTO employeesDTO = DAO().getById(employeesDTO1.getCode());

            ISalEmpSalaryElementsDTO firstSalEmpSalaryElementsDTO = null;

            try {

                suspendTransaction();

                firstSalEmpSalaryElementsDTO =
                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpSalElementByCivilAndType(employeesDTO.getRealCivilId(),
                                                                                                                                  IEMPConstants.ELEMENT_GUIDE_TYPE_RAISE);


            } finally {
                resumeTransaction();
            }

            if (firstSalEmpSalaryElementsDTO == null) { // || secondSalEmpSalaryElementsDTO == null) {
                throw new InvalidDataEntryException();

            }
            try {
                SalClientFactory.getSalEmpSalaryElementsClient().remove(firstSalEmpSalaryElementsDTO);
                firstSalEmpSalaryElementsDTO.setFromDate(((IEmployeesDTO)employeesDTO1).getStartWorkingDate());
                //secondSalEmpSalaryElementsDTO.setFromDate(((IEmployeesDTO)employeesDTO1).getStartWorkingDate());
                System.out.println("firstSalEmpSalaryElementsDTO SalElementGuide>>" +
                                   firstSalEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode().getKey());

                SalClientFactory.getSalEmpSalaryElementsCMTClient().add(firstSalEmpSalaryElementsDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }

            employeesDTO.setStartWorkingDate(((IEmployeesDTO)employeesDTO1).getStartWorkingDate());
            this.checkHireAndStartWorkDate(employeesDTO);
            employeesDTO.getHireStageDTO().setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_STARTING_WORK));
            employeesDTO.getHireStatusDTO().setCode(EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstant.EMP_STATUS_EMPLOYMENT));
            employeesDTO.setActiveFlag(IEMPConstants._EMP_ACTIVE_STATUS);
            return DAO().update(employeesDTO);
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw new RuntimeException(e);
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }


    public Long countEmployeesByJobAndMin(IRequestInfoDTO requestInfo, String jobCode,
                                          Long minCode) throws RemoteException, SharedApplicationException,
                                                               DataBaseException {
        return DAO().countEmployeesByJobAndMin(jobCode, minCode);
    }

    public Long countHiredEmployeesByMinWrkCode(IRequestInfoDTO ri, Long minCode,
                                                String wrkCode) throws SharedApplicationException, DataBaseException {
        return DAO().countHiredEmployeesByMinWrkCode(minCode, wrkCode);
    }

    public String getStatusForHire(IRequestInfoDTO ri) {
        return DAO().getStatusForHire();
    }

    public List<IMinistriesDTO> getMinistriesListByEvalCivilId(IRequestInfoDTO ri, Long evalCivilId,
                                                               List<IMinistryInfo> minList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        return DAO().getMinistriesListByEvalCivilId(evalCivilId, minList);
    }

    public List<IMinistriesDTO> getMinistriesListByEvalCivilIdNew(IRequestInfoDTO ri, Long evalCivilId,
                                                               List<IMinistriesDTO> minList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        return DAO().getMinistriesListByEvalCivilIdNew(evalCivilId, minList);
    }

    public IEmployeesDTO getByRealCivilIdAllMinByRecordDesc(IRequestInfoDTO ri,
                                                            Long realCivilId) throws DataBaseException,
                                                                                     SharedApplicationException {
        return DAO().getByRealCivilIdAllMinByRecordDesc(realCivilId);
    }

    public Boolean isEmployeeHiredWithRecordDesc(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                              SharedApplicationException {
        return DAO().isEmployeeHiredWithRecordDesc(realCivilId);
    }

    public List<IBasicDTO> getEmpByEmpSearch(IRequestInfoDTO ri,
                                             EmpSearchRecodDesc searchDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().getEmpByEmpSearch(searchDTO);
    }

    //@Auditable
    public Boolean updateEmployeeForPRM(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws DataBaseException,
                                                                                            SharedApplicationException {
        return DAO().updateEmployeeForPRM(employeesDTO1);
    }

    //@Auditable
    public Boolean updateEmployeeStaus(IRequestInfoDTO ri, IEntityKey empKey,
                                       IEntityKey employeeHireStatus) throws DataBaseException,
                                                                             SharedApplicationException {
        return DAO().updateEmployeeStaus(empKey, employeeHireStatus);
    }


    public IEmployeesDTO getCurrActiveEmpByRealCivilId(IRequestInfoDTO ri, Long realCivilId,
                                                       Long minCode) throws DataBaseException,
                                                                            SharedApplicationException {
        //        EmployeeExtraDataDAO employeeExtraDataDAO =
        //            (EmployeeExtraDataDAO)DAOFactoryUtil.getInstance(EmployeeExtraDataEntity.class);
        IEmployeesDTO employeesDTO = DAO().getCurrActiveEmpByRealCivilId(realCivilId, minCode);
        //        Long civilId = null;
        //        if (employeesDTO != null) {
        //            civilId = Long.valueOf(employeesDTO.getCode().getKey());
        //        }
        // get Employee Salary
        //        try {
        //            List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList =
        //                (List)SalClientFactory.getSalEmpSalaryElementsClient().getEmpSalaryElementByCivilId(civilId);
        //            employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
        //        } catch (Exception e) {
        //            employeesDTO.setSalEmpSalaryElementsDTOList(new ArrayList());
        //        }
        // get EmployeeExtraData
        //        try {
        //            List<IEmployeeExtraDataDTO> employeeExtraDataDTOList =
        //                employeeExtraDataDAO.getAllEmployeeExtraDataByCivilId(civilId);
        //            if (employeeExtraDataDTOList != null && employeeExtraDataDTOList.size() > 0) {
        //                employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
        //                for (IEmployeeExtraDataDTO dto : employeeExtraDataDTOList) {
        //                    Long extdattypeCode = Long.valueOf(dto.getEmpExtraDataTypesDTO().getCode().getKey());
        //                    if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
        //                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(dto.getValue());
        //                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
        //                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(dto.getValue());
        //                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
        //                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(dto.getValue());
        //                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
        //                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(dto.getValue());
        //                    } else if (extdattypeCode.equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
        //                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(dto.getValue());
        //
        //                    }
        //                }
        //            }
        //            employeesDTO.setEmployeeExtraDataDTOList((List)employeeExtraDataDTOList);
        //        } catch (Exception e) {
        //            employeesDTO.setEmployeeExtraDataDTOList(new ArrayList());
        //        }

        return employeesDTO;
    }

    public Boolean checkFileNoWithMinAndEmployeeForMov(IRequestInfoDTO ri, Long minCode, String minFileNo,
                                                       Long civilId) throws DataBaseException,
                                                                            SharedApplicationException {
        if (DAO().countFilesNoWithMinAndEmployeeForMov(minCode, minFileNo, civilId) > 0L) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    //@Auditable
    public int updateActiveFlagByRealCivilId(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().updateActiveFlagByRealCivilId(realCivilId, IEMPConstants._EMP_NOT_ACTIVE_STATUS);

    }
    //@Auditable
    public int updateWorkCodeByRealCivilId(IRequestInfoDTO ri, Long realCivilId,
                                           String workCode) throws DataBaseException, SharedApplicationException {
        return DAO().updateWorkCodeByRealCivilId(realCivilId, workCode);

    }


    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilId(IRequestInfoDTO ri,
                                                                   Long realCivilId) throws DataBaseException,
                                                                                            SharedApplicationException {
        List<IEmployeesDTO> employeesDTOList = DAO().getCurrActiveEmployeesByRealCivilId(realCivilId);

        return employeesDTOList;
    }

    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(IRequestInfoDTO ri,
                                                                                         Long realCivilId) throws DataBaseException,
                                                                                                                  SharedApplicationException {
        List<IEmployeesDTO> employeesDTOList =
            DAO().getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(realCivilId);

        return employeesDTOList;
    }

    public Boolean checkHireDateContradiction(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        if (DAO().countHireDateContradiction(basicDTO) > 0L) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    //@Auditable
    public IBasicDTO addEmployeeForExecuteMovOnly(IRequestInfoDTO ri,
                                                  IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                                  DataBaseException {

        IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
        IEmployeesEntityKey employeesEntityKey = (IEmployeesEntityKey)employeesDTO.getCode();
        Long civilId = employeesEntityKey.getCivilId();
        if (civilId == null)
            throw new InvalidDataEntryException();
        IEmployeesDTO addedDTO = null;
        employeesDTO.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
        //            employeesDTO.setRecordDescCode(null);
        employeesDTO.setTabrecSerial(null);
        addedDTO = DAO().addEmployeeForMovOnly(employeesDTO);
        System.out.println("@@@ EmployeesBO : addEmployeeForMovOnly : emp Added Successfully ");
        return addedDTO;

    }


    public IPagingResponseDTO simpleSearchMovEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountMovWithPaging(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().simpleSearchMovEmpWithPaging(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;

    }

    public IPagingResponseDTO simpleSearchMovEmpWithPagingAllChecked(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                                     IPagingRequestDTO requestDTO,
                                                                     List<String> unCheckedList) throws DataBaseException,
                                                                                                        SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountMovWithPaging(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().simpleSearchMovEmpWithPagingAllChecked(basicDTO, requestDTO, unCheckedList);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;

    }
    //@Auditable
    public Boolean updateEmployeeBeforeExecuteEmployment(IRequestInfoDTO ri,
                                                         Long realCivilId) throws DataBaseException,
                                                                                  SharedApplicationException {

        return DAO().updateEmployeeBeforeExecuteEmployment(realCivilId);

    }
    //@Auditable
    public int updateActiveFlagForDelegation(IRequestInfoDTO ri, Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode) throws DataBaseException, SharedApplicationException {
        return DAO().updateActiveFlagForDelegation(activeFlag, IEMPConstants._EMP_NOT_ACTIVE_STATUS, hireStatusCode,
                                                   hireDate, minCode);

    }

    //@Auditable
    public int updateActiveFlagForDelegationWithRCivil(IRequestInfoDTO ri, Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode ,Long realCivilId) throws DataBaseException, SharedApplicationException {
        return DAO().updateActiveFlagForDelegationWithRCivil(activeFlag, IEMPConstants._EMP_NOT_ACTIVE_STATUS, hireStatusCode,
                                                   hireDate, minCode, realCivilId);

    }

    public Boolean isEmpIndebt(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                            SharedApplicationException {
        return DAO().isEmpIndebt(realCivilId);
    }


    public Boolean isConditionFromGrsValid(IRequestInfoDTO ri, Long realCivilId,
                                           Long degreeCode) throws DataBaseException, SharedApplicationException {
        Boolean condApplied = true;
        ICheckCivilIdOnConditionDTO resultDTO;
        ICheckCivilIdOnConditionDTO inputDTO = GrsDTOFactory.createCheckCivilIdOnConditionDTO();
        IConditionRelationsDTO conditionRelationsDTO = null;
        Long tabrecSerial;
        try {
            conditionRelationsDTO =
                    SalClientFactory.getSalElementGuidesClient().getDegreeConditionRelation(degreeCode);
            tabrecSerial = conditionRelationsDTO.getRTabrecSerial();
        } catch (Exception e) {
            return condApplied;
        }
        inputDTO.setCivilId(realCivilId);
        inputDTO.setRTabrecSerial(tabrecSerial);
        resultDTO = GrsClientFactory.getConditionsClient().checkCivilIdOnConditionByRTabrecSerial(inputDTO);
        condApplied = (resultDTO.getStatus() == 1 ? true : false);
        return condApplied;
    }

    public List<IEmployeesDTO> getAllHiredEmployeesByJobLevelsAndType(IRequestInfoDTO ri,
                                                                      IJobSearchCriteriaDTO jobSearchCriteriaDTO,
                                                                      Map<String, Long> conditionMap) throws DataBaseException,
                                                                                                             SharedApplicationException {
        return DAO().getAllHiredEmployeesByJobLevelsAndType(jobSearchCriteriaDTO, conditionMap);
    }

    public IPagingResponseDTO getAllEmployeesByForLoginMinistries(IRequestInfoDTO ri,
                                                                  IBasicDTO basicDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        IPagingRequestDTO requestDTO = ((IEmpEmployeeSearchDTO)basicDTO).getPagingRequestDTO();
        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().getCountAllEmployeesByForLoginMinistries(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().getAllEmployeesByForLoginMinistries(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }

    public IEmployeesDTO getEmpCodeNameByRealCivilId(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().getEmpCodeNameByRealCivilId(basicDTO);
    }

    public List<IBasicDTO> searchByCivilAndNameUsingMinCode(IRequestInfoDTO ri,
                                                            IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().searchByCivilAndNameUsingMinCode(basicDTO);
    }

    public List<IBasicDTO> searchByCivilAndNameUsingMinCodeForCER(IRequestInfoDTO ri,
                                                                  IBasicDTO basicDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
//        List<IBasicDTO> resultList = new ArrayList<IBasicDTO>();
//       List<IBasicDTO> list =  DAO().searchByCivilAndNameUsingMinCodeForCER(basicDTO);
//       Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest();
//       for(IBasicDTO dto:list){
//           ISimpleEmployeesDTO empDTO = (ISimpleEmployeesDTO)dto;
//           if(minCode.equals(empDTO.getMinistryCode())){
//               resultList.add(empDTO);
//           }
//       }
//       return resultList;
        return DAO().searchByCivilAndNameUsingMinCodeForCER(basicDTO);
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 31-3-2016
     * @param groupCode
     * @param userCode
     * @param realCivilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     *    * CSC-17343 
     * check if group code or user code granted or revoked for such real civil
     */
    public boolean checkFilterDataForEmp(IRequestInfoDTO ri, Long groupCode, Long userCode,
                                         Long realCivilID) throws DataBaseException, SharedApplicationException {
        return DAO().checkFilterDataForEmp(groupCode, userCode, realCivilID);
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return String
     * CSC-17343 
     * check if all workcenters tree for 02133
     */

    public String applyWrkcenterTreeFilter(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().applyWrkcenterTreeFilter();
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return List<String>
     * CSC-17343 
     * check if all workcenters tree for 02133
     */
    public List<String> filterDataByWrkCenterList(IRequestInfoDTO ri) throws DataBaseException,
                                                                             SharedApplicationException {
        return DAO().filterDataByWrkCenterList();
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<String,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all workcenters tree for 02133
     */

    public HashMap<String, String> getworkCenterTree(IRequestInfoDTO ri) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().getworkCenterTree();
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<String,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all Employees in workcenters tree for 02133
     */
    public HashMap<Long, String> getEmployeesworkCenterTree(IRequestInfoDTO ri) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getEmployeesworkCenterTree();
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<Long,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all groups for workcenters tree for 02133
     */
    public HashMap<Long, String> getGroupsworkCenterTree(IRequestInfoDTO ri) throws DataBaseException,
                                                                                    SharedApplicationException {
        return DAO().getGroupsworkCenterTree();
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 12-4-2016
     * @param isNative
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get  all workcenters tree for any group
     * return string of comma separated values of wrk codes  in case u pass native return qry for inner
     */
    public String getWrkcenterDataFilter(IRequestInfoDTO ri, boolean isNative) throws DataBaseException,
                                                                                      SharedApplicationException {
        return DAO().getWrkcenterDataFilter(isNative);
    }

    public String getDataByWrkCenter(IRequestInfoDTO ri, String colName) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().getDataByWrkCenter(colName);
    }


    public List<IEmployeesDTO> getByRealCivilForFTQ(IRequestInfoDTO ri, Long realCivilId,
                                                    String name) throws DataBaseException, SharedApplicationException {
        return DAO().getByRealCivilForFTQ(realCivilId, name);
    }

    @Override
    public IEmployeesDTO getEmpMainInfoBySecurity(IRequestInfoDTO ri, String user_code,
                                                  String user_name) throws DataBaseException,
                                                                           SharedApplicationException {
        return DAO().getEmpMainInfoBySecurity(user_code, user_name);
    }


    public List<IEmployeesDTO> getEmployeesByRealCivilId_eos(IRequestInfoDTO ri,
                                                             Long realCivilId) throws DataBaseException,
                                                                                      SharedApplicationException {
        return DAO().getEmployeesByRealCivilId_eos(realCivilId);
    }

    public List<IBasicDTO> searchByCivilIdAndName(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {

        return DAO().searchByCivilIdAndName(basicDTO);
    }


    public String getUserName(IRequestInfoDTO ri, Long userCode) throws DataBaseException, SharedApplicationException {
        return DAO().getUserName(userCode);
    }

    public Long getCandidateCode(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                          SharedApplicationException {
        return DAO().getCandidateCode(civilId);
    }


    public IPagingResponseDTO getAllEmployeesInMinWithPaging(IRequestInfoDTO ri,
                                                             IBasicDTO _searchDTO) throws DataBaseException,
                                                                                          SharedApplicationException {
        List<IBasicDTO> result = null;
        IPagingResponseDTO responseDTO = new PagingResponseDTO();
        IPagingRequestDTO requestDTO = null;

        if (_searchDTO != null) {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;
            requestDTO = (IPagingRequestDTO)searchDTO.getPagingRequestDTO();

            if (requestDTO != null) {
                Long pageNum = requestDTO.getPageNum();
                // get current page with pageNum:index
                Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
                if (pageNum != null) {
                    if (maxNoOfRecords != null) {
                        Long firstRow = (pageNum * maxNoOfRecords) - maxNoOfRecords;
                        requestDTO.setFirstRowNumber(firstRow);
                        if (firstRow % maxNoOfRecords != 0) {
                            requestDTO.setFirstRowNumber(firstRow + 1);
                        }

                    } else {
                        throw new MaxNoOfRecordsRequiredException();
                    }
                } else {
                    throw new PageNumRequiredException();
                }
                if (requestDTO.isCountRequired()) {
                    Long count = DAO().getCountOfAllEmployeesInMin(searchDTO);
                    responseDTO.setCount(count);
                }
                try {
                    result = DAO().getAllEmployeesInMinWithPaging(searchDTO);
                } catch (ItemNotFoundException e) {
                    throw new NoResultException();
                }
                responseDTO.setBasicDTOList(result);
                responseDTO.setRequestDTO(requestDTO);
            }
        }
        return responseDTO;
    }
    //@Auditable
    public Boolean updateJobCodeForADC(IRequestInfoDTO ri, List<IBasicDTO> basicDTOList,
                                       String jobCode) throws DataBaseException, SharedApplicationException {
        if (basicDTOList != null && basicDTOList.size() != 0 && jobCode != null && !jobCode.isEmpty()) {
            return DAO().updateJobCodeForADC(basicDTOList, jobCode);
        } else {
            return Boolean.FALSE;
        }
    }

    //////////////////////////////////////////////// ESRV ///////////////////////////////////////////

    public List<IBasicDTO> getAllEmployeeswithContract(IRequestInfoDTO ri, Long minCode) throws DataBaseException,
                                                                                                SharedApplicationException {
        return DAO().getAllEmployeeswithContract(minCode);
    }

    public IEmployeesDTO getEmployeeSimpleDTOForESRV(IRequestInfoDTO ri, EmployeesDTO emp) throws DataBaseException,
                                                                                                  SharedApplicationException {
        return DAO().getEmployeeSimpleDTOForESRV(emp);
    }

    public String getUserNameByRole(IRequestInfoDTO ri, Long roleId, Long realCivil,
                                    Long minCode) throws DataBaseException, SharedApplicationException {
        return DAO().getUserNameByRole(roleId, realCivil, minCode);
    }

    public String getUserNamesHighgerRole(IRequestInfoDTO ri, Long roleId, String userNames,
                                          Long minCode) throws DataBaseException, SharedApplicationException {
        return DAO().getUserNamesHighgerRole(roleId, userNames, minCode);
    }

    public List<IEmployeesDTO> getEmpByRealCivilId(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                                SharedApplicationException {
        return DAO().getEmpByRealCivilId(realCivilId);
    }

    public IPagingResponseDTO searchEmpWithoutValidations(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                               SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().searchEmpWithoutValidationsCount(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().searchEmpWithoutValidationsQuery(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }

    public IEmployeesDTO getByRealCivilIdAndHireStatusForExternalMov(IRequestInfoDTO ri, Long realCivilId,
                                                                     Long minCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().getByRealCivilIdAndHireStatusForExternalMov(realCivilId, minCode);

    }

    public IPagingResponseDTO searchEmpWithHireStatus(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                      IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                           SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().searchEmpWithHireStatusCount(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().searchEmpWithHireStatusData(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }

    public IPagingResponseDTO simpleSearchBasicWithPagingADC(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().simpleSearchCountBasicWithPagingADC(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().simpleSearchBasicWithPagingADC(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }

    public IEmployeesDTO getByRealCivilIdForADCWithoutFilteration(IRequestInfoDTO ri, Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getByRealCivilIdForADCWithoutFilteration(realCivilId, minCode);

    }

    public IEmployeesDTO getByRealCivilIdForADCEmployees(IRequestInfoDTO ri, Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getByRealCivilIdForADCEmployees(realCivilId, minCode);

    }

    public List<EmployeesDTO> getAllUserNameByRole(IRequestInfoDTO ri, Long roleId, Long realCivil, Long minCode,
                                                   List<String> userCodes) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().getAllUserNameByRole(roleId, realCivil, minCode, userCodes);
    }

    public Long getMinCodeByCivil(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                           SharedApplicationException {

        return DAO().getMinCodeByCivil(civilId);

    }

    public List<IBasicDTO> searchByCivilAndNameAllMinistries(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                             Boolean mokfaaShamla) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().searchByCivilAndNameAllMinistries(basicDTO, mokfaaShamla);
    }

    public List<ISimpleEmployeesDTO> getByRealCivilForCER(IRequestInfoDTO ri, Long realCivilId,
                                                          String name) throws DataBaseException,
                                                                              SharedApplicationException {
        return DAO().getByRealCivilForCER(realCivilId, name);
    }


    /////////////////start ftq part //////////////////////

    public IPagingResponseDTO searchEmpWithPagingForFTQ(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                             SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().searchEmpWithPagingForFTQCount(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().searchEmpWithPagingForFTQ(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }

    ////////////////end ftq part ////////////////////////

    public IEmployeesDTO getByRealCivilIdBasicData(IRequestInfoDTO ri, Long realCivilId,
                                                   Long minCode) throws DataBaseException, SharedApplicationException {
        return DAO().getByRealCivilIdBasicData(realCivilId, minCode);
    }

    public IEmployeesDTO getByRealCivilIdBasicDataAndMinistryName(IRequestInfoDTO ri, Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getByRealCivilIdBasicDataAndMinistryName(realCivilId, minCode);
    }


    public Long checkFileNoWithMinAndEmployeeForADC(IRequestInfoDTO ri, Long minCode, String minFileNo,
                                                    Long civilId) throws DataBaseException,
                                                                         SharedApplicationException {
        return DAO().countFilesNoWithMinAndEmployeeForADC(minCode, minFileNo, civilId);
    }

    public IPagingResponseDTO searchEmployeeWithPagingForAtt(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().getCountEmpCodeNameForAttSpecialPermission(basicDTO));
            }

            List<IBasicDTO> result = null;

            result = DAO().getEmpCodeNameForAttSpecialPermission(basicDTO, requestDTO);


            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;


    }


    public IBasicDTO getEmpCodeNameByRealCivilIdForAtt(IRequestInfoDTO ri,
                                                       IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().getEmpCodeNameByRealCivilIdForAtt(basicDTO);

    }

    public String getCivilsRelatedToWorkCenter(IRequestInfoDTO ri, String workCenter, boolean isRelated,
                                               boolean isNative) throws DataBaseException, SharedApplicationException {
        return DAO().getCivilsRelatedToWorkCenter( workCenter,  isRelated,isNative);

    }


    public Date getEmpFirstHireDate(IRequestInfoDTO ri,Long realCivilId) throws DataBaseException, SharedApplicationException {
        return DAO().getEmpFirstHireDate(realCivilId) ;
    }

    public IEmployeesDTO getEmployeesByRealCivilIdAndMinForBankWebService(IRequestInfoDTO ri, Long realCivilId,
                                                                          Long minCode,
                                                                          String hireStatusListCommaSeparated) throws DataBaseException,
                                                                                                                      SharedApplicationException {
        IEmployeesDTO employeesDTO = DAO().getEmployeesByRealCivilIdAndMinForBankWebService(realCivilId, minCode,
                                                                      hireStatusListCommaSeparated);
        Date firstHireDate  = DAO().getEmpFirstHireDate(realCivilId);
        employeesDTO.setFirstHireDate(firstHireDate);
        //employeesDTO.setF
        return employeesDTO;

    }

    /**
     * @author m.sayed
     * @since   20-6-2018
     * @param realCivilId
     * @param name
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IEmployeesDTO> getByRealCivilForFTQWithDF(IRequestInfoDTO ri,Long realCivilId, String name) throws DataBaseException,SharedApplicationException{
        return DAO().getByRealCivilForFTQWithDF(realCivilId,name) ;
    }

    public String getEmpCodeNameForContractEmp(IRequestInfoDTO ri,Long  civilID, Long minCode) throws DataBaseException,
    SharedApplicationException{
        return DAO().getEmpCodeNameForContractEmp(civilID, minCode);
    }

    public boolean checkIfEmployeeExists(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException {
        List<IEmployeesDTO> empList = new ArrayList<IEmployeesDTO>();
        empList = DAO().checkIfEmployeeExists(realCivilId);
        if (empList != null && empList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    //@Auditable
    public boolean updateNextRaiseDate(IRequestInfoDTO ri, Date nextRaiseDate, String civilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().updateNextRaiseDate(nextRaiseDate, civilId);
    }

    public IEmployeesDTO searchByCivilIdEstana(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        return DAO().searchByCivilIdEstana(basicDTO);
    }

    public IBasicDTO getEmployeeDTOCodeNameWithoutFilteration(IRequestInfoDTO ri, Long civilID) throws DataBaseException,
                                                                                     SharedApplicationException {
        return DAO().getEmployeeDTOCodeNameWithoutFilteration(civilID);
    }

    public IBasicDTO getEmpCurrentJob(IRequestInfoDTO ri, Long realCivilID) throws DataBaseException, SharedApplicationException {

        return DAO().getEmpCurrentJob(realCivilID);
    }

    public IEmployeesDTO getByRealCivilIdAllMinistriesForAdc(IRequestInfoDTO ri,Long realCivilId) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().getByRealCivilIdAllMinistriesForAdc(realCivilId);
                                                                                }

    public IBasicDTO getSubWorkCeneterCodeName(IRequestInfoDTO ri, String workCode,Long realCivilId , Long minCode)throws DataBaseException,SharedApplicationException {
        IBasicDTO basicDTO =null;
        //check workCenter have sub workCenters
        Long searchResult = DAO().checkWCHaveSubWC(workCode ,  minCode);
        if(searchResult >=1L){
            //get workName
            basicDTO = DAO().getSubWCCodeName(realCivilId, minCode);
        }

        return basicDTO;

    }

    public EmpStatusForSalDTO getEmpStatusAndHireOrEndServiceDateForSal(IRequestInfoDTO ri,long realCivilId) throws DataBaseException,SharedApplicationException {
        return   DAO().getEmpStatusAndHireOrEndServiceDateForSal(realCivilId);
     }
    public IPagingResponseDTO simpleSearchBasicWithPagingForLeaders(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {

           IPagingResponseDTO responseDTO = null;
           if (requestDTO != null) {
               Long pageNum = requestDTO.getPageNum();
               Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
               if (pageNum != null) {
                   if (maxNoOfRecords != null) {
                       requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                   } else {
                       throw new MaxNoOfRecordsRequiredException();
                   }
               } else {
                   throw new PageNumRequiredException();
               }

               responseDTO = new PagingResponseDTO();

               if (requestDTO.isCountRequired()) {
                 //  responseDTO.setCount(DAO().simpleSearchCountBasicWithPagingForLeaders(basicDTO));
                   responseDTO.setCount(10L);
               }

               List<IBasicDTO> result = null;

               result = DAO().simpleSearchBasicWithPagingForLeaders(basicDTO, requestDTO);


               responseDTO.setBasicDTOList(result);
               responseDTO.setRequestDTO(requestDTO);
           }

           return responseDTO;


       }

    public IEmployeesDTO getEmpCodeNameByRealCivilIdForLeaders(IRequestInfoDTO ri,EmpEmployeeSearchDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().getEmpCodeNameByRealCivilIdForLeaders(basicDTO);
         }

    public IEmployeesDTO getByCivilIdForPRM(IRequestInfoDTO ri,Long civilId) throws DataBaseException, SharedApplicationException{
        return DAO().getByCivilIdForPRM(civilId);
      }
      /**
       *@author msayed
       * @since 14-5-2020
       * @get data for mobile webserivce
       * @param realCivilID
       * @return
       * @throws DataBaseException
       * @throws SharedApplicationException
       */
      public IEmployeeDTOService getAllEmployeeDataForProfileMob(IRequestInfoDTO ri,Long  realCivilID) throws DataBaseException, SharedApplicationException {
          return DAO().getAllEmployeeDataForProfileMob(realCivilID);
      }

      /**
       *@author msayed
       * @since 14-5-2020
       * @get data for mobile webserivce
       * @param ri
       * @param realCivilID
       * @param yearCode
       * @param monthCode
       * @return
       * @throws DataBaseException
       * @throws SharedApplicationException
       */
      public IEmpFinicialData getMertisAndDeducts(IRequestInfoDTO ri,Long realCivilID,Long yearCode,Long monthCode)throws DataBaseException, SharedApplicationException {
          return DAO().getMertisAndDeducts(realCivilID,yearCode,monthCode);
      }
    /**
     *@author msayed
     * @since 04-07-2020
     * @get data for mobile webserivce
     * @param realCivilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IEmployeeDTOService getAllEmployeeDataForProfileMobVW(IRequestInfoDTO ri,Long  realCivilID) throws DataBaseException, SharedApplicationException {
        return DAO().getAllEmployeeDataForProfileVW(realCivilID);
    }

    /**
     *@author msayed
     * @since 04-07-2020
     * @get data for mobile webserivce
     * @param ri
     * @param realCivilID
     * @param yearCode
     * @param monthCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IEmpFinicialData getMertisAndDeductsVW(IRequestInfoDTO ri,Long realCivilID,Long yearCode,Long monthCode)throws DataBaseException, SharedApplicationException {
        return DAO().getMertisAndDeductsVw(realCivilID,yearCode,monthCode);
    }
    /**
     *@author msayed
     * @since 04-07-2020
     * @get data for mobile webserivce
     * @param ri
     * @param realCivilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IEmpBonusData> getEmpBonusDataVW(IRequestInfoDTO ri,Long realCivilID)throws DataBaseException, SharedApplicationException {
        return DAO().getEmpBonusData(realCivilID);
    }
}
