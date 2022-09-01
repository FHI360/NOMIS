/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class AppConstant implements Serializable
{
    public static int REPORTGENERATIONDISABLED_NUM=1;
    public static int REGULAR_SCHOOL_ATTENDANCE_YES_NUM=1;
    public static int REGULAR_SCHOOL_ATTENDANCE_NO_NUM=2;
    public static int REGULAR_SCHOOL_ATTENDANCE_NOTAPPLICABLE_NUM=0;
    public static String FACILITY_NAME="Facility";
    public static String COMMUNITY_NAME="Community";
    public static final int ENROLLMENTSETTING_COMMUNITY_NUM=1;
    public static final int ENROLLMENTSETTING_FACILITY_NUM=2;
    public static final int MAINVULNERABILITY_NUM=1;
    public static final int OTHERVULNERABILITY_NUM=0;
    public static final int VIRALLOAD_SUPPRESSED_VALUE_NUM=999;
    public static final int DAYSOFVIRALLOADELIGIBILITY_NUM=180;
    public static final int OLDER_OVC_STARTAGE_NUM=18;
    public static final int OLDER_OVC_ENDAGE_NUM=24;
    public static final int ADULT_STARTAGE_NUM=18;
    public static final int ADULT_ENDAGE_NUM=200;
    public static final String FIRSTREPORTROWCOLOUR="#FFFFFF";
    public static final String SECONDREPORTROWCOLOUR="#D7E5F2";
    public static final String HEALTHDOMAINCODE="HLT";
    public static final String SAFEDOMAINCODE="SFT";
    public static final String SCHDOMAINCODE="SCH";
    public static final String STABLEDOMAINCODE="STB";
    public static final int TRUE_OPTION_NUM=1;
    public static final int FALSE_OPTION_NUM=2;
    public static final String DEFAULTUID="xxxxxxxxxxx";
    public static final int YES_OPTION_NUM=1;
    public static final int NO_OPTION_NUM=2;
    public static final int MARITALSTATUS_SINGLE_NUM=1;
    public static final int MARITALSTATUS_SEPARATED_NUM=2;
    public static final int MARITALSTATUS_MARRIED_NUM=3;
    public static final int MARITALSTATUS_WIDOWED_NUM=4;
    public static final int MARITALSTATUS_DIVORCED_NUM=5;
    
    public static final int OCCUPATION_FORMALLYEMPLOYED_NUM=1;
    public static final int OCCUPATION_INFORMALLYEMPLOYED_NUM=2;
    public static final int OCCUPATION_SELFEMPLOYED_NUM=3;
    public static final int OCCUPATION_RETIREDPENSIONER_NUM=4;
    public static final int OCCUPATION_RETIREDNONPENSIONER_NUM=5;
    public static final int OCCUPATION_UNEMPLOYED_NUM=6;
    
    
    public static final String DEAULT_PARTNER_CODE="XXX";
    public static final String VULNERABILITYSTATUS_SUBTYPE="vulnerabilitystatus";
    public static final String MAIN_INDICATOR_TYPE="main indicator";
    public static final String HIVRISK_POINTOFUPDATE="hra";
    public static final String HOUSEHOLD_TYPE="Household";
    public static final String CAREGIVER_TYPE="Adult";
    public static final String OVC_TYPE="Child";
    public static final String SUPERUSER_ROLEID="superuser";
    public static final String DEFAULTGRPID="defaultgpId";
    public static final String DEFAULTGRP="defaultgrp";
    public static final String ENABLEDACCOUNTSTATUS="enabled";
    public static final String DISABLEDACCOUNTSTATUS="disabled";
    public static final String RECREATION_ACTIVITIES="Recreational activity";
    public static final String LINKED_TO_GOVT_POSTVIOLENCE="Linkage to Govt for post-violence offences";
    public static final String OVC_HIVPREV="Adolescent HIV prevention and sexual reproductive health services";
    public static final String HIV_POSITIVE="positive";
    public static final String HIV_NEGATIVE="negative";
    public static final String HIV_UNKNOWN="unknown";
    public static final String HIV_UNDISCLOSED="unknown";
    public static final String TRUEVALUE="true";
    public static final String FALSEVALUE="false";
    
    public static final int NUTRITIONSTATUS_OBESSE_NUM=1;
    public static final int NUTRITIONSTATUS_OVERWEIGHT_NUM=2;
    public static final int NUTRITIONSTATUS_NORMAL_NUM=3;
    public static final int NUTRITIONSTATUS_MILD_MALNUTRITION_NUM=4;
    public static final int NUTRITIONSTATUS_MODERATE_MALNUTRITION_NUM=5;
    public static final int NUTRITIONSTATUS_SEVERE_MALNUTRITION_NUM=6;
    public static final int NUTRITIONSTATUS_MALNOURISHED_ALL_NUM=7;
    
    public static final int ENROLLED_ON_TREATMENT_YES_NUM=1;
    public static final int ENROLLED_ON_TREATMENT_NO_NUM=2;
    public static final int ENROLLED_ON_TREATMENT_NOTAPPLICABLE=0;
    public static final int AGEUNIT_MONTH_NUM=1;
    public static final int AGEUNIT_YEAR_NUM=2;
    
    public static final int HIV_ALL_STATUS_NUM=0;
    public static final int HIV_POSITIVE_NUM=1;
    public static final int HIV_NEGATIVE_NUM=2;
    public static final int HIV_UNKNOWN_NUM=3;
    public static final int HIV_UNDISCLOSED_NUM=4;
    public static final int HIV_TEST_NOT_INDICATED_NUM=5;
    public static final int HIV_TEST_REQUIRED_NUM=6;
    public static final int HIV_RESULT_NOT_RECEIVED_NUM=7;
    public static final int HIV_UNKNOWN_NEGATIVE_NUM=8;
    
    
    public static final int CHILD_AT_RISK_NUM=1;
    public static final int CHILD_NOT_AT_RISK_NUM=2;
    public static final int CHILD_AT_LOW_RISK_NUM=4;
    public static final int CHILD_AT_HIGH_RISK_NUM=5;
    public static final int CHILD_NOT_SCREENED_NUM=9;
    //public static final int CHILD_REQUIRE_HIVTEST_NUM=1;
    //public static final int CHILD_DOES_NOT_REQUIRE_HIVTEST_NUM=2;
    //public static final int CHILD_PARENT_AT_RISK_NUM=1;
    
    
    public static final int HHE_POINTOFUPDATE_NUM=1;
    public static final int AHM_POINTOFUPDATE_NUM=2;
    public static final int OVC_POINTOFUPDATE_NUM=3;
    public static final int HIVSTATUS_POINTOFUPDATE_NUM=4;
    public static final int HIVRISK_POINTOFUPDATE_NUM=5;
    public static final int CHILDSERVICE_POINTOFUPDATE_NUM=6;
    public static final int FOLLOWUP_POINTOFUPDATE_NUM=7;
    public static final int HHS_POINTOFUPDATE_NUM=8;
    public static final int HHF_POINTOFUPDATE_NUM=9;
    
    
    
    public static final String HIV_TEST_NOT_INDICATED="unk_tni";
    public static final String HIV_TEST_NOT_DONE="unk_tnd";
    public static final String HIV_RESULT_NOT_DISCLOSED="unk_rnd";
    //public static final String HIV_STATUS_NOT_DISCLOSED="rtd";
    //refusedtodisclose
    public static final int NOTVULNERABLE_STARTVALUE=0;
    public static final int NOTVULNERABLE_ENDVALUE=6;
    public static final int VULNERABLE_STARTVALUE=7;
    public static final int VULNERABLE_ENDVALUE=13;
    public static final int MOREVULNERABLE_STARTVALUE=14;
    public static final int MOREVULNERABLE_ENDVALUE=21;
    public static final int MOSTVULNERABLE_STARTVALUE=22;
    public static final int MOSTVULNERABLE_ENDVALUE=50;
    
    public static final String NOTASSESSED_VULNERABLE_STATUS="Not assessed";
    public static final String MOREVULNERABLE_STATUS="More Vulnerable";
    public static final String VULNERABLE_STATUS="Vulnerable";
    public static final String MOSTVULNERABLE_STATUS="Most Vulnerable";
    
    
    public static final int HHUNIQUEID_LENGTH=17;
    public static final int CAREGIVERID_LENGTH=19;
    public static final int OVCID_LENGTH=23;
    public static final int HOUSEHOLD_TYPE_NUM=1;
    public static final int CAREGIVER_TYPE_NUM=2;
    public static final int OVC_TYPE_NUM=3;
    public static final int HOUSEHOLDHEAD_CAREGIVER_NUM=4;
    public static final int COMMUNITYCODE_LENGTH=15;
    public static final int CBOCODE_LENGTH=11;
    
    public static final String ACTIVE="active";
    public static final String GRADUATED="graduated";
    public static final String MIGRATED="migrated";
    public static final String LOST_TO_FOLLOWUP="Loss to follow-up";
    public static final String DIED="Known death";
    public static final String TRANSFERED="transfer";
    public static final String TRANSFERED_TO_PEPFAR="Transfered to PEPFAR program";
    public static final String TRANSFERED_TO_NONPEPFAR="Transfer to non PEPFAR program";
    public static final String AGED_OUT="age above 17";
    public static final String VOLUNTARILY_WITHDRAWN="voluntary withdrawal";
    public static final String INACTIVE="Inactive";
    public static final String EXITED_WITHOUT_GRADUATION="exited without graduation";
    public static final String REENROLLED_STATUS="RE-ENROLLED";
    
    public static final int EVER_ENROLLED_NUM=0;
    public static final int ACTIVE_NUM=1;
    public static final int GRADUATED_NUM=2;
    public static final int MIGRATED_NUM=3;
    public static final int LOST_TO_FOLLOWUP_NUM=4;
    public static final int DIED_NUM=5;
    public static final int TRANSFERED_PEPFAR_NUM=6;
    public static final int TRANSFERED_NONPEPFAR_NUM=7;
    public static final int AGED_OUT_NUM=8;
    public static final int VOLUNTARILY_WITHDRAWN_NUM=9;
    public static final int INACTIVE_NUM=10;
    public static final int EXITED_WITHOUT_GRADUATION_NUM=11;
    public static final int OVC_SERV_NUM=12;
    public static final int REENROLLED_NUM=13;
    public static final int CURRENTLY_ENROLLED_NUM=14;
    public static final int TRANSFERED_NUM=15;
    public static final int EXITEDFROMPROGRAM_NUM=16;
    public static final int ACTIVE_GRADUATED_NUM=17;
    
    public static final int GRADUATION_ASSESSMENT_SCORE=12;
    
    public static final String SUBDOMAIN_PSYCHOSOCIAL="psychosocial";
    public static final String SUBDOMAIN_HEALTH="health";
    public static final String SUBDOMAIN_NUTRITION="nutrition";
    public static final String SUBDOMAIN_EDUCATION="education";
    public static final String SUBDOMAIN_PROTECTION="protection";
    public static final String SUBDOMAIN_SHELTER="shelter";
    public static final String SUBDOMAIN_ECONOMICSTRENTHENINIG="economicStrengthening";
    
    public static final int SUBDOMAIN_PSYCHOSOCIAL_NUM=1;
    public static final int SUBDOMAIN_HEALTH_NUM=3;
    public static final int SUBDOMAIN_NUTRITION_NUM=2;
    public static final int SUBDOMAIN_EDUCATION_NUM=4;
    public static final int SUBDOMAIN_PROTECTION_NUM=5;
    public static final int SUBDOMAIN_SHELTER_NUM=6;
    public static final int SUBDOMAIN_ECONOMICSTRENTHENINIG_NUM=7;
    
    public static final String ENROLLMENT_POINTOFUPDATE="enrollment";
    public static final String SERVICE_POINTOFUPDATE="service";
    public static final String FOLLOWUP_POINTOFUPDATE="followup";
    public static final String HHE_POINTOFUPDATE="hhenrollment";
    public static final String HHS_POINTOFUPDATE="hhservice";
    public static final String HHF_POINTOFUPDATE="hhfollowup";
    public static String SYNC_DELETEDFILES="off";
    public static final String HIV_BIRTHREGUPDATE="off";
    public static final String AUTO_ACTION="auto";
    public static final int MARKEDFORDELETE=1;
    public static final int UNMARKFORDELETE=0;
    public final static String MONTH_SYMBOL="M";
    public final static String YEAR_SYMBOL="Y";
    public final static String MALESEX="M";
    public final static String FEMALESEX="F";
    public final static String BOTHSEX="BG";
    public final static String GBVSERVICE_DEFAULT_CODE="gbv";
    public final static String REFERRALSERVICE_DEFAULT_CODE="ref";
    
    public static final int NO_TREATMENT_CRITERIA=0;
    //public static final int ON_TREATMENT=1;
    //public static final int NOT_ON_TREATMENT=2;
    public static final int ALL_SERVICE_DOMAIN=0;
    public static final int HEALTH_DOMAIN=1;
    public static final int REFERRAL_DOMAIN=2;
    public static final int SAFETY_DOMAIN=3;
    public static final int SCHOOL_DOMAIN=4;
    public static final int STABLE_DOMAIN=5;
    public static final int REFERRALCOMPLETED_NOT_APPLICABLE_NUM=0;
    public static final int REFERRALCOMPLETED_YES_NUM=1;
    public static final int REFERRALCOMPLETED_NO_NUM=2;
    
    
    public static final int CHILD_IN_SCHOOL=1;
    public static final int CHILD_NOT_IN_SCHOOL=2;
    public static final int CHILD_SCHOOL_STATUS_NOT_APPLICABLE=3;
    public static final int CHILD_HAS_NO_SCHOOL_STATUS_DOCUMENTATION=0;
    
    public static final int CHILD_HAS_BIRTHCERTIFICATE=1;
    public static final int CHILD_HAS_NO_BIRTHCERTIFICATE=2;
    public static final int CHILD_HAS_NO_BIRTHCERTIFICATE_DOCUMENTATION=0;
    
    public static final int REF_CBO_TO_FACILITY=1;
    public static final int REF_FACILITY_TO_CBO=2;
    public static final int REF_CBO_TO_CBO=3;
    public static final int REF_FACILITY_TO_FACILITY=4;
    public final static String DEFAULT_FACILITY_ID="xxxxxxxxxxx";
    
    public final static int LISTOFINDICATORS_REPORTTYPE=1;
    public final static int CUSTOMINDICATORS_REPORTTYPE=2;
    public final static int HOUSEHOLDREGISTER_REPORTTYPE=3;
    public final static int CHILDREGISTER_REPORTTYPE=4;
    public final static String DEFAULT_ACCESS_MSG="<label style='color:red; font-weight:bold;font-size:14px;'>Either your session has expired or you may not have data entry permission on this page</label>";
    public final static String LICENCE_ACCESS_MSG="<label style='color:red; font-weight:bold;font-size:16px;'>Oops. It seems your licence has expired. contact the administrator</label>";
    
    public final static int NUTRITION_WEIGHTGAIN=1;
    public final static int NUTRITION_WEIGHTLOSS=3;
    public final static int NUTRITION_WEIGHTSTATIONARY=2;
}
