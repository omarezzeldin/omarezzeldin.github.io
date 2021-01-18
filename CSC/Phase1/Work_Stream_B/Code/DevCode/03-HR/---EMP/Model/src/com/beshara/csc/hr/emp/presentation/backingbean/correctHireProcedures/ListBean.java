package com.beshara.csc.hr.emp.presentation.backingbean.correctHireProcedures;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpCandidatesClient;
import com.beshara.csc.hr.emp.business.client.IEmpReasonDataClient;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpReasonDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IProcedureResultsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidateProceduresEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpReasonDataEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireProceduresEntityKey;
import com.beshara.csc.hr.emp.business.entity.IProcedureResultsEntityKey;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBeanLocal;
import com.beshara.jsfbase.csc.backingbean.lov.EmployeeListOfValues;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class ListBean extends LookUpBaseBeanLocal {
    private Long civilId;
    private boolean validCivilId = false;
    private boolean civilExist = false;
    private boolean noCandidateFound = false;
    private String civilName;
    private String ministryFileNo;
    private Date hireDate;
    private Long candidateCode;
    private List hireProceduresList = new ArrayList();
    private Long selectedProcedure;
    private Date convertDate, postponeDate;
    private Date resultDate;
    private Long selectedProcedureResult, selectedResult;
    private List procedureResults;
    //edit div properties
    private boolean showReasonPanel;
    private String procedureType;
    private List<IEmpReasonDataDTO> reasonsDataList;
    private Long selectedReasonData, selectedAddReasonData;
    private Long candidateCodeSeq = 1L;
    IEmpCandidateProceduresDTO empCandidateProceduresDTO = null;
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private int maintainMode = 0;
    private boolean empInMin = false;


    public ListBean() {
        setEmpListOfValues(new EmployeeListOfValues());
        setSelectedPageDTO(EmpDTOFactory.createEmpCandidateProceduresDTO());
        setLookupEditDiv("m2mEditDivClass");
        setLookupAddDiv("m2mEditDivClass");
    }

    public void initiateBeanOnce() {
        fillHireProceduresList();
        fillProcedureResults();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        app.setShowEmpSrchDiv(true);
        app.setShowLookupAdd(true);
        app.setShowLookupEdit(true);
        return app;

    }

    public void openEditDiv() {
        IEmpCandidateProceduresDTO selectedDTO = (IEmpCandidateProceduresDTO)(getSelectedDTOS().get(0));
        setSelectedPageDTO(selectedDTO);
        IProcedureResultsEntityKey procedureResultsEntityKey =
            (IProcedureResultsEntityKey)selectedDTO.getProcedureResultsDTO().getCode();
        Long proresultCode = procedureResultsEntityKey.getProresultCode();

        if (selectedDTO.getEmpReasonDataDTO() != null &&
            (IEmpReasonDataEntityKey)selectedDTO.getEmpReasonDataDTO().getCode() != null) {
            IEmpReasonDataEntityKey empReasonDataEntityKey =
                (IEmpReasonDataEntityKey)selectedDTO.getEmpReasonDataDTO().getCode();
            Long resdatSerial = empReasonDataEntityKey.getResdatSerial();
            setSelectedReasonData(resdatSerial);
        }
        setSelectedProcedureResult(proresultCode);

        if (proresultCode != null && proresultCode.equals(3L)) {
            setShowReasonPanel(true);
        } else {
            setShowReasonPanel(false);
        }

        fillReasonData();
        setMaintainMode(2);
    }

    private void fillReasonData() {
        IEmpReasonDataClient client = EmpClientFactory.getEmpReasonDataClient();
        try {

            reasonsDataList = (List)client.getByTypeCode(ISystemConstant.ACTIVE_FLAG);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    public void onChangeResult() {


        if (selectedProcedureResult != null && selectedProcedureResult == 3L) {
            setShowReasonPanel(true);
        } else {
            setShowReasonPanel(false);
        }
    }

    public void onChangeAddResult() {
        if (reasonsDataList == null || reasonsDataList.size() == 0) {
            fillReasonData();
        }

        if (getSelectedResult() != null && getSelectedResult().equals(3L)) {
            setShowReasonPanel(true);
        } else {
            setShowReasonPanel(false);
            setPostponeDate(null);
            setSelectedAddReasonData(null);
        }
    }


    public void checkAvailable() throws DataBaseException {
        validCivilId = false;
        civilExist = false;
        empInMin = false;
        noCandidateFound = false;
        if (getCivilId() != null) {
            IEmployeesClient employeesClient = EmpClientFactory.getEmployeesClient();
            IEmpCandidatesClient empCandidatesClient = EmpClientFactory.getEmpCandidatesClient();
            civilExist = GlobalValidation.isValidCivilId(civilId);
            if (civilExist) {
                try {
                    Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
                    IEmployeesDTO employeesDTO =
                        (IEmployeesDTO)employeesClient.getByRealCivilIdAndHireStatusForMov(civilId, minCode);

                    setCivilName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFullName());
                    //                    setPageDTO(employeesDTO);
                    setMinistryFileNo(employeesDTO.getMinistryFileNo());
                    setHireDate(employeesDTO.getHireDate());
                    setCandidateCode(employeesClient.getCandidateCode(Long.valueOf(employeesDTO.getCode().getKey())));
                    if (candidateCode == 0L || candidateCode == null) {
                        setCandidateCode(empCandidatesClient.getCandidateCode(employeesDTO.getRealCivilId()));
                    }
                } catch (SharedApplicationException e) {
                    if (e instanceof EmployeeNotHiredException || e instanceof ItemNotFoundException) {
                        empInMin = true;
                        return;
                    }else
                    e.printStackTrace();
                    validCivilId = false;
                    noCandidateFound = true;
                    setMyTableData(new ArrayList());
                    civilId = null;
                    civilName = "";
                    ministryFileNo = "";
                    hireDate = null;
                    candidateCode = null;
                    return;

                } catch (DataBaseException e) {
                    e.printStackTrace();
                    validCivilId = false;
                    noCandidateFound = true;
                    setMyTableData(new ArrayList());
                    civilId = null;
                    civilName = "";
                    ministryFileNo = "";
                    hireDate = null;
                    candidateCode = null;
                    return;
                }
            } else {
                return;
            }
            validCivilId = true;
            return; // everything ok
        }
        //        setPageDTO(EmpDTOFactory.createEmpDTO());
        setMyTableData(new ArrayList());
        reinitializeSort();
    }

    public void showSearchForEmployeeDiv() {
        EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
        employeeListOfValuesBean.setUseCustomSearch(true);
        employeeListOfValuesBean.setEmpListOfValuesStyle("m2mEditDivClass");
        employeeListOfValuesBean.setReturnMethodName("correctHireProceduresBean.returnMethodAction");
        employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel,searchBtnPnlgrd,OperationBar,scriptpanel");
        employeeListOfValuesBean.resetData();
        employeeListOfValuesBean.setResetDivAfterClose(true);
    }

    public void returnMethodAction() {
        if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
            getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
            validCivilId = true;
            civilExist = true;
            IEmployeesDTO employeesDTO = (IEmployeesDTO)getEmpListOfValues().getSelectedDTOS().get(0);
            //            setPageDTO(employeesDTO);
            setCivilId(employeesDTO.getRealCivilId());
            setCivilName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFullName());
            try {
                checkAvailable();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
    }

    public void showDataTable() {

        List<IEmpCandidateProceduresDTO> empCandidateProceduresDTOList = null;
        try {
            empCandidateProceduresDTOList =
                    EmpClientFactory.getEmpCandidateProceduresClient().getByCandCode(candidateCode);
            //            setSelectedDTOS((List)empCandidateProceduresDTOList);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        setMyTableData(empCandidateProceduresDTOList);
    }

    public void reSetData(ActionEvent ae) {
        ae = null;
        //        setPageDTO(EmpDTOFactory.createEmpDTO());
        setMyTableData(new ArrayList());
        reinitializeSort();
        setSelectedDTOS(new ArrayList<IBasicDTO>());
        validCivilId = false;
        civilExist = false;
        civilId = null;
        civilName = "";
        ministryFileNo = "";
        hireDate = null;
        candidateCode = null;
        noCandidateFound = false;
    }

    public void back() {
        selectedProcedure = null;
        convertDate = null;
        resultDate = null;
        selectedProcedureResult = null;
        selectedResult = null;
        setShowReasonPanel(false);
        setPostponeDate(null);
        setSelectedAddReasonData(null);

    }

    //    public void cancelAdd() {
    //        setSelectedProcedure(null);
    //        setSelectedProcedureResult(null);
    //        setSelectedResult(null);
    //        setConvertDate(null);
    //        setResultDate(null);
    //        setShowReasonPanel(false);
    //        setSelectedAddReasonData(null);
    //        setPostponeDate(null);
    //
    //    }

    public void performAdd() {

        IEmpCandidateProceduresEntityKey empCandidateProceduresEntityKey =
            EmpEntityKeyFactory.createEmpCandidateProceduresEntityKey(getCandidateCode(), candidateCodeSeq,
                                                                      getSelectedProcedure());
        //        empCandidateProceduresEntityKey.setCandidateCode(getCandidateCode());
        //        empCandidateProceduresEntityKey.setCandidateCodeSeq(candidateCodeSeq);
        //        empCandidateProceduresEntityKey.setHirProcedureCode(getSelectedProcedure());

        try {
            empCandidateProceduresDTO =
                    (IEmpCandidateProceduresDTO)EmpClientFactory.getEmpCandidateProceduresClient().getById(empCandidateProceduresEntityKey);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }

        if (empCandidateProceduresDTO == null) {

            try {
                addEmpCand(empCandidateProceduresDTO, empCandidateProceduresEntityKey);
                EmpClientFactory.getEmpCandidateProceduresClient().add(empCandidateProceduresDTO);

                setPageDTO(empCandidateProceduresDTO);
                showDataTable();

                highlightAddRecord();
                //               if (getHighlightedDTOS() != null)
                //                    getHighlightedDTOS().add(empCandidateProceduresDTO);
                //                this.getSharedUtil().setSuccessMsgValue("SuccessAdds");
            } catch (DataBaseException e) {
                e.printStackTrace();
                this.setShowErrorMsg(true);
                this.setErrorMsg("FailureInAdd");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAdd");
                System.out.println(e.getMessage());
            } catch (SharedApplicationException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                this.setShowErrorMsg(true);
                this.setErrorMsg("FailureInAdd");
                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                "FailureInAdd");
            }
        } else {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "added_before"));

            //                getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                                "FailureInAdd");
        }
        back();
    }


    public void addEmpCand(IEmpCandidateProceduresDTO empCandidateProceduresDTO,
                           IEmpCandidateProceduresEntityKey empCandidateProceduresEntityKey) {

        empCandidateProceduresDTO = EmpDTOFactory.createEmpCandidateProceduresDTO();
        empCandidateProceduresDTO.setCode(empCandidateProceduresEntityKey);

        IEmpCandidatesDTO empCandidatesDto = EmpDTOFactory.createEmpCandidatesDTO();
        IEmpCandidatesEntityKey candidateEk = EmpEntityKeyFactory.createEmpCandidatesEntityKey();
        candidateEk.setCandidateCode(getCandidateCode());
        candidateEk.setCandidateCodeSeq(candidateCodeSeq);
        empCandidatesDto.setCode(candidateEk);


        IHireProceduresDTO hireProceduresDTO = EmpDTOFactory.createHireProceduresDTO();
        IHireProceduresEntityKey hireProceduresEk =
            EmpEntityKeyFactory.createHireProceduresEntityKey(getSelectedProcedure());
        hireProceduresDTO.setCode(hireProceduresEk);

        IProcedureResultsDTO procedureResultsDTO = EmpDTOFactory.createProcedureResultsDTO();
        IProcedureResultsEntityKey procedureResultsEntityKey =
            EmpEntityKeyFactory.createProcedureResultsEntityKey(getSelectedResult());
        procedureResultsDTO.setCode(procedureResultsEntityKey);

        empCandidateProceduresDTO.setEmpCandidatesDTO(empCandidatesDto);
        empCandidateProceduresDTO.setHireProceduresDTO(hireProceduresDTO);
        empCandidateProceduresDTO.setProcedureResultsDTO(procedureResultsDTO);

        empCandidateProceduresDTO.setSendDate(getConvertDate());
        empCandidateProceduresDTO.setResultDate(getResultDate());

        setEmpCandidateProceduresDTO(empCandidateProceduresDTO);

        //create postponeReasonDTO

        if (getSelectedAddReasonData() != null) {
            IEmpReasonDataDTO erDTO = EmpDTOFactory.createEmpReasonDataDTO();
            IEmpReasonDataEntityKey ek =
                EmpEntityKeyFactory.createEmpReasonDataEntityKey(1L, getSelectedAddReasonData());
            erDTO.setCode(ek);
            empCandidateProceduresDTO.setEmpReasonDataDTO(erDTO);
            empCandidateProceduresDTO.setPostponeDate(getPostponeDate());
        }


    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
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

    public void setCivilName(String civilName) {
        this.civilName = civilName;
    }

    public String getCivilName() {
        return civilName;
    }

    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    public String getMinistryFileNo() {
        return ministryFileNo;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setNoCandidateFound(boolean noCandidateFound) {
        this.noCandidateFound = noCandidateFound;
    }

    public boolean isNoCandidateFound() {
        return noCandidateFound;
    }

    public void fillHireProceduresList() {
        try {
            hireProceduresList = (List)EmpClientFactory.getHireProceduresClient().getAll();
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
    }


    public void setHireProceduresList(List hireProceduresList) {
        this.hireProceduresList = hireProceduresList;
    }

    public List getHireProceduresList() {
        return hireProceduresList;
    }


    public void setSelectedProcedure(Long selectedProcedure) {
        this.selectedProcedure = selectedProcedure;
    }

    public Long getSelectedProcedure() {
        return selectedProcedure;
    }


    public void setConvertDate(Date convertDate) {
        this.convertDate = convertDate;
    }

    public Date getConvertDate() {
        return convertDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setSelectedProcedureResult(Long selectedProcedureResult) {
        this.selectedProcedureResult = selectedProcedureResult;
    }

    public Long getSelectedProcedureResult() {
        return selectedProcedureResult;
    }

    public void setProcedureResults(List procedureResults) {
        this.procedureResults = procedureResults;
    }

    public List getProcedureResults() {
        return procedureResults;
    }

    public void fillProcedureResults() {
        try {
            procedureResults = EmpClientFactory.getProcedureResultsClient().getAll();
        } catch (DataBaseException e) {
            System.out.println(e.getMessage());
        } catch (SharedApplicationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    public String getProcedureType() {
        return procedureType;
    }

    /**
     *Added By H.Omar @ 20/7/2016
     * Description : reset resonData & postponedDate when their rendered = false
     */
    private void checkProcedureResult() {
        if (!isShowReasonPanel()) {
            setSelectedReasonData(null);
            setReasonsDataList(new ArrayList());
            ((IEmpCandidateProceduresDTO)getSelectedPageDTO()).setPostponeDate(null);
        }
    }

    /**
     *Added By H.Omar @ 20/7/2016
     * Description : update selectedPageDTO with changes
     * @return
     */
    private IEmpCandidateProceduresDTO modifySelectedDTO() {
        IEmpCandidateProceduresDTO selectedDTO = (IEmpCandidateProceduresDTO)getSelectedPageDTO();
        //create procedureResultDTO
        IProcedureResultsEntityKey prEK = EmpEntityKeyFactory.createProcedureResultsEntityKey(selectedProcedureResult);
        IProcedureResultsDTO prDTO = EmpDTOFactory.createProcedureResultsDTO();
        prDTO.setCode(prEK);
        selectedDTO.setProcedureResultsDTO(prDTO);
        //create postponeReasonDTO
        if (selectedReasonData != null) {
            IEmpReasonDataDTO erDTO = EmpDTOFactory.createEmpReasonDataDTO();
            IEmpReasonDataEntityKey ek = EmpEntityKeyFactory.createEmpReasonDataEntityKey(1L, selectedReasonData);
            erDTO.setCode(ek);

            selectedDTO.setEmpReasonDataDTO(erDTO);
        } else {
            selectedDTO.setEmpReasonDataDTO(EmpDTOFactory.createEmpReasonDataDTO());
        }
        return selectedDTO;
    }

    /**
     *Added By H.Omar @ 20/7/2016
     * Description : highlight the updated DTO
     * go to page where updated recored exist
     * show success msg
     */
    private void highlightUpdatedRecord() throws DataBaseException {
        if (super.isUsingPaging()) {
            generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());

        } else {
            getPageIndex((String)getSelectedPageDTO().getCode().getKey());
        }

        if (super.getHighlightedDTOS() != null) {
            super.getHighlightedDTOS().clear();
            super.getHighlightedDTOS().add(this.getSelectedPageDTO());
        }

        super.setShowEdit(false);
        getSharedUtil().setSuccessMsgValue("SuccesUpdated");
    }

    public String getPartString(int partIndex, Object[] parts) {
        Object partValue = parts[partIndex];
        return partValue == null ? "" : partValue.toString();


    }

    private void highlightAddRecord() throws DataBaseException {
        if (super.isUsingPaging()) {
            generatePagingRequestDTO((String)getEmpCandidateProceduresDTO().getCode().getKey());

        } else {
            getPageIndex((String)getEmpCandidateProceduresDTO().getCode().getKey());
        }

        if (super.getHighlightedDTOS() != null) {
            super.getHighlightedDTOS().clear();
            super.getHighlightedDTOS().add(getEmpCandidateProceduresDTO());
        }


        getSharedUtil().setSuccessMsgValue("SuccessAdds");
    }

    /**
     * Added By H.Omar @ 20/7/2016
     * description : update the selected DTO into DB
     */

    public void edit() {
        checkProcedureResult();
        IEmpCandidateProceduresDTO modifiedSelectedDTO = modifySelectedDTO();
        try {
            EmpClientFactory.getEmpCandidateProceduresClient().update(modifiedSelectedDTO);
            showDataTable();
            highlightUpdatedRecord();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }


    public void setReasonsDataList(List<IEmpReasonDataDTO> reasonsDataList) {
        this.reasonsDataList = reasonsDataList;
    }

    public List<IEmpReasonDataDTO> getReasonsDataList() {
        return reasonsDataList;
    }

    public void setSelectedReasonData(Long selectedReasonData) {
        this.selectedReasonData = selectedReasonData;
    }

    public Long getSelectedReasonData() {
        return selectedReasonData;
    }

    public void setShowReasonPanel(boolean showReasonPanel) {
        this.showReasonPanel = showReasonPanel;
    }

    public boolean isShowReasonPanel() {
        return showReasonPanel;
    }

    public void setEmpCandidateProceduresDTO(IEmpCandidateProceduresDTO empCandidateProceduresDTO) {
        this.empCandidateProceduresDTO = empCandidateProceduresDTO;
    }

    public IEmpCandidateProceduresDTO getEmpCandidateProceduresDTO() {
        return empCandidateProceduresDTO;
    }

    public void setMaintainMode(int maintainMode) {
        this.maintainMode = maintainMode;
    }

    public int getMaintainMode() {
        return maintainMode;
    }


    public void setSelectedResult(Long selectedResult) {
        this.selectedResult = selectedResult;
    }

    public Long getSelectedResult() {
        return selectedResult;
    }


    public void setSelectedAddReasonData(Long selectedAddReasonData) {
        this.selectedAddReasonData = selectedAddReasonData;
    }

    public Long getSelectedAddReasonData() {
        return selectedAddReasonData;
    }


    public void setPostponeDate(Date postponeDate) {
        this.postponeDate = postponeDate;
    }

    public Date getPostponeDate() {
        return postponeDate;
    }

    public void setEmpInMin(boolean empInMin) {
        this.empInMin = empInMin;
    }

    public boolean isEmpInMin() {
        return empInMin;
    }
}
