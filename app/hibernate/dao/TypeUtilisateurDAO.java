package hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import hibernate.model.TypeUtilisateur;

public class TypeUtilisateurDAO {

	public static TypeUtilisateur findById(Long id) {
		TypeUtilisateur tu = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			Query q = null;
			q = BDDUtils.getCurrentSession().createQuery(
					"SELECT tu FROM TypeUtilisateur tu " +
					"WHERE tu.id = :id");
			q.setParameter("id", id);
			tu = (TypeUtilisateur) q.uniqueResult();
			BDDUtils.commit(isActive, tx);
		}
		catch(Exception ex) {
			System.out.println("Hibernate failure : "+ ex.getMessage());
			BDDUtils.rollback(isActive, tx);
		}
		return tu;
	}
	
	public static TypeUtilisateur getTypeUtilisateurByLibelle(String libelle) {
		TypeUtilisateur tu = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			Query q = null;
			q = BDDUtils.getCurrentSession().createQuery(
					"SELECT tu FROM TypeUtilisateur tu " +
					"WHERE tu.libelle = :libelle");
			q.setParameter("libelle", libelle);
			tu = (TypeUtilisateur) q.uniqueResult();
			BDDUtils.commit(isActive, tx);
		}
		catch(Exception ex) {
			System.out.println("Hibernate failure : "+ ex.getMessage());
			BDDUtils.rollback(isActive, tx);
		}
		return tu;
	}
	
	@SuppressWarnings("unchecked")
	public static List<TypeUtilisateur> getAllTypeUtilisateurs() {
		List<TypeUtilisateur> ltu = null;
		Transaction tx = null;
		boolean isActive = BDDUtils.getTransactionStatus();
		try {
			tx = BDDUtils.beginTransaction(isActive);
			Query q = null;
			q = BDDUtils.getCurrentSession().createQuery("SELECT tu FROM TypeUtilisateur tu");
			ltu = (List<TypeUtilisateur>) q.list();
			BDDUtils.commit(isActive, tx);
		}
		catch(Exception ex) {
			System.out.println("Hibernate failure : "+ ex.getMessage());
			BDDUtils.rollback(isActive, tx);
		}
		return ltu;
	}
}
