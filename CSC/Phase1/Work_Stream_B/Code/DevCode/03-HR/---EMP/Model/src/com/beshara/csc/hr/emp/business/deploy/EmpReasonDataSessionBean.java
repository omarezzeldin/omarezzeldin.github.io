package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmpReasonDataDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpReasonDataDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpReasonDataEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpReasonDataEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Represents the Business Object Wrapper (as Session Bean ) for Business Component publishing.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author     Beshara Group
 * @author     Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "EmpReasonDataSession", mappedName = "Emp-Model-EmpReasonDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpReasonDataSessionBean extends EmpBaseSessionBean implements EmpReasonDataSession {

    /**
     * JobsSession Default Constructor */
    public EmpReasonDataSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpReasonDataEntity.class;
    }

    protected EmpReasonDataDAO DAO() {
        return (EmpReasonDataDAO)super.DAO();
    }

    public List<IBasicDTO> getByTypeCode(IRequestInfoDTO ri, Long restypeCode) throws SharedApplicationException,
                                                                                      DataBaseException {
        return DAO().getByTypeCode(restypeCode);
    }

    public List<IBasicDTO> getPublicReasons(IRequestInfoDTO ri, Long restypeCode) throws SharedApplicationException,
                                                                                         DataBaseException {
        return DAO().getPublicReasons(restypeCode);
    }

    public List<IBasicDTO> getSpecialReasons(IRequestInfoDTO ri, Long restypeCode,
                                             Long vactypeCode) throws RemoteException, SharedApplicationException,
                                                                      DataBaseException {
        return DAO().getSpecialReasons(restypeCode, vactypeCode);
    }

    public List<IBasicDTO> getSpecialReasonsForOthersVac(IRequestInfoDTO ri, Long restypeCode,
                                                         Long vactypeCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().getSpecialReasonsForOthersVac(restypeCode, vactypeCode);
    }

    public List<IBasicDTO> getEmpReasonsForVacType(IRequestInfoDTO ri, Long restypeCode,
                                                   Long vactypeCode) throws RemoteException,
                                                                            SharedApplicationException,
                                                                            DataBaseException {
        return DAO().getEmpReasonsForVacType(restypeCode, vactypeCode);
    }

    //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                         SharedApplicationException {

        IEmpReasonDataDTO empReasonDataDTO = (IEmpReasonDataDTO)iBasicDTO;
        Long restypeCode = empReasonDataDTO.getEmpReasonTypesCode();
        if (DAO().checkDublicateNameAndType(restypeCode, empReasonDataDTO.getName()).size() > 0) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        Long resdatSerial = DAO().queryEmpReasonDataEntityFindNewId(restypeCode);

        IEmpReasonDataEntityKey key = EmpEntityKeyFactory.createEmpReasonDataEntityKey(restypeCode, resdatSerial);

        empReasonDataDTO.setCode(key);

        return DAO().add(empReasonDataDTO);
    }

    //@Auditable
    public IBasicDTO addRejectionReason(IRequestInfoDTO ri, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        IEmpReasonDataDTO empReasonDataDTO = (IEmpReasonDataDTO)iBasicDTO;
        String name = empReasonDataDTO.getName();

        Long restypeCode = empReasonDataDTO.getEmpReasonTypesCode();
        Long resdatSerial = DAO().queryEmpReasonDataEntityFindNewId(restypeCode);

        List reasonList = DAO().checkDublicateNameAndType(restypeCode, name);

        if (reasonList.size() > 0) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        IEmpReasonDataEntityKey key = EmpEntityKeyFactory.createEmpReasonDataEntityKey(restypeCode, resdatSerial);
        empReasonDataDTO.setCode(key);
        return DAO().add(empReasonDataDTO);
    }
    //@Auditable
    public Boolean update(IRequestInfoDTO iRequestInfoDTO, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        IEmpReasonDataDTO empReasonDataDTO = (IEmpReasonDataDTO)iBasicDTO;
        String name = empReasonDataDTO.getName();
        Long restypeCode = empReasonDataDTO.getEmpReasonTypesCode();

        List<IBasicDTO> list = DAO().checkDublicateNameAndType(restypeCode, name);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).getCode().equals(iBasicDTO.getCode())) {
                    throw new DataBaseException("EnteredNameAlreadyExist");
                }
            }

        }

        return super.update(iRequestInfoDTO, iBasicDTO);
    }

    //@Auditable
    public int convertPrivateToGen(IRequestInfoDTO ri, IBasicDTO empReasonDataDTO1) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().convertPrivateToGen(empReasonDataDTO1);

    }
    //@Auditable
    public int proceedGenReasonAdd(IRequestInfoDTO ri, IBasicDTO empReasonDataDTO1) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().proceedGenReasonAdd(empReasonDataDTO1);

    }

    public Boolean isRejReasonUsedBefore(IRequestInfoDTO iRequestInfoDTO, Long resDatSerial) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().isRejReasonUsedBefore(resDatSerial);
    }

    @Override
    public Boolean isGenRejReasonRedundant(IRequestInfoDTO iRequestInfoDTO, String resDatDesc,
                                           Long resDatSerial) throws SharedApplicationException, DataBaseException {
        return DAO().isGenRejReasonRedundant(resDatDesc, resDatSerial);
    }

    @Override
    public Boolean isGenRejReasonRedundanInPrivateReasons(IRequestInfoDTO iRequestInfoDTO,
                                                          String resDatDesc) throws SharedApplicationException,
                                                                                    DataBaseException {
        return DAO().isGenRejReasonRedundanInPrivateReasons(resDatDesc);
    }

    @Override
    public Boolean isPrivateRejReasonRedundan(IRequestInfoDTO iRequestInfoDTO, String resDatDesc,
                                              Long resDatSerial) throws SharedApplicationException, DataBaseException {
        return DAO().isPrivateRejReasonRedundan(resDatDesc, resDatSerial);
    }

    //@Auditable
    public void removeItems(IRequestInfoDTO ri, IBasicDTO dto, IBasicDTO dto1) throws SharedApplicationException,
                                                                                      DataBaseException {
        try {
            if (isTransactionBegun()) {
                DAO().convertPrivateToGen(dto1);
                super.remove(ri, dto);
            } else {
                beginTransaction();
                DAO().convertPrivateToGen(dto);
                super.remove(ri, dto);
            }
            commitTransaction();
        } catch (TransactionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }

    }
}
