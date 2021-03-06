package hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import hibernate.model.Genre;

public class GenreDAO {

	public static Genre findById(Long id) {
		Genre g = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			Query q = null;
			q = BDDUtils.getCurrentSession().createQuery(
					"SELECT g FROM Genre g " +
					"WHERE g.id = :id");
			q.setParameter("id", id);
			g = (Genre) q.uniqueResult();
			BDDUtils.commit(isActive, tx);
		}
		catch(Exception ex) {
			System.out.println("Hibernate failure : "+ ex.getMessage());
			BDDUtils.rollback(isActive, tx);
		}
		return g;
	}
	
	public static Genre getGenreByLibelle(String libelle) {
		Genre g = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			Query q = null;
			q = BDDUtils.getCurrentSession().createQuery(
					"SELECT g FROM Genre g " +
					"WHERE LOWER(g.genre) = :libelle");
			q.setParameter("libelle", libelle.toLowerCase());
			g = (Genre) q.uniqueResult();
			BDDUtils.commit(isActive, tx);
		}
		catch(Exception ex) {
			System.out.println("Hibernate failure : "+ ex.getMessage());
			BDDUtils.rollback(isActive, tx);
		}
		return g;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Genre> getAllGenres() {
		List<Genre> lg = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			Query q = null;
			q = BDDUtils.getCurrentSession().createQuery("SELECT g FROM Genre g");
			lg = (List<Genre>) q.list();
			BDDUtils.commit(isActive, tx);
		}
		catch(Exception ex) {
			System.out.println("Hibernate failure : "+ ex.getMessage());
			BDDUtils.rollback(isActive, tx);
		}
		return lg;
	}
}
