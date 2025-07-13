package uce.edu.web.api.service.mapper;

import java.util.List;

import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

public class ProfesorMapper {

    public static ProfesorTo toTo(Profesor profesor) {
        ProfesorTo profesorTo = new ProfesorTo();
        profesorTo.setId(profesor.getId());
        profesorTo.setNombre(profesor.getNombre());
        profesorTo.setApellido(profesor.getApellido());
        profesorTo.setTitulo(profesor.getTitulo());
        profesorTo.setFechaIngreso(profesor.getFechaIngreso());
        
        return profesorTo;
    }

    public static Profesor toEntity(ProfesorTo profesorTo) {
        Profesor profesor = new Profesor();
        profesor.setId(profesorTo.getId());
        profesor.setNombre(profesorTo.getNombre());
        profesor.setApellido(profesorTo.getApellido());
        profesor.setTitulo(profesorTo.getTitulo());
        profesor.setFechaIngreso(profesorTo.getFechaIngreso());

        return profesor;
    }

    public static List<ProfesorTo> toToList(List<Profesor> profesores) {
        return profesores.stream().map(ProfesorMapper::toTo).toList();
    }

    public static void updateEntityFromTo(Profesor entity, ProfesorTo to) {
        if (to.getApellido() != null) {
            entity.setApellido(to.getApellido());
        }
        if (to.getNombre() != null) {
            entity.setNombre(to.getNombre());
        }
        if (to.getTitulo() != null) {
            entity.setTitulo(to.getTitulo());
        }
        if (to.getFechaIngreso() != null) {
            entity.setFechaIngreso(to.getFechaIngreso());
        }
    }
}
