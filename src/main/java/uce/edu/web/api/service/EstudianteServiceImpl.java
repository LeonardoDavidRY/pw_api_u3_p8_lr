package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IEstudianteRepo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

@ApplicationScoped
public class EstudianteServiceImpl implements IEstudianteService {

    @Inject
    private IEstudianteRepo estudianteRepo;

    @Override
    public Estudiante buscarPorId(Integer id) {
        Estudiante e = this.estudianteRepo.seleccionarPorId(id);
        return e;
    }

    @Override
    public List<Estudiante> buscarTodos(String genero) {
        return this.estudianteRepo.seleccionarTodos(genero);
    }

    @Override
    public void borrarPorId(Integer id) {
        this.estudianteRepo.borrarPorId(id);
    }

    @Override
    public void guardar(EstudianteTo estudiante) {
        Estudiante est = EstudianteMapper.toEntity(estudiante);
        this.estudianteRepo.insertar(est);
    }

    @Override
    public void actualizarPorId(EstudianteTo estudiante) {
        Estudiante est = EstudianteMapper.toEntity(estudiante);
        this.estudianteRepo.actualizarPorId(est);
    }

    @Override
    public void actualizarParcialPorId(EstudianteTo estudiante, Integer id) {
        Estudiante est = this.estudianteRepo.seleccionarPorId(id);
        EstudianteMapper.updateEntityFromTo(est, estudiante);
        this.estudianteRepo.actualizarParcialPorId(est);
    }

}
