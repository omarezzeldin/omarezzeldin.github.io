package com.beshara.csc.hr.emp.presentation.backingbean.hiretypes;


import com.beshara.base.dto.ITreeDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.presentation.backingbean.hiretypes.treelist.HireTypesTreeListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;

import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import javax.faces.context.FacesContext;


public class MaintainBean extends ManyToManyMaintainBaseBean {
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";

    public MaintainBean() {
        setClient(EmpClientFactory.getHireTypesClient());
        setPageDTO(EmpDTOFactory.createHireTypesDTO());
        setCurrentStep("hiretypesmaindata");
        setNextNavigationCase("documenttypemaintain");
        setFinishNavigationCase("hiretypestreelist");
    }

    public String finish() throws DataBaseException, ItemNotFoundException, SharedApplicationException {
        String defultFinish = super.finish();

        HireTypesListBean listPage =
            (HireTypesListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{hireTypesListBean}").getValue(FacesContext.getCurrentInstance());
        listPage.getMyTableData();

        if (getPageDTO().getCode() != null) {
            listPage.getPageIndex((String)getPageDTO().getCode().getKey());

            listPage.getHighlightedDTOS().add(getPageDTO());
        }

        HireTypesTreeListBean hireTypesTreeBean =
            (HireTypesTreeListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                         "hireTypesTreeListBean" +
                                                                                                         "}").getValue(FacesContext.getCurrentInstance());
        hireTypesTreeBean.setTreeCode(getPageDTO().getCode().getKey());
        returnToTreePage();
        return defultFinish;
    }

    public void returnToTreePage() {

        HireTypesTreeListBean bean =
            (HireTypesTreeListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                         "hireTypesTreeListBean" +
                                                                                                         "}").getValue(FacesContext.getCurrentInstance());
        ITreeDTO itemDTO = (ITreeDTO)getPageDTO();
        String code = itemDTO.getCode().getKey();

        bean.SetSelectedNode(code);
        try {
            bean.getAll();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        bean.setHighLightedDTO(itemDTO);
    }
    
    public String back() {
        returnToTreePage();
        return super.back();
    }

    public void add() throws DataBaseException {

           SharedUtilBean sharedUtil = getSharedUtil();

           try {

               setPageDTO(executeAdd());

               if (isUsingPaging()) {

                   getPagingBean().clearDTOS();

                   generatePagingRequestDTO((String)getPageDTO().getCode().getKey());

               } else {
                   getAll();
                   getPageIndex((String)getPageDTO().getCode().getKey());
               }

               if (getHighlightedDTOS() != null) {
                   getHighlightedDTOS().add(getPageDTO());
               }
               this.setSearchQuery("");
               this.setSearchType(0);
               this.setSearchMode(false);
               sharedUtil.setSuccessMsgValue("SuccessAdds");

           } catch (DataBaseException e) {
               this.setShowErrorMsg(true);

               //            sharedUtil.handleException(e,
               //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
               //                                       "FailureInAdd");
               sharedUtil.handleException(e);
               this.setErrorMsg(sharedUtil.getErrMsgValue());
           } catch (SharedApplicationException e) {
              
             if(e instanceof InvalidDataEntryException){
                  super.setSuccess(false);
                  this.setErrorMsg(getSharedUtil().messageLocator(BUNDLE_NAME,"name_duplicated"));
                  getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                                         "name_duplicated"));
             }else{
                     sharedUtil.handleException(e);
                     this.setErrorMsg("FailureInAdd");
                 }
               
               this.setShowErrorMsg(true);

           } catch (Exception e) {
               this.setShowErrorMsg(true);
               this.setErrorMsg("FailureInAdd");
               sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInAdd");

           }
           //added by nora to reset radio if list has radio column
           setSelectedRadio("");
       }

    public void edit() throws DataBaseException, ItemNotFoundException, SharedApplicationException {

        SharedUtilBean sharedUtil = getSharedUtil();

        try {

            executeEdit();

            cancelSearch();

            if (isUsingPaging()) {

                setUpdateMyPagedDataModel(true);
                setRepositionTable(true);
                getPagingBean().clearDTOS();
                if (getPagingRequestDTO() == null) {
                    setPagingRequestDTO(new PagingRequestDTO());
                }
                getPagingRequestDTO().setRepositionKey((String)getPageDTO().getCode().getKey());

            }

            if (getHighlightedDTOS() != null) {
                getHighlightedDTOS().add(getPageDTO());
            }

            sharedUtil.setSuccessMsgValue("SuccesUpdated");
            this.getSelectedDTOS().clear();

        } catch (DataBaseException e) {
            sharedUtil.handleException(e);

        } catch (ItemNotFoundException item) {
            sharedUtil.handleException(item);

        } catch (SharedApplicationException e) {
            
            if(e instanceof InvalidDataEntryException){
                super.setSuccess(false);
                this.setErrorMsg(getSharedUtil().messageLocator(BUNDLE_NAME,"name_duplicated"));
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME,
                                                                              "name_duplicated"));
            }else{
                    sharedUtil.handleException(e);
                }

           
        } catch (Exception e) {
            sharedUtil.handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions", "FailureInUpdate");
        }
        //Added By Yassmine to reset the value of radio button after Edit
        setSelectedRadio("");

    }
}

