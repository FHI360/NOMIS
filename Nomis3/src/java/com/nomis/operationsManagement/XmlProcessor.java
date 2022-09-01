/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nomis.operationsManagement;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author smomoh
 */
public class XmlProcessor
{
    public static String getNodeValue(String value,int s,NodeList listOfObjects)
    {
        String elementValue=" ";
        Node firstPersonNode = listOfObjects.item(s);
        Element firstPersonElement = (Element)firstPersonNode;
        NodeList firstNameList = firstPersonElement.getElementsByTagName(value);
        Element firstNameElement = (Element)firstNameList.item(0);
        if(firstNameElement !=null)
        {
            NodeList textFNList = firstNameElement.getChildNodes();
            try
            {
                if((Node)textFNList==null)
                elementValue="";
                else if((Node)textFNList.item(0)==null)
                elementValue="";
                else if(((Node)textFNList.item(0)).getNodeValue()==null || (((Node)textFNList.item(0)).getNodeValue()).equals("") || (((Node)textFNList.item(0)).getNodeValue()).equals(" ") || (((Node)textFNList.item(0)).getNodeValue()).equals("none"))
                elementValue="";
                else
                elementValue=((Node)textFNList.item(0)).getNodeValue();
            }
            catch(NullPointerException npe)
            {
                elementValue="";
                npe.printStackTrace();
            }
            catch(Exception ex)
            {
                elementValue="";
                ex.printStackTrace();
            }
        }
        return elementValue;
    }
    public static String getNodeName(String value,int s,NodeList listOfObjects)
    {
        String elementName=" ";
        Node firstPersonNode = listOfObjects.item(s);
        Element firstPersonElement = (Element)firstPersonNode;
        NodeList firstNameList = firstPersonElement.getElementsByTagName(value);
        Element firstNameElement = (Element)firstNameList.item(0);
        try
        {
            elementName=firstNameElement.getNodeName();
        }
        catch(NullPointerException npe)
        {
            elementName=null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return elementName;
    }
    public static String getTagValue(String filePath,String rootTagName,String nodeName)
    {
        String tagValue=null;
        try
        {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                File file=null;
                Document doc = null;
                String fileName=filePath;
                int t=0;
                int i=1;
                   file=new File(fileName);
                    if(!file.exists())
                    {
                        return tagValue;
                    }

                    doc = docBuilder.parse (file);
                    // normalize text representation
                    doc.getDocumentElement().normalize();
                    NodeList listOfObjects = doc.getElementsByTagName(rootTagName);
                    String tagName=null;
                for(int s=0; s<listOfObjects.getLength() ; s++)
                {
                    Node firstNode = listOfObjects.item(s);
                     if(firstNode.getNodeType() == Node.ELEMENT_NODE)
                     {
                         tagValue=getNodeValue(nodeName, s, listOfObjects);
                         System.err.println("tagValue is "+tagValue);                       
                     }
                }      
        }
        catch (SAXParseException err)
        {
                err.printStackTrace();
        }
        catch (SAXException e)
        {
                Exception x = e.getException ();
                ((x == null) ? e : x).printStackTrace ();
        }
        catch (Exception ex)
        {
                ex.printStackTrace ();
        }
    return tagValue;
}
    public String getAppropriateStringLength(String str,int length)
    {
        if(str !=null)
        {
            str=str.trim();
            if(str.length()>length-1)
            str=str.substring(0, length-2);
        }
        return str;
    }
}
