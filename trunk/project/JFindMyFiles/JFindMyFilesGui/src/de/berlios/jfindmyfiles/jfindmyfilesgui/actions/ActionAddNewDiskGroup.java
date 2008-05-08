/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.actions;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class ActionAddNewDiskGroup extends CallableSystemAction {

    public void performAction() {
        CatalogEngine engine = new CatalogEngine();
        engine.runtest(true);
        
        for(Object u : engine.getUsers()) {
            System.out.println(">>>>>>>>>>>>>>>>>>" + u);
        }
    }

    public String getName() {
        return NbBundle.getMessage(ActionAddNewDiskGroup.class, "CTL_ActionAddNewDiskGroup");
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
