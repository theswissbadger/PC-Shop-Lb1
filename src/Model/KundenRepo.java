package Model;

import DbAccess.DbAccess;

import java.util.ArrayList;

public class KundenRepo {
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

    /*
    public Kunde getById(String id) {
        return dbAccess.getById(id);
    }

     */

    public ArrayList<Kunde> getAll() {
        return dbAccess.getAll();
    }

    public void update(Kunde kunde) {
        dbAccess.update(kunde);
    }

    public void delete(String id) {
        dbAccess.delete(id);
    }
}
