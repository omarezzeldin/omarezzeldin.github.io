package com.beshara.csc.hr.emp.presentation.backingbean.employeequery;

import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.List;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;


public class ListBean extends LookupMaintainBaseBean {
    private String civilId;
    private boolean validCivilId = true;
    private boolean civilExist = false;
    private boolean empHired = true;
    private boolean payrollInfoExist = true;
    private static final Long MINISTRY_CODE = 13L;
    private ISalEmpSalaryElementsDTO salaryElementDTO;
    private IPersonQualificationsDTO personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
    private boolean outerModule=false;
    public ListBean() {
        setPageDTO(EmpDTOFactory.createEmployeesDTO());
        if (((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("employeeCivilId") != null) {
            civilId = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("employeeCivilId");
            outerModule = true;
            filterByCivilId();
        } else
            outerModule = false;
           
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app =super.appMainLayoutBuilder();
        //app.setShowNavigation(true);
        return app;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
    }

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setEmpHired(boolean empHired) {
        this.empHired = empHired;
    }

    public boolean isEmpHired() {
        return empHired;
    }

    public void setPayrollInfoExist(boolean payrollInfoExist) {
        this.payrollInfoExist = payrollInfoExist;
    }

    public boolean isPayrollInfoExist() {
        return payrollInfoExist;
    }
    
    public void filterByCivilId() {
    
        if (civilId != null && !civilId.equals("")) {
            empHired = true;
            payrollInfoExist = true;
            getHighlightedDTOS().clear();
            if (GlobalValidation.isValidCivilId(Long.parseLong(civilId))) {
                validCivilId = true;
                civilExist = true;
                IEmployeesEntityKey empKey = EmpEntityKeyFactory.createEmployeesEntityKey(Long.parseLong(civilId));
                try {

                    empHired =  EmpClientFactory.getEmployeesClient().isEmployeeHired(empKey);

                } catch (SharedApplicationException e) {
                    empHired = false;
                    civilExist = false;
                    e.printStackTrace();
                } catch (DataBaseException e) {
                    empHired = false;
                    civilExist = false;
                    e.printStackTrace();
                } catch (Exception e) {
                    empHired = false;
                    civilExist = false;
                    e.printStackTrace();
                }

//                if (empHired) {
//                    Boolean employeeInMinistry = true;
//                    try {
//                        employeeInMinistry =  EmpClientFactory.getEmployeesClient().isEmployeeInMinistry(Long.parseLong(civilId),MINISTRY_CODE);
//                    } catch (DataBaseException e) {
//                        e.printStackTrace();
//                    }

                    if (empHired) {
                        try {
                            setPageDTO((IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeeInfoByElmType(Long.parseLong(civilId)));
                            List personQulList = null;

                            if (((IEmployeesDTO)getPageDTO()).getCitizensResidentsDTO() != 
                                null) {
                                try {
                                    ((IEmployeesDTO)getPageDTO()).setCitizensResidentsDTO(InfClientFactory.getKwtCitizensResidentsClient().getCitizenInfoForEmp(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(Long.valueOf(civilId))));
                                     personQulList = ((IKwtCitizensResidentsDTO)((IEmployeesDTO)getPageDTO()).getCitizensResidentsDTO()).getPersonQualificationsDTOList();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            
                            if (personQulList != null && personQulList.size() > 0)
                                personQulDTO =  (IPersonQualificationsDTO)personQulList.get(0);

                        } catch (SharedApplicationException e) {
                            civilExist = false;
                            payrollInfoExist = false;
                            e.printStackTrace();
                        } catch (Exception e) {
                            civilExist = false;
                            payrollInfoExist = false;
                            e.printStackTrace();
                        }
                        
                        try {                
                            salaryElementDTO = (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(Long.parseLong(civilId),ISalConstants.ELEMENT_GUIDE_TYPE_RAISE);
                        } catch (DataBaseException e) {
                            e.printStackTrace();
                        }

                    }
            } else {
                validCivilId = false;
                civilExist = false;
//                ((SalEmpDedAllSalariesDTO)getPageDTO()).setEmployeesDTO(new EmployeesDTO());
            }
        }
    }
    
    
 public void reSetData() {
     setPageDTO(EmpDTOFactory.createEmployeesDTO());
     setCivilId(null);
     validCivilId = true;
     civilExist = false;
     empHired = true;
     salaryElementDTO=null;
     personQulDTO= InfDTOFactory.createPersonQualificationsDTO();
 }

    public void setSalaryElementDTO(ISalEmpSalaryElementsDTO salaryElementDTO) {
        this.salaryElementDTO = salaryElementDTO;
    }

    public ISalEmpSalaryElementsDTO getSalaryElementDTO() {
        return salaryElementDTO;
    }

    public void setPersonQulDTO(IPersonQualificationsDTO personQulDTO) {
        this.personQulDTO = personQulDTO;
    }

    public IPersonQualificationsDTO getPersonQulDTO() {
        return personQulDTO;
    }

    public void setOuterModule(boolean outerModule) {
        this.outerModule = outerModule;
    }

    public boolean isOuterModule() {
        return outerModule;
    }
}



