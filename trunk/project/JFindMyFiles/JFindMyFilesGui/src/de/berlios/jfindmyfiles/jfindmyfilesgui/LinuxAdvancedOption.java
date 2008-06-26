/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class LinuxAdvancedOption extends AdvancedOption {

    public String getDisplayName() {
        return NbBundle.getMessage(LinuxAdvancedOption.class, "AdvancedOption_DisplayName_Linux");
    }

    public String getTooltip() {
        return NbBundle.getMessage(LinuxAdvancedOption.class, "AdvancedOption_Tooltip_Linux");
    }

    public OptionsPanelController create() {
        return new LinuxOptionsPanelController();
    }
}
