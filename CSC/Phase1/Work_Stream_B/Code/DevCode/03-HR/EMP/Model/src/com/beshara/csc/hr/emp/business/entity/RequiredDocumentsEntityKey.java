package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class RequiredDocumentsEntityKey extends EntityKey implements IRequiredDocumentsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hirtypeCode;
    private Long doctypeCode;
    public RequiredDocumentsEntityKey() {        super();
    }    public RequiredDocumentsEntityKey(Long hirtypeCode, Long doctypeCode) {        super(new Object[] { hirtypeCode, doctypeCode });
        this.hirtypeCode = hirtypeCode;
        this.doctypeCode = doctypeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getHirtypeCode() {        return hirtypeCode;
    }    public Long getDoctypeCode() {        return doctypeCode;
    }}
