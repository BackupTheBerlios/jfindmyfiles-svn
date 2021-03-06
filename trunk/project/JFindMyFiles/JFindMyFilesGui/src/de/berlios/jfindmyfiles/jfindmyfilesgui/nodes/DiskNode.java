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

import de.berlios.jfindmyfiles.catalog.entities.Media;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcItemProperties;
import de.berlios.jfindmyfiles.jfindmyfilesgui.actions.AcRemove;
import de.berlios.jfindmyfiles.jfindmyfilesgui.utils.GuiUtils;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.nodes.Node.Property;
import org.openide.nodes.PropertySupport;
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
public class DiskNode extends AbstractNode {

    private Media media;
    private SystemAction[] sysact;

    public DiskNode(Media media) {
        super(new DiskChildren(media.getId(), true), Lookups.singleton(media));
        this.media = media;
        setName(media.getName());
        Lookup lo = Lookups.forPath("/Actions");//TODO: sort actions correctly and add separators
        sysact = new SystemAction[]{lo.lookup(AcRemove.class), lo.lookup(AcItemProperties.class)};
    }

    /*@Override
    protected Sheet createSheet() {
    Sheet s = Sheet.createDefault();
    //try {
    
    Sheet.Set sSet = Sheet.createPropertiesSet();
    //Property p = new PropertySupport.Reflection(media, String.class, "name");
    //Property p = new PropertySupport.Reflection(media, String.class, "name");
    DiskNameProperty p = new DiskNameProperty(media.getName(), "", media.getName());
    sSet.put(p);
    s.put(sSet);
    //} catch (NoSuchMethodException ex) {
    //  Exceptions.printStackTrace(ex);
    //}
    return s;
    }*/
    @Override
    public SystemAction[] getActions(boolean bool) {
        //return new SystemAction[]{};
        return sysact;
    }

    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage(GuiUtils.findIconForType(media.getType()));
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    public Object getValue() {
        return null;
    }
}
