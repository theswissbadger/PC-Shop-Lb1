package Model;

public class Bestellposition {
    private Computer computer;
    private double preis;
    private int stückzahl;

    public Bestellposition(Computer computer, double preis, int stückzahl) {
        this.computer = computer;
        this.preis = preis;
        this.stückzahl = stückzahl;
    }

    public Bestellposition() {

    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getStückzahl() {
        return stückzahl;
    }

    public void setStückzahl(int stückzahl) {
        this.stückzahl = stückzahl;
    }
}
