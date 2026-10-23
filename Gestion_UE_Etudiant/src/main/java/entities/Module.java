package entities;

import java.util.Objects;

public class Module {
    private String matricule;
    private String nom;
    private int coefficient;
    private int volumeHoraire;
    private TypeModule type;
    private UniteEnseignement uniteEnseignement; // Référence vers l'UE associée (1 UE -> * Modules)

    public Module() {
    }

    // Constructeurs
    public Module(String matricule, String nom, int coefficient, int volumeHoraire, TypeModule type) {
        this.matricule = matricule;
        this.nom = nom;
        this.coefficient = coefficient;
        this.volumeHoraire = volumeHoraire;
        this.type = type;
    }

    public Module(String matricule, String nom, int coefficient, int volumeHoraire, TypeModule type,
                  UniteEnseignement uniteEnseignement) {
        this(matricule, nom, coefficient, volumeHoraire, type);
        this.uniteEnseignement = uniteEnseignement;
    }

    // Enumération TypeModule
    public enum TypeModule {
        TRANSVERSAL,
        PROFESSIONNEL,
        RECHERCHE
    }

    // Getters et Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getVolumeHoraire() {
        return volumeHoraire;
    }

    public void setVolumeHoraire(int volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }

    public TypeModule getType() {
        return type;
    }

    public void setType(TypeModule type) {
        this.type = type;
    }

    public UniteEnseignement getUniteEnseignement() {
        return uniteEnseignement;
    }

    public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
        this.uniteEnseignement = uniteEnseignement;
    }

    /*
     * equals() est utilisée par la plupart des collections pour déterminer
     * si une collection contient un élément donné (ex: List.contains).
     * Deux modules sont égaux s'ils ont le même matricule (identifiant métier).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return Objects.equals(matricule, module.matricule);
    }

    /*
     * hashCode() est utilisée pour déterminer où stocker et où chercher
     * l'objet en interne (HashMap, HashSet...). Doit être cohérente avec equals().
     */
    @Override
    public int hashCode() {
        return Objects.hash(matricule);
    }
}
