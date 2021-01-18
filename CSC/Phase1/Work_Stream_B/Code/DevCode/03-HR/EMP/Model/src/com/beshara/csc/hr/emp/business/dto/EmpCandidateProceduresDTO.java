package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpReasonDataEntityKey;
import com.beshara.csc.hr.emp.business.entity.IProcedureResultsEntityKey;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.sql.Date;


public class EmpCandidateProceduresDTO extends EmpDTO implements IEmpCandidateProceduresDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private String procDesc;
    private Date sendDate;
    private Date resultDate;
    private Date postponeDate;
    private Long auditStatus;
    private Long tabrecSerial;
    private IEmpReasonDataDTO empReasonDataDTO;
    private IEmpCandidatesDTO empCandidatesDTO;
    private IProcedureResultsDTO procedureResultsDTO;
    private IHireProceduresDTO hireProceduresDTO;
    private String crmStatusCode;
    private String crmLetterNo;
    private Date crmLetterDate;
    private String crmSheetNo;
    private boolean crmChecked;
    public EmpCandidateProceduresDTO() {
        super();
    }

    public EmpCandidateProceduresDTO(EmpCandidateProceduresEntity empCandidateProceduresEntity) {
        EmpEntityConverter.getEmpCandidateProceduresDTO(empCandidateProceduresEntity);
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setPostponeDate(Date postponeDate) {
        this.postponeDate = postponeDate;
    }

    public Date getPostponeDate() {
        return postponeDate;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setEmpReasonDataDTO(IEmpReasonDataDTO empReasonDataDTO) {
        this.empReasonDataDTO = empReasonDataDTO;
    }

    public IEmpReasonDataDTO getEmpReasonDataDTO() {
        return empReasonDataDTO;
    }

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO) {
        this.empCandidatesDTO = empCandidatesDTO;
    }

    public IEmpCandidatesDTO getEmpCandidatesDTO() {
        return empCandidatesDTO;
    }

    public void setProcedureResultsDTO(IProcedureResultsDTO procedureResultsDTO) {
        this.procedureResultsDTO = procedureResultsDTO;
    }

    public IProcedureResultsDTO getProcedureResultsDTO() {
        return procedureResultsDTO;
    }

    public void setHireProceduresDTO(IHireProceduresDTO hireProceduresDTO) {
        this.hireProceduresDTO = hireProceduresDTO;
    }

    public IHireProceduresDTO getHireProceduresDTO() {
        return hireProceduresDTO;
    }

    public String getProcedureResultKey() {
        if (this.procedureResultsDTO != null) {
            return procedureResultsDTO.getCode().getKey();
        }
        return null;
    }

    public void setProcedureResultKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
            IProcedureResultsEntityKey entityKey =
                EmpEntityKeyFactory.createProcedureResultsEntityKey(Long.parseLong(key));
            this.procedureResultsDTO = new ProcedureResultsDTO();
            procedureResultsDTO.setCode(entityKey);
        } else {
            this.procedureResultsDTO = null;
        }
    }

    public void setEmpReasonDataKey(String key) {

        try {
            if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE)) {
                String[] partsStringArray = key.split("\\*");
                IEmpReasonDataEntityKey entityKey =
                    EmpEntityKeyFactory.createEmpReasonDataEntityKey(Long.parseLong(partsStringArray[0]),
                                                                     Long.parseLong(partsStringArray[1]));
                this.empReasonDataDTO = EmpDTOFactory.createEmpReasonDataDTO();
                empReasonDataDTO.setCode(entityKey);
            } else {
                this.procedureResultsDTO = null;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public String getEmpReasonDataKey() {
        return null;
    }


    public void setCrmStatusCode(String crmStatusCode) {
        this.crmStatusCode = crmStatusCode;
    }

    public String getCrmStatusCode() {
        return crmStatusCode;
    }

    public void setCrmLetterNo(String crmLetterNo) {
        this.crmLetterNo = crmLetterNo;
    }

    public String getCrmLetterNo() {
        return crmLetterNo;
    }

    public void setCrmLetterDate(Date crmLetterDate) {
        this.crmLetterDate = crmLetterDate;
    }

    public Date getCrmLetterDate() {
        return crmLetterDate;
    }

    public void setCrmSheetNo(String crmSheetNo) {
        this.crmSheetNo = crmSheetNo;
    }

    public String getCrmSheetNo() {
        return crmSheetNo;
    }

    public void setCrmChecked(boolean crmChecked) {
        this.crmChecked = crmChecked;
    }

    public boolean isCrmChecked() {
        return crmChecked;
    }
}
