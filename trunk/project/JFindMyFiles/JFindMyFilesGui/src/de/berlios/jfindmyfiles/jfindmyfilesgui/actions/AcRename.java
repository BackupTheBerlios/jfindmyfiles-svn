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

public final class AcRename extends CallableSystemAction {

    private CatalogEngine eng;

    public AcRename() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }

    public void performAction() {
        //TODO: rename action
        /* String name = JOptionPane.showInputDialog(WindowManager.getDefault().getMainWindow(), "New name:");//TODO: i18n
        if (name != null && !name.isEmpty()) {
        Session s = Lookup.getDefault().lookup(CatalogEngine.class).sessionFactory.getCurrentSession();
        s.beginTransaction();
        DiskGroup g = Utilities.actionsGlobalContext().lookup(DiskGroup.class);
        g.setName(name);
        s.merge(g);
        s.getTransaction().commit();
        }*/
    }

    public String getName() {
        return NbBundle.getMessage(AcRename.class, "CTL_AcRename");
    }

    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/rename.png";
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
