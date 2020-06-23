package arsw.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	private boolean paused;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		paused=false;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {			
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			
			if (paso == carril.size()) {
				carril.finish();
				int ubicacion = regl.increaseAndGet();
				System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
				if (ubicacion==1) {
					regl.setGanador(this.getName());
				}
			}
			synchronized (this) {
				while (paused) {
					wait();
				}
			}
		}
	}

	synchronized void pause(){
		paused=true;
		notifyAll();
	}

	synchronized void pursue(){
		paused=false;
		notifyAll();
	}



	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
