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
import de.berlios.jfindmyfiles.exportengine.CSV;
import de.berlios.jfindmyfiles.exportengine.ExportListener;
import de.berlios.jfindmyfiles.exportengine.HTML;
import de.berlios.jfindmyfiles.exportengine.ODS;
import de.berlios.jfindmyfiles.exportengine.SQL;
import de.berlios.jfindmyfiles.exportengine.XLS;
import de.berlios.jfindmyfiles.exportengine.XML;
import de.berlios.jfindmyfiles.exportengine.Template;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import org.openide.util.Lookup;
import org.openide.util.Utilities;

/**
 *
 * @author  knitter
 */
public class ExportDlg extends javax.swing.JDialog implements ExportListener {

    private static final Logger logger = Logger.getLogger(ExportDlg.class.getName());
    //private Integer[] values = new Integer[]{0, 1, 2, 3, 4, 5};
    private Integer[] values = new Integer[]{0, 1, 2};
    private File selectedFile;
    private int visiblePanel = 0,  previousPanel = 0;
    private boolean openAfterExport;
    private Vector<Template> temps;

    /** Creates new form ExportDlg */
    public ExportDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        temps = new Vector<Template>();
        try {
            startUp();
        } catch (IOException ex) {
            logger.log(Level.WARNING, "Error reading templates", ex); //TODO: i18n
        }
        initComponents();
        updateDetails();
    }

    private void updateDetails() {
        Template tpl = (Template) jcbxSelectTemplate.getSelectedItem();
        if (tpl != null) {
            jlblPreview.setIcon(new ImageIcon(tpl.getFolder() + File.separator + "preview.jpg"));
            jlblTemplateAuthorValue.setText(tpl.getAuthor());
            jlblVersionValue.setText(tpl.getVersion());
            jlblTypeValue.setText(tpl.getType() == Template.COMPLETE ? "Complete" : "Single");//TODO: i18n);
        }
    }

    private void startUp() throws IOException {
        String base = System.getProperty("user.home") + File.separator +
                ".jfmfuserfiles" + File.separator + "Templates";
        File folder = new File(base);
        File[] list;
        int y;
        Properties p;

        if (folder.isDirectory() && folder.exists()) {
            System.out.println("Folder exists");
            File[] templates = folder.listFiles();
            if (templates != null) {
                for (int z = templates.length; z-- > 0;) {
                    if (templates[z].isDirectory()) {
                        list = templates[z].listFiles();
                        if (list != null) {
                            for (y = list.length; y-- > 0;) {
                                if (list[y].getName().equals(templates[z].getName() + ".properties")) {
                                    p = new Properties();
                                    p.load(new FileReader(list[y]));
                                    temps.add(new Template(p.getProperty("name"),
                                            p.getProperty("author"),
                                            p.getProperty("version"),
                                            p.getProperty("type").equals("single") ? Template.SINGLE : Template.COMPLETE,
                                            templates[z], p));
                                    break;

                                }
                            }
                        }
                    }
                }
            }
        }
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

        jpExportType = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlstExportTypes = new JList(values);
        jpExportOptions = new javax.swing.JPanel();
        jpFileExport = new javax.swing.JPanel();
        jlblDestination = new javax.swing.JLabel();
        jtfDestination = new javax.swing.JTextField();
        jbtnBrowse = new javax.swing.JButton();
        jchkOpenAfter = new javax.swing.JCheckBox();
        jlblInclude = new javax.swing.JLabel();
        jchkIncludeImages = new javax.swing.JCheckBox();
        jchkIncludeAudioC = new javax.swing.JCheckBox();
        jchkIncludeVideoP = new javax.swing.JCheckBox();
        jchkIncludeCustomI = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jpTemplateExport = new javax.swing.JPanel();
        jpPreview = new javax.swing.JPanel();
        jlblPreview = new javax.swing.JLabel();
        jlblTemplateDestination = new javax.swing.JLabel();
        jtfTemplateDestination = new javax.swing.JTextField();
        jbtnTemplateBrowse = new javax.swing.JButton();
        jpTemplateDetails = new javax.swing.JPanel();
        jlblSelectTemplate = new javax.swing.JLabel();
        jcbxSelectTemplate = new JComboBox(temps);
        jsDetailsSeparator = new javax.swing.JSeparator();
        jlblTemplateAuthor = new javax.swing.JLabel();
        jlblTemplateAuthorValue = new javax.swing.JLabel();
        jlblVersion = new javax.swing.JLabel();
        jlblType = new javax.swing.JLabel();
        jlblVersionValue = new javax.swing.JLabel();
        jlblTypeValue = new javax.swing.JLabel();
        jchkTemplateOpenAfter = new javax.swing.JCheckBox();
        jpProgressBar = new javax.swing.JPanel();
        jpbExportProgress = new javax.swing.JProgressBar();
        jbtnCancel = new javax.swing.JButton();
        jbtnExport = new javax.swing.JButton();
        jbtnHelp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.title")); // NOI18N

        jpExportType.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jpExportType.border.title"))); // NOI18N

        jlstExportTypes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlstExportTypes.setCellRenderer(new Renderer());
        jlstExportTypes.setFixedCellHeight(62);
        jlstExportTypes.setVisibleRowCount(6);
        jlstExportTypes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlstExportTypesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlstExportTypes);

        javax.swing.GroupLayout jpExportTypeLayout = new javax.swing.GroupLayout(jpExportType);
        jpExportType.setLayout(jpExportTypeLayout);
        jpExportTypeLayout.setHorizontalGroup(
            jpExportTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExportTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpExportTypeLayout.setVerticalGroup(
            jpExportTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExportTypeLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpExportOptions.setLayout(new java.awt.CardLayout());

        jpFileExport.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jpFileExport.border.title"))); // NOI18N

        jlblDestination.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblDestination.text")); // NOI18N

        jtfDestination.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jtfDestination.text")); // NOI18N

        jbtnBrowse.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jbtnBrowse.text")); // NOI18N
        jbtnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBrowseActionPerformed(evt);
            }
        });

        jchkOpenAfter.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jchkOpenAfter.text")); // NOI18N
        jchkOpenAfter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jchkOpenAfterStateChanged(evt);
            }
        });

        jlblInclude.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblInclude.text")); // NOI18N

        jchkIncludeImages.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jchkIncludeImages.text")); // NOI18N

        jchkIncludeAudioC.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jchkIncludeAudioC.text")); // NOI18N

        jchkIncludeVideoP.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jchkIncludeVideoP.text")); // NOI18N

        jchkIncludeCustomI.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jchkIncludeCustomI.text")); // NOI18N

        javax.swing.GroupLayout jpFileExportLayout = new javax.swing.GroupLayout(jpFileExport);
        jpFileExport.setLayout(jpFileExportLayout);
        jpFileExportLayout.setHorizontalGroup(
            jpFileExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFileExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFileExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jchkOpenAfter)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(jpFileExportLayout.createSequentialGroup()
                        .addComponent(jlblInclude)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                    .addGroup(jpFileExportLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpFileExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchkIncludeImages)
                            .addComponent(jchkIncludeAudioC)
                            .addComponent(jchkIncludeVideoP)
                            .addComponent(jchkIncludeCustomI))
                        .addGap(4, 4, 4))
                    .addGroup(jpFileExportLayout.createSequentialGroup()
                        .addComponent(jlblDestination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfDestination, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBrowse)))
                .addContainerGap())
        );
        jpFileExportLayout.setVerticalGroup(
            jpFileExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFileExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFileExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblDestination)
                    .addComponent(jbtnBrowse)
                    .addComponent(jtfDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFileExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlblInclude)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkIncludeAudioC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkIncludeImages)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkIncludeVideoP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkIncludeCustomI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkOpenAfter)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jpExportOptions.add(jpFileExport, "FileExport");

        jpTemplateExport.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jpTemplateExport.border.title"))); // NOI18N

        jpPreview.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jpPreview.border.title"))); // NOI18N

        jlblPreview.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblPreview.text")); // NOI18N
        jlblPreview.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpPreviewLayout = new javax.swing.GroupLayout(jpPreview);
        jpPreview.setLayout(jpPreviewLayout);
        jpPreviewLayout.setHorizontalGroup(
            jpPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
        );
        jpPreviewLayout.setVerticalGroup(
            jpPreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblPreview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        );

        jlblTemplateDestination.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblTemplateDestination.text")); // NOI18N

        jtfTemplateDestination.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jtfTemplateDestination.text")); // NOI18N

        jbtnTemplateBrowse.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jbtnTemplateBrowse.text")); // NOI18N
        jbtnTemplateBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTemplateBrowseActionPerformed(evt);
            }
        });

        jlblSelectTemplate.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblSelectTemplate.text")); // NOI18N

        jcbxSelectTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxSelectTemplateActionPerformed(evt);
            }
        });

        jlblTemplateAuthor.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblTemplateAuthor.text")); // NOI18N

        jlblTemplateAuthorValue.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblTemplateAuthorValue.text")); // NOI18N

        jlblVersion.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblVersion.text")); // NOI18N

        jlblType.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblType.text")); // NOI18N

        jlblVersionValue.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblVersionValue.text")); // NOI18N

        jlblTypeValue.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jlblTypeValue.text")); // NOI18N

        javax.swing.GroupLayout jpTemplateDetailsLayout = new javax.swing.GroupLayout(jpTemplateDetails);
        jpTemplateDetails.setLayout(jpTemplateDetailsLayout);
        jpTemplateDetailsLayout.setHorizontalGroup(
            jpTemplateDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTemplateDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTemplateDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsDetailsSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addGroup(jpTemplateDetailsLayout.createSequentialGroup()
                        .addComponent(jlblSelectTemplate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxSelectTemplate, 0, 136, Short.MAX_VALUE))
                    .addGroup(jpTemplateDetailsLayout.createSequentialGroup()
                        .addComponent(jlblTemplateAuthor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblTemplateAuthorValue, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                    .addGroup(jpTemplateDetailsLayout.createSequentialGroup()
                        .addComponent(jlblVersion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblVersionValue))
                    .addGroup(jpTemplateDetailsLayout.createSequentialGroup()
                        .addComponent(jlblType)
                        .addGap(18, 18, 18)
                        .addComponent(jlblTypeValue)))
                .addContainerGap())
        );
        jpTemplateDetailsLayout.setVerticalGroup(
            jpTemplateDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTemplateDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTemplateDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblSelectTemplate)
                    .addComponent(jcbxSelectTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsDetailsSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTemplateDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblTemplateAuthor)
                    .addComponent(jlblTemplateAuthorValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTemplateDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblVersion)
                    .addComponent(jlblVersionValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTemplateDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblType)
                    .addComponent(jlblTypeValue))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jchkTemplateOpenAfter.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jchkTemplateOpenAfter.text")); // NOI18N
        jchkTemplateOpenAfter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jchkTemplateOpenAfterStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpTemplateExportLayout = new javax.swing.GroupLayout(jpTemplateExport);
        jpTemplateExport.setLayout(jpTemplateExportLayout);
        jpTemplateExportLayout.setHorizontalGroup(
            jpTemplateExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTemplateExportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTemplateExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTemplateExportLayout.createSequentialGroup()
                        .addComponent(jpPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpTemplateDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpTemplateExportLayout.createSequentialGroup()
                        .addComponent(jlblTemplateDestination)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpTemplateExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchkTemplateOpenAfter)
                            .addGroup(jpTemplateExportLayout.createSequentialGroup()
                                .addComponent(jtfTemplateDestination, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnTemplateBrowse)))))
                .addContainerGap())
        );
        jpTemplateExportLayout.setVerticalGroup(
            jpTemplateExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTemplateExportLayout.createSequentialGroup()
                .addGroup(jpTemplateExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpTemplateExportLayout.createSequentialGroup()
                        .addComponent(jpPreview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpTemplateExportLayout.createSequentialGroup()
                        .addComponent(jpTemplateDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jpTemplateExportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblTemplateDestination)
                    .addComponent(jbtnTemplateBrowse)
                    .addComponent(jtfTemplateDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jchkTemplateOpenAfter)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jpExportOptions.add(jpTemplateExport, "TemplateExport");

        jpbExportProgress.setIndeterminate(true);
        jpbExportProgress.setString(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jpbExportProgress.string")); // NOI18N
        jpbExportProgress.setStringPainted(true);

        javax.swing.GroupLayout jpProgressBarLayout = new javax.swing.GroupLayout(jpProgressBar);
        jpProgressBar.setLayout(jpProgressBarLayout);
        jpProgressBarLayout.setHorizontalGroup(
            jpProgressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProgressBarLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jpbExportProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jpProgressBarLayout.setVerticalGroup(
            jpProgressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProgressBarLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jpbExportProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jpExportOptions.add(jpProgressBar, "ProgressBar");

        jbtnCancel.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jbtnCancel.text")); // NOI18N
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });

        jbtnExport.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jbtnExport.text")); // NOI18N
        jbtnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnExportActionPerformed(evt);
            }
        });

        jbtnHelp.setText(org.openide.util.NbBundle.getMessage(ExportDlg.class, "ExportDlg.jbtnHelp.text")); // NOI18N
        jbtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpExportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpExportOptions, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtnExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnHelp)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jbtnCancel, jbtnExport, jbtnHelp});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpExportOptions, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(jpExportType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnHelp)
                    .addComponent(jbtnCancel)
                    .addComponent(jbtnExport))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jlstExportTypesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlstExportTypesValueChanged
    if (jlstExportTypes.getSelectedIndex() == 1) {
        CardLayout lay = (CardLayout) jpExportOptions.getLayout();
        if (visiblePanel == 0) {
            lay.next(jpExportOptions);
        } else if (visiblePanel == 2) {
            lay.previous(jpExportOptions);
        }
        visiblePanel = previousPanel = 1;
    } else {
        CardLayout lay = (CardLayout) jpExportOptions.getLayout();
        lay.first(jpExportOptions);
        visiblePanel = previousPanel = 0;
    }
}//GEN-LAST:event_jlstExportTypesValueChanged

private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
    dispose();
}//GEN-LAST:event_jbtnCancelActionPerformed

private void jbtnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnExportActionPerformed
    //csv html sql ods xls xml
    switch (jlstExportTypes.getSelectedIndex()) {
        case 0://CSV
            CSV cvs = new CSV(selectedFile);
            cvs.addListener(this);
            cvs.export();
            dispose();
            break;
        case 1://HTML
            HTML html = new HTML(selectedFile,
                    Lookup.getDefault().lookup(CatalogEngine.class).getProperties().getName(),
                    (Template) jcbxSelectTemplate.getSelectedItem());
            html.addListener(this);
            html.export();
            dispose();
            break;
        case 2://SQL
            SQL sql = new SQL(selectedFile);
            sql.addListener(this);
            sql.export();
            dispose();
            break;
        case 3://ODS
            ODS ods = new ODS(selectedFile);
            ods.addListener(this);
            ods.export();
            dispose();
            break;
        case 4://XLS
            XLS xls = new XLS(selectedFile);
            xls.addListener(this);
            xls.export();
            dispose();
            break;
        case 5://XML
            XML xml = new XML(selectedFile);
            xml.addListener(this);
            xml.export();
            dispose();
    }
}//GEN-LAST:event_jbtnExportActionPerformed

private void jbtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnHelpActionPerformed
// TODO: help actions
}//GEN-LAST:event_jbtnHelpActionPerformed

private void jbtnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBrowseActionPerformed
    JFileChooser jfc = new JFileChooser();
    if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        selectedFile = jfc.getSelectedFile();
        jtfDestination.setText(selectedFile.getAbsolutePath().trim());
    }
}//GEN-LAST:event_jbtnBrowseActionPerformed

private void jbtnTemplateBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTemplateBrowseActionPerformed
    JFileChooser jfc = new JFileChooser();
    if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        selectedFile = jfc.getSelectedFile();
        jtfTemplateDestination.setText(selectedFile.getAbsolutePath().trim());
    }
}//GEN-LAST:event_jbtnTemplateBrowseActionPerformed

private void jchkTemplateOpenAfterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jchkTemplateOpenAfterStateChanged
    openAfterExport = true;
}//GEN-LAST:event_jchkTemplateOpenAfterStateChanged

private void jcbxSelectTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxSelectTemplateActionPerformed
    updateDetails();
}//GEN-LAST:event_jcbxSelectTemplateActionPerformed

private void jchkOpenAfterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jchkOpenAfterStateChanged
    openAfterExport = jchkOpenAfter.isSelected();
}//GEN-LAST:event_jchkOpenAfterStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnBrowse;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnExport;
    private javax.swing.JButton jbtnHelp;
    private javax.swing.JButton jbtnTemplateBrowse;
    private javax.swing.JComboBox jcbxSelectTemplate;
    private javax.swing.JCheckBox jchkIncludeAudioC;
    private javax.swing.JCheckBox jchkIncludeCustomI;
    private javax.swing.JCheckBox jchkIncludeImages;
    private javax.swing.JCheckBox jchkIncludeVideoP;
    private javax.swing.JCheckBox jchkOpenAfter;
    private javax.swing.JCheckBox jchkTemplateOpenAfter;
    private javax.swing.JLabel jlblDestination;
    private javax.swing.JLabel jlblInclude;
    private javax.swing.JLabel jlblPreview;
    private javax.swing.JLabel jlblSelectTemplate;
    private javax.swing.JLabel jlblTemplateAuthor;
    private javax.swing.JLabel jlblTemplateAuthorValue;
    private javax.swing.JLabel jlblTemplateDestination;
    private javax.swing.JLabel jlblType;
    private javax.swing.JLabel jlblTypeValue;
    private javax.swing.JLabel jlblVersion;
    private javax.swing.JLabel jlblVersionValue;
    private javax.swing.JList jlstExportTypes;
    private javax.swing.JPanel jpExportOptions;
    private javax.swing.JPanel jpExportType;
    private javax.swing.JPanel jpFileExport;
    private javax.swing.JPanel jpPreview;
    private javax.swing.JPanel jpProgressBar;
    private javax.swing.JPanel jpTemplateDetails;
    private javax.swing.JPanel jpTemplateExport;
    private javax.swing.JProgressBar jpbExportProgress;
    private javax.swing.JSeparator jsDetailsSeparator;
    private javax.swing.JTextField jtfDestination;
    private javax.swing.JTextField jtfTemplateDestination;
    // End of variables declaration//GEN-END:variables
    public void exportStarted() {
        visiblePanel = 2;
        CardLayout lay = (CardLayout) jpExportOptions.getLayout();
        lay.last(jpExportOptions);
    }

    public void exportFinished() {
        if (previousPanel == 0) {
            CardLayout lay = (CardLayout) jpExportOptions.getLayout();
            lay.first(jpExportOptions);
        } else {
            CardLayout lay = (CardLayout) jpExportOptions.getLayout();
            lay.previous(jpExportOptions);
        }
        visiblePanel = previousPanel;
        if (openAfterExport) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(selectedFile);
                } catch (IOException ex) {
                    logger.log(Level.FINE, "Desktop not supported", ex);
                }
            }
            openAfterExport = false;
        }
    }

    public void exportError() {
        //TODO: i18n
        JOptionPane.showMessageDialog(this, "", "", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Renderer class for showing icons on the list of existing export options
     */
    private class Renderer extends JLabel implements ListCellRenderer {

        private ImageIcon[] images;

        public Renderer() {
            images = new ImageIcon[]{
                        new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-csv.png")),
                        new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-html.png")),
                        /*new ImageIcon(Utilities.loadImage("de/berlios/jfindmyfiles/jfindmyfilesgui/resources/icons/importexport/export-import-ods.png")),
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
