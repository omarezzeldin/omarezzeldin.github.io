package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;

public interface IEmpCandidatesEntityKey extends IEntityKey {

    public void setCandidateCode(Long candidateCode);

    public Long getCandidateCode();

    public void setCandidateCodeSeq(Long candidateCodeSeq);

    public Long getCandidateCodeSeq();

    public int hashCode();
}
