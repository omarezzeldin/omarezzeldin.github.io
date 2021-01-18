<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>


<t:panelGrid  rowClasses="row02,row01" cellpadding="3"  cellspacing="0" width="100%"   columns="2" >

<h:outputText  value="#{resourcesBundle.extraDataTypesName}"  styleClass="lable01"/>
   <t:panelGroup>
       <t:selectOneMenu forceId="true"  id="extraDataTypesValueID" value="#{pageBeanName.extraDataTypesValue}" onchange="filterByExtraDataType(event);">
            <t:selectItems value="#{pageBeanName.extraDataTypesList}" itemLabel="#{extraDataTypesList.name}" itemValue="#{extraDataTypesList.code.key}" var="extraDataTypesList"/>              
            <a4j:jsFunction action="#{pageBeanName.getAll}" name="filterByExtraDataType" reRender="dataT_data,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
        </t:selectOneMenu>
   </t:panelGroup>

</t:panelGrid>





</script>
