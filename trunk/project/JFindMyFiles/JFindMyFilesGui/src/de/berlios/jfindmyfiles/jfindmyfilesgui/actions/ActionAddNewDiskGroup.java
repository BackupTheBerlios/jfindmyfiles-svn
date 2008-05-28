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
import javax.swing.JOptionPane;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.util.lookup.Lookups;
import org.openide.windows.WindowManager;

public final class ActionAddNewDiskGroup extends CallableSystemAction {

    public void performAction() {
        //TODO: get input dialog again!
        NotifyDescriptor.InputLine nd = new NotifyDescriptor.InputLine("Disk Group Name:", "New Disk Group", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE);//TODO: i18n

        Object result = DialogDisplayer.getDefault().notify(nd);

        if (result.equals(NotifyDescriptor.OK_OPTION)) {
            String name = nd.getInputText();



            if (name != null && !name.isEmpty()) {
                Lookup lu = Lookups.forPath("/CatalogEngine");
                CatalogEngine eng = lu.lookup(CatalogEngine.class);
                if (eng != null) {
                    eng.addDiskGroup(name, "", null);//TODO: get selected node
                }
            }
        }
    }

    public String getName() {
        return NbBundle.getMessage(ActionAddNewDiskGroup.class, "CTL_ActionAddNewDiskGroup");
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-disk-group.png";
    }

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
