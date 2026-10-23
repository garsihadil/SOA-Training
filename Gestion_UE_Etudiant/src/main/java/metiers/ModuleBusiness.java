package metiers;

import entities.Module;
import entities.UniteEnseignement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ModuleBusiness {

    private static final UniteEnseignementBusiness uniteEnseignementBusiness = new UniteEnseignementBusiness();

    // Liste partagée, initialisée une seule fois (voir UniteEnseignementBusiness)
    private static final List<Module> modules = new CopyOnWriteArrayList<>();

    static {
        modules.add(new Module("M101", "Algorithmique", 3, 30, Module.TypeModule.PROFESSIONNEL,
                uniteEnseignementBusiness.getUEByCode(1)));
        modules.add(new Module("M102", "Base de données", 2, 20, Module.TypeModule.PROFESSIONNEL,
                uniteEnseignementBusiness.getUEByCode(1)));
        modules.add(new Module("M201", "Communication", 1, 15, Module.TypeModule.TRANSVERSAL,
                uniteEnseignementBusiness.getUEByCode(2)));
    }

    public ModuleBusiness() {
    }

    // Ajouter un module : l'UE associée doit exister et le matricule doit être unique
    public boolean addModule(Module module) {
        if (module == null || module.getMatricule() == null || module.getUniteEnseignement() == null)
            return false;
        if (modules.contains(module)) // utilise equals() (matricule)
            return false;
        UniteEnseignement ue = uniteEnseignementBusiness.getUEByCode(module.getUniteEnseignement().getCode());
        if (ue == null)
            return false;
        module.setUniteEnseignement(ue);
        return modules.add(module);
    }

    // Récupérer un module par son matricule
    public Module getModuleByMatricule(String matricule) {
        for (Module m : modules) {
            if (m.getMatricule().equals(matricule)) {
                return m;
            }
        }
        return null;
    }

    // Récupérer les modules par type
    public List<Module> getModulesByType(Module.TypeModule type) {
        List<Module> result = new ArrayList<>();
        for (Module m : modules) {
            if (m.getType() == type) {
                result.add(m);
            }
        }
        return result;
    }

    // Mettre à jour un module (le matricule du chemin fait foi ; sans UE dans le corps, l'UE actuelle est conservée)
    public boolean updateModule(String matricule, Module updatedModule) {
        for (int i = 0; i < modules.size(); i++) {
            Module current = modules.get(i);
            if (current.getMatricule().equals(matricule)) {
                updatedModule.setMatricule(matricule);
                if (updatedModule.getUniteEnseignement() == null) {
                    updatedModule.setUniteEnseignement(current.getUniteEnseignement());
                } else {
                    UniteEnseignement ue = uniteEnseignementBusiness
                            .getUEByCode(updatedModule.getUniteEnseignement().getCode());
                    if (ue == null)
                        return false;
                    updatedModule.setUniteEnseignement(ue);
                }
                modules.set(i, updatedModule);
                return true;
            }
        }
        return false;
    }

    // Supprimer un module
    public boolean deleteModule(String matricule) {
        return modules.removeIf(m -> m.getMatricule().equals(matricule));
    }

    // Récupérer tous les modules
    public List<Module> getAllModules() {
        return modules;
    }

    // Récupérer les modules d'une UE spécifique
    public List<Module> getModulesByUE(UniteEnseignement ue) {
        List<Module> result = new ArrayList<>();
        for (Module m : modules) {
            if (m.getUniteEnseignement() != null &&
                    m.getUniteEnseignement().getCode() == ue.getCode()) {
                result.add(m);
            }
        }
        return result;
    }
}
