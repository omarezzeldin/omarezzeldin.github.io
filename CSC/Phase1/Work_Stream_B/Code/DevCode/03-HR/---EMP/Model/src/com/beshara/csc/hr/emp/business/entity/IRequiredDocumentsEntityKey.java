package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;


public interface IRequiredDocumentsEntityKey extends IEntityKey {
    public int hashCode();

    public Long getHirtypeCode();

    public Long getDoctypeCode();

}
