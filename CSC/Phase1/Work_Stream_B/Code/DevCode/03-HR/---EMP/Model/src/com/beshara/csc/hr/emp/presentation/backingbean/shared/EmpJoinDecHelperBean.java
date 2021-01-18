package com.beshara.csc.hr.emp.presentation.backingbean.shared;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.deploy.EmpCandidatesJoinDecHelper;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.presentation.backingbean.hiredecision.HireDecisionListBean;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.integration.business.joindec.IJoinDecTargetDTO;
import com.beshara.csc.nl.reg.integration.presentation.backingbean.joindec.JoinDecHelperBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;


///// new EmpJoinDecHelperBean

public class EmpJoinDecHelperBean extends JoinDecHelperBean {
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private String beanName;
    private String selectedDTOBinding;

    public static EmpJoinDecHelperBean getInstance(String beanName, String selectedDTOBinding) {
        return new EmpJoinDecHelperBean(beanName, selectedDTOBinding);
    }

    private EmpJoinDecHelperBean(String beanName, String selectedDTOBinding) {
        super();
        this.beanName = beanName;
        this.selectedDTOBinding = selectedDTOBinding;

        setSearchInCandidate(true);
    }


    @Override
    protected String getContainerBeanName() {
        return beanName;
    }

    @Override
    protected IJoinDecTargetDTO getTarget() {
        IJoinDecTargetDTO target = null;
        if (getSelectedDTOS().size() == 1) {

            try {
                target =
                        (IJoinDecTargetDTO)EmpClientFactory.getEmpCandidatesClient().getById(getSelectedDTOS().get(0).getCode());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return target;

    }

    public IBasicDTO getSelectedDTO() {
        return (IBasicDTO)JSFHelper.getValue(selectedDTOBinding);
    }

    //    @Override
    //    protected List<Long> getEmpCivilIdList() {
    //        Long civilId = null;
    //        if(((IEmpCandidatesDTO)getSelectedDTO()).getEmployeesDTO() != null){
    //            civilId = ((IEmployeesEntityKey)((IEmpCandidatesDTO)getSelectedDTO()).getEmployeesDTO().getCode()).getCivilId();
    //        } else if(((IEmpCandidatesDTO)getSelectedDTO()).getCitizensResidentsDTO() !=null ){
    //            civilId = ((IKwtCitizensResidentsEntityKey)((IEmpCandidatesDTO)getSelectedDTO()).getCitizensResidentsDTO().getCode()).getCivilId();
    //        }
    //        List<Long> empCivilIdList = new ArrayList<Long>(1);
    //        empCivilIdList.add(civilId);
    //        return empCivilIdList;
    //    }

    @Override
    protected List<Long> getRelTabrecSerialList() {
        return null;
    }

    @Override
    protected void applyJoin(IJoinDecTargetDTO iJoinDecTargetDTO) {


    }

    public void setSelectedDTOBinding(String selectedDTOBinding) {
        this.selectedDTOBinding = selectedDTOBinding;
    }

    public String getSelectedDTOBinding() {
        return selectedDTOBinding;
    }

    public void openJoinDivForExcute() {
        // super.openJoinDiv();
        // added by M.abdelsabour to hide details column 
//       HtmlDataTable htmlDataTable= super.getMyDataTable();
//        List<UIComponent> listCom=htmlDataTable.getChildren();
//        HtmlSimpleColumn htmlSimpleColumn = (HtmlSimpleColumn)listCom.get(6);
//        htmlSimpleColumn.setRendered(false);
        
        super.openJoinDivList();
    }

    protected String getDivId() {
        return "masterDetailDiv";
    }


    //    @Override
    //    protected List<IJoinDecTargetDTO> getTargetList() {
    //        return Collections.emptyList();
    //    }


    ////////////////// new/////////////////////////////


    public List<IBasicDTO> getSelectedDTOS() {
        return (List<IBasicDTO>)JSFHelper.getValue(selectedDTOBinding);
    }

    @Override
    protected List<IJoinDecTargetDTO> getTargetList() {

        IJoinDecTargetDTO target = null;
        List<IJoinDecTargetDTO> targetList = new ArrayList<IJoinDecTargetDTO>();
        try {

            for (IBasicDTO dto : getSelectedDTOS()) {
                target = (IJoinDecTargetDTO)EmpClientFactory.getEmpCandidatesClient().getById(dto.getCode());
                targetList.add(target);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetList;
    }


    @Override
    protected List<Long> getEmpCivilIdList() {

        Long civilId = null;
        List<Long> empCivilIdList = new ArrayList<Long>();

        for (IBasicDTO dto : getSelectedDTOS()) {
            if (((IEmpCandidatesDTO)dto).getEmployeesDTO() != null) {
                civilId = ((IEmployeesEntityKey)((IEmpCandidatesDTO)dto).getEmployeesDTO().getCode()).getCivilId();
            } else if (((IEmpCandidatesDTO)dto).getCitizensResidentsDTO() != null) {
                civilId =
                        ((IKwtCitizensResidentsEntityKey)((IEmpCandidatesDTO)dto).getCitizensResidentsDTO().getCode()).getCivilId();
            }

            empCivilIdList.add(civilId);

        }
        return empCivilIdList;
    }


    /// to implement single selection with join

    @Override
    public void execute() {

        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {

            try {
                IDecisionsDTO decisionsDTO = (IDecisionsDTO)getSelectFromDecDIV();

                List targetList = new ArrayList<IJoinDecTargetDTO>();
                IJoinDecTargetDTO target;
                IEmpCandidatesDTO empcan;
                IKwtCitizensResidentsDTO citizen;
                for (int i = 0; i < getSelectedDTOS().size(); i++) {
                    target = ((IJoinDecTargetDTO)getSelectedDTOS().get(i));
                    target.setDecisionsDTO(decisionsDTO);
                    empcan = (IEmpCandidatesDTO)getSelectedDTOS().get(i);

                    citizen = (IKwtCitizensResidentsDTO)empcan.getCitizensResidentsDTO();
                    target.setRealCivilId(citizen.getCivilId());
                    target.setTabrecSerial(((IEmpCandidatesDTO)getSelectedDTOS().get(i)).getTabrecSerial());

                    targetList.add(target);
                }

                new EmpCandidatesJoinDecHelper(targetList);

                FacesContext fc = FacesContext.getCurrentInstance();
                Application app = fc.getApplication();
                HireDecisionListBean hireDecisionListBean =
                    (HireDecisionListBean)app.createValueBinding("#{hireDecisionListBean}").getValue(fc);
                hireDecisionListBean.getHighlightedDTOS().clear();
                hireDecisionListBean.getHighlightedDTOS().addAll(getSelectedDTOS());
                getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "Added"));
                //تن�?يذ ربط القرار
                if (((IEmpCandidatesClient)hireDecisionListBean.getClient()).applyDecisionForHire(getSelectedDTOS())) {
                    //String msg = getSharedUtil().messageLocator(getSharedUtil().messageLocator(BUNDLE_NAME, "applydecJoinhireSuccess"));

                    getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                      "applydecJoinhireSuccess"));
                    hireDecisionListBean.filterEmpByHireType();
                    
                }
                //
            } catch (Exception e) {
                e.printStackTrace();
                getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "NotAdded"));

            }


        }


    }
}
