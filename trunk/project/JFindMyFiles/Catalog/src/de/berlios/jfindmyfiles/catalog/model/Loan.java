/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.model;

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
    private Medium medium;
    
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

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date returned) {
        this.returned = returned;
    }

}
