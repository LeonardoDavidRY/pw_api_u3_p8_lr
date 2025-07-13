package uce.edu.web.api.service.mapper;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public class EstudianteMapper {

    public static EstudianteTo toTo(Estudiante estudiante) {

        EstudianteTo estudianteTo = new EstudianteTo();
        estudianteTo.setId(estudiante.getId());
        estudianteTo.setNombre(estudiante.getNombre());
        estudianteTo.setApellido(estudiante.getApellido());
        estudianteTo.setFechaNacimiento(estudiante.getFechaNacimiento());
        estudianteTo.setGenero(estudiante.getGenero());     
        
        return estudianteTo;
    }

    public static Estudiante toEntity(EstudianteTo estudianteTo) {
        
        Estudiante estudiante = new Estudiante();
        estudiante.setId(estudianteTo.getId()); 
        estudiante.setNombre(estudianteTo.getNombre());
        estudiante.setApellido(estudianteTo.getApellido());
        estudiante.setFechaNacimiento(estudianteTo.getFechaNacimiento());

        return estudiante;
    }
    public static List<EstudianteTo> toToList(List<Estudiante> estudiantes) {
        return estudiantes.stream().map(EstudianteMapper::toTo).toList();
    }

    public static void updateEntityFromTo(Estudiante entity, EstudianteTo to) {
        if (to.getApellido() != null) {
            entity.setApellido(to.getApellido());
        }
        if (to.getNombre() != null) {
            entity.setNombre(to.getNombre());
        }
        if (to.getFechaNacimiento() != null) {
            entity.setFechaNacimiento(to.getFechaNacimiento());
        }
        if (to.getGenero() != null) {
            entity.setGenero(to.getGenero());
        }
    }

}
