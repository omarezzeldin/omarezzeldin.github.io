package com.beshara.csc.hr.emp.presentation.backingbean.governmentalemployeedatarevision;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeHistoriesDTO;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.myfaces.component.html.ext.HtmlDataTable;


public class ForthStepBean extends LookupMaintainBaseBean{
    private static final String BEAN_NAME = "govEmpForthStepBean";
    private static final String NAVIGATION_KEY = "govempforthstep";
    private Long totalServiceDays;
    private Long totalServiceMonths;
    private Long totalServiceYears;
    private int serviceDays;
    private int serviceMonths;
    private int serviceYears;
    
    private int days;
    private int months;
    private int years;
    
    private List<IBasicDTO> dataList = new ArrayList<IBasicDTO>();
    private HtmlDataTable dataListTable = new HtmlDataTable();
    
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();
    private Long[] empServicePeriod = {0L,0L,0L};
    
    public ForthStepBean() {
        setDivMainContent("divContentWithFullWizardAndThreeRowsContent1");
        setContent1Div("module_tabs_cont");
    }
    
    public static ForthStepBean getInstance(){
        return (ForthStepBean)JSFHelper.getValue(BEAN_NAME);
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        return app;
    }
    
    public String init(){
        Long civilID = GovEmpMaintainBean.getEmpCivilId();
//        Date firstHireDate = null;
//        try {
//            firstHireDate = 
//                    EmpClientFactory.getEmpEmployeeHistoriesClient().getFirstHireDate(civilID);
//        } catch (Throwable e) {
//            System.err.println(e.getMessage());
//        }
        try {
            //dataList = (List<IBasicDTO>)EmpClientFactory.getEmpEmployeeHistoriesClient().getAllByCivilID(civilID,13L);
            dataList = EmpClientFactory.getEmpEmployeeHistoriesClient().getEmployeeDataByCivilID(civilID);
        } catch (Throwable e) {
             e.printStackTrace();
        }
//        for(int i=0; i<10; i++){
//            IEmpEmployeeHistoriesDTO empEmployeeHistoriesDTO = EmpDTOFactory.createEmpEmployeeHistoriesDTO();
//            empEmployeeHistoriesDTO.setFromDate(SharedUtils.getCurrentTimestamp());
//            empEmployeeHistoriesDTO.setUntilDate(SharedUtils.getCurrentTimestamp());
//            try {
//                empEmployeeHistoriesDTO.setJobsDTO((JobsDTO)JobClientFactory.getJobsClient().getById(new JobsEntityKey("110051904")));
//                empEmployeeHistoriesDTO.setWorkCentersDTO((WorkCentersDTO)OrgClientFactory.getWorkCentersClient().getById(new WorkCentersEntityKey(13L,"021339")));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }//TODO just for test
//            
//            dataList.add(empEmployeeHistoriesDTO);
//        }
        setMyTableData(dataList);
        loadEmpServivePeriod();
        
        return NAVIGATION_KEY;
    }
    
    
    private void loadEmpServivePeriod(){
        empServicePeriod[0] = empServicePeriod[1] = empServicePeriod[2] = 0L;
        try {
        
            ////////////// check GET DATA FROM LOCAL CLIENT OR FROM SPECIFIC MINISTRY CLIENT 
            if(maintainBean.getMinistryCode() != null){    
            empServicePeriod = EmpClientFactory.getEmployeesClient(maintainBean.getMinistryCode()).getEmpServicePeriod(GovEmpMaintainBean.getEmpCivilId());
            }
            else
            {
            empServicePeriod = EmpClientFactory.getEmployeesClient().getEmpServicePeriod(GovEmpMaintainBean.getEmpCivilId());
            }
        
        } catch (Throwable e) {
            e.printStackTrace();
        }
        totalServiceDays = empServicePeriod[0];
        totalServiceMonths = empServicePeriod[1];
        totalServiceYears = empServicePeriod[2];
    }
    
//    private void calcTotalService(){
//        totalServiceDays = totalServiceMonths = totalServiceYears = 0;
//        if (dataList != null){
//            for(IBasicDTO dto : dataList){
//                IEmpEmployeeHistoriesDTO row = (IEmpEmployeeHistoriesDTO)dto;
//                calcDaysBetween(row.getFromDate(), row.getUntilDate());
//                totalServiceDays += days;
//                totalServiceMonths += months;
//                totalServiceYears += years;
//            }
//        }
//    }

    private void calcDaysBetween(Date fromDate, Date untilDate){
        days = months = years = 0;
        if(fromDate != null){
            if(untilDate == null){
                untilDate = new Date();
            }
            
            GregorianCalendar fromCal = new GregorianCalendar();
            fromCal.setTime(fromDate);
            GregorianCalendar untilCal = new GregorianCalendar();
            untilCal.setTime(untilDate);            
            
            long diffInMillis = untilCal.getTimeInMillis() - fromCal.getTimeInMillis();
            int ONE_DAY = 24*60*60*1000;
            Long totaldays = (diffInMillis/ONE_DAY) + 1;
            if(totaldays >= 365){
                Long years = totaldays / 365;
                totaldays = totaldays % 365;
                this.years = years.intValue();
            }
            if(totaldays >= 30){
                Long months = totaldays / 30;
                totaldays = totaldays % 30;
                this.months = months.intValue();
            }
            this.days = totaldays.intValue();
            
//            days = untilCal.get(Calendar.DAY_OF_MONTH) - fromCal.get(Calendar.DAY_OF_MONTH);
//            if(days < 0){
//                int untilMonth = untilCal.get(Calendar.MONTH);
//                if(untilMonth > 0){
//                    untilCal.set(Calendar.MONTH, ( untilMonth - 1 ));                    
//                } else {
//                    untilCal.set(Calendar.MONTH, 11);
//                }
//                days += untilCal.getMaximum(Calendar.DAY_OF_MONTH);
//            }
//            months = untilCal.get(Calendar.MONTH) - fromCal.get(Calendar.MONTH);
//            if(months < 0){
//                int untilYear = untilCal.get(Calendar.YEAR);
//                months += 12;
//                untilCal.set(Calendar.YEAR, (untilYear - 1));
//            }
//            years =  untilCal.get(Calendar.YEAR) - fromCal.get(Calendar.YEAR);
        }
    }

    public void setTotalServiceDays(Long totalServiceDays) {
        this.totalServiceDays = totalServiceDays;
    }

    public Long getTotalServiceDays() {
        return totalServiceDays;
    }

    public void setTotalServiceMonths(Long totalServiceMonths) {
        this.totalServiceMonths = totalServiceMonths;
    }

    public Long getTotalServiceMonths() {
        return totalServiceMonths;
    }

    public void setTotalServiceYears(Long totalServiceYears) {
        this.totalServiceYears = totalServiceYears;
    }

    public Long getTotalServiceYears() {
        return totalServiceYears;
    }

    public void setServiceDays(int serviceDays) {
        this.serviceDays = serviceDays;
    }

    public int getServiceDays() {
        IEmpEmployeeHistoriesDTO row = 
            (IEmpEmployeeHistoriesDTO)this.getMyDataTable().getRowData();
        calcDaysBetween(row.getFromDate(), row.getUntilDate());
        serviceDays = days;
        serviceMonths = months;
        serviceYears = years;
        return serviceDays;
    }

    public void setServiceMonths(int serviceMonths) {
        this.serviceMonths = serviceMonths;
    }

    public int getServiceMonths() {
        return serviceMonths;
    }

    public void setServiceYears(int serviceYears) {
        this.serviceYears = serviceYears;
    }

    public int getServiceYears() {
        return serviceYears;
    }

    public void setDataList(List<IBasicDTO> dataList) {
        this.dataList = dataList;
    }

    public List<IBasicDTO> getDataList() {
        return dataList;
    }

    public void setDataListTable(HtmlDataTable dataListTable) {
        this.dataListTable = dataListTable;
    }

    public HtmlDataTable getDataListTable() {
        return dataListTable;
    }
}
