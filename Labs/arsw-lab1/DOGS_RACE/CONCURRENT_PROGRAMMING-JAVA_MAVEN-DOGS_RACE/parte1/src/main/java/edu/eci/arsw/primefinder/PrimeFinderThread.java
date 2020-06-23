package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	private boolean paused;
	private int a,b;
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
		paused=false;
	}

	public void run(){
		try {
			for (int i=a;i<=b;i++){
				if (isPrime(i)){
					primes.add(i);
					//System.out.println(i);
				}
				synchronized (this) {
					while (paused) {
						wait();
					}
				}
			}
		}catch (InterruptedException exc){
			System.out.println("Hilo que tiene de rango "+a+"-"+b+ "interrumpido.");
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
	
	private boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
}


