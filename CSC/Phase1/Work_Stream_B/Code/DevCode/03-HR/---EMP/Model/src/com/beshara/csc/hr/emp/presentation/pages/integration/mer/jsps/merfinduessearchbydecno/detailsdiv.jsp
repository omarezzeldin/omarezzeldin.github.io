<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGroup forceId="true" id="emp_details_panel"  colspan="6">

<t:panelGrid columns="4"  border="0" cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%">
    <h:outputText value="#{intgerationBundle.Financial_Dues_Name}" styleClass="divtext"/>
    <t:inputText disabled="true"  styleClass="textboxmedium"  forceId="true" id="details_empName" value="#{pageBeanName.salFinDuesDetails.civilName}"/>
    
    <h:outputText value="#{globalResources.globalCivilId}" styleClass="divtext"/>
    <t:inputText disabled="true" maxlength="12" styleClass="textboxmedium"  forceId="true" id="details_empCode" value="#{pageBeanName.salFinDuesDetails.civilID}" style="width: 100px;"/>
    
    <h:outputText value="#{intgerationBundle.Financial_Dues_Min}" styleClass="divtext"/>
    <t:inputText disabled="true"  styleClass="textboxmedium"  forceId="true" id="details_minsName" value="#{pageBeanName.salFinDuesDetails.ministryName}"/>    
</t:panelGrid>

        <t:dataTable id="dataT_Details" var="listDetails" value="#{pageBeanName.empPayslips}" preserveDataModel="false" renderedIfEmpty="true" footerClass="grid-footer" sortable="false"
                styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" 
                width="100%" align="top" rowIndexVar="index" dir="rtl">

                    <t:column id="payslip_elementGuideCode_column" width="15%">
                    <f:facet name="header">
                    <t:outputText id="sort-payslip_elementGuideCode" forceId="true" value="#{intgerationBundle.Financial_Dues_Guide_Code}"/>
                            
                    </f:facet>
                    <h:outputText id="ps_elementGuide_code" value="#{listDetails.elementGuidesDTO.code.key}"/>
                    </t:column>
                    
                    <t:column id="payslip_elementGuideName_column" width="70%">
                    <f:facet name="header">
                    <t:outputText id="sort-payslip_elementGuideName" forceId="true" value="#{intgerationBundle.Financial_Dues_Guide_Name}"/>
                            
                    </f:facet>
                    <h:outputText id="ps_elementGuide_name" value="#{listDetails.elementGuidesDTO.name}"/>
                    </t:column>
                    
                     <t:column id="payslip_value_column" width="15%">
                    <f:facet name="header">
                    <t:outputText id="sort-payslip_value" forceId="true" value="#{intgerationBundle.Financial_Dues_Guide_Value}"/>
                            
                    </f:facet>
                    <h:outputText id="ps_value" value="#{listDetails.value}"/>
                    </t:column>                    
                                                                     
        </t:dataTable>

    <t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" 
        id="backButtonCustomDiv1" styleClass="cssButtonSmall"
        value="#{globalResources.back}" onclick="hideCustomDiv();"/>
    </t:panelGrid>
    
</t:panelGroup>
