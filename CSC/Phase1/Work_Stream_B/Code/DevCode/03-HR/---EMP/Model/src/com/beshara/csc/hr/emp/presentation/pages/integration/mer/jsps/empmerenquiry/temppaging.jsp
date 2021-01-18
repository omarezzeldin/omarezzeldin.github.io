<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<t:panelGrid id="paging_panel_grid" forceId="true" border="0" cellpadding="0" cellspacing="0" width="100%" columns="2" rowClasses="#{pageBeanName.displayBtn ? 'row02,row01' : ''}">


<%--<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<deductions paging panel >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> --%>
<t:panelGroup forceId="true" id="benfits_paging_panel_group" style="display: block; margin-top: 0px;">
    <%--<t:panelGroup forceId="true" id="benfits_paging_panel" >
        <t:outputText dir="ltr" forceId="true" id="benfitsListSize" style="display: block; margin-bottom: 30px; right: -445px; top: 0px; width: 445px;"
                      styleClass="noOfRecords"
                      value="#{globalResources.noOfRecords} : #{pageBeanName.benfitsListSize}"
                      />
     
    </t:panelGroup>--%>

    <t:panelGrid columns="2" id="benfitsTotalValue" align="left">
        <h:outputText value="#{integrationBundle.total_benfit_value}" styleClass="divtext" rendered="#{pageBeanName.displayBtn}"/>
        <t:inputText styleClass="textboxsmall2" disabled="true" forceId="true" id="_benfitsTotalValue" rendered="#{pageBeanName.displayBtn}"
                value="#{pageBeanName.benfitsTotalValue}" converter="FloatThreeDigitConverter" style="margin-right: 5px; font-weight: bold;"/>
        
    </t:panelGrid>
</t:panelGroup>



<t:panelGroup forceId="true" id="deductions_paging_panel_group" style="display: block; margin-top: 0px;">
    <%--<t:panelGroup forceId="true" id="deductions_paging_panel" >
        <t:outputText dir="ltr" forceId="true" id="deductionslistSize" style="display: block; margin-bottom: 30px;  right: -445px; top: 0px; width: 445px;"
                      styleClass="noOfRecords"
                      value="#{globalResources.noOfRecords} : #{pageBeanName.deductionsListSize}"
                      />
     
    </t:panelGroup>--%>
    <t:panelGrid columns="2" id="dedectionsTotalValue" align="left">
        <h:outputText value="#{integrationBundle.total_deduction_value}" styleClass="divtext" rendered="#{pageBeanName.displayBtn}"/>
        <t:inputText styleClass="textboxsmall2" disabled="true" forceId="true" id="_dedectionsTotalValue" rendered="#{pageBeanName.displayBtn}"
             value="#{pageBeanName.dedectionsTotalValue}" converter="FloatThreeDigitConverter" style="margin-right: 5px; font-weight: bold;"/>
    </t:panelGrid>

</t:panelGroup>

<t:panelGroup id="totalSalaryGroup" colspan="2" style="text-align:center; display: block;" >   
    <h:outputText value="#{integrationBundle.items_total}" styleClass="divtext" style="font-weight: bold;"  rendered="#{pageBeanName.displayBtn}"/>
    <t:inputText styleClass="textboxsmall2" disabled="true" forceId="true" id="totalSalary" rendered="#{pageBeanName.displayBtn}"
             value="#{pageBeanName.totalSalary}" converter="FloatThreeDigitConverter" style="margin-right: 40px; font-weight: bold;"/>
                 
</t:panelGroup>


</t:panelGrid>
