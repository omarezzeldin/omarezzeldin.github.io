package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.IBasicClient;
import com.beshara.csc.hr.emp.business.dto.EmpMinEdtypesDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpMinEdtypesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.Collection;
import java.util.List;
 
 /**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public interface IEmpMinEdtypesClient extends IBasicClient {
   
   
    public List<IEmpMinEdtypesDTO> getByMinCode(Long minCode) throws DataBaseException, SharedApplicationException ; 
}
