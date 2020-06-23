package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CopyOnWriteArrayList;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback = null;

    private AtomicInteger health;

    private int defaultDamageValue;

    private final CopyOnWriteArrayList<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());

    private volatile boolean isActive;

    private static final Object bloqueo = new Object();

    public Immortal(String name, CopyOnWriteArrayList<Immortal> immortalsPopulation, int health, int defaultDamageValue, ImmortalUpdateReportCallback ucb) {
        super(name);
        this.updateCallback = ucb;
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = new AtomicInteger(health);
        this.defaultDamageValue = defaultDamageValue;
        isActive = true;
    }

    public void run() {
        while (isActive) {
            synchronized (immortalsPopulation) {
                while (ControlFrame.isPaused()) {
                    try {
                        immortalsPopulation.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Immortal im;
            int myIndex = immortalsPopulation.indexOf(this);
            if (immortalsPopulation.size() <= 1) {
                if (immortalsPopulation.contains(this)) {
                    showFinalResult();
                }
                break;
            }
            int nextFighterIndex = r.nextInt(immortalsPopulation.size());

            //avoid self-fight
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
            }

            try{
                im = immortalsPopulation.get(nextFighterIndex);
            }catch (Exception e){
                if (nextFighterIndex == myIndex) {
                    nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
                }
                im = immortalsPopulation.get(nextFighterIndex);
            }



            this.fight(im);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void showFinalResult() {
        synchronized (updateCallback) {
            updateCallback.processReport("El ganador fue " + this + " con una vida de " + this.getHealth() + "\n");
        }
    }


    synchronized void parar() {
        isActive = false;
        notifyAll();
    }

    private void fight(Immortal i2) {
        synchronized (bloqueo) {
            if (i2.getHealth() > 0 && getHealth()>0) {
                //System.out.println(this + " voy contra " + i2);
                i2.addHealth(-defaultDamageValue);
                addHealth(defaultDamageValue);
            }
        }
        synchronized (updateCallback){
            updateCallback.processReport("Fight: " + this + " vs " + i2 + "\n");
        }
    }

    public void changeHealth(int v) {
        health.set(v);
    }

    public void addHealth(int v) {
        int salud = health.addAndGet(v);
        if (salud <= 0) {
            //System.out.println(this + " esta muerto -----------------------------------------------------------------------------------------");
            this.parar();
            immortalsPopulation.remove(this);
        }
    }

    public int getHealth() {
        return health.intValue();
    }

    @Override
    public String toString() {

        return name + "[" + health + "]";
    }

}
