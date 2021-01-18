package com.beshara.csc.esv.emp.eservices.emprequest;


import com.beshara.csc.esv.emp.dao.EmpRequestDAO;
import com.beshara.csc.esv.emp.entity.EmpRequestEntity;
import com.beshara.csc.esv.emp.eservices.EmpBaseEServiceImpl;
import com.beshara.csc.esv.emp.eservices.emprequest.dto.EmpRequestDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.Stateless;


@Stateless(name = "ERV-EMP-EosRequestEService", mappedName = "ERV-EMP-EosRequestEService")
public class EmpRequestEServiceImpl extends EmpBaseEServiceImpl implements IEmpRequestEService {

    public EmpRequestEServiceImpl() {
        super();
    }

    public EmpRequestDTO getEmpRequestById(Long requestNo) throws SharedApplicationException, DataBaseException {
        EmpRequestDAO empRequestDAO = new EmpRequestDAO(EM());
        EmpRequestEntity empRequestEntity = empRequestDAO.getEmpRequestById(requestNo);
        if (empRequestEntity == null) {
            throw new ItemNotFoundException();
        }
        return new EmpRequestDTO(empRequestEntity.getAddressInDetails(), empRequestEntity.getBuildingCode(),
                                 empRequestEntity.getCivilID(), empRequestEntity.getEmail(),
                                 empRequestEntity.getEmpName(), empRequestEntity.getFlatNo(),
                                 empRequestEntity.getFloorNo(), empRequestEntity.getGovCode(),
                                 empRequestEntity.getMapCode(), empRequestEntity.getMobileNo(),
                                 empRequestEntity.getPhonesNo(), empRequestEntity.getRealCivilID(),
                                 empRequestEntity.getRefuseReason(), empRequestEntity.getRegCode(),
                                 empRequestEntity.getRequestDate(), empRequestEntity.getRequestNo(),
                                 empRequestEntity.getRequestType(), empRequestEntity.getReturnReason(),
                                 empRequestEntity.getStatusCode(), empRequestEntity.getStreetCode(),
                                 empRequestEntity.getSysIterID(), empRequestEntity.getSysIterTotalsChild(),
                                 empRequestEntity.getSysIterTrID());
    }
}
