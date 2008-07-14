/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.actions;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class ActionSearch extends CallableSystemAction {

    private CatalogEngine eng;

    public ActionSearch() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        putValue("iconBase", "");
    }

    public void performAction() {
        //tODO: implement body
    }

    public String getName() {
        return NbBundle.getMessage(ActionSearch.class, "CTL_ActionSearch");
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/menu-find.png";
    }
    
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return eng.isOpened();
    }
}
