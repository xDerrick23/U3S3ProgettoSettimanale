package dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Prestito;

public class PrestitoDAO {
    private EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiPrestito(Prestito prestito) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(prestito);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
        LocalDate oggi = LocalDate.now();
        return em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("oggi", oggi)
                .getResultList();
    }
}
