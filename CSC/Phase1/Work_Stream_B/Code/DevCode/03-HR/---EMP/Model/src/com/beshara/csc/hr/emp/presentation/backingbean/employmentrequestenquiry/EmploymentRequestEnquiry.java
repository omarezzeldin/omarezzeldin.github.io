package com.beshara.csc.hr.emp.presentation.backingbean.employmentrequestenquire;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.empenquiry.IEmpReqEnquiryDTO;
import com.beshara.csc.hr.emp.presentation.backingbean.employmentrequestenquiry.EmployeeListOfValues;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class EmploymentRequestEnquiry extends LookUpBaseBean {
    private String concernedMinCode;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    public static final String BEAN_NAME = "employmentRequestEnquiry";
    private String selectedMinistry;
       private String selectedCategory;
       private List<IBasicDTO> categoryList = new ArrayList<IBasicDTO>();
       private List<IBasicDTO> ministryList = new ArrayList<IBasicDTO>();
       private boolean validCivilId = false;
       private boolean civilExist = false;
       private String civilName;
       private Long civilId;
    private IEmpReqEnquiryDTO searchDTO = EmpDTOFactory.createEmpReqEnquiryDTO();
    public EmploymentRequestEnquiry(){
        setUsingPaging(true);
        setUsingBsnPaging(true);
        setSaveSortingState(true);
        setEmpListOfValues(new EmployeeListOfValues());
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowEmpSrchDiv(true);
        return app;
    }
    
    
    public void filterDataTable() throws DataBaseException {

        if (isUsingPaging()) {
            generatePagingRequestDTO(BEAN_NAME, "filterDataTableWithPaging");
            resetPageIndex();
        } else {
            setMyTableData(new ArrayList());
        }


        getSelectedDTOS().clear();
    }
    
    public PagingResponseDTO getAllWithPaging(PagingRequestDTO pagingRequest) {

        return new PagingResponseDTO(new ArrayList());

    }

    public PagingResponseDTO filterDataTableWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = getCandDataithPaging(pagingRequest);

        PagingResponseDTO pagingResponseDTO = null;
        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO = new PagingResponseDTO(bsnResponseDTO.getBasicDTOList());

      
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
             
        }

        return pagingResponseDTO;
    }

    private com.beshara.base.paging.impl.PagingResponseDTO getCandDataithPaging(PagingRequestDTO pagingRequestDTO) {

        int pageIndex = getCurrentPageIndex();

        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));
        
            bsnPagingRequestDTO.setCountRequired(true);
        

        if (isSorting()) {
            bsnPagingRequestDTO.setSortAscending(pagingRequestDTO.isSortAscending());

            List<String> sortingColumnList = new ArrayList<String>();
            sortingColumnList.add(pagingRequestDTO.getBsnSortingColumnName());
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }

      
        if(getConcernedMinCode() != null && !"".equals(getConcernedMinCode())){
            searchDTO.setConcernedMinCode(Long.valueOf( getConcernedMinCode()));    
        }else{
                searchDTO.setConcernedMinCode(null);
            }
        if(getSelectedMinistry() != null && !getSelectedMinistry().equals("")){
        searchDTO.setMinCode(Long.valueOf(getSelectedMinistry()));
        }else{
                searchDTO.setMinCode(null);   
            }
        
        if(getCivilId() != null && validCivilId){
                searchDTO.setRealCivilId(getCivilId());  
        }else{
                searchDTO.setRealCivilId(null);
            }
        
        searchDTO.setPagingRequestDTO(bsnPagingRequestDTO);

        try {
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)EmpClientFactory.getEmpCandidatesClient().getAllCandidateDataWithPaging(searchDTO);
        } catch (NoResultException ne) { //this means no search results found
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().setErrMsgValue(e.getMessage());
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }


    public void setConcernedMinCode(String concernedMinCode) {
        this.concernedMinCode = concernedMinCode;
    }

    public String getConcernedMinCode() {
        return concernedMinCode;
    }
    public void filterByCategory(ActionEvent event) {
          setSelectedMinistry(null);
          if (selectedCategory != null && !(selectedCategory.equals(""))) {
              try {
                  ministryList = SecClientFactory.getUsersClient().getMinistries(CSCSecurityInfoHelper.getGroupCode(),Long.valueOf(selectedCategory));
              } catch (SharedApplicationException e) {
                  e.printStackTrace();
                  ministryList = new ArrayList<IBasicDTO>();
              }
          } else {
              ministryList = new ArrayList<IBasicDTO>();
          }
      }
      
      public List<IBasicDTO> getCategoryList() {
          if (categoryList == null || categoryList.size() == 0) {
              try {
                  categoryList = SecClientFactory.getUsersClient().getCategories(CSCSecurityInfoHelper.getGroupCode());
              } catch (SharedApplicationException e) {
                  e.printStackTrace();
              }
          }

          return categoryList;
      }
      public void showSearchForEmployeeDiv() {
          EmployeeListOfValues employeeListOfValuesBean = (EmployeeListOfValues)getEmpListOfValues();
          employeeListOfValuesBean.setEmpEmployeeSearchDTO(searchDTO);
          employeeListOfValuesBean.setEmpListOfValuesStyle("m2mEditDivClass");
          employeeListOfValuesBean.setReturnMethodName("employmentRequestEnquiry.returnMethodAction");
          employeeListOfValuesBean.getOkCommandButton().setReRender("mainDataEmpPanel,searchBtnPnlgrd,OperationBar,scriptpanel");
          employeeListOfValuesBean.resetData();
          employeeListOfValuesBean.setResetDivAfterClose(true);
      }
      
      public void returnMethodAction() {
          if (getEmpListOfValues().getSelectedDTOS() != null && getEmpListOfValues().getSelectedDTOS().get(0) != null &&
              getEmpListOfValues().getSelectedDTOS().get(0).getCode() != null) {
              validCivilId = true;
              civilExist = true;
              try {
             IEmpReqEnquiryDTO empCandidatesDTO=(IEmpReqEnquiryDTO)getEmpListOfValues().getSelectedDTOS().get(0);
              setCivilId(empCandidatesDTO.getRealCivilId());
                  checkAvailable();
              } catch (Exception e) {
                  e.printStackTrace();
              } 
          }
      }
      public void checkAvailable() throws DataBaseException {
          validCivilId = false;
          civilExist = false;
          if (getCivilId() != null) {
              civilExist = GlobalValidation.isValidCivilId(civilId);
              if (civilExist) {
                      try {
                          searchDTO.setRealCivilId(civilId);
                          setCivilName(EmpClientFactory.getEmpCandidatesClient().getCandidateName(searchDTO));
                          if(!getCivilName().equals("")){
                                  validCivilId = true;
                          } else{
                                  setCivilName(null);
                                  validCivilId = false;
                                  searchDTO.setRealCivilId(null);
                                  searchDTO.setMinCode(null);
                                  return;
                              }
                       
                      } catch (Exception e) {
                          setCivilName(null);
                          validCivilId = false;
                          searchDTO.setRealCivilId(null);
                          searchDTO.setMinCode(null);
                          return;
                      }
                  }

              }
         
      }
    public void reSetData(ActionEvent e){
            e=null;
            civilId=null;
            validCivilId=false;
            civilExist=false;
            civilName="";
            searchDTO.setName(null);
            searchDTO.setRealCivilId(null);
            searchDTO.setMinCode(null);
        }

      public void setSelectedMinistry(String selectedMinistry) {
          this.selectedMinistry = selectedMinistry;
      }

      public String getSelectedMinistry() {
          return selectedMinistry;
      }

      public void setSelectedCategory(String selectedCategory) {
          this.selectedCategory = selectedCategory;
      }

      public String getSelectedCategory() {
          return selectedCategory;
      }

      public void setCategoryList(List<IBasicDTO> categoryList) {
          this.categoryList = categoryList;
      }

    
      public void setMinistryList(List<IBasicDTO> ministryList) {
          this.ministryList = ministryList;
      }

      public List<IBasicDTO> getMinistryList() {
          return ministryList;
      }

      public void setValidCivilId(boolean validCivilId) {
          this.validCivilId = validCivilId;
      }

      public boolean isValidCivilId() {
          return validCivilId;
      }

      public void setCivilExist(boolean civilExist) {
          this.civilExist = civilExist;
      }

      public boolean isCivilExist() {
          return civilExist;
      }

      public void setCivilName(String civilName) {
          this.civilName = civilName;
      }

      public String getCivilName() {
          return civilName;
      }

      public void setCivilId(Long civilId) {
          this.civilId = civilId;
      }

      public Long getCivilId() {
          return civilId;
      }

    public void setSearchDTO(IEmpReqEnquiryDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public IEmpReqEnquiryDTO getSearchDTO() {
        return searchDTO;
    }
}
