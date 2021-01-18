package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public interface IEmpCndSalaryElementsClient extends IBasicClient {

    public IEmpCndSalaryElementsDTO getByCandCode(IEntityKey candidateCode) throws DataBaseException,
                                                                                   SharedApplicationException;
    public List<IEmpCandidateExtraDataDTO> getBounsForCandidae(IEmpCandidatesEntityKey ek) throws DataBaseException,
                                                                                                  SharedApplicationException ;

}
