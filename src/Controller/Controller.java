package Controller;

import Model.*;

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

    public Controller() {
        this.bestellungRepo = new BestellungRepo();
        this.kundenRepo = new KundenRepo();
        this.computerRepo = new ComputerRepo();
        this.scanner = new Scanner(System.in);
    }

    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
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

    public static void displayMenu() {
        System.out.println("Bitte wählen Sie eine Option:");
        System.out.println("1. Kunden anzeigen");
        System.out.println("2. Computer anzeigen");
        System.out.println("3. Bestellpositionen anzeigen");
        System.out.println("0. Programm beenden");
        System.out.print("Ihre Auswahl: ");
    }

    public void startCustomerOptions() {
        boolean running = true;
        while (running) {
            displayCustomerMenu();
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

    private void displayCustomerMenu() {
        System.out.println("1. Alle Kunden anzeigen");
        System.out.println("2. Kunden nach ID anzeigen");
        System.out.println("3. Neuen Kunden hinzufügen");
        System.out.println("4. Kunden aktualisieren");
        System.out.println("5. Kunden löschen");
        System.out.println("6. Zurück zur Hauptansicht");
        System.out.print("Wählen Sie eine Option: ");
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

            // KundenRepo aktualisieren
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

    public void startComputerOptions() {
        boolean running = true;
        while (running) {
            displayComputerMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    displayAllComputers();
                    break;
                case 2:
                    // Computer via ID ausgeben
                    break;
                case 3:
                    // Methode Computer hinzufügen
                    addNewComputer();

                    break;
                case 4:
                    // Methode Computer aktualisieren
                    break;
                case 5:
                    // Methode Computer löschen
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Option. Bitte wählen Sie erneut.");
            }
        }
    }
    private void displayComputerMenu() {
        System.out.println("1. Alle Computer anzeigen");
        System.out.println("2. Computer nach Index anzeigen");
        System.out.println("3. Neuen Computer erfassen");
        System.out.println("4. Computer aktualisieren");
        System.out.println("5. Computer löschen");
        System.out.println("6. Zurück zum Hauptmenü");
        System.out.print("Wählen Sie eine Option: ");
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



}
