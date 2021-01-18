<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<t:div id="lookupEditDiv" style="visibility:visible;" forceId="true">
<%--t:panelGroup>
<t:outputLabel value="#{resourcesBundle.bookdetails}" styleClass="pannel_large_title" id="needsid1"/>
<f:verbatim>
   <br/>
</f:verbatim>
</t:panelGroup--%>
<!-- *************************************************************************************** -->
<t:panelGroup  style="background-color:#ffffff;">
 <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
  <htm:tr>
   <htm:td width="9">
    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
   </htm:td>
   <htm:td styleClass="group_title">
    <t:outputLabel value="#{resourcesBundle.bookdetails}" styleClass="lable01"/>
   </htm:td>
  </htm:tr>
  <htm:tr>
   <htm:td colspan="2" height="1">
    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
   </htm:td>
  </htm:tr>
 </htm:table>
</t:panelGroup>

<!-- *************************************************************************************** -->

<t:panelGrid id="panel2" columns="4" rowClasses="row02,row01" cellpadding="0" columnClasses="colu1,colu2,colu1,colu2" cellspacing="0" width="100%">
   <!-- Row 1-->
   <t:outputLabel value="#{resourcesBundle.bookNumber}" styleClass="lable01" id="needsid2"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.needBooksDTO.code.bookNo}" styleClass="lable01" id="needsid3"/>
   <t:outputLabel value="#{resourcesBundle.periodType}" styleClass="lable01" id="needsid4"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.needBooksDTO.regPeriodTypesDTO.name}" styleClass="lable01" id="needsid5"/>
   <!-- Row 2-->
   <t:outputLabel value="#{resourcesBundle.bookDes}" styleClass="lable01" id="needsid6"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.needBooksDTO.bookDesc}" styleClass="lable01" id="needsid7"/>
   <t:outputLabel/>
   <t:outputLabel/>
   <!-- Row 3-->
   <t:outputLabel value="#{resourcesBundle.sendingDate}" styleClass="lable01" id="needsid8"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.needBooksDTO.sendDate}" converter="SqlDateConverter" styleClass="lable01" id="needsid9"/>
   <t:outputLabel/>
   <t:outputLabel/>
   <!-- Row 4-->
   <t:outputLabel value="#{resourcesBundle.recevingNumber}" styleClass="lable01" id="needsid10"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.needBooksDTO.receivedNo}" styleClass="lable01" id="needsid11"/>
   <t:outputLabel value="#{resourcesBundle.recevingDate}" styleClass="lable01" id="needsid12"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.needBooksDTO.receivedDate}" converter="SqlDateConverter" styleClass="lable01" id="needsid13"/>
</t:panelGrid>
<%--t:outputLabel value="#{resourcesBundle.NeedDetails}" styleClass="pannel_large_title" id="needsid14"/>
<f:verbatim>
   <br/>
</f:verbatim--%>

<!-- *************************************************************************************** -->
<t:panelGroup  style="background-color:#ffffff;">
 <htm:table width="100%" border="0" cellspacing="0" cellpadding="0">
  <htm:tr>
   <htm:td width="9">
    <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9" height="9"/>
   </htm:td>
   <htm:td styleClass="group_title">
    <t:outputLabel value="#{resourcesBundle.NeedDetails}" styleClass="lable01"/>
   </htm:td>
  </htm:tr>
  <htm:tr>
   <htm:td colspan="2" height="1">
    <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg" width="100%" height="1"/>
   </htm:td>
  </htm:tr>
 </htm:table>
</t:panelGroup>
<!-- *************************************************************************************** -->

<t:panelGrid columns="4" rowClasses="row02,row01" id="panel1" cellpadding="0" columnClasses="colu1,colu2,colu1,colu2" cellspacing="0" width="100%">
   <!-- Row 5-->
   <t:outputLabel value="#{resourcesBundle.needName}" styleClass="lable01" id="needsid15"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.name}" styleClass="lable01" id="needsid16"/>
   <t:outputLabel value="#{resourcesBundle.NeedDetails}" styleClass="lable01" id="needsid17"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.needDesc}" styleClass="lable01" id="needsid18"/>
   <!-- Row 6-->
   <t:outputLabel value="#{resourcesBundle.maleNo}" styleClass="lable01" id="needsid19"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.reqMaleNow}" styleClass="lable01" id="needsid20"/>
   <t:outputLabel value="#{resourcesBundle.femaleNo}" styleClass="lable01" id="needsid21"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.reqFemaleNow}" styleClass="lable01" id="needsid22"/>
   <!-- Row 7-->
   <t:outputLabel value="#{resourcesBundle.undefinedNum}" styleClass="lable01" id="needsid23"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.reqUndefinedNow}" styleClass="lable01" id="needsid24"/>
   <t:outputLabel value="#{resourcesBundle.end_date}" styleClass="lable01" id="needsid25"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.expireDate}" converter="TimeStampConverter" styleClass="lable01" id="needsid26"/>
   <!-- Row 8-->
   <t:outputLabel value="#{resourcesBundle.qualification}" styleClass="lable01" id="needsid27"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.qualificationsDTO.qualificationDesc}" styleClass="lable01" id="needsid28"/>
   <t:outputLabel value="#{resourcesBundle.job}" styleClass="lable01" id="needsid29"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.jobsDTO.name}" styleClass="lable01" id="needsid30"/>
   <!-- Row 9-->
   <t:outputLabel value="#{resourcesBundle.jobCenter}" styleClass="lable01" id="needsid31"/>
   <t:outputLabel value="#{pageBeanName.selectedPageDTO.jobNeedsDTO.workCentersDTO.name}" styleClass="lable01" id="needsid32"/>
   <!-- Row 10-->
</t:panelGrid>
<f:verbatim>
   <br/>
</f:verbatim>
<t:panelGrid columns="1" cellpadding="0" cellspacing="0" width="10%" align="center">
   <t:commandButton value="#{globalResources.back}" id="CancelButtonEdit" forceId="true" action="#{pageBeanName.back}" styleClass="cssbuttonSmall"
                    onblur="setFocusOnlyOnElement('CancelButtonEdit');"/>
</t:panelGrid>
</t:div>

<script type="text/javascript"> 
    setFocusOnlyOnElement('CancelButtonEdit');
</script>