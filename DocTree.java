/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aircom.test;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author hasaneinali
 */
public class DocTree<T> extends AbstractCollection<T>
{

    /**
     * This is the root element that the DocTree is enclosing. It is
     * what defines the docTree.
     */
    private DocNode<T> rootElement;
    /**
     * This method sets the root element of this DocTree to the argument
     * of the rootElement passed to this method.
     */
    public void setRootElement(DocNode<T> rootElement)
    {
        if (rootElement == null)
        {
            throw new IllegalArgumentException("a DocTree<T> root element need to be provided to create an instance of this DocTree");
        }
        DocNode<T> rootElementClone = rootElement.clone();
        if (!rootElementClone.isRootElement())
        {
            throw new IllegalArgumentException("The provided DocNode is not a valid root element");
        }
        this.rootElement = rootElementClone;
    }
    
    /**
     * This method returns a defensive copy of the root element of
     * this particular DocTree.
     */
    public DocNode<T> getRootElement()
    {
        return rootElement.clone();
    }

    /**
     * This method will return a List view of all of the elements contained
     * within this DocTree, either root or non-root elements.
     */
    public List<DocNode<T>> flattenTree()
    {
        return traverseTree(rootElement);
    }

    private List<DocNode<T>> traverseTree(DocNode<T> rootElement)
    {
        /**
         * Create an empty ArrayList
         */
        ArrayList<DocNode<T>> flattenedList = new ArrayList<DocNode<T>>();
        /**
         * Add the rootElement argument to the arrayList
         */
        flattenedList.add(rootElement);
        /**
         * Base rule of the recursive function.
         */
        if (rootElement.getNumberOfChilds() == 0)
        {
            return flattenedList;
        }
        /**
         * Otherwise, we need to dig in recursively and call
         * the traverseTree method on each single child element
         * contained within the rootElement.
         */
        for (DocNode<T> childDocNode : rootElement.getChildNodes())
        {
            flattenedList.addAll(traverseTree(childDocNode));
        }
        return flattenedList;
    }

    @Override
    public Iterator<T> iterator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (DocNode<T> docNode : flattenTree())
        {
            stringBuilder.append(docNode.getElementName() + ", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2); // Deletes the last comma.
        return stringBuilder.toString();
    }
}
