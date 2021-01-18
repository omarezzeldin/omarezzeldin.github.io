package com.beshara.csc.hr.emp.business.dto;


public class EmpCandidateParent extends EmpDTO implements IEmpCandidateParent {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private IEmpCandidatesDTO selectedEmpCandidatesDTO;
    private IEmpCandidatesDTO firstEmpCandidatesDTO;

    public EmpCandidateParent() {
        super();
    }

    public void setSelectedEmpCandidatesDTO(IEmpCandidatesDTO selectedEmpCandidatesDTO) {
        this.selectedEmpCandidatesDTO = selectedEmpCandidatesDTO;
    }

    public IEmpCandidatesDTO getSelectedEmpCandidatesDTO() {
        return selectedEmpCandidatesDTO;
    }

    public void setFirstEmpCandidatesDTO(IEmpCandidatesDTO firstEmpCandidatesDTO) {
        this.firstEmpCandidatesDTO = firstEmpCandidatesDTO;
    }

    public IEmpCandidatesDTO getFirstEmpCandidatesDTO() {
        return firstEmpCandidatesDTO;
    }
}
