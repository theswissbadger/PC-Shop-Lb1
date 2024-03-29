package Controller;

import Model.*;
import View.MenuView;
import org.bson.types.ObjectId;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Controller {
    private KundenRepo kundenRepo;
    private ComputerRepo computerRepo;
    private BestellungRepo bestellungRepo;
    private Scanner scanner;

    private MenuView menuView;

    //Konstruktor instanziert jeweils die neuen Klassen, um sie im Controller benutzen zu können.


    public Controller() {
        this.bestellungRepo = new BestellungRepo();
        this.kundenRepo = new KundenRepo();
        this.computerRepo = new ComputerRepo();
        this.menuView = new MenuView();
        this.scanner = new Scanner(System.in);
    }

    // printMenu zeigt das Hauptmenü an
    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            menuView.displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    startCustomerOptions();
                    break;
                case 2:
                    startComputerOptions();
                    break;
                case 3:
                    startBestellungOptions();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte wählen Sie erneut.");
            }
        }

        scanner.close();
    }

    // startCustomerOptions startet die Kundenoptionen, um die Kundendaten zu ändern
    public void startCustomerOptions() {
        boolean running = true;
        while (running) {
            menuView.displayCustomerMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    displayAllCustomers();
                    break;
                case 2:
                    displayCustomerByIndex();
                    break;
                case 3:
                    addNewCustomer();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    deleteCustomer();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte wählen Sie erneut.");
            }
        }
    }

    // startComputerOptions startet die Computeroptionen, um alle Daten eines Computers zu ändern.
    public void startComputerOptions() {
        boolean running = true;
        while (running) {
            menuView.displayComputerMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    displayAllComputers();
                    break;
                case 2:
                    // Computer via ID ausgeben
                    displayComputerbyId();
                    break;
                case 3:
                    // Methode Computer hinzufügen
                    addNewComputer();

                    break;
                case 4:
                    // Methode Computer aktualisieren
                    updateComputer();
                    break;
                case 5:
                    // Methode Computer löschen
                    deleteComputer();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte wählen Sie erneut.");
            }
        }
    }

    public void startBestellungOptions() {
        boolean running = true;
        while (running) {
            menuView.displayBestellungsMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    // Alle Bestellungen anzeigen
                    printAllBestellungen();
                    break;
                case 2:
                    // Bestellung nach Index anzeigen
                    displayBestellungByIndex();
                    break;
                case 3:
                    // Neue Bestelung hinzufügen
                    addBestellung();
                    break;
                case 4:
                    // Bestellung aktualisieren
                    updateBestellungViaConsole();
                    break;
                case 5:
                    // Bestellung löschen
                    deleteBestellung();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte wählen Sie erneut.");
            }
        }
    }


    private void displayAllCustomers() {
        ArrayList<Kunde> kunden = kundenRepo.getAll();
        for (Kunde kunde : kunden) {
            System.out.println(kunde);
        }
    }


    private void displayCustomerByIndex(){
        System.out.print("Geben Sie den Index an: ");
        int index = scanner.nextInt();
        Kunde kunde = kundenRepo.getByIndex(index);

        if (kunde != null) {
            System.out.println(kunde);
        } else {
            System.out.println("Kein Kunde mit dieser ID gefunden.");
        }
    }

    private void addNewCustomer() {
        System.out.print("Geben Sie das Geschlecht ein: ");
        String geschlecht = scanner.nextLine();
        System.out.print("Geben Sie den Nachnamen ein: ");
        String nachname = scanner.nextLine();
        System.out.print("Geben Sie den Vornamen ein: ");
        String vorname = scanner.nextLine();
        System.out.print("Geben Sie die Telefonnummer ein: ");
        String telefon = scanner.nextLine();
        System.out.print("Geben Sie die E-Mail-Adresse ein: ");
        String email = scanner.nextLine();
        System.out.print("Geben Sie die Sprache ein: ");
        String sprache = scanner.nextLine();
        System.out.print("Geben Sie das Geburtsdatum ein (Format: yyyy-MM-dd): ");
        String geburtsdatumStr = scanner.nextLine();
        Date geburtsdatum;
        try {
            geburtsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(geburtsdatumStr);
        } catch (ParseException e) {
            System.out.println("Ungültiges Datumsformat.");
            return;
        }

        System.out.println("Adresse eingeben:");
        System.out.print("Straße: ");
        String strasse = scanner.nextLine();
        System.out.print("PLZ: ");
        String plz = scanner.nextLine();
        System.out.print("Ort: ");
        String ort = scanner.nextLine();


        Adresse adresse = new Adresse(strasse, plz, ort);

        Kunde newCustomer = new Kunde(geschlecht, nachname, vorname, telefon, email, sprache, geburtsdatum, adresse);
        kundenRepo.insert(newCustomer);
        System.out.println("Neuer Kunde hinzugefügt.");
    }

    private void updateCustomer() {
        System.out.print("Geben Sie den Index des Kunden ein, den Sie aktualisieren möchten: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        Kunde kundeToUpdate = kundenRepo.getByIndex(index);
        if (kundeToUpdate != null) {
            System.out.println("Kunde gefunden. Geben Sie die neuen Daten ein:");

            System.out.print("Geben Sie das neue Geschlecht ein (leer lassen, um es nicht zu ändern): ");
            String geschlecht = scanner.nextLine();
            if (!geschlecht.isEmpty()) {
                kundeToUpdate.setGeschlecht(geschlecht);
            }

            System.out.print("Geben Sie den neuen Nachnamen ein (leer lassen, um ihn nicht zu ändern): ");
            String nachname = scanner.nextLine();
            if (!nachname.isEmpty()) {
                kundeToUpdate.setNachname(nachname);
            }

            System.out.print("Geben Sie den neuen Vornamen ein (leer lassen, um ihn nicht zu ändern): ");
            String vorname = scanner.nextLine();
            if (!vorname.isEmpty()) {
                kundeToUpdate.setVorname(vorname);
            }

            System.out.print("Geben Sie die neue Telefonnummer ein (leer lassen, um sie nicht zu ändern): ");
            String telefon = scanner.nextLine();
            if (!telefon.isEmpty()) {
                kundeToUpdate.setTelefon(telefon);
            }

            System.out.print("Geben Sie die neue E-Mail-Adresse ein (leer lassen, um sie nicht zu ändern): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                kundeToUpdate.setEmail(email);
            }

            System.out.print("Geben Sie die neue Sprache ein (leer lassen, um sie nicht zu ändern): ");
            String sprache = scanner.nextLine();
            if (!sprache.isEmpty()) {
                kundeToUpdate.setSprache(sprache);
            }

            System.out.print("Geben Sie das neue Geburtsdatum ein (Format: yyyy-MM-dd) (leer lassen, um es nicht zu ändern): ");
            String geburtsdatumStr = scanner.nextLine();
            if (!geburtsdatumStr.isEmpty()) {
                try {
                    Date geburtsdatum = new SimpleDateFormat("yyyy-MM-dd").parse(geburtsdatumStr);
                    kundeToUpdate.setGeburtsdatum(geburtsdatum);
                } catch (ParseException e) {
                    System.out.println("Ungültiges Datumsformat.");
                    return;
                }
            }

            System.out.println("Adresse aktualisieren (leer lassen, um sie nicht zu ändern):");
            System.out.print("Geben Sie die neue Straße ein: ");
            String strasse = scanner.nextLine();
            if (!strasse.isEmpty()) {
                kundeToUpdate.getAdresse().setStrasse(strasse);
            }

            System.out.print("Geben Sie die neue PLZ ein: ");
            String plz = scanner.nextLine();
            if (!plz.isEmpty()) {
                kundeToUpdate.getAdresse().setPlz(plz);
            }

            System.out.print("Geben Sie den neuen Ort ein: ");
            String ort = scanner.nextLine();
            if (!ort.isEmpty()) {
                kundeToUpdate.getAdresse().setOrt(ort);
            }


            kundenRepo.update(kundeToUpdate);
            System.out.println("Kunde aktualisiert.");
        } else {
            System.out.println("Kein Kunde mit diesem Index gefunden.");
        }
    }

    private void deleteCustomer() {
        System.out.print("Geben Sie den Index des Kunden ein, den Sie löschen möchten: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        kundenRepo.deleteByIndex(index);
        System.out.println("Kunde gelöscht.");
    }

    private void addNewComputer() {
        System.out.print("Geben Sie den Hersteller ein: ");
        String hersteller = scanner.nextLine();
        System.out.print("Geben Sie das Modell ein: ");
        String modell = scanner.nextLine();
        System.out.print("Geben Sie den arbeitsspeicher ein: ");
        String arbeitsspeicher = scanner.nextLine();
        System.out.print("Geben Sie die Cpu ein: ");
        String cpu = scanner.nextLine();
        System.out.print("Geben sie den Massenspeicher ein: ");
        String massenspeicher = scanner.nextLine();
        System.out.print("Geben Sie den Typ ein: ");
        String typ = scanner.nextLine();
        System.out.print("Geben Sie den Einzelpreis ein: ");
        double einzelpreis = scanner.nextDouble();


        System.out.println("Schnittstellen erfassen:");
        System.out.print("Anzahl USB Ports: ");
        int anzahlUsbPorts = scanner.nextInt();
        System.out.print("Anzahl USB C Ports: ");
        int anzahlUsbcPorts = scanner.nextInt();
        System.out.print("Anzhal HDMI Ports: ");
        int anzahlHdmiPorts = scanner.nextInt();
        System.out.println("Anzahl Display Port Ports: ");
        int anzahlDpPorts = scanner.nextInt();
        System.out.println("Anzahl RJ45 Ports: ");
        int anzahlRJ45Ports = scanner.nextInt();


        Schnittstelle schnittstelle = new Schnittstelle(anzahlUsbPorts, anzahlUsbcPorts, anzahlHdmiPorts, anzahlDpPorts, anzahlRJ45Ports);

        Computer newComputer = new Computer(hersteller, modell, arbeitsspeicher, cpu, massenspeicher, typ, einzelpreis, schnittstelle);
        computerRepo.insert(newComputer);
        System.out.println("Neuer Kunde hinzugefügt.");
    }

    private void displayAllComputers() {
        ArrayList<Computer> computers = computerRepo.getAllComputer();
        for (Computer computer : computers) {
            System.out.println(computer);
        }
    }

    private void displayComputerbyId(){
        System.out.print("Geben Sie den Index an: ");
        int index = scanner.nextInt();
        Computer computer = computerRepo.getComputerById(index);


        if (computer != null) {
            System.out.println(computer);
        } else {
            System.out.println("Keinen Computer mit dieser ID gefunden.");
        }
    }

    private void updateComputer() {
        System.out.print("Geben Sie die ID des Computers ein, den Sie aktualisieren möchten: ");
        int computerId = scanner.nextInt();
        scanner.nextLine();

        Computer computerToUpdate = computerRepo.getComputerById(computerId);
        if (computerToUpdate != null) {
            System.out.println("Computer gefunden. Geben Sie die neuen Daten ein:");

            System.out.print("Geben Sie den neuen Hersteller ein (leer lassen, um ihn nicht zu ändern): ");
            String hersteller = scanner.nextLine();
            if (!hersteller.isEmpty()) {
                computerToUpdate.setHersteller(hersteller);
            }

            System.out.print("Geben Sie das neue Modell ein (leer lassen, um es nicht zu ändern): ");
            String modell = scanner.nextLine();
            if (!modell.isEmpty()) {
                computerToUpdate.setModell(modell);
            }

            System.out.print("Geben Sie den neuen Arbeitsspeicher ein (leer lassen, um ihn nicht zu ändern): ");
            String arbeitsspeicher = scanner.nextLine();
            if (!arbeitsspeicher.isEmpty()) {
                computerToUpdate.setArbeitsspeicher(arbeitsspeicher);
            }

            System.out.print("Geben Sie die neue CPU ein (leer lassen, um sie nicht zu ändern): ");
            String cpu = scanner.nextLine();
            if (!cpu.isEmpty()) {
                computerToUpdate.setCpu(cpu);
            }

            System.out.print("Geben Sie den neuen Massenspeicher ein (leer lassen, um ihn nicht zu ändern): ");
            String massenspeicher = scanner.nextLine();
            if (!massenspeicher.isEmpty()) {
                computerToUpdate.setMassenspeicher(massenspeicher);
            }

            System.out.print("Geben Sie den neuen Typ ein (leer lassen, um ihn nicht zu ändern): ");
            String typ = scanner.nextLine();
            if (!typ.isEmpty()) {
                computerToUpdate.setTyp(typ);
            }

            System.out.print("Geben Sie den neuen Einzelpreis ein (leer lassen, um ihn nicht zu ändern): ");
            String einzelpreisStr = scanner.nextLine();
            if (!einzelpreisStr.isEmpty()) {
                double einzelpreis = Double.parseDouble(einzelpreisStr);
                computerToUpdate.setEinzelpreis(einzelpreis);
            }

            System.out.println("Schnittstellen aktualisieren (leer lassen, um sie nicht zu ändern):");
            System.out.print("Geben Sie die Anzahl der neuen USB-Ports ein: ");
            String anzahlUsbPortsStr = scanner.nextLine();
            if (!anzahlUsbPortsStr.isEmpty()) {
                int anzahlUsbPorts = Integer.parseInt(anzahlUsbPortsStr);
                computerToUpdate.getSchnittstelle().setAnzahlUsbPorts(anzahlUsbPorts);
            }

            System.out.print("Geben Sie die Anzahl der neuen USB-C-Ports ein: ");
            String anzahlUsbcPortsStr = scanner.nextLine();
            if (!anzahlUsbcPortsStr.isEmpty()) {
                int anzahlUsbcPorts = Integer.parseInt(anzahlUsbcPortsStr);
                computerToUpdate.getSchnittstelle().setAnzahlUsbcPorts(anzahlUsbcPorts);
            }

            System.out.print("Geben Sie die Anzahl der neuen HDMI-Ports ein: ");
            String anzahlHdmiPortsStr = scanner.nextLine();
            if (!anzahlHdmiPortsStr.isEmpty()) {
                int anzahlHdmiPorts = Integer.parseInt(anzahlHdmiPortsStr);
                computerToUpdate.getSchnittstelle().setAnzahlHdmiPorts(anzahlHdmiPorts);
            }

            System.out.print("Geben Sie die Anzahl der neuen DisplayPort-Ports ein: ");
            String anzahlDpPortsStr = scanner.nextLine();
            if (!anzahlDpPortsStr.isEmpty()) {
                int anzahlDpPorts = Integer.parseInt(anzahlDpPortsStr);
                computerToUpdate.getSchnittstelle().setAnzahlDpPorts(anzahlDpPorts);
            }

            System.out.print("Geben Sie die Anzahl der neuen RJ45-Ports ein: ");
            String anzahlRJ45PortsStr = scanner.nextLine();
            if (!anzahlRJ45PortsStr.isEmpty()) {
                int anzahlRJ45Ports = Integer.parseInt(anzahlRJ45PortsStr);
                computerToUpdate.getSchnittstelle().setAnzahlRJ45Ports(anzahlRJ45Ports);
            }


            computerRepo.update(computerToUpdate);

            System.out.println("Computer aktualisiert.");
        } else {
            System.out.println("Kein Computer mit dieser ID gefunden.");
        }
    }
    private void deleteComputer() {
        System.out.print("Geben Sie den Index des Computers ein, den Sie löschen möchten: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        computerRepo.deleteComputerByIndex(index);
    }

    public void addBestellung() {
        System.out.println("Welcher Kunde hat die Bestellung getätigt? (Index angeben!)");
        int kundenIndex = scanner.nextInt();

        Kunde kundeForBestellung = kundenRepo.getByIndex(kundenIndex);

        System.out.println("Geben Sie die Bestellnummer ein: ");
        int bestellnummer = scanner.nextInt();

        Date bestellDatum = new Date();

        System.out.println("Geben Sie die Bestellpositionen an:");

        ArrayList<Bestellposition> bestellpositionList = new ArrayList<>();
        double total = 0;

        boolean addMore = true;
        while (addMore) {
            System.out.println("Welcher PC wurde bestellt? (Index angeben!)");
            int computerIndex = scanner.nextInt();
            Computer computerForBestellung = computerRepo.getComputerById(computerIndex);
            double preis = computerForBestellung.getEinzelpreis();
            System.out.println("Wie viele Stücke wurden bestellt?");
            int stueckzahl = scanner.nextInt();

            Bestellposition bestellposition = new Bestellposition(computerForBestellung, preis, stueckzahl);
            bestellpositionList.add(bestellposition);

            total += preis * stueckzahl;

            System.out.println("Möchten Sie eine weitere Bestellposition hinzufügen? (ja/nein)");
            String response = scanner.next();
            addMore = response.equalsIgnoreCase("ja");

        }

        Bestellung bestellung = new Bestellung(bestellnummer, bestellDatum, total, bestellpositionList, kundeForBestellung);
        bestellungRepo.addBestellung(bestellung);
    }

    public void printAllBestellungen() {

        ArrayList<Bestellung> bestellungen = bestellungRepo.getAllBestellungen();
        for (Bestellung bestellung : bestellungen) {
            System.out.println("---------------------------------------------------");
            System.out.println("Bestellnummer: " + bestellung.getBestellnummer());
            System.out.println("Bestelldatum: " + bestellung.getBestelldatum());
            System.out.println("Total: " + bestellung.getTotal());
            System.out.println("Kunde: " + bestellung.getKunde().getNachname() + " " + bestellung.getKunde().getVorname());
            System.out.println("Bestellpositionen:");
            for (Bestellposition position : bestellung.getBestellPositionen()) {
                System.out.println("\tComputer: " + position.getComputer().getModell() + " " + position.getComputer().getHersteller());
                System.out.println("\tPreis: " + position.getPreis());
                System.out.println("\tStückzahl: " + position.getStueckzahl());
                System.out.println();

            }
        }
    }
    private void deleteBestellung() {
        System.out.print("Geben Sie den Index der Bestellung ein, den Sie löschen möchten: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        bestellungRepo.deleteBestellungByIndex(index);
    }

    private void displayBestellungByIndex(){
        System.out.print("Geben Sie den Index an: ");
        int index = scanner.nextInt();
        Bestellung bestellung = bestellungRepo.getBestellungbyIndex(index);

        if (bestellung != null) {
            System.out.println(bestellung);
        } else {
            System.out.println("Keine Bestellung mit dieser ID gefunden.");
        }
    }

    public void updateBestellungViaConsole() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Geben Sie den Index der zu aktualisierenden Bestellung ein: ");
        int index;
        if (scanner.hasNextInt()) {
            index = scanner.nextInt();
        } else {
            System.out.println("Ungültige Eingabe für den Index. Bitte geben Sie eine ganze Zahl ein.");
            return;
        }
        scanner.nextLine();


        Bestellung bestellung = bestellungRepo.getBestellungbyIndex(index);
        if (bestellung != null) {
            System.out.println("Bestellung gefunden. Geben Sie die neuen Daten ein:");


            System.out.print("Geben Sie die neue Bestellnummer ein (leer lassen, um sie nicht zu ändern): ");
            String neueBestellnummerStr = scanner.nextLine();
            if (!neueBestellnummerStr.isEmpty()) {
                try {
                    int neueBestellnummer = Integer.parseInt(neueBestellnummerStr);
                    bestellung.setBestellnummer(neueBestellnummer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe für die Bestellnummer. Bitte geben Sie eine ganze Zahl ein.");
                    return;
                }
            }


            System.out.print("Geben Sie den neuen Gesamtbetrag ein (leer lassen, um ihn nicht zu ändern): ");
            String neuerTotalStr = scanner.nextLine();
            if (!neuerTotalStr.isEmpty()) {
                try {
                    double neuerTotal = Double.parseDouble(neuerTotalStr);
                    bestellung.setTotal(neuerTotal);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe für den Gesamtbetrag. Bitte geben Sie eine Zahl ein.");
                    return;
                }
            }


            System.out.print("Geben Sie die ID des neuen Kunden ein (leer lassen, um ihn nicht zu ändern): ");
            String kundenIndexStr = scanner.nextLine();
            if (!kundenIndexStr.isEmpty()) {
                try {
                    int kundenIndex = Integer.parseInt(kundenIndexStr);
                    Kunde neuerKunde = kundenRepo.getByIndex(kundenIndex);
                    if (neuerKunde != null) {
                        bestellung.setKunde(neuerKunde);
                    } else {
                        System.out.println("Kunde mit dieser ID nicht gefunden. Kundeninformationen bleiben unverändert.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Eingabe für die Kunden-ID. Bitte geben Sie eine ganze Zahl ein.");
                    return;
                }
            }

            bestellungRepo.updateBestellung(bestellung);
            System.out.println("Bestellung erfolgreich aktualisiert.");
        } else {
            System.out.println("Keine Bestellung mit diesem Index gefunden.");
        }
    }





}
