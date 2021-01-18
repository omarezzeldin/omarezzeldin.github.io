<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<form id="reportFormTest" name="ReportFormTest" target="iframe">
<t:aliasBean alias="#{pageBeanName}" value="#{paySlipMaintainQueryBean}">
<t:inputHidden forceId="true" id="url" value="#{paySlipMaintainQueryBean.rdfReportUrlLink}"></t:inputHidden>
    </t:aliasBean>

<script type="text/javascript">
  var reportAction = document.getElementById("url").value;
  
  
    openReport()
    function openReport(){
        submitNewForm();
    }
    
  function submitNewForm() {
     var form = document.forms['reportFormTest'];
     document.getElementById("url").value="";
      form.setAttribute("action", reportAction);     
      form.setAttribute("method", "POST");
      form.setAttribute("target", "iframe");
  }
    
    

</script>
<iframe name="iframe" frameborder="0" scrolling="no" width="100%" height="100%"></iframe>    
</form>
<script type="text/javascript">
document.getElementById("url").value="";
document.getElementById('reportFormTest').submit();
</script>