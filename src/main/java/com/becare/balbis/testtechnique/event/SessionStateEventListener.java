/**
 * 
 */
package com.becare.balbis.testtechnique.event;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.becare.balbis.testtechnique.ressource.Message;
import com.becare.balbis.testtechnique.ressource.SessionUser;
import com.becare.balbis.testtechnique.ressource.SessionUserRepository;

/**
 * @author m429610
 *
 */
@Component
public class SessionStateEventListener implements ApplicationListener<SessionStateEvent> {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    SessionUserRepository sessionRepo;

    @Autowired
    SessionStateEventPublisher ssPublisher;

    /**
     * Called when a SessionStateEvent is published
     */
    @Override
    public void onApplicationEvent(SessionStateEvent event) {

        SessionUser sessionUser = event.getsession();
        String state = event.getState();

        System.out.println("Received Session User event");
        try {
            // wait for 1000 to 2000 milliseconds
            Thread.sleep((long) (Math.random() * 1000 + 1000));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Change the state of the session
        sessionUser.setState(state);

        // Save the session in database
        sessionRepo.save(sessionUser);

        // Get current time
        Date dnow = new Date();

        Message msg1;

        msg1 = new Message(dnow, sessionUser.getId(), sessionUser.getState());

        // Send message to client of the webSocket
        this.sendMessage(msg1);

        // If the session is not processed
        if (sessionUser.getState() != "PROCESSED") {

            // Go to next stage
            ssPublisher.send(sessionUser);
        }

    }

    /**
     * Send message to client of webSocket
     * 
     * @param msg
     */
    public void sendMessage(Message msg) {
        System.out.println("Fire");
        this.template.convertAndSend("/topic/messages", msg);
    }
}