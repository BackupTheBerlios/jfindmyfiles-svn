/*
 * LerDados.java
 *
 * Created on 16 de Abril de 2008, 14:13
 */
package de.berlios.jfindmyfiles.spklerdados;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author  ei10635
 */
public class LerDados extends javax.swing.JFrame {

    /** Creates new form LerDados */
    public LerDados() {
        initComponents();
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension mySize = getSize();

        if (mySize.height > scrSize.height) {
            mySize.height = scrSize.height;
        }

        if (mySize.width > scrSize.width) {
            mySize.width = scrSize.width;
        }

        setLocation((scrSize.width - mySize.width) / 2,
                (scrSize.height - mySize.height) / 2);
    }
       
    private long calculateSize(File f) {
        long s = 0L;
        if(f.isFile()) {
            return f.length();
        } else {
            for(File f2 : f.listFiles()) 
                s +=calculateSize(f2);
        }
        return s;
    } 
    
    private String findExtension(File f) {
        String name = f.getName();
        int x;
        return ((x = name.lastIndexOf(".")) > 0 ? name.substring(x + 1) : "");
    }

    private String[] pesquisar(String origem) {
        StringBuffer aux = new StringBuffer();
        File f = new File(origem);
        aux.append("Nome: " + f.getName() + "\n");
        aux.append("Caminho: " + f.getPath() + "\n");
        aux.append("Caminho absoluto: " + f.getAbsolutePath() + "\n");
        aux.append("Tamanho: " + calculateSize(f) + " bytes\n");
        aux.append("Modificado em: " + f.lastModified() + "\n");
        aux.append("Oculto: " + (f.isHidden() ? "Sim" : "Não") + "\n");
        aux.append("Pasta: " + (f.isDirectory() ? "Sim" : "Não") + "\n");
        String origemDetails = aux.toString();
        
        File[] list = f.listFiles();
        aux = new StringBuffer(list.length * 8);
        for (File r : list) {
            aux.append("Nome: " + r.getName() + "\n");
            aux.append("Caminho: " + r.getPath() + "\n");
            aux.append("Caminho absoluto: " + r.getAbsolutePath() + "\n");
            aux.append("Extensão: " + findExtension(r) + "\n");
            aux.append("Tamanho: " + calculateSize(r) + " bytes\n");
            aux.append("Modificado em: " + r.lastModified() + "\n");
            aux.append("Oculto: " + (r.isHidden() ? "Sim" : "Não") + "\n");
            aux.append("Pasta: " + (r.isDirectory() ? "Sim" : "Não") + "\n");
            aux.append("\n");
        }

        return new String[]{origemDetails, aux.toString()};
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jtfOrigem = new javax.swing.JTextField();
        jtbBrowse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDetalhesOrigem = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaDetalhesConteudo = new javax.swing.JTextArea();
        jbtClose = new javax.swing.JButton();
        jtbnLimpar = new javax.swing.JButton();
        jbtPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Origem:");

        jtbBrowse.setText("...");
        jtbBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbBrowseActionPerformed(evt);
            }
        });

        jtaDetalhesOrigem.setColumns(20);
        jtaDetalhesOrigem.setEditable(false);
        jtaDetalhesOrigem.setRows(5);
        jScrollPane1.setViewportView(jtaDetalhesOrigem);

        jLabel2.setText("Detalhes da origem:");

        jLabel3.setText("Detalhes do conteúdo:");

        jtaDetalhesConteudo.setColumns(20);
        jtaDetalhesConteudo.setEditable(false);
        jtaDetalhesConteudo.setRows(5);
        jScrollPane2.setViewportView(jtaDetalhesConteudo);

        jbtClose.setText("Fechar");
        jbtClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCloseActionPerformed(evt);
            }
        });

        jtbnLimpar.setText("Limpar");
        jtbnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbnLimparActionPerformed(evt);
            }
        });

        jbtPesquisar.setText("Pesquisar");
        jbtPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfOrigem, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtbBrowse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtPesquisar))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtbnLimpar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtClose))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jbtPesquisar)
                    .addComponent(jtbBrowse)
                    .addComponent(jtfOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtClose)
                    .addComponent(jtbnLimpar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jtbBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbBrowseActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jtfOrigem.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_jtbBrowseActionPerformed

    private void jbtPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPesquisarActionPerformed
        if (!jtfOrigem.getText().isEmpty()) {
            String[] results = pesquisar(jtfOrigem.getText());
            jtaDetalhesOrigem.setText(results[0]);
            jtaDetalhesConteudo.setText(results[1]);
        }
    }//GEN-LAST:event_jbtPesquisarActionPerformed

    private void jbtCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCloseActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_jbtCloseActionPerformed

    private void jtbnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbnLimparActionPerformed
        jtfOrigem.setText("");
        jtaDetalhesConteudo.setText("");
        jtaDetalhesOrigem.setText("");
    }//GEN-LAST:event_jtbnLimparActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LerDados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LerDados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LerDados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LerDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LerDados().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtClose;
    private javax.swing.JButton jbtPesquisar;
    private javax.swing.JTextArea jtaDetalhesConteudo;
    private javax.swing.JTextArea jtaDetalhesOrigem;
    private javax.swing.JButton jtbBrowse;
    private javax.swing.JButton jtbnLimpar;
    private javax.swing.JTextField jtfOrigem;
    // End of variables declaration//GEN-END:variables
}
