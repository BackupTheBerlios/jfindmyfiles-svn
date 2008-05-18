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

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author  ei10635
 */
public class NewDiskDlg extends javax.swing.JDialog {

    private String currentSelectedPath;
    private NewDiskDlg me = this;
    private CatalogEngine eng;

    /** Creates new form NewDiskDlg */
    public NewDiskDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        Lookup lu = Lookups.forPath("/CatalogEngine"); // NOI18N

        eng = lu.lookup(CatalogEngine.class);
        initComponents();
    //TODO: get the available plugins and create the popup menu
    }

    public void showCentered() {
        setLocation(getParent().getX() + (getParent().getWidth() / 2) - (getWidth() / 2),
                getParent().getY() + (getParent().getHeight() / 2) - (getHeight() / 2));
        setVisible(true);
    }

    /** Creates toggle buttons and adds them to the top panel. The buttons will 
     * have the icon given to the drive by the OS and the name provided by the 
     * absolute path.
     * 
     * All buttons are added to a button group so that no two buttons can be 
     * selected at the same time.
     */
    private void createDriveButtons() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = File.listRoots();

        JToggleButton toggle;
        for (int i = 0; i < roots.length; i++) {
            toggle = new JToggleButton(roots[i].getAbsolutePath(), fsv.getSystemIcon(roots[i]));
            toggle.setHorizontalTextPosition(SwingConstants.CENTER);
            toggle.setVerticalTextPosition(SwingConstants.BOTTOM);
            toggle.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    currentSelectedPath = ((JToggleButton) e.getSource()).getText();
                }
            });
            jbtngrpDrives.add(toggle);
            jpButtons.add(toggle);
        }
        toggle = new JToggleButton("..."); // NOI18N

        toggle.setIcon(new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x22/button-folder.png"))); // NOI18N

        toggle.setHorizontalTextPosition(SwingConstants.CENTER);
        toggle.setVerticalTextPosition(SwingConstants.BOTTOM);
        toggle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(new File(System.getProperty("user.home")));
                jfc.setDialogTitle("Select a folder to scan");//TODO: i18n

                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.setMultiSelectionEnabled(false);
                if (jfc.showOpenDialog(me) == JFileChooser.APPROVE_OPTION) {
                    File f = jfc.getSelectedFile();
                    currentSelectedPath = f.getAbsolutePath();
                    ((JToggleButton) e.getSource()).setText(f.getName());
                }
            }
        });
        jpButtons.add(toggle);

        jpButtons.validate();
    }

    private Object[] listDiskGroups() {
        if (eng != null) {
            List g = eng.getDiskGroups();
            if (g != null) {
                ArrayList ar = new ArrayList(g.size());
                for (Object obj : g) {
                    ar.add(obj);
                }

                return ar.toArray();
            }
        }
        return new Object[]{};
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtngrpDrives = new javax.swing.ButtonGroup();
        jpopPlugins = new javax.swing.JPopupMenu();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jpButtons = new javax.swing.JPanel();
        createDriveButtons();
        jlblDiskNumber = new javax.swing.JLabel();
        jffDiskNumber = new javax.swing.JFormattedTextField();
        jlblDiskName = new javax.swing.JLabel();
        jtfDiskName = new javax.swing.JTextField();
        jcbxCatalog = new JComboBox(listDiskGroups());
        jlblCatalog = new javax.swing.JLabel();
        jpScanningOptions = new javax.swing.JPanel();
        jchkCalculateCRC = new javax.swing.JCheckBox();
        jchkAskDescription = new javax.swing.JCheckBox();
        jchkShowAgain = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jtbSelectedPlugins = new javax.swing.JToggleButton();
        jbtnHelp = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        jbtnScan = new javax.swing.JButton();

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jCheckBoxMenuItem2.text")); // NOI18N
        jpopPlugins.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jCheckBoxMenuItem1.text")); // NOI18N
        jpopPlugins.add(jCheckBoxMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpButtons.setPreferredSize(new java.awt.Dimension(10, 50));
        jpButtons.setLayout(new javax.swing.BoxLayout(jpButtons, javax.swing.BoxLayout.LINE_AXIS));

        jlblDiskNumber.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jlblDiskNumber.text")); // NOI18N

        jlblDiskName.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jlblDiskName.text")); // NOI18N

        jcbxCatalog.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empty" }));

        jlblCatalog.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jlblCatalog.text")); // NOI18N

        jpScanningOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Options "));

        jchkCalculateCRC.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jchkCalculateCRC.text")); // NOI18N

        jchkAskDescription.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jchkAskDescription.text")); // NOI18N

        jchkShowAgain.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jchkShowAgain.text")); // NOI18N

        jtbSelectedPlugins.setIcon(new ImageIcon(Utilities.loadImage("/de/berlios/jfindmyfiles/jfindmyfilesgui/resources/images/x16/button-show-plugins.png")));
        jtbSelectedPlugins.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jtbSelectedPlugins.text")); // NOI18N
        jtbSelectedPlugins.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jtbSelectedPlugins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbSelectedPluginsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpScanningOptionsLayout = new javax.swing.GroupLayout(jpScanningOptions);
        jpScanningOptions.setLayout(jpScanningOptionsLayout);
        jpScanningOptionsLayout.setHorizontalGroup(
            jpScanningOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpScanningOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpScanningOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jchkCalculateCRC)
                    .addComponent(jchkAskDescription)
                    .addComponent(jchkShowAgain)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addComponent(jtbSelectedPlugins))
                .addContainerGap())
        );
        jpScanningOptionsLayout.setVerticalGroup(
            jpScanningOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpScanningOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jchkCalculateCRC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkAskDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkShowAgain)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtbSelectedPlugins)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpScanningOptions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jffDiskNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblDiskNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblDiskName)
                            .addComponent(jtfDiskName, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblCatalog)
                            .addComponent(jcbxCatalog, 0, 131, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblDiskName)
                    .addComponent(jlblDiskNumber)
                    .addComponent(jlblCatalog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jffDiskNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfDiskName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxCatalog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpScanningOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jbtnHelp.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jbtnHelp.text")); // NOI18N
        jbtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHelpActionPerformed(evt);
            }
        });

        jbtnCancel.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jbtnCancel.text")); // NOI18N
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        jbtnScan.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jbtnScan.text")); // NOI18N
        jbtnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnScanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtnScan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnHelp)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnHelp)
                    .addComponent(jbtnCancel)
                    .addComponent(jbtnScan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jtbSelectedPluginsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbSelectedPluginsActionPerformed
    //TODO:
    if (jtbSelectedPlugins.isSelected()) {
        jpopPlugins.setLocation(getParent().getX() + this.getX() + jtbSelectedPlugins.getX() + jtbSelectedPlugins.getWidth(),
                getParent().getY() + this.getY() + jtbSelectedPlugins.getY() + jtbSelectedPlugins.getHeight() + 48);
        jpopPlugins.setVisible(true);
    } else {
        jpopPlugins.setVisible(false);
    }

}//GEN-LAST:event_jtbSelectedPluginsActionPerformed

private void jbtnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnScanActionPerformed
    if (eng != null) {
        //TODO: eng.readDisk(new File(currentSelectedPath), (DiskGroup)jcbxCatalog.getSelectedItem());
        eng.readDisk(new File(currentSelectedPath), null, false);
    }
}//GEN-LAST:event_jbtnScanActionPerformed

private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
    jpopPlugins.setVisible(false);
    dispose();
}//GEN-LAST:event_jbtnCancelActionPerformed

private void jbtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHelpActionPerformed
// TODO: help code
}//GEN-LAST:event_jbtnHelpActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnHelp;
    private javax.swing.JButton jbtnScan;
    private javax.swing.ButtonGroup jbtngrpDrives;
    private javax.swing.JComboBox jcbxCatalog;
    private javax.swing.JCheckBox jchkAskDescription;
    private javax.swing.JCheckBox jchkCalculateCRC;
    private javax.swing.JCheckBox jchkShowAgain;
    private javax.swing.JFormattedTextField jffDiskNumber;
    private javax.swing.JLabel jlblCatalog;
    private javax.swing.JLabel jlblDiskName;
    private javax.swing.JLabel jlblDiskNumber;
    private javax.swing.JPanel jpButtons;
    private javax.swing.JPanel jpScanningOptions;
    private javax.swing.JPopupMenu jpopPlugins;
    private javax.swing.JToggleButton jtbSelectedPlugins;
    private javax.swing.JTextField jtfDiskName;
    // End of variables declaration//GEN-END:variables
}
