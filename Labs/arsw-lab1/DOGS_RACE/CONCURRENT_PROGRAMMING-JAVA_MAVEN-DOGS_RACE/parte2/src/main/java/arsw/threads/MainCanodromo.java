package arsw.threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainCanodromo {

    private static Galgo[] galgos;

    private static Canodromo can;

    private static RegistroLlegada reg = new RegistroLlegada();

    private static Timer timer;

    private static Thread aa;

    public static void main(String[] args) {
        can = new Canodromo(17, 100);
        galgos = new Galgo[can.getNumCarriles()];
        can.setVisible(true);

        //Acción del botón start
        can.setStartAction(
                new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        //como acción, se crea un nuevo hilo que cree los hilos
                        //'galgos', los pone a correr, y luego muestra los resultados.
                        //La acción del botón se realiza en un hilo aparte para evitar
                        //bloquear la interfaz gráfica.
                        ((JButton) e.getSource()).setEnabled(false);
                        new Thread() {
                            public void run() {
                                for (int i = 0; i < can.getNumCarriles(); i++) {
                                    //crea los hilos 'galgos'
                                    galgos[i] = new Galgo(can.getCarril(i), "" + (i+1), reg);
                                    //inicia los hilos
                                    galgos[i].start();
                                }
                                for (int i = 0; i < can.getNumCarriles(); i++) {
                                    try {
                                        galgos[i].join();
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }

                                SomeThreadAlive();

                            }
                        }.start();

                    }

                    private void SomeThreadAlive() {
                        can.winnerDialog(reg.getGanador(), reg.getUltimaPosicionAlcanzada().intValue());
                        System.out.println("El ganador fue: " + reg.getGanador());
                    }
                }
        );

        can.setStopAction(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Carrera pausada!");
                        for (int i = 0; i < can.getNumCarriles(); i++) {
                            //crea los hilos 'galgos'
                            galgos[i].pause();
                        }
                    }
                }
        );

        can.setContinueAction(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Carrera reanudada!");
                        for (int i = 0; i < can.getNumCarriles(); i++) {
                            //crea los hilos 'galgos'
                            galgos[i].pursue();
                        }
                    }
                }
        );

    }

}
