package coopain.modele;

public class GestionTournee {
    private Tournee laTournee;

    public GestionTournee(Tournee uneTournee) {
        this.laTournee = uneTournee;
    }

    public void ajouterVisite(Visite uneVisite) {
        laTournee.ajouterVisite(uneVisite);
    }

    // Calcul du CA de toute la tournée
    public float CATournee() {
        float caTotal = 0;
        for (Visite v : laTournee.getLesVisites()) {
            caTotal += v.montantAFacturer();
        }
        return caTotal;
    }
}