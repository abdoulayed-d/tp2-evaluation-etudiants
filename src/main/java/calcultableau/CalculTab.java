package calcultableau;

import java.util.Scanner;

// Somme des éléments d'un tableau d'entiers
public class CalculTab {

    public static void main(String[] args) {
        int[] tab = new int[50];
        int i;
        int n = 0;
        int sum = 0;

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Veuillez entrer la taille du tableau");
            n = sc.nextInt();
        } while (n > 50);

        System.out.println("****DEBUT PROGRAMME****");

        for (i = 0; i < n; i++) {
            System.out.println("Veuillez entrer un nombre");
            tab[i] = sc.nextInt();
        }

        System.out.println("Les éléments du tableau sont : ");

        for (i = 0; i < n; i++) {
            System.out.println(tab[i]);
        }

        for (i = 0; i < n; i++) {
            sum += tab[i];
        }

        System.out.println("La somme des éléments est égale à " + sum);
        System.out.println("****FIN PROGRAMME****");

        sc.close();
    }
}