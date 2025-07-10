package uce.edu.web.api.service.mapper;

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

}
