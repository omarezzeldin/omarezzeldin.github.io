//package com.beshara.csc.nl.org.presentation.backingbean.workcenters;
package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.nl.org.business.client.IWorkCentersClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.util.Helper;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.event.ValueChangeEvent;


public class WorkCentersDependTo extends TreeDivBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private String selectedMinistryryId = getVirtualConstValue();
    private Long startTreeLevel = ISystemConstant.FIRST_LEVEL_IN_TREE;
    private IWorkCentersDTO selectedWorkCenterDTO;

    private Boolean dependTo = false;
    private Boolean pageTitle = false;
    private Integer pagedTreeListSize;

    public WorkCentersDependTo() {
        setBundle(ResourceBundle.getBundle("com.beshara.csc.hr.emp.integration.presentation.resources.integration_" +
                                           ((SharedUtilBean)getSession().getAttribute("shared_util")).getLocale()));
//            setBundle(ResourceBundle.getBundle("com.beshara.csc.hr.emp.presentation.resources.emp_" +
//                                               getSharedUtil().getLocale()));
        setRootName("WorkCenterTitle");
        this.setClient(OrgClientFactory.getWorkCentersClient());
        setPageDTO(OrgDTOFactory.createWorkCentersDTO());
        setDto(OrgDTOFactory.createWorkCentersDTO());

        setKeyIndex(1);
        setUsingTreePaging(false);
    }
    
    
    public IWorkCentersDTO getSelectedWorkCenterDTO() {
        return selectedWorkCenterDTO;
    }

    public static WorkCentersDependTo getInstance() {
        return (WorkCentersDependTo)JSFHelper.getValue("workCentersDependToBean");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app = super.appMainLayoutBuilder();
        app.setShowCustomDiv1(true);
        // app.setShowCustomDiv2(true);

        return app;
    }

    public void openDependToDiv() {
        try {
            getAll();
            setStartTreeLevel(selectedWorkCenterDTO.getTreeLevel());

            buildTree();
            getHtmlTree().expandAll();
            
            //getHtmlTree().expandPath(new String[] {"0:1"});
            
            List<BesharaTree> list = new ArrayList<BesharaTree>();
            
            list.add(getTreeNodeBase());
            setSearchType(1);
            Pattern pattern = Pattern.compile(selectedWorkCenterDTO.getName());
            highlightSearchedResultFromSpecificChar(list, selectedWorkCenterDTO.getName(), "person-highlight", pattern);
            //getTreeNodeBase().setExpanded(true);
             
            //getHtmlTree().expandPath(new String[] {"0:0"});
            setMyTableData(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
        } catch (Exception e) {
        }
        //        catch (RemoteException e) {
        //        } catch (Exception e) {
        //        }
    }

    public void openDependFromDiv() {
        try {
            getAll();
            setStartTreeLevel(ISystemConstant.FIRST_LEVEL_IN_TREE);
            buildTree();
            getHtmlTree().expandAll();
            List<BesharaTree> list = new ArrayList<BesharaTree>();
            
            list.add(getTreeNodeBase());
            setSearchType(1);
            Pattern pattern = Pattern.compile(selectedWorkCenterDTO.getName());
            highlightSearchedResultFromSpecificChar(list, selectedWorkCenterDTO.getName(), "person-highlight", pattern);
            //getHtmlTree().expandPath(new String[] {"0:0"});
            setMyTableData(new ArrayList());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
        } catch (Exception e) {
        }
       
    }
   

//    public Integer getPagedTreeListSize() {
//        if (getPagedTreeListSize() == 0) {
//            fillPagedTreeListSize();
//        }
//        return getPagedTreeListSize();
//    }

//    public void fillPagedTreeListSize() {
//        Integer TreePageSize = 0;
//        if (!selectedMinistryryId.equals(getVirtualConstValue())) {
//            try {
//                TreePageSize =
//                        ((IWorkCentersClient)getClient()).getAllChildrenCount(OrgEntityKeyFactory.createMinistriesEntityKey(Long.parseLong(selectedMinistryryId)));
//            } catch (DataBaseException e) {
//                ;
//            }
//        }
//        setPagedTreeListSize(TreePageSize);
//    }

    public void getAll() throws DataBaseException {
        if (selectedWorkCenterDTO != null) {
            SharedUtilBean shared_util = getSharedUtil();

            try {

                if (selectedWorkCenterDTO != null && selectedWorkCenterDTO.getCode() != null ) {
                    List<IWorkCentersDTO> list =
                        (List)OrgClientFactory.getWorkCentersClient().getUpperLevelTreeByWrkCode(selectedWorkCenterDTO);
                    setMyTableData(list);

                    System.out.println("get All " + list.size());

                }
//                if (selectedWorkCenterDTO.getCode() != null && getDependTo() == false) {
//                    List<IWorkCentersDTO> list =
//                        (List)OrgClientFactory.getWorkCentersClient().getDependFromByWrkDTO(selectedWorkCenterDTO);
//                    setMyTableData(list);
//                }


                if (getSelectedDTOS() != null) {
                    getSelectedDTOS().clear();
                }
                if (getHighlightedDTOS() != null) {
                    getHighlightedDTOS().clear();
                }
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
    }

    public void setSelectedMinistryryId(String selectedMinistryryId) {
        this.selectedMinistryryId = selectedMinistryryId;
    }

    public String getSelectedMinistryryId() {
        return selectedMinistryryId;
    }

    public void setSelectedWorkCenterDTO(IWorkCentersDTO selectedWorkCenterDTO) {
        this.selectedWorkCenterDTO = selectedWorkCenterDTO;
    }

    public void idChange(ValueChangeEvent e) {
        String nodeCode = (String)e.getNewValue();

        if (nodeCode != null && !nodeCode.equals("")) {
         //   setHighLightedDTO(null);
            // if there is composte key
            //if (keyIndex > -1 && !usingTreePaging) {
            try {
                nodeCode = getFullEntityKey((List<BesharaTree>)getTreeNodeBase().getChildren(), nodeCode);

            } catch (Exception f) {
                f.printStackTrace();
            }
            //}

            if (nodeCode != null && !nodeCode.equals("0")) {

                SharedUtilBean shared_util = getSharedUtil();
                try {
                    setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), nodeCode));

                    this.setAlreadyDeleted(false);

                    setDtoDetails((ITreeDTO)this.preEdit());

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

                    setParentName(getDtoDetails().getName());
                    setParentLevel(nodeCode);
                } catch (ItemNotFoundException ex) {
                    System.out.println("treebase bean::idchange::ItemNotFoundException=" + ex);
                    this.setAlreadyDeleted(true);
                } catch (DataBaseException ex) {
                    shared_util.setErrMsgValue(ex.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    shared_util.setErrMsgValue(ex.getMessage());
                }
            } else {
                setParentName(getBundle().getString(getRootName()));
                setParentLevel(getVirtualLevelCode().toString());
                setEnabledRoot(true);
                setEnabledNotLeaf(false);
                setEnabledNotHasChlid(true);
                getDtoDetails().setCode(null);
                getDtoDetails().setParentObject(null);
                getDtoDetails().setParentCode(null);
                getDtoDetails().setName(getParentName());

            }
            setSelectionNo(1);
        } else {
            setSelectedNodeId(null);
        }
    }

    public void SetSelectedNode(String nodeCode) {


        if (nodeCode != null && !nodeCode.equals("")) {
           // setHighLightedDTO(null);
            // if there is composte key
            if (getKeyIndex() > -1 && !isUsingTreePaging()) {
                try {
                    /*nodeCode =
                            getFullEntityKey((List<BesharaTree>)getTreeNodeBase().getChildren(),
                                             nodeCode);*/
                    setSelectedNodeId(nodeCode);

                } catch (Exception f) {
                    f.printStackTrace();
                }
            }

            if (nodeCode != null && !nodeCode.equals("0")) {

                SharedUtilBean shared_util = getSharedUtil();
                try {
                    if (isUsingTreePaging()) {
                        setEntityKey(getSelectedEntityKey(nodeCode));
                        setSelectedNodeId(nodeCode);
                    } else {
                        setEntityKey(Helper.getEntityKey((List<IBasicDTO>)getMyTableData(), nodeCode));
                        setSelectedNodeId(nodeCode);
                    }
                    this.setAlreadyDeleted(false);

                    setDtoDetails((ITreeDTO)this.preEdit());

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
                //setDtoDetails(null);
                initializeDtoDetails();
                //getDtoDetails().setTreeLevel(virtualLevelCode.toString());

            }
            setSelectionNo(1);
        } else {
            setSelectedNodeId(null);
        }
    }

    public void setDependTo(Boolean dependTo) {
        this.dependTo = dependTo;
    }

    public Boolean getDependTo() {
        return dependTo;
    }

    public void setPageTitle(Boolean pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Boolean getPageTitle() {
        return pageTitle;
    }


    public void setPagedTreeListSize(Integer pagedTreeListSize) {
        this.pagedTreeListSize = pagedTreeListSize;
    }

    public Integer getPagedTreeListSize1() {
        return pagedTreeListSize;
    }
    
    public String getSelectedStyleClass() {

        BesharaTree node = (BesharaTree)getHtmlTree().getNode();
        if (node.getDetailId() != null &&  node.getDetailId().equals(getSelectedNodeId())||  node.getTreeId() != null &&node.getTreeId().equals(getSelectedNodeId())    ) {
            return "newHigthLighttreepage-link";
        }
        return "treepage-link";
    }
}
