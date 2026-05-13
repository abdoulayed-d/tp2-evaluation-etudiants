package calcultableau;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests unitaires de la classe UtilisateurTab.
 */
class UtilisateurTabTest {

    @Test
    @DisplayName("Créer un utilisateur avec une adresse email valide")
    void givenValidEmail_whenCreateUtilisateurTab_thenUserIsCreated() {
        // Given
        String prenom = "Alain";
        String nom = "Dupont";
        String email = "alain.dupont@iut.fr";

        // When
        UtilisateurTab utilisateur = new UtilisateurTab(prenom, nom, email);

        // Then
        assertThat(utilisateur.getPrenom()).isEqualTo("Alain");
        assertThat(utilisateur.getNom()).isEqualTo("Dupont");
        assertThat(utilisateur.getEmail()).isEqualTo("alain.dupont@iut.fr");
    }

    @Test
    @DisplayName("Refuser la création d'un utilisateur avec une adresse email invalide")
    void givenInvalidEmail_whenCreateUtilisateurTab_thenThrowsException() {
        // Given
        String prenom = "Alain";
        String nom = "Dupont";
        String email = "alain.dupont-iut.fr";

        // When / Then
        assertThatThrownBy(() -> new UtilisateurTab(prenom, nom, email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("L'adresse email n'est pas valide.");
    }

    @Test
    @DisplayName("Vérifier qu'une adresse email correcte est reconnue comme valide")
    void givenValidEmail_whenEstEmailValide_thenReturnsTrue() {
        // Given
        String email = "alain.dupont@iut.fr";

        // When
        boolean resultat = UtilisateurTab.estEmailValide(email);

        // Then
        assertThat(resultat).isTrue();
    }

    @Test
    @DisplayName("Vérifier qu'une adresse email incorrecte est reconnue comme invalide")
    void givenInvalidEmail_whenEstEmailValide_thenReturnsFalse() {
        // Given
        String email = "alain.dupont-iut.fr";

        // When
        boolean resultat = UtilisateurTab.estEmailValide(email);

        // Then
        assertThat(resultat).isFalse();
    }

    @Test
    @DisplayName("Vérifier que l'affichage texte de l'utilisateur est correct")
    void givenUtilisateur_whenToString_thenReturnsFormattedText() {
        // Given
        UtilisateurTab utilisateur = new UtilisateurTab(
                "Alain",
                "Dupont",
                "alain.dupont@iut.fr"
        );

        // When
        String texte = utilisateur.toString();

        // Then
        assertThat(texte).isEqualTo("Alain, Dupont, alain.dupont@iut.fr");
    }
}