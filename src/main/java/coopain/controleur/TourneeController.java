package coopain.controleur;

import coopain.modele.*;
import coopain.util.HibernateUtil; // Import de votre utilitaire Hibernate
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import java.util.List;

public class TourneeController {
    @FXML private ComboBox<TypePrestation> comboPrestations;
    @FXML private TextField txtNbActes;
    @FXML private Label lblTotal;
    @FXML private Label lblMessage;
    @FXML private TextArea txtHistorique;

    private GestionTournee gestionTournee;
    private Visite visiteCourante;

    @FXML
    public void initialize() {
        // 1. Initialisation sécurisée de la logique métier
        gestionTournee = new GestionTournee(new Tournee());
        visiteCourante = new Visite();
        gestionTournee.ajouterVisite(visiteCourante);

        // 2. Correction de l'appel Hibernate (utilisation d'une classe Util)
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<TypePrestation> liste = session.createQuery("from TypePrestation", TypePrestation.class).list();

            if (liste != null && !liste.isEmpty()) {
                comboPrestations.setItems(FXCollections.observableArrayList(liste));
                comboPrestations.getSelectionModel().selectFirst();
            } else {
                lblMessage.setText("Aucune prestation trouvée en base.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Important pour voir l'erreur réelle dans la console
            lblMessage.setText("Erreur BDD : Vérifiez la connexion MySQL.");
        }
    }

    @FXML
    public void handleAjouterActe() {
        lblMessage.setText("");
        try {
            // Vérification si le champ n'est pas vide avant le parse
            if (txtNbActes.getText().trim().isEmpty()) {
                lblMessage.setText("Veuillez saisir un nombre.");
                return;
            }

            int nbActes = Integer.parseInt(txtNbActes.getText().trim());
            TypePrestation typeSelectionne = comboPrestations.getValue();

            if (typeSelectionne != null && nbActes > 0) {
                // Ajout métier
                visiteCourante.ajouterPrestationVisite(typeSelectionne, nbActes);

                // Mise à jour UI
                lblTotal.setText("CA Total : " + gestionTournee.CATournee() + " €");
                txtHistorique.appendText("- " + nbActes + "x " + typeSelectionne.getLibelle() + "\n");

                // Reset du champ après ajout
                txtNbActes.clear();
            } else {
                lblMessage.setText("Sélectionnez une prestation et un nombre > 0.");
            }
        } catch (NumberFormatException ex) {
            lblMessage.setText("Erreur : Le nombre d'actes doit être un entier.");
        }
    }

    @FXML
    public void handleEffacer() {
        // 1. Vide la zone de texte à l'écran
        txtHistorique.clear();

        // 2. Réinitialise la logique métier (on repart à zéro)
        gestionTournee = new GestionTournee(new Tournee());
        visiteCourante = new Visite();
        gestionTournee.ajouterVisite(visiteCourante);

        // 3. Met à jour l'affichage du total et des messages
        lblTotal.setText("CA Total de la tournée : 0.0 €");
        lblMessage.setText("Historique et calculs réinitialisés.");
    }
}