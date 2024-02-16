package Model;

import org.bson.types.ObjectId;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

public class Kunde {
    private ObjectId id;
    private String kundenId;
    private String geschlecht;
    private String nachname;
    private String vorname;
    private String telefon;
    private String email;
    private String sprache;
    private Date geburtsdatum;
    private Adresse adresse;

    public Kunde() {
    }

    public Kunde(ObjectId id, String geschlecht, String nachname, String vorname, String telefon, String email, String sprache, Date geburtsdatum, Adresse adresse) {
        this.id = id;
        this.geschlecht = geschlecht;
        this.nachname = nachname;
        this.vorname = vorname;
        this.telefon = telefon;
        this.email = email;
        this.sprache = sprache;
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
    }

    public Kunde(String geschlecht, String nachname, String vorname, String telefon, String email, String sprache, Date geburtsdatum, Adresse adresse) {
        this.geschlecht = geschlecht;
        this.nachname = nachname;
        this.vorname = vorname;
        this.telefon = telefon;
        this.email = email;
        this.sprache = sprache;
        this.geburtsdatum = geburtsdatum;
        this.adresse = adresse;
    }

    public Kunde(ObjectId id, String nachname, String vorname) {
        this.id = id;
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Kunde(String geschlecht, String nachname, String vorname, String telefon, String email, String sprache, Date geburtsdatum) {
    }



    public String toString() {
        return "Kunde: " + vorname + " " + nachname + " identifiziert sich als: " + geschlecht + "\n----------------------------------------------------------------------";
    }


    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }


}
