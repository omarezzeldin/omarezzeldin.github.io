<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.faces.context.FacesContext"%>
<%@ page import="com.beshara.jsfbase.csc.util.wizardbar.WizardBar"%>
<tiles:importAttribute scope="request"/>

<%--<script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/js/WizardBar.jsf"></script>--%>

 <t:saveState value="#{wizardbar.wizardSteps}"/>
 <t:saveState value="#{wizardbar.configurationId}"/>
                            