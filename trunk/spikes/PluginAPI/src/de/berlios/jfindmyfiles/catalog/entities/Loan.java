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
    
    public Loan() {
        //DO NOTHING
    }
    
    public Loan(Date loaned) {
        this.loaned = loaned;
    }

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
