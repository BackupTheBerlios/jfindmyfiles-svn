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
 * Action which shows FileProperties component.
 */
public class FilePropertiesAction extends AbstractAction {

    public FilePropertiesAction() {
        super(NbBundle.getMessage(FilePropertiesAction.class, "CTL_FilePropertiesAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(FilePropertiesTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = FilePropertiesTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
