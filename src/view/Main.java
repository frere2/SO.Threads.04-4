package view;

import model.Carro;
import model.Piloto;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaforoPista = new Semaphore(5);

        Semaphore[] semaforoEscuderia = new Semaphore[7];
        for (int i = 0; i < semaforoEscuderia.length; i++) {
            semaforoEscuderia[i] = new Semaphore(1);
        }

        Piloto[] gridLargada = new Piloto[14];

        String[] escuderias = {"Ferrari", "Mercedes", "Red Bull", "McLaren", "Renault", "Williams", "Alfa Romeo"};

        Carro[] carros = new Carro[14];
        for (int i = 0; i < 7; i++) {
            carros[i * 2] = new Carro(escuderias[i], 0, semaforoPista, semaforoEscuderia, gridLargada, i * 2);
            carros[i * 2 + 1] = new Carro(escuderias[i], 1, semaforoPista, semaforoEscuderia, gridLargada, i * 2 + 1);
        }

        for (Carro carro : carros) {
            carro.start();
        }

        for (Carro carro : carros) {
            carro.join();
        }

        ordenarGrid(gridLargada);

        System.out.println("\nGrid de Largada:");
        int posicao = 1;
        for (Piloto piloto : gridLargada) {
            System.out.println(posicao + "º - " + piloto);
            posicao++;
        }
    }

    public static void ordenarGrid(Piloto[] grid) {
        int n = grid.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (grid[j].getMelhorTempo() > grid[j + 1].getMelhorTempo()) {
                    Piloto temp = grid[j];
                    grid[j] = grid[j + 1];
                    grid[j + 1] = temp;
                }
            }
        }
    }
}
