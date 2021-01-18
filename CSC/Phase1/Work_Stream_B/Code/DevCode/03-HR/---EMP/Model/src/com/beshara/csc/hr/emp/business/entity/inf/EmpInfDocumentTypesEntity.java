package com.beshara.csc.hr.emp.business.entity.inf;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "INF_DOCUMENT_TYPES")
public class EmpInfDocumentTypesEntity implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DOCTYPE_CODE", nullable = false)
    private Long doctypeCode;
    @Column(name = "DOCTYPE_NAME", nullable = false, length = 400)
    private String doctypeName;
   
    public EmpInfDocumentTypesEntity() {
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }

    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public String getDoctypeName() {
        return doctypeName;
    }

    public void setDoctypeName(String doctypeName) {
        this.doctypeName = doctypeName;
    }

   
}
