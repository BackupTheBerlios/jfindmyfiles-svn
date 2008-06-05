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
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import javax.swing.JOptionPane;
import org.openide.modules.ModuleInstall;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

    @Override
    public boolean closing() {
        return JOptionPane.OK_OPTION ==
                JOptionPane.showConfirmDialog(WindowManager.getDefault().getMainWindow(), 
                NbBundle.getMessage(CatalogOptionsCategory.class, "Installer.ClosingMessage"), 
                NbBundle.getMessage(CatalogOptionsCategory.class, "Installer.ClosingTitle"),
                JOptionPane.YES_NO_CANCEL_OPTION);
    }
}
