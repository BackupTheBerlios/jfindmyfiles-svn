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
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;

public final class ActionRemoveFromCatalog extends CallableSystemAction {

    private CatalogEngine eng;
    
    public ActionRemoveFromCatalog() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }
    public void performAction() {
        // TODO implement action body
    }

    public String getName() {
        return NbBundle.getMessage(ActionRemoveFromCatalog.class, "CTL_ActionRemoveFromCatalog");
    }

    @Override
    protected void initialize() {
        super.initialize();
        putValue("iconBase", "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/empty.png");
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
