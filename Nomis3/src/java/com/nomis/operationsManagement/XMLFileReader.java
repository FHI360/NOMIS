/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nomis.operationsManagement;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author Siaka
 */
public class XMLFileReader implements Serializable
{
    List<String> nameAndValueList=new ArrayList<String>();
    public List readXMLFile(String filePath)
    {
                File file=new File(filePath);
                if(!file.exists())
                return nameAndValueList;
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler()
                        {
                            boolean tagName=false;
                            boolean tagValue=false;
                            public void startElement(String uri, String localName,String qName,
			Attributes attributes) throws SAXException
                        {
                            nameAndValueList.add(qName);
                            tagName=true;
                            //System.err.println("qName is :" + qName);
                        }
					
			public void endElement(String uri, String localName,
			String qName) throws SAXException
                        {
                            //System.err.println("End Element :" + qName);
			}
			public void characters(char ch[], int start, int length) throws SAXException
                        {
                            String value=new String(ch, start, length);
                            if(tagName)
                            {
                                nameAndValueList.add(value);
                                //System.err.println("value is :" + value);
                                tagName=false;
                            }
			}
			};
                            //saxParser.parse(inputStream, handler);
			saxParser.parse(filePath, handler);
			} catch (Exception e) {
			e.printStackTrace();
			}
                    return nameAndValueList;
                }
}
