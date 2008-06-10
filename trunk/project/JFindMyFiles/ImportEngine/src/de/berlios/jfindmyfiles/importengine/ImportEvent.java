/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.importengine;

import java.util.EventObject;

/**
 *
 * @author Knitter
 */
public class ImportEvent extends EventObject {
    
    public ImportEvent(ImportEngine source) {
        super(source);
    }

}
