package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

    @Inject
    private IProfesorRepo profesorRepo;

    @Override
    public Profesor buscarPorId(Integer id) {
        return this.profesorRepo.seleccionarPorId(id);
    }
    
    @Override
    public List<Profesor> buscarTodos(String titulo) {
        return this.profesorRepo.seleccionarTodos(titulo);
    }

    @Override
    public void actualizarPorId(ProfesorTo profesorTo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorId(ProfesorTo profesorTo, Integer id) {
        Profesor profesor = this.profesorRepo.seleccionarPorId(id);
        ProfesorMapper.updateEntityFromTo(profesor, profesorTo);
        this.profesorRepo.actualizarParcialPorId(profesor);
    }

    @Override
    public void eliminarPorId(Integer id) {
        this.profesorRepo.borrarPorId(id);
    }

    @Override
    public void guardar(ProfesorTo profesorTo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorRepo.insertar(profesor);
    }

}
