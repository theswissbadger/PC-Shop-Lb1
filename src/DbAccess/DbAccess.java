package DbAccess;

import Model.Adresse;
import Model.Kunde;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class DbAccess {
    private MongoCollection<Document> collection;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private String connectionString = "mongodb://localhost:27017";

    public DbAccess() {}

    public void connectDb(String dbName, String collectionName) {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(dbName);
        this.collection = database.getCollection(collectionName);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void insert(Kunde kunde) {
        Document doc = new Document("kundenId", kunde.getKundenId())
                .append("geschlecht", kunde.getGeschlecht())
                .append("nachname", kunde.getNachname())
                .append("vorname", kunde.getVorname())
                .append("telefon", kunde.getTelefon())
                .append("email", kunde.getEmail())
                .append("sprache", kunde.getSprache())
                .append("geburtsdatum", kunde.getGeburtsdatum())
                .append("adresse", new Document("strasse", kunde.getAdresse().getStrasse())
                        .append("plz", kunde.getAdresse().getPlz())
                        .append("ort", kunde.getAdresse().getOrt()));
        collection.insertOne(doc);
    }


    public Kunde getByIndex(int index) {
        ArrayList<Kunde> kundenList = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    return documentToKunde(doc);
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        return null; // Rückgabe null, wenn der Index nicht gefunden wird
    }

    /*
    public Kunde getById(String kundenId) {
        Document doc = collection.find(Filters.eq("kundenId", Integer.parseInt(kundenId))).first();
        if (doc == null) return null;
        return documentToKunde(doc);
    }
    */
     


    public ArrayList<Kunde> getAll() {
        ArrayList<Kunde> kundenList = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                kundenList.add(documentToKunde(doc));
            }
        } finally {
            cursor.close();
        }
        return kundenList;
    }

    public void update(Kunde kunde) {
        Document doc = new Document()
                .append("geschlecht", kunde.getGeschlecht())
                .append("nachname", kunde.getNachname())
                .append("vorname", kunde.getVorname())
                .append("telefon", kunde.getTelefon())
                .append("email", kunde.getEmail())
                .append("sprache", kunde.getSprache())
                .append("geburtsdatum", kunde.getGeburtsdatum())
                .append("adresse", new Document("strasse", kunde.getAdresse().getStrasse())
                        .append("plz", kunde.getAdresse().getPlz())
                        .append("ort", kunde.getAdresse().getOrt()));
        collection.updateOne(Filters.eq("_id", kunde.getId()), new Document("$set", doc));
    }

    public void delete(String id) {
        collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    private Kunde documentToKunde(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        String geschlecht = doc.getString("geschlecht");
        String nachname = doc.getString("nachname");
        String vorname = doc.getString("vorname");
        String telefon = doc.getString("telefon");
        String email = doc.getString("email");
        String sprache = doc.getString("sprache");
        Date geburtsdatum = doc.getDate("geburtsdatum");

        Document adresseDoc = (Document) doc.get("adresse");
        String strasse = adresseDoc.getString("strasse");
        String plz = adresseDoc.getString("plz");
        String ort = adresseDoc.getString("ort");
        Adresse adresse = new Adresse(strasse, plz, ort);

        return new Kunde(id, geschlecht, nachname, vorname, telefon, email, sprache, geburtsdatum, adresse);
    }
}
