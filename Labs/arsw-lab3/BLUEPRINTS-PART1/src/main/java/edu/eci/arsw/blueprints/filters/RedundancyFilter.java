package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//@Service("blueprintFilter")
public class RedundancyFilter implements PointsFilter{

    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints();
        List<Point> newpoints = new ArrayList<Point>();
        for(Point point:points){
            boolean contain=false;
            for(Point punto : newpoints){
                if(point.equals(punto)){
                    contain=true;
                    break;
                }
            }
            if(!contain){
                newpoints.add(point);
            }
        }
        return new Blueprint(blueprint.getAuthor(),blueprint.getName(),newpoints);
    }
}
