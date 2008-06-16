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
 * Action which shows SearchWindow component.
 */
public class SearchWindowAction extends AbstractAction {

    public SearchWindowAction() {
        super(NbBundle.getMessage(SearchWindowAction.class, "CTL_SearchWindowAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(SearchWindowTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = SearchWindowTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
