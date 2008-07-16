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
import de.berlios.jfindmyfiles.jfindmyfilesgui.dialogs.NewDiskDlg;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.WindowManager;

public final class AcNewDisk extends CallableSystemAction {

    private CatalogEngine eng;

    public AcNewDisk() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }

    /**
     * Action to be performed by this action class.
     * An ActionAddNewDisk will open a JDialog allowing the user to scan a new 
     * disk or folder and add the information gathered to the database.
     */
    public void performAction() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewDiskDlg(WindowManager.getDefault().getMainWindow(),
                        true).showCentered();
            }
        });
    }

    public String getName() {
        return NbBundle.getMessage(AcNewDisk.class, "CTL_AcNewDisk");
    }

    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/new-disk.png";
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
