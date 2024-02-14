package Interfaces;

import Model.Kunde;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public interface iKunde {
    ArrayList<Kunde> getAll();
    void insert(Kunde kunde);
    void update(Kunde kunde);
    void delete(int kundenId);
}
