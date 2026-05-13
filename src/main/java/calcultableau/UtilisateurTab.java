package calcultableau;

/**
 * Représente l'utilisateur qui saisit les notes des étudiants.
 */
public class UtilisateurTab {

    private final String prenom;
    private final String nom;
    private final String email;

    public UtilisateurTab(String prenom, String nom, String email) {
        if (!estEmailValide(email)) {
            throw new IllegalArgumentException("L'adresse email n'est pas valide.");
        }

        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public static boolean estEmailValide(String email) {
        return email != null && email.matches("[\\w.-]+@[\\w.-]+\\.[a-z]{2,}");
    }

    @Override
    public String toString() {
        return prenom + ", " + nom + ", " + email;
    }
}