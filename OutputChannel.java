/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aircom.test;

/**
 *
 * @author hasaneinali
 */
public interface OutputChannel
{
    public <T>void display(DocTree<T> docTree);
}
