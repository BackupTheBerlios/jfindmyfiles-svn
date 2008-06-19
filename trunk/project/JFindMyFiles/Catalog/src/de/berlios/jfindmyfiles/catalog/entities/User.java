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

import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author ei10635
 */
public class User {

    private Long id;
    private String firstname;
    private String surname;
    private Set loans;

    public User() {
        //DO NOTHING
    }

    public User(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

    @SuppressWarnings("unchecked")
    public void addLoan(Loan loan) {
        if (loans == null) {
            loans = new LinkedHashSet();
        }
        loans.add(loan);
    }

    public void removeLoan(Loan loan) {
        if (loans != null) {
            loans.remove(loan);
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private Set getLoans() {
        return loans;
    }

    private void setLoans(Set loans) {
        this.loans = loans;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return firstname.equals(other.firstname) && surname.equals(other.surname);
    }

    /**
     * HashCode for this object, excluding the id field.
     * @return integer representing the hashcode
     * 
     * @see equals
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.firstname != null ? this.firstname.hashCode() : 0);
        hash = 11 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return firstname + " " + surname;
    }
}
