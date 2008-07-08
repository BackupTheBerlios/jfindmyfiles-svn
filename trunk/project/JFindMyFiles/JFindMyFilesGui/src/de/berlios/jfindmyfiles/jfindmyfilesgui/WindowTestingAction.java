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
 * Action which shows WindowTesting component.
 */
public class WindowTestingAction extends AbstractAction {

    public WindowTestingAction() {
        super(NbBundle.getMessage(WindowTestingAction.class, "CTL_WindowTestingAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(WindowTestingTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = WindowTestingTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
    
    public boolean isEnabled() {
        return false;
    }
}
