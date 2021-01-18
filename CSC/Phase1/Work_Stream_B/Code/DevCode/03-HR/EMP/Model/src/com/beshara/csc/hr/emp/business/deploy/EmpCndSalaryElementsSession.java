package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface EmpCndSalaryElementsSession extends BasicSession {
    
    public IEmpCndSalaryElementsDTO getByCandCode(IRequestInfoDTO requestInfo, IEntityKey candidateCode) throws DataBaseException, RemoteException,
                                                                                   SharedApplicationException;
    
    public List<IEmpCandidateExtraDataDTO> getBounsForCandidae(IRequestInfoDTO requestInfo,IEmpCandidatesEntityKey ek) throws DataBaseException,RemoteException,
                                                                                                  SharedApplicationException ;

}
