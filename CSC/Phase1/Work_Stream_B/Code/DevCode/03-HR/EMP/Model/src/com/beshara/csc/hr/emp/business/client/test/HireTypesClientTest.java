package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IHireTypesClient;
import com.beshara.csc.hr.emp.business.client.IRequiredDocumentsClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class HireTypesClientTest {
    IHireTypesClient client = EmpClientFactory.getHireTypesClient();

    /** 
     */
    public HireTypesClientTest() {
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        HireTypesClientTest test = new HireTypesClientTest();
        try { //test.add ( ) ; 
            test.update();
            //test.remove ( ) ; 
            //test.getAll ( ) ; 
            //test.searchByCode ( ) ; 
            //test.searchByName ( ) ; 
            //test.getCodeName ( ) ; 
            // test.getAvailableDocument ( ) ; 
            //test.getValidDocumentsForEmployee ( ) ; 
        } catch (SharedApplicationException e) {
            System.out.println("from client I.I.I.I " + e.getMessage());
        } catch (DataBaseException e) {
            System.out.println("from client I.I.I.I " + e.getMessage());
        }
    }

    public void add() throws DataBaseException, SharedApplicationException {
        IHireTypesDTO hireTypesDTO = EmpDTOFactory.createHireTypesDTO();
        hireTypesDTO.setName("TEST FOR ADD");
        List<IRequiredDocumentsDTO> documentsList = 
            new ArrayList<IRequiredDocumentsDTO>();
        IRequiredDocumentsDTO doc1 = 
            EmpDTOFactory.createRequiredDocumentsDTO();
        IInfDocumentTypesDTO docType1 = InfDTOFactory.createInfDocumentTypesDTO();
        docType1.setCode(InfEntityKeyFactory.createInfDocumentTypesEntityKey(1L));
        doc1.setDocumentTypeDTO(docType1);
        doc1.setBasicStatus(1L);
//        IGenderTypesDTO genderType = InfDTOFactory.createGenderTypesDTO();
//        genderType.setCode(InfEntityKeyFactory.createGenderTypesEntityKey(2L));
        doc1.setGenderType("1");
        doc1.setNationalityType(1L);
        IRequiredDocumentsDTO doc2 = 
            EmpDTOFactory.createRequiredDocumentsDTO();
        IInfDocumentTypesDTO docType2 = InfDTOFactory.createInfDocumentTypesDTO();
//        docType2.setCode(EmpEntityKeyFactory.createDocumentTypesEntityKey(2L));
        doc2.setDocumentTypeDTO(docType2);
        doc2.setBasicStatus(1L);
        doc2.setGenderType("1");
        doc2.setNationalityType(1L);
        documentsList.add(doc1);
        documentsList.add(doc2);
        hireTypesDTO.setRequiredDocumentsDTOList(documentsList);
        client.add(hireTypesDTO);
    }

    public void update() throws DataBaseException, SharedApplicationException {
        IHireTypesDTO hireTypesDTO = 
            (IHireTypesDTO)client.getById(EmpEntityKeyFactory.createHireTypesEntityKey(1L));
        //hireTypesDTO.setName ( "UPDATED RECORD" ) ; 
        List<IRequiredDocumentsDTO> docList = 
            hireTypesDTO.getRequiredDocumentsDTOList();
        // hireTypesDTO.getRequiredDocumentsDTOList ( ) ; 
        // 
        // IRequiredDocumentsDTO dto1 = docList.get ( 0 ) ; 
        // dto1.setNationalityType ( 2L ) ; 
        // 
        // IRequiredDocumentsDTO dto2 = docList.get ( 1 ) ; 
        // dto2.setStatusFlag ( ISystemConstant.DELEDTED_ITEM ) ; 
        IRequiredDocumentsDTO dto3 = 
            EmpDTOFactory.createRequiredDocumentsDTO();
        IInfDocumentTypesDTO docType = InfDTOFactory.createInfDocumentTypesDTO();
        docType.setCode(InfEntityKeyFactory.createInfDocumentTypesEntityKey(8L));
        dto3.setDocumentTypeDTO(docType);
        dto3.setBasicStatus(1L);
        IGenderTypesDTO genderType = InfDTOFactory.createGenderTypesDTO();
        genderType.setCode(InfEntityKeyFactory.createGenderTypesEntityKey(3L));
        dto3.setGenderType("2");
        dto3.setNationalityType(3L);
        dto3.setStatusFlag(ISystemConstant.ADDED_ITEM);
        docList.add(dto3);
        hireTypesDTO.setRequiredDocumentsDTOList(docList);
        client.update(hireTypesDTO);
    }

    public void remove() throws DataBaseException, SharedApplicationException {
        IHireTypesDTO hireTypesDTO = 
            (IHireTypesDTO)client.getById(EmpEntityKeyFactory.createHireTypesEntityKey(10L));
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        list.add(hireTypesDTO);
        List<IResultDTO> resultList = client.remove(list);
        for (IResultDTO result: resultList) {
            System.out.println(result.getStatus());
        }
    }

    public void getAll() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> resultList = client.getAll();
        for (IBasicDTO result: resultList) {
            System.out.println(result.getCode().getKey());
            System.out.println(result.getName());
        }
    }

    public void searchByCode() throws DataBaseException, 
                                      SharedApplicationException {
        List<IBasicDTO> resultList = client.searchByCode(3L);
        for (IBasicDTO result: resultList) {
            System.out.println(result.getCode().getKey());
            System.out.println(result.getName());
        }
    }

    public void searchByName() throws DataBaseException, 
                                      SharedApplicationException {
        List<IBasicDTO> resultList = client.searchByName("%");
        for (IBasicDTO result: resultList) {
            System.out.println(result.getCode().getKey());
            System.out.println(result.getName());
        }
    }

    public void getCodeName() throws DataBaseException, 
                                     SharedApplicationException {
        List resultList = client.getCodeName();
        for (IHireTypesDTO result: (List<IHireTypesDTO>)resultList) {
            System.out.println(result.getCode().getKey());
            System.out.println(result.getName());
        }
    }

    public void getAvailableDocument() throws DataBaseException, 
                                              SharedApplicationException {
        IRequiredDocumentsClient docClient = 
            EmpClientFactory.getRequiredDocumentsClient();
        List<IInfDocumentTypesDTO> list = 
            docClient.getAvailableDocuments(EmpEntityKeyFactory.createHireTypesEntityKey(8L));
        for (IInfDocumentTypesDTO result: list) {
            System.out.println(result.getCode().getKey());
            System.out.println(result.getName());
        }
    }

    public void getValidDocumentsForEmployee() throws DataBaseException, 
                                                      SharedApplicationException {
        List<IBasicDTO> resultList = 
            client.getAll(); //client.getValidDocumentsForEmployee ( 1L , 1L , 2L ) ; 
        for (IBasicDTO result: resultList) {
            System.out.println(result.getCode().getKey());
            System.out.println(result.getName());
        }
    }
}
