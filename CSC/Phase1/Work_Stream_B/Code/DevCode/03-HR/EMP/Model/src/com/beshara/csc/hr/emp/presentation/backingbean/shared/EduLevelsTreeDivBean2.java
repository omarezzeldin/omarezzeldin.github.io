package com.beshara.csc.hr.emp.presentation.backingbean.shared;


import com.beshara.base.dto.ITreeDTO;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.IEducationLevelsDTO;
import com.beshara.csc.nl.qul.business.dto.QulDTOFactory;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.jsfbase.csc.backingbean.BesharaTree;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.util.Helper;

import java.rmi.RemoteException;

import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class EduLevelsTreeDivBean2 extends TreeDivBean {
    public static final String BEAN_NAME = "empMainDataBean";

    public EduLevelsTreeDivBean2() {
        setRootName("eduLevel");
        setClient(QulClientFactory.getEducationLevelsClient());
        setClientValueBinding(BEAN_NAME + ".levelsClient");
        setPageDTO(QulDTOFactory.createEducationLevelsDTO());
        setBundle(ResourceBundle.getBundle("com.beshara.csc.hr.emp.presentation.resources.emp_ar"));
        setDto(QulDTOFactory.createEducationLevelsDTO());
        setDtoDetails(QulDTOFactory.createEducationLevelsDTO());
        setEntityKey(QulEntityKeyFactory.createEducationLevelsEntityKey(null));
    }

    public BesharaTree buildTree() throws DataBaseException {
        try {

          //  if (isUsingTreePaging()) {
                setTreeList(getMyTableData());
//            } else {
//                if (getStartTreeLevel().equals(ISystemConstant.FIRST_LEVEL_IN_TREE)) {
//                    setTreeList(Helper.buildTree(getMyTableData()));
//                } else {
//                    setTreeList(Helper.buildTree(getMyTableData(), 
//                                                 getStartTreeLevel()));
//                }
//
//            }

            String treeLevel = "0";

            BesharaTree rootNode = 
                new BesharaTree("foo-folder", getBundle().getString(rootName), 
                                "0", null, true, false, false, treeLevel);
            rootNode.setPrivateCode(treeLevel);
            setTreeNodeBase(rootNode);

            for (int i = 0; i < treeList.size(); i++) {
                treeLevel = "0";
                ITreeDTO item = (ITreeDTO)treeList.get(i);
                if (item.getChildernNumbers() != 0) {
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaTree treeNode = null;

                if (item.getChildernNumbers() > 0) {
                    treeLevel = "0:" + i;
                }

                if (keyIndex == -1) {
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), (String)item.getCode().getKey(), 
                                            null, hasChild, 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag(), 
                                            treeLevel);
                } else if (keyIndex > -1) {
                    treeNode = 
                            new BesharaTree("foo-folder", item.getName(), item.getCode().getKeys()[keyIndex].toString(), 
                                            item.getCode().getKey().toString(), 
                                            null, hasChild, 
                                            item.isBooleanLeafFlag(), 
                                            item.isBooleanLeafFlag(), 
                                            treeLevel);
                }

                treeNode.setPrivateCode(((IEducationLevelsDTO)item).getPrivateCode());

                if (treeNode != null) {
                    treeNode.setParent(treeNodeBase);
                    treeNodeBase.getChildren().add(treeNode);
                    parseTree(item.getChildrenList(), item, treeNode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            treeNodeBase = 
                    new BesharaTree("foo-folder", getBundle().getString(rootName), 
                                    "0", null, true, false, false);
        }

        return treeNodeBase;
    }

    public BesharaTree parseTree(List treeList, ITreeDTO treeDTO, 
                                 BesharaTree treeNodeBase) throws DataBaseException, 
                                                                  RemoteException {
        String treeLevel = treeNodeBase.getTreeNodeLevelsID();
        for (int i = 0; i < treeList.size(); i++) {

            String tempTreeLevel = treeLevel;
            if (treeNodeBase.getChildCount() > 0) {
                tempTreeLevel = treeLevel + ":" + i;
            }
            ITreeDTO treeDTOElem = (ITreeDTO)treeList.get(i);

            if (treeDTOElem.getParentCode() != null && 
                (treeDTOElem.getParentCode().getKey().equals(treeDTO.getCode().getKey()))) {
                if (treeDTOElem.getChildernNumbers() != 0) {
                    hasChild = true;
                } else {
                    hasChild = false;
                }
                BesharaTree treeNode = null;
                if (keyIndex == -1) {
                    treeNode = 
                            new BesharaTree("foo-folder", treeDTOElem.getName(), 
                                            (String)treeDTOElem.getCode().getKey(), 
                                            (String)treeDTOElem.getParentCode().getKey(), 
                                            hasChild, 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            tempTreeLevel);
                } else if (keyIndex > -1) {
                    treeNode = 
                            new BesharaTree("foo-folder", treeDTOElem.getName(), 
                                            treeDTOElem.getCode().getKeys()[keyIndex].toString(), 
                                            treeDTOElem.getCode().getKey().toString(), 
                                            (String)treeDTOElem.getParentCode().getKey(), 
                                            hasChild, 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            treeDTOElem.isBooleanLeafFlag(), 
                                            tempTreeLevel);
                }

                treeNode.setPrivateCode(((IEducationLevelsDTO)treeDTOElem).getPrivateCode());

                if (treeNode != null) {
                    treeNode.setParent(treeNodeBase);
                    treeNodeBase.getChildren().add(treeNode);
                    if (treeDTOElem.getChildernNumbers() != 0) {
                        parseTree(treeDTOElem.getChildrenList(), treeDTOElem, 
                                  treeNode);
                    }
                }

            }
        }
        return treeNodeBase;
    }

    public void highlightSearchedResult(List<BesharaTree> list, 
                                        String searchedString, String facet) {
        for (BesharaTree child: list) {
            if (this.getSearchType().intValue() == 0) {
                if (searchedString != null && !searchedString.equals("") && 
                    child.getTreeId().equals(searchedString)) {
                    child.setType(facet);
                    this.setFounded(true);
                    setSelectedListSize(1);
                    setSearchResultListSize(getSearchResultListSize() + 1);
                } else {
                    child.setType("foo-folder");
                }
            } else {
                String childIdentifier = 
                    child.getIdentifier().trim().toLowerCase();
                String searchBy = searchedString.trim().toLowerCase();
                if (Helper.searchInTree(searchBy, childIdentifier, false)) {
                    child.setType(facet);
                    this.setFounded(true);
                    setSearchResultListSize(getSearchResultListSize() + 1);
                    setSelectedListSize(1);
                } else {
                    child.setType("foo-folder");
                }
            }

            if (child.getChildCount() != 0) {
                highlightSearchedResult(child.getChildren(), searchedString, 
                                        facet);
            }
        }
    }

    public void searchByCode(List<BesharaTree> list, String searchedString, 
                             String facet) {
        for (BesharaTree child: list) {
            if (searchedString != null && !searchedString.equals("") && child.getTreeId()!= null &&
                child.getTreeId().equals(searchedString)) {
                child.setType(facet);
                this.setFounded(true);
                setSelectedListSize(1);
                setSearchResultListSize(getSearchResultListSize() + 1);
            } else {
                child.setType("foo-folder");
            }
            if (child.getChildCount() != 0) {
                Pattern pattern = null;
                highlightSearchedResultFromSpecificChar(child.getChildren(), 
                                                        searchedString, facet, 
                                                        pattern);
            }
        }

    }
}
