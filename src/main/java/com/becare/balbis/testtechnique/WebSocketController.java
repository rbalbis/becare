/**
 * 
 */
package com.becare.balbis.testtechnique;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.becare.balbis.testtechnique.ressource.Message;

/**
 * @author m429610
 *
 */
@Controller
public class WebSocketController {

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message message(Message msg) throws Exception {
        System.out.println(msg);
        return msg;
    }

}
