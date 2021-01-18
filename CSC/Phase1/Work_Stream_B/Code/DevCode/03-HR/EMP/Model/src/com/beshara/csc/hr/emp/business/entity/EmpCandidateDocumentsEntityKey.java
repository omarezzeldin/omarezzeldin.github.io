package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.EntityKey;


public class EmpCandidateDocumentsEntityKey extends EntityKey implements IEmpCandidateDocumentsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long candidateCode;
    private Long candidateCodeSeq;
    private Long cnddocSerial;

    public EmpCandidateDocumentsEntityKey() {
    }

    public EmpCandidateDocumentsEntityKey(Long candidateCode, Long candidateCodeSeq, Long cnddocSerial) {
        super(new Object[] { candidateCode, candidateCodeSeq, cnddocSerial });
        this.candidateCode = candidateCode;
        this.candidateCodeSeq = candidateCodeSeq;
        this.cnddocSerial = cnddocSerial;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCandidateCode() {
        return candidateCode;
    }


    public Long getCandidateCodeSeq() {
        return candidateCodeSeq;
    }


    public Long getCnddocSerial() {
        return cnddocSerial;
    }

}
