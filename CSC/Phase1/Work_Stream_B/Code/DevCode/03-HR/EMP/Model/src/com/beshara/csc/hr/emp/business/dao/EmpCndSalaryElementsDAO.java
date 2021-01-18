package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCndSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpCndSalaryElementsEntityKey;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.sal.business.entity.ISalDegreeReasonsEntityKey;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class EmpCndSalaryElementsDAO extends EmpBaseDAO {
    public EmpCndSalaryElementsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpCndSalaryElementsDAO();
    }

    public IEmpCndSalaryElementsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpCndSalaryElementsEntityKey id = (IEmpCndSalaryElementsEntityKey)id1;
            EmpCndSalaryElementsEntity empCndSalaryElementsEntity = EM().find(EmpCndSalaryElementsEntity.class, id);
            if (empCndSalaryElementsEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO =
                EmpEntityConverter.getEmpCndSalaryElementsDTO(empCndSalaryElementsEntity);
            return empCndSalaryElementsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IEmpCndSalaryElementsDTO getByCandCode(IEntityKey candidateCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            Long candCode = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCode();
            Query query = EM().createNamedQuery("EmpCndSalaryElementsEntity.getByCndCode");
            query.setParameter("candidateCode", candCode);
            query.setParameter("candidateCodeSeq", 1);

            List<EmpCndSalaryElementsEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new ItemNotFoundException();
            }
            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO =
                EmpEntityConverter.getEmpCndSalaryElementsDTO(list.get(0));

            return empCndSalaryElementsDTO;
        } catch (Exception e) {
            e.printStackTrace();
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IEmpCndSalaryElementsDTO getByCandCodeForCRS(IEntityKey candidateCode) throws DataBaseException,
                                                                                         SharedApplicationException {
        try {
            Long candCode = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCode();
            Query query = EM().createNamedQuery("EmpCndSalaryElementsEntity.getByCndCode");
            query.setParameter("candidateCode", candCode);
            query.setParameter("candidateCodeSeq", 1);

            List<EmpCndSalaryElementsEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                return null;
            }
            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO =
                EmpEntityConverter.getEmpCndSalaryElementsDTO(list.get(0));

            return empCndSalaryElementsDTO;
        } catch (Exception e) {
            e.printStackTrace();
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmpCndSalaryElementsEntity.findNewId").getSingleResult();
            if (id != null) {
                return id + 1;
            } else {
                return new Long(1);
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IEmpCndSalaryElementsDTO add(IBasicDTO empCndSalaryElementsDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = (IEmpCndSalaryElementsDTO)empCndSalaryElementsDTO1;
            EmpCndSalaryElementsEntity empCndSalaryElementsEntity = new EmpCndSalaryElementsEntity();


            //empCndSalaryElementsEntity.setCndSalelmSerial(((IEmpCndSalaryElementsEntityKey)empCndSalaryElementsDTO.getCode()).getCndSalelmSerial());
            empCndSalaryElementsEntity.setCndSalelmSerial(findMaxId());
            empCndSalaryElementsEntity.setFromDate(empCndSalaryElementsDTO.getFromDate());
            empCndSalaryElementsEntity.setCreatedBy(empCndSalaryElementsDTO.getCreatedBy());
            empCndSalaryElementsEntity.setCreatedDate(empCndSalaryElementsDTO.getCreatedDate());
            empCndSalaryElementsEntity.setAuditStatus(empCndSalaryElementsDTO.getAuditStatus());
            empCndSalaryElementsEntity.setTabrecSerial(empCndSalaryElementsDTO.getTabrecSerial());
            empCndSalaryElementsEntity.setElementValue(empCndSalaryElementsDTO.getElementValue());
            empCndSalaryElementsEntity.setElmguideCodeEquv(empCndSalaryElementsDTO.getElmguideCodeEquv());
            if (empCndSalaryElementsDTO.getEmpCandidatesDTO() != null) {
                EmpCandidatesEntity empCandidatesEntity =
                    EM().find(EmpCandidatesEntity.class, (empCndSalaryElementsDTO.getEmpCandidatesDTO().getCode()));
                if (empCandidatesEntity == null) {
                    throw new ItemNotFoundException();
                }
                empCndSalaryElementsEntity.setEmpCandidatesEntity(empCandidatesEntity);

            }

            if (empCndSalaryElementsDTO.getSalDegreeReasonsDTO() != null) {
                // SalDegreeReasonsEntity salDegreeReasonsEntity = em.find(SalDegreeReasonsEntity.class, (empCndSalaryElementsDTO.getSalDegreeReasonsDTO().getCode()));
                ISalDegreeReasonsEntityKey salDEk =
                    (ISalDegreeReasonsEntityKey)empCndSalaryElementsDTO.getSalDegreeReasonsDTO().getCode();
                if (salDEk == null) {
                    throw new ItemNotFoundException();
                }
                empCndSalaryElementsEntity.setDegreasonCode(salDEk.getDegreasonCode());
            }

            if (empCndSalaryElementsDTO.getSalElementGuidesDTO() != null) {
                //SalElementGuidesEntity salElementGuidesEntity = em.find(SalElementGuidesEntity.class, (empCndSalaryElementsDTO.getSalElementGuidesDTO().getCode()));
                ISalElementGuidesEntityKey elmgEk =
                    (ISalElementGuidesEntityKey)empCndSalaryElementsDTO.getSalElementGuidesDTO().getCode();
                if (elmgEk == null) {
                    throw new ItemNotFoundException();
                } else {
                    empCndSalaryElementsEntity.setElmguideCode(elmgEk.getElmguideCode());
                    // empCndSalaryElementsEntity.setElmguideCodeEquv(elmgEk.get);
                }

            }
            IEmpCndSalaryElementsDTO addedDTO =
                EmpEntityConverter.getEmpCndSalaryElementsDTO(doAdd(empCndSalaryElementsEntity));
            addedDTO.setCode(EmpEntityKeyFactory.createEmpExternalExperienceEntityKey(BigDecimal.valueOf(findMaxId())));
            return addedDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public boolean updat(IBasicDTO empCndSalaryElementsDTO1) throws DataBaseException, SharedApplicationException {
        try {

            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = (IEmpCndSalaryElementsDTO)empCndSalaryElementsDTO1;


            EmpCndSalaryElementsEntity empCndSalaryElementsEntity =
                EM().find(EmpCndSalaryElementsEntity.class, empCndSalaryElementsDTO.getCode());

            empCndSalaryElementsEntity.setCandidateCodeSeq(1L);
            empCndSalaryElementsEntity.setFromDate(empCndSalaryElementsDTO.getFromDate());
            empCndSalaryElementsEntity.setCreatedBy(empCndSalaryElementsDTO.getCreatedBy());
            empCndSalaryElementsEntity.setCreatedDate(empCndSalaryElementsDTO.getCreatedDate());
            empCndSalaryElementsEntity.setAuditStatus(empCndSalaryElementsDTO.getAuditStatus());
            empCndSalaryElementsEntity.setTabrecSerial(empCndSalaryElementsDTO.getTabrecSerial());
            empCndSalaryElementsEntity.setElmguideCodeEquv(empCndSalaryElementsDTO.getElmguideCodeEquv());
            empCndSalaryElementsEntity.setElementValue(empCndSalaryElementsDTO.getElementValue());
            if (empCndSalaryElementsDTO.getEmpCandidatesDTO() != null) {
                EmpCandidatesEntity empCandidatesEntity =
                    EM().find(EmpCandidatesEntity.class, (empCndSalaryElementsDTO.getEmpCandidatesDTO().getCode()));
                if (empCandidatesEntity == null) {
                    throw new ItemNotFoundException();
                }
                empCndSalaryElementsEntity.setEmpCandidatesEntity(empCandidatesEntity);

            }

            if (empCndSalaryElementsDTO.getSalDegreeReasonsDTO() != null) {
                ISalDegreeReasonsEntityKey salDEk =
                    (ISalDegreeReasonsEntityKey)empCndSalaryElementsDTO.getSalDegreeReasonsDTO().getCode();
                if (salDEk == null) {
                    throw new ItemNotFoundException();
                }
                empCndSalaryElementsEntity.setDegreasonCode(salDEk.getDegreasonCode());
            }

            if (empCndSalaryElementsDTO.getSalElementGuidesDTO() != null) {
                ISalElementGuidesEntityKey elmgEk =
                    (ISalElementGuidesEntityKey)empCndSalaryElementsDTO.getSalElementGuidesDTO().getCode();
                if (elmgEk == null) {
                    throw new ItemNotFoundException();
                } else {
                    empCndSalaryElementsEntity.setElmguideCode(elmgEk.getElmguideCode());

                }
            }
            doUpdate(empCndSalaryElementsEntity);


        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return true;
    }

    public boolean updateElmguideCodeEquv(IBasicDTO empCndSalaryElementsDTO1) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {

            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = (IEmpCndSalaryElementsDTO)empCndSalaryElementsDTO1;


            EmpCndSalaryElementsEntity empCndSalaryElementsEntity =
                EM().find(EmpCndSalaryElementsEntity.class, empCndSalaryElementsDTO.getCode());

            empCndSalaryElementsEntity.setCandidateCodeSeq(1L);
            empCndSalaryElementsEntity.setElmguideCodeEquv(empCndSalaryElementsDTO.getElmguideCodeEquv());
            empCndSalaryElementsEntity.setElementValue(empCndSalaryElementsDTO.getElementValue());
            doUpdate(empCndSalaryElementsEntity);


        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return true;
    }

    public boolean updateElementGuide(IBasicDTO empCndSalaryElementsDTO1) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {

            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = (IEmpCndSalaryElementsDTO)empCndSalaryElementsDTO1;


            EmpCndSalaryElementsEntity empCndSalaryElementsEntity =
                EM().find(EmpCndSalaryElementsEntity.class, (IEmpCndSalaryElementsEntityKey)empCndSalaryElementsDTO.getCode());
            if (empCndSalaryElementsDTO.getSalElementGuidesDTO() != null) {
                //SalElementGuidesEntity salElementGuidesEntity = em.find(SalElementGuidesEntity.class, (empCndSalaryElementsDTO.getSalElementGuidesDTO().getCode()));
                ISalElementGuidesEntityKey elmgEk =
                    (ISalElementGuidesEntityKey)empCndSalaryElementsDTO.getSalElementGuidesDTO().getCode();
                if (elmgEk == null) {
                    throw new ItemNotFoundException();
                } else {
                    empCndSalaryElementsEntity.setElmguideCode(elmgEk.getElmguideCode());
                    // empCndSalaryElementsEntity.setElmguideCodeEquv(elmgEk.get);
                }

            }
            empCndSalaryElementsEntity.setCandidateCodeSeq(1L);
            // empCndSalaryElementsEntity.setElmguideCode(empCndSalaryElementsDTO.getElmguideCode());
            doUpdate(empCndSalaryElementsEntity);


        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return true;
    }


    public List<IEmpCandidateExtraDataDTO> getBounsForCandidae(IEmpCandidatesEntityKey ek) throws DataBaseException,
                                                                                                  SharedApplicationException {
        List<IEmpCandidateExtraDataDTO> arrayList = new ArrayList<IEmpCandidateExtraDataDTO>();

        try {
            StringBuilder str = new StringBuilder("");
            str.append(" SELECT  ");
            str.append("  extra_data.VALUE, extra_data.VALUE_DESC , guide.EMLGUIDE_NAME ");

            str.append("  FROM  HR_EMP_CANDIDATE_EXTRA_DATA extra_data ,  HR_SAL_ELEMENT_GUIDES guide ");
            str.append("  Where   ");
            str.append("  extra_data.VALUE =  guide.ELMGUIDE_CODE  ");
            str.append("  and extra_data.CANDIDATE_CODE = " + ek.getCandidateCode());
            str.append("  AND extra_data.CANDIDATE_CODE_SEQ = " + ek.getCandidateCodeSeq());
            str.append("   AND extra_data.EXTDATTYPE_CODE = 21   ");

            System.out.println("Query to get Bouns for emp candidate" + str.toString());

            Query query = EM().createNativeQuery(str.toString());
           
            List <Vector>list = query.getResultList();
            if (list == null || list.size() == 0)
                return arrayList;


            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = null;

            for (Vector obj : list) {
                empCandidateExtraDataDTO = new EmpCandidateExtraDataDTO();
                empCandidateExtraDataDTO.setValue((String)obj.elementAt(0));
                empCandidateExtraDataDTO.setValueDesc((String)obj.elementAt(1));
                empCandidateExtraDataDTO.setName((String)obj.elementAt(2));

                arrayList.add(empCandidateExtraDataDTO);

            }

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;

            }
        }
        return arrayList  ;
    }

}
