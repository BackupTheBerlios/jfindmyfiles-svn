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
package de.berlios.jfindmyfiles.jfindmyfilesgui.nodes;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionAddNewDisk;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionAddNewDiskGroup;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionCatalogProperties;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionClose;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionExport;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionImport;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionRenumberDisks;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionScanForDuplicates;
import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author knitter
 */
public class CatalogNode extends AbstractNode {

    private SystemAction[] sysact;

    public CatalogNode(String name, boolean created) {
        //super(Children.create(new CatalogChildNodesFactory(), true));
        //TODO: populate child nodes
        super(created ? Children.LEAF : new CatalogChildren(null));

        CatalogEngine eng = Lookup.getDefault().lookup(CatalogEngine.class);
        setName(eng.getProperties().getName());

        Lookup lo = Lookups.forPath("/Actions");//TODO: sort actions correctly and add separators
        sysact = new SystemAction[]{lo.lookup(ActionCatalogProperties.class),
                    lo.lookup(ActionRenumberDisks.class), lo.lookup(ActionScanForDuplicates.class),
                    lo.lookup(ActionAddNewDisk.class), lo.lookup(ActionAddNewDiskGroup.class), lo.lookup(ActionClose.class),
                    lo.lookup(ActionExport.class), lo.lookup(ActionImport.class)
                };


    //Collections.sort(sysact);
    //Arrays.sort(sysact);
    }

    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-catalog.png"); // NOI18N
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public SystemAction[] getActions(boolean bool) {
        return sysact;
    }
}
