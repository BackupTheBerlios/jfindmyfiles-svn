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
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import javax.swing.JOptionPane;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.WindowManager;

public final class ActionAddNewDiskGroup extends CallableSystemAction {

    private CatalogEngine eng;

    public ActionAddNewDiskGroup() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }

    public void performAction() {
        String name = JOptionPane.showInputDialog(WindowManager.getDefault().getMainWindow(),
                NbBundle.getMessage(ActionAddNewDiskGroup.class, "AddNewDiskGroupInputMessage"));
        if (name != null && !name.isEmpty()) {
            eng.addDiskGroup(name, "", Utilities.actionsGlobalContext().lookup(DiskGroup.class));
        }
    }

    public String getName() {
        return NbBundle.getMessage(ActionAddNewDiskGroup.class, "CTL_ActionAddNewDiskGroup");
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        putValue("iconBase", "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/disk-group.png");
    }

    /*@Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-disk-group.png";
    }*/

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
