package com.beshara.csc.hr.emp.presentation.backingbean.governmentalemployeedatarevision;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.Calendar;


public class SecondStepBean extends LookupMaintainBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "govEmpSecondStepBean";
    private static final String NAVIGATION_KEY = "govempsecondstep";
    
    private IPersonQualificationsDTO personQualificationsDTO = 
        InfDTOFactory.createPersonQualificationsDTO();
    private ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = 
        SalDTOFactory.createSalEmpSalaryElementsDTO();
    private IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
    
    private Date endJobDate;
    private Date firstHireDate;
    

    private final Long MAL_GENDER = ISystemConstant.GENDER_MALE;
    private final Long KW_NATIONALITY = ISystemConstant.KUWAIT_NATIONALITY;

    private String groupName;
    private String caderName;
    private String degreeName;
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

    public SecondStepBean() {
        setContent1Div("module_tabs_cont");
    }
    
    public static SecondStepBean getInstance(){
        return (SecondStepBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        return app;
    }


    public String init() {
        Long civilID = GovEmpMaintainBean.getEmpCivilId();
        try {
            personQualificationsDTO = (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getLastPersonQualification(maintainBean.getRealCivilId());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT 
            if(maintainBean.getMinistryCode() != null){
            salEmpSalaryElementsDTO =(ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient(maintainBean.getMinistryCode()).getEmpSalaryElementByCivilId(civilID).get(0);
            }
            else
            {
            salEmpSalaryElementsDTO =(ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpSalaryElementByCivilId(civilID).get(0);
            }
            ISalElementGuidesDTO salElementGuidesDTORaise = 
                SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO salElementGuidesDTODegree = 
                SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO salElementGuidesDTOGroup = 
                SalDTOFactory.createSalElementGuidesDTO();
            ISalElementGuidesDTO salElementGuidesDTOCader = 
                SalDTOFactory.createSalElementGuidesDTO();

            if (salEmpSalaryElementsDTO != null) {
                salElementGuidesDTORaise = 
                        salEmpSalaryElementsDTO.getSalElementGuidesDTO();
                setDegreeName(salElementGuidesDTORaise.getName());
            }
            if (salElementGuidesDTORaise != null) {
                salElementGuidesDTODegree = 
                        (ISalElementGuidesDTO)((ISalElementGuidesDTO)salElementGuidesDTORaise.getParentObject()).getParentObject();
            }
            if (salElementGuidesDTODegree != null) {
                salElementGuidesDTOGroup = 
                        (ISalElementGuidesDTO)salElementGuidesDTODegree.getParentObject();
                setGroupName(salElementGuidesDTOGroup.getName());
            }
            if (salElementGuidesDTOGroup != null) {
                salElementGuidesDTOCader = 
                        (ISalElementGuidesDTO)salElementGuidesDTOGroup.getParentObject();
                setCaderName(salElementGuidesDTOCader.getName());
            }


        } catch (Throwable e) {
            e.printStackTrace();
        }


        try {
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT 
                if(maintainBean.getMinistryCode() != null){
                employeesDTO =(IEmployeesDTO)EmpClientFactory.getEmployeesClient(maintainBean.getMinistryCode()).getEmployeByCivilId(civilID).get(0);
                }
                else
                {
                employeesDTO =(IEmployeesDTO)EmpClientFactory.getEmployeesClient().getEmployeByCivilId(civilID).get(0);
                }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        firstHireDate = getEmpFirstHireDate();
        if (employeesDTO != null && 
            employeesDTO.getCitizensResidentsDTO() != null && 
            ((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGenderTypesDTO() != null){
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO();
            if (kwtCitizensResidentsDTO.getGenderTypesDTO().getGentypeCode().equals(MAL_GENDER) && 
                kwtCitizensResidentsDTO.getNationality().equals(KW_NATIONALITY)) {    
                endJobDate=calculateJobEndDate(kwtCitizensResidentsDTO.getBirthDate(),65);
            } else {
                endJobDate =calculateJobEndDate(kwtCitizensResidentsDTO.getBirthDate(),60);
            }
        }
        return NAVIGATION_KEY;
    }
    
    public void getAll() throws DataBaseException {
    }

    private Date getEmpFirstHireDate() {
        Date firstHireDate = null;
        try {
            firstHireDate = (Date)EmpClientFactory.getEmpEmployeeHistoriesClient().getFirstHireDate(maintainBean.getRealCivilId());
        } catch (Throwable e) {
            System.err.println(e.getMessage());
        }

        return (firstHireDate != null ? 
                new java.sql.Date(firstHireDate.getTime()) : null);
    }

    public Date calculateJobEndDate(Timestamp date, int noOfYears) {
        if(date != null){
            Calendar calendar = Calendar.getInstance();
            Date birthDate = new Date(date.getTime());
            calendar.setTime(birthDate);
            calendar.add(Calendar.YEAR, noOfYears);
            java.util.Date dateUtil = calendar.getTime();
            return new Date(dateUtil.getTime());
        }
        return null;
    }

    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO) {
        this.personQualificationsDTO = personQualificationsDTO;
    }

    public IPersonQualificationsDTO getPersonQualificationsDTO() {
        return personQualificationsDTO;
    }

    public void setSalEmpSalaryElementsDTO(ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO) {
        this.salEmpSalaryElementsDTO = salEmpSalaryElementsDTO;
    }

    public ISalEmpSalaryElementsDTO getSalEmpSalaryElementsDTO() {
        return salEmpSalaryElementsDTO;
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setEndJobDate(Date endJobDate) {
        this.endJobDate = endJobDate;
    }

    public Date getEndJobDate() {
        return endJobDate;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setCaderName(String caderName) {
        this.caderName = caderName;
    }

    public String getCaderName() {
        return caderName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setFirstHireDate(Date firstHireDate) {
        this.firstHireDate = firstHireDate;
    }

    public Date getFirstHireDate() {
        return firstHireDate;
    }
}

