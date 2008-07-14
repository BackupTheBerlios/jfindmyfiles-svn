/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.spi.options.OptionsCategory;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

public final class PluginsOptionsCategory extends OptionsCategory {

    @Override
    public Icon getIcon() {
        return new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/general/options-plugins.png"));
    }

    public String getCategoryName() {
        return NbBundle.getMessage(PluginsOptionsCategory.class, "PluginsOptionsCategory_Name");
    }

    public String getTitle() {
        return NbBundle.getMessage(PluginsOptionsCategory.class, "PluginsOptionsCategory_Title");
    }

    public OptionsPanelController create() {
        return new PluginsPanelController();
    }
}
