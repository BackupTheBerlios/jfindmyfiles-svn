/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.awt.Component;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import org.openide.explorer.propertysheet.PropertyPanel;
import org.openide.explorer.view.NodeTableModel;
import org.openide.nodes.Node;
import org.openide.nodes.Node.Property;
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
final class WindowTestingTopComponent extends TopComponent implements LookupListener {

    private NodeTableModel ntModel;
    private static WindowTestingTopComponent instance;
    private static final String PREFERRED_ID = "WindowTestingTopComponent";

    private WindowTestingTopComponent() {
        ntModel = new NodeTableModel();
        ntModel.addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                System.err.println("VALUE CLASS: " + ntModel.getColumnClass(e.getColumn()));
            }
        });
        Lookup.Result r = Utilities.actionsGlobalContext().lookup(new Lookup.Template<Media>(Media.class));
        r.addLookupListener(this);
        initComponents();
        setName(NbBundle.getMessage(WindowTestingTopComponent.class, "CTL_WindowTestingTopComponent"));
        setToolTipText(NbBundle.getMessage(WindowTestingTopComponent.class, "HINT_WindowTestingTopComponent"));
        //
        //WORK DAMN YOU!!!!!!!!!!!!!!!!!!!
        jTable1.setDefaultRenderer(Node.Property.class, new MyPropertyCellRenderer());
//        setIcon(Utilities.loadImage(ICON_PATH, true));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jpTableView = new javax.swing.JPanel();

        jTable1.setModel(ntModel);
        jScrollPane1.setViewportView(jTable1);

        jpTableView.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(WindowTestingTopComponent.class, "WindowTestingTopComponent.jpTableView.border.title"))); // NOI18N

        javax.swing.GroupLayout jpTableViewLayout = new javax.swing.GroupLayout(jpTableView);
        jpTableView.setLayout(jpTableViewLayout);
        jpTableViewLayout.setHorizontalGroup(
            jpTableViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );
        jpTableViewLayout.setVerticalGroup(
            jpTableViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpTableView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpTableView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpTableView;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized WindowTestingTopComponent getDefault() {
        if (instance == null) {
            instance = new WindowTestingTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the WindowTestingTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized WindowTestingTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(WindowTestingTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof WindowTestingTopComponent) {
            return (WindowTestingTopComponent) win;
        }
        Logger.getLogger(WindowTestingTopComponent.class.getName()).warning(
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
        Lookup.Result r = Utilities.actionsGlobalContext().lookup(new Lookup.Template<Media>(Media.class));
        r.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
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
            return WindowTestingTopComponent.getDefault();
        }
    }

    public void resultChanged(LookupEvent arg0) {
        TopComponent.Registry registry = TopComponent.getRegistry();
        Node[] act = registry.getActivatedNodes();
        ntModel.setNodes(act);
        ntModel.fireTableStructureChanged();
        for (Node n : act) {
            System.out.println(n);
        }
    }

    class PropertyCellRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            try {
                PropertyPanel panel = new PropertyPanel((Property) value);
                panel.setPreferences(PropertyPanel.PREF_TABLEUI);
                System.err.println("VALUE FROM PROPERTY: " + ((Property) value).getValue());
                return panel;
            } catch (IllegalAccessException ex) {
                System.err.println("EXCEPTION");
                return null;
            } catch (InvocationTargetException ex) {
                System.err.println("EXCEPTION");
                return null;
            }
        }
    }

    class MyPropertyCellRenderer extends JLabel implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            try {
                Property p = (Property) value;
                setText((String) p.getValue());
                return this;
            } catch (IllegalAccessException ex) {
                return null;
            } catch (InvocationTargetException ex) {
                return null;
            }
        }
    }
}
