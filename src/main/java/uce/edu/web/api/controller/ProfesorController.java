package uce.edu.web.api.controller;

import java.util.List;

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
import uce.edu.web.api.service.HijoService;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.HijoMapper;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.HijoTo;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private HijoService hijoService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar profesor por ID", description = "Busca y retorna un profesor específico por su identificador único")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo profesorTo = ProfesorMapper.toTo(
                this.profesorService.buscarPorId(id));
        profesorTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK)
                .entity(profesorTo)
                .build();
    }

    @GET
    @Path("")
    @Operation(summary = "Consultar todos los profesores", description = "Retorna la lista completa de profesores registrados en el sistema")
    public Response consultarTodos(@QueryParam("titulo") String titulo,
            @QueryParam("carrera") String carrera) {
        System.out.println(carrera);
        List<ProfesorTo> profesoresTo = ProfesorMapper.toToList(
                this.profesorService.buscarTodos(titulo));
        return Response.status(Response.Status.OK)
                .entity(profesoresTo)
                .build();
    }

    @POST
    @Path("")
    @Operation(summary = "Crear nuevo profesor", description = "Registra un nuevo profesor en el sistema")
    public Response guardar(@RequestBody ProfesorTo profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED)
                .entity("{\"mensaje\": \"Profesor guardado exitosamente\"}")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar profesor completo", description = "Actualiza todos los campos de un profesor existente")
    public Response actualizarPorId(@RequestBody ProfesorTo profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Profesor actualizado exitosamente\"}")
                .build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Actualizar profesor parcial", description = "Actualiza solo los campos específicos de un profesor existente")
    public Response actualizarParcialPorId(@RequestBody ProfesorTo profesor, @PathParam("id") Integer id) {
        this.profesorService.actualizarParcialPorId(profesor, id);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Profesor actualizado parcialmente exitosamente\"}")
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar profesor", description = "Elimina un profesor del sistema por su identificador")
    public Response eliminarPorId(@PathParam("id") Integer id) {
        this.profesorService.eliminarPorId(id);
        return Response.status(Response.Status.NO_CONTENT)
                .entity("{\"mensaje\": \"Profesor eliminado exitosamente\"}")
                .build();
    }

    @GET
    @Path("/{id}/hijos")
    @Operation(summary = "Consultar hijos de un profesor", description = "Retorna los hijos asociados a un profesor por su ID")
    public List<HijoTo> obtenerHijosPorId(@PathParam("id") Integer id) {
        return HijoMapper.toTOList(this.hijoService.buscarPorProfesorId(id));
    }
}
