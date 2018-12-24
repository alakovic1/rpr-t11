package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }

    public static void glavniGrad() {
        System.out.println("Unesite naziv drzave: ");
        Scanner ulaz = new Scanner(System.in);
        String naziv = ulaz.nextLine();
    }

    public static String ispisiGradove() {
        return null;
    }
}
