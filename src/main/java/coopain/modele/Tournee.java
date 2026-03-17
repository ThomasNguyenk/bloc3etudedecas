package coopain.modele;
import java.util.ArrayList;

public class Tournee {
    private ArrayList<Visite> lesVisites = new ArrayList<>();
    public void ajouterVisite(Visite v) { lesVisites.add(v); }
    public ArrayList<Visite> getLesVisites() { return lesVisites; }
}