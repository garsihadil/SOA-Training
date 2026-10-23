package metiers;

import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UniteEnseignementBusiness {

    // Liste partagée par toutes les instances (JAX-RS crée une instance de ressource par requête).
    // Initialisée UNE seule fois (bloc static) pour que les ajouts/suppressions persistent entre les requêtes.
    private static final List<UniteEnseignement> unitesEnseignement = new CopyOnWriteArrayList<>();

    static {
        unitesEnseignement.add(new UniteEnseignement(1, "Informatique", "Mme Maroua Douiri", 6, 1));
        unitesEnseignement.add(new UniteEnseignement(2, "Mathématiques", "Mme Ines ElMejid", 5, 1));
        unitesEnseignement.add(new UniteEnseignement(3, "Physique", "Mme Sarra Abidi", 4, 2));
        unitesEnseignement.add(new UniteEnseignement(4, "Infographie", "Mme Oumeima Ibnelfkih", 3, 1));
        unitesEnseignement.add(new UniteEnseignement(5, "Chimie", "M. Mohamed Amine Chebbi", 4, 2));
    }

    public UniteEnseignementBusiness() {
    }

    // Récupérer une UE par son code
    public UniteEnseignement getUEByCode(int code) {
        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getCode() == code)
                return ue;
        }
        return null;
    }

    // Ajouter une UE (refusé si le code existe déjà)
    public boolean addUniteEnseignement(UniteEnseignement ue) {
        if (ue == null || getUEByCode(ue.getCode()) != null)
            return false;
        return unitesEnseignement.add(ue);
    }

    // Récupérer les UE d'un domaine
    public List<UniteEnseignement> getUEByDomaine(String domaine) {
        List<UniteEnseignement> liste = new ArrayList<>();
        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getDomaine().equalsIgnoreCase(domaine))
                liste.add(ue);
        }
        return liste;
    }

    // Récupérer les UE d'un semestre
    public List<UniteEnseignement> getUEBySemestre(int semestre) {
        List<UniteEnseignement> liste = new ArrayList<>();
        for (UniteEnseignement ue : unitesEnseignement) {
            if (ue.getSemestre() == semestre)
                liste.add(ue);
        }
        return liste;
    }

    // Supprimer une UE par son code
    public boolean deleteUniteEnseignement(int code) {
        return unitesEnseignement.removeIf(ue -> ue.getCode() == code);
    }

    // Modifier une UE par son code
    public boolean updateUniteEnseignement(int code, UniteEnseignement updatedUE) {
        for (int i = 0; i < unitesEnseignement.size(); i++) {
            if (unitesEnseignement.get(i).getCode() == code) {
                unitesEnseignement.set(i, updatedUE);
                return true;
            }
        }
        return false;
    }

    // Récupérer toutes les UE
    public List<UniteEnseignement> getListeUE() {
        return unitesEnseignement;
    }
}
