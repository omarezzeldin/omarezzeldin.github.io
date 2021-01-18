package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public interface IEmpCandidateDocumentsClient extends IBasicClient {

    /**
     * list All available entities * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> listAvailableEntities(IEntityKey code, IEntityKey hireType) throws DataBaseException,
                                                                                              SharedApplicationException;

    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws DataBaseException,
                                                                                             SharedApplicationException;

    /**
     * filter All available entities * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> filterAvailableEntities(IEntityKey code, IEntityKey hireType,
                                                   String name) throws DataBaseException, SharedApplicationException;

    List<IBasicDTO> filterAvailableEntitiesByCode(IEntityKey code, IEntityKey hireType,
                                                  Long docTypeCode) throws DataBaseException,
                                                                           SharedApplicationException;

    public List<IBasicDTO> filterAvailableEntities(IBasicDTO paramDto, IEntityKey hireType, String name,
                                                   Long docTypeCode) throws DataBaseException,
                                                                            SharedApplicationException;

    public Long checkAboutAttachmentForCivil(Long civilId, Long docTypeCode) throws DataBaseException,
                                                                                    SharedApplicationException;
    
    
    public void addNewRecord(IEmpCandidateDocumentsDTO dto) throws DataBaseException,
                                                                           SharedApplicationException;
    
    public boolean addedRecordBefore(IEmpCandidateDocumentsDTO dto) throws DataBaseException,
                                                                           SharedApplicationException;
    
    public boolean removeRecord(IEmpCandidateDocumentsDTO dto) throws DataBaseException,
    SharedApplicationException;
    
    public List<IEmpCandidateDocumentsDTO> getByCandCodeNew(IEntityKey candidateCode) throws DataBaseException,
                                                                                          SharedApplicationException;
}
