package View;

import Controller.Controller;

public class MenuView {

    public MenuView(){

    }

    public void displayComputerMenu() {
        System.out.println("1. Alle Computer anzeigen");
        System.out.println("2. Computer nach Index anzeigen");
        System.out.println("3. Neuen Computer erfassen");
        System.out.println("4. Computer aktualisieren");
        System.out.println("5. Computer löschen");
        System.out.println("6. Zurück zum Hauptmenü");
        System.out.print("Wählen Sie eine Option: ");
    }

    public void displayBestellungsMenu() {
        System.out.println("1. Alle Bestellungen anzeigen");
        System.out.println("2. Bestellung nach ID anzeigen");
        System.out.println("3. Neue Bestellung hinzufügen");
        System.out.println("4. Bestellung aktualisieren");
        System.out.println("5. Bestellung löschen");
        System.out.println("6. Zurück zur Hauptansicht");
        System.out.print("Wählen Sie eine Option: ");
    }

    public void displayCustomerMenu() {
        System.out.println("1. Alle Kunden anzeigen");
        System.out.println("2. Kunden nach ID anzeigen");
        System.out.println("3. Neuen Kunden hinzufügen");
        System.out.println("4. Kunden aktualisieren");
        System.out.println("5. Kunden löschen");
        System.out.println("6. Zurück zur Hauptansicht");
        System.out.print("Wählen Sie eine Option: ");
    }

    public void displayMenu() {
        System.out.println("Bitte wählen Sie eine Option:");
        System.out.println("1. Kunden anzeigen");
        System.out.println("2. Computer anzeigen");
        System.out.println("3. Bestellpositionen anzeigen");
        System.out.println("0. Programm beenden");
        System.out.print("Ihre Auswahl: ");
    }
}
