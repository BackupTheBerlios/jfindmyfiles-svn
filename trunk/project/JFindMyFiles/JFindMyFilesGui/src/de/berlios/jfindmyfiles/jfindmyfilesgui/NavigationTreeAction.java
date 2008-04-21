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
 * Action which shows NavigationTree component.
 */
public class NavigationTreeAction extends AbstractAction {

    public NavigationTreeAction() {
        super(NbBundle.getMessage(NavigationTreeAction.class, "CTL_NavigationTreeAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(NavigationTreeTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = NavigationTreeTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
