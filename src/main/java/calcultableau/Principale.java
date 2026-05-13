package calcultableau;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe principale de l'application.
 *
 * Elle contient la méthode main().
 * Son rôle est de gérer les interactions avec l'utilisateur :
 * - saisir le nombre d'étudiants ;
 * - saisir les notes ;
 * - vérifier les erreurs de saisie ;
 * - appeler les méthodes de CalculTab ;
 * - afficher le résultat final.
 */
public class Principale {

    /**
     * Nombre maximal d'étudiants autorisés.
     *
     * Cette limite reprend l'idée du code initial du TP,
     * qui utilisait un tableau de taille 50.
     */
    private static final int NOMBRE_MAX_ETUDIANTS = 50;

    /**
     * Note minimale autorisée.
     */
    private static final double NOTE_MIN = 0.0;

    /**
     * Note maximale autorisée.
     */
    private static final double NOTE_MAX = 20.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UtilisateurTab utilisateur = new UtilisateurTab(
                "Alain",
                "Dupont",
                "alain.dupont@iut.fr"
        );

        System.out.println("**** DEBUT PROGRAMME ****");

        boolean continuer = true;

        while (continuer) {
            CalculTab calculTab = new CalculTab();

            int nombreEtudiants = lireNombreEtudiants(scanner);

            for (int i = 0; i < nombreEtudiants; i++) {
                double note = lireNote(scanner, i + 1);
                calculTab.ajouterNote(note);
            }

            afficherResultat(utilisateur, calculTab);

            continuer = demanderNouvelleSaisie(scanner);
        }

        System.out.println("**** FIN PROGRAMME ****");

        scanner.close();
    }

    /**
     * Demande le nombre d'étudiants à l'utilisateur.
     *
     * Le nombre doit être compris entre 1 et 50.
     *
     * @param scanner l'objet Scanner utilisé pour la saisie
     * @return le nombre d'étudiants valide
     */
    private static int lireNombreEtudiants(Scanner scanner) {
        int nombreEtudiants;

        do {
            nombreEtudiants = lireEntier(scanner, "Veuillez entrer le nombre d'étudiants notés : ");

            if (nombreEtudiants <= 0) {
                System.out.println("Erreur : le nombre d'étudiants doit être supérieur à 0.");
            } else if (nombreEtudiants > NOMBRE_MAX_ETUDIANTS) {
                System.out.println("Erreur : le nombre d'étudiants ne doit pas dépasser "
                        + NOMBRE_MAX_ETUDIANTS + ".");
            }

        } while (nombreEtudiants <= 0 || nombreEtudiants > NOMBRE_MAX_ETUDIANTS);

        return nombreEtudiants;
    }

    /**
     * Demande une note à l'utilisateur.
     *
     * La note peut être entière ou décimale.
     * Exemples acceptés : 12, 12.5, 12,5.
     *
     * @param scanner l'objet Scanner utilisé pour la saisie
     * @param numeroEtudiant le numéro de l'étudiant en cours de saisie
     * @return la note valide
     */
    private static double lireNote(Scanner scanner, int numeroEtudiant) {
        double note;

        do {
            note = lireDouble(scanner, "Veuillez entrer la note de l'étudiant " + numeroEtudiant + " : ");

            if (note < NOTE_MIN || note > NOTE_MAX) {
                System.out.println("Erreur : la note doit être comprise entre 0 et 20.");
            }

        } while (note < NOTE_MIN || note > NOTE_MAX);

        return note;
    }

    /**
     * Demande à l'utilisateur s'il veut saisir les notes d'un autre groupe.
     *
     * Cette question est posée seulement après l'affichage de la moyenne
     * et de la médiane du groupe actuel.
     *
     * @param scanner l'objet Scanner utilisé pour la saisie
     * @return true si l'utilisateur veut continuer, false sinon
     */
    private static boolean demanderNouvelleSaisie(Scanner scanner) {
        while (true) {
            System.out.print("Voulez-vous saisir les notes d'un autre groupe ? (o/n) : ");

            String reponse = scanner.next().trim().toLowerCase();

            if (reponse.equals("o") || reponse.equals("oui")) {
                return true;
            }

            if (reponse.equals("n") || reponse.equals("non")) {
                return false;
            }

            System.out.println("Erreur : veuillez répondre par o pour oui ou n pour non.");
        }
    }

    /**
     * Demande à l'utilisateur s'il souhaite continuer la saisie.
     *
     * @param scanner l'objet Scanner utilisé pour la saisie
     * @return true si l'utilisateur veut continuer, false sinon
     */
    private static boolean demanderSiContinuer(Scanner scanner) {
        while (true) {
            System.out.print("Voulez-vous saisir une autre note ? (o/n) : ");

            String reponse = scanner.next().trim().toLowerCase();

            if (reponse.equals("o") || reponse.equals("oui")) {
                return true;
            }

            if (reponse.equals("n") || reponse.equals("non")) {
                return false;
            }

            System.out.println("Erreur : veuillez répondre par o pour oui ou n pour non.");
        }
    }

    /**
     * Lit un entier au clavier.
     *
     * Cette méthode évite que le programme plante si l'utilisateur
     * saisit du texte au lieu d'un nombre entier.
     *
     * @param scanner l'objet Scanner utilisé pour la saisie
     * @param message le message affiché à l'utilisateur
     * @return l'entier saisi
     */
    private static int lireEntier(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Erreur : vous devez saisir un nombre entier.");
            scanner.next();
        }
    }

    /**
     * Lit un nombre décimal au clavier.
     *
     * Cette méthode accepte le point et la virgule comme séparateur décimal.
     * Exemple : 11.5 et 11,5 sont tous les deux acceptés.
     *
     * @param scanner l'objet Scanner utilisé pour la saisie
     * @param message le message affiché à l'utilisateur
     * @return le nombre décimal saisi
     */
    private static double lireDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            String saisie = scanner.next();
            saisie = saisie.replace(",", ".");

            try {
                return Double.parseDouble(saisie);
            } catch (NumberFormatException e) {
                System.out.println("Erreur : vous devez saisir un nombre.");
            }
        }
    }

    /**
     * Affiche le résultat final de l'évaluation.
     *
     * @param utilisateur l'enseignant qui saisit les notes
     * @param calculTab l'objet contenant les notes et les calculs
     */
    private static void afficherResultat(UtilisateurTab utilisateur, CalculTab calculTab) {
        System.out.println();
        System.out.println("Résultat de l'évaluation");
        System.out.println("------------------------");
        System.out.println("Utilisateur : " + utilisateur);
        System.out.println("Date de l'examen : " + LocalDate.now());
        System.out.println("Nombre d'étudiants : " + calculTab.getNombreNotes());
        System.out.println("Notes triées : " + calculTab.getNotesTriees());
        System.out.printf("Moyenne : %.2f%n", calculTab.calculerMoyenne());
        System.out.printf("Médiane : %.2f%n", calculTab.calculerMediane());
    }
}