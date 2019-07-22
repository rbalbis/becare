/**
 * 
 */
package com.becare.balbis.testtechnique.ressource;

import java.util.Date;
import java.util.Map;

/**
 * @author m429610
 *
 */
public class Message {

    /**
     * @param timeStamp
     * @param sessionId
     * @param state
     * @param data
     */
    public Message(Date timeStamp, Long sessionId, String state, Map<String, Double> data) {
        super();
        this.timeStamp = timeStamp;
        this.sessionId = sessionId;
        this.state = state;
        this.data = data;
    }

    private Date timeStamp;

    private Long sessionId;

    private String state;

    private Map<String, Double> data;

    /**
     * @param timeStamp
     * @param sessionId
     * @param state
     */
    public Message(Date timeStamp, Long sessionId, String state) {
        super();
        this.timeStamp = timeStamp;
        this.sessionId = sessionId;
        this.state = state;
    }

    /**
     * @return the timeStamp
     */
    public Date getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * @param timeStamp
     *            the timeStamp to set
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the sessionId
     */
    public Long getSessionId() {
        return this.sessionId;
    }

    /**
     * @param sessionId
     *            the sessionId to set
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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

}
