package Model;

public class Bestellposition {
    private Computer computer;
    private double preis;
    private int stueckzahl;

    public Bestellposition(Computer computer, double preis, int stueckzahl) {
        this.computer = computer;
        this.preis = preis;
        this.stueckzahl = stueckzahl;
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

    public int getStueckzahl() {
        return stueckzahl;
    }

    public void setStueckzahl(int stueckzahl) {
        this.stueckzahl = stueckzahl;
    }
}
