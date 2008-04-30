/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.actions;

import de.berlios.jfindmyfiles.jfindmyfilesgui.panels.NewDiskPanel;
import java.awt.Dialog;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class ActionAddNewDisk extends CallableSystemAction {

    public void performAction() {
        Dialog dlg = DialogDisplayer.getDefault().createDialog(
                new DialogDescriptor(new NewDiskPanel(), "Add new disk"));

        dlg.setResizable(false);
        dlg.setVisible(true);
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
