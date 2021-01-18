package com.beshara.csc.hr.emp.presentation.backingbean.hiretypes;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IRequiredDocumentsClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IRequiredDocumentsEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.event.ActionEvent;


public class DocumentTypeMaintain extends ManyToManyDetailsMaintain {
    private String selectedGender;
    // private List<IGenderTypesDTO> genderList = null;
    private Long nationality_Kuwaiti = ISystemConstant.NATIONALITY_KUWAITI;
    private Long nationality_NonKuwaiti = ISystemConstant.NATIONALITY_NON_KUWAITI;
    private Long nationality_NonSpecified = ISystemConstant.NATIONALITY_NON_SPECIFIED;
    private boolean documentStatus = true;
    

    private Long searchTypeLongVal = 0L;
    private String searchTypeLongVal2 = "0";
    private boolean showMsg = false;
    


    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";

    public DocumentTypeMaintain() {
        //        setDivMainContent("divMainContentMany2");

        setDivMainContentMany("divMainContentMany2");
        setContent1Div("module_tabs_cont1");
    }
    //add by Ahmed Khaled
    public void selectedDocStatus(javax.faces.event.ActionEvent event)  {
        if (((IRequiredDocumentsDTO)getCurrentDataTable().getRowData()).getBasicStatus()==0L){
         
            ((IRequiredDocumentsDTO)getCurrentDataTable().getRowData()).setAttachmentRequiredBoolean(false);
        }
    }


    // abdelsabour
    @Override
    public void delete() {
        Long count = null;
        Long hireTypeCode = ((IRequiredDocumentsEntityKey)this.getCurrentDetails().get(0).getCode()).getHirtypeCode();

        if (currentDetails == null)
            currentDetails = new ArrayList<IBasicDTO>();
        if (getSelectedCurrentDetails() != null && getSelectedCurrentDetails().size() > 0) {


            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                IBasicDTO selected = getSelectedCurrentDetails().get(i);
                Long docTypeCode = ((IInfDocumentTypesEntityKey)selected.getCode()).getDoctypeCode();

                try {
                    count =
                            EmpClientFactory.getRequiredDocumentsClient().checkBeforeDeleteDocument(docTypeCode, hireTypeCode);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (getCurrentSelectedDetail(selected).getStatusFlag() == null) {
                    //((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);

                    if (count.equals(0L)) {
                        getCurrentSelectedDetail(selected).setStatusFlag(deleted);
                        availableDetails.add(selected);
                        getSelectedCurrentDetails().remove(i);
                        i--;
                    } else {
                        getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "canNoDeleteDoc"));
                    }

                }
                if (getCurrentSelectedDetail(selected).getStatusFlag() != null &&
                    getCurrentSelectedDetail(selected).getStatusFlag().longValue() == added.longValue()) {

                    currentDetails.remove(getCurrentSelectedDetail(selected));
                    //bugs
                    // ((IClientDTO)getCurrentSelectedDetail(selected)).setChecked(false);
                    availableDetails.add(selected);

                    getSelectedCurrentDetails().remove(i);
                    i--;


                    //
                }

            }

        }

    }


    private Long getId() {
        Random generator2 = new Random();
        int r = generator2.nextInt(1000);
        Long maxId = new Long(r);
        if (this.getCurrentDetails() == null || this.getCurrentDetails().size() == 0) {
            return maxId;
        }
        for (IBasicDTO dto : this.getCurrentDetails()) {
            if (dto.getCode() != null) {
                Long id = ((IRequiredDocumentsEntityKey)dto.getCode()).getHirtypeCode();
                if (id < maxId)
                    maxId = id;
            } else { //int randomIndex = generator2.nextInt ( 10 ) ;
                return maxId;
            }
        }
        return maxId - 1;
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

                    currentDetails.add(mdto);
                    availableDetails.remove(dto);
                    getSelectedAvailableDetails().remove(i);
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


    /**
     * Purpose: this method handle show and hide divs
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(false);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        return app;
    }

    /**
     * Purpose: set list Available
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public void getListAvailable() {
        try {
            //List list = (List)EmpClientFactory.getRequiredDocumentsClient().getAvailableDocuments(getMasterEntityKey());
            List list = (List)EmpClientFactory.getRequiredDocumentsClient().getAvailableDocuments();
            setAvailableDetails(list);

        } catch (DataBaseException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setAvailableDetails(new ArrayList<IBasicDTO>());
        }
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
        this.getMasterEntityKey();
        //        IHireTypesDTO hireTypesDTO = null;
        //        IHireTypesEntityKey hireTypesEntityKey =null;
        //        IRequiredDocumentsEntityKey requiredDocumentsEntityKey = null;
        //        IRequiredDocumentsDTO requiredDocumentsDTO = EmpDTOFactory.createRequiredDocumentsDTO();
        //        requiredDocumentsDTO.setDocumentTypeDTO(dto);
        //
        //        if(this.getMasterEntityKey() !=null){
        //            hireTypesEntityKey = (IHireTypesEntityKey)this.getMasterEntityKey();
        //            hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
        //            hireTypesDTO.setCode(hireTypesEntityKey);
        //            requiredDocumentsDTO.setHireTypeDTO(hireTypesDTO);
        //        }
        //        if(hireTypesEntityKey !=null){
        //            requiredDocumentsEntityKey = EmpEntityKeyFactory.createRequiredDocumentsEntityKey(getId(), requiredDocumentsEntityKey.getDoctypeCode());
        //        }
        //        requiredDocumentsDTO.setCode(requiredDocumentsEntityKey);
        //        return requiredDocumentsDTO;
        IRequiredDocumentsDTO detailDTO = EmpDTOFactory.createRequiredDocumentsDTO();
        detailDTO.setCode(EmpEntityKeyFactory.createRequiredDocumentsEntityKey(getId(),
                                                                               ((IInfDocumentTypesEntityKey)dto.getCode()).getDoctypeCode()));
        detailDTO.setDocumentTypeDTO((IInfDocumentTypesDTO)dto);
        return detailDTO;

    }

    public void cancelSearchAvailable() throws DataBaseException, SharedApplicationException {
        super.cancelSearchAvailable();
        setShowMsg(false);
        setSearchTypeLongVal(new Long(0));

    }

    public void scrollerAction(ActionEvent ae) {

        setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.lookupAddDiv); setFocusOnlyOnElement('add_first_inputTxt');");
        //pageIndexAdd=((HtmlDataScroller)ae.getComponent()).getPageIndex();

    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {

        return ((IRequiredDocumentsDTO)dto).getDocumentTypeDTO();
    }

    //    public void setGenderList(List<IGenderTypesDTO> genderList) {
    //        this.genderList = genderList;
    //    }

    /**
     * Purpose: set Gender list Available
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    //    public List<IGenderTypesDTO> getGenderList() {
    //        if(genderList == null)
    //        {
    //            try {
    //                genderList =
    //                        (List)InfClientFactory.getGenderTypesClient().getCodeNameInCenter();
    //
    //            } catch (DataBaseException e) {
    //                e.printStackTrace();
    //                genderList = new ArrayList<IGenderTypesDTO>();
    //            }
    //        }
    //        return genderList;
    //    }


    /**
     * Purpose: search using in div method
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public void searchAvailable() throws DataBaseException, SharedApplicationException {


        System.out.println("dsdjhxajdjksad>>>>>>>>>>>");
        //        AvailableEntitiesDTO searchElement = new AvailableEntitiesDTO();
        //        searchElement.setMasterCode(getMasterEntityKey());
        //        searchElement.setSearchString(this.formatSearchString(getSearchString()));
        List<IBasicDTO> result = new ArrayList<IBasicDTO>();
        String srch= getSearchString();
        if (getSearchString().equals("") || getSearchString() == "" || getSearchString() == null) {
            setShowMsg(true);
        } else {
            searchTypeLongVal = Long.parseLong(searchTypeLongVal2);


         //   try {
                
                if (searchTypeLongVal.equals(0L)) { //by code
              
                    for(IBasicDTO dto:availableDetails){
                        if(dto.getCode().getKey().equals(srch) ){
                           result.add(dto);
                           break;
                        }
                    }
                    setAvailableDetails(result); 
                   // setAvailableDetails((List)EmpClientFactory.getRequiredDocumentsClient().filterAvailableDocuments(getMasterEntityKey(),
                                                                                                                    // Long.parseLong(getSearchString())));
                } else {
                    for(IBasicDTO dto:availableDetails){
                        if(dto.getName().contains(srch) ){
                           result.add(dto);
                        }
                    }
                    setAvailableDetails(result); 
                  ///  setAvailableDetails((List)EmpClientFactory.getRequiredDocumentsClient().filterAvailableDocuments(getMasterEntityKey(),
                                      //                                                                               this.formatSearchString(getSearchString())));
                }
//            } catch (SharedApplicationException e) {
//                setAvailableDetails(new ArrayList<IBasicDTO>());
//                setSearchMode(true);
//            } catch (DataBaseException e) {
//                setAvailableDetails(new ArrayList<IBasicDTO>());
//                setSearchMode(true);
//            }
//
//            catch (Exception e) {
//                setAvailableDetails(new ArrayList<IBasicDTO>());
//                setSearchMode(false);
//            }

        super.searchAvailable();

        }
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    public String getSelectedGender() {
        return selectedGender;
    }

    public void setNationality_Kuwaiti(Long nationality_Kuwaiti) {
        this.nationality_Kuwaiti = nationality_Kuwaiti;
    }

    public Long getNationality_Kuwaiti() {
        return nationality_Kuwaiti;
    }

    public void setNationality_NonKuwaiti(Long nationality_NonKuwaiti) {
        this.nationality_NonKuwaiti = nationality_NonKuwaiti;
    }

    public Long getNationality_NonKuwaiti() {
        return nationality_NonKuwaiti;
    }

    public void setNationality_NonSpecified(Long nationality_NonSpecified) {
        this.nationality_NonSpecified = nationality_NonSpecified;
    }

    public Long getNationality_NonSpecified() {
        return nationality_NonSpecified;
    }

    public void setSearchTypeLongVal(Long searchTypeLongVal) {
        this.searchTypeLongVal = searchTypeLongVal;
    }

    public Long getSearchTypeLongVal() {
        return searchTypeLongVal;
    }

    public void setDocumentStatus(boolean documentStatus) {
        this.documentStatus = documentStatus;
    }

    public boolean isDocumentStatus() {
        return documentStatus;
    }


    public void setSearchTypeLongVal2(String searchTypeLongVal2) {
        this.searchTypeLongVal2 = searchTypeLongVal2;
    }

    public String getSearchTypeLongVal2() {
        return searchTypeLongVal2;
    }

    public void setShowMsg(boolean showMsg) {
        this.showMsg = showMsg;
    }

    public boolean isShowMsg() {
        return showMsg;
    }


    
}

