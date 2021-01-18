<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<t:panelGrid  rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%" forceId="true" id="employeeMainDataPanel" columns="4">
  
   <h:outputText  value="#{resourcesBundle.civilid}" id="cvl_id1" styleClass="lable01"/>
   <t:panelGroup >
         <t:inputText forceId="true"  id="employees_civilId"  styleClass="textbox" value="#{(pageBeanName.pageDTO.citizensResidentsDTO !=null && pageBeanName.pageDTO.citizensResidentsDTO.code!=null ) ? pageBeanName.pageDTO.citizensResidentsDTO.code.civilId : null}" converter="javax.faces.Long" disabled="true"/>
   </t:panelGroup>
   
   <h:outputText  value="#{resourcesBundle.candidate_name}" id="cndnme_id1" styleClass="lable01"/>
   <t:inputText forceId="true"  id="employees_name"  styleClass="textboxlarge" value="#{pageBeanName.pageDTO.citizensResidentsDTO.fullName}" disabled="true"/>

   <%--<h:outputText  value="#{resourcesBundle.ministry_fileNo}" id="mnsryfil_id1" styleClass="lable01"/>--%>
   <%--<t:inputText forceId="true"  id="ministry_fileNo"  styleClass="textbox" value="#{pageBeanName.pageDTO.ministryFileNo}" disabled="true"/>--%>

   <h:outputText  value="#{resourcesBundle.hireDate}" id="hirdt_id1" styleClass="lable01"/>
   <t:panelGroup>
        <t:inputText forceId="true"  id="hireDate"  styleClass="textbox" value="#{pageBeanName.pageDTO.hireDate}" disabled="true">
             <f:converter converterId="SqlDateConverter" />
         </t:inputText>
   </t:panelGroup>
   
</t:panelGrid>