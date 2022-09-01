/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.ovc.util;


import com.nomis.ovc.business.DataImportIndicator;
import com.nomis.ovc.business.EnvironmentInformationProvider;
import com.nomis.ovc.business.User;
import com.ovc.databasemanagement.DatabaseConnectionManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author smomoh
 */
public class AppUtility implements Serializable
{
    //DaoUtil util=new DaoUtil(); EnvironmentInformationProvider
    public static int xmlDataImportThreadCounter=0;
    public static int enrollmentStatusUpdateThreadCounter=0;
    public static int customIndicatorsThreadCounter=0;
    public static int hivRiskAssessmentDataGeneratorThreadCounter=0;
    public static int enrollmentStatusDataGeneratorCounter=0;
    private static String seperator="\\";
    private static String dbRootDirectory="C:"+seperator+"Nomis3";
    public static String resourceString;
    private static String resourceLocation=dbRootDirectory;
    private static String dbBackupDirectory="C:"+seperator+"Nomis3_backup";
    static String[] validLetters={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int[] validNumbers={0,1,2,3,4,5,6,7,8,9};
    String[] specialCharacters={"@","#","$","%","^","&","*","!","~","`","+","=","\\","/","?","'","\"",".","[","]","{","}",","};
    private String metadataDirName="Metadata";
    private final static String environmentFileName="StartupConfig.txt";
    public static boolean CANWRITETODATABASE=false;
    public static boolean importHivOnly=false;//setCurrentImportFileName
    public static String currentImportFileName=null;
    public static String currentImportRecordName=null;
    private static DataImportIndicator dataImportIndicator;
    public static HttpServletRequest request=null;
    public static DatabaseConnectionManager dbConnectionManager=null;
    private static EnvironmentInformationProvider environmentInformationProvider=null;
    
    public AppUtility()
    {
        //setDbRootDirectory(getUserHomeDirectory());
    }

    public static EnvironmentInformationProvider getEnvironmentInformationManager() {
        return environmentInformationProvider;
    }

    public static void setEnvironmentInformationProvider(EnvironmentInformationProvider environmentInformationProvider) {
        AppUtility.environmentInformationProvider = environmentInformationProvider;
    }

    public static DataImportIndicator getDataImportIndicator() {
        return dataImportIndicator;
    }

    public static void setDataImportIndicator(DataImportIndicator dataImportIndicator) {
        AppUtility.dataImportIndicator = dataImportIndicator;
    }
    
    public static HttpServletRequest getRequestObject()
    {
        return request;
    }
    public static String getCurrentImportFileName()
    {
        return currentImportFileName;
    }
    public static void setCurrentImportFileName(String importFileName)
    {
        currentImportFileName=importFileName;
    }

    public static String getCurrentImportRecordName() {
        return currentImportRecordName;
    }

    public static void setCurrentImportRecordName(String currentImportRecordName) {
        AppUtility.currentImportRecordName = currentImportRecordName;
    }
    public static boolean isNull(String str)
    {
        if(str !=null && str.trim().length()>0)
        return false;
        return true;
    }
    public String getBeneficiaryWithrawnMessage(int currentEnrollmentStatus)
    {
        String beneficiaryWithrawnMessage=null;
        if(currentEnrollmentStatus==AppConstant.AGED_OUT_NUM)
        beneficiaryWithrawnMessage="Beneficiary has aged out of the program";
        else if(currentEnrollmentStatus==AppConstant.DIED_NUM)
        beneficiaryWithrawnMessage="Beneficiary has died";
        else if(currentEnrollmentStatus==AppConstant.EXITED_WITHOUT_GRADUATION_NUM)
        beneficiaryWithrawnMessage="Beneficiary has exited from the program";
        else if(currentEnrollmentStatus==AppConstant.GRADUATED_NUM)
        beneficiaryWithrawnMessage="Beneficiary has graduated from the program";
        else if(currentEnrollmentStatus==AppConstant.LOST_TO_FOLLOWUP_NUM)
        beneficiaryWithrawnMessage="Beneficiary is lost to follow up";
        else if(currentEnrollmentStatus==AppConstant.MIGRATED_NUM)
        beneficiaryWithrawnMessage="Beneficiary has migrated";
        
        else if(currentEnrollmentStatus==AppConstant.TRANSFERED_NONPEPFAR_NUM)
        beneficiaryWithrawnMessage="Beneficiary has been transfered to a non PEPFAR program";
        else if(currentEnrollmentStatus==AppConstant.TRANSFERED_PEPFAR_NUM)
        beneficiaryWithrawnMessage="Beneficiary has been transfered to another PEPFAR funded program";
        else if(currentEnrollmentStatus==AppConstant.TRANSFERED_NUM)
        beneficiaryWithrawnMessage="Beneficiary has been transfered out of the program";
        else if(currentEnrollmentStatus==AppConstant.VOLUNTARILY_WITHDRAWN_NUM)
        beneficiaryWithrawnMessage="Beneficiary has voluntarily withdrawn from the program";
        
        return beneficiaryWithrawnMessage;
    }
    /*public String changeFirstLetterToUpperCase(String input)
    {
        if(input !=null && input.length()>0)
        {
            String strValue=new String();
            for(int i=0; i<input.length(); i++)
            {
                strValue+=input.charAt(i);
                if(i==0)
                strValue=strValue.toUpperCase();
            }
            input=strValue;
        }
        return input;
    }*/
    public static boolean hasLetters(String input)
    {
        boolean hasLetters=false;
        if(input==null)
        return true;
        List letterList=getValidLetters();
        if(input !=null)
        {
            for(int i=0; i<input.length(); i++)
            { 
                if(letterList.contains(input.charAt(i)))
                {
                    hasLetters=true;
                    break;
                }
            }
        }
        return hasLetters;
    }
    public static List getValidLetters()
    {
        List validLettersList=new ArrayList();
        for(int i=0; i<validLetters.length; i++)
        {
            validLettersList.add(validLetters[i]);
        }
        return validLettersList;
    }
    public List getNumber0to9()
    {
        List list=new ArrayList<String>();
        for(int i=0; i<10; i++)
        {
            Integer in=new Integer(i);
            list.add(in.toString());
        }
        return list;
    }
    public List getValidCharacters()
    {
        List validList=new ArrayList();
        for(int i=0; i<validLetters.length; i++)
        {
            validList.add(validLetters[i]);
        }
        for(int i=0; i<validNumbers.length; i++)
        {
            validList.add(validNumbers[i]);
        }
        return validList;
    }
    public String getExportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Export";
    }
    public String getDeleteScriptsFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"DeleteScripts";
    }
    public String getMetadataExportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Metadata"+seperator+"Export";
    }
    public String getMetadataImportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Metadata"+seperator+"Import";
    }
    /*public String getZipExtractFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"ZipExtract";
    }*/
    public void createZipExtractDirectories()
    {
        createDirectories(getZipExtractsFilePath());
    }
    public String getDxExportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"DxExport";
    }
    public void setDbRootDirectory(String newRootDirectory)
    {
        dbRootDirectory=newRootDirectory+seperator+"Nomis3";
    }
    public String getDbRootDirectory()
    {
        return getResourceLocation();
    }
    public void setResourceDirectory(String newDirectory)
    {
        resourceLocation=newDirectory+seperator+"Nomis3";
    }
    
    public String getDatabaseDirectory()
    {
        return getResourceLocation()+seperator+"dbs";
    }
    public String getDatabaseBackupDirectory()
    {
        createDirectories(dbBackupDirectory);
        return dbBackupDirectory;
    }
    public String getFileSeperator()
    {
        return seperator;
    }
    public void setFileSeperator(String seperator)
    {
        this.seperator=seperator;
    }
    
public String removeSpacesFromString(String str)
{
    String[] strArray=str.split(" ");
    String cleanedStr=str;
    if(cleanedStr !=null)
    {
        if(strArray !=null && strArray.length>0)
        {
            cleanedStr="";
            cleanedStr=cleanedStr.trim();
            for(int i=0; i<strArray.length; i++)
            {
                cleanedStr+=strArray[i].trim();
            }
        }
    }
    return cleanedStr;
}
public void moveAllImportFilesToDoneDirectory()
{
    System.err.println("inside moveAllImportFilesToDoneDirectory()-----");
    AppUtility appUtil=new AppUtility();
    File destinationFolder=new File(appUtil.getImportDoneDirectoryPath());
    if(!destinationFolder.exists())
    {
        String[] uploadedFileList=appUtil.listFiles(appUtil.getImportFilePath());
        if(uploadedFileList !=null && uploadedFileList.length>0)
        {
            String filePath=null;
            File fileToBeImported=null;
            for(int i=0; i<uploadedFileList.length; i++)
            {
                filePath=appUtil.getImportFilePath()+appUtil.getFileSeperator()+uploadedFileList[i];
                fileToBeImported=new File(filePath);
                if(!fileToBeImported.exists() || fileToBeImported.isDirectory())
                continue;
                             
                //MoveFile.moveFileToAnotherDirectory(filePath,appUtil.getImportDoneDirectoryPath());
            }
        }
    }
}
    public String getVulnerabilityStatus(int vulnerabilityScore)
    {
        String vulnerabilityStatus="Not vulnerable";
        if(vulnerabilityScore <7)
        vulnerabilityStatus="Not assessed";
        else if(vulnerabilityScore >6 && vulnerabilityScore <14)
        vulnerabilityStatus="Vulnerable";
        else if(vulnerabilityScore >13 && vulnerabilityScore <23)
        vulnerabilityStatus="More vulnerable";
        else if(vulnerabilityScore >22 )
        vulnerabilityStatus="Most vulnerable";
        return vulnerabilityStatus;
    }
    public String getDatimStatus(String status)
    {
        String datimStatus=null;
        if(status==null || status.trim().length()<1 || !this.isString(status) || status.trim().equalsIgnoreCase(AppConstant.ACTIVE))
        {
            datimStatus=AppConstant.ACTIVE;
        }
        else if(status.trim().equalsIgnoreCase(AppConstant.GRADUATED))
        datimStatus=AppConstant.GRADUATED;
        else if(status.trim().equalsIgnoreCase(AppConstant.TRANSFERED))
        datimStatus=AppConstant.TRANSFERED;
        else
        {
            datimStatus=AppConstant.EXITED_WITHOUT_GRADUATION;
        }
        return datimStatus;
    }
    public String getOvcAgeCategory(int currentAge,String currentAgeUnit)
    {
        String ageCategory=" ";
        if(currentAgeUnit !=null)
        {
            if(currentAgeUnit.equalsIgnoreCase("Month"))
            ageCategory="<1";
            else
            {
                if(currentAge >0 && currentAge <5)
                ageCategory="1-4";
                else if(currentAge >4 && currentAge <10)
                ageCategory="5-9";
                else if(currentAge >9 && currentAge <15)
                ageCategory="10-14";
                else if(currentAge >14 && currentAge <18)
                ageCategory="15-17";
                else if(currentAge >17 && currentAge <25)
                ageCategory="18-24";
                else if(currentAge >24 && currentAge <50)
                ageCategory="25-49";
                else if(currentAge >49)
                ageCategory="50+";
            }
        }
        return ageCategory;
    }
    public String getMajorAgeCategory(int currentAge)
    {
        String ageCategory=" ";
        if(currentAge <18)
        ageCategory="Under 18";
        else
        ageCategory="18+";
                   
        return ageCategory;
    }
    public boolean isString(String input)
    {
        boolean isLetter=false;
        if(input !=null)
        {
            for(int i=0; i<input.length(); i++)
            {
                if(Character.isLetter(input.charAt(i)))
                {
                    isLetter=true;
                    break;
                }
                
            }
        }
        return isLetter;
    }
    public boolean isEmptyArray(String[] input)
    {
        boolean empty=true;
        if(input !=null)
        {
            String content=null;
            for(int i=0; i<input.length; i++)
            {
                content=input[i];
                if(content !=null && content.trim().length()>0)
                {
                    empty=false;
                    break;
                }
                
            }
        }
        System.err.println("empty is "+empty);
        return empty;
    }
    public String replaceSpecialCharacters(String input,String[] exceptions)
    {
        System.err.println("input is "+input);
        String strValue=new String();
        if(input !=null)
        {
            List validCharacterList=getValidCharacters();
            boolean contIterate=true;
            for(int i=0; i<input.length(); i++)
            {
                contIterate=true;
                if(exceptions !=null && exceptions.length >0)
                {
                    for(int j=0; j<exceptions.length; j++)
                    {
                        if(input.contains(exceptions[j]))
                        strValue+=input.charAt(i);
                        contIterate=false;
                    }
                    if(!contIterate)
                    continue;
                }
                if(!validCharacterList.contains(input.charAt(i)))
                {
                    strValue+="-";
                    continue;
                }
                strValue+=input.charAt(i);
            }
        }
        //System.err.println("strValue is "+strValue.trim());
        if(strValue.length()<1)
        return null;
        return strValue.trim();
    }
    public String getConcatenatedLetters()
    {
        String concatenatedLetters="";
        List validCharacterList=getValidLetters();
        if(validCharacterList !=null)
        {
            for(int i=0; i<validCharacterList.size(); i++)
            {
                if(i==0)
                concatenatedLetters=validCharacterList.get(i).toString();
                else
                concatenatedLetters+=validCharacterList.get(i).toString();
            }
        }
        return concatenatedLetters;
    }
    public String removeSpecialCharacters(String input,String[] exceptions)
    {
        System.err.println("input is "+input);
        String strValue=new String();
        if(input !=null)
        {
            input=input.trim();
            String inputInUpperCase=input.toUpperCase();
            List validCharacterList=getValidLetters();
            String concatenatedLetters=getConcatenatedLetters();
            boolean contIterate=true;
            for(int i=0; i<input.length(); i++)
            {
                contIterate=true;
                if(exceptions !=null && exceptions.length >0)
                {
                    for(int j=0; j<exceptions.length; j++)
                    {
                        if(input.contains(exceptions[j]))
                        strValue+=input.charAt(i);
                        contIterate=false;
                    }
                    if(!contIterate)
                    continue;
                }
                if(concatenatedLetters.indexOf(inputInUpperCase.charAt(i)) !=-1)
                strValue+=input.charAt(i);
                /*if(!validCharacterList.contains(inputInUpperCase.charAt(i)))
                {
                    System.err.println("inputInUpperCase.charAt(i) is "+inputInUpperCase.charAt(i));
                    //strValue+="-";
                    continue;
                }
                strValue+=input.charAt(i);*/
            }
        }
        //System.err.println("strValue is "+strValue.trim());
        if(strValue.length()<1)
        return null;
        return strValue.trim();
    }
    public boolean hasSpecialCharacters(String name)
    {
        
        if(name==null)
        return true;
        boolean hasSpecialCharacter=false;
        for(int i=0; i<specialCharacters.length; i++)
        {
            if(name.indexOf(specialCharacters[i]) != -1)
            {
                hasSpecialCharacter=true;
                break;
            }
        }
        return hasSpecialCharacter;
    }
    public boolean hasSpecialCharacters(String name,String[] exceptions)
    {
        boolean contIterate=true;
        if(name==null)
        return true;
        boolean hasSpecialCharacter=false;
        for(int i=0; i<specialCharacters.length; i++)
        {
            contIterate=true;
            if(exceptions !=null && exceptions.length >0)
            {
                for(int j=0; j<exceptions.length; j++)
                {
                    if(exceptions[j].equalsIgnoreCase(specialCharacters[i]))
                    contIterate=false;
                }
                if(!contIterate)
                continue;
            }
            
            if(name.indexOf(specialCharacters[i]) != -1)
            {
                hasSpecialCharacter=true;
                break;
            }
        }
        return hasSpecialCharacter;
    }
    public String removeCharactersFromNumbers(String input)
    {
        System.err.println("input is "+input);
        String strValue=new String();
        if(input !=null)
        {
            for(int i=0; i<input.length(); i++)
            {
                
                if(!Character.isDigit(input.charAt(i)))
                {
                    //System.err.println(input.charAt(i)+" is not a number ");
                    continue;
                }
                strValue+=input.charAt(i);
            }
        }
        //System.err.println("strValue is "+strValue.trim());
        if(strValue.length()<1)
        return null;
        return strValue.trim();
    }
    public String removeCharactersFromNumbers(String input,String[] exceptions)
    {
        //System.err.println("input is "+input);
        String strValue=new String();
        if(input !=null)
        {
            boolean contIterate=true;
            for(int i=0; i<input.length(); i++)
            {
                contIterate=true;
                if(exceptions !=null && exceptions.length >0)
                {
                    for(int j=0; j<exceptions.length; j++)
                    {
                        if(input.contains(exceptions[j]))
                        strValue+=input.charAt(i);
                        contIterate=false;
                    }
                    if(!contIterate)
                    continue;
                }
                if(!Character.isDigit(input.charAt(i)))
                {
                    //System.err.println(input.charAt(i)+" is not a number ");
                    continue;
                }
                strValue+=input.charAt(i);
            }
        }
        //System.err.println("strValue is "+strValue.trim());
        if(strValue.length()<1)
        return null;
        return strValue.trim();
    }
    public boolean isNumberGreaterThanZero(String input)
    {
        boolean numberGreaterThanZero=false;
        String strNum=removeCharactersFromNumbers(input);
        try
        {
            //System.err.println("Serial number is "+strNum);
            if(strNum !=null)
            {
                int number=Integer.parseInt(strNum);
                //System.err.println(" number is "+number);
                if(number > 0)
                numberGreaterThanZero=true;
            }
        }
        catch(NumberFormatException nex)
        {
            System.err.println("A NumberFormatException has occured: "+nex.getMessage());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return numberGreaterThanZero;
    }
    public String getOvcVulnerabilityStatusByCSI(int totalCsiScore)
    {
        String vulnerabilityStatus=AppConstant.NOTASSESSED_VULNERABLE_STATUS;
            if(totalCsiScore<13)
            vulnerabilityStatus=AppConstant.MOREVULNERABLE_STATUS;
            else if(totalCsiScore >12 && totalCsiScore<25)
            vulnerabilityStatus=AppConstant.MOREVULNERABLE_STATUS;
            else if(totalCsiScore >24)
            vulnerabilityStatus=AppConstant.VULNERABLE_STATUS;
        return vulnerabilityStatus;
    }
    
    public AppUtility getInstance()
    {
        AppUtility appUtil=new AppUtility();
        appUtil.setFileSeperator("/");
        return appUtil;
    }
    public String getUserHomeDirectory()
    {
        String userHomeDirectory=System.getProperty("user.home");
        if(userHomeDirectory !=null && userHomeDirectory.indexOf("/") !=-1)
        seperator="/";
        return userHomeDirectory;
    }
    public static String getStartupFileName()
    {
        return environmentFileName;
    }
    public static String getRealDeploymentPath(ServletContext sc)
    {
        return sc.getRealPath("/Resources/dbs");
        
    }
    /*public static String getEnvironmentFilePath(String path)
    {
        String environmentFilePath=null;
        //String path=getRealDeploymentPath();
        if(path !=null)
        {
            if( path.indexOf("webapps") !=-1)
            {
                String[] partArray=path.split("webapps");
                environmentFilePath=partArray[0]+"bin\\"+getStartupFileName();
                System.err.println("environmentFilePath is "+environmentFilePath);
                File file=new File(environmentFilePath);
                if(!file.exists())
                {
                    System.err.println("environmentFilePath is "+environmentFilePath);
                    System.err.println("environmentFile is does not exist ");
                    return null;
                }
            }
            else
            {
               environmentFilePath="C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\bin\\"+getStartupFileName(); 
            }
            System.err.println("environmentFilePath is "+environmentFilePath);
        }
        return environmentFilePath;
    }*/
    public static String getResourceString()
    {
        return resourceString;
    }
    public static String getResourceLocation()
    {
        return resourceLocation;
    }
    public static String getHibernateFile()
    {
        return getConfigurationDirectory()+seperator+"hibernate.txt";
    }
    /*public static String getResourceLocation()
    {
        String resourceString=getResourceString();
        if(resourceString !=null && resourceString.indexOf("=") !=-1)
        {
            String[] resourceStringArray=resourceString.split("=");
            if(resourceStringArray.length>1)
            resourceLocation=resourceStringArray[1]+seperator+"Pomis";
        }
        return resourceLocation;
    }*/
   /* public static String getHibernateFilePath(String path)
    {
        String hibernateFilePath=null;
        List list=null;
        String environmentFilePath=getEnvironmentFilePath(path);
        if(environmentFilePath !=null )
        {
            //Read file
            File file=new File(path);
            if(file.exists())
            {
                String resourcePath=null;
                list=AppUtility.readFiles(environmentFilePath);
                if(list !=null && !list.isEmpty())
                {
                    for(int i=0; i<list.size(); i++)
                    {
                        resourcePath=(String)list.get(i);
                        if(resourcePath !=null)
                        {
                            if(resourcePath.indexOf("dbLocation") !=-1)
                            hibernateFilePath=resourcePath;
                            else if(resourcePath.indexOf("ResourceLocation") !=-1)
                            resourceString=resourcePath;
                        }
                        System.err.println("environmentFilePath is "+environmentFilePath);
                        System.err.println("hibernateFilePath is "+hibernateFilePath);
                    }
                }
            }
            //String hibernateFilePath=environmentFilePath.substring(environmentFilePath.indexOf("="));
            //list=AppUtility.readFiles(hibernateFilePath);
            
        }
        return hibernateFilePath;
    }*/
    public int createHibernateFile()
    {
        int successNumber=0;
        File file=new File(AppUtility.getConfigurationDirectory());
        createDirectories(AppUtility.getConfigurationDirectory());
        
        String driver="hibernate.connection.driver_class=org.apache.derby.jdbc.EmbeddedDriver";
        String hibernatedialect="hibernate.dialect=org.hibernate.dialect.DerbyDialect";
        String dbUrl="hibernate.connection.url="+getDatabaseURL();
        String userName="hibernate.connection.username=";
        String password="hibernate.connection.password=";
        List list=new ArrayList();
        list.add(driver);
        list.add(hibernatedialect);
        list.add(dbUrl);
        list.add(userName);
        list.add(password);
        if(file.exists())
        {
            successNumber=1;
            writeFile(AppUtility.getHibernateFile(), list);
            File hibernateFile=new File(AppUtility.getHibernateFile());
            if(hibernateFile.exists())
            {
               successNumber=2; 
            }
        }
        return successNumber;
    }
    public static List getHibernateConnectionDetails()
    {
        List list=null;
        list=AppUtility.readFiles(AppUtility.getHibernateFile());
         if(list !=null && !list.isEmpty())
         {
             for(int i=0; i<list.size(); i++)
             {
                System.err.println("list content at "+i+" is "+list.get(i).toString());
             }
         }
        return list;
    }
public static void initializeDatabaseConnectionManager()
{
    System.err.println("Inside AppUtility.initializeDatabaseConnectionManager() ");
    dbConnectionManager=new DatabaseConnectionManager();
    List list=getHibernateConnectionDetails();
    if(list !=null && !list.isEmpty())
    {
        //System.err.println("list size in getPreparedConfiguration(cfg) is "+list.size());
        String property=null;
        String value=null;
        for(int i=0; i<list.size(); i++)
        {
            String str=list.get(i).toString();
            if(str !=null && str.indexOf("=") !=-1)
            {
                String[] strArray=str.split("=");
                if(strArray !=null && strArray.length>1)
                {
                    property=strArray[0];
                    value=strArray[1];
                    if(property !=null && value !=null)
                    {
                        property=property.trim();
                        value=value.trim();
                        //System.err.println("property: "+property+" value: "+value);
                        if(value.length()>0)
                        {
                            if(property.equalsIgnoreCase("hibernate.connection.driver_class"))
                            {
                                dbConnectionManager.setDbDriverClass(value);
                                System.err.println("property: "+property+" value: "+dbConnectionManager.getDbDriverClass());
                            }
                            else if(property.equalsIgnoreCase("hibernate.dialect"))
                            {
                                dbConnectionManager.setDbDialect(value);
                               System.err.println("property: "+property+" value: "+dbConnectionManager.getDbDialect());
                            }
                            else if(property.equalsIgnoreCase("hibernate.connection.url"))
                            {
                                dbConnectionManager.setConnectionURL(value);
                                System.err.println("property: "+property+" value: "+dbConnectionManager.getConnectionURL());
                            }
                            else if(property.equalsIgnoreCase("hibernate.connection.username"))
                            {
                                dbConnectionManager.setUsername(value);
                                System.err.println("property: "+property+" value: "+dbConnectionManager.getUsername());
                            }
                            else if(property.equalsIgnoreCase("hibernate.connection.password"))
                            {
                                dbConnectionManager.setPassword(value);
                               System.err.println("property: "+property+" value: "+dbConnectionManager.getPassword());
                            }
                        }
                    }
                }
                
            }
        }
    }
    //return dbConnectionManager;
}
    /*public static List getHibernateConnectionDetails(String path)
    {
        List list=null;
        String hibernateFilePath=getHibernateFilePath(path);
        System.err.println("hibernateFilePath 2 is "+hibernateFilePath);
         if(hibernateFilePath !=null)
         {
             if(hibernateFilePath.indexOf("=") !=-1)
             {
                 hibernateFilePath=hibernateFilePath.substring(hibernateFilePath.indexOf("=")+1);
             }
             list=AppUtility.readFiles(hibernateFilePath);
             if(list !=null && !list.isEmpty())
             {
                 for(int i=0; i<list.size(); i++)
                 {
                    System.err.println("list content at "+i+" is "+list.get(i).toString());
                 }
             }
         }
        
        return list;
    }*/
    public String getDatabaseURL()
    {
        return "jdbc:derby:"+getDatabaseDirectory()+seperator+"nomis3db";
    }
    public void createReportDirectory()
    {
        createDirectories(getResourceLocation()+seperator+"Reports");
    }
    public String getReportDirectory()
    {
        return getResourceLocation()+seperator+"Reports";
    }
    public String getZipExtractsFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"ZipExtracts";
    }
    public String getImportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Import";
    }
    
    public String getImportDoneDirectoryPath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Done";
    }
    public void createLogDirectory()
    {
        createDirectories(getResourceLocation()+seperator+"logs");
    }
    public static String getLogDirectoryPath()
    {
        return getResourceLocation()+seperator+"logs";
    }
    public String getDxImportFilePath()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"DxImport";
    }
    public String getAccessDeniedMessage()
    {
        return "Access denied. You do not have enough privilege to access this page";
    }
    public String getSuperUserRoleId()
    {
        return "superuser";
    }
    /*public List scrambleIdentifiers(List list,HttpSession session)
    {
        DataEncryption de=new DataEncryption();
        List scrambledRecordsList=de.encryptOvcInfo(list, session);
        return scrambledRecordsList;
    }*/
    
    public static String getCurrentDateAndTime() 
    {
        String dateAndTime=null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateAndTime=dateFormat.format(date);
        return dateAndTime; 
    }
    public String getHhsRecipientType(String flag)
    {
        if(flag !=null)
        {
            if(flag.equalsIgnoreCase("h"))
            return "householdhead";
            else
            return "caregiver";
        }
        return null;
    }
    /*public String getCurrentState(HttpSession session)
    {
       CboSetup setup=getSetupInfo(getCurrentUser(session));
       if(setup==null)
       setup=new CboSetup();
       String stateCode=setup.getState_code();
       return stateCode;
    }
    public CboSetup getSetupInfo(String user)
    {
        CboSetup setup=null;
           try
           {
                CboSetupDao dao = new CboSetupDaoImpl();
                setup= dao.getCboSetup(user);
           }
           catch(Exception e)
           {
                e.printStackTrace();
           }
           return setup;
    }
    public User getUser(HttpSession session)
    {
        UserDao userDao=new UserDaoImpl();
        User user=null;
        try
        {
            user=userDao.getUser(getCurrentUser(session));
        }
        catch(Exception ex)
        {
            
        }
        return user;
    }
    public User getUser()
    {
        UserDao userDao=new UserDaoImpl();
        User user=null;
        try
        {
            if(getRequestObject().getSession() !=null)
            user=userDao.getUser(getCurrentUser(getRequestObject().getSession()));
        }
        catch(Exception ex)
        {
            
        }
        return user;
    }*/
    public void createExportImportDirectories()
    {
      createDirectories(getExportFilePath()+seperator+"HouseholdEnrollments");
      createDirectories(getExportFilePath()+seperator+"HouseholdVulnerabilityAssessments");
      createDirectories(getExportFilePath()+seperator+"RevisedHouseholdVulnerabilityAssessments");
      createDirectories(getExportFilePath()+seperator+"AdultHouseholdMembers");
      createDirectories(getExportFilePath()+seperator+"ChildEnrollment");
      createDirectories(getExportFilePath()+seperator+"ChildServices");
      createDirectories(getExportFilePath()+seperator+"HouseholdServices");
      createDirectories(getExportFilePath()+seperator+"HouseholdReferral");
      createDirectories(getExportFilePath()+seperator+"CareAndSupportChecklist");
      createDirectories(getExportFilePath()+seperator+"HivRiskAssessments");
      createDirectories(getExportFilePath()+seperator+"NutritionAssessment");
      createDirectories(getExportFilePath()+seperator+"HouseholdCarePlan");
      createDirectories(getExportFilePath()+seperator+"ChildCarePlan");
      createDirectories(getExportFilePath()+seperator+"BeneficiaryStatusUpdates");
      createDirectories(getExportFilePath()+seperator+"ChildEducationPerformanceAssessment");
      createDirectories(getExportFilePath()+seperator+"CareplanAchievementChecklist");
      createDirectories(getExportFilePath()+seperator+"CaregiverAccessToEmergencyFund");
      createDirectories(getExportFilePath()+seperator+"DataExportSummary");
      createDirectories(getResourceLocation()+seperator+"Transfer"+seperator+"Zips");
      createDirectories(getConfigurationDirectory());
      createDirectories(getResourcesDirectory());
      //createDirectories(getExportFilePath()+seperator+"CBOSetup");
      //createDirectories(getExportFilePath()+seperator+"GBVService");
      //createDirectories(getExportFilePath()+seperator+"GBVEnrollment");
      //createDirectories(getExportFilePath()+seperator+"School");
      //createDirectories(getExportFilePath()+seperator+"SchoolGrade");
      //createDirectories(getExportFilePath()+seperator+"HouseholdProfile");
      //createDirectories(getExportFilePath()+seperator+"HivStatusManager");
      //createDirectories(getExportFilePath()+seperator+"OrganizationUnitHierachy");
      //createDirectories(getExportFilePath()+seperator+"GraduationBenchmarkAchievement");
      //createDirectories(getExportFilePath()+seperator+"Partner");
      //createDirectories(getExportFilePath()+seperator+"OrganizationUnit");
      //createDirectories(getExportFilePath()+seperator+"ReferralFacility");
      //createDirectories(getExportFilePath()+seperator+"ServiceDomain");
      //createDirectories(getExportFilePath()+seperator+"SiteSetup");
      //createDirectories(getExportFilePath()+seperator+"Training");
      //createDirectories(getExportFilePath()+seperator+"TrainingCategory");
      //createDirectories(getExportFilePath()+seperator+"TrainingDesignation");
      
      //createDirectories(getExportFilePath()+seperator+"TrainingInformationSetup");
      //createDirectories(getExportFilePath()+seperator+"TrainingParticipants");
      //createDirectories(getExportFilePath()+seperator+"TrainingStatusSetup");
      //createDirectories(getExportFilePath()+seperator+"EnumeratorRegistration");
      //createDirectories(getImportFilePath());
      //createDirectories(getDxImportFilePath());
      //createDirectories(getDxExportFilePath());
      
      //createDirectories(getTrainingDataXmlFolderPathFile());
      //createDirectories(getTrainingMetadataXmlFolderPathFile());
      //createMetadataExportImportDirectories();
      System.err.println("ExportImportDirectories created");
    }
    public String getMetadataDirectoryPath(String parentFolderName)
    {
        if(parentFolderName !=null)
        return getExportFilePath()+seperator+metadataDirName+seperator+parentFolderName+seperator;
        else
        return getExportFilePath()+seperator+metadataDirName+seperator;
    }
    public String getReferralDirectoryDirectoryPath()
    {
        return getMetadataDirectoryPath("ReferralDirectory");
    }
    public String getOrganizationRecordsDirectoryPath()
    {
        return getMetadataDirectoryPath("OrganizationRecords");
    }
    public String getWardsDirectoryPath()
    {
        return getMetadataDirectoryPath("Wards");
    }
    public void createMetadataExportImportDirectories()
    {
       
      createDirectories(getExportFilePath()+seperator+metadataDirName+seperator+"Training"+seperator);   
      createDirectories(getExportFilePath()+seperator+metadataDirName+seperator+"ReferralDirectory"+seperator);
      createDirectories(getExportFilePath()+seperator+metadataDirName+seperator+"OrganizationRecords"+seperator);
      createDirectories(getExportFilePath()+seperator+metadataDirName+seperator+"Wards"+seperator);
      createDirectories(getImportFilePath());
      createDirectories(getTrainingDataXmlFolderPathFile());
      createDirectories(getTrainingMetadataXmlFolderPathFile());
      System.err.println("MetadataExportImportDirectories created");
    }
    public String getTrainingDataXmlFolderPathFile()
    {
        return getExportFilePath()+seperator+"Training"+seperator;
    }
    public String getTrainingMetadataXmlFolderPathFile()
    {
        return getExportFilePath()+seperator+"Metadata"+seperator+"Training"+seperator;
    }
    
    public List getAgeListAbove18(int startAge)
    {
        List endAgeList=getAgeList(startAge,25);
        endAgeList.add("24+");
        return endAgeList;
     }
    /*public List getStates(List stateCodeList)
    {
        List stateList=new ArrayList();
        if(stateCodeList !=null && !stateCodeList.isEmpty())
        {
            String stateCode=null;
            States state=null;
            StatesDao sdao=new StatesDaoImpl();
            try
            {
                for(int i=0; i<stateCodeList.size(); i++)
                {
                    stateCode=(String)stateCodeList.get(i);
                    state=sdao.getStateByStateCode(stateCode);
                    stateList.add(state);
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return stateList;
    }*/
    public int getVersion()
    {
        int version=1;
        File file=new File(getXmlConfigurationFile());
        if(!file.exists())
        return 0;
        return version;
    }
    public static String[] getCharacterArray()
    {
        String[] characterArray={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
        return characterArray;
    }
    public int getRandomNumberForId()
    {
        Random random=new Random();
        int randomNum=random.nextInt(62);
        return randomNum;
    }
    public List getCharactersInList()
    {
        String[] characterArray=getCharacterArray();
        List list=new ArrayList();
        for(int i=0; i<characterArray.length; i++)
        {
            list.add(characterArray[i]);
        }
        return list;
    }
    public String generateUniqueId()
    {
        List characterList=getCharactersInList();
        String uniqueId="";
        for(int i=0; i<11; i++)
        {
            Collections.shuffle(characterList);
            uniqueId+=characterList.get(getRandomNumberForId());
        }
    //    System.err.println("recId is "+uniqueId);
        return uniqueId;
    }
    public String generateUniqueId(int len)
    {
        List characterList=getCharactersInList();
        String uniqueId="";
        for(int i=0; i<len; i++)
        {
            Collections.shuffle(characterList);
            uniqueId+=characterList.get(getRandomNumberForId());
        }
       return uniqueId;
    }
    public String getXmlConfigurationFile()
    {
        return getConfigurationDirectory()+seperator+"configurationInfo.xml";
    }
    public String getDhisExportPath()
    {
        return AppUtility.getResourceLocation()+seperator+"Transfer"+seperator+"dhis";
    }
    public String getDefaultDatabaseDirectory()
    {
        return AppUtility.dbRootDirectory+seperator+"dbs";
    }
    public String getDbExportZipDirectory()
    {
        return getResourceLocation()+seperator+"Transfer"+seperator+"Zips";
    }
    public String changeFirstLetterToUpperCase(String str)
    {
        //System.err.println("str in AppUtility.changeFirstLetterToUpperCase is "+str);
        String finalStr=null;
        if(str !=null && str.trim().length()>0)
        {
            String firstLetter=str.substring(0,1);
            finalStr=firstLetter.toUpperCase();
            if(str.length()>1)
            finalStr=finalStr+str.substring(1);
        }
        //System.err.println("finalStr is "+finalStr);
        return finalStr;
    }
    public String getLicenseConfigFile()
    {
        return getConfigurationDirectory()+seperator+"license.txt";
    }
    public String getLoginConfigFile()
    {
        return getConfigurationDirectory()+seperator+"loginconfig.txt";
    }
    public static String getMetadataConfigFile()
    {
        return getConfigurationDirectory()+seperator+"metadataconfig.txt";
    }
    public static String getDatimFacilityFile()
    {
        return getConfigurationDirectory()+seperator+"DATIMFacility.zip";
    }
    public static String getConfigurationDirectory()
    {
        return getResourceLocation()+seperator+"conf";
    }
    public static String getResourcesDirectory()
    {
        return getResourceLocation()+seperator+"Resources";
    }
    public String changeToUppercase(String word)
    {
        if(word !=null)
        word=word.toUpperCase();
        return word;
    }
    public String getUserAssignedOrgCode(String orgCode)
    {
        if(orgCode !=null && orgCode.indexOf("/") !=1)
        orgCode=orgCode.substring(orgCode.lastIndexOf("/")+1);
        return orgCode;
    }
    public String capitalizeFirstLetter(String word)
    {
        if(word !=null && word.length()>0)
        {
            if(word.length()>1)
            {
                String firstLetter=word.substring(0, 1);
                firstLetter=firstLetter.toUpperCase();
                word=firstLetter+word.substring(1);
            }
            else
            word=word.toUpperCase();    
        }
        return word;
    }
    
    public String padNumberWithZeros(String num,int len)
    {
        if(num !=null)
        {
            while(num.length() < len)
            {
                num="0"+num;
            }
        }
        return num;
    }
    public String getDbDirectoryFromConfigFile()
    {
        String result=readFiles(getLoginConfigFile(), "");
        if(result==null || result.equalsIgnoreCase("filedoesnotexist"))
        return null;
        else
        return result;
    }
    public boolean loginConfigFileExists()
    {
        boolean fileExists=true;
        File file=new File(getLoginConfigFile());
        if(!file.exists())
        fileExists=false;
        return fileExists;
    }
    public boolean userIsLicensed()
    {
        String result=readFiles(getLicenseConfigFile(), "");
        if(result==null || result.equalsIgnoreCase("filedoesnotexist"))
        return false;
        //else if(result !=null && result.equalsIgnoreCase("filedoesnotexist"))
        //return true;
        else
        {
            String[] defaultParameter=result.split(":");
            if(defaultParameter.length >1 && defaultParameter[1].equalsIgnoreCase("on"))
            return true;
        }
        return false;
    }
    public boolean isDefaultAccountEnabled()
    {
        String result=readFiles(getLoginConfigFile(), "");
        if(result==null || result.equalsIgnoreCase("filedoesnotexist"))
        return false;
        else
        {
            String[] defaultParameter=result.split(":");
            if(defaultParameter.length >1 && defaultParameter[1].equalsIgnoreCase("on"))
            return true;
        }
        return false;
    }
    public void disableDefaultAccount()
    {
        createDirectories(getConfigurationDirectory());
        writeFile(getLoginConfigFile(), "DefaultLogin:off");
    }
    public static boolean isMetadataAccessEnabled()
    {
        String result=readFiles(getMetadataConfigFile(), "");
        if(result==null || result.equalsIgnoreCase("filedoesnotexist"))
        return false;
        else
        {
            String[] defaultParameter=result.split(":");
            if(defaultParameter.length >1 && defaultParameter[1].equalsIgnoreCase("on"))
            return true;
        }
        return false;
    }
    public void disableMetadataAccss()
    {
        createDirectories(getConfigurationDirectory());
        writeFile(getMetadataConfigFile(), "Metadataaccess:off");
    }
    //
    /*public boolean hasPriviledgeToAccessPage(HttpSession session)
    {
        if(!isUserInRole(session, "Administrator") && !isUserInRole(session, "DataEntry"))
        return false;
        return true;
    }*/
    public String replaceRegex(String parentString,String target,String replacement)
    {
        parentString=parentString.replaceAll(target,replacement);
        return parentString;
    }
    public void createDatabase(String source,String destination)
    {
        String dbFolder=destination+seperator+"pomisdb";
        File file=new File(dbFolder);
        if(!file.exists())
        {
            copyAndPasteDbFiles(source, destination);
        }
    }
    public boolean copyAndPasteDbFiles(String source,String destination)
    {
        System.err.println("source is "+source);
        System.err.println("destination is "+destination);
        String seperator="\\";
        if(source !=null && source.indexOf("/") !=-1)
        seperator="/";
        String[] files=null;
        File sourceDirectory=new File(source);
        File destinationFile=new File(destination);
        if(!destinationFile.exists())
        destinationFile.mkdirs();
        File sourceFile;
        files=sourceDirectory.list();
        if(files !=null)
        {
            for(int i=0; i<files.length; i++)
            {
               sourceFile =new File(source+seperator+files[i]);
               if(sourceFile.isDirectory())
               {
                   destinationFile=new File(destination+seperator+files[i]);
                   destinationFile.mkdirs();
                   copyAndPasteDbFiles(sourceFile.getAbsolutePath(),destinationFile.getAbsolutePath());
               }
               else
               {
                   String parent=sourceFile.getParent();
                   String immediateParentFolder=parent.substring(parent.lastIndexOf(seperator)+1, parent.length());
                   System.err.println("destinationFile is "+destinationFile);
                   System.err.println("immediateParentFolder is "+immediateParentFolder);
                   String destPath=destinationFile.getAbsolutePath().substring(0,destinationFile.getAbsolutePath().indexOf(immediateParentFolder));
                   System.err.println("destPath is "+destPath);
                   destPath=destPath+immediateParentFolder+seperator+files[i];
                   System.err.println("destPath2 is "+destPath);
                   writeFilesAsStream(sourceFile.getPath(), destPath);
               }
            }
        }
        return true;
    }
    public boolean copyAndPasteFiles(String source,String destination)
    {
        String seperator="\\";
        if(source !=null && source.indexOf("/") !=-1)
        seperator="/";
        String[] files=null;
        File sourceDirectory=new File(source);
        File destinationFile=new File(destination);
        if(!destinationFile.exists())
        destinationFile.mkdirs();
        File sourceFile;
        files=sourceDirectory.list();
        for(int i=0; i<files.length; i++)
        {
           sourceFile =new File(source+seperator+files[i]);
           if(sourceFile.isDirectory())
           {
               destinationFile=new File(destination+seperator+files[i]);
               destinationFile.mkdirs();
               copyAndPasteFiles(sourceFile.getAbsolutePath(),destinationFile.getAbsolutePath());
           }
           else
           {
               String parent=sourceFile.getParent();
               String immediateParentFolder=parent.substring(parent.lastIndexOf(seperator)+1, parent.length());
               System.err.println("destinationFile is "+destinationFile);
               System.err.println("immediateParentFolder is "+immediateParentFolder);
               String destPath=destinationFile.getAbsolutePath().substring(0,destinationFile.getAbsolutePath().indexOf(immediateParentFolder));
               System.err.println("destPath is "+destPath);
               destPath=destPath+immediateParentFolder+seperator+files[i];
               System.err.println("destPath2 is "+destPath);
               writeFilesAsStream(sourceFile.getPath(), destPath);
           }
        }
        return true;
    }
    public List copyFilePathsIntoList(String source)
    {
        String[] files=null;
        File sourceDirectory=new File(source);
        List pathList=new ArrayList();
        String seperator="\\";
        if(source !=null && source.indexOf("/") !=-1)
        seperator="/";
        File sourceFile;
        files=sourceDirectory.list();
        for(int i=0; i<files.length; i++)
        {
           sourceFile =new File(source+seperator+files[i]);
           if(sourceFile.isDirectory())
           {
               pathList.addAll(copyFilePathsIntoList(sourceFile.getAbsolutePath()));
           }
           else
           {
               pathList.add(source+seperator+files[i]);
           }
        }
        return pathList;
    }
    public static List readFiles(String filePath)
    {
	  BufferedReader reader;
          List list=new ArrayList();
          File file=new File(filePath);
          System.err.println("filePath is "+filePath);
          String str=null;
      try
      {
           if(file.exists())
           {
               System.err.println("filePath "+filePath+" exists");
		int i=0;
		reader=new BufferedReader(new FileReader(file));
		while ((str=reader.readLine())!=null)
		{
                    System.err.println("str is "+str);
			list.add(str);
			i++;
		}
		reader.close();
		if(i==0)
		return null;
           }
          else
	  {
		return null;
	  }
     }
     catch(Exception e)
     {
	System.out.println("Could not read file");
	e.printStackTrace();
	return null;
     }
     return list;
}
    public static String readFiles(String filePath, String delimiter)
    {
	  BufferedReader reader;
          String buffer=new String();
	  File file=new File(filePath);
          String str=null;
      try
      {
           if(file.exists())
           {
		int i=0;
		reader=new BufferedReader(new FileReader(file));
		while ((str=reader.readLine())!=null)
		{
                    buffer+=str;
                    if(delimiter !=null)
                    buffer+=delimiter;
			i++;
		}
		reader.close();
		if(i==0)
		return null;
           }
          else
	  {
		return "filedoesnotexist";
	  }
     }
     catch(Exception e)
     {
	System.out.println("Could not read file");
	e.printStackTrace();
	return null;
     }
     return buffer;
}
    public void writeFile(String fileName,List list)
    {
        OutputStreamWriter out;
        PrintWriter pw=null;
        try
        {
            //out = new OutputStreamWriter(new FileOutputStream(fileName));
            pw=new PrintWriter(fileName,"UTF-8");
            for(int i=0; i<list.size(); i++)
            {
                //out.write("\n");
                pw.println((String)list.get(i));
            }
            pw.close();
        }
        catch(FileNotFoundException fnfe)
        {
            try
            {
                fnfe.printStackTrace();
                //out.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Other exception occured "+ex.getMessage());
        }
    }
    public void writeFile(String fileName,String content)
    {
        OutputStreamWriter out;
        try
        {
            out = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
            out.write(content);
            out.close();
        }
        catch(FileNotFoundException fnfe)
        {
            try
            {
                fnfe.printStackTrace();
                //out.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Other exception occured "+ex.getMessage());
        }
    }
    public Object readObjectsFromFile(String fileName)
    {
        ObjectInputStream ois=null;
        Object obj=null;
        try
        {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            obj=ois.readObject();
            ois.close();
        }
        catch(FileNotFoundException fnfe)
        {
            try
            {
                fnfe.printStackTrace();
                ois.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Other exception occured "+ex.getMessage());
        }
        return obj;
    }
    public void writeFilesAsStream(String sourceFileName,String destinationFileName)
    {
        FileOutputStream fos=null;
        try
        {
            File file=new File(sourceFileName);
            byte[] outputByte=new byte[(int)file.length()];
            FileInputStream fileInputStream=new FileInputStream(file);
            fileInputStream.read(outputByte);
            fileInputStream.close();

            fos=new FileOutputStream(destinationFileName);
            fos.write(outputByte);
            fos.close();
        }
        catch(FileNotFoundException fnfe)
        {
            try
            {
                fnfe.printStackTrace();
                fos.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Other exception occured "+ex.getMessage());
        }
    }
    public void writeObjectsToFile(String fileName,Object content)
    {
        ObjectOutputStream out=null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(content);

            out.close();
        }
        catch(FileNotFoundException fnfe)
        {
            try
            {
                fnfe.printStackTrace();
                out.close();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        catch(Exception ex)
        {
            System.err.println("Other exception occured "+ex.getMessage());
        }
    }
    public void deleteFiles(String directoryPath)
    {
        File file=new File(directoryPath);
        String[] files=null;
        if(file.isDirectory())
        {
         files=file.list();
         File file2=null;
         String filePath=null;
         for(int i=0; i<files.length; i++)
         {
             filePath=directoryPath+"\\"+files[i];
             file2=new File(filePath);      
             if(file2.isDirectory())
             deleteFiles(file2.getPath());
             file2.delete();
         }
        }
        else
        file.delete();
    }
    public boolean isUserInRole(HttpSession session,String role)
    {
        boolean userInRole=false;
        String userRole=(String)session.getAttribute("userrole");
        if(userRole !=null && userRole.equalsIgnoreCase(role))
        userInRole=true;
        return userInRole;
    }
    public String getUserRole(HttpSession session)
    {
        String userRole=(String)session.getAttribute("userrole");
        return userRole;
    }
    public User getCurrentUser(HttpSession session)
    {
        User user=(User)session.getAttribute("USER");
        return user;
    }
    public String removeTags(String str)
    {
        String str2="";
        if(str !=null)
        {
            str2=str.replace("<b>", " ");
            str2=str2.replace("</b>", " ");
        }
        return str2;
    }
    public String getCVIStatus(int score)
    {
        String vulStatus="";
        if(score >0 && score <9)
        vulStatus="Vunerable";
        else if(score >8 && score <13)
        vulStatus="More vunerable";
        else if(score >12)
        vulStatus="Most vulnerable";

        return vulStatus;
    }
    public String getHVAStatus(int score)
    {
        String vulStatus="";
        if(score <AppConstant.VULNERABLE_STARTVALUE)
        vulStatus=AppConstant.NOTASSESSED_VULNERABLE_STATUS;
        else if(score >AppConstant.NOTVULNERABLE_ENDVALUE && score <AppConstant.MOREVULNERABLE_STARTVALUE)
        vulStatus=AppConstant.VULNERABLE_STATUS;
        else if(score >AppConstant.VULNERABLE_ENDVALUE && score <AppConstant.MOSTVULNERABLE_STARTVALUE)
        vulStatus=AppConstant.MOREVULNERABLE_STATUS;
        else if(score >AppConstant.MOREVULNERABLE_ENDVALUE)
        vulStatus=AppConstant.MOSTVULNERABLE_STATUS;
        return vulStatus;
    }
    public List getReasonForWithdrawal(int score)
    {
        List list=new ArrayList();
        
        return list;
    }
    public String[] listFiles(String directoryPath)
    {
        File file=new File(directoryPath);
        String[] files=null;
        StringBuilder s=new StringBuilder();

        if(file.isDirectory())
        {
            files=file.list();
        }
        //if(files==null)
        
        return files;
    }
    public String padSerialNumber(String number)
    {
        if(number !=null)
        {
            if(number.length() ==1)
            number="0000"+number;
            else if(number.length() ==2)
            number="000"+number;
            else if(number.length() ==3)
            number="00"+number;
            else if(number.length() ==4)
            number="0"+number;
        }

        return number;
    }
    /*public String getCurrentDate()
    {
        String dateFormat="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
        String currentDate=sdf.format(new java.util.Date());
        return currentDate;
    }
    public List generatDate(int startYear)
    {
        List listOfDates=new ArrayList();
        for(int i=startYear; i<=2020; i++)
        {
            listOfDates.add(i);
        }
        return listOfDates;
    }
    public List generateMonths(int startMonth)
    {
        List listOfMonths=new ArrayList();
        String[] months={"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(int i=startMonth-1; i<months.length; i++)
        {
            listOfMonths.add(months[i]);
        }
        return listOfMonths;
    }
    public List generateMonths()
    {
        Month month=null;
        String[] monthArray={"01","January","02","February","03","March","04","April","05","May","06","June","07","July","08","August","09","September","10","October","11","November","12","December"};
        List monthList=new ArrayList();
        for(int i=0; i<monthArray.length; i+=2)
        {
            month=new Month();
            month.setValue(monthArray[i]);
            month.setName(monthArray[i+1]);
            monthList.add(month);
        }
        return monthList;
    }
    public List generateYears(int firstYr,int lastYr)
    {
         List yearList=new ArrayList();
         for(int i=firstYr; i<lastYr+1; i++)
         {
             yearList.add(i);
         }
         return yearList;
    }
    public String getPreviousDate(int year,int mth,int day,int diff)
    {
        String dateFormat="yyyy/MM/dd  hh:mm:ss";
      SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
	Calendar c=new GregorianCalendar(year,mth,day);
	c.add(Calendar.MONTH,diff);
        String previousMonth=sdf.format(c.getTime());
        return previousMonth;
    }
    public String getCurrentMthAndDate()
    {
        String currentDate=getCurrentDate();
        String[] dateArray=currentDate.split("-");
        String month=util.getFullMonthAsString(Integer.parseInt(dateArray[1]));
        String today=month+" "+dateArray[2]+", "+dateArray[0];
        return today;
    }
    public String getDisplayDate(String mysqlDate)
    {
        String displayDate=null;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateArray=mysqlDate.split("-");
            displayDate=dateArray[1]+"/"+dateArray[2]+"/"+dateArray[0];
        }
        return displayDate;
    }
    
    public String monthDayYear(String mysqlDate)
    {
        String displayDate=null;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateArray=mysqlDate.split("-");
            displayDate=dateArray[1]+"/"+dateArray[2]+"/"+dateArray[0];
        }
        return displayDate;
    }
    public String convertMysqlDateTomonthDayYear(String mysqlDate)
    {
        String displayDate=null;
        if(mysqlDate !=null && mysqlDate.indexOf("-") !=-1)
        {
            String[] dateArray=mysqlDate.split("-");
            displayDate=dateArray[1]+"/"+dateArray[2]+"/"+dateArray[0];
        }
        return displayDate;
    }
    public String processMthDayYearToMysqlFormat(String date)
    {
        String[] dateArray=null;
        String mysqlDate=date;
        if(date !=null && !date.equalsIgnoreCase("All"))
        {
            if(date.indexOf("/") !=-1)
            {
                dateArray=date.split("/");
                if(dateArray !=null && dateArray.length>2)
                mysqlDate=getMySqlDate(dateArray[1],dateArray[0],dateArray[2]);
            }
        }
        return mysqlDate;
    }
    public long monthsBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long monthsBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.MONTH, 1);
            monthsBetween++;
        }
        return monthsBetween;
    }
    public String getMySqlDate(String day,String month,String year)
    {
        String mysqlDate=year+"-"+month+"-"+day;
        return mysqlDate;
    }*/
    public void createDirectories(String directoryPath)
    {
        try
        {
            boolean success = (new File(directoryPath)).mkdirs();
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
    }

public void createDirectory(String directoryPath)
{
    try
    {
        boolean success = (new File(directoryPath)).mkdir();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
}
    public String getFullMonthAsString(int mth)
    {
        String month=null;
        if(mth==1 || mth==01)
        month="January";
        else if(mth==2 || mth==02)
        month="February";
        else if(mth==3 || mth==03)
        month="March";
        else if(mth==4 || mth==04)
        month="April";
        else if(mth==5 || mth==05)
        month="May";
        else if(mth==6 || mth==06)
        month="June";
        else if(mth==7 || mth==07)
        month="July";
        else if(mth==8)
        month="August";
        else if(mth==9)
        month="September";
        else if(mth==10)
        month="October";
        else if(mth==11)
        month="November";
        else if(mth==12)
        month="December";
        return month;
    }
    public String getMonthAsString(int mth)
    {
        String month=null;
        if(mth==1 || mth==01)
        month="Jan";
        else if(mth==2 || mth==02)
        month="Feb";
        else if(mth==3 || mth==03)
        month="Mar";
        else if(mth==4 || mth==04)
        month="Apr";
        else if(mth==5 || mth==05)
        month="May";
        else if(mth==6 || mth==06)
        month="Jun";
        else if(mth==7 || mth==07)
        month="Jul";
        else if(mth==8)
        month="Aug";
        else if(mth==9)
        month="Sep";
        else if(mth==10)
        month="Oct";
        else if(mth==11)
        month="Nov";
        else if(mth==12)
        month="Dec";
        return month;
    }
    public int getMonthAsNumber(String mth)
    {
        int month=1;
        if (mth !=null)
        {
            if(mth.equalsIgnoreCase("Jan") || mth.equalsIgnoreCase("January"))
            month=1;
            else if(mth.equalsIgnoreCase("feb") || mth.equalsIgnoreCase("february"))
            month=2;
            else if(mth.equalsIgnoreCase("mar") || mth.equalsIgnoreCase("march"))
            month=3;
            else if(mth.equalsIgnoreCase("apr") || mth.equalsIgnoreCase("april"))
            month=4;
            else if(mth.equalsIgnoreCase("may") || mth.equalsIgnoreCase("may"))
            month=5;
            else if(mth.equalsIgnoreCase("jun") || mth.equalsIgnoreCase("june"))
            month=6;
            else if(mth.equalsIgnoreCase("jul") || mth.equalsIgnoreCase("july"))
            month=7;
            else if(mth.equalsIgnoreCase("aug") || mth.equalsIgnoreCase("august"))
            month=8;
            else if(mth.equalsIgnoreCase("sep") || mth.equalsIgnoreCase("september"))
            month=9;
            else if(mth.equalsIgnoreCase("oct") || mth.equalsIgnoreCase("october"))
            month=10;
            else if(mth.equalsIgnoreCase("nov") || mth.equalsIgnoreCase("november"))
            month=11;
            else if(mth.equalsIgnoreCase("dec") || mth.equalsIgnoreCase("december"))
            month=12;
        }
        return month;
    }
    public String getLastDayOfMonth(String mth)
    {
        String lastDay="31";
        if(mth.equalsIgnoreCase("02"))
        lastDay="28";
        else if(mth.equalsIgnoreCase("04") || mth.equalsIgnoreCase("06") || mth.equalsIgnoreCase("09") || mth.equalsIgnoreCase("11"))
        lastDay="30";
        return lastDay;
    }
    public List getAgeList(int startAge,int endAge)
    {
        List ageList=new ArrayList();
        for(int i=startAge; i<endAge; i++)
        {
            ageList.add(i);
        }
        return ageList;
    }
    public List tokenizeStr(String str, String delim)
    {
        List tokenList = new ArrayList();
        StringTokenizer st = new StringTokenizer(str, delim);
        while (st.hasMoreTokens())
        {
            tokenList.add(st.nextToken());
        }
        return tokenList;
    }
public String[] splitServices(String service)
{
    //System.err.println("service is "+service);
   String[] services=null;
   if(service !=null)
   {
       service=service.replaceAll(";", ",");
        if(service.indexOf(",") != -1)
        services=service.split(",");
        else
        {
            services=new String[1];
            services[0]=service;
        }
        services=trimString(services);
   }
   return services;
}
public String[] splitString(String regex,String seperator)
{
    //System.err.println("regex is "+regex);
   String[] contents=null;
   if(regex !=null)
   {
        if(regex.indexOf(seperator) != -1 || regex.indexOf(";") != -1)
        {
            regex=regex.replace(";", seperator);
            contents=regex.split(seperator);
        }
        else
        {
            contents=new String[1];
            contents[0]=regex;
        }
        contents=trimString(contents);
   }
   return contents;
}
public String cleanString(String regex,String seperator)
{
   if(regex !=null)
   {
       if(seperator !=null)
       {
            if(regex.indexOf(seperator) != -1 && regex.startsWith(seperator))
            {
                regex=regex.substring(1);
            }    
       }
   }
   return regex;
}
public String[] trimString(String[] array)
{
   String[] trimmedArray=null;
   String str="";
   if(array !=null)
   {
       if(array.length==1)
       {
           trimmedArray=new String[1];
           trimmedArray[0]=array[0].trim();
       }
       else
       {
            for(int i=0; i<array.length; i++)
            {
                if(i==array.length-1)
                str+=array[i].trim();
                else
                str+=array[i].trim()+";";
            }
        trimmedArray=str.split(";");
       }
   }
   return trimmedArray;
}
    public String concatStr(String[] strArray,String additionalString)
    {
        String str="";
        if(strArray !=null && strArray.length>0)
        {
            for(int i=0; i<strArray.length; i++)
            {
                if(i==strArray.length-1)
                str+=strArray[i];
                else
                str+=strArray[i]+",";
            }
        }
        if(additionalString !=null && !additionalString.isEmpty() && !additionalString.equals(" ") && !additionalString.equals("") && !additionalString.equals("  "))
        {
            if(str ==null || str.equalsIgnoreCase(""))
            str=additionalString;
            else
            str+=";"+additionalString;
        }
        return str;
    }

}
