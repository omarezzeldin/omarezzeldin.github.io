package com.beshara.csc.hr.emp.presentation.backingbean.hiretypes.treelist;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.integration.business.joincond.ITargetForJoinConditionDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.TargetForJoinConditionDTO;
import com.beshara.csc.gn.grs.integration.presentation.backingbean.joincond.JoinConditionHelperBean;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireTypesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.HireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.presentation.backingbean.hiretypes.HireTypesMainData;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.ManyToManyMaintainBaseBean;
import com.beshara.jsfbase.csc.backingbean.TreeBaseBeanMany;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


public class HireTypesTreeListBean extends TreeBaseBeanMany {
    String addNavigationCase = "hiretypesmaindata";
    String editNavigationCase = "hiretypesmaindata";
    public static final String BEAN_NAME = "hireTypesTreeListBean";
    public static final String CONDITION_TARGET_BINDING = "hireTypesTreeListBean.dtoDetails";
    private static final String BUNDLE_NAME = "com.beshara.csc.hr.emp.presentation.resources.emp";
    String treeCode;
    private IBasicDTO highlightedParentDTO;
    private boolean enabled = false;
    private IConditionsDTO conditionsDTO;
    private JoinConditionHelperBean jcHelper;
    private boolean mustDecision = false;

    public HireTypesTreeListBean() {
        try {
            setPageDTO(EmpDTOFactory.createHireTypesDTO());
            setBundle(ResourceBundle.getBundle("com.beshara.csc.hr.emp.presentation.resources.emp_" +
                                               getSharedUtil().getLocale()));
            setRootName("hireTypesList_title");
            setDto(EmpDTOFactory.createHireTypesDTO());
            setDtoDetails(EmpDTOFactory.createHireTypesDTO());
            setEntityKey(EmpEntityKeyFactory.createHireTypesEntityKey());
            setClient((IHireTypesClient)EmpClientFactory.getHireTypesClient());
            setNameMaxLength(380);
            setLovBaseBean(new LOVBaseBean());
            initiLovBaseBean();
            initJCHelperBean();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        setEnabled(true);
    }

    private void initJCHelperBean() {
        if (getJcHelper() == null) {
            setJcHelper(new JoinConditionHelperBean());
        }
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowSearch(true);
        app.setShowbar(true);
        app.setShowLovDiv(true);
        app.setShowTreeDivDetails(true);
        app.setShowtreeContent(true);
        app.setShowCustomDiv1(true);
        return app;
    }


    public String goAdd() {
        ManyToManyMaintainBaseBean maintainBean =
            (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                              "hireTypesMaintainBean" +
                                                                                                              "}").getValue(FacesContext.getCurrentInstance());
        maintainBean.setMaintainMode(0);
        //        HireTypesMainData hireTypesMainData =
        //            (HireTypesMainData)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
        //                                                                                                     "hireTypesMainData" +
        //                                                                                                     "}").getValue(FacesContext.getCurrentInstance());

        IHireTypesDTO hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
        // hireTypesDTO = (IHireTypesDTO)getClient().getById(this.getDtoDetails().getCode());

        if (this.getDtoDetails() != null) {
            hireTypesDTO.setParentCode(this.getDtoDetails().getCode());
            hireTypesDTO.setParentObject(this.getDtoDetails());
            hireTypesDTO.setParentHireType((HireTypesDTO)this.getDtoDetails());
            hireTypesDTO.setFirstParent((IEntityKey)this.getDtoDetails().getFirstParent());
            Long treeLevel = this.getDtoDetails().getTreeLevel() + 1;
            hireTypesDTO.setTreeLevel(treeLevel);
            hireTypesDTO.setLeafFlag(ISystemConstant.LEAF_FLAG);
            hireTypesDTO.setDecisionMust(mustDecision == true ? 1L : 0L);
            //  added by Ahmed Khaled
            hireTypesDTO.setTabrecSerial(hireTypesDTO.getParentHireType().getTabrecSerial());
            hireTypesDTO.setCode(hireTypesDTO.getParentHireType().getCode());
            //          hireTypesDTO.setName("");
            ////
            //           if (hireTypesDTO.getConditionsDTO() != null && !hireTypesDTO.getConditionsDTO().equals("")) {

            conditionsDTO = GrsDTOFactory.createConditionsDTO();
            conditionsDTO.setCode(GrsEntityKeyFactory.createConditionsEntityKey());

            hireTypesDTO.setName("");
            hireTypesDTO.setConditionsDTO(conditionsDTO);
        }
        initializeObjectBeforeMaintain(hireTypesDTO);
        maintainBean.setPageDTO(hireTypesDTO);
        return addNavigationCase;
    }


    public String goEdit() throws DataBaseException, SharedApplicationException {
        if (this.getDtoDetails() != null) {

            ManyToManyMaintainBaseBean maintainBean =
                (ManyToManyMaintainBaseBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                                  "hireTypesMaintainBean" +
                                                                                                                  "}").getValue(FacesContext.getCurrentInstance());

            IHireTypesDTO hireTypesDTO = (IHireTypesDTO)getClient().getById(this.getDtoDetails().getCode());
            HireTypesMainData hireTypesMainData =
                (HireTypesMainData)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{" +
                                                                                                         "hireTypesMainData" +
                                                                                                         "}").getValue(FacesContext.getCurrentInstance());
            if (hireTypesDTO.getMinistriesDTO() != null && !hireTypesDTO.getMinistriesDTO().equals("")) {
                hireTypesMainData.setMinistryCode(hireTypesDTO.getMinistriesDTO().getCode().getKey());
            }
            if (hireTypesDTO.getConditionsDTO() != null && !hireTypesDTO.getConditionsDTO().equals("")) {
                hireTypesMainData.setConditionCode(hireTypesDTO.getConditionsDTO().getCode().getKey());
            }
            initializeObjectBeforeMaintain(hireTypesDTO);
            setPageDTO(hireTypesDTO);
            maintainBean.setMaintainMode(1);

            maintainBean.setPageDTO(this.getPageDTO());
            return editNavigationCase;

        }
        return null;
    }


    private void initiLovBaseBean() {
        setTreeListPagingRequestDTO(new PagingRequestDTO(BEAN_NAME, "getChildrenNodes"));
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMultiSelect(false);
        getLovBaseBean().setSearchTyp("0");
    }


    public void showSearchListOfValuesDiv() {
        if (isSearchMode() == true) {
            getLovBaseBean().setSearchMode(true);
            getLovBaseBean().setCleanDataTableFlage(true);
        } else {
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setCleanDataTableFlage(false);
            getLovBaseBean().setSearchQuery("");
        }
        getLovBaseBean().setReturnMethodName(BEAN_NAME + ".returnSearchLovDiv");
        getLovBaseBean().setSearchMethod(BEAN_NAME + ".search");
        getLovBaseBean().setCancelSearchMethod(BEAN_NAME + ".cancelSearchLovDiv");
        getLovBaseBean().setRenderedDropDown("treeList,lov_dataT_data_panel,treeDivPanel ,divLov, OperationBar , theSelectedNodeId , selectedNodeTreeLevelId , treeDetailsDiv,treeAlertDelete");
        getLovBaseBean().setOnCompleteList(getLovBaseBean().getOnCompleteList() + " ; focusHighlightedNode ( ) ; ");
        getLovBaseBean().setHighlightedDTOS(null);
    }

    public String cancelSearchLovDiv() {
        setSearchMode(false);
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
        getLovBaseBean().setSelectedRadio("");
        getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().setSelectedDTOS(new ArrayList<IBasicDTO>());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        try {
            cancelSearchTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String returnSearchLovDiv() {
        getLovBaseBean().setSearchMode(true);
        setSearchResultListSize(getLovBaseBean().getSelectedDTOS().size());
        this.setCurrentSelectedSearchIndex(getLovBaseBean().getIndexOfSelectedDataInDataTable());
        getHtmlTree().collapsePath(new String[] { "0" });
        expandAllForSpecificNodeList((List)getLovBaseBean().getSelectedDTOS());
        setSearchMode(true);
        List list = new ArrayList();
        try {
            list = buildTree().getChildren();
        } catch (DataBaseException e) {
        }

        IHireTypesDTO hireTypesDTO = (IHireTypesDTO)getLovBaseBean().getSelectedDTOS().get(0);
        highlightAddEditResult(list, hireTypesDTO.getCode().getKey(), "person-highlight");
        return "";
    }

    public String search(Object searchType, Object searchQuery) {
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                if (searchType.toString().equals("0")) {
                    getLovBaseBean().setMyTableData(getClient().searchByCode((new Long(searchQuery.toString()))));
                } else if (searchType.toString().equals("1")) {
                    getLovBaseBean().setMyTableData(getClient().searchByName(searchQuery.toString()));
                }
            } //this.setSearchMode ( true ) ;
            initializeNavigation();
            setSearchResultListSize(getLovBaseBean().getMyTableData().size());
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

        return "";
    }


    public void idChange(ValueChangeEvent e) {


        if (isUsingTreePaging()) {
            setDtoDetails(new TreeDTO());
        } else {
            initializeDtoDetails();
        }
        setSuccess(false);
        this.setSelectionNo(0);
        String value = (String)e.getNewValue();
        if (value != null && !value.equals("")) {
            String nodeCode = (String)e.getNewValue();

            // if there is composte key
            if (getKeyIndex() > -1 && !isUsingTreePaging()) {
                try {
                    nodeCode = getFullEntityKey(getTreeNodeBase().getChildren(), nodeCode);

                } catch (Exception f) {
                    f.printStackTrace();
                }
            }

            if (nodeCode != null && !nodeCode.equals("0")) {
                setEnabled(false);
                SharedUtilBean shared_util = getSharedUtil();
                try {
                    if (isUsingTreePaging()) {
                        setEntityKey(getSelectedEntityKey(nodeCode));
                    } else {
                        setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), nodeCode));
                    }

                    this.setAlreadyDeleted(false);

                    if (getDtoDetails() == null || getDtoDetails().getCode() == null ||
                        (!value.equals(getDtoDetails().getCode().getKey().toString()))) {

                        setDtoDetails((ITreeDTO)preEdit());
                    }

                    if (getDtoDetails() != null) {
                        if (getDtoDetails().isBooleanLeafFlag()) {
                            setEnabledNotLeaf(true);
                        } else {
                            setEnabledNotLeaf(false);
                        }
                        if (getDtoDetails().getChildernNumbers() != 0) {
                            setEnabledNotHasChlid(true);
                        } else {
                            setEnabledNotHasChlid(false);
                        }
                    }

                    if (getDtoDetails() != null && getDtoDetails().getParentCode() == null) {
                        getDtoDetails().setParentCode(getEntityKey());
                    }
                    setRenderEdit(true);
                    setParentName(getDtoDetails().getName());

                    setParentLevel(nodeCode);
                } catch (ItemNotFoundException ex) {
                    System.out.println("treebase bean::idchange::ItemNotFoundException=" + ex);
                    this.setAlreadyDeleted(true);
                } catch (DataBaseException ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                } catch (Exception ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                }
            } else {
                setParentName(getBundle().getString(getRootName()));
                setParentLevel(getVirtualLevelCode().toString());
                setEnabledRoot(true);
                setEnabledNotLeaf(false);
                setEnabledNotHasChlid(true);
            }
            this.setSelectionNo(1);
        }
    }

    public IBasicDTO preEdit() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            return getClient().getById(getEntityKey());
        } catch (ItemNotFoundException e) {
            shared_util.setErrMsgValue("itemALreadyDeleted");
        } catch (DataBaseException e) {
            //shared_util.setErrMsgValue("SystemFailureException");
            getSharedUtil().handleException(e);
        } catch (SharedApplicationException e) {
            //shared_util.setErrMsgValue(e.getMessage());
            getSharedUtil().handleException(e);
        }
        return null;
    }

    /**
     * Purpose: initialize Object Before Maintain
     * Creation/Modification History :
     * 1.1 - Developer Name: Ahmed Abd El-Fatah
     * 1.2 - Date:  Jul 21, 2008
     * 1.3 - Creation/Modification:
     * 1.4-  Description:
     * @return
     * @throws
     */
    public void initializeObjectBeforeMaintain(IBasicDTO dto) {

        if (((IHireTypesDTO)dto).getRequiredDocumentsDTOList() == null) {
            ((IHireTypesDTO)dto).setRequiredDocumentsDTOList(new ArrayList());
        }

        if (((IHireTypesDTO)dto).getHireTypeProcedureDTOList() == null) {
            ((IHireTypesDTO)dto).setHireTypeProcedureDTOList(new ArrayList());
        }
    }

    public void expandSpecificNode(ITreeDTO treeDTO, boolean highLigthChildNode, String childNodeID) {
        BesharaTree node = null;
        try {
            node = getSpecificNode(getTreeNodeBase().getChildren(), treeDTO.getCode().getKey().toString());
            getTreeNodeBase().setExpanded(true);
        } catch (Exception e) {
            ;
        }
        if (node.getChildren().size() == 0) {
            PagingRequestDTO pagingRequestDTO = getTreeListPagingRequestDTO();

            if (pagingRequestDTO != null) {

                pagingRequestDTO.setParams(new Object[] { node });

                String methodBindingExpression =
                    pagingRequestDTO.getBeanName() + "." + pagingRequestDTO.getMethodName();

                PagingResponseDTO responseDTO =
                    (PagingResponseDTO)executeMethodBindingWithParams(methodBindingExpression,
                                                                      new Object[] { pagingRequestDTO },
                                                                      new Class[] { PagingRequestDTO.class });

                List children = responseDTO.getResultList();
                if (children != null && children.size() > 0) {
                    buildChildrenTree(node, children);
                }
            }
        }

        if (highLigthChildNode) {
            try {
                BesharaTree childNode = getSpecificNode(getTreeNodeBase().getChildren(), childNodeID);
                childNode.setType("person-highlight");
                SetSelectedNode(childNodeID);
                //focus on the highlightednode
                setSelectedNodeTreeLevelId(childNode.getTreeNodeLevelsID());
            } catch (Exception e) {
                ;
            }
        }
        getHtmlTree().expandPath(new String[] { "0" });
        node.setExpanded(true);
        if (node.getChildren() != null && node.getChildren().size() > 0) {
            node.setHasChild(true);
        }
        String nodePath = node.getPath();
        System.out.println("node path ------------------------>  " + nodePath);
        getHtmlTree().expandPath(new String[] { nodePath });
        //getHtmlTree().getDataModel().getTreeState().expandPath(new String[] { nodePath });

    }


    public void expandAllForSpecificNode(ITreeDTO treeDTO) {
        String nodeID = treeDTO.getCode().getKey().toString();
        try {
            treeDTO = (ITreeDTO)getClient().getById(treeDTO.getCode());
        } catch (SharedApplicationException e) {
            ;
        } catch (DataBaseException e) {
            ;
        }
        Stack stack = getAllParents(treeDTO);
        boolean highLigthChildNode = false;
        if (stack.size() == 1) {
            try {
                BesharaTree childNode = getSpecificNode(getTreeNodeBase().getChildren(), nodeID);
                childNode.setType("person-highlight");
                //expandedNodes.add()
                SetSelectedNode(nodeID);
                //focus on the highlightednode
                setSelectedNodeTreeLevelId(childNode.getTreeNodeLevelsID());
            } catch (Exception e) {
                ;
            }
            getHtmlTree().expandPath(new String[] { "0" });
            return;
        }

        while (stack.size() > 1) {
            if (stack.size() == 2) {
                highLigthChildNode = true;
            }
            expandSpecificNode((ITreeDTO)stack.pop(), highLigthChildNode, nodeID);
        }
    }


    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setMustDecision(boolean mustDecision) {
        ((IHireTypesDTO)(IHireTypesDTO)getPageDTO()).setDecisionMust(mustDecision == true ? 1L : 0L);
        this.mustDecision = mustDecision;
    }

    public boolean isMustDecision() {
        return mustDecision;
    }

    //    public void setJcHelper(HireTypeJoinCondHelperBean jcHelper) {
    //        this.jcHelper = jcHelper;
    //    }
    //
    //    public HireTypeJoinCondHelperBean getJcHelper() {
    //        return jcHelper;
    //    }


    //add by Ahmed Khaled

    public void preOpenJoinDiv() {
        IHireTypesDTO _dto = (IHireTypesDTO)getDtoDetails();
        ITargetForJoinConditionDTO target = new TargetForJoinConditionDTO();

        IEntityKey ek = new EntityKey();
        if (_dto.getCode().getKey() != null) {
            ek.setKey(_dto.getCode().getKey());
        }
        if (_dto.getCode().getKeys() != null) {
            ek.setKeys(_dto.getCode().getKeys());
        }
        target.setCode(ek);
        if (_dto.getName() != null) {
            target.setName(_dto.getName());
        }
        target.setFromDate(SharedUtils.getCurrentDate());
        target.setTabrecSerial(_dto.getTabrecSerial());
        target.setJCTableName("HR_EMP_HIRE_TYPES");
        jcHelper.setTargetForJoinConditionDTO(target);
        jcHelper.setAllowMultiCondition(false);
        jcHelper.setContainerBeanName("hireTypesTreeListBean");

        jcHelper.setTransactionName((getSharedUtil().messageLocator(BUNDLE_NAME, "module_div_title")) + "-" +
                                    _dto.getName());
        //  List l= jcHelper.getMyTableData();
        //    setConditionsDTO((IConditionsDTO)l);

        jcHelper.openJoinDiv();

    }

    //add by Ahmed Khaled

    public void viewCondDetails() {

        IHireTypesDTO _dto = (IHireTypesDTO)getDtoDetails();
        ITargetForJoinConditionDTO target = new TargetForJoinConditionDTO();
        IEntityKey ek = new EntityKey();
        if (_dto.getCode().getKey() != null) {
            ek.setKey(_dto.getCode().getKey());
        }
        if (_dto.getCode().getKeys() != null) {
            ek.setKeys(_dto.getCode().getKeys());
        }
        target.setCode(ek);
        if (_dto.getName() != null) {
            target.setName(_dto.getName());
        }
        target.setFromDate(SharedUtils.getCurrentDate());
        target.setTabrecSerial(_dto.getTabrecSerial());
        target.setJCTableName("HR_EMP_HIRE_TYPES");

        jcHelper.setContainerBeanName("hireTypesTreeListBean");
        jcHelper.setTargetForJoinConditionDTO(target);

        jcHelper.viewConditionDetails();

    }

    //add by Ahmed Khaled

    public void viewRelatedCond() {

        IHireTypesDTO _dto = (IHireTypesDTO)getDtoDetails();
        ITargetForJoinConditionDTO target = new TargetForJoinConditionDTO();
        IEntityKey ek = new EntityKey();
        if (_dto.getCode().getKey() != null) {
            ek.setKey(_dto.getCode().getKey());
        }
        if (_dto.getCode().getKeys() != null) {
            ek.setKeys(_dto.getCode().getKeys());
        }
        target.setCode(ek);
        if (_dto.getName() != null) {
            target.setName(_dto.getName());
        }
        target.setFromDate(SharedUtils.getCurrentDate());
        target.setTabrecSerial(_dto.getTabrecSerial());
        target.setJCTableName("HR_EMP_HIRE_TYPES");
        jcHelper.setAllowMultiCondition(false);
        jcHelper.setContainerBeanName("hireTypesTreeListBean");
        jcHelper.setTargetForJoinConditionDTO(target);
        jcHelper.setTransactionName("emp hire types Related condition view");
        jcHelper.viewRelatedConditions();

    }

    public void setJcHelper(JoinConditionHelperBean jcHelper) {
        this.jcHelper = jcHelper;
    }

    public JoinConditionHelperBean getJcHelper() {
        return jcHelper;
    }

    public void setConditionsDTO(IConditionsDTO conditionsDTO) {
        this.conditionsDTO = conditionsDTO;
    }

    public IConditionsDTO getConditionsDTO() {
        return conditionsDTO;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setHighlightedParentDTO(IBasicDTO highlightedParentDTO) {
        this.highlightedParentDTO = highlightedParentDTO;
    }

    public IBasicDTO getHighlightedParentDTO() {
        return highlightedParentDTO;
    }


    public void mainCancelSearch() {
        try {
            cancelSearchLovDiv();
          //  super.cancelSearchTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

