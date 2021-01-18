package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public interface IEmpCandidateProceduresClient extends IBasicClient {

    /**
     * list All available entities * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> listAvailableEntities(IEntityKey code, IEntityKey hireType) throws DataBaseException,
                                                                                              SharedApplicationException;

    /**
     * Filtr avilable entity * @param code
     * @param name
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> filterAvailableEntities(IEntityKey code, String name,
                                                   IEntityKey hireType) throws DataBaseException,
                                                                               SharedApplicationException;

    public List<IBasicDTO> filterAvailableEntitiesbyHireType(IEntityKey code, IEntityKey hireType,
                                                             String name) throws DataBaseException,
                                                                                 SharedApplicationException;

    List<IBasicDTO> filterAvailableEntitiesByCode(IEntityKey code, Long hirProcedureCode,
                                                  IEntityKey hireType) throws DataBaseException,
                                                                              SharedApplicationException;

    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws DataBaseException,
                                                                                             SharedApplicationException;

    public List<IBasicDTO> filterAvailableEntities(IBasicDTO paramDto, IEntityKey hireType, String name,
                                                   Long hirProcedureCode) throws DataBaseException,
                                                                                 SharedApplicationException;
    
    public List<IEmpCandidateProceduresDTO> getByCandCode(Long candidateCode) throws DataBaseException,
                                                                                 SharedApplicationException;
}
