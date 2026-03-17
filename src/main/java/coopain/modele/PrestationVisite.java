package coopain.modele;

public class PrestationVisite {
    private TypePrestation leTypePrestation;
    private int nombreActes;

    public PrestationVisite(TypePrestation type, int nbActes) {
        this.leTypePrestation = type;
        this.nombreActes = nbActes;
    }
    public TypePrestation getLeTypePrestation() { return leTypePrestation; }
    public int getNombreActes() { return nombreActes; }
}