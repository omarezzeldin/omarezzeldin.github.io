package com.beshara.csc.hr.emp.business.dto;

public interface IEmpCandidateParent extends IEmpDTO {

    public void setSelectedEmpCandidatesDTO(IEmpCandidatesDTO selectedEmpCandidatesDTO);

    public IEmpCandidatesDTO getSelectedEmpCandidatesDTO();

    public void setFirstEmpCandidatesDTO(IEmpCandidatesDTO firstEmpCandidatesDTO);

    public IEmpCandidatesDTO getFirstEmpCandidatesDTO();
}
