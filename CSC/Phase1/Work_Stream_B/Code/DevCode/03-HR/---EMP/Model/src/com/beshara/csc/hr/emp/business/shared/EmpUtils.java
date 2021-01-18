package com.beshara.csc.hr.emp.business.shared;


import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ICheckCivilIdOnConditionDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;


public class EmpUtils {
    private final static String _statusForHire;
    private final static String _statusForHireWithoutDELEGATION_OUT;
    private final static String _statusForHireWithoutDELEGATION_OUT_REG;
    private final static String _statusForHireWithoutDELEGATION_OUT_ADC;
    private final static String _statusForHireWithoutDELEGATION_OUT_VAC;
    //added by Tech.Team [m.sayed] 1-4-2016
    //* CSC-17343 
//    private  static HashMap<String, String> _resturctWorkCenters = new HashMap<String, String>();
//    private  static HashMap<Long, String> _resturctgroups = new HashMap<Long, String>();
//    private  static HashMap<Long, String> _resturctEmployees = new HashMap<Long, String>();
    
//    static {
//        _resturctgroups = initGroups();
//    }
//    static {
//        _resturctWorkCenters = initWorkCenters();
//    }
//    static {
//        _resturctEmployees = initEmployeesForStruct();
//    }
    static {
        _statusForHire = initStatusForHire();
    }
    static {
        _statusForHireWithoutDELEGATION_OUT = initStatusForHireWithoutDELEGATION_OUT();
    }
    
    static {
        _statusForHireWithoutDELEGATION_OUT_REG = initStatusForHireWithoutDELEGATION_OUT_REG();
    }
    
    static {
        _statusForHireWithoutDELEGATION_OUT_ADC = initStatusForHireWithoutDELEGATION_OUT_ADC();
    }
    static {
        _statusForHireWithoutDELEGATION_OUT_VAC = initStatusForHireWithoutDELEGATION_OUT_VAC();
    }
    public EmpUtils() {
        super();
    }

    public static String getStatusForHire() {
        return _statusForHire;
    }
    
    public static String getStatusForHireWithoutDELEGATION() {
        return _statusForHireWithoutDELEGATION_OUT;
    }
    
    public static String getStatusForHireWithoutDELEGATIONREG() {
        return _statusForHireWithoutDELEGATION_OUT_REG;
    }
    
    public static String getStatusForHireWithoutDELEGATIONVAC() {
        return _statusForHireWithoutDELEGATION_OUT_VAC;
    }
    /**
     * * @author black Horse team[M.sayed]
     * @since 15/06/2015
     * @return
     * @Note used get hire status with DELEGATION_OUT  
      */
    private static String initStatusForHire() {
        StringBuilder strHire = new StringBuilder(IEMPConstants.EMP_STATUS_EMPLOYMENT.toString());
        strHire.append(",");
        strHire.append(IEMPConstants.EMP_STATUS_DELEGATION_TO_OUT);
        strHire.append(",");
        strHire.append(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO_IT);
        strHire.append(",");
        strHire.append(IEMPConstants.EMP_STATUS_FREEZING);
        return strHire.toString();
    }
   
   /**
    * * @author black Horse team[M.sayed]
    * @since 20/08/2015
    * @return
    * @Note used get hire status without DELEGATION_OUT it used by (emp,vac,Mov)
     */
    private static String initStatusForHireWithoutDELEGATION_OUT() {
        StringBuilder strHire = new StringBuilder(IEMPConstants.EMP_STATUS_EMPLOYMENT.toString());
        strHire.append(",");
        strHire.append(IEMPConstants.EMP_STATUS_DELEGATION_TO_OUT);
        strHire.append(",");
        strHire.append(IEMPConstants.EMP_STATUS_FREEZING);
        return strHire.toString();
    }
   
    /**
     * * @author black Horse team[A.Khaled]
     * @since 7/01/2016
     * @return
     * @Note used get hire status without DELEGATION_OUT it used by (reg)
      */
     private static String initStatusForHireWithoutDELEGATION_OUT_REG() {
         StringBuilder strHire = new StringBuilder(IEMPConstants.EMP_STATUS_EMPLOYMENT.toString());
         strHire.append(",");
         strHire.append(IEMPConstants.EMP_STATUS_DELEGATION_TO_OUT);
         strHire.append(",");
         strHire.append(IEMPConstants.EMP_STATUS_FREEZING);
         strHire.append(",");
         strHire.append(IEMPConstants.Emp_With_Status_END_Service);
         return strHire.toString();
     }
    /**
     * * @author black Horse team[A.Khaled]
     * @since 7/01/2016
     * @return
     * @Note used get hire status without DELEGATION_OUT it used by (reg)
      */
     private static String initStatusForHireWithoutDELEGATION_OUT_ADC() {
         StringBuilder strHire = new StringBuilder(IEMPConstants.EMP_STATUS_EMPLOYMENT.toString());
         strHire.append(",");
         strHire.append(IEMPConstants.EMP_STATUS_DELEGATION_TO_OUT);
         strHire.append(",");
         strHire.append(IEMPConstants.EMP_STATUS_FREEZING);
         strHire.append(",");
         strHire.append(IEMPConstants.Emp_With_Status_END_Service);
         strHire.append(",");
         strHire.append(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO_IT);
         return strHire.toString();
     }
    /**
     * @author black Horse team[A.Elmasry]
     * @since 15/09/2017
     * @return
     * @Note used get hire status without DELEGATION_OUT
     */
    private static String initStatusForHireWithoutDELEGATION_OUT_VAC() {
        StringBuilder strHire = new StringBuilder(IEMPConstants.EMP_STATUS_EMPLOYMENT.toString());
        strHire.append(",");
        strHire.append(IEMPConstants.EMP_STATUS_FREEZING);
        return strHire.toString();
    }
   
    ////// added by Mohamed Sayed at 27-7-2015
        public Boolean testConditionFromGrs(Long CivilID,Long tabrecSerial) {
            Boolean condApplied = false;
            ICheckCivilIdOnConditionDTO resultDTO;
            ICheckCivilIdOnConditionDTO inputDTO = GrsDTOFactory.createCheckCivilIdOnConditionDTO();
           
                try {

                    try {
                        inputDTO.setCivilId(CivilID);
                        inputDTO.setRTabrecSerial(tabrecSerial);
                        resultDTO =
                                GrsClientFactory.getConditionsClient().checkCivilIdOnConditionByRTabrecSerial(inputDTO);
                        condApplied = (resultDTO.getStatus() == 1 ? true : false);
                    } catch (DataBaseException e) {
                     
                        e.printStackTrace();
                        //      return condApplied;
                    }
                    //                }
                } catch (Exception e) {
                    e.printStackTrace();
                
                    //  return condApplied;
                }

           
            return condApplied;
        }
        
        /**
     * @author Tech.team [m.sayed]
     * @since 1-4-2016
     *  //* CSC-17343 
     * @return
     */
//        private static HashMap<String, String> initWorkCenters(){
//            IEmployeesClient empClient= EmpClientFactory.getEmployeesClient();
//        try {
//           return empClient.getworkCenterTree();
//        } catch (DataBaseException e) {
//            e.printStackTrace();
//        } catch (SharedApplicationException e) {
//            e.printStackTrace();
//        }
//        return null;
//        }
    /**
    * @author Tech.team [m.sayed]
    * @since 1-4-2016
    *  //* CSC-17343 
    * @return
    */
//    private static HashMap<Long, String> initEmployeesForStruct(){
//        IEmployeesClient empClient= EmpClientFactory.getEmployeesClient();
//        try {
//          return  empClient.getEmployeesworkCenterTree();
//        } catch (DataBaseException e) {
//            e.printStackTrace();
//        } catch (SharedApplicationException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    /**
    * @author Tech.team [m.sayed]
    * @since 1-4-2016
    *  //* CSC-17343 
    * @return
    */
//    private static HashMap<Long, String> initGroups(){
//        IEmployeesClient empClient= EmpClientFactory.getEmployeesClient();
//        try {
//          return  empClient.getGroupsworkCenterTree();
//        } catch (DataBaseException e) {
//            e.printStackTrace();
//        } catch (SharedApplicationException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
   /**
    * @author Tech.team [m.sayed]
    * @since 1-4-2016
    *  //* CSC-17343 
     * @param groupCode
     * @param wrkCode
     * @return
     */
//    public static boolean checkWrkCenterFound(Long groupCode,String wrkCode){
//        if (! getResturctgroups().containsKey(groupCode)){
//            return true;
//        }
//        if ( getResturctWorkCenters().containsKey(wrkCode)){
//            return true;
//        }
//        return false;
//    }
    //    public static boolean checkEmployeeFound(Long groupCode,Long RealCivil){
//        if (! getResturctgroups().containsKey(groupCode)){
//            return true;
//        }
//        if ( getResturctEmployees().containsKey(RealCivil)){
//            return true;
//        }
//        return false;
//    }

//    public static HashMap<String, String> getResturctWorkCenters() {
//        return _resturctWorkCenters;
//    }
//
//    public static HashMap<Long, String> getResturctgroups() {
//        return _resturctgroups;
//    }
//
//    public static HashMap<Long, String> getResturctEmployees() {
//        return _resturctEmployees;
//    }

    /**
     * @author Tech.team [m.sayed]
     * @since 1-4-2016
     *  //* CSC-17343 
     * @param groupCode
     * @param Real Civil
     * @return
     */
    public static String getStatusForHireWithoutDELEGATION_OUT_ADC() {
        return _statusForHireWithoutDELEGATION_OUT_ADC;
    }
}
