package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.deploy.EmpCandidateProceduresSession;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class EmpCandidateProceduresClientImpl extends BaseClientImpl3 implements IEmpCandidateProceduresClient {

    public EmpCandidateProceduresClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpCandidateProceduresSession.class;
    }

    protected EmpCandidateProceduresSession SESSION() {
        return (EmpCandidateProceduresSession)super.SESSION();
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

    /**
     * @param code
     * @param name
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> filterAvailableEntities(IEntityKey code, String name,
                                                   IEntityKey hireType) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return SESSION().filterAvailableEntities(RI(), code, name, hireType);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> filterAvailableEntitiesbyHireType(IEntityKey code, IEntityKey hireType,
                                                             String name) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return SESSION().filterAvailableEntities(RI(), code, name, hireType);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> filterAvailableEntitiesByCode(IEntityKey code, Long hirProcedureCode,
                                                         IEntityKey hireType) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().filterAvailableEntitiesByCode(RI(), code, hirProcedureCode, hireType);
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

    public List<IBasicDTO> filterAvailableEntities(IBasicDTO paramDto, IEntityKey hireType, String name,
                                                   Long hirProcedureCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return SESSION().filterAvailableEntities(RI(), paramDto, hireType,name,hirProcedureCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    public List<IEmpCandidateProceduresDTO> getByCandCode(Long candidateCode) throws DataBaseException,
    SharedApplicationException{
            try {
                return SESSION().getByCandCode(RI(), candidateCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        }
}
