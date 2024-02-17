package Interfaces;

import Model.Bestellung;

import java.util.ArrayList;

public interface iBestellungen {
    void addBestellung(Bestellung bestellung);
    ArrayList<Bestellung> getAllBestellungen();
    Bestellung getBestellungbyIndex(int index);
    void deleteBestellungByIndex(int index);


}
