package com.beshara.csc.hr.emp.presentation.backingbean.empexecute;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.presentation.backingbean.governmentalemployeedatarevision.GovEmpMaintainBean;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalExtraDataTypesDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IGenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class EmpExecuteListBean extends LookUpBaseBean {

    private static final String BEAN_NAME = "empExecuteListBean";
    private static final String NAVIGATION_KEY = "empexecutelist";
    private static final String EMP_EXECUTE_NAVIGATION = "empexecute";
    private static final String METHOD_NAME_RESTORE = "restorePage";
    private final Long KWT_CITIZEN = Long.valueOf(101);
    private String virtualStringValue = ISystemConstant.VIRTUAL_VALUE.toString();

    private Long searchTypeLongVal = 0L;
    private List<IBasicDTO> firstLevelHireTypesList;
    private IPersonQualificationsDTO personQulDTO = InfDTOFactory.createPersonQualificationsDTO();
    List allList = new ArrayList();

    private boolean selectedAllHireTypes;

    private Long loginedMinistrycode = null;


    public EmpExecuteListBean() {

        setPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        super.setSelectedPageDTO(EmpDTOFactory.createEmpCandidatesDTO());
        setClient(EmpClientFactory.getEmpCandidatesClient());
        setSaveSortingState(false);
        setUsingPaging(true);

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowLookupAdd(false);
        app.setShowLookupEdit(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    public String viewEmpDetails() {
        try {
            IEmpCandidatesDTO emp = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
            getIntegrationBean().reInitializeBean();
            getIntegrationBean().setBeanNameTo(GovEmpMaintainBean.BEAN_NAME);
            getIntegrationBean().setActionTo(GovEmpMaintainBean.METHOD_NAME_VIEW);
            getIntegrationBean().setNavgationCaseFrom(NAVIGATION_KEY);
            getIntegrationBean().getHmBaseBeanFrom().put(BEAN_NAME, evaluateValueBinding(BEAN_NAME));
            getIntegrationBean().setBeanNameFrom(BEAN_NAME);
            getIntegrationBean().setActionFrom(METHOD_NAME_RESTORE);
            getIntegrationBean().getHmObjects().put(GovEmpMaintainBean.MAP_KEY_CIVIL_ID, emp.getCode().getKey());
            return getIntegrationBean().goTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String restorePage() {
        return NAVIGATION_KEY;
    }

    public void getAll() throws DataBaseException {

        if (getFilterDTO() == null) {

            if (isUsingPaging()) {

                generatePagingRequestDTO("empExecuteListBean", "getAllWithPaging");

            } else {

                setMyTableData(getAllResult());

                if (getSelectedDTOS() != null)
                    getSelectedDTOS().clear();
                if (getHighlightedDTOS() != null)
                    getHighlightedDTOS().clear();

            }

        }

        this.reinitializeSort();

    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {

        return new PagingResponseDTO(getAllResult());

    }

    private List getAllResult() {

        //SharedUtilBean shared_util = getSharedUtil();
        //List allList = null;

        //        try {
        //
        //            allList = ((IEmployeesClient)getClient()).getAllToStartWork();
        //
        //        } catch (SharedApplicationException ne) {
        //            allList = new ArrayList();
        //            ne.printStackTrace();
        //        } catch (DataBaseException db) {
        //            shared_util.handleException(db);
        //        } catch (Exception e) {
        //            shared_util.handleException(e);
        //        }

        return allList;

    }


    public void cancelSearch() {
        setSelectedRadio("");
        getSelectedDTOS().clear();
        try {
            super.cancelSearch();
            filterByHireType();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search() {

        setSelectedRadio("");
        getSelectedDTOS().clear();

        System.out.println("Calling search()...");
        this.setSearchMode(true);

        if (isUsingPaging()) {

            generatePagingRequestDTO("empExecuteListBean", "searchWithPaging");
            Object[] params = new Object[] { searchTypeLongVal, getSearchQuery() };
            getPagingRequestDTO().setParams(params);

            resetPageIndex();

        } else {


            try {
                setMyTableData(getSearchResult(searchTypeLongVal, getSearchQuery()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();

            this.repositionPage(0);

        }

    }

    public PagingResponseDTO searchWithPaging(PagingRequestDTO request) throws DataBaseException {

        Long searchTypeLongVal = (Long)request.getParams()[0];
        String searchQuery = (String)request.getParams()[1];

        return new PagingResponseDTO(getSearchResult(searchTypeLongVal, searchQuery));

    }

    private List getSearchResult(Long searchTypeLongVal, String searchQuery) {

        List searchResult = null;

        try {

            Long hireTypeKey = Long.valueOf(((IEmpCandidatesDTO)getPageDTO()).getHireTypeKey());


            if (searchTypeLongVal.intValue() == 0) {

                // call search by code
                searchResult =
                        ((IEmpCandidatesClient)getClient()).searchForHireExecute(getLoginedMinistrycode(), Long.parseLong(searchQuery),
                                                                                 null, hireTypeKey);
            } else {


                // call search by Name

                searchResult =
                        ((IEmpCandidatesClient)getClient()).searchForHireExecute(getLoginedMinistrycode(), null, formatSearchString(searchQuery),
                                                                                 hireTypeKey);
            }

        }

        catch (Exception db) {
            searchResult = new ArrayList();

            db.printStackTrace();
        }

        return searchResult;


    }

    public static EmpExecuteListBean getInstance() {
        return (EmpExecuteListBean)JSFHelper.getValue(BEAN_NAME);
    }

    public String goEmpExecute() {

        //TODO locking code
        if (!lock("emp_work_execute")) {
            return null;
        }

        Long firstParentHireTypeCode = null;
        if (getSelectedDTOS() != null && getSelectedDTOS().size() != 0) {
            IEmpCandidatesDTO emp = (IEmpCandidatesDTO)getSelectedDTOS().get(0);
            List personQulList = null;
            IKwtCitizensResidentsDTO kwtCitizenDTO = null;
            try {
                //String civilId = emp.getCode().getKey();
                Long civilId = Long.valueOf(emp.getCitizensResidentsDTO().getCode().getKey());
                kwtCitizenDTO =
                        (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInfoForEmp(InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId));
                ((IEmpCandidatesDTO)getPageDTO()).setCitizensResidentsDTO(kwtCitizenDTO);

                List personalQulList = new ArrayList();
                IPersonQualificationsDTO persontDTO =
                    (IPersonQualificationsDTO)InfClientFactory.getPersonQualificationsClient().getPersonQulificationInfo(civilId);
                personalQulList.add(persontDTO);
                personQulList = personalQulList;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (personQulList != null && personQulList.size() > 0)
                personQulDTO = (IPersonQualificationsDTO)personQulList.get(0);

            EmpExecuteBean empExecuteBean = EmpExecuteBean.getInstance();

            empExecuteBean.setPageDTO(emp);
            firstParentHireTypeCode = Long.valueOf(((IHireTypesDTO)emp.getHireTypesDTO()).getFirstParent().getKey());
            if (IEMPConstants._EMP_CENTERAL_HIRE_TYPE.equals(firstParentHireTypeCode)) {
                empExecuteBean.setCentralEmphireType(true);
            } else {
                empExecuteBean.setCentralEmphireType(false);
            }
            Long cntryCode = ((IGenderCountryEntityKey)kwtCitizenDTO.getCountriesDTO().getCode()).getCntryCode();
            if (cntryCode.equals(KWT_CITIZEN))
                empExecuteBean.setEnableDateOfNextRaise(true);
            else
                empExecuteBean.setEnableDateOfNextRaise(false);
            empExecuteBean.setPersonQulDTO(personQulDTO);
            empExecuteBean.setSelectedAllHireTypes(selectedAllHireTypes);
            fillSalaryElementDTO(empExecuteBean, emp);
        }
        setLastLockingAction(getLastLockingAction());

        return EMP_EXECUTE_NAVIGATION;
    }

    private IEmpCndSalaryElementsDTO fillSalaryElementDTO(EmpExecuteBean empExecuteBean, IEmpCandidatesDTO emp) {
        ISalElementGuidesDTO raiseDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO degreeDTO = SalDTOFactory.createSalElementGuidesDTO();
        ISalElementGuidesDTO groupDTO = SalDTOFactory.createSalElementGuidesDTO();
        List<ISalExtraDataTypesDTO> salExtraDataTypesDTOList = new ArrayList();
        IEmpCndSalaryElementsDTO salaryElementDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
        if (salaryElementDTO.getCode() == null) {
            try {
                salaryElementDTO = EmpClientFactory.getEmpCndSalaryElementsClient().getByCandCode(emp.getCode());
                empExecuteBean.setCountGuid(salaryElementDTO.getSalElementGuidesDTO().getCountGuide());
                String raiseCode =
                    ((ISalElementGuidesEntityKey)salaryElementDTO.getSalElementGuidesDTO().getCode()).getElmguideCode().toString();
                if (salaryElementDTO != null && salaryElementDTO.getCode() != null) {
                    if (raiseCode != null) {
                        raiseDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(Long.valueOf(raiseCode));
                    }
                    try {
                        salExtraDataTypesDTOList =
                                (List)SalClientFactory.getSalGuideExtraDataClient().getExtraDataTypesByElmguideCode(raiseDTO.getCode());
                    } catch (DataBaseException e) {
                        empExecuteBean.setContractType("0");

                    } catch (SharedApplicationException e) {
                        empExecuteBean.setContractType("0");
                    }
                    if (salExtraDataTypesDTOList != null && salExtraDataTypesDTOList.size() > 0) {
                        empExecuteBean.setContractType(salExtraDataTypesDTOList.get(0).getCode().getKey());
                    }
                    //Degree
                    if (raiseDTO.getParentObject() != null && raiseDTO.getParentObject().getCode() != null) {
                        empExecuteBean.setDegreeCode(((ISalElementGuidesEntityKey)raiseDTO.getParentObject().getCode()).getElmguideCode());
                        empExecuteBean.setDegreeName(raiseDTO.getParentObject().getName());
                    }
                    if (empExecuteBean.getDegreeCode() != null) {
                        degreeDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(empExecuteBean.getDegreeCode());
                    }
                    //Group
                    if (degreeDTO.getParentObject() != null && degreeDTO.getParentObject().getCode() != null) {
                        empExecuteBean.setGroupCode(((ISalElementGuidesEntityKey)degreeDTO.getParentObject().getCode()).getElmguideCode());
                        empExecuteBean.setGroupName(degreeDTO.getParentObject().getName());
                    }
                    if (empExecuteBean.getGroupCode() != null) {
                        groupDTO =
                                (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getElmGuideByCode(empExecuteBean.getGroupCode());
                    }
                    //Cader
                    if (groupDTO.getParentObject() != null && groupDTO.getParentObject().getCode() != null) {
                        empExecuteBean.setCaderCode(((ISalElementGuidesEntityKey)groupDTO.getParentObject().getCode()).getElmguideCode());
                        empExecuteBean.setCaderName(groupDTO.getParentObject().getName());
                    }
                    empExecuteBean.setSalaryElementDTO(salaryElementDTO);
                }
            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            } catch (SharedApplicationException e) {
                System.out.println(e.getMessage());
            }
        }
        return salaryElementDTO;
    }

    public void setSearchTypeLongVal(Long searchTypeLongVal) {
        this.searchTypeLongVal = searchTypeLongVal;
    }

    public Long getSearchTypeLongVal() {
        return searchTypeLongVal;
    }

    public void setPersonQulDTO(IPersonQualificationsDTO personQulDTO) {
        this.personQulDTO = personQulDTO;
    }

    public IPersonQualificationsDTO getPersonQulDTO() {
        return personQulDTO;
    }

    public void setFirstLevelHireTypesList(List<IBasicDTO> firstLevelHireTypesList) {
        this.firstLevelHireTypesList = firstLevelHireTypesList;
    }

    public List<IBasicDTO> getFirstLevelHireTypesList() {

        if (firstLevelHireTypesList == null) {


            try {
                firstLevelHireTypesList = EmpClientFactory.getHireTypesClient().getFirstLevelHireTypes();

                initDTO();
            } catch (DataBaseException e) {
                e.printStackTrace();
                firstLevelHireTypesList = new ArrayList<IBasicDTO>();
            }
        }
        return firstLevelHireTypesList;
    }

    public void setVirtualStringValue(String virtualStringValue) {
        this.virtualStringValue = virtualStringValue;
    }

    public String getVirtualStringValue() {
        return virtualStringValue;
    }

    public void filterEmpByHireType(ActionEvent e) {
        filterByHireType();
    }

    public void filterByHireType() {
        if (isSearchMode()) {
            return;
        }
        getPagingBean().clearDTOS();
        generatePagingRequestDTO("0");
        List<IBasicDTO> empList = new ArrayList<IBasicDTO>();
        IEmpCandidatesDTO dto = (IEmpCandidatesDTO)getPageDTO();

        try {
            if (dto.getHireTypeKey() != null && !dto.getHireTypeKey().equals("0")) {
                selectedAllHireTypes = false;
                empList =
                        EmpClientFactory.getEmpCandidatesClient().filterByHireTypeForHireExecute(Long.valueOf(dto.getHireTypesDTO().getCode().getKey()),
                                                                                                 getLoginedMinistrycode());
            } else {

                // enter to All
                selectedAllHireTypes = true;
                // empList = EmpClientFactory.getEmployeesClient().filterByAllHireTypesForHireExecute();


                empList =
                        EmpClientFactory.getEmpCandidatesClient().filterByAllHireTypeForHireExecute(getLoginedMinistrycode());

            }

        } catch (DataBaseException f) {
            f.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isSearchMode()) {
            try {
                cancelSearch();
                setSearchMode(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setAllList(empList);
        setUpdateMyPagedDataModel(true);
        setSelectedRadio("");
    }


    public void setAllList(List allList) {
        this.allList = allList;
    }

    public List getAllList() {
        return allList;
    }

    public void setSelectedAllHireTypes(boolean selectedAllHireTypes) {
        this.selectedAllHireTypes = selectedAllHireTypes;
    }

    public boolean isSelectedAllHireTypes() {
        return selectedAllHireTypes;
    }


    public Long getLoginedMinistrycode() {
        if (loginedMinistrycode == null) {
            try {
                loginedMinistrycode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (loginedMinistrycode == null) {
                loginedMinistrycode = 13L;
            }
        }
        return loginedMinistrycode;
    }


    public void initDTO() {
        try {
            if (super.getMyTableData() == null || super.getMyTableData().size() == 0) {
                IHireStagesEntityKey entityKey = EmpEntityKeyFactory.createHireStagesEntityKey(Long.parseLong("0"));
                IHireStagesDTO hireStagesDTO = EmpDTOFactory.createHireStagesDTO();
                hireStagesDTO.setCode(entityKey);
                ((IEmpCandidatesDTO)getPageDTO()).setHireTypesDTO(hireStagesDTO);
                filterByHireType();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }


    public void clearData() {
        if (isSearchMode() == false) {

            setSearchQuery("");
        }
    }
}
