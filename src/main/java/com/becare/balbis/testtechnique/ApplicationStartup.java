/**
 * 
 */
package com.becare.balbis.testtechnique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.becare.balbis.testtechnique.ressource.User;
import com.becare.balbis.testtechnique.ressource.UserRepository;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    UserRepository userRepo;

    /**
     * This method will be executed when the application is ready to respond
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        // We create the first user to test the application
        User user1 = new User("email@address.com", "John", "Doe");

        // We save the user in the db
        userRepo.save(user1);

        return;
    }

} // class