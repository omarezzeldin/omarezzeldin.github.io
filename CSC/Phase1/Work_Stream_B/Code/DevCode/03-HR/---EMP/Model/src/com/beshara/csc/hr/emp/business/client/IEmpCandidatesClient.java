package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.ParametersDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.TerminationReturnType;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;


public interface IEmpCandidatesClient extends IBasicClient {

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     * @throws DataBaseException
     */
    Boolean isMinistryFileNoExist(String ministryFileNo) throws DataBaseException;

    public IPagingResponseDTO getByHireStageWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                    SharedApplicationException;

    public IPagingResponseDTO getByHireStageWithPaging(IBasicDTO _searchDTO, int manageId) throws DataBaseException,
                                                                                                  SharedApplicationException;

    /**
     * filter employees by hire types for employee hire execution
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     * @author Dina
     */
    public List<IBasicDTO> filterByHireTypeForHireExecute(Long hirtypeCode, Long loggedMin) throws DataBaseException;

    public List<IBasicDTO> filterByHireType(Long hirtypeCode) throws DataBaseException;

    public List<IBasicDTO> filterByHireTypeWithMin(Long hirtypeCode, Long minCode) throws DataBaseException;

    public List<IBasicDTO> filterByAllHireTypes() throws DataBaseException;

    public List<IBasicDTO> filterByAllHireTypesWithMin(Long minCode) throws DataBaseException;

    public List<IBasicDTO> filterByAllHireTypesForHireExecute() throws DataBaseException;


    public List<IBasicDTO> getFilterEmpWaitingForHireDecWithDiffStage(IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                                             SharedApplicationException;

    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                                          SharedApplicationException;


    public IPagingResponseDTO searchEmployeeToCompleteData(IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                            SharedApplicationException;


    public IEmpCandidatesDTO getByCndCodeAndSequnc(IEntityKey id1) throws DataBaseException,
                                                                          SharedApplicationException;


    public IBasicDTO updateEmp(IBasicDTO empCandidateDTO1) throws DataBaseException, SharedApplicationException;
    
    public IBasicDTO updateEmpData(IBasicDTO empCandidateDTO1) throws DataBaseException, SharedApplicationException;

    public IBasicDTO addNewSeqForCandidate(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                              SharedApplicationException;

    public IBasicDTO adoptionOfFinal(IBasicDTO empCandidatesDTO1) throws DataBaseException, SharedApplicationException;


    public boolean checkEmployeeByExtraDataType(Long civilId, Long extDatatype,Long candidateCode) throws DataBaseException,
                                                                                       SharedApplicationException;

    public boolean suggestNewJob(IBasicDTO empCandidatesDTO, IBasicDTO extraDataDTO) throws DataBaseException,
                                                                                            SharedApplicationException;

    public IBasicDTO proceedContractPersonData(IBasicDTO empCandidatesDTO,
                                               IBasicDTO extraDataDTO) throws DataBaseException,
                                                                              SharedApplicationException;

    public IBasicDTO updateEmpCandCMT(IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException;

    public IBasicDTO updateEmpCancelCandidate(IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                              DataBaseException;
    //    public boolean suggestAndConvertToDewan( IBasicDTO empCandidatesDTO,
    //                                            IBasicDTO extraDataDTO) throws  DataBaseException,
    //                                                                           SharedApplicationException ;

    public IBasicDTO applyJobSuggestion(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                           SharedApplicationException;

    public IBasicDTO proceedCRSPersonData(IBasicDTO empCandidatesDTO,
                                          List<IEmpCandidateExtraDataDTO> extraDataDTO) throws DataBaseException,
                                                                                               SharedApplicationException;

    public IBasicDTO applyCRSSuggestion(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                           SharedApplicationException;

    public boolean approveFinancialDegreeFatwa(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                  SharedApplicationException;

    public boolean fromFatwaToChoiceDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException;

    public boolean fromOrderToChoiceDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException;

    public boolean approveJobOrderDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                          SharedApplicationException;
    
    public boolean saveJobOrderDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                          SharedApplicationException;

    public boolean fromChoiceToOrder(IBasicDTO empCandidatesDTO) throws DataBaseException, SharedApplicationException;
    
    public boolean fromChoiceToFatwa(IBasicDTO empCandidatesDTO) throws DataBaseException, SharedApplicationException;

    public boolean returnFromChoiceToOrg(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException;

    public boolean approveDataChoiceDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException;

    public List<IEmpCandidatesDTO> checkIfCitizenIsCandidate(Long civilId) throws DataBaseException,
                                                                                  SharedApplicationException;

    public IBasicDTO addCandidateInfo(IBasicDTO empCandidatesDTO1) throws SharedApplicationException,
                                                                          DataBaseException;

    public int isFilNumRedundant(Long minCode, String minFileNo, Long civilId) throws DataBaseException,
                                                                                      SharedApplicationException;


    public List<IBasicDTO> filterByAllHireTypeForHireExecute(Long loggedMin) throws DataBaseException,
                                                                                    SharedApplicationException;


    public Boolean executeEmployment(IBasicDTO dto) throws DataBaseException, SharedApplicationException;

    public boolean validateChildRaise(IEmpCandidatesDTO cndDTO) throws DataBaseException, SharedApplicationException;


    public List<IBasicDTO> searchForHireExecute(Long loggedMin, Long civilId, String name,
                                                Long hireTypeKey) throws DataBaseException, SharedApplicationException;


    public List<IBasicDTO> searchForSpecificEmployeeByLoggedMin(IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException;


    public List<IBasicDTO> checkDecisionHire(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                       SharedApplicationException;

    public boolean applyDecisionForHire(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                  SharedApplicationException;

    public List<IBasicDTO> checkApplyHire(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                    SharedApplicationException;

    public boolean applyHire(List<IBasicDTO> empCandidatesList) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> getOtherCandByCivils(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                          SharedApplicationException;

    public List<IBasicDTO> checkConditions(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                     SharedApplicationException;


    public boolean checkEmployeeInNeed(Long civilId) throws DataBaseException, SharedApplicationException;

    public List getAllRequests(IEmployeeSearchDTO searchDTO) throws DataBaseException, SharedApplicationException;

    public Long countCitizensRegisteredOnQual(Long qualificationKey) throws DataBaseException,
                                                                            SharedApplicationException;

    public boolean approveDataByCivilService(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                SharedApplicationException;

    public void addNewEmpVacBalance(IEmpCandidatesDTO empCandidatesDTO) throws DataBaseException,
                                                                               SharedApplicationException;

    public Boolean executeFinanciaPackage(IEmpCandidatesDTO dto, Long elementGuide,
                                          Long eqDegreeCode) throws DataBaseException, SharedApplicationException;

    public Long getCandidateCode(Long civilId) throws DataBaseException, SharedApplicationException;

    public String queryCrimnalStatus(Long realCivil) throws DataBaseException, SharedApplicationException;
    
    
    public Long getHirDateCount(Long candidateCode) throws DataBaseException, SharedApplicationException;
    
    
    IPagingResponseDTO getAllCandidateDataWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                       SharedApplicationException;
    
    public String getCandidateName(IBasicDTO searchDTO) throws DataBaseException,
                                                                                SharedApplicationException;
    
    public String getPreviosNotes(IBasicDTO empDTO) throws DataBaseException,
                                                                                SharedApplicationException;
    
    public  TerminationReturnType terminate(ParametersDTO dto) throws DataBaseException,
                                                                        SharedApplicationException;


    public Boolean chkWrkCenterStatus(Long minCode, String wrkCode) throws DataBaseException,
    SharedApplicationException;
}
