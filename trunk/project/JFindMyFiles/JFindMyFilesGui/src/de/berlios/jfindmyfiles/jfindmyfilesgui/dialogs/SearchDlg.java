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
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import org.hibernate.Session;
import org.openide.util.Lookup;

/**
 *
 * @author  knitter
 */
public class SearchDlg extends javax.swing.JDialog {

    private CatalogEngine eng;
    private Vector<DiskGroup> groups;
    
    /** Creates new form SearchDlg */
    public SearchDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        populateDiskGroupBox();
        initComponents();
        jpbProgress.setVisible(false);
    }
    
    /** Creates a new form SeachDlg and changes the selected item in the 
     * combobox that list disk groups to match the given group.
     *
     * @param parent
     * @param modal
     * @param group the group in which the search is to take place
     */
    public SearchDlg(java.awt.Frame parent, boolean modal, DiskGroup group) {
        this(parent, modal);
        jrdbDiskGroupOnly.setSelected(true);
        jcbxDiskGroups.setSelectedItem(group);
    }
    
    public void showCentered() {
        setLocation(getParent().getX() + (getParent().getWidth() / 2) - (getWidth() / 2),
                getParent().getY() + (getParent().getHeight() / 2) - (getHeight() / 2));
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    private void populateDiskGroupBox() {
        groups = new Vector<DiskGroup>();
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        groups.addAll(s.createQuery("from DiskGroup group order by group.name").list());
        s.getTransaction().commit();
    }
    
    @SuppressWarnings("unchecked")
    private void search() {
        boolean inDescriptions = jchkSearchInDesc.isSelected();
        boolean caseSensitive = jchkCaseSensitive.isSelected();
        boolean useRegular = jchkUseReGex.isSelected();
        boolean scopeAll = jrdbEntireCatalog.isSelected();
        List l = new LinkedList();
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        l.addAll(s.createQuery("from FileWrapper file where file.name like %?%").list());
        s.getTransaction().commit();
        for(Object o : l) {
            ((DefaultListModel)jlstResults.getModel()).addElement(o);
        }
        jpbProgress.setVisible(false);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrpScope = new javax.swing.ButtonGroup();
        jpTopPanel = new javax.swing.JPanel();
        jlbText = new javax.swing.JLabel();
        jtfSearchText = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        jpOptionsPanel = new javax.swing.JPanel();
        jchkSearchInDesc = new javax.swing.JCheckBox();
        jchkCaseSensitive = new javax.swing.JCheckBox();
        jchkUseReGex = new javax.swing.JCheckBox();
        jpScopePanel = new javax.swing.JPanel();
        jcbxDiskGroups = new JComboBox(groups);
        jrdbEntireCatalog = new javax.swing.JRadioButton();
        jrdbDiskGroupOnly = new javax.swing.JRadioButton();
        jpResults = new javax.swing.JPanel();
        jscrResults = new javax.swing.JScrollPane();
        jlstResults = new javax.swing.JList();
        jbtnClose = new javax.swing.JButton();
        jpbProgress = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jpTopPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jpTopPanel.border.title"))); // NOI18N

        jlbText.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jlbText.text")); // NOI18N

        jtfSearchText.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jtfSearchText.text")); // NOI18N

        jbtnSearch.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jbtnSearch.text")); // NOI18N
        jbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchActionPerformed(evt);
            }
        });

        jpOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jpOptionsPanel.border.title"))); // NOI18N

        jchkSearchInDesc.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jchkSearchInDesc.text")); // NOI18N

        jchkCaseSensitive.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jchkCaseSensitive.text")); // NOI18N

        jchkUseReGex.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jchkUseReGex.text")); // NOI18N

        javax.swing.GroupLayout jpOptionsPanelLayout = new javax.swing.GroupLayout(jpOptionsPanel);
        jpOptionsPanel.setLayout(jpOptionsPanelLayout);
        jpOptionsPanelLayout.setHorizontalGroup(
            jpOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jchkSearchInDesc)
                    .addComponent(jchkCaseSensitive)
                    .addComponent(jchkUseReGex))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jpOptionsPanelLayout.setVerticalGroup(
            jpOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOptionsPanelLayout.createSequentialGroup()
                .addComponent(jchkSearchInDesc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkCaseSensitive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkUseReGex)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jpScopePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jpScopePanel.border.title"))); // NOI18N

        jcbxDiskGroups.setEnabled(false);

        btngrpScope.add(jrdbEntireCatalog);
        jrdbEntireCatalog.setSelected(true);
        jrdbEntireCatalog.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jrdbEntireCatalog.text")); // NOI18N

        btngrpScope.add(jrdbDiskGroupOnly);
        jrdbDiskGroupOnly.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jrdbDiskGroupOnly.text")); // NOI18N
        jrdbDiskGroupOnly.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jrdbDiskGroupOnlyStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpScopePanelLayout = new javax.swing.GroupLayout(jpScopePanel);
        jpScopePanel.setLayout(jpScopePanelLayout);
        jpScopePanelLayout.setHorizontalGroup(
            jpScopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpScopePanelLayout.createSequentialGroup()
                .addGroup(jpScopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpScopePanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jcbxDiskGroups, 0, 218, Short.MAX_VALUE))
                    .addGroup(jpScopePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jrdbEntireCatalog))
                    .addGroup(jpScopePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jrdbDiskGroupOnly)))
                .addContainerGap())
        );
        jpScopePanelLayout.setVerticalGroup(
            jpScopePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpScopePanelLayout.createSequentialGroup()
                .addComponent(jrdbEntireCatalog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrdbDiskGroupOnly)
                .addGap(6, 6, 6)
                .addComponent(jcbxDiskGroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpTopPanelLayout = new javax.swing.GroupLayout(jpTopPanel);
        jpTopPanel.setLayout(jpTopPanelLayout);
        jpTopPanelLayout.setHorizontalGroup(
            jpTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTopPanelLayout.createSequentialGroup()
                        .addComponent(jlbText)
                        .addGap(18, 18, 18)
                        .addComponent(jtfSearchText, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnSearch))
                    .addGroup(jpTopPanelLayout.createSequentialGroup()
                        .addComponent(jpOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpScopePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpTopPanelLayout.setVerticalGroup(
            jpTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTopPanelLayout.createSequentialGroup()
                .addGroup(jpTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSearch)
                    .addComponent(jtfSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbText))
                .addGap(11, 11, 11)
                .addGroup(jpTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpOptionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpScopePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpTopPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jpOptionsPanel, jpScopePanel});

        jpResults.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jpResults.border.title"))); // NOI18N

        jscrResults.setViewportView(jlstResults);

        javax.swing.GroupLayout jpResultsLayout = new javax.swing.GroupLayout(jpResults);
        jpResults.setLayout(jpResultsLayout);
        jpResultsLayout.setHorizontalGroup(
            jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jscrResults, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpResultsLayout.setVerticalGroup(
            jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultsLayout.createSequentialGroup()
                .addComponent(jscrResults, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbtnClose.setText(org.openide.util.NbBundle.getMessage(SearchDlg.class, "SearchDlg.jbtnClose.text")); // NOI18N
        jbtnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCloseActionPerformed(evt);
            }
        });

        jpbProgress.setValue(50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jpbProgress, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                        .addGap(203, 203, 203)
                        .addComponent(jbtnClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnClose)
                    .addComponent(jpbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jrdbDiskGroupOnlyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jrdbDiskGroupOnlyStateChanged
    jcbxDiskGroups.setEnabled(jrdbDiskGroupOnly.isSelected());
}//GEN-LAST:event_jrdbDiskGroupOnlyStateChanged

private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed
    jpbProgress.setVisible(true);
    new Thread(new Runnable() {

            public void run() {
                search();
            }
        }).start();
}//GEN-LAST:event_jbtnSearchActionPerformed

private void jbtnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCloseActionPerformed
    dispose();
}//GEN-LAST:event_jbtnCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngrpScope;
    private javax.swing.JButton jbtnClose;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox jcbxDiskGroups;
    private javax.swing.JCheckBox jchkCaseSensitive;
    private javax.swing.JCheckBox jchkSearchInDesc;
    private javax.swing.JCheckBox jchkUseReGex;
    private javax.swing.JLabel jlbText;
    private javax.swing.JList jlstResults;
    private javax.swing.JPanel jpOptionsPanel;
    private javax.swing.JPanel jpResults;
    private javax.swing.JPanel jpScopePanel;
    private javax.swing.JPanel jpTopPanel;
    private javax.swing.JProgressBar jpbProgress;
    private javax.swing.JRadioButton jrdbDiskGroupOnly;
    private javax.swing.JRadioButton jrdbEntireCatalog;
    private javax.swing.JScrollPane jscrResults;
    private javax.swing.JTextField jtfSearchText;
    // End of variables declaration//GEN-END:variables

}
