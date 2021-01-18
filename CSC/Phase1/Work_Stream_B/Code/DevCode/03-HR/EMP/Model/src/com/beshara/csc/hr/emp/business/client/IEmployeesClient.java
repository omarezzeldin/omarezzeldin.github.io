package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.base.security.IMinistryInfo;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
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
 * This Interface Contains a set of Methods Which Should be Implemented * with Different Implementation ( SessionBean , Message Driven Bean , RMI Service etc ... ) * and Generated through The Client Factory Class . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Sherif El-Rabbat 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry
 * @version 1.0
 * @since 03/09/2007
 */
public interface IEmployeesClient extends IBasicClient {
    List<IBasicDTO> search(Long hirtypeCode) throws DataBaseException;

    List<IBasicDTO> searchByMainHireType(Long hirtypeCode) throws DataBaseException;

    List<IBasicDTO> getAllEmployeesWithActiveHireTypes() throws DataBaseException, SharedApplicationException;

    /** added by
     * Hany Omar 16/2/2014
     */
    List<IBasicDTO> filterByHireType(Long hirtypeCode) throws DataBaseException;

    /**
     * 18-2-2014
     * @param hireTypeList
     * @return
     * @throws DataBaseException
     * @Author Hany Omar
     */
    List<IBasicDTO> filterByAllHireTypes() throws DataBaseException;

    /**
     * filter employees by hire types for employee hire execution
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     * @author Dina
     */
    public List<IBasicDTO> filterByHireTypeForHireExecute(Long hirtypeCode) throws DataBaseException;

    public List<IBasicDTO> filterByAllHireTypesForHireExecute() throws DataBaseException;

    /**
     * Get all employee copmleted their documents , procedures and waiting for hire decision * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllEmployeeWaitingForHireDecision() throws DataBaseException, SharedApplicationException;

    /**
     * This method to send procedure for list of employee and check if the procedure valid to send or not * @param employeeDTOList
     * @return list of resultDTO
     */
    List<IResultDTO> sendProcedure(List<IBasicDTO> employeeDTOList) throws SharedApplicationException;

    /**
     * Filter employee waiting for hire decision depend on hire type * @param empEmployeeSearchDTO
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                                   SharedApplicationException;

    /**
     * Filter employee waiting for hire decision depend on hire type 22/2/2009 * @param hireTypesEntityKey
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                  SharedApplicationException;

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> simpleSearch(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;

    /**
     * used in to filter employees * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> simpleSearchForAOE(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;

    public IPagingResponseDTO simpleSearchWithPaging(IBasicDTO basicDTO,
                                                     IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                          DataBaseException;

    public IPagingResponseDTO simpleSearchEmpWithPaging(IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                             DataBaseException;

    public List<IBasicDTO> simpleSearchBasic(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;

    public IPagingResponseDTO simpleSearchBasicWithPaging(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                               DataBaseException;

    public IPagingResponseDTO simpleSearchBasicWithPagingAtt(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                               DataBaseException;

    /**
     * search hired employees and return list of employees whose civilIDs are not in the * @param basicDTO the IEmployeesDTO search criteria DTO
     * @param salEmpSalaryElementsDTOList the list of employees to be not included in the search result set
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchFilterEmployee(IBasicDTO basicDTO,
                                         List<IBasicDTO> salEmpSalaryElementsDTOList) throws SharedApplicationException,
                                                                                             DataBaseException;

    /**
     * To add new Employee * @param employeesDTO1
     * @param usingTransAction
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    IBasicDTO add(IBasicDTO employeesDTO1, boolean usingTransAction) throws DataBaseException,
                                                                            SharedApplicationException;
    ///////////////////////////

    /**
     * Add Join To Decision * @param employeesList
     * @param decisionDTO
     * @return ResultDTO
     * @throws DataBaseException
     */
    List<ResultDTO> addJoinToDecision(List<IBasicDTO> employeesList,
                                      IBasicDTO decisionDTO) throws SharedApplicationException, DataBaseException;
    ////////////////////////////

    /**
     * Change employee budget type * @param changeBudgetTypeDTO1
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<ResultDTO> changeBudgetType(IBasicDTO changeBudgetTypeDTO1) throws DataBaseException,
                                                                            SharedApplicationException;

    /**
     * get employee info with his salary element info ( valid ) , for specific type * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Amir Nasr
     */
    IBasicDTO getEmployeeAndPayrollByElmType(Long civilId) throws DataBaseException, SharedApplicationException;

    /**
     * used in to filter employees by WorkCenter * @param workCenterCode
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllEmployeesByWorkCenter(String workCenterCode) throws DataBaseException,
                                                                              SharedApplicationException;

    /**
     * find if an employee exists in a certain ministry * @param civilId
     * @param minCode
     * @return Boolean
     * @throws DataBaseException
     */
    Boolean isEmployeeInMinistry(Long civilId, Long minCode) throws DataBaseException;

    /**
     * Add Decision to employee to hire * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean addDecision(List<IBasicDTO> employeesList, IBasicDTO basicDecisionDTO) throws SharedApplicationException,
                                                                                          DataBaseException;


    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     * @throws DataBaseException
     */
    //    Boolean isMinistryFileNoExist(String ministryFileNo) throws DataBaseException;

    /**
     * Get all employee after hire decision to start work * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllToStartWork() throws DataBaseException, SharedApplicationException;

    /**
     * Search employee after hire decision to start work * @param civilId
     * @param name
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployeeStartWork(Long civilId, String name) throws DataBaseException,
                                                                              SharedApplicationException;

    /**
     * execute start work * @param employeesDTO1
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean executeStartWork(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException;

    /**
     * Get all employee to complete employee's date * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllToCompleteData() throws DataBaseException, SharedApplicationException;

    /**
     * Search employee to complete employee's date * @param employeeSearchDTO
     * @return list of employee
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployeeToCompleteData(IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                     SharedApplicationException;

    /**
     * Check if citizen is legel to employment with specific hire type * @param civilID
     * @param hireType
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean checkLegelHireType(Long civilID, Long hireType) throws DataBaseException, SharedApplicationException;

    /**
     * used in to filter employees by WorkCenter * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> getAllEmployeesByCuurentMinistry() throws DataBaseException, SharedApplicationException;

    //    List<IBasicDTO> getAllEmployeeByStageAndMinistry(long MinCode , String selectedStage) throws DataBaseException,
    //                                                                  SharedApplicationException;

    /**
     * check if employee is hired * @param employeesEntityKey
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean isEmployeeHired(IEntityKey employeesEntityKey) throws DataBaseException, SharedApplicationException;

    public Boolean isEmployeeHiredForVac(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                SharedApplicationException;

    /**
     * Update Employee Data * @param employeesDTO1
     * @return Boolean
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    IBasicDTO updateEmp(IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException;

    /**
     * Get all employee match search criteria and sorted by full name * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployee(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException;

    /**
     * To add new Employee in ministry * @param minKey ministry key to add the employee to it
     * @param employeesDTO1 the employee that will be added
     * @param usingTransAction to commit or not commit ( ignored )
     * @return IEmployeesDTO the added employee dto
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    IBasicDTO addInMinistry(IEntityKey minKey, IBasicDTO employeesDTO1,
                            boolean usingTransAction) throws DataBaseException, SharedApplicationException;

    /**
     * Validate employee civil id if it exists or nt then validate his hire type * @param civilID
     * @param hireType
     * @return TRUE : Employee exist and his hire type is legal
     * FALSE : Employee doesnt exist * throws NotMatchedOnHireTypeException : not a legal hire type * @throws DataBaseException
     * @throws SharedApplicationException
     */
    Boolean validateEmployeeHireType(Long civilID, Long hireType) throws DataBaseException, SharedApplicationException;

    /**
     * Get all employees match search criteria and sorted by full name for sal cal * @param basicDTO
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    List<IBasicDTO> searchEmployeeForSalCalc(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException;

    /**
     * get employee info for specific type * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author MLotfy
     */
    IBasicDTO getEmployeeInfoByElmType(Long civilId) throws DataBaseException, SharedApplicationException;

    List<IBasicDTO> searchEmployeeForSalPayment(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException;

    IBasicDTO getEmployeeInfoHasSocialRaise(Long civilId) throws DataBaseException, SharedApplicationException;

    IEmployeesDTO getCurrentEmpFinancialData(IEntityKey id1) throws DataBaseException, SharedApplicationException;

    /**
     * check if employee valid to add request to end his/her service * @param civilId
     * @return Boolean
     * @throws DataBaseException
     */
    public Boolean isEmployeeValidToEndService(Long civilId) throws DataBaseException;

    //    public Boolean updateEmployeeDataForMov(Long civilId,
    //                                            Long employeeHireStatus,
    //                                            Date endJobDate) throws DataBaseException,
    //                                                                    SharedApplicationException;

    //    public IBasicDTO addEmployeeForMovOnly(IBasicDTO employeesDTO1) throws DataBaseException,
    //                                                                           SharedApplicationException;

    public List<IBasicDTO> getEmployeByCivilId(Long civilID) throws FinderException, SharedApplicationException;

    /**
     * return EmployeeDTO civilId and KwtCitizensResidentsDTO fullName with auditing attributes
     * @param civilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Ashraf Gaber 19/01/2011
     */
    public IBasicDTO getEmployeeDTOCodeName(Long civilID) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> searchEmployeesForEvaluator(Long evaluatorCivilId,
                                                       IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                           SharedApplicationException;

    public List<IBasicDTO> searchEmployeesForEvaluatorByCivilId(Long evaluatorCivilId,
                                                                IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                                    SharedApplicationException;

    public List<IBasicDTO> searchEmployeesForEvaluatorByName(Long evaluatorCivilId,
                                                             IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                                 SharedApplicationException;

    public Date calculateNextRaiseDate(Date hireDate) throws SharedApplicationException;

    public Long[] getEmpServicePeriod(Long civilId) throws DataBaseException;

    IPagingResponseDTO getByHireStageWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                             SharedApplicationException;

    IPagingResponseDTO searchWithPaging(IBasicDTO _searchDTO) throws DataBaseException, SharedApplicationException;

    void refresh() throws DataBaseException;

    public List<IBasicDTO> searchByCivilAndName(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException;

    public List<IBasicDTO> simpleSearchFullData(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException;

    public List<IBasicDTO> simpleSearchAllMinistriesFullData(IBasicDTO basicDTO) throws DataBaseException,
                                                                                        SharedApplicationException;

    @Deprecated
    public IEmployeesDTO getByRealCivilId(Long realCivilId, Long minCode) throws RemoteException, FinderException;

    IEmployeesDTO getByRealCivilIdForEsrv(Long realCivilId, Long minCode) throws RemoteException, FinderException;

    IEmployeesDTO getCivilByRealCivil(Long realCivilId, Long minCode) throws RemoteException, FinderException;

    public IEmployeesDTO getSimpleEmployeeByRealCivilId(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                               SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdAndHireStatus(Long realCivilId, Long minCode) throws RemoteException,
                                                                                              FinderException;

    public IEmployeesDTO getByRealCivilIdInallMin(Long realCivilId) throws RemoteException, FinderException;

    public IEmployeesDTO getByRealCivilIdInallMinForReg(Long realCivilId) throws RemoteException, FinderException;

    public IEmployeesDTO getByRealCivilIdForReg(Long realCivilId) throws RemoteException, FinderException;


    public IEmployeesDTO getByRealCivilIdAndHireStatusForMov(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                                    SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdForMovWithoutFilteration(Long realCivilId,
                                                                  Long minCode) throws RemoteException,
                                                                                       FinderException;

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMovSimple(Long realCivilId,
                                                                   Long minCode) throws RemoteException,
                                                                                        FinderException;

    public IEmployeesDTO getEmpForMov(Long realCivilId, Long minCode) throws  SharedApplicationException,
                                                                                                 DataBaseException;

    public IEmployeesDTO getByRealCivilIdAllMinistries(Long realCivilId) throws RemoteException, FinderException;
    // cancelCandidate

    IBasicDTO updateEmpCancelCandidate(IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException;

    public Boolean isEmployeeHired(Long realCivilId, Long minCode) throws DataBaseException,
                                                                          SharedApplicationException;

    /**
     * return true if employee is hired
     * @param civilID
     * @return
     * @throws RemoteException
     * @author Dina Abd El-Rahim 06/03/2014
     **/
    public Boolean isEmployeeHiredForVac(Long civilId) throws DataBaseException, SharedApplicationException;

    public Long isJobHasEmployees(Long jobCode) throws DataBaseException, SharedApplicationException;

    public Boolean checkEmployeeRequiredProceduresStatus(Long serial) throws DataBaseException,
                                                                             SharedApplicationException;

    public Boolean checkEmployeeRequiredDocumentsStatus(Long serial) throws DataBaseException,
                                                                            SharedApplicationException;

    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                DataBaseException;

    public IPagingResponseDTO simpleSearchVacEmpWithPaging(IBasicDTO basicDTO,IBgtProgramsDTO level,
                                                           IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                DataBaseException;

    public List<IBasicDTO> simpleSearchVacEmp(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;

    public List<IBasicDTO> simpleSearchVacEmpAllData(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                DataBaseException;

    public List simpleSearchVacEmpEnhanced(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;

    public List<IBasicDTO> searchVacEmp(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;

    public List<IBasicDTO> getAllEmployeesByPremittedMinistries(IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException;

    public List<IBasicDTO> filterAvailableEntitiesUsingPaging(IBasicDTO employeeSearchDTO1,
                                                              IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                   SharedApplicationException;

    public Long filterAvailableEntitiesUsingPagingCount(IBasicDTO employeeSearchDTO1) throws DataBaseException,
                                                                                             SharedApplicationException;

    public List<IBasicDTO> simpleSearchWithoutMinCode(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                 DataBaseException;

    public List<IBasicDTO> searchMerVariableEmployee(IBasicDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException;

    Long countEmployeesByJobAndMin(String jobCode, Long minCode) throws SharedApplicationException, DataBaseException;

    public Long countHiredEmployeesByMinWrkCode(Long minCode, String wrkCode) throws RemoteException, FinderException;

    public String getStatusForHire() throws RemoteException;

    public List<IMinistriesDTO> getMinistriesListByEvalCivilId(Long evalCivilId,
                                                               List<IMinistryInfo> minList) throws DataBaseException,
                                                                                                   SharedApplicationException;
    public List<IMinistriesDTO> getMinistriesListByEvalCivilIdNew(Long evalCivilId,
                                                               List<IMinistriesDTO> minList) throws DataBaseException,
                                                                                                   SharedApplicationException;

    public IBasicDTO addEmployeeForMovOnly(IBasicDTO employeesDTO1) throws DataBaseException,
                                                                           SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdAllMinByRecordDesc(Long realCivilId) throws DataBaseException,
                                                                                     SharedApplicationException;

    Boolean isEmployeeHiredWithRecordDesc(Long realCivilId) throws DataBaseException, SharedApplicationException;

    List<IBasicDTO> getEmpByEmpSearch(EmpSearchRecodDesc searchDTO) throws DataBaseException,
                                                                           SharedApplicationException;

    Boolean updateEmployeeForPRM(IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException;

    public Boolean updateEmployeeStaus(IEntityKey empKey, IEntityKey employeeHireStatus) throws DataBaseException,
                                                                                                SharedApplicationException;

    public IEmployeesDTO getCurrActiveEmpByRealCivilId(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException;

    public Boolean checkFileNoWithMinAndEmployeeForMov(Long minCode, String minFileNo,
                                                       Long civilId) throws DataBaseException,
                                                                            SharedApplicationException;

    public int updateActiveFlagByRealCivilId(Long realCivilId) throws DataBaseException, SharedApplicationException;

    public int updateWorkCodeByRealCivilId(Long realCivilId, String workCode) throws DataBaseException,
                                                                                     SharedApplicationException;

    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                                            SharedApplicationException;

    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(Long realCivilId) throws DataBaseException,
                                                                                                                  SharedApplicationException;

    public Boolean checkHireDateContradiction(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException;


    public IBasicDTO addEmployeeForExecuteMovOnly(IBasicDTO employeesDTO1) throws DataBaseException,
                                                                                  SharedApplicationException;

    public IPagingResponseDTO simpleSearchMovEmpWithPaging(IBasicDTO basicDTO,
                                                           IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                DataBaseException;

    public IPagingResponseDTO simpleSearchMovEmpWithPagingAllChecked(IBasicDTO basicDTO, IPagingRequestDTO requestDTO,
                                                                     List<String> unCheckedList) throws SharedApplicationException,
                                                                                                        DataBaseException;

    public Boolean updateEmployeeBeforeExecuteEmployment(Long realCivilId) throws DataBaseException,
                                                                                  SharedApplicationException;


    public int updateActiveFlagForDelegation(Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode) throws DataBaseException, SharedApplicationException;

    public int updateActiveFlagForDelegationWithRCivil(Long activeFlag, Long hireStatusCode, Date hireDate,
                                             Long minCode , Long realCivilId) throws DataBaseException, SharedApplicationException;

    public Boolean isEmpIndebt(Long realCivilId) throws DataBaseException, SharedApplicationException;

    public Boolean isConditionFromGrsValid(Long realCivilId, Long degreeCode) throws DataBaseException,
                                                                                     SharedApplicationException;

    /**
     * return all hired employees which not in specific job level
     * @param jobSearchCriteriaDTO
     * @return List<IEmployeesDTO>
     * @author I.Omar 15/10/2015
     **/
    List<IEmployeesDTO> getAllHiredEmployeesByJobLevelsAndType(IJobSearchCriteriaDTO jobSearchCriteriaDTO,
                                                               Map<String, Long> conditionMap) throws DataBaseException,
                                                                                                      SharedApplicationException;

    public IPagingResponseDTO getAllEmployeesByForLoginMinistries(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                             DataBaseException;

    public IEmployeesDTO getEmpCodeNameByRealCivilId(IBasicDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException;

    public List<IBasicDTO> searchByCivilAndNameUsingMinCode(IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException;

    public List<IBasicDTO> searchByCivilAndNameUsingMinCodeForCER(IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException;

    public boolean checkFilterDataForEmp(Long groupCode, Long userCode, Long realCivilID) throws DataBaseException,
                                                                                                 SharedApplicationException;

    public String applyWrkcenterTreeFilter() throws DataBaseException, SharedApplicationException;

    public HashMap<String, String> getworkCenterTree() throws DataBaseException, SharedApplicationException;

    public HashMap<Long, String> getEmployeesworkCenterTree() throws DataBaseException, SharedApplicationException;

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<Long,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all groups for workcenters tree for 02133
     */
    public HashMap<Long, String> getGroupsworkCenterTree() throws DataBaseException, SharedApplicationException;

    public List<String> filterDataByWrkCenterList() throws DataBaseException, SharedApplicationException;

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
    public String getWrkcenterDataFilter(boolean isNative) throws DataBaseException, SharedApplicationException;

    public String getDataByWrkCenter(String colName) throws DataBaseException, SharedApplicationException;

    List<IEmployeesDTO> getByRealCivilForFTQ(Long realCivilId, String name) throws DataBaseException,
                                                                                   SharedApplicationException;


    public List<IEmployeesDTO> getEmployeesByRealCivilId_eos(Long realCivilId) throws DataBaseException,
                                                                                      SharedApplicationException;

    public List<IBasicDTO> searchByCivilIdAndName(IBasicDTO basicDTO) throws DataBaseException,
                                                                             SharedApplicationException;


    public String getUserName(Long userCode) throws DataBaseException, SharedApplicationException;

    public Long getCandidateCode(Long civilId) throws DataBaseException, SharedApplicationException;

    public IPagingResponseDTO getAllEmployeesInMinWithPaging(IBasicDTO searchDTO) throws DataBaseException,
                                                                                         SharedApplicationException;


    public Boolean updateJobCodeForADC(List<IBasicDTO> basicDTOList, String jobCode) throws DataBaseException,
                                                                                            SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdAndHireStatusForExternalMov(Long realCivilId,
                                                                     Long minCode) throws SharedApplicationException,
                                                                                          DataBaseException;

    public List<IEmployeesDTO> getEmpByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                            SharedApplicationException;

    public IPagingResponseDTO searchEmpWithoutValidations(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                               DataBaseException;


    public IPagingResponseDTO searchEmpWithHireStatus(IBasicDTO basicDTO,
                                                      IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                           DataBaseException;

    public IPagingResponseDTO simpleSearchBasicWithPagingADC(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                  DataBaseException;

    public IEmployeesDTO getByRealCivilIdForADCWithoutFilteration(Long realCivilId,
                                                                  Long minCode) throws SharedApplicationException,
                                                                                       DataBaseException;

    public IEmployeesDTO getByRealCivilIdForADCEmployees(Long realCivilId,
                                                                  Long minCode) throws SharedApplicationException,
                                                                                       DataBaseException;

    public IEmployeesDTO getEmpMainInfoBySecurity(String user_code,
                                                  String user_name) throws SharedApplicationException,
                                                                           DataBaseException;


    public Long getMinCodeByCivil(Long civilId) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> searchByCivilAndNameAllMinistries(IBasicDTO basicDTO,
                                                             Boolean mokfaaShamla) throws DataBaseException,
                                                                                          SharedApplicationException;

    public List<ISimpleEmployeesDTO> getByRealCivilForCER(Long realCivilId, String name) throws DataBaseException,
                                                                                                SharedApplicationException;

    public IPagingResponseDTO searchEmpWithPagingForFTQ(IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                             DataBaseException;

    public IEmployeesDTO getByRealCivilIdBasicData(Long realCivilId, Long minCode) throws SharedApplicationException,
                                                                                          DataBaseException;

    public IEmployeesDTO getByRealCivilIdBasicDataAndMinistryName(Long realCivilId,
                                                                  Long minCode) throws SharedApplicationException,
                                                                                       DataBaseException;

    public Long checkFileNoWithMinAndEmployeeForADC(Long minCode, String minFileNo,
                                                    Long civilId) throws DataBaseException, SharedApplicationException;

    public IPagingResponseDTO searchEmployeeWithPagingForAtt(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws SharedApplicationException,
                                                                                                  DataBaseException;

    public IBasicDTO getEmpCodeNameByRealCivilIdForAtt(IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException;

    public String getCivilsRelatedToWorkCenter(String workCenter, boolean isRelated,
                                               boolean isNative) throws DataBaseException, SharedApplicationException;

    public Date getEmpFirstHireDate(Long realCivilId) throws DataBaseException, SharedApplicationException ;

    public IEmployeesDTO getEmployeesByRealCivilIdAndMinForBankWebService(Long realCivilId, Long minCode,
                                                                          String hireStatusListCommaSeparated) throws SharedApplicationException,
                                                                                                                      DataBaseException;
    /**
     * @author m.sayed
     * @since   20-6-2018
     * @param realCivilId
     * @param name
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IEmployeesDTO> getByRealCivilForFTQWithDF(Long realCivilId, String name) throws DataBaseException,SharedApplicationException;


    public String getEmpCodeNameForContractEmp(Long  civilID, Long minCode) throws DataBaseException,
                                                                                      SharedApplicationException;

    public boolean updateNextRaiseDate(Date nextRaiseDate , String civilId) throws DataBaseException,
                                                                           SharedApplicationException ;

    public IEmployeesDTO searchByCivilIdEstana( IBasicDTO basicDTO) throws DataBaseException,
    SharedApplicationException;


    public IBasicDTO getEmployeeDTOCodeNameWithoutFilteration(Long civilID) throws DataBaseException, SharedApplicationException;

    public IEmployeesDTO getByRealCivilIdAllMinistriesForAdc(Long realCivilId) throws DataBaseException,
                                                                                SharedApplicationException;

    public IBasicDTO getSubWorkCeneterCodeName(String workCode,Long realCivilId , Long minCode)throws DataBaseException,SharedApplicationException;


    public EmpStatusForSalDTO getEmpStatusAndHireOrEndServiceDateForSal( long realCivilId)throws DataBaseException,SharedApplicationException;

    public IPagingResponseDTO simpleSearchBasicWithPagingForLeaders(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                               SharedApplicationException;

    public IEmployeesDTO getEmpCodeNameByRealCivilIdForLeaders(EmpEmployeeSearchDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException;

    public IEmployeesDTO getByCivilIdForPRM(Long civilId) throws DataBaseException, SharedApplicationException;

}
