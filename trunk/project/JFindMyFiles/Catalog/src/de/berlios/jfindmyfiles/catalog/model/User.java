/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.berlios.jfindmyfiles.catalog.model;

import java.util.List;

/**
 *
 * @author ei10635
 */
public class User {
    
    private Long id;
    private String firstname;
    private String surname;
    private List<Loan> loans;

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

    private List<Loan> getLoans() {
        return loans;
    }

    private void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
