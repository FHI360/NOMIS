/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.legacydatamanagement.utils;

import java.io.Serializable;

/**
 *
 * @author smomoh
 */
public class NomisConstant implements Serializable
{
    public static final String MAIN_INDICATOR_TYPE="main indicator";
    public static final String HIVRISK_POINTOFUPDATE="hra";
    public static final String HOUSEHOLD_TYPE="household";
    public static final String Caregiver_TYPE="caregiver";
    public static final String OVC_TYPE="ovc";
    public static final String SUPERUSER_ROLEID="superuser";
    public static final String DEFAULTGRPID="defaultgrpId";
    public static final String OLDDEFAULTGRPID="defaultgpId";
    public static final String DEFAULTGRP="defaultgrp";
    public static final String ENABLEDACCOUNTSTATUS="enabled";
    public static final String DISABLEDACCOUNTSTATUS="disabled";
    public static final String RECREATION_ACTIVITIES="Recreational activity";
    public static final int CHILDABUSED_NUM=2;
    public static final int LINKEDTOGOVT_NUM=2;
    public static final String LINKED_TO_GOVT_POSTVIOLENCE="Linkage to Govt for post-violence offences";
    public static final String OVC_HIVPREV="Adolescent HIV prevention and sexual reproductive health services";
    public static final String HIV_POSITIVE="positive";
    public static final String HIV_NEGATIVE="negative";
    public static final String HIV_UNKNOWN="unknown";
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
    public static final int COMMUNITYCODE_LENGTH=15;
    public static final int CBOCODE_LENGTH=11;
    
    public static final String ACTIVE="active";
    public static final String GRADUATED="graduated";
    public static final String MIGRATED="migrated";
    public static final String LOST_TO_FOLLOWUP="Loss to follow-up";
    public static final String DIED="Known death";
    public static final String TRANSFERED="transfer";
    public static final String AGED_OUT="ageabove17";
    public static final String VOLUNTARILY_WITHDRAWN="voluntary_withdrawal";
    public static final String INACTIVE="Inactive";
    public static final String EXITED_WITHOUT_GRADUATION="exited without graduation";
    
    public static final int ACTIVECODE=1;
    public static final int INACTIVECODE=2;
    public static final int GRADUATEDCODE=3;
    public static final int MIGRATEDCODE=4;
    public static final int LOST_TO_FOLLOWUPCODE=5;
    public static final int KNOWN_DEATHCODE=6;
    public static final int TRANSFEREDCODE=7;
    public static final int AGED_OUTCODE=8;
    public static final int VOLUNTARILY_WITHDRAWNCODE=9;
    public static final int EXITED_WITHOUT_GRADUATIONCODE=10;
    
    
    public static final String DOMAIN_PSYCHOSOCIAL="psychosocial";
    public static final String DOMAIN_HEALTH="health";
    public static final String DOMAIN_NUTRITION="nutrition";
    public static final String DOMAIN_EDUCATION="education";
    public static final String DOMAIN_PROTECTION="protection";
    public static final String DOMAIN_SHELTER="shelter";
    public static final String DOMAIN_ECONOMICSTRENTHENINIG="economicStrengthening";
    public static final String ENROLLMENT_POINTOFUPDATE="enrollment";
    public static final String CAREGIVER_ENROLL_POINTOFUPDATE="cgenrollment";
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
    public static final String DEAULT_PARTNER_CODE="XXX";
}
