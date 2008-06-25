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

import de.berlios.jfindmyfiles.catalog.CatalogConstants;
import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.hibernate.Session;
import org.openide.explorer.view.NodeTableModel;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

//import org.openide.util.Utilities;
/**
 * Top component which displays something.
 */
final class DetailsViewTopComponent extends TopComponent {

    private static DetailsViewTopComponent instance;
    private static final String PREFERRED_ID = "DetailsViewTopComponent";
    private NodeModel nModel;
    private CatalogEngine eng;
    private Lookup.Result catalogResult = null;
    private Lookup.Result diskResult = null;
    private Lookup.Result groupResult = null;
    private Lookup.Result fileResult = null;

    private DetailsViewTopComponent() {
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        nModel = new NodeModel();
        initComponents();
        setName(NbBundle.getMessage(DetailsViewTopComponent.class, "CTL_DetailsViewTopComponent"));
        setToolTipText(NbBundle.getMessage(DetailsViewTopComponent.class, "HINT_DetailsViewTopComponent"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jscpDetailsScroll = new javax.swing.JScrollPane();
        jtDetailsTable = new javax.swing.JTable();

        jtDetailsTable.setModel(nModel);
        jscpDetailsScroll.setViewportView(jtDetailsTable);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jscpDetailsScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jscpDetailsScroll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jscpDetailsScroll;
    private javax.swing.JTable jtDetailsTable;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized DetailsViewTopComponent getDefault() {
        if (instance == null) {
            instance = new DetailsViewTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the DetailsViewTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized DetailsViewTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(DetailsViewTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof DetailsViewTopComponent) {
            return (DetailsViewTopComponent) win;
        }
        Logger.getLogger(DetailsViewTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        diskResult = Utilities.actionsGlobalContext().lookup(new Lookup.Template(Media.class));
        diskResult.addLookupListener(nModel);

        catalogResult = Utilities.actionsGlobalContext().lookup(new Lookup.Template(CatalogEngine.class));
        catalogResult.addLookupListener(nModel);

        fileResult = Utilities.actionsGlobalContext().lookup(new Lookup.Template(FileWrapper.class));
        fileResult.addLookupListener(nModel);

        groupResult = Utilities.actionsGlobalContext().lookup(new Lookup.Template(DiskGroup.class));
        groupResult.addLookupListener(nModel);
    }

    @Override
    public void componentClosed() {
        diskResult.removeLookupListener(nModel);
        diskResult = null;
        catalogResult.removeLookupListener(nModel);
        catalogResult = null;
        fileResult.removeLookupListener(nModel);
        fileResult = null;
        groupResult.removeLookupListener(nModel);
        groupResult = null;
    }

    /** replaces this in object stream */
    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    final static class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
            return DetailsViewTopComponent.getDefault();
        }
    }

    private class NodeModel extends AbstractTableModel implements LookupListener {

        private List catalogChildren;
        private Object[] sItem = new Object[4];
        private int sItemIndex;

        public NodeModel() {
            super();
            loadCatalogChildren();
            sItem[0] = eng;
        }

        private void loadCatalogChildren() {
            Session s = eng.sessionFactory.getCurrentSession();
            s.beginTransaction();
            catalogChildren.addAll(s.createQuery("from DiskGroup where parent.id is null").list());
            catalogChildren.addAll(s.createQuery("from Media where group.id is null").list());
            s.getTransaction().commit();
        }

        public int getRowCount() {
            switch (sItemIndex) {
                case 0://Catalog
                    return catalogChildren.size();
                case 1://Group
                    return ((DiskGroup) sItem[1]).getGroupList().size() + ((DiskGroup) sItem[1]).getDiskList().size();
                case 2://Media
                    ((Media) sItem[2]).getFileList().size();
                    return 0;
                case 3://File
                    ((FileWrapper) sItem[3]).getChildrenList().size();
                default:
                    return 0;
            }
        }

        public int getColumnCount() {
            switch (sItemIndex) {
                case 0://CatalogEngine
                    return 7;
                case 1://Group
                    return 7;
                case 2://Media
                case 3://File
                    return 6;
                default:
                    return 0;
            }
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Object o = null;
            switch (sItemIndex) {
                case 0://CatalogEngine
                    o = catalogChildren.get(rowIndex);
                    if (o instanceof DiskGroup) {
                        switch (columnIndex) {
                            case 0: //name;
                                return ((DiskGroup) o).getName();
                            case 1: //capacity;
                                return ((DiskGroup) o).getCapacity();
                            case 2: //freeSpace;
                            case 3: //lastModified;
                                return 0;
                            case 6: //type;
                                return "Disk Group";//TODO: i18n
                            case 4: //description;
                                return ((DiskGroup) o).getDescription();
                            case 5: //location;
                                return "";
                        }
                    } else {
                        switch (columnIndex) {
                            case 0: //name;
                                return ((Media) o).getName();
                            case 1: //capacity;
                                return ((Media) o).getCapacity();
                            case 2: //freeSpace;
                                return ((Media) o).getFreeSpace();
                            case 3: //lastModified;
                                return ((Media) o).getLastModified();
                            case 4: //type;
                                return CatalogConstants.getTypeName(((Media) o).getType());
                            case 5: //location;
                                return ((Media) o).getLocation();
                            case 6: //description;
                                return ((Media) o).getDescription();
                        }
                    }
                    return new Object();
                case 1://Group
                    if (rowIndex < ((DiskGroup) sItem[1]).getGroupList().size()) {
                        o = ((DiskGroup) sItem[1]).getGroupList().get(rowIndex);
                    } else {
                        o = ((DiskGroup) sItem[1]).getDiskList().get(rowIndex - ((DiskGroup) sItem[1]).getGroupList().size());
                    }
                    if (o instanceof DiskGroup) {
                        switch (columnIndex) {
                            case 0: //name;
                                return ((DiskGroup) o).getName();
                            case 1: //description;
                                return ((DiskGroup) o).getDescription();
                            case 2: //capacity;
                                return ((DiskGroup) o).getCapacity();
                            case 3: //lastModified;
                            case 4: //freeSpace;
                                return 0;
                            case 5: //location;
                                return "";
                            case 6: //type;
                                return "Disk Group";//TODO: i18n
                        }
                    } else {
                        switch (columnIndex) {
                            case 0: //name;
                                return ((Media) o).getName();
                            case 1: //description;
                                return ((Media) o).getDescription();
                            case 2: //capacity;
                                return ((Media) o).getCapacity();
                            case 3: //lastModified;
                                return ((Media) o).getLastModified();
                            case 4: //freeSpace;
                                return ((Media) o).getFreeSpace();
                            case 5: //location;
                                return ((Media) o).getLocation();
                            case 6: //type;
                                return CatalogConstants.getTypeName(((Media) o).getType());
                        }
                    }
                    return new Object();
                case 2://Media
                case 3://File
                    if (o instanceof Media) {
                        switch (columnIndex) {
                            case 0://name
                                return ((Media) o).getName();
                            case 1://extension;
                                return "";
                            case 2://size;
                                return ((Media) o).getCapacity() - ((Media) o).getFreeSpace();
                            case 3://lastModified;
                                return ((Media) o).getLastModified();
                            case 4://sha1;
                                return "";
                            case 5://description;
                                return ((Media) o).getDescription();
                        }
                    } else {
                        switch (columnIndex) {
                            case 0://name
                                return ((FileWrapper) o).getName();
                            case 1://extension;
                                return ((FileWrapper) o).getExtension();
                            case 2://size;
                                return ((FileWrapper) o).getSize();
                            case 3://lastModified;
                                return ((FileWrapper) o).getLastModified();
                            case 4://sha1;
                                return ((FileWrapper) o).getSha1();
                            case 5://description;
                                return ((FileWrapper) o).getDescription();
                        }
                    }
                    return new Object();
                default:
                    return new Object();
            }
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            Object o = null;
            switch (sItemIndex) {
                case 0://CatalogEngine
                    switch (columnIndex) {
                        case 1: //capacity;
                        case 2: //freeSpace;
                        case 3: //lastModified;
                            return long.class;
                        case 0: //name;
                        case 6: //type;
                        case 4: //description;
                        case 5: //location;
                            return String.class;
                        default:
                            return Object.class;
                    }
                case 1://Group
                    switch (columnIndex) {
                        case 0: //name;
                        case 1: //description;
                        case 5: //location;
                        case 6: //type;
                            return String.class;
                        case 2: //capacity;
                        case 3: //lastModified;
                        case 4: //freeSpace;
                            return long.class;
                        default:
                            return Object.class;
                    }
                case 2://Media
                case 3://File
                    switch (columnIndex) {
                        case 0://name
                        case 1://extension;
                        case 4://sha1;
                        case 5://description;
                            return String.class;
                        case 2://size;
                        case 3://lastModified;
                            return long.class;
                    }
                default:
                    return Object.class;
            }
        }

        public void resultChanged(LookupEvent evt) {
            Collection c = ((Lookup.Result) evt.getSource()).allInstances();
            Object o;
            if (!c.isEmpty()) {
                o = c.iterator().next();
                if (o instanceof CatalogEngine) {
                    sItemIndex = 0;
                } else if (o instanceof DiskGroup) {
                    sItem[1] = o;
                    sItemIndex = 1;
                } else if (o instanceof Media) {
                    sItem[2] = o;
                    sItemIndex = 2;
                } else if (o instanceof FileWrapper) {
                    sItem[3] = o;
                    sItemIndex = 3;
                }
                fireTableDataChanged();
            }
        }
    }
}
