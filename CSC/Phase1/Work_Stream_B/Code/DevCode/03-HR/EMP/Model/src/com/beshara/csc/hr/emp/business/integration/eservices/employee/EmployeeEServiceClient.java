package com.beshara.csc.hr.emp.business.integration.eservices.employee;


import com.beshara.base.integration.eservices.EServiceClient;
import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmpSalDetailsDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmployeeDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.CivilIDNotFoundException;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.EmployeeNotHiredException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class EmployeeEServiceClient extends EServiceClient {
    public EmployeeEServiceClient() {
        super();
    }

    @Override
    protected Class<? extends IEService> getEServiceInterface() {
        return IEmployeeEService.class;
    }

    @Override
    protected IEmployeeEService SERVICE() {
        return (IEmployeeEService)super.SERVICE();
    }

    public EmployeeDTO getEmployeeData(EmployeeDTO reqEmp) throws SharedApplicationException, DataBaseException {
        try {
            return SERVICE().getEmployeeData(reqEmp);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public void CheckIfEmployeeOrCandidate(Long civilId) throws SharedApplicationException, DataBaseException {
        try {
            SERVICE().CheckIfEmployeeOrCandidate(civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public EmployeeDTO getAllEmployeeData(EmployeeDTO reqEmp) throws SharedApplicationException, DataBaseException {
        try {
            return SERVICE().getAllEmployeeData(reqEmp);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public EmployeeDTO getEmpMainInfoBySecurity(String user_code, String user_name) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            return SERVICE().getEmpMainInfoBySecurity(user_code, user_name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public String getEmployeeIBANData(Long civilId) {

        return SERVICE().getEmployeeIBANData(civilId);

    }

    public List<EmployeeDTO> getAllEmployeeswithContract(Long minCode) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return SERVICE().getAllEmployeeswithContract(minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public EmployeeDTO getEmployeeSimpleDTOForESRV(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                              DataBaseException,
                                                                              InvalidDataEntryException,
                                                                              EmployeeNotHiredException,
                                                                              CivilIDNotFoundException {
        return SERVICE().getEmployeeSimpleDTOForESRV(reqEmp);
    }

    public EmployeeDTO getAllEmployeeDataForProfile(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException {
        return SERVICE().getAllEmployeeDataForProfile(reqEmp);
    }

    public EmployeeDTO getSalaryInfoByDate(EmployeeDTO reqEmp, int year, int month) {
        return SERVICE().getSalaryInfoByDate(reqEmp, year, month);
    }

    public String getUserNameByRole(Long roleId, Long realCivil, Long minCode) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            return SERVICE().getUserNameByRole(roleId, realCivil, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public String getUserNamesHighgerRole(Long roleId, String userNames, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            return SERVICE().getUserNamesHighgerRole(roleId, userNames, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<EmployeeDTO> getAllUserNameByRole(Long roleID, Long realCivil, Long minCode,
                                                  String userNames) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SERVICE().getAllUserNameByRole(roleID, realCivil, minCode, userNames);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<EmpSalDetailsDTO> getSalElementDatails(Long realCivil, Long civilId) {
        return SERVICE().getSalElementDatails(realCivil, civilId);
    }
    
    public boolean checkIfEmpExists(Long civilId) throws SharedApplicationException, DataBaseException {
        try {
            System.out.println("in Client >>> checkIfEmpExists");
            return SERVICE().checkIfEmployeeExists(civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
