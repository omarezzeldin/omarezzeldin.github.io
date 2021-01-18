package com.beshara.csc.hr.emp.presentation.backingbean.hireprocedures;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireProceduresClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.ISearchHireProceduresDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.ICatsDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class HireProceduresListBean extends LookUpBaseBean {

    ISearchHireProceduresDTO searchHireProceduresDTO = EmpDTOFactory.createSearchHireProceduresDTO();

    //empty item in combobox
    private String emptyItemSelection = String.valueOf(ISystemConstant.VIRTUAL_VALUE.longValue());

    private String selectedNationalityType = "";
    private String selectedGenderType = "";
    private String selectedCategory = "";
    private String selectedMinistry = "";
    private List genderTypeList = new ArrayList();
    private List categoryList = new ArrayList();
    private List ministryList = new ArrayList();

    // flag to indicate if user in simple search mode or advanced
    private boolean simpleSearchFlag = true;
    private boolean rendrAdvancedSearch = false;
    private String stringSearchType = "false";

    //flags government constants
    private String stringGovFlag = "";
    private String govFlag = String.valueOf(ISystemConstant.GOVERNMENT_FLAG.longValue());
    private String nonGovFlag = String.valueOf(ISystemConstant.NON_GOVERNMENT_FLAG.longValue());

    //nationalty type constants
    private String kuwaiti = String.valueOf(ISystemConstant.NATIONALITY_KUWAITI.longValue());
    private String nonKuwaiti = String.valueOf(ISystemConstant.NATIONALITY_NON_KUWAITI.longValue());
    private String nonSpecified = String.valueOf(ISystemConstant.NATIONALITY_NON_SPECIFIED.longValue());
    private List<IHireProceduresDTO> relatedList;

    public HireProceduresListBean() {
        setPageDTO(EmpDTOFactory.createHireProceduresDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createHireProceduresDTO());
        setClient(EmpClientFactory.getHireProceduresClient());
        setSaveSortingState(true);
        setSingleSelection(true);

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowCustomDiv1(true);
        return app;
    }

    public void initiateBeanOnce() {
        relatedList = new ArrayList();
    }

    /**
     * Purpose: search in hire procedures by simple or advanced search
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  16-July-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void searchHireProcedures() throws DataBaseException, NoResultException, SharedApplicationException {
        System.out.println("Calling search()...");
        this.setSearchMode(true);
        Long code;
        try {
            //It means search by code with simple search
            if ((getSearchQuery() != null) && (!getSearchQuery().equals(""))) {
                if (getStringSearchType().equals("false") && simpleSearchFlag) {
                    try {
                        code = Long.parseLong(getSearchQuery());
                    } catch (Exception e) {
                        e.printStackTrace();
                        code = -456L;
                    }
                    setMyTableData((getClient()).searchByCode(code));

                }
                //It means search by name with simple search
                if (getStringSearchType().equals("true") && simpleSearchFlag) {
                    setMyTableData((getClient()).searchByName(formatSearchString(getSearchQuery())));
                }
            }

            else if (searchHireProceduresDTO != null) { // it means that user in advanced search mode
                if (selectedMinistry != null) {
                    if ((!selectedMinistry.equals("")) &&
                        (!selectedMinistry.equals(emptyItemSelection))) { //set ministry code
                        searchHireProceduresDTO.setMinCode(Long.parseLong(selectedMinistry));
                    }
                }
                if (selectedGenderType != null) {
                    if (!selectedGenderType.equals("") &&
                        (!selectedGenderType.equals(emptyItemSelection))) { //set gender type
                        searchHireProceduresDTO.setGenderType(Long.parseLong(selectedGenderType));
                    }
                }

                if (selectedNationalityType != null) {
                    if (!selectedNationalityType.equals("") &&
                        (!selectedNationalityType.equals(emptyItemSelection))) { // set nationality type
                        searchHireProceduresDTO.setNationalityType(Long.parseLong(selectedNationalityType));
                    }
                }
                setMyTableData((getClient()).search(searchHireProceduresDTO));
            }

        } catch (ItemNotFoundException e) { //this means no search results found
            //setSimpleSearchFlag(true);
            setMyTableData(new ArrayList());
            //this.setStringSearchType("false");
            //setSearchQuery("");
            e.printStackTrace();

        }

        catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
            this.setStringSearchType("false");
            //setSearchQuery("");
            ne.printStackTrace();
        }

        catch (Exception db) {
            this.setStringSearchType("false");
            //setSearchQuery("");
            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);

            db.printStackTrace();
        }

        if (getSelectedDTOS() != null)
            getSelectedDTOS().clear();
        if (getHighlightedDTOS() != null)
            getHighlightedDTOS().clear();
        this.repositionPage(0);
        //this.setStringSearchType("false");
        //setSearchQuery("");
        //setSimpleSearchFlag(true);
        //setStringGovFlag("");
        //setSelectedCategory("");
        //setMinistryList(new ArrayList(0));
        //setSelectedGenderType("");
        setSearchHireProceduresDTO(EmpDTOFactory.createSearchHireProceduresDTO());

    }

    public void cancelSearch() throws DataBaseException {
        HireProceduresSearchBean hireProceduresSearchBean =
            (HireProceduresSearchBean)evaluateValueBinding("hireProceduresSearchBean");
        hireProceduresSearchBean.clearDTO();
        hireProceduresSearchBean.getSelectedDTOS().clear();
        setRendrAdvancedSearch(false);
        super.cancelSearch();
        stringSearchType = "false";
        back();
    }

    public void setSearchHireProceduresDTO(ISearchHireProceduresDTO searchHireProceduresDTO) {
        this.searchHireProceduresDTO = searchHireProceduresDTO;
    }

    public ISearchHireProceduresDTO getSearchHireProceduresDTO() {
        return searchHireProceduresDTO;
    }

    public void setGenderTypeList(List genderTypes) {
        this.genderTypeList = genderTypes;
    }

    public List getGenderTypeList() {

        if (genderTypeList == null || genderTypeList.size() == 0) {
            try {
                genderTypeList = (InfClientFactory.getGenderTypesClient()).getCodeNameInCenter();
            } catch (DataBaseException e) {
                genderTypeList = new ArrayList();
                e.printStackTrace();
            } catch (Exception e) {
                genderTypeList = new ArrayList();
                e.printStackTrace();
            }
        }
        return genderTypeList;
    }

    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }

    public List getCategoryList() {
        return categoryList;
    }

    public void setMinistryList(List ministries) {
        this.ministryList = ministries;
    }

    public List getMinistryList() throws SharedApplicationException {
        return ministryList;
    }

    public void setSelectedGenderType(String genderType) {
        this.selectedGenderType = genderType;
    }

    public String getSelectedGenderType() {
        return selectedGenderType;
    }

    public void setSelectedCategory(String category) {
        this.selectedCategory = category;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedMinistry(String ministry) {
        this.selectedMinistry = ministry;
    }

    public String getSelectedMinistry() {
        return selectedMinistry;
    }

    public void setEmptyItemSelection(String itemSelection) {
        this.emptyItemSelection = itemSelection;
    }

    public String getEmptyItemSelection() {
        return emptyItemSelection;
    }

    public void setSimpleSearchFlag(boolean simpleSearchFlag) {
        this.simpleSearchFlag = simpleSearchFlag;
    }

    public boolean isSimpleSearchFlag() {
        return simpleSearchFlag;
    }

    public void changeSearchStatus() {
        simpleSearchFlag = !simpleSearchFlag;
    }


    /**
     * Purpose: this method called when user press back button in search div
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  15-July-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: reset values that are binded on components
     */
    public String back() {
        if (isSearchMode() == false) {
            setSimpleSearchFlag(true);
            setSearchQuery("");
            setStringGovFlag("");
            setSelectedCategory("");
            setMinistryList(new ArrayList(0));
            setSelectedGenderType("");
            setSearchHireProceduresDTO(EmpDTOFactory.createSearchHireProceduresDTO());
        }
        setJavaScriptCaller("hideLookUpDiv(window.blocker,window.divSearch,null,null);");
        return null;
    }

    public void setStringSearchType(String stringSearchType) {
        this.stringSearchType = stringSearchType;
    }

    public String getStringSearchType() {
        String result = "true";
        if (getSearchType() == 0)
            result = "false";
        return result;
    }

    /**
     * Purpose: Fires when selecting any value from categoryList
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  15-July-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: Fills MinistryList when selecting any value from categoryList
     */
    public void updateMinistryList(ActionEvent ae) {
        ae = null; //unused

        if ((selectedCategory != null) && !selectedCategory.equals("")) {
            try {
                //fill job list depending on jobsector selection
                ministryList =
                        (OrgClientFactory.getMinistriesClient()).getAllByCategory(Long.parseLong(selectedCategory));
            } catch (DataBaseException e) {
                e.printStackTrace();
                ministryList = new ArrayList();
            } catch (Exception e) {
                ministryList = new ArrayList();
                e.printStackTrace();
            }
        } else {
            setMinistryList(new ArrayList());
            setSelectedMinistry(null);
        }

    }

    /**
     * Purpose: Fires when selecting any value from stringGovFlag
     * Creation/Modification History :
     * 1.1 - Developer Name: Khalid Mohie
     * 1.2 - Date:  15-July-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description: Fills CategoryList when selecting any value from stringGovFlag
     */
    public void updateCategoryList(ActionEvent ae) {
        ae = null; //unused
        if ((stringGovFlag != null) && (!stringGovFlag.equals(""))) {
            try {
                //fill category list depending on stringGovFlag
                categoryList = OrgClientFactory.getCatsClient().getCatsByGovFlag(Long.parseLong(stringGovFlag));
                //must clear ministry list because we didn't choose any value from category
                setSelectedCategory(null);
                setMinistryList(new ArrayList(0));
            } catch (DataBaseException e) {
                e.printStackTrace();
                categoryList = new ArrayList();
            } catch (Exception e) {
                categoryList = new ArrayList();
                e.printStackTrace();
            }
        } else {
            setCategoryList(new ArrayList());
            setSelectedCategory(null);
        }

    }

    public void setStringGovFlag(String stringGovFlag) {
        this.stringGovFlag = stringGovFlag;
    }

    public String getStringGovFlag() {
        return stringGovFlag;
    }

    public void setGovFlag(String govFlag) {
        this.govFlag = govFlag;
    }

    public String getGovFlag() {
        return govFlag;
    }

    public void setNonGovFlag(String nonGovFlag) {
        this.nonGovFlag = nonGovFlag;
    }

    public String getNonGovFlag() {
        return nonGovFlag;
    }

    public void setKuwaiti(String kuwaiti) {
        this.kuwaiti = kuwaiti;
    }

    public String getKuwaiti() {
        return kuwaiti;
    }

    public void setNonKuwaiti(String nonKuwaiti) {
        this.nonKuwaiti = nonKuwaiti;
    }

    public String getNonKuwaiti() {
        return nonKuwaiti;
    }

    public void setNonSpecified(String nonSpecified) {
        this.nonSpecified = nonSpecified;
    }

    public String getNonSpecified() {
        return nonSpecified;
    }

    public void setSelectedNationalityType(String selectedNationalityType) {
        this.selectedNationalityType = selectedNationalityType;
    }

    public String getSelectedNationalityType() {
        return selectedNationalityType;
    }

    /**
     * Purpose:go to add page and serch
     * Creation/Modification History :
     * 1.1 - Developer Name:m.masoud
     * 1.2 - Date:  21/Des/2010
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:go to add page and search
     */
    public String addProcedure() {
        return "add";
    }

    public String searchProcedure() {
        return "search";
    }

    /**
     * Purpose:fill selected pageDTO in edit bean and go to edit bean through navigation rule
     * Creation/Modification History :
     * 1.1 - Developer Name: Assmaa Omar
     * 1.2 - Date:  17/Jul/2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:fill selected pageDTO in edit bean and go to edit bean through navigation rule
     */
    public String editProcedure() {
        try {
            if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
                FacesContext fc = FacesContext.getCurrentInstance();
                Application app = fc.getApplication();
                HireProceduresEditBean hireProceduresEditBean =
                    (HireProceduresEditBean)app.createValueBinding("#{hireProceduresEditBean}").getValue(fc);
                try {
                    ICatsDTO catsDTO = OrgDTOFactory.createCatsDTO();
                    setSelectedPageDTO(getClient().getById(getSelectedDTOS().get(0).getCode()));
                    hireProceduresEditBean.setSelectedPageDTO(getSelectedPageDTO());
                    String ministryCode =
                        "" + ((IHireProceduresDTO)getSelectedPageDTO()).getMinistriesDTO().getCode().getKey();

                    hireProceduresEditBean.setMinistryCode(ministryCode);
                    //                    hireProceduresEditBean.setSelectedGender(((IHireProceduresDTO)getSelectedPageDTO()).getGenderTypesDTO().getCode().getKey().toString());
                    //                    hireProceduresEditBean.setSelectedNationalityType(((IHireProceduresDTO)getSelectedPageDTO()).getNationalityType().toString());
                    ((IHireProceduresDTO)hireProceduresEditBean.getSelectedPageDTO()).setMinistriesDTO(((IHireProceduresDTO)getSelectedPageDTO()).getMinistriesDTO());
                    catsDTO = ((IHireProceduresDTO)getSelectedPageDTO()).getMinistriesDTO().getCatsDTO();

                    if (catsDTO.getCode() != null) {
                        hireProceduresEditBean.setSelectedCategoryType(catsDTO.getCode().getKey().toString());
                        hireProceduresEditBean.setSelectedGovFlag(catsDTO.getGovFlag().toString());
                        Long categoryId = Long.parseLong(catsDTO.getCode().getKey().toString());
                        //                        hireProceduresEditBean.setMyTableData((OrgClientFactory.getMinistriesClient()).getAllByCategory(categoryId));
                        //                        hireProceduresEditBean.setSelectedRadio(((IHireProceduresDTO)getSelectedPageDTO()).getMinistriesDTO().getCode().getKey().toString());
                        //                        hireProceduresEditBean.getPageIndex(((IHireProceduresDTO)getSelectedPageDTO()).getMinistriesDTO().getCode().getKey().toString());
                    }
                    if(((IHireProceduresDTO)getSelectedPageDTO()).getStatus() != null &&
                       ((IHireProceduresDTO)getSelectedPageDTO()).getStatus().equals(1L))
                        hireProceduresEditBean.setViewStatus(true);
                    else{
                        hireProceduresEditBean.setViewStatus(false);
                    }
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                    setSelectedPageDTO(EmpDTOFactory.createHireProceduresDTO());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                    setSelectedPageDTO(EmpDTOFactory.createHireProceduresDTO());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "edit";
    }

    public void preDelete() {

        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delAlert);");

    }


    @Override
    public void deleteDiv() {
        try {

            IHireProceduresDTO dto = (IHireProceduresDTO)getSelectedDTOS().get(0);
            Long hireProcdureCode = Long.valueOf(dto.getCode().getKey());
            List<IHireProceduresDTO> list = new ArrayList();
            try {
                list = (List)((IHireProceduresClient)getClient()).getAllRelatedByHireProcdure(hireProcdureCode);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }

            if (list == null || list.size() == 0) {
                this.delete();
                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
            }

            else {
                setRelatedList(list);
                setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setRendrAdvancedSearch(boolean rendrAdvancedSearch) {
        this.rendrAdvancedSearch = rendrAdvancedSearch;
    }

    public boolean isRendrAdvancedSearch() {
        return rendrAdvancedSearch;
    }


    public void setRelatedList(List<IHireProceduresDTO> relatedList) {
        this.relatedList = relatedList;
    }

    public List<IHireProceduresDTO> getRelatedList() {
        return relatedList;
    }
}

