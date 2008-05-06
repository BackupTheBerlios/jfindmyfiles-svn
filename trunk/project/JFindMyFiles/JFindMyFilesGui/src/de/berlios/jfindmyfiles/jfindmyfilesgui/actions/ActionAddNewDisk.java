/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.actions;

import de.berlios.jfindmyfiles.jfindmyfilesgui.dialogs.NewDiskDlg;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.WindowManager;

public final class ActionAddNewDisk extends CallableSystemAction {

    public void performAction() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewDiskDlg(WindowManager.getDefault().getMainWindow(), true).showCentered();
            }
        });
    }

    public String getName() {
        return NbBundle.getMessage(ActionAddNewDisk.class, "CTL_ActionAddNewDisk");
    }

    @Override
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() Javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
