package coopain.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GestionTourneeTest {
    private Visite v1, v2;
    private TypePrestation tp1, tp2;
    private Tournee t1;
    private GestionTournee gt;

    @BeforeEach
    void setUp() {
        tp1 = new TypePrestation("Insémination", 100);
        tp2 = new TypePrestation("Echographie", 10);
        t1 = new Tournee();
        gt = new GestionTournee(t1);

        // Visite 1 : Georges Duboeuf (3 inséminations, 1 échographie)
        v1 = new Visite();
        v1.ajouterPrestationVisite(tp1, 3); // 3 * 100 = 300
        v1.ajouterPrestationVisite(tp2, 1); // 1 * 10 = 10

        // Visite 2 : Marguerite Cow (1 insémination)
        v2 = new Visite();
        v2.ajouterPrestationVisite(tp1, 1); // 1 * 100 = 100
    }

    @Test
    void testCATournee() {
        // 1. Vérification que le CA initial est nul
        assertEquals(0, gt.CATournee(), "Le CA initial doit être 0");

        // 2. Vérification après la 1ère visite (300 + 10 = 310)
        gt.ajouterVisite(v1);
        assertEquals(310.0f, gt.CATournee(), "Erreur après la 1ère visite");

        // 3. Vérification après la 2ème visite (310 + 100 = 410)
        gt.ajouterVisite(v2);
        assertEquals(410.0f, gt.CATournee(), "Erreur sur le CA final de la tournée");
    }
}
@Test
void testAjoutZeroActe() {
    // 1. On crée une visite factice
    Visite visiteZero = new Visite();

    // 2. On tente d'ajouter 0 échographie (qui coûte 10€ l'unité)
    visiteZero.ajouterPrestationVisite(tp2, 0);

    // 3. On ajoute la visite à la tournée
    gt.ajouterVisite(visiteZero);

    // 4. On vérifie (Assertion) que le CA reste strictement à 0
    assertEquals(0.0f, gt.CATournee(), "L'ajout de 0 acte ne doit pas augmenter le CA");
}