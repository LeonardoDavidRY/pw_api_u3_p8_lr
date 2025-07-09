package uce.edu.web.api.service;

import java.util.List;

import uce.edu.web.api.repository.modelo.Hijo;

public interface HijoService {
    
    public List<Hijo> buscarPorEstudianteId(Integer id);

}
