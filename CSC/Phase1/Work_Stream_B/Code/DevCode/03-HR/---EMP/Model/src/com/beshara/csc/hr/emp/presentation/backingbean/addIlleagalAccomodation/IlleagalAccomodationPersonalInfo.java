package com.beshara.csc.hr.emp.presentation.backingbean.addIlleagalAccomodation;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.ilLegalCitizens.IlLegalCitizensListBean;
import com.beshara.csc.inf.business.client.IKwMapClient;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.IStreetZonesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsSearchDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.KwMapDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.qul.integration.presentation.backingbean.listqul.QulIntegrationListBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.util.IntegrationBean;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.component.html.ext.HtmlCommandButton;


public class IlleagalAccomodationPersonalInfo extends ManyToManyDetailsMaintain {

    private final Long DEWAN_CODE = new Long(13);
    private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> genderTypeList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> maritalStatusList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> nonCheckList = new ArrayList<IBasicDTO>();
    private String selectedCategory;
    private String civilId;
    private String unitNo;
    private String orderType;
    private String genderType;
    private String floorNum;
    private String buildingNo;
    private String mobileNum;
    private String phoneNum;
    private String streetName;
    private String maritalStatus;
    private String candidateName;
    private Date birthDate;
    private boolean validCivilId = true;
    private boolean leagalCivilAccomodation = true;
    private boolean newCivil = true;
    private boolean civilExist = false;
    private Boolean searchInEmpMode = false;
    private IKwMapClient kwMapClient = InfClientFactory.getKwMapClient();
    private IStreetZonesClient kwStreetZoneClient = InfClientFactory.getStreetZonesClient();
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    TreeDivBean treepageDivBean;
    private String kwMapName = "";
    private String kwMapCode = "";
    private HtmlCommandButton okBtnLovDiv;
    private EmployeeListOfValues empListOfValues;
    private static final int ADD_MODE = 0;
    private static final int EDIT_MODE = 1;
    private List<IBasicDTO> streetsList = new ArrayList<IBasicDTO>();
    private static final Long REQ_STATUS_FOR_ADD = 1L; // جارى الدراشة
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    KwMapDTO mapDto;
    private boolean dataNotcomplete;
    private String dataNotcompleteErrorMSG;
    private QulIntegrationListBean qulIntegrationListBean =
        (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
    private List<IBasicDTO> catList = new ArrayList<IBasicDTO>();
    public IlleagalAccomodationPersonalInfo() {
        setEmpListOfValues(new EmployeeListOfValues());
        initiateLovDiv();
        setContent1Div("module_tabs_cont");
        treepageDivBean = (TreeDivBean)super.evaluateValueBinding("TreePaggedDivBean");
        treepageDivBean.setShowTreeContent(true);
        treepageDivBean.setBundleName("com.beshara.csc.hr.emp.presentation.resources.emp_");
        //   treepageDivBean.setBundleName("com.beshara.csc.nl.org.presentation.resources.org_");
        treepageDivBean.setRootName("cuntry_erea");
        treepageDivBean.setClient(kwMapClient);
        treepageDivBean.setPageDTO(InfDTOFactory.createKwMapDTO());
        treepageDivBean.setDto(InfDTOFactory.createKwMapDTO());
        treepageDivBean.setDtoDetails(InfDTOFactory.createKwMapDTO());
        treepageDivBean.setEnableSearchByCode(false);
        //        loadDtoValues();
    }

    /**
     * This method is deprecated by code in getters.
     * It is not professional to build the presenation on individual values.
     * It is professional to build it using pageDto connected by the page itself.
     * @deprecated
     */
    private void loadDtoValues() {
        if (getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            this.civilId = String.valueOf(dto.getCivilId());
            this.genderType = String.valueOf(dto.getGentypeCode());
            this.maritalStatus = String.valueOf(dto.getMaritalStatusCode());
            this.birthDate = new Date(dto.getBirthDate().getTime());
            this.selectedCategory = String.valueOf(dto.getSpccsetypeCode());
            this.phoneNum = dto.getPhonesNo();
            this.mobileNum = dto.getMobileNo();
            this.kwMapCode = dto.getMapCode();
            this.streetName = String.valueOf(dto.getStreetCode());
            this.buildingNo = dto.getBuildingNo();
            this.floorNum = String.valueOf(dto.getFloorNo());
            this.unitNo = String.valueOf(dto.getFlatNo());
        }
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showAddeditPage();
        app.setManyToMany(true);
        app.setShowsteps(true);
        app.setShowbar(false);
        app.setShowNavigation(true);
        app.setShowTreediv(true);
        app.setShowCustomDiv1(true);
        return app;
    }

    public void initiateLovDiv() {


        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setUsingPaging(false);
        getLovBaseBean().setMyTableData(new ArrayList());
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().setSearchMode(false);
        if (getLovBaseBean().getSelectedDTOS() != null)
            getLovBaseBean().getSelectedDTOS().clear();


    }

    private boolean validateCivilId(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {

        //Reset Values

        if (kwtCitizensResidentsDTO.getFirstName() == null || kwtCitizensResidentsDTO.getFirstName().equals("")) {
            setDataNotcomplete(true);
            setDataNotcompleteErrorMSG(getSharedUtil().messageLocator(BUNDLE_NAME, "first_name_not_found"));
            return false;
        }
        if (kwtCitizensResidentsDTO.getGenderTypesDTO() == null) {
            setDataNotcomplete(true);
            setDataNotcompleteErrorMSG(getSharedUtil().messageLocator(BUNDLE_NAME, "gender_not_found"));
            return false;
        }
        if (kwtCitizensResidentsDTO.getNationality() == null) {
            setDataNotcomplete(true);
            setDataNotcompleteErrorMSG(getSharedUtil().messageLocator(BUNDLE_NAME, "nationality_not_found"));
            return false;
        }
        //Validate Status
        if (kwtCitizensResidentsDTO.getNationality() != 0) {
            leagalCivilAccomodation = false;
            return false;
        }

        //Validate nationality
        if (kwtCitizensResidentsDTO.getNationality() == 0 && kwtCitizensResidentsDTO.getNonStatus() != null) {
            newCivil = false;
            return false;
        }
        return true;
    }


    public void filterByCivilId() {
        leagalCivilAccomodation = true;
        newCivil = true;
        dataNotcomplete = false;
        civilExist = true;
        validCivilId = true;
        setDataNotcompleteErrorMSG("");
        if (this.getCivilId() != null) {
            try {
                kwtCitizensResidentsDTO =
                        InfClientFactory.getKwtCitizensResidentsClient().getPersonInfo(Long.valueOf(civilId));
            } catch (Exception e) {
                civilExist = false;
                validCivilId = false;
                System.out.println("civilExist ? >>>>>>>>>>>>>>>>>>>>" + civilExist);
                System.out.println("validCivilId ? >>>>>>>>>>>>>>>>>>>>" + validCivilId);
                return;
            }
            if (kwtCitizensResidentsDTO == null) {
                civilExist = false;
                validCivilId = false;
                return;
            }
            if (!validateCivilId(kwtCitizensResidentsDTO)) {
                civilExist = false;
                return;
            }
            if (kwtCitizensResidentsDTO.getNationality() == 0 && kwtCitizensResidentsDTO.getNonStatus() == null) {

                civilExist = true;
                validCivilId = true;
                System.out.println("civilExist ? >>>>>>>>>>>>>>>>>>>>" + civilExist);
                System.out.println("validCivilId ? >>>>>>>>>>>>>>>>>>>>" + validCivilId);

                setCandidateName(getFullNameFromID());
                if (kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey() != null &&
                    !kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey().equals("null")) {
                    setGenderType(kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey());
                }
                if (kwtCitizensResidentsDTO.getMapCode() != null &&
                    !kwtCitizensResidentsDTO.getMapCode().equals("null")) {
                    setKwMapName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                    setKwMapCode(kwtCitizensResidentsDTO.getMapCode());
                    updateStreetListForCivil();
                }
                if (kwtCitizensResidentsDTO.getSpecialCaseTypesDTO() != null &&
                    !kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().equals("null")) {
                    setSelectedCategory(String.valueOf(kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().getCode().getKey()));
                }
                if (kwtCitizensResidentsDTO.getBirthDate() != null &&
                    !kwtCitizensResidentsDTO.getBirthDate().equals("null")) {
                    setBirthDate(new Date(kwtCitizensResidentsDTO.getBirthDate().getTime()));
                }
                if (kwtCitizensResidentsDTO.getMobileNo() != null &&
                    !kwtCitizensResidentsDTO.getMobileNo().equals("null")) {
                    setMobileNum(kwtCitizensResidentsDTO.getMobileNo());
                }
                if (kwtCitizensResidentsDTO.getPhonesNo() != null &&
                    !kwtCitizensResidentsDTO.getPhonesNo().equals("null")) {
                    setPhoneNum(kwtCitizensResidentsDTO.getPhonesNo());
                }
                if (kwtCitizensResidentsDTO.getStreetCode() != null &&
                    !kwtCitizensResidentsDTO.getStreetCode().equals("null")) {
                    setStreetName(String.valueOf(kwtCitizensResidentsDTO.getStreetCode()));
                }
                if (kwtCitizensResidentsDTO.getBuildingNo() != null &&
                    !kwtCitizensResidentsDTO.getBuildingNo().equals("null")) {
                    setBuildingNo(String.valueOf(kwtCitizensResidentsDTO.getBuildingNo()));
                }
                if (kwtCitizensResidentsDTO.getFloorNo() != null &&
                    !kwtCitizensResidentsDTO.getFloorNo().equals("null")) {
                    setFloorNum(String.valueOf(kwtCitizensResidentsDTO.getFloorNo()));
                }
                if (kwtCitizensResidentsDTO.getFlatNo() != null &&
                    !kwtCitizensResidentsDTO.getFlatNo().equals("null")) {
                    setUnitNo(String.valueOf(kwtCitizensResidentsDTO.getFlatNo()));
                }
                if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null &&
                    !kwtCitizensResidentsDTO.getMaritalStatusDTO().equals("null")) {
                    setMaritalStatus(kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode().getKey());
                }
                fillQualIntegration();

            }
        }
    }

    public void fillQualIntegration() {
        getIntegrationBean().reInitializeBean();
        Integer WIZARD_BAR_PAGE = 1;
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        // qulIntegrationListBean.setPersonsInformationDTOList(new ArrayList());
        qulIntegrationListBean.setCivilId(Long.parseLong(getCivilId()));
        if (kwtCitizensResidentsDTO != null && kwtCitizensResidentsDTO.getStatusFlag() != null &&
            kwtCitizensResidentsDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
            qulIntegrationListBean.setKwtCitizenDTO(kwtCitizensResidentsDTO);
        }
        qulIntegrationListBean.setPageType(WIZARD_BAR_PAGE);
        qulIntegrationListBean.setAddQualificationIntegrationpage("AddQualificationIntegration1");
        qulIntegrationListBean.setQulListPageinWizardBar("illeagalAccomodationQualCase");
        qulIntegrationListBean.setSaveInDB(false);
        qulIntegrationListBean.getSaveStateList().add(IlleagalAccomodationMaintainBean.getInstance());
        qulIntegrationListBean.getSaveStateList().add(IlleagalAccomodationMaintainBean.getMaintainBean());
        qulIntegrationListBean.setDataTablestyleClass("dataT-With-3-row-filter");
        qulIntegrationListBean.setCustomCurentQual(true);
        qulIntegrationListBean.setNeedAddQualification(true);
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());
        integrationBean.getHmBaseBeanFrom().put("qulIntegrationBean", qulIntegrationListBean);
    }

    public void hideEmpLovDiv() {
        getEmpListOfValues().setMyTableData(new ArrayList<IBasicDTO>());
        getEmpListOfValues().setSelectedRadio("");
        if (getEmpListOfValues().getSelectedDTOS() != null) {
            getEmpListOfValues().getSelectedDTOS().clear();
        }
        if (getNonCheckList() != null) {
            getNonCheckList().clear();
        }
        getEmpListOfValues().resetData();

    }

    public void reSetData() {
        civilId = "";
        validCivilId = true;
        leagalCivilAccomodation = true;
        newCivil = true;
        civilExist = false;
        candidateName = "";
        setGenderType("");
        setKwMapName("");
        setSelectedCategory("");
        setBirthDate(null);
        setMobileNum("");
        setPhoneNum("");
        setStreetName("");
        setBuildingNo("");
        setFloorNum("");
        setUnitNo("");
        setMaritalStatus("");
    }

    public void showSearchForEmployeeDiv() {
        getEmpListOfValues().setSearchMethod("illeagalAccomodationPersonalInfo.searchEmpResults");
        getEmpListOfValues().setReturnMethodName("illeagalAccomodationPersonalInfo.returnMethodAction");
        getEmpListOfValues().getOkCommandButton().setReRender("employeeMainDataPanel,phonesPanel,addressPanel,dataT_data_panel,paging_panel_group");
        getEmpListOfValues().resetData();
        getEmpListOfValues().setResetDivAfterClose(true);
        getEmpListOfValues().setSearchTyp("0");
        getEmpListOfValues().setSearchQuery("");
        getEmpListOfValues().setMyTableData(new ArrayList());
        getEmpListOfValues().setSelectedDTOS(null);
        getEmpListOfValues().setUseCustomSearch(true);
    }


    public void searchEmpResults(Object searchType, Object searchQuery) {


        getEmpListOfValues().setSearchMode(true);


        IEmployeeSearchDTO searchDTO = EmpDTOFactory.createEmployeeSearchDTO();

        try {
            if (searchQuery != null && !searchQuery.equals("")) {

                if (searchType.toString().equals("0")) {
                    searchDTO.setCivilId(Long.parseLong(searchQuery.toString()));
                } else if (searchType.toString().equals("1")) {
                    searchDTO.setName(searchQuery.toString());
                }
            }
            IKwtCitizensResidentsClient kwtCitizensResidentsClient = InfClientFactory.getKwtCitizensResidentsClient();
            List list = kwtCitizensResidentsClient.searchCandIllegal(searchDTO);

            getEmpListOfValues().setMyTableData(list);
            getEmpListOfValues().getMyDataTable().setFirst(0);
            //  resetEmpDiv(searchDTO);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            getEmpListOfValues().setMyTableData(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
            getEmpListOfValues().setMyTableData(new ArrayList());
        } catch (Exception e) {
            getEmpListOfValues().setMyTableData(new ArrayList());
            e.printStackTrace();
        }

    }


    private boolean empExistInSelectedDTOSList(IBasicDTO dto) {
        if (getSelectedDTOS() != null && !getSelectedDTOS().isEmpty()) {
            int i = getSelectedDTOS().indexOf(dto);
            if (i >= 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    private void resetEmpDiv(IEmployeeSearchDTO searchDTO) {
        getEmpListOfValues().setSearchMode(true);
        getEmpListOfValues().setSearchTyp("0");
        searchDTO.setCivilId(null);
        searchDTO.setName(null);
        getEmpListOfValues().setSearchQuery("");

    }


    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            validCivilId = true;

            reSetData();
            civilExist = true;
            kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)getEmpListOfValues().getSelectedDTOS().get(0);


            setCivilId(kwtCitizensResidentsDTO.getCivilId().toString());
            setCandidateName(getFullNameFromID());
            if (kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey() != null &&
                !kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey().equals("null")) {
                setGenderType(kwtCitizensResidentsDTO.getGenderTypesDTO().getCode().getKey());
            }
            if (kwtCitizensResidentsDTO.getMapCode() != null && !kwtCitizensResidentsDTO.getMapCode().equals("null")) {
                setKwMapName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                setKwMapCode(kwtCitizensResidentsDTO.getMapCode());
                updateStreetListForCivil();
            }
            if (kwtCitizensResidentsDTO.getSpecialCaseTypesDTO() != null &&
                !kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().equals("null")) {
                setSelectedCategory(String.valueOf(kwtCitizensResidentsDTO.getSpecialCaseTypesDTO().getCode().getKey()));
            }
            if (kwtCitizensResidentsDTO.getBirthDate() != null &&
                !kwtCitizensResidentsDTO.getBirthDate().equals("null")) {
                setBirthDate(new Date(kwtCitizensResidentsDTO.getBirthDate().getTime()));
            }
            if (kwtCitizensResidentsDTO.getMobileNo() != null &&
                !kwtCitizensResidentsDTO.getMobileNo().equals("null")) {
                setMobileNum(kwtCitizensResidentsDTO.getMobileNo());
            }
            if (kwtCitizensResidentsDTO.getPhonesNo() != null &&
                !kwtCitizensResidentsDTO.getPhonesNo().equals("null")) {
                setPhoneNum(kwtCitizensResidentsDTO.getPhonesNo());
            }
            if (kwtCitizensResidentsDTO.getStreetCode() != null &&
                !kwtCitizensResidentsDTO.getStreetCode().equals("null")) {
                setStreetName(String.valueOf(kwtCitizensResidentsDTO.getStreetCode()));
            }
            if (kwtCitizensResidentsDTO.getBuildingNo() != null &&
                !kwtCitizensResidentsDTO.getBuildingNo().equals("null")) {
                setBuildingNo(String.valueOf(kwtCitizensResidentsDTO.getBuildingNo()));
            }
            if (kwtCitizensResidentsDTO.getFloorNo() != null && !kwtCitizensResidentsDTO.getFloorNo().equals("null")) {
                setFloorNum(String.valueOf(kwtCitizensResidentsDTO.getFloorNo()));
            }
            if (kwtCitizensResidentsDTO.getFlatNo() != null && !kwtCitizensResidentsDTO.getFlatNo().equals("null")) {
                setUnitNo(String.valueOf(kwtCitizensResidentsDTO.getFlatNo()));
            }
            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null &&
                !kwtCitizensResidentsDTO.getMaritalStatusDTO().equals("null")) {
                setMaritalStatus(kwtCitizensResidentsDTO.getMaritalStatusDTO().getCode().getKey());
            }

            fillQualIntegration();
            if (getSelectedDTOS() == null) {
                setSelectedDTOS(new ArrayList());
            }
            if (getNonCheckList() == null) {
                setNonCheckList(new ArrayList());
            }
            getSelectedDTOS().addAll(getNonCheckList());
            for (IBasicDTO item : getEmpListOfValues().getSelectedDTOS()) {
                if (empExistInSelectedDTOSList(item)) {
                    getSelectedDTOS().remove(item);
                }
            }


            try {
                nonCheckList.clear();
                setSearchInEmpMode(true);
                getAll();
            } catch (DataBaseException e) {
            }


        }
    }


    public Long getLoggedInMinistry() {
        return CSCSecurityInfoHelper.getLoggedInMinistryCode() == null ? DEWAN_CODE :
               CSCSecurityInfoHelper.getLoggedInMinistryCode();
    }

    public void updateKwMapName() {
        String firstName = "";
        String secondName = "";
        String thirdName = "";
        IlleagalAccomodationMaintainBean illeagalAccomodationMaintainBean =
            (IlleagalAccomodationMaintainBean)super.evaluateValueBinding("illeagalAccomodationMaintainBean");
        if (!treepageDivBean.getDtoDetails().getName().equals("")) { // if Div is opened either in add or edit mode
            IKwMapDTO dto = SharedUtils.getACopy((IKwMapDTO)treepageDivBean.getDtoDetails());
            ((IKwtCitizensResidentsDTO)illeagalAccomodationMaintainBean.getPageDTO()).setKwMapDTO(dto);
            firstName = treepageDivBean.getDtoDetails().getName();
            /*
             * 09-12-2014 to get parents if selected
             */
            IKwMapDTO dto1 = (IKwMapDTO)treepageDivBean.getDtoDetails();
            if (dto1.getParentObject() != null) {
                IKwMapDTO dto2 = (IKwMapDTO)dto1.getParentObject();
                secondName = dto2.getName() + "/";
                if (dto2.getParentObject() != null) {
                    IKwMapDTO dto3 = (IKwMapDTO)dto2.getParentObject();
                    thirdName = dto3.getName() + "/";


                }
            }
            kwMapName = thirdName + secondName + firstName;
            // get kwMapCode
            kwMapCode = treepageDivBean.getDtoDetails().getCode().getKey();
            // update StreetList
            updateStreetList();
        }

    }

    //    public void updateKwMapName1() {
    //        String firstName = "";
    //        String secondName = "";
    //        String thirdName = "";
    //        IlleagalAccomodationMaintainBean illeagalAccomodationMaintainBean =
    //            (IlleagalAccomodationMaintainBean)super.evaluateValueBinding("illeagalAccomodationMaintainBean");
    //       // if (!treepageDivBean.getDtoDetails().getName().equals("")) { // if Div is opened either in add or edit mode
    //
    //            ((IKwtCitizensResidentsDTO)illeagalAccomodationMaintainBean.getPageDTO()).setKwMapDTO(mapDto);
    //            firstName = treepageDivBean.getDtoDetails().getName();
    //            /*
    //             * 09-12-2014 to get parents if selected
    //             */
    //            IKwMapDTO dto1 = (IKwMapDTO)treepageDivBean.getDtoDetails();
    //            if (dto1.getParentObject() != null) {
    //                IKwMapDTO dto2 = (IKwMapDTO)dto1.getParentObject();
    //                secondName = dto2.getName() + "/";
    //                if (dto2.getParentObject() != null) {
    //                    IKwMapDTO dto3 = (IKwMapDTO)dto2.getParentObject();
    //                    thirdName = dto3.getName() + "/";
    //
    //
    //                }
    //            }
    //            kwMapName = thirdName + secondName + firstName;
    //            // get kwMapCode
    //            kwMapCode = treepageDivBean.getDtoDetails().getCode().getKey();
    //            // update StreetList
    ////            updateStreetList();
    ////        }
    //
    //    }

    private void updateStreetListForCivil() {
        try {
            setStreetsList(kwStreetZoneClient.getByMapCode(kwtCitizensResidentsDTO.getMapCode()));
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    private void updateStreetList() {
        try {
            setStreetsList(kwStreetZoneClient.getByMapCode(kwMapCode));
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    public void openTreeKwmap() {
        try {
            treepageDivBean.setSearchMode(false);
            treepageDivBean.cancelSearchTree();
            treepageDivBean.setShowTreeContent(true);
            treepageDivBean.setSearchQuery("");
            treepageDivBean.setSerachResult(false);
            treepageDivBean.setSearchType(1);
            treepageDivBean.setDtoDetails(new KwMapDTO());

            MethodBinding mb =
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{illeagalAccomodationPersonalInfo.updateKwMapName}",
                                                                                       null);
            ValueBinding vb =
                FacesContext.getCurrentInstance().getApplication().createValueBinding("#{(!TreePaggedDivBean.enabledRoot )&& (TreePaggedDivBean.selectionNo==1)}");
            treepageDivBean.getOkCommandButton().setValueBinding("rendered", vb);
            treepageDivBean.getOkCommandButton().setAction(mb);
            treepageDivBean.getOkCommandButton().setReRender("selectedNodeField");
            if (treepageDivBean.isSearchMode() == true) {
                treepageDivBean.setSearchMode(true);
            } else {
                treepageDivBean.setSearchMode(false);
                treepageDivBean.setSearchQuery("");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        if (civilId == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getCode() != null && dto.getCode().getKey() != null) {

                this.civilId = String.valueOf(dto.getCode().getKey());
                //  fillQualIntegration();
            }
        }

        return civilId;
    }

    public void setValidCivilId(boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public boolean isValidCivilId() {
        return validCivilId;
    }

    public void setCivilExist(boolean civilExist) {
        this.civilExist = civilExist;
    }

    public boolean isCivilExist() {
        return civilExist;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateName() {
        if (candidateName == null && getPageDTO() != null) {
            candidateName = getFullNameFromPageDTO();
        }
        return candidateName;
    }

    public void setSelectedCategory(String selectedCategory) {
        setCategoryInputHidden(selectedCategory);
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedCategory() {
        if ((selectedCategory == null || selectedCategory.equals("null")) && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if(dto.getSpecialCaseTypesDTO() != null &&(selectedCategory == null || !selectedCategory.equals("null"))){
            this.selectedCategory = String.valueOf(dto.getSpecialCaseTypesDTO().getCode().getKey());
            }
            setCategoryInputHidden(selectedCategory);
        }
        return selectedCategory;
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

    public void setMaritalStatusList(List<IBasicDTO> maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }

    public List<IBasicDTO> getMaritalStatusList() {
        if (maritalStatusList == null || maritalStatusList.isEmpty()) {
            try {
                maritalStatusList = InfClientFactory.getMaritalStatusClient().getAll();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return maritalStatusList;
    }

    public void setGenderTypeList(List<IBasicDTO> genderTypeList) {
        this.genderTypeList = genderTypeList;
    }

    public List<IBasicDTO> getGenderTypeList() {
        if (genderTypeList == null || genderTypeList.size() == 0) {
            try {
                genderTypeList = InfClientFactory.getGenderTypesClient().getAll();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return genderTypeList;
    }

    public void setTreepageDivBean(TreeDivBean treepageDivBean) {
        this.treepageDivBean = treepageDivBean;
    }

    public TreeDivBean getTreepageDivBean() {
        return treepageDivBean;
    }

    public void setKwMapName(String kwMapName) {
        this.kwMapName = kwMapName;
    }

    public String getKwMapName() {
        IlleagalAccomodationMaintainBean illeagalAccomodationMaintainBean =
            (IlleagalAccomodationMaintainBean)super.evaluateValueBinding("illeagalAccomodationMaintainBean");
        //kwMapName = "" ; //Tree Div case see openTreeKwmap method
        String firstName = "";
        String secondName = "";
        String thirdName = "";
        if (illeagalAccomodationMaintainBean.getMaintainMode() != 0 &&
            ((IKwtCitizensResidentsDTO)illeagalAccomodationMaintainBean.getPageDTO()).getKwMapDTO() !=
            null) { // in edit/view mode return the value from dto
            if (((IKwtCitizensResidentsDTO)illeagalAccomodationMaintainBean.getPageDTO()).getKwMapDTO() != null)
                firstName =
                        ((IKwtCitizensResidentsDTO)illeagalAccomodationMaintainBean.getPageDTO()).getKwMapDTO().getName();

            /*
            * 09-12-2014 to get parents if selected
            */
            IKwMapDTO dto1 = ((IKwtCitizensResidentsDTO)illeagalAccomodationMaintainBean.getPageDTO()).getKwMapDTO();
            if (dto1.getParentObject() != null) {
                IKwMapDTO dto2 = (IKwMapDTO)dto1.getParentObject();
                secondName = dto2.getName() + "/";
                if (dto2.getParentObject() != null) {
                    IKwMapDTO dto3 = (IKwMapDTO)dto2.getParentObject();
                    thirdName = dto3.getName() + "/";


                }
            }
            kwMapName = thirdName + secondName + firstName;
            // get kwMapCode
            kwMapCode =
                    ((IKwtCitizensResidentsDTO)illeagalAccomodationMaintainBean.getPageDTO()).getKwMapDTO().getCode().getKey();
            // update StreetList
            updateStreetList();


        }
        return kwMapName;
    }

    public void setKwMapCode(String kwMapCode) {
        this.kwMapCode = kwMapCode;
    }

    public String getKwMapCode() {
        if (kwMapCode == "" && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            mapDto = (KwMapDTO)(InfDTOFactory.createKwMapDTO());
            IEntityKey key = InfEntityKeyFactory.createKwMapEntityKey(dto.getMapCode());
            mapDto.setCode(key);
            //String KMapname=  mapDto.getName();
            //     this.kwMapCode = KMapname;
            //        updateKwMapName1();
        }
        return kwMapCode;
    }

    public void setStreetsList(List<IBasicDTO> streetsList) {
        this.streetsList = streetsList;
    }

    public List<IBasicDTO> getStreetsList() {
        return streetsList;
    }

    public void setGenderType(String genderType) {
        setGenderInputHidden(genderType);
        this.genderType = genderType;
    }

    public String getGenderType() {
        if (genderType == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getGenderTypesDTO().getCode().getKey() != null &&
                !dto.getGenderTypesDTO().getCode().getKey().equals("null")) {
                this.genderType = String.valueOf(dto.getGenderTypesDTO().getCode().getKey());
                setGenderInputHidden(genderType);
            }

        }
        return genderType;
    }

    public void setMaritalStatus(String maritalStatus) {
        setMaritalStatusInputHidden(maritalStatus);
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        if (maritalStatus == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getMaritalStatusDTO().getCode().getKey() != null &&
                !dto.getMaritalStatusDTO().getCode().getKey().equals("null")) {
                this.maritalStatus = String.valueOf(dto.getMaritalStatusDTO().getCode().getKey());
                setMaritalStatusInputHidden(maritalStatus);
            }
        }
        return maritalStatus;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        if (birthDate == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getBirthDate() != null && !dto.getBirthDate().equals("null")) {
                this.birthDate = new Date(dto.getBirthDate().getTime());
            }
        }
        return birthDate;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getMobileNum() {
        if (mobileNum == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getMobileNo() != null && !dto.getMobileNo().equals("null")) {
                this.mobileNum = dto.getMobileNo();
            }
        }
        return mobileNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        if (phoneNum == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getPhonesNo() != null && !dto.getPhonesNo().equals("null")) {
                this.phoneNum = dto.getPhonesNo();
            }
        }
        return phoneNum;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetName() {
        if (streetName == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getStreetCode() != null && !dto.getStreetCode().equals("null")) {
                this.streetName = String.valueOf(dto.getStreetCode());
            }
        }
        return streetName;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getBuildingNo() {
        if (buildingNo == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getBuildingNo() != null && !dto.getBuildingNo().equals("null")) {
                this.buildingNo = String.valueOf(dto.getBuildingNo());
            }

        }
        return buildingNo;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getFloorNum() {
        if (floorNum == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getFloorNo() != null && !dto.getFloorNo().equals("null")) {
                this.floorNum = String.valueOf(dto.getFloorNo());
            }

        }
        return floorNum;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getUnitNo() {
        if (unitNo == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getFlatNo() != null && !dto.getFlatNo().equals("null")) {
                this.unitNo = String.valueOf(dto.getFlatNo());
            }
        }
        return unitNo;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        if (orderType == null && getPageDTO() != null) {
            KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
            if (dto.getNonStatus() != null && !dto.getNonStatus().equals("null")) {

                this.orderType = String.valueOf(dto.getNonStatus());

            }
        }
        return orderType;
    }


    public void setEmpListOfValues(EmployeeListOfValues empListOfValues) {
        this.empListOfValues = empListOfValues;
    }

    public EmployeeListOfValues getEmpListOfValues() {
        return empListOfValues;
    }

    public void setNonCheckList(List<IBasicDTO> nonCheckList) {
        this.nonCheckList = nonCheckList;
    }

    public List<IBasicDTO> getNonCheckList() {
        return nonCheckList;
    }

    public void setSearchInEmpMode(Boolean searchInEmpMode) {
        this.searchInEmpMode = searchInEmpMode;
    }

    public Boolean getSearchInEmpMode() {
        return searchInEmpMode;
    }


    /**
     * A.Mostafa
     */
    public void saveOrUpdatePageDTO() {
        //Start Call Update Or Save
        IKwtCitizensResidentsSearchDTO dtoFiltered = InfDTOFactory.createKwtCitizensResidentsSearchDTO();
        dtoFiltered.setNonStatus(0L);
        try {
            IKwtCitizensResidentsClient kwtCitizensResidentsClient = InfClientFactory.getKwtCitizensResidentsClient();
            if(qulIntegrationListBean.getKwtCitizenDTO() == null){
                     qulIntegrationListBean.loadKwtCitizenDTO();
                     }
                     if (qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList() != null &&
                         qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList().size() > 0) {
                     Long currntQualCount=0L;
                             for (IPersonsInformationDTO dto : qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList()) {
                                 
                                 if(dto.getPersonQualificationsDTO() == null){
                                       continue;
                                     }
                                 
                                     if( dto.getPersonQualificationsDTO().isCurrentQualStatus()){
                                         currntQualCount += 1;
                                        
                                     }
                             } 
                             if(currntQualCount == 0 ){
                                 String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "select_one_qual");
                                 getSharedUtil().setErrMsgValue(message);
                                 return;
                             }else if(currntQualCount > 1 ){
                                     String message = getSharedUtil().messageLocator(IEMPConstants.EMP_RESOURCES, "select_one_qual_only");
                                     getSharedUtil().setErrMsgValue(message);
                                     return;
                                 }
                     }
            
            List<IPersonsInformationDTO> qualDto = new ArrayList<IPersonsInformationDTO>();
            List<IPersonsInformationDTO> deletedPersonsInformationDTOList = new ArrayList<IPersonsInformationDTO>();
            if (qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList() != null &&
                      qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList().size() > 0) {
                          for (IPersonsInformationDTO dto : qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList()) {
                              if( dto.getPersonQualificationsDTO() != null){
                                  if(   dto.getPersonQualificationsDTO().isCurrentQualStatus()){
                                  dto.getPersonQualificationsDTO().setCurrentQual(1L);
                                  }else{
                                          dto.getPersonQualificationsDTO().setCurrentQual(0L);   
                                      }
                              }
                              qualDto.add(dto);
                          } 
                      }
                  if (qulIntegrationListBean.getKwtCitizenDTO().getDeletedPersonsInformationDTOList() != null &&
                      qulIntegrationListBean.getKwtCitizenDTO().getDeletedPersonsInformationDTOList().size() > 0) {
                          deletedPersonsInformationDTOList =((qulIntegrationListBean.getKwtCitizenDTO().getDeletedPersonsInformationDTOList()));
                      }

            if (getPageMode() == ADD_MODE) {


                try {
                    KwtCitizensResidentsDTO dto = buildIllegalCitizinDTO();
                    dto.setNonStatus(REQ_STATUS_FOR_ADD);
                    KwtCitizensResidentsDTO newDto =
                        (KwtCitizensResidentsDTO)kwtCitizensResidentsClient.updateDTO(dto, kwtCitizensResidentsDTO,
                                                                                      qualDto,deletedPersonsInformationDTOList);
                    getSharedUtil().setSuccessMsgValue("SuccessAdds");
                    setPageDTO(newDto);
                    getIlLegalCitizensListBean().getHighlightedDTOS().clear();
                    getIlLegalCitizensListBean().getHighlightedDTOS().add(getPageDTO());
                    getIlLegalCitizensListBean().setRequestStatust("0");
                    getIlLegalCitizensListBean().setFilterDTO(dtoFiltered);


                } catch (Exception ex) {
                    getSharedUtil().handleException(ex, BUNDLE_NAME, "addIllegalException");
                    ex.printStackTrace();
                }

            } else if (getPageMode() == EDIT_MODE) { //Edit

                try {
                    KwtCitizensResidentsDTO dto = buildIllegalCitizinDTO();

                    kwtCitizensResidentsClient.updateDTO(dto, null, qualDto,deletedPersonsInformationDTOList);
                    getSharedUtil().setSuccessMsgValue("SuccessAdds");
                    getIlLegalCitizensListBean().getHighlightedDTOS().clear();
                    getIlLegalCitizensListBean().getHighlightedDTOS().add(getPageDTO());
                    getIlLegalCitizensListBean().setRequestStatust("0");
                    getIlLegalCitizensListBean().setFilterDTO(dtoFiltered);
                } catch (Exception ex) {
                    getSharedUtil().handleException(ex, BUNDLE_NAME, "editIllegalException");
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
        }


    }

    private boolean checkQul() {
        QulIntegrationListBean qulIntegrationListBean =
            (QulIntegrationListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{qulListIntegrationBean}").getValue(FacesContext.getCurrentInstance());
        List<IPersonsInformationDTO> list = qulIntegrationListBean.getKwtCitizenDTO().getPersonsInformationDTOList();
        boolean qualNotExist = true;
        for (IPersonsInformationDTO dto : list) {
            if (dto.getPersonQualificationsDTO() != null) {
                qualNotExist = false;
                break;
            }
        }
        return qualNotExist;


    }

    private IlLegalCitizensListBean getIlLegalCitizensListBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        return (IlLegalCitizensListBean)app.createValueBinding("#{ilLegalCitizensListBean}").getValue(ctx);
    }

    public void openLovDiv(ActionEvent ae) {
        Boolean manyToMany = (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }

        BaseBean currentBaseBean = (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");
        //if(!isMultiSelect()){
        Integer listSize = (Integer)evaluateValueBinding(beanNameBindingKey + ".lovBaseBean.selectedListSize");
        Boolean flage = listSize == 0 ? true : false;
        if (getOkBtnLovDiv() != null)
            getOkBtnLovDiv().setDisabled(flage);
        //}
        //Paging
        if (isUsingPaging()) {
            super.changePageIndex(ae);
        }

    }

    /**
     * A.MOSTAFA
     * @return
     */

    private KwtCitizensResidentsDTO buildIllegalCitizinDTO() throws SharedApplicationException {
        try {
            Long genderType =
                this.genderType != null && !this.genderType.equals("null") && !this.genderType.isEmpty() ?
                Long.parseLong(this.genderType.trim()) : null;
            Long unitNo =
                this.unitNo != null && !this.unitNo.equals("null") && !this.unitNo.isEmpty() ? Long.parseLong(this.unitNo.trim()) :
                null;
            Long floorNum =
                this.floorNum != null && !this.floorNum.equals("null") && !this.floorNum.isEmpty() ? Long.parseLong(this.floorNum.trim()) :
                null;
            Long streetName =
                this.streetName != null && !this.streetName.equals("null") && !this.streetName.trim().isEmpty() ?
                Long.parseLong(this.streetName.trim()) : null;
            Long maritalStatus =
                this.maritalStatus != null && !this.maritalStatus.equals("null") && !this.maritalStatus.isEmpty() ?
                Long.parseLong(this.maritalStatus.trim()) : null;
            Long selectedCategory =
                this.selectedCategory != null && !this.selectedCategory.equals("null") && !this.selectedCategory.isEmpty() ?
                Long.parseLong(this.selectedCategory.trim()) : null;
            Timestamp birthDate =
                this.birthDate != null && !this.birthDate.equals("null") ? new Timestamp(this.birthDate.getTime()) :
                null;

            KwtCitizensResidentsDTO dto =
                new KwtCitizensResidentsDTO.Builder().civilId(Long.parseLong(this.civilId.trim())).gentypeCode(genderType).maritalStatusCode(maritalStatus).birthDate(birthDate).spccsetypeCode(selectedCategory).phonesNo(phoneNum).mobileNo(mobileNum).mapCode(kwMapCode).streetCode(streetName).buildingNo(buildingNo).floorNo(floorNum).flatNo(unitNo).build();
            if(dto.getSpccsetypeCode() == null){
                    dto.setSpecialCaseTypesDTO(null);
                }
            return dto;
        } catch (Exception ex) {
            throw new SharedApplicationException();
        }


    }

    public void setOkBtnLovDiv(HtmlCommandButton okBtnLovDiv) {
        this.okBtnLovDiv = okBtnLovDiv;
    }

    public HtmlCommandButton getOkBtnLovDiv() {
        return okBtnLovDiv;
    }

    private String getFullNameFromID() {
        String name = "";

        if (kwtCitizensResidentsDTO != null) {
            if (kwtCitizensResidentsDTO.getFirstName() != null &&
                !kwtCitizensResidentsDTO.getFirstName().equals("null")) {
                name += kwtCitizensResidentsDTO.getFirstName();
                name += " ";
            }
            if (kwtCitizensResidentsDTO.getSecondName() != null &&
                !kwtCitizensResidentsDTO.getSecondName().equals("null")) {
                name += kwtCitizensResidentsDTO.getSecondName();
                name += " ";
            }
            if (kwtCitizensResidentsDTO.getThirdName() != null &&
                !kwtCitizensResidentsDTO.getThirdName().equals("null")) {
                name += kwtCitizensResidentsDTO.getThirdName();
                name += " ";
            }
            if (kwtCitizensResidentsDTO.getLastName() != null &&
                !kwtCitizensResidentsDTO.getLastName().equals("null")) {
                name += kwtCitizensResidentsDTO.getLastName();
                name += " ";
            }
            if (kwtCitizensResidentsDTO.getFamilyName() != null &&
                !kwtCitizensResidentsDTO.getFamilyName().equals("null")) {
                name += kwtCitizensResidentsDTO.getFamilyName();

            }
        }
        return name;
    }

    private String getFullNameFromPageDTO() {
        String name = "";
        KwtCitizensResidentsDTO dto = (KwtCitizensResidentsDTO)getPageDTO();
        if (dto != null) {
            if (dto.getFirstName() != null && !dto.getFirstName().equals("null")) {
                name += dto.getFirstName();
                name += " ";
            }
            if (dto.getSecondName() != null && !dto.getSecondName().equals("null")) {
                name += dto.getSecondName();
                name += " ";
            }
            if (dto.getThirdName() != null && !dto.getThirdName().equals("null")) {
                name += dto.getThirdName();
                name += " ";
            }
            if (dto.getLastName() != null && !dto.getLastName().equals("null")) {
                name += dto.getLastName();
                name += " ";
            }
            if (dto.getFamilyName() != null && !dto.getFamilyName().equals("null")) {
                name += dto.getFamilyName();

            }
        }
        return name;
    }

    private String genderInputHidden;

    public void setGenderInputHidden(String genderInputHidden) {
        this.genderInputHidden =
                genderInputHidden != null && genderInputHidden.equals("null") ? "" : genderInputHidden;
    }

    public String getGenderInputHidden() {
        return genderInputHidden;
    }

    private String maritalStatusInputHidden;

    public void setMaritalStatusInputHidden(String maritalStatusInputHidden) {
        this.maritalStatusInputHidden =
                maritalStatusInputHidden != null && maritalStatusInputHidden.equals("null") ? "" :
                maritalStatusInputHidden;
    }

    public String getMaritalStatusInputHidden() {
        return maritalStatusInputHidden;
    }

    private String categoryInputHidden;

    public void setCategoryInputHidden(String categoryInputHidden) {
        this.categoryInputHidden =
                categoryInputHidden != null && categoryInputHidden.equals("null") ? "" : categoryInputHidden;
    }

    public String getCategoryInputHidden() {
        return categoryInputHidden;
    }

    public void setLeagalCivilAccomodation(boolean leagalCivilAccomodation) {
        this.leagalCivilAccomodation = leagalCivilAccomodation;
    }

    public boolean isLeagalCivilAccomodation() {
        return leagalCivilAccomodation;
    }

    public void setNewCivil(boolean newCivil) {
        this.newCivil = newCivil;
    }

    public boolean isNewCivil() {
        return newCivil;
    }

    public void setDataNotcomplete(boolean dataNotcomplete) {
        this.dataNotcomplete = dataNotcomplete;
    }

    public boolean isDataNotcomplete() {
        return dataNotcomplete;
    }

    public void setDataNotcompleteErrorMSG(String dataNotcompleteErrorMSG) {
        this.dataNotcompleteErrorMSG = dataNotcompleteErrorMSG;
    }

    public String getDataNotcompleteErrorMSG() {
        return dataNotcompleteErrorMSG;
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
