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

import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionAddNewDisk;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionAddNewDiskGroup;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionProperties;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionRemoveFromCatalog;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.ActionRenumberDisks;
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
public class DiskGroupNode extends AbstractNode {

    private SystemAction[] sysact;

    public DiskGroupNode(DiskGroup group, boolean leaf) {
        super((leaf ? Children.LEAF : new DiskGroupChildren(group.getId())), Lookups.singleton(group));
        setName(group.getName());

        Lookup lo = Lookups.forPath("/Actions");//TODO: sort actions correctly and add separators
        sysact = new SystemAction[]{lo.lookup(ActionRemoveFromCatalog.class), lo.lookup(ActionProperties.class),
                    lo.lookup(ActionRenumberDisks.class), lo.lookup(ActionAddNewDisk.class), lo.lookup(ActionAddNewDiskGroup.class)
                };
    }

    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/disk-group.png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public SystemAction[] getActions(boolean bool) {
        return new SystemAction[]{};
    }
}
