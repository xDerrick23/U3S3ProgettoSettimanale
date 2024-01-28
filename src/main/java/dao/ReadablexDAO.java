package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Libro;
import entities.Readablex;
import entities.Rivista;

public class ReadablexDAO {

	private EntityManager em;

	public ReadablexDAO(EntityManager em) {
		this.em = em;
	}

	public void aggiungiElementoCatalogo(Readablex readablex) {
	    EntityTransaction transaction = em.getTransaction();
	    try {
	        transaction.begin();
	        em.persist(readablex);
	        transaction.commit();
	    } catch (Exception e) {
	        transaction.rollback();
	        throw e;
	    }
	}

	public void rimuoviElementoCatalogo(Long isbn) {
	    EntityTransaction transaction = em.getTransaction();
	    try {
	        transaction.begin();
	        Readablex readablex = em.find(Readablex.class, isbn);
	        if (readablex != null) {
	            em.remove(readablex);
	        }
	        transaction.commit();
	        System.out.println("Elemento cancellato con successo");
	    } catch (Exception e) {
	        transaction.rollback();
	        throw e;
	    }
	}

	public Readablex ricercaPerISBN(Long isbn) {
		Readablex readablex = em.find(Readablex.class, isbn);
		if (readablex instanceof Libro) {
			return (Libro) readablex;
		} else if (readablex instanceof Rivista) {
			return (Rivista) readablex;
		} else {
			return null;
		}

	}
	public List<Readablex> ricercaPerAnnoPubblicazione(int anno) {
	    return em.createQuery("SELECT r FROM Readablex r WHERE r.annoPubblicazione = :anno", Readablex.class)
	            .setParameter("anno", anno)
	            .getResultList();
	}

	public List<Readablex> ricercaPerAutore(String autore) {
	    return em.createQuery("SELECT r FROM Readablex r WHERE r.autore = :autore", Readablex.class)
	            .setParameter("autore", autore)
	            .getResultList();
	}

	public List<Readablex> ricercaPerTitolo(String titolo) {
	    return em.createQuery("SELECT r FROM Readablex r WHERE r.titolo LIKE CONCAT('%', :titolo, '%')", Readablex.class)
	            .setParameter("titolo", titolo)
	            .getResultList();
	}
}
