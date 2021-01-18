<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<t:panelGrid  rowClasses="row02,row01" cellpadding="3" columnClasses="colu1,colu2" cellspacing="0" width="100%" forceId="true" id="employeeMainDataPanel" columns="4">
  
   <h:outputText  value="#{resourcesBundle.civilid}" id="cvl_id1" styleClass="lable01"/>
   <t:panelGroup >
         <t:inputText forceId="true"  id="employees_civilId"  styleClass="textbox" converter="javax.faces.Long" disabled="true" value="#{illeagalAccomodationPersonalInfo.civilId}"/>
   </t:panelGroup>
   
   <h:outputText  value="#{resourcesBundle.candidate_name}" id="cndnme_id1" styleClass="lable01"/>
   <t:inputText forceId="true"  id="employees_name" value="#{illeagalAccomodationPersonalInfo.candidateName}" styleClass="textboxlarge" disabled="true"/>

  
</t:panelGrid>