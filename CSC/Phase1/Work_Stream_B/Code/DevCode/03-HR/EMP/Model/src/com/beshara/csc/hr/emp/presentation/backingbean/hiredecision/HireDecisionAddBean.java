package com.beshara.csc.hr.emp.presentation.backingbean.hiredecision;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HireDecisionAddBean extends LookupMaintainBaseBean {
    private IPersonQualificationsDTO personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
    private static final String BEAN_NAME = "hireDecisionAddBean";
    private final Long KWT_CITIZEN = Long.valueOf(101);
    private Long countGuid;
    private String raiseName;
    private boolean isCaseOne;
    private Long CaderCode;
    private String caderName;

    private Long groupCode;
    private String GroupName;

    private Long degreeCode;
    private String degreeName;
    IEmpCndSalaryElementsDTO salaryElementDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
    private String firstDegreeName;
    private boolean invalidNextYear;
    private Date dateOfNextRaise;
    private boolean renderErrorHireDate = false;
    private Date nextYear;
    private Date previousYear;
    private boolean kuwaitCitizen;

    public HireDecisionAddBean() {
        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    public static HireDecisionAddBean getInstance() {
        return (HireDecisionAddBean)JSFHelper.getValue(BEAN_NAME);
    }


    public String goEmpExecute() {


        Long firstParentHireTypeCode = null;
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            IEmpCandidatesDTO emp = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
            List personQulList = null;
            IKwtCitizensResidentsDTO kwtCitizenDTO = null;
            try {
                Long civilId = Long.valueOf(emp.getCitizensResidentsDTO().getCode().getKey());
                kwtCitizenDTO =
                        (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInfoForEmp(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId));
                ((IEmpCandidatesDTO)getPageDTO()).setCitizensResidentsDTO(kwtCitizenDTO);

                List personalQulList = new ArrayList();
                IPersonQualificationsDTO persontDTO =
                    (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getPersonQulificationInfo(civilId);
                personalQulList.add(persontDTO);
                personQulList = personalQulList;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (personQulList != null && personQulList.size() > 0)
                personQulDTO = (IPersonQualificationsDTO)personQulList.get(0);

            setPageDTO(emp);
            firstParentHireTypeCode = Long.valueOf(((IHireTypesDTO)emp.getHireTypesDTO()).getFirstParent().getKey());


            fillSalaryElementDTO(emp);
        }


        return "hireDecisionAddList";
    }

    public String excuteHire() {
        
  
        if (!checkaboutHireDateForExecute()) {
            if (isRenderErrorHireDate()) {
                String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "renderErrorHireDate");
                getSharedUtil().setErrMsgValue(message);
                return "";
            } else if (isInvalidNextYear()) {
                return "";
            }
        }
        
        HireDecisionListBean hireDecisionListBean = HireDecisionListBean.getInstance();
        hireDecisionListBean.excuteHire();
        return "hiredecisionListFromIntegration";
    }

    public String goBack() {

        return "hiredecisionListFromIntegration";
    }

    private IEmpCndSalaryElementsDTO fillSalaryElementDTO(IEmpCandidatesDTO emp) {
        ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
        IEmpCndSalaryElementsDTO salaryElementDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
        if (salaryElementDTO.getCode() == null) {
            try {
                salaryElementDTO = EmpClientFactory.getEmpCndSalaryElementsClient().getByCandCode(emp.getCode());
                setCountGuid(salaryElementDTO.getSalElementGuidesDTO().getCountGuide());
                String raiseCode =
                    ((ISalElementGuidesEntityKey)salaryElementDTO.getSalElementGuidesDTO().getCode()).getElmguideCode().toString();
                if (salaryElementDTO != null && salaryElementDTO.getCode() != null) {
                    if (raiseCode != null) {
                        raiseDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.valueOf(raiseCode));
                    }

                    //Degree
                    if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                        setDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                        setDegreeName(raiseDTO.getParentObject().getName());
                    }
                    if (getDegreeCode() != null) {
                        degreeDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(getDegreeCode());
                    }
                    //Group
                    if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                        setGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                        setGroupName(degreeDTO.getParentObject().getName());
                    }
                    if (getGroupCode() != null) {
                        groupDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(getGroupCode());
                    }
                    //Cader
                    if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                        setCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                        setCaderName(groupDTO.getParentObject().getName());
                    }
                    setSalaryElementDTO(salaryElementDTO);
                }
            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            } catch (SharedApplicationException e) {
                System.out.println(e.getMessage());
            }
        }
        return salaryElementDTO;
    }

    public void setPersonQulDTO(IPersonQualificationsDTO personQulDTO) {
        this.personQulDTO = personQulDTO;
    }

    public IPersonQualificationsDTO getPersonQulDTO() {
        return personQulDTO;
    }

    public void setCountGuid(Long countGuid) {
        this.countGuid = countGuid;
    }

    public Long getCountGuid() {
        return countGuid;
    }

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getRaiseName() {
        return raiseName;
    }

    public void setIsCaseOne(boolean isCaseOne) {
        this.isCaseOne = isCaseOne;
    }

    public boolean isIsCaseOne() {
        return isCaseOne;
    }

    public void setCaderCode(Long CaderCode) {
        this.CaderCode = CaderCode;
    }

    public Long getCaderCode() {
        return CaderCode;
    }

    public void setCaderName(String caderName) {
        this.caderName = caderName;
    }

    public String getCaderName() {
        return caderName;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Long getGroupCode() {
        return groupCode;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setDegreeCode(Long degreeCode) {
        this.degreeCode = degreeCode;
    }

    public Long getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setSalaryElementDTO(IEmpCndSalaryElementsDTO salaryElementDTO) {
        this.salaryElementDTO = salaryElementDTO;
    }

    public IEmpCndSalaryElementsDTO getSalaryElementDTO() {
        return salaryElementDTO;
    }

    public void setFirstDegreeName(String firstDegreeName) {
        this.firstDegreeName = firstDegreeName;
    }

    public String getFirstDegreeName() {
        if (getSalaryElementDTO().getCode() != null) {
            String name = getSalaryElementDTO().getSalElementGuidesDTO().getFullName();
            if (name != null && name != "") {
                int firstSpaceIndex = name.lastIndexOf("/");
                firstDegreeName = name.substring(firstSpaceIndex + 1);
            }
        }
        return firstDegreeName;
    }

    public boolean checkaboutHireDateForExecute() {
        setInvalidNextYear(false);
        IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)getPageDTO();
        Long count = 1L;
        Long candidateCode=((IEmpCandidatesEntityKey)empCandidateDTO.getCode()).getCandidateCode();
        try {
            count = ((IEmpCandidatesClient)getClient()).getHirDateCount(candidateCode);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        Date hireDate = empCandidateDTO.getHireDate();
        Long cntryCode = ((IKwtCitizensResidentsDTO)empCandidateDTO.getCitizensResidentsDTO()).getNationality();
        if (hireDate != null) {
            if (cntryCode.equals(101L))
                setKuwaitCitizen(true);
            else
                setKuwaitCitizen(false);
            if(count == 0L){
            if (compareHireDateWithNextYear(hireDate) || compareHireDateWithPreviousYear(hireDate)) {
                setInvalidNextYear(true);
                setDateOfNextRaise(null);
                return false;
            } 
            }
            if (checkAboutHireDateAndCivilId(empCandidateDTO)) {
                setRenderErrorHireDate(true);
                setDateOfNextRaise(null);
                return false;
            } else {
                setRenderErrorHireDate(false);
                setInvalidNextYear(false);
                calculateNextDateOfRaise();
                return true;
            }
        } else {
            setRenderErrorHireDate(false);
            setInvalidNextYear(false);
            setDateOfNextRaise(null);
        }
        return false;
    }
    
    public void checkaboutHireDate() {
        setInvalidNextYear(false);
        IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)getPageDTO();
        Long count = 1L;
        Long candidateCode=((IEmpCandidatesEntityKey)empCandidateDTO.getCode()).getCandidateCode();
        try {
            count = ((IEmpCandidatesClient)getClient()).getHirDateCount(candidateCode);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
      
        Date hireDate = empCandidateDTO.getHireDate();
        Long cntryCode = ((IKwtCitizensResidentsDTO)empCandidateDTO.getCitizensResidentsDTO()).getNationality();
        
        
        if (hireDate != null) {
            if (cntryCode.equals(101L))
                setKuwaitCitizen(true);
            else
                setKuwaitCitizen(false);
            if(count == 0L ){
            if ( compareHireDateWithNextYear(hireDate) || compareHireDateWithPreviousYear(hireDate)) {
                setInvalidNextYear(true);
                setDateOfNextRaise(null);
            }} 
            if (checkAboutHireDateAndCivilId(empCandidateDTO)) {
                setRenderErrorHireDate(true);
                setDateOfNextRaise(null);
            } else {
                setRenderErrorHireDate(false);
                setInvalidNextYear(false);
                calculateNextDateOfRaise();
            }
        } else {
            setRenderErrorHireDate(false);
            setInvalidNextYear(false);
            setDateOfNextRaise(null);
        }
       
    }

    private boolean compareHireDateWithNextYear(Date hireDate) {
        // TODO compare hireDate with getNextYear
        if (hireDate.compareTo(getNextYear()) > 0) {
            return true;
        }

        else {
            return false;
        }
    }

    private boolean compareHireDateWithPreviousYear(Date hireDate) {
        Date preYear = getPreviousYear();
        if (hireDate.getYear() >= preYear.getYear())
            if (hireDate.getMonth() >= preYear.getMonth())
                if (hireDate.getDate() >= preYear.getDate())
                    return false;
        return true;
    }

    private boolean checkAboutHireDateAndCivilId(IEmpCandidatesDTO empCandidateDTO) {
        boolean found = false;
        try {
            found = InfClientFactory.getKwtWorkDataClient().checkAboutHireDate(empCandidateDTO);
        } catch (DataBaseException e) {
            e.printStackTrace();
            found = false;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            found = false;
        }
        return found;
    }

    public void setInvalidNextYear(boolean invalidNextYear) {
        this.invalidNextYear = invalidNextYear;
    }

    public boolean isInvalidNextYear() {
        return invalidNextYear;
    }

    public void setDateOfNextRaise(Date dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    public Date getDateOfNextRaise() {

        return dateOfNextRaise;
    }

    public void calculateNextDateOfRaise() {
        IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)getPageDTO();
        Date empHireDate = empCandidateDTO.getHireDate();
        if (empHireDate != null) {
            try {
                setDateOfNextRaise(EmpClientFactory.getEmployeesClient().calculateNextRaiseDate(empHireDate));
                empCandidateDTO.setDateOfNextRaise(dateOfNextRaise);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setDateOfNextRaise(null);
            empCandidateDTO.setDateOfNextRaise(null);
        }
        System.out.println("Next Raise of Date>>>>>>>" + dateOfNextRaise);
    }

    public void setRenderErrorHireDate(boolean renderErrorHireDate) {
        this.renderErrorHireDate = renderErrorHireDate;
    }

    public boolean isRenderErrorHireDate() {
        return renderErrorHireDate;
    }

    public void setNextYear(Date nextYear) {
        this.nextYear = nextYear;
    }

    public Date getNextYear() {
        return storeNextYearFromNow();
    }

    public void setPreviousYear(Date previousYear) {
        this.previousYear = previousYear;
    }

    public Date getPreviousYear() {
        return storePreviousYearFromNow();
    }

    public Date storeNextYearFromNow() {
        java.util.Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1); // to get previous year add -1
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        Date nextYear = new java.sql.Date(cal.getTime().getTime());

        return nextYear;
    }

    public Date storePreviousYearFromNow() {
        java.util.Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date preYear = new java.sql.Date(cal.getTime().getTime());
        return preYear;
    }

    public void setKuwaitCitizen(boolean kuwaitCitizen) {
        this.kuwaitCitizen = kuwaitCitizen;
    }

    public boolean isKuwaitCitizen() {
        return kuwaitCitizen;
    }


}


