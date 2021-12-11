package es.wuolahpop.images.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import es.wuolahpop.data.Imagenenbbdd;


public class ImagenenbbddManager {

	private EntityManagerFactory emf;
	
	public ImagenenbbddManager(String unidadDePersistencia)
	{
		emf = Persistence.createEntityManagerFactory(unidadDePersistencia);
	}
	
	public ImagenenbbddManager(EntityManagerFactory factory)
	{
		emf = factory;
	}
	
	
	public String create(Imagenenbbdd imagen) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(imagen);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}
	
	// Esta anotación es para quitar el warning avisandonos que es está
	// haciendo una conversión de List a List<Imagenenbbdd> y puede no ser válida
	@SuppressWarnings("unchecked")
	public List<Imagenenbbdd> findAll() {
		List<Imagenenbbdd> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Imagenenbbdd.findAll",Imagenenbbdd.class);
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	@SuppressWarnings("unchecked")
	public List<Imagenenbbdd> findBySimilarTitle(String titulo) {
		List<Imagenenbbdd> resultado;
		EntityManager em = emf.createEntityManager();
		try {
			Query query = em.createNamedQuery("Imagenenbbdd.findBySimilarTitle",Imagenenbbdd.class);
			// Atención: Se neceista agregar el % porque se usa una consutla con like (buscar en google)
			query.setParameter("titulo","%"+titulo+"%");
			resultado = query.getResultList();
		} finally {
			em.close();
		}
		return resultado;

	}
	
	public Imagenenbbdd findById(int id) {
		Imagenenbbdd resultado;
		EntityManager em = emf.createEntityManager();
		try {
			resultado  = em.find(Imagenenbbdd.class, id);
		} finally {
			em.close();
		}
		return resultado;
	}
}
