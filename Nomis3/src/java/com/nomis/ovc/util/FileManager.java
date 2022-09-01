/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.ovc.util;

import java.io.File;
import java.nio.channels.FileChannel;
import org.apache.taglibs.standard.extra.spath.Path;
//import java.nio.file.Path;
//import java.nio.file.Paths;

/**
 *
 * @author smomoh
 */
public class FileManager 
{
    public static void moveFileToAnotherDirectory(String sourceFilePath,String destinationDirectoryPath)
    {
    	try
        {
            AppUtility appUtil=new AppUtility();
            String dateAndTime="";
            String fileName="";
            if(DateManager.getDefaultCurrentDateAndTime() !=null)
            {
                dateAndTime=DateManager.getDefaultCurrentDateAndTime();
                dateAndTime=dateAndTime.replaceAll(":", "-");
                dateAndTime="-"+dateAndTime;
            }
            //System.out.println("sourceFilePath is "+sourceFilePath);
            //System.out.println("destinationDirectoryPath is "+destinationDirectoryPath);
            if(sourceFilePath !=null)
            {
                if(destinationDirectoryPath !=null)
                {
                   File destinationDirectory=new File(destinationDirectoryPath);
                   destinationDirectory.mkdirs();
                   File file =new File(sourceFilePath);
                   if(file.exists())
                   {
                       fileName=file.getName();
                       String addedName="_"+dateAndTime+".zip";
                       if(fileName.endsWith(".zip"))
                       {
                           fileName=fileName.replace(".zip", addedName);
                       }
                       else
                       fileName=fileName+dateAndTime;   
                       //System.out.println("destinationDirectoryPath is "+destinationDirectoryPath+appUtil.getFileSeperator() + file.getName());
                       if(file.renameTo(new File(destinationDirectoryPath+appUtil.getFileSeperator()+ fileName)))
                       {
                            System.out.println("File is moved successful!");
                       }
                       else
                       {
                            System.out.println("File is failed to move!");
                       }
                   }
                   else
                   System.out.println("Unable to move file. It does not exist");
                }
            }

    	}
        catch(Exception ex)
        {
            ex.printStackTrace();
    	}
    }
    /*public long getFileSize(String filePath)
    {
        long fileSize=0;
        try
        {
            if(filePath !=null)
            {
                File file=new File(filePath);
                if(file.exists())
                {
                    Path path = Paths.get(filePath);
                    FileChannel fileChannel = FileChannel.open(path);
                    if(fileChannel !=null && fileChannel.isOpen())
                    fileSize=fileChannel.size();
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return fileSize;
    }*/
}
