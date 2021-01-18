package com.beshara.csc.hr.emp.presentation.backingbean.minExceptionFromDewanApprove;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.nl.org.business.client.IOrgMinExtraDataClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IExtraDataTypesDTO;
import com.beshara.csc.nl.org.business.dto.IOrgMinExtraDataDTO;
import com.beshara.csc.nl.org.business.entity.IExtraDataTypesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;


public class ListBean extends LookUpBaseBean {
    public List extraDataTypesList;
    public String extraDataTypesValue;
    public String extraDataTypesName;
    private String selectedCategory;
    private String selectedMinistry;
    private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();
    private boolean addBefore;
    private List wcBgtList = new ArrayList();
    private String wcBgtValue = "";
    private boolean statusActive;
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    public ListBean() {
        setLookupAddDiv("m2mAddDivClass");
    }

    public void initiateBeanOnce() {
        fillExtraDataTypesList();
    }

    public AppMainLayout appMainLayoutBuilder() {

        AppMainLayout app = new AppMainLayout();

        app.setShowdatatableContent(true);
        app.setShowContent1(true);
        app.setShowpaging(true);
        app.setShowbar(true);
        app.setShowLookupAdd(true);

        return app;
    }

    public void fillExtraDataTypesList() {

        try {
            extraDataTypesList = OrgClientFactory.getExtraDataTypesClient().getExtraDataTypesList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void filterByCategory(ActionEvent event) {

        setSelectedMinistry(null);

        if (selectedCategory != null && !(selectedCategory.equals(""))) {
            try {
                ministryList = OrgClientFactory.getMinistriesClient().getAllByCategory(Long.valueOf(selectedCategory));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            } catch (DataBaseException e) {
                e.printStackTrace();
                ministryList = new ArrayList<IBasicDTO>();
            }
        } else {
            ministryList = new ArrayList<IBasicDTO>();
        }

    }

    public void setExtraDataTypesList(List extraDataTypesList) {
        this.extraDataTypesList = extraDataTypesList;
    }

    public List getExtraDataTypesList() {
        return extraDataTypesList;
    }

    public void setExtraDataTypesValue(String extraDataTypesValue) {
        this.extraDataTypesValue = extraDataTypesValue;
    }

    public String getExtraDataTypesValue() {
        return extraDataTypesValue;
    }

    public void setExtraDataTypesName(String extraDataTypesName) {
        this.extraDataTypesName = extraDataTypesName;
    }

    public String getExtraDataTypesName() {
        return extraDataTypesName;
    }


    public void getAll() {
        try {
            if (extraDataTypesValue == null)
                setExtraDataTypesValue("44");
            IExtraDataTypesEntityKey ek =
                OrgEntityKeyFactory.createExtraDataTypesEntityKey(Long.valueOf(extraDataTypesValue));
            IExtraDataTypesDTO extraDataTypesDTO =
                (IExtraDataTypesDTO)OrgClientFactory.getExtraDataTypesClient().getById(ek);
            extraDataTypesName = extraDataTypesDTO.getName();
            List orgMinExtraData = OrgClientFactory.getOrgMinExtraDataClient().getByExtraDataTypeCodeForEmp(extraDataTypesValue);
            setMyTableData(orgMinExtraData);
        } catch (Exception e) {  
            if(e instanceof NoResultException){
                    setMyTableData(new ArrayList());
                }
            e.printStackTrace();
        }
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedMinistry(String selectedMinistry) {
        this.selectedMinistry = selectedMinistry;
    }

    public String getSelectedMinistry() {
        return selectedMinistry;
    }

    public void setCategoryList(List<IBasicDTO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<IBasicDTO> getCategoryList() {
        if (categoryList == null || categoryList.size() == 0) {
            try {
                categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(ISystemConstant.GOVERNMENT_FLAG);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return categoryList;
    }

    public void setMinistryList(List<IBasicDTO> ministryList) {
        this.ministryList = ministryList;
    }

    public List<IBasicDTO> getMinistryList() {
        return ministryList;
    }

    public void preAdd() {
        addBefore = false;
        selectedCategory = "";
        selectedMinistry = "";
        ministryList = new ArrayList<IBasicDTO>();
        wcBgtValue = "";
    }

    public void performAdd() {
        addBefore = false;
        IOrgMinExtraDataClient client = OrgClientFactory.getOrgMinExtraDataClient();
        try {
            boolean returnStatus=client.checkAddedMin(selectedMinistry,extraDataTypesValue,wcBgtValue);
            if (returnStatus) {
                addBefore = true;
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv);");
                return;
            }

            IOrgMinExtraDataDTO minExtraDataDTO =
                (IOrgMinExtraDataDTO)client.addNewRecord(Long.valueOf(selectedMinistry),
                                                         Long.valueOf(extraDataTypesValue),wcBgtValue);
            getHighlightedDTOS().clear();
            getHighlightedDTOS().add(minExtraDataDTO);
            getAll();
            getPageIndex(minExtraDataDTO.getCode().getKey());
            getSharedUtil().setSuccessMsgValue("SuccessAdds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAddBefore(boolean addBefore) {
        this.addBefore = addBefore;
    }

    public boolean isAddBefore() {
        return addBefore;
    }
    
    public void setWcBgtList(List wcBgtList) {
        this.wcBgtList = wcBgtList;
    }

    public void fillWcBgtList() {
         if (selectedMinistry != null && !selectedMinistry.equals("")){
            try {
              
                    wcBgtList =
                            BgtClientFactory.getBgtProgramsClient().getCodeNameByMinCode (Long.valueOf(selectedMinistry));
            } catch (DataBaseException e) {
                wcBgtList = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                wcBgtList = new ArrayList();
                e.printStackTrace();
            }
        }

    }
    
    public List getWcBgtList() {
        return wcBgtList;
    }
    public void setWcBgtValue(String wcBgtValue) {
        this.wcBgtValue = wcBgtValue;
    }

    public String getWcBgtValue() {
        return wcBgtValue;
    }

    public void setStatusActive(boolean statusActive) {
        this.statusActive = statusActive;
    }

    public boolean isStatusActive() {
        return statusActive;
    }
    
    public void selectedRadioButton(ActionEvent event) throws Exception {
        statusActive=false;
        setIndexOfSelectedDataInDataTable(getMyDataTable().getRowIndex());
        this.getSelectedDTOS().clear();
        IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();
        this.getSelectedDTOS().add(selected);
        IOrgMinExtraDataDTO dto=(IOrgMinExtraDataDTO)selected;
        if(dto.getStatus() == 1L){
                statusActive=true;
            }
    }

    public void freezeRequest() {
        IOrgMinExtraDataDTO dto = (IOrgMinExtraDataDTO)getSelectedDTOS().get(0);
        dto.setStatus(0L);
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        dto.setUntilDate(ts);
        try {
            IOrgMinExtraDataClient client = OrgClientFactory.getOrgMinExtraDataClient();
            client.updateRequest(dto);
            getHighlightedDTOS().clear();
            getHighlightedDTOS().add(dto);
            getAll();

            getPageIndex(dto.getCode().getKey());

            getSharedUtil().setSuccessMsgValue( getSharedUtil().messageLocator(BUNDLE_NAME,"freezeRequest_done"));

        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
    }
}
