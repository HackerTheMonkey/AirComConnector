package com.aircom.test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author hasaneinali
 */
public class EventHandler extends DefaultHandler
{    
    private boolean isRootElementParsed = false;
    private DocTree<String> docTree;
    private DocNode<String> rootElement = null;
    private ElementStack<String> elementStack = new ElementStack<String> ();
    private OutputChannel outputChannel;
    /**
     * This variable will hold the name of the XML
     * file being processed, this will be passed to this
     * instance via the parameterized constructor.
     */
    private String documentName;
    /**
     * Any text that is encountered for the processed XML elements
     * will be stored in this text accumulator.
     */
    private StringBuffer textAccumulator;
    /**
     * The parameterized constructor.
     */
    public EventHandler(String documentName, OutputChannel outputChannel)
    {
        this.documentName = documentName;
        this.outputChannel = outputChannel;
    }
    /**
     * The SAX Parser shall invoke this method whenever it
     * starts parsing the XML document.
     * @throws SAXException 
     */
    @Override
    public void startDocument() throws SAXException 
    {
        super.startDocument();
        /**
         * Create a new textAccumulator for the text that could potentially
         * be encountered.
         */
        System.out.println("EventHandler: Document processing started: " + documentName);
        /**
         * Here a DocTree<T> instance will be created, this DocTree will
         * represent the data model that will holds the contents (the elements)
         * contained within the XML document being processed.
         */
        docTree = new DocTree<String>();
    }
    /**
     * This method shall be invoked by the SAX parser when the end
     * of the document being currently processing is reached.
     * @throws SAXException 
     */
    @Override
    public void endDocument() throws SAXException 
    {
        super.endDocument();
        System.out.println("EventHandler: Document processing completed: " + documentName);
        /**
         * Attach the root element (and all the child elements, inherently) into
         * the DocTree.
         */
        docTree.setRootElement(rootElement);
        /**
         * Pass the parsed DocTree to the output channel to
         * be printed accordingly.
         */
        outputChannel.display(docTree);
    }
    /**
     * 
     * @param uri
     * @param localName
     * @param qname
     * @throws SAXException 
     */
    @Override
    public void endElement(String uri, String localName, String qname) throws SAXException 
    {
        super.endElement(uri, localName, qname);
        /**
         * Remove the ended element from the element stack.
         */
        elementStack.removeLastElement();
    }
    /**
     * 
     * @param uri
     * @param localName
     * @param qname
     * @param attributes
     * @throws SAXException 
     */
    @Override
    public void startElement(String uri, String localName, String qname, Attributes attributes) throws SAXException 
    {
        super.startElement(uri, localName, qname, attributes);        
        /**
         * Check if the rootElement has been parsed before or not.
         */
        if(!isRootElementParsed)
        {
            isRootElementParsed = true;
            /**
             * Create a new DocNode that represent the parsed element.
             */
            rootElement = new DocNode<String>(qname);
            /**
             * Add all the parsed attributes to the created DocNode.
             */
            for(int i = 0 ; i < attributes.getLength() ; i++)
            {
                Attribute<String> strAttribute = new Attribute<String>(attributes.getQName(i), attributes.getValue(i));
                rootElement.addAttribute(strAttribute);
            }
            /**
             * Push the root element into the element stack
             */
            elementStack.push(rootElement);
            return;
        }
        /**
         * Create a new DocNode that represent the parsed element.
         */
        DocNode<String> parsedDocNode = new DocNode<String>(qname);
        /**
         * Add all the parsed attributes to the created DocNode.
         */
        for(int i = 0 ; i < attributes.getLength() ; i++)
        {
            Attribute<String> strAttribute = new Attribute<String>(attributes.getQName(i), attributes.getValue(i));
            parsedDocNode.addAttribute(strAttribute);
        }
        try 
        {
            /**
             * Get the last element from the elementStack and attach this
             * docNode as a child of that element.
             */
            elementStack.getLastIn().addChildDocNode(parsedDocNode);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(EventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void characters(char[] charactersArray, int startIndex, int length) throws SAXException 
    {
        super.characters(charactersArray, startIndex, length);
        /**
         * Append the content of the encountered text into the accumulator
         */
        textAccumulator.append(charactersArray, startIndex, length);
        /**
         * Get the last element from the element stack and set this text
         * as its text content.
         */
        elementStack.getLastIn().setTextContent(textAccumulator.toString());
    }            
}
