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

import de.berlios.jfindmyfiles.catalog.CatalogEngineEvent;
import de.berlios.jfindmyfiles.catalog.CatalogEngineListener;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcItemProperties;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcNewDisk;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcNewGroup;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcRemove;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcRenumberDisks;
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
public class DiskGroupNode extends AbstractNode implements CatalogEngineListener {

    private SystemAction[] sysact;
    private DiskGroup group;

    public DiskGroupNode(DiskGroup group, boolean leaf) {
        super((leaf ? Children.LEAF : new DiskGroupChildren(group.getId())), Lookups.singleton(group));
        setName(group.getName());
        this.group = group;
        Lookup lo = Lookups.forPath("/Actions");//TODO: sort actions correctly and add separators
        sysact = new SystemAction[]{lo.lookup(AcRemove.class), lo.lookup(AcItemProperties.class),
                    lo.lookup(AcRenumberDisks.class), lo.lookup(AcNewDisk.class), lo.lookup(AcNewGroup.class)
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

    public void catalogCreated(CatalogEngineEvent evt) {
        //ignore
    }

    public void catalogOpened(CatalogEngineEvent evt) {
        //ignore
    }

    public void catalogClosed(CatalogEngineEvent evt) {
        //ignore
    }

    public void diskGroupAdded(CatalogEngineEvent evt) {
        //ignore
    }

    public void diskGroupRemoved(CatalogEngineEvent evt) {
        //ignore
    }

    @SuppressWarnings("unchecked")
    public void diskGroupRenamed(CatalogEngineEvent evt) {
        if(evt.getNewDiskGroup().getId().equals(group.getId())) {
            group = evt.getNewDiskGroup();
            setName(group.getName());
        }
    }

    public void diskAdded(CatalogEngineEvent evt) {
        //ignore
    }

    public void diskRemoved(CatalogEngineEvent evt) {
        //ignore
    }
}
