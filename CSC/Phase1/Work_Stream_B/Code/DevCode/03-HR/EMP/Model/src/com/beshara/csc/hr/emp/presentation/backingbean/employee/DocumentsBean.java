package com.beshara.csc.hr.emp.presentation.backingbean.employee;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.documentAttachments.DocumentAttachmentsIntegrationBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class DocumentsBean extends ManyToManyDetailsMaintain {


    private static final String SEARCH_TYPE_BY_CODE = "0";
    private static final String SEARCH_TYPE_BY_NAME = "1";
    private static final String EMP_RESOURCES = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private String searchTypeStr = SEARCH_TYPE_BY_NAME;
    boolean attachementStatus;
    boolean docStatus;


    //Constructor.

    public DocumentsBean() {
        setDivMainContentMany("divSearchContent1FixedCustom");
        //setLookupAddDiv("lookupAddDivClass500X800");
        setContent1Div("module_tabs_cont1");
        setLookupAddDiv("m2mAddDivClass");
        setAttachementStatus(true);
        setDocStatus(false);
    }

    public static DocumentsBean getInstance() {
        return (DocumentsBean)JSFHelper.getValue("empDocumentsBean");
    }
    //All Methods.

    /**
     * Purpose:Override appMainLayoutBuilder to setShowContent1 true and ShowSearch false
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  31-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowSearch(false);
        app.setShowContent1(true);
        return app;
    }

    public void selectedAvailable(ActionEvent event) throws Exception {
        event = null; // unused
        try {

            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedAvailableDetails());

            IClientDTO selected = (IClientDTO)this.getAvailableDataTable().getRowData();
            selected.setChecked(!(s.contains(selected)));
            if (selected.getChecked()) {


                try {
                    s.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            if (!(selected.getChecked())) {


                selected.setChecked(Boolean.TRUE);
                s.remove(selected);
                selected.setChecked(Boolean.FALSE);


            }

            this.getSelectedAvailableDetails().clear();
            this.getSelectedAvailableDetails().addAll(s);


        } catch (Exception e) {
            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }


    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        IRequiredDocumentsDTO requiredDocumentsDTO = (IRequiredDocumentsDTO)dto;
        IEmpCandidateDocumentsDTO detailDTO = EmpDTOFactory.createEmpCandidateDocumentsDTO();
        detailDTO.setDocumentTypesDTO(requiredDocumentsDTO.getDocumentTypeDTO());
        detailDTO.setStatusFlag(requiredDocumentsDTO.getStatus());
        return detailDTO;
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        IEmpCandidateDocumentsDTO empCandidateDocumentsDTO = (IEmpCandidateDocumentsDTO)dto;
        IRequiredDocumentsDTO codeNameDTO = EmpDTOFactory.createRequiredDocumentsDTO();
        codeNameDTO.setDocumentTypeDTO((IInfDocumentTypesDTO)empCandidateDocumentsDTO.getDocumentTypesDTO());
        return codeNameDTO;
    }

    /**
     * Purpose:Method that fills Div
     * Creation/Modification History :
     * 1.1 - Developer Name: Sherif Omar.
     * 1.2 - Date:  31-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void getListAvailable() throws DataBaseException, SharedApplicationException {

        try {
            MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");
            String hireTypeCode = maintainBean.getSelectedHireTypeKey();
            // String hireType =
            //    ((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireTypeKey();
            if (hireTypeCode != null && !hireTypeCode.equals("")) {
                IHireTypesEntityKey HirentityKey =
                    EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(hireTypeCode));
                //                System.out.println(EmpClientFactory.getEmployeeDocumentsClient().listAvailableEntities((IEmployeesEntityKey)this.getMasterEntityKey(),
                //                                                                                                       HirentityKey).size() +
                //                                   "_______________3lashan semo_______________");

                setAvailableDetails(EmpClientFactory.getEmpCandidateDocumentsClient().listAvailableEntitiesByKwtCitizenCode(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO(),
                                                                                                                            HirentityKey));
                //                    EmpClientFactory.getRequiredDocumentsClient()

                int aaaaa = getSelectedAvailableListSize();

            }
            //else
            //    setAvailableDetails(EmpClientFactory.getEmployeeDocumentsClient().listAvailableEntities(null));
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (DataBaseException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (Exception e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        }
    }

    /**
     * Purpose:Action Method of Search Button in documents div.
     * Creation/Modification History :
     * 1.1 - Developer Name: Yassmine Ali Mohamed.
     * 1.2 - Date:  31-7-2008
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void searchAvailable() throws DataBaseException, SharedApplicationException {
        try {
            MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");

            String hireTypeCode = "";
            hireTypeCode = ((IEmpCandidatesDTO)maintainBean.getPageDTO()).getHireTypesDTO().getCode().getKey();
            if (hireTypeCode != null && !hireTypeCode.equals("")) {
                IHireTypesEntityKey HirentityKey =
                    EmpEntityKeyFactory.createHireTypesEntityKey(Long.parseLong(hireTypeCode));

                if (searchTypeStr.toString().equals(SEARCH_TYPE_BY_CODE)) {
                    setAvailableDetails(EmpClientFactory.getEmpCandidateDocumentsClient().filterAvailableEntities(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO(),
                                                                                                                  HirentityKey,
                                                                                                                  null,
                                                                                                                  Long.parseLong(this.getSearchString())));
                } else {
                    setAvailableDetails(EmpClientFactory.getEmpCandidateDocumentsClient().filterAvailableEntities(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO(),
                                                                                                                  HirentityKey,
                                                                                                                  this.formatSearchString(this.getSearchString()),
                                                                                                                  null));
                }

            }

            int sss = getAvailableDetails().size();
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


    public void removeCurrentFromAvailable() {

        System.out.println("removing current");
        if (getCurrentDetails() != null && availableDetails != null)
            for (int j = 0; j < getCurrentDetails().size(); j++) {
                IBasicDTO dto = getCurrentDetails().get(j);
                IBasicDTO codeNameDTO = this.mapToCodeNameDTO(dto);

                boolean exist = false;
                for (int i = 0; i < availableDetails.size(); i++) {
                    IBasicDTO availableDTO = availableDetails.get(i);

                    if (((IRequiredDocumentsDTO)codeNameDTO).getDocumentTypeDTO().getCode().getKey().equals(((IRequiredDocumentsDTO)availableDTO).getDocumentTypeDTO().getCode().getKey())) {
                        exist = true;
                        if (dto.getStatusFlag() != null &&
                            dto.getStatusFlag().longValue() != ISystemConstant.DELEDTED_ITEM) {
                            availableDetails.remove(availableDTO);
                        }

                        if (dto.getStatusFlag() == null) {

                            availableDetails.remove(availableDTO);
                        }
                    }
                }
                if (!exist && dto.getStatusFlag() != null &&
                    dto.getStatusFlag().longValue() == ISystemConstant.DELEDTED_ITEM) {

                    if (isSearchMode() == true &&
                        containSubString(((IRequiredDocumentsDTO)codeNameDTO).getDocumentTypeDTO().getName()))
                        availableDetails.add(codeNameDTO);
                    else if (isSearchMode() == false)
                        availableDetails.add(codeNameDTO);
                }
            }
    }


    public void add() {
        if (getCurrentDetails() == null)
            setCurrentDetails(new ArrayList<IBasicDTO>());
        if (getSelectedAvailableDetails() != null)
            for (int i = 0; i < getSelectedAvailableDetails().size(); i++) {
                IBasicDTO dto = getSelectedAvailableDetails().get(i);
                boolean exist = false;
                for (int j = 0; j < getCurrentDetails().size(); j++) {
                    IBasicDTO current = getCurrentDetails().get(j);
                    IBasicDTO mappedCurrent = this.mapToCodeNameDTO(current);
                    if (((IRequiredDocumentsDTO)mappedCurrent).getDocumentTypeDTO().getCode().getKey().equals(((IRequiredDocumentsDTO)dto).getDocumentTypeDTO().getCode().getKey())) {
                        exist = true;
                        if (current.getStatusFlag() != null &&
                            current.getStatusFlag().longValue() == ISystemConstant.DELEDTED_ITEM) {
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
                    mdto.setStatusFlag(ISystemConstant.ADDED_ITEM);
                    getCurrentDetails().add(mdto);

                    //                    try {
                    //                        getCurrentDetails().add((IBasicDTO)getSharedUtil().deepCopyObject(mdto));
                    //                    } catch (Exception e) {
                    //                        e.printStackTrace();
                    //                    }
                    availableDetails.remove(dto);
                    getSelectedAvailableDetails().remove(i);
                    i--;
                }
            }
        // goFirstPage(this.getAvailableDataTable());
        this.restoreDetailsTablePosition(this.getAvailableDataTable(), availableDetails);
        this.resetSelection();
        try {
            this.cancelSearchAvailable();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        }
    }

    //    public IBasicDTO getCurrentSelectedDetail(IBasicDTO selected) {
    //        for (IBasicDTO dto : getCurrentDetails()) {
    //            if (((IRequiredDocumentsDTO)selected).getDocumentTypeDTO().getCode().getKey().equals(((IRequiredDocumentsDTO)this.mapToCodeNameDTO(dto)).getDocumentTypeDTO().getCode().getKey())) {
    //                return dto;
    //            }
    //        }
    //        return null;
    //    }

    public IBasicDTO getCurrentSelectedDetail(IBasicDTO selected) {
        if (selected == null)
            return null;
        String dtoCode, selectedCode = ((IRequiredDocumentsDTO)selected).getDocumentTypeDTO().getCode().getKey();
        IBasicDTO dto = null;
        for (int i = 0; i < getCurrentDetails().size(); i++) {
            dto = getCurrentDetails().get(i);
            if (dto == null)
                continue;
            dtoCode = ((IRequiredDocumentsDTO)this.mapToCodeNameDTO(dto)).getDocumentTypeDTO().getCode().getKey();
            if (dtoCode.equals(selectedCode))
                break;
        }
        return dto;
    }

    public void delete() {
        if (currentDetails == null)
            currentDetails = new ArrayList<IBasicDTO>();
        if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() > 0) {


            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                IBasicDTO selected = getSelectedCurrentDetails().get(i);
                if (getCurrentSelectedDetail(selected).getStatusFlag() == null) {
                    getCurrentSelectedDetail(selected).setStatusFlag(deleted);
                    //((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    getSelectedCurrentDetails().remove(i);
                    i--;
                }
                if (getCurrentSelectedDetail(selected).getStatusFlag().longValue() == added.longValue()) {
                    //currentDetails.remove(getCurrentSelectedDetail(selected));

                    IEmpCandidateDocumentsDTO empCandDTO;
                    String empCandDTOCode, selectedCode =
                        ((IRequiredDocumentsDTO)selected).getDocumentTypeDTO().getCode().getKey();
                    for (int z = 0; z < getCurrentDetails().size(); z++) {
                        empCandDTO = (IEmpCandidateDocumentsDTO)getCurrentDetails().get(z);
                        if (empCandDTO == null)
                            continue;
                        empCandDTOCode = empCandDTO.getDocumentTypesDTO().getCode().getKey();
                        if (empCandDTOCode.equals(selectedCode)) {
                            currentDetails.remove(z);
                            break;
                        }
                    }
                    //bugs
                    // ((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    availableDetails.add(selected);

                    getSelectedCurrentDetails().remove(i);
                    i--;
                }

            }


        }
        // goFirstPage(this.getAvailableDataTable());
        restoreDetailsTablePosition(this.getCurrentDataTable(), this.getCurrentDetails());
        resetSelection();
    }

    public String back() {
        initAttachmentStatus();
        return "empdocuments";
    }

    private void initAttachmentStatus() {
        List<IBasicDTO> list = getDocumentAttachmentsIntegrationBean().getCurrentDisplayList();
        String code = getDocumentAttachmentsIntegrationBean().getSelectedDocType();
        IEmpCandidateDocumentsDTO currentRow = (IEmpCandidateDocumentsDTO)getSelectedRow(code);
        if (list == null || list.isEmpty()) {
            currentRow.setAttachmentStatusFlag(false);
        }
        currentRow.setAttachmentsLst(getDocumentAttachmentsIntegrationBean().getAttachmentList());
        MainDataBean maindataBean = (MainDataBean)evaluateValueBinding("empMainDataBean");
        maindataBean.fillQualIntegration(maindataBean.getCivilId());
    }

    private IBasicDTO getSelectedRow(String code) {
        for (IBasicDTO basicDTO : getCurrentDisplayDetails()) {
            String rowCode = ((IEmpCandidateDocumentsDTO)basicDTO).getDocumentTypesDTO().getCode().getKey();
            if (rowCode.equals(code)) {
                return basicDTO;
            }
        }
        return null;
    }

    public String addIntegrationDocument() {

        getIntegrationBean().reInitializeBean();
        getIntegrationBean().setNavgationCaseFrom("empdocuments");
        getIntegrationBean().setBeanNameFrom("empDocumentsBean");
        getIntegrationBean().setActionFrom("back");
        getIntegrationBean().setActionTo("goAdd");

        IEmpCandidateDocumentsDTO empCandidateDocumentsDTO =
            (IEmpCandidateDocumentsDTO)getCurrentDataTable().getRowData();
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");

        getIntegrationBean().getHmBaseBeanFrom().put("empDocumentsBean", DocumentsBean.getInstance());
        getIntegrationBean().getHmBaseBeanFrom().put("empMaintainBean", maintainBean.getInstance());

        Long civilId =
            Long.valueOf(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
        Long docTypeCode = Long.valueOf(empCandidateDocumentsDTO.getDocumentTypesDTO().getCode().getKey());

        String fullName = ((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO().getName();
        String docTypeName = empCandidateDocumentsDTO.getDocumentTypesDTO().getName();


        getDocumentAttachmentsIntegrationBean().setCivilId(civilId);
        getDocumentAttachmentsIntegrationBean().setEmpName(fullName);
        getDocumentAttachmentsIntegrationBean().setSelectedDocType(docTypeCode.toString());
        getDocumentAttachmentsIntegrationBean().setDoctypeName(docTypeName);
        getDocumentAttachmentsIntegrationBean().setDisableSelectedDocType(true);
        getDocumentAttachmentsIntegrationBean().setModuleName("emp");
        getDocumentAttachmentsIntegrationBean().addDocAttachment(empCandidateDocumentsDTO.getAttachmentsLst());
        getDocumentAttachmentsIntegrationBean().setForShowAttachmentOnly(false);
        return "adddocAttachmentsPageInEdit";

    }

//    private AddDocAttachmentsIntegrationBean getAddDocAttachmentsIntegrationBean() {
//        return (AddDocAttachmentsIntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{addDocAttachmentsIntegrationBean}").getValue(FacesContext.getCurrentInstance());
//
//    }
    
    private DocumentAttachmentsIntegrationBean getDocumentAttachmentsIntegrationBean() {
        return (DocumentAttachmentsIntegrationBean)JSFHelper.getValue("documentAttachmentsIntegrationBean");
    }
    
    public void checkAboutAttachmentForCivil(ActionEvent event) {
        IEmpCandidateDocumentsDTO empCandidateDocumentsDTO =
            (IEmpCandidateDocumentsDTO)getCurrentDataTable().getRowData();
        MaintainBean maintainBean = (MaintainBean)evaluateValueBinding("empMaintainBean");

        Long civilId =
            Long.valueOf(((IEmpCandidatesDTO)maintainBean.getPageDTO()).getCitizensResidentsDTO().getCode().getKey());
        Long docTypeCode = Long.valueOf(empCandidateDocumentsDTO.getDocumentTypesDTO().getCode().getKey());
        Long count = 0L;
        if(empCandidateDocumentsDTO.getAttachmentsLst() != null){
            for(Object obj : empCandidateDocumentsDTO.getAttachmentsLst()){
                if(((IBasicDTO)obj).getStatusFlag() == null || ((IBasicDTO)obj).getStatusFlag().equals(ISystemConstant.ADDED_ITEM)){
                    count += 1;
                }else if(((IBasicDTO)obj).getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)){
                    count -= 1;
                }
            }
        }
        if (count <= 0L) {
            try {
                count +=
                        EmpClientFactory.getEmpCandidateDocumentsClient().checkAboutAttachmentForCivil(civilId, docTypeCode);
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        if (count <= 0L) {
            String message = getSharedUtil().messageLocator(EMP_RESOURCES, "attachment_not_attached");
            getSharedUtil().setErrMsgValue(message);
            ((IEmpCandidateDocumentsDTO)getCurrentDataTable().getRowData()).setAttachmentStatusFlag(false);
        }
    }

    public void selectedDocStatus(javax.faces.event.ActionEvent event) {

        if (((IEmpCandidateDocumentsDTO)getCurrentDataTable().getRowData()).getDocStatus() == 0L) {
            ((IEmpCandidateDocumentsDTO)getCurrentDataTable().getRowData()).setAttachmentStatusFlag(false);
            ((IEmpCandidateDocumentsDTO)getCurrentDataTable().getRowData()).setAttachmentStatusEnabled(true);
        } else {
            ((IEmpCandidateDocumentsDTO)getCurrentDataTable().getRowData()).setAttachmentStatusEnabled(false);
        }
    }

    @Override
    public void selectedCurrentAll(javax.faces.event.ActionEvent event) throws Exception {
        event = null; // unused
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        try {
            Set<IBasicDTO> s = new HashSet<IBasicDTO>();
            s.addAll(this.getSelectedCurrentDetails());
            int first = this.getCurrentDataTable().getFirst();
            //Integer rowsCountPerPage=(Integer)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.noOfTableRows}").getValue(FacesContext.getCurrentInstance());
            Integer rowsCountPerPage = getCurrentDataTable().getRowCount();
            if (rowsCountPerPage == null) {
                throw new Exception("#{shared_util.noOfTableRows} return null");
            }
            int count = rowsCountPerPage.intValue();
            for (int j = first; j < first + count; j++) {
                this.getCurrentDataTable().setRowIndex(j);
                if (!this.getCurrentDataTable().isRowAvailable())
                    break;
                IBasicDTO selected = (IBasicDTO)this.getCurrentDataTable().getRowData();
                if (isCheckAllFlag() == true) {
                    s.add(this.mapToCodeNameDTO(selected));
                    list.add(this.mapToCodeNameDTO(selected));
                }
                if (isCheckAllFlag() == false) {
                    s.remove(this.mapToCodeNameDTO(selected));
                    list.remove(this.mapToCodeNameDTO(selected));
                }

            }
            this.getSelectedCurrentDetails().clear();
            this.getSelectedCurrentDetails().addAll(list);
        } catch (Exception e) {

            System.out.println(e.toString());

        }

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


    public void setAttachementStatus(boolean attachementStatus) {
        this.attachementStatus = attachementStatus;
    }

    public boolean isAttachementStatus() {
        return attachementStatus;
    }

    public void setDocStatus(boolean docStatus) {
        this.docStatus = docStatus;
    }

    public boolean isDocStatus() {
        return docStatus;
    }
}
