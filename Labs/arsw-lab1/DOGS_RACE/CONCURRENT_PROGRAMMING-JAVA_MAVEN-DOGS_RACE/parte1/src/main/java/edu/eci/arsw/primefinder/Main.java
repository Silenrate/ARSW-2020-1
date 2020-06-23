package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Timer;

public class Main {

	private static int numberOfThreads = 3;
	private static PrimeFinderThread[] threads = new PrimeFinderThread[numberOfThreads];
	private static boolean resume,isRunning;

	public static void main(String[] args){
		/*
		//Solucion Original
		PrimeFinderThread pft = new PrimeFinderThread(0, 30000000);
		pft.start();
		 */
		//Programa de Pausa
		Timer timer = new Timer (5000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateState();
					pauseThreads();
				} catch (InterruptedException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		timer.start();
		//Solucion con 3 threads
		int start = 0;
		int end = 30000000;
		int count = end-start;
		int amount = count/numberOfThreads;
		int threadAmount = amount;
		for(int i=0;i<numberOfThreads;i++){
			if(i==numberOfThreads-1){
				threadAmount += (count % numberOfThreads);
			}
			threads[i] = new PrimeFinderThread(start, threadAmount);
			threads[i].start();
			start += amount;
			threadAmount += amount;
		}
		for(int i=0;i<numberOfThreads;i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("La respuesta es: ");
		showPrimes();
	}

	private static void updateState() {
		boolean isRunning = false;
		for(int i=0;i<numberOfThreads;i++){
			isRunning |= threads[i].isAlive();
		}
	}


	private static void pauseThreads() throws InterruptedException {
		for(int i=0;i<numberOfThreads;i++){
			threads[i].pause();
		}
		Thread.sleep(500);
		showPrimes();
		resume=false;
		while(!resume){
			requestResume();
			if (resume) { resumeThreads(); }
		}
	}

	private static void showPrimes() {
		for(int i=0;i<numberOfThreads;i++){
			System.out.println(threads[i].getPrimes());
		}
	}

	private static void requestResume() {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Ejecucion pausada despues de 5 segundos, presione la tecla Enter para continuar");
		System.out.println("        En caso de error de digitacion este mensaje volvera a aparecer.        ");
		System.out.println("-------------------------------------------------------------------------------");
		Scanner t = new Scanner(System.in);
		String enterkey = t.nextLine();
		if (enterkey.isEmpty()) {
			resume=true;
		}
	}

	private static void resumeThreads() {
		for(int i=0;i<numberOfThreads;i++){
			threads[i].pursue();
		}
	}
}


