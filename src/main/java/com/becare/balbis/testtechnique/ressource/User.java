package com.becare.balbis.testtechnique.ressource;
/**
 * 
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author m429610
 *
 */
@Entity
public class User {

    // private @Id @GeneratedValue Long id;

    /**
     * 
     */
    public User() {
    }

    @Id
    @Column(name = "EMAIL", nullable = false)
    private String email;

    private String firstName;

    private String lastName;

    /**
     * @param email
     * @param firstName
     * @param lastName
     */
    public User(String email, String firstName, String lastName) {
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
