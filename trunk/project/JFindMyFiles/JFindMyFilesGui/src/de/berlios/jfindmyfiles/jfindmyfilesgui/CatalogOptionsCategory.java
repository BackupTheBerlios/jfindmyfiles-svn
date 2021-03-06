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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.spi.options.OptionsCategory;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

public final class CatalogOptionsCategory extends OptionsCategory {

    @Override
    public Icon getIcon() {
        return new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/general/options-storage-config.png"));
    }

    public String getCategoryName() {
        return NbBundle.getMessage(CatalogOptionsCategory.class, "CatalogOptionsCategory_Name");
    }

    public String getTitle() {
        return NbBundle.getMessage(CatalogOptionsCategory.class, "CatalogOptionsCategory_Title");
    }

    public OptionsPanelController create() {
        return new CatalogOptionsPanelController();
    }
}
