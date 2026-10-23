package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleWS {

    private final ModuleBusiness helper = new ModuleBusiness();
    private final UniteEnseignementBusiness ueHelper = new UniteEnseignementBusiness();

    // 1. Création d'un nouveau module (l'UE associée est donnée par son code dans le corps JSON)
    //    POST /rest/module/add
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {
        if (helper.addModule(module)) {
            return Response.status(201).entity("Module added").build();
        }
        return Response.status(400).entity("Module not added").build();
    }

    // 2. Récupération de la liste de tous les modules
    //    GET /rest/module/list
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    // 3. Récupération d'un module ayant un matricule spécifique
    //    GET /rest/module/matricule/{matricule}
    @Path("/matricule/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByMatricule(@PathParam("matricule") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);
        if (module != null) {
            return Response.status(200).entity(module).build();
        }
        return Response.status(404).build();
    }

    // 4. Suppression d'un module ayant un matricule spécifique
    //    DELETE /rest/module/delete/{matricule}
    @Path("/delete/{matricule}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200).entity("Module deleted").build();
        }
        return Response.status(404).entity("Module not found").build();
    }

    // 5. Modification d'un module ayant un matricule spécifique
    //    PUT /rest/module/update/{matricule}
    @Path("/update/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(Module module, @PathParam("matricule") String matricule) {
        if (helper.updateModule(matricule, module)) {
            return Response.status(200).entity("Module updated").build();
        }
        return Response.status(404).entity("Module not found (or unknown UE)").build();
    }

    // 6. Récupération de tous les modules associés à une UE identifiée par son code (codeUE)
    //    GET /rest/module/ue/{codeUE}
    @Path("/ue/{codeUE}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByUE(@PathParam("codeUE") int codeUE) {
        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);
        if (ue == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(helper.getModulesByUE(ue)).build();
    }
}
