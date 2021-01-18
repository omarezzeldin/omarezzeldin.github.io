package com.beshara.csc.hr.emp.business.integration.eservices.employee;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.integration.eservices.EServiceImpl;
import com.beshara.csc.hr.emp.business.deploy.EmployeesSession;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpFinicialData;
import com.beshara.csc.hr.emp.business.dto.IEmployeeDTOService;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmpFinicialDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmpSalDetailsDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmployeeDTO;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmployeeDTOMapper;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.ApplicantIsAlreadyEmployedException;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.ApplicantIsCandidateException;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.CivilIDNotFoundException;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.EmployeeNotHiredException;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSalariesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMonSearchCriteriaDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpPayslipsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalDegreeReasonsEntityKey;
import com.beshara.csc.hr.sal.business.entity.ISalEmpMonSalariesEntityKey;
import com.beshara.csc.hr.sal.business.entity.ISalEmpSalaryElementsEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.sal.business.shared.IScpConstants;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.nl.bnk.business.client.IPersonBankAccountsClient;
import com.beshara.csc.nl.bnk.business.client.PersonBankAccountsClientImpl;
import com.beshara.csc.nl.bnk.business.dto.PersonBankAccountsDTO;
import com.beshara.csc.nl.bnk.business.shared.IBnkConstant;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.jws.WebService;

import weblogic.wsee.wstx.wsat.Transactional;


/**
 *
 * The Class EmployeeEServiceImpl.
 * Date Created: 03-6-2014
 */
@Stateless(name = "EmpEmployeeEService", mappedName = "EmpEmployeeEService")
@WebService(endpointInterface = "com.beshara.csc.hr.emp.business.integration.eservices.employee.IEmployeeEService")
@Transactional
public class EmployeeEServiceImpl extends EServiceImpl implements IEmployeeEService {
    @EJB
    private EmployeesSession employeesSession;

    public EmployeeEServiceImpl() {
    }


    /**
     * Method used to get Employee Data.
     *
     * @param reqEmp the req emp
     * @return the employee data
     * @throws SharedApplicationException the shared application exception
     * @throws DataBaseException the data base exception
     * @throws InvalidDataEntryException the invalid data entry exception
     * @throws com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.EmployeeNotHiredException the employee not hired exception
     * @throws com.beshara.csc.hr.emp.business.integration.eservices.employee.exceptions.CivilIDNotFoundException the civil id not found exception
     */
    @Override
    public EmployeeDTO getEmployeeData(EmployeeDTO reqEmp) throws SharedApplicationException, DataBaseException,
                                                                  InvalidDataEntryException, EmployeeNotHiredException,
                                                                  CivilIDNotFoundException {

        try {
            System.out.println("getEmployeeData for Portal step 1 : RealCivilId : " + reqEmp.getRealCivilId());
            System.out.println("getEmployeeData for Portal step 1 : MinCode : " + reqEmp.getMinCode());
            IEmployeesDTO employeesDTO = null;
            try {
                employeesDTO =
                        employeesSession.getByRealCivilIdForEsrv(RI(), reqEmp.getRealCivilId(), reqEmp.getMinCode());
            } catch (RemoteException e) {
                System.out.println("getEmployeeData for Portal Exception 1 ");
                throw new DataBaseException(SharedUtils.getExceptionMessage(e));
            } catch (SharedApplicationException e) {
                System.out.println("getEmployeeData for Portal Exception 2 ");
                //means item not found
                throw new CivilIDNotFoundException();
            }

            System.out.println("getEmployeeData for Portal step 2 : RealCivilId : " + employeesDTO.getRealCivilId());
            System.out.println("getEmployeeData for Portal step 2 : MinCode : " + employeesDTO.getMinCode());
            System.out.println("getEmployeeData for Portal step 2 : civilId : " + employeesDTO.getCivilIds());

            if (employeesDTO == null || !employeesSession.isEmployeeHired(RI(), employeesDTO.getCode()))
                throw new EmployeeNotHiredException();
            // conver Employee Data return from bussiens Layer to request Employee Data
            reqEmp = EmployeeDTOMapper.getEmployeeDTO(employeesDTO, reqEmp);
            System.out.println("getEmployeeData for Portal step 3 : RealCivilId : " + reqEmp.getRealCivilId());
            System.out.println("getEmployeeData for Portal step 3 : MinCode : " + reqEmp.getMinCode());
            System.out.println("getEmployeeData for Portal step 3 : civilId : " + reqEmp.getCivilId());
            return reqEmp;
        } catch (RemoteException e) {
            System.out.println("getEmployeeData for Portal Exception 3 ");
            throw new DataBaseException(e.getMessage());
        }
    }


    public void CheckIfEmployeeOrCandidate(Long civilId) throws SharedApplicationException, DataBaseException {
        try {
            long id = employeesSession.CheckIfEmployeeOrCandidate(RI(), civilId);
            if (id == 1L) {
                throw new ApplicantIsAlreadyEmployedException("ApplicantIsAlreadyEmployedException");
            } else if (id == 2L) {
                throw new ApplicantIsCandidateException("ApplicantIsCandidateException");
            }
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public EmployeeDTO getAllEmployeeData(EmployeeDTO reqEmp) throws SharedApplicationException, DataBaseException,
                                                                     InvalidDataEntryException,
                                                                     EmployeeNotHiredException,
                                                                     CivilIDNotFoundException {
        System.out.println("getAllEmployeeDatagetAllEmployeeDatagetAllEmployeeDatagetAllEmployeeData222222");
        try {
            IEmployeesDTO employeesDTO = null;
            try {
                employeesDTO =
                        employeesSession.getByRealCivilIdForEsrv(RI(), reqEmp.getRealCivilId(), reqEmp.getMinCode());
            } catch (RemoteException e) {
                throw new DataBaseException(SharedUtils.getExceptionMessage(e));
            } catch (SharedApplicationException e) {
                throw new CivilIDNotFoundException();
            }
            if (employeesDTO == null || !employeesSession.isEmployeeHired(RI(), employeesDTO.getCode()))
                throw new EmployeeNotHiredException();
            reqEmp = EmployeeDTOMapper.getEmployeeWithAdressDTO(employeesDTO, reqEmp);
            IPersonQualificationsDTO personQualificationsDTO = InfDTOFactory.createPersonQualificationsDTO();
            try {
                personQualificationsDTO =
                        (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getLastPersonQualification(employeesDTO.getRealCivilId());
            } catch (DataBaseException dbe) {
                personQualificationsDTO = null;
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                personQualificationsDTO = null;
                sae.printStackTrace();
            }
            if (personQualificationsDTO != null) {
                reqEmp.setQualDate(new Date(personQualificationsDTO.getQualificationDate().getTime()));
                reqEmp.setQualName(personQualificationsDTO.getQualificationsDTO().getName());
            }
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
            try {
                salEmpSalaryElementsDTO =
                        (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getCurrentSalEmpRaiseByCivilAndType(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId(),
                                                                                                                                       ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
            } catch (DataBaseException dbe) {
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                sae.printStackTrace();
            }
            if (salEmpSalaryElementsDTO != null) {
                if (salEmpSalaryElementsDTO.getSalElementGuidesDTO() != null) {
                    if((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject() != null){
                        reqEmp.setSalDegree(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject().getName());
                        reqEmp.setSalDegreeCode((Long)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject().getCode().getKeys()[0]);
                        if(((ISalElementGuidesDTO)((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()) != null){
                            reqEmp.setSalGroup((((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getName());
                            reqEmp.setSalGroupCode((Long)(((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getCode().getKeys()[0]);
                            if(((ISalElementGuidesDTO)((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getParentObject() != null){
                                reqEmp.setSalCader(((ISalElementGuidesDTO)((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getParentObject().getName());
                                reqEmp.setSalCaderCode((Long)((ISalElementGuidesDTO)((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getParentObject().getCode().getKeys()[0]);    
                            }
                        }
                        
                        
                    }
                    
                    reqEmp.setSalRaise(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCountGuide());
                    reqEmp.setSalRaiseCode(((SalElementGuidesEntityKey)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode()).getElmguideCode());
                }
            }
            IPersonBankAccountsClient personBankAccountsClient = new PersonBankAccountsClientImpl();
            List BankList = new ArrayList();
            try {
                BankList = personBankAccountsClient.getValidPersonBankAccountsByCivilID(employeesDTO.getRealCivilId());
            } catch (DataBaseException dbe) {
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                sae.printStackTrace();
            }
            if (BankList.size() > 0) {
                PersonBankAccountsDTO personBankAccountsDTO = (PersonBankAccountsDTO)BankList.get(0);
                reqEmp.setBankBranche(personBankAccountsDTO.getBankBranchesDTO().getBanksDTO().getName());
                reqEmp.setAccountNo(personBankAccountsDTO.getAccountNo());
            }
            return reqEmp;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public EmployeeDTO getEmpMainInfoBySecurity(String user_code, String user_name) throws SharedApplicationException,
                                                                                           DataBaseException {
        IEmployeesDTO employeesDTO;
        try {
            employeesDTO = employeesSession.getEmpMainInfoBySecurity(RI(), user_code, user_name);
        } catch (RemoteException e) {
            e.printStackTrace();
            return new EmployeeDTO();
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setRealCivilId(employeesDTO.getRealCivilId());
        employeeDTO.setMinCode(employeesDTO.getMinCode());
        if (employeesDTO.getCitizensResidentsDTO() != null) {
            KwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                (KwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO();
            employeeDTO.setMobileNo(kwtCitizensResidentsDTO.getMobileNo());
            employeeDTO.setEMail(kwtCitizensResidentsDTO.getEMail());
        }
        return employeeDTO;
    }

    public String getEmployeeIBANData(Long civilId) {
        IPersonBankAccountsClient personBankAccountsClient = new PersonBankAccountsClientImpl();
        try {
            //List personBankAccountsListDTO = personBankAccountsClient.getValidPersonBankAccountsByCivilID(civilId);
            List personBankAccountsListDTO = personBankAccountsClient.getPersonBankAccountsByCivilAndAccStatusCode(civilId, IBnkConstant.ACCOUNT_VALID);
            if (personBankAccountsListDTO != null && personBankAccountsListDTO.size() > 0) {
                PersonBankAccountsDTO personBankAccountsDTO = (PersonBankAccountsDTO)personBankAccountsListDTO.get(0);
                if (personBankAccountsDTO != null)
                    return personBankAccountsDTO.getAccountNo();
                else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (DataBaseException e) {
            return null;
        } catch (SharedApplicationException e) {
            return null;
        }
    }

    public List<EmployeeDTO> getAllEmployeeswithContract(Long minCode) throws DataBaseException,
                                                                              SharedApplicationException {

        try {
            List<EmployeeDTO> resultList = new ArrayList<EmployeeDTO>();
            List<IBasicDTO> list;

            list = employeesSession.getAllEmployeeswithContract(RI(), minCode);
            for (IBasicDTO basicDTO : list) {
                EmployeeDTO dto = new EmployeeDTO();
                dto.setRealCivilId(((IEmployeesDTO)basicDTO).getRealCivilId());
                dto.setHireDate(((IEmployeesDTO)basicDTO).getHireDate());
                dto.setEmployeeFullName(((IEmployeesDTO)basicDTO).getEmployeeName());
                resultList.add(dto);
            }
            return resultList;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }

    }

    public EmployeeDTO getAllEmployeeDataForProfile(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException {
        try {
            IEmployeesDTO employeesDTO = null;
            try {
                employeesDTO =
                        employeesSession.getByRealCivilIdForEsrv(RI(), reqEmp.getRealCivilId(), reqEmp.getMinCode());
            } catch (RemoteException e) {
                throw new DataBaseException(SharedUtils.getExceptionMessage(e));
            } catch (SharedApplicationException e) {
                throw new CivilIDNotFoundException();
            }

            if (employeesDTO == null || !employeesSession.isEmployeeHired(RI(), employeesDTO.getCode()))
                throw new EmployeeNotHiredException();
            reqEmp = EmployeeDTOMapper.getEmployeeWithAdressDTO(employeesDTO, reqEmp);
            IPersonQualificationsDTO personQualificationsDTO = InfDTOFactory.createPersonQualificationsDTO();
            try {
                personQualificationsDTO =
                        (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getLastPersonQualification(employeesDTO.getRealCivilId());
            } catch (DataBaseException dbe) {
                personQualificationsDTO = null;
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                personQualificationsDTO = null;
                sae.printStackTrace();
            }
            if (personQualificationsDTO != null) {
                reqEmp.setQualDate(new Date(personQualificationsDTO.getQualificationDate().getTime()));
                reqEmp.setQualName(personQualificationsDTO.getQualificationsDTO().getName());
            }
            reqEmp = getSalInfo(reqEmp);
            try {
                String startWorkingDate =
                    InfClientFactory.getKwtWorkDataClient().getFirstExperience(reqEmp.getRealCivilId());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                reqEmp.setStartWorkingDate(df.parse(startWorkingDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return reqEmp;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public EmployeeDTO getSalInfo(EmployeeDTO reqEmp) throws NoResultException, DataBaseException,
                                                             SharedApplicationException {
        ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
        try {
            salEmpSalaryElementsDTO =
                    (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getCurrentSalEmpRaiseByCivilAndType(reqEmp.getCivilId(),
                                                                                                                                   ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
        } catch (DataBaseException dbe) {
            dbe.printStackTrace();
        } catch (SharedApplicationException sae) {
            sae.printStackTrace();
        }
        if (salEmpSalaryElementsDTO != null) {
            if (salEmpSalaryElementsDTO.getSalElementGuidesDTO() != null) {
                reqEmp.setSalCader(((ISalElementGuidesDTO)((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getParentObject().getName());
                reqEmp.setSalCaderCode((Long)((ISalElementGuidesDTO)((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getParentObject().getCode().getKeys()[0]);
                reqEmp.setSalGroup((((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getName());
                reqEmp.setSalGroupCode((Long)(((ISalElementGuidesDTO)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject()).getParentObject()).getCode().getKeys()[0]);
                reqEmp.setSalDegree(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject().getName());
                reqEmp.setSalDegreeCode((Long)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getParentObject().getCode().getKeys()[0]);
                reqEmp.setSalRaise(salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCountGuide());
                reqEmp.setSalRaiseCode(((SalElementGuidesEntityKey)salEmpSalaryElementsDTO.getSalElementGuidesDTO().getCode()).getElmguideCode());
                Date salDegreeDate =
                    SalClientFactory.getSalEmpSalaryElementsClient().getFirstDateForRaiseESRV(reqEmp.getSalDegreeCode(),
                                                                                              reqEmp.getCivilId());
                reqEmp.setSalDegreeDate(salDegreeDate);
            }
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        if (month > 12) {
            year = year + 1;
            month = 1;
        }
        reqEmp = getSalaryInfoByDate(reqEmp, year, month);
        return reqEmp;
    }


    public EmployeeDTO getSalaryInfoByDate(EmployeeDTO reqEmp, int year, int month) {
        ISalEmpMonSalariesEntityKey code =
            SalEntityKeyFactory.createSalEmpMonSalariesEntityKey(reqEmp.getRealCivilId(), Long.valueOf(year),
                                                                 Long.valueOf(month), null);
        System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDate1" +
                           reqEmp.getRealCivilId());
        System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDate1" +
                           year);
        System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDate1" +
                           month);
        ISalEmpMonSalariesDTO monSalDTO = null;
        try {
            monSalDTO =
                    (ISalEmpMonSalariesDTO)SalClientFactory.getSalEmpMonSalariesClient().getByCivilMonYearPaymethod(code,
                                                                                                                    Long.parseLong(IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDatemonSalDTO1" +
                           monSalDTO);

        if (monSalDTO != null) {
            reqEmp.setAccountNo(monSalDTO.getAccountChekNo());
            if (monSalDTO.getBankBranchesDTO() != null) {
                reqEmp.setBankBranche(monSalDTO.getBankBranchesDTO().getName());
                if (monSalDTO.getBankBranchesDTO().getBanksDTO() != null) {
                    reqEmp.setBankName(monSalDTO.getBankBranchesDTO().getBanksDTO().getName());
                    if (monSalDTO.getBankBranchesDTO().getBanksDTO().getCode() != null) {
                        reqEmp.setBankId(Long.valueOf(monSalDTO.getBankBranchesDTO().getBanksDTO().getCode().getKey()));
                    }
                }
            }
            System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDate2");
            BigDecimal totalDed = new BigDecimal(0L);
            BigDecimal totalMer = new BigDecimal(0L);
            List<IBasicDTO> meritsList;
            ISalEmpMonSearchCriteriaDTO searchDTO = SalDTOFactory.createSalEmpMonSearchCriteriaDTO();
            searchDTO.setCivilId(reqEmp.getRealCivilId());
            searchDTO.setSalaryMonth(Long.valueOf(month));
            searchDTO.setYear(Long.valueOf(year));
            String payStatus =
                IScpConstants.MON_SAL_STATUS_NOT_REVIEWED.toString() + "," + IScpConstants.PAYORDER_PREPARED_DURATION.toString() +
                "," + IScpConstants.MON_SAL_STATUS_FIN_WITH_SALARY_NOT_SIGNED.toString() + "," +
                IScpConstants.SAL_ELEM_GUIDE_FIN_DUES.toString() + "," + "23," +
                IScpConstants.MON_SAL_STATUS_FIN_SINGLE_PAYED.toString();

            String filterByPayType =
                IScpConstants.MON_SAL_PAY_TYPE_SALARIES.toString() + "," + IScpConstants.MON_SAL_PAY_TYPE_OTHERS.toString();
            String filterByPayMethod = IScpConstants.MONTHLY_SALARIES_PAYMETHOD_CODE.toString();
            searchDTO.setFilterByPayType(filterByPayType);
            searchDTO.setFilterByPayStatus(payStatus);
            searchDTO.setFilterByPayMethod(filterByPayMethod);
            try {
                meritsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
            } catch (Exception e) {
                e.printStackTrace();
                meritsList = null;
            }

            System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDate3" +
                               meritsList);
            if (meritsList != null) {
                for (Object dto1 : (ArrayList)meritsList) {
                    ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)dto1;
                    if (dto.getElementGuidesDTO().getSalElementGuidesDTO() == null) {
                        dto.getElementGuidesDTO().setSalElementGuidesDTO(dto.getElementGuidesDTO());
                    }
                    dto.getElementGuidesDTO().getSalElementGuidesDTO().setBigValue(dto.getValue());
                    if (dto.getValue() != null)
                        totalMer = totalMer.add(dto.getValue());
                }
            }

            searchDTO.setSalElementType(IScpConstants.MON_SAL_TYPE_DED);
            List<IBasicDTO> deductionsList;
            try {
                deductionsList = SalClientFactory.getSalEmpPayslipsClient().getMerDedList(searchDTO);
            } catch (Exception e) {
                e.printStackTrace();
                deductionsList = null;
            }
            if (deductionsList != null) {
                for (Object dto1 : (ArrayList)deductionsList) {
                    ISalEmpPayslipsDTO dto = (ISalEmpPayslipsDTO)dto1;
                    if (dto.getElementGuidesDTO().getSalElementGuidesDTO() == null) {
                        dto.getElementGuidesDTO().setSalElementGuidesDTO(dto.getElementGuidesDTO());
                    }
                    if (dto.getValue() != null)
                        totalDed = totalDed.add(dto.getValue());
                }
            }

            System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDate4" +
                               totalMer);
            System.out.println("getSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDategetSalaryInfoByDatetotalDed" +
                               totalDed);
            reqEmp.setActualSalary(BigDecimalThreeDigitConverter(totalMer.add(totalDed)));
            reqEmp.setMerits(BigDecimalThreeDigitConverter(totalMer));
            reqEmp.setDeductions(BigDecimalThreeDigitConverter(totalDed));
        } else {
            IPersonBankAccountsClient personBankAccountsClient = new PersonBankAccountsClientImpl();
            List personBankAccountsListDTO = null;

            try {
                personBankAccountsListDTO =
                        personBankAccountsClient.getValidPersonBankAccountsByCivilID(reqEmp.getRealCivilId());
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
            if (personBankAccountsListDTO != null && personBankAccountsListDTO.size() > 0) {
                PersonBankAccountsDTO personBankAccountsDTO = (PersonBankAccountsDTO)personBankAccountsListDTO.get(0);
                if (personBankAccountsDTO != null) {
                    reqEmp.setAccountNo(personBankAccountsDTO.getAccountNo());
                    if (personBankAccountsDTO.getBankBranchesDTO() != null) {
                        reqEmp.setBankBranche(personBankAccountsDTO.getBankBranchesDTO().getName());
                        if (personBankAccountsDTO.getBankBranchesDTO().getBanksDTO() != null) {
                            reqEmp.setBankName(personBankAccountsDTO.getBankBranchesDTO().getBanksDTO().getName());
                            if (personBankAccountsDTO.getBankBranchesDTO().getBanksDTO().getCode() != null) {
                                reqEmp.setBankId(Long.valueOf(personBankAccountsDTO.getBankBranchesDTO().getBanksDTO().getCode().getKey()));
                            }
                        }
                    }

                }
            }
        }
        return reqEmp;
    }

    @Override
    public EmployeeDTO getEmployeeSimpleDTOForESRV(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                              DataBaseException,
                                                                              InvalidDataEntryException,
                                                                              EmployeeNotHiredException,
                                                                              CivilIDNotFoundException {
        try {
            EmployeesDTO inputEmp = new EmployeesDTO();
            inputEmp.setRealCivilId(reqEmp.getRealCivilId());
            inputEmp.setMinCode(reqEmp.getMinCode());
            IEmployeesDTO employeesDTO = null;
            try {
                employeesDTO = employeesSession.getEmployeeSimpleDTOForESRV(RI(), inputEmp);
            } catch (RemoteException e) {
                throw new DataBaseException(SharedUtils.getExceptionMessage(e));
            } catch (SharedApplicationException e) {
                //means item not found
                throw new CivilIDNotFoundException();
            }
            if (employeesDTO == null || !employeesSession.isEmployeeHired(RI(), employeesDTO.getCode()))
                throw new EmployeeNotHiredException();
            reqEmp = EmployeeDTOMapper.getEmployeeSimpleDTO(employeesDTO, reqEmp);
            return reqEmp;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public String getUserNameByRole(Long roleId, Long realCivil, Long minCode) throws DataBaseException,
                                                                                      SharedApplicationException {
        System.out.println("getUserNameByRolegetUserNameByRolegetUserNameByRolegetUserNameByRole" + realCivil);

        String userNames = "";
        try {
            userNames = employeesSession.getUserNameByRole(RI(), roleId, realCivil, minCode);
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
        return userNames;
    }

    public String getUserNamesHighgerRole(Long roleId, String userNames, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        System.out.println("getUserNamesHighgerRolegetUserNamesHighgerRole" + userNames);
        System.out.println("getUserNamesHighgerRolegetUserNamesHighgerRole" + roleId);

        String resultUserNames = "";
        try {
            resultUserNames = employeesSession.getUserNamesHighgerRole(RI(), roleId, userNames, minCode);
            System.out.println("getUserNamesHighgerRolegetUserNamesHighgerRoleresultUserNames" + resultUserNames);
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
        return resultUserNames;
    }

    private BigDecimal BigDecimalThreeDigitConverter(BigDecimal number) {
        if (number != null) {
            NumberFormat BigDecimalFormat = new DecimalFormat("0.000");
            String str = (BigDecimalFormat.format(number));
            System.out.println("BigDecimalThreeDigitConverter.BigDecimal " + new BigDecimal(str));
            return new BigDecimal(str);
        } else {
            return number;
        }

    }

    public List<EmployeeDTO> getAllUserNameByRole(Long roleID, Long realCivil, Long minCode,
                                                  String userCodes) throws SharedApplicationException,
                                                                           DataBaseException {

        try {
            List<EmployeeDTO> empListDTO = new ArrayList<EmployeeDTO>();
            List<String> userCodesList = new ArrayList<String>();
            if (userCodes != null && !"".equals(userCodes)) {
                String[] userCodesArr = userCodes.split(",");
                userCodesList = Arrays.asList(userCodesArr);
            }
            List<EmployeesDTO> list =
                employeesSession.getAllUserNameByRole(RI(), roleID, realCivil, minCode, userCodesList);
            for (EmployeesDTO emp : list) {
                EmployeeDTO empDTO = new EmployeeDTO();
                empDTO.setUserName(emp.getUserName());
                empDTO.setUserCode(emp.getUserCode());
                empDTO.setEmployeeFullName(emp.getEmployeeName());
                empDTO.setRealCivilId(emp.getRealCivilId());
                empListDTO.add(empDTO);
            }
            return empListDTO;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }

    }

    public List<EmpSalDetailsDTO> getSalElementDatails(Long realCivil, Long civilId) {
        List<EmpSalDetailsDTO> list = new ArrayList<EmpSalDetailsDTO>();
        List<IBasicDTO> allEmployeeDegreesList = new ArrayList<IBasicDTO>();
        try {
            allEmployeeDegreesList =
                    SalClientFactory.getSalEmpSalaryElementsClient().getAllEmpDegreesInAllMin(realCivil);
            if (allEmployeeDegreesList != null) {
                if (allEmployeeDegreesList.size() > 0) {
                    
                    ISalEmpSalaryElementsDTO currentDTO = (ISalEmpSalaryElementsDTO)allEmployeeDegreesList.get(0);
                    Long elmguideCode = ((ISalEmpSalaryElementsEntityKey)currentDTO.getCode()).getEmpSalElmSerial();
                    Float selectedDTOValue = currentDTO.getSalElementGuidesDTO().getValue();
                    Long salDegreeReasonsCode = null;
                    if (currentDTO.getSalDegreeReasonsDTO() != null &&
                        currentDTO.getSalDegreeReasonsDTO().getCode() != null) {
                        salDegreeReasonsCode =
                                ((ISalDegreeReasonsEntityKey)currentDTO.getSalDegreeReasonsDTO().getCode()).getDegreasonCode();
                    }
                    List<ISalEmpSalaryElementsDTO> listSal = new ArrayList<ISalEmpSalaryElementsDTO>();
                    if (selectedDTOValue != null && !selectedDTOValue.equals(0F)) {
                        try {

                            listSal =
                                    (List)SalClientFactory.getSalEmpSalaryElementsClient().getAllActiveEmpSalaryElementForPrm(elmguideCode,
                                                                                                                              civilId,
                                                                                                                              currentDTO.getFromDate(),//ISalConstants.ELEMENT_GUIDE_TYPE_RAISE,
                                                                                                                              salDegreeReasonsCode);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        for (ISalEmpSalaryElementsDTO salDTO : listSal) {
                            EmpSalDetailsDTO dto = new EmpSalDetailsDTO();
                            if (salDTO.getElementValue() != null) {
                                Float raiseValue =
                                    salDTO.getElementValue() - currentDTO.getSalElementGuidesDTO().getValue();
                                dto.setElementValue(raiseValue);
                            }
                            dto.setFromDate(salDTO.getFromDate());
                            dto.setName(salDTO.getSalElementGuidesDTO().getName());
                            list.add(dto);
                        }

                    }
                }


            }
            return list;
        } catch (DataBaseException e) {
            return list;
        } catch (SharedApplicationException e) {
            return list;
        }
    }
    
    public boolean checkIfEmployeeExists(Long realCivilId) throws DataBaseException, SharedApplicationException {
        boolean result = false;
        try {
            result = employeesSession.checkIfEmployeeExists(RI(), realCivilId);
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
        return result;
    }
    /**
     * @author msayed
     * @since 14-5-2020
     * @param reqEmp
     * @return
     * @throws SharedApplicationException
     * @throws DataBaseException
     * @throws InvalidDataEntryException
     * @throws EmployeeNotHiredException
     * @throws CivilIDNotFoundException
     */
    public EmployeeDTO getAllEmployeeDataForProfileMob(EmployeeDTO reqEmp) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException {
        try {
            IEmployeeDTOService empDTO=employeesSession.getAllEmployeeDataForProfileMob(RI(),reqEmp.getRealCivilId());
            //convert data to reqemp DTO 
            reqEmp.setRealCivilId(empDTO.getRealCivilId());
            reqEmp.setEmployeeFullName(empDTO.getEmployeeFullName());
            reqEmp.setReligonName(empDTO.getReligonName());
            reqEmp.setGentypeName(empDTO.getGentypeName());
            reqEmp.setNationalityName(empDTO.getNationalityName());
            reqEmp.setMaritalStatusName(empDTO.getMaritalStatusName());
            reqEmp.setBirthDate(empDTO.getBirthDate());
            reqEmp.setQualName(empDTO.getQualName());
            reqEmp.setQualDate(empDTO.getQualDate());
            reqEmp.setMobileNo(empDTO.getMobileNo());
            reqEmp.setSendSMSFlag(empDTO.getSendSMSFlag());
            reqEmp.setBuildingNo(empDTO.getBuildingNo());
            reqEmp.setFloorNo(empDTO.getFloorNo());
            reqEmp.setFlatNo(empDTO.getFlatNo());
            reqEmp.setCityName(empDTO.getCityName());
            reqEmp.setStatusName(empDTO.getStatusName());
            reqEmp.setMinName(empDTO.getMinName());
            reqEmp.setWrkName(empDTO.getWrkName());
            reqEmp.setMinistryFileNo(empDTO.getMinistryFileNo());
            reqEmp.setHireDate(empDTO.getHireDate());
            reqEmp.setStartWorkingDate(empDTO.getStartWorkingDate());
            reqEmp.setDateOfNextRaise(empDTO.getDateOfNextRaise());
            reqEmp.setSalDegreeDate(empDTO.getSalDegreeDate());
            reqEmp.setJobName(empDTO.getJobName());
            reqEmp.setSalDegree(empDTO.getSalDegree());
            return reqEmp;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
    public EmployeeDTO getAllEmployeeDataForProfileMob2(Long civilID) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException {
        
                    try {
                        IEmployeeDTOService empDTO=employeesSession.getAllEmployeeDataForProfileMob(RI(),civilID);
                        //convert data to reqemp DTO 
                        EmployeeDTO reqEmp=new EmployeeDTO();
                        reqEmp.setRealCivilId(empDTO.getRealCivilId());
                        reqEmp.setEmployeeFullName(empDTO.getEmployeeFullName());
                        reqEmp.setReligonName(empDTO.getReligonName());
                        reqEmp.setGentypeName(empDTO.getGentypeName());
                        reqEmp.setNationalityName(empDTO.getNationalityName());
                        reqEmp.setMaritalStatusName(empDTO.getMaritalStatusName());
                        reqEmp.setBirthDate(empDTO.getBirthDate());
                        reqEmp.setQualName(empDTO.getQualName());
                        reqEmp.setQualDate(empDTO.getQualDate());
                        reqEmp.setMobileNo(empDTO.getMobileNo());
                        reqEmp.setEMail(empDTO.getEMail());
                        reqEmp.setSendSMSFlag(empDTO.getSendSMSFlag());
                        reqEmp.setBuildingNo(empDTO.getBuildingNo());
                        reqEmp.setFloorNo(empDTO.getFloorNo());
                        reqEmp.setFlatNo(empDTO.getFlatNo());
                        reqEmp.setCityName(empDTO.getCityName());
                        reqEmp.setStatusName(empDTO.getStatusName());
                        reqEmp.setMinName(empDTO.getMinName());
                        reqEmp.setWrkName(empDTO.getWrkName());
                        reqEmp.setMinistryFileNo(empDTO.getMinistryFileNo());
                        reqEmp.setHireDate(empDTO.getHireDate());
                        reqEmp.setStartWorkingDate(empDTO.getStartWorkingDate());
                        reqEmp.setDateOfNextRaise(empDTO.getDateOfNextRaise());
                        reqEmp.setSalDegreeDate(empDTO.getSalDegreeDate());
                        reqEmp.setJobName(empDTO.getJobName());
                        reqEmp.setSalDegree(empDTO.getSalDegree());
                        return reqEmp;
                    } catch (RemoteException e) {
                        throw new DataBaseException(e.getMessage());
                    }
        }
    
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
    public EmpFinicialDTO getEmpMertisAndDeducts(Long realCivilID,Long yearCode,Long monthCode)throws SharedApplicationException,DataBaseException{
        try {
            IEmpFinicialData empDTO=employeesSession.getMertisAndDeducts(RI(),realCivilID,yearCode,monthCode);
            EmpFinicialDTO resDTO=new EmpFinicialDTO();
            resDTO.setAccountCheckNo(empDTO.getAccountCheckNo());
            resDTO.setActualSalary(empDTO.getActualSalary());
            resDTO.setBankBranchCode(empDTO.getBankBranchCode());
            resDTO.setBankBranchName(empDTO.getBankBranchName());
            resDTO.setBankCode(empDTO.getBankCode());
            resDTO.setBankName(empDTO.getBankName());
            resDTO.setMinCode(empDTO.getMinCode());
            resDTO.setRealCivilID(empDTO.getRealCivilID());
            resDTO.setSalMonth(empDTO.getSalMonth());
            resDTO.setSalYear(empDTO.getSalYear());
            resDTO.setTotalDeducts(empDTO.getTotalDeducts());
            resDTO.setTotalMertis(empDTO.getTotalMertis());
            return resDTO;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
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
    public EmployeeDTO getAllEmployeeDataForProfileVW(Long civilID) throws SharedApplicationException,
                                                                               DataBaseException,
                                                                               InvalidDataEntryException,
                                                                               EmployeeNotHiredException,
                                                                               CivilIDNotFoundException {
        
                    try {
                        IEmployeeDTOService empDTO=employeesSession.getAllEmployeeDataForProfileMob(RI(),civilID);
                        //convert data to reqemp DTO 
                        EmployeeDTO reqEmp=new EmployeeDTO();
                        reqEmp.setRealCivilId(empDTO.getRealCivilId());
                        reqEmp.setEmployeeFullName(empDTO.getEmployeeFullName());
                        reqEmp.setReligonName(empDTO.getReligonName());
                        reqEmp.setGentypeName(empDTO.getGentypeName());
                        reqEmp.setNationalityName(empDTO.getNationalityName());
                        reqEmp.setMaritalStatusName(empDTO.getMaritalStatusName());
                        reqEmp.setBirthDate(empDTO.getBirthDate());
                        reqEmp.setQualName(empDTO.getQualName());
                        reqEmp.setQualDate(empDTO.getQualDate());
                        reqEmp.setMobileNo(empDTO.getMobileNo());
                        reqEmp.setEMail(empDTO.getEMail());
                        reqEmp.setSendSMSFlag(empDTO.getSendSMSFlag());
                        reqEmp.setBuildingNo(empDTO.getBuildingNo());
                        reqEmp.setFloorNo(empDTO.getFloorNo());
                        reqEmp.setFlatNo(empDTO.getFlatNo());
                        reqEmp.setCityName(empDTO.getCityName());
                        reqEmp.setStatusName(empDTO.getStatusName());
                        reqEmp.setMinName(empDTO.getMinName());
                        reqEmp.setWrkName(empDTO.getWrkName());
                        reqEmp.setMinistryFileNo(empDTO.getMinistryFileNo());
                        reqEmp.setHireDate(empDTO.getHireDate());
                        reqEmp.setStartWorkingDate(empDTO.getStartWorkingDate());
                        reqEmp.setDateOfNextRaise(empDTO.getDateOfNextRaise());
                        reqEmp.setSalDegreeDate(empDTO.getSalDegreeDate());
                        reqEmp.setJobName(empDTO.getJobName());
                        reqEmp.setSalDegree(empDTO.getSalDegree());
                        return reqEmp;
                    } catch (RemoteException e) {
                        throw new DataBaseException(e.getMessage());
                    }
        }
    
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
    public EmpFinicialDTO getEmpMertisAndDeductsVW(Long realCivilID,Long yearCode,Long monthCode)throws SharedApplicationException,DataBaseException{
        try {
            IEmpFinicialData empDTO=employeesSession.getMertisAndDeducts(RI(),realCivilID,yearCode,monthCode);
            EmpFinicialDTO resDTO=new EmpFinicialDTO();
            resDTO.setAccountCheckNo(empDTO.getAccountCheckNo());
            resDTO.setActualSalary(empDTO.getActualSalary());
            resDTO.setBankBranchCode(empDTO.getBankBranchCode());
            resDTO.setBankBranchName(empDTO.getBankBranchName());
            resDTO.setBankCode(empDTO.getBankCode());
            resDTO.setBankName(empDTO.getBankName());
            resDTO.setMinCode(empDTO.getMinCode());
            resDTO.setRealCivilID(empDTO.getRealCivilID());
            resDTO.setSalMonth(empDTO.getSalMonth());
            resDTO.setSalYear(empDTO.getSalYear());
            resDTO.setTotalDeducts(empDTO.getTotalDeducts());
            resDTO.setTotalMertis(empDTO.getTotalMertis());
            return resDTO;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }        

}

