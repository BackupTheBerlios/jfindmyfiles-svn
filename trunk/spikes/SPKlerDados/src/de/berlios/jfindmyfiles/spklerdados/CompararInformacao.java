/*
 * CompararInformacao.java
 *
 * Created on 19 de Abril de 2008, 15:18
 */

package de.berlios.jfindmyfiles.spklerdados;

/**
 *
 * @author  Knitter
 */
public class CompararInformacao extends javax.swing.JFrame {
    
    /** Creates new form CompararInformacao */
    public CompararInformacao() {
        initComponents();
    }
    
    private void execute() {
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtaStored = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaLive = new javax.swing.JTextArea();
        jtbnClose = new javax.swing.JButton();
        jbtnExecute = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaDiff = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jtaStored.setColumns(20);
        jtaStored.setRows(5);
        jScrollPane1.setViewportView(jtaStored);

        jtaLive.setColumns(20);
        jtaLive.setRows(5);
        jScrollPane2.setViewportView(jtaLive);

        jtbnClose.setText("Fechar");
        jtbnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnCloseActionPerformed(evt);
            }
        });

        jbtnExecute.setText("Executar");
        jbtnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExecuteActionPerformed(evt);
            }
        });

        jtaDiff.setColumns(20);
        jtaDiff.setRows(5);
        jScrollPane3.setViewportView(jtaDiff);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jbtnExecute)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtbnClose))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtbnClose)
                    .addComponent(jbtnExecute))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtbnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnCloseActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_jtbnCloseActionPerformed

    private void jbtnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExecuteActionPerformed
        execute();
    }//GEN-LAST:event_jbtnExecuteActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompararInformacao().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnExecute;
    private javax.swing.JTextArea jtaDiff;
    private javax.swing.JTextArea jtaLive;
    private javax.swing.JTextArea jtaStored;
    private javax.swing.JButton jtbnClose;
    // End of variables declaration//GEN-END:variables
    
}
