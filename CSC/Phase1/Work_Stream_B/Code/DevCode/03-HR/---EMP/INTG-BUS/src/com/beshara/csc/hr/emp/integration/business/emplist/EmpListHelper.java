package com.beshara.csc.hr.emp.integration.business.emplist;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;

import java.lang.reflect.Field;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public abstract class EmpListHelper {
    //IEmpListDTO empListDTO;
    private List<IBasicDTO> values;
    private boolean valuesLoaded;
    private boolean colNamesInitialized;
    private Map<String, String> colNames;
    private String query;
    private String relationTableElias;
    private List<IBasicDTO> empIntgList;
    private boolean empIntgListLoaded;
    private IEntityKey code;
    
    
    public EmpListHelper(IEntityKey code){
        this.code = code;
    }
    
    //abstract methods 
    protected abstract String getRTCode();
    protected abstract String getECode();
    protected abstract String getMaxCol();    
    protected abstract String getRelationTable();
    protected abstract void loadValues();
    protected abstract void initColNames();
    protected abstract String getExtraWhereCond();
    protected abstract void loadEmpIntgList();
    public String buildQuery() {
        if(getValues() == null || getValues().size() == 0){
            return null;
        }
        StringBuilder q =
            new StringBuilder("SELECT HR_ORG_INTERFACE_PACK.GET_MIN_DESC(E.MIN_CODE,NULL,NULL) MIN_DESC, ");
        q.append(" E.REAL_CIVIL_ID , GET_NAME(E.REAL_CIVIL_ID) E_NAME , ");
        q.append(" (SELECT QUALIFICATION_NAME FROM NL_QUL_QUALIFICATIONS Q WHERE QUALIFICATION_KEY = HR_EMP_PAC.GET_EMP_LAST_QUL(E.REAL_CIVIL_ID)) EMP_QUL , ");
        q.append(" HR_JOB_INTERFACE_PACK.GET_JOB_NAME(E.JOB_CODE ) EMP_JOB , ");
        q.append(" (SELECT WRK_NAME FROM NL_ORG_WORK_CENTERS WC WHERE WC.WRK_CODE = E.WRK_CODE) EMP_WRK , E.HIRE_DATE ");
        q.append(" FROM HR_EMP_EMPLOYEES E ");
        //-----
        if(getRelationTable() != null){
            setRelationTableElias("RT");
            q.append(" , " + getRelationTable() + " " + getRelationTableElias());
        }else{
            setRelationTableElias("E");
        }
        q.append(" WHERE ");
        
        if(getRelationTable() != null){
            q.append(getRelationTableElias() + "." + getRTCode() + " = E." + getECode());
            q.append(" AND ");    
        }
        
        q.append("  ( " + getColValPairs() + " ) ");
        if(getMaxCol()!=null){
            q.append(" AND " + getRelationTableElias() + "." + getMaxCol() + " = (SELECT MAX(" + getRelationTableElias() + "." + getMaxCol() + ") ");
        
            q.append(" FROM " + getRelationTable() + " " + getRelationTableElias() );
            q.append(" WHERE " + getRelationTableElias() + "." + getRTCode() + " = E." + getECode() + ") ");
        }
        q.append(" AND ACTIVE_FLAG = 1 ");
        q.append(" AND HIRSTATUS_CODE IN (");
        q.append(IEMPConstants.EMP_HIRE_TYPE_ACTIVE_STATUS.toString() + ", ");
        q.append(IEMPConstants.EMP_STATUS_FREEZED.toString() +") ");
        if(getExtraWhereCond()!=null){
            q.append("" + getExtraWhereCond());
        }
        System.out.println("------------>>> Query <<<<----------------------");
        System.out.println(q.toString());
        System.out.println("------------>>> End Query <<<<------------------");
        setQuery(q.toString());
        return q.toString();
    }
    
    private String getColValPairs() {
        StringBuilder colValPairs = new StringBuilder();
        Map<String,String> colNames =  getColNames();
        int j = 0;
        for(int i = 0; i< getValues().size();i++){
            if(i!=0){
                colValPairs.append(" OR ");
            }
            colValPairs.append(" ( ");
            j = 0;
            for (Map.Entry<String, String> colName : colNames.entrySet()) {
                if(j!=0){
                    colValPairs.append( " AND " );
                }
                colValPairs.append(getRelationTableElias() + ".");
                colValPairs.append(colName.getKey());
                colValPairs.append( " = " );
                colValPairs.append("'" + getAttrValue(getValues().get(i), colName.getValue()) + "'");
                j++;

            } 
            colValPairs.append(" ) ");
        }
        return colValPairs.toString();
    }
    
    private String getAttrValue(IBasicDTO dto, String attrName){
        Field field = null;
        String value = null;
        try {
            if(attrName.length() > 6 && attrName.substring(0, 6).equals(".code.")){
                field = dto.getCode().getClass().getDeclaredField(attrName.substring(6, attrName.length()));
                field.setAccessible(true);
                value = field.get(dto.getCode()).toString();
            }else{
                field = dto.getCode().getClass().getDeclaredField(attrName.substring(1,attrName.length()));
                field.setAccessible(true);
                value = field.get(dto).toString();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }
    public static IEmpIntgDTO createEmpIntgDTO(Vector empData){
        IEmpIntgDTO emp =  EmpIntgDTO.getInstance();
        //min_desc, real_civil_id,  e_name,  emp_qul, emp_job, emp_wrk, hire_date
        emp.setMinName((String)empData.get(0));
        emp.setRealCivilId(((BigDecimal)empData.get(1)).longValue());
        emp.setName((String)empData.get(2));
        emp.setQulName((String)empData.get(3));
        emp.setJobName((String)empData.get(4));
        emp.setWrkName((String)empData.get(5));
        emp.setHireDate(new Date(((Timestamp)empData.get(6)).getTime()));
        return emp;
    }
    
    public static List<IBasicDTO> createEmpIntgDTOList(List empDataList){
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        for(Object empData1 : empDataList){
            Vector empData = (Vector)empData1;
            list.add(createEmpIntgDTO(empData));
        }
        return list;
    }
    
    
    
    protected List<IBasicDTO> getValues(){
        if(!isValuesLoaded()){
            loadValues();
            setValuesLoaded(true);
        }
        return values;
    }
    
    //-------------------------setters and getters-------------------------

    public void setValuesLoaded(boolean valuesLoaded) {
        this.valuesLoaded = valuesLoaded;
    }

    public boolean isValuesLoaded() {
        return valuesLoaded;
    }

    public void setValues(List<IBasicDTO> values) {
        this.values = values;
    }

    public void setColNames(Map<String, String> colNames) {
        this.colNames = colNames;
    }

    public Map<String, String> getColNames() {
        if(!isColNamesInitialized()){
            initColNames();
            setColNamesInitialized(true);
        }
        return colNames;
    }

    public void setColNamesInitialized(boolean colNamesInitialized) {
        this.colNamesInitialized = colNamesInitialized;
    }

    public boolean isColNamesInitialized() {
        return colNamesInitialized;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setEmpIntgList(List<IBasicDTO> empIntgList) {
        this.empIntgList = empIntgList;
    }

    public List<IBasicDTO> getEmpIntgList() {
        if(!isEmpIntgListLoaded()){
            loadEmpIntgList();
            setEmpIntgListLoaded(true);
        }
        return empIntgList;
    }

    public void setEmpIntgListLoaded(boolean empIntgListLoaded) {
        this.empIntgListLoaded = empIntgListLoaded;
    }

    public boolean isEmpIntgListLoaded() {
        return empIntgListLoaded;
    }

    public void setRelationTableElias(String relationTableElias) {
        this.relationTableElias = relationTableElias;
    }

    public String getRelationTableElias() {
        return relationTableElias;
    }


    public void setCode(IEntityKey code) {
        this.code = code;
    }

    public IEntityKey getCode() {
        return code;
    }
}
