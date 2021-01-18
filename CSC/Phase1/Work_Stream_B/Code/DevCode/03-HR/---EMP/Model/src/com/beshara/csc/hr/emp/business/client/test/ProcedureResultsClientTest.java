package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IProcedureResultsClient;
import com.beshara.csc.hr.emp.business.deploy.ProcedureResultsSession;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IProcedureResultsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ServiceLocator;

import javax.naming.NamingException;


/**
 */
public class ProcedureResultsClientTest {
    /** 
     */
    public ProcedureResultsClientTest() {
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        IProcedureResultsClient procedureResultsClient = 
            EmpClientFactory.getProcedureResultsClient();
        try { /*
 * Use ServiceLocator to Lookup Session Remote and make the appropriate Process * */
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            ProcedureResultsSession procedureResultsSession = 
                (ProcedureResultsSession)serviceLocator.lookup("ProcedureResultsSession", 
                                                               ProcedureResultsSession.class);
            IProcedureResultsDTO procedureResultsDTO = 
                EmpDTOFactory.createProcedureResultsDTO();
            // Add your set values here >> 
            try {
                procedureResultsDTO = 
                        (IProcedureResultsDTO)procedureResultsClient.add(procedureResultsDTO);
            } catch (SharedApplicationException e) {
                System.out.println("from client I.I.I.I " + e.getMessage());
            } catch (DataBaseException e) {
                System.out.println("from client I.I.I.I " + e.getMessage());
            }
        } catch (NamingException e) {
        }
    }
}
