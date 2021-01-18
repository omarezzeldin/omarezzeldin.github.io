package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;

public interface IEmpCandidateDocumentsEntityKey extends IEntityKey {

    public int hashCode();

    public Long getCandidateCode();


    public Long getCandidateCodeSeq();


    public Long getCnddocSerial();
}
