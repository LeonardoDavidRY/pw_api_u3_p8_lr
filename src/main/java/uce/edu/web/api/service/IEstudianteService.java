package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public interface IEstudianteService {

    public Estudiante buscarPorId(Integer id);

    public List<Estudiante> buscarTodos(String genero);

    public void actualizarPorId(EstudianteTo estudiante);

    public void actualizarParcialPorId(EstudianteTo estudiante, Integer id);

    public void borrarPorId(Integer id);

    public void guardar(EstudianteTo estudiante);


}
