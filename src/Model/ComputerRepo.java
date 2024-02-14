package Model;

import DbAccess.DbAccess;
import Interfaces.iComputer;

import java.util.ArrayList;

public class ComputerRepo implements iComputer {
    private DbAccess dbAccess;

    public ComputerRepo(){
        this.dbAccess = new DbAccess();
        this.dbAccess.connectDb("PC-Shop", "Computer");
    }


    @Override
    public ArrayList<Computer> getAllComputer() {
        return dbAccess.getAllComputer();
    }

    @Override
    public void insert(Computer computer) {
        dbAccess.insert(computer);
    }

    @Override
    public void update(Kunde computer) {

    }

    @Override
    public void delete(int computerId) {

    }
}
