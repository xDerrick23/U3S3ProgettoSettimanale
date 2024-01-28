package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Prestito;
import entities.Utente;

public class UtenteDAO {
    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void aggiungiUtente(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(utente);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
    
    public Utente ricercaUtenteDaNumeroTessera(Long numeroTessera) {
        return em.find(Utente.class, numeroTessera);
    }

    public List<Prestito> ricercaPrestitiUtente(Long numeroTessera) {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera", Prestito.class)
                .setParameter("numeroTessera", numeroTessera)
                .getResultList();
    }
}
