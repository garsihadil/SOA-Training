package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnsRestApi {
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    //getListUE
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUe() {
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUe(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201).entity("Created").build();
        }
        else {
            return Response.status(400).entity("Erreur").build();
        }
    }

    @Path("/delete/{code}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUe(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200).entity("Deleted").build();
        }
        else {
            return Response.status(404).entity("Not found").build();
        }
    }
}