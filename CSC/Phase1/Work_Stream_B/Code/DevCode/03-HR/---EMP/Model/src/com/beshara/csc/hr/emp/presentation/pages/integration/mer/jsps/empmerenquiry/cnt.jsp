<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:outputText   value="#{integrationBundle.emp_Stop_message}" rendered="#{pageBeanName.empStopFlag}" styleClass="errMsg"/>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" columns="2">
        <htm:table width="100%">
           <htm:tr>
               <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                   <htm:td styleClass="group_title">&nbsp;<t:outputLabel value="#{integrationBundle.benfits}" styleClass="lable01"/> </htm:td>
               </htm:tr>
           <htm:tr><htm:td colspan="2" height="1"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td></htm:tr>
          
           <htm:tr><htm:td colspan="2" height="1">
                         
    <t:panelGroup forceId="true" id="headerbenpnl" style="display: block; width: 426px;margin-right: -3px; margin-bottom: -4px;">

              <t:dataTable id="dataT_datah" var="list"  
                       rowIndexVar="index" style="width: 424px; margin-right: 2px;"
                      footerClass="grid-footer" rendered="#{!empty pageBeanName.benfitsList}"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}" >
            <t:column id="code_columnh" sortable="false" width="11%">
                <f:facet name="header">
                    <t:outputText id="sort_codeh" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"/>
                </f:facet>
            </t:column>
            <t:column id="name_columnh" sortable="false" width="50%">
                <f:facet name="header">
                    <t:outputText id="sort_nameh" forceId="true" styleClass="headerLink" value="#{integrationBundle.item}"/>
                </f:facet>
            </t:column>
           
            <t:column id="value_columnh" sortable="false" width="20%">
                <f:facet name="header">
                    <t:outputText id="value_counth" forceId="true" styleClass="headerLink" value="#{integrationBundle.MER_RAISE_VALUE}"/>
                </f:facet>
              
            </t:column>
            
             <t:column id="actualValue_columnh" sortable="false" width="19%">
                <f:facet name="header">
                    <t:outputText id="value_actualValueh" forceId="true" styleClass="headerLink" value="#{integrationBundle.needed_value_header}"/>
                </f:facet>
            </t:column>
            
        </t:dataTable>
</t:panelGroup>
           </htm:td></htm:tr>
       
       
        </htm:table>
        
        <htm:table width="100%">
               <htm:tr>
                   <htm:td width="9"><htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/></htm:td>
                       <htm:td styleClass="group_title">&nbsp;<t:outputLabel value="#{integrationBundle.deductions}" styleClass="lable01"/> </htm:td>
                   </htm:tr>
               <htm:tr><htm:td colspan="2" height="1"><htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/></htm:td></htm:tr>
               <htm:tr><htm:td colspan="2" height="1">
     <t:panelGroup forceId="true" id="headerdedpnl" style="display: block; width: 426px;margin-right: -3px; margin-bottom: -4px;">

                 <t:dataTable id="deductionheader" var="list" rendered="#{!empty pageBeanName.deductionsList}"
                       rowIndexVar="index" footerClass="grid-footer" style="width: 424px; margin-right: 2px;"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="deductioncode_columnh" sortable="false" width="11%">
                <f:facet name="header">
                    <t:outputText id="deductionsort_codeh" forceId="true" styleClass="headerLink" value="#{globalResources.Code}"/>    
                </f:facet>
            </t:column>
            <t:column id="deductionname_columnh" sortable="false" width="74%">
                <f:facet name="header">
                    <t:outputText id="sort_nameh" forceId="true" styleClass="headerLink"
                                  value="#{integrationBundle.item}"/>
                </f:facet>
               
            </t:column>
          
            <t:column id="deductionvalue_columnh" sortable="false" width="12%">
                <f:facet name="header">
                    <t:outputText id="value_count2h" forceId="true" styleClass="headerLink" value="#{integrationBundle.MER_RAISE_VALUE}"/>
                </f:facet>
            </t:column>
        </t:dataTable>
      
               </t:panelGroup>
               </htm:td></htm:tr>
        </htm:table>
   
   

    <t:panelGroup forceId="true" id="benfitsList_panel" style="display: block;height: 174px;overflow-x: hidden;overflow-y: auto; width: 445px;">
       
           

        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.benfitsList}"
                     forceIdIndexFormula="#{list.code.key}"  rowIndexVar="index" style="width: 424px; margin-right: 2px;"
                     renderedIfEmpty="false" binding="#{pageBeanName.benfitsTable}" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}" >
            <t:column id="code_column" sortable="false" width="8%">
               
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="52%">
               
                <h:outputText id="content_name" value="#{list.name}"/>
                 <f:verbatim>&nbsp;</f:verbatim>
               <a4j:commandButton   id="EmpSalaryDetailsId"
                                    action="#{pageBeanName.displayEmpSalaryDetails}" 
                                    styleClass="info-icon-btn"
                                    reRender="customDiv1"  
                                    rendered="#{list.financialGuide == pageBeanName.basicSalaryCode}"
                                    oncomplete="changeVisibilityDiv(window.blocker,window.customDiv1);return false;" 
                                    onmouseout="hideTip()"
                                    onmouseover="doTooltip(event,' #{integrationBundle.EmpSalaryDetailsLabel}')"/>
                                    
               <%--<t:commandButton type="button" id="SpecialRaiseDetailsId" rendered="#{list.salElementTypesDTO.elmtypeCode == 28}"  styleClass="info-icon-btn" onmouseout="hideTip()"
                                    onmouseover="doTooltip(event,' #{integrationBundle.SpecialRaiseDetailsLabel}')">
                    <a4j:support event="onclick" action="#{pageBeanName.displaySpecialRaiseDetails}"  reRender="customDiv2" oncomplete="changeVisibilityDiv(window.blocker,window.customDiv2);return false;" />
                </t:commandButton>--%>
               <a4j:commandButton   id="SpecialRaiseDetailsId"
                                    action="#{pageBeanName.displaySpecialRaiseDetails}" 
                                    styleClass="info-icon-btn"
                                    reRender="customDiv2"  
                                    rendered="#{list.salElementTypesDTO.elmtypeCode == 28}"
                                    oncomplete="changeVisibilityDiv(window.blocker,window.customDiv2);return false;" 
                                    onmouseout="hideTip()"
                                    onmouseover="doTooltip(event,' #{integrationBundle.SpecialRaiseDetailsLabel}')"/>
            </t:column>
            <%--<t:column id="count_column" sortable="false" width="82%">
                <f:facet name="header">
                    <t:commandLink id="sort_count" forceId="true" styleClass="headerLink" value="#{integrationBundle.Number}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='countGuide') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='countGuide') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_count" value="#{list.countGuide}"/>
            </t:column>--%>
            <t:column id="value_column" sortable="false" width="20%">
             
                <h:outputText id="content_value" value="#{list.value}" converter="FloatThreeDigitConverter"/>
            </t:column>
            
             <t:column id="actualValue_column" sortable="false" width="20%">
              
                <h:outputText id="content_actualValue" value="#{list.actualValue}"  title="#{list.elmGuideComment}" converter="FloatThreeDigitConverter"/>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.benfitsList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
    <t:panelGroup forceId="true" id="deductionsList_panel" style=" margin-top: -14px;display: block;height: 174px;overflow-x: hidden;overflow-y: auto; width: 445px;">
   
        <t:dataTable id="deductionsList_data" var="list" value="#{pageBeanName.deductionsList}"
                     forceIdIndexFormula="#{list.code.key}"  rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.deductionsTable}" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     style="width: 424px; margin-right: 2px;"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="80%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            <t:column id="deductioncode_column" sortable="false" width="8%">
             
                <h:outputText id="deductioncontent_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="deductionname_column" sortable="false" width="82%">
                <h:outputText id="deductioncontent_name" value="#{list.name}"/>
                <f:verbatim>&nbsp;</f:verbatim>
              
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 1}" onmouseout="hideTip()"
                onmouseover="doTooltip(event,'  #{integrationBundle.this_ded_return_affect}  <br/> #{integrationBundle.basic_insurance} <br/> #{integrationBundle.ext_insurance} <br/> #{integrationBundle.add_insurance}')"/>
               
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 5}" onmouseout="hideTip()"
                onmouseover="doTooltip(event,'  #{integrationBundle.this_ded_return_affect} <br/> #{integrationBundle.basic_insurance} <br/> #{integrationBundle.ext_insurance}')"/>
              
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 6}" onmouseout="hideTip()"
                         onmouseover="doTooltip(event,'  #{integrationBundle.this_ded_return_affect} <br/> #{integrationBundle.basic_insurance} <br/> #{integrationBundle.add_insurance}')"/>

              
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 7}" onmouseout="hideTip()"
                onmouseover="doTooltip(event,' #{integrationBundle.this_ded_return_affect}  <br/> #{integrationBundle.basic_insurance}')"/>
              
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 9}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,' #{integrationBundle.this_ded_return_affect} <br/> #{integrationBundle.basic_insurance} <br/> #{integrationBundle.add_insurance} <br/> #{integrationBundle.ext_insurance} <br/> #{integrationBundle.award_insurance}')"/>

             
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 13}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,' #{integrationBundle.this_ded_return_affect} <br/> #{integrationBundle.basic_insurance}  <br/> #{integrationBundle.ext_insurance} <br/> #{integrationBundle.award_insurance}')"/>
               
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 14}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,' #{integrationBundle.this_ded_return_affect} <br/> #{integrationBundle.basic_insurance} <br/> #{integrationBundle.add_insurance}  <br/> #{integrationBundle.award_insurance}')"/>
              
                <a4j:commandButton action=""  onclick="return false;" styleClass="info-icon-btn"
                                   rendered="#{list.statusFlag == 15}" onmouseout="hideTip()"
                 onmouseover="doTooltip(event,' #{integrationBundle.this_ded_return_affect} <br/> #{integrationBundle.basic_insurance} <br/> #{integrationBundle.award_insurance}')"/>

            </t:column>
            <%--<t:column id="deductioncount_column" sortable="false" width="82%">
                <f:facet name="header">
                    <t:commandLink id="sort_count" forceId="true" styleClass="headerLink" value="#{integrationBundle.Number}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='countGuide') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='countGuide') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="deductioncontent_count" value="#{list.countGuide}"/>
            </t:column>--%>
            <t:column id="deductionvalue_column" sortable="false" width="82%">
                
                <h:outputText id="deductioncontent_value" value="#{list.value}" converter="FloatThreeDigitConverter"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.deductionsList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
</t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
