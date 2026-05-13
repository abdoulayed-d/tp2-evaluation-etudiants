package calcultableau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe chargée de gérer les notes des étudiants.
 *
 * Elle permet :
 * - d'ajouter des notes ;
 * - de compter le nombre de notes ;
 * - de calculer la moyenne ;
 * - de trier les notes ;
 * - de calculer la médiane.
 *
 * Cette classe ne contient pas de main().
 * Le main() sera placé dans une autre classe appelée Principale.
 */
public class CalculTab {

    /**
     * Liste contenant les notes des étudiants.
     *
     * On utilise une ArrayList au lieu d'un tableau fixe int[],
     * car le TP demande de remplacer le tableau par une collection.
     */
    private final List<Integer> notes;

    /**
     * Constructeur de la classe.
     *
     * Il initialise une liste vide de notes.
     */
    public CalculTab() {
        this.notes = new ArrayList<>();
    }

    /**
     * Ajoute une note dans la liste.
     *
     * @param note la note à ajouter
     */
    public void ajouterNote(int note) {
        notes.add(note);
    }

    /**
     * Retourne le nombre de notes saisies.
     *
     * @return le nombre de notes
     */
    public int getNombreNotes() {
        return notes.size();
    }

    /**
     * Calcule la moyenne des notes.
     *
     * Si aucune note n'a été saisie, on retourne 0
     * pour éviter une division par zéro.
     *
     * @return la moyenne des notes
     */
    public double calculerMoyenne() {
        if (notes.isEmpty()) {
            return 0;
        }

        int somme = 0;

        for (int note : notes) {
            somme += note;
        }

        return (double) somme / notes.size();
    }

    /**
     * Calcule la médiane des notes.
     *
     * La médiane est la valeur située au milieu
     * lorsque les notes sont triées.
     *
     * Exemple avec un nombre impair de notes :
     * notes triées : 10, 12, 15
     * médiane : 12
     *
     * Exemple avec un nombre pair de notes :
     * notes triées : 10, 12, 14, 16
     * médiane : (12 + 14) / 2 = 13
     *
     * @return la médiane des notes
     */
    public double calculerMediane() {
        if (notes.isEmpty()) {
            return 0;
        }

        /*
         * On crée une copie de la liste.
         * Cela évite de modifier l'ordre original des notes saisies.
         */
        List<Integer> notesTriees = new ArrayList<>(notes);

        /*
         * On trie les notes dans l'ordre croissant.
         */
        Collections.sort(notesTriees);

        int taille = notesTriees.size();
        int milieu = taille / 2;

        /*
         * Si le nombre de notes est impair,
         * la médiane est directement l'élément du milieu.
         */
        if (taille % 2 == 1) {
            return notesTriees.get(milieu);
        }

        /*
         * Si le nombre de notes est pair,
         * la médiane est la moyenne des deux éléments du milieu.
         */
        return (notesTriees.get(milieu - 1) + notesTriees.get(milieu)) / 2.0;
    }

    /**
     * Retourne une copie triée des notes.
     *
     * On ne retourne pas directement la liste originale
     * pour protéger les données internes de la classe.
     *
     * @return une liste contenant les notes triées
     */
    public List<Integer> getNotesTriees() {
        List<Integer> notesTriees = new ArrayList<>(notes);
        Collections.sort(notesTriees);
        return notesTriees;
    }

    /**
     * Retourne une représentation textuelle des notes.
     *
     * Le TP conseille d'utiliser StringBuilder pour construire
     * proprement les chaînes de caractères.
     *
     * @return une chaîne contenant les notes
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Notes : ");

        for (int note : notes) {
            builder.append(note).append(" ");
        }

        return builder.toString();
    }
}