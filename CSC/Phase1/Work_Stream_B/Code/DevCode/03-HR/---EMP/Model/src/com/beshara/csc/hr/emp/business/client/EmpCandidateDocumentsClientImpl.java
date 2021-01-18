package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.deploy.EmpCandidateDocumentsSession;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class EmpCandidateDocumentsClientImpl extends BaseClientImpl3 implements IEmpCandidateDocumentsClient {


    public EmpCandidateDocumentsClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpCandidateDocumentsSession.class;
    }

    protected EmpCandidateDocumentsSession SESSION() {
        return (EmpCandidateDocumentsSession)super.SESSION();
    }

    /**
     * list All available entities * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> listAvailableEntities(IEntityKey code, IEntityKey hireType) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            return SESSION().listAvailableEntities(RI(), code, hireType);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return SESSION().listAvailableEntitiesByKwtCitizenCode(RI(), paramDto, hireType);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * filter All available entities * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> filterAvailableEntities(IEntityKey code, IEntityKey hireType,
                                                   String name) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().filterAvailableEntities(RI(), code, hireType, name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> filterAvailableEntitiesByCode(IEntityKey code, IEntityKey hireType,
                                                         Long docTypeCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().filterAvailableEntitiesByCode(RI(), code, hireType, docTypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> filterAvailableEntities(IBasicDTO paramDto, IEntityKey hireType, String name,
                                                   Long docTypeCode) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().filterAvailableEntities(RI(), paramDto, hireType, name, docTypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Long checkAboutAttachmentForCivil(Long civilId, Long docTypeCode) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            return SESSION().checkAboutAttachmentForCivil(RI(), civilId, docTypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public void addNewRecord(IEmpCandidateDocumentsDTO dto) throws DataBaseException,
    SharedApplicationException{
            try {
                 SESSION().addNewRecord(RI(), dto);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        
        }
    
    public boolean addedRecordBefore(IEmpCandidateDocumentsDTO dto) throws DataBaseException,
    SharedApplicationException{
            try {
             return    SESSION().addedRecordBefore(RI(), dto);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
            
        }
    
    public boolean removeRecord(IEmpCandidateDocumentsDTO dto) throws DataBaseException,
    SharedApplicationException{
            try {
             return    SESSION().removeRecord(RI(), dto);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
            
        }
    
    public List<IEmpCandidateDocumentsDTO> getByCandCodeNew(IEntityKey candidateCode) throws DataBaseException,
    SharedApplicationException{
            try {
             return    SESSION().getByCandCodeNew(RI(), candidateCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        
        }

}
