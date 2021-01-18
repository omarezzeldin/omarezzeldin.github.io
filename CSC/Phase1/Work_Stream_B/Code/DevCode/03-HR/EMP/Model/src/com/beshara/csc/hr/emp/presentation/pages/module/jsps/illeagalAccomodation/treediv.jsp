<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


<t:saveState value="#{TreePaggedDivBean.enabledRoot}"/>
<t:saveState value="#{TreePaggedDivBean.selectionNo}"/>

<t:saveState value="#{TreePaggedDivBean.searchMode}"/>
<t:saveState value="#{TreePaggedDivBean.showTreeContent}"/>
<t:saveState value="#{TreePaggedDivBean.searchType}"/>
<t:saveState value="#{TreePaggedDivBean.clientValueBinding}"/>
<t:saveState value="#{TreePaggedDivBean.enabledNotLeaf}"/>
<t:saveState value="#{TreePaggedDivBean.enableSearchByCode}"/>
<t:saveState value="#{TreePaggedDivBean.keyIndex}"/>
<t:saveState value="#{TreePaggedDivBean.cancelSearchMethod}"/>

<t:saveState value="#{TreePaggedDivBean.goActionOkMethod}"/>
<t:saveState value="#{TreePaggedDivBean.goActionBackMethod}"/>
<t:saveState value="#{TreePaggedDivBean.cancelSearchMethod}"/>
<t:saveState value="#{TreePaggedDivBean.idChangeMethod}"/>
<t:saveState value="#{TreePaggedDivBean.searchTreeMethod}"/>

<t:saveState value="#{TreePaggedDivBean.myTableData}"/>
<t:saveState value="#{TreePaggedDivBean.startTreeLevel}"/>

    <t:saveState value="#{TreePaggedDivBean.searchMode}"/>
    <t:saveState value="#{TreePaggedDivBean.treeNodeBase}"/>
    <t:saveState value="#{TreePaggedDivBean.usingTreePaging}"/>
    <t:saveState value="#{TreePaggedDivBean.treeSearchPagingRequestDTO}"/>
    <t:saveState value="#{TreePaggedDivBean.treeListPagingRequestDTO}"/>    
    <t:saveState value="#{TreePaggedDivBean.pagedTreeListSize}"/>
    <t:saveState value="#{TreePaggedDivBean.dtoDetails}"/>
    <t:saveState value="#{TreePaggedDivBean.selectedNodeTreeLevelId}"/>
    <t:saveState value="#{TreePaggedDivBean.saveResult}"/>
     
    
    <t:panelGroup forceId="true" id="radioTreeDivPanel">
   <t:panelGrid align="center"  border="0" rendered="#{TreePaggedDivBean.enableSearchByCode}" >
       <t:selectOneRadio styleClass="divtext" forceId="true" id="radioTreeDivButton"  value="#{TreePaggedDivBean.searchType}" >
           <f:selectItem itemLabel="#{globalResources.Code}" itemValue="#{TreePaggedDivBean.searchTypeCode}"/>
           <f:selectItem itemLabel="#{globalResources.name}" itemValue="#{TreePaggedDivBean.searchTypeName}"/>
       </t:selectOneRadio>
   </t:panelGrid>
</t:panelGroup>

<h:panelGrid columns="3">
    <t:outputLabel rendered="#{!TreePaggedDivBean.enableSearchByCode}" value="#{globalResources.name}" styleClass="treepage-link"/>
    <t:inputText forceId="true" id="searchText1"  value="#{TreePaggedDivBean.searchQuery}" styleClass="textboxmedium" onkeypress="FireButtonClick('myForm:searchTreeDivButtonID');"/>
    <t:panelGrid columns="2" align="center">
        <a4j:commandButton action="#{TreePaggedDivBean.searchinTree}" reRender="treeDivList,cancelsearchpanel,noResult,okPanel,theSelectedNodeId" oncomplete="focusOnSearchResult();" styleClass="ManyToManySearchButton" id="searchTreeDivButtonID"/>
        <h:panelGroup id="cancelsearchpanel">
            <f:verbatim>&nbsp;</f:verbatim>
            <a4j:commandButton action="#{TreePaggedDivBean.cancelSearchTree}" reRender="treeDivList,searchText1,cancelsearchpanel,noResult,clientErrorMessage,okPanel,radioTreeDivPanel,theSelectedNodeId" rendered="#{TreePaggedDivBean.searchMode == true}"
                              styleClass="ManyToManyCancelSearchButton" binding="#{TreePaggedDivBean.cancelSearchCommandButton}" oncomplete="document.getElementById('theSelectedNodeId').value ='';"/>
        </h:panelGroup>
    </t:panelGrid>
    </h:panelGrid>
    
<t:panelGrid width="100%">
   
    <t:panelGroup id="noResult">
        <t:panelGrid align="center" rendered="#{TreePaggedDivBean.serachResult}" >
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
     
    <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" id="treeList" forceId="true" >       
        <t:panelGroup id="treeDivPanel" forceId="true" onkeypress="FireButtonClick('myForm:treeDivOkBtn')" styleClass="TreeDivScrol">  
 
            <a4j:jsFunction name="updatetree" reRender="treeList,OperationBar,treeAlertDelete,treeDetailsDiv,theSelectedNodeId,okPanel,treeDivOkBtn,noResult" oncomplete="foucsAtServerTree();focusHighlightedNode();" ></a4j:jsFunction>
  
                <t:panelGrid columns="1" align="right" dir="#{jobResource.align}" forceId="true" id="treeDivList">
 
                    <t:tree2 id="clientTree" value="#{TreePaggedDivBean.treeNodeBase}" 
                        var="node" imageLocation="/app/media/images/ar/tree2" 
                        varNodeToggler="t" binding="#{TreePaggedDivBean.htmlTree}"
                        clientSideToggle="false" showNav="false">

                        <f:facet name="foo-folder">
                            <t:panelGroup>
                                <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{TreePaggedDivBean.expand}"
                                    reRender="treeList,cancelsearchpanel"  oncomplete="foucsAtServerTree();" 
                                    onmousedown="javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');setCmdTreeId(this);reStyle(this,'treeDivList','label');"
                                    onkeypress="setCmdTreeId(this);collapseExpandServerTree(this);" rendered="#{node.hasChild && TreePaggedDivBean.listSize >0}">

                                    <t:graphicImage value="/app/media/images/minus_open_Root.gif"
                                        rendered="#{node.parent == null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                        rendered="#{node.parent == null && !node.expanded}" border="0"/>                                        
                                    <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                        rendered="#{node.parent != null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                        rendered="#{node.parent != null && !node.expanded}" border="0"/>
                                </a4j:commandLink>

                                <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{!node.hasChild || TreePaggedDivBean.listSize == 0}" border="0"/>                                
                                <t:commandLink id="hiddenLink" value="" />
                                <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                    onmousedown="reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';document.getElementById('selectedNodeTreeLevelId').value ='#{node.path}';javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');"
                                    value="[#{node.treeId}] #{node.description}" styleClass="#{TreePaggedDivBean.selectedStyleClass}"/> <%-- #{node.treeNodeLevelsID}--%>
                                <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>    
                            </t:panelGroup>

                        </f:facet>

                        <f:facet name="person-highlight" >
                                <t:panelGroup>
                                    <a4j:commandLink id="expandCollapseCmdLink" actionListener="#{TreePaggedDivBean.expand}"
                                    reRender="treeList" onmousedown="setCmdTreeId(this);" oncomplete="foucsAtServerTree();" 
                                    onkeypress="setCmdTreeId(this);collapseExpandServerTree(this);" rendered="#{node.hasChild}">
                                    
                                    <t:graphicImage value="/app/media/images/minus_open_Root.gif"
                                        rendered="#{node.parent == null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/plus_close_Root.gif"
                                        rendered="#{node.parent == null && !node.expanded}" border="0"/>                                        
                                    <t:graphicImage value="/app/media/images/Middle_minus_open.gif"
                                        rendered="#{node.parent != null && node.expanded}" border="0"/>
                                    <t:graphicImage value="/app/media/images/Middle_root_plus_close.gif"
                                        rendered="#{node.parent != null && !node.expanded}" border="0"/>
                                </a4j:commandLink>
                                
                                <t:graphicImage value="/app/media/images/line-middle-tree.gif"
                                        rendered="#{!node.hasChild}" border="0"/>
                                  <t:commandLink id="hiddenLink"  value=""  />
                                   <t:outputLabel id="commandDescription2" onclick="updatetree();"
                                       onmousedown="reStyle(this,'treeDivList','label');document.getElementById('theSelectedNodeId').value ='#{node.treeId}';document.getElementById('selectedNodeTreeLevelId').value ='#{node.path}';javascript:setTreeNodeLevel('#{node.treeNodeLevelsID}');"
                                        value="[#{node.treeId}] #{node.description}" styleClass="#{TreePaggedDivBean.searchSelectedStyleClass}"/> <%-- #{node.treeNodeLevelsID}--%>
                                    <t:inputHidden id="expandCollapseValue" value="#{node.expanded}"/>
                                     <t:inputText id="nodedivfocus" forceId="true" styleClass="textboxnodefocus"/>
                                </t:panelGroup> 
                            </f:facet>
                        
                    </t:tree2>
                    
                </t:panelGrid>
               
            </t:panelGroup>
                        
                    
        </t:panelGrid>
                    <!-- This part responsibility to highlight the node when selected-->
                    <t:inputHidden forceId="true" id="theSelectedNodeId" value="#{TreePaggedDivBean.selectedNodeId}"  valueChangeListener="#{TreePaggedDivBean.idChange}"/>
                    <t:inputHidden forceId="true" value="0" id="rootID"/>
                    <t:inputHidden forceId="true" id="lastNode" value=""/>
                    <t:inputHidden forceId="true" value="#{TreePaggedDivBean.treeNodeNameForCollapseExpand}" id="treeNodeNameForCollapseExpand"/>
                    <t:inputHidden forceId="true" id="selectedNodeTreeLevelId" value="#{TreePaggedDivBean.selectedNodeTreeLevelId}"/>
                          <t:panelGrid columns="2" align="center">
        <h:panelGroup id="okPanel">
         <a4j:commandButton id="treeDivOkBtn" binding="#{TreePaggedDivBean.okCommandButton}"  value="#{globalResources.ok}" styleClass="cssButtonSmall" actionListener="#{TreePaggedDivBean.goActionOk}"
                               oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();okButtonTreeDivJs();" /> 
         <a4j:jsFunction name="okButtonTreeDivJs" reRender="edit_StreetID,add_StreetID" />
        </h:panelGroup>
        <t:panelGroup>
            <t:commandButton type="button" forceId="true" id="backButtonTreeDiv" onblur="if(isVisibleComponent('divTree')){settingFoucsAtTreeDiv();}" onclick="backButtonTreeDivJs();block(); " value="#{globalResources.back}" styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="backButtonTreeDivJs" actionListener="#{TreePaggedDivBean.goActionBack}" oncomplete="hidTreeDiv(null,window.blocker,window.divTree,null);focusAfterBackFromTreeDiv();"/>
        </t:panelGroup>
    </t:panelGrid>
                    </t:panelGrid>
<f:verbatim>
    <script language="javascript" type="text/javascript">
//        if(document.getElementById("nodefocus") != null){
//           setTimeout("document.getElementById('nodefocus').focus()",400);
//       }
      
//    if(document.getElementById("selectedNodeTreeLevelId")!=null)
//       alert(document.getElementById("selectedNodeTreeLevelId").value);

      setTimeout("collapseExpandTreeNode();",400);
      setTimeout("focusHighlightedNode();",400);
      setTimeout("focusOnSearchResult();",400);
       function focusHighlightedNode(){  
           //alert(document.getElementById("selectedNodeTreeLevelId").value);
        
           if(document.getElementById("selectedNodeTreeLevelId")!=null)
               {
                var levelId=document.getElementById("selectedNodeTreeLevelId").value;
                if(levelId != ""){
                    var cmdId="myForm:clientTree:"+levelId+":expandCollapseCmdLink";
                    if(document.getElementById(cmdId)==null){
                        cmdId="myForm:clientTree:"+levelId+":hiddenLink";
                    }     
                    resetScrollPositionAfterSelection(cmdId);
                    document.getElementById(cmdId).focus();
                    document.getElementById(cmdId).focus();
                }
           }
       
       }
        function focusOnSearchResult(){
       if(document.getElementById("nodedivfocus") != null){
           setTimeout("document.getElementById('nodedivfocus').focus()",400);
           setTimeout("document.getElementById('nodedivfocus').select()",400);
       }
   }
   function focusAfterBackFromTreeDiv(){
        if(typeof(setFocusAfterBackFromTreeDiv)!='undefined'){
            setFocusAfterBackFromTreeDiv();
        }
   }
    function resetScrollPositionAfterSelection(cmdId) {
        if(document.getElementById(cmdId)!=null){
            var x =findPosX(cmdId); 
            var y = findPosY(cmdId);
            document.getElementById('treeDivPanel').scrollLeft = x;
            document.getElementById('treeDivPanel').scrollTop = y;
            
        } else {
       // alert('prob');
        }
    }
        
        
    </script>
</f:verbatim>
