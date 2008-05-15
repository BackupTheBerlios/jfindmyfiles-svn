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
import de.berlios.jfindmyfiles.catalog.entities.CatalogProperties;
import java.util.Date;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author  knitter
 */
public class CatalogPropertiesDlg extends javax.swing.JDialog {

    private CatalogEngine eng;
    private CatalogProperties props;

    /** Creates new form CatalogPropertiesDlg */
    public CatalogPropertiesDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Lookup lu = Lookups.forPath("/CatalogEngine");
        eng = (CatalogEngine) lu.lookup(CatalogEngine.class);

        fillIn();
    }

    public void showCentered() {
        setLocation(getParent().getX() + (getParent().getWidth() / 2) - (getWidth() / 2),
                getParent().getY() + (getParent().getHeight() / 2) - (getHeight() / 2));
        setVisible(true);
    }

    private void fillIn() {
        if (eng != null) {
            Date temp;
            props = eng.getProperties();
            jlblCatalogName.setText(props.getName());
            if ((temp = props.getCreationDate()) != null) {
                jdcCreatedOn.setDate(temp);
            }
            jtaDescription.setText(props.getDescription());
            jlblNrDisksValue.setText(String.valueOf(props.getDiskNumber()));
            jlblNrFilesValue.setText(String.valueOf(props.getTotalFiles()));
            jlblNrFoldersValue.setText(String.valueOf(props.getTotalFolders()));
            jlblTotalSizeValue.setText(String.valueOf(props.getTotalSize()));
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpCatalogData = new javax.swing.JPanel();
        jlblCatalogName = new javax.swing.JLabel();
        jlblCreatedOn = new javax.swing.JLabel();
        jlblIcon = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jdcCreatedOn = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jlblDescription = new javax.swing.JLabel();
        jscrDescription = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();
        jlblTotalSize = new javax.swing.JLabel();
        jlblNrDisks = new javax.swing.JLabel();
        jlblNrFolders = new javax.swing.JLabel();
        jlblNrFiles = new javax.swing.JLabel();
        jlblNrFoldersValue = new javax.swing.JLabel();
        jlblNrFilesValue = new javax.swing.JLabel();
        jlblTotalSizeValue = new javax.swing.JLabel();
        jlblNrDisksValue = new javax.swing.JLabel();
        jbtnCancel = new javax.swing.JButton();
        jbtnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpCatalogData.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jpCatalogData.border.title"))); // NOI18N

        jlblCatalogName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblCatalogName.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblCatalogName.text")); // NOI18N

        jlblCreatedOn.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblCreatedOn.text")); // NOI18N

        jlblIcon.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblIcon.text")); // NOI18N

        jlblDescription.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblDescription.text")); // NOI18N

        jtaDescription.setColumns(20);
        jtaDescription.setRows(5);
        jscrDescription.setViewportView(jtaDescription);

        jlblTotalSize.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblTotalSize.text")); // NOI18N

        jlblNrDisks.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblNrDisks.text")); // NOI18N

        jlblNrFolders.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblNrFolders.text")); // NOI18N

        jlblNrFiles.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblNrFiles.text")); // NOI18N

        jlblNrFoldersValue.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblNrFoldersValue.text")); // NOI18N

        jlblNrFilesValue.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblNrFilesValue.text")); // NOI18N

        jlblTotalSizeValue.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblTotalSizeValue.text")); // NOI18N

        jlblNrDisksValue.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jlblNrDisksValue.text")); // NOI18N

        javax.swing.GroupLayout jpCatalogDataLayout = new javax.swing.GroupLayout(jpCatalogData);
        jpCatalogData.setLayout(jpCatalogDataLayout);
        jpCatalogDataLayout.setHorizontalGroup(
            jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCatalogDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jscrDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpCatalogDataLayout.createSequentialGroup()
                        .addComponent(jlblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblCatalogName, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpCatalogDataLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblCreatedOn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcCreatedOn, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpCatalogDataLayout.createSequentialGroup()
                        .addComponent(jlblNrDisks)
                        .addGap(18, 18, 18)
                        .addComponent(jlblNrDisksValue))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(jlblDescription, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpCatalogDataLayout.createSequentialGroup()
                        .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblNrFolders)
                            .addComponent(jlblNrFiles)
                            .addComponent(jlblTotalSize))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblTotalSizeValue)
                            .addComponent(jlblNrFilesValue)
                            .addComponent(jlblNrFoldersValue))))
                .addContainerGap())
        );
        jpCatalogDataLayout.setVerticalGroup(
            jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCatalogDataLayout.createSequentialGroup()
                .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlblCatalogName)
                    .addComponent(jlblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCatalogDataLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jlblCreatedOn))
                    .addGroup(jpCatalogDataLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcCreatedOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblTotalSize)
                    .addComponent(jlblTotalSizeValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNrDisks)
                    .addComponent(jlblNrDisksValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNrFolders)
                    .addComponent(jlblNrFoldersValue))
                .addGap(7, 7, 7)
                .addGroup(jpCatalogDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNrFiles)
                    .addComponent(jlblNrFilesValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbtnCancel.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jbtnCancel.text")); // NOI18N
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        jbtnOK.setText(org.openide.util.NbBundle.getMessage(CatalogPropertiesDlg.class, "CatalogPropertiesDlg.jbtnOK.text")); // NOI18N
        jbtnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCatalogData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancel)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbtnCancel, jbtnOK});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCatalogData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCancel)
                    .addComponent(jbtnOK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
    dispose();
}//GEN-LAST:event_jbtnCancelActionPerformed

private void jbtnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOKActionPerformed
    if (eng != null && props != null) {
        props.setCreationDate(jdcCreatedOn.getDate());
        props.setDescription(jtaDescription.getText().trim());
        eng.setProperties(props);
    }
}//GEN-LAST:event_jbtnOKActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnOK;
    private com.toedter.calendar.JDateChooser jdcCreatedOn;
    private javax.swing.JLabel jlblCatalogName;
    private javax.swing.JLabel jlblCreatedOn;
    private javax.swing.JLabel jlblDescription;
    private javax.swing.JLabel jlblIcon;
    private javax.swing.JLabel jlblNrDisks;
    private javax.swing.JLabel jlblNrDisksValue;
    private javax.swing.JLabel jlblNrFiles;
    private javax.swing.JLabel jlblNrFilesValue;
    private javax.swing.JLabel jlblNrFolders;
    private javax.swing.JLabel jlblNrFoldersValue;
    private javax.swing.JLabel jlblTotalSize;
    private javax.swing.JLabel jlblTotalSizeValue;
    private javax.swing.JPanel jpCatalogData;
    private javax.swing.JScrollPane jscrDescription;
    private javax.swing.JTextArea jtaDescription;
    // End of variables declaration//GEN-END:variables
}
