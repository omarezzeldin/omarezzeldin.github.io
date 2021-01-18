package com.beshara.csc.hr.emp.presentation.backingbean.queryAboutEmployee;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredInMinException;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeePayrollInfoServerException;
import com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision.GovEmpMaintainBean;
import com.beshara.csc.hr.emp.integration.presentation.utils.EmployeeHelper;
import com.beshara.csc.hr.sal.business.shared.exception.EmployeePayrollInfoNotExistException;
import com.beshara.csc.inf.business.exception.EmployeeCivilIdNotExistException;
import com.beshara.csc.integration.hrm.emp.backingbean.EmployeeSearchBean;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;

import java.util.ArrayList;

public class QueryAboutEmployee extends LookUpBaseBean {

    @SuppressWarnings("compatibility:182504359434245558")
    private static final long serialVersionUID = 1L;
    private EmployeeSearchBean employeeSearchBean;
    private Long civilId;
    private boolean civilExist;
    private boolean validCivilId = true;
    private boolean empHired = true;
    private boolean empHiredInMin = true;
    private boolean payrollInfoExist = true;
    private boolean   payrollInfoServer=true;
    private IEmployeesDTO employeesDTO;

    private static final String BEAN_NAME = "queryAboutEmployee";
    private static final String NAVIGATION_KEY = "query_About_Employee";
    private static final String METHOD_NAME_RESTORE = "restorePage";
    private boolean enableEmpLovDiv;

    public QueryAboutEmployee() {
        setEmployeeSearchBean(new EmployeeSearchBean());
        setPageDTO(EmpDTOFactory.createEmployeesDTO());
        setSelectedPageDTO(EmpDTOFactory.createEmployeesDTO());
        setClient(EmpClientFactory.getEmployeesClient());
        if (getEmpListOfValues() == null)
            setEmpListOfValues(new EmployeeListOfValues());
        getEmpListOfValues().setMyTableData(new ArrayList());
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowEmpSrchDiv(true);
        app.setShowScirptGenerator(true);
        app.setShowbar(false);


        return app;
    }


    public String getEmpInfo() {
        setCivilExist(true);

        resetMessages();
        if (civilId != null) {
            try {
                employeesDTO = new EmployeeHelper().getHiredAndHavePayrollInfoEmpNew(Long.valueOf(civilId));
                civilExist = true;
                validCivilId = true;
            } catch (EmployeeNotHiredInMinException e) {
                empHiredInMin = false;
            } catch (EmployeeNotHiredException e) {
                empHired = false;
            } catch (EmployeePayrollInfoNotExistException e) {
                payrollInfoExist = false;
            } catch (EmployeePayrollInfoServerException e) {
                    payrollInfoServer = false;
            } catch (EmployeeCivilIdNotExistException e) {
                validCivilId = false;
            } catch (Exception e) {
                validCivilId = false;
            }
        }
        return null;
    }

    private void resetMessages() {
        empHired = true;
        empHiredInMin = true;
        payrollInfoExist = true;
        payrollInfoServer=true;
        validCivilId = true;
        civilExist = false;
    }


    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues empListOfValuesBean = ((EmployeeListOfValues)getEmpListOfValues());
        empListOfValuesBean.setEmpListOfValuesStyle("m2mEditDivClass");
        empListOfValuesBean.setReturnMethodName(BEAN_NAME + ".returnMethodAction");
        empListOfValuesBean.resetData();
        empListOfValuesBean.getOkCommandButton().setReRender("empInquiryCnt1Pnl,cntPanel,civilId,searchBtn,name,resetBtn,scriptPanelGn,scriptpanel,OperationBar,dataT_data_panel,paging_panel,emp_love_div_group,divMsg");
    }


    public String returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            validCivilId = true;
            civilExist = true;
            empHired = true;
            empHiredInMin = true;
            IEmployeesDTO employeesDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            //civilId = ((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId().toString();
            civilId = employeesDTO.getRealCivilId();
            enableEmpLovDiv = false;
            return getEmpInfo();

        }
        return null;
    }


    public void reSetData() {
        civilId = null;
        setCivilExist(false);
        employeesDTO = EmpDTOFactory.createEmployeesDTO();

    }


    /* added By M.abdelsabour */
    /////////// Apply Integration For Employee Data /////

    public String viewEmpDetails() {
        try {
            String civilId = employeesDTO.getCode().getKey();
            getIntegrationBean().reInitializeBean();
            getIntegrationBean().setBeanNameTo(GovEmpMaintainBean.BEAN_NAME);
            getIntegrationBean().setActionTo(GovEmpMaintainBean.METHOD_NAME_VIEW);
            getIntegrationBean().setNavgationCaseFrom(NAVIGATION_KEY);
            getIntegrationBean().getHmBaseBeanFrom().put(BEAN_NAME, evaluateValueBinding(BEAN_NAME));
            getIntegrationBean().setBeanNameFrom(BEAN_NAME);
            getIntegrationBean().setActionFrom(METHOD_NAME_RESTORE);
            getIntegrationBean().getHmObjects().put(GovEmpMaintainBean.MAP_KEY_CIVIL_ID, civilId);
            GovEmpMaintainBean govEmpMaintainBean = (GovEmpMaintainBean)evaluateValueBinding("govEmpMaintainBean");
            govEmpMaintainBean.setTitlePageKey("Gov_emp_data_revision_title");
            return getIntegrationBean().goTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String restorePage() {
        return NAVIGATION_KEY;
    }


    public void setEmployeeSearchBean(EmployeeSearchBean employeeSearchBean) {
        this.employeeSearchBean = employeeSearchBean;
    }

    public EmployeeSearchBean getEmployeeSearchBean() {
        return employeeSearchBean;
    }


    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
    }

    public void setEmpHired(boolean empHired) {
        this.empHired = empHired;
    }

    public boolean isEmpHired() {
        return empHired;
    }

    public void setEmpHiredInMin(boolean empHiredInMin) {
        this.empHiredInMin = empHiredInMin;
    }

    public boolean isEmpHiredInMin() {
        return empHiredInMin;
    }

    public void setPayrollInfoExist(boolean payrollInfoExist) {
        this.payrollInfoExist = payrollInfoExist;
    }

    public boolean isPayrollInfoExist() {
        return payrollInfoExist;
    }

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }


    public void setPayrollInfoServer(boolean payrollInfoServer) {
        this.payrollInfoServer = payrollInfoServer;
    }

    public boolean isPayrollInfoServer() {
        return payrollInfoServer;
    }
}

