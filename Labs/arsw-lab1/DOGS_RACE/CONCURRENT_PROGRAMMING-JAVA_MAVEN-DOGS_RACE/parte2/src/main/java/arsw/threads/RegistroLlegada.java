package arsw.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class RegistroLlegada {

	private AtomicInteger ultimaPosicionAlcanzada=new AtomicInteger(0);

	private String ganador=null;
	
	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public AtomicInteger getUltimaPosicionAlcanzada() {
		return ultimaPosicionAlcanzada;
	}

	public int decreaseAndGet() {
		return ultimaPosicionAlcanzada.decrementAndGet();
	}

	public int increaseAndGet() {
		return ultimaPosicionAlcanzada.incrementAndGet();
	}

	public void setUltimaPosicionAlcanzada(AtomicInteger ultimaPosicionAlcanzada) {
		this.ultimaPosicionAlcanzada = ultimaPosicionAlcanzada;
	}

	
	
}
