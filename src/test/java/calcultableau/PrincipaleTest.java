package calcultableau;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests de la classe Principale.
 *
 * Ces tests simulent les saisies clavier de l'utilisateur
 * et vérifient les messages affichés dans la console.
 */
class PrincipaleTest {

    private final PrintStream sortieOriginale = System.out;

    @AfterEach
    void restaurerSortieStandard() {
        System.setOut(sortieOriginale);
    }

    @Test
    @DisplayName("Saisir un groupe valide puis arrêter le programme")
    void givenValidGroup_whenMainRuns_thenDisplaysResults() {
        // Given
        String saisieUtilisateur = String.join(System.lineSeparator(),
                "3",
                "10",
                "12.5",
                "14",
                "n"
        );

        ByteArrayInputStream entreeSimulee = new ByteArrayInputStream(
                saisieUtilisateur.getBytes(StandardCharsets.UTF_8)
        );

        ByteArrayOutputStream sortieSimulee = new ByteArrayOutputStream();

        System.setIn(entreeSimulee);
        System.setOut(new PrintStream(sortieSimulee));

        // When
        Principale.main(new String[]{});

        // Then
        String sortie = sortieSimulee.toString(StandardCharsets.UTF_8);

        assertThat(sortie).contains("**** DEBUT PROGRAMME ****");
        assertThat(sortie).contains("Résultat de l'évaluation");
        assertThat(sortie).contains("Utilisateur : Alain, Dupont, alain.dupont@iut.fr");
        assertThat(sortie).contains("Nombre d'étudiants : 3");
        assertThat(sortie).contains("Notes triées : [10.0, 12.5, 14.0]");
        assertThat(sortie).contains("Moyenne");
        assertThat(sortie).contains("Médiane");
        assertThat(sortie).contains("**** FIN PROGRAMME ****");
    }

    @Test
    @DisplayName("Refuser un nombre d'étudiants invalide puis accepter une saisie correcte")
    void givenInvalidStudentCount_whenMainRuns_thenAsksAgain() {
        // Given
        String saisieUtilisateur = String.join(System.lineSeparator(),
                "-1",
                "51",
                "2",
                "10",
                "15",
                "n"
        );

        ByteArrayInputStream entreeSimulee = new ByteArrayInputStream(
                saisieUtilisateur.getBytes(StandardCharsets.UTF_8)
        );

        ByteArrayOutputStream sortieSimulee = new ByteArrayOutputStream();

        System.setIn(entreeSimulee);
        System.setOut(new PrintStream(sortieSimulee));

        // When
        Principale.main(new String[]{});

        // Then
        String sortie = sortieSimulee.toString(StandardCharsets.UTF_8);

        assertThat(sortie).contains("Erreur : le nombre d'étudiants doit être supérieur à 0.");
        assertThat(sortie).contains("Erreur : le nombre d'étudiants ne doit pas dépasser 50.");
        assertThat(sortie).contains("Nombre d'étudiants : 2");
        assertThat(sortie).contains("Notes triées : [10.0, 15.0]");
    }

    @Test
    @DisplayName("Refuser les notes invalides puis accepter les notes valides")
    void givenInvalidGrades_whenMainRuns_thenAsksAgain() {
        // Given
        String saisieUtilisateur = String.join(System.lineSeparator(),
                "2",
                "26",
                "-1",
                "11,5",
                "abc",
                "12.5",
                "n"
        );

        ByteArrayInputStream entreeSimulee = new ByteArrayInputStream(
                saisieUtilisateur.getBytes(StandardCharsets.UTF_8)
        );

        ByteArrayOutputStream sortieSimulee = new ByteArrayOutputStream();

        System.setIn(entreeSimulee);
        System.setOut(new PrintStream(sortieSimulee));

        // When
        Principale.main(new String[]{});

        // Then
        String sortie = sortieSimulee.toString(StandardCharsets.UTF_8);

        assertThat(sortie).contains("Erreur : la note doit être comprise entre 0 et 20.");
        assertThat(sortie).contains("Erreur : vous devez saisir un nombre.");
        assertThat(sortie).contains("Nombre d'étudiants : 2");
        assertThat(sortie).contains("Notes triées : [11.5, 12.5]");
    }

    @Test
    @DisplayName("Saisir deux groupes successifs puis arrêter le programme")
    void givenTwoGroups_whenMainRuns_thenDisplaysTwoResults() {
        // Given
        String saisieUtilisateur = String.join(System.lineSeparator(),
                "2",
                "10",
                "12",
                "o",
                "3",
                "8",
                "14",
                "18",
                "n"
        );

        ByteArrayInputStream entreeSimulee = new ByteArrayInputStream(
                saisieUtilisateur.getBytes(StandardCharsets.UTF_8)
        );

        ByteArrayOutputStream sortieSimulee = new ByteArrayOutputStream();

        System.setIn(entreeSimulee);
        System.setOut(new PrintStream(sortieSimulee));

        // When
        Principale.main(new String[]{});

        // Then
        String sortie = sortieSimulee.toString(StandardCharsets.UTF_8);

        assertThat(sortie).contains("Notes triées : [10.0, 12.0]");
        assertThat(sortie).contains("Notes triées : [8.0, 14.0, 18.0]");
        assertThat(sortie).contains("**** FIN PROGRAMME ****");
    }
}