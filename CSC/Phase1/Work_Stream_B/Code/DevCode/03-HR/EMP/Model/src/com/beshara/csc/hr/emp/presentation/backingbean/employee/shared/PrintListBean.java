package com.beshara.csc.hr.emp.presentation.backingbean.employee.shared;

import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

public class PrintListBean extends LookUpBaseBean {
    private static final String BEAN_NAME = "printListBean";
    private String fullURL;
    private String workCenterName;
    private String suggestedJobValue;
    private String suggestedCaderName;
    private String suggestedGroupName;
    private String suggestedDegreeName;
    private String suggestedRaiseName;
    private String acceptedJobValue;
    private IBgtTypesDTO bgtTypesDTO;
    private String acceptedCaderName;
    private String acceptedGroupName;
    private String acceptedDegreeName;
    private String acceptedRaiseName;
    private String civilServiceNotes;
    public PrintListBean() {
        initDto();
    }

    public void initDto() {
        if (JSFHelper.getSession().getAttribute("printPageDTO") != null) {

            IEmpCandidatesDTO selectedDTO = (IEmpCandidatesDTO)JSFHelper.getSession().getAttribute("printPageDTO");
            setPageDTO(selectedDTO);
        }
        if (JSFHelper.getSession().getAttribute("workCenterName") != null) {

            workCenterName = (String)JSFHelper.getSession().getAttribute("workCenterName");

        }
        if (JSFHelper.getSession().getAttribute("suggestedJobValue") != null) {

            suggestedJobValue = (String)JSFHelper.getSession().getAttribute("suggestedJobValue");

        }
        if (JSFHelper.getSession().getAttribute("suggestedCaderName") != null) {

            suggestedCaderName = (String)JSFHelper.getSession().getAttribute("suggestedCaderName");

        }

        if (JSFHelper.getSession().getAttribute("suggestedGroupName") != null) {

            suggestedGroupName = (String)JSFHelper.getSession().getAttribute("suggestedGroupName");

        }

        if (JSFHelper.getSession().getAttribute("suggestedDegreeName") != null) {

            suggestedDegreeName = (String)JSFHelper.getSession().getAttribute("suggestedDegreeName");

        }

        if (JSFHelper.getSession().getAttribute("suggestedRaiseName") != null) {

            suggestedRaiseName = (String)JSFHelper.getSession().getAttribute("suggestedRaiseName");

        }
        if (JSFHelper.getSession().getAttribute("acceptedJobValue") != null) {

            acceptedJobValue = (String)JSFHelper.getSession().getAttribute("acceptedJobValue");

        }
        if (JSFHelper.getSession().getAttribute("bgtTypesDTO") != null) {

            bgtTypesDTO = (IBgtTypesDTO)JSFHelper.getSession().getAttribute("bgtTypesDTO");

        }
        if (JSFHelper.getSession().getAttribute("acceptedCaderName") != null) {

            acceptedCaderName = (String)JSFHelper.getSession().getAttribute("acceptedCaderName");

        }
        if (JSFHelper.getSession().getAttribute("acceptedGroupName") != null) {

            acceptedGroupName = (String)JSFHelper.getSession().getAttribute("acceptedGroupName");

        }
        if (JSFHelper.getSession().getAttribute("acceptedDegreeName") != null) {

            acceptedDegreeName = (String)JSFHelper.getSession().getAttribute("acceptedDegreeName");

        }
        if (JSFHelper.getSession().getAttribute("acceptedRaiseName") != null) {

            acceptedRaiseName = (String)JSFHelper.getSession().getAttribute("acceptedRaiseName");

        }
        if (JSFHelper.getSession().getAttribute("civilServiceNotes") != null) {

            civilServiceNotes = (String)JSFHelper.getSession().getAttribute("civilServiceNotes");

        }

    }

    public void constructConditionDetailsPagePath(String requestCode, String url) {
        fullURL = constructPageURL(requestCode, url);
        System.out.println(fullURL);
    }

    public static String constructPageURL(String requestCode, String url) {
        StringBuilder uri = new StringBuilder(url);
        uri.append("?");
        uri.append("requestCode").append("=").append(requestCode);
        return uri.toString();
    }

    public void setFullURL(String fullURL) {
        this.fullURL = fullURL;
    }

    public String getFullURL() {
        return fullURL;
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setSuggestedJobValue(String suggestedJobValue) {
        this.suggestedJobValue = suggestedJobValue;
    }

    public String getSuggestedJobValue() {
        return suggestedJobValue;
    }

    public void setSuggestedCaderName(String suggestedCaderName) {
        this.suggestedCaderName = suggestedCaderName;
    }

    public String getSuggestedCaderName() {
        return suggestedCaderName;
    }

    public void setSuggestedGroupName(String suggestedGroupName) {
        this.suggestedGroupName = suggestedGroupName;
    }

    public String getSuggestedGroupName() {
        return suggestedGroupName;
    }

    public void setSuggestedDegreeName(String suggestedDegreeName) {
        this.suggestedDegreeName = suggestedDegreeName;
    }

    public String getSuggestedDegreeName() {
        return suggestedDegreeName;
    }

    public void setSuggestedRaiseName(String suggestedRaiseName) {
        this.suggestedRaiseName = suggestedRaiseName;
    }

    public String getSuggestedRaiseName() {
        return suggestedRaiseName;
    }

    public void setAcceptedJobValue(String acceptedJobValue) {
        this.acceptedJobValue = acceptedJobValue;
    }

    public String getAcceptedJobValue() {
        return acceptedJobValue;
    }

    public void setBgtTypesDTO(IBgtTypesDTO bgtTypesDTO) {
        this.bgtTypesDTO = bgtTypesDTO;
    }

    public IBgtTypesDTO getBgtTypesDTO() {
        return bgtTypesDTO;
    }

    public void setAcceptedCaderName(String acceptedCaderName) {
        this.acceptedCaderName = acceptedCaderName;
    }

    public String getAcceptedCaderName() {
        return acceptedCaderName;
    }

    public void setAcceptedGroupName(String acceptedGroupName) {
        this.acceptedGroupName = acceptedGroupName;
    }

    public String getAcceptedGroupName() {
        return acceptedGroupName;
    }

    public void setAcceptedDegreeName(String acceptedDegreeName) {
        this.acceptedDegreeName = acceptedDegreeName;
    }

    public String getAcceptedDegreeName() {
        return acceptedDegreeName;
    }

    public void setAcceptedRaiseName(String acceptedRaiseName) {
        this.acceptedRaiseName = acceptedRaiseName;
    }

    public String getAcceptedRaiseName() {
        return acceptedRaiseName;
    }

    public void setCivilServiceNotes(String civilServiceNotes) {
        this.civilServiceNotes = civilServiceNotes;
    }

    public String getCivilServiceNotes() {
        return civilServiceNotes;
    }
}
