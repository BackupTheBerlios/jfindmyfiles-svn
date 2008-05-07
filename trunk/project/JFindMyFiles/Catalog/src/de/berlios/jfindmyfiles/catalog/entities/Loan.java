/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.entities;

import java.util.Date;

/**
 *
 * @author ei10635
 */
public class Loan {
    
    private Long id;
    private Date loaned;
    private Date returned;
    private User loanee;
    private Media media;
    
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getLoaned() {
        return loaned;
    }

    public void setLoaned(Date loaned) {
        this.loaned = loaned;
    }

    public User getLoanee() {
        return loanee;
    }

    public void setLoanee(User loanee) {
        this.loanee = loanee;
    }

    public Media getMedium() {
        return media;
    }

    public void setMedium(Media media) {
        this.media = media;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }
    
    public boolean equals(Object obj) {
        //TODO: implement equals into all objects
        return false;
    }

}
