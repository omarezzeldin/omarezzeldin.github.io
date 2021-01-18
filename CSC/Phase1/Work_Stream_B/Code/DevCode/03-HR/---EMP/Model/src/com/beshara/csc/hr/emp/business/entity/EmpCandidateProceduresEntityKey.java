package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.EntityKey;


public class EmpCandidateProceduresEntityKey extends EntityKey implements IEmpCandidateProceduresEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long candidateCode;
    public Long candidateCodeSeq;
    public Long hirProcedureCode;

    public EmpCandidateProceduresEntityKey() {
    }
    public EmpCandidateProceduresEntityKey(Long candidateCode, Long candidateCodeSeq, Long hirprocedureCode) {
        super(new Object[] { candidateCode, candidateCodeSeq, hirprocedureCode });
        this.candidateCode = candidateCode;
        this.candidateCodeSeq = candidateCodeSeq;
        this.hirProcedureCode = hirprocedureCode;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCodeSeq() {
        return candidateCodeSeq;
    }

    public void setCandidateCodeSeq(Long candidateCodeSeq) {
        this.candidateCodeSeq = candidateCodeSeq;
    }

    public Long getHirProcedureCode() {
        return hirProcedureCode;
    }

    public void setHirProcedureCode(Long hirprocedureCode) {
        this.hirProcedureCode = hirprocedureCode;
    }
}
