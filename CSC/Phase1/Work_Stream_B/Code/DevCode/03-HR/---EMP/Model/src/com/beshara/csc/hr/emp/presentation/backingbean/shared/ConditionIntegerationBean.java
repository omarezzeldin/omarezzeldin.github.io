package com.beshara.csc.hr.emp.presentation.backingbean.shared;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;


/**
 * Bean used to join & add condition for sal module
 *
 * */
public class

ConditionIntegerationBean extends LookUpBaseBean {

    // this method will be called after save method in div to reset or reintialize before return to calling use-case
    private String resetButtonMethod;


    public ConditionIntegerationBean() {
    setSaveSortingState(true);
    }

    /**
     * Purpose:called when user navigate to add condition to make common initialization  for 6 use cases (social raise ,increases....@ SaL Module )
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public void initializeBeforeAddCondition(String backNavgationCase, 
                                             String backBeanNameFrom) {

        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setRenderedBackButton(true);
        getIntegrationBean().setBeanNameTo("conditionListBean");
        getIntegrationBean().setNavgationCaseFrom(backNavgationCase);
        getIntegrationBean().setNavgationCaseTo("condition_list");
        getIntegrationBean().getHmBaseBeanFrom().put(backBeanNameFrom, 
                                                     evaluateValueBinding(backBeanNameFrom));
        getIntegrationBean().setModuleFrom("emp");
        getIntegrationBean().setActionTo("goAdd");
        getIntegrationBean().setBeanNameFrom(backBeanNameFrom);
        getIntegrationBean().setActionFrom("addCondition");
        getIntegrationBean().getHmObjects().put("selectedDTO", 
                                                getSelectedDTOS().get(0));

    }

    /**
     * Purpose:called when user press finish to add condition and each  bean called that method @ action of save 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public boolean executeConditionAdd() {
        boolean ret = false;
        try {
            IBasicDTO selectedDTO = null;
            // EZ : , NOTE we can use selectedDTOS variable witin integration baean instead of HashMap 
            if (getIntegrationBean().getHmObjects() != null && 
                getIntegrationBean().getHmObjects().size() > 0 && 
                getIntegrationBean().getHmObjects().get("selectedDTO") != 
                null) {
                selectedDTO = 
                        (IBasicDTO)getIntegrationBean().getHmObjects().get("selectedDTO");
            }

            if (selectedDTO != null && selectedDTO.getCode() != null && 
                getIntegrationBean().getSelectedDTOTo() != null && 
                getIntegrationBean().getSelectedDTOTo().get(0) != null) {
                SalClientFactory.getSalElementGuidesClient().addCondition(selectedDTO, 
                                                                          getIntegrationBean().getSelectedDTOTo().get(0));
                getSharedUtil().handleSuccMsg("SuccesAddCondition2");
                ret = true;
            } else
                getSharedUtil().setErrMsgValue("FailureInUpdate");

        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (Exception e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        }

        if (resetButtonMethod != null)
            executeMethodBinding(getResetButtonMethod(), null);

        return ret;
    }

    /**
     * Purpose:used to join condition at specific sal element guide node , it will be called by condition div service bean @ save method 
     * Creation/Modification History :
     * 1.1 - Developer Name: Nora Ismail
     * 1.2 - Date:   14-10-2008
     * 1.3 - Creation/Modification:Creation      
     * 1.4-  Description: 
     */
    public boolean executejoinCondition() {
        boolean ret = false;
        /*try {
            ConditionDivService conditionDivService = 
                (ConditionDivService)evaluateValueBinding("conditionDivService");
            if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0 && 
                getSelectedDTOS().get(0) != null && 
                conditionDivService.getSelectedDTOS() != null && 
                conditionDivService.getSelectedDTOS().size() > 0 && 
                conditionDivService.getSelectedDTOS().get(0) != null) {
                SalClientFactory.getSalElementGuidesClient().joinCondition(getSelectedDTOS().get(0), 
                                                                           conditionDivService.getSelectedDTOS().get(0));
                
            getSharedUtil().handleSuccMsg("SuccesJoinCondition");
                ret = true;
            } else
                getSharedUtil().setErrMsgValue("FailureInUpdate");

        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        } catch (Exception e) {
            getSharedUtil().handleException(e, 
                                            "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                            "FailureInUpdate");
        }
            */
        return ret;
    }


    public void setResetButtonMethod(String resetButtonMethod) {
        this.resetButtonMethod = resetButtonMethod;
    }

    public String getResetButtonMethod() {
        return resetButtonMethod;
    }


}
