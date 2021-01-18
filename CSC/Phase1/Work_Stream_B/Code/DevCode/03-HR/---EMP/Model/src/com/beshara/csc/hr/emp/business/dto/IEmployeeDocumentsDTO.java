package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Date;


public interface IEmployeeDocumentsDTO extends IEmpDTO {
    public Long getStatus();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setStatus(Long status);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setEmployeesDTO(IBasicDTO employeesDTO);

    public IBasicDTO getEmployeesDTO();

    public void setDocumentTypeDTO(IBasicDTO documentTypeDTO);

    public IBasicDTO getDocumentTypeDTO();

    public boolean isBooleanStatus();

    public void setBooleanStatus(boolean _status);
    
     void setAttachmentFile(String attachmentFile);
     String getAttachmentFile();

     void setAttachmentDesc(String attachmentDesc);

     String getAttachmentDesc();

     void setAttachmentDate(Date attachmentDate);

     Date getAttachmentDate();

}
