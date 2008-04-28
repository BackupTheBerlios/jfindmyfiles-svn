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

public final class JfindmyfilesguiOptionsCategory extends OptionsCategory {

    public Icon getIcon() {
        return new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x32/options-server-config.png"));
    }

    public String getCategoryName() {
        return NbBundle.getMessage(JfindmyfilesguiOptionsCategory.class, "OptionsCategory_Name_Jfindmyfilesgui");
    }

    public String getTitle() {
        return NbBundle.getMessage(JfindmyfilesguiOptionsCategory.class, "OptionsCategory_Title_Jfindmyfilesgui");
    }

    public OptionsPanelController create() {
        return new JfindmyfilesguiOptionsPanelController();
    }
}
