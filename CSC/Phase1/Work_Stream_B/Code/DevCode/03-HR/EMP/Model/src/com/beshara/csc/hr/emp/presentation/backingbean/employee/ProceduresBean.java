package com.beshara.csc.hr.emp.presentation.backingbean.employee;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidateProceduresClient;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.HireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCriminalStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpMoiWsCriminalDTO;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireProceduresEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProceduresBean extends ManyToManyDetailsMaintain {

    private static final String SEARCH_TYPE_BY_CODE = "0";
    private static final String SEARCH_TYPE_BY_NAME = "1";
    public static final String BEAN_NAME = "empProceduresBean";
    private String searchTypeStr = SEARCH_TYPE_BY_NAME;
    private List<IBasicDTO> proceduresResultList = new ArrayList();
    //private List proceduresResultList = new ArrayList();
    private List suspensionReasonsList;

    private String suspensionReasonCode;
    private int procedureRowIndex;
    private Date postponeDate;
    private MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
    private String errorCode;
    private String criminalResult= "";
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    IEmpCandidateProceduresDTO empProceduresDTO = EmpDTOFactory.createEmpCandidateProceduresDTO();
    private boolean hasCriminalCase;
    private boolean showBlock;
    private String reportUrl = "rep=2090&_paramsP_CIVIL=";
    //Constructor

    public ProceduresBean() {

        setClient(EmpClientFactory.getEmpCandidateProceduresClient());
        setDivMainContentMany("divSearchContent1FixedCustom");
        setContent1Div("module_tabs_cont1");
        setSelectedPageDTO(EmpDTOFactory.createEmpCandidateProceduresDTO());
        setLookupAddDiv("m2mAddDivClass");
        setLookupEditDiv("m2mAddDivClass");
    }

    public boolean validateStep(String stepKey) {
        boolean valid = super.validateStep(stepKey);
        checkProcedureResults();
        return valid;
    }

    public boolean validate() {
        boolean valid = super.validate();
        checkProcedureResults();
        return valid;
    }

    public static ProceduresBean getInstance() {
        return (ProceduresBean)JSFHelper.getValue(BEAN_NAME);
    }

    private void checkProcedureResults() {
        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)evaluateValueBinding("empMaintainBean.pageDTO");
        try {
            for (IBasicDTO basicDTO : empCandidatesDTO.getEmpCandidateProceduresList()) {
                String key = ((IEmpCandidateProceduresDTO)basicDTO).getProcedureResultKey();
                if (!(key.equals(IEMPConstant.EMP_RESULT_SUSPENDED.toString()))) {
                    ((IEmpCandidateProceduresDTO)basicDTO).setEmpReasonDataDTO(null);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    public void setProceduresResultList(List proceduresResultList) {
        this.proceduresResultList = proceduresResultList;
    }

    public List<IBasicDTO> getProceduresResultList() {
        return proceduresResultList;
    }

    public void fillProcedureList() {
        proceduresResultList = new ArrayList();
        if (proceduresResultList != null && proceduresResultList.isEmpty()) {
            try {
                proceduresResultList = EmpClientFactory.getProcedureResultsClient().getAll();
            } catch (Exception e) {
                e.printStackTrace();
                proceduresResultList = new ArrayList();
            }
        }
    }

    /**
     * Purpose:Override appMainLayoutBuilder to setShowLookupAdd setShowContent1 true and ShowSearch false
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  31-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowLookupAdd(true);
        app.setShowLookupEdit(true);
        app.setShowSearch(false);
        app.setShowContent1(true);
        app.setShowpaging(false);
        return app;
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        IEmpCandidateProceduresDTO detailDTO = EmpDTOFactory.createEmpCandidateProceduresDTO();
        /////////////////////////////////tessssssssssssssssssst  192014
        HireProceduresDTO dto1 = (HireProceduresDTO)dto;
        detailDTO.setHireProceduresDTO(dto1);
        //detailDTO.setProcedureResultsDTO((IProcedureResultsDTO)getProceduresResultList());
        return detailDTO;
    }

    public void selectedAvailableAll() throws Exception {

        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedAvailableDetails());


            this.getSelectedAvailableDetails().clear();
            this.getSelectedAvailableDetails().addAll(s);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void update(){
        
            currentDetails.clear();
        }
    public void add() {

        if (currentDetails == null)
            currentDetails = new ArrayList<IBasicDTO>();
        if (getSelectedAvailableDetails() != null)
            for (int i = 0; i < getSelectedAvailableDetails().size(); i++) {
                IBasicDTO dto = getSelectedAvailableDetails().get(i);

                boolean exist = false;

                for (int j = 0; j < currentDetails.size(); j++) {
                    IBasicDTO current = currentDetails.get(j);
                    IBasicDTO mappedCurrent = this.mapToCodeNameDTO(current);

                    if (mappedCurrent.getCode().getKey().equals(dto.getCode().getKey())) {

                        exist = true;
                        if (current.getStatusFlag() != null &&
                            current.getStatusFlag().longValue() == deleted.longValue()) {
                            current.setStatusFlag(null);
                            this.resetDetailDTO(current);
                            availableDetails.remove(dto);
                            getSelectedAvailableDetails().remove(i);
                            i--;
                        }
                    }
                    System.out.println("add inner loop");
                }

                if (!exist) {

                    IBasicDTO mdto = this.mapToDetailDTO(dto);
                    mdto.setStatusFlag(added);
                    if (!((IEmpCandidateProceduresDTO)mdto).getHireProceduresDTO().getCode().getKey().equals("2")) {
                        currentDetails.add(mdto);
                        hasCriminalCase = false;
                    } else {
                        hasCriminalCase = true;
                    }


                    availableDetails.remove(dto);
                    i--;
                }
                System.out.println("add end");

            }
        // goFirstPage(this.getAvailableDataTable());
        this.restoreDetailsTablePosition(this.getAvailableDataTable(), availableDetails);

        this.resetSelection();

        try {
            this.cancelSearchAvailable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        return ((IEmpCandidateProceduresDTO)dto).getHireProceduresDTO();
    }

    /**
     * Purpose:Method that fills detail dataTable
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  31-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void fillListAvailable() throws DataBaseException, SharedApplicationException {
        try {
            // if (this.getMasterEntityKey() != null)


            MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
            String hireTypeCode = maintainBean.getSelectedHireTypeKey();
            //     String hireType =
            //        ((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireTypeKey();
            if (hireTypeCode != null && !hireTypeCode.equals("")) {
                IHireTypesEntityKey HirentityKey =
                    EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(hireTypeCode));

                setAvailableDetails(((IEmpCandidateProceduresClient)getClient()).listAvailableEntities(this.getMasterEntityKey(),
                                                                                                       HirentityKey));

                setSelectedAvailableDetails(getAvailableDetails());
            }
            // else
            //     setAvailableDetails(((IEmployeeProceduresClient)getClient()).listAvailableEntities(null));
        } catch (DataBaseException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (Exception e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        }
    }

    /**
     * Purpose:Action Method of Search Button in procedures div.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  31-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void searchAvailable() throws DataBaseException, SharedApplicationException {
        try {
            MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
            String hireTypeCode = maintainBean.getSelectedHireTypeKey();
            //   String hireType =
            //        ((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireTypeKey();

            IHireTypesEntityKey HirentityKey =
                EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(hireTypeCode));

            if (searchTypeStr.toString().equals(SEARCH_TYPE_BY_CODE)) {
                setAvailableDetails(EmpClientFactory.getEmpCandidateProceduresClient().filterAvailableEntitiesByCode((IEmpCandidatesEntityKey)this.getMasterEntityKey(),
                                                                                                                     Long.parseLong(this.getSearchString()),
                                                                                                                     HirentityKey));
            } else {
                setAvailableDetails(EmpClientFactory.getEmpCandidateProceduresClient().filterAvailableEntitiesbyHireType((IEmpCandidatesEntityKey)this.getMasterEntityKey(),
                                                                                                                         HirentityKey,
                                                                                                                         this.formatSearchString(this.getSearchString())));
            }


        } catch (SharedApplicationException e) {
            setSearchMode(true);
            setSearchString("");
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (DataBaseException e) {
            setAvailableDetails(new ArrayList<IBasicDTO>());
            e.printStackTrace();
        } catch (Exception e) {
            setAvailableDetails(new ArrayList<IBasicDTO>());
            e.printStackTrace();
        }
        super.searchAvailable();

    }

    public void showSuspensionReasonsDiv() {
        getCurrentDataTable().setRowIndex(procedureRowIndex);
        IEmpCandidateProceduresDTO selectedDTO =
            (IEmpCandidateProceduresDTO)getCurrentDetails().get(procedureRowIndex);
        setSelectedPageDTO(selectedDTO);
        if (selectedDTO.getEmpReasonDataDTO() != null) {
            suspensionReasonCode = selectedDTO.getEmpReasonDataDTO().getCode().getKey();
        }
        postponeDate = selectedDTO.getPostponeDate();

    }

    public void saveSuspensionReasonCode() {
        IEmpCandidateProceduresDTO selectedDTO = (IEmpCandidateProceduresDTO)getSelectedPageDTO();
        ((IEmpCandidateProceduresDTO)(getCurrentDetails().get(procedureRowIndex))).setEmpReasonDataKey(suspensionReasonCode);
        ((IEmpCandidateProceduresDTO)(getCurrentDetails().get(procedureRowIndex))).setPostponeDate(postponeDate);

    }

    public void setSuspensionReasonsList(List suspensionReasonsList) {
        this.suspensionReasonsList = suspensionReasonsList;
    }

    public List getSuspensionReasonsList() {
        if (suspensionReasonsList == null || suspensionReasonsList.size() == 0) {
            try {
                suspensionReasonsList =
                        EmpClientFactory.getEmpReasonDataClient().getByTypeCode(IEMPConstant.EMP_REASON_TYPES_PROC_SUSPENSION);
            } catch (Exception e) {
                suspensionReasonsList = new ArrayList();
                e.printStackTrace();
            }
        }
        return suspensionReasonsList;
    }

    public void setSuspensionReasonCode(String suspensionReasonCode) {
        this.suspensionReasonCode = suspensionReasonCode;
    }

    public String getSuspensionReasonCode() {
        return suspensionReasonCode;
    }

    public String getEmpResultSuspended() {
        return IEMPConstant.EMP_RESULT_SUSPENDED.toString();
    }

    public void setEmpResultSuspended(String empResultSuspended) {
        System.out.println(empResultSuspended);
    }

    public void setProcedureRowIndex(int procedureRowIndex) {
        this.procedureRowIndex = procedureRowIndex;
    }

    public int getProcedureRowIndex() {
        return procedureRowIndex;
    }

    public String getSearchTypeByCode() {
        return SEARCH_TYPE_BY_CODE;
    }

    public String getSearchTypeByName() {
        return SEARCH_TYPE_BY_NAME;
    }

    public void setSearchTypeStr(String searchTypeStr) {
        this.searchTypeStr = searchTypeStr;
    }

    public String getSearchTypeStr() {
        return searchTypeStr;
    }

    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {

        super.cancelSearchAvailable();
        searchTypeStr = SEARCH_TYPE_BY_NAME;
    }

    public void setPostponeDate(Date postponeDate) {
        this.postponeDate = postponeDate;
    }

    public Date getPostponeDate() {
        return postponeDate;
    }

    public void openReport() {

        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
        Long civilId =
            Long.valueOf(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
        String P_CIVIL = "P_CIVIL";
        if (reportUrl.indexOf(P_CIVIL) != -1) {
            reportUrl = reportUrl.replace("P_CIVIL" + "=", P_CIVIL + "=" + civilId);
        }


        String stringcaller = "openReportWindow('reportUrl')";
        setJavaScriptCaller(stringcaller);

    }

    public void saveAndCheckWebService() {
       
        IEmpCandidatesClient client = EmpClientFactory.getEmpCandidatesClient();
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
        Long civilId =
            Long.valueOf(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO().getCode().getKey());


        try {
            setErrorCode(client.queryCrimnalStatus(civilId));
        } catch (Exception e) {
            setErrorCode("0");
        }

        try {
            List statusList =null;
            try {
                statusList = EmpClientFactory.getEmpCriminalStatusClient().searchByCode(getErrorCode());
            } catch (Exception e) {
                setErrorCode("0");
                
                statusList = EmpClientFactory.getEmpCriminalStatusClient().searchByCode(getErrorCode());
            }
            IEmpMoiWsCriminalDTO moiDto = EmpDTOFactory.createEmpMoiWsCriminalDTO();


            Long count = EmpClientFactory.getEmpMoiWsCriminalClient().getCriminalCount(civilId);
            if (count != 3L) {
                empProceduresDTO.setCrmStatusCode(getErrorCode());
                IHireProceduresEntityKey ek = EmpEntityKeyFactory.createHireProceduresEntityKey(2L);
                IHireProceduresDTO hireProceduresDTO = EmpDTOFactory.createHireProceduresDTO();
                hireProceduresDTO.setCode(ek);
                

                moiDto.setCivilId(civilId);
                moiDto.setUserCode(CSCSecurityInfoHelper.getUser().getCode());
                moiDto.setCrmstatusCode(getErrorCode());
                moiDto.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
                EmpClientFactory.getEmpMoiWsCriminalClient().add(moiDto);
                String successMsgValue = getSharedUtil().messageLocator(BUNDLE_NAME, "criminal_Case_success_msg");
                Long reminder = 2L - count;
                successMsgValue = successMsgValue.replace("v", reminder.toString());
                getSharedUtil().setSuccessMsgValue(successMsgValue);
                setCriminalResult(((IEmpCriminalStatusDTO)statusList.get(0)).getName());
                if (getErrorCode().equals("1")) {
                    empProceduresDTO.setCrmChecked(true);
                    empProceduresDTO.setCrmLetterDate(null);
                    empProceduresDTO.setCrmLetterNo("");
                    empProceduresDTO.setCrmSheetNo("");
                    showBlock = false;
                } else if (getErrorCode().equals("2")) {
                    empProceduresDTO.setCrmChecked(false);
                    empProceduresDTO.setCrmLetterDate(null);
                    empProceduresDTO.setCrmLetterNo("");
                    empProceduresDTO.setCrmSheetNo("");
                    showBlock = false;
                } else {
                    showBlock = true;
                }
                empProceduresDTO.setHireProceduresDTO(hireProceduresDTO);
            } else {
                getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "criminal_Case_error_msg"));
                return;
            }

        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }


    public void setCriminalResult(String criminalResult) {
        this.criminalResult = criminalResult;
    }

    public String getCriminalResult() {
        return criminalResult;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setEmpProceduresDTO(IEmpCandidateProceduresDTO empProceduresDTO) {
        this.empProceduresDTO = empProceduresDTO;
    }

    public IEmpCandidateProceduresDTO getEmpProceduresDTO() {
        return empProceduresDTO;
    }

    public void setHasCriminalCase(boolean hasCriminalCase) {
        this.hasCriminalCase = hasCriminalCase;
    }

    public boolean isHasCriminalCase() {
        return hasCriminalCase;
    }

    public void setShowBlock(boolean showBlock) {
        this.showBlock = showBlock;
    }

    public boolean isShowBlock() {
        return showBlock;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public String getReportUrl() {
        return reportUrl;
    }
}
