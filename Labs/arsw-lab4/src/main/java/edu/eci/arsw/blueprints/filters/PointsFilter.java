package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

import java.util.List;

public interface PointsFilter {

    public Blueprint filter(Blueprint blueprint);
}
