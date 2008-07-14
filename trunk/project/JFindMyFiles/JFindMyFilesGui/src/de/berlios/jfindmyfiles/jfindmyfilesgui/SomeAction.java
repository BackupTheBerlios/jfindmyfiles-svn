/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class SomeAction extends CallableSystemAction {

    public void performAction() {
        // TODO implement action body
    }

    public String getName() {
        return NbBundle.getMessage(SomeAction.class, "CTL_SomeAction");
    }

    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/empty.png";
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
