package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireProceduresClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.ISearchHireProceduresDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class HireProceduresClientTest {
    IHireProceduresClient client = EmpClientFactory.getHireProceduresClient();

    /** 
     */
    public HireProceduresClientTest() {
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        HireProceduresClientTest test = new HireProceduresClientTest();
        try {
            test.add();
            //test.update ( ) ; 
            //test.remove ( ) ; 
            //test.getAll ( ) ; 
            //test.getCodeName ( ) ; 
            // test.search ( ) ; 
            // test.getValidHireProceduresForEmployee ( ) ; 
        } catch (SharedApplicationException e) {
            System.out.println("from client I.I.I.I " + e.getMessage());
        } catch (DataBaseException e) {
            System.out.println("from client I.I.I.I " + e.getMessage());
        }
    }

    public void add() throws DataBaseException, SharedApplicationException {
        IHireProceduresDTO hireProceduresDTO = 
            EmpDTOFactory.createHireProceduresDTO();
        hireProceduresDTO.setName("TEST HIRE PROCEDURE ADD2");
        IMinistriesDTO minDTO = OrgDTOFactory.createMinistriesDTO();
        minDTO.setCode(OrgEntityKeyFactory.createMinistriesEntityKey(26L));
        //hireProceduresDTO.setMinistriesDTO(minDTO);
//        hireProceduresDTO.setNationalityType(3L);
//        IGenderTypesDTO genderDTO = InfDTOFactory.createGenderTypesDTO();
//        genderDTO.setCode(InfEntityKeyFactory.createGenderTypesEntityKey(3L));
        //hireProceduresDTO.setGenderTypesDTO(genderDTO);
        hireProceduresDTO = (IHireProceduresDTO)client.add(hireProceduresDTO);
    }

    public void update() throws DataBaseException, SharedApplicationException {
        IHireProceduresDTO hireProceduresDTO = 
            (IHireProceduresDTO)client.getById(EmpEntityKeyFactory.createHireProceduresEntityKey(3L));
        hireProceduresDTO.setName("TEST HIRE PROCEDURE UPDATE");
        client.update(hireProceduresDTO);
    }

    public void remove() throws DataBaseException, SharedApplicationException {
        IHireProceduresDTO hireProceduresDTO1 = 
            (IHireProceduresDTO)client.getById(EmpEntityKeyFactory.createHireProceduresEntityKey(3L));
        IHireProceduresDTO hireProceduresDTO2 = 
            (IHireProceduresDTO)client.getById(EmpEntityKeyFactory.createHireProceduresEntityKey(4L));
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        list.add(hireProceduresDTO1);
        list.add(hireProceduresDTO2);
        List<IResultDTO> resultList = client.remove(list);
        for (IResultDTO result: resultList) {
            System.out.println(result.getStatus());
        }
    }

    public void getAll() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> list = client.getAll();
        for (IBasicDTO dto: list) {
            System.out.println(dto.getCode().getKey());
            System.out.println(dto.getName());
        }
    }

    public void getCodeName() throws DataBaseException, 
                                     SharedApplicationException {
        List<IBasicDTO> list = client.getCodeName();
        for (IBasicDTO dto: list) {
            System.out.println(dto.getCode().getKey());
            System.out.println(dto.getName());
        }
    }

    public void getValidHireProceduresForEmployee() throws DataBaseException, 
                                                           SharedApplicationException {
        List<IBasicDTO> list = 
            client.getAll(); //client.getValidHireProceduresForEmployee ( 1L , 1L ) ; 
        for (IBasicDTO dto: list) {
            System.out.println(dto.getCode().getKey());
        }
    }

    public void search() throws DataBaseException, SharedApplicationException {
        ISearchHireProceduresDTO searchDTO = 
            EmpDTOFactory.createSearchHireProceduresDTO();
        searchDTO.setHirprocedureCode(2L);
        searchDTO.setMinCode(26L);
        searchDTO.setGenderType(2L);
        searchDTO.setNationalityType(3L);
        List<IBasicDTO> list = client.search(searchDTO);
        for (IBasicDTO dto: list) {
            System.out.println(dto.getCode().getKey());
            System.out.println(dto.getName());
        }
    }
}
