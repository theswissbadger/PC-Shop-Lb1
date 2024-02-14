package Model;

public class Computer {
    private int computerId;
    private String hersteller;
    private String modell;
    private String arbeitsspeicher;
    private String cpu;
    private String massenspeicher;
    private String typ;
    private double einzelpreis;
    private Schnittstelle schnittstelle;
    private Bestellposition bestellposition;

    public Computer() {
    }

    public Computer(String hersteller, String modell, String arbeitsspeicher, String cpu, String massenspeicher, String typ, double einzelpreis, Schnittstelle schnittstelle) {
        this.hersteller = hersteller;
        this.modell = modell;
        this.arbeitsspeicher = arbeitsspeicher;
        this.cpu = cpu;
        this.massenspeicher = massenspeicher;
        this.typ = typ;
        this.einzelpreis = einzelpreis;
        this.schnittstelle = schnittstelle;
    }

    @Override
    public String toString() {
        return "Computer" +
                ", hersteller='" + hersteller + '\'' +
                ", modell='" + modell + '\'' +
                ", arbeitsspeicher='" + arbeitsspeicher + '\'' +
                ", cpu='" + cpu + '\'' +
                ", massenspeicher='" + massenspeicher + '\'' +
                ", typ='" + typ + '\'' +
                ", einzelpreis=" + einzelpreis
                ;
    }


    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getArbeitsspeicher() {
        return arbeitsspeicher;
    }

    public void setArbeitsspeicher(String arbeitsspeicher) {
        this.arbeitsspeicher = arbeitsspeicher;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMassenspeicher() {
        return massenspeicher;
    }

    public void setMassenspeicher(String massenspeicher) {
        this.massenspeicher = massenspeicher;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public double getEinzelpreis() {
        return einzelpreis;
    }

    public void setEinzelpreis(double einzelpreis) {
        this.einzelpreis = einzelpreis;
    }

    public Schnittstelle getSchnittstelle() {
        return schnittstelle;
    }

    public void setSchnittstelle(Schnittstelle schnittstelle) {
        this.schnittstelle = schnittstelle;
    }

    public Bestellposition getBestellposition() {
        return bestellposition;
    }

    public void setBestellposition(Bestellposition bestellposition) {
        this.bestellposition = bestellposition;
    }
}
