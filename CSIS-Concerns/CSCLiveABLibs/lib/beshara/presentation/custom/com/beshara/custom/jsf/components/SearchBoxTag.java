package com.beshara.custom.jsf.components;

import java.io.PrintStream;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import org.apache.myfaces.shared_impl.taglib.UIComponentTagBase;

public class SearchBoxTag extends UIComponentTagBase
{
  private String searchAction;
  private String searchType;
  private String searchQuery;
  private String direction;
  private String radioStyle;
  private String textBoxStyle;
  private String buttonStyle;
  private String errorStyle;
  private String tdStyle;
  private String searchCodeLabel;
  private String searchNameLabel;

  public String getComponentType()
  {
    return "com.beshara.custom.jsf.components.searchbox";
  }

  public String getRendererType()
  {
    return null;
  }

  protected void setProperties(UIComponent uIComponent) {
    super.setProperties(uIComponent);
    SearchBox search = (SearchBox)uIComponent;
    if (this.searchAction != null) {
      String _searchAction = (String)FacesContext.getCurrentInstance().getApplication().createValueBinding(this.searchAction).getValue(FacesContext.getCurrentInstance());

      search.setSearchAction(_searchAction);
    }
    System.out.println("TAAAAAAAAAAAAAAAAAAAAG searchAction:" + this.searchAction);
    System.out.println("TAAAAAAAAAAAAAAAAAAAAG searchAction:" + search.getSearchAction());
    if (this.searchQuery != null) {
      String _searchQuery = (String)FacesContext.getCurrentInstance().getApplication().createValueBinding(this.searchQuery).getValue(FacesContext.getCurrentInstance());

      search.setSearchQuery(_searchQuery);
    }
    if (this.searchType != null) {
      String _searchType = (String)FacesContext.getCurrentInstance().getApplication().createValueBinding(this.searchType).getValue(FacesContext.getCurrentInstance());

      search.setSearchType(_searchType);
    }
    if (this.direction != null) {
      search.setDirection(this.direction);
    }
    if (this.radioStyle != null) {
      search.setRadioStyle(this.radioStyle);
    }
    if (this.textBoxStyle != null) {
      search.setTextBoxStyle(this.textBoxStyle);
    }
    if (this.buttonStyle != null) {
      search.setButtonStyle(this.buttonStyle);
    }
    if (this.errorStyle != null) {
      search.setErrorStyle(this.errorStyle);
    }
    if (this.tdStyle != null) {
      search.setTdStyle(this.tdStyle);
    }
    if (this.searchCodeLabel != null) {
      search.setSearchCodeLabel(this.searchCodeLabel);
    }
    if (this.searchNameLabel != null)
      search.setSearchNameLabel(this.searchNameLabel);
  }

  public void release()
  {
    super.release();
    this.searchAction = null;
    this.searchType = null;
    this.searchQuery = null;
    this.direction = null;
  }

  public void setSearchAction(String searchAction) {
    this.searchAction = searchAction;
  }

  public String getSearchAction()
  {
    return this.searchAction;
  }

  public void setSearchType(String searchType) {
    this.searchType = searchType;
  }

  public String getSearchType()
  {
    return this.searchType;
  }

  public void setSearchQuery(String searchQuery) {
    this.searchQuery = searchQuery;
  }

  public String getSearchQuery()
  {
    return this.searchQuery;
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