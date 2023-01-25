package Diar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Diar {
    //Vytvoření dvou statických proměnných
    public static DateTimeFormatter FORMAT_DATA = DateTimeFormatter.ofPattern("d'.'M'.'y H:mm");
    public static DateTimeFormatter FORMAT_DATA_BEZ_CASU = DateTimeFormatter.ofPattern("d'.'M'.'y");

    private Databaze databaze;

    // instance scanneru pro čtení vstupu z konzole
    private Scanner sc = new Scanner(System.in);

    public Diar() {
        databaze = new Databaze();
    }

    private LocalDateTime zjistiDatumCas() {
        System.out.println("Zadejte datum a čas ve tvaru [19.01.2023 12:00]:");
        while (true) {
            try {
                return LocalDateTime.parse(sc.nextLine(), FORMAT_DATA);
            } catch (Exception ex) {
                System.out.println("Nesprávně zadáno, zadejte prosím znovu");
            }
        }
    }

    private LocalDateTime zjistiDatum() {
        System.out.println("Zadejte datum ve tvaru [19.01.2023]");
        while (true) {
            try {
                return LocalDate.parse(sc.nextLine(),FORMAT_DATA_BEZ_CASU).atStartOfDay();
            } catch (Exception ex) {
                System.out.println("Nesprávně zadáno, zadejte prosím znovu");
            }
        }
    }

    public void vypisZaznamy(LocalDateTime den) {
        ArrayList<Zaznam> zaznamy = databaze.najdiZaznamy(den, false);
        for (Zaznam z : zaznamy) {
            System.out.println(z);
        }
    }

    //
    public void pridejZaznam() {
        LocalDateTime datumCas = zjistiDatumCas();
        System.out.println("Zadejte text záznamu:");
        String text = sc.nextLine();
        databaze.pridejZaznam(datumCas, text);
    }

    public void vyhledejZaznamy() {
        // Zadání data uživatelem
        LocalDateTime datumCas = zjistiDatum();
        // Vyhledání záznamů
        ArrayList<Zaznam> zaznamy = databaze.najdiZaznamy(datumCas, false);
        // Výpis záznamů
        if (zaznamy.size() > 0) {
            System.out.println("Nalezeny tyto záznamy: ");
            for (Zaznam z : zaznamy) {
                System.out.println(z);
            }
        } else {
            // Nenalezeno
            System.out.println("Nebyly nalezeny žádné záznamy.");
        }
    }

    public void vymazZaznamy() {
        System.out.println("Budou vymazány záznamy v daný den a hodinu");
        LocalDateTime datumCas = zjistiDatumCas();
        databaze.vymazZaznamy(datumCas);
    }

    public void vypisUvodniObrazovku() {
        System.out.println();
        System.out.println("Vítejte v diáři!☻\n-----------------");
        LocalDateTime dnes = LocalDateTime.now();
        System.out.printf("Dnes je: %s\n", FORMAT_DATA.format(dnes));
        System.out.println();
        // výpis hlavní obrazovky
        System.out.println("Dnešní poznámky:");
        System.out.println();
        vypisZaznamy(dnes);
        System.out.println();
        System.out.println("Poznámky/úkoly na další den:");
        LocalDateTime zitra = LocalDateTime.now().plusDays(1);
        vypisZaznamy(zitra);
        System.out.println();
    }
}
