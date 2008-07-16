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

public final class AcLaunchFile extends CallableSystemAction {

    private static final Logger logger = Logger.getLogger(AcLaunchFile.class.getName());
    private CatalogEngine eng;

    public AcLaunchFile() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }

    public void performAction() {
        if (Desktop.isDesktopSupported()) {
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
        return NbBundle.getMessage(AcLaunchFile.class, "CTL_AcLaunchFile");
    }

    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/run.png";
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
       // return eng.isOpened();
        return true;
    }
}
