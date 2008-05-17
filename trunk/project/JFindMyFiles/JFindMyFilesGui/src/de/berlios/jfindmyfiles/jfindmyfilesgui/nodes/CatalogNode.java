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

import java.awt.Image;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;

/**
 *
 * @author knitter
 */
public class CatalogNode extends AbstractNode {
    
    public CatalogNode() {
        super(Children.LEAF);
    }
    
    @Override
    public Image getIcon(int type) {
        return Utilities.loadImage("/de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/icon-catalog.png"); // NOI18N
    }
    
    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

}
