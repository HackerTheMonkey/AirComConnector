
package com.aircom.test;

/**
 *
 * @author Hasanein Khafaji
 */
public class Attribute<T>
{

    private T value;
    private String name;

    public Attribute(String name, T value)
    {
        this.name = name;
        this.value = value;
    }

    public T getValue()
    {
        return value;
    }

    public void reSetValue(T value)
    {
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
