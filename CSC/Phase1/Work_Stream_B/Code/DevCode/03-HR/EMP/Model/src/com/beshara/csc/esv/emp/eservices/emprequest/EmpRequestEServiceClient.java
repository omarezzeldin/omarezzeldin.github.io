package com.beshara.csc.esv.emp.eservices.emprequest;

import com.beshara.base.integration.eservices.EServiceClient;
import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.esv.emp.eservices.emprequest.dto.EmpRequestDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class EmpRequestEServiceClient extends EServiceClient {

    public EmpRequestEServiceClient() {
        super();
    }

    @Override
    protected Class<? extends IEService> getEServiceInterface() {
        return IEmpRequestEService.class;
    }

    protected IEmpRequestEService SERVICE() {
        return (IEmpRequestEService)super.SERVICE();
    }

    public EmpRequestDTO getEmpRequestByRequestNo(Long requestNo) throws SharedApplicationException,
                                                                         DataBaseException {
        return SERVICE().getEmpRequestById(requestNo);
    }
}
