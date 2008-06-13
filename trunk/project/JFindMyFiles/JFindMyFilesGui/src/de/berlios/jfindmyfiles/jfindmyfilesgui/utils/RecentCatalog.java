/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.jfindmyfilesgui.utils;

import java.io.Serializable;

public class RecentCatalog implements Serializable {
    
    private String name;
    private boolean local;
    private String address;
    private String user;
    private String password;
    private int port;

    public RecentCatalog(String name, boolean local, String address, String user, String password, int port) {
        this.name = name;
        this.local = local;
        this.address = address;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public boolean isLocal() {
        return local;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
