package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.EmpWorkPermitsDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpWorkPermitsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpWorkPermitsEntity;
import com.beshara.csc.hr.emp.business.shared.exception.EmpWorkPermitsConflictException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "EmpWorkPermitsSession", mappedName = "Emp-Model-EmpWorkPermitsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpWorkPermitsSessionBean extends EmpBaseSessionBean implements EmpWorkPermitsSession  {
    public EmpWorkPermitsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpWorkPermitsEntity.class;
    }
    
    protected EmpWorkPermitsDAO DAO() {
        return (EmpWorkPermitsDAO)super.DAO();
    }
    
    
    public List<IEmpWorkPermitsDTO> getEmpWorkPermitsByCivilId(IRequestInfoDTO ri,
                                                                        Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getEmpWorkPermitsByCivilId(civilId);
    }
    
    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByMinCode(IRequestInfoDTO ri,
                                                                        Long MinCode) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getAllEmpPermitsByMinCode(MinCode);
        
        
    }
    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByCivilAndMinCode(IRequestInfoDTO ri,Long civilId,
                                                                        Long minCode) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getEmpWorkPermitsByCivilIdAndMinCode(civilId,minCode);
        
        
    }

    //@Auditable
    public IBasicDTO add(IRequestInfoDTO iRequestInfoDTO, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        IEmpWorkPermitsDTO dto = (IEmpWorkPermitsDTO)iBasicDTO;
        // check conflict
        
       Boolean result = DAO().checkConflictForworkPermits(dto.getCivilId(), dto.getFromDate(), dto.getUntilDate(),null);
       if(result){
           throw new EmpWorkPermitsConflictException();
           
       }
        
        return super.add(iRequestInfoDTO, dto);
    }
    //@Auditable
    public Boolean update(IRequestInfoDTO iRequestInfoDTO, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        
        IEmpWorkPermitsDTO dto = (IEmpWorkPermitsDTO)iBasicDTO;
        // check conflict
        Long serial = Long.valueOf(dto.getCode().getKey());
        
        Boolean result = DAO().checkConflictForworkPermits(dto.getCivilId(), dto.getFromDate(), dto.getUntilDate(),serial);
        if(result){
           throw new EmpWorkPermitsConflictException();
           
        }
        return super.update(iRequestInfoDTO, dto);
    }
}
