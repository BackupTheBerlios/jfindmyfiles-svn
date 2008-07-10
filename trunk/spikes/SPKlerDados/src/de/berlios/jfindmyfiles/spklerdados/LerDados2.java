/*
 * LerDados2.java
 *
 * Created on 24 de Abril de 2008, 19:23
 */
package de.berlios.jfindmyfiles.spklerdados;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author  ei10635
 */
public class LerDados2 extends javax.swing.JFrame {

    File fi;
    /** Creates new form LerDados2 */
    public LerDados2() {
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

    private long iterativeCalc(File f) {
        Stack<File> dados = new Stack<File>();
        File f2;
        long size = 0L;
        dados.push(f);
        while (!dados.empty()) {
            f2 = dados.pop();
            if (f2.isDirectory()) {
                File[] list = f2.listFiles();
                if (list != null) {
                    for (int i = list.length; i-- > 0;) {
                        if (list[i].isDirectory()) {
                            dados.push(list[i]);
                        } else {
                            size += list[i].length();
                        }
                    }
                }
            }
        }
        return size;
    }

    /**
    private long getSize(File f) {
    File inDir[] = f.litFiles();
    if (inDir == null) {
    return f.getSize();
    }
    long size = 0L;
    for (int i = inDir.length; i-- > 0; ) {
    size += getSize(inDir[i]);
     * }
    return size; 
    }
     */
    /**Unusable code, makes little sense.
    private Hashtable<String, File> files;
    private void listAllFilesIntoHash(File f) {
    if (f.isDirectory()) {
    File[] some = f.listFiles();
    for (int i = 0; i < some.length; i++) {
    listAllFilesIntoHash(some[i]);
    }
    }
    files.put(f.getParent(), f);
    }*/
    private long calculateFolderSize(File f) {
        long s = 0L;
        if (f.isFile()) {
            return f.length();
        } else {
            for (File f2 : f.listFiles()) {
                s += calculateFolderSize(f2);
            }
        }
        return s;
    }

    private long calculateFolderSizeListFirst(File f) {
        Vector<File> results = new Vector<File>(100);
        listAllFiles(f, results);
        long size = 0L;
        for (File f2 : results) {
            size += f2.length();
        }
        return size;
    }

    private void listAllFiles(File f, Vector<File> list) {
        if (f.isFile()) {
            list.add(f);
            return;
        }
        for (File f2 : f.listFiles()) {
            listAllFiles(f2, list);
        }
    }

    private String findExtension(File f) {
        int x = -1;
        String name = f.getName();
        return ((x = name.lastIndexOf(".")) > 0 ? name.substring(x + 1) : "");
    }

    private void pesquisar(String origem) {
        File f = new File(origem);
        long totalSize = 0L;
        StringBuffer aux = new StringBuffer(700);
        String tempo;
        long tmp6 = 0L, tmp7 = 0L;
        if (jchkListFirst.isSelected()) {
            long tmp1 = System.currentTimeMillis();
            totalSize = calculateFolderSizeListFirst(f);
            long tmp2 = System.currentTimeMillis();
            tempo = "Tempo 1: " + tmp1 + "/" + tmp2 + ": " + (tmp2 - tmp1);
        } else {
            long tmp1 = System.currentTimeMillis();
            totalSize = calculateFolderSize(f);
            long tmp2 = System.currentTimeMillis();
            tempo = "Tempo 1: " + tmp1 + "/" + tmp2 + ": " + (tmp2 - tmp1);
        }


        aux.append("Nome: " + f.getName() + "\n");
        aux.append("Caminho: " + f.getPath() + "\n");
        aux.append("Caminho absoluto: " + f.getAbsolutePath() + "\n");
        aux.append("Tamanho: " + totalSize + " bytes\n");
        aux.append("Modificado em: " + f.lastModified() + "\n");
        aux.append("--------------------------\n\n");

        if (!jchkSizeOnly.isSelected()) {
            Vector<File> list = new Vector<File>(100);

            tmp6 = System.currentTimeMillis();
            listAllFiles(f, list);
            for (File f2 : list) {
                aux.append("Nome: " + f2.getName() + "\n");
                aux.append("Caminho: " + f2.getPath() + "\n");
                aux.append("Caminho absoluto: " + f2.getAbsolutePath() + "\n");
                if (!f2.isDirectory()) {
                    aux.append("Tamanho: " + f2.length() + " bytes\n");
                    aux.append("Extensão: " + findExtension(f2) + "\n");
                }
                aux.append("Modificado em: " + f2.lastModified() + "\n");
                aux.append("Oculto: " + (f2.isHidden() ? "Sim" : "Não") + "\n");
                aux.append("--------------------------\n\n");
            }
            tmp7 = System.currentTimeMillis();
        }
        aux.append("Tempos:\n");
        aux.append(tempo + "\n");
        aux.append("Tempo 2: " + tmp6 + "-" + tmp7 + ": " + (tmp7 - tmp6) + "\n");

        //Get user info:
        aux.append("Java: " + System.getProperty("java.vendor") + " " + System.getProperty("java.vm.name") + " " + System.getProperty("java.specification.version") + "(" + System.getProperty("java.version") + " " + System.getProperty("java.vm.version") + ")\n");
        aux.append("SO: " + System.getProperty("os.name") + " " + System.getProperty("os.arch") + " " + System.getProperty("os.version") + "\n");
        jtaresults.setText(aux.toString());

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbnSearch = new javax.swing.JButton();
        jbtnClean = new javax.swing.JButton();
        jtbDesktop = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jchkListFirst = new javax.swing.JCheckBox();
        jtfFolder = new javax.swing.JTextField();
        jbtnBrowse = new javax.swing.JButton();
        jchkSizeOnly = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaresults = new javax.swing.JTextArea();
        jbtnClose = new javax.swing.JButton();
        jbtnSomething = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbnSearch.setText("Pesquisar");
        jbnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbnSearchActionPerformed(evt);
            }
        });

        jbtnClean.setText("Limpar");
        jbtnClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCleanActionPerformed(evt);
            }
        });

        jtbDesktop.setText("Home");
        jtbDesktop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbDesktopActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Pasta:");

        jchkListFirst.setText("Listar primeiro");

        jbtnBrowse.setText("...");
        jbtnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBrowseActionPerformed(evt);
            }
        });

        jchkSizeOnly.setText("Só calcular tamanho");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jchkListFirst)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jchkSizeOnly)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jtfFolder, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbtnBrowse))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jbtnBrowse)
                    .add(jtfFolder, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jchkListFirst)
                    .add(jchkSizeOnly)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados:"));

        jtaresults.setColumns(20);
        jtaresults.setRows(5);
        jScrollPane1.setViewportView(jtaresults);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbtnClose.setText("Fechar");
        jbtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCloseActionPerformed(evt);
            }
        });

        jbtnSomething.setText("!");
        jbtnSomething.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSomethingActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jbtnSomething)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 128, Short.MAX_VALUE)
                        .add(jbnSearch)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jtbDesktop)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbtnClean)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jbtnClose))
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jbtnClose)
                    .add(jbtnClean)
                    .add(jtbDesktop)
                    .add(jbnSearch)
                    .add(jbtnSomething))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jbnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbnSearchActionPerformed
        if (!jtfFolder.getText().isEmpty()) {
            pesquisar(jtfFolder.getText());
        }
    }//GEN-LAST:event_jbnSearchActionPerformed

    private void jbtnCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCleanActionPerformed
        jtfFolder.setText("");
        jtaresults.setText("");
    }//GEN-LAST:event_jbtnCleanActionPerformed

    private void jtbDesktopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbDesktopActionPerformed
        jtfFolder.setText(System.getProperty("user.home"));
    }//GEN-LAST:event_jtbDesktopActionPerformed

    private void jbtnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBrowseActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            jtfFolder.setText(jfc.getSelectedFile().getAbsolutePath());
            fi = jfc.getSelectedFile();
        }
    }//GEN-LAST:event_jbtnBrowseActionPerformed

    private void jbtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCloseActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_jbtnCloseActionPerformed

    private void jbtnSomethingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSomethingActionPerformed
        /*JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            //listAllFilesIntoHash(jfc.getSelectedFile());
            jtaresults.setText(String.valueOf(iterativeCalc(jfc.getSelectedFile())));
        }*/
    /*for(File f : files.values()) {
    System.out.println(f.getAbsolutePath());
     * if(9
    }*/
        
        //String mesg = (new File("C:\\").isDirectory()? "Sim" : "nao");
        //JOptionPane.showMessageDialog(this, mesg);
        jtaresults.setText(String.valueOf(iterativeCalc(fi)));
    }//GEN-LAST:event_jbtnSomethingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex1) {
            //ignore
            }
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new LerDados2().setVisible(true);
            }
            });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbnSearch;
    private javax.swing.JButton jbtnBrowse;
    private javax.swing.JButton jbtnClean;
    private javax.swing.JButton jbtnClose;
    private javax.swing.JButton jbtnSomething;
    private javax.swing.JCheckBox jchkListFirst;
    private javax.swing.JCheckBox jchkSizeOnly;
    private javax.swing.JTextArea jtaresults;
    private javax.swing.JButton jtbDesktop;
    private javax.swing.JTextField jtfFolder;
    // End of variables declaration//GEN-END:variables
}
