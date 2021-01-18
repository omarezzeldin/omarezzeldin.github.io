package com.beshara.csc.esv.emp.eservices.emprequest;


import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.esv.emp.eservices.emprequest.dto.EmpRequestDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.Remote;

@Remote
public interface IEmpRequestEService extends IEService{
    EmpRequestDTO getEmpRequestById(Long requestNo) throws SharedApplicationException, DataBaseException;
}
