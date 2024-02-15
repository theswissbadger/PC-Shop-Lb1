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

    public Computer getComputerById(int index){return dbAccess.getComputerByIndex(index);}

    @Override
    public void insert(Computer computer) {
        dbAccess.insert(computer);
    }

    @Override
    public void update(Computer computer) {
        dbAccess.updateComputer(computer);
    }

    @Override
    public void delete(int computerId) {

    }

    public void deleteComputerByIndex(int index) {
        ArrayList<Computer> computerList = dbAccess.getAllComputer();
        if (index >= 0 && index < computerList.size()) {
            Computer computerToDelete = computerList.get(index);

            dbAccess.deleteComputer(computerToDelete.getId());


            computerList.remove(index);
            System.out.println("Computer erfolgreich gelöscht.");
        } else {
            System.out.println("Ungültiger Index. Der Computer konnte nicht gelöscht werden.");
        }
    }
}
