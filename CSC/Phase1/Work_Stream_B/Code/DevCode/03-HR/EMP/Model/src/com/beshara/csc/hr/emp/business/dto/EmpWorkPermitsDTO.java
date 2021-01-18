package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpWorkPermitsEntity;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;

import java.sql.Date;

public class EmpWorkPermitsDTO extends EmpDTO implements IEmpWorkPermitsDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    private Long civilId;
    private Date fromDate;
    private Date untilDate;
    private Long auditStatus;
    private Long tabrecSerial;
    private IEmployeesDTO employeesDTO;
    private IMinistriesDTO ministryDTO;
    private Long minCode;
    private Long realCivilId;


    public EmpWorkPermitsDTO() {
        super();
    }

    public EmpWorkPermitsDTO(EmpWorkPermitsEntity empWorkPermitsEntity) {
        this.setCode(EmpEntityKeyFactory.createEmpWorkPermitsEntityKey(empWorkPermitsEntity.getSerial()));
        this.minCode = empWorkPermitsEntity.getMinCode();
        this.fromDate = empWorkPermitsEntity.getFromDate();
        this.untilDate = empWorkPermitsEntity.getUntilDate();
        setCreatedBy(empWorkPermitsEntity.getCreatedBy());
        setCreatedDate(empWorkPermitsEntity.getCreatedDate());
        setLastUpdatedBy(empWorkPermitsEntity.getLastUpdatedBy());
        setLastUpdatedDate(empWorkPermitsEntity.getLastUpdatedDate());
        this.auditStatus = empWorkPermitsEntity.getAuditStatus();
        this.tabrecSerial = empWorkPermitsEntity.getTabrecSerial();

        try {
            if (empWorkPermitsEntity.getEmployeesEntity() != null) {
                employeesDTO = EmpDTOFactory.createEmployeesDTO(true, empWorkPermitsEntity.getEmployeesEntity());
                employeesDTO.setMinistriesDTO((IMinistriesDTO)OrgClientFactory.getMinistriesClient().getSimpleMinistryDTO(employeesDTO.getMinCode()));

            }
            
            } catch (Exception e) {
            e.printStackTrace();
            }
        try {
            if (empWorkPermitsEntity.getMinCode() != null) {
                ministryDTO =
                        (IMinistriesDTO)OrgClientFactory.getMinistriesClient().getSimpleMinistryDTO(empWorkPermitsEntity.getMinCode());
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
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

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setMinistryDTO(IMinistriesDTO ministryDTO) {
        this.ministryDTO = ministryDTO;
    }

    public IMinistriesDTO getMinistryDTO() {
        return ministryDTO;
    }
}
