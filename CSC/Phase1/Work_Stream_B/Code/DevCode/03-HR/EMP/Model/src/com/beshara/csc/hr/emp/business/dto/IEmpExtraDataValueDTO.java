package com.beshara.csc.hr.emp.business.dto;

public interface IEmpExtraDataValueDTO {
    public void setMinistryNotes(String ministryNotes);

    public String getMinistryNotes();

    public void setSuggestedJobCode(String suggestedJobCode);

    public String getSuggestedJobCode();

    public void setSelectionDeptNotes(String selectionDeptNotes);

    public String getSelectionDeptNotes();

    public void setArrangmentDeptNotes(String arrangmentDeptNotes);

    public String getArrangmentDeptNotes();

    public void setFatwaDeptNotes(String fatwaDeptNotes);

    public String getFatwaDeptNotes();
    
    public void setEmpExtraDataTypesDTO(IEmpExtraDataTypesDTO empExtraDataTypesDTO);

    public IEmpExtraDataTypesDTO getEmpExtraDataTypesDTO();
    public void setCivilServiceNotes(String CivilServiceNotes) ;

    public String getCivilServiceNotes() ;
    
    public void setSuggestedTotalReward(String suggestedTotalReward);

    public String getSuggestedTotalReward();
    
    public void setAcceptedTotalReward(String AcceptedTotalReward);

    public String getAcceptedTotalReward();
    
    public void setApprovedJob(String approvedJob);

    public String getApprovedJob();
}
