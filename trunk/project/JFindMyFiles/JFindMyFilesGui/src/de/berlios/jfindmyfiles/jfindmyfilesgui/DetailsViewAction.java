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
 * Action which shows DetailsView component.
 */
public class DetailsViewAction extends AbstractAction {

    public DetailsViewAction() {
        super(NbBundle.getMessage(DetailsViewAction.class, "CTL_DetailsViewAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(DetailsViewTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = DetailsViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
