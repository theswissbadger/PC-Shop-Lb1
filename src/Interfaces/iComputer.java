package Interfaces;

import Model.Computer;
import Model.Kunde;

import java.util.ArrayList;

public interface iComputer {
    ArrayList<Computer> getAllComputer();
    void insert(Computer computer);
    void update(Kunde computer);
    void delete(int computerId);
}
