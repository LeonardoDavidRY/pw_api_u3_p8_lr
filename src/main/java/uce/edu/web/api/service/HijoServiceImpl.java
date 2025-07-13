package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.HijoRepo;
import uce.edu.web.api.repository.modelo.Hijo;

@ApplicationScoped
public class HijoServiceImpl implements HijoService {

    @Inject
    private HijoRepo hijoRepo;

    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {
        return hijoRepo.buscarPorEstudianteId(id);
    }

    @Override
    public List<Hijo> buscarPorProfesorId(Integer id) {
        return hijoRepo.buscarPorProfesorId(id);
    }

}
