package com.beshara.csc.hr.emp.presentation.backingbean.employmentcycle.experiencelist;


import com.beshara.csc.inf.business.client.IKwtWorkDataClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class ExperienceListBean extends LookUpBaseBean {

    private Long civileId;
    private String employeeName;
    private List saveStateList = new ArrayList();
private String crsFromMouduleNmae ;
private String backBtnNavigationCase;
  private String  backActionMethodName;
    public ExperienceListBean() {
        setPageDTO(InfDTOFactory.createKwtWorkDataDTO());
        super.setSelectedPageDTO(InfDTOFactory.createKwtWorkDataDTO());
        setClient((IKwtWorkDataClient)InfClientFactory.getKwtWorkDataClient());
        setDivMainContent("divMainContentWithCnt2");
        setSelectedRadio("false");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowSearch(false);
        app.setShowdatatableContent(true);
        return app;
    }

    public void selectedRadioButton(ActionEvent event) throws Exception {

        System.out.println(getSelectedRadio());
        setMyTableData(null);
    }


public String back(){
//    ListBean proceedingCandidateListBean = (ListBean)evaluateValueBinding("proceedingCandidateListBean"); 
//    proceedingCandidateListBean.setFromModuleName((String)getSaveStateList().get(0));
//   return "backToProceedingCandidate";

 System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
        if(!"".equals(backActionMethodName))
            executeMethodBinding(backActionMethodName,null);
        return getBackBtnNavigationCase();

}
    public void getAll() throws DataBaseException {
        try {
            this.repositionPage(0);
            if (civileId != null && getSelectedRadio() != null) {
                if (getSelectedRadio().equals("false")) {
                    setMyTableData(InfClientFactory.getKwtWorkDataClient().getGovExperiences(civileId));
                
                } else {
                    setMyTableData(InfClientFactory.getKwtWorkDataClient().getNonGovExperiences(civileId));
                   
                }
            }
        } catch (SharedApplicationException e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
        } catch (DataBaseException e) {
            setMyTableData(new ArrayList());
            e.printStackTrace();
        }
        
        this.reinitializeSort();


    }


    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {


        return employeeName;
    }

    public void setCivileId(Long civileId) {
        this.civileId = civileId;
    }

    public Long getCivileId() {
        return civileId;
    }

   
    public void setCrsFromMouduleNmae(String crsFromMouduleNmae) {
        this.crsFromMouduleNmae = crsFromMouduleNmae;
    }

    public String getCrsFromMouduleNmae() {
    
        return crsFromMouduleNmae;
    }

    public void setSaveStateList(List saveStateList) {
        this.saveStateList = saveStateList;
    }

    public List getSaveStateList() {
        return saveStateList;
    }

    public void setBackBtnNavigationCase(String backBtnNavigationCase) {
        this.backBtnNavigationCase = backBtnNavigationCase;
    }

    public String getBackBtnNavigationCase() {
        return backBtnNavigationCase;
    }

    public void setBackActionMethodName(String backActionMethodName) {
        this.backActionMethodName = backActionMethodName;
    }

    public String getBackActionMethodName() {
        return backActionMethodName;
    }
}
