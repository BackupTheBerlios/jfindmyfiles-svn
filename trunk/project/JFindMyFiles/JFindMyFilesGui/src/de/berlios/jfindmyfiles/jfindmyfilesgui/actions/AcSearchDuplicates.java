/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.actions;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.jfindmyfilesgui.DuplicateResultsTopComponent;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.TopComponent;

public final class AcSearchDuplicates extends CallableSystemAction {

    private CatalogEngine eng;

    public AcSearchDuplicates() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }

    public void performAction() {
        TopComponent win = DuplicateResultsTopComponent.findInstance();
        win.open();
        win.requestActive();
        ((DuplicateResultsTopComponent) win).startSearching();
    }

    public String getName() {
        return NbBundle.getMessage(AcSearchDuplicates.class, "CTL_AcSearchDuplicates");
    }

    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/search-duplicates.png";
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
        //return eng.isOpened();
        return true;
    }
}
