package com.beshara.csc.hr.emp.presentation.backingbean.employment.shared.config;

import java.io.Serializable;


public class ReviewConfigBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
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
    private boolean disableSuggestJobDegree = true;
    private boolean disableJobContainer = false;
    private boolean disableBudgetContainer = false;
    private boolean renderbtnGrp2 = false;
    private boolean renderbtnGrp1 = false;
    private boolean renderbtnGrp3 = false;
    private boolean renderArrangementDeptBtnGrp = false;
    private boolean renderOpenionDeptBtnGrp = false;
    private boolean renderMinistryBtnGrp = false;
    private boolean disableFirstJobGroup = false;
    private boolean renderFirstSalariesSection = false ;
    private boolean disableFirstCadre = false;
    private boolean renderCadergroupId = false;
    private boolean renderSalariesSection = true;
    private boolean disableFirstSalDegree = false ;
    private boolean disableOrderStatus = false;
    private boolean renderApprovebtnGrp = false;
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
        setRenderbtnGrp1(false);
        setRenderArrangementDeptBtnGrp(false);
        setRenderOpenionDeptBtnGrp(false);
            
    }
    
    public void ministryView() {
        setDisableWorkCenter(false);
        setDisableSuggestedJob(false);
        setDisableMinistryNotes(false);
        setRenderMinistryBtnGrp(true);
    }
    

    public void jobAndDegreeInProgressView() {
        
        setRenderCadergroupId(true);
        setRenderFirstSalariesSection(true);
        setDisableFirstCadre(true);
        setDisableFirstJobGroup(true);
        setDisableFirstSalDegree(true);
        setDisableOrderStatus(true);
        setDisableSelectionDeptNotes(true);
        setDisableJobContainer(true);
        setDisableCandidateJob(true);
        setDisableBudgetContainer(true);
        setDisableBudgetType(true);
        setDisableArrangementDept(true);
        setRenderSalariesSection(true);
        setDisableCadre(true);
        setDisableJobGroup(true);
        setDisableSalDegree(true);
        setDisableOpenionDept(true);
        //setRenderArrangementDeptBtnGrp(true);
    }
    
    public void opinionDepartmentView() {
    
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setDisableOpenionDept(false);
        setRenderOpenionDeptBtnGrp(true);
    
    }
    
    
    
    
    public void selectionDepartmentView2() {
        setDisableWorkCenter(true);
        setDisableSuggestedJob(false);
        setDisableMinistryNotes(false);
        
        setDisableCandidateJob(false);
        setDisableBudgetType(false);
        setDisableArrangementDept(false);
        setDisableCadre(false);
        setRenderSalariesSection(false);
        setDisableJobContainer(false);
        setDisableBudgetContainer(false);
        setDisableOpenionDept(false);
        setRenderCadergroupId(true);
        setRenderFirstSalariesSection(true);
        setDisableFirstCadre(false);
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setDisableFirstJobGroup(false);
        setDisableSelectionDeptNotes(false);
        setRenderbtnGrp1(true);
        setDisableFirstSalDegree(false);
        //setDisableCandidateJob(false);
        //setDisableSuggestJobDegree(true);
        
        //disableBudgetType
        
    }
    
   
    public void selectionCompleteView() {
        setDisableWorkCenter(true);
        setDisableSuggestedJob(false);
        setDisableMinistryNotes(false);
        setDisableOrderStatus(true);
        setDisableBudgetType(false);
        setDisableOpenionDept(false);
        setRenderCadergroupId(true);
        setRenderFirstSalariesSection(true);
        setDisableFirstCadre(false);
        setDisableFirstJobGroup(false);
        setDisableFirstSalDegree(false);
        //الجزء الخاص بالادارات 
        setDisableSelectionDeptNotes(true);
        setDisableJobContainer(true);
        setRenderbtnGrp1(true);
        setDisableCandidateJob(true);
        setDisableBudgetContainer(true);
        setDisableBudgetType(true);
        setDisableArrangementDept(true);
        setRenderSalariesSection(true);
        setDisableCadre(true);
        setDisableJobGroup(true);
        setDisableSalDegree(true);
        setDisableOpenionDept(true); 
    }
    
    
    public void approvalView() {
        setDisableWorkCenter(true);
        setDisableSuggestedJob(false);
        setDisableMinistryNotes(false);
        setDisableOrderStatus(true);
        setDisableBudgetType(false);
        setDisableOpenionDept(false);
        setRenderCadergroupId(true);
        setRenderFirstSalariesSection(true);
        setDisableFirstCadre(false);
        setDisableFirstJobGroup(false);
        setDisableFirstSalDegree(false);
        //الجزء الخاص بالادارات 
        setDisableSelectionDeptNotes(true);
        setDisableJobContainer(true);
        setRenderbtnGrp1(true);
        setDisableCandidateJob(true);
        setDisableBudgetContainer(true);
        setDisableBudgetType(true);
        setDisableArrangementDept(true);
        setRenderSalariesSection(true);
        setDisableCadre(true);
        setDisableJobGroup(true);
        setDisableSalDegree(true);
        setDisableOpenionDept(true); 
        setRenderbtnGrp1(false);
        setRenderApprovebtnGrp(true);
    }
    
    public void renderButton(){
        setRenderbtnGrp1(false);
        setRenderApprovebtnGrp(true);
        
    }
    public void renderButtonView(){
        setRenderbtnGrp1(false);
        setRenderbtnGrp3(true);
        
    }
    

    public void selectionDepartmentView1() {
        setDisableSelectionDeptNotes(false);
        setRenderbtnGrp2(true);
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

    public void setDisableSuggestJobDegree(boolean disableSuggestJobDegree) {
        this.disableSuggestJobDegree = disableSuggestJobDegree;
    }

    public boolean isDisableSuggestJobDegree() {
        return disableSuggestJobDegree;
    }

    public void setDisableJobContainer(boolean disableJobContaner) {
        this.disableJobContainer = disableJobContaner;
    }

    public boolean isDisableJobContainer() {
        return disableJobContainer;
    }

    public void setDisableBudgetContainer(boolean disableBudgetContainer) {
        this.disableBudgetContainer = disableBudgetContainer;
    }

    public boolean isDisableBudgetContainer() {
        return disableBudgetContainer;
    }

    

    public void setRenderFirstSalariesSection(boolean renderFirstSalariesSection) {
        this.renderFirstSalariesSection = renderFirstSalariesSection;
    }

    public boolean isRenderFirstSalariesSection() {
        return renderFirstSalariesSection;
    }

    public void setDisableFirstCadre(boolean disableFirstCadre) {
        this.disableFirstCadre = disableFirstCadre;
    }

    public boolean isDisableFirstCadre() {
        return disableFirstCadre;
    }

    public void setRenderCadergroupId(boolean renderCadergroupId) {
        this.renderCadergroupId = renderCadergroupId;
    }

    public boolean isRenderCadergroupId() {
        return renderCadergroupId;
    }

    public void setDisableFirstJobGroup(boolean disableFirstJobGroup) {
        this.disableFirstJobGroup = disableFirstJobGroup;
    }

    public boolean isDisableFirstJobGroup() {
        return disableFirstJobGroup;
    }

    public void setDisableFirstSalDegree(boolean disableFirstSalDegree) {
        this.disableFirstSalDegree = disableFirstSalDegree;
    }

    public boolean isDisableFirstSalDegree() {
        return disableFirstSalDegree;
    }

    public void setDisableOrderStatus(boolean disableOrderStatus) {
        this.disableOrderStatus = disableOrderStatus;
    }

    public boolean isDisableOrderStatus() {
        return disableOrderStatus;
    }

    public void setRenderApprovebtnGrp(boolean renderApprovebtnGrp) {
        this.renderApprovebtnGrp = renderApprovebtnGrp;
    }

    public boolean isRenderApprovebtnGrp() {
        return renderApprovebtnGrp;
    }

    public void setRenderbtnGrp3(boolean renderbtnGrp3) {
        this.renderbtnGrp3 = renderbtnGrp3;
    }

    public boolean isRenderbtnGrp3() {
        return renderbtnGrp3;
    }
}
