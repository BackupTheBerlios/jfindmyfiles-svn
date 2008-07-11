/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import org.openide.util.NbPreferences;

final class LinuxPanel extends javax.swing.JPanel {

    private final LinuxOptionsPanelController controller;
    private DefaultListModel model;
    private JFileChooser jfc;

    LinuxPanel(LinuxOptionsPanelController controller) {
        this.controller = controller;
        model = new DefaultListModel();
        jfc = new JFileChooser(System.getProperty("user.home"));
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setDragEnabled(false);
        jfc.setMultiSelectionEnabled(false);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jscpFolders = new javax.swing.JScrollPane();
        jlstFolders = new javax.swing.JList();
        jlblMountFolder = new javax.swing.JLabel();
        jtfMountFolder = new javax.swing.JTextField();
        jtbnBrowse = new javax.swing.JButton();
        jbtnAdd = new javax.swing.JButton();
        jbtnRemove = new javax.swing.JButton();
        jchkScanMedia = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        jlstFolders.setModel(model);
        jscpFolders.setViewportView(jlstFolders);

        org.openide.awt.Mnemonics.setLocalizedText(jlblMountFolder, org.openide.util.NbBundle.getMessage(LinuxPanel.class, "LinuxPanel.jlblMountFolder.text")); // NOI18N

        jtfMountFolder.setText(org.openide.util.NbBundle.getMessage(LinuxPanel.class, "LinuxPanel.jtfMountFolder.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jtbnBrowse, org.openide.util.NbBundle.getMessage(LinuxPanel.class, "LinuxPanel.jtbnBrowse.text")); // NOI18N
        jtbnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnBrowseActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jbtnAdd, org.openide.util.NbBundle.getMessage(LinuxPanel.class, "LinuxPanel.jbtnAdd.text")); // NOI18N
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jbtnRemove, org.openide.util.NbBundle.getMessage(LinuxPanel.class, "LinuxPanel.jbtnRemove.text")); // NOI18N
        jbtnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRemoveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jchkScanMedia, org.openide.util.NbBundle.getMessage(LinuxPanel.class, "LinuxPanel.jchkScanMedia.text")); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/dialog-warning.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(LinuxPanel.class, "LinuxPanel.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(jchkScanMedia)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblMountFolder)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfMountFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                            .addComponent(jscpFolders, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnRemove, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtbnBrowse, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbtnAdd, jbtnRemove, jtbnBrowse});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jchkScanMedia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblMountFolder)
                            .addComponent(jtfMountFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jscpFolders, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtbnBrowse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnRemove)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jtbnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnBrowseActionPerformed
    if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        jtfMountFolder.setText(jfc.getSelectedFile().getAbsolutePath());
    }
}//GEN-LAST:event_jtbnBrowseActionPerformed

private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
    if(!jtfMountFolder.getText().trim().isEmpty()) {
        model.addElement(jtfMountFolder.getText().trim());
        controller.changed();
    }
}//GEN-LAST:event_jbtnAddActionPerformed

private void jbtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRemoveActionPerformed
    if(jlstFolders.getSelectedIndex() >= 0) {
        model.remove(jlstFolders.getSelectedIndex());
        controller.changed();
    }
}//GEN-LAST:event_jbtnRemoveActionPerformed

    void load() {
        jchkScanMedia.setSelected(NbPreferences.forModule(LinuxPanel.class).getBoolean("scanmedia", false));
        int count = NbPreferences.forModule(LinuxPanel.class).getInt("moutnumber", 0);
        for(int z = 0; z < count; z++) {
            model.addElement(NbPreferences.forModule(LinuxPanel.class).get("mountfolder-" + z, ""));
        }
    }

    void store() {
        NbPreferences.forModule(LinuxPanel.class).putBoolean("scanmedia", jchkScanMedia.isSelected());
        NbPreferences.forModule(LinuxPanel.class).putInt("moutnumber", model.size());
        for(int z = 0; z < model.size(); z++) {
            NbPreferences.forModule(LinuxPanel.class).put("mountfolder-" + z, model.get(z).toString());
        }
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnRemove;
    private javax.swing.JCheckBox jchkScanMedia;
    private javax.swing.JLabel jlblMountFolder;
    private javax.swing.JList jlstFolders;
    private javax.swing.JScrollPane jscpFolders;
    private javax.swing.JButton jtbnBrowse;
    private javax.swing.JTextField jtfMountFolder;
    // End of variables declaration//GEN-END:variables
}
