package com.beshara.csc.hr.emp.presentation.backingbean.employmentcycle.shared.config;

import java.io.Serializable;


public class ReviewConfigBean implements Serializable {
    
    private boolean disableWorkCenter = true;
    private boolean disableSuggestedJob = true;
    private boolean disableMinistryNotes = true;
    private boolean disableSelectionDeptNotes = true;
    private boolean disableCandidateJob = true;
    private boolean disableBudgetType = true;
    private boolean disableArrangementDept = true;
    private boolean disableCadre = true;
    private boolean disableJobGroup = true;
    private boolean disableSalDegree = true;
    private boolean disableOpenionDept = true;
    
    
    private boolean renderbtnGrp2 = false;
    private boolean renderbtnGrp1 = false;
    private boolean renderbtnGrp3 = false;
    private boolean renderbtnGrp4 = false;
    private boolean renderArrangementDeptBtnGrp = false;
    private boolean renderOpenionDeptBtnGrp = false;
    private boolean renderMinistryBtnGrp = false;
    
    
    private boolean renderSuggestSalariesSection=false;
    private boolean renderSalariesSection = true;
    
    private boolean hideFinancialGroup=false;
    private boolean renderJobFromMinCheckBox = false;
    public ReviewConfigBean() {
        //jobArrangementDepartmentView();//pass
        //opinionDepartmentView();// pass
        //selectionDepartmentView1();//pass
        //selectionDepartmentView2();//pass
        //resetAll();
        //ministryView();
    }
    
    public void resetAll() {
        setDisableWorkCenter(true);
        setDisableSuggestedJob(true);
        setDisableMinistryNotes(true);
        setDisableSelectionDeptNotes(true);
        setDisableCandidateJob(true);
        setDisableBudgetType(true);
        setDisableArrangementDept(true);
        setDisableCadre(true);
        setDisableJobGroup(true);
        setDisableSalDegree(true);
        setDisableOpenionDept(true);
        // Btn section
        setRenderbtnGrp2(false);
        setRenderbtnGrp4(false);
        setRenderbtnGrp1(false);
        setRenderArrangementDeptBtnGrp(false);
        setRenderOpenionDeptBtnGrp(false);
        setRenderJobFromMinCheckBox(false);    
    }
    
    public void ministryView() {
        setDisableWorkCenter(false);
        setDisableSuggestedJob(false);
        setDisableMinistryNotes(false);
        setRenderMinistryBtnGrp(true);
        setRenderJobFromMinCheckBox(false);
      
    }
        //ص�?حات ادارة ترتيب الوظائ�?
    public void jobArrangementDepartmentView() {
        setDisableCandidateJob(false);
        setDisableBudgetType(false);
        setDisableArrangementDept(false);
        setRenderSalariesSection(false);
        setRenderSuggestSalariesSection(true);
        setRenderArrangementDeptBtnGrp(true);
        setHideFinancialGroup(true);
        setRenderJobFromMinCheckBox(false);
//        setDisableSelectionDeptNotes(false);
//        setRenderbtnGrp1(true);
//        setHideFinancialGroup(false);
        
    }
    
//(9) جارى مراجعة الطلب -البيانات    
    public void selectionDepartmentView2() {
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setRenderSuggestSalariesSection(true);
        setDisableSelectionDeptNotes(false);
        setRenderbtnGrp1(true);
        setHideFinancialGroup(false);
        setRenderSalariesSection(false);
        setRenderJobFromMinCheckBox(false);
        //disableBudgetType
        
    }
    
    //(10) // جارى استي�?اء الدرجة الماليه لمرشح بخبرة
    public void fatwaSalDegreeDetect() {
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setDisableOpenionDept(false);
        setRenderbtnGrp2(true);
        setRenderSalariesSection(true);
        setRenderSuggestSalariesSection(true);   
        setRenderJobFromMinCheckBox(false);
    }
    
 //(10) // جارى استي�?اء الدرجة الماليه لمرشح بدون خبرة
    public void selectionSalDegreeDetect() {
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setDisableSelectionDeptNotes(false);
        setRenderOpenionDeptBtnGrp(true);
        setRenderSalariesSection(true);
        setRenderSuggestSalariesSection(true);  
        setRenderJobFromMinCheckBox(true);
    }
    
// تم استي�?اء المسمى الوظي�?ى والدرجة(13)
    public void selectionDepartmentView1() {
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setDisableSelectionDeptNotes(false);
        setRenderbtnGrp3(true);
        setDisableOpenionDept(false);
        setRenderSuggestSalariesSection(true);  
        setRenderJobFromMinCheckBox(true);
    }
    public void selectionDepartmentView4() {
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setDisableSelectionDeptNotes(false);
        setRenderbtnGrp4(true);
        setDisableOpenionDept(false);
        setRenderSuggestSalariesSection(true); 
        setRenderJobFromMinCheckBox(false);
    } 
    

    public void setDisableWorkCenter(boolean disableWorkCenter) {
        this.disableWorkCenter = disableWorkCenter;
    }

    public boolean isDisableWorkCenter() {
        return disableWorkCenter;
    }

    public void setDisableSuggestedJob(boolean disableSuggestedJob) {
        this.disableSuggestedJob = disableSuggestedJob;
    }

    public boolean isDisableSuggestedJob() {
        return disableSuggestedJob;
    }

    public void setDisableMinistryNotes(boolean disableMinistryNotes) {
        this.disableMinistryNotes = disableMinistryNotes;
    }

    public boolean isDisableMinistryNotes() {
        return disableMinistryNotes;
    }

    public void setDisableSelectionDeptNotes(boolean disableSelectionDeptNotes) {
        this.disableSelectionDeptNotes = disableSelectionDeptNotes;
    }

    public boolean isDisableSelectionDeptNotes() {
        return disableSelectionDeptNotes;
    }

    public void setDisableCandidateJob(boolean disableCandidateJob) {
        this.disableCandidateJob = disableCandidateJob;
    }

    public boolean isDisableCandidateJob() {
        return disableCandidateJob;
    }

    public void setDisableBudgetType(boolean disableBudgetType) {
        this.disableBudgetType = disableBudgetType;
    }

    public boolean isDisableBudgetType() {
        return disableBudgetType;
    }

    public void setDisableArrangementDept(boolean disableArrangementDept) {
        this.disableArrangementDept = disableArrangementDept;
    }

    public boolean isDisableArrangementDept() {
        return disableArrangementDept;
    }

    public void setDisableCadre(boolean disableCadre) {
        this.disableCadre = disableCadre;
    }

    public boolean isDisableCadre() {
        return disableCadre;
    }

    public void setDisableJobGroup(boolean disableJobGroup) {
        this.disableJobGroup = disableJobGroup;
    }

    public boolean isDisableJobGroup() {
        return disableJobGroup;
    }

    public void setDisableSalDegree(boolean disableSalDegree) {
        this.disableSalDegree = disableSalDegree;
    }

    public boolean isDisableSalDegree() {
        return disableSalDegree;
    }

    public void setDisableOpenionDept(boolean disableOpenionDept) {
        this.disableOpenionDept = disableOpenionDept;
    }

    public boolean isDisableOpenionDept() {
        return disableOpenionDept;
    }

    public void setRenderSalariesSection(boolean renderSalariesSection) {
        this.renderSalariesSection = renderSalariesSection;
    }

    public boolean isRenderSalariesSection() {
        return renderSalariesSection;
    }


    public void setRenderbtnGrp2(boolean renderbtnGrp2) {
        this.renderbtnGrp2 = renderbtnGrp2;
    }

    public boolean isRenderbtnGrp2() {
        return renderbtnGrp2;
    }

    public void setRenderbtnGrp1(boolean renderbtnGrp1) {
        this.renderbtnGrp1 = renderbtnGrp1;
    }

    public boolean isRenderbtnGrp1() {
        return renderbtnGrp1;
    }

    public void setRenderArrangementDeptBtnGrp(boolean renderArrangementDeptBtnGrp) {
        this.renderArrangementDeptBtnGrp = renderArrangementDeptBtnGrp;
    }

    public boolean isRenderArrangementDeptBtnGrp() {
        return renderArrangementDeptBtnGrp;
    }

    public void setRenderOpenionDeptBtnGrp(boolean renderOpenionDeptBtnGrp) {
        this.renderOpenionDeptBtnGrp = renderOpenionDeptBtnGrp;
    }

    public boolean isRenderOpenionDeptBtnGrp() {
        return renderOpenionDeptBtnGrp;
    }


    public void setRenderMinistryBtnGrp(boolean renderMinistryBtnGrp) {
        this.renderMinistryBtnGrp = renderMinistryBtnGrp;
    }

    public boolean isRenderMinistryBtnGrp() {
        return renderMinistryBtnGrp;
    }

    public void setRenderSuggestSalariesSection(boolean renderSuggestSalariesSection) {
        this.renderSuggestSalariesSection = renderSuggestSalariesSection;
    }

    public boolean isRenderSuggestSalariesSection() {
        return renderSuggestSalariesSection;
    }

    public void setRenderbtnGrp3(boolean renderbtnGrp3) {
        this.renderbtnGrp3 = renderbtnGrp3;
    }

    public boolean isRenderbtnGrp3() {
        return renderbtnGrp3;
    }

    public void setRenderbtnGrp4(boolean renderbtnGrp4) {
        this.renderbtnGrp4 = renderbtnGrp4;
    }

    public boolean isRenderbtnGrp4() {
        return renderbtnGrp4;
    }

    public void setHideFinancialGroup(boolean hideFinancialGroup) {
        this.hideFinancialGroup = hideFinancialGroup;
    }

    public boolean isHideFinancialGroup() {
        return hideFinancialGroup;
    }

    public void setRenderJobFromMinCheckBox(boolean renderJobFromMinCheckBox) {
        this.renderJobFromMinCheckBox = renderJobFromMinCheckBox;
    }

    public boolean isRenderJobFromMinCheckBox() {
        return renderJobFromMinCheckBox;
    }
}
