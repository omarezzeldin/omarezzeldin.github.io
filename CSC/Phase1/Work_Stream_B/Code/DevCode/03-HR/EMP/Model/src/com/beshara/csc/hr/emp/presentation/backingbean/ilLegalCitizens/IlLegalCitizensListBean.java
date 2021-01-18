package com.beshara.csc.hr.emp.presentation.backingbean.ilLegalCitizens;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.emp.presentation.backingbean.addIlleagalAccomodation.IlleagalAccomodationMaintainBean;
import com.beshara.csc.hr.emp.presentation.backingbean.addIlleagalAccomodation.IlleagalAccomodationPersonalInfo;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsSearchDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.ISpecialCaseTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ISpecialCaseTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class IlLegalCitizensListBean extends LookUpBaseBean {


    protected static final String BEAN_NAME = "ilLegalCitizensListBean";

    private static final String RESOURCE_BUNDLE = "com.beshara.csc.hr.emp.presentation.resources.emp";

    private static final Long approved = 2L;
    private static final Long underStuding = 1L;
    private static final Long refused = 3L;
    private static final Long Initialization = 0L;


    private String requestStatust = "0";

    private String allValues = "0";

    private String underStudding = "1";

    private String approve = "2";
    private String refuse = "3";

    // to do
    private IKwtCitizensResidentsSearchDTO listFilterDTO = InfDTOFactory.createKwtCitizensResidentsSearchDTO();


    //represent search dto
    private IKwtCitizensResidentsSearchDTO searchDTO = InfDTOFactory.createKwtCitizensResidentsSearchDTO();
    IKwtCitizensResidentsSearchDTO filterDTO = listFilterDTO;

    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;

    private String searchName;

    private String selectedCategory;

    private static final int ADD_MODE = 0;
    private static final int EDIT_MODE = 1;


    List<String> sortingColumnList = new ArrayList<String>();

    private String requestStatustInSearch;

    private static final String columnNameCivil = "o.civilId";
    private static final String columnNameFullName = "fullName";

    private static final String columnNameSpecName = "o.specialCaseTypesEntity.spccsetypeName";
    private List<IBasicDTO> catList = new ArrayList<IBasicDTO>();

    public IlLegalCitizensListBean() {
        setPageDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        setSelectedPageDTO(InfDTOFactory.createKwtCitizensResidentsDTO());
        setClient(InfClientFactory.getKwtCitizensResidentsClient());


        // to use business paging
        setSaveSortingState(true);
        setUsingPaging(true);
        setUsingBsnPaging(true);

        filterDTO.setNonStatus(Long.valueOf(requestStatust));
        fillTable();
        setSearchMode(true);

    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();

        app.showLookupListPage();
        app.setShowdatatableContent(true);
        app.setShowContent1(true);
        app.setShowSearch(true);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowCommonData(true);
        app.setShowshortCut(true);


        return app;

    }


    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequestDTO) {
        return baseSearchWithPaging(pagingRequestDTO);
    }

    public PagingResponseDTO baseSearchWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = getDataWithPaging(pagingRequest);
        PagingResponseDTO pagingResponseDTO = null;

        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
            } else if (pagingRequest != null && pagingRequest.getParams() != null) {
                pagingResponseDTO.setTotalListSize(((Long)pagingRequest.getParams()[0]).intValue());
            }


        }
        return pagingResponseDTO;
    }

    private com.beshara.base.paging.impl.PagingResponseDTO getDataWithPaging(PagingRequestDTO pagingRequestDTO) {
        System.out.println(pagingRequestDTO == null);
        int pageIndex = getCurrentPageIndex();
        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));

        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }

        if (isSorting() && getBsnSortingColumnName() != null) {
            List<String> sortingColumnList = new ArrayList<String>();
            bsnPagingRequestDTO.setSortAscending(isSortAscending());
            if (getBsnSortingColumnName().equals(columnNameFullName)) {
                sortingColumnList.add("o.firstName");
                sortingColumnList.add("o.secondName");
                sortingColumnList.add("o.thirdName");
                sortingColumnList.add("o.lastName");
                sortingColumnList.add("o.familyName");
            } else {
                sortingColumnList.add(getBsnSortingColumnName());

            }
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);

        }


        try {

            if (filterDTO.getNonStatus() == null) {
                filterDTO.setNonStatus(0L);
            }
            filterDTO.setPagingRequestDTO(bsnPagingRequestDTO);
            bsnPagingResponseDTO =
                        (com.beshara.base.paging.impl.PagingResponseDTO)InfClientFactory.getKwtCitizensResidentsClient().searchWithPaging(filterDTO);

        } catch (NoResultException ne) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            //getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            //  getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } finally {

        }

        return bsnPagingResponseDTO;
    }


    public void getAll() throws DataBaseException {
        setBsnSortingColumnName(null);
        if (fillTable()) {
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();

            this.repositionPage(0);
            setSelectedRadio("");
            reinitializeSort();
            this.setSearchMode(true);
        }

    }

    private boolean fillTable() {
        try {

            if (isUsingPaging()) {
                resetPageIndex();
                setUpdateMyPagedDataModel(true);
                setPagingRequestDTO(new PagingRequestDTO(BEAN_NAME, "getAllWithPaging"));

            } else {
                setMyTableData(InfClientFactory.getKwtCitizensResidentsClient().search(listFilterDTO));
            }

        } catch (Exception e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
        } finally {

        }
        return true;

    }


    public void getSelectedSataus(ActionEvent e) {

        filterDTO.setNonStatus(Long.valueOf(requestStatust));
        fillTable();
    }


    public void setRequestStatust(String requestStatust) {
        this.requestStatust = requestStatust;
    }

    public String getRequestStatust() {
        return requestStatust;
    }

    public void setSearchDTO(IKwtCitizensResidentsSearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IKwtCitizensResidentsSearchDTO getSearchDTO() {
        return searchDTO;
    }

    public void setListFilterDTO(IKwtCitizensResidentsSearchDTO listFilterDTO) {
        this.listFilterDTO = listFilterDTO;
    }

    public IKwtCitizensResidentsSearchDTO getListFilterDTO() {
        return listFilterDTO;
    }


    public void setUnderStudding(String underStudding) {
        this.underStudding = underStudding;
    }

    public String getUnderStudding() {
        return underStudding;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApprove() {
        return approve;
    }

    public void setRefuse(String refuse) {
        this.refuse = refuse;
    }

    public String getRefuse() {
        return refuse;
    }

    public void setAllValues(String allValues) {
        this.allValues = allValues;
    }

    public String getAllValues() {
        return allValues;
    }

    public String goEdit() {
        getIlleagalAccomodationMaintainBean().setMaintainMode(EDIT_MODE);
        getIlleagalAccomodationMaintainBean().setPageMode(EDIT_MODE);
        getIlleagalAccomodationMaintainBean().setPageDTO(getSelectedDTOS().get(0));
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        String civilId=getSelectedDTOS().get(0).getCode().getKey();
        if(civilId !=null){
            qulIntegrationListBean.setKwtCitizenDTO(null);
      qulIntegrationListBean.setCivilId(Long.parseLong(civilId));
            getPersonalInfoBean().setPageDTO(getSelectedDTOS().get(0));
            getPersonalInfoBean().fillQualIntegration();
        }
        getPersonalInfoBean().setPageMode(EDIT_MODE);
      
        IKwtCitizensResidentsDTO dto = (IKwtCitizensResidentsDTO)getSelectedDTOS().get(0);
        if (dto.getNonStatus() == 2) {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "not_available_edit"));
            return "";
        }
        return "illeagalAccomodationPersonalInfoCase";
    }

    // this method not used

    public String goAdd() {

        getIlleagalAccomodationMaintainBean().setMaintainMode(getPageMode());
        getIntegrationBean().reInitializeBean();

        return "illeagalAccomodationPersonalInfoCase";
    }

    private IlleagalAccomodationMaintainBean getIlleagalAccomodationMaintainBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (IlleagalAccomodationMaintainBean)app.createValueBinding("#{illeagalAccomodationMaintainBean}").getValue(ctx);
    }

    private IlleagalAccomodationPersonalInfo getPersonalInfoBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (IlleagalAccomodationPersonalInfo)app.createValueBinding("#{illeagalAccomodationPersonalInfo}").getValue(ctx);
    }

  

    public IPersonQualificationsDTO getLastPersonQualification() {
        IKwtCitizensResidentsDTO currentDTO = (IKwtCitizensResidentsDTO)this.getMyDataTable().getRowData();
        try {
            return (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getCurrentCentralEmpPersonQul(Long.valueOf(currentDTO.getCode().getKey()));
        } catch (SharedApplicationException e) {
            return InfDTOFactory.createPersonQualificationsDTO();
        } catch (DataBaseException e) {
            return InfDTOFactory.createPersonQualificationsDTO();
        }
    }


    ////////////////// cancel search //////////////////////////////

    public void cancelSearch() {

        Initialization();

        try {
            super.cancelSearch();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        filterDTO.setNonStatus(Long.valueOf(requestStatust));
       filterDTO.setSpecialCaseTypesDTO(null);
        filterDTO.setCivilId(null);
        filterDTO.setName(null);
        fillTable();
        setSearchMode(true);
    }

    /////////////////////////////////////////////////////////////


    /////////////////// added bu suzzan  to handle approve or refuse ////////////////////


    ////////////=============   approve =============//////////////


    public void updateToApproveStatus() {

        IKwtCitizensResidentsDTO citizensResidentsDTO = (IKwtCitizensResidentsDTO)getSelectedDTOS().get(0);

        Long status = citizensResidentsDTO.getNonStatus();

        if (status.equals(underStuding) || status.equals(refused)) {

            citizensResidentsDTO.setNonStatus(approved);
            Boolean updatedKwtDTO;

            try {
                IKwtCitizensResidentsClient client = (IKwtCitizensResidentsClient)getClient();
                updatedKwtDTO = client.updatekwtCitizensResidentStatus(citizensResidentsDTO);


                if (updatedKwtDTO) {
                    setRequestStatust("0");

                    listFilterDTO.setNonStatus(Long.valueOf(requestStatust));

                    //                    this.fillTable();
                    getHighlightedDTOS().clear();
                    getHighlightedDTOS().add(citizensResidentsDTO);
                    generatePagingRequestDTO((String)citizensResidentsDTO.getCode().getKey());

                    setSelectedRadio("");
                    getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(RESOURCE_BUNDLE,
                                                                                      "perform_approve"));
                    listFilterDTO.setNonStatus(Initialization);


                    getSelectedDTOS().clear();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        else {

            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "previous_approved"));
        }


    }


    /////////////===================================///////////////


    ////////////=============   refuse =============//////////////

    public void updateToRefuseStatus() {

        IKwtCitizensResidentsDTO citizensResidentsDTO = (IKwtCitizensResidentsDTO)getSelectedDTOS().get(0);
        Long status = citizensResidentsDTO.getNonStatus();

        if (status.equals(underStuding)) {

            citizensResidentsDTO.setNonStatus(refused);
            Boolean updatedKwtDTO;

            try {
                IKwtCitizensResidentsClient client = (IKwtCitizensResidentsClient)getClient();
                updatedKwtDTO = client.updatekwtCitizensResidentStatus(citizensResidentsDTO);


                if (updatedKwtDTO) {
                    setRequestStatust("0");
                    listFilterDTO.setNonStatus(Long.valueOf(requestStatust));
                    // this.fillTable();
                    getHighlightedDTOS().clear();
                    getHighlightedDTOS().add(citizensResidentsDTO);
                    generatePagingRequestDTO((String)citizensResidentsDTO.getCode().getKey());
                    setSelectedRadio("");
                    getSharedUtil().setSuccessMsgValue(getSharedUtil().messageLocator(RESOURCE_BUNDLE,
                                                                                      "perform_refuse"));
                    listFilterDTO.setNonStatus(Initialization);

                    getSelectedDTOS().clear();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        else {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(RESOURCE_BUNDLE, "refuse_for_only"));

        }


    }


    public void Initialization() {
        setRequestStatust("0");
        listFilterDTO.setNonStatus(Long.valueOf(requestStatust));
        searchName = null;
        setSelectedCategory("0");
        setRequestStatustInSearch("0");
        searchDTO.setName(null);
        searchDTO.setCivilId(null);
        searchDTO.setNonStatus(0L);
        searchDTO.setSpecialCaseTypesDTO(null);
        selectedCategory = "0";


    }


    public void setSortingColumnList(List<String> sortingColumnList) {
        this.sortingColumnList = sortingColumnList;
    }

    public List<String> getSortingColumnList() {
        return sortingColumnList;
    }


    public String getSortByCivil() {

        return columnNameCivil;
    }


    public String getSortBySpecName() {
        return columnNameSpecName;
    }

    public String getSortByFullName() {

        return columnNameFullName;
    }


    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setRequestStatustInSearch(String requestStatustInSearch) {
        this.requestStatustInSearch = requestStatustInSearch;
    }

    public String getRequestStatustInSearch() {
        return requestStatustInSearch;
    }

    public void search() {

        try {


            //////////////to handle search Name ////////////////

            if (searchName != null && !searchName.equals("")) {
                searchDTO.setName(searchName);

            }

            ////////////////////////////////


            ///////////////// to handle الفئة///////////////////////
            Long categoryCode =null;
            ISpecialCaseTypesDTO specialCaseTypesDTO = null;
            if(!selectedCategory.equals("null"))
                categoryCode = Long.valueOf(selectedCategory);
            
            if (categoryCode != null && !categoryCode.equals(0L)) {

                ISpecialCaseTypesEntityKey ek = InfEntityKeyFactory.createSpecialCaseTypesEntityKey(categoryCode);

               

                try {
                    specialCaseTypesDTO =
                            (ISpecialCaseTypesDTO)InfClientFactory.getSpecialCaseTypesClient().getById(ek);
                } catch (Exception e) {
                    e.printStackTrace();
                }

               

            }
            searchDTO.setSpecialCaseTypesDTO(specialCaseTypesDTO);
            ///////////////////////// to handle حالة الطلب  //////////////////////////

            Long requestStatusSearch = Long.valueOf(requestStatustInSearch);
            if (requestStatusSearch != null && !requestStatusSearch.equals(0L)) {

                searchDTO.setNonStatus(requestStatusSearch);
                setRequestStatust(searchDTO.getNonStatus().toString());

            } else {
                setRequestStatust("0");
            }


            filterDTO = searchDTO;

            filterDTO.setNonStatus(Long.valueOf(requestStatustInSearch));
            fillTable();


            if (requestStatustInSearch != null && !requestStatustInSearch.equals("0")) {
                setRequestStatust(requestStatustInSearch);
            } else {
                requestStatustInSearch = "0";
                setRequestStatust("0");
            }


            setSearchMode(false);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setFilterDTO(IKwtCitizensResidentsSearchDTO filterDTO) {
        this.filterDTO = filterDTO;
    }

    public IKwtCitizensResidentsSearchDTO getFilterDTO() {
        return filterDTO;
    }
    
    public void setCatList(List<IBasicDTO> catList) {
        this.catList = catList;
    }

    public List<IBasicDTO> getCatList() {
        if (catList == null || catList.size() == 0) {
            try {
                catList = InfClientFactory.getSpecialCaseTypesClient().getCodeNameExcept10();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return catList;
    }
}

