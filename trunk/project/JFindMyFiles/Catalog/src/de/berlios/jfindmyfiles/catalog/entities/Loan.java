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

    /**
     * Equals method doesn't take into account the id of the object. Hibernate 
     * only gives an id to a new object when that object is persisted, it is 
     * therefor not possible to use the id attribute.
     * 
     * A <em>Bussines Key</em> is used instead.
     * @param obj the object to compare
     * @return true if both objects are equal under the <em>Bussines Key</em>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Loan)) {
            return false;
        }

        Loan other = (Loan) obj;
        return loaned.equals(other.loaned) && loanee.equals(other.loaned) 
                && media.equals(other.media) 
                && (returned != null ? returned.equals(other.returned) 
                : (other.returned != null ? false : true));
    }
    
    //TODO: link for the equals method
    /**
     * HashCode for this object, excluding the id field.
     * @return integer representing the hashcode
     * 
     * @see equals
     */    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.loaned != null ? this.loaned.hashCode() : 0);
        hash = 59 * hash + (this.returned != null ? this.returned.hashCode() : 0);
        hash = 59 * hash + (this.loanee != null ? this.loanee.hashCode() : 0);
        hash = 59 * hash + (this.media != null ? this.media.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return super.toString() + " " + id + " " + loaned.toString() + " " 
                + loanee.toString();
    }
}
