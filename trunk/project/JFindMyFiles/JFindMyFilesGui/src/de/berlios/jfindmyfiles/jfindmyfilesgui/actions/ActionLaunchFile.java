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
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.WindowManager;

public final class ActionLaunchFile extends CallableSystemAction {
    
    private static final Logger logger = Logger.getLogger(ActionLaunchFile.class.getName());
    private CatalogEngine eng;
    
    public ActionLaunchFile() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }

    public void performAction() {
        if(Desktop.isDesktopSupported()) {
            Desktop dsk = Desktop.getDesktop();
            try {//TODO: get the file
                dsk.open(new File(""));
            } catch (IOException ex) {
                logger.log(Level.FINE, "Operation not supported or file not found", ex);//TODO: i18n
                JOptionPane.showMessageDialog(WindowManager.getDefault().getMainWindow(), "", "", JOptionPane.ERROR_MESSAGE);
            }
        } else {//TODO: i18n
            JOptionPane.showMessageDialog(WindowManager.getDefault().getMainWindow(), "", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String getName() {
        return NbBundle.getMessage(ActionLaunchFile.class, "CTL_ActionLaunchFile");
    }

    @Override
    protected void initialize() {
        super.initialize();
        // see org.openide.util.actions.SystemAction.iconResource() Javadoc for more details
        putValue("noIconInMenu", Boolean.TRUE);
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
