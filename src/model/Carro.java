package model;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Carro extends Thread {
    private final String escuderia;
    private final int numeroCarro;
    private final Semaphore semaforoPista;
    private final Semaphore[] semaforoEscuderia;
    private final Piloto[] gridLargada;
    private final int indiceGrid;
    private int voltaMaisRapida;

    public Carro(String escuderia, int numeroCarro, Semaphore semaforoPista, Semaphore[] semaforoEscuderia, Piloto[] gridLargada, int indiceGrid) {
        this.escuderia = escuderia;
        this.numeroCarro = numeroCarro;
        this.semaforoPista = semaforoPista;
        this.semaforoEscuderia = semaforoEscuderia;
        this.gridLargada = gridLargada;
        this.indiceGrid = indiceGrid;
        this.voltaMaisRapida = Integer.MAX_VALUE;
    }

    @Override
    public void run() {
        try {
            semaforoEscuderia[numeroCarro].acquire();
            semaforoPista.acquire();

            System.out.println(escuderia + " - Carro " + (numeroCarro + 1) + " entrou na pista.");

            Random random = new Random();
            for (int i = 1; i <= 3; i++) {
                int tempoVolta = random.nextInt(2000) + 1000;
                System.out.println(escuderia + " - Carro " + (numeroCarro + 1) + " completou a volta " + i + " em " + tempoVolta + " ms.");
                voltaMaisRapida = Math.min(voltaMaisRapida, tempoVolta);
                Thread.sleep(tempoVolta);
            }

            System.out.println(escuderia + " - Carro " + (numeroCarro + 1) + " terminou suas voltas.");

            semaforoPista.release();
            semaforoEscuderia[numeroCarro].release();

            gridLargada[indiceGrid] = new Piloto(escuderia, numeroCarro + 1, voltaMaisRapida);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
