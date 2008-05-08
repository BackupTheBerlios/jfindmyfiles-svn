/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.catalog;

import de.berlios.jfindmyfiles.catalog.entities.*;
import de.berlios.jfindmyfiles.catalog.utils.ConnectionManager;
import java.io.File;
import java.util.Set;
import java.util.Stack;
import java.util.zip.CRC32;
import org.hibernate.Session;

/**
 *
 * @author ei10635
 */
public class CatalogEngine {
    
    private static int count = 0;

    public void runtest(boolean store) {
        Session session = ConnectionManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Label l = new Label();
        l.setName("uma label" + ++count);
        session.save(l);

        session.getTransaction().commit();
        ConnectionManager.getSessionFactory().close();
    }
    
    public void addDiskGroup(String name, String description, DiskGroup parent) {      
    }
    
    public void addLabel(String name) {        
    }
    
    public void removeLabel(Label label) {        
    }
    
    public void addUser(String firstname, String surname) {        
    }
    
    public void removeUser(User user) {        
    }
    
    public Set getLabels() {
        return null;
    }
    
    public Set getUsers() {
        return null;
    }
    
    public Set getDiskGroups() {
        return null;
    }
    
    public void readDisk(File file) {
    }
    
    private long calculateFileSize(File f) {
        Stack<File> dados = new Stack<File>();
        File temp;
        long size = 0L;
        dados.push(f);
        while (!dados.empty()) {
            temp = dados.pop();
            if (temp.isDirectory()) {
                File[] list = temp.listFiles();
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
    /*    
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

    }*/    
}
