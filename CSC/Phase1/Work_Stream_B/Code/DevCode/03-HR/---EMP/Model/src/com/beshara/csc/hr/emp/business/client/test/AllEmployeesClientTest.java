package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IAllEmployeesClient;
import com.beshara.csc.hr.emp.business.deploy.AllEmployeesSession;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAllEmployeesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ServiceLocator;

import javax.naming.NamingException;


/**
 */
public class AllEmployeesClientTest {
    /** 
     */
    public AllEmployeesClientTest() {
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        IAllEmployeesClient allEmployeesClient = 
            EmpClientFactory.getAllEmployeesClient();
        try { /*
 * Use ServiceLocator to Lookup Session Remote and make the appropriate Process * */
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            AllEmployeesSession allEmployeesSession = 
                (AllEmployeesSession)serviceLocator.lookup("AllEmployeesSession", 
                                                           AllEmployeesSession.class);
            IAllEmployeesDTO allEmployeesDTO = 
                EmpDTOFactory.createAllEmployeesDTO();
            // Add your set values here >> 
            try {
                allEmployeesDTO = 
                        (IAllEmployeesDTO)allEmployeesClient.add(allEmployeesDTO);
            } catch (SharedApplicationException e) {
                System.out.println("from client I.I.I.I " + e.getMessage());
            } catch (DataBaseException e) {
                System.out.println("from client I.I.I.I " + e.getMessage());
            }
        } catch (NamingException e) {
        }
    }
}
