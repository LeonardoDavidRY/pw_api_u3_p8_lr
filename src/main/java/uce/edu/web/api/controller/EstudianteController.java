package uce.edu.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
public class EstudianteController extends BaseControllador {

    @Inject
    private IEstudianteService estudianteService;
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar estudiante por ID", description = "Este endpoint permite consultar un estudiante por su ID.")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {

        EstudianteTo estu = this.estudianteService.buscarPorId(id, uriInfo);

        return Response.status(Response.Status.OK)
                .entity(estu)
                .build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar estudiante", description = "Consulta todos los estudiantes registrados en el sistema")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        return Response.status(Response.Status.OK)
                .entity(this.estudianteService.buscarTodos(genero)).build();
    }

    @POST
    @Path("")
    public Response guardar(@RequestBody Estudiante estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(Response.Status.CREATED)
                .entity("{\"mensaje\": \"Estudiante guardado exitosamente\"}")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Estudiante actualizado exitosamente\"}")
                .build();
    }

    /* 
    @PATCH
    @Path("/{id}")
    public Response actualizarParcialPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        Estudiante e = this.estudianteService.buscarPorId(id);
        if (estudiante.getApellido() != null) {
            e.setApellido(estudiante.getApellido());
        }
        if (estudiante.getNombre() != null) {
            e.setNombre(estudiante.getNombre());
        }
        if (estudiante.getFechaNacimiento() != null) {
            e.setFechaNacimiento(estudiante.getFechaNacimiento());
        }
        this.estudianteService.actualizarParcialPorId(e);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Estudiante actualizado parcialmente exitosamente\"}")
                .build();
    }
     */
    @DELETE
    @Path("/{id}")
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT)
                .entity("{\"mensaje\": \"Estudiante eliminado exitosamente\"}")
                .build();
    }

    @GET
    @Path("/{id}/hijos")
    @Operation(summary = "Consultar hijos de un estudiante", description = "Consulta los hijos de un estudiante por su ID")
    public List<Hijo> obtenerHijosPorId(@PathParam("id") Integer id) {
        Hijo h1 = new Hijo();
        h1.setNombre("Sebastian");
        Hijo h2 = new Hijo();
        h2.setNombre("Andres");

        List<Hijo> hijos = new ArrayList<>();
        hijos.add(h1);
        hijos.add(h2);
        return hijos;
    }

}
