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
 * Action which shows FileList component.
 */
public class FileListAction extends AbstractAction {

    public FileListAction() {
        super(NbBundle.getMessage(FileListAction.class, "CTL_FileListAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(FileListTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = FileListTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
