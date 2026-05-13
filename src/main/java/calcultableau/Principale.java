package calcultableau;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe principale de l'application.
 *
 * Elle contient la méthode main().
 * Son rôle est de gérer les interactions avec l'utilisateur :
 * - afficher les messages ;
 * - demander les notes ;
 * - appeler les méthodes de CalculTab ;
 * - afficher le résultat final.
 */
public class Principale {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
         * Création de l'utilisateur imposé par le sujet.
         */
        UtilisateurTab utilisateur = new UtilisateurTab(
                "Alain",
                "Dupont",
                "alain.dupont@iut.fr"
        );

        /*
         * Création de l'objet qui va gérer les notes.
         */
        CalculTab calculTab = new CalculTab();

        System.out.println("**** DEBUT PROGRAMME ****");

        System.out.print("Veuillez entrer le nombre d'étudiants notés : ");
        int nombreEtudiants = scanner.nextInt();

        /*
         * Saisie des notes.
         */
        for (int i = 0; i < nombreEtudiants; i++) {
            System.out.print("Veuillez entrer la note de l'étudiant " + (i + 1) + " : ");
            int note = scanner.nextInt();
            calculTab.ajouterNote(note);
        }

        /*
         * Affichage des résultats.
         */
        System.out.println();
        System.out.println("Résultat de l'évaluation");
        System.out.println("------------------------");
        System.out.println("Utilisateur : " + utilisateur);
        System.out.println("Date de l'examen : " + LocalDate.now());
        System.out.println("Nombre d'étudiants : " + calculTab.getNombreNotes());
        System.out.println("Notes triées : " + calculTab.getNotesTriees());
        System.out.println("Moyenne : " + calculTab.calculerMoyenne());
        System.out.println("Médiane : " + calculTab.calculerMediane());

        System.out.println("**** FIN PROGRAMME ****");

        scanner.close();
    }
}