package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.base.security.IMinistryInfo;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.emp.business.dto.EmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.EmpSearchRecodDesc;
import com.beshara.csc.hr.emp.business.dto.EmpStatusForSalDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpBonusData;
import com.beshara.csc.hr.emp.business.dto.IEmpFinicialData;
import com.beshara.csc.hr.emp.business.dto.IEmployeeDTOService;
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
import javax.ejb.Remote;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Remote
public interface EmployeesSession extends BasicSession {

    public IEmployeesDTO getById(IRequestInfoDTO ri, IEntityKey id1) throws RemoteException, DataBaseException,
                                                                            SharedApplicationException;

    List<IBasicDTO> search(IRequestInfoDTO ri, Long hirtypeCode) throws RemoteException, DataBaseException,
                                                                        SharedApplicationException;

    List<IBasicDTO> searchByMainHireType(IRequestInfoDTO ri, Long hirtypeCode) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;

    List<IBasicDTO> getAllEmployeesWithActiveHireTypes(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;


    /**
     * Get all employee copmleted their documents , procedures and waiting for hire decision * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */

    List<IBasicDTO> getAllEmployeeWaitingForHireDecision(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;


    /**
     * Hany Omar 16/2/2014
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws FinderException
     */

    List<IBasicDTO> filterByHireType(IRequestInfoDTO ri, Long hirtypeCode) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;

    /**
     * Hany Omar 18/2/2014
     * @param hireTypeList
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> filterByAllHireTypes(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;


    public List<IBasicDTO> filterByHireTypeForHireExecute(IRequestInfoDTO ri, Long hirtypeCode) throws RemoteException,
                                                                                                       DataBaseException,
                                                                                                       SharedApplicationException;

    public List<IBasicDTO> filterByAllHireTypesForHireExecute(IRequestInfoDTO ri) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;

    /**
     * This method to send procedure for list of employee and check if the procedure valid to send or not * @param employeeDTOList
     * @return list of resultDTO
     */
    List<IResultDTO> sendProcedure(IRequestInfoDTO ri, List<IBasicDTO> employeeDTOList) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    /**
     * Filter employee waiting for hire decision depend on hire type * @param empEmployeeSearchDTO
     * @return list of employee
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO ri,
                                                            IBasicDTO empEmployeeSearchDTO) throws RemoteException,
                                                                                                   DataBaseException,
                                                                                                   SharedApplicationException;

    /**
     * Filter employee waiting for hire decision depend on hire type 22/2/2009 * @param hireTypesEntityKey
     * @return list of employee
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO ri,
                                                            IEntityKey hireTypesEntityKey) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException;

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> simpleSearch(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> simpleSearchForAOE(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;


    /**
     * search hired employees and return list of employees whose civilIDs are not in the * @param basicDTO the IEmployeesDTO search criteria DTO
     * @param salEmpSalaryElementsDTOList the list of employees to be not included in the search result set
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchFilterEmployee(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                         List<IBasicDTO> salEmpSalaryElementsDTOList) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    /**
     * To add new Employee * @param employeesDTO1
     * @param usingTransAction
     * @return IEmployeesDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    IBasicDTO add(IRequestInfoDTO ri, IBasicDTO employeesDTO1, boolean usingTransAction) throws RemoteException,
                                                                                                SharedApplicationException,
                                                                                                DataBaseException;

    /**
     * Add Join To Decision * @param employeesList
     * @param decisionDTO
     * @return ResultDTO
     * @throws RemoteException
     */
    List<ResultDTO> addJoinToDecision(IRequestInfoDTO ri, List<IBasicDTO> employeesList,
                                      IBasicDTO decisionDTO) throws RemoteException, DataBaseException,
                                                                    SharedApplicationException;

    /**
     * Change employee budget type * @param changeBudgetTypeDTO1
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<ResultDTO> changeBudgetType(IRequestInfoDTO ri, IBasicDTO changeBudgetTypeDTO1) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    /**
     * get employee info with his salary element info ( valid ) , for specific type * @param civilId
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author Amir Nasr
     */
    IBasicDTO getEmployeeAndPayrollByElmType(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;

    /**
     * used in to filter employees by WorkCenter * @param workCenterCode
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllEmployeesByWorkCenter(IRequestInfoDTO ri, String workCenterCode) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException;

    /**
     * find if an employee exists in a certain ministry * @param civilId
     * @param minCode
     * @return Boolean
     * @throws RemoteException
     */
    Boolean isEmployeeInMinistry(IRequestInfoDTO ri, Long civilId, Long minCode) throws RemoteException,
                                                                                        DataBaseException,
                                                                                        SharedApplicationException;

    /**
     * Add Decision to employee to hire * @param employeesList
     * @param basicDecisionDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    Boolean addDecision(IRequestInfoDTO ri, List<IBasicDTO> employeesList,
                        IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException,
                                                           RemoteException;

    /**
     * Get all employee after hire decision to start work * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllToStartWork(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                 SharedApplicationException;

    /**
     * Search employee after hire decision to start work * @param civilId
     * @param name
     * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployeeStartWork(IRequestInfoDTO ri, Long civilId, String name) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException;

    /**
     * execute start work * @param employeesDTO1
     * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean executeStartWork(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;

    /**
     * Get all employee to complete employee's date * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllToCompleteData(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                    SharedApplicationException;

    /**
     * Search employee to complete employee's date * @param employeeSearchDTO
     * @return list of employee
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployeeToCompleteData(IRequestInfoDTO ri,
                                                 IBasicDTO employeeSearchDTO) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    /**
     * Check if citizen is legel to employment with specific hire type * @param civilID
     * @param hireType
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean checkLegelHireType(IRequestInfoDTO ri, Long civilID, Long hireType) throws RemoteException,
                                                                                       DataBaseException,
                                                                                       SharedApplicationException;

    /**
     * Get all employees in a given ministry * @param minCode
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getEmployeesByMinistry(IRequestInfoDTO ri, Long minCode) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;

    /**
     * check if employee is hired * @param employeesEntityKey
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean isEmployeeHired(IRequestInfoDTO ri, IEntityKey employeesEntityKey) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;

    /**
     * Update Employee Data * @param employeesDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    IBasicDTO updateEmp(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException;

    /** Get all employee match search criteria and sorted by full name
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployee(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;

    /**
     * Validate employee civil id if it exists or nt then validate his hire type * @param civilID
     * @param hireType
     * @return TRUE : Employee exist and his hire type is legal
     * FALSE : Employee doesnt exist * throws NotMatchedOnHireTypeException : not a legal hire type * @throws RemoteException
     * @throws SharedApplicationException
     */
    Boolean validateEmployeeHireType(IRequestInfoDTO ri, Long civilID, Long hireType) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    /**
     * Get all employees match search criteria and sorted by full name for sal cal * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployeeForSalCalc(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;

    /**
     * get employee info for specific type * @param civilId
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author MLotfy
     */
    IBasicDTO getEmployeeInfoByElmType(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;

    List<IBasicDTO> searchEmployeeForSalPayment(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    IBasicDTO getEmployeeInfoHasSocialRaise(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public IEmployeesDTO getCurrentEmpFinancialData(IRequestInfoDTO ri, IEntityKey id1) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    /**
     * check if employee valid to add request to end his/her service * @param civilId
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isEmployeeValidToEndService(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                        DataBaseException,
                                                                                        SharedApplicationException;

    public List<IBasicDTO> getEmployeByCivilId(IRequestInfoDTO ri, Long civilID) throws RemoteException,
                                                                                        DataBaseException,
                                                                                        SharedApplicationException;

    /**
     * return EmployeeDTO civilId and KwtCitizensResidentsDTO fullName with auditing attributes
     * @param civilID
     * @return
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author Ashraf Gaber 19/01/2011
     */
    public IBasicDTO getEmployeeDTOCodeName(IRequestInfoDTO ri, Long civilID) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public List<IBasicDTO> searchEmployeesForEvaluator(IRequestInfoDTO ri, Long evaluatorCivilId,
                                                       IBasicDTO employeeSearchDTO) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public List<IBasicDTO> searchEmployeesForEvaluatorByCivilId(IRequestInfoDTO ri, Long evaluatorCivilId,
                                                                IBasicDTO employeeSearchDTO) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;

    public List<IBasicDTO> searchEmployeesForEvaluatorByName(IRequestInfoDTO ri, Long evaluatorCivilId,
                                                             IBasicDTO employeeSearchDTO) throws RemoteException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException;

    public Date calculateNextRaiseDate(IRequestInfoDTO ri, Date hireDate) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;

    //*************************************

    //    List<IBasicDTO> search(IRequestInfoDTO requestInfo, Long hirtypeCode) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //
    //
    //    /**
    //     * This method to send procedure for list of employee and check if the procedure valid to send or not * @param employeeDTOList
    //     * @return list of resultDTO
    //     */
    //    List<IResultDTO> sendProcedure(IRequestInfoDTO requestInfo,
    //                                   List<IBasicDTO> employeeDTOList) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * Filter employee waiting for hire decision depend on hire type * @param empEmployeeSearchDTO
    //     * @return list of employee
    //     * @throws SharedApplicationException
    //     * @throws RemoteException
    //     */
    //    List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO requestInfo,
    //                                                            IBasicDTO empEmployeeSearchDTO) throws RemoteException,DataBaseException,SharedApplicationException;

    /**
     * Filter employee waiting for hire decision depend on hire type 22/2/2009 * @param hireTypesEntityKey
     * @return list of employee
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    //    List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO ri,
    //                                                            IEntityKey hireTypesEntityKey) throws RemoteException,DataBaseException,SharedApplicationException;

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //  List<IBasicDTO> simpleSearch(IRequestInfoDTO requestInfo, IBasicDTO basicDTO) throws RemoteException,DataBaseException,SharedApplicationException;

    public IPagingResponseDTO simpleSearchWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                     IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                          DataBaseException,
                                                                                          SharedApplicationException;

    public IPagingResponseDTO simpleSearchEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    public List<IBasicDTO> simpleSearchBasic(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;

    public IPagingResponseDTO simpleSearchBasicWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    public IPagingResponseDTO simpleSearchBasicWithPagingAtt(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    /**
     * search hired employees and return list of employees whose civilIDs are not in the * @param basicDTO the IEmployeesDTO search criteria DTO
     * @param salEmpSalaryElementsDTOList the list of employees to be not included in the search result set
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    List<IBasicDTO> searchFilterEmployee(IRequestInfoDTO requestInfo, IBasicDTO basicDTO,
    //                                         List<IBasicDTO> salEmpSalaryElementsDTOList) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * To add new Employee * @param employeesDTO1
    //     * @param usingTransAction
    //     * @return IEmployeesDTO
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    IBasicDTO add(IRequestInfoDTO requestInfo, IBasicDTO employeesDTO1,
    //                  boolean usingTransAction) throws RemoteException, SharedApplicationException, DataBaseException;

    /**
     * Add Join To Decision * @param employeesList
     * @param decisionDTO
     * @return ResultDTO
     * @throws RemoteException
     */
    //    List<ResultDTO> addJoinToDecision(IRequestInfoDTO requestInfo, List<IBasicDTO> employeesList,
    //                                      IBasicDTO decisionDTO) throws RemoteException, DataBaseException,
    //                                                                    SharedApplicationException;

    /**
     * Change employee budget type * @param changeBudgetTypeDTO1
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    List<ResultDTO> changeBudgetType(IRequestInfoDTO requestInfo,
    //                                     IBasicDTO changeBudgetTypeDTO1) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * get employee info with his salary element info ( valid ) , for specific type * @param civilId
    //     * @return IBasicDTO
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     * @author Amir Nasr
    //     */
    //    IBasicDTO getEmployeeAndPayrollByElmType(IRequestInfoDTO requestInfo, Long civilId) throws RemoteException,DataBaseException,SharedApplicationException;

    /**
     * used in to filter employees by WorkCenter * @param workCenterCode
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    List<IBasicDTO> getAllEmployeesByWorkCenter(IRequestInfoDTO requestInfo,
    //                                                String workCenterCode) throws RemoteException,DataBaseException,SharedApplicationException;
    //    /**
    //     * find if an employee exists in a certain ministry * @param civilId
    //     * @param minCode
    //     * @return Boolean
    //     * @throws RemoteException
    //     */
    //    Boolean isEmployeeInMinistry(IRequestInfoDTO requestInfo, Long civilId, Long minCode) throws RemoteException,DataBaseException,SharedApplicationException;

    /**
     * Add Decision to employee to hire * @param employeesList
     * @param basicDecisionDTO
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    //    Boolean addDecision(IRequestInfoDTO requestInfo, List<IBasicDTO> employeesList,
    //                        IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException,
    //                                                           RemoteException;
    //
    //    /**
    //     * check if employee exists * @param civilId
    //     * @return Boolean
    //     * @throws RemoteException
    //     */
    //    Boolean isEmployeeExist(IRequestInfoDTO requestInfo, Long civilId) throws RemoteException,DataBaseException,SharedApplicationException;

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     * @throws RemoteException
     */
    //    Boolean isMinistryFileNoExist(IRequestInfoDTO requestInfo, String ministryFileNo) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * Get all employee after hire decision to start work * @return list of employee
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    List<IBasicDTO> getAllToStartWork(IRequestInfoDTO requestInfo) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * Search employee after hire decision to start work * @param civilId
    //     * @param name
    //     * @return list of employee
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    List<IBasicDTO> searchEmployeeStartWork(IRequestInfoDTO requestInfo, Long civilId,
    //                                            String name) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * execute start work * @param employeesDTO1
    //     * @return list of employee
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    Boolean executeStartWork(IRequestInfoDTO requestInfo, IBasicDTO employeesDTO1) throws RemoteException,DataBaseException,SharedApplicationException;
    //    /**
    //     * Get all employee to complete employee's date * @return list of employee
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    List<IBasicDTO> getAllToCompleteData(IRequestInfoDTO requestInfo) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * Search employee to complete employee's date * @param employeeSearchDTO
    //     * @return list of employee
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    List<IBasicDTO> searchEmployeeToCompleteData(IRequestInfoDTO requestInfo,
    //                                                 IBasicDTO employeeSearchDTO) throws RemoteException,DataBaseException,SharedApplicationException;
    //
    //    /**
    //     * Check if citizen is legel to employment with specific hire type * @param civilID
    //     * @param hireType
    //     * @return Boolean
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    Boolean checkLegelHireType(IRequestInfoDTO requestInfo, Long civilID, Long hireType) throws RemoteException,
    //                                                                                                SharedApplicationException,
    //                                                                                                DataBaseException;
    //
    //    /**
    //     * Get all employees in a given ministry * @param minCode
    //     * @return list
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    List<IBasicDTO> getEmployeesByMinistry(IRequestInfoDTO requestInfo, Long minCode) throws RemoteException,
    //                                                                                             SharedApplicationException;

    public List<IBasicDTO> getAllEmployeeByStageAndMinistry(IRequestInfoDTO ri, Long minCode,
                                                            String selectedStage) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;

    /**
     * check if employee is hired * @param employeesEntityKey
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    Boolean isEmployeeHired(IRequestInfoDTO requestInfo, IEntityKey employeesEntityKey) throws RemoteException,
    //                                                                                               SharedApplicationException;


    // empUpdateCancelCandidate

    IBasicDTO updateEmpCancelCandidate(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws RemoteException,
                                                                                           SharedApplicationException,
                                                                                           DataBaseException;

    /** Get all employee match search criteria and sorted by full name
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    List<IBasicDTO> searchEmployee(IRequestInfoDTO requestInfo, IBasicDTO basicDTO) throws RemoteException,
    //                                                                                           SharedApplicationException;

    /**
     * Validate employee civil id if it exists or nt then validate his hire type * @param civilID
     * @param hireType
     * @return TRUE : Employee exist and his hire type is legal
     * FALSE : Employee doesnt exist * throws NotMatchedOnHireTypeException : not a legal hire type * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    Boolean validateEmployeeHireType(IRequestInfoDTO requestInfo, Long civilID, Long hireType) throws RemoteException,
    //                                                                                                      SharedApplicationException;
    //
    //    /**
    //     * Get all employees match search criteria and sorted by full name for sal cal * @param basicDTO
    //     * @return List
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     */
    //    List<IBasicDTO> searchEmployeeForSalCalc(IRequestInfoDTO requestInfo, IBasicDTO basicDTO) throws RemoteException,
    //                                                                                                     SharedApplicationException;

    /**
     * get employee info for specific type * @param civilId
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     * @author MLotfy
     */
    //    IBasicDTO getEmployeeInfoByElmType(IRequestInfoDTO requestInfo, Long civilId) throws RemoteException,
    //                                                                                         SharedApplicationException;
    //
    //    List<IBasicDTO> searchEmployeeForSalPayment(IRequestInfoDTO requestInfo,
    //                                                IBasicDTO basicDTO) throws RemoteException, SharedApplicationException;
    //
    //    IBasicDTO getEmployeeInfoHasSocialRaise(IRequestInfoDTO requestInfo, Long civilId) throws RemoteException,
    //                                                                                              SharedApplicationException;
    //
    //    public IEmployeesDTO getCurrentEmpFinancialData(IRequestInfoDTO requestInfo,
    //                                                    IEntityKey id1) throws RemoteException, SharedApplicationException;
    //
    //    /**
    //     * check if employee valid to add request to end his/her service * @param civilId
    //     * @return Boolean
    //     * @throws RemoteException
    //     */
    //    public Boolean isEmployeeValidToEndService(IRequestInfoDTO requestInfo, Long civilId) throws RemoteException;
    //
    //    public Boolean updateEmployeeDataForMov(IRequestInfoDTO requestInfo, Long civilId, Long employeeHireStatus,
    //                                            Date endJobDate) throws RemoteException, SharedApplicationException;
    //
    //    public IBasicDTO addEmployeeForMovOnly(IRequestInfoDTO requestInfo,
    //                                           IBasicDTO employeesDTO1) throws RemoteException, SharedApplicationException,
    //                                                                           DataBaseException;
    //
    //    public List<IBasicDTO> getEmployeByCivilId(IRequestInfoDTO requestInfo, Long civilID) throws RemoteException,
    //                                                                                                 FinderException;
    //
    //    public List<IBasicDTO> searchEmployeesForEvaluator(IRequestInfoDTO requestInfo, Long evaluatorCivilId,
    //                                                       IBasicDTO employeeSearchDTO) throws RemoteException,
    //                                                                                           SharedApplicationException;
    //
    //    public List<IBasicDTO> searchEmployeesForEvaluatorByCivilId(IRequestInfoDTO requestInfo, Long evaluatorCivilId,
    //                                                                IBasicDTO employeeSearchDTO) throws RemoteException,
    //                                                                                                    SharedApplicationException;
    //
    //    public List<IBasicDTO> searchEmployeesForEvaluatorByName(IRequestInfoDTO requestInfo, Long evaluatorCivilId,
    //                                                             IBasicDTO employeeSearchDTO) throws RemoteException,
    //                                                                                                 SharedApplicationException;
    //
    //    public Date calculateNextRaiseDate(IRequestInfoDTO requestInfo, Date hireDate) throws RemoteException;

    public Long[] getEmpServicePeriod(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                               SharedApplicationException,
                                                                               DataBaseException;

    //    public Long[] getEmpServicePeriod(IRequestInfoDTO requestInfo, Long civilId) throws RemoteException;

    IPagingResponseDTO getByHireStageWithPaging(IRequestInfoDTO ri, IBasicDTO _searchDTO) throws RemoteException,
                                                                                                 SharedApplicationException,
                                                                                                 DataBaseException;

    //    IPagingResponseDTO getByHireStageWithPaging(IRequestInfoDTO requestInfo,
    //                                                IBasicDTO _searchDTO) throws RemoteException, SharedApplicationException,DataBaseException;

    IPagingResponseDTO searchWithPaging(IRequestInfoDTO ri, IBasicDTO _searchDTO) throws RemoteException,
                                                                                         SharedApplicationException,
                                                                                         DataBaseException;

    //    IPagingResponseDTO searchWithPaging(IRequestInfoDTO requestInfo, IBasicDTO _searchDTO) throws RemoteException, SharedApplicationException,DataBaseException;

    public void refresh(IRequestInfoDTO ri) throws RemoteException, SharedApplicationException, DataBaseException;

    public List<IBasicDTO> searchByCivilAndName(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                               SharedApplicationException,
                                                                                               DataBaseException;

    public List<IBasicDTO> simpleSearchFullData(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                               SharedApplicationException,
                                                                                               DataBaseException;

    public List<IBasicDTO> simpleSearchAllMinistriesFullData(IRequestInfoDTO ri,
                                                             IBasicDTO basicDTO) throws RemoteException,
                                                                                        SharedApplicationException,
                                                                                        DataBaseException;

    public IEmployeesDTO getByRealCivilId(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws RemoteException,
                                                                                                     SharedApplicationException,
                                                                                                     DataBaseException;

    IEmployeesDTO getByRealCivilIdForEsrv(IRequestInfoDTO iRequestInfoDTO, Long realCivilId,
                                          Long minCode) throws RemoteException, SharedApplicationException,
                                                             DataBaseException;

    IEmployeesDTO getCivilByRealCivil(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws RemoteException,
                                                                                                     SharedApplicationException,
                                                                                                     DataBaseException;

    IEmployeesDTO getSimpleEmployeeByRealCivilId(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws RemoteException,
                                                                                                     SharedApplicationException,
                                                                                                     DataBaseException;

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMov(IRequestInfoDTO ri, Long realCivilId,
                                                             Long minCode) throws RemoteException,
                                                                                  SharedApplicationException,
                                                                                  DataBaseException;


    public IEmployeesDTO getByRealCivilIdForMovWithoutFilteration(IRequestInfoDTO ri, Long realCivilId,
                                                                  Long minCode) throws RemoteException,
                                                                                       SharedApplicationException,
                                                                                       DataBaseException;

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMovSimple(IRequestInfoDTO ri, Long realCivilId,
                                                                   Long minCode) throws RemoteException,
                                                                                        SharedApplicationException,
                                                                                        DataBaseException;

    public IEmployeesDTO getEmpForMov(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws RemoteException,
                                                                                                 SharedApplicationException,
                                                                                                 DataBaseException;

    public IEmployeesDTO getByRealCivilIdInallMin(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                               SharedApplicationException,
                                                                                               DataBaseException;

    public IEmployeesDTO getByRealCivilIdInallMinForReg(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                                     SharedApplicationException,
                                                                                                     DataBaseException;

    public IEmployeesDTO getByRealCivilIdForReg(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                             SharedApplicationException,
                                                                                             DataBaseException;


    public IEmployeesDTO getByRealCivilIdAndHireStatus(IRequestInfoDTO ri, Long realCivilId,
                                                       Long minCode) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException;

    public IEmployeesDTO getByRealCivilIdAllMinistries(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                                    SharedApplicationException,
                                                                                                    DataBaseException;

    public Boolean isEmployeeHiredForVac(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;

    public Boolean isEmployeeHired(IRequestInfoDTO ri, Long realCivilId, Long minCode) throws RemoteException,
                                                                                              SharedApplicationException,
                                                                                              DataBaseException;

    /**
     * return true if employee is hired
     * @param civilID
     * @return
     * @throws RemoteException
     * @author Dina Abd El-Rahim 06/03/2014
     **/
    public Boolean isEmployeeHiredForVac(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                  SharedApplicationException,
                                                                                  DataBaseException;

    public Long isJobHasEmployeees(IRequestInfoDTO ri, Long jobCode) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException;

    public boolean checkEmployeeRequiredProceduresStatus(IRequestInfoDTO ri, Long serial) throws RemoteException,
                                                                                                 SharedApplicationException,
                                                                                                 DataBaseException;

    /**
     * @param ri
     * @param serial
     * @return
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public boolean checkEmployeeRequiredDocumentsStatus(IRequestInfoDTO ri, Long serial) throws RemoteException,
                                                                                                SharedApplicationException,
                                                                                                DataBaseException;


    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                                SharedApplicationException,
                                                                                                DataBaseException;

    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,IBgtProgramsDTO level,
                                                           IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                                SharedApplicationException,
                                                                                                DataBaseException;

    public List<IBasicDTO> simpleSearchVacEmp(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                             SharedApplicationException,
                                                                                             DataBaseException;

    public List<IBasicDTO> simpleSearchVacEmpAllData(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                                    SharedApplicationException,
                                                                                                    DataBaseException;

    public List simpleSearchVacEmpEnhanced(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                          DataBaseException,
                                                                                          SharedApplicationException;

    public List<IBasicDTO> searchVacEmp(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                       SharedApplicationException,
                                                                                       DataBaseException;

    public List<IBasicDTO> getAllEmployeesByPremittedMinistries(IRequestInfoDTO ri,
                                                                IBasicDTO basicDTO) throws RemoteException,
                                                                                           SharedApplicationException,
                                                                                           DataBaseException;

    public List<IBasicDTO> filterAvailableEntitiesUsingPaging(IRequestInfoDTO ri, IBasicDTO employeeSearchDTO1,
                                                              IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                                   SharedApplicationException,
                                                                                                   DataBaseException;

    public Long filterAvailableEntitiesUsingPagingCount(IRequestInfoDTO ri,
                                                        IBasicDTO employeeSearchDTO1) throws RemoteException,
                                                                                             SharedApplicationException,
                                                                                             DataBaseException;

    public Long CheckIfEmployeeOrCandidate(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                    SharedApplicationException,
                                                                                    DataBaseException;

    List<IBasicDTO> searchMerVariableEmployee(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    Long countEmployeesByJobAndMin(IRequestInfoDTO ri, String jobCode, Long minCode) throws RemoteException,
                                                                                            SharedApplicationException,
                                                                                            DataBaseException;

    public Long countHiredEmployeesByMinWrkCode(IRequestInfoDTO ri, Long minCode,
                                                String wrkCode) throws RemoteException, SharedApplicationException,
                                                                       DataBaseException;

    public String getStatusForHire(IRequestInfoDTO ri) throws RemoteException;

    public Boolean updateEmployeeDataForMov(IRequestInfoDTO requestInfo, Long civilId, Long employeeHireStatus,
                                            Date endJobDate) throws RemoteException, DataBaseException,
                                                                    SharedApplicationException;

    public IBasicDTO addEmployeeForMovOnly(IRequestInfoDTO requestInfo,
                                           IBasicDTO employeesDTO1) throws RemoteException, SharedApplicationException,
                                                                           DataBaseException;

    Boolean isEmployeeExist(IRequestInfoDTO requestInfo, Long civilId) throws RemoteException, DataBaseException,
                                                                              SharedApplicationException;

    Boolean isMinistryFileNoExist(IRequestInfoDTO requestInfo, String ministryFileNo) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    public IBasicDTO updateEmpCMT(IRequestInfoDTO requestInfo, IBasicDTO employeesDTO1) throws RemoteException,
                                                                                               SharedApplicationException,
                                                                                               DataBaseException;

    public Boolean executeStartWorkCMT(IRequestInfoDTO requestInfo, IBasicDTO employeesDTO1) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;

    public List<IMinistriesDTO> getMinistriesListByEvalCivilId(IRequestInfoDTO ri, Long evalCivilId,
                                                               List<IMinistryInfo> minList) throws RemoteException,
                                                                                                   DataBaseException,
                                                                                                   SharedApplicationException;

    public List<IMinistriesDTO> getMinistriesListByEvalCivilIdNew(IRequestInfoDTO ri, Long evalCivilId,
                                                               List<IMinistriesDTO> minList) throws RemoteException,
                                                                                                   DataBaseException,
                                                                                                   SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdAllMinByRecordDesc(IRequestInfoDTO ri,
                                                            Long realCivilId) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public Boolean isEmployeeHiredWithRecordDesc(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                              DataBaseException,
                                                                                              SharedApplicationException;

    public List<IBasicDTO> getEmpByEmpSearch(IRequestInfoDTO ri, EmpSearchRecodDesc searchDTO) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;

    public Boolean updateEmployeeForPRM(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;

    public Boolean updateEmployeeStaus(IRequestInfoDTO ri, IEntityKey empKey,
                                       IEntityKey employeeHireStatus) throws RemoteException, DataBaseException,
                                                                             SharedApplicationException;

    public IEmployeesDTO getCurrActiveEmpByRealCivilId(IRequestInfoDTO ri, Long realCivilId,
                                                       Long minCode) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException;

    public Boolean checkFileNoWithMinAndEmployeeForMov(IRequestInfoDTO ri, Long minCode, String minFileNo,
                                                       Long civilId) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException;

    public int updateActiveFlagByRealCivilId(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                          SharedApplicationException,
                                                                                          DataBaseException;

    public int updateWorkCodeByRealCivilId(IRequestInfoDTO ri, Long realCivilId,
                                           String workCode) throws RemoteException, SharedApplicationException,
                                                                   DataBaseException;


    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilId(IRequestInfoDTO ri,
                                                                   Long realCivilId) throws RemoteException,
                                                                                            SharedApplicationException,
                                                                                            DataBaseException;
    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(IRequestInfoDTO ri,
                                                                   Long realCivilId) throws RemoteException,
                                                                                            SharedApplicationException,
                                                                                            DataBaseException;

    public Boolean checkHireDateContradiction(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                             SharedApplicationException,
                                                                                             DataBaseException;


    public IBasicDTO addEmployeeForExecuteMovOnly(IRequestInfoDTO requestInfo,
                                                  IBasicDTO employeesDTO1) throws RemoteException,
                                                                                  SharedApplicationException,
                                                                                  DataBaseException;


    public IPagingResponseDTO simpleSearchMovEmpWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                                SharedApplicationException,
                                                                                                DataBaseException;

    public IPagingResponseDTO simpleSearchMovEmpWithPagingAllChecked(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                                     IPagingRequestDTO requestDTO,
                                                                     List<String> unCheckedList) throws RemoteException,
                                                                                                        SharedApplicationException,
                                                                                                        DataBaseException;

    public Boolean updateEmployeeBeforeExecuteEmployment(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;

    public int updateActiveFlagForDelegation(IRequestInfoDTO ri, Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode) throws RemoteException, SharedApplicationException,
                                                                  DataBaseException;

    public int updateActiveFlagForDelegationWithRCivil(IRequestInfoDTO ri, Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode ,Long realCivilId) throws RemoteException, SharedApplicationException,
                                                                  DataBaseException;

    public Boolean isEmpIndebt(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException;

    public Boolean isConditionFromGrsValid(IRequestInfoDTO ri, Long realCivilId,
                                           Long degreeCode) throws RemoteException, DataBaseException,
                                                                   SharedApplicationException;

    List<IEmployeesDTO> getAllHiredEmployeesByJobLevelsAndType(IRequestInfoDTO ri,
                                                               IJobSearchCriteriaDTO jobSearchCriteriaDTO,
                                                               Map<String, Long> conditionMap) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;

    public IPagingResponseDTO getAllEmployeesByForLoginMinistries(IRequestInfoDTO ri,
                                                                  IBasicDTO basicDTO) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    public IEmployeesDTO getEmpCodeNameByRealCivilId(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;

    public List<IBasicDTO> searchByCivilAndNameUsingMinCode(IRequestInfoDTO ri,
                                                            IBasicDTO basicDTO) throws RemoteException,
                                                                                       DataBaseException,
                                                                                       SharedApplicationException;

    public List<IBasicDTO> searchByCivilAndNameUsingMinCodeForCER(IRequestInfoDTO ri,
                                                                         IBasicDTO basicDTO) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 21-3-2016
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
                                         Long realCivilID) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;

    public String applyWrkcenterTreeFilter(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                      SharedApplicationException;

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<String,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all workcenters tree for 02133
     */
    public HashMap<String, String> getworkCenterTree(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<String,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all Employees in workcenters tree for 02133
     */
    public HashMap<Long, String> getEmployeesworkCenterTree(IRequestInfoDTO ri) throws RemoteException,
                                                                                       DataBaseException,
                                                                                       SharedApplicationException;

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<Long,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all groups for workcenters tree for 02133
     */
    public HashMap<Long, String> getGroupsworkCenterTree(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;

    public List<String> filterDataByWrkCenterList(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                             SharedApplicationException;

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

    public String getWrkcenterDataFilter(IRequestInfoDTO ri, boolean isNative) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;

    public IEmployeesDTO getEmpMainInfoBySecurity(IRequestInfoDTO ri, String user_code,
                                                  String user_name) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;

    public String getDataByWrkCenter(IRequestInfoDTO ri, String colName) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;

    List<IEmployeesDTO> getByRealCivilForFTQ(IRequestInfoDTO ri, Long realCivilId, String name) throws RemoteException,
                                                                                                       DataBaseException,
                                                                                                       SharedApplicationException;

    public List<IEmployeesDTO> getEmployeesByRealCivilId_eos(IRequestInfoDTO ri,
                                                             Long realCivilId) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;

    public List<IBasicDTO> searchByCivilIdAndName(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException;

    public String getUserName(IRequestInfoDTO ri, Long userCode) throws RemoteException, DataBaseException,
                                                                        SharedApplicationException;

    public Long getCandidateCode(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                          SharedApplicationException;

    public IPagingResponseDTO getAllEmployeesInMinWithPaging(IRequestInfoDTO ri,
                                                             IBasicDTO _searchDTO) throws RemoteException,
                                                                                          DataBaseException,
                                                                                          SharedApplicationException;

    public Boolean updateJobCodeForADC(IRequestInfoDTO ri, List<IBasicDTO> basicDTOList,
                                       String jobCode) throws RemoteException, DataBaseException,
                                                              SharedApplicationException;

    List<IBasicDTO> getAllEmployeeswithContract(IRequestInfoDTO ri, Long minCode) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;


    public List<IEmployeesDTO> getEmpByRealCivilId(IRequestInfoDTO ri,Long realCivilId) throws DataBaseException,
                                                                             SharedApplicationException ,RemoteException;

    public IPagingResponseDTO searchEmpWithoutValidations(IRequestInfoDTO ri, IBasicDTO basicDTO,IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;
    public IEmployeesDTO getEmployeeSimpleDTOForESRV(IRequestInfoDTO ri, EmployeesDTO emp) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException;

    public String getUserNameByRole(IRequestInfoDTO ri, Long roleId, Long realCivil,
                                    Long minCode) throws RemoteException, DataBaseException,
                                                         SharedApplicationException;

    public String getUserNamesHighgerRole(IRequestInfoDTO ri, Long roleId, String userNames,
                                          Long minCode) throws RemoteException, DataBaseException,
                                                               SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdAndHireStatusForExternalMov(IRequestInfoDTO ri, Long realCivilId,
                                                                     Long minCode) throws RemoteException,
                                                                                          SharedApplicationException,
                                                                                          DataBaseException;

    public IPagingResponseDTO searchEmpWithHireStatus(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                      IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public IPagingResponseDTO simpleSearchBasicWithPagingADC(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdForADCWithoutFilteration(IRequestInfoDTO ri, Long realCivilId,
                                                                  Long minCode) throws RemoteException,
                                                                                       SharedApplicationException,
                                                                                       DataBaseException;

    public IEmployeesDTO getByRealCivilIdForADCEmployees(IRequestInfoDTO ri, Long realCivilId,
                                                                  Long minCode) throws RemoteException,
                                                                                       SharedApplicationException,
                                                                                       DataBaseException;

    public List<EmployeesDTO> getAllUserNameByRole(IRequestInfoDTO ri,Long roleId, Long realCivil, Long minCode , List<String> userCodes) throws RemoteException,
                                                                                                     DataBaseException,
                                                                                                     SharedApplicationException;

    public Long getMinCodeByCivil(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException, SharedApplicationException ;

    public List<IBasicDTO> searchByCivilAndNameAllMinistries(IRequestInfoDTO ri,IBasicDTO basicDTO,Boolean mokfaaShamla) throws RemoteException,DataBaseException,SharedApplicationException;

    public List<ISimpleEmployeesDTO> getByRealCivilForCER(IRequestInfoDTO ri, Long realCivilId,
                                                          String name) throws RemoteException, DataBaseException,
                                                                              SharedApplicationException;


    public IPagingResponseDTO searchEmpWithPagingForFTQ(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws RemoteException,DataBaseException,
                                                                                               SharedApplicationException ;

    public IEmployeesDTO getByRealCivilIdBasicData(IRequestInfoDTO ri, Long realCivilId,
                                                   Long minCode) throws RemoteException, DataBaseException, SharedApplicationException ;
    public IEmployeesDTO getByRealCivilIdBasicDataAndMinistryName(IRequestInfoDTO ri, Long realCivilId,
                                                   Long minCode) throws RemoteException, DataBaseException, SharedApplicationException ;


    public Long checkFileNoWithMinAndEmployeeForADC(IRequestInfoDTO ri, Long minCode, String minFileNo,
                                                       Long civilId) throws RemoteException,DataBaseException,
                                                                            SharedApplicationException;

    public IPagingResponseDTO searchEmployeeWithPagingForAtt(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws RemoteException,DataBaseException,
                                                                                               SharedApplicationException ;

    public IBasicDTO getEmpCodeNameByRealCivilIdForAtt(IRequestInfoDTO ri,
                                                       IBasicDTO basicDTO) throws RemoteException,DataBaseException,
                                                                                  SharedApplicationException ;
    public String getCivilsRelatedToWorkCenter(IRequestInfoDTO ri, String workCenter, boolean isRelated,
                                               boolean isNative) throws RemoteException,DataBaseException, SharedApplicationException ;

    public Date getEmpFirstHireDate(IRequestInfoDTO ri, Long realCivilId) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;

    public IEmployeesDTO getEmployeesByRealCivilIdAndMinForBankWebService(IRequestInfoDTO ri, Long realCivilId,
                                                                          Long minCode,
                                                                          String hireStatusListCommaSeparated) throws RemoteException,
                                                                                                                      DataBaseException,
                                                                                                                      SharedApplicationException;
    public  List<IEmployeesDTO> getByRealCivilForFTQWithDF(IRequestInfoDTO ri,Long realCivilId, String name) throws RemoteException,
                                                                                                                      DataBaseException,
                                                                                                                      SharedApplicationException;

    public String getEmpCodeNameForContractEmp(IRequestInfoDTO ri,Long  civilID, Long minCode) throws RemoteException,DataBaseException,
                                                                                      SharedApplicationException;

    public boolean checkIfEmployeeExists(IRequestInfoDTO ri, Long realCivilId) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;

    public boolean updateNextRaiseDate(IRequestInfoDTO ri,Date nextRaiseDate , String civilId) throws  RemoteException,DataBaseException,
                                                                           SharedApplicationException ;

    public IEmployeesDTO searchByCivilIdEstana(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException,DataBaseException,
                                                                          SharedApplicationException;

    public IBasicDTO getEmployeeDTOCodeNameWithoutFilteration(IRequestInfoDTO ri, Long civilID) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public IBasicDTO getEmpCurrentJob(IRequestInfoDTO ri, Long realCivilID) throws RemoteException, DataBaseException,
                                                                   SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdAllMinistriesForAdc(IRequestInfoDTO ri,Long realCivilId) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;

    public IBasicDTO getSubWorkCeneterCodeName(IRequestInfoDTO ri, String workCode,Long realCivilId , Long minCode)throws RemoteException,DataBaseException,SharedApplicationException ;

    public EmpStatusForSalDTO getEmpStatusAndHireOrEndServiceDateForSal(IRequestInfoDTO ri,long realCivilId)throws RemoteException,DataBaseException,SharedApplicationException ;
    
    public IPagingResponseDTO simpleSearchBasicWithPagingForLeaders(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws RemoteException,DataBaseException,
                                                                                               SharedApplicationException;

    public IEmployeesDTO getEmpCodeNameByRealCivilIdForLeaders(IRequestInfoDTO ri,EmpEmployeeSearchDTO basicDTO) throws RemoteException,DataBaseException,
                                                                                SharedApplicationException;



    public IEmployeesDTO getByCivilIdForPRM(IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException, SharedApplicationException;
    /**
         *@author msayed
         * @since 14-5-2020
         * @get data for mobile webserivce
         * @param realCivilID
         * @return
         * @throws DataBaseException
         * @throws SharedApplicationException
         */
        public IEmployeeDTOService getAllEmployeeDataForProfileMob(IRequestInfoDTO ri,Long  realCivilID) throws RemoteException,DataBaseException, SharedApplicationException ;
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
    public IEmpFinicialData getMertisAndDeducts(IRequestInfoDTO ri,Long realCivilID,Long yearCode,Long monthCode)throws RemoteException,DataBaseException, SharedApplicationException ;
    
    /**
         *@author msayed
         * @since 04-07-2020
         * @get data for mobile webserivce
         * @param realCivilID
         * @return
         * @throws DataBaseException
         * @throws SharedApplicationException
         */
        public IEmployeeDTOService getAllEmployeeDataForProfileMobVW(IRequestInfoDTO ri,Long  realCivilID) throws RemoteException,DataBaseException, SharedApplicationException ;
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
    public IEmpFinicialData getMertisAndDeductsVW(IRequestInfoDTO ri,Long realCivilID,Long yearCode,Long monthCode)throws RemoteException,DataBaseException, SharedApplicationException ;
    
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
    public List<IEmpBonusData> getEmpBonusDataVW(IRequestInfoDTO ri,Long realCivilID)throws RemoteException,DataBaseException, SharedApplicationException ;
}
