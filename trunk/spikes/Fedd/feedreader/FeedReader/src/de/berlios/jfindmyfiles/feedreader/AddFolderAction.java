/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.loaders.DataFolder;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author Administrador
 */
public class AddFolderAction extends AbstractAction {

    private DataFolder folder;

    public AddFolderAction(DataFolder df) {
        folder = df;
        putValue(Action.NAME, NbBundle.getMessage(RssNode.class, "FN_addfolderbutton"));
    }

    public void actionPerformed(ActionEvent ae) {
        NotifyDescriptor.InputLine nd =
                new NotifyDescriptor.InputLine(
                NbBundle.getMessage(RssNode.class, "FN_askfolder_msg"),
                NbBundle.getMessage(RssNode.class, "FN_askfolder_title"),
                NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE);
        Object result = DialogDisplayer.getDefault().notify(nd);
        if (result.equals(NotifyDescriptor.OK_OPTION)) {
            final String folderString = nd.getInputText();
            try {
                DataFolder.create(folder, folderString);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
