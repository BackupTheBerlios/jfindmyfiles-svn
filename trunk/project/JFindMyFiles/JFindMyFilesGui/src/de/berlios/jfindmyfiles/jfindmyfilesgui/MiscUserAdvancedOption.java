/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import org.netbeans.spi.options.AdvancedOption;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.NbBundle;

public final class MiscUserAdvancedOption extends AdvancedOption {

    public String getDisplayName() {
        return NbBundle.getMessage(MiscUserAdvancedOption.class, "AdvancedOption_DisplayName_MiscUser");
    }

    public String getTooltip() {
        return NbBundle.getMessage(MiscUserAdvancedOption.class, "AdvancedOption_Tooltip_MiscUser");
    }

    public OptionsPanelController create() {
        return new MiscUserOptionsPanelController();
    }
}
