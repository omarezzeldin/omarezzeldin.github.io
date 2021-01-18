package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.base.security.IMinistryInfo;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.emp.business.deploy.EmployeesSession;
import com.beshara.csc.hr.emp.business.dto.EmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.EmpSearchRecodDesc;
import com.beshara.csc.hr.emp.business.dto.EmpStatusForSalDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.shared.ISimpleEmployeesDTO;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class EmployeesClientImpl extends BaseClientImpl3 implements IEmployeesClient {

    /**
     * @param contextSession
     */
    public EmployeesClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmployeesSession.class;
    }

    protected EmployeesSession SESSION() {
        return (EmployeesSession)super.SESSION();

    }

    public List<IBasicDTO> search(Long hirtypeCode) throws DataBaseException {
        try {
            return SESSION().search(RI(), hirtypeCode);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> searchByMainHireType(Long hirtypeCode) throws DataBaseException {
        try {
            return SESSION().searchByMainHireType(RI(), hirtypeCode);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getAllEmployeesWithActiveHireTypes() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllEmployeesWithActiveHireTypes(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Get all employee copmleted their documents , procedures and waiting for hire decision * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllEmployeeWaitingForHireDecision() throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().getAllEmployeeWaitingForHireDecision(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * This method to send procedure for list of employee and check if the procedure valid to send or not * @param employeeDTOList
     * @return list of resultDTO
     */
    public List<IResultDTO> sendProcedure(List<IBasicDTO> employeeDTOList) throws SharedApplicationException {


        try {
            return SESSION().sendProcedure(RI(), employeeDTOList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Filter employee waiting for hire decision depend on hire type * @param empEmployeeSearchDTO
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                                          SharedApplicationException {
        try {
            return SESSION().getFilterEmployeeWaitingForHireDecision(RI(), empEmployeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Filter employee waiting for hire decision depend on hire type 22/2/2009 * @param hireTypesEntityKey
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                         SharedApplicationException {
        try {
            return SESSION().getFilterEmployeeWaitingForHireDecision(RI(), hireTypesEntityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> simpleSearch(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        try {

            return SESSION().simpleSearch(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> simpleSearchForAOE(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                         DataBaseException {
        try {

            return SESSION().simpleSearchForAOE(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Aly Nour @since 30/10/2014
     * to used in Mer to check if added child is Emp
     */
    public List<IBasicDTO> simpleSearchWithoutMinCode(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                 DataBaseException {
        try {
            return SESSION().simpleSearch(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IPagingResponseDTO simpleSearchWithPaging(IBasicDTO basicDTO,
                                                     IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                          DataBaseException {
        try {
            return SESSION().simpleSearchWithPaging(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO simpleSearchEmpWithPaging(IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                             DataBaseException {
        try {
            return SESSION().simpleSearchEmpWithPaging(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> simpleSearchBasic(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().simpleSearchBasic(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO simpleSearchBasicWithPaging(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                               DataBaseException {
        try {
            return SESSION().simpleSearchBasicWithPaging(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO simpleSearchBasicWithPagingAtt(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                               DataBaseException {
        try {
            return SESSION().simpleSearchBasicWithPagingAtt(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                DataBaseException {
        try {
            return SESSION().simpleSearchVacEmpWithPaging(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IBasicDTO basicDTO,IBgtProgramsDTO level,
                                                           IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                DataBaseException {
        try {
            return SESSION().simpleSearchVacEmpWithPaging(RI(), basicDTO,level, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> simpleSearchVacEmp(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                         DataBaseException {
        try {
            return SESSION().simpleSearchVacEmp(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> simpleSearchVacEmpAllData(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                DataBaseException {
        try {
            return SESSION().simpleSearchVacEmpAllData(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List simpleSearchVacEmpEnhanced(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().simpleSearchVacEmpEnhanced(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IBasicDTO> searchVacEmp(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().searchVacEmp(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * search hired employees and return list of employees whose civilIDs are not in the * @param basicDTO the IEmployeesDTO search criteria DTO
     * @param salEmpSalaryElementsDTOList the list of employees to be not included in the search result set
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchFilterEmployee(IBasicDTO basicDTO,
                                                List<IBasicDTO> salEmpSalaryElementsDTOList) throws SharedApplicationException,
                                                                                                    DataBaseException {
        try {
            return SESSION().searchFilterEmployee(RI(), basicDTO, salEmpSalaryElementsDTOList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * To add new Employee * @param employeesDTO1
     * @param usingTransAction
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO add(IBasicDTO employeesDTO1, boolean usingTransAction) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().add(RI(), employeesDTO1, usingTransAction);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Add Join To Decision * @param employeesList
     * @param decisionDTO
     * @return ResultDTO
     * @throws DataBaseException
     */
    public List<ResultDTO> addJoinToDecision(List<IBasicDTO> employeesList,
                                             IBasicDTO decisionDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().addJoinToDecision(RI(), employeesList, decisionDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Change employee budget type * @param changeBudgetTypeDTO1
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<ResultDTO> changeBudgetType(IBasicDTO changeBudgetTypeDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().changeBudgetType(RI(), changeBudgetTypeDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * get employee info with his salary element info ( valid ) , for specific type * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Amir Nasr
     */
    public IBasicDTO getEmployeeAndPayrollByElmType(Long civilId) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().getEmployeeAndPayrollByElmType(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * used in to filter employees by WorkCenter * @param workCenterCode
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllEmployeesByWorkCenter(String workCenterCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().getAllEmployeesByWorkCenter(RI(), workCenterCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * find if an employee exists in a certain ministry * @param civilId
     * @param minCode
     * @return Boolean
     * @throws DataBaseException
     */
    public Boolean isEmployeeInMinistry(Long civilId, Long minCode) throws DataBaseException {

        try {
            return SESSION().isEmployeeInMinistry(RI(), civilId, minCode);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            throw getWrappedException(ex);
        }
    }

    /**
     * Add Decision to employee to hire * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean addDecision(List<IBasicDTO> employeesList,
                               IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().addDecision(RI(), employeesList, basicDecisionDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    //    /**
    //     * check if an minFileNo exists * @param ministryFileNo
    //     * @return Boolean
    //     * @throws DataBaseException
    //     */
    //    public Boolean isMinistryFileNoExist(String ministryFileNo) throws DataBaseException {
    //        try {
    //            return SESSION().isMinistryFileNoExist(RI(), ministryFileNo);
    //        } catch (SharedApplicationException e) {
    //            throw new RuntimeException(e);
    //        } catch (Exception e) {
    //            throw getWrappedException(e);
    //        }
    //
    //    }

    /**
     * Get all employee after hire decision to start work * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToStartWork() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllToStartWork(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Search employee after hire decision to start work * @param civilId
     * @param name
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployeeStartWork(Long civilId, String name) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().searchEmployeeStartWork(RI(), civilId, name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * execute start work * @param employeesDTO1
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean executeStartWork(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().executeStartWork(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Get all employee to complete employee's date * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToCompleteData() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllToCompleteData(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Search employee to complete employee's date * @param employeeSearchDTO
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployeeToCompleteData(IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            return SESSION().searchEmployeeToCompleteData(RI(), employeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Check if citizen is legel to employment with specific hire type * @param civilID
     * @param hireType
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean checkLegelHireType(Long civilID, Long hireType) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().checkLegelHireType(RI(), civilID, hireType);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * used in to filter employees by user ministry * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllEmployeesByCuurentMinistry() throws DataBaseException, SharedApplicationException {
        try { //TODO add security code for ministry
            return SESSION().getEmployeesByMinistry(RI(), CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getAllEmployeeByStageAndMinistry(Long minCode,
                                                            String selectedStage) throws DataBaseException,
                                                                                         SharedApplicationException {
        try { //TODO add security code for ministry
            return SESSION().getAllEmployeeByStageAndMinistry(RI(), minCode, selectedStage);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * check if employee is hired * @param employeesEntityKey
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean isEmployeeHired(IEntityKey employeesEntityKey) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().isEmployeeHired(RI(), employeesEntityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean isEmployeeHiredForVac(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                SharedApplicationException {

        try {
            return SESSION().isEmployeeHiredForVac(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean isEmployeeHired(Long realCivilId, Long minCode) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().isEmployeeHired(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    /**
     * Update Employee Data * @param employeesDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public IBasicDTO updateEmp(IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().updateEmp(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    // update Emp cancelCandidate

    public IBasicDTO updateEmpCancelCandidate(IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                              DataBaseException {
        try {
            return SESSION().updateEmpCancelCandidate(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Get all employee match search criteria and sorted by full name * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployee(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().searchEmployee(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * To add new Employee in ministry * @param minKey ministry key to add the employee to it
     * @param employeesDTO1 the employee that will be added
     * @param usingTransAction to commit or not commit ( ignored )
     * @return IEmployeesDTO the added employee dto
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO addInMinistry(IEntityKey minKey, IBasicDTO employeesDTO1,
                                   boolean usingTransAction) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().add(RI(), employeesDTO1, usingTransAction);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * Validate employee civil id if it exists or nt then validate his hire type * @param civilID
     * @param hireType
     * @return TRUE : Employee exist and his hire type is legal
     * FALSE : Employee doesnt exist * throws NotMatchedOnHireTypeException : not a legal hire type * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean validateEmployeeHireType(Long civilID, Long hireType) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return SESSION().validateEmployeeHireType(RI(), civilID, hireType);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Get all employees match search criteria and sorted by full name for sal cal * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchEmployeeForSalCalc(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return SESSION().searchEmployeeForSalCalc(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> searchMerVariableEmployee(IBasicDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return SESSION().searchMerVariableEmployee(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchEmployeeForSalPayment(IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().searchEmployeeForSalPayment(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * get employee info for specific type * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author MLotfy
     */
    public IBasicDTO getEmployeeInfoByElmType(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmployeeInfoByElmType(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO getEmployeeInfoHasSocialRaise(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmployeeInfoHasSocialRaise(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getCurrentEmpFinancialData(IEntityKey id1) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().getCurrentEmpFinancialData(RI(), id1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * check if employee valid to add request to end his/her service * @param civilId
     * @return Boolean
     * @throws DataBaseException
     */
    public Boolean isEmployeeValidToEndService(Long civilId) throws DataBaseException {
        try {
            return SESSION().isEmployeeValidToEndService(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    //    public Boolean updateEmployeeDataForMov(Long civilId, Long employeeHireStatus,
    //                                            Date endJobDate) throws DataBaseException, SharedApplicationException {
    //        try {
    //            return SESSION().updateEmployeeDataForMov(RI(), civilId, employeeHireStatus,
    //                                                             endJobDate);
    //        } catch (SharedApplicationException e) {
    //            throw e;
    //        } catch (Exception e) {
    //            throw getWrappedException(e);
    //        }
    //
    //    }

    //    public IBasicDTO addEmployeeForMovOnly(IBasicDTO employeesDTO1) throws DataBaseException,
    //                                                                           SharedApplicationException {
    //        try {
    //            return SESSION().addEmployeeForMovOnly(RI(), employeesDTO1);
    //        } catch (SharedApplicationException e) {
    //            throw e;
    //        } catch (Exception e) {
    //            throw getWrappedException(e);
    //        }
    //
    //    }


    public List<IBasicDTO> getEmployeByCivilId(Long civilID) throws FinderException, SharedApplicationException {
        try {
            return SESSION().getEmployeByCivilId(RI(), civilID);
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * return EmployeeDTO civilId and KwtCitizensResidentsDTO fullName with auditing attributes
     * @param civilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Ashraf Gaber 19/01/2011
     */
    public IBasicDTO getEmployeeDTOCodeName(Long civilID) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmployeeDTOCodeName(RI(), civilID);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public List<IBasicDTO> searchEmployeesForEvaluator(Long evaluatorCivilId,
                                                       IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            return SESSION().searchEmployeesForEvaluator(RI(), evaluatorCivilId, employeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchEmployeesForEvaluatorByCivilId(Long evaluatorCivilId,
                                                                IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            return SESSION().searchEmployeesForEvaluatorByCivilId(RI(), evaluatorCivilId, employeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchEmployeesForEvaluatorByName(Long evaluatorCivilId,
                                                             IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().searchEmployeesForEvaluatorByName(RI(), evaluatorCivilId, employeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Date calculateNextRaiseDate(Date hireDate) throws SharedApplicationException {
        try {
            return SESSION().calculateNextRaiseDate(RI(), hireDate);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }
    }

    public Long[] getEmpServicePeriod(Long civilId) throws DataBaseException {
        try {
            return SESSION().getEmpServicePeriod(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IPagingResponseDTO getByHireStageWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            return SESSION().getByHireStageWithPaging(RI(), _searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO searchWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().searchWithPaging(RI(), _searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public void refresh() throws DataBaseException {
        try {
            SESSION().refresh(RI());
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByCivilAndName(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().searchByCivilAndName(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> simpleSearchFullData(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().simpleSearchFullData(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> simpleSearchAllMinistriesFullData(IBasicDTO basicDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            return SESSION().simpleSearchAllMinistriesFullData(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    /** added by
     * Hany Omar 16/2/2014
     */
    @Override
    public List<IBasicDTO> filterByHireType(Long hirtypeCode) throws DataBaseException {
        try {
            return SESSION().filterByHireType(RI(), hirtypeCode);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     *
     * @param hireTypeList
     * @return
     * @throws DataBaseException
     * @Author Hany Omar
     */

    public List<IBasicDTO> filterByAllHireTypes() throws DataBaseException {
        try {
            return SESSION().filterByAllHireTypes(RI());
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    @Override
    @Deprecated
    public IEmployeesDTO getByRealCivilId(Long realCivilId, Long minCode) throws RemoteException, FinderException {

        try {
            return SESSION().getByRealCivilId(RI(), realCivilId, minCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    /**
     * this method will get employee data for e-service
     * @param realCivilId
     * @param minCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public IEmployeesDTO getByRealCivilIdForEsrv(Long realCivilId, Long minCode) throws RemoteException,
                                                                                        FinderException {

        try {
            return SESSION().getByRealCivilIdForEsrv(RI(), realCivilId, minCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public IEmployeesDTO getCivilByRealCivil(Long realCivilId, Long minCode) throws RemoteException, FinderException {

        try {
            return SESSION().getCivilByRealCivil(RI(), realCivilId, minCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public IEmployeesDTO getSimpleEmployeeByRealCivilId(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                               SharedApplicationException {

        try {
            return SESSION().getSimpleEmployeeByRealCivilId(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IEmployeesDTO getByRealCivilIdAndHireStatus(Long realCivilId, Long minCode) throws RemoteException,
                                                                                              FinderException {

        try {
            return SESSION().getByRealCivilIdAndHireStatus(RI(), realCivilId, minCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdInallMin(Long realCivilId) throws RemoteException, FinderException {

        try {
            return SESSION().getByRealCivilIdInallMin(RI(), realCivilId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }


    public IEmployeesDTO getByRealCivilIdInallMinForReg(Long realCivilId) throws RemoteException, FinderException {

        try {
            return SESSION().getByRealCivilIdInallMinForReg(RI(), realCivilId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdForReg(Long realCivilId) throws RemoteException, FinderException {
        try {
            return SESSION().getByRealCivilIdForReg(RI(), realCivilId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdForMovWithoutFilteration(Long realCivilId,
                                                                  Long minCode) throws RemoteException,
                                                                                       FinderException {

        try {
            return SESSION().getByRealCivilIdForMovWithoutFilteration(RI(), realCivilId, minCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMov(Long realCivilId,
                                                             Long minCode) throws SharedApplicationException,
                                                                                  DataBaseException {

        try {
            return SESSION().getByRealCivilIdAndHireStatusForMov(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw (e);
        } catch (DataBaseException e) {
            throw getWrappedException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMovSimple(Long realCivilId,
                                                                   Long minCode) throws RemoteException,
                                                                                        FinderException {

        try {
            return SESSION().getByRealCivilIdAndHireStatusForMovSimple(RI(), realCivilId, minCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }


    public IEmployeesDTO getEmpForMov(Long realCivilId, Long minCode) throws   SharedApplicationException,
                                                                                                 DataBaseException{

        try {
            return SESSION().getEmpForMov(RI(), realCivilId, minCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

    }


    public IEmployeesDTO getByRealCivilIdAllMinistries(Long realCivilId) throws RemoteException, FinderException {

        try {
            return SESSION().getByRealCivilIdAllMinistries(RI(), realCivilId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    /**
     * filter employees by hire types for employee hire execution
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     * @author Dina
     */
    public List<IBasicDTO> filterByHireTypeForHireExecute(Long hirtypeCode) throws DataBaseException {
        try {
            return SESSION().filterByHireTypeForHireExecute(RI(), hirtypeCode);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> filterByAllHireTypesForHireExecute() throws DataBaseException {
        try {
            return SESSION().filterByAllHireTypesForHireExecute(RI());
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    /**
     * return true if employee is hired
     * @param civilID
     * @return
     * @throws RemoteException
     * @author Dina Abd El-Rahim 06/03/2014
     **/
    public Boolean isEmployeeHiredForVac(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().isEmployeeHiredForVac(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Long isJobHasEmployees(Long jobCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().isJobHasEmployeees(RI(), jobCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public Long countEmployeesByJobAndMin(String jobCode, Long minCode) throws SharedApplicationException,
                                                                               DataBaseException {
        try {
            return SESSION().countEmployeesByJobAndMin(RI(), jobCode, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean checkEmployeeRequiredProceduresStatus(Long serial) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            return SESSION().checkEmployeeRequiredProceduresStatus(RI(), serial);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean checkEmployeeRequiredDocumentsStatus(Long serial) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().checkEmployeeRequiredDocumentsStatus(RI(), serial);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getAllEmployeesByPremittedMinistries(IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            return SESSION().getAllEmployeesByPremittedMinistries(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> filterAvailableEntitiesUsingPaging(IBasicDTO employeeSearchDTO1,
                                                              IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            return SESSION().filterAvailableEntitiesUsingPaging(RI(), employeeSearchDTO1, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Long filterAvailableEntitiesUsingPagingCount(IBasicDTO employeeSearchDTO1) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return SESSION().filterAvailableEntitiesUsingPagingCount(RI(), employeeSearchDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /*
         * count employees hired Status By minCode,wrkCode
         */

    public Long countHiredEmployeesByMinWrkCode(Long minCode, String wrkCode) throws RemoteException, FinderException {

        try {
            return SESSION().countHiredEmployeesByMinWrkCode(RI(), minCode, wrkCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public String getStatusForHire() throws RemoteException {
        return SESSION().getStatusForHire(RI());
    }

    public List<IMinistriesDTO> getMinistriesListByEvalCivilId(Long evalCivilId,
                                                               List<IMinistryInfo> minList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            return SESSION().getMinistriesListByEvalCivilId(RI(), evalCivilId, minList);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public List<IMinistriesDTO> getMinistriesListByEvalCivilIdNew(Long evalCivilId,
                                                               List<IMinistriesDTO> minList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            return SESSION().getMinistriesListByEvalCivilIdNew(RI(), evalCivilId, minList);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public IBasicDTO addEmployeeForMovOnly(IBasicDTO employeesDTO1) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().addEmployeeForMovOnly(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdAllMinByRecordDesc(Long realCivilId) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().getByRealCivilIdAllMinByRecordDesc(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean isEmployeeHiredWithRecordDesc(Long realCivilId) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().isEmployeeHiredWithRecordDesc(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getEmpByEmpSearch(EmpSearchRecodDesc searchDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().getEmpByEmpSearch(RI(), searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public Boolean updateEmployeeForPRM(IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().updateEmployeeForPRM(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean updateEmployeeStaus(IEntityKey empKey, IEntityKey employeeHireStatus) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            return SESSION().updateEmployeeStaus(RI(), empKey, employeeHireStatus);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getCurrActiveEmpByRealCivilId(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {

        try {
            return SESSION().getCurrActiveEmpByRealCivilId(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean checkFileNoWithMinAndEmployeeForMov(Long minCode, String minFileNo,
                                                       Long civilId) throws DataBaseException,
                                                                            SharedApplicationException {

        try {
            return SESSION().checkFileNoWithMinAndEmployeeForMov(RI(), minCode, minFileNo, civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public int updateActiveFlagByRealCivilId(Long realCivilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().updateActiveFlagByRealCivilId(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public int updateWorkCodeByRealCivilId(Long realCivilId, String workCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().updateWorkCodeByRealCivilId(RI(), realCivilId, workCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                                            SharedApplicationException {

        try {
            return SESSION().getCurrActiveEmployeesByRealCivilId(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(Long realCivilId) throws DataBaseException,
                                                                                                                  SharedApplicationException {

        try {
            return SESSION().getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean checkHireDateContradiction(IBasicDTO basicDTO) throws DataBaseException,
                                                                         SharedApplicationException {

        try {
            return SESSION().checkHireDateContradiction(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IBasicDTO addEmployeeForExecuteMovOnly(IBasicDTO employeesDTO1) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().addEmployeeForExecuteMovOnly(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IPagingResponseDTO simpleSearchMovEmpWithPaging(IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                DataBaseException {
        try {
            return SESSION().simpleSearchMovEmpWithPaging(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO simpleSearchMovEmpWithPagingAllChecked(IBasicDTO basicDTO, IPagingRequestDTO requestDTO,
                                                                     List<String> unCheckedList) throws SharedApplicationException,
                                                                                                        DataBaseException {
        try {
            return SESSION().simpleSearchMovEmpWithPagingAllChecked(RI(), basicDTO, requestDTO, unCheckedList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public Boolean updateEmployeeBeforeExecuteEmployment(Long realCivilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().updateEmployeeBeforeExecuteEmployment(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public int updateActiveFlagForDelegation(Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().updateActiveFlagForDelegation(RI(), activeFlag, hireStatusCode, hireDate, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public int updateActiveFlagForDelegationWithRCivil(Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode, Long realCivilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().updateActiveFlagForDelegationWithRCivil(RI(), activeFlag, hireStatusCode, hireDate, minCode,realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean isEmpIndebt(Long realCivilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().isEmpIndebt(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean isConditionFromGrsValid(Long realCivilId, Long degreeCode) throws DataBaseException,
                                                                                     SharedApplicationException {

        try {
            return SESSION().isConditionFromGrsValid(RI(), realCivilId, degreeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * return all hired employees which not in specific job level ( used in JOB-MODULE )
     * @param jobSearchCriteriaDTO
     * @return List<IEmployeesDTO>
     * @author I.Omar 15/10/2015
     **/
    public List<IEmployeesDTO> getAllHiredEmployeesByJobLevelsAndType(IJobSearchCriteriaDTO jobSearchCriteriaDTO,
                                                                      Map<String, Long> conditionMap) throws DataBaseException,
                                                                                                             SharedApplicationException {
        try {
            return SESSION().getAllHiredEmployeesByJobLevelsAndType(RI(), jobSearchCriteriaDTO, conditionMap);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IPagingResponseDTO getAllEmployeesByForLoginMinistries(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                             DataBaseException {
        try {
            return SESSION().getAllEmployeesByForLoginMinistries(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getEmpCodeNameByRealCivilId(IBasicDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return SESSION().getEmpCodeNameByRealCivilId(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByCivilAndNameUsingMinCode(IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            return SESSION().searchByCivilAndNameUsingMinCode(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByCivilAndNameUsingMinCodeForCER(IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            return SESSION().searchByCivilAndNameUsingMinCodeForCER(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 30-3-2016
     * @param groupCode
     * @param userCode
     * @param realCivilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * check if group code or user code granted or revoked for such real civil
     */
    public boolean checkFilterDataForEmp(Long groupCode, Long userCode, Long realCivilID) throws DataBaseException,
                                                                                                 SharedApplicationException {

        try {
            return SESSION().checkFilterDataForEmp(RI(), groupCode, userCode, realCivilID);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return String
     * CSC-17343 
     * check if all workcenters tree for 02133
     */
    public String applyWrkcenterTreeFilter() throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().applyWrkcenterTreeFilter(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
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

    public HashMap<String, String> getworkCenterTree() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getworkCenterTree(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public HashMap<Long, String> getEmployeesworkCenterTree() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmployeesworkCenterTree(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
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
    public HashMap<Long, String> getGroupsworkCenterTree() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getGroupsworkCenterTree(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return List<String>
     * CSC-17343 
     * check if all workcenters tree for 02133
     */
    public List<String> filterDataByWrkCenterList() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().filterDataByWrkCenterList(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
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
    public String getWrkcenterDataFilter(boolean isNative) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getWrkcenterDataFilter(RI(), isNative);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public String getDataByWrkCenter(String colName) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getDataByWrkCenter(RI(), colName);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IEmployeesDTO> getByRealCivilForFTQ(Long realCivilId, String name) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return SESSION().getByRealCivilForFTQ(RI(), realCivilId, name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public List<IEmployeesDTO> getEmployeesByRealCivilId_eos(Long realCivilId) throws DataBaseException,
                                                                                      SharedApplicationException

    {

        try {
            return SESSION().getEmployeesByRealCivilId_eos(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public List<IBasicDTO> searchByCivilIdAndName(IBasicDTO basicDTO) throws DataBaseException,
                                                                             SharedApplicationException {

        try {
            return SESSION().searchByCivilIdAndName(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public String getUserName(Long userCode) throws DataBaseException, SharedApplicationException {


        try {
            return SESSION().getUserName(RI(), userCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public Long getCandidateCode(Long civilId) throws DataBaseException, SharedApplicationException {


        try {
            return SESSION().getCandidateCode(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IPagingResponseDTO getAllEmployeesInMinWithPaging(IBasicDTO searchDTO) throws DataBaseException,
                                                                                         SharedApplicationException {
        try {
            return SESSION().getAllEmployeesInMinWithPaging(RI(), searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean updateJobCodeForADC(List<IBasicDTO> basicDTOList, String jobCode) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            return SESSION().updateJobCodeForADC(RI(), basicDTOList, jobCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IEmployeesDTO> getEmpByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                            SharedApplicationException {

        try {
            return SESSION().getEmpByRealCivilId(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IEmployeesDTO getByRealCivilIdAndHireStatusForExternalMov(Long realCivilId,
                                                                     Long minCode) throws DataBaseException,
                                                                                          SharedApplicationException {

        try {
            return SESSION().getByRealCivilIdAndHireStatusForExternalMov(RI(), realCivilId, minCode);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (DataBaseException e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }

    }

    public IPagingResponseDTO searchEmpWithHireStatus(IBasicDTO basicDTO,
                                                      IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                           DataBaseException {
        try {
            return SESSION().searchEmpWithHireStatus(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO simpleSearchBasicWithPagingADC(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                  DataBaseException {
        try {
            return SESSION().simpleSearchBasicWithPagingADC(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdForADCWithoutFilteration(Long realCivilId,
                                                                  Long minCode) throws SharedApplicationException,
                                                                                       DataBaseException {

        try {
            return SESSION().getByRealCivilIdForADCWithoutFilteration(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getByRealCivilIdForADCEmployees(Long realCivilId,
                                                                  Long minCode) throws SharedApplicationException,
                                                                                       DataBaseException {

        try {
            return SESSION().getByRealCivilIdForADCEmployees(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getEmpMainInfoBySecurity(String user_code,
                                                  String user_name) throws SharedApplicationException,
                                                                           DataBaseException {
        try {
            return SESSION().getEmpMainInfoBySecurity(RI(), user_code, user_name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Long getMinCodeByCivil(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getMinCodeByCivil(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO searchEmpWithoutValidations(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                               DataBaseException {
        try {
            return SESSION().searchEmpWithoutValidations(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByCivilAndNameAllMinistries(IBasicDTO basicDTO,
                                                             Boolean mokfaaShamla) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return SESSION().searchByCivilAndNameAllMinistries(RI(), basicDTO, mokfaaShamla);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<ISimpleEmployeesDTO> getByRealCivilForCER(Long realCivilId, String name) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            return SESSION().getByRealCivilForCER(RI(), realCivilId, name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IPagingResponseDTO searchEmpWithPagingForFTQ(IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                             DataBaseException {
        try {
            return SESSION().searchEmpWithPagingForFTQ(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IEmployeesDTO getByRealCivilIdBasicData(Long realCivilId, Long minCode) throws SharedApplicationException,
                                                                                          DataBaseException {
        try {
            return SESSION().getByRealCivilIdBasicData(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IEmployeesDTO getByRealCivilIdBasicDataAndMinistryName(Long realCivilId,
                                                                  Long minCode) throws SharedApplicationException,
                                                                                       DataBaseException {
        try {
            return SESSION().getByRealCivilIdBasicDataAndMinistryName(RI(), realCivilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Long checkFileNoWithMinAndEmployeeForADC(Long minCode, String minFileNo,
                                                    Long civilId) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().checkFileNoWithMinAndEmployeeForADC(RI(), minCode, minFileNo, civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IPagingResponseDTO searchEmployeeWithPagingForAtt(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                  DataBaseException {
        try {
            return SESSION().searchEmployeeWithPagingForAtt(RI(), basicDTO, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IBasicDTO getEmpCodeNameByRealCivilIdForAtt(IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().getEmpCodeNameByRealCivilIdForAtt(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public String getCivilsRelatedToWorkCenter(String workCenter, boolean isRelated,
                                               boolean isNative) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCivilsRelatedToWorkCenter(RI(), workCenter, isRelated, isNative);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }



    public Date getEmpFirstHireDate(Long realCivilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmpFirstHireDate(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmployeesDTO getEmployeesByRealCivilIdAndMinForBankWebService(Long realCivilId, Long minCode,
                                                                          String hireStatusListCommaSeparated) throws SharedApplicationException,
                                                                                                                      DataBaseException {
        try {
            return SESSION().getEmployeesByRealCivilIdAndMinForBankWebService(RI(), realCivilId, minCode,
                                                                              hireStatusListCommaSeparated);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
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
    public List<IEmployeesDTO> getByRealCivilForFTQWithDF(Long realCivilId, String name) throws DataBaseException,SharedApplicationException{
        try{
            return SESSION().getByRealCivilForFTQWithDF(RI(),realCivilId,name) ;
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public String getEmpCodeNameForContractEmp(Long  civilID, Long minCode) throws DataBaseException,
    SharedApplicationException{
            try{
                return SESSION().getEmpCodeNameForContractEmp(RI(),civilID,minCode) ;
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

        }


    public boolean updateNextRaiseDate(Date nextRaiseDate, String civilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().updateNextRaiseDate(RI(), nextRaiseDate, civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IEmployeesDTO searchByCivilIdEstana( IBasicDTO basicDTO) throws DataBaseException,
    SharedApplicationException{
            try {
                return SESSION().searchByCivilIdEstana(RI(), basicDTO);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

        }

    public IBasicDTO getEmployeeDTOCodeNameWithoutFilteration(Long civilID) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmployeeDTOCodeNameWithoutFilteration(RI(), civilID);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IEmployeesDTO getByRealCivilIdAllMinistriesForAdc(Long realCivilId) throws DataBaseException,
                                                                                SharedApplicationException {
     try {
            return SESSION().getByRealCivilIdAllMinistriesForAdc(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getSubWorkCeneterCodeName(String workCode,Long realCivilId , Long minCode)throws DataBaseException,SharedApplicationException{
        try {
               return SESSION().getSubWorkCeneterCodeName(RI(), workCode,realCivilId , minCode);
           } catch (SharedApplicationException e) {
               throw e;
           } catch (Exception e) {
               throw getWrappedException(e);
           }
    }

    public EmpStatusForSalDTO getEmpStatusAndHireOrEndServiceDateForSal( long realCivilId)throws DataBaseException,SharedApplicationException{
        try {
               return SESSION().getEmpStatusAndHireOrEndServiceDateForSal(RI(),realCivilId);
           } catch (SharedApplicationException e) {
               throw e;
           } catch (Exception e) {
               throw getWrappedException(e);
           }
    }

    public IPagingResponseDTO simpleSearchBasicWithPagingForLeaders(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                               SharedApplicationException {


    try {
        return SESSION().simpleSearchBasicWithPagingForLeaders(RI(), basicDTO, requestDTO) ;       }
    catch (SharedApplicationException e) {
           throw e;
       } catch (Exception e) {
           throw getWrappedException(e);
       }
    }
    public IEmployeesDTO getEmpCodeNameByRealCivilIdForLeaders(EmpEmployeeSearchDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
    try {
        return SESSION().getEmpCodeNameByRealCivilIdForLeaders(RI(), basicDTO) ;

        }
    catch (SharedApplicationException e) {
           throw e;
       } catch (Exception e) {
           throw getWrappedException(e);
       }
    }

    public IEmployeesDTO getByCivilIdForPRM(Long civilId) throws DataBaseException, SharedApplicationException{
        try {
            return SESSION().getByCivilIdForPRM(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
