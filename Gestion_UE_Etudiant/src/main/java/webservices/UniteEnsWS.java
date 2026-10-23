package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnsWS {

    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    // 1. Création d'une nouvelle unité d'enseignement
    //    POST /rest/ue/add
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUE(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).entity("object added").build();
        }
        return Response.status(400).entity("object not added").build();
    }

    // 2. Récupération de la liste de toutes les unités d'enseignement
    //    GET /rest/ue/list
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200).entity(helper.getListeUE()).build();
    }

    // 3. Récupération de la liste des unités d'enseignement d'un semestre spécifique
    //    GET /rest/ue/semestre/{semestre}
    @Path("/semestre/{semestre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBySemestre(@PathParam("semestre") int semestre) {
        return Response.status(200).entity(helper.getUEBySemestre(semestre)).build();
    }

    // 4. Suppression d'une unité d'enseignement ayant un identifiant spécifique
    //    DELETE /rest/ue/delete/{id}
    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("id") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200).entity("object deleted").build();
        }
        return Response.status(404).entity("object not found").build();
    }

    // 5. Modification d'une unité d'enseignement ayant un identifiant spécifique
    //    PUT /rest/ue/update/{id}
    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(UniteEnseignement ue, @PathParam("id") int code) {
        ue.setCode(code); // le code du chemin fait foi
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(200).entity("object updated").build();
        }
        return Response.status(404).entity("object not found").build();
    }

    // 6. Récupération d'une unité d'enseignement ayant un code donné
    //    GET /rest/ue/code/{code}
    @Path("/code/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCode(@PathParam("code") int code) {
        UniteEnseignement ue = helper.getUEByCode(code);
        if (ue != null) {
            return Response.status(200).entity(ue).build();
        }
        return Response.status(404).build();
    }
}
