package model;

public class Piloto {
    private final String escuderia;
    private final int numeroCarro;
    private final int melhorTempo;

    public Piloto(String escuderia, int numeroCarro, int melhorTempo) {
        this.escuderia = escuderia;
        this.numeroCarro = numeroCarro;
        this.melhorTempo = melhorTempo;
    }

    public int getMelhorTempo() {
        return melhorTempo;
    }

    @Override
    public String toString() {
        return escuderia + " - Carro " + numeroCarro + " | Melhor Tempo: " + melhorTempo + " ms";
    }
}
