/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.feedreader;

import javax.swing.Action;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 *
 * @author Administrador
 */
public class RssNode extends FilterNode {

    public RssNode(Node folderNode) throws DataObjectNotFoundException {
        super(folderNode, new RssFolderChildren(folderNode));
    }

    @Override
    public Action[] getActions(boolean popup) {

        //Declare our actions
        //and pass along the node's data folder:
        DataFolder df = getLookup().lookup(DataFolder.class);
        return new Action[]{ new AddRssAction(df), new AddFolderAction(df)};

    }

    public static class RootRssNode extends RssNode {

        //The filter node will serve as a proxy
        //for the 'RssFeeds' node, which we here
        //obtain from the NetBeans user directory:
        public RootRssNode() throws DataObjectNotFoundException {
            super(DataObject.find(Repository.getDefault().getDefaultFileSystem().
                    getRoot().getFileObject("RssFeeds")).getNodeDelegate());
        }

        //Set the display name of the node,
        //referring to the bundle file, and
        //a key, which we will define later:
        @Override
        public String getDisplayName() {
            return NbBundle.getMessage(RssNode.class, "FN_title");
        }
    }
}
