/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.highlandersim;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Carlos Gomez y Daniel Walteros
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InmortalTest {

    private ControlFrame frame;

    @Before
    public void setUp() {
        frame = new ControlFrame();
    }

    @Test
    public void sholudPauseAndResume() throws Exception {
        frame.actionStart();
        assertFalse(frame.isPaused());
        frame.actionPause();
        assertTrue(frame.isPaused());
        frame.actionResume();
        assertFalse(frame.isPaused());
        frame.actionStop();
    }

    @Test
    public void sholudMantainTheInvariant() throws Exception {
        frame.actionStart();
        assertFalse(frame.isPaused());
        int sum;
        for(int i=0;i<100;i++){
            frame.actionPause();
            frame.actionCheck();
            sum = frame.getSum();
            assertEquals(300,sum);
        }
        frame.actionStop();
    }

    @Test
    public void sholudKillInmortls() throws Exception {
        frame.actionStart();
        int inicialAmount = frame.getInmortals().size();
        Thread.sleep(2000);
        int currentAmount = frame.getInmortals().size();
        assertNotEquals(inicialAmount,currentAmount);
        frame.actionStop();

    }


}