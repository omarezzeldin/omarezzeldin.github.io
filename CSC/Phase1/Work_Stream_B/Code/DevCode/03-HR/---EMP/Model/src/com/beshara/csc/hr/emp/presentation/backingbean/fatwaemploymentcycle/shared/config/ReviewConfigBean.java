package com.beshara.csc.hr.emp.presentation.backingbean.fatwaemploymentcycle.shared.config;

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
    private boolean disableAcceptedJob = true;

    private boolean renderbtnGrp2 = false;
    private boolean renderbtnGrp1 = false;
    private boolean renderbtnGrp3 = false;
    private boolean renderbtnGrp4 = false;
    private boolean renderArrangementDeptBtnGrp = false;
    private boolean renderOpenionDeptBtnGrp = false;
    private boolean renderMinistryBtnGrp = false;


    private boolean renderSuggestSalariesSection = false;
    private boolean renderSalariesSection = true;

    private boolean hideFinancialGroup = false;
    
    private boolean renderJobFromMinCheckBox = false;

    public ReviewConfigBean() {
    }

    public void resetAll() {
        setDisableWorkCenter(true);
        setDisableSuggestedJob(true);
        setDisableAcceptedJob(true);
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
    
    //fatwa-Contract ::gary_morag3t_Talb_from_Dewan
    public void selectionFatwaReview1() {
        setDisableCadre(true);
        setDisableJobGroup(true);
        setDisableSalDegree(true);
        setRenderSalariesSection(false);
        setDisableWorkCenter(true);
        setDisableSuggestedJob(true);
        setDisableAcceptedJob(false);
        setDisableArrangementDept(false);
        setRenderSuggestSalariesSection(true);
        setHideFinancialGroup(false);
        setDisableOpenionDept(false);
        setDisableMinistryNotes(true);
        setRenderbtnGrp1(true);
        setRenderJobFromMinCheckBox(false);
    }
    
    //fatwa-Contract :: gary_t7ded_draga_malya_mo3adla
    public void fatwaSalDegreeDetectInContract() {
        setDisableCadre(false);
        setDisableJobGroup(false);
        setDisableSalDegree(false);
        setDisableArrangementDept(true);
        setDisableAcceptedJob(true);
        setRenderSalariesSection(true);
        setDisableWorkCenter(true);
        setDisableSuggestedJob(true);
        setRenderSuggestSalariesSection(true);
        setHideFinancialGroup(false);
        setDisableOpenionDept(false);
        setDisableMinistryNotes(true);
        setRenderbtnGrp2(true);
        setRenderJobFromMinCheckBox(true);
    }
    
    //fatwa-Contract :: order refused
    public void orderRefusedFormDewan() {
        setDisableCadre(true);
        setDisableJobGroup(true);
        setDisableSalDegree(true);
        setDisableArrangementDept(true);
        setDisableAcceptedJob(true);
        setRenderSalariesSection(false);
        setDisableWorkCenter(true);
        setDisableSuggestedJob(true);
        setRenderSuggestSalariesSection(true);
        setHideFinancialGroup(false);
        setDisableOpenionDept(false);
        setDisableMinistryNotes(true);
        setRenderJobFromMinCheckBox(false);
    }
    
    
    //centeral Employement
    public void orderReturnToChoiceDept() {
        setDisableWorkCenter(true);
        setDisableSuggestedJob(true);
        setDisableAcceptedJob(true);
        setDisableMinistryNotes(true);
        setDisableSelectionDeptNotes(true);
        setDisableCandidateJob(true);
        setDisableBudgetType(true);
        setDisableArrangementDept(true);
        setDisableCadre(true);
        setDisableJobGroup(true);
        setDisableSalDegree(true);
        // Btn section
        //setRenderbtnGrp2(false);
        setRenderbtnGrp4(false);
        setRenderbtnGrp1(false);
        setRenderArrangementDeptBtnGrp(false);
        setRenderOpenionDeptBtnGrp(false);
        setDisableOpenionDept(false);
        setDisableMinistryNotes(true);
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

    public void setDisableAcceptedJob(boolean acceptedJob) {
        this.disableAcceptedJob = acceptedJob;
    }

    public boolean isDisableAcceptedJob() {
        return disableAcceptedJob;
    }

    public void setRenderJobFromMinCheckBox(boolean renderJobFromMinCheckBox) {
        this.renderJobFromMinCheckBox = renderJobFromMinCheckBox;
    }

    public boolean isRenderJobFromMinCheckBox() {
        return renderJobFromMinCheckBox;
    }
}
