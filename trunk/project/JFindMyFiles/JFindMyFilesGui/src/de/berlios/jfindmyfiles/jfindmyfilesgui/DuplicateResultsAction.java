/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Action which shows DuplicateResults component.
 */
public class DuplicateResultsAction extends AbstractAction {
    
    public DuplicateResultsAction() {
        super(NbBundle.getMessage(DuplicateResultsAction.class, "CTL_DuplicateResultsAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(DuplicateResultsTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = DuplicateResultsTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
