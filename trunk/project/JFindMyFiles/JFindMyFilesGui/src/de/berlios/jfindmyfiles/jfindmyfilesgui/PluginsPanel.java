/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import de.berlios.jfindmyfiles.readingfiles.pluginapi.PluginCache;
import de.berlios.jfindmyfiles.readingfiles.pluginapi.Reader;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.Lookup;
import org.openide.util.NbPreferences;

final class PluginsPanel extends javax.swing.JPanel {

    private final PluginsPanelController controller;
    private PluginCache cache;

    PluginsPanel(PluginsPanelController controller) {
        this.controller = controller;
        cache = Lookup.getDefault().lookup(PluginCache.class);
        initComponents();
    // TODO listen to changes in form fields and call controller.changed()
        /*jtfPluginFolder.getDocument().addDocumentListener(new DocumentListener() {
    
    public void insertUpdate(DocumentEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void removeUpdate(DocumentEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void changedUpdate(DocumentEvent e) {
    throw new UnsupportedOperationException("Not supported yet.");
    }
    });*/
    }

    private void myInitComponents() {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblPluginFolder = new javax.swing.JLabel();
        jtfPluginFolder = new javax.swing.JTextField();
        jbtnBrowse = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jscpPluginList = new javax.swing.JScrollPane();
        jtbPluginList = new javax.swing.JTable();

        org.openide.awt.Mnemonics.setLocalizedText(jlblPluginFolder, org.openide.util.NbBundle.getMessage(PluginsPanel.class, "PluginsPanel.jlblPluginFolder.text")); // NOI18N

        jtfPluginFolder.setText(org.openide.util.NbBundle.getMessage(PluginsPanel.class, "PluginsPanel.jtfPluginFolder.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jbtnBrowse, org.openide.util.NbBundle.getMessage(PluginsPanel.class, "PluginsPanel.jbtnBrowse.text")); // NOI18N
        jbtnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBrowseActionPerformed(evt);
            }
        });

        jtbPluginList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbPluginList.setRowSelectionAllowed(false);
        jscpPluginList.setViewportView(jtbPluginList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jscpPluginList, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblPluginFolder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPluginFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBrowse)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblPluginFolder)
                    .addComponent(jbtnBrowse)
                    .addComponent(jtfPluginFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscpPluginList, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jbtnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBrowseActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jbtnBrowseActionPerformed

    void load() {
        jtfPluginFolder.setText(NbPreferences.forModule(PluginCache.class).get("PluginFolder","")); // NOI18N
        //TODO: load plugin definition
    }

    void store() {
        NbPreferences.forModule(PluginCache.class).put("PluginFolder", jtfPluginFolder.getText().trim()); // NOI18N
        //TODO: save plugin definition
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnBrowse;
    private javax.swing.JLabel jlblPluginFolder;
    private javax.swing.JScrollPane jscpPluginList;
    private javax.swing.JTable jtbPluginList;
    private javax.swing.JTextField jtfPluginFolder;
    // End of variables declaration//GEN-END:variables

    private class PluginModel extends AbstractTableModel {

        private List<Reader> values;
        private int rowCount;
        
        public PluginModel() {
            super();
            values = cache.listAll();            
            rowCount = cache.pluginCount();
        }
        
        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex) {
                case 0:
                    return values.get(rowIndex).getName();
                case 1:
                    return values.get(rowIndex).pluginFor();
                case 2:
                    return values.get(rowIndex).getAuthor();
                case 3:
                    return null;//TODO: create an object that can represent if the plugin is active or not
            }
            return null;
        }
        
        @Override
        public Class getColumnClass(int c) {
            switch(c) {
                case 0:
                    return String.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return Boolean.class;
            }
            return Object.class;
        }
    
        @Override
        public boolean isCellEditable(int l, int c) {
            return c == 3;
        }        
    }
}
