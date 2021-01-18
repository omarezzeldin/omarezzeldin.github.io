package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.impl.PagingRequestDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.client.IEmpEmployeeHistoriesClient;
import com.beshara.csc.hr.emp.business.client.IEmployeesClient;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


/**
 */
public class EmployeesClientTest {
    IEmployeesClient client = EmpClientFactory.getEmployeesClient();
    IEmpEmployeeHistoriesClient historyClient;

    public EmployeesClientTest() {
    }    

    /** 
     * @param args 
     */
    public static void main(String[] args) {

        EmployeesClientTest test = new EmployeesClientTest();

        //        //test.searchEmployeeForSalCalc ( ) ; 
        //        //test.searchEmployeeForSalCalcCancel ( ) ; 
        //        // test.getAllEmployeeWaitingForHireDecision ( ) ; 
        //        // test.isEmployeeExist ( ) ; 
        //        // test.isMinistryFileNoExist ( ) ; 
        //        // test.searchBasic ( ) ; 
        //        // test.getFilterEmployeeWaitingForHireDecision ( ) ; 
        //        // test.add ( ) ; 
                try {
        test.getEmployeeInfoByElmType();
        //            test.getEmployeeDataByCivil();
        //            test.getFirstHireDate();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } // test.addDecision ( ) ; 

        try {
            //System.out.println( EmpClientFactoryOld.getEmployeeExtraDataClient().getByWorkCenterForSickVac(OrgEntityKeyFactory.createWorkCentersEntityKey(13L,"021332")).size());
            //             IEmployeeExtraDataDTO employeeExtraDataDTO = EmpDTOFactory.createEmployeeExtraDataDTO();
            //             employeeExtraDataDTO.setCode( EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(1L));
            //            employeeExtraDataDTO.setValue("1");
            //             EmpClientFactoryOld.getEmployeeExtraDataClient().updateExtraDataValue(employeeExtraDataDTO);
            //             

            /*IEmployeeExtraDataSearchDTO searchDTO =
                EmpDTOFactory.createEmployeeExtraDataSearchDTO();
            searchDTO.setCivilId(231122800071L);
            searchDTO.setExtraDataTypeCode(1L);
            System.out.println(((IEmployeeExtraDataDTO)(EmpClientFactoryOld.getEmployeeExtraDataClient().searchForSickVac(searchDTO).get(0))).getValue());*/
            test.getByHireStageWithPaging();
            
            test.getByRealCivilId(222222222222l, 13l);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("END");
    }
    
    public String getTest() {
        try {
            IEmployeesDTO employeeDTO = getByRealCivilId(275071601383L ,13l);
                return employeeDTO.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "zeft";
            }    
    }

    void searchEmployeeForSalCalc() {
        IEmpEmployeeSearchDTO searchDTO = 
            EmpDTOFactory.createEmpEmployeeSearchDTO();
        searchDTO.setMinistryCode(13L);
        searchDTO.setKuwaiti(true);
        //searchDTO.setCivilId ( 249010400028L ) ; 
        searchDTO.setBgtProgramsCode("51");
        //searchDTO.setBgtTypesCode ( 2L ) ; 
        //searchDTO.setWorkCenterCode ( "02131" ) ; 
        searchDTO.setKaderCode(108L);
        searchDTO.setYearCode(2009L);
        searchDTO.setMonthCode(4L);
        try {
            List<IBasicDTO> data = client.searchEmployeeForSalCalc(searchDTO);
            System.out.println("->>>>>>>>>>>> '" + data.size() + 
                               "' Item Found");
            for (IBasicDTO dto: data) {
                System.out.println(dto.getCode().getKey());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    void searchEmployeeForSalCalcCancel() {
        IEmpEmployeeSearchDTO searchDTO = 
            EmpDTOFactory.createEmpEmployeeSearchDTO();
        searchDTO.setMinistryCode(13L);
        searchDTO.setKuwaiti(true);
        searchDTO.setHasCalculatedMonSalary(true);
        searchDTO.setCivilId(258070100195L);
        //searchDTO.setBgtProgramsCode ( "51" ) ; 
        //searchDTO.setBgtTypesCode ( 2L ) ; 
        //searchDTO.setWorkCenterCode ( "02131" ) ; 
        //searchDTO.setKaderCode ( 108L ) ; 
        searchDTO.setYearCode(2009L);
        searchDTO.setMonthCode(4L);
        try {
            List<IBasicDTO> data = client.searchEmployeeForSalCalc(searchDTO);
            System.out.println("->>>>>>>>>>>> '" + data.size() + 
                               "' Item Found");
            for (IBasicDTO dto: data) {
                System.out.println(dto.getCode().getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
   

    // void addDecision ( ) { 
    // try { 
    // IDecisionsDTO dec = 
    // ( IDecisionsDTO ) decisionClient.getById ( RegEntityKeyFactory.createDecisionsEntityKey ( 1L , 1L , 1L ) ) ; 
    // dec.setDecisionNumber ( 159951L ) ; 
    // IEmployeesDTO emp = 
    // ( IEmployeesDTO ) client.getById ( EmpEntityKeyFactory.createEmployeesEntityKey ( 264102000772L ) ) ; 
    // List lis = new ArrayList ( ) ; 
    // lis.add ( emp ) ; 
    // System.out.println ( client.addDecision ( lis , dec ) ) ; 
    // } catch ( Exception e ) { 
    // // TODO } 
    // } 
    // } 
    // 
    // void getAllToStartWork ( ) { 
    // try { 
    // //210010100027 
    // 
    // for ( IBasicDTO dto: client.getAllToStartWork ( ) ) 
    // System.out.println ( dto.getCode ( ) .getKey ( ) ) ; 
    // } catch ( Exception e ) { 
    // // TODO } 
    // } 
    // } 
    // 
    // void searchEmployeeStartWork ( ) { 
    // try { 
    // //210010100027 
    // 
    // for ( IBasicDTO dto: client.searchEmployeeStartWork ( null , "% %" ) ) 
    // System.out.println ( dto.getCode ( ) .getKey ( ) ) ; 
    // } catch ( Exception e ) { 
    // // TODO } 
    // } 
    // } 
    // 
    // void executeStartWork ( ) { 
    // try { 
    // //210010100027 
    // IEmployeesDTO dto = 
    // ( IEmployeesDTO ) client.getById ( EmpEntityKeyFactory.createEmployeesEntityKey ( 210010100027L ) ) ; 
    // dto.setStartWorkingDate ( SharedUtils.getCurrentDate ( ) ) ; 
    // System.out.println ( client.executeStartWork ( dto ) ) ; 
    // } catch ( Exception e ) { 
    // // TODO } 
    // } 
    // } 
    // 
    // void add ( ) { 
    // try { 
    // IJobsClient jobclient = JobClientFactory.getJobsClient ( ) ; 
    // IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO ( ) ; 
    // IKwtCitizensResidentsDTO citizen = InfDTOFactory.createKwtCitizensResidentsDTO ( ) ; 
    // citizen.setCode ( InfEntityKeyFactory.createKwtCitizensResidentsEntityKey ( 280120601782L ) ) ; 
    // //dto.setCode ( EmpEntityKeyFactory.createEmployeesEntityKey ( 281080301244L ) ) ; 
    // IJobsDTO job ; 
    // job = ( IJobsDTO ) jobclient.getById ( JobEntityKeyFactory.createJobsEntityKey ( "2" ) ) ; 
    // //job.setCode ( JobEntityKeyFactory.createJobsEntityKey ( "2" ) ) ; 
    // IJobsDTO tecjob = 
    // ( IJobsDTO ) jobclient.getById ( JobEntityKeyFactory.createJobsEntityKey ( "1" ) ) ; 
    // //tecjob.setCode ( JobEntityKeyFactory.createJobsEntityKey ( "2" ) ) ; 
    // IHireStagesDTO hstage = EmpDTOFactory.createHireStagesDTO ( ) ; 
    // hstage.setCode ( EmpEntityKeyFactory.createHireStagesEntityKey ( 1L ) ) ; 
    // IHireStatusDTO hstatus = EmpDTOFactory.createHireStatusDTO ( ) ; 
    // hstatus.setCode ( EmpEntityKeyFactory.createHireStatusEntityKey ( 1L ) ) ; 
    // IHireTypesDTO htype = EmpDTOFactory.createHireTypesDTO ( ) ; 
    // htype.setCode ( EmpEntityKeyFactory.createHireTypesEntityKey ( 21L ) ) ; 
    // IWorkCentersDTO wrk = OrgDTOFactory.createWorkCentersDTO ( ) ; 
    // wrk.setCode ( OrgEntityKeyFactory.createWorkCentersEntityKey ( 14L , "021398" ) ) ; 
    // dto.setWorkCenterDTO ( wrk ) ; 
    // dto.setCitizensResidentsDTO ( citizen ) ; 
    // dto.setJobDTO ( job ) ; 
    // dto.setTechJobsDTO ( tecjob ) ; 
    // dto.setHireStageDTO ( hstage ) ; 
    // dto.setHireStatusDTO ( hstatus ) ; 
    // dto.setHireTypeDTO ( htype ) ; 
    // // dto.setCscFileNo ( "466" ) ; 
    // dto.setMinistryFileNo ( "17/17" ) ; 
    // dto.setHireDate ( Date.valueOf ( "2008-7-1" ) ) ; 
    // dto.setStartWorkingDate ( Date.valueOf ( "2008-7-2" ) ) ; 
    // //SET LIST 
    // IEmployeeProceduresDTO proDTO = EmpDTOFactory.createEmployeeProceduresDTO ( ) ; 
    // proDTO.setSendDate ( Date.valueOf ( "2008-5-5" ) ) ; 
    // IProcedureResultsDTO re = EmpDTOFactory.createProcedureResultsDTO ( ) ; 
    // re.setCode ( EmpEntityKeyFactory.createProcedureResultsEntityKey ( IEMPConstant.EMP_RESULT_GOOD ) ) ; 
    // proDTO.setProcedureResultsDTO ( re ) ; 
    // IHireProceduresDTO hirp = EmpDTOFactory.createHireProceduresDTO ( ) ; 
    // hirp.setCode ( EmpEntityKeyFactory.createHireProceduresEntityKey ( 1L ) ) ; 
    // proDTO.setHireProcedureDTO ( hirp ) ; 
    // proDTO.setEmployeesDTO ( dto ) ; 
    // // 
    // IEmployeeProceduresDTO proDTO1 = EmpDTOFactory.createEmployeeProceduresDTO ( ) ; 
    // proDTO1.setSendDate ( Date.valueOf ( "2008-5-5" ) ) ; 
    // proDTO1.setProcedureResultsDTO ( re ) ; 
    // IHireProceduresDTO hirp1 = EmpDTOFactory.createHireProceduresDTO ( ) ; 
    // hirp1.setCode ( EmpEntityKeyFactory.createHireProceduresEntityKey ( 2L ) ) ; 
    // proDTO1.setHireProcedureDTO ( hirp1 ) ; 
    // proDTO1.setEmployeesDTO ( dto ) ; 
    // ///////// 
    // IEmployeeDocumentsDTO empdo = EmpDTOFactory.createEmployeeDocumentsDTO ( ) ; 
    // empdo.setStatus ( 1L ) ; 
    // empdo.setEmployeesDTO ( dto ) ; 
    // IDocumentTypesDTO docty = SrvDTOFactory.createDocumentTypesDTO ( ) ; 
    // docty.setCode ( EmpEntityKeyFactory.createDocumentTypesEntityKey ( 1L ) ) ; 
    // empdo.setDocumentTypeDTO ( docty ) ; 
    // // 
    // IEmployeeDocumentsDTO empdo1 = EmpDTOFactory.createEmployeeDocumentsDTO ( ) ; 
    // empdo1.setStatus ( 0L ) ; 
    // empdo1.setEmployeesDTO ( dto ) ; 
    // IDocumentTypesDTO docty1 = SrvDTOFactory.createDocumentTypesDTO ( ) ; 
    // docty1.setCode ( EmpEntityKeyFactory.createDocumentTypesEntityKey ( 2L ) ) ; 
    // empdo1.setDocumentTypeDTO ( docty1 ) ; 
    // // 
    // IEmployeeDocumentsDTO empdo2 = EmpDTOFactory.createEmployeeDocumentsDTO ( ) ; 
    // empdo2.setStatus ( 0L ) ; 
    // empdo2.setEmployeesDTO ( dto ) ; 
    // IDocumentTypesDTO docty2 = SrvDTOFactory.createDocumentTypesDTO ( ) ; 
    // docty2.setCode ( EmpEntityKeyFactory.createDocumentTypesEntityKey ( 3L ) ) ; 
    // empdo2.setDocumentTypeDTO ( docty2 ) ; 
    // // 
    // IEmployeeDocumentsDTO empdo3 = EmpDTOFactory.createEmployeeDocumentsDTO ( ) ; 
    // empdo3.setStatus ( 1L ) ; 
    // empdo3.setEmployeesDTO ( dto ) ; 
    // IDocumentTypesDTO docty3 = SrvDTOFactory.createDocumentTypesDTO ( ) ; 
    // docty3.setCode ( EmpEntityKeyFactory.createDocumentTypesEntityKey ( 4L ) ) ; 
    // empdo3.setDocumentTypeDTO ( docty3 ) ; 
    // // 
    // IEmployeeDocumentsDTO empdo4 = EmpDTOFactory.createEmployeeDocumentsDTO ( ) ; 
    // empdo4.setStatus ( 1L ) ; 
    // empdo4.setEmployeesDTO ( dto ) ; 
    // IDocumentTypesDTO docty4 = SrvDTOFactory.createDocumentTypesDTO ( ) ; 
    // docty4.setCode ( EmpEntityKeyFactory.createDocumentTypesEntityKey ( 5L ) ) ; 
    // empdo4.setDocumentTypeDTO ( docty4 ) ; 
    // //list added 
    // List docList = new ArrayList ( ) ; 
    // docList.add ( empdo ) ; 
    // docList.add ( empdo1 ) ; 
    // docList.add ( empdo2 ) ; 
    // docList.add ( empdo3 ) ; 
    // docList.add ( empdo4 ) ; 
    // List proList = new ArrayList ( ) ; 
    // proList.add ( proDTO ) ; 
    // proList.add ( proDTO1 ) ; 
    // /// 
    // //dto.setEmployeeDocumentsDTOList ( docList ) ; 
    // //dto.setEmployeeProceduresDTOList ( proList ) ; 
    // //dto.setDateOfNextRaise ( Date.valueOf ( "2009-7-1" ) ) ; 
    // System.out.println ( employeesClient.add ( dto , 
    // true ) .getCode ( ) .getKey ( ) ) ; 
    // } catch ( SharedApplicationException e ) { 
    // ; 
    // } catch ( DataBaseException e ) { 
    // ; 
    // } 
    // } 
    // 
    // void update ( ) { 
    // try { 
    // IEmployeesDTO dto = 
    // ( IEmployeesDTO ) employeesClient.getById ( EmpEntityKeyFactory.createEmployeesEntityKey ( 278110300246L ) ) ; 
    // 
    // System.out.println ( employeesClient.update ( dto ) ) ; 
    // } catch ( SharedApplicationException e ) { 
    // // TODO 
    // } catch ( DataBaseException e ) { 
    // // TODO 
    // } 
    // } 
    // 
    // public void getAll ( ) { 
    // 
    // 
    // try { 
    // List<IEmployeesDTO> list = 
    // ( List<IEmployeesDTO> ) employeesClient.getAll ( ) ; 
    // 
    // System.out.println ( "" + list ) ; 
    // } catch ( SharedApplicationException e ) { 
    // // TODO 
    // } catch ( DataBaseException e ) { 
    // // TODO 
    // } 
    // } 
    // 
    // public void getAllEmployeeWaitingForHireDecision ( ) { 
    // 
    // 
    // try { 
    // List<IBasicDTO> list = 
    // employeesClient.getAllEmployeeWaitingForHireDecision ( ) ; 
    // 
    // for ( IBasicDTO basic: list ) 
    // System.out.println ( basic.getCode ( ) .getKey ( ) ) ; 
    // } catch ( SharedApplicationException e ) { 
    // // TODO 
    // } catch ( DataBaseException e ) { 
    // // TODO 
    // } 
    // } 
    // 
    // public void sendProsedure ( ) { 
    // try { 
    // List<IBasicDTO> list = 
    // employeesClient.getAllEmployeeWaitingForHireDecision ( ) ; 
    // IHireProceduresClient pro = 
    // EmpClientFactory.getHireProceduresClient ( ) ; 
    // IHireProceduresDTO hire = 
    // ( IHireProceduresDTO ) pro.getById ( EmpEntityKeyFactory.createHireProceduresEntityKey ( 1L ) ) ; 
    // List<IBasicDTO> l1 = new ArrayList ( ) ; 
    // IEmployeeProceduresDTO dto = EmpDTOFactory.createEmployeeProceduresDTO ( ) ; 
    // dto.setEmployeesDTO ( list.get ( 0 ) ) ; 
    // dto.setHireProcedureDTO ( hire ) ; 
    // dto.setSendDate ( Date.valueOf ( "2008-10-10" ) ) ; 
    // IProcedureResultsDTO r = EmpDTOFactory.createProcedureResultsDTO ( ) ; 
    // r.setCode ( EmpEntityKeyFactory.createProcedureResultsEntityKey ( 1L ) ) ; 
    // dto.setProcedureResultsDTO ( r ) ; 
    // IEmployeeProceduresDTO dto1 = EmpDTOFactory.createEmployeeProceduresDTO ( ) ; 
    // dto1.setEmployeesDTO ( list.get ( 0 ) ) ; 
    // dto1.setHireProcedureDTO ( hire ) ; 
    // dto1.setSendDate ( Date.valueOf ( "2008-10-10" ) ) ; 
    // dto1.setProcedureResultsDTO ( r ) ; 
    // l1.add ( dto ) ; 
    // //l1.add ( dto1 ) ; 
    // for ( ResultDTO basic: employeesClient.sendProcedure ( l1 ) ) 
    // System.out.println ( basic.getStatus ( ) ) ; 
    // } catch ( SharedApplicationException e ) { 
    // // TODO 
    // } catch ( DataBaseException e ) { 
    // // TODO 
    // } 
    // } 
    // 
    // public void search ( ) { 
    // 
    // 
    // try { 
    // List<IEmployeesDTO> list = 
    // ( List<IEmployeesDTO> ) employeesClient.search ( 2L ) ; 
    // 
    // System.out.println ( "" + list ) ; 
    // } catch ( DataBaseException e ) { 
    // // TODO 
    // } 
    // } 
    // 
    // public void getFilterEmployeeWaitingForHireDecision ( ) { 
    // 
    // 
    // 
    // List<IBasicDTO> list = null ; 
    // // employeesClient.getFilterEmployeeWaitingForHireDecision ( EmpEntityKeyFactory.createHireTypesEntityKey ( 1L ) ) ; 
    // 
    // for ( IBasicDTO basic: list ) 
    // System.out.println ( basic.getCode ( ) .getKey ( ) ) ; 
    // 
    // } 
    // 
    // public void searchBasic ( ) throws RemoteException { 
    // IEmployeeSearchDTO dto = RegDTOFactory.createEmployeeSearchDTO ( ) ; 
    // //dto.setEmpName ( "%test%" ) ; 
    // 
    // // dto.setWorkCenterCode ( "13*021324" ) ; 
    // dto.setGenderTypeCode ( 1L ) ; 
    // try { 
    // System.out.println ( new Timestamp ( System.currentTimeMillis ( ) ) ) ; 
    // List<IBasicDTO> list = client.search ( dto ) ; 
    // // for ( IBasicDTO basic: list ) 
    // // System.out.println ( ( ( IKwtCitizensResidentsDTO ) ( ( ( IEmployeesDTO ) basic ) .getCitizensResidentsDTO ( ) ) ) .getBirthDate ( ) ) ; 
    // System.out.println ( new Timestamp ( System.currentTimeMillis ( ) ) ) ; 
    // System.out.println ( list.size ( ) + 
    // "______________________________________________" ) ; 
    // } catch ( SharedApplicationException e ) { 
    // // TODO 
    // } catch ( DataBaseException e ) { 
    // // TODO 
    // } 
    // } 
    // 

    public void getEmployeeInfoByElmType() throws DataBaseException, 
                                                  SharedApplicationException {
        IEmployeesDTO employeeDTO = 
            (IEmployeesDTO)client.getEmployeeInfoHasSocialRaise(283020800518L);//(210010100027L);
        System.out.println("employeeDTO id " + employeeDTO.getCode().getKey());
    } // 
    // void isEmployeeExist ( ) { 
    // try { 
    // 
    // 
    // System.out.println ( employeesClient.isEmployeeExist ( 279062600889L ) ) ; 
    // } catch ( Exception e ) { 
    // ; 
    // } 
    // } 
    // 
    // void isMinistryFileNoExist ( ) { 
    // try { 
    // 
    // 
    // System.out.println ( employeesClient.isMinistryFileNoExist ( "1547/7851" ) ) ; 
    // } catch ( Exception e ) { 
    // ; 
    // } 
    // } 

    public void getEmployeeDataByCivil() throws DataBaseException, 
                                                SharedApplicationException {
        List<IBasicDTO> result = 
            historyClient.getEmployeeDataByCivilID(282082900069L);
        for (IBasicDTO resultDTO: result) {
            System.out.println(resultDTO.getCode());
            System.out.println(resultDTO.getName());
        }
    }

    public void getFirstHireDate() throws DataBaseException, 
                                          SharedApplicationException {
        System.out.println(historyClient.getFirstHireDate(282082900069L));

    }


    public void getByHireStageWithPaging() {

        IEmployeeSearchDTO empSearchDTO = 
            EmpDTOFactory.createEmployeeSearchDTO();
        List hireStagesList = new ArrayList();
        hireStagesList.add(12L);
        empSearchDTO.setEmpHireStages(hireStagesList);

        com.beshara.base.paging.IPagingRequestDTO requestDTO = 
            new PagingRequestDTO();
        requestDTO.setPageNum(1L);
        requestDTO.setMaxNoOfRecords(11L);
        empSearchDTO.setPagingRequestDTO(requestDTO);

        try {
            com.beshara.base.paging.IPagingResponseDTO data = 
                client.getByHireStageWithPaging(empSearchDTO);
            System.out.println("End");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    
    public IEmployeesDTO getByRealCivilId(Long realCivilId, Long minCode) throws RemoteException, FinderException {
            IEmployeesDTO employeeDTO = 
                (IEmployeesDTO)client.getByRealCivilId(realCivilId, minCode);
        return employeeDTO;
        }
}
