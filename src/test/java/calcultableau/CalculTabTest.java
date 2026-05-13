package calcultableau;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests unitaires de la classe CalculTab.
 */
class CalculTabTest {

    @Test
    @DisplayName("Calculer la moyenne avec plusieurs notes")
    void givenSeveralNotes_whenCalculerMoyenne_thenReturnsAverage() {
        // Given
        CalculTab calculTab = new CalculTab();
        calculTab.ajouterNote(10.0);
        calculTab.ajouterNote(12.0);
        calculTab.ajouterNote(14.0);

        // When
        double moyenne = calculTab.calculerMoyenne();

        // Then
        assertThat(moyenne).isEqualTo(12.0);
    }

    @Test
    @DisplayName("Calculer la médiane avec un nombre impair de notes")
    void givenOddNumberOfNotes_whenCalculerMediane_thenReturnsMiddleValue() {
        // Given
        CalculTab calculTab = new CalculTab();
        calculTab.ajouterNote(14.0);
        calculTab.ajouterNote(10.0);
        calculTab.ajouterNote(12.0);

        // When
        double mediane = calculTab.calculerMediane();

        // Then
        assertThat(mediane).isEqualTo(12.0);
    }

    @Test
    @DisplayName("Calculer la médiane avec un nombre pair de notes")
    void givenEvenNumberOfNotes_whenCalculerMediane_thenReturnsAverageOfTwoMiddleValues() {
        // Given
        CalculTab calculTab = new CalculTab();
        calculTab.ajouterNote(10.0);
        calculTab.ajouterNote(12.0);
        calculTab.ajouterNote(14.0);
        calculTab.ajouterNote(16.0);

        // When
        double mediane = calculTab.calculerMediane();

        // Then
        assertThat(mediane).isEqualTo(13.0);
    }

    @Test
    @DisplayName("Retourner zéro quand aucune note n'est saisie")
    void givenNoNote_whenCalculerMoyenneAndMediane_thenReturnsZero() {
        // Given
        CalculTab calculTab = new CalculTab();

        // When
        double moyenne = calculTab.calculerMoyenne();
        double mediane = calculTab.calculerMediane();

        // Then
        assertThat(moyenne).isZero();
        assertThat(mediane).isZero();
    }

    @Test
    @DisplayName("Retourner les notes triées dans l'ordre croissant")
    void givenUnsortedNotes_whenGetNotesTriees_thenReturnsSortedNotes() {
        // Given
        CalculTab calculTab = new CalculTab();
        calculTab.ajouterNote(15.5);
        calculTab.ajouterNote(8.0);
        calculTab.ajouterNote(12.5);

        // When
        List<Double> notesTriees = calculTab.getNotesTriees();

        // Then
        assertThat(notesTriees).containsExactly(8.0, 12.5, 15.5);
    }
}