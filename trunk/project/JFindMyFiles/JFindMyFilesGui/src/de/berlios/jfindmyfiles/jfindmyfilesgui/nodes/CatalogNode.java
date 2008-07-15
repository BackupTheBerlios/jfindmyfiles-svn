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
import de.berlios.jfindmyfiles.catalog.CatalogEngineEvent;
import de.berlios.jfindmyfiles.catalog.CatalogEngineListener;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcCatalogProperties;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcClose;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcExport;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcImport;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcNewDisk;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcNewGroup;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcRenumberDisks;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcSearchDuplicates;
import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
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
        super(created ? Children.LEAF : new CatalogChildren(null), Lookups.singleton(Lookup.getDefault().lookup(CatalogEngine.class)));

        CatalogEngine eng = Lookup.getDefault().lookup(CatalogEngine.class);
        setName(eng.getProperties().getName());

        Lookup lo = Lookups.forPath("/Actions");//TODO: sort actions correctly and add separators
        sysact = new SystemAction[]{lo.lookup(AcCatalogProperties.class),
                    lo.lookup(AcRenumberDisks.class), lo.lookup(AcSearchDuplicates.class),
                    lo.lookup(AcNewDisk.class), lo.lookup(AcNewGroup.class), lo.lookup(AcClose.class),
                    lo.lookup(AcExport.class), lo.lookup(AcImport.class)
                };
    }

    @Override
    protected Sheet createSheet() {
        Sheet s = Sheet.createDefault();
        /*try {
        
        Sheet.Set sSet = Sheet.createPropertiesSet();
        //Property p = new PropertySupport.Reflection(media, String.class, "name");
        Property p = new PropertySupport.Reflection(media, String.class, "name");
        //DiskNameProperty p = new DiskNameProperty(media.getName(), "", media.getName());
        sSet.put(p);
        s.put(sSet);
        } catch (NoSuchMethodException ex) {
        Exceptions.printStackTrace(ex);
        }*/
        return s;
    }

    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/nodes/catalog.png");
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
