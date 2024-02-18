package Model;

import DbAccess.DbAccess;
import Interfaces.iKunde;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class KundenRepo implements iKunde {
    private DbAccess dbAccess;

    public KundenRepo() {
        this.dbAccess = new DbAccess();
        this.dbAccess.connectDb("PC-Shop", "Kunden");
    }


    public void insert(Kunde kunde) {
        dbAccess.insert(kunde);
    }

    public Kunde getByIndex(int index){
        return dbAccess.getByIndex(index);
    }

    public ArrayList<Kunde> getAll() {
        return dbAccess.getAll();
    }


    @Override
    public void delete(int index) {

    }
    public Kunde getbyId(ObjectId id){
        return dbAccess.getById(id);
    }


    public void update(Kunde kunde) {
        dbAccess.updateComputer(kunde);
    }

    public void deleteByIndex(int index) {
        ArrayList<Kunde> kundenListe = dbAccess.getAll();
        if (index >= 0 && index < kundenListe.size()) {
            Kunde kundeToDelete = kundenListe.get(index);
            dbAccess.delete(kundeToDelete.getId().toString());

            kundenListe.remove(index);
            System.out.println("Kunde erfolgreich gelöscht.");
        } else {
            System.out.println("Ungültiger Index. Der Kunde konnte nicht gelöscht werden.");
        }
    }

}
