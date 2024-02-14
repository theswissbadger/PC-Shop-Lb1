package Model;

import java.util.Date;

public class Bestellung {
    private int bestellungId;
    private String bestellnummer;
    private Date bestelldatum;
    private double total;
    private Kunde kunde;

    public Bestellung() {
    }

    public Bestellung(String bestellnummer, Date bestelldatum, double total, Kunde kunde) {
        this.bestellnummer = bestellnummer;
        this.bestelldatum = bestelldatum;
        this.total = total;
        this.kunde = kunde;
    }

    public int getBestellungId() {
        return bestellungId;
    }

    public void setBestellungId(int bestellungId) {
        this.bestellungId = bestellungId;
    }

    public String getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }

    public Date getBestelldatum() {
        return bestelldatum;
    }

    public void setBestelldatum(Date bestelldatum) {
        this.bestelldatum = bestelldatum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }
}
