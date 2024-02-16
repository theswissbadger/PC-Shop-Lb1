package Model;

import DbAccess.DbAccess;
import Interfaces.iBestellungen;

import java.util.ArrayList;

public class BestellungRepo implements iBestellungen {
    private DbAccess dbAccess;

    public BestellungRepo() {
        dbAccess = new DbAccess();
        dbAccess.connectDb("PC-Shop", "Bestellungen");
    }

    public void addBestellung(Bestellung bestellung){
        dbAccess.addBestellung(bestellung);
    }

    public ArrayList<Bestellung> getAllBestellungen(){
        return dbAccess.getAllBestellungen();
    }
}
