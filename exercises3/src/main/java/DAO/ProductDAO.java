package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Hibernate.HibernateUtil;
import Model.Product;

public class ProductDAO {
	private Session session = null;
	
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}

	public List<Product> getAll() {
		session = HibernateUtil.getFactory().openSession();

		try {
			session.beginTransaction();

			List<Product> products = session.createQuery("from Product").getResultList();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
	
	public Product getById(int id) {
		session = HibernateUtil.getFactory().openSession();

		try {
			session.beginTransaction();

			Query<Product> query = session.createQuery("from Product p where p.id =: id");
			query.setParameter("id", id);
			
			Product product = query.uniqueResult();
			
			session.getTransaction().commit();
			return product;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
	
	public boolean save(Product product) {
		session = HibernateUtil.getFactory().openSession();

		try {
			session.beginTransaction();
			
			session.save(product);
			
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean update(Product product) {
		session = HibernateUtil.getFactory().openSession();

		try {
			session.beginTransaction();
			
			session.update(product);
			
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
	}
	
	public boolean remove(int id) {
		session = HibernateUtil.getFactory().openSession();

		try {
			session.beginTransaction();

			Query<Product> query = session.createQuery("delete from Product p where p.id =: id");
			query.setParameter("id", id);
			
			int result = query.executeUpdate();
			
			session.getTransaction().commit();
			return (result == 0) ? false : true;
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
	}
}
