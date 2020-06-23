package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filters.RedundancyFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedundancyFilterTest {

    private RedundancyFilter rf;

    @Before
    public void setUp(){
        rf = new RedundancyFilter();
    }

    @Test
    public void shouldHaveTheSamePoints(){
        Point[] pts=new Point[]{new Point(10, 10),new Point(20, 10)};
        Blueprint blueprint = new Blueprint("david","edificio",pts);
        Blueprint blue = rf.filter(blueprint);
        assertEquals(blueprint.getPoints(),blue.getPoints());
    }

    @Test
    public void shouldEliminateRepeatedPoints(){
        Point point = new Point(10, 10);
        Point[] pts=new Point[]{point,point,new Point(20, 10)};
        Blueprint blueprint = new Blueprint("david","edificio",pts);
        Blueprint blue = rf.filter(blueprint);
        assertNotEquals(blueprint.getPoints(),blue.getPoints());
        assertTrue(blue.getPoints().contains(point));
        assertEquals(blueprint.getPoints().size(),blue.getPoints().size()+1);
    }
}
