/**
 * 
 */
package com.becare.balbis.testtechnique.event;

import org.springframework.context.ApplicationEvent;

import com.becare.balbis.testtechnique.ressource.SessionUser;

/**
 * @author m429610
 *
 */
public class SessionStateEvent extends ApplicationEvent {
    /**
     * 
     */
    private static final long serialVersionUID = 6455909385869503645L;

    private SessionUser session;

    private String state;

    public SessionStateEvent(Object source, SessionUser session, String state) {
        super(source);
        this.session = session;
        this.state = state;
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

    public SessionUser getsession() {
        return session;
    }
}