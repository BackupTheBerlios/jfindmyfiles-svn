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
 * Action which shows Loan component.
 */
public class LoanAction extends AbstractAction {

    public LoanAction() {
        super(NbBundle.getMessage(LoanAction.class, "CTL_LoanAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(LoanTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = LoanTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
