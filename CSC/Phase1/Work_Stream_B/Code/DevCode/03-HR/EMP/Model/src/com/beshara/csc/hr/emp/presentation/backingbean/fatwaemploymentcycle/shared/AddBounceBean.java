package com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.sal.business.client.ISalElementGuidesClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuideSearchDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElmguideMinDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.event.ValueChangeEvent;


public class AddBounceBean extends LookupMaintainBaseBean {
    private static final String BENEFIT = "0";
    private static final String REWARD = "1";
    private static final String BUNDLE = "com.beshara.csc.hr.emp.presentation.resources.emp";
    private String id;
    private String civilId;
    private String fullName;
    private Float totalBounces;
    private Float basicSalary;
    private String selectedBenReward = BENEFIT;

    private String benefitConst = ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT.toString();
    private String rewardConst = ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_PAY_CAT.toString();
    TreeDivBean treedivBean;
    private boolean benefitRewardCodeExists = false;
    private boolean benefitRewardCodeWrong;
    private boolean benfitAndConditionExceptionFlag = false;
    private boolean elemGuideCodeParentMinJoinFlag = false;
    private boolean renderShowCont1 = true;
    private Float elmGuideMaxValue;
    private Float elmGuideMinValue;
    private List saveStateAddBounceList = new ArrayList();
    private IEmpCandidatesDTO empCandidatesDTO;
    private String backActionMethodName;
    private String afterSaveMethodName;
    private String bckBtnNavigationCase;
    private HashMap<String, IEmpCandidateExtraDataDTO> savedList = new HashMap<String, IEmpCandidateExtraDataDTO>();
    private Float totalBouncseNotBasicSal = 0F;
    private boolean viewBounses;
    private List<IEmpCandidateExtraDataDTO> empCandExtraDataList;

    public AddBounceBean() {
        setPageDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
        setClient(SalClientFactory.getSalEmpSalaryElementsClient()); 
        setTreeDivTitleKey("chooseRaiseTitle");
        setPageMode(1);
        initTreeDivBean();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(isRenderShowCont1());
        app.setShowCommonData(true);
        app.setShowshortCut(true);
        app.setShowPagedTreediv(!isViewBounses());
        app.setShowScirptGenerator(true);
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        return app;
    }

    public static AddBounceBean getInstance() {
        return (AddBounceBean)JSFHelper.getValue("addBounceBean");
    }

    public void initTreeDivBean() {
        treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");  
        treedivBean.setUsingTreePaging(true);
        treedivBean.setRootName("Benefit_Reward");
        treedivBean.setBundleName("com.beshara.csc.hr.emp.presentation.resources.emp_");
        treedivBean.setClient(SalClientFactory.getSalElementGuidesClient());
        treedivBean.setPageDTO(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setDto(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setDtoDetails(SalDTOFactory.createSalElementGuidesDTO());
        treedivBean.setMyTableData(new ArrayList());
        treedivBean.generateTreeListPagingRequestDTO("addBounceBean", "getChildrenNodes");
        treedivBean.generateTreeSearchPagingRequestDTO("addBounceBean", "searchTree");
        setTreeDivTitleKey("chooseRaiseTitle");
        treedivBean.setShowTreeContent(true);
        treedivBean.setSearchQuery("");
        treedivBean.setSelectedRadio("");
        treedivBean.setGoActionOkMethod("addBounceBean.addEmpBen");
        treedivBean.getOkCommandButton().setReRender("addPanel,scriptpanel");
        treedivBean.getOkCommandButton().setRendered(true);
        treedivBean.setCancelSearchMethod("addBounceBean.cancelTreeDegreeSearch");
        treedivBean.setSelectedEntityKeyMethod("addBounceBean.getSelectedIEntityKey");
    }

    public IEntityKey getSelectedIEntityKey(String nodeId) {
        return SalEntityKeyFactory.createSalElementGuidesEntityKey(Long.parseLong(nodeId));
    }

    public PagingResponseDTO getChildrenNodes(PagingRequestDTO pagingRequest) throws DataBaseException,
                                                                                     SharedApplicationException {
        BesharaTree node = (BesharaTree)pagingRequest.getParams()[0];
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        ISalElementGuidesClient client = SalClientFactory.getSalElementGuidesClient();
        List resultList = client.getManualBenifitRewardCodeName(Long.parseLong(node.getTreeId()), minCode);

        treedivBean.setMyTableData(resultList);

        return new PagingResponseDTO(resultList);

    }

    public PagingResponseDTO searchTree(PagingRequestDTO pagingRequest) throws DataBaseException,
                                                                               SharedApplicationException {
        ISalElementGuidesClient client = SalClientFactory.getSalElementGuidesClient();
        List resultList = null;
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
        try {
            if (selectedBenReward.equals(BENEFIT)) {
                resultList =
                        client.searchManualBenRewardByNameAndType(treedivBean.getSearchQuery(), ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_PAY_CAT,
                                                                  minCode);
            } else if (selectedBenReward.equals(REWARD)) {
                resultList =
                        client.searchManualBenRewardByNameAndType(treedivBean.getSearchQuery(), ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_PAY_CAT,
                                                                  minCode);
            }
            return new PagingResponseDTO(resultList);
        } catch (DataBaseException dbe) {
            dbe.printStackTrace();
            return new PagingResponseDTO(new ArrayList());
        } catch (SharedApplicationException sae) {
            sae.printStackTrace();
            return new PagingResponseDTO(new ArrayList());
        }
    }

    public void addEmpBen() {
        if (treedivBean.getDtoDetails() != null && treedivBean.getDtoDetails().getCode() != null) {

            setId((treedivBean.getDtoDetails()).getCode().getKey().toString());
            fillSalElementGuide();
        }
    }

    public void cancelTreeDegreeSearch() {
        obtainBenRewardsBySelectedBenReward();
        treedivBean.setSearchMode(false);
        treedivBean.setSearchQuery("");
        treedivBean.setSearchType(0);
    }

    public void openTreeDiv() throws DataBaseException {
        cancelTreeSearch();
        obtainBenRewardsBySelectedBenReward();
    }

    public void obtainBenRewardsBySelectedBenReward() {

        Long type = null;
        if (selectedBenReward.equals(BENEFIT)) {
            type = ISalConstants.ELEMENT_GUIDE_TYPE_BENIFIT_TYPE_ROOT;
        } else {
            type = ISalConstants.ELEMENT_GUIDE_TYPE_REWARD_TYPE_ROOT;
        }

        try {
            ISalElementGuidesClient client = SalClientFactory.getSalElementGuidesClient();
            ISalElementGuideSearchDTO searchDTO = SalDTOFactory.createSalElementGuideSearchDTO();
            searchDTO.setElmguideCode(type);
            List resultList = client.search(searchDTO);

            treedivBean.setMyTableData(resultList);

            treedivBean.buildTree();

        } catch (SharedApplicationException e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (DataBaseException e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (Exception e) {
            treedivBean.setMyTableData(new ArrayList());
            e.printStackTrace();
        }
    }


    public void cancelTreeSearch() {
        try {

            if (treedivBean.isUsingTreePaging()) {
                treedivBean.setSearchQuery("");
                treedivBean.setSearchType(0);
                treedivBean.setSearchMode(false);
            } else {
                super.cancelSearch();
                treedivBean.setSelectionNo(0);
                treedivBean.buildTree();
                treedivBean.getHtmlTree().collapseAll();
            }
            treedivBean.setDtoDetails(SalDTOFactory.createSalElementGuidesDTO());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectedBenRewardChange(ValueChangeEvent ce) {
        selectedBenReward = ce.getNewValue().toString();
        obtainBenRewardsBySelectedBenReward();
    }

    public void reSetPage() {
        reIntializeMsgs();
        benefitRewardCodeExists = false;
        benefitRewardCodeWrong = false;
        setId(null);
        setSelectedBenReward(BENEFIT);
        setPageDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
        elmGuideMinValue = null;
        elmGuideMaxValue = null;
    }

    public void fillSalElementGuide() {
        elmGuideMinValue = null;
        elmGuideMaxValue = null;
        reIntializeMsgs();
        if (id != null && !id.equals("")) {
            ISalElementGuidesDTO salElementGuidesDTO = SalDTOFactory.createSalElementGuidesDTO();
            ISalElmguideMinDTO salElemGuidesMinDTO = SalDTOFactory.createSalElmguideMinDTO();
            try {
                IBasicDTO dto =
                    SalClientFactory.getSalElementGuidesClient().getByIdNotLinkWitgCond(Long.parseLong(getId()));
                if (dto != null) {
                    salElementGuidesDTO = (ISalElementGuidesDTO)dto;


                    if ((salElementGuidesDTO.getSalElementTypesDTO().getCode().getKey().equals(benefitConst) &&
                         selectedBenReward.equals(BENEFIT)) ||
                        (salElementGuidesDTO.getSalElementTypesDTO().getCode().getKey().equals(rewardConst) &&
                         selectedBenReward.equals(REWARD))) {
                        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCode();
                        Long elmguideCode = Long.parseLong(salElementGuidesDTO.getParentCode().getKey());
                        if (salElementGuidesDTO.getMaxValue() != null) {
                            setElmGuideMaxValue(salElementGuidesDTO.getMaxValue());
                        }
                        if (salElementGuidesDTO.getMinValue() != null) {
                            setElmGuideMinValue(salElementGuidesDTO.getMinValue());
                        }
                        salElemGuidesMinDTO =
                                (ISalElmguideMinDTO)SalClientFactory.getSalElmguideMinClient().getManualValidByElmGuideAndMinCode(elmguideCode,
                                                                                                                                  minCode);
                        if (salElemGuidesMinDTO != null) {
                            ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(salElementGuidesDTO);
                            benefitRewardCodeExists = true;
                            elemGuideCodeParentMinJoinFlag = false;
                            setSuccess(false);
                            setShowErrorMsg(false);
                        } else {
                            ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(SalDTOFactory.createSalElementGuidesDTO());
                            benefitRewardCodeWrong = true;
                            elemGuideCodeParentMinJoinFlag = true;
                            setSuccess(false);
                            setShowErrorMsg(false);
                        }

                    } else {
                        ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(SalDTOFactory.createSalElementGuidesDTO());
                        benefitRewardCodeWrong = true;
                        setSuccess(false);
                        setShowErrorMsg(false);
                    }
                } else {
                    benefitRewardCodeWrong = true;
                    ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(null);
                }

            } catch (SharedApplicationException e) {
                benefitRewardCodeWrong = true;
                e.printStackTrace();
                ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(null);

            } catch (Exception e) {
                benefitRewardCodeWrong = true;
                e.printStackTrace();
                ((ISalEmpSalaryElementsDTO)getPageDTO()).setSalElementGuidesDTO(null);
            }
        }
    }


    public void addbounces() throws DataBaseException {

        if (savedList.get(getId()) == null) {
            ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();
            try {
                salEmpSalaryElementsDTO = (ISalEmpSalaryElementsDTO)getSharedUtil().deepCopyObject(getPageDTO());
            } catch (Exception e) {
            }
            Float bounseValue = ((ISalEmpSalaryElementsDTO)getPageDTO()).getSalElementGuidesDTO().getValue();
            totalBouncseNotBasicSal = totalBouncseNotBasicSal + bounseValue;
            if (getMyTableData() == null) {
                setMyTableData(new ArrayList());
            }
            if (empCandExtraDataList == null) {
                empCandExtraDataList = new ArrayList();
            }
            getMyTableData().add(salEmpSalaryElementsDTO);
            //            List<IEmpCandidateExtraDataDTO> empCandExtraDataList =
            //                (List)empCandidatesDTO.getEmpCandidateExtraDataList();
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = EmpDTOFactory.createEmpCandidateExtraDataDTO();
            IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
            empExtraDataTypesDTO.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(IEMPConstants.EX_DATA_REWARD_ACCEPTED));
            empCandidateExtraDataDTO.setValue(getId());
            empCandidateExtraDataDTO.setValueDesc(String.valueOf(bounseValue));
            empCandidateExtraDataDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            empCandidateExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);
            empCandidateExtraDataDTO.setEmpCandidatesDTO(empCandidatesDTO);
            empCandExtraDataList.add(empCandidateExtraDataDTO);
            //            empCandidatesDTO.setEmpCandidateExtraDataList((List)empCandExtraDataList);
            savedList.put(getId(), empCandidateExtraDataDTO);
            totalBounces = totalBounces + bounseValue;
            if (afterSaveMethodName != null && !"".equals(afterSaveMethodName))
                executeMethodBinding(afterSaveMethodName, null);
            reSetPage();
        } else {
            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE, "duplicate_bounceId"));
        }


    }

    public String backAction() {
        reSetPage();
        if (!"".equals(backActionMethodName))
            executeMethodBinding(backActionMethodName, null);
        return getBckBtnNavigationCase();
    }

    public String backActionApprove() {
        reSetPage();
        if (!"".equals(backActionMethodName))
            executeMethodBinding(backActionMethodName, null);
        return "fatwaReviewRequestApprovePage";
    }
    
    public void setTreedivBean(TreeDivBean treedivBean) {
        this.treedivBean = treedivBean;
    }

    public TreeDivBean getTreedivBean() {
        return treedivBean;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setSelectedBenReward(String selectedBenReward) {
        this.selectedBenReward = selectedBenReward;
    }

    public String getSelectedBenReward() {
        return selectedBenReward;
    }

    public void setBenefitRewardCodeExists(boolean benefitRewardCodeExists) {
        this.benefitRewardCodeExists = benefitRewardCodeExists;
    }

    public boolean isBenefitRewardCodeExists() {
        return benefitRewardCodeExists;
    }

    public void setBenefitRewardCodeWrong(boolean benefitRewardCodeWrong) {
        this.benefitRewardCodeWrong = benefitRewardCodeWrong;
    }

    public boolean isBenefitRewardCodeWrong() {
        return benefitRewardCodeWrong;
    }

    public void setElemGuideCodeParentMinJoinFlag(boolean elemGuideCodeParentMinJoinFlag) {
        this.elemGuideCodeParentMinJoinFlag = elemGuideCodeParentMinJoinFlag;
    }

    public boolean isElemGuideCodeParentMinJoinFlag() {
        return elemGuideCodeParentMinJoinFlag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBenfitAndConditionExceptionFlag(boolean benfitAndConditionExceptionFlag) {
        this.benfitAndConditionExceptionFlag = benfitAndConditionExceptionFlag;
    }

    public boolean isBenfitAndConditionExceptionFlag() {
        return benfitAndConditionExceptionFlag;
    }

    public void setRenderShowCont1(boolean renderShowCont1) {
        this.renderShowCont1 = renderShowCont1;
    }

    public boolean isRenderShowCont1() {
        return renderShowCont1;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setTotalBounces(Float totalBounces) {
        this.totalBounces = totalBounces;
    }

    public Float getTotalBounces() {
        return totalBounces;
    }

    public void setBasicSalary(Float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Float getBasicSalary() {
        return basicSalary;
    }

    public void setElmGuideMaxValue(Float elmGuideMaxValue) {
        this.elmGuideMaxValue = elmGuideMaxValue;
    }

    public Float getElmGuideMaxValue() {
        return elmGuideMaxValue;
    }

    public void setElmGuideMinValue(Float elmGuideMinValue) {
        this.elmGuideMinValue = elmGuideMinValue;
    }

    public Float getElmGuideMinValue() {
        return elmGuideMinValue;
    }

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO) {
        this.empCandidatesDTO = empCandidatesDTO;
    }

    public IEmpCandidatesDTO getEmpCandidatesDTO() {
        return empCandidatesDTO;
    }

    public void setBackActionMethodName(String backActionMethodName) {
        this.backActionMethodName = backActionMethodName;
    }

    public String getBackActionMethodName() {
        return backActionMethodName;
    }

    public void setBckBtnNavigationCase(String bckBtnNavigationCase) {
        this.bckBtnNavigationCase = bckBtnNavigationCase;
    }

    public String getBckBtnNavigationCase() {
        return bckBtnNavigationCase;
    }

    public void setSavedList(HashMap<String, IEmpCandidateExtraDataDTO> savedList) {
        this.savedList = savedList;
    }

    public HashMap<String, IEmpCandidateExtraDataDTO> getSavedList() {
        return savedList;
    }

    public void setAfterSaveMethodName(String afterSaveMethodName) {
        this.afterSaveMethodName = afterSaveMethodName;
    }

    public String getAfterSaveMethodName() {
        return afterSaveMethodName;
    }

    public void setSaveStateAddBounceList(List saveStateAddBounceList) {
        this.saveStateAddBounceList = saveStateAddBounceList;
    }

    public List getSaveStateAddBounceList() {
        return saveStateAddBounceList;
    }

    public void setTotalBouncseNotBasicSal(Float totalBouncseNotBasicSal) {
        this.totalBouncseNotBasicSal = totalBouncseNotBasicSal;
    }

    public Float getTotalBouncseNotBasicSal() {
        return totalBouncseNotBasicSal;
    }

    public void setEmpCandExtraDataList(List<IEmpCandidateExtraDataDTO> empCandExtraDataList) {
        this.empCandExtraDataList = empCandExtraDataList;
    }

    public List<IEmpCandidateExtraDataDTO> getEmpCandExtraDataList() {
        return empCandExtraDataList;
    }

    public void setViewBounses(boolean viewBounses) {
        this.viewBounses = viewBounses;
    }

    public boolean isViewBounses() {
        return viewBounses;
    }
}
