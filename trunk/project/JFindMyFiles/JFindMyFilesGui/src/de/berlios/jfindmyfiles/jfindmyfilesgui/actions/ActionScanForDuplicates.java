/**
 *  Copyright (C) 2008  Patrícia Monteiro e Sérgio Lopes
 *
 *  This file is part of JFindMyFiles.
 *
 *  JFindMyFiles is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  JFindMyFiles is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with JFindMyFiles.  If not, see 
 * <http://www.gnu.org/licenses/gpl.html>.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui.actions;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.TopComponent;
import de.berlios.jfindmyfiles.jfindmyfilesgui.DuplicateResultsTopComponent;
import org.openide.util.Lookup;

public final class ActionScanForDuplicates extends CallableSystemAction {
    
    private CatalogEngine eng;
    
    public ActionScanForDuplicates() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }
    
    public void performAction() {
        TopComponent win = DuplicateResultsTopComponent.findInstance();
        win.open();
        win.requestActive();
        ((DuplicateResultsTopComponent)win).startSearching();
    }

    public String getName() {
        return NbBundle.getMessage(ActionScanForDuplicates.class, "CTL_ActionScanForDuplicates");
    }
    
    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-search.png";
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
