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

import de.berlios.jfindmyfiles.jfindmyfilesgui.dialogs.NewCatalogDlg;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.WindowManager;

public final class ActionNewCatalog extends CallableSystemAction {

    public void performAction() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCatalogDlg(WindowManager.getDefault().getMainWindow(), true).showCentered();
            }
        });
    }

    public String getName() {
        return NbBundle.getMessage(ActionNewCatalog.class, "CTL_ActionNewCatalog");
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/menu-new-folder.png";
    }    

    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
