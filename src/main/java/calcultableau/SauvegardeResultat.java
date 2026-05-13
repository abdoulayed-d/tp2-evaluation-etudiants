package calcultableau;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Classe chargée de sauvegarder les résultats d'une évaluation.
 *
 * Les résultats sont enregistrés dans un fichier CSV afin de pouvoir
 * être consultés ou réutilisés facilement.
 */
public class SauvegardeResultat {

    /**
     * Nom du fichier de sauvegarde.
     */
    private static final Path FICHIER_RESULTATS = Path.of("resultats-evaluations.csv");

    /**
     * Sauvegarde le résultat d'une évaluation dans un fichier CSV.
     *
     * Les informations sauvegardées sont :
     * prénom, nom, email, date de l'examen, nombre d'étudiants,
     * moyenne et médiane.
     *
     * @param utilisateur l'enseignant qui saisit les notes
     * @param calculTab l'objet contenant les notes et les calculs
     * @throws IOException si l'écriture dans le fichier échoue
     */
    public static void sauvegarder(UtilisateurTab utilisateur, CalculTab calculTab) throws IOException {
        boolean fichierExiste = Files.exists(FICHIER_RESULTATS);

        StringBuilder contenu = new StringBuilder();

        if (!fichierExiste) {
            contenu.append("prenom;nom;email;date_examen;nombre_etudiants;moyenne;mediane")
                    .append(System.lineSeparator());
        }

        contenu.append(utilisateur.getPrenom()).append(";")
                .append(utilisateur.getNom()).append(";")
                .append(utilisateur.getEmail()).append(";")
                .append(LocalDate.now()).append(";")
                .append(calculTab.getNombreNotes()).append(";")
                .append(String.format(Locale.US, "%.2f", calculTab.calculerMoyenne())).append(";")
                .append(String.format(Locale.US, "%.2f", calculTab.calculerMediane()))
                .append(System.lineSeparator());

        Files.writeString(
                FICHIER_RESULTATS,
                contenu.toString(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }
}