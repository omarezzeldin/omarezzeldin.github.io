package com.beshara.csc.hr.emp.presentation.backingbean.hiretypes;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireTypesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.ISearchHireTypeDTO;
import com.beshara.csc.hr.emp.presentation.backingbean.shared.ConditionIntegerationBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.ManyToManyListBaseBean;

import java.util.ArrayList;
import java.util.List;


public class HireTypesListBean extends ManyToManyListBaseBean {

    private ISearchHireTypeDTO searchHireTypeDTO = EmpDTOFactory.createSearchHireTypeDTO();
    private ConditionIntegerationBean conditionIntegerationBean = 
        (ConditionIntegerationBean)evaluateValueBinding("conditionIntegerationBean");
    public static String BACK_BEAN_NAME = "hireTypesListBean";
    public static String NAVIGATION_CASE = "hiretypeslist";

    public HireTypesListBean() {
        setClient(EmpClientFactory.getHireTypesClient());
        setPageDTO(EmpDTOFactory.createHireTypesDTO());
        setSelectedPageDTO(EmpDTOFactory.createHireTypesDTO());
        setEditNavigationCase("hiretypesmaindata");
        setAddNavigationCase("hiretypesmaindata");
        setEditBeanName("hireTypesMaintainBean");
        setAddBeanName("hireTypesMaintainBean");
//        setDivSearch("typeSearchDiv");
        setSaveSortingState(true);
    }

    public void setSearchHireTypeDTO(ISearchHireTypeDTO searchHireTypeDTO) {
        this.searchHireTypeDTO = searchHireTypeDTO;
    }

    public ISearchHireTypeDTO getSearchHireTypeDTO() {
        return searchHireTypeDTO;
    }

   

    /**
     * Purpose: search method
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void search() {
        try {
            setMyTableData(((IHireTypesClient)getClient()).search(getSearchHireTypeDTO()));
            repositionPage(0);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        } catch (Exception e) {
            e.printStackTrace();
            setMyTableData(new ArrayList());
        } finally {
            if(getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }
        }
        
        setSearchMode(true);
    }

    /**
     * Purpose: cancel search method
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void cancelSearch() throws DataBaseException {
        setSearchHireTypeDTO(EmpDTOFactory.createSearchHireTypeDTO());
        super.cancelSearch();
    }

    /**
     * Purpose: initialize Object Before Maintain
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description: 
     * @return 
     * @throws 
     */
    public void initializeObjectBeforeMaintain() {
        //HireTypesDTO dto = (HireTypesDTO)getPageDTO();
        if (((IHireTypesDTO)getPageDTO()).getRequiredDocumentsDTOList() == 
            null) {
            ((IHireTypesDTO)getPageDTO()).setRequiredDocumentsDTOList(new ArrayList());
            //super.initializeObjectBeforeMaintain();
        }
    }

    public void backAction() {
        if (!isSearchMode()) {
            setSearchHireTypeDTO(EmpDTOFactory.createSearchHireTypeDTO());
        }
    }


    public String viewAvailableCondition() {

        IHireTypesDTO hireTypesDTO = (IHireTypesDTO)getSelectedDTOS().get(0);
        //To refactor
        IConditionsDTO conditionsDTO = GrsDTOFactory.createConditionsDTO();
        //IConditionsDTO conditionsDTO = hireTypesDTO.getConditionsDTO();
            if (conditionsDTO != null) {
            conditionIntegerationBean.setSelectedDTOS(getSelectedDTOS());
            conditionIntegerationBean.initializeBeforeAddCondition(NAVIGATION_CASE, 
                                                                   BACK_BEAN_NAME);
            conditionIntegerationBean.getIntegrationBean().setActionTo("goView");
            setValueBinding("conditionListBean.viewInCenter", true);

            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(conditionsDTO);
            conditionIntegerationBean.getIntegrationBean().setSelectedDTOFrom(list);

            return getIntegrationBean().goTO();
        } else {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator("com.beshara.jsfbase.csc.msgresources.global", 
                                                                          "hasNoConditions"));

        }
        return null;
    }
  
}

