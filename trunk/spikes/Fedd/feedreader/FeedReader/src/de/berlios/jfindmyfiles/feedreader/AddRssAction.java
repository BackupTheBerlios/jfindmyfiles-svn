/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Action;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 *
 * @author Administrador
 */
public class AddRssAction extends AbstractAction {

    private DataFolder folder;

    public AddRssAction(DataFolder df) {
        folder = df;
        putValue(Action.NAME, NbBundle.getMessage(RssNode.class, "FN_addbutton"));
    }

    public void actionPerformed(ActionEvent ae) {

        NotifyDescriptor.InputLine nd = new NotifyDescriptor.InputLine(
                NbBundle.getMessage(RssNode.class, "FN_askurl_msg"),
                NbBundle.getMessage(RssNode.class, "FN_askurl_title"),
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE);

        Object result = DialogDisplayer.getDefault().notify(nd);

        if (result.equals(NotifyDescriptor.OK_OPTION)) {
            String urlString = nd.getInputText();
            URL url;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException e) {
                String message = NbBundle.getMessage(RssNode.class, "FN_askurl_err", urlString);
                Exceptions.attachLocalizedMessage(e, message);
                Exceptions.printStackTrace(e);
                return;
            }
            try {
                checkConnection(url);
            } catch (IOException e) {
                String message = NbBundle.getMessage(RssNode.class, "FN_cannotConnect_err", urlString);
                Exceptions.attachLocalizedMessage(e, message);
                Exceptions.printStackTrace(e);
                return;
            }
            Feed f=null;
            try
            {
            f = new Feed(url.toString());
            }catch(IOException io) {
                Exceptions.printStackTrace(io);
            }
            FileObject fld = folder.getPrimaryFile();
            String baseName = "RssFeed";
            int ix = 1;
            while (fld.getFileObject(baseName + ix, "ser") != null) {
                ix++;
            }
            try {
                FileObject writeTo = fld.createData(baseName + ix, "ser");
                FileLock lock = writeTo.lock();
                try {
                    ObjectOutputStream str = new ObjectOutputStream(writeTo.getOutputStream(lock));
                    try {
                        str.writeObject(f);
                    } finally {
                        str.close();
                    }
                } finally {
                    lock.releaseLock();
                }
            } catch (IOException ioe) {
                Exceptions.printStackTrace(ioe);
            }
        }  
    }
    
    private static void checkConnection(final URL url) throws IOException {
        InputStream is = url.openStream();
        is.close();
    }
}
