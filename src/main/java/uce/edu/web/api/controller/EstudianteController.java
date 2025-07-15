package uce.edu.web.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.HijoService;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.mapper.HijoMapper;
import uce.edu.web.api.service.to.EstudianteTo;
import uce.edu.web.api.service.to.HijoTo;

@Path("/estudiantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private HijoService hijoService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar estudiante por ID", description = "Este endpoint permite consultar un estudiante por su ID.")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {

        EstudianteTo estu = EstudianteMapper.toTo(
                this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
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
        List<EstudianteTo> estudiantesTo = this.estudianteService.buscarTodos(genero).stream().map(EstudianteMapper::toTo).collect(Collectors.toList());
        return Response.status(Response.Status.OK)
                .entity(estudiantesTo)
                .build();
    }

    @POST
    @Path("")
    public Response guardar(@RequestBody EstudianteTo estudiante) {
        this.estudianteService.guardar(estudiante);
        return Response.status(Response.Status.CREATED)
                .entity("{\"mensaje\": \"Estudiante guardado exitosamente\"}")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarPorId(@RequestBody EstudianteTo estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.estudianteService.actualizarPorId(estudiante);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Estudiante actualizado exitosamente\"}")
                .build();
    }

    
    @PATCH
    @Path("/{id}")
    public Response actualizarParcialPorId(@RequestBody EstudianteTo estudiante, @PathParam("id") Integer id) {
        this.estudianteService.actualizarParcialPorId(estudiante, id);
         // Actualizar el ID en el objeto estudianteTo
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Estudiante actualizado parcialmente exitosamente\"}")
                .build();
    }
    
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
    public List<HijoTo> obtenerHijosPorId(@PathParam("id") Integer id) {

        return HijoMapper.toTOList(this.hijoService.buscarPorEstudianteId(id));
    }

}
