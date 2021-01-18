package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireStagesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;


/**
 */
public class HireStagesClientTest {
    /** 
     */
    IHireStagesClient hireStagesClient;

    public HireStagesClientTest() {
        hireStagesClient = EmpClientFactory.getHireStagesClient();
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        HireStagesClientTest client = new HireStagesClientTest();
        client.getAll();
        // client.add ( ) ; 
        // client.remove ( ) ; 
        //client.searchByCode ( ) ; 
        //client.searchByName ( ) ; 
    }

    void add() {
        IHireStagesDTO dto = EmpDTOFactory.createHireStagesDTO();
        dto.setName("TEST ADD BY TAHA ABDUL MEJID");
        try {
            System.out.println(hireStagesClient.add(dto).getName());
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void getAll() {
        try {
            for (IBasicDTO basic: hireStagesClient.getAll()) {
                System.err.println(basic.getCode().getKey());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void getCodeName() {
        try {
            for (IBasicDTO basic: hireStagesClient.getCodeName()) {
                System.out.println(basic.getCode().getKey());
            }
        } catch (DataBaseException e) { // TODO 
        }
    }

    void update() {
        try {
            IHireStagesDTO dto = 
                (IHireStagesDTO)hireStagesClient.getById(EmpEntityKeyFactory.createHireStagesEntityKey(5L));
            dto.setName("TEST UPDATE BY TAHA ABDUL MEJID");
            System.out.println(hireStagesClient.update(dto));
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void remove() {
        try {
            IHireStagesDTO dto = 
                (IHireStagesDTO)hireStagesClient.getById(EmpEntityKeyFactory.createHireStagesEntityKey(5L));
            ArrayList list = new ArrayList();
            list.add(dto);
            System.out.println(hireStagesClient.remove(list));
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void searchByName() {
        try {
            for (IBasicDTO basic: hireStagesClient.searchByName("%ADD%")) {
                System.err.println(basic.getCode().getKey());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void searchByCode() {
        try {
            for (IBasicDTO basic: hireStagesClient.searchByCode(1L)) {
                System.err.println(basic.getCode().getKey());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }
}
