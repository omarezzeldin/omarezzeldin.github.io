package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;


public interface IEmpReasonDataEntityKey extends IEntityKey {

    public int hashCode();

    public Long getRestypeCode();

    public Long getResdatSerial();
}
