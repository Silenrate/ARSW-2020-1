package edu.eci.arsw.airportfinder.websocket;

//import edu.eci.arsw.collabpaint.model.Point;
import edu.eci.arsw.airportfinder.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class AirportFinderMessageHandler {

    @Autowired
    SimpMessagingTemplate msgt;

    @MessageMapping("/newpoint.{numdibujo}")
    public void handlePointEvent(Point pt, @DestinationVariable String name) throws Exception {
        msgt.convertAndSend("/topic/newpoint."+name, pt);
    }
}
