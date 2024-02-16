package Model;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class Bestellung {
    private ObjectId bestellungId;
    private int bestellnummer;
    private Date bestelldatum;
    private double total;
    private ArrayList<Bestellposition> bestellPositionen;
    private Kunde kunde;

    public Bestellung(int bestellnummer, Date bestelldatum, double total, ArrayList<Bestellposition> bestellPositionen, Kunde kunde) {
        this.bestellnummer = bestellnummer;
        this.bestelldatum = bestelldatum;
        this.total = total;
        this.bestellPositionen = bestellPositionen;
        this.kunde = kunde;
    }

    public Bestellung(ObjectId bestellungId, int bestellnummer, Date bestelldatum, double total, Kunde kunde) {
        this.bestellungId = bestellungId;
        this.bestellnummer = bestellnummer;
        this.bestelldatum = bestelldatum;
        this.total = total;
        this.kunde = kunde;
    }

    public Bestellung(ObjectId id,Date bestelldatum,double total, ArrayList<Bestellposition> bestellPositionen, Kunde kunde) {
        this.bestellungId = id;
        this.bestelldatum = bestelldatum;
        this.total = total;
        this.bestellPositionen = bestellPositionen;
        this.kunde = kunde;
    }

    public ArrayList<Bestellposition> getBestellPositionen() {
        return bestellPositionen;
    }

    public void setBestellPositionen(ArrayList<Bestellposition> bestellPositionen) {
        this.bestellPositionen = bestellPositionen;
    }

    public ObjectId getBestellungId() {
        return bestellungId;
    }

    public void setBestellungId(ObjectId bestellungId) {
        this.bestellungId = bestellungId;
    }

    public int getBestellnummer() {
        return bestellnummer;
    }

    public void setBestellnummer(int bestellnummer) {
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
