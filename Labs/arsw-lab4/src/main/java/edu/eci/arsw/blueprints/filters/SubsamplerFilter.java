package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("blueprintFilter")
public class SubsamplerFilter implements PointsFilter {
    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints();
        List<Point> newpoints = new ArrayList<Point>();
        for(int i = 0; i<points.size(); i += 2){
            newpoints.add(points.get(i));
        }
        return new Blueprint(blueprint.getAuthor(),blueprint.getName(),newpoints);
    }
}
