package Controller;

import Model.Adresse;
import Model.Kunde;
import Model.KundenRepo;
import java.util.UUID;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class KundenController {
    private KundenRepo kundenRepo;
    private Scanner scanner;

    public KundenController() {
        this.kundenRepo = new KundenRepo();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
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

    private void displayMenu() {
        System.out.println("1. Alle Kunden anzeigen");
        System.out.println("2. Kunden nach ID anzeigen");
        System.out.println("3. Neuen Kunden hinzufügen");
        System.out.println("4. Kunden aktualisieren");
        System.out.println("5. Kunden löschen");
        System.out.println("6. Programm beenden");
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

    /*
    private void displayCustomerById() {
        System.out.print("Geben Sie die Kunden-ID ein: ");
        String customerId = scanner.nextLine();
        Kunde kunde = kundenRepo.getById(customerId);
        if (kunde != null) {
            System.out.println(kunde);
        } else {
            System.out.println("Kein Kunde mit dieser ID gefunden.");
        }
    }

     */



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

        // Adresse erfassen
        System.out.println("Adresse eingeben:");
        System.out.print("Straße: ");
        String strasse = scanner.nextLine();
        System.out.print("PLZ: ");
        String plz = scanner.nextLine();
        System.out.print("Ort: ");
        String ort = scanner.nextLine();

        // Adresse erstellen
        Adresse adresse = new Adresse(strasse, plz, ort);

        Kunde newCustomer = new Kunde(geschlecht, nachname, vorname, telefon, email, sprache, geburtsdatum, adresse);
        kundenRepo.insert(newCustomer);
        System.out.println("Neuer Kunde hinzugefügt.");
    }



    private void updateCustomer() {
        System.out.print("Geben Sie den Index des Kunden ein, den Sie aktualisieren möchten: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

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
        scanner.nextLine(); // Consume newline character
        kundenRepo.deleteByIndex(index);
        System.out.println("Kunde gelöscht.");
    }

}
