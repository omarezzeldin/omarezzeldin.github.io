package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;

public interface IEmpCandidateProceduresEntityKey extends IEntityKey{
    
    public int hashCode();
    
    public Long getCandidateCode() ;

    public void setCandidateCode(Long candidateCode);

    public Long getCandidateCodeSeq();

    public void setCandidateCodeSeq(Long candidateCodeSeq);

    public Long getHirProcedureCode() ;

    public void setHirProcedureCode(Long hirProcedureCode);
}
