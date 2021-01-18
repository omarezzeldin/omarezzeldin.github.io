package com.beshara.csc.hr.emp.business.shared;

import com.beshara.csc.sharedutils.business.util.IEMPConstant;

public interface IEMPConstants extends IEMPConstant {


    public static final String EMP_RESOURCES = "com.beshara.csc.hr.emp.presentation.resources.emp";

    public static final String SELECTION_BEAN_NAME = "selectionListBean";

    public static final String COLUMN_NAME_HR_EMP_EMPLOYEES_CIVIL_ID = "HR_EMP_EMPLOYEES.CIVIL_ID";
    public static final String COLUMN_NAME_HR_EMP_EMPLOYEES_FULL_NAME = "FULL_NAME";

    public static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FIRST_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME";
    public static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_SECOND_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME";
    public static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_THIRD_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME";
    public static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_LAST_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.LAST_NAME";
    public static final String COLUMN_NAME_INF_KWT_CITIZENS_RESIDENTS_FAMILY_NAME =
        "INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME";


    //    HIRE TYPES
    public static final Long EMP_HIRE_TYPE_ACTIVE_STATUS = Long.valueOf(1);
    public static final Long _EMP_ACTIVE_STATUS = Long.valueOf(1);
    public static final Long EMP_HIRE_TYPE_TREE_LEVEL_ONE = Long.valueOf(1);
    public static final Long EMP_HIRE_TYPE_CONDITION_OPTIONAL = Long.valueOf(1);
    public static final Long EMP_HIRE_TYPE_INACTIVE_STATUS = Long.valueOf(0);
    public static final Long _EMP_NOT_ACTIVE_STATUS = Long.valueOf(0);
    public static final Long EMP_HIRE_TYPE_CONDITION_NOT_OPTIONAL = Long.valueOf(0);
    public static final Long EMP_HIRE_TYPE_RE_EMPLOYEE = Long.valueOf(4);
    public static final Long EMP_HIRE_TYPE_DATA_LOADING = Long.valueOf(9);


    public static final Long EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
    public static final Long EMP_STATUS_DELEGATION_OUT_TO = Long.valueOf(2); //منقول من الجهة
    public static final Long EMP_STATUS_END_JOB = Long.valueOf(9); //منهي خدمته
    public static final Long EMP_STATUS_DELEGATION_INSIDE = Long.valueOf(3); //منتدب داخلياً
    public static final Long EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
    public static final Long EMP_STATUS_IN_DELEGATION = Long.valueOf(5); //في بعثة
    public static final Long EMP_STATUS_IN_GIVING = Long.valueOf(6); //في منحة
    public static final Long EMP_STATUS_IN_VAC = Long.valueOf(7); //في اجازة
    public static final Long EMP_STATUS_PRESONER = Long.valueOf(8); //اسير/مفقود
    public static final Long EMP_STATUS_DELEGATION_OUT_FROM_IT = Long.valueOf(10); //منتدب خارجياً منها
    public static final Long EMP_STATUS_DELEGATION_OUT_TO_IT = Long.valueOf(11); //منتدب خارجياُ اليها
    public static final Long EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد


    public static final Long _EMP_STAGE_WAITING_EMPLOYMENT_DECISION = Long.valueOf(2);
    public static final Long _EMP_CENTERAL_HIRE_TYPE = Long.valueOf(2);
    public static final Long _EMP_CANDIDATE_FOR_MINISTRY = Long.valueOf(3);
    public static final Long _EMP_REEMPLOYEMENTS = Long.valueOf(4);
    public static final Long _EMP_STAGE_WAITING_EMPLOYMENT = Long.valueOf(5);
    public static final Long _EMP_STATUS_CANDIDATE = Long.valueOf(12);
    public static final Long _EMP_NOMINATED_ACTIVE_FLAG = Long.valueOf(-1);
    public static final Long _EMP_STAGE_WAITING_EXECUTION = Long.valueOf(7);

    public static final Long EMP_ACTIVE_FLAG_NON = Long.valueOf(-1);
    public static final Long EMP_ACTIVE_FLAG = Long.valueOf(1);
    public static final Long EMP_CANDIDATE_HIRE_TYPE = Long.valueOf(2);


    public static final String PAGE_ID_FROM_CRS = "1";
    public static final String PAGE_ID_JOB_NAME_AND_FIN_DEGREE_ACCEPTED = "2";
    public static final String PAGE_ID_JOB_NAME_REQUIRED = "3";
    public static final String PAGE_ID_FIN_DEGREE_REQUIRED = "4";

    // Hire Stages
    public static final String HIRE_STAGE_COMPLETING_INFO_INPROGRESS = "1"; //جارى استكمال بيانات التعيين
    public static final String HIRE_STAGE_RELEASE_DECISION_INPROGRESS = "2"; // جارى اصدار قرار التعيين
    public static final String HIRE_STAGE_IMPLEMENT_JOB_INPROGRESS = "5"; // جارى تن�?يذ التعيين
    public static final String HIRE_STAGE_IMPLEMENT_DIRECT_WORK_INPROGRESS = "7"; //جارى تن�?يذ مباشرة العمل
    public static final String HIRE_STAGE_JOB_ASSIGNED = "3"; //تم التعيين
    public static final String HIRE_STAGE_SUGGEST_JOB_DEGREE_AND_NAME_INPROGRESS = "8"; //جارى اقتراح مسمى وظي�?ى ودرجة
    public static final String HIRE_STAGE_REVIEW_ORDER_IN_SELECTION_INPROGRESS =
        "9"; //جارى مراجعة الطلب بإدارة الاختيار
    public static final String HIRE_STAGE_FIN_DEGREE_REQUIRED_INPROGRESS = "10"; // جارى استي�?اء الدرجة الماليه
    public static final String HIRE_STAGE_JOB_NAME_REQUIRED_INPROGRESS = "11"; // جارى استي�?اء المسمى الوظي�?ى
    public static final String HIRE_STAGE_JOB_NAME_AND_DEGREE_ACCEPTRD =
        "13"; // تم استي�?اء المسمى الوظي�?ى والدرجة المالية
    public static final String HIRE_STAGE_REJECTED_BY_JOBS_ARRANGEMENT = "14"; // تم رد الطلب من ادارة توصي�? الوظائ�?
    public static final String HIRE_STAGE_JOB_NAME_AND_FIN_DEGREE_ACCEPTED =
        "15"; // تم اعتماد المسمى الوظي�?ى والدرجة المالية
    public static final String HIRE_STAGE_REJECTED_BY_FATWA = "16"; // تم رد الطلب من ادارة توصي�? الوظائ�?
    public static final String HIRE_STAGE_DEMAND_RESPONSE_FROM_DEPARTMENT_OF_CHOICE =
        "12"; // تم رد الطلب من ادارة الاختيار
    public static final Long EXTA_DATA_ACCEPTED_FIN_DEGREE = 14L; // الدرجة المعتمدة
    public static final int HIRE_SUGG_JOB_AND_DEGREE = 21;
    

    public static final Long EMP_PROCEDURE_ORDER_FIRST = Long.valueOf(1);

    //saL constants
    public static final Long ELEMENT_GUIDE_TYPE_RAISE = Long.valueOf(13);
    public static final Long ELEMENT_GUIDE_TYPE_SOCIAL_RAISE = Long.valueOf(4);
    public static final Long _SALARY_ELEMENT_RATIO = Long.valueOf(100);
    public static final Long _SALARY_ELEMENT_SERIAL = Long.valueOf(1);


    public static final Long EMP_NATIONALITY_KWT = Long.valueOf(101);


    public static final Long EMP_CANCEL_Candidate = Long.valueOf(-2);
    public static final Long EMP_HSTAGE_CANCEL_Candidate = Long.valueOf(4);


    public static final int HIRE_STAGE_SUGGEST_JOB_DEGREE = 8; //جارى اقتراح مسمى وظي�?ى ودرجة
    public static final int ACCEPT_JOB_AND_NAME_IN_PROGRESS = 9111013; //جارى اعتماد الظي�?ه والدرجه
    public static final int REJECT_ORDER_TO_WORK_MINISTRY = 121416; //رد الطلب لجهة العمل
    public static final int HIRE_STAGE_JOB_NAME_AND_DEGREE_ACCEPTED = 15; //تم اعتماد الوظي�?ة والدرجة


    public static final int EXPERIENCE_CHECK = 0;
    public static final int HAS_EXPERIENCE = 1;
    public static final int HAS_NOT_EXPERIENCE = 2;

    public static final Long HIRE_STATUS_HIRED_FROM_DB_FUNCTION = Long.valueOf(1);

    public static final Long ORG_CAT_CODE_ATT_BGT = 3L; //الهيئات ذات الميزانيات الملحقة
    public static final Long ORG_CAT_CODE_SPR_BGT = 4L; //المؤسسات ذات الميزانية المستقلة
    public static final Long ORG_CAT_CODE_MIN_BGT = 1L; //ميزانية الجهة
    public static final Long ORG_CAT_CODE_ADM_BGT = 2L; // ميزانية الادارة

    public static final Long BGT_TYPE_CODE_TAKMELY_AWAL = 1L;
    public static final Long BGT_TYPE_CODE_MINISTRY_BGT = 2L;
    public static final Long BGT_TYPE_CODE_TAKMELY_TANY = 3L;

    public static final Long EX_DATA_BGT_TYPE = 15L;
    public static final Long EX_DATA_JOB_ACCEPTED = 13L;
    public static final Long EX_DATA_REWARD_SUGGESTED = 17L;
    public static final Long EX_DATA_REWARD_ACCEPTED = 21L;

    public static final Long EMPLOYEE_RECORD_DESC = 1L;

    public static final Long EMPLOYEE_HIRE_STAGE_WORK = 3L;

    public static final Long EX_DATA_JOB_SUGGESTED = 4L;
    public static final Long EX_DATA_CADER_SUGGESTED = 12L;
    public static final Long EX_DATA_CADER_ACCEPTED = 14L;
    public static final Long EX_DATA_MIN_NOTES = 5L;

    public static final String APP_MODULE_CODE_EMP = "190";

    public static final Long EMP_STATUS_FREEZED = Long.valueOf(13);

    public static final Long HR_EMP_REASON_TYPES_EMPLOYMENT_DEFER = Long.valueOf(1);
    public static final Long HR_EMP_REASON_TYPES_CANCEL_EOS_REQ = Long.valueOf(2);
    public static final Long HR_EMP_REASON_TYPES_REJECT_EOS_REQ = Long.valueOf(3);
    public static final Long HR_EMP_REASON_TYPES_REJECT_VAC_REQ = Long.valueOf(4);
    public static final Long HR_EMP_REASON_TYPES_REJECT_MEDICAL_REVIEW_REQ = Long.valueOf(5);
    public static final Long HR_EMP_REASON_TYPES_REJECT_INT_MOV_REQ = Long.valueOf(6);
    public static final Long HR_EMP_REASON_TYPES_REJECT_EXT_MOV_REQ_BY_MIN = Long.valueOf(7);
    public static final Long HR_EMP_REASON_TYPES_TERMINATE_INT_DLG = Long.valueOf(8);
    public static final Long HR_EMP_REASON_TYPES_TERMINATE_EXT_DLG = Long.valueOf(9);
    public static final Long HR_EMP_REASON_TYPES_REJECT_INT_DLG_REQ = Long.valueOf(10);
    public static final Long HR_EMP_REASON_TYPES_REJECT_EXT_DLG_REQ_BY_MIN = Long.valueOf(11);
    public static final Long HR_EMP_REASON_TYPES_REJECT_EXT_MOV_REQ_BY_DWN = Long.valueOf(12);
    public static final Long HR_EMP_REASON_TYPES_REJECT_EXT_DLG_REQ_BY_DWN = Long.valueOf(13);
    public static final Long HR_EMP_REASON_TYPES_REJECT_MOV_AND_DLG_REQ = Long.valueOf(14);
    public final static Long HR_EMP_REASON_TYPES_REJECT_EMP_FREEZ = Long.valueOf(15);

    // Cand_Status
    public final static Long EMP_CAND_STATUS_EMPLOEMENT = Long.valueOf(1);
    public final static Long EMP_CAND_STATUS_CANDITATE = Long.valueOf(2);
    public final static Long EMP_CAND_STATUS_REFUSE_CANDITATE = Long.valueOf(3);

    public static final Long EMP_CND_SEQ = Long.valueOf(1);
    public static final String TABLE_HR_EMP_HIRE_TYPES = "HR_EMP_HIRE_TYPES";
    public final static String TRX_HR_EMP_HIRE_TYPES = "Hire type join condition trancation";

    Long CENTRAL_EMPLOYEMENT = 2L;
    Long CONTRACTS = 1L;

    /////////// new hire stage
    public static final String HIRE_STAGE_REVIEW_ORDER_IN_DEWAN_INPROGRESS = "9"; //جاري مراجعة الطلب بالديوان =  9

    public static final int FATWA_MANAGER = 4;
    public static final int JOBS_ARRANGMENT_MANAGER = 3;

    public static final Long CONTRACT_TYPE = Long.valueOf(6);
    public static final Long ESTANA_TYPE = Long.valueOf(7);


    public static final Long TRANSFERE_TYPE_EXTRA_DATA = Long.valueOf(1); //نوع التحويل وزارة الصحه
    public static final String EXTRA_DATA_VALUE_ONE = "1";

    public static final Long EmpHireStageMakeingHireDecision = Long.valueOf(2);
    public static final Long EmpHireStageDoneHireDecision = Long.valueOf(5);
    
    public static final Long STATUS_ONE = Long.valueOf(1);
    public static final Long KUWAITY = Long.valueOf(101);
    
    public static final Long TRXTYPE_30 = Long.valueOf(30);
    
    public static final Long REJECTION_REASON = Long.valueOf(4);
    
    public static final Long REJECTION_REASON_CODE = Long.valueOf(5);
    
    
    public static final Long CND_SEQ_ONE = Long.valueOf(1);
    public static final Long REG_STATUS_ZERO = Long.valueOf(0);
    public static final Long EXT_DATA_TYPE_NOTES_BY_CIVIL_SERVICE = Long.valueOf(16);
    public static final String REJECTED_BY_CIVIL_SERVICE= "17";
    
    public static final Long Active_Status_Extra_Data = Long.valueOf(1);
    public static final Long Emp_With_Status_END_Service = Long.valueOf(9);
    public static final String WRK_CENTER_ISOLATED = "02133";
    public static final String DATA_FILTERATION_EXCEPTION_MSG= "DATA_FILTERATION_EXCEPTION";
    
    public static final Long Attachment_FATWA  = 51L;
    public static final Long Attachment_jobArrangement  = 50L;
    public static final Long Attachment_order  = 49L;
    
    //pifss webservice conistant
    public static String USER_NAME = "PIFSS_User", PASSWORD = "PIFSS_Passw0rd#1234#";


    public static String BASE_URL = "https://sbservices.csc.gov.kw/ProxyServices/";                                          
    public static String BASE_URL_TEST = "http://sbservices.csc.gov.kw/CSCTest/ProxyServices/";  
    public static String  REGISTRATION = "PIFSSRegistrationOrReEnrollmentCustomer_ProxySrv";
    public static String  TERMINATION = "PIFSSTerminationCustomer_ProxySrv";
    public static String REGISTER_URL = BASE_URL + REGISTRATION;
    public static String REGISTER_URL_TEST = BASE_URL_TEST + REGISTRATION;
    public static String TERMINATE_URL = BASE_URL + TERMINATION;
    public static String TERMINATE_URL_TEST = BASE_URL_TEST + TERMINATION;
    public static final Long JOB_FROM_MIN = 345L;
}



