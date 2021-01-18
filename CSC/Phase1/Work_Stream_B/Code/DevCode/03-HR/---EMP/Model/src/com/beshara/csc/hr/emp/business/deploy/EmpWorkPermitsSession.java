package com.beshara.csc.hr.emp.business.deploy;

import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpWorkPermitsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;
@Remote
public interface EmpWorkPermitsSession extends BasicSession {
    
  
 public List<IEmpWorkPermitsDTO> getEmpWorkPermitsByCivilId(IRequestInfoDTO ri,
                                                                        Long civilId) throws  RemoteException, DataBaseException,
                                                                                             SharedApplicationException;
 
    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByMinCode(IRequestInfoDTO ri,
                                                                        Long MinCode) throws  RemoteException, DataBaseException,
                                                                                             SharedApplicationException;
    
    
    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByCivilAndMinCode(IRequestInfoDTO ri,Long civilId,
                                                                        Long minCode)  throws  RemoteException, DataBaseException,
                                                                                             SharedApplicationException;
    
    


}
