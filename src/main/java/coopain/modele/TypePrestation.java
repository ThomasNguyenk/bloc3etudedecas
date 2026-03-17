package coopain.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "TypePrestation")
public class TypePrestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private float prixForfaitaire;

    // Constructeur vide requis par Hibernate
    public TypePrestation() {}

    public TypePrestation(String libelle, float prixForfaitaire) {
        this.libelle = libelle;
        this.prixForfaitaire = prixForfaitaire;
    }

    public String getLibelle() { return libelle; }
    public float getPrixForfaitaire() { return prixForfaitaire; }

    @Override
    public String toString() {
        return libelle + " (" + prixForfaitaire + " €)";
    }
}