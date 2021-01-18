package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmpCandidatesEntityKey extends EntityKey implements IEmpCandidatesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long candidateCode;
    private Long candidateCodeSeq;

    public EmpCandidatesEntityKey() {
        super();
    }

    public EmpCandidatesEntityKey(Long candidateCode, Long candidateCodeSeq) {
        super(new Object[] { candidateCode, candidateCodeSeq });
        this.candidateCode = candidateCode;
        this.candidateCodeSeq = candidateCodeSeq;
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCodeSeq(Long candidateCodeSeq) {
        this.candidateCodeSeq = candidateCodeSeq;
    }

    public Long getCandidateCodeSeq() {
        return candidateCodeSeq;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
