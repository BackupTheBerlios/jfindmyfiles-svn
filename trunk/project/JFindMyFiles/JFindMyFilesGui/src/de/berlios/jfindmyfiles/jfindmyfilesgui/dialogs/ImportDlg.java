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

import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import org.openide.util.Utilities;

/**
 *
 * @author  knitter
 */
public class ImportDlg extends javax.swing.JDialog {
    //private Integer[] values = new Integer[]{0, 1, 2, 3, 4, 5};
    private Integer[] values = new Integer[]{0};
    private File selectedFile;

    /** Creates new form ImportDlg */
    public ImportDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void showCentered() {
        setLocation(getParent().getX() + (getParent().getWidth() / 2) - (getWidth() / 2),
                getParent().getY() + (getParent().getHeight() / 2) - (getHeight() / 2));
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnHelp = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        jbtnImport = new javax.swing.JButton();
        jpImportTypes = new javax.swing.JPanel();
        jscImportTypes = new javax.swing.JScrollPane();
        jlstImportTypes = new JList(values);
        jpImportDetails = new javax.swing.JPanel();
        jpGeneralDetails = new javax.swing.JPanel();
        jlblFile = new javax.swing.JLabel();
        jtfFile = new javax.swing.JTextField();
        jbtnBrowse = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jchkImportAudio = new javax.swing.JCheckBox();
        jchkImportImage = new javax.swing.JCheckBox();
        jchkImportVideo = new javax.swing.JCheckBox();
        jchkImportCustomIcons = new javax.swing.JCheckBox();
        jlblInclude = new javax.swing.JLabel();
        jpDatabaseDetails = new javax.swing.JPanel();
        jpProgressBar = new javax.swing.JPanel();
        jpbExporting = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.title")); // NOI18N

        jbtnHelp.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jbtnHelp.text")); // NOI18N

        jbtnCancel.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jbtnCancel.text")); // NOI18N
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        jbtnImport.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jbtnImport.text")); // NOI18N

        jpImportTypes.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jpImportTypes.border.title"))); // NOI18N

        jlstImportTypes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlstImportTypes.setCellRenderer(new Renderer());
        jscImportTypes.setViewportView(jlstImportTypes);

        javax.swing.GroupLayout jpImportTypesLayout = new javax.swing.GroupLayout(jpImportTypes);
        jpImportTypes.setLayout(jpImportTypesLayout);
        jpImportTypesLayout.setHorizontalGroup(
            jpImportTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpImportTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jscImportTypes, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpImportTypesLayout.setVerticalGroup(
            jpImportTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpImportTypesLayout.createSequentialGroup()
                .addComponent(jscImportTypes, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpImportDetails.setLayout(new java.awt.CardLayout());

        jpGeneralDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jpGeneralDetails.border.title"))); // NOI18N

        jlblFile.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jlblFile.text")); // NOI18N

        jtfFile.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jtfFile.text")); // NOI18N

        jbtnBrowse.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jbtnBrowse.text")); // NOI18N

        jchkImportAudio.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jchkImportAudio.text")); // NOI18N

        jchkImportImage.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jchkImportImage.text")); // NOI18N

        jchkImportVideo.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jchkImportVideo.text")); // NOI18N

        jchkImportCustomIcons.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jchkImportCustomIcons.text")); // NOI18N

        jlblInclude.setText(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jlblInclude.text")); // NOI18N

        javax.swing.GroupLayout jpGeneralDetailsLayout = new javax.swing.GroupLayout(jpGeneralDetails);
        jpGeneralDetails.setLayout(jpGeneralDetailsLayout);
        jpGeneralDetailsLayout.setHorizontalGroup(
            jpGeneralDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGeneralDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGeneralDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpGeneralDetailsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpGeneralDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchkImportCustomIcons)
                            .addComponent(jchkImportVideo)
                            .addComponent(jchkImportImage)
                            .addComponent(jchkImportAudio)))
                    .addGroup(jpGeneralDetailsLayout.createSequentialGroup()
                        .addComponent(jlblFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfFile, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBrowse))
                    .addGroup(jpGeneralDetailsLayout.createSequentialGroup()
                        .addComponent(jlblInclude)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpGeneralDetailsLayout.setVerticalGroup(
            jpGeneralDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGeneralDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGeneralDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblFile)
                    .addComponent(jbtnBrowse)
                    .addComponent(jtfFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpGeneralDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblInclude)
                    .addGroup(jpGeneralDetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkImportAudio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkImportImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkImportVideo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkImportCustomIcons)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jpImportDetails.add(jpGeneralDetails, "GeneralDetails");

        javax.swing.GroupLayout jpDatabaseDetailsLayout = new javax.swing.GroupLayout(jpDatabaseDetails);
        jpDatabaseDetails.setLayout(jpDatabaseDetailsLayout);
        jpDatabaseDetailsLayout.setHorizontalGroup(
            jpDatabaseDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        jpDatabaseDetailsLayout.setVerticalGroup(
            jpDatabaseDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        jpImportDetails.add(jpDatabaseDetails, "DatabaseDetails");

        jpbExporting.setIndeterminate(true);
        jpbExporting.setString(org.openide.util.NbBundle.getMessage(ImportDlg.class, "ImportDlg.jpbExporting.string")); // NOI18N
        jpbExporting.setStringPainted(true);

        javax.swing.GroupLayout jpProgressBarLayout = new javax.swing.GroupLayout(jpProgressBar);
        jpProgressBar.setLayout(jpProgressBarLayout);
        jpProgressBarLayout.setHorizontalGroup(
            jpProgressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpProgressBarLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jpbExporting, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jpProgressBarLayout.setVerticalGroup(
            jpProgressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProgressBarLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jpbExporting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(183, Short.MAX_VALUE))
        );

        jpImportDetails.add(jpProgressBar, "ProgessBar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtnImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnHelp))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpImportTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpImportDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbtnCancel, jbtnHelp, jbtnImport});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpImportDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addComponent(jpImportTypes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnHelp)
                    .addComponent(jbtnCancel)
                    .addComponent(jbtnImport))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
    dispose();
}//GEN-LAST:event_jbtnCancelActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnBrowse;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnHelp;
    private javax.swing.JButton jbtnImport;
    private javax.swing.JCheckBox jchkImportAudio;
    private javax.swing.JCheckBox jchkImportCustomIcons;
    private javax.swing.JCheckBox jchkImportImage;
    private javax.swing.JCheckBox jchkImportVideo;
    private javax.swing.JLabel jlblFile;
    private javax.swing.JLabel jlblInclude;
    private javax.swing.JList jlstImportTypes;
    private javax.swing.JPanel jpDatabaseDetails;
    private javax.swing.JPanel jpGeneralDetails;
    private javax.swing.JPanel jpImportDetails;
    private javax.swing.JPanel jpImportTypes;
    private javax.swing.JPanel jpProgressBar;
    private javax.swing.JProgressBar jpbExporting;
    private javax.swing.JScrollPane jscImportTypes;
    private javax.swing.JTextField jtfFile;
    // End of variables declaration//GEN-END:variables
    /**
     * Renderer class for showing icons on the list of existing export options
     */
    private class Renderer extends JLabel implements ListCellRenderer {

        private ImageIcon[] images;

        public Renderer() {
            images = new ImageIcon[]{
                        /*new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-csv.png")),
                        new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-html.png")),
                        new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-ods.png")),
                        new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-sql.png")),
                        new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-xls.png")),*/
                        new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-xml.png"))
                    };

            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }

        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {

            int selectedIndex = ((Integer) value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setIcon(images[selectedIndex]);
            setFont(list.getFont());
            return this;
        }
    }
}
