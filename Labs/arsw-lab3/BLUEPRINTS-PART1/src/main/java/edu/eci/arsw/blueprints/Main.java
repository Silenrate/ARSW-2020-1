package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws BlueprintNotFoundException, BlueprintPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        bps.addNewBlueprint(new Blueprint("aa","bb"));
        Blueprint blueprint = bps.getBlueprint("aa","bb");
        System.out.println(blueprint);
        System.out.println(bps.getAllBlueprints());
        System.out.println(bps.getBlueprintsByAuthor("aa"));
        Point[] pts=new Point[]{new Point(10, 10),new Point(20, 20),new Point(30, 30)};
        Blueprint blue = new Blueprint("a","b",pts);
        System.out.println(blue.getPoints());
        System.out.println(bps.filter(blue).getPoints());


    }
}
