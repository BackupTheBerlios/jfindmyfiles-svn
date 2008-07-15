/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.PropertySupport;

/**
 *
 * @author knitter
 */
public class DiskNameProperty extends PropertySupport {

    private String value;
    
    @SuppressWarnings("unchecked")
    public DiskNameProperty(String displayName, String shortDescription, String value) {
        super("name", String.class, displayName, shortDescription, true, false);
        this.value = value;
    }

    @Override
    public Object getValue() throws IllegalAccessException, InvocationTargetException {
        return value;
    }

    @Override
    public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
