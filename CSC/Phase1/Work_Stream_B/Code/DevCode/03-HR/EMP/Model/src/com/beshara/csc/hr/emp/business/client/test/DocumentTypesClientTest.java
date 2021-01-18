//package com.beshara.csc.hr.emp.business.client.test;
//
//
//import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
//import com.beshara.csc.hr.emp.business.client.IDocumentTypesClient;
//import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
//import com.beshara.csc.hr.emp.business.dto.IDocumentTypesDTO;
//import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
//import com.beshara.csc.sharedutils.business.exception.DataBaseException;
//import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
//
//import java.util.List;
//
//
///**
// */
//public class DocumentTypesClientTest {
//    /** 
//     */
//    IDocumentTypesClient documentTypesClient;
//
//    public DocumentTypesClientTest() {
//        documentTypesClient = EmpClientFactory.getDocumentTypesClient();
//    }
//
//    /** 
//     * @param args 
//     */
//    public static void main(String[] args) {
//        DocumentTypesClientTest test = new DocumentTypesClientTest();
//        //test.getAll ( ) ; 
//        test.searchByName();
//    }
//
//    public void getAll() {
//        try {
//            List<IDocumentTypesDTO> list = 
//                (List)documentTypesClient.getAll();
//            System.out.println("ttttt" + list);
//        } catch (SharedApplicationException e) { // TODO 
//        } catch (DataBaseException e) { // TODO 
//        }
//    }
//
//    public void getCodeName() {
//        List<IDocumentTypesDTO> list;
//        try {
//            list = (List)documentTypesClient.getCodeName();
//            System.out.println("ttttt" + list);
//        } catch (DataBaseException e) { // TODO 
//        }
//    }
//
//    public void searchByName() {
//        List<IDocumentTypesDTO> list;
//        try {
//            list = 
//(List)documentTypesClient.searchByCode(2L);
//            System.out.println("ttttt" + list);
//        } catch (DataBaseException e) { // TODO 
//        } catch (SharedApplicationException e) { // TODO 
//        }
//    }
//
//    public void add() {
//        IDocumentTypesDTO dto = EmpDTOFactory.createDocumentTypesDTO();
//        dto.setName("test");
//        //dto.setCode ( EmpEntityKeyFactory.createDocumentTypesEntityKey ( 20L ) ) ; 
//        IDocumentTypesDTO dto1;
//        try {
//            dto1 = (IDocumentTypesDTO)documentTypesClient.add(dto);
//        } catch (SharedApplicationException e) { // TODO 
//        } catch (DataBaseException e) { // TODO 
//        }
//    }
//
//    public void update() {
//        try {
//            IDocumentTypesDTO dto = 
//                (IDocumentTypesDTO)documentTypesClient.getById(EmpEntityKeyFactory.createDocumentTypesEntityKey(8L));
//            dto.setName("test2");
//            //dto.setCode ( EmpEntityKeyFactory.createDocumentTypesEntityKey ( 20L ) ) ; 
//            documentTypesClient.update(dto);
//        } catch (SharedApplicationException e) { // TODO 
//        } catch (DataBaseException e) { // TODO 
//        }
//    }
//}
