/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.berlios.jfindmyfiles.jfindmyfilesgui;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import java.io.Serializable;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.Utilities;
/**
 * Top component which displays something.
 */
final class SearchWindowTopComponent extends TopComponent {

    private CatalogEngine eng;
    private Vector<DiskGroup> groups;
    private DefaultListModel listModel;
    private boolean searching;
    private static SearchWindowTopComponent instance;
    private static final String PREFERRED_ID = "SearchWindowTopComponent";

    private SearchWindowTopComponent() {
        eng = Lookup.getDefault().lookup(CatalogEngine.class);
        obtainDiskGroupNames();
        initComponents();
        setName(NbBundle.getMessage(SearchWindowTopComponent.class, "CTL_SearchWindowTopComponent"));
        setToolTipText(NbBundle.getMessage(SearchWindowTopComponent.class, "HINT_SearchWindowTopComponent"));
    //setIcon(Utilities.loadImage("", true));
    }

    @SuppressWarnings("unchecked")
    private void obtainDiskGroupNames() {
        groups = new Vector<DiskGroup>();
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        groups.addAll(s.createQuery("from DiskGroup group order by group.name").list());
        s.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    private void search() {
        listModel.clear();
        boolean inDescription = jchkSearchInDesc.isSelected();
        boolean casein = jchkCaseSensitive.isSelected();
        String sText = jtfSearchText.getText().trim();
        Session s = eng.sessionFactory.getCurrentSession();
        s.beginTransaction();
        Criteria crit = s.createCriteria(FileWrapper.class);

        if (casein && inDescription) {//case sensitive and in description
            crit.add(Restrictions.or(Restrictions.ilike("name", sText, MatchMode.ANYWHERE),
                    Restrictions.ilike("description", sText, MatchMode.ANYWHERE)));
        } else if (casein) { //only case sensitive not in descriptions
            crit.add(Restrictions.ilike("name", sText, MatchMode.ANYWHERE));
        } else if (inDescription) { //in description but not case sensitive
            crit.add(Restrictions.or(Restrictions.like("name", sText, MatchMode.ANYWHERE),
                    Restrictions.like("description", sText, MatchMode.ANYWHERE)));
        } else { //only in name not in descriptions and not case sensitive
            crit.add(Restrictions.like("name", sText, MatchMode.ANYWHERE));
        }

        /*if (jchkUseReGex.isSelected()) {//NOTE: don't really know if this is necessary.
        sText = sText.replaceAll("*", "%");
        }*/

        if (jrdbDiskGroupOnly.isSelected()) {
            DiskGroup dg = (DiskGroup) jcbxDiskGroups.getSelectedItem();
            crit.add(Restrictions.eq("disk.group.id", dg.getId()));
        }

        for (Object o : crit.list()) {
            o.equals(null);
            listModel.addElement(o);
        }
        s.getTransaction().commit();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        jpTopPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        org.openide.awt.Mnemonics.setLocalizedText(jlbText, org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jlbText.text")); // NOI18N

        jtfSearchText.setText(org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jtfSearchText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jbtnSearch, org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jbtnSearch.text")); // NOI18N
        jbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchActionPerformed(evt);
            }
        });

        jpOptionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        org.openide.awt.Mnemonics.setLocalizedText(jchkSearchInDesc, org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jchkSearchInDesc.text")); // NOI18N

        jchkCaseSensitive.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jchkCaseSensitive, org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jchkCaseSensitive.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jchkUseReGex, org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jchkUseReGex.text")); // NOI18N
        jchkUseReGex.setEnabled(false);

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
                .addContainerGap(105, Short.MAX_VALUE))
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

        jpScopePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Scope"));

        jcbxDiskGroups.setEnabled(false);

        jrdbEntireCatalog.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jrdbEntireCatalog, org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jrdbEntireCatalog.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jrdbDiskGroupOnly, org.openide.util.NbBundle.getMessage(SearchWindowTopComponent.class, "SearchWindowTopComponent.jrdbDiskGroupOnly.text")); // NOI18N
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
                        .addComponent(jcbxDiskGroups, 0, 261, Short.MAX_VALUE))
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
                        .addComponent(jtfSearchText, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
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

        jpResults.setBorder(javax.swing.BorderFactory.createTitledBorder("Results"));

        jlstResults.setModel(listModel = new DefaultListModel());
        jscrResults.setViewportView(jlstResults);

        javax.swing.GroupLayout jpResultsLayout = new javax.swing.GroupLayout(jpResults);
        jpResults.setLayout(jpResultsLayout);
        jpResultsLayout.setHorizontalGroup(
            jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jscrResults, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpResultsLayout.setVerticalGroup(
            jpResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResultsLayout.createSequentialGroup()
                .addComponent(jscrResults, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed
    if (!searching) {
        new Thread(new Runnable() {

            public void run() {
                //NOTE: race condition!
                searching = true;
                ProgressHandle p = ProgressHandleFactory.createHandle("searching...");//TODO: i18n
                p.start();
                search();
                p.finish();
                searching = false;
            }
        }).start();
    }
}//GEN-LAST:event_jbtnSearchActionPerformed

private void jrdbDiskGroupOnlyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jrdbDiskGroupOnlyStateChanged
    jcbxDiskGroups.setEnabled(jrdbDiskGroupOnly.isSelected());
}//GEN-LAST:event_jrdbDiskGroupOnlyStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JRadioButton jrdbDiskGroupOnly;
    private javax.swing.JRadioButton jrdbEntireCatalog;
    private javax.swing.JScrollPane jscrResults;
    private javax.swing.JTextField jtfSearchText;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link findInstance}.
     */
    public static synchronized SearchWindowTopComponent getDefault() {
        if (instance == null) {
            instance = new SearchWindowTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the SearchWindowTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized SearchWindowTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(SearchWindowTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof SearchWindowTopComponent) {
            return (SearchWindowTopComponent) win;
        }
        Logger.getLogger(SearchWindowTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        searching = false;
    }

    @Override
    public void componentClosed() {
        searching = false;
    }

    /** replaces this in object stream */
    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    final static class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
            return SearchWindowTopComponent.getDefault();
        }
    }
}
