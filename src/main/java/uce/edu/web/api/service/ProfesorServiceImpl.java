package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

  
    @Inject
    private IProfesorRepo profesorRepo;

    @Override
    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo) {

        Profesor profesor = this.profesorRepo.seleccionarPorId(id);
        ProfesorTo profesorTo = new ProfesorTo(profesor.getId(), profesor.getNombre(), profesor.getApellido(),
                profesor.getTitulo(), profesor.getFechaIngreso(), uriInfo);

        return profesorTo;
    }
    
    @Override
    public List<Profesor> buscarTodos(String titulo) {
        return this.profesorRepo.seleccionarTodos(titulo);
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.profesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.profesorRepo.actualizarParcialPorId(profesor);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.profesorRepo.borrarPorId(id);
    }

    @Override
    public void guardar(Profesor profesor) {
        this.profesorRepo.insertar(profesor);
    }

}
