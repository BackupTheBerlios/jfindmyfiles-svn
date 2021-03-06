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
 * Action which shows Statistics component.
 */
public class StatisticsAction extends AbstractAction {

    public StatisticsAction() {
        super(NbBundle.getMessage(StatisticsAction.class, "CTL_StatisticsAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(StatisticsTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = StatisticsTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
