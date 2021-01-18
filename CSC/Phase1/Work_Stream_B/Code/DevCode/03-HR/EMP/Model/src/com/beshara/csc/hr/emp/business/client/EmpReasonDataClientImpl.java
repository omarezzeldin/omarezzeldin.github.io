package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.csc.hr.emp.business.deploy.EmpReasonDataSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Implements a specified Client Interface as Session Bean
 * and Generated through the Client Factory Class Based on the Key Returned from the Properties File .
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
 * @author       Beshara Group
 * @author       Ahmed Sabry, Taha El-Fitiany, Sherif El-Rabbat
 * @version      1.0
 * @since        03/09/2007
 */

public class EmpReasonDataClientImpl extends BaseClientImpl3 implements IEmpReasonDataClient {

    /**
     * @param contextSession
     */
    public EmpReasonDataClientImpl() {
        super();
    }


    public List<IBasicDTO> getPublicReasons(Long restypeCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getPublicReasons(RI(), restypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getSpecialReasons(Long restypeCode, Long vactypeCode) throws SharedApplicationException,
                                                                                        DataBaseException {
        try {
            return SESSION().getSpecialReasons(RI(), restypeCode, vactypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getSpecialReasonsForOthersVac(Long restypeCode,
                                                         Long vactypeCode) throws SharedApplicationException,
                                                                                  DataBaseException {
        try {
            return SESSION().getSpecialReasonsForOthersVac(RI(), restypeCode, vactypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public List<IBasicDTO> getByTypeCode(Long restypeCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getByTypeCode(RI(), restypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpReasonDataSession.class;
    }


    public List<IBasicDTO> getEmpReasonsForVacType(Long restypeCode, Long vactypeCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            return SESSION().getEmpReasonsForVacType(RI(), restypeCode, vactypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    protected EmpReasonDataSession SESSION() {
        try {
            return (EmpReasonDataSession)super.SESSION();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public int convertPrivateToGen(IBasicDTO empReasonDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().convertPrivateToGen(RI(), empReasonDataDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public int proceedGenReasonAdd(IBasicDTO empReasonDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().proceedGenReasonAdd(RI(), empReasonDataDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean isRejReasonUsedBefore(Long resDatSerial) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().isRejReasonUsedBefore(RI(), resDatSerial);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    @Override
    public Boolean isGenRejReasonRedundant(String resDatDesc, Long resDatSerial) throws SharedApplicationException,
                                                                                        DataBaseException {
        try {
            return SESSION().isGenRejReasonRedundant(RI(), resDatDesc, resDatSerial);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    @Override
    public Boolean isGenRejReasonRedundanInPrivateReasons(String resDatDesc) throws SharedApplicationException,
                                                                                    DataBaseException {
        try {
            return SESSION().isGenRejReasonRedundanInPrivateReasons(RI(), resDatDesc);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    @Override
    public Boolean isPrivateRejReasonRedundan(String resDatDesc, Long resDatSerial) throws SharedApplicationException,
                                                                                           DataBaseException {
        try {
            return SESSION().isPrivateRejReasonRedundan(RI(), resDatDesc, resDatSerial);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public void removeItems(IBasicDTO dto, IBasicDTO dto1) throws SharedApplicationException, DataBaseException {
        try {
            SESSION().removeItems(RI(), dto, dto1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IResultDTO> remove(List<IBasicDTO> list) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().remove(RI(), list);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO addRejectionReason(IBasicDTO iBasicDTO) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().addRejectionReason(RI(), iBasicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

}
