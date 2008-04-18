/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Action which shows Feed component.
 */
public class FeedAction extends AbstractAction {

    public FeedAction() {
        super(NbBundle.getMessage(FeedAction.class, "CTL_FeedAction"));
        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(FeedTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = FeedTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
