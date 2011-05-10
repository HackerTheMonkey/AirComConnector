package com.aircom.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
/**
 * 
 * @author Hasanein Khafaji
 * @param <T> 
 */
public final class DocNode<T>
{
    /**
     * This is the name of the DocNode, this works as an identification
     * of the DocNode.
     */ 
    private String nodeName;
    /**
     * The text content of the DocNode, if it will have any textual content
     * at all contained within it. The data type of the text content is a 
     * generic type that is defined at the class level.
     */
    private T textContent = null;
    /**
     * Here we define a boolean value that express if this particular
     * node can be thought of as a root element for the DocTree that contains
     * it. This will default to false.
     */
    private boolean isRootElement;
    /**
     * Any DocNode might contain a list of child DocNodes contained
     * within it, here we define the List of child DocNodes. The initial
     * value is null, which signifies that there are no elements contained
     * within this root element.
     */
    private List<DocNode<T>> childNodes = new ArrayList<DocNode<T>>();
    /**
     * Here we define a List of attributes that might be contained
     * within this particular DocNode. The generic data type of the
     * attribute can be anything, this is not to be tied by any means to the
     * generic data type of the text content of the DocNode. The use of the "?"
     * wildcard simplifies the parameterized argument passing to this generic
     * attribute.
     */
    private HashMap<String, Attribute<T>> attributesHashMap = new HashMap<String, Attribute<T>>();

    
    public DocNode(String elementName)
    {
        this.nodeName = elementName;
    }
    /**
     * This method returns the text content contained within this
     * DocNode, or returns null if this DocNode doesn't have any text content
     * included within it.
     */
    public T getTextContent()
    {
        return textContent;
    }

    public String getElementName()
    {
        return nodeName;
    }

    
    
    /**
     * This method sets the text content of this node to the
     * value of the textContent argument passed into this method.
     */
    public void setTextContent(T textContent)
    {
        this.textContent = textContent;
    }

    /**
     * Sets this DocNode root element status as either
     * being a root element of the enclosing DocTree or
     * a second-level DocNode.
     */
    public void setIsRootElement(boolean isRootElement)
    {
        this.isRootElement = isRootElement;
    }

    /**
     * returns true if this DocNode is a root element of the
     * containing DocTree, false otherwise.
     */
    public boolean isRootElement()
    {
        return isRootElement;
    }

    /**
     * This method returns a defensive copy to the mutable list of child nodes
     * to the caller. If there are no child nodes, then this method will return
     * an empty ArrayList.
     */
    public List<DocNode<T>> getChildNodes()
    {
        /**
         * If there are no child nodes, then return an
         * empty ArrayList instead.
         */
        if (childNodes == null)
        {
            return new ArrayList<DocNode<T>>();
        }
        /**
         * Otherwise returns a defensive copy of the
         * childNodes to prevent the client from altering the internal
         * childNodes.
         */
        return new ArrayList<DocNode<T>>(childNodes);        
    }

    /**
     * This method sets the child nodes of the DocNode into the
     * passed childNodes argument. Rather than setting the passed in
     * reference to the childNodes, we are setting a defensive copy to
     * the passed argument to prevent any malicious change to our internal
     * data structure.
     */
    public void setChildNodes(List<DocNode<T>> childNodes)
    {
        if(childNodes == null)
            return;
        /**
         * Make the defensive copy.
         */
        ArrayList<DocNode<T>> defensiveCopy = new ArrayList<DocNode<T>>(childNodes);
        /**
         * Assign the defensive copy into the internal private childNode
         * data structure.
         */
        this.childNodes = defensiveCopy;
    }

    /**
     * This method will simply returns the number of children
     * contained in this particular DocNode
     */
    public int getNumberOfChilds()
    {
        if (childNodes == null)
        {
            return 0;
        }
        return childNodes.size();
    }

    /**
     * This method will simply clear all the nodes contained within
     * this particular DocNode.
     */
    public void clearAllChildNodes()
    {
        if (childNodes == null)
        {
            return;
        }
        childNodes.removeAll(childNodes);
    }

    /**
     * This method adds the passed DocNode argument into the list of DocNodes
     * contained within this particular DocNode.
     */
    public void addChildDocNode(DocNode<T> docNode) throws Exception
    {        
        /**
         * Avoid un-necessary processing if docNode is null.
         */
        if(docNode == null)
            return;        
        /**
         * If there are no child nodes that are currently present, then
         * we need to create an ArrayList and add the DocNode argument as
         * the first child in this ArrayList.
         */
        if (childNodes == null)
        {
            childNodes = new ArrayList<DocNode<T>>();
        }
        childNodes.add(docNode.clone());
    }

    /**
     * This method will remove the passed in DocNode from the childNodes
     * ArrayList, if there are no child nodes currently present, then this
     * method will return silently.
     */
    public void removeChildDocNode(DocNode<T> docNode)
    {
        if (childNodes == null)
        {
            return;
        }
        childNodes.remove(docNode);
    }

    /**
     * This method will return the number of attributes that are contained
     * within this DocNode
     */
    public int getNumberOfAttributes()
    {
        if (attributesHashMap == null)
        {
            return 0;
        }
        return attributesHashMap.size();
    }

    /**
     * This method will return all of the attributes that are contained within
     * this particular DocNode in an ArrayList.
     */
    public List<Attribute<T>> getAllAttributes()
    {
        if (attributesHashMap == null)
        {
            return new ArrayList<Attribute<T>>();
        }
        /**
         * Here we need to convert the attributeList HashMap into
         * an ArrayList.
         */
        return new ArrayList<Attribute<T>>(attributesHashMap.values());
    }

    /**
     * This method is to set the attributesList to the value of the
     * ArrayList attribute passed into this method.
     */
    public void setAttributeList(Set<Attribute<T>> attributesSet)
    {
        if (attributesHashMap == null)
        {
            attributesHashMap = new HashMap<String, Attribute<T>>();
        }
        for (Attribute<T> attribute : attributesSet)
        {
            attributesHashMap.put(attribute.getName(), attribute);
        }
    }

    /**
     * This method clears all the attributes stored inside this
     * DocNode.
     */
    public void clearAllAttributes()
    {
        if (attributesHashMap == null)
        {
            return;
        }
        attributesHashMap.clear();
    }

    /**
     * This method adds a single attribute to the list of attributes
     * contained within this DocNode.
     */
    public void addAttribute(Attribute<T> attribute)
    {
        if (attributesHashMap == null)
        {
            attributesHashMap = new HashMap<String, Attribute<T>>();
        }
        attributesHashMap.put(attribute.getName(), attribute);
    }
    
    @Override
    protected DocNode<T> clone()
    {
        DocNode<T> clonnedDocNode = new DocNode<T>(this.nodeName);
        clonnedDocNode.attributesHashMap = new HashMap<String, Attribute<T>>(attributesHashMap);
        clonnedDocNode.childNodes = new ArrayList<DocNode<T>>(childNodes);
        clonnedDocNode.isRootElement = isRootElement; // No need for a defensive copy, it is a primitive and primitves passed by value.        
        clonnedDocNode.textContent = textContent; // No need for a defensive copy, it is already an immutable class.
        return clonnedDocNode;
    }

    @Override
    public String toString()
    {
        return nodeName;
    }
    
    
}