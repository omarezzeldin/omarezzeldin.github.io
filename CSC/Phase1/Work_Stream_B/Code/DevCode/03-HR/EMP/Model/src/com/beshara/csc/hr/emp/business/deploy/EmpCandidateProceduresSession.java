package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface EmpCandidateProceduresSession extends BasicSession {

    /**
     * list All available entities * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    //public List<IBasicDTO> listAvailableEntities(IEntityKey code,IEntityKey hireType) throws RemoteException, DataBaseException, SharedApplicationException;

    /**
     * @param code
     * @param name
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //public List<IBasicDTO> filterAvailableEntities(IEntityKey code, String name,IEntityKey hireType) throws RemoteException, DataBaseException, SharedApplicationException;
    //public List<IBasicDTO> filterAvailableEntities(IEntityKey code,IEntityKey hireType, String name) throws RemoteException, DataBaseException, SharedApplicationException;

    public List<IBasicDTO> listAvailableEntities(IRequestInfoDTO requestInfo, IEntityKey code,
                                                 IEntityKey hireType) throws RemoteException, DataBaseException,
                                                                             SharedApplicationException;

    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IRequestInfoDTO ri, IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    /**
     * @param code
     * @param name
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO requestInfo, IEntityKey code, String name,
                                                   IEntityKey hireType) throws RemoteException, DataBaseException,
                                                                               SharedApplicationException;

    //List<IBasicDTO> filterAvailableEntitiesByCode(IEntityKey code, Long hirProcedureCode,IEntityKey hireType) throws RemoteException, DataBaseException, SharedApplicationException;

    List<IBasicDTO> filterAvailableEntitiesByCode(IRequestInfoDTO requestInfo, IEntityKey code, Long hirProcedureCode,
                                                  IEntityKey hireType) throws RemoteException, DataBaseException,
                                                                              SharedApplicationException;

    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO requestInfo, IBasicDTO paramDto,
                                                   IEntityKey hireType, String name,
                                                   Long hirProcedureCode) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;
    
    public List<IEmpCandidateProceduresDTO> getByCandCode(IRequestInfoDTO requestInfo,Long candidateCode) throws RemoteException,DataBaseException,
                                                                                 SharedApplicationException;
}
