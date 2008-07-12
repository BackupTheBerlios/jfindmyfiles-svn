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
 * Action which shows SingleView component.
 */
public class SingleViewAction extends AbstractAction {

    public SingleViewAction() {
        super(NbBundle.getMessage(SingleViewAction.class, "CTL_SingleViewAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(SingleViewTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = SingleViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
