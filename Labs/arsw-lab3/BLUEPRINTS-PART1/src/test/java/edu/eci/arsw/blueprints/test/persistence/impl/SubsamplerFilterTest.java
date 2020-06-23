package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filters.RedundancyFilter;
import edu.eci.arsw.blueprints.filters.SubsamplerFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SubsamplerFilterTest {
    private SubsamplerFilter sf;

    @Before
    public void setUp(){
        sf = new SubsamplerFilter();
    }

    @Test
    public void shouldHaveTheSamePoints(){
        Point[] pts=new Point[]{new Point(10, 10)};
        Blueprint blueprint = new Blueprint("david","edificio",pts);
        Blueprint blue = sf.filter(blueprint);
        assertEquals(blueprint.getPoints(),blue.getPoints());
    }

    @Test
    public void shouldEliminateHalfOfPoints(){
        Point point = new Point(10, 10);
        Point[] pts=new Point[]{point,point,new Point(20, 10)};
        Point[] expected=new Point[]{point,new Point(20, 10)};
        Blueprint blueprint = new Blueprint("david","edificio",pts);
        int size = blueprint.getPoints().size();
        Blueprint blue = sf.filter(blueprint);
        assertEquals(Arrays.asList(expected),blue.getPoints());
        assertEquals((size/2)+(size%2),blue.getPoints().size());
        assertNotEquals(blueprint.getPoints(),blue.getPoints());
        assertEquals(blueprint.getPoints().size(),blue.getPoints().size()+1);
    }
}
