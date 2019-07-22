/**
 * 
 */
package com.becare.balbis.testtechnique.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.becare.balbis.testtechnique.ressource.SessionUser;

/**
 * @author m429610
 *
 */
@Component
public class SessionStateEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void send(final SessionUser sessionUser) {
        String state = "";
        String currentSessionState = sessionUser.getState();

        // Choose the next stage
        switch (currentSessionState) {
        case "RECEIVED":
            state = "STAGE_1";
            break;
        case "STAGE_1":
            state = "STAGE_2";
            break;
        case "STAGE_2":
            state = "PROCESSED";
            break;
        default:
            break;
        }

        System.out.println("Publishing Session User event. ");

        SessionStateEvent ssEvent;

        // Create a new Event
        ssEvent = new SessionStateEvent(this, sessionUser, state);

        // And publish it
        applicationEventPublisher.publishEvent(ssEvent);
    }
}
