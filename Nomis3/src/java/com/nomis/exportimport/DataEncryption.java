/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.exportimport;

import com.nomis.ovc.business.User;
import com.nomis.ovc.dao.DaoUtility;
import com.nomis.ovc.dao.UserDao;
import com.nomis.ovc.dao.UserDaoImpl;
import com.nomis.ovc.util.AppUtility;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author COMPAQ USER
 */
public class DataEncryption implements Serializable
{
Random randomNum=new Random();
UserDao userDao=new UserDaoImpl();
AppUtility appUtil=new AppUtility();
DaoUtility util=new DaoUtility();
    public DataEncryption()
    {
        
    }
    /*public List encryptOvcInfo(List ovcList,HttpSession session)
    {
        if(ovcList ==null)
        return ovcList;
        List encryptedOvcList=new ArrayList();
        User user=appUtil.getUser(session);
        //List ovcWithCaregiverInfoList=util.getOvcWithCaregiverInfo(ovcList);
        if(userInRole(user))
        return ovcList;
        //if(ovcList !=null)
        //{
          
        for(int i=0; i<ovcList.size();i++)
        {
            Ovc ovc=(Ovc)ovcList.get(i);
            ovc.setFirstName(encryptData(ovc.getFirstName()));
            ovc.setLastName(encryptData(ovc.getLastName()));
            ovc.setAddress(encryptData(ovc.getAddress()));
            ovc.setPhone(encryptData(ovc.getPhone()));
            ovc.setCaregiverName(encryptData(ovc.getCaregiverName()));
            ovc.setCaregiverAddress(encryptData(ovc.getCaregiverAddress()));
            ovc.setCaregiverPhone(encryptData(ovc.getCaregiverPhone()));
            encryptedOvcList.add(ovc);
        }
        //}
        return encryptedOvcList;
    }
    public List encryptCVIInfo(List cviList,HttpSession session)
    {
        List encryptedCviList=new ArrayList();
        User user=appUtil.getUser(session);
        if(userInRole(user))
        {
        return cviList;
        }
        for(int i=0; i<cviList.size();i++)
        {
            ChildVulnerabilityIndex cvi=(ChildVulnerabilityIndex)cviList.get(i);
            cvi.setFirstName(encryptData(cvi.getFirstName()));
            cvi.setSurName(encryptData(cvi.getSurName()));
            cvi.setAddress(encryptData(cvi.getAddress()));
            encryptedCviList.add(cvi);
        }
        return encryptedCviList;
    }
    public List encryptCaregiverInfo(List hviList,HttpSession session)
    {
        List encryptedList=new ArrayList();
        //System.err.println("hviList size is "+hviList.size());
        User user=appUtil.getUser(session);
        if(userInRole(user))
        return hviList;
        //System.err.println("hviList 1 size is "+hviList.size());
        for(int i=0; i<hviList.size();i++)
        {
            AdultHouseholdMember cgiver=(AdultHouseholdMember)hviList.get(i);
            cgiver.setCaregiverFirstname(encryptData(cgiver.getCaregiverFirstname()));
            cgiver.setCaregiverLastName(encryptData(cgiver.getCaregiverLastName()));
            cgiver.setCaregiverAddress(encryptData(cgiver.getCaregiverAddress()));
            cgiver.setCaregiverPhone(cgiver.getCaregiverPhone());
            encryptedList.add(cgiver);
        }
        return encryptedList;
    }
    public List encryptHVIInfo(List hviList,HttpSession session)
    {
        List encryptedHviList=new ArrayList();
        User user=appUtil.getUser(session);
        if(userInRole(user))
        return hviList;
        for(int i=0; i<hviList.size();i++)
        {
            HouseholdEnrollment hvi=(HouseholdEnrollment)hviList.get(i);
            hvi.setHhFirstname(encryptData(hvi.getHhFirstname()));
            hvi.setHhSurname(encryptData(hvi.getHhSurname()));
            hvi.setAddress(encryptData(hvi.getAddress()));
            hvi.setPhone(hvi.getPhone());
            encryptedHviList.add(hvi);
        }
        return encryptedHviList;
    }*/
    public String encryptData(String data)
    {
        String[] encrypChars={"%","@","*","^",">","?","[","&","!","|","{","/","-","+","\\","}","ß","K","=",")",":","(",";","0","=","1","2","3","A","B","4","C","H","9","5","6","7","Z","8"};
        String encrytedData="";
        if(data !=null)
        {
            for(int i=0; i<data.length(); i++)
            {
                int randomPos=randomNum.nextInt(encrypChars.length);
                String[] shuffledArray=(String[])shuffle(encrypChars);
                encrytedData+= shuffledArray[randomPos];
            }
        }
        return encrytedData ;
    } 
public String encryptData1(String wordToEncrypt)
{
	String[] padding={"^","*","@","5","3","u"};
        String wordInUpperCase=wordToEncrypt.toUpperCase();
        char[] wordInChar=wordInUpperCase.toCharArray();
	shuffle(padding);
        String encryptedWord=padding[0]+padding[2];
	for(char c: wordInChar)
	{
	   switch(c)
	   {
		case 'A':encryptedWord+='0';
		break;
		case 'B':encryptedWord+='%';
		break;
		case 'C':encryptedWord+='@';
		break;
		case 'D':encryptedWord+='*';
		break;
		case 'E':encryptedWord+='^';
		break;
		case 'F':encryptedWord+='>';
		break;
		case 'G':encryptedWord+='?';
		break;
		case 'H':encryptedWord+='[';
		break;
		case 'I':encryptedWord+='&';
		break;
		case 'J':encryptedWord+='!';
		break;
		case 'K':encryptedWord+='|';
		break;
		case 'L':encryptedWord+='{';
		break;
		case 'M':encryptedWord+='/';
		break;
		case 'N':encryptedWord+='-';
		break;
		case 'O':encryptedWord+='+';
		break;
		case 'P':encryptedWord+='\\';
		break;
		case 'Q':encryptedWord+='}';
		break;
		case 'R':encryptedWord+='ß';
		break;
		case 'S':encryptedWord+='#';
		break;
		case 'T':encryptedWord+='=';
		break;
		case 'U':encryptedWord+=')';
		break;
		case 'V':encryptedWord+=':';
		break;
		case 'W':encryptedWord+='(';
		break;
		case 'X':encryptedWord+=';';
		break;
		case 'Y':encryptedWord+='µ';
		break;
		case 'Z':encryptedWord+='_';
		break;
		case ' ':encryptedWord+='H';
		break;
		case '.':encryptedWord+='$';
		break;
		case ',':encryptedWord+='`';
		break;
		case '\'':encryptedWord+='<';
		break;

		case '0':encryptedWord+='A';
		break;
		case '1':encryptedWord+='B';
		break;
		case '2':encryptedWord+='C';
		break;
		case '3':encryptedWord+='D';
		break;
		case '4':encryptedWord+='E';
		break;
		case '5':encryptedWord+='F';
		break;
		case '6':encryptedWord+='G';
		break;
		case '7':encryptedWord+='I';
		break;
		case '8':encryptedWord+='J';
		break;
		case '9':encryptedWord+='K';
		break;
	   }
	}
return encryptedWord;
}
public String decryptData(String wordToDecrypt)
{
        char[] wordInChar=wordToDecrypt.toCharArray();
        String decryptedWord=" ";
	for(int i=2; i<wordInChar.length; i++)
	{
	  char c= wordInChar[i];
	   switch(c)
	   {
		case '0':decryptedWord+='A';
		break;
		case '%':decryptedWord+='B';
		break;
		case '@':decryptedWord+='C';
		break;
		case '*':decryptedWord+='D';
		break;
		case '^':decryptedWord+='E';
		break;
		case '>':decryptedWord+='F';
		break;
		case '?':decryptedWord+='G';
		break;
		case '[':decryptedWord+='H';
		break;
		case '&':decryptedWord+='I';
		break;
		case '!':decryptedWord+='J';
		break;
		case '|':decryptedWord+='K';
		break;
		case '{':decryptedWord+='L';
		break;
		case '/':decryptedWord+='M';
		break;
		case '-':decryptedWord+='N';
		break;
		case '+':decryptedWord+='O';
		break;
		case '\\':decryptedWord+='P';
		break;
		case '}':decryptedWord+='Q';
		break;
		case 'ß':decryptedWord+='R';
		break;
		case '#':decryptedWord+='S';
		break;
		case '=':decryptedWord+='T';
		break;
		case ')':decryptedWord+='U';
		break;
		case ':':decryptedWord+='V';
		break;
		case '(':decryptedWord+='W';
		break;
		case ';':decryptedWord+='X';
		break;
		case 'µ':decryptedWord+='Y';
		break;
		case '_':decryptedWord+='Z';
		break;
		case 'H':decryptedWord+=' ';
		break;
		case '$':decryptedWord+='.';
		break;
		case '`':decryptedWord+=',';
		break;
		case '<':decryptedWord+="'";
		break;

		case 'A':decryptedWord+='0';
		break;
		case 'B':decryptedWord+='1';
		break;
		case 'C':decryptedWord+='2';
		break;
		case 'D':decryptedWord+='3';
		break;
		case 'E':decryptedWord+='4';
		break;
		case 'F':decryptedWord+='5';
		break;
		case 'G':decryptedWord+='6';
		break;
		case 'I':decryptedWord+='7';
		break;
		case 'J':decryptedWord+='8';
		break;
		case 'K':decryptedWord+='9';
		break;
	   }
	}
return decryptedWord;
}
    private Object shuffle(String[] arrayData)
    {
        int secondPos=randomNum.nextInt(arrayData.length);
        for(int i=0; i<arrayData.length; i++)
        {
            String temp=arrayData[i];
            arrayData[i]=arrayData[secondPos];
            arrayData[secondPos]=temp;
        }
        return arrayData;
    }
    public boolean userInRole(User user)
    {
        if(user !=null && (user.getViewClientDetails().equalsIgnoreCase("yes") || user.getViewClientDetails().equalsIgnoreCase("on")))
        return true;
        return false;
    }
}
