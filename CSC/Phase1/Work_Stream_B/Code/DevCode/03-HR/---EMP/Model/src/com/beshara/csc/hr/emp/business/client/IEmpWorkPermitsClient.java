package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.IBasicClient;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpWorkPermitsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

public interface IEmpWorkPermitsClient extends IBasicClient {

    public List<IEmpWorkPermitsDTO> getEmpWorkPermitsByCivilId(Long civilId) throws DataBaseException,
                                                                                    SharedApplicationException;

    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByMinCode(Long MinCode) throws DataBaseException,
                                                                                   SharedApplicationException;


    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByCivilAndMinCode(Long civilId,
                                                                      Long minCode) throws DataBaseException,
                                                                                           SharedApplicationException;


}
