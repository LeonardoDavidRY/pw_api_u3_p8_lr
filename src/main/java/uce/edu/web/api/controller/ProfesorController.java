package uce.edu.web.api.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar profesor por ID", description = "Busca y retorna un profesor específico por su identificador único")
    public Response consultarPorId(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK)
                .entity(this.profesorService.buscarPorId(id))
                .build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar todos los profesores", description = "Retorna la lista completa de profesores registrados en el sistema")
    public Response consultarTodos(@QueryParam("titulo") String titulo,
            @QueryParam("carrera") String carrera) {
            System.out.println(carrera);
        return Response.status(Response.Status.OK)
                .entity(this.profesorService.buscarTodos(titulo))
                .build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crear nuevo profesor", description = "Registra un nuevo profesor en el sistema")
    public Response guardar(Profesor profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Profesor guardado exitosamente\"}")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar profesor completo", description = "Actualiza todos los campos de un profesor existente")
    public Response actualizarPorId(Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Profesor actualizado exitosamente\"}")
                .build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar profesor parcial", description = "Actualiza solo los campos específicos de un profesor existente")
    public Response actualizarParcialPorId(Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        Profesor p = this.profesorService.buscarPorId(id);
        if (profesor.getApellido() != null) {
            p.setApellido(profesor.getApellido());
        }
        if (profesor.getNombre() != null) {
            p.setNombre(profesor.getNombre());
        }
        if (profesor.getTitulo() != null) {
            p.setTitulo(profesor.getTitulo());
        }
        if (profesor.getFechaIngreso() != null) {
            p.setFechaIngreso(profesor.getFechaIngreso());
        }
        this.profesorService.actualizarParcialPorId(p);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Profesor actualizado parcialmente exitosamente\"}")
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Eliminar profesor", description = "Elimina un profesor del sistema por su identificador")
    public Response eliminarPorId(@PathParam("id") Integer id) {
        this.profesorService.eliminarPorId(id);
        return Response.status(Response.Status.OK)
                .entity("{\"mensaje\": \"Profesor eliminado exitosamente\"}")
                .build();
    }
}
