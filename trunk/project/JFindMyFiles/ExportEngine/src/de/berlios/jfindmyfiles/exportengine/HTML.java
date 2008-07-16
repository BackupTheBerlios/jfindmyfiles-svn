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
package de.berlios.jfindmyfiles.exportengine;

import de.berlios.jfindmyfiles.catalog.CatalogEngine;
import de.berlios.jfindmyfiles.catalog.entities.DiskGroup;
import de.berlios.jfindmyfiles.catalog.entities.FileWrapper;
import de.berlios.jfindmyfiles.catalog.entities.Media;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Knitter
 */
public class HTML extends ExportEngine {

    File destination;
    String name;
    Template template;

    public HTML(File destination, String name, Template template) {
        this.destination = destination;
        this.name = name;
        this.template = template;
    }

    public void export() {
        start();
    }

    @Override
    public void run() {
        int flag_media = 0, flag_diskgroup = 0, flag_file = 0, aux;
        String media = "", diskgroup = "", fileWrapper = "", auxStr, auxStr2, auxStr3;

        FileWriter stream = null;
        try {
            CatalogEngine catalog = Lookup.getDefault().lookup(CatalogEngine.class);
            List<Media> disks = catalog.getDisks();
            List<DiskGroup> diskgroups = catalog.getDiskGroups();
            stream = new FileWriter(destination);
            BufferedWriter out = new BufferedWriter(stream);
            Properties properties = template.getProperties();
            String fileName = properties.getProperty("index");
            if (fileName != null) {
                FileReader fr = new FileReader(template.getFolder() + File.separator + fileName);
                BufferedReader br = new BufferedReader(fr);
                String line, all = "";
                while ((line = br.readLine()) != null) {
                    if (flag_diskgroup == 0) {
                        if ((aux = line.indexOf("«DISKGROUP_START»")) != -1) {
                            flag_diskgroup = 1;
                            diskgroup = diskgroup.concat(line.substring(aux + 17));
                            line = line.substring(0, aux);
                        }
                    } else {
                        if ((aux = line.indexOf("«DISKGROUP_END»")) != -1) {
                            flag_diskgroup = 0;
                            diskgroup = diskgroup.concat(line.substring(0, aux));
                            line = line.substring(aux + 15);
                            for (DiskGroup d : diskgroups) {
                                auxStr = String.valueOf(diskgroup);
                                if ((auxStr2 = d.getName()) != null) {
                                    auxStr = auxStr.replace("«DISKGROUP_NAME»", auxStr2);
                                } else {
                                    auxStr = auxStr.replace("«DISKGROUP_NAME»", "");
                                }
                                if ((auxStr2 = d.getDescription()) != null) {
                                    auxStr = auxStr.replace("«DISKGROUP_DESCRIPTION»", auxStr2);
                                } else {
                                    auxStr = auxStr.replace("«DISKGROUP_DESCRIPTION»", "");
                                }
                                auxStr = auxStr.replace("«DISKGROUP_CAPACITY»", String.valueOf(d.getCapacity()));

                                all = all.concat(auxStr);
                            }
                        } else {
                            diskgroup = diskgroup.concat(line);
                            line = "";
                        }
                    }


                    if (flag_media == 0) {
                        if ((aux = line.indexOf("«MEDIA_START»")) != -1) {
                            flag_media = 1;
                            media = media.concat(line.substring(aux + 13));
                            line = line.substring(0, aux);
                        }
                    } else {
                        if ((aux = line.indexOf("«MEDIA_END»")) != -1) {
                            flag_media = 0;
                            media = media.concat(line.substring(0, aux));
                            line = line.substring(aux + 11);
                            for (Media disk : disks) {
                                auxStr = String.valueOf(media);
                                if ((auxStr2 = disk.getName()) != null) {
                                    auxStr = auxStr.replace("«MEDIA_NAME»", auxStr2);
                                } else {
                                    auxStr = auxStr.replace("«MEDIA_NAME»", "");
                                }
                                if ((auxStr2 = disk.getDescription()) != null) {
                                    auxStr = auxStr.replace("«MEDIA_DESCRIPTION»", auxStr2);
                                } else {
                                    auxStr = auxStr.replace("«MEDIA_DESCRIPTION»", "");
                                }
                                auxStr = auxStr.replace("«MEDIA_CAPACITY»", String.valueOf(disk.getCapacity()));
                                auxStr = auxStr.replace("«MEDIA_FREESPACE»", String.valueOf(disk.getFreeSpace()));
                                auxStr = auxStr.replace("«MEDIA_LASTMODIFIED»", String.valueOf(disk.getLastModified()));
                                if ((auxStr2 = disk.getLocation()) != null) {
                                    auxStr = auxStr.replace("«MEDIA_LOCATION»", disk.getLocation());
                                } else {
                                    auxStr = auxStr.replace("«MEDIA_LOCATION»", "");
                                }

                                all = all.concat(auxStr);

                                /*if (flag_file == 0) {
                                    if ((aux = line.indexOf("«FILE_START»")) != -1) {
                                        flag_file = 1;
                                        fileWrapper = fileWrapper.concat(line.substring(aux + 12));
                                        line = line.substring(0, aux);
                                    }
                                } else {
                                    if ((aux = line.indexOf("«FILE_END»")) != -1) {
                                        flag_file = 0;
                                        fileWrapper = fileWrapper.concat(line.substring(0, aux));
                                        line = line.substring(aux + 10);
                                        for (FileWrapper w : disk.getFileList()) { //lazyinitializationexception
                                            auxStr = String.valueOf(fileWrapper);
                                            if ((auxStr2 = w.getName()) != null) {
                                                auxStr = auxStr.replace("«FILE_NAME»", auxStr2);
                                            } else {
                                                auxStr = auxStr.replace("«FILE_NAME»", "");
                                            }
                                            if ((auxStr2 = w.getDescription()) != null) {
                                                auxStr = auxStr.replace("«FILE_DESCRIPTION»", auxStr2);
                                            } else {
                                                auxStr = auxStr.replace("«FILE_DESCRIPTION»", "");
                                            }
                                            auxStr = auxStr.replace("«FILE_PATH»", String.valueOf(w.getAbsolutePath()));
                                            auxStr = auxStr.replace("«FILE_SIZE»", String.valueOf(w.getSize()));
                                            auxStr = auxStr.replace("«FILE_LASTMODIFIED»", String.valueOf(w.getLastModified()));

                                            all = all.concat(auxStr);
                                        }
                                    } else {
                                        fileWrapper = fileWrapper.concat(line);
                                        line = "";
                                    }
                                }*/
                            }
                        } else {
                            media = media.concat(line);
                            line = "";
                        }
                    }
                    all = all.concat(line + '\n');
                }

                if ((auxStr = catalog.getProperties().getName()) != null) {
                    all = all.replace("«CATALOG_NAME»", catalog.getProperties().getName());
                } else {
                    all = all.replace("«CATALOG_NAME»", "");
                }
                if ((auxStr = catalog.getProperties().getDescription()) != null) {
                    all = all.replace("«CATALOG_DESCRIPTION»", catalog.getProperties().getDescription());
                } else {
                    all = all.replace("«CATALOG_DESCRIPTION»", "");
                }
                all = all.replace("«CATALOG_SIZE»", String.valueOf(catalog.getProperties().getTotalSize()));
                all = all.replace("«CATALOG_DISKNUMBER»", String.valueOf(catalog.getProperties().getDiskNumber()));
                all = all.replace("«CATALOG_FILES»", String.valueOf(catalog.getProperties().getTotalFiles()));
                all = all.replace("«CATALOG_FOLDERS»", String.valueOf(catalog.getProperties().getTotalFolders()));
                all = all.replace("«CATALOG_CREATION»", catalog.getProperties().getCreationDate().toString());
                out.write(all + '\n');
                fr.close();
            }

            out.close();

            fileName = properties.getProperty("style");
            if (fileName != null) {
                System.out.println();
                stream = new FileWriter(destination.getParent() + File.separator + fileName);
                out = new BufferedWriter(stream);
                FileReader fr = new FileReader(template.getFolder() + File.separator + fileName);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    out.write(line);
                }
                out.close();
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
