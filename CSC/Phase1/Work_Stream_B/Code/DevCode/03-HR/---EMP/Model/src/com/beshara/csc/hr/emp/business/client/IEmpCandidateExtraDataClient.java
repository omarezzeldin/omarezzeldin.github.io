package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.IBasicClient;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public interface IEmpCandidateExtraDataClient extends IBasicClient{
    
    public String extraDataValueForType(Long candCode, Long candSeq, Long extraDataType) throws DataBaseException,
                                                                                                    SharedApplicationException;
}
