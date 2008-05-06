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

public final class JFindMyFilesGuiNetWOptsCategory extends OptionsCategory {

    @Override
    public Icon getIcon() {
        return new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x32/options-server-config.png"));
    }

    public String getCategoryName() {
        return NbBundle.getMessage(JFindMyFilesGuiNetWOptsCategory.class, "OptionsCategory_Name_Jfindmyfilesgui");
    }

    public String getTitle() {
        return NbBundle.getMessage(JFindMyFilesGuiNetWOptsCategory.class, "OptionsCategory_Title_Jfindmyfilesgui");
    }

    public OptionsPanelController create() {
        return new JFindMyFilesGuiNetWOptsPanelController();
    }
}
