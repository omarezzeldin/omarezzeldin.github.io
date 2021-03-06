package com.beshara.csc.hr.emp.presentation.backingbean.governmentalemployeedatarevision;

import com.beshara.base.entity.IEntityKey;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IInfCitizensResidentsDataDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;


public class ThirdStepBean extends LookupMaintainBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "govEmpThirdStepBean";
    private static final String NAVIGATION_KEY = "govempthirdstep";

    private String passportIssuanceCountry;
    private String streetName;

    private IInfCitizensResidentsDataDTO citizensResidentsDataDTO = InfDTOFactory.createInfCitizensResidentsDataDTO();
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

    public ThirdStepBean() {
        setContent1Div("module_tabs_cont"); //divContent1Dynamic if fixed use divContent1Fixed
    }

    public static ThirdStepBean getInstance() {
        return (ThirdStepBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        return app;
    }

    public String init() {
        try {
            kwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(maintainBean.getRealCivilId());
        } catch (Throwable e) {
            e.printStackTrace();
            kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        }
        if (kwtCitizensResidentsDTO != null) {
            if (kwtCitizensResidentsDTO.getPassportCntryCode() != null) {
                try {
                    IEntityKey key =
                        InfEntityKeyFactory.createCountriesEntityKey(kwtCitizensResidentsDTO.getPassportCntryCode());
                    passportIssuanceCountry = InfClientFactory.getCountriesClient().getById(key).getName();
                } catch (Throwable e) {
                    e.printStackTrace();
                    passportIssuanceCountry = null;
                }
            }
            if (kwtCitizensResidentsDTO.getStreetCode() != null) {
                try {
                    IEntityKey key =
                        InfEntityKeyFactory.createKwStreetsEntityKey(kwtCitizensResidentsDTO.getStreetCode());
                    streetName = InfClientFactory.getKwStreetsClient().getById(key).getName();
                } catch (Throwable e) {
                    e.printStackTrace();
                    streetName = null;
                }
            }
        }
        try {
            citizensResidentsDataDTO =
                    (IInfCitizensResidentsDataDTO)InfClientFactory.getInfCitizensResidentsDataClient().getByCivilID(maintainBean.getRealCivilId());
        } catch (Throwable e) {
            e.printStackTrace();
            citizensResidentsDataDTO = InfDTOFactory.createInfCitizensResidentsDataDTO();
        }
        return NAVIGATION_KEY;
    }

    public void getAll() throws DataBaseException {
    }

    public void setCitizensResidentsDataDTO(IInfCitizensResidentsDataDTO citizensResidentsDataDTO) {
        this.citizensResidentsDataDTO = citizensResidentsDataDTO;
    }

    public IInfCitizensResidentsDataDTO getCitizensResidentsDataDTO() {
        return citizensResidentsDataDTO;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setPassportIssuanceCountry(String passportIssuanceCountry) {
        this.passportIssuanceCountry = passportIssuanceCountry;
    }

    public String getPassportIssuanceCountry() {
        return passportIssuanceCountry;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetName() {
        return streetName;
    }
}
