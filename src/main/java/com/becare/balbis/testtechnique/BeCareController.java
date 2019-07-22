/**
 * 
 */
package com.becare.balbis.testtechnique;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.becare.balbis.testtechnique.event.SessionStateEventPublisher;
import com.becare.balbis.testtechnique.ressource.Message;
import com.becare.balbis.testtechnique.ressource.SessionUser;
import com.becare.balbis.testtechnique.ressource.SessionUserRepository;
import com.becare.balbis.testtechnique.ressource.User;
import com.becare.balbis.testtechnique.ressource.UserRepository;

/**
 * @author m429610
 *
 */

@RestController
@RequestMapping({ "/api" })
public class BeCareController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    SessionUserRepository sessionRepo;

    @Autowired
    SessionStateEventPublisher ssPublisher;

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * Receive a new request from the client
     * 
     * @param body
     * @param req
     * @return -1 if the user in the request does not exist, the Id of the session in database otherwise
     */
    @PostMapping("/receive")
    public ResponseEntity<Long> receive(@RequestBody Map<String, Object> body, HttpServletRequest req) {

        // Get the user from the Database
        Optional<User> userOpt = userRepo.findById((String) body.get("user"));
        User user;

        // If the user does not exist in database we send a response with 404 status and -1
        if (!userOpt.isPresent()) {
            return new ResponseEntity<Long>(new Long(-1), HttpStatus.NOT_FOUND);
        }

        // Otherwise we get the user from the Database
        user = userOpt.get();

        // We create a new sessionUser instance with request data
        SessionUser sessionUser = this.createSessionFromRequest(body);

        sessionUser.setState("RECEIVED");

        // And we save the session in the database
        sessionUser = sessionRepo.save(sessionUser);

        Date dnow = new Date();

        // Create a new Message
        Message msg1 = new Message(dnow, sessionUser.getId(), sessionUser.getState(), sessionUser.getData());

        // Send the Message to client via the websocket
        this.sendMessage(msg1);

        // Send SessionUser Event
        ssPublisher.send(sessionUser);

        // We send the id of the session in database to the client
        return new ResponseEntity<Long>(sessionUser.getId(), HttpStatus.CREATED);
    }

    /**
     * Send message to the websocket
     * 
     * @param msg
     */
    public void sendMessage(Message msg) {
        System.out.println("Fire");
        this.template.convertAndSend("/topic/messages", msg);
    }

    /**
     * Create a new Object session from data of the request
     * 
     * @param body
     *            of the request
     * @return Session create thanks to parameter in the request
     */
    private SessionUser createSessionFromRequest(Map<String, Object> body) {

        Map<String, Double> mapOfData = new HashMap<String, Double>();

        // Parse the request body to JSON Object
        JSONObject bodyJson = new JSONObject(body);
        try {

            // Get the JSON of data
            String dataString = bodyJson.getString("data");
            JSONArray dataJson = new JSONArray(dataString);

            // For every value in the data Array
            for (int i = 0; i < dataJson.length(); i++) {

                // Create a new object
                JSONObject newObjJson = new JSONObject(dataJson.get(i).toString());

                // Get the key and value of the new object
                String key = newObjJson.keys().next().toString();
                Double value = new Double(newObjJson.get(key).toString());

                // Add the key and value to map of Data
                mapOfData.put(key, value);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Create the SessionUser
        SessionUser session = new SessionUser((String) body.get("user"), (String) body.get("begin"), (String) body.get("end"), mapOfData);

        return session;
    }

    /**
     * Create a new User
     * 
     * @param email
     * @param firstName
     * @param lastName
     * @return
     */
    @PostMapping("/createUser")
    public User createUser(@RequestParam(value = "email") String email, @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {

        User newUser = userRepo.save(new User(email, firstName, lastName));
        return newUser;

    }

}
