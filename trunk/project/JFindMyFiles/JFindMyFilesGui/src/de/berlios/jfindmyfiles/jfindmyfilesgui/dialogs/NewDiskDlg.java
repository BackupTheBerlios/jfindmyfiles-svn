/*
 * NewDiskDlg.java
 *
 * Created on 5 de Maio de 2008, 15:14
 */

package de.berlios.jfindmyfiles.jfindmyfilesgui.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
/**
 *
 * @author  ei10635
 */
public class NewDiskDlg extends javax.swing.JDialog {
    
    /** Creates new form NewDiskDlg */
    public NewDiskDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
                    toggleDriveAction();//NOTE: will most likely be removed
                }
            });
            jbtngrpDrives.add(toggle);
            jpButtons.add(toggle);
        }

        jpButtons.validate();
    }

    /** Provides the action for the toggle buttons with the drive names.
     * Gets the selected drive from the name of the selected button in the 
     * button group.
     */
    private void toggleDriveAction() {
        //TODO: add suport for choosing the drive
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private String[] listOpenCatalogs() {
        /*List<String> opended = eng.openedCatalogs();
        return opended.toArray(new String[opended.size()]);*/
        //TODO: implement this
        return new String[]{"A", "B"};
    }    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtngrpDrives = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jpButtons = new javax.swing.JPanel();
        createDriveButtons();
        jlblDiskNumber = new javax.swing.JLabel();
        jffDiskNumber = new javax.swing.JFormattedTextField();
        jlblDiskName = new javax.swing.JLabel();
        jtfDiskName = new javax.swing.JTextField();
        jcbxCatalog = new JComboBox(listOpenCatalogs());
        jlblCatalog = new javax.swing.JLabel();
        jpScanningOptions = new javax.swing.JPanel();
        jchkCalculateCRC = new javax.swing.JCheckBox();
        jchkAskDescription = new javax.swing.JCheckBox();
        jchkShowAgain = new javax.swing.JCheckBox();
        jbtnSelectedPlugins = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpButtons.setPreferredSize(new java.awt.Dimension(10, 50));
        jpButtons.setLayout(new javax.swing.BoxLayout(jpButtons, javax.swing.BoxLayout.LINE_AXIS));

        jlblDiskNumber.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jlblDiskNumber.text")); // NOI18N

        jlblDiskName.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jlblDiskName.text")); // NOI18N

        jlblCatalog.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jlblCatalog.text")); // NOI18N

        jpScanningOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Options "));

        jchkCalculateCRC.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jchkCalculateCRC.text")); // NOI18N

        jchkAskDescription.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jchkAskDescription.text")); // NOI18N

        jchkShowAgain.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jchkShowAgain.text")); // NOI18N

        jbtnSelectedPlugins.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jbtnSelectedPlugins.text")); // NOI18N

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
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                    .addComponent(jbtnSelectedPlugins))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnSelectedPlugins)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpScanningOptions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpButtons, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
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
                .addComponent(jpScanningOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton1.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jButton1.text")); // NOI18N

        jButton2.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jButton2.text")); // NOI18N

        jButton3.setText(org.openide.util.NbBundle.getMessage(NewDiskDlg.class, "NewDiskDlg.jButton3.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnSelectedPlugins;
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
    private javax.swing.JTextField jtfDiskName;
    // End of variables declaration//GEN-END:variables
    
}
