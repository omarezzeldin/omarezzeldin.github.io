package com.beshara.csc.hr.emp.business.integration.eservices.employee;


import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmpFinicialDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmpSalDetailsDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmployeeDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.CivilIDNotFoundException;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.EmployeeNotHiredException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;

import javax.jws.WebParam;
import javax.jws.WebService;


@Remote
@WebService
public interface IEmployeeEService extends IEService {

    public EmployeeDTO getEmployeeData(EmployeeDTO reqEmp) throws SharedApplicationException, DataBaseException,
                                                                  InvalidDataEntryException, EmployeeNotHiredException,
                                                                  CivilIDNotFoundException;

    public void CheckIfEmployeeOrCandidate(Long civilId) throws SharedApplicationException, DataBaseException;

    public EmployeeDTO getAllEmployeeData(EmployeeDTO reqEmp) throws SharedApplicationException, DataBaseException,
                                                                     InvalidDataEntryException,
                                                                     EmployeeNotHiredException,
                                                                     CivilIDNotFoundException;

    public EmployeeDTO getEmpMainInfoBySecurity(String user_code, String user_name) throws SharedApplicationException,
                                                                                           DataBaseException;

    public String getEmployeeIBANData(Long civilId);

    public List<EmployeeDTO> getAllEmployeeswithContract(Long minCode) throws DataBaseException,
                                                                              SharedApplicationException;

    public EmployeeDTO getAllEmployeeDataForProfile(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException;

    public EmployeeDTO getEmployeeSimpleDTOForESRV(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                              DataBaseException,
                                                                              InvalidDataEntryException,
                                                                              EmployeeNotHiredException,
                                                                              CivilIDNotFoundException;

    public String getUserNameByRole(Long roleId, Long realCivil, Long minCode) throws DataBaseException,
                                                                                      SharedApplicationException;

    public String getUserNamesHighgerRole(Long roleId, String userNames, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException;

    public EmployeeDTO getSalaryInfoByDate(EmployeeDTO reqEmp, int year, int month);

    public List<EmployeeDTO> getAllUserNameByRole(Long roleID, Long realCivil,
                                                  Long minCode, String userCodes) throws SharedApplicationException,
                                                                             DataBaseException;
    public List<EmpSalDetailsDTO> getSalElementDatails(Long realCivil, Long civilId);

    public boolean checkIfEmployeeExists(Long realCivilId) throws DataBaseException, SharedApplicationException;
    public EmployeeDTO getAllEmployeeDataForProfileMob(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException;
    public EmployeeDTO getAllEmployeeDataForProfileMob2(@WebParam(name="realCivilID") Long civilID) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException ;
    /**
     *@author msayed
     * @since 14-5-2020
     * @get data for mobile webserivce
     * @param realCivilID
     * @param yearCode
     * @param monthCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public EmpFinicialDTO getEmpMertisAndDeducts(@WebParam(name="realCivilID") Long realCivilID,@WebParam(name="yearCode") Long yearCode,@WebParam(name="monthCode") Long monthCode)throws SharedApplicationException,DataBaseException;
    
    /**
     * @author msayed
     * @since 04-07-2020
     * @param civilID
     * @return
     * @throws SharedApplicationException
     * @throws DataBaseException
     * @throws InvalidDataEntryException
     * @throws EmployeeNotHiredException
     * @throws CivilIDNotFoundException
     */
    public EmployeeDTO getAllEmployeeDataForProfileVW(@WebParam(name="realCivilID") Long civilID) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException ;
    /**
     * @author msayed
     * @since 04-07-2020
     * @get data for mobile webserivce
     * @param realCivilID
     * @param yearCode
     * @param monthCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public EmpFinicialDTO getEmpMertisAndDeductsVW(@WebParam(name="realCivilID") Long realCivilID,@WebParam(name="yearCode") Long yearCode,@WebParam(name="monthCode") Long monthCode)throws SharedApplicationException,DataBaseException;
    
     /**
      * @author msayed
      * @since 04-07-2020
      * @get data for mobile webserivce
      * @param realCivilID
      * @return
      * @throws DataBaseException
      * @throws SharedApplicationException
      */
     
}
