package com.beshara.csc.hr.emp.presentation.backingbean.addIlleagalAccomodation;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.client.IKwMapClient;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.TreeBaseBeanMany;
import com.beshara.jsfbase.csc.backingbean.paging.PagingBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;


/**
 *this bean responsible for parsing the list to build the tree Structure
 *use this bean when implement open tree page in div and select note then press ok
 * this pean is managed bean bean name is "treeDivBean"
 * must be set rootName Attribute into Constructor
 */
public class TreeDivBean extends TreeBaseBeanMany {
    private String bundleName = "";
    private boolean showTreeContent = false;
    private HtmlAjaxCommandButton okCommandButton = 
        new HtmlAjaxCommandButton();
    private HtmlAjaxCommandButton cancelSearchCommandButton = 
        new HtmlAjaxCommandButton();
    private int enableAddNode = 
        0; // (0) means developer can add parent or leaf .(1) means enable leaf only
    private String clientValueBinding;

    //added to enable search by code added by Nora
    private static int searchTypeCode = 0;
    private static int searchTypeName = 1;
    private boolean enableSearchByCode = false;
    private String cancelSearchMethod;

    private String selectedEntityKeyMethod;

    //Paging in tree, by MLotfy
    private String scrollX;
    private String scrollY;

    private String divName = "pagedDivTree";
    private boolean readCenter = false;

    // this attribute is seeting in the bean to bind the method of Ok button of the treediv
    private String goActionOkMethod;
    // this attribute is seeting in the bean to bind the method of Back button of the treediv
    private String goActionBackMethod;
    // this attribute is seeting in the bean to bind the method of Search button of the treediv
    private String searchTreeMethod;
    // this attribute is seeting in the bean to bind the method of idChange of the treediv
    private String idChangeMethod;

    private Long startTreeLevel = ISystemConstant.FIRST_LEVEL_IN_TREE;
    List saveResult = new ArrayList();
    String searchSelectedStyleClass = "treepage-link";
    

    public TreeDivBean() {
        setRootName("MapName");
        ////////////////for tree paging
        setUsingTreePaging(true);
        setTreeListPagingRequestDTO(new PagingRequestDTO("TreePaggedDivBean", 
                                                         "getChildrenNodes"));

    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
        setBundle(ResourceBundle.getBundle(bundleName + 
                                           ((SharedUtilBean)getSession().getAttribute("shared_util")).getLocale()));
    }

    public String getBundleName() {
        return bundleName;
    }

    /**
     * @Used-in Paged Tree Div
     * @Updated-by MLotfy
     * @updated-by a.beltagy
     * we add the execution method binding to allow developer to overrite the method of Ok button in his bean.
     * The developer have to set goActionOkMethod in his bean with the method called in Ok button
     */
    public void goActionOk(ActionEvent e) {
        setShowTreeContent(false);
        if (isUsingTreePaging()) {
            setUsingPaging(false);
            setSearchMode(false);
        }

        if (goActionOkMethod != null && !goActionOkMethod.equals("")) {
            try {
                executeMethodBinding(goActionOkMethod, null);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    /**
     * @Used-in Paged Tree Div
     * @Updated-by MLotfy
     * @updated-by a.beltagy
     * we add the execution method binding to allow developer to overrite the method of Back button in his bean
     * The developer have to set goActionMethod in his bean with the method called in Back button
     */
    public void goActionBack(ActionEvent e) {

        setShowTreeContent(false);
        if (isUsingTreePaging()) {
            setUsingPaging(false);
            setSearchMode(false);
        }

        if (goActionBackMethod != null && !goActionBackMethod.equals("")) {
            try {
                executeMethodBinding(goActionBackMethod, null);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }

    }

    /**
     * @Used-in Paged Tree Div
     * @created-by a.beltagy
     * we add the execution method binding to allow developer to overrite the method of change te id in his bean
     * The developer have to set idChangeMethod in his bean with the method called in id change of the tree
     */
    public void idChange(ValueChangeEvent e) {
        if (idChangeMethod != null && !idChangeMethod.equals("")) {
            try {
                Object valChangeListnerList[] = new Object[1];
                Class types[] = new Class[1];
                types[0] = ValueChangeEvent.class;
                valChangeListnerList[0] = e;
                executeMethodBindingWithParams(idChangeMethod, 
                                               valChangeListnerList, types);

            } catch (Exception er) {
                er.printStackTrace();
            }
        } else {
            String nodeCode = (String)e.getNewValue();
            if (nodeCode != null && !nodeCode.equals("")) {
                setEnabledRoot(false);
                super.idChange(e);
            }
            
            BesharaTree childNode = null;

            try {
                childNode = getSpecificNode(getTreeNodeBase().getChildren(), nodeCode);
            } catch (Exception f) {
                // TODO
            }
            if (childNode != null) {
                setSelectedNodeTreeLevelId(childNode.getPath());
            }
            
        }
    }

    public void setShowTreeContent(boolean showTreeContent) {
        this.showTreeContent = showTreeContent;
    }

    public boolean isShowTreeContent() {
        return showTreeContent;
    }

    public void setOkCommandButton(HtmlAjaxCommandButton okCommandButton) {
        this.okCommandButton = okCommandButton;
    }

    public HtmlAjaxCommandButton getOkCommandButton() {
        return okCommandButton;
    }

    public void setCancelSearchCommandButton(HtmlAjaxCommandButton cancelSearchCommandButton) {
        this.cancelSearchCommandButton = cancelSearchCommandButton;
    }

    public HtmlAjaxCommandButton getCancelSearchCommandButton() {
        return cancelSearchCommandButton;
    }

    public void setEnableAddNode(int enableAddNode) {
        this.enableAddNode = enableAddNode;
    }

    public int getEnableAddNode() {
        return enableAddNode;
    }


    public IBasicDTO preEdit() {

        if (this.getClientValueBinding() != null) {
            setClient((IBasicClient)evaluateValueBinding(this.getClientValueBinding()));
        }

        if (readCenter)
            return super.preEdit();
        else {
            SharedUtilBean shared_util = getSharedUtil();
            try {
                return getClient().getById(getEntityKey());
            } catch (ItemNotFoundException e) {
                shared_util.setErrMsgValue("itemALreadyDeleted");
            } catch (DataBaseException e) {
                //shared_util.setErrMsgValue("
                //FailureException");
                getSharedUtil().handleException(e);
            } catch (SharedApplicationException e) {
                //shared_util.setErrMsgValue(e.getMessage());
                getSharedUtil().handleException(e);
            }
        }
        return null;

    }


    public void setClientValueBinding(String clientValueBinding) {
        this.clientValueBinding = clientValueBinding;
    }

    public String getClientValueBinding() {
        return clientValueBinding;
    }

    /**
     * @Used-in Paged Tree Div
     * @Updated-by MLotfy
     */
    public void searchTree() throws DataBaseException, RemoteException, 
                                    Exception {


        if (!enableSearchByCode) {
            super.setSearchType(1);
        }

        if (isUsingTreePaging()) {

            setSearchMode(true);
            setUsingPaging(true);

            setPagingRequestDTO(getTreeSearchPagingRequestDTO());
            resetPageIndex();
            setUpdateMyPagedDataModel(true);

        } else {
            super.searchTree();
        }

        if (searchTreeMethod != null && !searchTreeMethod.equals("")) {
            try {
                executeMethodBinding(searchTreeMethod, null);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    public String searchinTree() {

        try {
            setTreeNodeBase(null);
            getAll();
            buildTree();
            getHtmlTree().collapsePath(new String[] { "0" });
            if (!enableSearchByCode) {
                super.setSearchType(1);
            }

            List result = new ArrayList();
            if (getSearchQuery() != null && !getSearchQuery().equals("")) {
                if (getSearchType().toString().equals("0")) {
                    result =getClient().searchByCode(getSearchQuery().toString());
                    setSearchResultListSize(result.size());
                    setSearchMode(true);
                    setSelectionNo(0);
                    expandAllForSpecificNodeList((List<ITreeDTO>)result);
                    List<BesharaTree> list = getTreeNodeBase().getChildren();
                    searchByCode(list, getSearchQuery().toString(), 
                                 "person-highlight");
                    SetSelectedNode(null);
                    setSearchMode(true);
                } else if (getSearchType().toString().equals("1")) {
                    result =getClient().searchByName(getSearchQuery().toString());
                    setSearchResultListSize(result.size());
                    setSearchMode(true);
                    expandAllForSpecificNodeList((List<ITreeDTO>)result);
                    List<BesharaTree> list = getTreeNodeBase().getChildren();
                    searchByName(list, getSearchQuery().toString(), 
                                 "person-highlight", 
                                 getSimilerCharRegexPattern(this.getSearchQuery()));
                    getHtmlTree().expandAll();
                    SetSelectedNode(null);
                    setSelectionNo(0);
                }
                System.out.println(getDtoDetails().getCode().getKey());
            }
            //  initializeNavigation();
        } catch (DataBaseException e) {
            e.printStackTrace();
            setSerachResult(true);
            // getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            setSerachResult(true);
            setSelectedNodeId(null);
            setSelectionNo(0);
            SetSelectedNode(null);
            getHtmlTree().collapsePath(new String[] { "0" });
            //    getLovBaseBean().setMyTableData(new ArrayList(0));
        }
        return "";
    }

    public void searchTreeFromSpecificChar() throws DataBaseException, 
                                                    RemoteException, 
                                                    Exception {


        if (!enableSearchByCode) {
            super.setSearchType(1);
        }

        if (isUsingTreePaging()) {
            setSearchMode(true);
            setUsingPaging(true);
            setPagingRequestDTO(getTreeSearchPagingRequestDTO());
            resetPageIndex();
            setUpdateMyPagedDataModel(true);
            super.searchTree();
        } else {
            setTreeNodeBase(null);
            super.searchTreeFromSpecificChar();
        }

        if (searchTreeMethod != null && !searchTreeMethod.equals("")) {
            try {
                executeMethodBinding(searchTreeMethod, null);
            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    public void setSearchTypeCode(int searchTypeCode) {
        this.searchTypeCode = searchTypeCode;
    }

    public int getSearchTypeCode() {
        return searchTypeCode;
    }

    public void setSearchTypeName(int searchTypeName) {
        this.searchTypeName = searchTypeName;
    }

    public int getSearchTypeName() {
        return searchTypeName;
    }

    /**
     * @Used-in Paged Tree Div
     * @Updated-by MLotfy
     */
    public void cancelSearchTree() throws DataBaseException, RemoteException, 
                                          Exception {

        if (isUsingTreePaging()) {
            setUsingPaging(false);
        }

        if (cancelSearchMethod != null) {

            executeMethodBinding(cancelSearchMethod, null);

        } else {

            genericCancelSerch();

        }
    }

    void genericCancelSerch() throws DataBaseException, RemoteException, 
                                     Exception {

        if (this.getClientValueBinding() != null) {
            setClient((IBasicClient)evaluateValueBinding(this.getClientValueBinding()));
        }
        super.cancelSearchTree();

    }

    /**
     * @Used-in Paged Tree Div
     * @Auther MLotfy
     */
    public void generateTreeSearchPagingRequestDTO(String beanName, 
                                                   String methodName) {
        setTreeSearchPagingRequestDTO(new PagingRequestDTO(beanName, 
                                                           methodName));
    }

    /**
     * @Used-in Paged Tree Div
     * @Auther MLotfy
     */
    public void generateTreeListPagingRequestDTO(String beanName, 
                                                 String methodName) {
        setTreeListPagingRequestDTO(new PagingRequestDTO(beanName, 
                                                         methodName));
    }

    /**
     * @Used-in Paged Tree Div
     * @Auther MLotfy
     */
    public void changePageIndex(ActionEvent event) {

        super.changePageIndex(event);

        String pageBeanName = getTreeSearchPagingRequestDTO().getBeanName();
        executeMethodBindingWithParams(pageBeanName + ".setJavaScriptCaller", 
                                       new Object[] { "changeVisibilityDiv(window.blocker,window." + 
                                                      divName + 
                                                      ");focusOnSearchText();" }, 
                                       new Class[] { String.class });
    }

    public void setEnableSearchByCode(boolean enableSearchByCode) {
        this.enableSearchByCode = enableSearchByCode;
    }

    public boolean isEnableSearchByCode() {
        return enableSearchByCode;
    }

    public void setCancelSearchMethod(String cancelSearchMethod) {
        this.cancelSearchMethod = cancelSearchMethod;
    }

    public String getCancelSearchMethod() {
        return cancelSearchMethod;
    }

    public void setScrollX(String scrollX) {
        this.scrollX = scrollX;
    }

    public String getScrollX() {
        return scrollX;
    }

    public void setScrollY(String scrollY) {
        this.scrollY = scrollY;
    }

    public String getScrollY() {
        return scrollY;
    }

    //this method override to use getAll() method

    public List getTotalList() {

        List totalList = null;

        try {

            IBasicDTO filterDTO = getFilterDTO();

            if (filterDTO == null) {
                totalList = getClient().getAll();
            } else {
                totalList = getClient().getAll(filterDTO);
            }

        } catch (SharedApplicationException ne) {
            totalList = new ArrayList();
            ne.printStackTrace();
        } catch (DataBaseException db) {
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            getSharedUtil().handleException(e);
        }

        return totalList;

    }

    //this method override to use searchByCode(Object code) and searchByName(String name) methods

    public List getBaseSearchResult() throws DataBaseException {

        List searchResult = new ArrayList();

        try {
            if (getSearchType().intValue() == 0) {
                searchResult = getClient().searchByCode(getSearchEntityObj());
            } else if (getSearchType().intValue() == 1) {
                searchResult = 
                        getClient().searchByName(formatSearchString(getSearchQuery()));
            }
        } catch (ItemNotFoundException e) { //this means no search results found
            setMyTableData(new ArrayList(0));
        } catch (NoResultException ne) { //this means no search results found
            setMyTableData(new ArrayList());
        } catch (Exception db) {

            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_display}").getValue(FacesContext.getCurrentInstance());

            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());

        }

        return searchResult;
    }


    //this method override to use getById(IEntityKey id) method

    public void showEditDiv() {
        setPageMode(2);
        reIntializeMsgs();
        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {
            //            selectedPageDTO=this.getSelectedDTOS().get(0);
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                setSelectedPageDTO(getClient().getById(dto.getCode()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            setShowEdit(true);

            //javaScriptCaller = "changeVisibilityDiv(window.blocker,window.lookupEditDiv);";


        } else {
            setSelectedPageDTO(new BasicDTO());
            setShowEdit(false);

        }
    }

    public Integer getListSize() throws DataBaseException {
        if (isUsingPaging()) {

            PagingBean pagingBean = getPagingBean();

            try {
                if (pagingBean.getMyPagedDataModel() == null) {

                    pagingBean.getMyPagedDataModel();

                }
            } catch (NoResultException e) {
                e.printStackTrace();
            }

            return pagingBean.getTotalListSize();

        } else {

            if (getMyTableData() != null && getMyTableData() != null) {
                return getMyTableData().size();
            }

            return 0;

        }
    }

    public IEntityKey getSelectedEntityKey(String nodeId) {
        if (selectedEntityKeyMethod == null) {
            return InfEntityKeyFactory.createKwMapEntityKey(nodeId);
        }
        Object[] objects = new Object[1];
        objects[0] = nodeId;

        Class[] classes = new Class[1];
        classes[0] = String.class;

        return (IEntityKey)executeMethodBindingWithParams(selectedEntityKeyMethod, 
                                                          objects, classes);
    }

    public void setSelectedEntityKeyMethod(String selectedEntityKeyMethod) {
        this.selectedEntityKeyMethod = selectedEntityKeyMethod;
    }

    public String getSelectedEntityKeyMethod() {
        return selectedEntityKeyMethod;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getDivName() {
        return divName;
    }

    public void setReadCenter(boolean readCenter) {
        this.readCenter = readCenter;
    }

    public boolean isReadCenter() {
        return readCenter;
    }

    public void setGoActionOkMethod(String goActionOkMethod) {
        this.goActionOkMethod = goActionOkMethod;
    }

    public String getGoActionOkMethod() {
        return goActionOkMethod;
    }

    public void setGoActionBackMethod(String goActionBackMethod) {
        this.goActionBackMethod = goActionBackMethod;
    }

    public String getGoActionBackMethod() {
        return goActionBackMethod;
    }

    public void setSearchTreeMethod(String searchTreeMethod) {
        this.searchTreeMethod = searchTreeMethod;
    }

    public String getSearchTreeMethod() {
        return searchTreeMethod;
    }

    public void setIdChangeMethod(String idChangeMethod) {
        this.idChangeMethod = idChangeMethod;
    }

    public String getIdChangeMethod() {
        return idChangeMethod;
    }

    public BesharaTree buildTree() throws DataBaseException {
        try {

            if (isUsingTreePaging()) {
                setTreeList(getMyTableData());
            } else {
                if (startTreeLevel.equals(ISystemConstant.FIRST_LEVEL_IN_TREE)) {
                    setTreeList(Helper.buildTree(getMyTableData()));
                } else {
                    setTreeList(Helper.buildTree(getMyTableData(), 
                                                 startTreeLevel));
                }

            }

            String treeLevel = "0";
            setTreeNodeBase(new BesharaTree("foo-folder", 
                                            getBundle().getString(getRootName()), 
                                            "0", null, true, false, false, 
                                            treeLevel));

            for (int i = 0; i < getTreeList().size(); i++) {
                treeLevel = "0";
                ITreeDTO item = (ITreeDTO)getTreeList().get(i);
                if (item.getChildernNumbers() != 0) {
                    setHasChild(true);
                } else {
                    setHasChild(false);
                }
                BesharaTree treeNode = null;

                if (item.getChildernNumbers() > 0) {
                    treeLevel = "0:" + i;
                }

                if (getKeyIndex() == -1)
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), (String)item.getCode().getKey(), 
                                            null, isHasChild(), 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag(), 
                                            treeLevel);
                else if (getKeyIndex() > -1) {
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), item.getCode().getKeys()[getKeyIndex()].toString(), 
                                            item.getCode().getKey().toString(), 
                                            null, isHasChild(), 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag(), 
                                            treeLevel);
                }

                if (treeNode != null) {
                    treeNode.setParent(getTreeNodeBase());
                    getTreeNodeBase().getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            setTreeNodeBase(new BesharaTree("foo-folder", 
                                            getBundle().getString(getRootName()), 
                                            "0", null, true, false, false));
        }


        try {
            return getTreeNodeBase();
        } catch (Exception e) {
            e.printStackTrace(); // TODO
            return null; // TODO
        }
    }


    public String getSelectedStyleClass() {

        BesharaTree node = (BesharaTree)getHtmlTree().getNode();
        if (node.getDetailId() != null &&  node.getDetailId().equals(getSelectedNodeId())||  node.getTreeId() != null &&node.getTreeId().equals(getSelectedNodeId())    ) {
            return "newHigthLighttreepage-link";
        }
        return "treepage-link";
    }
    
    public String getSearchSelectedStyleClass() {

        BesharaTree node = (BesharaTree)getHtmlTree().getNode();
        if (node.getDetailId() != null &&  node.getDetailId().equals(getSelectedNodeId())||  node.getTreeId() != null &&node.getTreeId().equals(getSelectedNodeId())    ) {
            return "newHigthLighttreepage-link";
        }
        return "HigthLighttreepage-link";
    }


    public void setStartTreeLevel(Long startTreeLevel) {
        this.startTreeLevel = startTreeLevel;
    }

    public Long getStartTreeLevel() {
        return startTreeLevel;
    }
    ////////////////for tree paging

    public PagingResponseDTO getChildrenNodes(PagingRequestDTO pagingRequest) throws DataBaseException, 
                                                                                     SharedApplicationException {

        BesharaTree node = (BesharaTree)pagingRequest.getParams()[0];
        List resultList = null;

        resultList = 
                ((IKwMapClient)getClient()).getChildrenList(getSelectedEntityKey(node.getTreeId()));


        setMyTableData(resultList);

        return new PagingResponseDTO(resultList);

    }


    public Integer getPagedTreeListSize() {
        if (super.getPagedTreeListSize() == 0) {
            fillPagedTreeListSize();
        }
        return super.getPagedTreeListSize();
    }

    public void fillPagedTreeListSize() {
        Integer TreePageSize = 0;
        try {
            TreePageSize = (((IKwMapClient)getClient()).getTotalNumber());
        } catch (DataBaseException e) {
            ;
        } catch (SharedApplicationException e) {
            ;
        }

        setPagedTreeListSize(TreePageSize);
    }

    public void getAll() throws DataBaseException {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            setMyTableData(((IKwMapClient)getClient()).getFirstLevel());
            if (getSelectedDTOS() != null)
                getSelectedDTOS().clear();
            if (getHighlightedDTOS() != null)
                getHighlightedDTOS().clear();
        } catch (DataBaseException db) {
            db.printStackTrace();
            shared_util.setErrMsgValue(db.getMessage());
        } catch (SharedApplicationException e) {
            shared_util.setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setSaveResult(List saveResult) {
        this.saveResult = saveResult;
    }

    public List getSaveResult() {
        return saveResult;
    }

    public void setSearchSelectedStyleClass(String searchSelectedStyleClass) {
        this.searchSelectedStyleClass = searchSelectedStyleClass;
    }

    public String get_searchSelectedStyleClass() {
        return searchSelectedStyleClass;
    }
}

