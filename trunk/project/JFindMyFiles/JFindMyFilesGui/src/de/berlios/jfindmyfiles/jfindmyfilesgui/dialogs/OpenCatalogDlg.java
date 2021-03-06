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
package de.berlios.jfindmyfiles.jfindmyfilesgui.dialogs;

import de.berlios.jfindmyfiles.catalog.CatalogConstants;
import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.jfindmyfilesgui.utils.CatalogFilter;
import de.berlios.jfindmyfiles.jfindmyfilesgui.utils.CatalogView;
import java.io.File;
import javax.swing.JFileChooser;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;

public class OpenCatalogDlg extends javax.swing.JDialog {

    private CatalogEngine eng;
    private OpenCatalogDlg me = this;

    /** Creates new form NewCatalogDlg */
    public OpenCatalogDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        initComponents();
        jlbError.setVisible(false);
    }

    public void showCentered() {
        setLocation(getParent().getX() + (getParent().getWidth() / 2) - (getWidth() / 2),
                getParent().getY() + (getParent().getHeight() / 2) - (getHeight() / 2));
        setVisible(true);
    }

    private String findCatalogName(File folder) {
        File[] list = folder.listFiles();
        for (File f : list) {
            if (f.getName().contains(".properties")) {// NOI18N
                return f.getName().substring(0, f.getName().lastIndexOf("."));// NOI18N
            }
        }
        return "";
    }

    private boolean validateWihtMessages() {
        if (!jchkUserinternalDB.isSelected() && jtfName.getText().isEmpty()) {
            jlbError.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.emptyname")); // NOI18N

            jlbError.setVisible(true);
            validate();
            return false;
        }

        if (jchkUserinternalDB.isSelected()) {
            if (jtfDestination.getText().isEmpty()) {
                jlbError.setText(NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.invaliddestination")); // NOI18N

                jlbError.setVisible(true);
                validate();
                return false;
            }
        } else {
            if (jtfHostname.getText().isEmpty()) {
                jlbError.setText(NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.invalidurl")); // NOI18N

                jlbError.setVisible(true);
                validate();
                return false;
            } else if (jtfUsername.getText().isEmpty()) {
                jlbError.setText(NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.invalidusername")); // NOI18N

                jlbError.setVisible(true);
                validate();
                return false;
            } else if (jffPort.getText().isEmpty()) {
                jlbError.setText(NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.invalidport")); // NOI18N

                jlbError.setVisible(true);
                validate();
                return false;
            }
        }
        if (jlbError.isVisible()) {
            jlbError.setVisible(false);
            validate();
        }
        return true;
    }

    /**
     * Enables or disables the various components that refer to the remote 
     * database's settings.
     * 
     * @param state true/false for activating and deactivating the components.
     */
    private void serverOptionsStateChanged(boolean state) {
        //jpDatabaseSettings.setEnabled(state);
        //Labels
        jlblDatabase.setEnabled(state);
        jlblHostname.setEnabled(state);
        jlblPassword.setEnabled(state);
        jlblPassword.setEnabled(state);
        jlblPort.setEnabled(state);
        jlblUsername.setEnabled(state);

        //Inputs
        jtfHostname.setEnabled(state);
        jtfUsername.setEnabled(state);
        jpfPassword.setEnabled(state);
        jffPort.setEnabled(state);
        jcbxDatabase.setEnabled(state);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDatabaseSettings = new javax.swing.JPanel();
        jlblHostname = new javax.swing.JLabel();
        jtfHostname = new javax.swing.JTextField();
        jlblUsername = new javax.swing.JLabel();
        jtfUsername = new javax.swing.JTextField();
        jlblPort = new javax.swing.JLabel();
        jffPort = new javax.swing.JFormattedTextField();
        jlblPassword = new javax.swing.JLabel();
        jlblDatabase = new javax.swing.JLabel();
        jcbxDatabase = new javax.swing.JComboBox();
        jpfPassword = new javax.swing.JPasswordField();
        jbtnHelp = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        jbtnOpen = new javax.swing.JButton();
        jchkUserinternalDB = new javax.swing.JCheckBox();
        jtfName = new javax.swing.JTextField();
        jlblName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlblDestination = new javax.swing.JLabel();
        jbtnBrowse = new javax.swing.JButton();
        jtfDestination = new javax.swing.JTextField();
        jlbError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "NewCatalogDlg.title")); // NOI18N
        setIconImage(null);

        jpDatabaseSettings.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jpDatabaseSettings.border.title"))); // NOI18N

        jlblHostname.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlblHostname.text")); // NOI18N
        jlblHostname.setEnabled(false);

        jtfHostname.setAutoscrolls(false);
        jtfHostname.setEnabled(false);

        jlblUsername.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlblUsername.text")); // NOI18N
        jlblUsername.setEnabled(false);

        jtfUsername.setEnabled(false);

        jlblPort.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlblPort.text")); // NOI18N
        jlblPort.setEnabled(false);

        try {
            jffPort.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jffPort.setEnabled(false);

        jlblPassword.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlblPassword.text")); // NOI18N
        jlblPassword.setEnabled(false);

        jlblDatabase.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlblDatabase.text")); // NOI18N
        jlblDatabase.setEnabled(false);

        jcbxDatabase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Firebird", "PostgreSQL", "MsSQL", "MySQL" }));
        jcbxDatabase.setEnabled(false);

        jpfPassword.setEnabled(false);

        javax.swing.GroupLayout jpDatabaseSettingsLayout = new javax.swing.GroupLayout(jpDatabaseSettings);
        jpDatabaseSettings.setLayout(jpDatabaseSettingsLayout);
        jpDatabaseSettingsLayout.setHorizontalGroup(
            jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatabaseSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDatabaseSettingsLayout.createSequentialGroup()
                        .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblPassword)
                            .addComponent(jlblHostname)
                            .addComponent(jlblUsername))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfHostname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jpfPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtfUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpDatabaseSettingsLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbxDatabase, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jlblDatabase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblPort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jffPort, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpDatabaseSettingsLayout.setVerticalGroup(
            jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatabaseSettingsLayout.createSequentialGroup()
                .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblHostname)
                    .addComponent(jffPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblPort)
                    .addComponent(jtfHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblUsername)
                    .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblPassword)
                    .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatabaseSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblDatabase)
                    .addComponent(jcbxDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbtnHelp.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jbtnHelp.text")); // NOI18N
        jbtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHelpActionPerformed(evt);
            }
        });

        jbtnCancel.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jbtnCancel.text")); // NOI18N
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        jbtnOpen.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jbtnOpen.text")); // NOI18N
        jbtnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOpenActionPerformed(evt);
            }
        });

        jchkUserinternalDB.setSelected(true);
        jchkUserinternalDB.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jchkUserinternalDB.text")); // NOI18N
        jchkUserinternalDB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jchkUserinternalDBStateChanged(evt);
            }
        });

        jlblName.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlblName.text")); // NOI18N

        jlblDestination.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlblDestination.text")); // NOI18N

        jbtnBrowse.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jbtnBrowse.text")); // NOI18N
        jbtnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBrowseActionPerformed(evt);
            }
        });

        jtfDestination.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "NewCatalogDlg.jtfDestination.text")); // NOI18N

        jlbError.setForeground(javax.swing.UIManager.getDefaults().getColor("nb.errorForeground"));
        jlbError.setText(org.openide.util.NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.jlbError.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfName, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkUserinternalDB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpDatabaseSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblDestination)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfDestination, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnBrowse))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlbError, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnOpen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnHelp)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbtnCancel, jbtnHelp, jbtnOpen});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblName)
                    .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jchkUserinternalDB)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblDestination)
                    .addComponent(jbtnBrowse)
                    .addComponent(jtfDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDatabaseSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnHelp)
                    .addComponent(jbtnCancel)
                    .addComponent(jbtnOpen)
                    .addComponent(jlbError))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
    dispose();
}//GEN-LAST:event_jbtnCancelActionPerformed

private void jbtnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOpenActionPerformed
    if (validateWihtMessages() && eng != null) {
        String selectedname = (String) jcbxDatabase.getSelectedItem();

        // if dbtype is selected to local return the CatalogEngine.LOCAL, if not 
        // check to see what the selected value of the combobox is and return 
        // the corresponding value.
        final int dbtype = (jchkUserinternalDB.isSelected() ? CatalogConstants.LOCAL : (selectedname.equals("Firebird") ? CatalogConstants.FIREBIRD : (selectedname.equals("PostgreSQL") ? CatalogConstants.POSTGRESQL : (selectedname.equals("MsSQL") ? CatalogConstants.MSSQL : CatalogConstants.MYSQL))));

        new Thread(new Runnable() {

            public void run() {
                eng.openCatalog(jtfName.getText().trim(),
                        (jchkUserinternalDB.isSelected() ? jtfDestination.getText().trim() : jtfHostname.getText().trim()),
                        jffPort.getText().trim(), dbtype,
                        jtfUsername.getText().trim(),
                        jpfPassword.getPassword().toString());
            }
        }).start();
        dispose();
    }
}//GEN-LAST:event_jbtnOpenActionPerformed

private void jbtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHelpActionPerformed
    //TODO: help action
}//GEN-LAST:event_jbtnHelpActionPerformed

private void jchkUserinternalDBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jchkUserinternalDBStateChanged
    serverOptionsStateChanged(!jchkUserinternalDB.isSelected());
    jbtnBrowse.setEnabled(jchkUserinternalDB.isSelected());
    jtfDestination.setEnabled(jchkUserinternalDB.isSelected());
}//GEN-LAST:event_jchkUserinternalDBStateChanged

private void jbtnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBrowseActionPerformed
    JFileChooser jfc = new JFileChooser();
    jfc.setCurrentDirectory(new File(System.getProperty("user.home"))); // NOI18N

    jfc.setDialogTitle(NbBundle.getMessage(OpenCatalogDlg.class, "OpenCatalogDlg.choosedirectory")); // NOI18N

    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    jfc.setMultiSelectionEnabled(false);
    jfc.setFileFilter(new CatalogFilter());
    jfc.setFileView(new CatalogView());
    if (jfc.showOpenDialog(me) == JFileChooser.APPROVE_OPTION) {
        jtfDestination.setText(jfc.getSelectedFile().getAbsolutePath());
        jtfName.setText(findCatalogName(jfc.getSelectedFile()));
    }
}//GEN-LAST:event_jbtnBrowseActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnBrowse;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnHelp;
    private javax.swing.JButton jbtnOpen;
    private javax.swing.JComboBox jcbxDatabase;
    private javax.swing.JCheckBox jchkUserinternalDB;
    private javax.swing.JFormattedTextField jffPort;
    private javax.swing.JLabel jlbError;
    private javax.swing.JLabel jlblDatabase;
    private javax.swing.JLabel jlblDestination;
    private javax.swing.JLabel jlblHostname;
    private javax.swing.JLabel jlblName;
    private javax.swing.JLabel jlblPassword;
    private javax.swing.JLabel jlblPort;
    private javax.swing.JLabel jlblUsername;
    private javax.swing.JPanel jpDatabaseSettings;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JTextField jtfDestination;
    private javax.swing.JTextField jtfHostname;
    private javax.swing.JTextField jtfName;
    private javax.swing.JTextField jtfUsername;
    // End of variables declaration//GEN-END:variables
}
