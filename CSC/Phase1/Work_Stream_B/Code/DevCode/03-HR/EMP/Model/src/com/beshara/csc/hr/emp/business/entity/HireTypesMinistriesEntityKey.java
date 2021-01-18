package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.EntityKey;


public class HireTypesMinistriesEntityKey extends EntityKey implements IHireTypesMinistriesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long hirtypeCode;
    private Long minCode;


    public HireTypesMinistriesEntityKey() {
        super();
    }

    public HireTypesMinistriesEntityKey(Long hirtypeCode, Long minCode) {
        super(new Object[] { hirtypeCode, minCode});
        this.hirtypeCode = hirtypeCode;
        this.minCode = minCode;

    }

    public int hashCode() {
        return super.hashCode();
    }


    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }
}
