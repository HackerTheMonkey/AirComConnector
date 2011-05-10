/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aircom.test;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author hasaneinali
 */
public class ParsingEngine 
{

    public <T> void parseXmlDocument(File xmlDocument, OutputChannel outputChannel) 
            throws ParserConfigurationException, SAXException, IOException 
    {
        /**
         * Check of the received xmlDocument exist and is ready to be parsed,
         * if anything wrong with the file, need to throw an appropriate
         * exception.
         */
        if (xmlDocument == null || !xmlDocument.exists()) 
        {
            throw new IllegalArgumentException("Can't find the XML file to be parsed...");
        }
        /**
         * Create a new SAX Parser factory to be used to get an instance
         * of a SAX parser to parse the given XML document.
         */        
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        System.out.println("SAXParserFactory has been instantiated...");
        /**
         * Create a SAXParser instance
         */
        SAXParser saxParser = saxParserFactory.newSAXParser();
        System.out.println("SAXParser instance has been obtained...");
        /**
         * Create a new instance of the EventHandler to be passed as
         * arguments to the SAXParser along with the XML document to be
         * parsed.
         */
        EventHandler eventHandler = new EventHandler(xmlDocument.getName(), outputChannel);
        System.out.println("EventHandler instance has been created...");
        /**
         * Pass the instance of the EventHandler as well as the XML document
         * to the SAXParser to start the event-based parsing operation.
         */        
        saxParser.parse(xmlDocument, eventHandler);
        System.out.println("XML parsing started for: " + xmlDocument.getName());
    }
}
