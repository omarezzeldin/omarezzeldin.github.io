package com.beshara.csc.hr.emp.presentation.backingbean.empexecute;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeHiredBeforeException;
import com.beshara.csc.hr.emp.business.shared.exception.ExperienceInsertionException;
import com.beshara.csc.hr.emp.business.shared.exception.ExtraDataInsertionException;
import com.beshara.csc.hr.emp.business.shared.exception.SalElementInsertionException;
import com.beshara.csc.hr.emp.business.shared.exception.VacInsertionException;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.sal.exception.InValidSalEmpSalaryElementException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class EmpExecuteBean extends LookupMaintainBaseBean {

    private static final String BEAN_NAME = "empExecuteBean";
    private static final String NAVIGATION_KEY = "empexecute";

    private static final String EMP_EXECUTE_LIST_NAVIGATION = "empexecutelist";

    IEmpCndSalaryElementsDTO salaryElementDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();

    private IPersonQualificationsDTO personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
    private Long countGuid;
    private String raiseName;
    private boolean isCaseOne;
    private Long CaderCode;
    private String caderName;

    private Long groupCode;
    private String GroupName;

    private Long degreeCode;
    private String degreeName;
    private int kwuaity = 0;
    private int raiseIndicator = 0;
    private boolean selectedAllHireTypes;
    private Date nextYearValue;
    private String contractType;
    private String firstDegreeName;
    private boolean centralEmphireType;
    private boolean enableDateOfNextRaise;
    private boolean renderEmpFilNumRedundant ;
    private boolean renderCandFilNumRedundant ;
   

    public EmpExecuteBean() {
        setPageDTO(EmpDTOFactory.createEmployeesDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createEmployeesDTO());
        setClient(EmpClientFactory.getEmployeesClient());
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setShowNavigation(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public static EmpExecuteBean getInstance() {
        return (EmpExecuteBean)JSFHelper.getValue(BEAN_NAME);
    }

    public static String getPageNavigationKey() {
        return NAVIGATION_KEY;
    }


    public String empExecute() {

        EmpExecuteListBean empExecuteListBean = (EmpExecuteListBean)evaluateValueBinding("empExecuteListBean");

        SharedUtilBean shared_util = getSharedUtil();

        try {

            if (!insureLocked()) {
                return EMP_EXECUTE_LIST_NAVIGATION;
            }

            boolean executed = EmpClientFactory.getEmployeesCMTClient().executeStartWorkCMT(getPageDTO());
            if (executed) {

                if (((IKwtCitizensResidentsDTO)((IEmployeesDTO)getPageDTO()).getCitizensResidentsDTO()).getNationality().equals(IEMPConstants.EMP_NATIONALITY_KWT)) {
                    getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                  "empExecuteMSG");
                } else {
                    getSharedUtil().handleSuccMsg("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                  "SuccesExecution");
                }

                empExecuteListBean.unlock();

                empExecuteListBean.setSelectedRadio("");
                empExecuteListBean.setSelectedDTOS(new ArrayList<IBasicDTO>());

                if (selectedAllHireTypes) {
                    ((IEmployeesDTO)empExecuteListBean.getPageDTO()).setHireTypeKey("0");
                } else {
                    ((IEmployeesDTO)empExecuteListBean.getPageDTO()).setHireTypeKey(((IEmployeesDTO)getPageDTO()).getHireTypeKey());
                }


                empExecuteListBean.filterByHireType();

                return EMP_EXECUTE_LIST_NAVIGATION;
            } else {
                this.setShowErrorMsg(true);
                this.setErrorMsg("FailureInUpdate");
            }
        } catch (InValidSalEmpSalaryElementException e) {
            getSharedUtil().handleException(e, "com.beshara.csc.hr.emp.presentation.resources.emp",
                                            "InValidSalEmpSalaryElementException");
            this.setShowErrorMsg(true);
        } catch (InvalidDataEntryException e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "InvalidDataEntryException");
        } catch (DataBaseException e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInUpdate");
            shared_util.setErrMsgValue("FailureInUpdate");
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInUpdate");
        } catch (Exception e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInUpdate");
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "FailureInUpdate");
        }

        return null;
    }


    public String preExecution() {
        /// validation
        if(renderEmpFilNumRedundant || renderCandFilNumRedundant){
            return null ;
        }
        
        
        IEmpCandidatesClient client = EmpClientFactory.getEmpCandidatesClient();
        Long nationality =
            ((IKwtCitizensResidentsDTO)(((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO())).getNationality();
        try {
            if (client.validateChildRaise((IEmpCandidatesDTO)getPageDTO()) &&
                nationality.equals(IEMPConstants.KUWAITY)) {
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setRaiseIndicator(1);
                setKwuaity(1);
                return "";
            } else if (client.validateChildRaise((IEmpCandidatesDTO)getPageDTO())) {
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setRaiseIndicator(1);
                setKwuaity(0);
                return "";
            } else if (nationality.equals(IEMPConstants.KUWAITY)) {
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
                setRaiseIndicator(0);
                setKwuaity(1);
                return "";

            } else {
                return executeEmployment();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String executeEmployment() {

        boolean isExecuted = false;
        try {
            isExecuted = EmpClientFactory.getEmpCandidatesClient().executeEmployment(getPageDTO());
        } catch (DataBaseException e) {
            e.printStackTrace();
            getSharedUtil().handleException(e);
        } catch (SharedApplicationException e) {
            if (e instanceof EmployeeHiredBeforeException) {
                System.out.println("Execution failed >>> EmployeeHiredBeforeException");
                String employeeHiredBeforeException =
                    getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                   "EmployeeHiredBeforeException");
                getSharedUtil().setErrMsgValue(employeeHiredBeforeException);
                return "";
            }
            if (e instanceof InvalidDataEntryException) {
                System.out.println("Execution failed >>> invalidDataEntryExceptionAOE");
                String invalidDataEntryException =
                    getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                   "aoe_Award_spend");
                getSharedUtil().setErrMsgValue(invalidDataEntryException);
                return "";
            }
            if (e instanceof VacInsertionException) {
                System.out.println("Execution failed >>> vacInsertionException");
                String vacInsertionException =
                    getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                   "VacInsertionException");
                getSharedUtil().setErrMsgValue(vacInsertionException);
                return "";
            }
            if (e instanceof SalElementInsertionException) {
                System.out.println("Execution failed >>> SalElementInsertionException");
                String salElementInsertionException =
                    getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                   "SalElementInsertionException");
                getSharedUtil().setErrMsgValue(salElementInsertionException);
                return "";
            }
            if (e instanceof ExtraDataInsertionException) {
                System.out.println("Execution failed >>> ExtraDataInsertionException");
                String extraDataInsertionException =
                    getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                   "ExtraDataInsertionException");
                getSharedUtil().setErrMsgValue(extraDataInsertionException);
                return "";
            }
            if (e instanceof ExperienceInsertionException) {
                System.out.println("Execution failed >>> ExperienceInsertionException");
                String experienceInsertionException =
                    getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp",
                                                   "ExperienceInsertionException");
                getSharedUtil().setErrMsgValue(experienceInsertionException);
                return "";
            }
        }
        if (isExecuted) {
            System.out.println("Execution Done");
            String executionSuccessMsg =
                getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "executionDone");
            getSharedUtil().setSuccessMsgValue(executionSuccessMsg);
        } else {
            System.out.println("Execution failed ");
            String executionFailed =
                getSharedUtil().messageLocator("com.beshara.csc.hr.emp.presentation.resources.emp", "executionFailed");
            getSharedUtil().setErrMsgValue(executionFailed);
            return "";
        }
        return EMP_EXECUTE_LIST_NAVIGATION;
    }

    public void setSalaryElementDTO(IEmpCndSalaryElementsDTO salaryElementDTO) {
        this.salaryElementDTO = salaryElementDTO;
    }

    public IEmpCndSalaryElementsDTO getSalaryElementDTO() {
        return salaryElementDTO;
    }


    public void setPersonQulDTO(IPersonQualificationsDTO personQulDTO) {
        this.personQulDTO = personQulDTO;
    }

    public IPersonQualificationsDTO getPersonQulDTO() {
        return personQulDTO;
    }

    public String goBack() {
        EmpExecuteListBean empExecuteListBean = (EmpExecuteListBean)evaluateValueBinding("empExecuteListBean");
        empExecuteListBean.unlock();

        empExecuteListBean.setSelectedRadio("");
        empExecuteListBean.setSelectedDTOS(new ArrayList<IBasicDTO>());

        //        if (selectedAllHireTypes) {
        //            (empExecuteListBean.getPageDTO()).setHireTypeKey("0");
        //        } else {
        //            ((IEmployeesDTO)empExecuteListBean.getPageDTO()).setHireTypeKey(((IEmployeesDTO)getPageDTO()).getHireTypeKey());
        //        }
        empExecuteListBean.filterByHireType();

        return EMP_EXECUTE_LIST_NAVIGATION;
    }

    public void setSelectedAllHireTypes(boolean selectedAllHireTypes) {
        this.selectedAllHireTypes = selectedAllHireTypes;
    }

    public boolean isSelectedAllHireTypes() {
        return selectedAllHireTypes;
    }

    public void setNextYearValue(Date nextYearValue) {
        this.nextYearValue = nextYearValue;
    }

    public Date getNextYearValue() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return cal.getTime();
    }

    public void setCountGuid(Long countGuid) {
        this.countGuid = countGuid;
    }

    public Long getCountGuid() {
        return countGuid;
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

    public void setRaiseName(String raiseName) {
        this.raiseName = raiseName;
    }

    public String getRaiseName() {
        return raiseName;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return contractType;
    }

    public void setIsCaseOne(boolean isCaseOne) {
        this.isCaseOne = isCaseOne;
    }

    public boolean isIsCaseOne() {
        return isCaseOne;
    }

    public void setKwuaity(int kwuaity) {
        this.kwuaity = kwuaity;
    }

    public int getKwuaity() {
        return kwuaity;
    }

    public void setRaiseIndicator(int raiseIndicator) {
        this.raiseIndicator = raiseIndicator;
    }

    public int getRaiseIndicator() {
        return raiseIndicator;
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
    
    
    public void checkAboutFilNum() {
         int count = 0;
         String fileNo = ((IEmpCandidatesDTO)getPageDTO()).getMinistryFileNo();
         Long realCivil =Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
         if (fileNo != null && !fileNo.trim().isEmpty()) {
             try {
                 
                 count =
                         EmpClientFactory.getEmpCandidatesClient().isFilNumRedundant(CSCSecurityInfoHelper.getLoggedInMinistryCode(),
                                                                                     fileNo, realCivil);
             } catch (DataBaseException e) {
                 e.printStackTrace();
             } catch (SharedApplicationException e) {
                 e.printStackTrace();
             }
             if (count == 1) {
                 renderEmpFilNumRedundant = true;
                 renderCandFilNumRedundant = false;
             } else if (count == 2) {
                 renderEmpFilNumRedundant = false;
                 renderCandFilNumRedundant = true;
             } else {
                 renderEmpFilNumRedundant = false;
                 renderCandFilNumRedundant = false;
             }
         } else {
             renderEmpFilNumRedundant = false;
             renderCandFilNumRedundant = false;
         }

     }

    public void setCentralEmphireType(boolean centralEmphireType) {
        this.centralEmphireType = centralEmphireType;
    }

    public boolean isCentralEmphireType() {
        return centralEmphireType;
    }

    public void setEnableDateOfNextRaise(boolean enableDateOfNextRaise) {
        this.enableDateOfNextRaise = enableDateOfNextRaise;
    }

    public boolean isEnableDateOfNextRaise() {
        return enableDateOfNextRaise;
    }

    public void setRenderEmpFilNumRedundant(boolean renderEmpFilNumRedundant) {
        this.renderEmpFilNumRedundant = renderEmpFilNumRedundant;
    }

    public boolean isRenderEmpFilNumRedundant() {
        return renderEmpFilNumRedundant;
    }

    public void setRenderCandFilNumRedundant(boolean renderCandFilNumRedundant) {
        this.renderCandFilNumRedundant = renderCandFilNumRedundant;
    }

    public boolean isRenderCandFilNumRedundant() {
        return renderCandFilNumRedundant;
    }

  
}
