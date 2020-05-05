package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import domain.Product;
import domain.Seller;

public class HibernateDAO {
	private static HibernateDAO instance;
	private static Session session;
	
	public static void main(String[] args) throws Exception {
		session = getInstance().getSession();
		
		System.out.println(returnProduct((long) 40).getName());
		
		 
	}
	
	public static HibernateDAO getInstance() {
	    if (null == instance) {
	      instance = new HibernateDAO();
	      getInstance().getSession();
	    }
	    return instance;
	  }
	
	public void closeSession() {
		  getSession().close();
		}
	
	public static List<Seller> returnSellerList() {
		org.hibernate.Query query = session.createQuery("FROM Seller");
		List<Seller> sellers = query.list();		
		return sellers;
	}
	
	public static Seller returnSeller(Long id) {
		org.hibernate.Query query = session.createQuery("FROM Seller where id = :id_seller");
		query.setParameter("id_seller", id);
		List<Seller> sellerlist = query.list();
		
		Seller seller = sellerlist.get(0);
		
		return seller;
	}
	
	public static Product returnProduct(Long id) {
		org.hibernate.Query query = session.createQuery("FROM Product where id = :id_product");
		query.setParameter("id_product", id);
		List<Product> productlist = query.list();
		
		Product product = productlist.get(0);
		
		return product;
	}
	
	public static List<Product> returnProductList() {
		org.hibernate.Query query = session.createQuery("FROM Product");
		List<Product> products = query.list();	
		return products;
	}
	
	public static void insertObject(Object object) {
		 Transaction transaction = session.beginTransaction();
		 session.saveOrUpdate(object);
		 transaction.commit();		
	}
	
	public static void updateObject(Object object) {
		 Transaction transaction = session.beginTransaction();
		 session.saveOrUpdate(object);
		 transaction.commit();
	}
	
	public static void deleteObject(Object object) {
		 Transaction transaction = session.beginTransaction();
		 session.delete(object);;
		 transaction.commit();
	}
	
	public Session getSession() {
	    if (null == session) {
	      Configuration configuration = new Configuration();
	      configuration.setProperty(Environment.DRIVER, "org.postgresql.Driver");
	      configuration.setProperty(Environment.URL,
	      "jdbc:postgresql://localhost:5432/Javabase");
	      configuration.setProperty(Environment.USER, "postgres");
	      configuration.setProperty(Environment.PASS, "0147258");
	      configuration.setProperty(Environment.DIALECT, 
	      "org.hibernate.dialect.PostgreSQLDialect");
	      configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
	      configuration.setProperty(Environment.SHOW_SQL, "true");
	      configuration.addAnnotatedClass(Product.class);
	      configuration.addAnnotatedClass(Seller.class);
	      StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	      serviceRegistryBuilder.applySettings(configuration.getProperties());
	      ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	      SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	      session = sessionFactory.openSession();
	    }
	    return session;
	  }



}
