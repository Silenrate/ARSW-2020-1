package edu.eci.arsw.collabpaint;

import edu.eci.arsw.collabpaint.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class CollabPaintMessageHandler  {

    @Autowired
    SimpMessagingTemplate msgt;

    private ConcurrentHashMap<String, CopyOnWriteArrayList<Point>> points = new ConcurrentHashMap<>();

    @MessageMapping("/newpoint.{numdibujo}")
    public void handlePointEvent(Point pt, @DestinationVariable String numdibujo) throws Exception {
        System.out.println("Nuevo punto recibido en el servidor!:"+pt);
        CopyOnWriteArrayList<Point> puntos;
        if(!points.containsKey(numdibujo)){
            puntos = new CopyOnWriteArrayList<>();
            points.put(numdibujo,puntos);
        }
        else{
            puntos = points.get(numdibujo);
        }
        puntos.add(pt);
        msgt.convertAndSend("/topic/newpoint."+numdibujo, pt);
        if(puntos.size()>=4){
            msgt.convertAndSend("/topic/newpolygon."+numdibujo, puntos);
        }
    }
}
