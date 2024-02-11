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
                    displayCustomerById();
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

        // UUID für die Kunden-ID generieren
        String kundenId = UUID.randomUUID().toString();

        // Kundenobjekt erstellen und hinzufügen
        Kunde newCustomer = new Kunde(kundenId, geschlecht, nachname, vorname, telefon, email, sprache, geburtsdatum, adresse);
        kundenRepo.insert(newCustomer);
        System.out.println("Neuer Kunde hinzugefügt.");
    }



    private void updateCustomer() {

    }

    private void deleteCustomer() {
        System.out.print("Geben Sie die Kunden-ID ein: ");
        String customerId = scanner.nextLine();
        kundenRepo.delete(customerId);
        System.out.println("Kunde gelöscht.");
    }
}
