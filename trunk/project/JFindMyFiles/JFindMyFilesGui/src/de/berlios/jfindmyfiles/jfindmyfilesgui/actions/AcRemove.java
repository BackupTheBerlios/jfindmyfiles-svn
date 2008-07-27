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
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import de.berlios.jfindmyfiles.jfindmyfilesgui.nodes.DiskGroupNode;
import de.berlios.jfindmyfiles.jfindmyfilesgui.nodes.DiskNode;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.TopComponent;

public final class AcRemove extends CallableSystemAction {

    private CatalogEngine eng;

    public AcRemove() {
        super();
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
    }

    public void performAction() {
        if (eng.isOpened()) {
            DiskGroup g;
            Media m;
            if ((g = Utilities.actionsGlobalContext().lookup(DiskGroup.class)) != null) {
                eng.removeDiskGroup(g);
            } else if ((m = Utilities.actionsGlobalContext().lookup(Media.class)) != null) {
                eng.removeDisk(m);
            }
        }

        /*TopComponent.Registry registry = TopComponent.getRegistry();
        Node[] act = registry.getActivatedNodes();
        if (act.length > 0) {
            if (act[0] instanceof DiskGroupNode) {
                System.err.println("group node selected");
            } else if (act[0] instanceof DiskNode) {
                System.err.println("media node selected");
            }
        }*/
    }

    public String getName() {
        return NbBundle.getMessage(AcRemove.class, "CTL_AcRemove");
    }

    @Override
    protected String iconResource() {
        return "de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/actions/remove.png";
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
