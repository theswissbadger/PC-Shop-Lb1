package Model;

import DbAccess.DbAccess;
import Interfaces.iBestellungen;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class BestellungRepo implements iBestellungen {
    private DbAccess dbAccess;

    public BestellungRepo() {
        dbAccess = new DbAccess();
        dbAccess.connectDb("PC-Shop", "Bestellungen");
    }
    @Override
    public void addBestellung(Bestellung bestellung){
        dbAccess.addBestellung(bestellung);
    }
    @Override
    public ArrayList<Bestellung> getAllBestellungen(){
        return dbAccess.getAllBestellungen();
    }

    public void deleteBestellungByIndex(int index) {
        ArrayList<Bestellung> bestellungen = getAllBestellungen();

        if (index >= 0 && index < bestellungen.size()) {
            Bestellung bestellungToDelete = bestellungen.get(index);
            bestellungen.remove(index);
            ObjectId id = bestellungToDelete.getBestellungId();

            dbAccess.deleteBestellung(id);
            System.out.println("Bestellung erfolgreich gelöscht.");
        } else {
            System.out.println("Ungültiger Index. Die Bestellung konnte nicht gelöscht werden.");
        }
    }
    @Override
    public Bestellung getBestellungbyIndex(int index){
        return dbAccess.getBestellungByIndex(index);
    }

    @Override
    public void updateBestellung(Bestellung bestellung){
        dbAccess.updateBestellung(bestellung);
    }

}
