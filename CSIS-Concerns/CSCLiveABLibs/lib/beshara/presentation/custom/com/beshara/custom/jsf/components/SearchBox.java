package com.beshara.custom.jsf.components;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.PhaseId;

public class SearchBox extends UIComponentBase
{
  public static final String COMPONENT_TYPE = "com.beshara.custom.jsf.components.searchbox";
  public static final String COMPONENT_FAMILY = "com.beshara.custom.jsf.components.searchbox";
  private String searchAction;
  private String searchQuery;
  private String searchType;
  private String direction;
  private String radioStyle;
  private String textBoxStyle;
  private String buttonStyle;
  private String errorStyle;
  private String tdStyle;
  private String searchCodeLabel;
  private String searchNameLabel;

  public SearchBox()
  {
    setRendererType(null);
  }

  public String getFamily()
  {
    return "com.beshara.custom.jsf.components.searchbox";
  }

  public void setSearchQuery(String searchQuery)
  {
    this.searchQuery = searchQuery;
  }

  public String getSearchQuery()
  {
    return this.searchQuery;
  }

  public void encodeComponent(ResponseWriter writer, String codeLabel, String nameLabel, String searchButtonLabel, String backButtonLabel, String pageDirection)
    throws IOException
  {
    System.out.println("#######$$$$$####### SHEKA #######$$$$$#######");
    writer.startElement("table", this);
    writer.writeAttribute("border", "0", null);
    writer.writeAttribute("width", "80%", null);
    writer.writeAttribute("cellspacing", "0", null);
    writer.writeAttribute("cellpadding", "0", null);
    writer.writeAttribute("dir", pageDirection, null);

    writeErrorMsgHtml(writer);
    writeRadioButtonsHtml(writer, codeLabel, nameLabel);
    writeInputTextHtml(writer);
    writeButtonsHtml(writer, searchButtonLabel, backButtonLabel);

    writer.endElement("table");
  }

  public void encodeBegin(FacesContext facesContext)
    throws IOException
  {
    ResponseWriter writer = facesContext.getResponseWriter();

    String pageDirection = (String)getValueOf(this.direction);
    String codeKey = (String)getValueOf(this.searchCodeLabel);
    if ((codeKey == null) || (codeKey.length() == 0)) {
      codeKey = "globalResources.Code";
    }
    String nameKey = (String)getValueOf(this.searchNameLabel);
    if ((nameKey == null) || (nameKey.length() == 0)) {
      nameKey = "globalResources.name";
    }
    String codeLabel = (String)getValueOf(codeKey);
    String nameLabel = (String)getValueOf(nameKey);

    String searchButtonLabel = (String)getValueOf("#{globalResources.SearchButton}");
    String backButtonLabel = (String)getValueOf("#{globalResources.back}");

    encodeComponent(writer, codeLabel, nameLabel, searchButtonLabel, backButtonLabel, pageDirection);
  }

  public void decode(FacesContext facesContext)
  {
    String searchButtonId = "customSearchBtn";
    String codeRadioId = "radioBtn";
    String searchQueryId = "searchString";

    String selectedType = (String)getRequestParameterOf(facesContext, codeRadioId);
    String searchCriteria = (String)getRequestParameterOf(facesContext, searchQueryId);

    setValueOf(this.searchType, new Integer(selectedType));
    setValueOf(this.searchQuery, searchCriteria);

    if (isRequestParametersContains(facesContext, searchButtonId))
      queueEvent(new ActionEvent(this));
  }

  public Object saveState(FacesContext context)
  {
    Object[] values = new Object[12];
    values[0] = super.saveState(context);
    values[1] = this.searchAction;
    values[2] = this.searchQuery;
    values[3] = this.searchType;
    values[4] = this.direction;

    values[5] = this.radioStyle;
    values[6] = this.textBoxStyle;
    values[7] = this.tdStyle;
    values[8] = this.buttonStyle;
    values[9] = this.errorStyle;
    values[10] = this.searchCodeLabel;
    values[11] = this.searchNameLabel;

    return values;
  }

  public void restoreState(FacesContext context, Object state) {
    Object[] values = (Object[])state;
    super.restoreState(context, values[0]);
    this.searchAction = ((String)values[1]);
    this.searchQuery = ((String)values[2]);
    this.searchType = ((String)values[3]);
    this.direction = ((String)values[4]);

    this.radioStyle = ((String)values[5]);
    this.textBoxStyle = ((String)values[6]);
    this.tdStyle = ((String)values[7]);
    this.buttonStyle = ((String)values[8]);
    this.errorStyle = ((String)values[9]);
    this.searchCodeLabel = ((String)values[10]);
    this.searchNameLabel = ((String)values[11]);
  }

  public void setSearchType(String searchType) {
    this.searchType = searchType;
  }

  public String getSearchType()
  {
    return this.searchType;
  }

  public void broadcast(FacesEvent facesEvent) throws AbortProcessingException {
    super.broadcast(facesEvent);

    FacesContext context = getFacesContext();
    String _searchAction = "#{" + this.searchAction + "}";
    System.out.println("SSSSSSSSSSSSSSSSSSSSSearchAction:" + _searchAction);
    MethodBinding binding = context.getApplication().createMethodBinding(_searchAction, null);

    if (binding != null)
      binding.invoke(context, null);
  }

  public void queueEvent(FacesEvent event)
  {
    if ((event instanceof ActionEvent)) {
      event.setPhaseId(PhaseId.INVOKE_APPLICATION);
    }
    super.queueEvent(event);
  }

  public void setSearchAction(String searchAction) {
    this.searchAction = searchAction;
  }

  public String getSearchAction()
  {
    return this.searchAction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getDirection()
  {
    return this.direction;
  }

  public void setRadioStyle(String radioStyle) {
    this.radioStyle = radioStyle;
  }

  public String getRadioStyle()
  {
    return this.radioStyle;
  }

  public void setTextBoxStyle(String textBoxStyle) {
    this.textBoxStyle = textBoxStyle;
  }

  public String getTextBoxStyle()
  {
    return this.textBoxStyle;
  }

  public void setButtonStyle(String buttonStyle) {
    this.buttonStyle = buttonStyle;
  }

  public String getButtonStyle()
  {
    return this.buttonStyle;
  }

  public void setErrorStyle(String errorStyle) {
    this.errorStyle = errorStyle;
  }

  public String getErrorStyle()
  {
    return this.errorStyle;
  }

  public void setTdStyle(String tdStyle) {
    this.tdStyle = tdStyle;
  }

  public String getTdStyle()
  {
    return this.tdStyle;
  }

  private void writeErrorMsgHtml(ResponseWriter writer)
    throws IOException
  {
    writer.startElement("tr", this);
    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("class", getTdStyle(), null);
    writer.writeAttribute("width", "100%", null);
    writer.writeAttribute("colspan", "6", null);
    writer.writeAttribute("align", "center", null);
    writer.write("&nbsp;");
    writer.startElement("label", this);
    writer.writeAttribute("id", "errorMessage", null);
    writer.writeAttribute("class", getErrorStyle(), null);
    writer.endElement("label");
    writer.endElement("td");
    writer.endElement("tr");
  }

  private void writeRadioButtonsHtml(ResponseWriter writer, String codeLabel, String nameLabel)
    throws IOException
  {
    writer.startElement("tr", this);
    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("width", "30%", null);
    writer.writeAttribute("class", getTdStyle(), null);
    writer.write("&nbsp;");
    writer.endElement("td");

    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("width", "5%", null);
    writer.writeAttribute("class", getTdStyle(), null);

    writer.startElement("input", this);
    writer.writeAttribute("id", "radioBtn", null);
    writer.writeAttribute("name", "radioBtn", null);
    writer.writeAttribute("type", "radio", null);
    writer.writeAttribute("onblur", "if(document.getElementById('divSearch').style.visibility == 'visible')document.getElementsByName('radioBtn')[1].focus();", null);

    Integer type = (Integer)getValueOf("#{" + this.searchType + "}");
    if (type.intValue() == 0) {
      writer.writeAttribute("checked", "true", null);
    }
    writer.writeAttribute("value", "0", null);
    writer.writeAttribute("class", getRadioStyle(), null);
    writer.endElement("input");
    writer.endElement("td");

    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("width", "30%", null);
    writer.writeAttribute("class", getTdStyle(), null);
    writer.write(codeLabel);
    writer.endElement("td");

    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("width", "5%", null);
    writer.writeAttribute("class", getTdStyle(), null);
    writer.startElement("input", this);
    writer.writeAttribute("id", "radioBtn", null);
    writer.writeAttribute("name", "radioBtn", null);
    writer.writeAttribute("type", "radio", null);
    writer.writeAttribute("onblur", "if(document.getElementById('divSearch').style.visibility == 'visible')document.getElementById('searchString').focus();", null);

    if (type.intValue() == 1) {
      writer.writeAttribute("checked", "true", null);
    }
    writer.writeAttribute("value", "1", null);
    writer.writeAttribute("class", getRadioStyle(), null);
    writer.endElement("input");
    writer.endElement("td");

    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("width", "30%", null);
    writer.writeAttribute("class", getTdStyle(), null);
    writer.write(nameLabel);
    writer.endElement("td");

    writer.endElement("tr");
  }

  private void writeInputTextHtml(ResponseWriter writer)
    throws IOException
  {
    String criteria = (String)getValueOf("#{" + this.searchQuery + "}");
    writer.startElement("tr", this);
    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("class", getTdStyle(), null);
    writer.writeAttribute("colspan", "6", null);
    writer.startElement("p", this);
    writer.writeAttribute("align", "center", null);
    writer.write("&nbsp;");
    writer.startElement("input", this);
    writer.writeAttribute("id", "searchString", null);
    writer.writeAttribute("name", "searchString", null);
    writer.writeAttribute("type", "text", null);
    writer.writeAttribute("onclick", "document.getElementById('searchString').focus();", null);
    writer.writeAttribute("onkeypress", "if(typeof(FireButtonClick)!='undefined'){FireButtonClick('customSearchBtn');}", null);
    writer.writeAttribute("value", criteria, null);
    writer.writeAttribute("class", getTextBoxStyle(), null);

    writer.endElement("input");
    writer.endElement("p");
    writer.endElement("td");
    writer.endElement("tr");
  }

  private void writeButtonsHtml(ResponseWriter writer, String searchButtonLabel, String backButtonLabel)
    throws IOException
  {
    writer.startElement("tr", this);
    writer.startElement("td", this);
    writer.writeAttribute("valign", "top", null);
    writer.writeAttribute("class", getTdStyle(), null);
    writer.writeAttribute("colspan", "6", null);
    writer.startElement("p", this);
    writer.writeAttribute("align", "center", null);
    writer.startElement("input", this);
    writer.writeAttribute("onclick", "if(document.getElementById('divSearch').style.visibility == 'visible'){document.getElementById('searchString').focus();document.getElementById('searchString').select();} return checkInput();", null);

    writer.writeAttribute("id", "customSearchBtn", null);

    writer.writeAttribute("name", "customSearchBtn", null);
    writer.writeAttribute("type", "submit", null);
    writer.writeAttribute("value", searchButtonLabel, null);
    writer.writeAttribute("class", getButtonStyle(), null);

    writer.endElement("input");

    writer.write("&nbsp;");

    writer.startElement("input", this);
    writer.writeAttribute("onclick", "hideDiv();return false;", null);

    writer.writeAttribute("onblur", "if(document.getElementById('divSearch').style.visibility == 'visible')document.getElementsByName('radioBtn')[0].focus();", null);

    writer.writeAttribute("id", "customSearchBackBtn", null);

    writer.writeAttribute("name", "customSearchBackBtn", null);
    writer.writeAttribute("type", "button", null);
    writer.writeAttribute("value", backButtonLabel, null);
    writer.writeAttribute("class", getButtonStyle(), null);

    writer.endElement("input");
    writer.endElement("p");
    writer.endElement("td");
    writer.endElement("tr");
  }

  private Object getValueOf(String key)
  {
    if (key == null)
    {
      return null;
    }
    FacesContext facesContext = FacesContext.getCurrentInstance();
    if (key.startsWith("#"))
    {
      return facesContext.getApplication().createValueBinding(key).getValue(facesContext);
    }

    return facesContext.getApplication().createValueBinding("#{" + key + "}").getValue(facesContext);
  }

  private void setValueOf(String key, Object value)
  {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    if (key.startsWith("#"))
      facesContext.getApplication().createValueBinding(key).setValue(facesContext, value);
    else
      facesContext.getApplication().createValueBinding("#{" + key + "}").setValue(facesContext, value);
  }

  private boolean isRequestParametersContains(FacesContext facesContext, String key)
  {
    return facesContext.getExternalContext().getRequestParameterMap().containsKey(key);
  }

  private Object getRequestParameterOf(FacesContext facesContext, String key)
  {
    return facesContext.getExternalContext().getRequestParameterMap().get(key);
  }

  public void setSearchCodeLabel(String searchCodeLabel) {
    this.searchCodeLabel = searchCodeLabel;
  }

  public String getSearchCodeLabel()
  {
    return this.searchCodeLabel;
  }

  public void setSearchNameLabel(String searchNameLabel) {
    this.searchNameLabel = searchNameLabel;
  }

  public String getSearchNameLabel()
  {
    return this.searchNameLabel;
  }
}