/**
 * 
 */
package com.becare.balbis.testtechnique.ressource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author m429610
 *
 */
@Entity
public class SessionUser {

    private @Id @GeneratedValue Long id;

    private String user;

    private Date begin;

    private Date end;

    @ElementCollection
    private Map<String, Double> data = new HashMap<String, Double>();

    private String state;

    /**
     * 
     */
    public SessionUser() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return the state
     */
    public String getState() {
        return this.state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @param user
     * @param begin
     * @param end
     * @param data
     */
    public SessionUser(String user, String begin, String end, Map<String, Double> data) {
        this.user = user;
        setBegin(begin);
        setEnd(end);
        this.data = data;
    }

    /**
     * @return the data
     */
    public Map<String, Double> getData() {
        return this.data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(Map<String, Double> data) {
        this.data = data;
    }

    /**
     * Convert String into Date
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public Date stringToDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
        Date res;
        res = df.parse(date);
        return res;

    }

    private boolean setBegin(String date) {

        try {
            this.begin = stringToDate(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean setEnd(String date) {

        try {
            this.end = stringToDate(date);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the begin
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

}
