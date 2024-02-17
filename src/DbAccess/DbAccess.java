package DbAccess;

import Model.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.MongoClient.getDefaultCodecRegistry;
import static javax.management.Query.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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
        // Logger blendet die roten Infos aus
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public void insert(Kunde kunde) {
        Document doc = new Document("geschlecht", kunde.getGeschlecht())
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

    public Kunde getById(ObjectId kundeId) {
        MongoCollection<Document> kundenCollection = database.getCollection("Kunde");
        Document query = new Document("_id", kundeId);
        Document kundeDoc = kundenCollection.find(query).first();
        if (kundeDoc == null) {
            return documentToKunde(kundeDoc);
        } else {
            return null;
        }
    }

    public Kunde getByIndex(int index) {
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
        return null;
    }

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

    public void updateComputer(Kunde kunde) {
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

    // Methoden für Computer Klasse
    public void insert(Computer computer) {
        Document computerDoc = new Document("hersteller", computer.getHersteller())
                .append("modell", computer.getModell())
                .append("arbeitsspeicher", computer.getArbeitsspeicher())
                .append("cpu", computer.getCpu())
                .append("massenspeicher", computer.getMassenspeicher())
                .append("typ", computer.getTyp())
                .append("einzelpreis", computer.getEinzelpreis())
                .append("schnittstellen", new Document("anzahlUsbPorts", computer.getSchnittstelle().getAnzahlUsbPorts())
                        .append("anzahlUsbcPorts", computer.getSchnittstelle().getAnzahlUsbcPorts())
                        .append("anzahlHdmiPorts", computer.getSchnittstelle().getAnzahlHdmiPorts())
                        .append("anzahlDpPorts", computer.getSchnittstelle().getAnzahlDpPorts())
                        .append("anzahlRJ45Ports", computer.getSchnittstelle().getAnzahlRJ45Ports()));
        collection.insertOne(computerDoc);
    }

    public void deleteComputer(ObjectId computerId) {
        collection.deleteOne(Filters.eq("_id", computerId));
    }

    private Computer documentToComputer(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        String hersteller = doc.getString("hersteller");
        String modell = doc.getString("modell");
        String arbeitsspeicher = doc.getString("arbeitsspeicher");
        String cpu = doc.getString("cpu");
        String massenspeicher = doc.getString("massenspeicher");
        String typ = doc.getString("typ");
        Double einzelpreis = doc.getDouble("einzelpreis");

        Document schnittstellenDoc = (Document) doc.get("schnittstellen");
        int anzahlUsbPorts = schnittstellenDoc.getInteger("anzahlUsbPorts");
        int anzahlUsbcPorts = schnittstellenDoc.getInteger("anzahlUsbcPorts");
        int anzahlHdmiPorts = schnittstellenDoc.getInteger("anzahlHdmiPorts");
        int anzahlDpPorts = schnittstellenDoc.getInteger("anzahlDpPorts");
        int anzahlRJ45Ports = schnittstellenDoc.getInteger("anzahlRJ45Ports");
        Schnittstelle schnittstelle = new Schnittstelle(anzahlUsbPorts, anzahlUsbcPorts, anzahlHdmiPorts, anzahlDpPorts, anzahlRJ45Ports);

        return new Computer(id, hersteller, modell, arbeitsspeicher, cpu, massenspeicher, typ, einzelpreis, schnittstelle);
    }

    public ArrayList<Computer> getAllComputer() {
        ArrayList<Computer> computerList = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document computerDoc = cursor.next();
                computerList.add(documentToComputer(computerDoc));
            }
        } finally {
            cursor.close();
        }
        return computerList;
    }

    public Computer getComputerByIndex(int index) {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    return documentToComputer(doc);
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public void updateComputer(Computer computer) {
        Document doc = new Document()
                .append("hersteller", computer.getHersteller())
                .append("modell", computer.getModell())
                .append("arbeitsspeicher", computer.getArbeitsspeicher())
                .append("cpu", computer.getCpu())
                .append("massenspeicher", computer.getMassenspeicher())
                .append("typ", computer.getTyp())
                .append("einzelpreis", computer.getEinzelpreis())
                .append("schnittstellen", new Document("anzahlUsbPorts", computer.getSchnittstelle().getAnzahlUsbPorts())
                        .append("anzahlUsbcPorts", computer.getSchnittstelle().getAnzahlUsbcPorts())
                        .append("anzahlHdmiPorts", computer.getSchnittstelle().getAnzahlHdmiPorts())
                        .append("anzahlDpPorts", computer.getSchnittstelle().getAnzahlDpPorts())
                        .append("anzahlRJ45Ports", computer.getSchnittstelle().getAnzahlRJ45Ports()));
        collection.updateOne(Filters.eq("_id", computer.getId()), new Document("$set", doc));
    }

    // Bestellungen;
    public void addBestellung(Bestellung bestellung) {
        Document bestellungDoc = new Document("bestellnummer", bestellung.getBestellnummer())
                .append("bestelldatum", bestellung.getBestelldatum())
                .append("total", bestellung.getTotal())
                .append("kundenId", bestellung.getKunde().getId())
                .append("vorname", bestellung.getKunde().getVorname())
                .append("nachname", bestellung.getKunde().getNachname());


        ArrayList<Document> bestellPositionenDocs = new ArrayList<>();
        for (Bestellposition position : bestellung.getBestellPositionen()) {
            Document positionDoc = new Document("computer", position.getComputer().getId())
                    .append("hersteller", position.getComputer().getHersteller())
                    .append("modell", position.getComputer().getModell())
                    .append("preis", position.getPreis())
                    .append("stueckzahl", position.getStueckzahl());
            bestellPositionenDocs.add(positionDoc);
        }
        bestellungDoc.append("bestellPositionen", bestellPositionenDocs);

        collection.insertOne(bestellungDoc);
    }

    public ArrayList<Bestellung> getAllBestellungen() {
        ArrayList<Bestellung> bestellungen = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document bestellungDoc = cursor.next();
                bestellungen.add(documentToBestellung(bestellungDoc));
            }
        }
        return bestellungen;
    }

    private Bestellung documentToBestellung(Document bestellungDoc) {
        ObjectId id = bestellungDoc.getObjectId("_id");
        int bestellnummer = bestellungDoc.getInteger("bestellnummer");
        Date bestelldatum = bestellungDoc.getDate("bestelldatum");
        double total = bestellungDoc.getDouble("total");

        ObjectId kundeId = bestellungDoc.getObjectId("kunde");
        String nachname = bestellungDoc.getString("nachname");
        String vorname = bestellungDoc.getString("vorname");

        Kunde kunde = new Kunde(kundeId, nachname, vorname);

        ArrayList<Bestellposition> bestellPositionen = new ArrayList<>();
        ArrayList<Document> positionenDocs = bestellungDoc.get("bestellPositionen", ArrayList.class);

        if (positionenDocs != null) {
            for (Document positionDoc : positionenDocs) {
                String hersteller = positionDoc.getString("hersteller");
                String modell = positionDoc.getString("modell");
                double preis = positionDoc.getDouble("preis");
                int stueckzahl = positionDoc.getInteger("stueckzahl");


                Computer computer = new Computer(hersteller, modell);
                Bestellposition bestellposition = new Bestellposition(computer, preis, stueckzahl);
                bestellPositionen.add(bestellposition);
            }
        }

        return new Bestellung(id, bestelldatum, total, bestellPositionen, kunde);
    }

    public void deleteBestellung(ObjectId id) {
        collection.deleteOne(Filters.eq("_id", id));
    }

    public Bestellung getBestellungByIndex(int index) {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    return documentToBestellung(doc);
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public void updateBestellung(Bestellung bestellung) {
        Document query = new Document("_id", bestellung.getBestellungId());

        Document bestellungDoc = new Document("bestellnummer", bestellung.getBestellnummer())
                .append("bestelldatum", bestellung.getBestelldatum())
                .append("total", bestellung.getTotal())
                .append("kundenId", bestellung.getKunde().getId())
                .append("vorname", bestellung.getKunde().getVorname())
                .append("nachname", bestellung.getKunde().getNachname());

        ArrayList<Document> bestellPositionenDocs = new ArrayList<>();
        for (Bestellposition position : bestellung.getBestellPositionen()) {
            Document positionDoc = new Document("computer", position.getComputer().getId())
                    .append("hersteller", position.getComputer().getHersteller())
                    .append("modell", position.getComputer().getModell())
                    .append("preis", position.getPreis())
                    .append("stueckzahl", position.getStueckzahl());
            bestellPositionenDocs.add(positionDoc);
        }
        bestellungDoc.append("bestellPositionen", bestellPositionenDocs);

        Document updateQuery = new Document("$set", bestellungDoc);

        collection.updateOne(query, updateQuery);
    }



}
