package si.fri.prpo.nakupovalniseznami.api.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.izjeme.NeveljavenUporabnikIdIzjema;
import si.fri.prpo.nakupovalniseznami.zrna.UporabnikiZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
@Path("uporabniki")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UporabnikiVir {

    @Context
    protected UriInfo uriInfo;

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @Operation(description = "Vrne seznam uporabnikov.",
                summary = "Seznam uporabnikov",
                tags = "uporabniki",
                responses = {
                    @ApiResponse(responseCode = "200",
                                description = "Seznam uporabnikov",
                                content = @Content(
                                        array = @ArraySchema(
                                                schema = @Schema(implementation = Uporabnik.class)
                                        )
                                ),
                                headers = {@Header(name = "X-Total-Count", description = "Stevilo vrnjenih uporabnikov")}
                )})
    @GET
    @CrossOrigin
    public Response pridobiUporabnike() {
        //return Response.ok(uporabnikiZrno.pridobiUporabnike()).build();

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long uporabnikiCount = uporabnikiZrno.pridobiUporabnikeCount(query);

        return Response
                .ok(uporabnikiZrno.pridobiUporabnike(query))
                .header("X-Total-Count", uporabnikiCount)
                .build();
    }

    @Operation(description = "Vrne podrobnosti uporabnika.",
                summary = "Podrobnosti uporabnika",
                tags = "uporabniki",
                responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Podrobnosti uporabnika",
                            content = @Content(
                                    schema = @Schema(implementation = Uporabnik.class)
                            )
                )})
    @GET
    @Path("{id}")
    @CrossOrigin
    public Response pridobiUporabnika(@Parameter(
                                        description = "Identifikator uporabnika za prikaz.",
                                        required = true) @PathParam("id") Integer id)
    {

        Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(id);

        if(uporabnik != null) {
            return Response.ok(uporabnik).build();
        } else {
            throw new NeveljavenUporabnikIdIzjema("Novega uporabnika ni mogoce najti. Neveljaven ID.");
            //return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

    @Operation(description = "Dodaj uporabnika.",
            summary = "Dodajanje uporabnika",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Uporabnik uspeno dodan."),
                    @ApiResponse(responseCode = "405", description = "Validacijska napaka.")
            })
    @POST
    @CrossOrigin
    public Response dodajUporabnika(@RequestBody(
                                        description = "DTO objekt za dodajanje uporabnikov.",
                                        required = true,
                                        content = @Content(
                                                schema = @Schema(implementation = Uporabnik.class)
                                        )) Uporabnik uporabnik)
    {

        // jax-rs bo poskrbel da se poslan json preslika v objekt
        return Response
                .status(Response.Status.CREATED)
                .entity(uporabnikiZrno.dodajUporabnika(uporabnik))
                .build();
    }

    @Operation(description = "Posodobi uporabnika.",
            summary = "Posadabljanje uporabnika",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Uporabnik uspeno posodobljen.")
            })
    @PUT
    @Path("{id}")
    @CrossOrigin
    public Response posodobiUporabnika(@Parameter(
                                        description = "Identifikator uporabnika za posodabljanje.",
                                        required = true) @PathParam("id") Integer id,
                                       @RequestBody(
                                        description = "DTO objekt za posodabljanje uporabnikov.",
                                        required = true,
                                        content = @Content(
                                                schema = @Schema(implementation = Uporabnik.class)
                                        )) Uporabnik uporabnik)
    {
        return Response
                .status(Response.Status.OK)
                .entity(uporabnikiZrno.posodobiUporabnika(id, uporabnik))
                .build();
    }

    @Operation(description = "Odstrani uporabnika.",
            summary = "Odstranjevanje uporabnika",
            tags = "uporabniki",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Uporabnik uspeno odstranjen."),
                    @ApiResponse(responseCode = "404", description = "Uporabnik ne obstaja.")
            })
    @DELETE
    @Path("{id}")
    @CrossOrigin
    public Response odstraniUporabnika(@Parameter(
                                        description = "Identifikator uporabnika za brisanje.",
                                        required = true) @PathParam("id") Integer id)
    {
        if(uporabnikiZrno.odstraniUporabnika(id)) {
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity(uporabnikiZrno.odstraniUporabnika(id))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
