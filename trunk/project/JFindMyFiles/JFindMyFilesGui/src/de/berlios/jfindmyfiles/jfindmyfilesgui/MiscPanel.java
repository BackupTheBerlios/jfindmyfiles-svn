/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.NbPreferences;

final class MiscPanel extends javax.swing.JPanel {

    private final MiscOptionsPanelController controller;

    MiscPanel(final MiscOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        jtfEmail.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                controller.changed();
            }

            public void removeUpdate(DocumentEvent e) {
                controller.changed();
            }

            public void changedUpdate(DocumentEvent e) {
                controller.changed();
            }
        });

        jtfUserName.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                controller.changed();
            }

            public void removeUpdate(DocumentEvent e) {
                controller.changed();
            }

            public void changedUpdate(DocumentEvent e) {
                controller.changed();
            }
        });

        jtfWebAddress.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                controller.changed();
            }

            public void removeUpdate(DocumentEvent e) {
                controller.changed();
            }

            public void changedUpdate(DocumentEvent e) {
                controller.changed();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblUserDataTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jtfUserName = new javax.swing.JTextField();
        jlblUserName = new javax.swing.JLabel();
        jlblEmail = new javax.swing.JLabel();
        jlblWebAddress = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jtfWebAddress = new javax.swing.JTextField();
        jlblBehaviorTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jchkConfirmExit = new javax.swing.JCheckBox();
        jlblRememberedCatalogs = new javax.swing.JLabel();
        jspRememberCatalogs = new javax.swing.JSpinner();
        jlblDisabeRememberCatalogsTip = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jlblUserDataTitle, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jlblUserDataTitle.text")); // NOI18N

        jtfUserName.setText(org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jtfUserName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jlblUserName, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jlblUserName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jlblEmail, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jlblEmail.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jlblWebAddress, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jlblWebAddress.text")); // NOI18N

        jtfEmail.setText(org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jtfEmail.text")); // NOI18N

        jtfWebAddress.setText(org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jtfWebAddress.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jlblBehaviorTitle, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jlblBehaviorTitle.text")); // NOI18N

        jchkConfirmExit.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jchkConfirmExit, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jchkConfirmExit.text")); // NOI18N
        jchkConfirmExit.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jchkConfirmExitStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jlblRememberedCatalogs, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jlblRememberedCatalogs.text")); // NOI18N
        jlblRememberedCatalogs.setEnabled(false);

        jspRememberCatalogs.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jspRememberCatalogs.setEnabled(false);
        jspRememberCatalogs.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspRememberCatalogsStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jlblDisabeRememberCatalogsTip, org.openide.util.NbBundle.getMessage(MiscPanel.class, "MiscPanel.jlblDisabeRememberCatalogsTip.text")); // NOI18N
        jlblDisabeRememberCatalogsTip.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jchkConfirmExit))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblUserDataTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblEmail)
                                            .addComponent(jlblWebAddress)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jlblUserName)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                    .addComponent(jtfWebAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                    .addComponent(jtfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblBehaviorTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jlblRememberedCatalogs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspRememberCatalogs, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblDisabeRememberCatalogsTip)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlblUserDataTitle)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblUserName)
                    .addComponent(jtfUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblEmail)
                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblWebAddress)
                    .addComponent(jtfWebAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlblBehaviorTitle)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkConfirmExit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblRememberedCatalogs)
                    .addComponent(jspRememberCatalogs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblDisabeRememberCatalogsTip))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jchkConfirmExitStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jchkConfirmExitStateChanged
    controller.changed();
}//GEN-LAST:event_jchkConfirmExitStateChanged

private void jspRememberCatalogsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspRememberCatalogsStateChanged
    controller.changed();
}//GEN-LAST:event_jspRememberCatalogsStateChanged

    void load() {
        jtfUserName.setText(NbPreferences.forModule(MiscPanel.class).get("name", ""));
        jtfEmail.setText(NbPreferences.forModule(MiscPanel.class).get("email", ""));
        jtfWebAddress.setText(NbPreferences.forModule(MiscPanel.class).get("web", ""));
        jchkConfirmExit.setSelected(NbPreferences.forModule(MiscPanel.class).getBoolean("confirmexit", true));
        jspRememberCatalogs.setValue(NbPreferences.forModule(MiscPanel.class).getInt("remembered", 3));
    }

    void store() {
        NbPreferences.forModule(MiscPanel.class).put("name", jtfUserName.getText().trim());
        NbPreferences.forModule(MiscPanel.class).put("email", jtfEmail.getText().trim());
        NbPreferences.forModule(MiscPanel.class).put("web", jtfWebAddress.getText().trim());
        NbPreferences.forModule(MiscPanel.class).putBoolean("confirmexit", jchkConfirmExit.isSelected());
        NbPreferences.forModule(MiscPanel.class).putInt("remembered", ((Integer)jspRememberCatalogs.getValue()).intValue());
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JCheckBox jchkConfirmExit;
    private javax.swing.JLabel jlblBehaviorTitle;
    private javax.swing.JLabel jlblDisabeRememberCatalogsTip;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblRememberedCatalogs;
    private javax.swing.JLabel jlblUserDataTitle;
    private javax.swing.JLabel jlblUserName;
    private javax.swing.JLabel jlblWebAddress;
    private javax.swing.JSpinner jspRememberCatalogs;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfUserName;
    private javax.swing.JTextField jtfWebAddress;
    // End of variables declaration//GEN-END:variables
}
