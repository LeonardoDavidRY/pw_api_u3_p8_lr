package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Hijo;

@ApplicationScoped
@Transactional
public class HijoRepoImpl implements HijoRepo {

    @PersistenceContext
    private EntityManager entityManager;

    // Implementación de los métodos definidos en la interfaz HijoRepo
    @Override
    public List<Hijo> buscarPorEstudianteId(Integer id) {
        TypedQuery<Hijo> query = this.entityManager.createQuery(
            "SELECT h FROM Hijo h WHERE h.estudiante.id = :id", Hijo.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

}
