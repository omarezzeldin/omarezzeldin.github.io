package com.beshara.csc.hr.emp.business.dto;

public class EmpExtraDataValueDTO extends EmpDTO implements IEmpExtraDataValueDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
    private String ministryNotes = " ";
    private String suggestedJobCode = " ";
    private String selectionDeptNotes = " ";
    private String arrangmentDeptNotes = " ";
    private String fatwaDeptNotes = " ";
    private String civilServiceNotes=" ";
    private String suggestedTotalReward = " ";
    private String AcceptedTotalReward = " ";
    private String approvedJob = " ";
    public void setMinistryNotes(String ministryNotes) {
        this.ministryNotes = ministryNotes;
    }

    public String getMinistryNotes() {
        return ministryNotes;
    }

    public void setSuggestedJobCode(String suggestedJobCode) {
        this.suggestedJobCode = suggestedJobCode;
    }

    public String getSuggestedJobCode() {
        return suggestedJobCode;
    }

    public void setSelectionDeptNotes(String selectionDeptNotes) {
        this.selectionDeptNotes = selectionDeptNotes;
    }

    public String getSelectionDeptNotes() {
        return selectionDeptNotes;
    }

    public void setArrangmentDeptNotes(String arrangmentDeptNotes) {
        this.arrangmentDeptNotes = arrangmentDeptNotes;
    }

    public String getArrangmentDeptNotes() {
        return arrangmentDeptNotes;
    }

    public void setFatwaDeptNotes(String fatwaDeptNotes) {
        this.fatwaDeptNotes = fatwaDeptNotes;
    }

    public String getFatwaDeptNotes() {
        return fatwaDeptNotes;
    }

    public void setEmpExtraDataTypesDTO(IEmpExtraDataTypesDTO empExtraDataTypesDTO) {
        this.empExtraDataTypesDTO = empExtraDataTypesDTO;
    }

    public IEmpExtraDataTypesDTO getEmpExtraDataTypesDTO() {
        return empExtraDataTypesDTO;
    }

    public void setCivilServiceNotes(String civilServiceNotes) {
        this.civilServiceNotes = civilServiceNotes;
    }

    public String getCivilServiceNotes() {
        return civilServiceNotes;
    }

    public void setSuggestedTotalReward(String suggestedTotalReward) {
        this.suggestedTotalReward = suggestedTotalReward;
    }

    public String getSuggestedTotalReward() {
        return suggestedTotalReward;
    }

    public void setAcceptedTotalReward(String AcceptedTotalReward) {
        this.AcceptedTotalReward = AcceptedTotalReward;
    }

    public String getAcceptedTotalReward() {
        return AcceptedTotalReward;
    }

    public void setApprovedJob(String approvedJob) {
        this.approvedJob = approvedJob;
    }

    public String getApprovedJob() {
        return approvedJob;
    }
}
