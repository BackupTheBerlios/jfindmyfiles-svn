/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.actions;

import de.berlios.jfindmyfiles.jfindmyfilesgui.NewCatalogPanel;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class ActionNewCatalog extends CallableSystemAction {

    public void performAction() {
        //TODO: correctly implement action body
        DialogDescriptor diagdisc = new DialogDescriptor(new NewCatalogPanel(), "");
        diagdisc.setModal(true);
        DialogDisplayer.getDefault().notify(diagdisc);
    }

    public String getName() {
        return NbBundle.getMessage(ActionNewCatalog.class, "CTL_ActionNewCatalog");
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
