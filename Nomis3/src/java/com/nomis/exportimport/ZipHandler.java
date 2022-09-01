/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.exportimport;


import com.nomis.ovc.util.AppUtility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author COMPAQ USER
 */
public class ZipHandler implements Serializable
{
    Enumeration enumEntries;
    ZipFile zip;
    AppUtility appUtil=new AppUtility();
    
public ZipHandler()
{
    //appUtil.setDbRootDirectory(appUtil.getUserHomeDirectory());
}
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
public void deleteDirectory(String directoryPath)
{
    try
    {
        boolean success = (new File(directoryPath)).delete();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
}
/*public void unZipFile(String zipFilePath)
{
	//OutputStream os= null;
        System.err.println("zipFilePath is "+zipFilePath);
	try
	{
            zip = new ZipFile(zipFilePath);
            
            enumEntries = zip.entries();
            String directory=null;
            String exportFilePath=null;
            String fileName=null;

      	while (enumEntries.hasMoreElements())
	{
                    
                    directory=appUtil.getExportFilePath();
                    //directory=zipentry.getName().substring(0,zipentry.getName().lastIndexOf("\\"));
                    //System.err.println("zipentry.getName() is "+zipentry.getName());
                    
                    
                    ZipEntry zipentry = (ZipEntry) enumEntries.nextElement();
                    fileName=zipentry.getName();
                    File file=new File(fileName);
                    if(file !=null)
                    System.err.println("file.getParent() is "+file.getParent());
                    if(fileName !=null && fileName.indexOf("\\") !=-1)
                    {
                        fileName=fileName.replace("\\", "/");
                        String[] fileNameArray=fileName.split("/");
                        for(int i=0; i<fileNameArray.length; i++)
                        {
                            //add immediate parent to the directory structure
                            if(i==fileNameArray.length-2)
                            directory+="\\"+fileNameArray[i];
                            else if(i==fileNameArray.length-1)
                            exportFilePath=directory+"\\"+fileNameArray[i];
                        }
                    }
                    System.err.println("exportFilePath is "+exportFilePath);
                    createDirectories(directory);
                    extractFile(zip.getInputStream(zipentry), new FileOutputStream(exportFilePath));
                    
	}
	}
	catch (IOException ioe)
	{
            //ioe.printStackTrace();
            return;
        }
}*/
public void unZipFile(String zipFilePath,String destination)
{
	//OutputStream os= null;
        //System.err.println("zipFilePath is "+zipFilePath);
        //System.err.println("destination is "+destination);
        appUtil.createDirectories(destination);
        if(destination ==null)
        {
            destination=appUtil.getExportFilePath();
        }
        String exportFilePath=null;
            try
            {
                zip = new ZipFile(zipFilePath);

                enumEntries = zip.entries();
                String directory=null;
                
                String fileName=null;

                while (enumEntries.hasMoreElements())
                {
                    directory=destination;
                    File destinationDir=new File(destination);
                    if(!destinationDir.exists())
                    destinationDir.mkdirs();
                    ZipEntry zipentry = (ZipEntry) enumEntries.nextElement();
                    fileName=zipentry.getName();
                    File file=new File(fileName);
                    if(file !=null)
                    {
                        //System.err.println("file.getParent() is "+file.getParent()+" file name is "+file.getName());
                        exportFilePath=directory+"\\"+file.getName();
                    }
                    if(fileName !=null && fileName.indexOf("\\") !=-1)
                    {
                        fileName=fileName.replace("\\", "/");
                        String[] fileNameArray=fileName.split("/");
                        for(int i=0; i<fileNameArray.length; i++)
                        {
                            //add immediate parent to the directory structure
                            if(i==fileNameArray.length-2)
                            directory+="\\"+fileNameArray[i];
                            else if(i==fileNameArray.length-1)
                            exportFilePath=directory+"\\"+fileNameArray[i];
                        }
                    }
                    //System.err.println("exportFilePath is "+exportFilePath);
                    createDirectories(directory);
                    if(exportFilePath !=null)
                    {
                        extractFile(zip.getInputStream(zipentry), new FileOutputStream(exportFilePath));
                    }
                }
                zip.close();
            }
            catch (IOException ioe)
            {
                closeZip(zip);
                ioe.printStackTrace();
                return;
            }
        
}
public void closeZip(ZipFile zip)
{
    try
    {
        if(zip !=null)
        zip.close();
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
}
public String zipFile(List listOfFilesToBeZipped,String destinationDirectory,String zipFileName)
{
    String zipFilePathAndName=null;
    appUtil.createDirectories(destinationDirectory);
    byte[] buf = new byte[1024];
    try
    {
        if(zipFileName==null || listOfFilesToBeZipped==null)
        {
            System.err.println("Enter valid name for the zip file and the Files to be zipped");
            return null;
        }
        if(destinationDirectory !=null)
        {
            createDirectories(destinationDirectory);
        }
        else
        destinationDirectory=" ";
        while(zipFileName.indexOf("/") !=-1 || zipFileName.indexOf("\\") !=-1)
        {
            zipFileName=zipFileName.replace("/", "_");
            zipFileName=zipFileName.replace("\\", "_");
        }
        zipFilePathAndName = destinationDirectory+"/"+zipFileName+".zip";
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilePathAndName));
        String fileName="";
        File file = null;
        for(int count=0; count<listOfFilesToBeZipped.size(); count++)
        {
            fileName =(String)listOfFilesToBeZipped.get(count);
            file = new File(fileName);
            if (!file.exists())
            {
                break;
            }
            FileInputStream in = new FileInputStream(fileName);
            out.putNextEntry(new ZipEntry(fileName));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    return zipFilePathAndName;
}
public String zipFiles(List fileList,String outputDirectory,String zipFileName)
{
    String zipFilePathAndName=null;
    byte[] buf = new byte[1024];
        
    try
    {
        createDirectories(outputDirectory);
        
        zipFilePathAndName =outputDirectory+ "/"+zipFileName+".zip";
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilePathAndName));
        String fileName = null;
        File file = null;
        for(int i=0; i<fileList.size(); i++)
        {
            fileName = (String)fileList.get(i);
           // System.err.println("fileName in zipFiles is "+fileName);
            if(fileName==null)
            continue;
            file = new File(fileName);
            if (!file.exists()) 
            {
                continue;
            }
            FileInputStream in = new FileInputStream(fileName);
            out.putNextEntry(new ZipEntry(fileName));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            
            out.closeEntry();
            in.close();
        }
        out.close();
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    }
    return zipFilePathAndName;
}
public static void extractFile(InputStream inStream,OutputStream outStream)
  throws IOException
  {
    byte[] buf = new byte[1024];
    int l;
    while ((l = inStream.read(buf)) >= 0)
	{
      outStream.write(buf, 0, l);
    }
    inStream.close();
    outStream.close();
  }
}
