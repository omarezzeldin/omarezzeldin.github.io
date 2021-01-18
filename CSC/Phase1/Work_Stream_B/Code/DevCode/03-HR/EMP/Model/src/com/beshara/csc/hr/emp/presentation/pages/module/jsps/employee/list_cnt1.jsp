<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>



<%--<t:saveState value="#{pageBeanName.hireTypesList}"/>--%>
<t:panelGrid  rowClasses="row02,row01" cellpadding="3" columnClasses="colCenter1,colCenter2" cellspacing="0" width="100%" align="center"  columns="2" >

<h:outputText  value="#{resourcesBundle.hiretype}"  styleClass="lable01"/>
   <t:panelGroup>
       <%--
       <t:selectOneMenu onblur="document.getElementById('employees_civilIdAdd').focus();" forceId="true"  id="employees_hiretypes" styleClass="DropdownboxMedium2" value="#{pageBeanName.pageDTO.hireTypeKey}">
       --%>
       <t:selectOneMenu forceId="true"  id="employees_hiretypes" value="#{pageBeanName.pageDTO.hireTypeKey}" onchange="filterByFLHireType(event);">
            <%--<f:selectItem itemValue="#{pageBeanName.virtualStringValue}" itemLabel="#{resourcesBundle.select}"/>--%>
            <f:selectItem itemValue="0" itemLabel="#{resourcesBundle.all}"/>
            <t:selectItems value="#{pageBeanName.firstLevelHireTypesList}" itemLabel="#{hireTypesList.name}" itemValue="#{hireTypesList.code.key}" var="hireTypesList"/>              
            <a4j:jsFunction action="#{pageBeanName.searchByMainHireType}" name="filterByFLHireType" reRender="dataT_data,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar,searchPanel"/>
        </t:selectOneMenu>
   </t:panelGroup>

</t:panelGrid>



<script type="text/javascript"> 
    setFocusAtMyFirstElement();
    function setFocusAtMyFirstElement(){
        if(document.getElementById('check_civil_btn') != null){
            document.getElementById('employees_hiretypes').focus();
            document.getElementById('employees_hiretypes').focus();
        }
    }

</script>
