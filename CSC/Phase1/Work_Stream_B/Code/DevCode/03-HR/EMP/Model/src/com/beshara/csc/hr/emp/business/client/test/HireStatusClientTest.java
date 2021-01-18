package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireStatusClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireStatusDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;


/**
 */
public class HireStatusClientTest {
    /** 
     */
    IHireStatusClient hireStatusClient;

    public HireStatusClientTest() {
        hireStatusClient = EmpClientFactory.getHireStatusClient();
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        HireStatusClientTest client = new HireStatusClientTest();
        client.getAll();
        client.add();
        //client.remove ( ) ; 
        client.searchByCode();
        client.searchByName();
    }

    void getAll() {
        try {
            for (IBasicDTO basic: hireStatusClient.getAll()) {
                System.err.println(basic.getCode().getKey());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void getCodeName() {
        try {
            for (IBasicDTO basic: hireStatusClient.getCodeName()) {
                System.out.println(basic.getCode().getKey());
            }
        } catch (DataBaseException e) { // TODO 
        }
    }

    void add() {
        IHireStatusDTO dto = EmpDTOFactory.createHireStatusDTO();
        dto.setName("TEST ADD BY TAHA ABDUL MEJID");
        try {
            System.out.println(hireStatusClient.add(dto).getName());
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void update() {
        try {
            IHireStatusDTO dto = 
                (IHireStatusDTO)hireStatusClient.getById(EmpEntityKeyFactory.createHireStatusEntityKey(11L));
            dto.setName("TEST UPDATE BY TAHA ABDUL MEJID");
            System.out.println(hireStatusClient.update(dto));
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void remove() {
        try {
            IHireStatusDTO dto = 
                (IHireStatusDTO)hireStatusClient.getById(EmpEntityKeyFactory.createHireStatusEntityKey(10L));
            ArrayList list = new ArrayList();
            list.add(dto);
            System.out.println(hireStatusClient.remove(list));
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void searchByName() {
        try {
            for (IBasicDTO basic: hireStatusClient.searchByName("%ADD%")) {
                System.err.println(basic.getCode().getKey());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void searchByCode() {
        try {
            for (IBasicDTO basic: hireStatusClient.searchByCode(1L)) {
                System.err.println(basic.getCode().getKey());
            }
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }
}
