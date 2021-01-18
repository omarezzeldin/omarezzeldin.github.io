package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface EmpCandidateDocumentsSession extends BasicSession {

    /**
     * list All available entities Related to an employee * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> listAvailableEntities(IRequestInfoDTO requestInfo, IEntityKey code,
                                                 IEntityKey hireType) throws RemoteException, DataBaseException,
                                                                             SharedApplicationException;

    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IRequestInfoDTO requestInfo, IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;

    public List<IBasicDTO> filterAvailableEntitiesByCode(IRequestInfoDTO requestInfo, IEntityKey code,
                                                         IEntityKey hireType, Long docTypeCode) throws RemoteException,
                                                                                                       DataBaseException,
                                                                                                       SharedApplicationException;


    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO requestInfo, IBasicDTO paramDto,
                                                   IEntityKey hireType, String name,
                                                   Long docTypeCode) throws RemoteException, DataBaseException,
                                                                            SharedApplicationException;


    /**
     * filter All available entities * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO requestInfo, IEntityKey code, IEntityKey hireType,
                                                   String name) throws RemoteException, DataBaseException,
                                                                       SharedApplicationException;

    public Long checkAboutAttachmentForCivil(IRequestInfoDTO requestInfo, Long civilId,
                                             Long docTypeCode) throws RemoteException, DataBaseException,
                                                                      SharedApplicationException;
    
    
    
    
    public void addNewRecord(IRequestInfoDTO requestInfo,IEmpCandidateDocumentsDTO dto) throws RemoteException,DataBaseException,
                                                                           SharedApplicationException;


    public boolean addedRecordBefore(IRequestInfoDTO requestInfo,IEmpCandidateDocumentsDTO dto) throws RemoteException,DataBaseException,
                                                                           SharedApplicationException;
    
    public boolean removeRecord(IRequestInfoDTO requestInfo,IEmpCandidateDocumentsDTO dto) throws RemoteException,DataBaseException, SharedApplicationException ;
    
    public List<IEmpCandidateDocumentsDTO> getByCandCodeNew(IRequestInfoDTO requestInfo,IEntityKey candidateCode) throws RemoteException,DataBaseException,
                                                                                          SharedApplicationException;
}
